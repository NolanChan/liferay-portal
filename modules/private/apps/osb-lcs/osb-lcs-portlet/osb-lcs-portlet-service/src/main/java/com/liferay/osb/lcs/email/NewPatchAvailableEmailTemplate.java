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

import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Marko Cikos
 * @author Matija Petanjek
 */
public class NewPatchAvailableEmailTemplate extends BaseEmailTemplate {

	public NewPatchAvailableEmailTemplate(EmailContext emailContext) {
		super(emailContext);
	}

	@Override
	public Map<Locale, String> getBodyMap() {
		return getLocalizationMap(
			"com/liferay/osb/lcs/email/dependencies" +
				"/email_notification_type_1_body.tmpl",
			"emailNotificationType1Body");
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
				translate(
					emailContext,
					"a-new-fix-pack-is-available-and-ready-for-installation-" +
						"for-the-cluster-x-project-x",
					lcsClusterEntryName, lcsProjectName));
			contextAttributes.add("[$SUBJECT$]");
			contextAttributes.add(
				translate(
					emailContext,
					"a-new-fix-pack-is-available-for-the-cluster-x-project-x",
					lcsClusterEntryName, lcsProjectName));
			contextAttributes.add("[$URL_FIRST_LINE$]");
			contextAttributes.add(
				_navigationAdvisor.getLCSClusterEntryURL(
					emailContext.getLCSClusterEntryId()));
			contextAttributes.add("[$URL_TEXT_FIRST_LINE$]");
			contextAttributes.add(
				translate(
					emailContext, "see-x-on-liferay-connected-services",
					lcsClusterEntryName));
		}
		else {
			contextAttributes.add(
				translate(
					emailContext,
					"a-new-fix-pack-is-available-and-ready-for-installation-" +
						"for-the-server-x-environment-x-project-x",
					lcsClusterNodeName, lcsClusterEntryName, lcsProjectName));
			contextAttributes.add("[$SUBJECT$]");
			contextAttributes.add(
				translate(
					emailContext,
					"a-new-fix-pack-is-available-for-the-server-x-" +
						"environment-x-project-x",
					lcsClusterNodeName, lcsClusterEntryName, lcsProjectName));
			contextAttributes.add("[$URL_FIRST_LINE$]");
			contextAttributes.add(
				_navigationAdvisor.getLCSClusterNodeURL(
					emailContext.getLCSClusterNodeId()));
			contextAttributes.add("[$URL_TEXT_FIRST_LINE$]");
			contextAttributes.add(
				translate(
					emailContext, "see-x-on-liferay-connected-services",
					lcsClusterNodeName));
		}

		return contextAttributes.toArray();
	}

	@Override
	public String getPopPrefix() {
		if (emailContext.isCluster()) {
			return "lcs_new_cluster_patch_available_id";
		}

		return "lcs_new_patch_available_id";
	}

}