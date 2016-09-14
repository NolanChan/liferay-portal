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
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.util.ContentUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Matija Petanjek
 */
public abstract class BaseEmailTemplate implements EmailTemplate {

	@Override
	public Map<Locale, String> getSubjectMap(
		PortletPreferences portletPreferences) {

		return getLocalizationMap(
			"com/liferay/osb/lcs/email/dependencies/email_subject.tmpl",
			"emailSubject", portletPreferences);
	}

	protected BaseEmailTemplate(EmailContext emailContext) {
		this.emailContext = emailContext;
	}

	protected List<Object> getBaseContextAttributes() throws PortalException {
		List<Object> contextAttributes = new ArrayList<>();

		User user = emailContext.getUser();

		contextAttributes.add("[$CHANGE_NOTIFICATIONS_TEXT$]");
		contextAttributes.add(
			emailContext.translate("change-notification-preferences"));
		contextAttributes.add("[$CONTACT_SUPPORT_TEXT$]");
		contextAttributes.add(emailContext.translate("contact-support"));
		contextAttributes.add("[$DEAR_X$]");
		contextAttributes.add(
			emailContext.translate("dear-x", user.getFullName()));
		contextAttributes.add("[$FEEDBACK_EMAIL_ADDRESS$]");
		contextAttributes.add(
			PortletPropsValues.OSB_LCS_PORTLET_MEMBERS_FEEDBACK_EMAIL_ADDRESS);
		contextAttributes.add("[$LCS_TEAM$]");
		contextAttributes.add(
			emailContext.translate("the-liferay-connected-services-team"));
		contextAttributes.add("[$NOTIFICATIONS_URL$]");
		contextAttributes.add(NavigationUtil.getLCSNotificationsURL());
		contextAttributes.add("[$THANK_YOU_TEXT$]");
		contextAttributes.add(emailContext.translate("thank-you"));

		return contextAttributes;
	}

	protected Map<Locale, String> getLocalizationMap(
		String templatePath, String parameter,
		PortletPreferences portletPreferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			portletPreferences, parameter);

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(defaultLocale, ContentUtil.get(templatePath));

		return map;
	}

	protected EmailContext emailContext;

}