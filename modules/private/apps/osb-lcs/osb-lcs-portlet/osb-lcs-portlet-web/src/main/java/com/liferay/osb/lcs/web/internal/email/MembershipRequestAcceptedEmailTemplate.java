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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Marko Cikos
 * @author Matija Petanjek
 */
public class MembershipRequestAcceptedEmailTemplate extends BaseEmailTemplate {

	public MembershipRequestAcceptedEmailTemplate(EmailContext emailContext) {
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
	public Object[] getContextAttributes()
		throws PortalException, SystemException {

		List<Object> contextAttributes = getBaseContextAttributes();

		contextAttributes.add("[$MESSAGE_FIRST_LINE$]");
		contextAttributes.add(
			emailContext.translate(
				"your-membership-request-for-project-x-has-been-accepted",
				emailContext.getLCSProjectName()));
		contextAttributes.add("[$MESSAGE_SECOND_LINE$]");
		contextAttributes.add(
			emailContext.translate(
				"you-can-access-the-project-dashboard-by-clicking-on-the-" +
					"link-below"));
		contextAttributes.add("[$SUBJECT$]");
		contextAttributes.add(
			emailContext.translate("your-membership-request-was-accepted"));
		contextAttributes.add("[$URL_TEXT_FIRST_LINE$]");
		contextAttributes.add(StringPool.BLANK);

		String lcsProjectURL = NavigationUtil.getLCSProjectURL(
			emailContext.getLCSProjectId());

		contextAttributes.add("[$URL_SECOND_LINE$]");
		contextAttributes.add(lcsProjectURL);
		contextAttributes.add("[$URL_TEXT_SECOND_LINE$]");
		contextAttributes.add(lcsProjectURL);

		return contextAttributes.toArray();
	}

	@Override
	public String getPopPrefix() {
		return "lcs_membership_request_accepted_id";
	}

}