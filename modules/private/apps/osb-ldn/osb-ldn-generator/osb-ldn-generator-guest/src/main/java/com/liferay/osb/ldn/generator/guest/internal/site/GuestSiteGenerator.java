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
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutSetLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

	public void generate(long groupId) throws Exception {
		generateLayouts(groupId);

		generateLayoutSets(groupId);
	}

	protected void generateLayouts(long groupId) throws Exception {
		List<LayoutGenerator> layoutGenerators =
			_layoutGeneratorRegistry.getLayoutGenerators(
				GuestSiteConstants.GUEST_SITE_KEY);

		for (LayoutGenerator layoutGenerator : layoutGenerators) {
			Layout layout = _layoutLocalService.fetchLayoutByFriendlyURL(
				groupId, false, layoutGenerator.getLayoutFriendlyURL());

			if (layout == null) {
				Group group = _groupLocalService.getGroup(groupId);

				User user = _userLocalService.getDefaultUser(
					group.getCompanyId());

				layout = _layoutLocalService.addLayout(
					user.getUserId(), groupId, false, 0,
					layoutGenerator.getLayoutName(),
					layoutGenerator.getLayoutTitle(),
					layoutGenerator.getLayoutDescription(),
					layoutGenerator.getLayoutType(),
					layoutGenerator.getLayoutHidden(),
					layoutGenerator.getLayoutFriendlyURL(),
					new ServiceContext());
			}
			else {
				Map<Locale, String> nameMap = layout.getNameMap();
				Map<Locale, String> titleMap = layout.getTitleMap();
				Map<Locale, String> descriptionMap = layout.getDescriptionMap();
				Map<Locale, String> friendlyURLMap = new HashMap<>();

				Locale locale = LocaleUtil.getSiteDefault();

				nameMap.put(locale, layoutGenerator.getLayoutName());
				titleMap.put(locale, layoutGenerator.getLayoutTitle());
				descriptionMap.put(
					locale, layoutGenerator.getLayoutDescription());
				friendlyURLMap.put(
					locale, layoutGenerator.getLayoutFriendlyURL());

				layout = _layoutLocalService.updateLayout(
					layout.getGroupId(), false, layout.getLayoutId(), 0,
					layout.getNameMap(), layout.getTitleMap(),
					layout.getDescriptionMap(), layout.getKeywordsMap(),
					layout.getRobotsMap(), layoutGenerator.getLayoutType(),
					layoutGenerator.getLayoutHidden(), friendlyURLMap, false,
					null, new ServiceContext());
			}

			layoutGenerator.generate(layout.getPlid());
		}
	}

	protected void generateLayoutSets(long groupId) throws Exception {
		LayoutSet layoutSet = _layoutSetLocalService.getLayoutSet(
			groupId, false);

		_layoutSetLocalService.updateLookAndFeel(
			groupId, "osbldn_WAR_osbldn", layoutSet.getColorSchemeId(),
			layoutSet.getCss());
	}

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private LayoutGeneratorRegistry _layoutGeneratorRegistry;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private LayoutSetLocalService _layoutSetLocalService;

	@Reference
	private UserLocalService _userLocalService;

}