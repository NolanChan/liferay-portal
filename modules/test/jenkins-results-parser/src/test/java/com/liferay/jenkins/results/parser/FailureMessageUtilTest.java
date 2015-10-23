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

package com.liferay.jenkins.results.parser;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;

import java.util.List;

import org.apache.tools.ant.Project;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.DefaultElement;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Peter Yoo
 */
public class FailureMessageUtilTest {

	@BeforeClass
	public static void setUpClass() throws Exception {
		_downloadTestDependencies(
			"generic-fail", "test-portal-acceptance-pullrequest(master)",
			"test-1-1", "1532");
		_downloadTestDependencies(
			"rebase-fail", "test-portal-acceptance-pullrequest(ee-6.2.x)",
			"test-1-20", "40");
	}

	public FailureMessageUtilTest() {
		_project = _initProject();
	}

	@Test
	public void testGetFailureMessage() throws Exception {
		File[] files = _testDependenciesDir.listFiles();

		for (File file : files) {
			if (file.isDirectory()) {
				assertTrue(validateGroup(_project, file));
			}
		}
	}

	private static URL _createURL(String urlString) throws Exception {
		URL url = new URL(urlString);

		return _encode(url);
	}

	private static void _deleteFile(File file) {
		if (!file.exists()) {
			return;
		}

		if (file.isFile()) {
			file.delete();
		}
		else {
			File[] files = file.listFiles();

			for (File childFile : files) {
				_deleteFile(childFile);
			}

			file.delete();
		}
	}

	private static String _downloadTestCaseData(
			String jenkinsReportName, String caseURL)
		throws Exception {

		String decodedCaseURLString = URLDecoder.decode(caseURL, "UTF8");

		int jobIdx = decodedCaseURLString.indexOf("/job/") + 5;

		String caseName = decodedCaseURLString.substring(jobIdx);
		System.out.println("downloading test case data: " + caseName);

		caseName = caseName.replace("/", "-");

		if (caseName.endsWith("-")) {
			caseName = caseName.substring(0, caseName.length() - 1);
		}

		String caseRootPath =
			_testDependenciesDir.getPath() + "/" + jenkinsReportName + "/" +
				caseName;

		String caseLogTextPath = caseRootPath + "/logText";
		String caseApiPath = caseRootPath + "/api";

		File caseLogTextDir = new File(caseLogTextPath);
		File caseApiDir = new File(caseApiPath);

		caseLogTextDir.mkdirs();
		caseApiDir.mkdirs();

		try {
			String caseJsonURL = caseURL + "/api/json";

			System.out.println(" downloading json from:" + caseJsonURL);

			String caseJson = JenkinsResultsParserUtil.toString(caseJsonURL);

			File caseJsonFile = new File(caseApiDir.getPath() + "/json");

			_write(caseJsonFile, caseJson);

			System.out.println(
				" wrote file: " + caseJsonFile.getPath() + " size; " +
					caseJsonFile.length());

			String consoleURL = caseURL + "/logText/progressiveText";

			System.out.println(" downloading console from:" + consoleURL);

			String console = JenkinsResultsParserUtil.toString(consoleURL);

			File consoleFile = new File(
				caseLogTextDir.getPath() + "/progressiveText");

			_write(consoleFile, console);

			System.out.println(
				" wrote file: " + consoleFile.getPath() + " size; " +
					consoleFile.length());
		}
		catch (IOException ioe) {
			File testGroupRootDir = new File(caseRootPath);

			caseLogTextDir.delete();

			caseApiDir.delete();

			testGroupRootDir.delete();

			throw ioe;
		}

		return caseRootPath;
	}

	private static void _downloadTestDependencies(
			String description, String jobName, String hostName,
			String buildNumber)
		throws Exception {

		String urlString =
			"https://${hostName}.liferay.com/userContent/jobs/${jobName}/" +
				"/builds/${buildNumber}/jenkins-report.html";

		urlString = _replaceToken(urlString, "buildNumber", buildNumber);
		urlString = _replaceToken(urlString, "hostName", hostName);
		urlString = _replaceToken(urlString, "jobName", jobName);

		URL url = _createURL(urlString);

		_downloadTestGroupData(
			description + "_" + hostName + "_" + buildNumber, url);
	}

	private static void _downloadTestGroupData(
			String testGroupName, URL jenkinsReportURL)
		throws Exception {

		FailureMessageUtilTest failureMessageUtilTest =
			new FailureMessageUtilTest();

		SAXReader saxReader = new SAXReader();

		File groupReportRootDir = null;

		try {
			Document document = saxReader.read(jenkinsReportURL);

			groupReportRootDir = new File(
				_testDependenciesDir.getPath() + "/" + testGroupName);

			if (groupReportRootDir.exists()) {
				return;
			}

			groupReportRootDir.mkdir();

			File jenkinsReportFile = new File(
				groupReportRootDir.getPath() + "/jenkins-report.html");

			_write(jenkinsReportFile, document);

			List<Node> caseList = _selectNodes(document);

			Project project = failureMessageUtilTest._project;

			int i = 0;

			for (Node node : caseList) {
				DefaultElement defaultElement = (DefaultElement)node;
				Element parent = defaultElement.getParent();

				if (parent.getText().endsWith("FAILURE")) {
					String url = defaultElement.attribute("href").getValue();

					try {
						String caseRootPath = _downloadTestCaseData(
							testGroupName, url);
						failureMessageUtilTest.createExpectedResultsFile(
							project, new File(caseRootPath));
						i++;
					}
					catch (FileNotFoundException e) {
						System.err.println(
							"Data was not found on server for " +
								url + " skipped.");
					}
				}
			}

			System.out.println(
				"Test data creation is complete. " + i +
				" test groups were created.");
		}
		catch (Exception e) {
			System.err.println(
				"Exception occurred while creating Jenkins Report test data. " +
					"Aborting." + e.getMessage());

			if ((groupReportRootDir != null) && groupReportRootDir.exists()) {
				_deleteFile(groupReportRootDir);
			}

			throw e;
		}
	}

	private static URL _encode(URL url) throws Exception {
		URI uri = new URI(
			url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(),
			url.getPath(), url.getQuery(), url.getRef());

		return new URL(uri.toASCIIString());
	}

	private static String _read(File file) throws IOException {
		return new String(Files.readAllBytes(Paths.get(file.toURI())));
	}

	private static List<Node> _selectNodes(Document document) {
		return document.selectNodes("//ul/li[not (ul)]//a[1]");
	}

	private static Project _initProject() {
		Project project = new Project();

		project.setProperty(
			"github.pull.request.head.branch", "junit-pr-head-branch");
		project.setProperty(
			"github.pull.request.head.username", "junit-pr-head-username");
		project.setProperty("plugins.branch.name", "junit-plugins-branch-name");
		project.setProperty("plugins.repository", "junit-plugins-repository");
		project.setProperty("portal.repository", "junit-portal-repository");
		project.setProperty("repository", "junit-repository");

		return project;
	}

	private static String _replaceToken(
		String string, String token, String value) {

		if (string == null) {
			return string;
		}

		return string.replace("${" + token + "}", value);
	}

	private static void _write(File file, String content) throws Exception {
		Files.write(Paths.get(file.toURI()), content.getBytes());
	}

	private static void _write(File file, Document document) throws Exception {
		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		XMLWriter xmlWriter = new XMLWriter(
			byteArrayOutputStream, OutputFormat.createPrettyPrint());

		xmlWriter.write(document);

		_write(file, new String(byteArrayOutputStream.toByteArray(), "UTF8"));
	}

	private void createExpectedResultsFile(Project project, File testRoot)
		throws Exception {

		String result = FailureMessageUtil.getFailureMessage(
			project, testRoot.toURI().toURL().toExternalForm());

		new File(testRoot.getPath() + "/expected-results").mkdir();

		File file = new File(
			testRoot.getPath() + "/" + _EXPECTED_RESULTS_FILE_PATH);

		_write(file, result);
	}

	private boolean validateCase(
			Project project, String groupName, File testRoot)
		throws Exception {

		String name = testRoot.getName();

		System.out.print("Testing case: " + name);

		File expectedResultsFile = new File(
			testRoot.getPath() + "/" + _EXPECTED_RESULTS_FILE_PATH);

		String expectedResults = _read(expectedResultsFile);

		String url = testRoot.toURI().toURL().toExternalForm();
		String results = FailureMessageUtil.getFailureMessage(project, url);

		boolean passed = results.equals(expectedResults);

		if (!passed) {
			System.out.println(groupName + ":" + name + ": FAILED");
			System.out.println("results: \n" + results);
			System.out.println("expected results: \n" + expectedResults);
		}
		else {
			System.out.println(" PASSED");
		}

		return passed;
	}

	private boolean validateGroup(Project project, File dir)
		throws Exception {

		String name = dir.getName();

		File[] files = dir.listFiles();

		for (File file : files) {
			if (file.isDirectory()) {
				if (!validateCase(project, name, file)) {
					return false;
				}
			}
		}

		return true;
	}

	private static final String _EXPECTED_RESULTS_FILE_PATH =
		"expected-results/FailureMessageUtilTest.html";

	private static final File _testDependenciesDir = new File(
		"src/test/resources/com/liferay/results/parser/dependencies/");

	private final Project _project;

}