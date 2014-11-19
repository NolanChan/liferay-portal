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

package com.liferay.portlet.asset.service;

import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.test.DeleteAfterTestRun;
import com.liferay.portal.test.Sync;
import com.liferay.portal.test.listeners.MainServletExecutionTestListener;
import com.liferay.portal.test.runners.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.util.test.GroupTestUtil;
import com.liferay.portal.util.test.ServiceContextTestUtil;
import com.liferay.portal.util.test.TestPropsValues;
import com.liferay.portlet.asset.model.AssetTag;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.util.test.BlogsTestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Michael C. Han
 */
@ExecutionTestListeners(
	listeners = {
		MainServletExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Sync
public class AssetTagLocalServiceTest {

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_blogsIndexer = IndexerRegistryUtil.getIndexer(BlogsEntry.class);
	}

	@After
	public void tearDown() throws Exception {
		IndexerRegistryUtil.register(BlogsEntry.class.getName(), _blogsIndexer);
	}

	@Test
	public void testDeleteTag() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), TestPropsValues.getUserId());

		AssetTag assetTag = AssetTagLocalServiceUtil.addTag(
			TestPropsValues.getUserId(), "Tag", null, serviceContext);

		serviceContext.setAssetTagNames(new String[] {assetTag.getName()});

		BlogsEntry blogsEntry = BlogsTestUtil.addEntry(
			TestPropsValues.getUserId(), "Test", true, serviceContext);

		AssetTestIndexer assetTestIndexer = new AssetTestIndexer();

		assetTestIndexer.setExpectedValues(
			BlogsEntry.class.getName(), blogsEntry.getEntryId());

		IndexerRegistryUtil.register(
			BlogsEntry.class.getName(), assetTestIndexer);

		AssetTagLocalServiceUtil.deleteTag(assetTag);
	}

	private Indexer _blogsIndexer;

	@DeleteAfterTestRun
	private Group _group;

}