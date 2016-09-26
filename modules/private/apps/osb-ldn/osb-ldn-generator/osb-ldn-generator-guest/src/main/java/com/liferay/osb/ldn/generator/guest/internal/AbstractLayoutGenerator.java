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

package com.liferay.osb.ldn.generator.guest.internal;

import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Yury Butrymovich
 */
public abstract class AbstractLayoutGenerator implements LayoutGenerator {

	protected void addPortlet(Layout layout, long userId, String portletId) {
		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.addPortletId(userId, portletId);

		layoutLocalService.updateLayout(layout);
	}

	protected void addVersion(Layout layout, long userId, long version)
		throws PortalException {

		long expandoVersion = expandoValueLocalService.getData(
			PortalUtil.getDefaultCompanyId(), Layout.class.getName(),
			ExpandoTableConstants.DEFAULT_TABLE_NAME, VERSION_ATTRIBUTE,
			layout.getPrimaryKey(), -1L);

		if (expandoVersion == -1) {
			expandoValueLocalService.addValue(
				PortalUtil.getDefaultCompanyId(), Layout.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME, VERSION_ATTRIBUTE,
				layout.getPrimaryKey(), version);
		}

		if (expandoVersion != version) {
			resetLayout(layout, userId);
		}
	}

	protected Layout getLayout(
			long userId, long groupId, String name, String title, String url,
			long version)
		throws PortalException {

		Layout layout = null;

		try {
			layout = layoutLocalService.getFriendlyURLLayout(
				groupId, false, url);
		}
		catch (PortalException pe) {
			layout = layoutLocalService.addLayout(
				userId, groupId, false, 0, name, title, StringPool.BLANK,
				LayoutConstants.TYPE_PORTLET, false, url, new ServiceContext());
		}

		addVersion(layout, userId, version);

		return layout;
	}

	protected void resetLayout(Layout layout, long userId) {
	}

	protected static final String VERSION_ATTRIBUTE = "version";

	protected ExpandoValueLocalService expandoValueLocalService;
	protected LayoutLocalService layoutLocalService;

}