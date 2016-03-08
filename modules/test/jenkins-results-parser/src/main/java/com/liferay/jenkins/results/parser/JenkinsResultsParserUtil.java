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

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Peter Yoo
 */
public class JenkinsResultsParserUtil {

	public static JSONObject createJSONObject(String jsonString)
		throws Exception {

		JSONObject jsonObject = new JSONObject(jsonString);

		if (jsonObject.isNull("duration") ||
			jsonObject.isNull("result") || jsonObject.isNull("url")) {

			return jsonObject;
		}

		String url = jsonObject.getString("url");

		if (!url.contains("AXIS_VARIABLE")) {
			return jsonObject;
		}

		Object result = jsonObject.get("result");

		if (result instanceof JSONObject) {
			return jsonObject;
		}

		if ((jsonObject.getInt("duration") == 0) && result.equals("FAILURE")) {
			String actualResult = getActualResult(url);

			System.out.println("Actual Result: " + actualResult);

			jsonObject.putOpt("result", actualResult);
		}

		return jsonObject;
	}

	public static URL createURL(String urlString) throws Exception {
		URL url = new URL(urlString);

		return encode(url);
	}

	public static URL encode(URL url) throws Exception {
		URI uri = new URI(
			url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(),
			url.getPath(), url.getQuery(), url.getRef());

		String uriASCIIString = uri.toASCIIString();

		return new URL(uriASCIIString.replace("#", "%23"));
	}

	public static String expandSlaveRange(String value) {
		StringBuilder sb = new StringBuilder();

		for (String hostName : value.split(",")) {
			hostName = hostName.trim();

			int x = hostName.indexOf("..");

			if (x == -1) {
				if (sb.length() > 0) {
					sb.append(",");
				}

				sb.append(hostName);

				continue;
			}

			int y = hostName.lastIndexOf("-") + 1;

			String prefix = hostName.substring(0, y);

			int first = Integer.parseInt(hostName.substring(y, x));
			int last = Integer.parseInt(hostName.substring(x + 2));

			for (int current = first; current <= last; current++) {
				if (sb.length() > 0) {
					sb.append(",");
				}

				sb.append(prefix);
				sb.append(current);
			}
		}

		return sb.toString();
	}

	public static String fixFileName(String fileName) {
		String prefix = "";

		if (fileName.startsWith("file:")) {
			prefix = "file:";

			fileName = fileName.substring(prefix.length());
		}

		fileName = fileName.replace(">", "[gt]");
		fileName = fileName.replace("<", "[lt]");
		fileName = fileName.replace("|", "[pi]");
		fileName = fileName.replace("?", "[qt]");
		fileName = fileName.replace(":", "[sc]");

		return prefix + fileName;
	}

	public static String fixJSON(String json) {
		json = json.replaceAll("'", "&#39;");
		json = json.replaceAll("<", "&#60;");
		json = json.replaceAll(">", "&#62;");
		json = json.replaceAll("\\(", "&#40;");
		json = json.replaceAll("\\)", "&#41;");
		json = json.replaceAll("\\[", "&#91;");
		json = json.replaceAll("\\\"", "&#34;");
		json = json.replaceAll("\\\\", "&#92;");
		json = json.replaceAll("\\]", "&#93;");
		json = json.replaceAll("\\{", "&#123;");
		json = json.replaceAll("\\}", "&#125;");
		json = json.replaceAll("\n", "<br />");
		json = json.replaceAll("\t", "&#09;");
		json = json.replaceAll("\u00BB", "&raquo;");

		return json;
	}

	public static String fixMarkdown(String markdown) {
		markdown = markdown.replace("\\", "\\\\");
		markdown = markdown.replace("`", "\\`");
		markdown = markdown.replace("*", "\\*");
		markdown = markdown.replace("_", "\\_");
		markdown = markdown.replace("{", "\\{");
		markdown = markdown.replace("}", "\\}");
		markdown = markdown.replace("[", "\\[");
		markdown = markdown.replace("]", "\\]");
		markdown = markdown.replace("(", "\\(");
		markdown = markdown.replace(")", "\\)");
		markdown = markdown.replace("#", "\\#");
		markdown = markdown.replace("+", "\\+");
		markdown = markdown.replace("-", "\\-");
		markdown = markdown.replace(".", "\\.");
		markdown = markdown.replace("!", "\\!");

		return markdown;
	}

	public static String fixURL(String url) {
		url = url.replace("(", "%28");
		url = url.replace(")", "%29");
		url = url.replace("[", "%5B");
		url = url.replace("]", "%5D");

		return url;
	}

	public static String format(Element element) throws IOException {
		Writer writer = new CharArrayWriter();

		XMLWriter xmlWriter = new XMLWriter(
			writer, OutputFormat.createPrettyPrint());

		xmlWriter.write(element);

		return writer.toString();
	}

	public static String getActualResult(String buildURL) throws Exception {
		String progressiveText = toString(
			getLocalURL(buildURL + "/logText/progressiveText"), false);

		if (progressiveText.contains("Finished:")) {
			if (progressiveText.contains("Finished: SUCCESS")) {
				return "SUCCESS";
			}

			if (progressiveText.contains("Finished: UNSTABLE")) {
				return "FAILURE";
			}

			if (progressiveText.contains("Finished: FAILURE")) {
				return "FAILURE";
			}
		}

		return null;
	}

	public static String getAxisVariable(JSONObject jsonObject)
		throws Exception {

		JSONArray actionsJSONArray = (JSONArray)jsonObject.get("actions");

		for (int i = 0; i < actionsJSONArray.length(); i++) {
			Object object = actionsJSONArray.get(i);

			if (object.equals(JSONObject.NULL)) {
				continue;
			}

			JSONObject actionsJSONObject = actionsJSONArray.getJSONObject(i);

			JSONArray parametersJSONArray = actionsJSONObject.optJSONArray(
				"parameters");

			if (parametersJSONArray == null) {
				continue;
			}

			for (int j = 0; j < parametersJSONArray.length(); j++) {
				JSONObject parametersJSONObject =
					parametersJSONArray.getJSONObject(j);

				String name = parametersJSONObject.getString("name");

				if (name.contains("AXIS_VARIABLE")) {
					return parametersJSONObject.getString("value");
				}
			}
		}

		return "";
	}

	public static String getJobVariant(JSONObject jsonObject) throws Exception {
		JSONArray actionsJSONArray = jsonObject.getJSONArray("actions");

		for (int i = 0; i < actionsJSONArray.length(); i++) {
			Object object = actionsJSONArray.get(i);

			if (object.equals(org.json.JSONObject.NULL)) {
				continue;
			}

			JSONObject actionsJSONObject = actionsJSONArray.getJSONObject(i);

			if (actionsJSONObject.has("parameters")) {
				JSONArray parametersJSONArray = actionsJSONObject.getJSONArray(
					"parameters");

				for (int j = 0; j < parametersJSONArray.length(); j++) {
					JSONObject parametersJSONObject =
						parametersJSONArray.getJSONObject(j);

					if ("JOB_VARIANT".contains(
							parametersJSONObject.getString("name"))) {

						return parametersJSONObject.getString("value");
					}
				}
			}
		}

		return "";
	}

	public static String getJobVariant(String json) throws Exception {
		return getJobVariant(new JSONObject(json));
	}

	public static String getLocalURL(String remoteURL) {
		if (remoteURL.contains("${dependencies.url}")) {
			remoteURL = fixFileName(remoteURL);

			String fileURL = remoteURL.replace(
				"${dependencies.url}", DEPENDENCIES_URL_FILE);

			File file = new File(fileURL.substring("file:".length()));

			if (file.exists()) {
				remoteURL = fileURL;
			}
			else {
				remoteURL = remoteURL.replace(
					"${dependencies.url}", DEPENDENCIES_URL_HTTP);
			}
		}

		if (remoteURL.startsWith("file")) {
			remoteURL = fixFileName(remoteURL);
		}

		Matcher matcher = _localURLPattern1.matcher(remoteURL);

		if (matcher.find()) {
			StringBuilder sb = new StringBuilder();

			sb.append("http://test-");
			sb.append(matcher.group(1));
			sb.append("/");
			sb.append(matcher.group(1));
			sb.append("/");

			return remoteURL.replaceAll(matcher.group(0), sb.toString());
		}

		matcher = _localURLPattern2.matcher(remoteURL);

		if (matcher.find()) {
			StringBuilder sb = new StringBuilder();

			sb.append("http://");
			sb.append(matcher.group(1));
			sb.append("/");

			return remoteURL.replaceAll(matcher.group(0), sb.toString());
		}

		return remoteURL;
	}

	public static String read(File file) throws IOException {
		return new String(Files.readAllBytes(Paths.get(file.toURI())));
	}

	public static JSONObject toJSONObject(String url) throws Exception {
		return toJSONObject(url, true, 0);
	}

	public static JSONObject toJSONObject(String url, boolean checkCache)
		throws Exception {

		return createJSONObject(toString(url, checkCache, 0));
	}

	public static JSONObject toJSONObject(
			String url, boolean checkCache, int timeout)
		throws Exception {

		return createJSONObject(toString(url, checkCache, timeout));
	}

	public static String toString(String url) throws Exception {
		return toString(url, true, 0);
	}

	public static String toString(String url, boolean checkCache)
		throws Exception {

		return toString(url, checkCache, 0);
	}

	public static String toString(String url, boolean checkCache, int timeout)
		throws Exception {

		url = fixURL(url);

		String key = url.replace("//", "/");

		if (checkCache && _toStringCache.containsKey(key) &&
			!url.startsWith("file:")) {

			System.out.println("Loading " + url);

			return _toStringCache.get(key);
		}

		int retryCount = 0;

		while (true) {
			try {
				System.out.println("Downloading " + url);

				StringBuilder sb = new StringBuilder();

				URL urlObject = new URL(url);

				URLConnection urlConnection = urlObject.openConnection();

				if (timeout != 0) {
					urlConnection.setConnectTimeout(timeout);
					urlConnection.setReadTimeout(timeout);
				}

				InputStreamReader inputStreamReader = new InputStreamReader(
					urlConnection.getInputStream());

				BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

				String line = null;

				while ((line = bufferedReader.readLine()) != null) {
					sb.append(line);
					sb.append("\n");
				}

				bufferedReader.close();

				if (!url.startsWith("file:")) {
					_toStringCache.put(key, sb.toString());
				}

				return sb.toString();
			}
			catch (FileNotFoundException fnfe) {
				retryCount++;

				if (retryCount > 3) {
					throw fnfe;
				}

				System.out.println("Retry in 5 seconds");

				Thread.sleep(5000);
			}
		}
	}

	public static void write(File file, String content) throws IOException {
		System.out.println(
			"Write file " + file + " with length " + content.length());

		File parentDir = file.getParentFile();

		if (!parentDir.exists()) {
			System.out.println("Make parent directories for " + file);

			parentDir.mkdirs();
		}

		Files.write(Paths.get(file.toURI()), content.getBytes());
	}

	protected static final String DEPENDENCIES_URL_FILE;

	static {
		File dependenciesDir = new File("src/test/resources/dependencies/");

		try {
			URI uri = dependenciesDir.toURI();

			URL url = uri.toURL();

			DEPENDENCIES_URL_FILE = url.toString();
		}
		catch (MalformedURLException murle) {
			throw new RuntimeException(murle);
		}
	}

	protected static final String DEPENDENCIES_URL_HTTP =
		"http://mirrors-no-cache.lax.liferay.com/github.com/liferay" +
			"/liferay-jenkins-results-parser-samples-ee/1/";

	private static final Pattern _localURLPattern1 = Pattern.compile(
		"https://test.liferay.com/([0-9]+)/");
	private static final Pattern _localURLPattern2 = Pattern.compile(
		"https://(test-[0-9]+-[0-9]+).liferay.com/");
	private static final Map<String, String> _toStringCache = new HashMap<>();

}