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

package com.liferay.osb.ldn.generator.guest.group.internal;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yury Butrymovich
 */
@Component(immediate = true)
public class GuestGroupGenerator {

	@Activate
	public void start() throws Exception {
		long userId = _userLocalService.getDefaultUserId(
			PortalUtil.getDefaultCompanyId());

		addPages(userId);
	}

	protected void addPages(long userId) throws PortalException {
		Locale locale = LocaleUtil.getDefault();

		Map<Locale, String> nameMap = new HashMap<>();
		nameMap.put(locale, "LDN Community");

		Map<Locale, String> descriptionMap = new HashMap<>();
		descriptionMap.put(locale, "Test LDN community");

		Group group;

		group = _groupLocalService.getGroup(
			PortalUtil.getDefaultCompanyId(), _GUEST_GROUP);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setUuid(PortalUUIDUtil.generate());

		for (int i = 1; i < 10; i++) {
			Layout layout = _layoutLocalService.addLayout(
				userId, group.getGroupId(), false, 0, "LDN Page " + i,
				"LDN Page title " + i, "LDN page description",
				LayoutConstants.TYPE_PORTLET, false, "/page" + i,
				serviceContext);
			addPortletToLayout(layout, userId);
		}
	}

	protected void addPortletToLayout(Layout layout, long userId) {
		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.addPortletId(userId, _RANDOM_NINE_PORTLET_ID);

		_layoutLocalService.updateLayout(layout);
	}

	private static final String _GUEST_GROUP = "Guest";

	private static final String _RANDOM_NINE_PORTLET_ID =
		"com_liferay_osb_ldn_documentation_project_random_nine_web_" +
			"DocumentationProjectRandomNinePortlet";

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private UserLocalService _userLocalService;

}