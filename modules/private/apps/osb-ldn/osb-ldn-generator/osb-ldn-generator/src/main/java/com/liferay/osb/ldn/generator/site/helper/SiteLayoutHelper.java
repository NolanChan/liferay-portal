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

package com.liferay.osb.ldn.generator.site.helper;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ryan Park
 */
@Component(service = SiteLayoutHelper.class)
public class SiteLayoutHelper {

	public Layout updateLayout(
			long groupId, String name, String title, String description,
			String type, boolean hidden, String friendlyURL)
		throws Exception {

		Layout layout = _layoutLocalService.fetchLayoutByFriendlyURL(
			groupId, false, friendlyURL);

		if (layout == null) {
			Group group = _groupLocalService.getGroup(groupId);

			layout = _layoutLocalService.addLayout(
				group.getCreatorUserId(), groupId, false, 0, name, title,
				description, type, hidden, friendlyURL, new ServiceContext());
		}
		else {
			Map<Locale, String> nameMap = layout.getNameMap();

			Locale locale = LocaleUtil.getSiteDefault();

			nameMap.put(locale, name);

			Map<Locale, String> titleMap = layout.getTitleMap();

			titleMap.put(locale, title);

			Map<Locale, String> descriptionMap = layout.getDescriptionMap();

			descriptionMap.put(locale, description);

			Map<Locale, String> friendlyURLMap = new HashMap<>();

			friendlyURLMap.put(locale, friendlyURL);

			layout = _layoutLocalService.updateLayout(
				layout.getGroupId(), false, layout.getLayoutId(), 0, nameMap,
				titleMap, descriptionMap, layout.getKeywordsMap(),
				layout.getRobotsMap(), type, hidden, friendlyURLMap, false,
				null, new ServiceContext());
		}

		return layout;
	}

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private LayoutLocalService _layoutLocalService;

}