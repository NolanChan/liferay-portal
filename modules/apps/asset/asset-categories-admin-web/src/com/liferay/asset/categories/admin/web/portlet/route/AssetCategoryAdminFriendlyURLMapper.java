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

package com.liferay.asset.categories.admin.web.portlet.route;

import com.liferay.asset.categories.admin.web.constants.AssetCategoriesAdminPortletKeys;
import com.liferay.portal.kernel.portlet.DefaultFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;

import org.osgi.service.component.annotations.Component;

/**
 * @author Julio Camarero
 */
@Component(
	property = {
		"com.liferay.portlet.friendly-url-routes=com/liferay/asset/categories/admin/web/portlet/route/asset-category-admin-friendly-url-routes.xml",
		"javax.portlet.name=" + AssetCategoriesAdminPortletKeys.ASSET_CATEGORIES_ADMIN
	},
	service = FriendlyURLMapper.class
)
public class AssetCategoryAdminFriendlyURLMapper
	extends DefaultFriendlyURLMapper {

	@Override
	public String getMapping() {
		return _MAPPING;
	}

	private static final String _MAPPING = "categories_admin";

}