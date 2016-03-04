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

package com.liferay.portal.tools;

import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ClassUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.IOException;

import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

/**
 * @author André de Oliveira
 * @author Hugo Huijser
 */
public abstract class BaseImportsFormatter implements ImportsFormatter {

	public String format(String content, Pattern importPattern)
		throws IOException {

		return doFormat(content, importPattern, null, null);
	}

	public String format(String content, String packageDir, String className)
		throws IOException {

		return doFormat(content, null, packageDir, className);
	}

	protected abstract ImportPackage createImportPackage(String line);

	protected abstract String doFormat(
			String content, Pattern importPattern, String packageDir,
			String className)
		throws IOException;

	protected String sortAndGroupImports(String imports) throws IOException {
		if (imports.contains("/*") || imports.contains("*/") ||
			imports.contains("\n//")) {

			return imports + "\n";
		}

		Set<ImportPackage> importPackages = new TreeSet<>();

		UnsyncBufferedReader unsyncBufferedReader = new UnsyncBufferedReader(
			new UnsyncStringReader(imports));

		String line = null;

		while ((line = unsyncBufferedReader.readLine()) != null) {
			ImportPackage importPackage = createImportPackage(line);

			if (importPackage != null) {
				importPackages.add(importPackage);
			}
		}

		StringBundler sb = new StringBundler(3 * importPackages.size());

		ImportPackage previousImportPackage = null;

		for (ImportPackage importPackage : importPackages) {
			if ((previousImportPackage != null) &&
				!importPackage.isGroupedWith(previousImportPackage)) {

				sb.append("\n");
			}

			sb.append(importPackage.getLine());
			sb.append("\n");

			previousImportPackage = importPackage;
		}

		return sb.toString();
	}

	protected String stripUnusedImports(
			String imports, String content, String packageDir, String className)
		throws IOException {

		Set<String> classes = ClassUtil.getClasses(
			new UnsyncStringReader(content), className);

		StringBundler sb = new StringBundler();

		UnsyncBufferedReader unsyncBufferedReader = new UnsyncBufferedReader(
			new UnsyncStringReader(imports));

		String line = null;

		while ((line = unsyncBufferedReader.readLine()) != null) {
			int x = line.indexOf("import ");

			if (x == -1) {
				continue;
			}

			int y = line.lastIndexOf(CharPool.PERIOD);

			String importPackage = line.substring(x + 7, y);

			if (importPackage.equals(packageDir) ||
				importPackage.equals("java.lang")) {

				continue;
			}

			String importClass = line.substring(y + 1, line.length() - 1);

			if (importClass.equals("*") || classes.contains(importClass)) {
				sb.append(line);
				sb.append("\n");
			}
		}

		return sb.toString();
	}

}