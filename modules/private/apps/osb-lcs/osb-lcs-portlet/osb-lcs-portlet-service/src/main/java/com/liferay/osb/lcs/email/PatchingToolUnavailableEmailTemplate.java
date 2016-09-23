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

package com.liferay.osb.lcs.email;

import com.liferay.osb.lcs.navigation.util.NavigationUtil;
import com.liferay.osb.lcs.util.PortletPropsValues;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Marko Cikos
 * @author Matija Petanjek
 */
public class PatchingToolUnavailableEmailTemplate extends BaseEmailTemplate {

	public PatchingToolUnavailableEmailTemplate(EmailContext emailContext) {
		super(emailContext);
	}

	@Override
	public Map<Locale, String> getBodyMap() {
		return getLocalizationMap(
			"com/liferay/osb/lcs/email/dependencies" +
				"/email_notification_type_2_body.tmpl",
			"emailNotificationType2Body");
	}

	@Override
	public Object[] getContextAttributes() throws PortalException {
		List<Object> contextAttributes = getBaseContextAttributes();

		String lcsClusterEntryName = emailContext.getLCSClusterEntryName();
		String lcsClusterNodeName = emailContext.getLCSClusterNodeName();
		String lcsProjectName = emailContext.getLCSProjectName();

		contextAttributes.add("[$MESSAGE_FIRST_LINE$]");
		contextAttributes.add(
			translate(
				emailContext,
				"the-patching-tool-for-the-server-x-environment-x-project-x-" +
					"is-currently-unavailable",
				lcsClusterNodeName, lcsClusterEntryName, lcsProjectName));
		contextAttributes.add("[$MESSAGE_SECOND_LINE$]");
		contextAttributes.add(
			translate(
				emailContext,
				"click-the-following-link-for-the-patching-tool-" +
					"configuration-instructions"));
		contextAttributes.add("[$SUBJECT$]");
		contextAttributes.add(
			translate(
				emailContext,
				"the-patching-tool-is-unavailable-for-the-server-x-" +
					"environment-x-project-x",
				lcsClusterNodeName, lcsClusterEntryName, lcsProjectName));
		contextAttributes.add("[$URL_FIRST_LINE$]");
		contextAttributes.add(
			NavigationUtil.getLCSClusterNodeURL(
				emailContext.getLCSClusterNodeId()));
		contextAttributes.add("[$URL_SECOND_LINE$]");
		contextAttributes.add(
			PortletPropsValues.LRDCOM_PATCHING_TOOL_OVERVIEW_URL);
		contextAttributes.add("[$URL_TEXT_FIRST_LINE$]");
		contextAttributes.add(
			translate(
				emailContext, "see-x-on-liferay-connected-services",
				lcsClusterNodeName));
		contextAttributes.add("[$URL_TEXT_SECOND_LINE$]");
		contextAttributes.add(
			PortletPropsValues.LRDCOM_PATCHING_TOOL_OVERVIEW_URL);

		return contextAttributes.toArray();
	}

	@Override
	public String getPopPrefix() {
		return "patch_tool_unavailable_id";
	}

}