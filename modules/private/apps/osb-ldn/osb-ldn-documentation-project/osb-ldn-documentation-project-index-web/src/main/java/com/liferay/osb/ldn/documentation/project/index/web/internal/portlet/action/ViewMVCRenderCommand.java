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

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.osb.ldn.documentation.project.constants.DocumentationProjectConstants;
import com.liferay.osb.ldn.documentation.project.index.web.internal.constants.DocumentationProjectPortletKeys;
import com.liferay.osb.ldn.documentation.project.model.DocumentationProject;
import com.liferay.osb.ldn.documentation.project.model.DocumentationProjectURLTypeSettings;
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
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Enoch Chu
 * @author Howie Chou
 * @author Yury Butrymovich
 * @author Ryan Park
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

		String predefinedFilterTag = ParamUtil.getString(
			renderRequest, "predefinedFilterTag");

		Template template = (Template)renderRequest.getAttribute(
			WebKeys.TEMPLATE);

		List<Map<String, Object>> documentationProjectsList = null;

		try {
			documentationProjectsList = getDocumentationProjectList(
				renderRequest, themeDisplay, predefinedFilterTag);
		}
		catch (Exception e) {
			_log.error(e, e);

			documentationProjectsList = new ArrayList<>(0);
		}

		template.put("documentationProjects", documentationProjectsList);

		template.put(
			"predefinedFilterTags",
			getPredefinedFilterTagsList(renderRequest, renderResponse));

		template.put("predefinedFilterTag", predefinedFilterTag);

		PortletURL viewUrl = renderResponse.createRenderURL();

		template.put("viewURL", viewUrl.toString());

		Map<String, Object> strings = getStringsMap(
			renderRequest, themeDisplay.getLanguageId(),
			documentationProjectsList.size());

		template.put("strings", strings);

		return "view";
	}

	protected List<Map<String, Object>> getDocumentationProjectList(
			RenderRequest renderRequest, ThemeDisplay themeDisplay,
			String predefinedFilterTag)
		throws Exception {

		List<Map<String, Object>> documentationProjectList = new LinkedList<>();

		List<DocumentationProject> documentationProjects =
			_documentationProjectLocalService.getDocumentationProjects(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		PortletConfig portletConfig = (PortletConfig)renderRequest.getAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG);

		for (DocumentationProject documentationProject :
				documentationProjects) {

			Map<String, Object> documentationProjectMap = new HashMap<>(5);

			AssetEntry assetEntry = _assetEntryLocalService.getEntry(
				DocumentationProject.class.getName(),
				documentationProject.getDocumentationProjectId());

			String[] tagNames = assetEntry.getTagNames();

			List<String> tagNamesList = Arrays.asList(tagNames);

			if (tagNamesList.size() > _TAG_NAMES_COUNT) {
				tagNamesList = tagNamesList.subList(0, _TAG_NAMES_COUNT);
			}

			if (Validator.isNotNull(predefinedFilterTag) &&
				!tagNamesList.contains(predefinedFilterTag)) {

				continue;
			}

			documentationProjectMap.put("assetTagNames", tagNamesList);

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

			String url = StringPool.BLANK;

			String type = documentationProject.getType();

			if (type.equals(DocumentationProjectConstants.TYPE_SITE)) {
				Group group = _groupLocalService.getGroup(
					documentationProject.getGroupId());

				url = group.getDisplayURL(themeDisplay, false);
			}
			else if (type.equals(DocumentationProjectConstants.TYPE_URL)) {
				DocumentationProjectURLTypeSettings settings =
					(DocumentationProjectURLTypeSettings)documentationProject.
						getDocumentationProjectTypeSettings();

				url = settings.getURL();
			}

			documentationProjectMap.put("siteURL", url);

			documentationProjectList.add(documentationProjectMap);
		}

		return documentationProjectList;
	}

	protected List<Map<String, Object>> getPredefinedFilterTagsList(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		List<Map<String, Object>> predefinedFilterTagsList = new LinkedList<>();

		PortletPreferences portletPreferences = renderRequest.getPreferences();

		String predefinedFilterTagsString = portletPreferences.getValue(
			"predefinedFilterTags", StringPool.BLANK);

		String[] predefinedFilterTags = StringUtil.split(
			predefinedFilterTagsString);

		PortletURL portletURL = renderResponse.createRenderURL();

		for (String predefinedFilterTag : predefinedFilterTags) {
			Map<String, Object> predefinedFilterTagMap = new HashMap<>();

			predefinedFilterTagMap.put("name", predefinedFilterTag);

			portletURL.setParameter("predefinedFilterTag", predefinedFilterTag);

			predefinedFilterTagMap.put("url", portletURL.toString());

			predefinedFilterTagsList.add(predefinedFilterTagMap);
		}

		return predefinedFilterTagsList;
	}

	protected Map<String, Object> getStringsMap(
		RenderRequest renderRequest, String languageId,
		int documentationProjectCount) {

		Map<String, Object> strings = new HashMap<>();

		HttpServletRequest httpServletRequest =
			PortalUtil.getHttpServletRequest(renderRequest);

		ResourceBundle resourceBundle =
			_resourceBundleLoader.loadResourceBundle(languageId);

		strings.put("all", LanguageUtil.get(httpServletRequest, "all"));
		strings.put("filter", LanguageUtil.get(httpServletRequest, "filter"));
		strings.put(
			"projects", LanguageUtil.get(httpServletRequest, "projects"));

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

	private static final int _TAG_NAMES_COUNT = 3;

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

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