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

import com.liferay.osb.ldn.documentation.project.model.DocumentationProject;
import com.liferay.osb.ldn.documentation.project.service.DocumentationProjectLocalService;
import com.liferay.osb.ldn.generator.basic.project.site.constants.BasicProjectSiteConstants;
import com.liferay.osb.ldn.generator.layout.BaseLayoutGenerator;
import com.liferay.osb.ldn.generator.layout.LayoutGenerator;
import com.liferay.osb.ldn.generator.layout.LayoutVersion;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.StringPool;

import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yury Butrymovich
 */
@Component(
	immediate = true,
	property = {
		"osb.ldn.layout.description=",
		"osb.ldn.layout.friendly.url=/contribution",
		"osb.ldn.layout.hidden:Boolean=false",
		"osb.ldn.layout.name=Contribution Guidelines",
		"osb.ldn.layout.title=Contribution Guidelines",
		"osb.ldn.layout.type=" + LayoutConstants.TYPE_PORTLET,
		"osb.ldn.site.generator.key=" + BasicProjectSiteConstants.BASIC_PROJECT_SITE_KEY
	},
	service = LayoutGenerator.class
)
public class ContributionLayoutGenerator extends BaseLayoutGenerator {

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

		User user = _userLocalService.getDefaultUser(layout.getCompanyId());

		layoutTypePortlet.setLayoutTemplateId(
			user.getUserId(), "1_column", false);

		if (!layoutTypePortlet.hasPortletId(_PROJECT_HEADER_PORTLET_ID)) {
			String projectHeaderPortletId = layoutTypePortlet.addPortletId(
				user.getUserId(), _PROJECT_HEADER_PORTLET_ID, "column-1", 1,
				false);
		}

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.getStrictPortletSetup(
				layout, _PROJECT_HEADER_PORTLET_ID);

		DocumentationProject documentationProject =
			_documentationProjectLocalService.getDocumentationProjectByGroupId(
				layout.getGroupId());

		portletPreferences.setValue(
			"documentationProjectId",
			String.valueOf(documentationProject.getDocumentationProjectId()));

		portletPreferences.store();

		_layoutLocalService.updateLayout(
			layout.getGroupId(), layout.getPrivateLayout(),
			layout.getLayoutId(), layout.getTypeSettings());
	}

	@Reference
	private void setLayoutVersion(LayoutVersion layoutVersion) {
		this.layoutVersion = layoutVersion;
	}

	private static final String _PROJECT_HEADER_PORTLET_ID =
		"com_liferay_osb_ldn_documentation_project_heading_web_" +
			"ProjectHeadingPortlet";

	@Reference
	private DocumentationProjectLocalService _documentationProjectLocalService;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private UserLocalService _userLocalService;

}