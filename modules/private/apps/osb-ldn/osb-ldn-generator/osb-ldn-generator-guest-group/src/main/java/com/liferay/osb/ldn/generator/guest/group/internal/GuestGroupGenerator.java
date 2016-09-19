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

import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.service.ExpandoColumnLocalService;
import com.liferay.expando.kernel.service.ExpandoTableLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.PortalUtil;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yury Butrymovich
 */
@Component(immediate = true)
public class GuestGroupGenerator {

	@Activate
	public void activate() throws Exception {
		long userId = _userLocalService.getDefaultUserId(
			PortalUtil.getDefaultCompanyId());

		createExpando();

		addLayouts(userId);
	}

	protected void addLayouts(long userId) throws PortalException {
		Group group = _groupLocalService.getGroup(
			PortalUtil.getDefaultCompanyId(), _GUEST_GROUP);

		int[] pageTypes = {
			_HOME_PAGE_TYPE, _PROJECTS_PAGE_TYPE, _FORUMS_PAGE_TYPE,
			_COMMUNITY_PAGE_TYPE, _BLOGS_PAGE_TYPE
		};

		for (int pageType : pageTypes) {
			LayoutGenerator layoutGenerator =
				_layoutGeneratorFactory.getLayoutGenerator(
					userId, group.getGroupId(), pageType);

			layoutGenerator.generate(userId, group.getGroupId());
		}
	}

	protected void createExpando() throws PortalException {
		ExpandoTable expandoTable = null;

		try {
			expandoTable = _expandoTableLocalService.getTable(
				PortalUtil.getDefaultCompanyId(), Layout.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME);
		}
		catch (PortalException pe) {
			expandoTable = _expandoTableLocalService.addDefaultTable(
				PortalUtil.getDefaultCompanyId(), Layout.class.getName());
		}

		ExpandoColumn expandoColumn = _expandoColumnLocalService.getColumn(
			expandoTable.getTableId(), "version");

		if (expandoColumn == null) {
			_expandoColumnLocalService.addColumn(
				expandoTable.getTableId(), "version",
				ExpandoColumnConstants.LONG);
		}
	}

	private static final int _BLOGS_PAGE_TYPE = 5;

	private static final int _COMMUNITY_PAGE_TYPE = 4;

	private static final int _FORUMS_PAGE_TYPE = 3;

	private static final String _GUEST_GROUP = "Guest";

	private static final int _HOME_PAGE_TYPE = 1;

	private static final int _PROJECTS_PAGE_TYPE = 2;

	@Reference
	private ExpandoColumnLocalService _expandoColumnLocalService;

	@Reference
	private ExpandoTableLocalService _expandoTableLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private LayoutGeneratorFactory _layoutGeneratorFactory;

	@Reference
	private UserLocalService _userLocalService;

}