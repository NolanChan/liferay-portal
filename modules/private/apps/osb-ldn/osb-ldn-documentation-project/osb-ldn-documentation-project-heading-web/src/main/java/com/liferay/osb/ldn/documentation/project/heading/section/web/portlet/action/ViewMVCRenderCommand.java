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
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;

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

		PortletPreferences portletPreferences = renderRequest.getPreferences();

		String documentProjectIdParam = portletPreferences.getValue(
			"documentationProjectId", StringPool.BLANK);

		long documentationProjectId = Long.parseLong(documentProjectIdParam);

		DocumentationProject documentationProject =
			_documentationProjectLocalService.fetchDocumentationProject(
				documentationProjectId);

		String gradientStartColor = "#ffffff";
		String gradientEndColor = "#ffffff";

		DocumentationProjectTypeSettings settings =
			DocumentationProjectTypeSettingsFactoryUtil.create(
				documentationProject);

		if (documentationProject.getType().equals(
				DocumentationProjectConstants.TYPE_SITE)) {

			DocumentationProjectSiteTypeSettings siteSettings =
				(DocumentationProjectSiteTypeSettings)settings;

			gradientStartColor = siteSettings.getHeaderGradientStartColor();
			gradientEndColor = siteSettings.getHeaderGradientEndColor();
		}

		PortletConfig portletConfig = (PortletConfig)renderRequest.getAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG);

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		LiferayPortletURL iconURL = PortletURLFactoryUtil.create(
			renderRequest, portletConfig.getPortletName(),
			themeDisplay.getPlid(), PortletRequest.RESOURCE_PHASE);

		iconURL.setCopyCurrentRenderParameters(false);
		iconURL.setParameter("documentationProjectId", documentProjectIdParam);
		iconURL.setResourceID("/serve_documentation_project_page_icon");

		String iconUrlParam = iconURL.toString();

		String projectName = documentationProject.getName();

		Template template = (Template)renderRequest.getAttribute(
			WebKeys.TEMPLATE);

		template.put("gradientStartColor", gradientStartColor);
		template.put("gradientEndColor", gradientEndColor);
		template.put("iconURL", iconUrlParam);
		template.put("projectName", projectName);

		return "view";
	}

	@Reference
	private DocumentationProjectLocalService _documentationProjectLocalService;

}