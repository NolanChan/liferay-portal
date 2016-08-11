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

import junit.framework.TestCase;

import org.junit.Test;

/**
 * @author Michael Hashimoto
 */
public class PQLVariableTest extends TestCase {

	@Test
	public void testGetValue() throws Exception {
		_validateVariableResult("false", Boolean.valueOf(false));
		_validateVariableResult("'false'", Boolean.valueOf(false));
		_validateVariableResult("\"false\"", Boolean.valueOf(false));
		_validateVariableResult("true", Boolean.valueOf(true));
		_validateVariableResult("'true'", Boolean.valueOf(true));
		_validateVariableResult("\"true\"", Boolean.valueOf(true));

		_validateVariableResult("3.2", Double.valueOf(3.2));
		_validateVariableResult("'3.2'", Double.valueOf(3.2));
		_validateVariableResult("\"3.2\"", Double.valueOf(3.2));
		_validateVariableResult("2016.0", Double.valueOf(2016));
		_validateVariableResult("'2016.0'", Double.valueOf(2016));
		_validateVariableResult("\"2016.0\"", Double.valueOf(2016));

		_validateVariableResult("2016", Integer.valueOf(2016));
		_validateVariableResult("'2016'", Integer.valueOf(2016));
		_validateVariableResult("\"2016\"", Integer.valueOf(2016));

		_validateVariableResult("test", "test");
		_validateVariableResult("'test'", "test");
		_validateVariableResult("\"test\"", "test");

		_validateVariableResult("'test test'", "test test");
		_validateVariableResult("\"test test\"", "test test");
	}

	@Test
	public void testVariableError() throws Exception {
		_validateVariableError(
			"invalid.property",
			"Property not found in 'test.case.available.property.names': " +
				"invalid.property");
		_validateVariableError(null, "Invalid variable: null");
		_validateVariableError("test == test", "Invalid value: test == test");
		_validateVariableError("test OR test", "Invalid value: test OR test");
	}

	private void _validateVariableError(String pql, String expected)
		throws Exception {

		String actual = null;

		try {
			PQLVariable pqlVariable = new PQLVariable(pql);
		}
		catch (Exception e) {
			actual = e.getMessage();

			if (!actual.equals(expected)) {
				StringBuilder sb = new StringBuilder();

				sb.append("Mismatched error within the following PQL:\n");
				sb.append(pql);
				sb.append("\n\n* Actual:   \"");
				sb.append(actual);
				sb.append("\"\n* Expected: \"");
				sb.append(expected);
				sb.append("\"");

				throw new Exception(sb.toString(), e);
			}
		}
		finally {
			if (actual == null) {
				throw new Exception(
					"No error thrown for the following PQL:\n" + pql);
			}
		}
	}

	private void _validateVariableResult(String pql, Object expected)
		throws Exception {

		Properties properties = new Properties();

		properties.put("portal.smoke", pql);

		Class clazz = expected.getClass();

		PQLVariable pqlVariable = new PQLVariable("portal.smoke");

		Object actual = pqlVariable.getValue(properties);

		if (!clazz.isInstance(actual)) {
			throw new Exception(
				pql + " should be of type '" + clazz.getName() + "'");
		}

		if (!actual.equals(expected)) {
			StringBuilder sb = new StringBuilder();

			sb.append("Mismatched result within the following PQL:\n");
			sb.append(pql);
			sb.append("\n\n* Actual:   \"");
			sb.append(actual);
			sb.append("\"\n* Expected: \"");
			sb.append(expected);
			sb.append("\"");

			throw new Exception(sb.toString());
		}
	}

}