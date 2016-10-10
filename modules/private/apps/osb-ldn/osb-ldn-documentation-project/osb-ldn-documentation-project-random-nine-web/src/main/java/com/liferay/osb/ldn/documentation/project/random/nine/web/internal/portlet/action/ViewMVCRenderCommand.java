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

package com.liferay.osb.ldn.documentation.project.random.nine.web.internal.portlet.action;

import com.liferay.osb.ldn.documentation.project.model.DocumentationProject;
import com.liferay.osb.ldn.documentation.project.random.nine.web.internal.constants.DocumentationProjectPortletKeys;
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
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
 * @author Ryan Park
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + DocumentationProjectPortletKeys.DOCUMENTATION_PROJECT_RANDOM_NINE,
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

		List<Map<String, Object>> documentationProjects = null;

		try {
			documentationProjects = getRandomDocumentationProjectList(
				renderRequest, themeDisplay);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			documentationProjects = new ArrayList<>(0);
		}

		template.put("documentationProjects", documentationProjects);

		Map<String, Object> strings = getStringsMap(
			themeDisplay.getLanguageId());

		template.put("strings", strings);

		return "view";
	}

	protected List<Map<String, Object>> getRandomDocumentationProjectList(
			RenderRequest renderRequest, ThemeDisplay themeDisplay)
		throws Exception {

		List<Map<String, Object>> documentationProjectList = new ArrayList<>(9);

		List<DocumentationProject> documentationProjects =
			_documentationProjectLocalService.getDocumentationProjects(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		documentationProjects = ListUtil.copy(documentationProjects);

		Collections.shuffle(documentationProjects);

		PortletConfig portletConfig = (PortletConfig)renderRequest.getAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG);

		for (int i = 0; (i < 9) && (i < documentationProjects.size()); i++) {
			DocumentationProject documentationProject =
				documentationProjects.get(i);

			Map<String, Object> documentationProjectMap = new HashMap<>(3);

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

	protected Map<String, Object> getStringsMap(String languageId) {
		Map<String, Object> strings = new HashMap<>();

		ResourceBundle resourceBundle =
			_resourceBundleLoader.loadResourceBundle(languageId);

		for (String key : resourceBundle.keySet()) {
			strings.put(key, LanguageUtil.get(resourceBundle, key));
		}

		return strings;
	}

	@Reference
	private DocumentationProjectLocalService _documentationProjectLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	private final Log _log = LogFactoryUtil.getLog(ViewMVCRenderCommand.class);

	@Reference(
		target = "(bundle.symbolic.name=com.liferay.osb.ldn.documentation.project.random.nine.web)"
	)
	private ResourceBundleLoader _resourceBundleLoader;

}