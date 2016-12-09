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
import com.liferay.osb.ldn.generator.site.BaseSiteGenerator;
import com.liferay.osb.ldn.generator.site.SiteGenerator;
import com.liferay.osb.ldn.generator.site.helper.SiteLayoutHelper;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.service.LayoutSetLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yury Butrymovich
 * @author Ryan Park
 */
@Component(
	immediate = true,
	property = {
		"osb.ldn.site.generator.key=" + GuestSiteConstants.GUEST_SITE_KEY,
		"osb.ldn.site.generator.name=Guest"
	},
	service = SiteGenerator.class
)
public class GuestSiteGenerator extends BaseSiteGenerator {

	@Override
	public void generate(long groupId) throws Exception {
		generateLayout(groupId, _homeLayoutGenerator);

		generateLayout(groupId, _projectsLayoutGenerator);

		generateLayout(groupId, _forumsLayoutGenerator);

		generateLayout(groupId, _blogLayoutGenerator);

		generateLayoutSets(groupId);
	}

	protected void generateLayout(long groupId, LayoutGenerator layoutGenerator)
		throws Exception {

		Layout layout = _siteLayoutHelper.updateLayout(
			groupId, layoutGenerator.getLayoutName(),
			layoutGenerator.getLayoutTitle(),
			layoutGenerator.getLayoutDescription(),
			layoutGenerator.getLayoutType(), layoutGenerator.getLayoutHidden(),
			layoutGenerator.getLayoutFriendlyURL());

		layoutGenerator.generate(layout.getPlid());
	}

	protected void generateLayoutSets(long groupId) throws Exception {
		LayoutSet layoutSet = _layoutSetLocalService.getLayoutSet(
			groupId, false);

		_layoutSetLocalService.updateLookAndFeel(
			groupId, "osbldn_WAR_osbldn", layoutSet.getColorSchemeId(),
			layoutSet.getCss());
	}

	@Reference(target = "(osb.ldn.layout.friendly.url=/blogs)")
	private LayoutGenerator _blogLayoutGenerator;

	@Reference(target = "(osb.ldn.layout.friendly.url=/forums)")
	private LayoutGenerator _forumsLayoutGenerator;

	@Reference(target = "(osb.ldn.layout.friendly.url=/home)")
	private LayoutGenerator _homeLayoutGenerator;

	@Reference
	private LayoutSetLocalService _layoutSetLocalService;

	@Reference(target = "(osb.ldn.layout.friendly.url=/projects)")
	private LayoutGenerator _projectsLayoutGenerator;

	@Reference
	private SiteLayoutHelper _siteLayoutHelper;

}