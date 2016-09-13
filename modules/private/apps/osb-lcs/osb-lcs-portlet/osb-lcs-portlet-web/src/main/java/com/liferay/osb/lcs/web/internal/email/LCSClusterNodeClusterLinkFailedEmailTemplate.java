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
import com.liferay.osb.lcs.util.LCSClusterNodeUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Marko Cikos
 * @author Matija Petanjek
 */
public class LCSClusterNodeClusterLinkFailedEmailTemplate
	extends BaseEmailTemplate {

	public LCSClusterNodeClusterLinkFailedEmailTemplate(
		EmailContext emailContext) {

		super(emailContext);
	}

	@Override
	public Map<Locale, String> getBodyMap(
		PortletPreferences portletPreferences) {

		return getLocalizationMap(
			"com/liferay/osb/lcs/email/dependencies" +
				"/email_notification_type_3_body.tmpl",
			"emailNotificationType3Body", portletPreferences);
	}

	@Override
	public Object[] getContextAttributes()
		throws PortalException, SystemException {

		List<Object> contextAttributes = getBaseContextAttributes();

		String lcsClusterEntryName = emailContext.getLCSClusterEntryName();
		String lcsClusterNodeName = emailContext.getLCSClusterNodeName();
		String lcsProjectName = emailContext.getLCSProjectName();

		contextAttributes.add("[$MESSAGE_FIRST_LINE$]");
		contextAttributes.add(
			emailContext.translate(
				"there-is-a-communication-error-between-nodes-in-one-of-" +
					"your-clusters"));
		contextAttributes.add("[$MESSAGE_SECOND_LINE$]");
		contextAttributes.add(
			emailContext.translate(
				"lcs-detected-a-cluster-link-failure-on-server-x-in-cluster-" +
					"x-for-project-x",
				lcsClusterNodeName, lcsClusterEntryName, lcsProjectName));
		contextAttributes.add("[$MESSAGE_THIRD_LINE$]");
		contextAttributes.add(
			emailContext.translate(
				"x-has-no-link-to-the-following-nodes", lcsClusterNodeName));
		contextAttributes.add("[$SIBLING_SERVER_NAMES$]");
		contextAttributes.add(
			LCSClusterNodeUtil.getLCSClusterNodeNames(
				emailContext.getSiblingLCSClusterNodeKeys()));
		contextAttributes.add("[$SUBJECT$]");
		contextAttributes.add(
			emailContext.translate(
				"broken-connections-detected-in-cluster-x-project-x",
				lcsClusterEntryName, lcsProjectName));
		contextAttributes.add("[$URL_FIRST_LINE$]");
		contextAttributes.add(
			NavigationUtil.getLCSClusterEntryURL(
				emailContext.getLCSClusterEntryId()));
		contextAttributes.add("[$URL_TEXT_FIRST_LINE$]");
		contextAttributes.add(
			emailContext.translate(
				"see-x-on-liferay-connected-services", lcsClusterEntryName));

		return contextAttributes.toArray();
	}

	@Override
	public String getPopPrefix() {
		return "lcs_cluster_node_cluster_link_failed_id";
	}

}