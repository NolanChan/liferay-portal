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

package com.liferay.osb.ldn.generator.guest.internal.runner;

import com.liferay.osb.ldn.generator.guest.site.constants.GuestSiteConstants;
import com.liferay.osb.ldn.generator.layout.LayoutGenerator;
import com.liferay.osb.ldn.generator.site.SiteGenerator;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.PortalUtil;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ryan Park
 */
@Component(immediate = true)
public class GuestGeneratorRunner {

	@Activate
	protected void activate() throws Exception {
		Group group = _groupLocalService.getGroup(
			PortalUtil.getDefaultCompanyId(), GroupConstants.GUEST);

		_guestSiteGenerator.generate(group.getGroupId());
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference(target = "(osb.ldn.layout.friendly.url=/blogs)")
	private LayoutGenerator _blogLayoutGenerator;

	@Reference(target = "(osb.ldn.layout.friendly.url=/community)")
	private LayoutGenerator _communityLayoutGenerator;

	@Reference(target = "(osb.ldn.layout.friendly.url=/forums)")
	private LayoutGenerator _forumsLayoutGenerator;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference(
		target = "(osb.ldn.site.generator.key=" + GuestSiteConstants.GUEST_SITE_KEY + ")"
	)
	private SiteGenerator _guestSiteGenerator;

	@Reference(target = "(osb.ldn.layout.friendly.url=/home)")
	private LayoutGenerator _homeLayoutGenerator;

	@Reference(target = "(osb.ldn.layout.friendly.url=/projects)")
	private LayoutGenerator _projectsLayoutGenerator;

}