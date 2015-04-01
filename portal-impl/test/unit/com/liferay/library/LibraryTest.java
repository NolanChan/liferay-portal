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

package com.liferay.library;

import com.liferay.portal.kernel.util.StringBundler;

import java.io.File;
import java.io.IOException;

import java.nio.charset.Charset;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Matthew Tambara
 */
public class LibraryTest {

	@BeforeClass
	public static void setUpClass() throws Exception {
		_initLibJars();

		DocumentBuilderFactory documentBuilderFactory =
			DocumentBuilderFactory.newInstance();

		DocumentBuilder documentBuilder =
			documentBuilderFactory.newDocumentBuilder();

		_initEclipseProjectJars(documentBuilder);

		_initNetBeansProjectJars(documentBuilder);

		_initVersionsJars(documentBuilder);
	}

	@Test
	public void testEclipseProjectJarsInLib() {
		_doSearch(_classpathJars, _libJars, _CLASSPATH_PATH);
	}

	@Test
	public void testLibJarsInEclipseProject() {
		_doSearch(_libJars, _classpathJars, _CLASSPATH_PATH);
	}

	@Test
	public void testLibJarsInNetBeansProject() {
		_doSearch(_libJars, _nbProjectJars, _NBPROJECT_PATH);
	}

	@Test
	public void testLibJarsInVersions() {
		_doSearch(_libJars, _versionsJars, _VERSIONS_PATH);
	}

	@Test
	public void testNetBeansProjectJarsInLib() {
		_doSearch(_nbProjectJars, _libJars, _NBPROJECT_PATH);
	}

	@Test
	public void testVersionsJarsInLib() {
		_doSearch(_versionsJars, _libJars, _VERSIONS_PATH);
	}

	private static void _initEclipseProjectJars(DocumentBuilder documentBuilder)
		throws Exception {

		Document document = documentBuilder.parse(new File(_CLASSPATH_PATH));

		NodeList nodelist = document.getElementsByTagName("classpathentry");

		for (int i = 0; i < nodelist.getLength(); i++) {
			Node node = nodelist.item(i);

			NamedNodeMap namedNodeMap = node.getAttributes();

			Node kindNode = namedNodeMap.getNamedItem("kind");

			String kind = kindNode.getNodeValue();

			if (!kind.equals("lib")) {
				continue;
			}

			Node pathNode = namedNodeMap.getNamedItem("path");

			_classpathJars.add(pathNode.getNodeValue());
		}
	}

	private static void _initLibJars() throws IOException {
		for (String line : Files.readAllLines(
			Paths.get(_LIB, "/versions-ignore.txt"),
			Charset.forName("UTF-8"))) {

			line = line.trim();

			if (!line.isEmpty()) {
				_excludes.add(line);
			}
		}

		Files.walkFileTree(
			Paths.get(_LIB),
			new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult visitFile(
					Path path, BasicFileAttributes basicFileAttributes) {

					String pathString = path.toString();

					if (pathString.endsWith(".jar")) {
						_libJars.add(pathString);
					}

					return FileVisitResult.CONTINUE;
				}

			});
	}

	private static void _initNetBeansProjectJars(
			DocumentBuilder documentBuilder)
		throws Exception {

		Document document = documentBuilder.parse(new File(_NBPROJECT_PATH));

		NodeList nodelist = document.getElementsByTagName("classpath");

		for (int i = 0; i < nodelist.getLength(); i++) {
			Node node = nodelist.item(i);

			_nbProjectJars.add(node.getTextContent());
		}
	}

	private static void _initVersionsJars(DocumentBuilder documentBuilder)
		throws Exception {

		Document document = documentBuilder.parse(new File(_VERSIONS_PATH));

		NodeList nodelist = document.getElementsByTagName("file-name");

		for (int i = 0; i < nodelist.getLength(); i++) {
			Node node = nodelist.item(i);

			_versionsJars.add(node.getTextContent());
		}
	}

	private void _doSearch(
		Set<String> searchFor, Set<String> searchIn, String path) {

		for (String jar : searchFor) {
			if (searchIn.contains(jar)) {
				continue;
			}

			if (searchFor == _libJars) {
				if (path.equals(_VERSIONS_PATH) && _excludes.contains(jar)) {
					continue;
				}

				Assert.fail(_generateMissingReferenceMessage(jar, path));
			}
			else {
				Assert.fail(_generateExtraReferenceMessage(jar, path));
			}
		}
	}

	private String _generateExtraReferenceMessage(String jar, String path) {
		StringBundler sb = new StringBundler(3);

		sb.append(path);
		sb.append(" references non-existent jar: ");
		sb.append(jar);

		return sb.toString();
	}

	private String _generateMissingReferenceMessage(String jar, String path) {
		StringBundler sb = new StringBundler(3);

		sb.append(jar);
		sb.append(" is not in ");
		sb.append(path);

		return sb.toString();
	}

	private static final String _CLASSPATH_PATH = ".classpath";

	private static final String _LIB = "lib";

	private static final String _NBPROJECT_PATH = "nbproject/project.xml";

	private static final String _VERSIONS_PATH = _LIB + "/versions.xml";

	private static final Set<String> _classpathJars = new HashSet<>();
	private static final Set<String> _excludes = new HashSet<>();
	private static final Set<String> _libJars = new HashSet<>();
	private static final Set<String> _nbProjectJars = new HashSet<>();
	private static final Set<String> _versionsJars = new HashSet<>();

}