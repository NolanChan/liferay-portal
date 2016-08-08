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

package com.liferay.poshi.runner.pql;

import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * @author Michael Hashimoto
 */
public class PQLEntityFactoryTest extends TestCase {

	@Test
	public void testPQLQueryGetPQLResultComparativeOperator() throws Exception {
		Properties properties = new Properties();

		properties.setProperty("component.names", "Blogs,Message Boards,WEM");
		properties.setProperty("portal.smoke", "true");
		properties.setProperty("priority", "5");

		Set<String> queries = new TreeSet<>();

		queries.add("component.names ~ 'Message Boards'");
		queries.add("component.names !~ 'Journal'");

		queries.add("portal.smoke == true");
		queries.add("portal.smoke != false");

		queries.add("priority > 4");
		queries.add("priority >= 5");
		queries.add("priority < 6");
		queries.add("priority <= 5");

		queries.add("priority > 4.1");
		queries.add("priority >= 4.9");
		queries.add("priority < 5.1");
		queries.add("priority <= 5.1");

		for (String query : queries) {
			_validateGetPQLResult(query, Boolean.valueOf(true), properties);
		}

		queries = new TreeSet<>();

		queries.add("component.names !~ 'Message Boards'");
		queries.add("component.names ~ 'Journal'");
		queries.add("portal.smoke != true");
		queries.add("portal.smoke == false");

		queries.add("priority < 4");
		queries.add("priority <= 4");
		queries.add("priority > 6");
		queries.add("priority >= 6");

		queries.add("priority < 4.1");
		queries.add("priority <= 4.9");
		queries.add("priority > 5.1");
		queries.add("priority >= 5.1");

		for (String query : queries) {
			_validateGetPQLResult(query, Boolean.valueOf(false), properties);
		}
	}

	@Test
	public void testPQLQueryGetPQLResultComparativeOperatorError()
		throws Exception {

		Properties properties = new Properties();

		properties.setProperty("component.names", "Blogs,Message Boards,WEM");
		properties.setProperty("portal.smoke", "true");
		properties.setProperty("priority", "5");

		_validateGetPQLResultError(
			"true ==", "Invalid value: true ==", properties);
		_validateGetPQLResultError(
			"false ==", "Invalid value: false ==", properties);
		_validateGetPQLResultError(
			"== true", "Invalid value: == true", properties);
		_validateGetPQLResultError(
			"== false", "Invalid value: == false", properties);
	}

	@Test
	public void testPQLQueryGetPQLResultConditionalOperator() throws Exception {
		Properties properties = new Properties();

		properties.setProperty("component.names", "Blogs,Message Boards,WEM");
		properties.setProperty("portal.smoke", "true");
		properties.setProperty("priority", "5");

		Set<String> queries = new TreeSet<>();

		queries.add("portal.smoke == true AND portal.smoke != false");
		queries.add("portal.smoke == true OR portal.smoke == false");

		queries.add("true AND true");
		queries.add("true OR true");
		queries.add("false OR true");
		queries.add("true OR false");

		for (String query : queries) {
			_validateGetPQLResult(query, Boolean.valueOf(true), properties);
		}

		queries = new TreeSet<>();

		queries.add("portal.smoke == true AND portal.smoke == false");
		queries.add("portal.smoke != true OR portal.smoke == false");

		queries.add("false AND true");
		queries.add("true AND false");
		queries.add("false AND false");
		queries.add("false OR false");

		for (String query : queries) {
			_validateGetPQLResult(query, Boolean.valueOf(false), properties);
		}
	}

	@Test
	public void testPQLQueryGetPQLResultConditionalOperatorError()
		throws Exception {

		Properties properties = new Properties();

		properties.setProperty("component.names", "Blogs,Message Boards,WEM");
		properties.setProperty("portal.smoke", "true");
		properties.setProperty("priority", "5");

		_validateGetPQLResultError(
			"AND true == true", "Invalid value: AND true == true", properties);
		_validateGetPQLResultError(
			"true == true AND", "Invalid value: true == true AND", properties);
		_validateGetPQLResultError(
			"OR true == true", "Invalid value: OR true == true", properties);
		_validateGetPQLResultError(
			"true == true OR", "Invalid value: true == true OR", properties);
		_validateGetPQLResultError(
			"true == true AND AND false == false",
			"Invalid value: AND false == false", properties);
		_validateGetPQLResultError(
			"(true == true) AND", "Invalid value: (true == true) AND",
			properties);
		_validateGetPQLResultError(
			"(true == true) OR", "Invalid value: (true == true) OR",
			properties);
	}

	@Test
	public void testPQLQueryGetPQLResultParenthesis() throws Exception {
		Properties properties = new Properties();

		properties.setProperty("component.names", "Blogs,Message Boards,WEM");
		properties.setProperty("portal.smoke", "true");
		properties.setProperty("priority", "5");

		Set<String> queries = new TreeSet<>();

		queries.add("(portal.smoke == true OR portal.smoke == false) AND true");
		queries.add("(portal.smoke == true AND portal.smoke == false) OR true");

		queries.add("(true OR false) AND true");
		queries.add("(true AND false) OR true");
		queries.add("(true AND false) OR true");
		queries.add("(true AND true) OR false");

		for (String query : queries) {
			_validateGetPQLResult(query, Boolean.valueOf(true), properties);
		}

		queries = new TreeSet<>();

		queries.add("(portal.smoke != true OR portal.smoke == false) AND true");
		queries.add("(portal.smoke != true AND portal.smoke == true) OR false");

		queries.add("(false OR false) AND true");
		queries.add("(false OR true) AND false");
		queries.add("(false OR false) AND false");
		queries.add("(false AND true) OR false");

		for (String query : queries) {
			_validateGetPQLResult(query, Boolean.valueOf(false), properties);
		}
	}

	@Test
	public void testPQLValueGetPQLResult() throws Exception {
		_validateGetPQLResult("false", Boolean.valueOf(false));
		_validateGetPQLResult("'false'", Boolean.valueOf(false));
		_validateGetPQLResult("\"false\"", Boolean.valueOf(false));
		_validateGetPQLResult("true", Boolean.valueOf(true));
		_validateGetPQLResult("'true'", Boolean.valueOf(true));
		_validateGetPQLResult("\"true\"", Boolean.valueOf(true));

		_validateGetPQLResult("3.2", Double.valueOf(3.2));
		_validateGetPQLResult("'3.2'", Double.valueOf(3.2));
		_validateGetPQLResult("\"3.2\"", Double.valueOf(3.2));
		_validateGetPQLResult("2016.0", Double.valueOf(2016));
		_validateGetPQLResult("'2016.0'", Double.valueOf(2016));
		_validateGetPQLResult("\"2016.0\"", Double.valueOf(2016));

		_validateGetPQLResult("2016", Integer.valueOf(2016));
		_validateGetPQLResult("'2016'", Integer.valueOf(2016));
		_validateGetPQLResult("\"2016\"", Integer.valueOf(2016));

		_validateGetPQLResult("test", "test");
		_validateGetPQLResult("'test'", "test");
		_validateGetPQLResult("\"test\"", "test");

		_validateGetPQLResult("'test test'", "test test");
		_validateGetPQLResult("\"test test\"", "test test");
	}

	@Test
	public void testPQLVariableGetPQLResult() throws Exception {
		_validateGetPQLResultFromVariable("false", Boolean.valueOf(false));
		_validateGetPQLResultFromVariable("'false'", Boolean.valueOf(false));
		_validateGetPQLResultFromVariable("\"false\"", Boolean.valueOf(false));
		_validateGetPQLResultFromVariable("true", Boolean.valueOf(true));
		_validateGetPQLResultFromVariable("'true'", Boolean.valueOf(true));
		_validateGetPQLResultFromVariable("\"true\"", Boolean.valueOf(true));

		_validateGetPQLResultFromVariable("3.2", Double.valueOf(3.2));
		_validateGetPQLResultFromVariable("'3.2'", Double.valueOf(3.2));
		_validateGetPQLResultFromVariable("\"3.2\"", Double.valueOf(3.2));
		_validateGetPQLResultFromVariable("2016.0", Double.valueOf(2016));
		_validateGetPQLResultFromVariable("'2016.0'", Double.valueOf(2016));
		_validateGetPQLResultFromVariable("\"2016.0\"", Double.valueOf(2016));

		_validateGetPQLResultFromVariable("2016", Integer.valueOf(2016));
		_validateGetPQLResultFromVariable("'2016'", Integer.valueOf(2016));
		_validateGetPQLResultFromVariable("\"2016\"", Integer.valueOf(2016));

		_validateGetPQLResultFromVariable("test", "test");
		_validateGetPQLResultFromVariable("'test'", "test");
		_validateGetPQLResultFromVariable("\"test\"", "test");

		_validateGetPQLResultFromVariable("'test test'", "test test");
		_validateGetPQLResultFromVariable("\"test test\"", "test test");
	}

	private static void _validateGetPQLResult(
			String pql, Object expectedPQLResult)
		throws Exception {

		_validateGetPQLResult(pql, expectedPQLResult, new Properties());
	}

	private static void _validateGetPQLResult(
			String pql, Object expectedPQLResult, Properties properties)
		throws Exception {

		PQLEntity pqlEntity = PQLEntityFactory.newPQLEntity(pql);

		Object actualPQLResult = pqlEntity.getPQLResult(properties);

		if (!actualPQLResult.equals(expectedPQLResult)) {
			StringBuilder sb = new StringBuilder();

			sb.append("Mismatched PQL result within the following PQL:\n");
			sb.append(pql);
			sb.append("\n* Actual:   ");
			sb.append(actualPQLResult);
			sb.append("\n* Expected: ");
			sb.append(expectedPQLResult);

			throw new Exception(sb.toString());
		}
	}

	private static void _validateGetPQLResultError(
			String pql, String expectedError, Properties properties)
		throws Exception {

		String actualError = null;

		try {
			PQLEntity pqlEntity = PQLEntityFactory.newPQLEntity(pql);

			Object objectValue = pqlEntity.getPQLResult(properties);
		}
		catch (Exception e) {
			actualError = e.getMessage();

			if (!actualError.equals(expectedError)) {
				StringBuilder sb = new StringBuilder();

				sb.append("Mismatched error within the following PQL:\n");
				sb.append(pql);
				sb.append("\n* Actual:   ");
				sb.append(actualError);
				sb.append("\n* Expected: ");
				sb.append(expectedError);

				throw new Exception(sb.toString(), e);
			}
		}
		finally {
			if (actualError == null) {
				throw new Exception(
					"No error thrown for the following PQL: " + pql);
			}
		}
	}

	private static void _validateGetPQLResultFromVariable(
			String pql, Object expectedPQLResult)
		throws Exception {

		Properties properties = new Properties();

		properties.put("portal.smoke", pql);

		_validateGetPQLResult("portal.smoke", expectedPQLResult, properties);
	}

}