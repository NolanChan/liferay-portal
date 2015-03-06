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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReader;
import com.liferay.portal.tools.javadocformatter.SinceJava;
import com.liferay.portal.tools.servicebuilder.ServiceBuilder;
import com.liferay.portal.util.FileImpl;
import com.liferay.portal.xml.SAXReaderImpl;
import com.liferay.util.xml.DocUtil;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.AbstractBaseJavaEntity;
import com.thoughtworks.qdox.model.AbstractJavaEntity;
import com.thoughtworks.qdox.model.Annotation;
import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaPackage;
import com.thoughtworks.qdox.model.JavaParameter;
import com.thoughtworks.qdox.model.Type;
import com.thoughtworks.qdox.model.annotation.AnnotationValue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tools.ant.DirectoryScanner;

/**
 * @author Brian Wing Shun Chan
 * @author Connor McKay
 * @author James Hinkey
 * @author Hugo Huijser
 */
public class JavadocFormatter {

	public static void main(String[] args) throws Exception {
		Map<String, String> arguments = ArgumentsUtil.parseArguments(args);

		try {
			new JavadocFormatter(arguments);
		}
		catch (Exception e) {
			ArgumentsUtil.processException(arguments, e);
		}
	}

	public JavadocFormatter(Map<String, String> arguments) throws Exception {
		String init = arguments.get("javadoc.init");

		if (Validator.isNotNull(init) && !init.startsWith("$")) {
			_initializeMissingJavadocs = GetterUtil.getBoolean(init);
		}

		_inputDir = GetterUtil.getString(arguments.get("javadoc.input.dir"));

		if (_inputDir.startsWith("$")) {
			_inputDir = "./";
		}

		if (!_inputDir.endsWith("/")) {
			_inputDir += "/";
		}

		System.out.println("Input directory is " + _inputDir);

		String[] limits = StringUtil.split(arguments.get("javadoc.limit"), ",");

		_outputFilePrefix = GetterUtil.getString(
			arguments.get("javadoc.output.file.prefix"));

		if (_outputFilePrefix.startsWith("$")) {
			_outputFilePrefix = "javadocs";
		}

		String update = arguments.get("javadoc.update");

		if (Validator.isNotNull(update) && !update.startsWith("$")) {
			_updateJavadocs = GetterUtil.getBoolean(update);
		}

		DirectoryScanner directoryScanner = new DirectoryScanner();

		directoryScanner.setBasedir(_inputDir);
		directoryScanner.setExcludes(
			new String[] {"**\\classes\\**", "**\\portal-client\\**"});

		for (String limit : limits) {
			List<String> includes = new ArrayList<String>();

			if (Validator.isNotNull(limit) && !limit.startsWith("$")) {
				System.out.println("Limit on " + limit);

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

			directoryScanner.setIncludes(
				includes.toArray(new String[includes.size()]));

			directoryScanner.scan();

			String[] fileNames = StringPool.EMPTY_ARRAY;

			fileNames = directoryScanner.getIncludedFiles();

			if ((fileNames.length == 0) && Validator.isNotNull(limit) &&
				!limit.startsWith("$")) {

				StringBundler sb = new StringBundler("Limit file not found: ");

				sb.append(limit);

				if (limit.contains(".")) {
					sb.append(
						" Specify limit filename without package path or ");
					sb.append("file type suffix.");
				}

				System.out.println(sb.toString());
			}

			_languagePropertiesFile = new File(
				"src/content/Language.properties");

			if (_languagePropertiesFile.exists()) {
				_languageProperties = new Properties();

				_languageProperties.load(
					new FileInputStream(
						_languagePropertiesFile.getAbsolutePath()));
			}

			for (String fileName : fileNames) {
				fileName = StringUtil.replace(fileName, "\\", "/");

				try {
					_format(fileName);
				}
				catch (Exception e) {
					throw new RuntimeException(
						"Unable to format file " + fileName, e);
				}
			}
		}

		for (Map.Entry<String, Tuple> entry : _javadocxXmlTuples.entrySet()) {
			Tuple tuple = entry.getValue();

			File javadocsXmlFile = (File)tuple.getObject(1);
			String oldJavadocsXmlContent = (String)tuple.getObject(2);
			Document javadocsXmlDocument = (Document)tuple.getObject(3);

			Element javadocsXmlRootElement =
				javadocsXmlDocument.getRootElement();

			javadocsXmlRootElement.sortElementsByChildElement(
				"javadoc", "type");

			String newJavadocsXmlContent =
				javadocsXmlDocument.formattedString();

			if (!oldJavadocsXmlContent.equals(newJavadocsXmlContent)) {
				_fileUtil.write(javadocsXmlFile, newJavadocsXmlContent);
			}

			_detachUnnecessaryTypes(javadocsXmlRootElement);

			File javadocsRuntimeXmlFile = new File(
				StringUtil.replaceLast(
					javadocsXmlFile.toString(), "-all.xml", "-rt.xml"));

			String oldJavadocsRuntimeXmlContent = StringPool.BLANK;

			if (javadocsRuntimeXmlFile.exists()) {
				oldJavadocsRuntimeXmlContent = _fileUtil.read(
					javadocsRuntimeXmlFile);
			}

			String newJavadocsRuntimeXmlContent =
				javadocsXmlDocument.compactString();

			if (!oldJavadocsRuntimeXmlContent.equals(
					newJavadocsRuntimeXmlContent)) {

				_fileUtil.write(
					javadocsRuntimeXmlFile, newJavadocsRuntimeXmlContent);
			}
		}
	}

	private List<Tuple> _addAncestorJavaClassTuples(
		JavaClass javaClass, List<Tuple> ancestorJavaClassTuples) {

		JavaClass superJavaClass = javaClass.getSuperJavaClass();

		if (superJavaClass != null) {
			ancestorJavaClassTuples.add(new Tuple(superJavaClass));

			ancestorJavaClassTuples = _addAncestorJavaClassTuples(
				superJavaClass, ancestorJavaClassTuples);
		}

		Type[] implementz = javaClass.getImplements();

		for (Type implement : implementz) {
			Type[] actualTypeArguments = implement.getActualTypeArguments();
			JavaClass implementedInterface = implement.getJavaClass();

			if (actualTypeArguments == null) {
				ancestorJavaClassTuples.add(new Tuple(implementedInterface));
			}
			else {
				ancestorJavaClassTuples.add(
					new Tuple(implementedInterface, actualTypeArguments));
			}

			ancestorJavaClassTuples = _addAncestorJavaClassTuples(
				implementedInterface, ancestorJavaClassTuples);
		}

		return ancestorJavaClassTuples;
	}

	private void _addClassCommentElement(
		Element rootElement, JavaClass javaClass) {

		String comment = _getCDATA(javaClass);

		if (comment.startsWith("Copyright (c)")) {
			comment = StringPool.BLANK;
		}

		if (Validator.isNull(comment)) {
			return;
		}

		Element commentElement = rootElement.addElement("comment");

		commentElement.addCDATA(comment);
	}

	private String _addDeprecatedTag(
		String comment, AbstractBaseJavaEntity abstractBaseJavaEntity,
		String indent) {

		if (comment == null) {
			return null;
		}

		if (!comment.contains("* @deprecated ") ||
			ServiceBuilder.hasAnnotation(
				abstractBaseJavaEntity, "Deprecated")) {

			return comment;
		}

		return comment + indent + "@Deprecated\n";
	}

	private void _addDocletElements(
			Element parentElement, AbstractJavaEntity abstractJavaEntity,
			String name)
		throws Exception {

		DocletTag[] docletTags = abstractJavaEntity.getTagsByName(name);

		for (DocletTag docletTag : docletTags) {
			String value = docletTag.getValue();

			value = _trimMultilineText(value);

			value = StringUtil.replace(value, " </", "</");

			Element element = parentElement.addElement(name);

			element.addCDATA(value);
		}

		if ((docletTags.length == 0) && name.equals("author")) {
			Element element = parentElement.addElement(name);

			element.addCDATA(ServiceBuilder.AUTHOR);
		}
	}

	private String _addDocletTags(
		Element parentElement, String[] tagNames, String indent,
		boolean publicAccess) {

		List<String> allTagNames = new ArrayList<String>();
		List<String> customTagNames = new ArrayList<String>();
		List<String> requiredTagNames = new ArrayList<String>();

		for (String tagName : tagNames) {
			List<Element> elements = parentElement.elements(tagName);

			for (Element element : elements) {
				Element commentElement = element.element("comment");

				String comment = null;

				// Get comment by comment element's text or the element's text

				if (commentElement != null) {
					comment = commentElement.getText();
				}
				else {
					comment = element.getText();
				}

				if (tagName.equals("param") || tagName.equals("return") ||
					tagName.equals("throws")) {

					if (Validator.isNotNull(comment)) {
						requiredTagNames.add(tagName);
					}
					else if (tagName.equals("param")) {
						if (GetterUtil.getBoolean(
								element.elementText("required"))) {

							requiredTagNames.add(tagName);
						}
					}
					else if (tagName.equals("throws")) {
						if (GetterUtil.getBoolean(
								element.elementText("required"))) {

							requiredTagNames.add(tagName);
						}
					}
				}
				else {
					customTagNames.add(tagName);
				}

				allTagNames.add(tagName);
			}
		}

		int maxTagNameLength = 0;

		List<String> maxTagNameLengthTags = new ArrayList<String>();

		if (_initializeMissingJavadocs) {
			maxTagNameLengthTags.addAll(allTagNames);
		}
		else if (_updateJavadocs) {
			if (!requiredTagNames.isEmpty()) {
				maxTagNameLengthTags.addAll(allTagNames);
			}
			else {
				maxTagNameLengthTags.addAll(customTagNames);
				maxTagNameLengthTags.addAll(requiredTagNames);
			}
		}
		else {
			maxTagNameLengthTags.addAll(customTagNames);
			maxTagNameLengthTags.addAll(requiredTagNames);
		}

		for (String name : maxTagNameLengthTags) {
			if (name.length() > maxTagNameLength) {
				maxTagNameLength = name.length();
			}
		}

		// There should be an @ sign before the tag and a space after it

		maxTagNameLength += 2;

		String tagNameIndent = _getSpacesIndent(maxTagNameLength);

		StringBundler sb = new StringBundler();

		for (String tagName : tagNames) {
			List<Element> elements = parentElement.elements(tagName);

			for (Element element : elements) {
				Element commentElement = element.element("comment");

				String comment = null;

				if (commentElement != null) {
					comment = commentElement.getText();
				}
				else {
					comment = element.getText();
				}

				String elementName = element.elementText("name");

				if (Validator.isNotNull(comment)) {
					comment = _assembleTagComment(
						tagName, elementName, comment, indent, tagNameIndent);

					sb.append(comment);
				}
				else {
					if (_initializeMissingJavadocs && publicAccess) {

						// Write out all tags

						comment = _assembleTagComment(
							tagName, elementName, comment, indent,
							tagNameIndent);

						sb.append(comment);
					}
					else if (_updateJavadocs && publicAccess) {
						if (!tagName.equals("param") &&
							!tagName.equals("return") &&
							!tagName.equals("throws")) {

							// Write out custom tag

							comment = _assembleTagComment(
								tagName, elementName, comment, indent,
								tagNameIndent);

							sb.append(comment);
						}
						else if (!requiredTagNames.isEmpty()) {

							// Write out all tags

							comment = _assembleTagComment(
								tagName, elementName, comment, indent,
								tagNameIndent);

							sb.append(comment);
						}
						else {

							// Skip empty common tag

						}
					}
					else {
						if (!tagName.equals("param") &&
							!tagName.equals("return") &&
							!tagName.equals("throws")) {

							// Write out custom tag

							comment = _assembleTagComment(
								tagName, elementName, comment, indent,
								tagNameIndent);

							sb.append(comment);
						}
						else if (tagName.equals("param") ||
								 tagName.equals("return") ||
								 tagName.equals("throws")) {

							if (GetterUtil.getBoolean(
									element.elementText("required"))) {

								elementName = element.elementText("name");

								comment = _assembleTagComment(
									tagName, elementName, comment, indent,
									tagNameIndent);

								sb.append(comment);
							}
						}
						else {

							// Skip empty common tag

						}
					}
				}
			}
		}

		return sb.toString();
	}

	private void _addFieldElement(Element rootElement, JavaField javaField)
		throws Exception {

		Element fieldElement = rootElement.addElement("field");

		DocUtil.add(fieldElement, "name", javaField.getName());

		String comment = _getCDATA(javaField);

		if (Validator.isNotNull(comment)) {
			Element commentElement = fieldElement.addElement("comment");

			commentElement.addCDATA(comment);
		}

		_addDocletElements(fieldElement, javaField, "version");
		_addDocletElements(fieldElement, javaField, "see");
		_addDocletElements(fieldElement, javaField, "since");
		_addDocletElements(fieldElement, javaField, "deprecated");
	}

	private void _addMethodElement(Element rootElement, JavaMethod javaMethod)
		throws Exception {

		Element methodElement = rootElement.addElement("method");

		DocUtil.add(methodElement, "name", javaMethod.getName());

		String comment = _getCDATA(javaMethod);

		if (Validator.isNotNull(comment)) {
			Element commentElement = methodElement.addElement("comment");

			commentElement.addCDATA(_getCDATA(javaMethod));
		}

		_addDocletElements(methodElement, javaMethod, "version");
		_addParamElements(methodElement, javaMethod);
		_addReturnElement(methodElement, javaMethod);
		_addThrowsElements(methodElement, javaMethod);
		_addDocletElements(methodElement, javaMethod, "see");
		_addDocletElements(methodElement, javaMethod, "since");
		_addDocletElements(methodElement, javaMethod, "deprecated");
	}

	private void _addParamElement(
		Element methodElement, JavaParameter javaParameter,
		DocletTag[] paramDocletTags) {

		String name = javaParameter.getName();

		String value = null;

		for (DocletTag paramDocletTag : paramDocletTags) {
			String curValue = paramDocletTag.getValue();

			if (curValue.equals(name) || curValue.startsWith(name + " ")) {
				value = curValue;

				break;
			}
		}

		Element paramElement = methodElement.addElement("param");

		DocUtil.add(paramElement, "name", name);
		DocUtil.add(paramElement, "type", _getTypeValue(javaParameter));

		if (value != null) {
			value = value.substring(name.length());

			DocUtil.add(paramElement, "required", true);
		}

		value = _trimMultilineText(value);

		Element commentElement = paramElement.addElement("comment");

		commentElement.addCDATA(value);
	}

	private void _addParamElements(
		Element methodElement, JavaMethod javaMethod) {

		JavaParameter[] javaParameters = javaMethod.getParameters();

		DocletTag[] paramDocletTags = javaMethod.getTagsByName("param");

		for (JavaParameter javaParameter : javaParameters) {
			_addParamElement(methodElement, javaParameter, paramDocletTags);
		}
	}

	private void _addReturnElement(Element methodElement, JavaMethod javaMethod)
		throws Exception {

		Type returns = javaMethod.getReturns();

		if (returns == null) {
			return;
		}

		String returnsValue = returns.getValue();

		if (returnsValue.equals("void")) {
			return;
		}

		Element returnElement = methodElement.addElement("return");

		DocletTag[] returnDocletTags = javaMethod.getTagsByName("return");

		String comment = StringPool.BLANK;

		if (returnDocletTags.length > 0) {
			DocletTag returnDocletTag = returnDocletTags[0];

			comment = GetterUtil.getString(returnDocletTag.getValue());

			DocUtil.add(returnElement, "required", true);
		}

		comment = _trimMultilineText(comment);

		Element commentElement = returnElement.addElement("comment");

		commentElement.addCDATA(comment);
	}

	private void _addThrowsElement(
		Element methodElement, Type exceptionType,
		DocletTag[] throwsDocletTags) {

		JavaClass javaClass = exceptionType.getJavaClass();

		String name = javaClass.getName();

		String value = null;

		for (DocletTag throwsDocletTag : throwsDocletTags) {
			String curValue = throwsDocletTag.getValue();

			if (!curValue.startsWith(name)) {
				continue;
			}
			else {
				value = curValue;

				break;
			}
		}

		Element throwsElement = methodElement.addElement("throws");

		DocUtil.add(throwsElement, "name", name);
		DocUtil.add(throwsElement, "type", exceptionType.getValue());

		if (value != null) {
			value = value.substring(name.length());

			DocUtil.add(throwsElement, "required", true);
		}

		value = _trimMultilineText(value);

		Element commentElement = throwsElement.addElement("comment");

		commentElement.addCDATA(_getCDATA(value));
	}

	private void _addThrowsElements(
		Element methodElement, JavaMethod javaMethod) {

		Type[] exceptionTypes = javaMethod.getExceptions();

		DocletTag[] throwsDocletTags = javaMethod.getTagsByName("throws");

		for (Type exceptionType : exceptionTypes) {
			_addThrowsElement(methodElement, exceptionType, throwsDocletTags);
		}
	}

	private String _assembleTagComment(
		String tagName, String elementName, String comment, String indent,
		String tagNameIndent) {

		String indentAndTagName = indent + StringPool.AT + tagName;

		if (Validator.isNotNull(elementName)) {
			if (Validator.isNotNull(comment)) {
				comment = elementName + StringPool.SPACE + comment;
			}
			else {
				comment = elementName;
			}

			// <name indent> elementName [comment]

			comment = _wrapText(comment, indent + tagNameIndent);

			// * @name <name indent> elementName [comment]

			comment =
				indentAndTagName + comment.substring(indentAndTagName.length());
		}
		else {
			if (Validator.isNotNull(comment)) {

				// <name indent> comment

				comment = _wrapText(comment, indent + tagNameIndent);

				// * @name <name indent> comment

				comment =
					indentAndTagName +
						comment.substring(indentAndTagName.length());
			}
			else {

				// * @name

				comment = indentAndTagName + "\n";
			}
		}

		return comment;
	}

	private void _detachUnnecessaryTypes(Element rootElement) {
		List<Element> elements = rootElement.elements();

		for (Element element : elements) {
			String type = element.elementText("type");

			if (!type.contains(".service.") || !type.endsWith("ServiceImpl")) {
				element.detach();
			}
		}
	}

	private void _format(String fileName) throws Exception {
		String originalContent = new String(
			Files.readAllBytes(Paths.get(_inputDir + fileName)),
			StringPool.UTF8);

		if (fileName.contains("modules/third-party") ||
			fileName.endsWith("Application.java") ||
			fileName.endsWith("JavadocFormatter.java") ||
			fileName.endsWith("SourceFormatter.java") ||
			fileName.endsWith("WebProxyPortlet.java") ||
			_hasGeneratedTag(originalContent)) {

			return;
		}

		JavaClass javaClass = _getJavaClass(
			fileName, new UnsyncStringReader(originalContent));

		String javadocLessContent = _removeJavadocFromJava(
			javaClass, originalContent);

		Document document = _getJavadocDocument(javaClass);

		_updateJavadocsXmlFile(fileName, javaClass, document);

		_updateJavaFromDocument(
			fileName, originalContent, javadocLessContent, document);
	}

	private String _formatCDATA(String cdata) {
		cdata = cdata.replaceAll(
			"(?s)\\s*<(p|[ou]l)>\\s*(.*?)\\s*</\\1>\\s*",
			"\n\n<$1>\n$2\n</$1>\n\n");
		cdata = cdata.replaceAll(
			"(?s)\\s*<li>\\s*(.*?)\\s*</li>\\s*", "\n<li>\n$1\n</li>\n");
		cdata = StringUtil.replace(cdata, "</li>\n\n<li>", "</li>\n<li>");
		cdata = cdata.replaceAll("\n\\s+\n", "\n\n");
		cdata = cdata.replaceAll(" +", " ");

		// Trim whitespace inside paragraph tags or in the first paragraph

		Matcher matcher = _paragraphTagPattern.matcher(cdata);

		StringBuffer sb = new StringBuffer();

		while (matcher.find()) {
			String trimmed = _trimMultilineText(matcher.group());

			// Escape dollar signs

			trimmed = StringUtil.replace(trimmed, "$", "\\$");

			matcher.appendReplacement(sb, trimmed);
		}

		matcher.appendTail(sb);

		cdata = sb.toString();

		return cdata.trim();
	}

	private String _formatInlines(String text) {

		// Capitalize ID

		text = text.replaceAll("[?@param id](?i)\\bid(s)?\\b", " ID$1");

		// Wrap special constants in code tags

		text = text.replaceAll(
			"(?i)(?<!<code>|\\w)(null|false|true)(?!\\w)", "<code>$1</code>");

		return text;
	}

	private String _getCDATA(AbstractJavaEntity abstractJavaEntity) {
		return _getCDATA(abstractJavaEntity.getComment());
	}

	private String _getCDATA(String cdata) {
		StringBundler sb = new StringBundler();

		if ((cdata == null) || cdata.isEmpty()) {
			return StringPool.BLANK;
		}

		int cdataBeginIndex = 0;

		while (!cdata.isEmpty()) {
			int preTagIndex = cdata.indexOf("<pre>");
			int tableTagIndex = cdata.indexOf("<table>");

			boolean hasPreTag = (preTagIndex != -1) ? true : false;
			boolean hasTableTag = (tableTagIndex != -1) ? true : false;

			if (!hasPreTag && !hasTableTag) {
				sb.append(_formatCDATA(cdata));

				break;
			}

			boolean startsWithPreTag = (preTagIndex == 0) ? true : false;
			boolean startsWithTableTag = (tableTagIndex == 0) ? true : false;

			if (startsWithPreTag || startsWithTableTag) {
				sb.append("\n");

				String tagName = null;

				if (preTagIndex == 0) {
					tagName = "pre";
				}
				else {
					tagName = "table";
				}

				String startTag = "<" + tagName + ">";
				String endTag = "</" + tagName + ">";

				int startTagLength = startTag.length();
				int endTagLength = endTag.length();

				int endTagIndex = cdata.indexOf(endTag, startTagLength - 1);

				sb.append(cdata.substring(0, endTagIndex + endTagLength));

				sb.append("\n");

				cdataBeginIndex = endTagIndex + endTagLength;
			}
			else {

				// Format the cdata up to the next pre or table tag

				int startTagIndex = 0;

				if (hasPreTag && hasTableTag) {
					if (preTagIndex < tableTagIndex) {
						startTagIndex = preTagIndex;
					}
					else {
						startTagIndex = tableTagIndex;
					}
				}
				else if (hasPreTag && !hasTableTag) {
					startTagIndex = preTagIndex;
				}
				else {

					// Must have table tag and no pre tag

					startTagIndex = tableTagIndex;
				}

				sb.append(_formatCDATA(cdata.substring(0, startTagIndex)));

				cdataBeginIndex = startTagIndex;
			}

			cdata = cdata.substring(cdataBeginIndex);
		}

		cdata = sb.toString();

		return cdata.trim();
	}

	private String _getClassName(String fileName) {
		int pos = fileName.indexOf("src/");

		if (pos == -1) {
			pos = fileName.indexOf("test/integration/");

			if (pos != -1) {
				pos = fileName.indexOf("integration/", pos);
			}
		}

		if (pos == -1) {
			pos = fileName.indexOf("test/unit/");

			if (pos != -1) {
				pos = fileName.indexOf("unit/", pos);
			}
		}

		if (pos == -1) {
			pos = fileName.indexOf("test/");
		}

		if (pos == -1) {
			pos = fileName.indexOf("service/");
		}

		if (pos == -1) {
			throw new RuntimeException(fileName);
		}

		pos = fileName.indexOf("/", pos);

		String srcFile = fileName.substring(pos + 1, fileName.length());

		return StringUtil.replace(
			srcFile.substring(0, srcFile.length() - 5), "/", ".");
	}

	private String _getFieldKey(Element fieldElement) {
		return fieldElement.elementText("name");
	}

	private String _getFieldKey(JavaField javaField) {
		return javaField.getName();
	}

	private String _getIndent(
		String[] lines, AbstractBaseJavaEntity abstractBaseJavaEntity) {

		String line = lines[abstractBaseJavaEntity.getLineNumber() - 1];

		String indent = StringPool.BLANK;

		for (char c : line.toCharArray()) {
			if (Character.isWhitespace(c)) {
				indent += c;
			}
			else {
				break;
			}
		}

		return indent;
	}

	private int _getIndentLength(String indent) {
		int indentLength = 0;

		for (char c : indent.toCharArray()) {
			if (c == '\t') {
				indentLength = indentLength + 4;
			}
			else {
				indentLength++;
			}
		}

		return indentLength;
	}

	private JavaClass _getJavaClass(String fileName, Reader reader)
		throws Exception {

		String className = _getClassName(fileName);

		JavaDocBuilder javadocBuilder = new JavaDocBuilder();

		if (reader == null) {
			File file = new File(fileName);

			if (!file.exists()) {
				return null;
			}

			javadocBuilder.addSource(file);
		}
		else {
			javadocBuilder.addSource(reader);
		}

		return javadocBuilder.getClassByName(className);
	}

	private String _getJavaClassComment(
		Element rootElement, JavaClass javaClass) {

		StringBundler sb = new StringBundler();

		String indent = StringPool.BLANK;

		sb.append("/**\n");

		String comment = rootElement.elementText("comment");

		if (Validator.isNotNull(comment)) {
			sb.append(_wrapText(comment, indent + " * "));
		}

		String docletTags = _addDocletTags(
			rootElement,
			new String[] {
				"author", "version", "see", "since", "serial", "deprecated"
			},
			indent + " * ", _hasPublicModifier(javaClass));

		if (Validator.isNotNull(docletTags)) {
			if (_initializeMissingJavadocs || Validator.isNotNull(comment)) {
				sb.append(" *\n");
			}

			sb.append(docletTags);
		}

		sb.append(" */\n");

		return sb.toString();
	}

	private int _getJavaClassLineNumber(JavaClass javaClass) {
		int lineNumber = javaClass.getLineNumber();

		Annotation[] annotations = javaClass.getAnnotations();

		if (annotations.length == 0) {
			return lineNumber;
		}

		for (Annotation annotation : annotations) {
			int annotationLineNumber = annotation.getLineNumber();

			Map<String, String> propertyMap = annotation.getPropertyMap();

			if (propertyMap.isEmpty()) {
				annotationLineNumber--;
			}

			if (annotationLineNumber < lineNumber) {
				lineNumber = annotationLineNumber;
			}
		}

		return lineNumber;
	}

	private Document _getJavadocDocument(JavaClass javaClass) throws Exception {
		Element rootElement = _saxReader.createElement("javadoc");

		Document document = _saxReader.createDocument(rootElement);

		DocUtil.add(rootElement, "name", javaClass.getName());
		DocUtil.add(rootElement, "type", javaClass.getFullyQualifiedName());

		_addClassCommentElement(rootElement, javaClass);
		_addDocletElements(rootElement, javaClass, "author");
		_addDocletElements(rootElement, javaClass, "version");
		_addDocletElements(rootElement, javaClass, "see");
		_addDocletElements(rootElement, javaClass, "since");
		_addDocletElements(rootElement, javaClass, "serial");
		_addDocletElements(rootElement, javaClass, "deprecated");

		JavaMethod[] javaMethods = javaClass.getMethods();

		for (JavaMethod javaMethod : javaMethods) {
			_addMethodElement(rootElement, javaMethod);
		}

		JavaField[] javaFields = javaClass.getFields();

		for (JavaField javaField : javaFields) {
			_addFieldElement(rootElement, javaField);
		}

		return document;
	}

	private Tuple _getJavadocsXmlTuple(String fileName) throws Exception {
		File file = new File(_inputDir + fileName);

		String absolutePath = file.getAbsolutePath();

		absolutePath = StringUtil.replace(absolutePath, "\\", "/");
		absolutePath = StringUtil.replace(absolutePath, "/./", "/");

		int pos = absolutePath.indexOf("/portal-impl/src/");

		String srcDirName = null;

		if (pos != -1) {
			srcDirName = absolutePath.substring(0, pos + 17);
		}

		if (srcDirName == null) {
			pos = absolutePath.indexOf("/portal-kernel/src/");

			if (pos == -1) {
				pos = absolutePath.indexOf("/portal-service/src/");
			}

			if (pos == -1) {
				pos = absolutePath.indexOf("/util-bridges/src/");
			}

			if (pos == -1) {
				pos = absolutePath.indexOf("/util-java/src/");
			}

			if (pos == -1) {
				pos = absolutePath.indexOf("/util-taglib/src/");
			}

			if (pos != -1) {
				srcDirName =
					absolutePath.substring(0, pos) + "/portal-impl/src/";
			}
		}

		if (srcDirName == null) {
			pos = absolutePath.indexOf("/WEB-INF/src/");

			if (pos != -1) {
				srcDirName = absolutePath.substring(0, pos + 13);
			}
		}

		if (srcDirName == null) {
			return null;
		}

		Tuple tuple = _javadocxXmlTuples.get(srcDirName);

		if (tuple != null) {
			return tuple;
		}

		File javadocsXmlFile = new File(
			srcDirName, "META-INF/" + _outputFilePrefix + "-all.xml");

		if (!javadocsXmlFile.exists()) {
			_fileUtil.write(
				javadocsXmlFile,
				"<?xml version=\"1.0\"?>\n\n<javadocs>\n</javadocs>");
		}

		String javadocsXmlContent = _fileUtil.read(javadocsXmlFile);

		Document javadocsXmlDocument = _saxReader.read(javadocsXmlContent);

		tuple = new Tuple(
			srcDirName, javadocsXmlFile, javadocsXmlContent,
			javadocsXmlDocument);

		_javadocxXmlTuples.put(srcDirName, tuple);

		return tuple;
	}

	private String _getJavaFieldComment(
		Map<String, Element> fieldElementsMap, JavaField javaField,
		String indent) {

		String fieldKey = _getFieldKey(javaField);

		Element fieldElement = fieldElementsMap.get(fieldKey);

		if (fieldElement == null) {
			return null;
		}

		StringBundler sb = new StringBundler();

		sb.append(indent);
		sb.append("/**\n");

		String comment = fieldElement.elementText("comment");

		if (Validator.isNotNull(comment)) {
			sb.append(_wrapText(comment, indent + " * "));
		}

		String docletTags = _addDocletTags(
			fieldElement,
			new String[] {"version", "see", "since", "deprecated"},
			indent + " * ", _hasPublicModifier(javaField));

		if (Validator.isNotNull(docletTags)) {
			if (_initializeMissingJavadocs || Validator.isNotNull(comment)) {
				sb.append(indent);
				sb.append(" *\n");
			}

			sb.append(docletTags);
		}

		sb.append(indent);
		sb.append(" */\n");

		if (!_initializeMissingJavadocs && Validator.isNull(comment) &&
			Validator.isNull(docletTags)) {

			return null;
		}

		if (!_hasPublicModifier(javaField) && Validator.isNull(comment) &&
			Validator.isNull(docletTags)) {

			return null;
		}

		return sb.toString();
	}

	private String _getJavaMethodComment(
		Map<String, Element> methodElementsMap, JavaMethod javaMethod,
		String indent) {

		String methodKey = _getMethodKey(javaMethod);

		Element methodElement = methodElementsMap.get(methodKey);

		if (methodElement == null) {
			return null;
		}

		StringBundler sb = new StringBundler();

		sb.append(indent);
		sb.append("/**\n");

		String comment = methodElement.elementText("comment");

		if (Validator.isNotNull(comment)) {
			sb.append(_wrapText(comment, indent + " * "));
		}

		String docletTags = _addDocletTags(
			methodElement,
			new String[] {
				"version", "param", "return", "throws", "see", "since",
				"deprecated"
			},
			indent + " * ", _hasPublicModifier(javaMethod));

		if (Validator.isNotNull(docletTags)) {
			if (_initializeMissingJavadocs || Validator.isNotNull(comment)) {
				sb.append(indent);
				sb.append(" *\n");
			}

			sb.append(docletTags);
		}

		sb.append(indent);
		sb.append(" */\n");

		if (!_initializeMissingJavadocs && Validator.isNull(comment) &&
			Validator.isNull(docletTags)) {

			return null;
		}

		if (!_hasPublicModifier(javaMethod) && Validator.isNull(comment) &&
			Validator.isNull(docletTags)) {

			return null;
		}

		return sb.toString();
	}

	private String _getMethodKey(Element methodElement) {
		StringBundler sb = new StringBundler();

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
		StringBundler sb = new StringBundler();

		sb.append(javaMethod.getName());
		sb.append(StringPool.OPEN_PARENTHESIS);

		JavaParameter[] javaParameters = javaMethod.getParameters();

		for (JavaParameter javaParameter : javaParameters) {
			sb.append(javaParameter.getName());
			sb.append("|");
			sb.append(_getTypeValue(javaParameter));
			sb.append(",");
		}

		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	private String _getSpacesIndent(int length) {
		String indent = StringPool.BLANK;

		for (int i = 0; i < length; i++) {
			indent += StringPool.SPACE;
		}

		return indent;
	}

	private String _getTypeValue(JavaParameter javaParameter) {
		Type type = javaParameter.getType();

		String typeValue = type.getValue();

		if (type.isArray()) {
			typeValue += "[]";
		}

		return typeValue;
	}

	private boolean _hasGeneratedTag(String content) {
		if (content.contains("* @generated") || content.contains("$ANTLR")) {
			return true;
		}
		else {
			return false;
		}
	}

	private boolean _hasPublicModifier(AbstractJavaEntity abstractJavaEntity) {
		String[] modifiers = abstractJavaEntity.getModifiers();

		if (modifiers == null) {
			return false;
		}

		for (String modifier : modifiers) {
			if (modifier.equals("public")) {
				return true;
			}
		}

		return false;
	}

	private boolean _isOverrideMethod(
		JavaClass javaClass, JavaMethod javaMethod,
		Collection<Tuple> ancestorJavaClassTuples) {

		if (javaMethod.isConstructor() || javaMethod.isPrivate() ||
			javaMethod.isStatic() ||
			_overridesHigherJavaAPIVersion(javaMethod)) {

			return false;
		}

		String methodName = javaMethod.getName();

		JavaParameter[] javaParameters = javaMethod.getParameters();

		Type[] types = new Type[javaParameters.length];

		for (int i = 0; i < javaParameters.length; i++) {
			types[i] = javaParameters[i].getType();
		}

		// Check for matching method in each ancestor

		for (Tuple ancestorJavaClassTuple : ancestorJavaClassTuples) {
			JavaClass ancestorJavaClass =
				(JavaClass)ancestorJavaClassTuple.getObject(0);

			JavaMethod ancestorJavaMethod = null;

			if (ancestorJavaClassTuple.getSize() > 1) {

				// LPS-35613

				Type[] ancestorActualTypeArguments =
					(Type[])ancestorJavaClassTuple.getObject(1);

				Type[] genericTypes = new Type[types.length];

				for (int i = 0; i < types.length; i++) {
					Type type = types[i];

					String typeValue = type.getValue();

					boolean useGenericType = false;

					for (int j = 0; j < ancestorActualTypeArguments.length;
							j++) {

						if (typeValue.equals(
								ancestorActualTypeArguments[j].getValue())) {

							useGenericType = true;

							break;
						}
					}

					if (useGenericType) {
						genericTypes[i] = new Type("java.lang.Object");
					}
					else {
						genericTypes[i] = type;
					}
				}

				ancestorJavaMethod = ancestorJavaClass.getMethodBySignature(
					methodName, genericTypes);
			}
			else {
				ancestorJavaMethod = ancestorJavaClass.getMethodBySignature(
					methodName, types);
			}

			if (ancestorJavaMethod == null) {
				continue;
			}

			boolean samePackage = false;

			JavaPackage ancestorJavaPackage = ancestorJavaClass.getPackage();

			if (ancestorJavaPackage != null) {
				samePackage = ancestorJavaPackage.equals(
					javaClass.getPackage());
			}

			// Check if the method is in scope

			if (samePackage) {
				return !ancestorJavaMethod.isPrivate();
			}

			if (ancestorJavaMethod.isProtected() ||
				ancestorJavaMethod.isPublic()) {

				return true;
			}
			else {
				return false;
			}
		}

		return false;
	}

	private boolean _overridesHigherJavaAPIVersion(JavaMethod javaMethod) {
		Annotation[] annotations = javaMethod.getAnnotations();

		if (annotations == null) {
			return false;
		}

		for (Annotation annotation : annotations) {
			Type type = annotation.getType();

			JavaClass javaClass = type.getJavaClass();

			String javaClassName = javaClass.getFullyQualifiedName();

			if (javaClassName.equals(SinceJava.class.getName())) {
				AnnotationValue annotationValue = annotation.getProperty(
					"value");

				double sinceJava = GetterUtil.getDouble(
					annotationValue.getParameterValue());

				if (sinceJava > _LOWEST_SUPPORTED_JAVA_VERSION) {
					return true;
				}
			}
		}

		return false;
	}

	private String _removeJavadocFromJava(JavaClass javaClass, String content) {
		Set<Integer> lineNumbers = new HashSet<Integer>();

		lineNumbers.add(_getJavaClassLineNumber(javaClass));

		JavaMethod[] javaMethods = javaClass.getMethods();

		for (JavaMethod javaMethod : javaMethods) {
			lineNumbers.add(javaMethod.getLineNumber());
		}

		JavaField[] javaFields = javaClass.getFields();

		for (JavaField javaField : javaFields) {
			lineNumbers.add(javaField.getLineNumber());
		}

		String[] lines = StringUtil.splitLines(content);

		for (int lineNumber : lineNumbers) {
			if (lineNumber == 0) {
				continue;
			}

			int pos = lineNumber - 2;

			String line = lines[pos];

			if (line == null) {
				continue;
			}

			int blankLines = 0;

			while (line.equals(StringPool.BLANK)) {
				line = lines[--pos];

				blankLines++;
			}

			line = line.trim();

			if (line.endsWith("*/")) {
				while (true) {
					lines[pos] = null;

					if (line.startsWith("/**") || line.startsWith("/*")) {
						break;
					}

					line = lines[--pos].trim();
				}

				for (int i = 0; i < blankLines; i++) {
					lines[lineNumber - i - 2] = null;
				}
			}
		}

		StringBundler sb = new StringBundler(content.length());

		for (String line : lines) {
			if (line != null) {
				sb.append(line);
				sb.append("\n");
			}
		}

		content = sb.toString();

		return content.trim();
	}

	private String _trimMultilineText(String text) {
		String[] lines = StringUtil.splitLines(text);

		StringBundler sb = new StringBundler();

		for (int i = 0; i < lines.length; i++) {
			String line = lines[i].trim();

			sb.append(line);

			if (!line.endsWith(StringPool.OPEN_PARENTHESIS) &&
				(i < (lines.length - 1))) {

				sb.append(StringPool.SPACE);
			}
		}

		return sb.toString();
	}

	private void _updateJavadocsXmlFile(
			String fileName, JavaClass javaClass, Document javaClassDocument)
		throws Exception {

		String javaClassFullyQualifiedName = javaClass.getFullyQualifiedName();

		/*if (!javaClassFullyQualifiedName.contains(".service.") ||
			!javaClassFullyQualifiedName.endsWith("ServiceImpl")) {

			return;
		}*/

		Tuple javadocsXmlTuple = _getJavadocsXmlTuple(fileName);

		if (javadocsXmlTuple == null) {
			return;
		}

		Document javadocsXmlDocument = (Document)javadocsXmlTuple.getObject(3);

		Element javadocsXmlRootElement = javadocsXmlDocument.getRootElement();

		List<Element> javadocElements = javadocsXmlRootElement.elements(
			"javadoc");

		for (Element javadocElement : javadocElements) {
			String type = javadocElement.elementText("type");

			if (type.equals(javaClassFullyQualifiedName)) {
				Element javaClassRootElement =
					javaClassDocument.getRootElement();

				if (Validator.equals(
						javadocElement.formattedString(),
						javaClassRootElement.formattedString())) {

					return;
				}

				javadocElement.detach();

				break;
			}
		}

		javadocsXmlRootElement.add(javaClassDocument.getRootElement());
	}

	private void _updateJavaFromDocument(
			String fileName, String originalContent, String javadocLessContent,
			Document document)
		throws Exception {

		String[] lines = StringUtil.splitLines(javadocLessContent);

		JavaClass javaClass = _getJavaClass(
			fileName, new UnsyncStringReader(javadocLessContent));

		_updateLanguageProperties(document, javaClass.getName());

		List<Tuple> ancestorJavaClassTuples = new ArrayList<Tuple>();

		ancestorJavaClassTuples = _addAncestorJavaClassTuples(
			javaClass, ancestorJavaClassTuples);

		Element rootElement = document.getRootElement();

		Map<Integer, String> commentsMap = new TreeMap<Integer, String>();

		String javaClassComment = _getJavaClassComment(rootElement, javaClass);

		javaClassComment = _addDeprecatedTag(
			javaClassComment, javaClass, StringPool.BLANK);

		commentsMap.put(_getJavaClassLineNumber(javaClass), javaClassComment);

		Map<String, Element> methodElementsMap = new HashMap<String, Element>();

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

			String indent = _getIndent(lines, javaMethod);

			String javaMethodComment = _getJavaMethodComment(
				methodElementsMap, javaMethod, indent);

			javaMethodComment = _addDeprecatedTag(
				javaMethodComment, javaMethod, indent);

			// Handle override tag insertion

			if (!ServiceBuilder.hasAnnotation(javaMethod, "Override")) {
				if (_isOverrideMethod(
						javaClass, javaMethod, ancestorJavaClassTuples)) {

					String overrideLine = indent + "@Override\n";

					if (Validator.isNotNull(javaMethodComment)) {
						javaMethodComment = javaMethodComment + overrideLine;
					}
					else {
						javaMethodComment = overrideLine;
					}
				}
			}

			commentsMap.put(javaMethod.getLineNumber(), javaMethodComment);
		}

		Map<String, Element> fieldElementsMap = new HashMap<String, Element>();

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

			String indent = _getIndent(lines, javaField);

			String javaFieldComment = _getJavaFieldComment(
				fieldElementsMap, javaField, indent);

			javaFieldComment = _addDeprecatedTag(
				javaFieldComment, javaField, indent);

			commentsMap.put(javaField.getLineNumber(), javaFieldComment);
		}

		StringBundler sb = new StringBundler(javadocLessContent.length());

		for (int lineNumber = 1; lineNumber <= lines.length; lineNumber++) {
			String line = lines[lineNumber - 1];

			String comments = commentsMap.get(lineNumber);

			if (comments != null) {
				sb.append(comments);
			}

			sb.append(line);
			sb.append("\n");
		}

		String formattedContent = sb.toString();

		formattedContent = formattedContent.trim();

		if (!originalContent.equals(formattedContent)) {
			File file = new File(_inputDir + fileName);

			_fileUtil.write(file, formattedContent.getBytes(StringPool.UTF8));

			System.out.println("Writing " + file);
		}
	}

	private void _updateLanguageProperties(Document document, String className)
		throws IOException {

		if (_languageProperties == null) {
			return;
		}

		int index = className.indexOf("ServiceImpl");

		if (index <= 0) {
			return;
		}

		StringBundler sb = new StringBundler();

		sb.append(Character.toLowerCase(className.charAt(0)));

		for (int i = 1; i < index; i++) {
			char c = className.charAt(i);

			if (Character.isUpperCase(c)) {
				if (((i + 1) < index) &&
					Character.isLowerCase(className.charAt(i + 1))) {

					sb.append(CharPool.DASH);
				}

				sb.append(Character.toLowerCase(c));
			}
			else {
				sb.append(c);
			}
		}

		sb.append("-service-help");

		String key = sb.toString();

		String value = _languageProperties.getProperty(key);

		if (value == null) {
			return;
		}

		Element rootElement = document.getRootElement();

		String comment = rootElement.elementText("comment");

		if ((comment == null) || value.equals(comment)) {
			return;
		}

		index = comment.indexOf("\n\n");

		if (index != -1) {
			value = comment.substring(0, index);
		}
		else {
			value = comment;
		}

		_updateLanguageProperties(key, value);
	}

	private void _updateLanguageProperties(String key, String value)
		throws IOException {

		StringBundler sb = new StringBundler();

		try (UnsyncBufferedReader unsyncBufferedReader = 
				new UnsyncBufferedReader(
					new FileReader(_languagePropertiesFile))) {

			boolean begin = false;
			boolean firstLine = true;
			String linePrefix = key + "=";

			String line = null;

			while ((line = unsyncBufferedReader.readLine()) != null) {
				if (line.equals(StringPool.BLANK)) {
					begin = !begin;
				}

				if (firstLine) {
					firstLine = false;
				}
				else {
					sb.append(StringPool.NEW_LINE);
				}

				if (line.startsWith(linePrefix)) {
					sb.append(linePrefix);
					sb.append(value);
				}
				else {
					sb.append(line);
				}
			}
		}

		try (Writer writer = new OutputStreamWriter(
				new FileOutputStream(_languagePropertiesFile, false),
				StringPool.UTF8)) {

			sb.writeTo(writer);
		}

		System.out.println(
			"Updating " + _languagePropertiesFile + " key " + key);
	}

	private String _wrapText(String text, int indentLength, String exclude) {
		StringBuffer sb = new StringBuffer();

		StringBundler regexSB = new StringBundler("(?<=^|</");

		regexSB.append(exclude);
		regexSB.append(">).+?(?=$|<");
		regexSB.append(exclude);
		regexSB.append(">)");

		Pattern pattern = Pattern.compile(regexSB.toString(), Pattern.DOTALL);

		Matcher matcher = pattern.matcher(text);

		while (matcher.find()) {
			String wrapped = _formatInlines(matcher.group());

			wrapped = StringUtil.wrap(wrapped, 80 - indentLength, "\n");

			matcher.appendReplacement(sb, wrapped);
		}

		matcher.appendTail(sb);

		return sb.toString();
	}

	private String _wrapText(String text, String indent) {
		int indentLength = _getIndentLength(indent);

		if (text.contains("<pre>")) {
			text = _wrapText(text, indentLength, "pre");
		}
		else if (text.contains("<table>")) {
			text = _wrapText(text, indentLength, "table");
		}
		else {
			text = _formatInlines(text);
			text = StringUtil.wrap(text, 80 - indentLength, "\n");
		}

		text = text.replaceAll("(?m)^", indent);
		text = text.replaceAll("(?m) +$", StringPool.BLANK);

		return text;
	}

	private static final double _LOWEST_SUPPORTED_JAVA_VERSION = 1.7;

	private static FileImpl _fileUtil = FileImpl.getInstance();
	private static final SAXReader _saxReader = new SAXReaderImpl();

	private boolean _initializeMissingJavadocs;
	private String _inputDir;
	private Map<String, Tuple> _javadocxXmlTuples =
		new HashMap<String, Tuple>();
	private Properties _languageProperties;
	private File _languagePropertiesFile;
	private String _outputFilePrefix;
	private Pattern _paragraphTagPattern = Pattern.compile(
		"(^.*?(?=\n\n|$)+|(?<=<p>\n).*?(?=\n</p>))", Pattern.DOTALL);
	private boolean _updateJavadocs;

}