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

package com.liferay.source.formatter;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.tools.ArgumentsUtil;

import java.io.IOException;
import java.io.File;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Hugo Huijser
 */
public class SourceFormatter {

	public static void main(String[] args) throws Exception {
		Map<String, String> arguments = ArgumentsUtil.parseArguments(args);

		try {
			SourceFormatterArgs sourceFormatterArgs = new SourceFormatterArgs();

			boolean autoFix = GetterUtil.getBoolean(
				arguments.get("source.auto.fix"), SourceFormatterArgs.AUTO_FIX);

			sourceFormatterArgs.setAutoFix(autoFix);

			String baseDirName = GetterUtil.getString(
				arguments.get("source.base.dir"),
				SourceFormatterArgs.BASE_DIR_NAME);

			sourceFormatterArgs.setBaseDirName(baseDirName);

			String copyrightFileName = GetterUtil.getString(
				arguments.get("source.copyright.file"),
				SourceFormatterArgs.COPYRIGHT_FILE_NAME);

			sourceFormatterArgs.setCopyrightFileName(copyrightFileName);

			String[] fileNames = StringUtil.split(
				arguments.get("source.files"), StringPool.COMMA);

			if (ArrayUtil.isNotEmpty(fileNames)) {
				sourceFormatterArgs.setFileNames(Arrays.asList(fileNames));
			}

			boolean printErrors = GetterUtil.getBoolean(
				arguments.get("source.print.errors"),
				SourceFormatterArgs.PRINT_ERRORS);

			sourceFormatterArgs.setPrintErrors(printErrors);

			boolean throwException = GetterUtil.getBoolean(
				arguments.get("source.throw.exception"),
				SourceFormatterArgs.THROW_EXCEPTION);

			sourceFormatterArgs.setThrowException(throwException);

			boolean useProperties = GetterUtil.getBoolean(
				arguments.get("source.use.properties"),
				SourceFormatterArgs.USE_PROPERTIES);

			sourceFormatterArgs.setUseProperties(useProperties);

			SourceFormatter sourceFormatter = new SourceFormatter(
				sourceFormatterArgs);

			sourceFormatter.format();
		}
		catch (Exception e) {
			ArgumentsUtil.processMainException(arguments, e);
		}
	}

	public SourceFormatter(SourceFormatterArgs sourceFormatterArgs) {
		_sourceFormatterArgs = sourceFormatterArgs;
	}

	public void format() throws Exception {
		List<SourceProcessor> sourceProcessors = new ArrayList<>();

		sourceProcessors.add(new CSSSourceProcessor());
		sourceProcessors.add(new FTLSourceProcessor());
		sourceProcessors.add(new JavaSourceProcessor());
		sourceProcessors.add(new JSPSourceProcessor());
		sourceProcessors.add(new JSSourceProcessor());
		sourceProcessors.add(new PropertiesSourceProcessor());
		sourceProcessors.add(new SHSourceProcessor());
		sourceProcessors.add(new SQLSourceProcessor());
		sourceProcessors.add(new TLDSourceProcessor());
		sourceProcessors.add(new XMLSourceProcessor());

		ExecutorService executorService = Executors.newFixedThreadPool(
			sourceProcessors.size());

		List<Future<Void>> futures = new ArrayList<>(sourceProcessors.size());

		for (final SourceProcessor sourceProcessor : sourceProcessors) {
			Future<Void> future = executorService.submit(
				new Callable<Void>() {

					@Override
					public Void call() throws Exception {
						_runSourceProcessor(sourceProcessor);

						return null;
					}

				}
			);

			futures.add(future);
		}

		ExecutionException ee1 = null;

		for (Future<Void> future : futures) {
			try {
				future.get();
			}
			catch (ExecutionException ee2) {
				if (ee1 == null) {
					ee1 = ee2;
				}
				else {
					ee1.addSuppressed(ee2);
				}
			}
		}

		executorService.shutdown();

		while (!executorService.isTerminated()) {
			Thread.sleep(20);
		}

		if (ee1 != null) {
			throw ee1;
		}

		if (_sourceFormatterArgs.isThrowException()) {
			if (!_errorMessages.isEmpty()) {
				throw new Exception(StringUtil.merge(_errorMessages, "\n"));
			}

			if (_firstSourceMismatchException != null) {
				throw _firstSourceMismatchException;
			}
		}
	}

	public List<String> getErrorMessages() {
		return new ArrayList<>(_errorMessages);
	}

	public List<String> getModifiedFileNames() {
		return _modifiedFileNames;
	}

	public SourceFormatterArgs getSourceFormatterArgs() {
		return _sourceFormatterArgs;
	}

	public SourceMismatchException getSourceMismatchException() {
		return _firstSourceMismatchException;
	}

	protected static List<String> getLocalChangesFileNames(String baseDirName) 
		throws Exception {

		Runtime runtime = Runtime.getRuntime();

		Process process = null;

		try {
			process = runtime.exec("git add . --dry-run");
		}
		catch (IOException ioe) {
			String errorMessage = ioe.getMessage();

			if (errorMessage.contains("Cannot run program")) {
				System.out.println(
					"Add Git to your PATH system variable before executing " +
						"'ant format-source-local-changes'.");

				System.exit(0);
			}

			throw ioe;
		}

		String content = StringUtil.read(process.getInputStream());

		int gitLevel = -1;

		for (int i = 0; i < BaseSourceProcessor.PORTAL_MAX_DIR_LEVEL; i++) {
			File file = new File(baseDirName + ".git");

			if (file.exists()) {
				gitLevel = i;

				break;
			}

			baseDirName = "../" + baseDirName;
		}

		if (gitLevel == -1) {
			System.out.println(
				"Cannot retrieve files because .git directory is missing.");

			System.exit(1);
		}

		List<String> localChangesFileNames = new ArrayList<>();

		for (String line : StringUtil.splitLines(content)) {
			if (!line.startsWith("add '") ||
				(StringUtil.count(line, StringPool.SLASH) < gitLevel)) {

				continue;
			}

			line = line.substring(5, line.length() - 1);

			for (int i = 0; i < gitLevel; i++) {
				int x = line.indexOf(StringPool.SLASH);

				line = line.substring(x + 1);
			}

			localChangesFileNames.add(line);
		}

		return localChangesFileNames;
	}

	private void _runSourceProcessor(SourceProcessor sourceProcessor)
		throws Exception {

		sourceProcessor.setSourceFormatterArgs(_sourceFormatterArgs);

		sourceProcessor.format();

		_errorMessages.addAll(sourceProcessor.getErrorMessages());
		_modifiedFileNames.addAll(sourceProcessor.getModifiedFileNames());

		if (_firstSourceMismatchException == null) {
			_firstSourceMismatchException =
				sourceProcessor.getFirstSourceMismatchException();
		}
	}

	private final Set<String> _errorMessages = new ConcurrentSkipListSet<>();
	private volatile SourceMismatchException _firstSourceMismatchException;
	private final List<String> _modifiedFileNames =
		new CopyOnWriteArrayList<>();
	private final SourceFormatterArgs _sourceFormatterArgs;

}