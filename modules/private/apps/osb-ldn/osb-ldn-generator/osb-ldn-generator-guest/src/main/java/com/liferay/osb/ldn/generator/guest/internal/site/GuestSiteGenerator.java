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

package com.liferay.osb.ldn.generator.guest.internal.site;

import com.liferay.osb.ldn.generator.guest.internal.LayoutGenerator;
import com.liferay.osb.ldn.generator.guest.internal.LayoutGeneratorFactory;
import com.liferay.osb.ldn.generator.guest.internal.constants.LayoutGeneratorConstants;
import com.liferay.osb.ldn.generator.site.SiteGenerator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.PortalUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yury Butrymovich
 * @author Ryan Park
 */
@Component(
	immediate = true,
	property = {
		"osb.ldn.site.generator.name=Guest Site"
	},
	service = SiteGenerator.class
)
public class GuestSiteGenerator implements SiteGenerator {

	public void generate(long groupId) {
		try {
			addLayouts(groupId);
		}
		catch (PortalException pe) {
		}
	}

	protected void addLayouts(long groupId) throws PortalException {
		long userId = _userLocalService.getDefaultUserId(
			PortalUtil.getDefaultCompanyId());

		for (int pageType : LayoutGeneratorConstants.PAGE_TYPES) {
			LayoutGenerator layoutGenerator =
				_layoutGeneratorFactory.getLayoutGenerator(
					userId, groupId, pageType);

			layoutGenerator.generate(userId, groupId);
		}
	}

	@Reference
	private LayoutGeneratorFactory _layoutGeneratorFactory;

	@Reference
	private UserLocalService _userLocalService;

}