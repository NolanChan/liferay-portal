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

package com.liferay.javadoc.formatter.ant;

import com.liferay.javadoc.formatter.JavadocFormatterArgs;
import com.liferay.javadoc.formatter.JavadocFormatterInvoker;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * @author Andrea Di Giorgi
 */
public class JavadocFormatterTask extends Task {

	@Override
	public void execute() throws BuildException {
		try {
			JavadocFormatterInvoker.invoke(_javadocFormatterArgs);
		}
		catch (Exception e) {
			throw new BuildException(e);
		}
	}

	public void setAuthor(String author) {
		_javadocFormatterArgs.setAuthor(author);
	}

	public void setInitializeMissingJavadocs(
		boolean initializeMissingJavadocs) {

		_javadocFormatterArgs.setInitializeMissingJavadocs(
			initializeMissingJavadocs);
	}

	public void setInputDir(File inputDir) {
		_javadocFormatterArgs.setInputDir(inputDir);
	}

	public void setLimits(String limits) {
		_javadocFormatterArgs.setLimits(limits);
	}

	public void setLowestSupportedJavaVersion(
		double lowestSupportedJavaVersion) {

		_javadocFormatterArgs.setLowestSupportedJavaVersion(
			lowestSupportedJavaVersion);
	}

	public void setOutputFilePrefix(String outputFilePrefix) {
		_javadocFormatterArgs.setOutputFilePrefix(outputFilePrefix);
	}

	public void setUpdateJavadocs(boolean updateJavadocs) {
		_javadocFormatterArgs.setUpdateJavadocs(updateJavadocs);
	}

	private final JavadocFormatterArgs _javadocFormatterArgs =
		new JavadocFormatterArgs();

}