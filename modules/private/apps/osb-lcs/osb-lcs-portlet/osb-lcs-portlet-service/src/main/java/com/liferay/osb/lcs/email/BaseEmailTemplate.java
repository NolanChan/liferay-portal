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

import com.liferay.osb.lcs.advisor.NavigationAdvisor;
import com.liferay.osb.lcs.configuration.OSBLCSConfiguration;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.ContentUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Matija Petanjek
 */
public abstract class BaseEmailTemplate implements EmailTemplate {

	@Override
	public Map<Locale, String> getSubjectMap() {
		return getLocalizationMap(
			"com/liferay/osb/lcs/email/dependencies/email_subject.tmpl",
			"emailSubject");
	}

	@Reference(bind = "-", unbind = "-")
	public void setNavigationAdvisor(NavigationAdvisor navigationAdvisor) {
		_navigationAdvisor = navigationAdvisor;
	}

	protected BaseEmailTemplate(EmailContext emailContext) {
		this.emailContext = emailContext;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_osbLCSConfiguration = ConfigurableUtil.createConfigurable(
			OSBLCSConfiguration.class, properties);
	}

	@Deactivate
	protected void deactivate() {
		_osbLCSConfiguration = null;
	}

	protected List<Object> getBaseContextAttributes() throws PortalException {
		List<Object> contextAttributes = new ArrayList<>();

		User user = emailContext.getUser();

		contextAttributes.add("[$CHANGE_NOTIFICATIONS_TEXT$]");
		contextAttributes.add(
			translate(emailContext, "change-notification-preferences"));
		contextAttributes.add("[$CONTACT_SUPPORT_TEXT$]");
		contextAttributes.add(translate(emailContext, "contact-support"));
		contextAttributes.add("[$DEAR_X$]");
		contextAttributes.add(
			translate(emailContext, "dear-x", user.getFullName()));
		contextAttributes.add("[$FEEDBACK_EMAIL_ADDRESS$]");
		contextAttributes.add(
			_osbLCSConfiguration.osbLcsPortletMembersFeedbackEmailAddress());
		contextAttributes.add("[$LCS_TEAM$]");
		contextAttributes.add(
			translate(emailContext, "the-liferay-connected-services-team"));
		contextAttributes.add("[$NOTIFICATIONS_URL$]");
		contextAttributes.add(_navigationAdvisor.getLCSNotificationsURL());
		contextAttributes.add("[$THANK_YOU_TEXT$]");
		contextAttributes.add(translate(emailContext, "thank-you"));

		return contextAttributes;
	}

	protected Map<Locale, String> getLocalizationMap(
		String templatePath, String parameter) {

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

	protected String translate(
		EmailContext emailContext, String pattern, Object... arguments) {

		return LanguageUtil.format(
			emailContext.getLocale(), pattern, arguments);
	}

	protected static volatile OSBLCSConfiguration _osbLCSConfiguration;

	protected NavigationAdvisor _navigationAdvisor;
	protected EmailContext emailContext;

}