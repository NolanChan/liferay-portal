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

package com.liferay.osb.ldn.github.top.contributors.web.internal.portlet.action;

import com.liferay.osb.ldn.documentation.project.constants.DocumentationProjectConstants;
import com.liferay.osb.ldn.documentation.project.model.DocumentationProject;
import com.liferay.osb.ldn.documentation.project.model.DocumentationProjectSiteTypeSettings;
import com.liferay.osb.ldn.documentation.project.model.DocumentationProjectTypeSettings;
import com.liferay.osb.ldn.documentation.project.service.DocumentationProjectLocalService;
import com.liferay.osb.ldn.documentation.project.util.DocumentationProjectTypeSettingsFactoryUtil;
import com.liferay.osb.ldn.github.model.GitHubContributor;
import com.liferay.osb.ldn.github.service.GitHubContributorLocalService;
import com.liferay.osb.ldn.github.top.contributors.web.internal.constants.GitHubPortletKeys;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Howie Chou
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + GitHubPortletKeys.GITHUB_TOP_CONTRIBUTORS,
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

		List<Map<String, Object>> gitHubContributors = null;

		try {
			gitHubContributors = getGitHubContributors(
				renderRequest, themeDisplay);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			gitHubContributors = new ArrayList<>(0);
		}

		template.put("gitHubContributors", gitHubContributors);

		Map<String, Object> strings = getStringsMap(
			themeDisplay.getLanguageId());

		template.put("strings", strings);

		return "view";
	}

	protected List<Map<String, Object>> getGitHubContributors(
			RenderRequest renderRequest, ThemeDisplay themeDisplay)
		throws Exception {

		List<Map<String, Object>> gitHubContributors = new ArrayList<>();

		PortletPreferences portletPreferences = renderRequest.getPreferences();

		long documentationProjectId = GetterUtil.getLong(
			portletPreferences.getValue("documentationProjectId", null));

		DocumentationProject documentationProject =
			_documentationProjectLocalService.getDocumentationProject(
				documentationProjectId);

		DocumentationProjectTypeSettings documentationProjectTypeSettings =
			DocumentationProjectTypeSettingsFactoryUtil.create(
				documentationProject);

		String gitHubRepositoryName = StringPool.BLANK;
		String gitHubRepositoryOwner = StringPool.BLANK;

		String type = documentationProject.getType();

		if (type.equals(DocumentationProjectConstants.TYPE_SITE)) {
			DocumentationProjectSiteTypeSettings
				documentationProjectSiteTypeSettings =
					(DocumentationProjectSiteTypeSettings)
						documentationProjectTypeSettings;

			gitHubRepositoryName =
				documentationProjectSiteTypeSettings.getGitHubRepositoryName();
			gitHubRepositoryOwner =
				documentationProjectSiteTypeSettings.getGitHubRepositoryOwner();
		}

		int contributorsCount = GetterUtil.getInteger(
			portletPreferences.getValue("contributorsCount", null), 5);

		List<GitHubContributor> topGitHubContributors =
			_gitHubContributorLocalService.getTopGitHubContributors(
				themeDisplay.getUserId(), gitHubRepositoryOwner,
				gitHubRepositoryName, contributorsCount);

		for (GitHubContributor gitHubContributor : topGitHubContributors) {
			Map<String, Object> gitHubContributorMap = new HashMap<>();

			gitHubContributorMap.put(
				"avatarURL", gitHubContributor.getAvatarURL());
			gitHubContributorMap.put("name", gitHubContributor.getName());
			gitHubContributorMap.put(
				"profileURL", gitHubContributor.getProfileURL());

			gitHubContributors.add(gitHubContributorMap);
		}

		return gitHubContributors;
	}

	protected Map<String, Object> getStringsMap(String languageId) {
		Map<String, Object> stringsMap = new HashMap<>();

		ResourceBundle resourceBundle =
			_resourceBundleLoader.loadResourceBundle(languageId);

		for (String key : resourceBundle.keySet()) {
			stringsMap.put(key, LanguageUtil.get(resourceBundle, key));
		}

		return stringsMap;
	}

	@Reference
	private DocumentationProjectLocalService _documentationProjectLocalService;

	@Reference
	private GitHubContributorLocalService _gitHubContributorLocalService;

	private final Log _log = LogFactoryUtil.getLog(ViewMVCRenderCommand.class);

	@Reference(
		target = "(bundle.symbolic.name=com.liferay.osb.ldn.github.top.contributors.web)"
	)
	private ResourceBundleLoader _resourceBundleLoader;

}