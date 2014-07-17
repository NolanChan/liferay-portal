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

package com.liferay.portal.security.membershippolicy;

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.test.runners.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Roberto Díaz
 */
@ExecutionTestListeners(listeners = {MainServletExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class SiteMembershipPolicyBasicTest
	extends BaseSiteMembershipPolicyTestCase {

	@Test
	public void testIsMembershipAllowed() throws Exception {
		long[] userIds = addUsers();
		long[] standardGroupIds = addStandardGroups();

		Assert.assertTrue(
			SiteMembershipPolicyUtil.isMembershipAllowed(
				userIds[0], standardGroupIds[0]));
	}

	@Test
	public void testIsMembershipNotAllowed() throws Exception {
		long[] userIds = addUsers();
		long[] forbiddenGroupIds = addForbiddenGroups();

		Assert.assertFalse(
			SiteMembershipPolicyUtil.isMembershipAllowed(
				userIds[0], forbiddenGroupIds[0]));
	}

	@Test
	public void testIsMembershipNotRequired() throws Exception {
		long[] userIds = addUsers();
		long[] standardGroupIds = addStandardGroups();

		Assert.assertFalse(
			SiteMembershipPolicyUtil.isMembershipRequired(
				userIds[0], standardGroupIds[0]));
	}

	@Test
	public void testIsMembershipRequired() throws Exception {
		long[] userIds = addUsers();
		long[] requiredGroupIds = addRequiredGroups();

		Assert.assertTrue(
			SiteMembershipPolicyUtil.isMembershipRequired(
				userIds[0], requiredGroupIds[0]));
	}

	@Test
	public void testIsRoleAllowed() throws Exception {
		long[] userIds = addUsers();
		long[] standardGroupIds = addStandardGroups();
		long[] standardRoleIds = addStandardRoles();

		Assert.assertTrue(
			SiteMembershipPolicyUtil.isRoleAllowed(
				userIds[0], standardGroupIds[0], standardRoleIds[0]));
	}

	@Test
	public void testIsRoleNotAllowed() throws Exception {
		long[] userIds = addUsers();
		long[] standardGroupIds = addStandardGroups();
		long[] forbiddenRoleIds = addForbiddenRoles();

		Assert.assertFalse(
			SiteMembershipPolicyUtil.isRoleAllowed(
				userIds[0], standardGroupIds[0], forbiddenRoleIds[0]));
	}

	@Test
	public void testIsRoleNotRequired() throws Exception {
		long[] userIds = addUsers();
		long[] standardGroupIds = addStandardGroups();
		long[] standardRoleIds = addStandardRoles();

		Assert.assertFalse(
			SiteMembershipPolicyUtil.isRoleRequired(
				userIds[0], standardGroupIds[0], standardRoleIds[0]));
	}

	@Test
	public void testIsRoleRequired() throws Exception {
		long[] userIds = addUsers();
		long[] standardGroupIds = addStandardGroups();
		long[] requiredRoleIds = addRequiredRoles();

		Assert.assertTrue(
			SiteMembershipPolicyUtil.isRoleRequired(
				userIds[0], standardGroupIds[0], requiredRoleIds[0]));
	}

}