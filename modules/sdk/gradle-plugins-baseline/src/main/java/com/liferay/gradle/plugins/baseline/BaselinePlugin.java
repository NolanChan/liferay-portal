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

package com.liferay.gradle.plugins.baseline;

import com.liferay.gradle.plugins.baseline.internal.util.GradleUtil;
import com.liferay.gradle.util.Validator;

import java.io.File;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.DependencySet;
import org.gradle.api.artifacts.ResolutionStrategy;
import org.gradle.api.file.FileCollection;
import org.gradle.api.file.SourceDirectorySet;
import org.gradle.api.plugins.JavaBasePlugin;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.ReportingBasePlugin;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.TaskContainer;
import org.gradle.api.tasks.bundling.AbstractArchiveTask;
import org.gradle.api.tasks.bundling.Jar;
import org.gradle.util.VersionNumber;

/**
 * @author Andrea Di Giorgi
 */
public class BaselinePlugin implements Plugin<Project> {

	public static final String BASELINE_CONFIGURATION_NAME = "baseline";

	public static final String BASELINE_TASK_NAME = "baseline";

	public static final String PLUGIN_NAME = "baseline";

	@Override
	public void apply(Project project) {
		GradleUtil.applyPlugin(project, JavaPlugin.class);
		GradleUtil.applyPlugin(project, ReportingBasePlugin.class);

		final BaselineExtension baselineExtension = GradleUtil.addExtension(
			project, PLUGIN_NAME, BaselineExtension.class);

		final Jar jar = (Jar)GradleUtil.getTask(
			project, JavaPlugin.JAR_TASK_NAME);

		final Configuration baselineConfiguration = _addConfigurationBaseline(
			jar);

		final BaselineTask baselineTask = _addTaskBaseline(project);

		_configureTasksBaseline(project);

		project.afterEvaluate(
			new Action<Project>() {

				@Override
				public void execute(Project project) {
					_configureTaskBaseline(
						baselineTask, jar, baselineConfiguration,
						baselineExtension);
				}

			});
	}

	private Configuration _addConfigurationBaseline(
		final AbstractArchiveTask newJarTask) {

		Configuration configuration = GradleUtil.addConfiguration(
			newJarTask.getProject(), BASELINE_CONFIGURATION_NAME);

		configuration.defaultDependencies(
			new Action<DependencySet>() {

				@Override
				public void execute(DependencySet dependencySet) {
					_addDependenciesBaseline(newJarTask);
				}

			});

		configuration.setDescription(
			"Configures the previous released version of this project for " +
				"baselining.");
		configuration.setVisible(false);

		ResolutionStrategy resolutionStrategy =
			configuration.getResolutionStrategy();

		resolutionStrategy.cacheChangingModulesFor(0, TimeUnit.SECONDS);
		resolutionStrategy.cacheDynamicVersionsFor(0, TimeUnit.SECONDS);

		return configuration;
	}

	private void _addDependenciesBaseline(AbstractArchiveTask newJarTask) {
		Project project = newJarTask.getProject();

		GradleUtil.addDependency(
			project, BASELINE_CONFIGURATION_NAME,
			String.valueOf(project.getGroup()), newJarTask.getBaseName(),
			"(," + newJarTask.getVersion() + ")", false);
	}

	private BaselineTask _addTaskBaseline(final Project project) {
		BaselineTask baselineTask = GradleUtil.addTask(
			project, BASELINE_TASK_NAME, BaselineTask.class);

		File bndFile = project.file("bnd.bnd");

		if (bndFile.exists()) {
			baselineTask.setBndFile(bndFile);
		}

		baselineTask.setDescription(
			"Compares the public API of this project with the public API of " +
				"the previous released version, if found.");
		baselineTask.setGroup(JavaBasePlugin.VERIFICATION_GROUP);

		baselineTask.setSourceDir(
			new Callable<File>() {

				@Override
				public File call() throws Exception {
					SourceSet sourceSet = GradleUtil.getSourceSet(
						project, SourceSet.MAIN_SOURCE_SET_NAME);

					return GradleUtil.getSrcDir(sourceSet.getResources());
				}

			});

		return baselineTask;
	}

	private void _configureTaskBaseline(BaselineTask baselineTask) {
		String ignoreFailures = GradleUtil.getTaskPrefixedProperty(
			baselineTask, "ignoreFailures");

		if (Validator.isNotNull(ignoreFailures)) {
			baselineTask.setIgnoreFailures(
				Boolean.parseBoolean(ignoreFailures));
		}
	}

	private void _configureTaskBaseline(
		BaselineTask baselineTask, final AbstractArchiveTask newJarTask,
		final FileCollection oldJarFileCollection,
		BaselineExtension baselineExtension) {

		VersionNumber lowestBaselineVersionNumber = VersionNumber.parse(
			baselineExtension.getLowestBaselineVersion());
		VersionNumber versionNumber = VersionNumber.parse(
			newJarTask.getVersion());

		if (lowestBaselineVersionNumber.compareTo(versionNumber) >= 0) {
			baselineTask.setEnabled(false);

			return;
		}

		baselineTask.dependsOn(newJarTask);

		baselineTask.setNewJarFile(
			new Callable<File>() {

				@Override
				public File call() throws Exception {
					return newJarTask.getArchivePath();
				}

			});

		baselineTask.setOldJarFile(
			new Callable<File>() {

				@Override
				public File call() throws Exception {
					return oldJarFileCollection.getSingleFile();
				}

			});
	}

	private void _configureTasksBaseline(Project project) {
		TaskContainer taskContainer = project.getTasks();

		taskContainer.withType(
			BaselineTask.class,
			new Action<BaselineTask>() {

				@Override
				public void execute(BaselineTask baselineTask) {
					_configureTaskBaseline(baselineTask);
				}

			});
	}

}