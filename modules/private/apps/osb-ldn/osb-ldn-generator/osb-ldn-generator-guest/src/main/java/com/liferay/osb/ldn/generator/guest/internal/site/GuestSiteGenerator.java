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

import com.liferay.osb.ldn.generator.guest.site.constants.GuestSiteConstants;
import com.liferay.osb.ldn.generator.layout.LayoutGenerator;
import com.liferay.osb.ldn.generator.layout.LayoutGeneratorRegistry;
import com.liferay.osb.ldn.generator.site.SiteGenerator;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yury Butrymovich
 * @author Ryan Park
 */
@Component(
	immediate = true,
	property = {
		"osb.ldn.site.generator.key=" + GuestSiteConstants.GUEST_SITE_KEY
	},
	service = SiteGenerator.class
)
public class GuestSiteGenerator implements SiteGenerator {

	public void generate(long groupId) {
		try {
			generateLayouts(groupId);
		}
		catch (PortalException pe) {
		}
	}

	protected void generateLayouts(long groupId) throws PortalException {
		List<LayoutGenerator> layoutGenerators =
			_layoutGeneratorRegistry.getLayoutGenerators(
				GuestSiteConstants.GUEST_SITE_KEY);

		for (LayoutGenerator layoutGenerator : layoutGenerators) {
			layoutGenerator.generate(groupId);
		}
	}

	@Reference
	private LayoutGeneratorRegistry _layoutGeneratorRegistry;

}