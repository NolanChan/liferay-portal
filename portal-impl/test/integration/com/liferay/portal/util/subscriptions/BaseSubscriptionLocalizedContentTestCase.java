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

package com.liferay.portal.util.subscriptions;

import com.dumbster.smtp.MailMessage;

import com.liferay.portal.kernel.settings.ModifiableSettings;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.settings.SettingsFactoryUtil;
import com.liferay.portal.kernel.util.LocaleThreadLocal;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Layout;
import com.liferay.portal.util.test.LayoutTestUtil;
import com.liferay.portal.util.test.MailServiceTestUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Roberto Díaz
 */
public abstract class BaseSubscriptionLocalizedContentTestCase
	extends BaseSubscriptionTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		defaultLocale = LocaleThreadLocal.getDefaultLocale();
		layout = LayoutTestUtil.addLayout(group);
	}

	@After
	public void tearDown() throws Exception {
		LocaleThreadLocal.setDefaultLocale(defaultLocale);
	}

	@Test
	public void testSubscriptionLocalizedContent() throws Exception {
		localizedContents.put(LocaleUtil.GERMANY, GERMAN_BODY);
		localizedContents.put(LocaleUtil.SPAIN, SPANISH_BODY);

		setAddBaseModelSubscriptionBodyPreferences();

		addSubscriptionContainerModel(PARENT_CONTAINER_MODEL_ID_DEFAULT);

		LocaleThreadLocal.setDefaultLocale(LocaleUtil.GERMANY);

		addBaseModel(PARENT_CONTAINER_MODEL_ID_DEFAULT);

		List<MailMessage> messages = MailServiceTestUtil.getMailMessages(
			"Body", GERMAN_BODY);

		Assert.assertEquals(1, messages.size());

		LocaleThreadLocal.setDefaultLocale(LocaleUtil.SPAIN);

		addBaseModel(PARENT_CONTAINER_MODEL_ID_DEFAULT);

		messages = MailServiceTestUtil.getMailMessages("Body", SPANISH_BODY);

		Assert.assertEquals(1, messages.size());
	}

	protected abstract void addSubscriptionContainerModel(long containerModelId)
		throws Exception;

	protected abstract String getPortletId();

	protected String getServiceName() {
		return StringPool.BLANK;
	}

	protected abstract String getSubscriptionBodyPreferenceName()
		throws Exception;

	protected void setAddBaseModelSubscriptionBodyPreferences()
		throws Exception {

		Settings settings = SettingsFactoryUtil.getGroupServiceSettings(
			group.getGroupId(), getServiceName());

		ModifiableSettings modifiableSettings =
			settings.getModifiableSettings();

		for (Map.Entry<Locale, String> localizedContent :
				localizedContents.entrySet()) {

			Locale locale = localizedContent.getKey();
			String content = localizedContent.getValue();

			String subscriptionBodyPreferencesKey =
				LocalizationUtil.getPreferencesKey(
					getSubscriptionBodyPreferenceName(),
					LocaleUtil.toLanguageId(locale));

			modifiableSettings.setValue(
				subscriptionBodyPreferencesKey, content);
		}

		modifiableSettings.store();
	}

	protected static final String GERMAN_BODY = "Hallo Welt";

	protected static final String SPANISH_BODY = "Hola Mundo";

	protected Locale defaultLocale;
	protected Layout layout;
	protected Map<Locale, String> localizedContents = new HashMap<>();

}