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
public class MonitoringUnavailableEmailTemplate extends BaseEmailTemplate {

	public MonitoringUnavailableEmailTemplate(EmailContext emailContext) {
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
				"monitoring-for-the-server-x-environment-x-project-x-is-" +
					"currently-unavailable",
				lcsClusterNodeName, lcsClusterEntryName, lcsProjectName));
		contextAttributes.add("[$MESSAGE_SECOND_LINE$]");
		contextAttributes.add(
			translate(
				emailContext,
				"please-make-sure-the-property-com-liferay-portal-servlet-" +
					"filters-monitoring-monitoringfilter-is-set-to-true-in-" +
					"your-portal-ext-properties-file",
				lcsClusterNodeName, lcsClusterEntryName, lcsProjectName));
		contextAttributes.add("[$SUBJECT$]");
		contextAttributes.add(
			translate(
				emailContext,
				"monitoring-is-unavailable-for-the-server-x-environment-x-" +
					"project-x",
				lcsClusterNodeName, lcsClusterEntryName, lcsProjectName));
		contextAttributes.add("[$URL_FIRST_LINE$]");
		contextAttributes.add(
			navigationAdvisor.getLCSClusterNodeURL(
				emailContext.getLCSClusterNodeId()));
		contextAttributes.add("[$URL_TEXT_FIRST_LINE$]");
		contextAttributes.add(
			translate(
				emailContext, "see-x-on-liferay-connected-services",
				lcsClusterNodeName));
		contextAttributes.add("[$URL_TEXT_SECOND_LINE$]");
		contextAttributes.add("");

		return contextAttributes.toArray();
	}

	@Override
	public String getPopPrefix() {
		return "monitoring_unavailable_id";
	}

}