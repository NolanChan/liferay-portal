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

import com.liferay.gradle.plugins.util.FileUtil;

import java.io.File;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.gradle.api.GradleException;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * @author Andrea Di Giorgi
 */
public class LiferayPlugin implements Plugin<Project> {

	public static final String PLUGIN_NAME = "liferay";

	@Override
	public void apply(Project project) {
		Plugin<Project> plugin = null;

		if (isOSGiPlugin(project)) {
			plugin = new LiferayOSGiPlugin();
		}
		else if (isThemePlugin(project)) {
			plugin = new LiferayThemePlugin();
		}
		else {
			plugin = new LiferayBasePlugin();
		}

		plugin.apply(project);
	}

	protected boolean isOSGiPlugin(Project project) {
		if (FileUtil.exists(project, "bnd.bnd")) {
			return true;
		}

		return false;
	}

	protected boolean isThemePlugin(Project project) {
		File gulpFile = project.file("gulpfile.js");

		if (!gulpFile.exists()) {
			return false;
		}

		String gulpFileContent;

		try {
			gulpFileContent = new String(
				Files.readAllBytes(gulpFile.toPath()), StandardCharsets.UTF_8);
		}
		catch (IOException ioe) {
			throw new GradleException("Unable to read " + gulpFile, ioe);
		}

		if (gulpFileContent.contains("require('liferay-theme-tasks')")) {
			return true;
		}

		return false;
	}

}