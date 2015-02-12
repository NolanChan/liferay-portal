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

import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReader;
import com.liferay.portal.util.FileImpl;
import com.liferay.portal.xml.SAXReaderImpl;
import com.liferay.util.xml.DocUtil;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.AbstractJavaEntity;
import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaParameter;
import com.thoughtworks.qdox.model.Type;

import jargs.gnu.CmdLineParser;

import java.io.File;
import java.io.Reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.tools.ant.DirectoryScanner;

/**
 * @author Brian Wing Shun Chan
 */
public class JavadocBuilder {

	public static void main(String[] args) {
		try {
			new JavadocBuilder(args);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JavadocBuilder(String[] args) throws Exception {
		CmdLineParser cmdLineParser = new CmdLineParser();

		cmdLineParser.parse(args);

		CmdLineParser.Option commandOption = cmdLineParser.addStringOption(
			"command");

		String command = (String)cmdLineParser.getOptionValue(commandOption);

		CmdLineParser.Option limitOption = cmdLineParser.addStringOption(
			"limit");

		String limit = (String)cmdLineParser.getOptionValue(limitOption);

		CmdLineParser.Option ignoreAutogeneratedOption =
			cmdLineParser.addBooleanOption("ignoreAutogenerated");

		Boolean ignoreAutogenerated = (Boolean)cmdLineParser.getOptionValue(
			ignoreAutogeneratedOption);

		_process(command, limit, ignoreAutogenerated);
	}

	private void _addClassCommentElement(
		Element rootElement, JavaClass javaClass) {

		Element commentElement = rootElement.addElement("comment");

		String comment = _getCDATA(javaClass);

		if (comment.startsWith("Copyright (c) 2000-present Liferay, Inc.")) {
			comment = StringPool.BLANK;
		}

		if (comment.startsWith(
				"<a href=\"" + javaClass.getName() + ".java.html\">")) {

			int pos = comment.indexOf("</a>");

			comment = comment.substring(pos + 4).trim();
		}

		commentElement.addCDATA(comment);
	}

	private void _addDocletElements(
		Element parentElement, AbstractJavaEntity abstractJavaEntity,
		String name) {

		DocletTag[] docletTags = abstractJavaEntity.getTagsByName(name);

		for (DocletTag docletTag : docletTags) {
			String value = docletTag.getValue();

			if (name.equals("author") || name.equals("see") ||
				name.equals("since") || name.equals("version")) {

				/*if (value.startsWith("Raymond Aug")) {
					value = "Raymond Aug\u00c3\u00a9";
				}*/

				DocUtil.add(parentElement, name, value);
			}
			else {
				Element element = parentElement.addElement(name);

				element.addCDATA(value);
			}
		}
	}

	private void _addDocletTags(
		Element parentElement, String name, String indent, StringBuilder sb) {

		List<Element> elements = parentElement.elements(name);

		for (Element element : elements) {
			sb.append(indent);
			sb.append(" * @");
			sb.append(name);
			sb.append(" ");

			Element commentElement = element.element("comment");

			if (commentElement != null) {
				sb.append(element.elementText("name"));
				sb.append(" ");
				sb.append(_getCDATA(element.elementText("comment")));
			}
			else {
				sb.append(_getCDATA(element.getText()));
			}

			sb.append("\n");
		}
	}

	private void _addFieldElement(Element rootElement, JavaField javaField) {
		Element fieldElement = rootElement.addElement("field");

		DocUtil.add(fieldElement, "name", javaField.getName());

		Element commentElement = fieldElement.addElement("comment");

		commentElement.addCDATA(_getCDATA(javaField));

		_addDocletElements(fieldElement, javaField, "deprecated");
		_addDocletElements(fieldElement, javaField, "see");
		_addDocletElements(fieldElement, javaField, "since");
		_addDocletElements(fieldElement, javaField, "version");
	}

	private void _addMethodElement(Element rootElement, JavaMethod javaMethod) {
		Element methodElement = rootElement.addElement("method");

		DocUtil.add(methodElement, "name", javaMethod.getName());

		Element commentElement = methodElement.addElement("comment");

		commentElement.addCDATA(_getCDATA(javaMethod));

		_addDocletElements(methodElement, javaMethod, "deprecated");
		_addParamElements(methodElement, javaMethod);
		_addReturnElement(methodElement, javaMethod);
		_addDocletElements(methodElement, javaMethod, "see");
		_addDocletElements(methodElement, javaMethod, "since");
		_addThrowsElements(methodElement, javaMethod);
		_addDocletElements(methodElement, javaMethod, "version");
	}

	private void _addParamElement(
		Element methodElement, JavaParameter javaParameter,
		DocletTag[] paramDocletTags) {

		String name = javaParameter.getName();
		String type = javaParameter.getType().getValue();
		String value = null;

		for (DocletTag paramDocletTag : paramDocletTags) {
			String curValue = paramDocletTag.getValue();

			if (!curValue.startsWith(name)) {
				continue;
			}
			else {
				curValue = value;

				break;
			}
		}

		Element paramElement = methodElement.addElement("param");

		DocUtil.add(paramElement, "name", name);
		DocUtil.add(paramElement, "type", type);

		if (value != null) {
			value = value.substring(name.length());
		}

		Element commentElement = paramElement.addElement("comment");

		commentElement.addCDATA(_getCDATA(value));
	}

	private void _addParamElements(
		Element methodElement, JavaMethod javaMethod) {

		JavaParameter[] javaParameters = javaMethod.getParameters();

		DocletTag[] paramDocletTags = javaMethod.getTagsByName("param");

		for (JavaParameter javaParameter : javaParameters) {
			_addParamElement(methodElement, javaParameter, paramDocletTags);
		}
	}

	private void _addReturnElement(
		Element methodElement, JavaMethod javaMethod) {

		Type returns = javaMethod.getReturns();

		if ((returns == null) || returns.getValue().equals("void")) {
			return;
		}

		_addDocletElements(methodElement, javaMethod, "return");
	}

	private void _addThrowsElement(
		Element methodElement, Type exception, DocletTag[] throwsDocletTags) {

		String name = exception.getJavaClass().getName();
		String value = null;

		for (DocletTag throwsDocletTag : throwsDocletTags) {
			String curValue = throwsDocletTag.getValue();

			if (!curValue.startsWith(name)) {
				continue;
			}
			else {
				curValue = value;

				break;
			}
		}

		Element throwsElement = methodElement.addElement("throws");

		DocUtil.add(throwsElement, "name", name);
		DocUtil.add(throwsElement, "type", exception.getValue());

		if (value != null) {
			value = value.substring(name.length());
		}

		Element commentElement = throwsElement.addElement("comment");

		commentElement.addCDATA(_getCDATA(value));
	}

	private void _addThrowsElements(
		Element methodElement, JavaMethod javaMethod) {

		Type[] exceptions = javaMethod.getExceptions();

		DocletTag[] throwsDocletTags = javaMethod.getTagsByName("throws");

		for (Type exception : exceptions) {
			_addThrowsElement(methodElement, exception, throwsDocletTags);
		}
	}

	private String _getCDATA(AbstractJavaEntity abstractJavaEntity) {
		return _getCDATA(abstractJavaEntity.getComment());
	}

	private String _getCDATA(String cdata) {
		if (cdata == null) {
			return StringPool.BLANK;
		}

		cdata = StringUtil.replace(
			cdata, new String[] {"\n", "<p>", "</p>"},
			new String[] {" ", " <p> ", " </p> "});

		while (cdata.contains("  ")) {
			cdata = StringUtil.replace(cdata, "  ", " ");
		}

		return cdata.trim();
	}

	private String _getFieldKey(Element fieldElement) {
		return fieldElement.elementText("name");
	}

	private String _getFieldKey(JavaField javaField) {
		return javaField.getName();
	}

	private JavaClass _getJavaClass(String fileName) throws Exception {
		return _getJavaClass(fileName, null);
	}

	private JavaClass _getJavaClass(String fileName, Reader reader)
		throws Exception {

		int pos = fileName.indexOf("src/");

		if (pos == -1) {
			pos = fileName.indexOf("test/");
		}

		if (pos == -1) {
			throw new RuntimeException(fileName);
		}

		pos = fileName.indexOf("/", pos);

		String srcFile = fileName.substring(pos + 1);
		String className = StringUtil.replace(
			srcFile.substring(0, srcFile.length() - 5), "/", ".");

		JavaDocBuilder builder = new JavaDocBuilder();

		if (reader == null) {
			File file = new File(fileName);

			if (!file.exists()) {
				return null;
			}

			builder.addSource(file);
		}
		else {
			builder.addSource(reader);
		}

		return builder.getClassByName(className);
	}

	private String _getJavaClassComment(Element rootElement) {
		StringBuilder sb = new StringBuilder();

		sb.append("/**\n");
		sb.append(" * ");
		sb.append(_getCDATA(rootElement.elementText("comment")));
		sb.append("\n");
		sb.append(" *\n");

		String indent = StringPool.BLANK;

		_addDocletTags(rootElement, "author", indent, sb);
		_addDocletTags(rootElement, "deprecated", indent, sb);
		_addDocletTags(rootElement, "see", indent, sb);
		_addDocletTags(rootElement, "serial", indent, sb);
		_addDocletTags(rootElement, "since", indent, sb);
		_addDocletTags(rootElement, "version", indent, sb);

		sb.append(" */\n");

		return sb.toString();
	}

	private String _getJavadocXml(JavaClass javaClass) throws Exception {
		Element rootElement = _saxReader.createElement("javadoc");

		Document document = _saxReader.createDocument(rootElement);

		DocUtil.add(rootElement, "name", javaClass.getName());
		DocUtil.add(rootElement, "type", javaClass.getFullyQualifiedName());

		_addClassCommentElement(rootElement, javaClass);
		_addDocletElements(rootElement, javaClass, "author");
		_addDocletElements(rootElement, javaClass, "deprecated");
		_addDocletElements(rootElement, javaClass, "see");
		_addDocletElements(rootElement, javaClass, "serial");
		_addDocletElements(rootElement, javaClass, "since");
		_addDocletElements(rootElement, javaClass, "version");

		JavaMethod[] javaMethods = javaClass.getMethods();

		for (JavaMethod javaMethod : javaMethods) {
			_addMethodElement(rootElement, javaMethod);
		}

		JavaField[] javaFields = javaClass.getFields();

		for (JavaField javaField : javaFields) {
			_addFieldElement(rootElement, javaField);
		}

		return document.formattedString();
	}

	private String _getJavaFieldComment(
		String[] lines, Map<String, Element> fieldElementsMap,
		JavaField javaField) {

		String fieldKey = _getFieldKey(javaField);

		Element fieldElement = fieldElementsMap.get(fieldKey);

		if (fieldElement == null) {
			return null;
		}

		String line = lines[javaField.getLineNumber() - 1];

		String indent = StringPool.BLANK;

		for (char c : line.toCharArray()) {
			if (Character.isWhitespace(c)) {
				indent += c;
			}
			else {
				break;
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(indent);
		sb.append("/**\n");
		sb.append(indent);
		sb.append(" * ");
		sb.append(fieldElement.elementText("comment"));
		sb.append("\n");
		sb.append(indent);
		sb.append(" *\n");

		_addDocletTags(fieldElement, "deprecated", indent, sb);
		_addDocletTags(fieldElement, "see", indent, sb);
		_addDocletTags(fieldElement, "since", indent, sb);
		_addDocletTags(fieldElement, "version", indent, sb);

		sb.append(indent);
		sb.append(" */\n");

		return sb.toString();
	}

	private String _getJavaMethodComment(
		String[] lines, Map<String, Element> methodElementsMap,
		JavaMethod javaMethod) {

		String methodKey = _getMethodKey(javaMethod);

		Element methodElement = methodElementsMap.get(methodKey);

		if (methodElement == null) {
			return null;
		}

		String line = lines[javaMethod.getLineNumber() - 1];

		String indent = StringPool.BLANK;

		for (char c : line.toCharArray()) {
			if (Character.isWhitespace(c)) {
				indent += c;
			}
			else {
				break;
			}
		}

		StringBuilder sb = new StringBuilder();

		sb.append(indent);
		sb.append("/**\n");
		sb.append(indent);
		sb.append(" * ");
		sb.append(methodElement.elementText("comment"));
		sb.append("\n");
		sb.append(indent);
		sb.append(" *\n");

		_addDocletTags(methodElement, "deprecated", indent, sb);
		_addDocletTags(methodElement, "param", indent, sb);
		_addDocletTags(methodElement, "return", indent, sb);
		_addDocletTags(methodElement, "see", indent, sb);
		_addDocletTags(methodElement, "since", indent, sb);
		_addDocletTags(methodElement, "throws", indent, sb);
		_addDocletTags(methodElement, "version", indent, sb);

		sb.append(indent);
		sb.append(" */\n");

		return sb.toString();
	}

	private String _getMethodKey(Element methodElement) {
		StringBuilder sb = new StringBuilder();

		sb.append(methodElement.elementText("name"));
		sb.append(StringPool.OPEN_PARENTHESIS);

		List<Element> paramElements = methodElement.elements("param");

		for (Element paramElement : paramElements) {
			sb.append(paramElement.elementText("name"));
			sb.append("|");
			sb.append(paramElement.elementText("type"));
			sb.append(",");
		}

		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	private String _getMethodKey(JavaMethod javaMethod) {
		StringBuilder sb = new StringBuilder();

		sb.append(javaMethod.getName());
		sb.append(StringPool.OPEN_PARENTHESIS);

		JavaParameter[] javaParameters = javaMethod.getParameters();

		for (JavaParameter javaParameter : javaParameters) {
			sb.append(javaParameter.getName());
			sb.append("|");
			sb.append(javaParameter.getType().getValue());
			sb.append(",");
		}

		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	private boolean _isGenerated(String content) {
		if (content.contains("<javadoc autogenerated=\"true\">")) {
			return true;
		}
		else {
			return false;
		}
	}

	private void _process(
			String command, String limit, Boolean ignoreAutogenerated)
		throws Exception {

		DirectoryScanner ds = new DirectoryScanner();

		ds.setBasedir(_BASEDIR);
		ds.setExcludes(
			new String[] {
				"**\\classes\\**", "**\\portal-client\\**", "**\\portal-web\\**"
			});

		List<String> includes = new ArrayList<>();

		if (Validator.isNotNull(limit) && !limit.startsWith("$")) {
			String[] limitArray = StringUtil.split(limit, '/');

			for (String curLimit : limitArray) {
				includes.add(
					"**\\" + StringUtil.replace(curLimit, ".", "\\") +
						"\\**\\*.java");
				includes.add("**\\" + curLimit + ".java");
			}
		}
		else {
			includes.add("**\\*.java");
		}

		ds.setIncludes(includes.toArray(new String[includes.size()]));

		ds.scan();

		String[] fileNames = ds.getIncludedFiles();

		for (String fileName : fileNames) {
			fileName = StringUtil.replace(fileName, "\\", "/");

			/*if (!fileName.endsWith("Isolation.java")) {
				continue;
			}*/

			if ((ignoreAutogenerated != null) &&
				ignoreAutogenerated.booleanValue()) {

				File file = new File(_BASEDIR + fileName);

				if (file.exists()) {
					String oldContent = _fileUtil.read(
						_BASEDIR + fileName + "doc");

					if (_isGenerated(oldContent)) {
						continue;
					}
				}
			}

			if (command.equals("cleanup")) {
				_processGet(fileName);
				_processSave(fileName);
				_processDelete(fileName);
			}
			else if (command.equals("commit")) {
				_processSave(fileName);
				_processDelete(fileName);
			}
			else if (command.equals("delete")) {
				_processDelete(fileName);
			}
			else if (command.equals("get")) {
				_processGet(fileName);
			}
			else if (command.equals("save")) {
				_processSave(fileName);
			}
		}
	}

	private void _processDelete(String fileName) throws Exception {
		_removeJavadocFromJava(fileName, true);
	}

	private void _processGet(String fileName) throws Exception {
		File javadocFile = new File(_BASEDIR + fileName + "doc");

		if (!javadocFile.exists()) {
			_updateJavadocFromJava(fileName);
		}

		String javaWithoutJavadoc = _removeJavadocFromJava(fileName, false);

		_updateJavaFromJavadoc(fileName, javaWithoutJavadoc);
	}

	private void _processSave(String fileName) throws Exception {
		_updateJavadocFromJava(fileName);
	}

	private String _removeJavadocFromJava(String fileName, boolean log)
		throws Exception {

		File file = new File(_BASEDIR + fileName);

		String oldContent = _fileUtil.read(file);

		String[] lines = StringUtil.splitLines(oldContent);

		JavaClass javaClass = _getJavaClass(
			fileName, new UnsyncStringReader(oldContent));

		Set<Integer> lineNumbers = new HashSet<>();

		lineNumbers.add(javaClass.getLineNumber());

		JavaMethod[] javaMethods = javaClass.getMethods();

		for (JavaMethod javaMethod : javaMethods) {
			lineNumbers.add(javaMethod.getLineNumber());
		}

		JavaField[] javaFields = javaClass.getFields();

		for (JavaField javaField : javaFields) {
			lineNumbers.add(javaField.getLineNumber());
		}

		for (int lineNumber : lineNumbers) {
			int pos = lineNumber - 2;

			String line = lines[pos].trim();

			if (line.endsWith("*/")) {
				while (true) {
					lines[pos] = null;

					if (line.startsWith("/**")) {
						break;
					}

					line = lines[--pos].trim();
				}
			}
		}

		StringBuilder sb = new StringBuilder(oldContent.length());

		for (String line : lines) {
			if (line != null) {
				sb.append(line);
				sb.append("\n");
			}
		}

		String newContent = sb.toString().trim();

		if ((oldContent == null) || !oldContent.equals(newContent)) {
			_fileUtil.write(file, newContent);

			if (log) {
				System.out.println("Writing " + file);
			}
		}

		return newContent;
	}

	private void _updateJavadocFromJava(String fileName) throws Exception {
		File file = new File(_BASEDIR + fileName + "doc");

		String oldContent = null;

		if (file.exists()) {
			oldContent = _fileUtil.read(file);

			if (_isGenerated(oldContent)) {
				return;
			}
		}

		JavaClass javaClass = _getJavaClass(fileName);

		String newContent = _getJavadocXml(javaClass);

		if ((oldContent == null) || !oldContent.equals(newContent)) {
			_fileUtil.write(file, newContent.getBytes());

			System.out.println("Writing " + file);
		}
	}

	private void _updateJavaFromJavadoc(String fileName, String oldContent)
		throws Exception {

		File javadocFile = new File(_BASEDIR + fileName + "doc");

		if (!javadocFile.exists()) {
			return;
		}

		File file = new File(_BASEDIR + fileName);

		if (oldContent == null) {
			oldContent = _fileUtil.read(file);
		}

		String[] lines = StringUtil.splitLines(oldContent);

		JavaClass javaClass = _getJavaClass(
			fileName, new UnsyncStringReader(oldContent));

		Document document = _saxReader.read(javadocFile);

		Element rootElement = document.getRootElement();

		Map<Integer, String> commentsMap = new TreeMap<>();

		commentsMap.put(
			javaClass.getLineNumber(), _getJavaClassComment(rootElement));

		Map<String, Element> methodElementsMap = new HashMap<>();

		List<Element> methodElements = rootElement.elements("method");

		for (Element methodElement : methodElements) {
			String methodKey = _getMethodKey(methodElement);

			methodElementsMap.put(methodKey, methodElement);
		}

		JavaMethod[] javaMethods = javaClass.getMethods();

		for (JavaMethod javaMethod : javaMethods) {
			if (commentsMap.containsKey(javaMethod.getLineNumber())) {
				continue;
			}

			commentsMap.put(
				javaMethod.getLineNumber(),
				_getJavaMethodComment(lines, methodElementsMap, javaMethod));
		}

		Map<String, Element> fieldElementsMap = new HashMap<>();

		List<Element> fieldElements = rootElement.elements("field");

		for (Element fieldElement : fieldElements) {
			String fieldKey = _getFieldKey(fieldElement);

			fieldElementsMap.put(fieldKey, fieldElement);
		}

		JavaField[] javaFields = javaClass.getFields();

		for (JavaField javaField : javaFields) {
			if (commentsMap.containsKey(javaField.getLineNumber())) {
				continue;
			}

			commentsMap.put(
				javaField.getLineNumber(),
				_getJavaFieldComment(lines, fieldElementsMap, javaField));
		}

		StringBuilder sb = new StringBuilder(oldContent.length());

		for (int lineNumber = 1; lineNumber <= lines.length; lineNumber++) {
			String line = lines[lineNumber - 1];

			String comments = commentsMap.get(lineNumber);

			if (comments != null) {
				sb.append(comments);
			}

			sb.append(line);
			sb.append("\n");
		}

		String newContent = sb.toString().trim();

		if ((oldContent == null) || !oldContent.equals(newContent)) {
			_fileUtil.write(file, newContent);

			System.out.println("Writing " + file);
		}
	}

	private static final String _BASEDIR = "./";

	private static final FileImpl _fileUtil = FileImpl.getInstance();
	private static final SAXReader _saxReader = new SAXReaderImpl();

}