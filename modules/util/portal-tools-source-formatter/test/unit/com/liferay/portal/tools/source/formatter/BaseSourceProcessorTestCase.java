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

package com.liferay.portal.tools.source.formatter;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.File;
import java.io.InputStream;

import java.net.URL;

import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 * @author Hugo Huijser
 */
public class BaseSourceProcessorTestCase {

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	protected SourceFormatterBean getSourceFormatterBean() {
		SourceFormatterBean sourceFormatterBean = new SourceFormatterBean();

		sourceFormatterBean.setAutoFix(true);
		sourceFormatterBean.setPrintErrors(false);
		sourceFormatterBean.setThrowException(false);
		sourceFormatterBean.setUseProperties(false);

		return sourceFormatterBean;
	}

	protected void test(String fileName) throws Exception {
		test(fileName, new String[0]);
	}

	protected void test(String fileName, String expectedErrorMessage)
		throws Exception {

		test(fileName, new String[] {expectedErrorMessage});
	}

	protected void test(
			String fileName, String expectedErrorMessage, int lineNumber)
		throws Exception {

		test(
			fileName, new String[] {expectedErrorMessage},
			new Integer[] {lineNumber});
	}

	protected void test(String fileName, String[] expectedErrorMessages)
		throws Exception {

		test(fileName, expectedErrorMessages, null);
	}

	protected void test(
			String fileName, String[] expectedErrorMessages,
			Integer[] lineNumbers)
		throws Exception {

		String originalExtension = FilenameUtils.getExtension(fileName);

		String extension = originalExtension;

		fileName = FilenameUtils.getBaseName(fileName);

		if (!keepTestExtension && originalExtension.startsWith("test")) {
			extension = extension.substring(4);
		}

		String fullFileName =
			_DIR_NAME + StringPool.SLASH + fileName + "." + originalExtension;

		File newFile = temporaryFolder.newFile(fileName + "." + extension);

		URL url = classLoader.getResource(fullFileName);

		try (InputStream inputStream = url.openStream()) {
			FileUtils.copyInputStreamToFile(inputStream, newFile);
		}

		SourceFormatterBean sourceFormatterBean = getSourceFormatterBean();

		sourceFormatterBean.setFileNames(
			Collections.singletonList(newFile.getAbsolutePath()));

		SourceFormatter sourceFormatter = new SourceFormatter(
			sourceFormatterBean);

		sourceFormatter.format();

		List<String> processedFiles = sourceFormatter.getProcessedFiles();

		if (processedFiles.isEmpty()) {
			throw new IllegalArgumentException(
				"The file name " + newFile.getAbsolutePath() +
					" does not end with a valid extension");
		}

		List<String> errorMessages = sourceFormatter.getErrorMessages();

		if (!errorMessages.isEmpty() || (expectedErrorMessages.length > 0)) {
			Assert.assertEquals(
				expectedErrorMessages.length, errorMessages.size());

			for (int i = 0; i < errorMessages.size(); i++) {
				String actualErrorMessage = errorMessages.get(i);
				String expectedErrorMessage = expectedErrorMessages[i];

				StringBundler sb = new StringBundler(5);

				sb.append(expectedErrorMessage);
				sb.append(StringPool.SPACE);
				sb.append(newFile.getAbsolutePath());

				if (lineNumbers != null) {
					sb.append(StringPool.SPACE);
					sb.append(lineNumbers[i]);
				}

				Assert.assertEquals(sb.toString(), actualErrorMessage);
			}
		}
		else {
			String actualFormattedContent = FileUtils.readFileToString(
				new File(processedFiles.get(0)));

			URL expectedUrl = classLoader.getResource(
				_DIR_NAME + "/expected/" + fileName + "." +
					originalExtension);

			String expectedFormattedContent = IOUtils.toString(
				expectedUrl, StringPool.UTF8);

			expectedFormattedContent = StringUtil.replace(
				expectedFormattedContent, StringPool.RETURN_NEW_LINE,
				StringPool.NEW_LINE);

			Assert.assertEquals(
				expectedFormattedContent, actualFormattedContent);
		}
	}

	protected final ClassLoader classLoader =
		BaseSourceProcessorTestCase.class.getClassLoader();

	private static final String _DIR_NAME =
		"com/liferay/portal/tools/source/formatter/dependencies";

	protected boolean keepTestExtension = false;

}