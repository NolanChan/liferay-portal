/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.gradle.plugins.defaults;

import com.liferay.gradle.plugins.LiferayThemePlugin;
import com.liferay.gradle.plugins.cache.WriteDigestTask;
import com.liferay.gradle.plugins.defaults.internal.LiferayRelengPlugin;
import com.liferay.gradle.plugins.defaults.internal.util.FileUtil;
import com.liferay.gradle.plugins.defaults.internal.util.GradleUtil;
import com.liferay.gradle.plugins.defaults.internal.util.IncrementVersionClosure;
import com.liferay.gradle.plugins.defaults.tasks.ReplaceRegexTask;
import com.liferay.gradle.plugins.extensions.LiferayExtension;
import com.liferay.gradle.plugins.gulp.ExecuteGulpTask;
import com.liferay.gradle.plugins.util.PortalTools;
import com.liferay.gradle.util.copy.StripPathSegmentsAction;

import groovy.lang.Closure;

import java.io.File;
import java.io.IOException;

import java.util.Properties;
import java.util.concurrent.Callable;

import org.gradle.api.Action;
import org.gradle.api.GradleException;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.Dependency;
import org.gradle.api.artifacts.DependencySet;
import org.gradle.api.file.FileTree;
import org.gradle.api.logging.Logger;
import org.gradle.api.plugins.BasePlugin;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.MavenPlugin;
import org.gradle.api.tasks.Copy;
import org.gradle.api.tasks.TaskContainer;
import org.gradle.api.tasks.Upload;
import org.gradle.api.tasks.bundling.Zip;

/**
 * @author Andrea Di Giorgi
 */
public class LiferayThemeDefaultsPlugin implements Plugin<Project> {

	public static final String EXPAND_FRONTEND_CSS_COMMON_TASK_NAME =
		"expandFrontendCSSCommon";

	public static final String FRONTEND_CSS_COMMON_CONFIGURATION_NAME =
		"frontendCSSCommon";

	public static final String WRITE_PARENT_THEMES_DIGEST_TASK_NAME =
		"writeParentThemesDigest";

	public static final String ZIP_RESOURCES_IMPORTER_ARCHIVES_TASK_NAME =
		"zipResourcesImporterArchives";

	@Override
	public void apply(Project project) {
		GradleUtil.applyPlugin(project, LiferayThemePlugin.class);

		applyPlugins(project);

		// GRADLE-2427

		addTaskInstall(project);

		applyConfigScripts(project);

		LiferayOSGiDefaultsPlugin.configureRepositories(project);

		Configuration frontendCSSCommonConfiguration =
			addConfigurationFrontendCSSCommon(project);

		Project frontendThemeStyledProject = getThemeProject(
			project, "frontend-theme-styled");
		Project frontendThemeUnstyledProject = getThemeProject(
			project, "frontend-theme-unstyled");

		WriteDigestTask writeDigestTask = addTaskWriteParentThemesDigest(
			project, frontendThemeStyledProject, frontendThemeUnstyledProject);

		Copy expandFrontendCSSCommonTask = addTaskExpandFrontendCSSCommon(
			project, frontendCSSCommonConfiguration);
		final ReplaceRegexTask updateVersionTask = addTaskUpdateVersion(
			project, writeDigestTask);

		File resourcesImporterArchivesDir = project.file(
			"src/WEB-INF/src/resources-importer");
		File resourcesImporterExpandedArchivesDir = project.file(
			"resources-importer");

		Task zipResourcesImporterArchivesTask = addTaskZipDirectories(
			project, ZIP_RESOURCES_IMPORTER_ARCHIVES_TASK_NAME,
			resourcesImporterExpandedArchivesDir, resourcesImporterArchivesDir,
			"lar");

		configureDeployDir(project);
		configureProject(project);

		configureTasksExecuteGulp(
			project, expandFrontendCSSCommonTask,
			zipResourcesImporterArchivesTask, frontendThemeStyledProject,
			frontendThemeUnstyledProject);

		project.afterEvaluate(
			new Action<Project>() {

				@Override
				public void execute(Project project) {
					GradleUtil.setProjectSnapshotVersion(project);

					// setProjectSnapshotVersion must be called before
					// configureTaskUploadArchives, because the latter one needs
					// to know if we are publishing a snapshot or not.

					configureTaskUploadArchives(project, updateVersionTask);
				}

			});
	}

	protected Configuration addConfigurationFrontendCSSCommon(
		final Project project) {

		Configuration configuration = GradleUtil.addConfiguration(
			project, FRONTEND_CSS_COMMON_CONFIGURATION_NAME);

		configuration.defaultDependencies(
			new Action<DependencySet>() {

				@Override
				public void execute(DependencySet dependencySet) {
					addDependenciesFrontendCSSCommon(project);
				}

			});

		configuration.setDescription(
			"Configures com.liferay.frontend.css.common for compiling the " +
				"theme.");
		configuration.setTransitive(false);
		configuration.setVisible(false);

		return configuration;
	}

	protected void addDependenciesFrontendCSSCommon(Project project) {
		String version = PortalTools.getVersion(
			project, _FRONTEND_COMMON_CSS_NAME);

		GradleUtil.addDependency(
			project, FRONTEND_CSS_COMMON_CONFIGURATION_NAME, "com.liferay",
			_FRONTEND_COMMON_CSS_NAME, version, false);
	}

	protected Copy addTaskExpandFrontendCSSCommon(
		final Project project,
		final Configuration frontendCSSCommonConfguration) {

		Copy copy = GradleUtil.addTask(
			project, EXPAND_FRONTEND_CSS_COMMON_TASK_NAME, Copy.class);

		copy.doFirst(
			new Action<Task>() {

				@Override
				public void execute(Task task) {
					Copy copy = (Copy)task;

					project.delete(copy.getDestinationDir());
				}

			});

		copy.eachFile(new StripPathSegmentsAction(2));

		copy.from(
			new Closure<Void>(project) {

				@SuppressWarnings("unused")
				public FileTree doCall() {
					return project.zipTree(
						frontendCSSCommonConfguration.getSingleFile());
				}

			});

		copy.include("META-INF/resources/");
		copy.into(new File(project.getBuildDir(), "frontend-css-common"));
		copy.setDescription(
			"Expands com.liferay.frontend.css.common to a temporary " +
				"directory.");
		copy.setIncludeEmptyDirs(false);

		return copy;
	}

	protected Upload addTaskInstall(Project project) {
		Upload upload = GradleUtil.addTask(
			project, MavenPlugin.INSTALL_TASK_NAME, Upload.class);

		Configuration configuration = GradleUtil.getConfiguration(
			project, Dependency.ARCHIVES_CONFIGURATION);

		upload.setConfiguration(configuration);
		upload.setDescription(
			"Installs the '" + configuration.getName() +
				"' artifacts into the local Maven repository.");

		return upload;
	}

	protected ReplaceRegexTask addTaskUpdateVersion(
		Project project, WriteDigestTask writeParentThemesDigestTask) {

		ReplaceRegexTask replaceRegexTask = GradleUtil.addTask(
			project, LiferayRelengPlugin.UPDATE_VERSION_TASK_NAME,
			ReplaceRegexTask.class);

		replaceRegexTask.finalizedBy(writeParentThemesDigestTask);
		replaceRegexTask.match("\\n\\t\"version\": \"(.+)\"", "package.json");
		replaceRegexTask.setDescription(
			"Updates the project version in the package.json file.");
		replaceRegexTask.setReplacement(
			IncrementVersionClosure.MICRO_INCREMENT);

		return replaceRegexTask;
	}

	protected WriteDigestTask addTaskWriteParentThemesDigest(
		Project project, Project... parentThemeProjects) {

		WriteDigestTask writeDigestTask = GradleUtil.addTask(
			project, WRITE_PARENT_THEMES_DIGEST_TASK_NAME,
			WriteDigestTask.class);

		writeDigestTask.setDescription(
			"Writes a digest file to keep track of the parent themes used by " +
				"this project.");

		for (Project parentThemeProject : parentThemeProjects) {
			if (parentThemeProject == null) {
				continue;
			}

			writeDigestTask.dependsOn(
				parentThemeProject.getPath() + ":" +
					JavaPlugin.CLASSES_TASK_NAME);

			File dir = parentThemeProject.file(
				"src/main/resources/META-INF/resources");

			writeDigestTask.source(dir);
		}

		return writeDigestTask;
	}

	protected Task addTaskZipDirectories(
		Project project, String taskName, File rootDir, File destinationDir,
		String extension) {

		Task task = project.task(taskName);

		StringBuilder sb = new StringBuilder();

		sb.append("Assembles ");
		sb.append(extension.toUpperCase());
		sb.append(" files in ");
		sb.append(project.relativePath(destinationDir));
		sb.append(" from the subdirectories of ");
		sb.append(project.relativePath(rootDir));
		sb.append('.');

		task.setDescription(sb.toString());

		File[] dirs = FileUtil.getDirectories(rootDir);

		if (dirs != null) {
			for (File dir : dirs) {
				Zip zip = addTaskZipDirectory(
					project, GradleUtil.getTaskName(taskName, dir), dir,
					destinationDir, extension);

				task.dependsOn(zip);
			}
		}

		return task;
	}

	protected Zip addTaskZipDirectory(
		Project project, String taskName, File dir, File destinationDir,
		String extension) {

		Zip zip = GradleUtil.addTask(project, taskName, Zip.class);

		zip.from(dir);
		zip.setArchiveName(dir.getName() + "." + extension);
		zip.setDestinationDir(destinationDir);

		zip.setDescription(
			"Assembles " + project.relativePath(zip.getArchivePath()) +
				" with the contents of the " + project.relativePath(dir) +
					" directory.");

		return zip;
	}

	protected void applyConfigScripts(Project project) {
		GradleUtil.applyScript(
			project,
			"com/liferay/gradle/plugins/defaults/dependencies" +
				"/config-maven.gradle",
			project);
	}

	protected void applyPlugins(Project project) {
		GradleUtil.applyPlugin(project, MavenPlugin.class);
	}

	protected void configureDeployDir(Project project) {
		final LiferayExtension liferayExtension = GradleUtil.getExtension(
			project, LiferayExtension.class);

		boolean requiredForStartup = getPluginPackageProperty(
			project, "required-for-startup");

		if (requiredForStartup) {
			liferayExtension.setDeployDir(
				new Callable<File>() {

					@Override
					public File call() throws Exception {
						return new File(
							liferayExtension.getLiferayHome(), "osgi/war");
					}

				});
		}
		else {
			liferayExtension.setDeployDir(
				new Callable<File>() {

					@Override
					public File call() throws Exception {
						return new File(
							liferayExtension.getLiferayHome(), "deploy");
					}

				});
		}
	}

	protected void configureProject(Project project) {
		project.setGroup(_GROUP);
	}

	protected void configureTaskExecuteGulp(
		ExecuteGulpTask executeGulpTask, final Copy expandFrontendCSSCommonTask,
		Task zipResourcesImporterLARsTask, Project frontendThemeStyledProject,
		Project frontendThemeUnstyledProject) {

		executeGulpTask.args(
			new Callable<String>() {

				@Override
				public String call() throws Exception {
					File dir = expandFrontendCSSCommonTask.getDestinationDir();

					return "--css-common-path=" + FileUtil.getAbsolutePath(dir);
				}

			});

		executeGulpTask.dependsOn(
			expandFrontendCSSCommonTask, zipResourcesImporterLARsTask);

		configureTaskExecuteGulpParentTheme(
			executeGulpTask, frontendThemeStyledProject, "styled");
		configureTaskExecuteGulpParentTheme(
			executeGulpTask, frontendThemeUnstyledProject, "unstyled");
	}

	protected void configureTaskExecuteGulpParentTheme(
		ExecuteGulpTask executeGulpTask, Project themeProject, String name) {

		if (themeProject == null) {
			Project project = executeGulpTask.getProject();

			Logger logger = project.getLogger();

			if (logger.isWarnEnabled()) {
				logger.warn("Unable to configure {} parent theme", name);
			}

			return;
		}

		File dir = themeProject.file(
			"src/main/resources/META-INF/resources/_" + name);

		executeGulpTask.args(
			"--" + name + "-path=" + FileUtil.getAbsolutePath(dir));

		executeGulpTask.dependsOn(
			themeProject.getPath() + ":" + JavaPlugin.CLASSES_TASK_NAME);
	}

	protected void configureTasksExecuteGulp(
		Project project, final Copy expandFrontendCSSCommonTask,
		final Task assembleResourcesImporterArchivesTask,
		final Project frontendThemeStyledProject,
		final Project frontendThemeUnstyledProject) {

		TaskContainer taskContainer = project.getTasks();

		taskContainer.withType(
			ExecuteGulpTask.class,
			new Action<ExecuteGulpTask>() {

				@Override
				public void execute(ExecuteGulpTask executeGulpTask) {
					configureTaskExecuteGulp(
						executeGulpTask, expandFrontendCSSCommonTask,
						assembleResourcesImporterArchivesTask,
						frontendThemeStyledProject,
						frontendThemeUnstyledProject);
				}

			});
	}

	protected void configureTaskUploadArchives(
		Project project, Task updateThemeVersionTask) {

		if (GradleUtil.isSnapshot(project)) {
			return;
		}

		Task uploadArchivesTask = GradleUtil.getTask(
			project, BasePlugin.UPLOAD_ARCHIVES_TASK_NAME);

		uploadArchivesTask.finalizedBy(updateThemeVersionTask);
	}

	protected boolean getPluginPackageProperty(Project project, String key) {
		try {
			Properties properties = FileUtil.readProperties(
				project, "src/WEB-INF/liferay-plugin-package.properties");

			return Boolean.parseBoolean(properties.getProperty(key));
		}
		catch (IOException ioe) {
			throw new GradleException(
				"Unable to read liferay-plugin-package.properties", ioe);
		}
	}

	protected Project getThemeProject(Project project, String name) {
		Project parentProject = project.getParent();

		Project themeProject = parentProject.findProject(name);

		if (themeProject == null) {
			themeProject = GradleUtil.getProject(
				project.getRootProject(), name);
		}

		return themeProject;
	}

	private static final String _FRONTEND_COMMON_CSS_NAME =
		"com.liferay.frontend.css.common";

	private static final String _GROUP = "com.liferay.plugins";

}