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

package com.liferay.osb.ldn.generator.guest.group.internal.impl;

import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.osb.ldn.generator.guest.group.internal.LayoutGenerator;
import com.liferay.osb.ldn.generator.guest.group.internal.LayoutGeneratorFactory;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.LayoutLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yury Butrymovich
 */
@Component(immediate = true, service = LayoutGeneratorFactory.class)
public class LayoutGeneratorFactoryImpl implements LayoutGeneratorFactory {

	@Override
	public LayoutGenerator getLayoutGenerator(
			long userId, long groupId, int type)
		throws PortalException {

		LayoutGenerator layoutGenerator = null;

		if (type == _HOME_PAGE_TYPE) {
			layoutGenerator = _homeLayoutGenerator;
		}
		else if (type == _PROJECTS_PAGE_TYPE) {
			layoutGenerator = _projectsLayoutGenerator;
		}
		else if (type == _COMMUNITY_PAGE_TYPE) {
			layoutGenerator = _communityLayoutGenerator;
		}
		else if (type == _BLOGS_PAGE_TYPE) {
			layoutGenerator = _blogsLayoutGenerator;
		}
		else if (type == _FORUMS_PAGE_TYPE) {
			layoutGenerator = _forumsLayoutGenerator;
		}

		return layoutGenerator;
	}

	private static final int _BLOGS_PAGE_TYPE = 5;

	private static final int _COMMUNITY_PAGE_TYPE = 4;

	private static final int _FORUMS_PAGE_TYPE = 3;

	private static final int _HOME_PAGE_TYPE = 1;

	private static final int _PROJECTS_PAGE_TYPE = 2;

	@Reference(target = "(ldn.page.generator.type=blogs)", unbind = "-")
	private LayoutGenerator _blogsLayoutGenerator;

	@Reference(target = "(ldn.page.generator.type=community)", unbind = "-")
	private LayoutGenerator _communityLayoutGenerator;

	@Reference(unbind = "-")
	private ExpandoValueLocalService _expandoValueLocalService;

	@Reference(target = "(ldn.page.generator.type=forums)", unbind = "-")
	private LayoutGenerator _forumsLayoutGenerator;

	@Reference(target = "(ldn.page.generator.type=home)", unbind = "-")
	private LayoutGenerator _homeLayoutGenerator;

	@Reference(unbind = "-")
	private LayoutLocalService _layoutLocalService;

	@Reference(target = "(ldn.page.generator.type=projects)", unbind = "-")
	private LayoutGenerator _projectsLayoutGenerator;

}