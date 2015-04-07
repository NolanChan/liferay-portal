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

package com.liferay.gradle.plugins.util;

import groovy.lang.Closure;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.gradle.api.AntBuilder;
import org.gradle.api.Project;

/**
 * @author Andrea Di Giorgi
 */
public class FileUtil {

	public static boolean exists(Project project, String fileName) {
		File file = project.file(fileName);

		return file.exists();
	}

	public static Properties readProperties(File file) throws Exception {
		Properties properties = new Properties();

		if (file.exists()) {
			try (FileInputStream fileInputStream = new FileInputStream(file)) {
				properties.load(fileInputStream);
			}
		}

		return properties;
	}

	public static Properties readProperties(Project project, String fileName)
		throws Exception {

		File file = project.file(fileName);

		return readProperties(file);
	}

	public static String relativize(File file, File start) {
		Path path = file.toPath();
		Path startPath = start.toPath();

		if (!path.startsWith(startPath)) {
			return null;
		}

		Path relativePath = startPath.relativize(path);

		return relativePath.toString();
	}

	public static void unzip(
		Project project, final File source, final File destination,
		final int cutDirs, final String[] includes, final String[] excludes) {

		Closure<Void> closure = new Closure<Void>(null) {

			@SuppressWarnings("unused")
			public void doCall(AntBuilder antBuilder) {
				_invokeAntMethodUnzip(
					antBuilder, source, destination, cutDirs, includes,
					excludes);
			}

		};

		project.ant(closure);
	}

	public static void write(File file, List<String> lines) throws Exception {
		try (PrintWriter printWriter = new PrintWriter(
				new OutputStreamWriter(
					new FileOutputStream(file), StandardCharsets.UTF_8))) {

			for (String line : lines) {
				printWriter.println(line);
			}
		}
	}

	private static void _invokeAntMethod(
		AntBuilder antBuilder, String method, String paramName,
		Object paramValue) {

		Map<String, Object> args = Collections.singletonMap(
			paramName, paramValue);

		antBuilder.invokeMethod(method, args);
	}

	private static void _invokeAntMethodPatternset(
		final AntBuilder antBuilder, final String[] includes,
		final String[] excludes) {

		Closure<Void> closure = new Closure<Void>(null) {

			@SuppressWarnings("unused")
			public void doCall() {
				if (ArrayUtil.isNotEmpty(excludes)) {
					for (String exclude : excludes) {
						_invokeAntMethod(
							antBuilder, "exclude", "name", exclude);
					}
				}

				if (ArrayUtil.isNotEmpty(includes)) {
					for (String include : includes) {
						_invokeAntMethod(
							antBuilder, "include", "name", include);
					}
				}
			}

		};

		antBuilder.invokeMethod("patternset", closure);
	}

	private static void _invokeAntMethodUnzip(
		final AntBuilder antBuilder, File source, File destination,
		final int cutDirs, final String[] includes, final String[] excludes) {

		Map<String, Object> args = new HashMap<>();

		args.put("dest", destination);
		args.put("src", source);

		Closure<Void> closure = new Closure<Void>(null) {

			@SuppressWarnings("unused")
			public void doCall() {
				if (cutDirs > 0) {
					_invokeAntMethod(
						antBuilder, "cutdirsmapper", "dirs", cutDirs);
				}

				_invokeAntMethodPatternset(antBuilder, includes, excludes);
			}

		};

		antBuilder.invokeMethod("unzip", new Object[] {args, closure});
	}

}