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

package com.liferay.portlet.bookmarks.service;

import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.settings.SettingsFactoryUtil;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.test.Sync;
import com.liferay.portal.test.SynchronousMailExecutionTestListener;
import com.liferay.portal.util.BaseSubscriptionTestCase;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.bookmarks.model.BookmarksEntry;
import com.liferay.portlet.bookmarks.model.BookmarksFolder;
import com.liferay.portlet.bookmarks.util.BookmarksConstants;
import com.liferay.test.portal.service.ServiceTestUtil;
import com.liferay.test.portal.util.TestPropsValues;
import com.liferay.test.portlet.bookmarks.util.BookmarksTestUtil;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Sergio González
 * @author Roberto Díaz
 */
@ExecutionTestListeners(
	listeners = {
		MainServletExecutionTestListener.class,
		SynchronousMailExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Sync
public class BookmarksSubscriptionTest extends BaseSubscriptionTestCase {

	@Ignore
	@Override
	@Test
	public void testSubscriptionClassType() {
	}

	@Ignore
	@Override
	@Test
	public void testSubscriptionDefaultClassType() {
	}

	@Override
	protected long addBaseModel(long containerModelId) throws Exception {
		ServiceContext serviceContext = ServiceTestUtil.getServiceContext(
			group.getGroupId());

		BookmarksEntry entry = BookmarksTestUtil.addEntry(
			containerModelId, true, serviceContext);

		return entry.getEntryId();
	}

	@Override
	protected long addContainerModel(long containerModelId) throws Exception {
		ServiceContext serviceContext = ServiceTestUtil.getServiceContext(
			group.getGroupId());

		BookmarksFolder folder = BookmarksTestUtil.addFolder(
			containerModelId, ServiceTestUtil.randomString(), serviceContext);

		return folder.getFolderId();
	}

	@Override
	protected void addSubscriptionBaseModel(long baseModelId) throws Exception {
		BookmarksEntryLocalServiceUtil.subscribeEntry(
			TestPropsValues.getUserId(), baseModelId);
	}

	@Override
	protected void addSubscriptionContainerModel(long containerModelId)
		throws Exception {

		BookmarksFolderLocalServiceUtil.subscribeFolder(
			TestPropsValues.getUserId(), group.getGroupId(), containerModelId);
	}

	@Override
	protected String getPortletId() {
		return PortletKeys.BOOKMARKS;
	}

	@Override
	protected String getSubscriptionBodyPreferenceName() throws Exception {
		return "emailEntryAddedBody";
	}

	@Override
	protected void setAddBaseModelSubscriptionBodyPreferences()
		throws Exception {

		Settings settings = SettingsFactoryUtil.getGroupServiceSettings(
			group.getGroupId(), BookmarksConstants.SERVICE_NAME);

		String germanSubscriptionBodyPreferencesKey =
			LocalizationUtil.getPreferencesKey(
				getSubscriptionBodyPreferenceName(),
				LocaleUtil.toLanguageId(LocaleUtil.GERMANY));

		settings.setValue(germanSubscriptionBodyPreferencesKey, GERMAN_BODY);

		String spanishSubscriptionBodyPreferencesKey =
			LocalizationUtil.getPreferencesKey(
				getSubscriptionBodyPreferenceName(),
				LocaleUtil.toLanguageId(LocaleUtil.SPAIN));

		settings.setValue(spanishSubscriptionBodyPreferencesKey, SPANISH_BODY);

		settings.store();
	}

	@Override
	protected long updateEntry(long baseModelId) throws Exception {
		BookmarksEntry entry = BookmarksEntryLocalServiceUtil.getEntry(
			baseModelId);

		entry = BookmarksTestUtil.updateEntry(entry);

		return entry.getEntryId();
	}

}