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

package com.liferay.portal.tools.sourceformatter;

import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hugo Huijser
 */
public class JavaClass {

	public JavaClass(
			String name, String fileName, String absolutePath, String content,
			int lineCount, String indent, JavaClass outerClass,
			List<String> javaTermAccessLevelModifierExclusions)
		throws Exception {

		_name = name;
		_fileName = fileName;
		_absolutePath = absolutePath;
		_content = content;
		_lineCount = lineCount;
		_outerClass = outerClass;
		_indent = indent;
		_javaTermAccessLevelModifierExclusions =
			javaTermAccessLevelModifierExclusions;

		_javaTerms = getJavaTerms();
	}

	public String formatJavaTerms(
			Set<String> annotationsExclusions, Set<String> immutableFieldTypes,
			List<String> finalableFieldTypesExclusions,
			List<String> javaTermSortExclusions,
			List<String> testAnnotationsExclusions)
		throws Exception {

		if (_javaTerms == null) {
			return _content;
		}

		String originalContent = _content;

		JavaTerm previousJavaTerm = null;

		Iterator<JavaTerm> itr = _javaTerms.iterator();

		while (itr.hasNext()) {
			JavaTerm javaTerm = itr.next();

			checkJavaFieldType(
				javaTerm, annotationsExclusions, immutableFieldTypes,
				finalableFieldTypesExclusions);

			if (!originalContent.equals(_content)) {
				return _content;
			}

			sortJavaTerms(previousJavaTerm, javaTerm, javaTermSortExclusions);
			fixTabsAndIncorrectEmptyLines(javaTerm);
			formatAnnotations(javaTerm, testAnnotationsExclusions);

			if (!originalContent.equals(_content)) {
				return _content;
			}

			previousJavaTerm = javaTerm;
		}

		for (JavaClass innerClass : _innerClasses) {
			String innerClassContent = innerClass.getContent();

			String newInnerClassContent = innerClass.formatJavaTerms(
				annotationsExclusions, immutableFieldTypes,
				finalableFieldTypesExclusions, javaTermSortExclusions,
				testAnnotationsExclusions);

			if (!innerClassContent.equals(newInnerClassContent)) {
				_content = StringUtil.replace(
					_content, innerClassContent, newInnerClassContent);

				return _content;
			}
		}

		fixJavaTermsDividers(_javaTerms, javaTermSortExclusions);

		return _content;
	}

	public String getContent() {
		return _content;
	}

	protected Set<JavaTerm> addStaticBlocks(
		Set<JavaTerm> javaTerms, List<JavaTerm> staticBlocks) {

		Set<JavaTerm> newJavaTerms = new TreeSet<JavaTerm>(
			new JavaTermComparator());

		Iterator<JavaTerm> javaTermsIterator = javaTerms.iterator();

		while (javaTermsIterator.hasNext()) {
			JavaTerm javaTerm = javaTermsIterator.next();

			if (!javaTerm.isStatic() || !javaTerm.isVariable()) {
				newJavaTerms.add(javaTerm);

				continue;
			}

			Iterator<JavaTerm> staticBlocksIterator = staticBlocks.iterator();

			while (staticBlocksIterator.hasNext()) {
				JavaTerm staticBlock = staticBlocksIterator.next();

				String staticBlockContent = staticBlock.getContent();

				if (staticBlockContent.contains(javaTerm.getName())) {
					staticBlock.setType(javaTerm.getType() + 1);

					newJavaTerms.add(staticBlock);

					staticBlocksIterator.remove();
				}
			}

			newJavaTerms.add(javaTerm);
		}

		if (!staticBlocks.isEmpty()) {
			newJavaTerms.addAll(staticBlocks);
		}

		return newJavaTerms;
	}

	protected void checkAnnotationForMethod(
		JavaTerm javaTerm, String annotation, String requiredMethodNameRegex,
		int requiredMethodType, String fileName) {

		String methodContent = javaTerm.getContent();
		String methodName = javaTerm.getName();

		Pattern pattern = Pattern.compile(requiredMethodNameRegex);

		Matcher matcher = pattern.matcher(methodName);

		if (methodContent.contains(
				_indent + StringPool.AT + annotation + "\n") ||
			methodContent.contains(
				_indent + StringPool.AT + annotation +
					StringPool.OPEN_PARENTHESIS)) {

			if (!matcher.find()) {
				BaseSourceProcessor.processErrorMessage(
					fileName,
					"LPS-36303: Incorrect method name: " + methodName + " " +
						fileName);
			}
			else if (javaTerm.getType() != requiredMethodType) {
				BaseSourceProcessor.processErrorMessage(
					fileName,
					"LPS-36303: Incorrect method type for " + methodName + " " +
						fileName);
			}
		}
		else if (matcher.find() &&
				 !methodContent.contains(_indent + "@Override")) {

			BaseSourceProcessor.processErrorMessage(
				fileName,
				"Annotation @" + annotation + " required for " + methodName +
					" " + fileName);
		}
	}

	protected void checkFinalableFieldType(
			JavaTerm javaTerm, Set<String> annotationsExclusions,
			boolean isStatic)
		throws Exception {

		String javaTermContent = javaTerm.getContent();

		for (String annotation : annotationsExclusions) {
			if (javaTermContent.contains(
					_indent + StringPool.AT + annotation)) {

				return;
			}
		}

		StringBundler sb = new StringBundler(4);

		sb.append("(\\b|\\.)");
		sb.append(javaTerm.getName());
		sb.append(" (=)|(\\+\\+)|(--)|(\\+=)|(-=)|(\\*=)|(/=)|(%=)");
		sb.append("|(\\|=)|(&=)|(^=) ");

		Pattern pattern = Pattern.compile(sb.toString());

		if (!isFinalableField(javaTerm, _name, pattern, true)) {
			return;
		}

		String newJavaTermContent = null;

		if (isStatic) {
			newJavaTermContent = StringUtil.replaceFirst(
				javaTermContent, "private static ", "private static final ");
		}
		else {
			newJavaTermContent = StringUtil.replaceFirst(
				javaTermContent, "private ", "private final ");
		}

		_content = StringUtil.replace(
			_content, javaTermContent, newJavaTermContent);
	}

	protected void checkImmutableFieldType(
		JavaTerm javaTerm, boolean isStatic) {

		String oldName = javaTerm.getName();

		if (!isStatic || oldName.equals("serialVersionUID")) {
			return;
		}

		Matcher matcher = _camelCasePattern.matcher(oldName);

		String newName = matcher.replaceAll("$1_$2");

		newName = StringUtil.toUpperCase(newName);

		if (newName.charAt(0) != CharPool.UNDERLINE) {
			newName = StringPool.UNDERLINE.concat(newName);
		}

		_content = _content.replaceAll(
			"(?<=[\\W&&[^.\"]])(" + oldName + ")\\b", newName);
	}

	protected void checkJavaFieldType(
			JavaTerm javaTerm, Set<String> annotationsExclusions,
			Set<String> immutableFieldTypes,
			List<String> finalableFieldTypesExclusions)
		throws Exception {

		if (!BaseSourceProcessor.portalSource || !javaTerm.isVariable()) {
			return;
		}

		Pattern pattern = Pattern.compile(
			"\t(private |protected |public )(static )?(final)?([\\s\\S]*?)" +
				javaTerm.getName());

		Matcher matcher = pattern.matcher(javaTerm.getContent());

		if (!matcher.find()) {
			return;
		}

		boolean isFinal = Validator.isNotNull(matcher.group(3));
		boolean isStatic = Validator.isNotNull(matcher.group(2));
		String javaFieldType = StringUtil.trim(matcher.group(4));

		if (isFinal && isStatic && javaFieldType.startsWith("Map<")) {
			checkMutableFieldType(javaTerm);
		}

		if (!javaTerm.isPrivate()) {
			return;
		}

		if (isFinal) {
			if (immutableFieldTypes.contains(javaFieldType)) {
				checkImmutableFieldType(javaTerm, isStatic);
				checkStaticableFieldType(javaTerm, isStatic);
			}
		}
		else if (!BaseSourceProcessor.isExcluded(
					finalableFieldTypesExclusions, _absolutePath)) {

			checkFinalableFieldType(javaTerm, annotationsExclusions, isStatic);
		}
	}

	protected void checkMutableFieldType(JavaTerm javaTerm) {
		String oldName = javaTerm.getName();

		String newName = oldName;

		if (newName.charAt(0) != CharPool.UNDERLINE) {
			newName = StringPool.UNDERLINE.concat(newName);
		}

		if (StringUtil.isUpperCase(newName)) {
			StringBundler sb = new StringBundler(newName.length());

			for (int i = 0; i < newName.length(); i++) {
				char c = newName.charAt(i);

				if (i > 1) {
					if (c == CharPool.UNDERLINE) {
						continue;
					}

					if (newName.charAt(i - 1) == CharPool.UNDERLINE) {
						sb.append(c);

						continue;
					}
				}

				sb.append(Character.toLowerCase(c));
			}

			newName = sb.toString();
		}


		if (!newName.equals(oldName)) {
			_content = _content.replaceAll(
				"(?<=[\\W&&[^.\"]])(" + oldName + ")\\b", newName);
		}
	}

	protected void checkStaticableFieldType(
		JavaTerm javaTerm, boolean isStatic) {

		String javaTermContent = javaTerm.getContent();

		if (isStatic || !javaTermContent.contains(StringPool.EQUAL)) {
			return;
		}

		String newJavaTermContent = StringUtil.replaceFirst(
			javaTermContent, "private final", "private static final");

		_content = StringUtil.replace(
			_content, javaTermContent, newJavaTermContent);
	}

	protected void checkTestAnnotations(JavaTerm javaTerm) {
		int methodType = javaTerm.getType();

		if ((methodType != JavaTerm.TYPE_METHOD_PUBLIC) &&
			(methodType != JavaTerm.TYPE_METHOD_PUBLIC_STATIC)) {

			return;
		}

		checkAnnotationForMethod(
			javaTerm, "After", "^.*tearDown\\z", JavaTerm.TYPE_METHOD_PUBLIC,
			_fileName);
		checkAnnotationForMethod(
			javaTerm, "AfterClass", "^.*tearDownClass\\z",
			JavaTerm.TYPE_METHOD_PUBLIC_STATIC, _fileName);
		checkAnnotationForMethod(
			javaTerm, "Before", "^.*setUp\\z", JavaTerm.TYPE_METHOD_PUBLIC,
			_fileName);
		checkAnnotationForMethod(
			javaTerm, "BeforeClass", "^.*setUpClass\\z",
			JavaTerm.TYPE_METHOD_PUBLIC_STATIC, _fileName);
		checkAnnotationForMethod(
			javaTerm, "Test", "^.*test", JavaTerm.TYPE_METHOD_PUBLIC,
			_fileName);
	}

	protected void fixJavaTermsDividers(
		Set<JavaTerm> javaTerms, List<String> javaTermSortExclusions) {

		JavaTerm previousJavaTerm = null;

		Iterator<JavaTerm> itr = javaTerms.iterator();

		while (itr.hasNext()) {
			JavaTerm javaTerm = itr.next();

			if (previousJavaTerm == null) {
				previousJavaTerm = javaTerm;

				continue;
			}

			String javaTermContent = javaTerm.getContent();

			if (javaTermContent.startsWith(_indent + "//")) {
				previousJavaTerm = javaTerm;

				continue;
			}

			String previousJavaTermContent = previousJavaTerm.getContent();

			if (previousJavaTermContent.startsWith(_indent + "//")) {
				previousJavaTerm = javaTerm;

				continue;
			}

			String javaTermName = javaTerm.getName();

			if (BaseSourceProcessor.isExcluded(
					javaTermSortExclusions, _absolutePath,
					javaTerm.getLineCount(), javaTermName)) {

				previousJavaTerm = javaTerm;

				continue;
			}

			String previousJavaTermName = previousJavaTerm.getName();

			boolean requiresEmptyLine = false;

			if (previousJavaTerm.getType() != javaTerm.getType()) {
				requiresEmptyLine = true;
			}
			else if (!javaTerm.isVariable()) {
				requiresEmptyLine = true;
			}
			else if ((StringUtil.isUpperCase(javaTermName) &&
					  !StringUtil.isLowerCase(javaTermName)) ||
					 (StringUtil.isUpperCase(previousJavaTermName) &&
					  !StringUtil.isLowerCase(previousJavaTermName))) {

				requiresEmptyLine = true;
			}
			else if (hasAnnotationCommentOrJavadoc(javaTermContent) ||
					 hasAnnotationCommentOrJavadoc(previousJavaTermContent)) {

				requiresEmptyLine = true;
			}
			else if ((previousJavaTerm.getType() ==
						JavaTerm.TYPE_VARIABLE_PRIVATE_STATIC) &&
					 (previousJavaTermName.equals("_instance") ||
					  previousJavaTermName.equals("_log") ||
					  previousJavaTermName.equals("_logger"))) {

				requiresEmptyLine = true;
			}
			else if (previousJavaTermContent.contains("\n\n\t") ||
					 javaTermContent.contains("\n\n\t")) {

				requiresEmptyLine = true;
			}

			if (requiresEmptyLine) {
				if (!_content.contains("\n\n" + javaTermContent)) {
					_content = StringUtil.replace(
						_content, "\n" + javaTermContent,
						"\n\n" + javaTermContent);

					return;
				}
			}
			else if (_content.contains("\n\n" + javaTermContent)) {
				_content = StringUtil.replace(
					_content, "\n\n" + javaTermContent, "\n" + javaTermContent);

				return;
			}

			previousJavaTerm = javaTerm;
		}
	}

	protected String fixLeadingTabs(
		String content, String line, int expectedTabCount) {

		int leadingTabCount = JavaSourceProcessor.getLeadingTabCount(line);

		String newLine = line;

		while (leadingTabCount != expectedTabCount) {
			if (leadingTabCount > expectedTabCount) {
				newLine = StringUtil.replaceFirst(
					newLine, StringPool.TAB, StringPool.BLANK);

				leadingTabCount--;
			}
			else {
				newLine = StringPool.TAB + newLine;

				leadingTabCount++;
			}
		}

		return StringUtil.replace(content, line, newLine);
	}

	protected void fixTabsAndIncorrectEmptyLines(JavaTerm javaTerm) {
		if (!javaTerm.isConstructor() && !javaTerm.isMethod()) {
			return;
		}

		String javaTermContent = "\n" + javaTerm.getContent();

		Pattern methodNameAndParametersPattern = Pattern.compile(
			"\n" + _indent + "(private |protected |public )(.|\n)*?(\\{|;)\n");

		Matcher matcher = methodNameAndParametersPattern.matcher(
			javaTermContent);

		if (!matcher.find()) {
			return;
		}

		String methodNameAndParameters = matcher.group();

		String[] lines = StringUtil.splitLines(methodNameAndParameters);

		if (lines.length == 1) {
			if (methodNameAndParameters.endsWith("{\n") &&
				javaTermContent.contains(methodNameAndParameters + "\n") &&
				!javaTermContent.contains(
					methodNameAndParameters + "\n" + _indent + StringPool.TAB +
						"/*") &&
				!javaTermContent.contains(
					methodNameAndParameters + "\n" + _indent + StringPool.TAB +
						"// ")) {

				String trimmedJavaTermContent = StringUtil.trimTrailing(
					javaTermContent);

				if (!trimmedJavaTermContent.endsWith(
						"\n\n" + _indent + StringPool.CLOSE_CURLY_BRACE)) {

					_content = StringUtil.replace(
						_content, methodNameAndParameters + "\n",
						methodNameAndParameters);
				}
			}

			return;
		}

		if (methodNameAndParameters.endsWith("{\n") &&
			!javaTermContent.contains(methodNameAndParameters + "\n") &&
			!javaTermContent.contains(
				methodNameAndParameters + _indent +
					StringPool.CLOSE_CURLY_BRACE)) {

			_content = StringUtil.replace(
				_content, methodNameAndParameters,
				methodNameAndParameters + "\n");
		}

		boolean throwsException = methodNameAndParameters.contains(
			_indent + "throws ");

		String newMethodNameAndParameters = methodNameAndParameters;

		int expectedTabCount = -1;

		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];

			if (line.contains(_indent + "throws ")) {
				newMethodNameAndParameters = fixLeadingTabs(
					newMethodNameAndParameters, line, _indent.length() + 1);

				break;
			}

			if (expectedTabCount == -1) {
				if (line.endsWith(StringPool.OPEN_PARENTHESIS)) {
					expectedTabCount =
						Math.max(
							JavaSourceProcessor.getLeadingTabCount(line),
							_indent.length()) +
								1;

					if (throwsException &&
						(expectedTabCount == (_indent.length() + 1))) {

						expectedTabCount += 1;
					}
				}
			}
			else {
				String previousLine = lines[i - 1];

				if (previousLine.endsWith(StringPool.COMMA) ||
					previousLine.endsWith(StringPool.OPEN_PARENTHESIS)) {

					newMethodNameAndParameters = fixLeadingTabs(
						newMethodNameAndParameters, line, expectedTabCount);
				}
				else {
					newMethodNameAndParameters = fixLeadingTabs(
						newMethodNameAndParameters, line,
						JavaSourceProcessor.getLeadingTabCount(previousLine) +
							1);
				}
			}
		}

		_content = StringUtil.replace(
			_content, methodNameAndParameters, newMethodNameAndParameters);
	}

	protected void formatAnnotations(
			JavaTerm javaTerm, List<String> testAnnotationsExclusions)
		throws Exception {

		if ((_indent.length() == 1) && _fileName.contains("/test/") &&
			!BaseSourceProcessor.isExcluded(
				testAnnotationsExclusions, _absolutePath) &&
			!_fileName.endsWith("TestCase.java")) {

			checkTestAnnotations(javaTerm);
		}

		String javaTermContent = javaTerm.getContent();

		String newJavaTermContent = JavaSourceProcessor.sortAnnotations(
			javaTermContent, _indent);

		if (!javaTermContent.equals(newJavaTermContent)) {
			_content = _content.replace(javaTermContent, newJavaTermContent);
		}
	}

	protected String getClassName(String line) {
		int pos = line.indexOf(" extends ");

		if (pos == -1) {
			pos = line.indexOf(" implements ");
		}

		if (pos == -1) {
			pos = line.indexOf(StringPool.OPEN_CURLY_BRACE);
		}

		if (pos != -1) {
			line = line.substring(0, pos);
		}

		pos = line.indexOf(StringPool.LESS_THAN);

		if (pos != -1) {
			line = line.substring(0, pos);
		}

		line = line.trim();

		pos = line.lastIndexOf(StringPool.SPACE);

		return line.substring(pos + 1);
	}

	protected String getConstructorOrMethodName(String line, int pos) {
		line = line.substring(0, pos);

		int x = line.lastIndexOf(StringPool.SPACE);

		return line.substring(x + 1);
	}

	protected Set<JavaTerm> getJavaTerms() throws Exception {
		if (_javaTerms != null) {
			return _javaTerms;
		}

		Set<JavaTerm> javaTerms = new TreeSet<JavaTerm>(
			new JavaTermComparator(false));
		List<JavaTerm> staticBlocks = new ArrayList<JavaTerm>();

		UnsyncBufferedReader unsyncBufferedReader = new UnsyncBufferedReader(
			new UnsyncStringReader(_content));

		int index = 0;
		int lineCount = _lineCount - 1;

		String line = null;

		JavaTerm javaTerm = null;

		String javaTermName = null;
		int javaTermLineCount = -1;
		int javaTermStartPosition = -1;
		int javaTermType = -1;

		int lastCommentOrAnnotationPos = -1;

		while ((line = unsyncBufferedReader.readLine()) != null) {
			lineCount++;

			if (JavaSourceProcessor.getLeadingTabCount(line) !=
					_indent.length()) {

				index = index + line.length() + 1;

				continue;
			}

			if (line.startsWith(_indent + "private ") ||
				line.equals(_indent + "private") ||
				line.startsWith(_indent + "protected ") ||
				line.equals(_indent + "protected") ||
				line.startsWith(_indent + "public ") ||
				line.equals(_indent + "public") ||
				line.equals(_indent + "static {")) {

				Tuple tuple = getJavaTermTuple(line, _content, index);

				if (tuple == null) {
					return null;
				}

				int javaTermEndPosition = 0;

				if (lastCommentOrAnnotationPos == -1) {
					javaTermEndPosition = index;
				}
				else {
					javaTermEndPosition = lastCommentOrAnnotationPos;
				}

				if ((javaTermStartPosition != -1) &&
					(javaTermEndPosition < _content.length())) {

					String javaTermContent = _content.substring(
						javaTermStartPosition, javaTermEndPosition);

					if (!isValidJavaTerm(javaTermContent)) {
						return null;
					}

					if (Validator.isNotNull(javaTermName)) {
						javaTerm = new JavaTerm(
							javaTermName, javaTermType, javaTermContent,
							javaTermLineCount);

						if (javaTermType == JavaTerm.TYPE_STATIC_BLOCK) {
							staticBlocks.add(javaTerm);
						}
						else {
							javaTerms.add(javaTerm);

							if (javaTerm.isClass()) {
								JavaClass innerClass = new JavaClass(
									javaTermName, _fileName, _absolutePath,
									javaTermContent, javaTermLineCount,
									_indent + StringPool.TAB, this,
									_javaTermAccessLevelModifierExclusions);

								_innerClasses.add(innerClass);
							}
						}
					}
				}

				javaTermLineCount = lineCount;
				javaTermName = (String)tuple.getObject(0);
				javaTermStartPosition = javaTermEndPosition;
				javaTermType = (Integer)tuple.getObject(1);

				lastCommentOrAnnotationPos = -1;
			}
			else if (hasAnnotationCommentOrJavadoc(line)) {
				if (lastCommentOrAnnotationPos == -1) {
					lastCommentOrAnnotationPos = index;
				}
			}
			else if (!line.startsWith(_indent + StringPool.CLOSE_CURLY_BRACE) &&
					 !line.startsWith(_indent + StringPool.CLOSE_PARENTHESIS) &&
					 !line.startsWith(_indent + "extends") &&
					 !line.startsWith(_indent + "implements") &&
					 !BaseSourceProcessor.isExcluded(
						 _javaTermAccessLevelModifierExclusions, _absolutePath,
						 lineCount)) {

				Matcher matcher = _classPattern.matcher(_content);

				if (matcher.find()) {
					String insideClass = _content.substring(matcher.end());

					if (insideClass.contains(line)) {
						BaseSourceProcessor.processErrorMessage(
							_fileName,
							"Missing access level modifier: " + _fileName +
								" " + lineCount);
					}
				}
			}

			index = index + line.length() + 1;
		}

		if (javaTermStartPosition != -1) {
			int javaTermEndPosition =
				_content.lastIndexOf(StringPool.CLOSE_CURLY_BRACE) -
					_indent.length();

			String javaTermContent = _content.substring(
				javaTermStartPosition, javaTermEndPosition);

			if (!isValidJavaTerm(javaTermContent)) {
				return null;
			}

			javaTerm = new JavaTerm(
				javaTermName, javaTermType, javaTermContent, javaTermLineCount);

			if (javaTermType == JavaTerm.TYPE_STATIC_BLOCK) {
				staticBlocks.add(javaTerm);
			}
			else {
				javaTerms.add(javaTerm);

				if (javaTerm.isClass()) {
					JavaClass innerClass = new JavaClass(
						javaTermName, _fileName, _absolutePath, javaTermContent,
						javaTermLineCount, _indent + StringPool.TAB, this,
						_javaTermAccessLevelModifierExclusions);

					_innerClasses.add(innerClass);
				}
			}
		}

		_javaTerms = addStaticBlocks(javaTerms, staticBlocks);

		return _javaTerms;
	}

	protected Tuple getJavaTermTuple(String line, String content, int index) {
		int posStartNextLine = index;

		while (!line.endsWith(StringPool.OPEN_CURLY_BRACE) &&
			   !line.endsWith(StringPool.SEMICOLON)) {

			posStartNextLine =
				content.indexOf(StringPool.NEW_LINE, posStartNextLine) + 1;

			int posEndNextline = content.indexOf(
				StringPool.NEW_LINE, posStartNextLine);

			String nextLine = content.substring(
				posStartNextLine, posEndNextline);

			nextLine = StringUtil.trimLeading(nextLine);

			if (line.endsWith(StringPool.OPEN_PARENTHESIS)) {
				line += nextLine;
			}
			else {
				line += StringPool.SPACE + nextLine;
			}
		}

		line = StringUtil.replace(line, " synchronized " , StringPool.SPACE);

		int pos = line.indexOf(StringPool.OPEN_PARENTHESIS);

		if (line.startsWith(_indent + "public static ")) {
			if (line.contains(" class ") || line.contains(" enum ")) {
				return new Tuple(
					getClassName(line), JavaTerm.TYPE_CLASS_PUBLIC_STATIC);
			}

			if (line.contains(StringPool.EQUAL) ||
				(line.endsWith(StringPool.SEMICOLON) && (pos == -1))) {

				return new Tuple(
					getVariableName(line),
					JavaTerm.TYPE_VARIABLE_PUBLIC_STATIC);
			}

			if (pos != -1) {
				return new Tuple(
					getConstructorOrMethodName(line, pos),
					JavaTerm.TYPE_METHOD_PUBLIC_STATIC);
			}
		}
		else if (line.startsWith(_indent + "public ")) {
			if (line.contains(" @interface ") || line.contains(" class ") ||
				line.contains(" enum ") || line.contains(" interface ")) {

				return new Tuple(
					getClassName(line), JavaTerm.TYPE_CLASS_PUBLIC);
			}

			if (line.contains(StringPool.EQUAL) ||
				(line.endsWith(StringPool.SEMICOLON) && (pos == -1))) {

				return new Tuple(
					getVariableName(line), JavaTerm.TYPE_VARIABLE_PUBLIC);
			}

			if (pos != -1) {
				int spaceCount = StringUtil.count(
					line.substring(0, pos), StringPool.SPACE);

				if (spaceCount == 1) {
					return new Tuple(
						getConstructorOrMethodName(line, pos),
						JavaTerm.TYPE_CONSTRUCTOR_PUBLIC);
				}

				if (spaceCount > 1) {
					return new Tuple(
						getConstructorOrMethodName(line, pos),
						JavaTerm.TYPE_METHOD_PUBLIC);
				}
			}
		}
		else if (line.startsWith(_indent + "protected static ")) {
			if (line.contains(" class ") || line.contains(" enum ")) {
				return new Tuple(
					getClassName(line), JavaTerm.TYPE_CLASS_PROTECTED_STATIC);
			}

			if (line.contains(StringPool.EQUAL) ||
				(line.endsWith(StringPool.SEMICOLON) && (pos == -1))) {

				return new Tuple(
					getVariableName(line),
					JavaTerm.TYPE_VARIABLE_PROTECTED_STATIC);
			}

			if (pos != -1) {
				return new Tuple(
					getConstructorOrMethodName(line, pos),
					JavaTerm.TYPE_METHOD_PROTECTED_STATIC);
			}
		}
		else if (line.startsWith(_indent + "protected ")) {
			if (line.contains(" @interface ") || line.contains(" class ") ||
				line.contains(" enum ") || line.contains(" interface ")) {

				return new Tuple(
					getClassName(line), JavaTerm.TYPE_CLASS_PROTECTED);
			}

			if (pos != -1) {
				if (!line.contains(StringPool.EQUAL)) {
					int spaceCount = StringUtil.count(
						line.substring(0, pos), StringPool.SPACE);

					if (spaceCount == 1) {
						return new Tuple(
							getConstructorOrMethodName(line, pos),
							JavaTerm.TYPE_CONSTRUCTOR_PROTECTED);
					}

					if (spaceCount > 1) {
						return new Tuple(
							getConstructorOrMethodName(line, pos),
							JavaTerm.TYPE_METHOD_PROTECTED);
					}
				}
			}

			return new Tuple(
				getVariableName(line), JavaTerm.TYPE_VARIABLE_PROTECTED);
		}
		else if (line.startsWith(_indent + "private static ")) {
			if (line.contains(" class ") || line.contains(" enum ")) {
				return new Tuple(
					getClassName(line), JavaTerm.TYPE_CLASS_PRIVATE_STATIC);
			}

			if (line.contains(StringPool.EQUAL) ||
				(line.endsWith(StringPool.SEMICOLON) && (pos == -1))) {

				return new Tuple(
					getVariableName(line),
					JavaTerm.TYPE_VARIABLE_PRIVATE_STATIC);
			}

			if (pos != -1) {
				return new Tuple(
					getConstructorOrMethodName(line, pos),
					JavaTerm.TYPE_METHOD_PRIVATE_STATIC);
			}
		}
		else if (line.startsWith(_indent + "private ")) {
			if (line.contains(" @interface ") || line.contains(" class ") ||
				line.contains(" enum ") || line.contains(" interface ")) {

				return new Tuple(
					getClassName(line), JavaTerm.TYPE_CLASS_PRIVATE);
			}

			if (line.contains(StringPool.EQUAL) ||
				(line.endsWith(StringPool.SEMICOLON) && (pos == -1))) {

				return new Tuple(
					getVariableName(line), JavaTerm.TYPE_VARIABLE_PRIVATE);
			}

			if (pos != -1) {
				int spaceCount = StringUtil.count(
					line.substring(0, pos), StringPool.SPACE);

				if (spaceCount == 1) {
					return new Tuple(
						getConstructorOrMethodName(line, pos),
						JavaTerm.TYPE_CONSTRUCTOR_PRIVATE);
				}

				if (spaceCount > 1) {
					return new Tuple(
						getConstructorOrMethodName(line, pos),
						JavaTerm.TYPE_METHOD_PRIVATE);
				}
			}
		}
		else if (line.startsWith(_indent + "static {")) {
			return new Tuple("static", JavaTerm.TYPE_STATIC_BLOCK);
		}

		return null;
	}

	protected String getVariableName(String line) {
		int x = line.indexOf(StringPool.EQUAL);
		int y = line.lastIndexOf(StringPool.SPACE);

		if (x != -1) {
			line = line.substring(0, x);
			line = StringUtil.trim(line);

			y = line.lastIndexOf(StringPool.SPACE);

			return line.substring(y + 1);
		}

		if (line.endsWith(StringPool.SEMICOLON)) {
			return line.substring(y + 1, line.length() - 1);
		}

		return StringPool.BLANK;
	}

	protected boolean hasAnnotationCommentOrJavadoc(String s) {
		if (s.startsWith(_indent + StringPool.AT) ||
			s.startsWith(_indent + StringPool.SLASH) ||
			s.startsWith(_indent + " *")) {

			return true;
		}
		else {
			return false;
		}
	}

	protected boolean isFinalableField(
		JavaTerm javaTerm, String javaTermClassName, Pattern pattern,
		boolean checkOuterClass) {

		if (checkOuterClass && (_outerClass != null)) {
			return _outerClass.isFinalableField(
				javaTerm, javaTermClassName, pattern, true);
		}

		for (JavaTerm curJavaTerm : _javaTerms) {
			if (!curJavaTerm.isMethod() &&
				(!curJavaTerm.isConstructor() ||
				 javaTermClassName.equals(_name))) {

				continue;
			}

			Matcher matcher = pattern.matcher(curJavaTerm.getContent());

			if (matcher.find()) {
				return false;
			}
		}

		for (JavaClass innerClass : _innerClasses) {
			if (!innerClass.isFinalableField(
					javaTerm, javaTermClassName, pattern, false)) {

				return false;
			}
		}

		return true;
	}

	protected boolean isValidJavaTerm(String content) {
		if (content.startsWith(_indent + "static {")) {
			return true;
		}

		while (!content.startsWith(_indent + "private") &&
			   !content.startsWith(_indent + "protected") &&
			   !content.startsWith(_indent + "public")) {

			content = content.substring(content.indexOf("\n") + 1);
		}

		int indentLinesCount =
			StringUtil.count(content, "\n" + _indent) -
				StringUtil.count(content, "\n" + _indent + StringPool.TAB);

		content = StringUtil.trim(content);

		if (content.endsWith(StringPool.CLOSE_CURLY_BRACE) &&
			((indentLinesCount == 1) ||
			 (((indentLinesCount == 2) || (indentLinesCount == 3)) &&
			  content.contains("\n" + _indent + "static {")))) {

			return true;
		}
		else if ((content.endsWith("};") && (indentLinesCount == 1)) ||
				 (content.endsWith(StringPool.SEMICOLON) &&
				  (indentLinesCount == 0))) {

			return true;
		}

		return false;
	}

	protected void sortJavaTerms(
		JavaTerm previousJavaTerm, JavaTerm javaTerm,
		List<String> javaTermSortExclusions) {

		if (previousJavaTerm == null) {
			return;
		}

		String javaTermName = javaTerm.getName();

		if (BaseSourceProcessor.isExcluded(
				javaTermSortExclusions, _absolutePath, -1, javaTermName)) {

			return;
		}

		if (previousJavaTerm.getLineCount() <= javaTerm.getLineCount()) {
			return;
		}

		String previousJavaTermName = previousJavaTerm.getName();

		String javaTermNameLowerCase = StringUtil.toLowerCase(javaTermName);
		String previousJavaTermNameLowerCase = StringUtil.toLowerCase(
			previousJavaTermName);

		if (_fileName.contains("persistence") &&
			((previousJavaTermName.startsWith("doCount") &&
			  javaTermName.startsWith("doCount")) ||
			 (previousJavaTermName.startsWith("doFind") &&
			  javaTermName.startsWith("doFind")) ||
			 (previousJavaTermNameLowerCase.startsWith("count") &&
			  javaTermNameLowerCase.startsWith("count")) ||
			 (previousJavaTermNameLowerCase.startsWith("filter") &&
			  javaTermNameLowerCase.startsWith("filter")) ||
			 (previousJavaTermNameLowerCase.startsWith("find") &&
			  javaTermNameLowerCase.startsWith("find")) ||
			 (previousJavaTermNameLowerCase.startsWith("join") &&
			  javaTermNameLowerCase.startsWith("join")))) {
		}
		else {
			_content = StringUtil.replaceFirst(
				_content, "\n" + javaTerm.getContent(),
				"\n" + previousJavaTerm.getContent());
			_content = StringUtil.replaceLast(
				_content, "\n" + previousJavaTerm.getContent(),
				"\n" + javaTerm.getContent());
		}
	}

	private String _absolutePath;
	private Pattern _camelCasePattern = Pattern.compile("([a-z])([A-Z0-9])");
	private Pattern _classPattern = Pattern.compile(
		"(private |protected |public )(static )*class ([\\s\\S]*?) \\{\n");
	private String _content;
	private String _fileName;
	private String _indent;
	private List<JavaClass> _innerClasses = new ArrayList<JavaClass>();
	private List<String> _javaTermAccessLevelModifierExclusions;
	private Set<JavaTerm> _javaTerms;
	private int _lineCount;
	private String _name;
	private JavaClass _outerClass;

}