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

package com.liferay.asset.browser.web.asset.provider;

import com.liferay.asset.browser.web.constants.AssetBrowserPortletKeys;
import com.liferay.portal.kernel.provider.BrowsePortletProvider;
import com.liferay.portal.kernel.provider.PortletProvider;

import org.osgi.service.component.annotations.Component;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true,
	property = {
		"model.class.name=" + PortletProvider.CLASS_NAME_ANY
	},
	service = BrowsePortletProvider.class
)
public class AssetBrowserPortletProvider implements BrowsePortletProvider {

	@Override
	public String getPortletId() {
		return AssetBrowserPortletKeys.ASSET_BROWSER;
	}

}