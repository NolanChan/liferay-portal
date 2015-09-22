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

package com.liferay.portlet.blogs.attachments;

import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.MainServletTestRule;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;

import java.util.Date;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;

/**
 * @author Roberto Díaz
 */
public class BlogsCoverImageTest extends BaseBlogsImageTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), MainServletTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		group = GroupTestUtil.addGroup();
		user = TestPropsValues.getUser();
	}

	@Override
	protected BlogsEntry addBlogsEntry(String imageTitle) throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				group.getGroupId(), user.getUserId());

		FileEntry fileEntry = getTempFileEntry(
			user.getUserId(), imageTitle, serviceContext);

		ImageSelector imageSelector = new ImageSelector(
			fileEntry.getFileEntryId(), StringPool.BLANK, _IMAGE_CROP_REGION);

		return BlogsEntryLocalServiceUtil.addEntry(
			user.getUserId(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), new Date(), true, true,
			new String[0], StringPool.BLANK, imageSelector, null,
			serviceContext);
	}

	@Override
	protected long getImageFileEntry(BlogsEntry blogsEntry) {
		return blogsEntry.getCoverImageFileEntryId();
	}

	@Override
	protected BlogsEntry updateBlogsEntry(
			long blogsEntryId, ImageSelector imageSelector)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				group.getGroupId(), user.getUserId());

		return BlogsEntryLocalServiceUtil.updateEntry(
			user.getUserId(), blogsEntryId, RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), new Date(), true, true,
			new String[0], StringPool.BLANK, imageSelector, null,
			serviceContext);
	}

	@Override
	protected BlogsEntry updateBlogsEntry(
			long blogsEntryId, String coverImageTitle)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				group.getGroupId(), user.getUserId());

		FileEntry fileEntry = getTempFileEntry(
			user.getUserId(), coverImageTitle, serviceContext);

		ImageSelector imageSelector = new ImageSelector(
			fileEntry.getFileEntryId(), StringPool.BLANK, _IMAGE_CROP_REGION);

		return updateBlogsEntry(blogsEntryId, imageSelector);
	}

	private static final String _IMAGE_CROP_REGION =
		"{\"height\": 10, \"width\": 10, \"x\": 0, \"y\": 0}";

}