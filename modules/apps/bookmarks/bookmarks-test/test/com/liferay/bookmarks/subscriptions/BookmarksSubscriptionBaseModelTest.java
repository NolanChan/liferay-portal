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

package com.liferay.bookmarks.subscriptions;

import com.liferay.bookmarks.model.BookmarksEntry;
import com.liferay.bookmarks.model.BookmarksFolder;
import com.liferay.bookmarks.service.BookmarksEntryLocalServiceUtil;
import com.liferay.bookmarks.util.BookmarksTestUtil;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.test.Sync;
import com.liferay.portal.test.SynchronousMailExecutionTestListener;
import com.liferay.portal.test.listeners.MainServletExecutionTestListener;
import com.liferay.portal.test.runners.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.subscriptions.BaseSubscriptionBaseModelTestCase;
import com.liferay.portal.util.test.RandomTestUtil;
import com.liferay.portal.util.test.ResourcePermissionTestUtil;
import com.liferay.portal.util.test.ServiceContextTestUtil;

import org.junit.runner.RunWith;

/**
 * @author Roberto Díaz
 */
@ExecutionTestListeners(
	listeners = {
		MainServletExecutionTestListener.class,
		SynchronousMailExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Sync
public class BookmarksSubscriptionBaseModelTest
	extends BaseSubscriptionBaseModelTestCase {

	@Override
	protected long addBaseModel(long containerModelId) throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		BookmarksEntry entry = BookmarksTestUtil.addEntry(
			containerModelId, true, serviceContext);

		return entry.getEntryId();
	}

	@Override
	protected long addContainerModel(long containerModelId) throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		_folder = BookmarksTestUtil.addFolder(
			containerModelId, RandomTestUtil.randomString(), serviceContext);

		return _folder.getFolderId();
	}

	@Override
	protected void addSubscriptionBaseModel(long baseModelId) throws Exception {
		BookmarksEntryLocalServiceUtil.subscribeEntry(
			user.getUserId(), baseModelId);
	}

	@Override
	protected void deleteContainerModelViewPermission() throws Exception {
		ResourcePermissionTestUtil.unsetResourcePermission(
			_folder.getCompanyId(), BookmarksFolder.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL,
			String.valueOf(_folder.getFolderId()), PortletKeys.BLOGS,
			RoleConstants.SITE_MEMBER, ActionKeys.VIEW);
	}

	@Override
	protected void updateBaseModel(long baseModelId) throws Exception {
		BookmarksEntry entry = BookmarksEntryLocalServiceUtil.getEntry(
			baseModelId);

		BookmarksTestUtil.updateEntry(entry);
	}

	private BookmarksFolder _folder;

}