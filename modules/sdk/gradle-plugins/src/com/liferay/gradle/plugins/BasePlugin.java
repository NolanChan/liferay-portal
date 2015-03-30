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

package com.liferay.gradle.plugins;

import java.util.Collections;
import java.util.Map;

import org.gradle.api.GradleException;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.ConfigurationContainer;
import org.gradle.api.artifacts.dsl.DependencyHandler;
import org.gradle.api.plugins.Convention;
import org.gradle.api.plugins.ExtensionContainer;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.SourceSetContainer;
import org.gradle.api.tasks.TaskContainer;

/**
 * @author Andrea Di Giorgi
 */
public abstract class BasePlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {
		this.project = project;

		try {
			doApply();
		}
		catch (Exception e) {
			throw new GradleException("Unable to apply plugin", e);
		}
	}

	protected void addDependencies(
		String configurationName, String... dependencyNotations) {

		DependencyHandler dependencyHandler = project.getDependencies();

		for (String dependencyNotation : dependencyNotations) {
			dependencyHandler.add(configurationName, dependencyNotation);
		}
	}

	protected <T extends Plugin<Project>> void applyPlugin(Class<T> clazz) {
		Map<String, Class<T>> args = Collections.singletonMap("plugin", clazz);

		project.apply(args);
	}

	protected <T> T createExtension(String name, Class<T> clazz) {
		ExtensionContainer extensionContainer = project.getExtensions();

		return extensionContainer.create(name, clazz, project);
	}

	protected <T extends Task> void createTask(String name, Class<T> clazz) {
		Map<String, Class<T>> args = Collections.singletonMap(
			Task.TASK_TYPE, clazz);

		project.task(args, name);
	}

	protected abstract void doApply() throws Exception;

	protected Configuration getConfiguration(String name) {
		ConfigurationContainer configurationContainer =
			project.getConfigurations();

		return configurationContainer.getByName(name);
	}

	protected <T> T getPluginConvention(Class<T> clazz) {
		Convention convention = project.getConvention();

		return convention.getPlugin(clazz);
	}

	protected SourceSet getSourceSet(String name) {
		JavaPluginConvention javaPluginConvention = getPluginConvention(
			JavaPluginConvention.class);

		SourceSetContainer sourceSetContainer =
			javaPluginConvention.getSourceSets();

		return sourceSetContainer.getByName(name);
	}

	protected Task getTask(String name) {
		TaskContainer taskContainer = project.getTasks();

		return taskContainer.getByName(name);
	}

	protected static final String CLEAN_TASK_NAME = "clean";

	protected static final String PORTAL_VERSION = "7.0.0";

	protected static final String REPOSITORY_URL =
		"http://cdn.repository.liferay.com/nexus/content/groups/public";

	protected Project project;

}