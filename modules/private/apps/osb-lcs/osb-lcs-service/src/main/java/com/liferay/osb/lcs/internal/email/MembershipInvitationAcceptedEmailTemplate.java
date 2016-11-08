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

package com.liferay.osb.lcs.internal.email;

import com.liferay.osb.lcs.email.EmailContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringPool;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Matija Petanjek
 */
public class MembershipInvitationAcceptedEmailTemplate
	extends BaseEmailTemplate {

	public MembershipInvitationAcceptedEmailTemplate(
		EmailContext emailContext) {

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

		contextAttributes.add("[$MESSAGE_FIRST_LINE$]");

		User user = emailContext.getUser();

		contextAttributes.add(
			translate(
				emailContext, "x-x-joined-the-project-x", user.getFullName(),
				user.getEmailAddress(), emailContext.getLCSProjectName()));

		contextAttributes.add("[$MESSAGE_SECOND_LINE$]");
		contextAttributes.add(
			translate(
				emailContext,
				"you-can-see-and-manage-your-project-members-by-clicking-on-" +
					"the-link-below"));
		contextAttributes.add("[$SUBJECT$]");
		contextAttributes.add(
			translate(emailContext, "membership-invitation-accepted"));
		contextAttributes.add("[$URL_TEXT_FIRST_LINE$]");
		contextAttributes.add(StringPool.BLANK);
		contextAttributes.add("[$URL_SECOND_LINE$]");

		String lcsProjectURL = navigationAdvisor.getLCSProjectUsersURL(
			emailContext.getLCSProjectId());

		contextAttributes.add(lcsProjectURL);

		contextAttributes.add("[$URL_TEXT_SECOND_LINE$]");
		contextAttributes.add(lcsProjectURL);

		return contextAttributes.toArray();
	}

	@Override
	public String getPopPrefix() {
		return "lcs_membership_invitation_accepted_id";
	}

}