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

package com.liferay.portlet.journal.subscriptions;

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.test.MainServletTestRule;
import com.liferay.portal.test.Sync;
import com.liferay.portal.test.SynchronousMailExecutionTestListener;
import com.liferay.portal.test.runners.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.util.subscriptions.BaseSubscriptionRootContainerModelTestCase;
import com.liferay.portal.util.test.RandomTestUtil;
import com.liferay.portal.util.test.UserTestUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalFolder;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalFolderLocalServiceUtil;
import com.liferay.portlet.journal.util.test.JournalTestUtil;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.runner.RunWith;

/**
 * @author Zsolt Berentey
 * @author Roberto Díaz
 */
@ExecutionTestListeners(
	listeners = {
		SynchronousMailExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Sync
public class JournalSubscriptionRootContainerModelTest
	extends BaseSubscriptionRootContainerModelTestCase {

	@ClassRule
	public static final MainServletTestRule mainServletTestRule =
		new MainServletTestRule();

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		user = UserTestUtil.addOmniAdminUser();
	}

	@Override
	protected long addBaseModel(long containerModelId) throws Exception {
		JournalArticle article = JournalTestUtil.addArticle(
			group.getGroupId(), containerModelId);

		return article.getResourcePrimKey();
	}

	@Override
	protected long addContainerModel(long containerModelId) throws Exception {
		JournalFolder folder = JournalTestUtil.addFolder(
			group.getGroupId(), containerModelId,
			RandomTestUtil.randomString());

		return folder.getFolderId();
	}

	@Override
	protected void addSubscriptionContainerModel(long containerModelId)
		throws Exception {

		JournalFolderLocalServiceUtil.subscribe(
			user.getUserId(), group.getGroupId(), containerModelId);
	}

	@Override
	protected void updateBaseModel(long baseModelId) throws Exception {
		JournalArticle article =
			JournalArticleLocalServiceUtil.getLatestArticle(baseModelId);

		JournalTestUtil.updateArticleWithWorkflow(article, true);
	}

}