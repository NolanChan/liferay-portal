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

package com.liferay.vldap.server.directory;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Minhchau Dang
 */
public class FilterConstraintTest {

	@Test
	public void testDifferentNameMerge() throws Exception {
		FilterConstraint leftFilterConstraint = getFilterConstraint(
			"cn", "test1");
		FilterConstraint rightFilterConstraint = getFilterConstraint(
			"description", "test2");

		Assert.assertFalse(leftFilterConstraint.merge(rightFilterConstraint));
		Assert.assertEquals("test1", leftFilterConstraint.getValue("cn"));
		Assert.assertEquals(
			"test2", leftFilterConstraint.getValue("description"));
	}

	@Test
	public void testDifferentValueMerge() throws Exception {
		FilterConstraint leftFilterConstraint = getFilterConstraint(
			"cn", "test1");
		FilterConstraint rightFilterConstraint = getFilterConstraint(
			"cn", "test2");

		Assert.assertTrue(leftFilterConstraint.merge(rightFilterConstraint));
	}

	@Test
	public void testLeftAsteriskMerge() throws Exception {
		FilterConstraint leftFilterConstraint = getFilterConstraint("cn", "*");
		FilterConstraint rightFilterConstraint = getFilterConstraint(
			"cn", "test");

		Assert.assertFalse(leftFilterConstraint.merge(rightFilterConstraint));
		Assert.assertEquals("test", leftFilterConstraint.getValue("cn"));
	}

	@Test
	public void testRightAsteriskMerge() throws Exception {
		FilterConstraint leftFilterConstraint = getFilterConstraint(
			"cn", "test");
		FilterConstraint rightFilterConstraint = getFilterConstraint("cn", "*");

		Assert.assertFalse(leftFilterConstraint.merge(rightFilterConstraint));
		Assert.assertEquals("test", leftFilterConstraint.getValue("cn"));
	}

	@Test
	public void testSameValueMerge() throws Exception {
		FilterConstraint leftFilterConstraint = getFilterConstraint(
			"cn", "test");
		FilterConstraint rightFilterConstraint = getFilterConstraint(
			"cn", "test");

		Assert.assertFalse(leftFilterConstraint.merge(rightFilterConstraint));
		Assert.assertEquals("test", leftFilterConstraint.getValue("cn"));
	}

	protected FilterConstraint getFilterConstraint(String name, String value) {
		FilterConstraint filterConstraint = new FilterConstraint();

		filterConstraint.addAttribute(name, value);

		return filterConstraint;
	}

}