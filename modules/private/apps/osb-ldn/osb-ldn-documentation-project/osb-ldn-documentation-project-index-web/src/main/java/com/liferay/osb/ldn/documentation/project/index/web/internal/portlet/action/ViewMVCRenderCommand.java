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

package com.liferay.osb.ldn.documentation.project.index.web.internal.portlet.action;

import com.liferay.osb.ldn.documentation.project.index.web.internal.constants.DocumentationProjectPortletKeys;
import com.liferay.osb.ldn.documentation.project.model.DocumentationProject;
import com.liferay.osb.ldn.documentation.project.service.DocumentationProjectLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Enoch Chu
 * @author Howie Chou
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + DocumentationProjectPortletKeys.DOCUMENTATION_PROJECT_INDEX,
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

		List<Map<String, Object>> documentationProjectsList = null;

		try {
			documentationProjectsList = getDocumentationProjectList(
				renderRequest, themeDisplay);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			documentationProjectsList = new ArrayList<>(0);
		}

		template.put("documentationProjects", documentationProjectsList);

		Map<String, Object> strings = getStringsMap(
			themeDisplay.getLanguageId(), documentationProjectsList.size());

		template.put("strings", strings);

		return "view";
	}

	protected List<Map<String, Object>> getDocumentationProjectList(
			RenderRequest renderRequest, ThemeDisplay themeDisplay)
		throws Exception {

		List<Map<String, Object>> documentationProjectList = new LinkedList<>();

		List<DocumentationProject> documentationProjects =
			_documentationProjectLocalService.getDocumentationProjects(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		PortletConfig portletConfig = (PortletConfig)renderRequest.getAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG);

		for (DocumentationProject documentationProject :
				documentationProjects) {

			Map<String, Object> documentationProjectMap = new HashMap<>(3);

			documentationProjectMap.put(
				"description", documentationProject.getDescription());

			LiferayPortletURL iconURL = PortletURLFactoryUtil.create(
				renderRequest, portletConfig.getPortletName(),
				themeDisplay.getPlid(), PortletRequest.RESOURCE_PHASE);

			iconURL.setCopyCurrentRenderParameters(false);
			iconURL.setParameter(
				"documentationProjectId",
				String.valueOf(
					documentationProject.getDocumentationProjectId()));
			iconURL.setResourceID("/serve_documentation_project_icon");

			documentationProjectMap.put("iconURL", iconURL.toString());

			documentationProjectMap.put("name", documentationProject.getName());

			Group group = _groupLocalService.getGroup(
				documentationProject.getGroupId());

			documentationProjectMap.put(
				"siteURL", group.getDisplayURL(themeDisplay, false));

			documentationProjectList.add(documentationProjectMap);
		}

		return documentationProjectList;
	}

	protected Map<String, Object> getStringsMap(
		String languageId, int documentationProjectCount) {

		Map<String, Object> strings = new HashMap<>();

		ResourceBundle resourceBundle =
			_resourceBundleLoader.loadResourceBundle(languageId);

		strings.put("projects", LanguageUtil.get(resourceBundle, "projects"));

		if (documentationProjectCount == 1) {
			strings.put(
				"x-projects", LanguageUtil.get(resourceBundle, "1-project"));
		}
		else {
			strings.put(
				"x-projects",
				LanguageUtil.format(
					resourceBundle, "x-projects", documentationProjectCount));
		}

		return strings;
	}

	@Reference
	private DocumentationProjectLocalService _documentationProjectLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	private final Log _log = LogFactoryUtil.getLog(ViewMVCRenderCommand.class);

	@Reference(
		target = "(bundle.symbolic.name=com.liferay.osb.ldn.documentation.project.index.web)"
	)
	private ResourceBundleLoader _resourceBundleLoader;

}