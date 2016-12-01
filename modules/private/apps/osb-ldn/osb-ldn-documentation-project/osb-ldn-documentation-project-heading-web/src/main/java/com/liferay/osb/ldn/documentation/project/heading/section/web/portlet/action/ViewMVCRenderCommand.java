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

package com.liferay.osb.ldn.documentation.project.heading.section.web.portlet.action;

import com.liferay.osb.ldn.documentation.project.constants.DocumentationProjectConstants;
import com.liferay.osb.ldn.documentation.project.heading.section.web.constants.DocumentationProjectHeadingPortletKeys;
import com.liferay.osb.ldn.documentation.project.model.DocumentationProject;
import com.liferay.osb.ldn.documentation.project.model.DocumentationProjectSiteTypeSettings;
import com.liferay.osb.ldn.documentation.project.model.DocumentationProjectTypeSettings;
import com.liferay.osb.ldn.documentation.project.service.DocumentationProjectLocalService;
import com.liferay.osb.ldn.documentation.project.util.DocumentationProjectTypeSettingsFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yury Butrymovich
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + DocumentationProjectHeadingPortletKeys.DOCUMENTATION_PROJECT_HEADING,
		"mvc.command.name=/", "mvc.command.name=/view"
	},
	service = MVCRenderCommand.class
)
public class ViewMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Template template = (Template)renderRequest.getAttribute(
			WebKeys.TEMPLATE);

		PortletPreferences portletPreferences = renderRequest.getPreferences();

		long documentationProjectId = GetterUtil.getLong(
			portletPreferences.getValue("documentationProjectId", null));

		DocumentationProject documentationProject =
			_documentationProjectLocalService.fetchDocumentationProject(
				documentationProjectId);

		String headerGradientEndColor = "#FFFFFF";
		String headerGradientStartColor = "#FFFFFF";

		DocumentationProjectTypeSettings documentationProjectTypeSettings =
			DocumentationProjectTypeSettingsFactoryUtil.create(
				documentationProject);

		String type = documentationProject.getType();

		if (type.equals(DocumentationProjectConstants.TYPE_SITE)) {
			DocumentationProjectSiteTypeSettings
				documentationProjectSiteTypeSettings =
					(DocumentationProjectSiteTypeSettings)
						documentationProjectTypeSettings;

			headerGradientEndColor =
				documentationProjectSiteTypeSettings.
					getHeaderGradientEndColor();
			headerGradientStartColor =
				documentationProjectSiteTypeSettings.
					getHeaderGradientStartColor();
		}

		template.put("headerGradientEndColor", headerGradientEndColor);
		template.put("headerGradientStartColor", headerGradientStartColor);

		PortletConfig portletConfig = (PortletConfig)renderRequest.getAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG);

		LiferayPortletURL iconURL = PortletURLFactoryUtil.create(
			renderRequest, portletConfig.getPortletName(),
			themeDisplay.getPlid(), PortletRequest.RESOURCE_PHASE);

		iconURL.setCopyCurrentRenderParameters(false);
		iconURL.setParameter(
			"documentationProjectId", String.valueOf(documentationProjectId));
		iconURL.setResourceID("/serve_documentation_project_page_icon");

		template.put("iconURL", iconURL.toString());

		template.put("layouts", getLayoutsList(themeDisplay));
		template.put("projectName", documentationProject.getName());

		return "view";
	}

	protected List<Map<String, Object>> getLayoutsList(
		ThemeDisplay themeDisplay) {

		Group siteGroup = themeDisplay.getSiteGroup();

		List<Layout> layouts = _layoutLocalService.getLayouts(
			siteGroup.getGroupId(), false, LayoutConstants.TYPE_PORTLET);

		List<Map<String, Object>> layoutsList = new LinkedList<>();

		String displayURL = siteGroup.getDisplayURL(themeDisplay);

		Locale locale = themeDisplay.getLocale();

		for (Layout layout : layouts) {
			Map<String, Object> layoutMap = new HashMap<>();

			layoutMap.put("name", layout.getHTMLTitle(locale));

			if (themeDisplay.getPlid() == layout.getPlid()) {
				layoutMap.put("selected", true);
			}
			else {
				layoutMap.put("selected", false);
			}

			layoutMap.put("url", displayURL + layout.getFriendlyURL());

			layoutsList.add(layoutMap);
		}

		return layoutsList;
	}

	@Reference
	private DocumentationProjectLocalService _documentationProjectLocalService;

	@Reference
	private LayoutLocalService _layoutLocalService;

}