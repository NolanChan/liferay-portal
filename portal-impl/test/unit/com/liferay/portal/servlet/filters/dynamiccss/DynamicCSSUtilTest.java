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

package com.liferay.portal.servlet.filters.dynamiccss;

import org.junit.Assert;
import org.junit.Test;

public class DynamicCSSUtilTest {

	@Test
	public void testPropagateQueryString() {
		String[] propagatedQueryStrings = {
			"@import url(//main?test=1);",
			"@import url(\"//main?test=1\");",
			"@import url('//main?test=1');"
		};

		String[] contents = {
			"@import url(//main);",
			"@import url(\"//main\");",
			"@import url('//main');"
		};
		
		for (int i = 0; i < propagatedQueryStrings.length; i++) {
			Assert.assertEquals(
				propagatedQueryStrings[i],
				DynamicCSSUtil.propagateQueryString(
					contents[i], "test=1"));
		} 

		String query = "test=1";

		Assert.assertEquals(
			"@import url(//main?p=2&test=1);",
			DynamicCSSUtil.propagateQueryString(
				"@import url(//main?p=2);", query));

		Assert.assertEquals(
			"@import url(\"//main?p=2&test=1\");",
				DynamicCSSUtil.propagateQueryString(
					"@import url(\"//main?p=2\");", query));

		Assert.assertEquals(
			"@import url('//main?p=2&test=1');",
			DynamicCSSUtil.propagateQueryString(
				"@import url('//main?p=2');", query));

		Assert.assertEquals(
			"@import url(http://main?test=1);",
			DynamicCSSUtil.propagateQueryString(
				"@import url(http://main);", query));

		Assert.assertEquals(
			"@import url(\"http://main?test=1\");",
			DynamicCSSUtil.propagateQueryString("@import url(\"http://main\");",
			query));

		Assert.assertEquals(
			"@import url('http://main?p=2&test=1');",
			DynamicCSSUtil.propagateQueryString(
				"@import url('http://main?p=2');", query));
	}
}