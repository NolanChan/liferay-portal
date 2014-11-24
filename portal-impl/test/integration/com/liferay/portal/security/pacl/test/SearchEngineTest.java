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

package com.liferay.portal.security.pacl.test;

import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.test.PACLTestRule;
import com.liferay.portal.test.runners.PACLIntegrationJUnitTestRunner;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Raymond Augé
 */
@RunWith(PACLIntegrationJUnitTestRunner.class)
public class SearchEngineTest {

	@ClassRule
	public static final PACLTestRule paclTestRule = PACLTestRule.INSTANCE;

	@Test
	public void test1() throws Exception {
		try {
			SearchEngineUtil.getSearchEngine("GENERIC_ENGINE");

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void test2() throws Exception {
		try {
			SearchEngineUtil.getSearchEngine("SYSTEM_ENGINE");
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

}