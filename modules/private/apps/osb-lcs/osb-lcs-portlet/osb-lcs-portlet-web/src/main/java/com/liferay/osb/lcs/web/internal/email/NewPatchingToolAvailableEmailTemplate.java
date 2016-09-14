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

package com.liferay.osb.lcs.web.internal.email;

import com.liferay.osb.lcs.navigation.util.NavigationUtil;
import com.liferay.osb.lcs.util.PortletPropsValues;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Marko Cikos
 * @author Matija Petanjek
 */
public class NewPatchingToolAvailableEmailTemplate extends BaseEmailTemplate {

	public NewPatchingToolAvailableEmailTemplate(EmailContext emailContext) {
		super(emailContext);
	}

	@Override
	public Map<Locale, String> getBodyMap(
		PortletPreferences portletPreferences) {

		return getLocalizationMap(
			"com/liferay/osb/lcs/email/dependencies" +
				"/email_notification_type_2_body.tmpl",
			"emailNotificationType2Body", portletPreferences);
	}

	@Override
	public Object[] getContextAttributes() throws PortalException {
		List<Object> contextAttributes = getBaseContextAttributes();

		String lcsClusterEntryName = emailContext.getLCSClusterEntryName();
		String lcsClusterNodeName = emailContext.getLCSClusterNodeName();
		String lcsProjectName = emailContext.getLCSProjectName();

		contextAttributes.add("[$MESSAGE_FIRST_LINE$]");

		if (emailContext.isCluster()) {
			contextAttributes.add(
				emailContext.translate(
					"a-new-patching-tool-for-the-cluster-x-project-x-is-" +
						"available",
					lcsClusterEntryName, lcsProjectName));
			contextAttributes.add("[$SUBJECT$]");
			contextAttributes.add(
				emailContext.translate(
					"a-new-patching-tool-is-available-for-the-cluster-x-" +
						"project-x",
					lcsClusterEntryName, lcsProjectName));
			contextAttributes.add("[$URL_FIRST_LINE$]");
			contextAttributes.add(
				NavigationUtil.getLCSClusterEntryURL(
					emailContext.getLCSClusterEntryId()));
			contextAttributes.add("[$URL_TEXT_FIRST_LINE$]");
			contextAttributes.add(
				emailContext.translate(
					"see-x-on-liferay-connected-services",
					lcsClusterEntryName));
		}
		else {
			contextAttributes.add(
				emailContext.translate(
					"a-new-patching-tool-for-the-server-x-environment-x-" +
						"project-x-is-available",
					lcsClusterNodeName, lcsClusterEntryName, lcsProjectName));
			contextAttributes.add("[$SUBJECT$]");
			contextAttributes.add(
				emailContext.translate(
					"a-new-patching-tool-is-available-for-the-server-x-" +
						"environment-x-project-x",
					lcsClusterNodeName, lcsClusterEntryName, lcsProjectName));
			contextAttributes.add("[$URL_FIRST_LINE$]");
			contextAttributes.add(
				NavigationUtil.getLCSClusterNodeURL(
					emailContext.getLCSClusterNodeId()));
			contextAttributes.add("[$URL_TEXT_FIRST_LINE$]");
			contextAttributes.add(
				emailContext.translate(
					"see-x-on-liferay-connected-services", lcsClusterNodeName));
		}

		contextAttributes.add("[$MESSAGE_SECOND_LINE$]");
		contextAttributes.add(
			emailContext.translate(
				"click-the-following-link-for-the-patching-tool-" +
					"configuration-instructions"));
		contextAttributes.add("[$URL_SECOND_LINE$]");
		contextAttributes.add(
			PortletPropsValues.LRDCOM_PATCHING_TOOL_OVERVIEW_URL);
		contextAttributes.add("[$URL_TEXT_SECOND_LINE$]");
		contextAttributes.add(
			PortletPropsValues.LRDCOM_PATCHING_TOOL_OVERVIEW_URL);

		return contextAttributes.toArray();
	}

	@Override
	public String getPopPrefix() {
		if (emailContext.isCluster()) {
			return "lcs_new_cluster_patching_tool_available_id";
		}

		return "lcs_new_patching_tool_available_id";
	}

}