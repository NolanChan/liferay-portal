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
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Matija Petanjek
 */
public class MembershipInvitationEmailTemplate extends BaseEmailTemplate {

	public MembershipInvitationEmailTemplate(EmailContext emailContext) {
		super(emailContext);
	}

	@Override
	public Map<Locale, String> getBodyMap() {
		return getLocalizationMap(
			"com/liferay/osb/lcs/email/dependencies" +
				"/email_notification_type_4_body.tmpl",
			"emailNotificationType4Body");
	}

	@Override
	public Object[] getContextAttributes() throws PortalException {
		List<Object> contextAttributes = getBaseContextAttributes();

		String invitationMessage = emailContext.getCustomMessage();
		User user = emailContext.getUser();

		if (Validator.isNotNull(invitationMessage)) {
			invitationMessage = StringUtil.quote(
				invitationMessage, StringPool.QUOTE);
		}

		contextAttributes.add("[$CUSTOM_MESSAGE$]");
		contextAttributes.add(invitationMessage);
		contextAttributes.add("[$DEAR_LIFERAY_USER$]");
		contextAttributes.add(translate(emailContext, "dear-liferay-user"));
		contextAttributes.add("[$MESSAGE_FIRST_LINE$]");
		contextAttributes.add(
			translate(emailContext,
				"x-invited-you-to-be-part-of-the-liferay-connected-services-" +
					"project-x",
				user.getFullName(), emailContext.getLCSProjectName()));
		contextAttributes.add("[$MESSAGE_SECOND_LINE$]");
		contextAttributes.add(
			translate(emailContext,
				"use-the-link-below-to-accept-this-invitation-and-access-" +
					"the-liferay-connected-services-site"));
		contextAttributes.add("[$SUBJECT$]");
		contextAttributes.add(translate(emailContext, "membership-invitation"));
		contextAttributes.add("[$URL_TEXT_FIRST_LINE$]");
		contextAttributes.add(StringPool.BLANK);

		StringBundler sb = new StringBundler(5);

		sb.append(NavigationUtil.getLCSPortalURL());
		sb.append(NavigationUtil.CHECK_LCS_INVITATION_URL);
		sb.append(StringPool.QUESTION);
		sb.append("lcsProjectId=");
		sb.append(emailContext.getLCSProjectId());

		String dashboardLayoutURL = sb.toString();

		contextAttributes.add("[$URL_SECOND_LINE$]");
		contextAttributes.add(dashboardLayoutURL);
		contextAttributes.add("[$URL_TEXT_SECOND_LINE$]");
		contextAttributes.add(dashboardLayoutURL);

		return contextAttributes.toArray();
	}

	@Override
	public String getPopPrefix() {
		return "lcs_membership_request_invitation_id";
	}

}