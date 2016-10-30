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

package com.liferay.osb.ldn.generator.basic.project.internal.layout;

import com.liferay.osb.ldn.generator.basic.project.site.constants.BasicProjectSiteConstants;
import com.liferay.osb.ldn.generator.layout.BaseLayoutGenerator;
import com.liferay.osb.ldn.generator.layout.LayoutGenerator;
import com.liferay.osb.ldn.generator.layout.LayoutVersion;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.util.StringPool;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yury Butrymovich
 */
@Component(
	immediate = true,
	property = {
		"osb.ldn.layout.description=",
		"osb.ldn.layout.friendly.url=/documentation",
		"osb.ldn.layout.hidden:Boolean=false",
		"osb.ldn.layout.name=Documentation",
		"osb.ldn.layout.title=Documentation",
		"osb.ldn.layout.type=" + LayoutConstants.TYPE_PORTLET,
		"osb.ldn.site.generator.key=" + BasicProjectSiteConstants.BASIC_PROJECT_SITE_KEY
	},
	service = LayoutGenerator.class
)
public class DocumentationLayoutGenerator extends BaseLayoutGenerator {

	public static final int LAYOUT_VERSION = 1;

	@Override
	public int getLayoutVersion() {
		return LAYOUT_VERSION;
	}

	@Override
	protected void doGenerate(long plid) throws Exception {
		Layout layout = _layoutLocalService.getLayout(plid);

		layout.setTypeSettings(StringPool.BLANK);

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		layoutTypePortlet.setLayoutTemplateId(
			layout.getUserId(), "1_column", false);

		layoutTypePortlet.addPortletId(
			layout.getUserId(), KNOWLEDGE_BASE_DISPLAY_PORTLET_ID, "column-1",
			0, false);

		_layoutLocalService.updateLayout(
			layout.getGroupId(), layout.getPrivateLayout(),
			layout.getLayoutId(), layout.getTypeSettings());
	}

	protected static final String KNOWLEDGE_BASE_DISPLAY_PORTLET_ID =
		"com_liferay_knowledge_base_web_portlet_DisplayPortlet";

	@Reference
	private void setLayoutVersion(LayoutVersion layoutVersion) {
		this.layoutVersion = layoutVersion;
	}

	@Reference
	private LayoutLocalService _layoutLocalService;

}