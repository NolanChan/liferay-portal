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

package com.liferay.osb.ldn.generator.basic.project.internal.site;

import com.liferay.osb.ldn.generator.basic.project.site.constants.BasicProjectSiteConstants;
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
		"osb.ldn.site.generator.key=" + BasicProjectSiteConstants.BASIC_PROJECT_SITE_KEY,
		"osb.ldn.site.generator.name=Basic Project"
	},
	service = SiteGenerator.class
)
public class BasicProjectSiteGenerator extends BaseSiteGenerator {

	public void generate(long groupId) throws Exception {
		generateLayout(groupId, _overviewLayoutGenerator);

		generateLayout(groupId, _documentationLayoutGenerator);

		generateLayout(groupId, _contributionLayoutGenerator);

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

	@Reference(target = "(osb.ldn.layout.friendly.url=/contribution)")
	private LayoutGenerator _contributionLayoutGenerator;

	@Reference(target = "(osb.ldn.layout.friendly.url=/documentation)")
	private LayoutGenerator _documentationLayoutGenerator;

	@Reference
	private LayoutSetLocalService _layoutSetLocalService;

	@Reference(target = "(osb.ldn.layout.friendly.url=/overview)")
	private LayoutGenerator _overviewLayoutGenerator;

	@Reference
	private SiteLayoutHelper _siteLayoutHelper;

}