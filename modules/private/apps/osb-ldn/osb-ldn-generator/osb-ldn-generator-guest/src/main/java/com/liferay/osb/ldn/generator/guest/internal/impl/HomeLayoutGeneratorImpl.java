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

package com.liferay.osb.ldn.generator.guest.internal.impl;

import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.osb.ldn.generator.guest.internal.AbstractLayoutGenerator;
import com.liferay.osb.ldn.generator.guest.internal.LayoutGenerator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.LayoutLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yury Butrymovich
 */
@Component(
	immediate = true, property = {"ldn.page.generator.type=home"},
	service = LayoutGenerator.class
)
public class HomeLayoutGeneratorImpl extends AbstractLayoutGenerator {

	public static final String RANDOM_NINE_PORTLET_ID =
		"com_liferay_osb_ldn_documentation_project_random_nine_web_" +
			"DocumentationProjectRandomNinePortlet";

	@Override
	public Layout generate(long userId, long groupId) throws PortalException {
		String name = "Home";
		String title = "Home";
		String url = "/home";
		long version = 1L;

		return getLayout(userId, groupId, name, title, url, version);
	}

	@Override
	protected void resetLayout(Layout layout, long userId) {
		addPortlet(layout, userId, RANDOM_NINE_PORTLET_ID);
	}

	@Reference(unbind = "-")
	protected void setExpandoValueLocalService(
		ExpandoValueLocalService expandoValueLocalService) {

		this.expandoValueLocalService = expandoValueLocalService;
	}

	@Reference(unbind = "-")
	protected void setLayoutLocalService(
		LayoutLocalService layoutLocalService) {

		this.layoutLocalService = layoutLocalService;
	}

}