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

package com.liferay.portlet.shopping.service.permission;

import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.permission.BasePermissionTestCase;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.MainServletTestRule;
import com.liferay.portlet.shopping.model.ShoppingCategory;
import com.liferay.portlet.shopping.util.test.ShoppingTestUtil;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Eric Chin
 * @author Shinn Lok
 */
public class ShoppingCategoryPermissionTest extends BasePermissionTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), MainServletTestRule.INSTANCE);

	@Test
	public void testContains() throws Exception {
		Assert.assertTrue(
			ShoppingCategoryPermission.contains(
				permissionChecker, _category, ActionKeys.VIEW));
		Assert.assertTrue(
			ShoppingCategoryPermission.contains(
				permissionChecker, _subcategory, ActionKeys.VIEW));

		removePortletModelViewPermission();

		Assert.assertFalse(
			ShoppingCategoryPermission.contains(
				permissionChecker, _category, ActionKeys.VIEW));
		Assert.assertFalse(
			ShoppingCategoryPermission.contains(
				permissionChecker, _subcategory, ActionKeys.VIEW));
	}

	@Override
	protected void doSetUp() throws Exception {
		_category = ShoppingTestUtil.addCategory(group.getGroupId());

		_subcategory = ShoppingTestUtil.addCategory(
			group.getGroupId(), _category.getCategoryId());
	}

	@Override
	protected String getResourceName() {
		return ShoppingPermission.RESOURCE_NAME;
	}

	private ShoppingCategory _category;
	private ShoppingCategory _subcategory;

}