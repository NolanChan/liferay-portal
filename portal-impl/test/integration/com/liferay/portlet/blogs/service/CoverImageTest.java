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

package com.liferay.portlet.blogs.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TempFileEntryUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Repository;
import com.liferay.portal.model.User;
import com.liferay.portal.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.service.RepositoryLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.MainServletTestRule;
import com.liferay.portlet.blogs.constants.BlogsConstants;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;

import java.io.InputStream;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Roberto Díaz
 */
public class CoverImageTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), MainServletTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
		_user = TestPropsValues.getUser();
	}

	@Test(expected = NoSuchFileEntryException.class)
	public void testCoverImageDeletedWhenEmptyCoverImageSelector()
		throws Exception {

		BlogsEntry entry = addBlogsEntry("image.jpg");

		FileEntry coverImageFileEntry =
			PortletFileRepositoryUtil.getPortletFileEntry(
				entry.getCoverImageFileEntryId());

		ImageSelector coverImageSelector = new ImageSelector(
			0, StringPool.BLANK, StringPool.BLANK);

		updateBlogsEntry(entry.getEntryId(), coverImageSelector);

		Assert.assertEquals(0, entry.getCoverImageFileEntryId());

		PortletFileRepositoryUtil.getPortletFileEntry(
			coverImageFileEntry.getFileEntryId());
	}

	@Test
	public void testCoverImageNotChangedWhenNullCoverImageSelector()
		throws Exception {

		BlogsEntry entry = addBlogsEntry("image.jpg");

		FileEntry coverImageFileEntry =
			PortletFileRepositoryUtil.getPortletFileEntry(
				entry.getCoverImageFileEntryId());

		ImageSelector coverImageSelector = null;

		updateBlogsEntry(entry.getEntryId(), coverImageSelector);

		Assert.assertEquals(
			coverImageFileEntry.getFileEntryId(),
			entry.getCoverImageFileEntryId());

		Folder folder = BlogsEntryLocalServiceUtil.addAttachmentsFolder(
			_user.getUserId(), _group.getGroupId());

		PortletFileRepositoryUtil.getPortletFileEntry(
			_group.getGroupId(), folder.getFolderId(), "image.jpg");

		PortletFileRepositoryUtil.getPortletFileEntry(
			coverImageFileEntry.getFileEntryId());
	}

	@Test
	public void testCoverImageStored() throws Exception {
		BlogsEntry entry = addBlogsEntry("image.jpg");

		FileEntry coverImageFileEntry =
			PortletFileRepositoryUtil.getPortletFileEntry(
				entry.getCoverImageFileEntryId());

		Repository repository = RepositoryLocalServiceUtil.getRepository(
			coverImageFileEntry.getRepositoryId());

		Assert.assertEquals(BlogsConstants.SERVICE_NAME, repository.getName());

		Folder coverImageFolder = coverImageFileEntry.getFolder();

		Assert.assertNotEquals(
			BlogsConstants.SERVICE_NAME, coverImageFolder.getName());
		Assert.assertEquals("image.jpg", coverImageFileEntry.getTitle());
	}

	@Test
	public void testOriginalCoverImageNotDeletedWhenNullCoverImageSelector()
		throws Exception {

		Folder folder = BlogsEntryLocalServiceUtil.addAttachmentsFolder(
			_user.getUserId(), _group.getGroupId());

		int initialFolderFileEntriesCount =
			PortletFileRepositoryUtil.getPortletFileEntriesCount(
				_group.getGroupId(), folder.getFolderId());

		BlogsEntry entry = addBlogsEntry("image.jpg");

		ImageSelector coverImageSelector = null;

		updateBlogsEntry(entry.getEntryId(), coverImageSelector);

		int finalPortletFileEntriesCount =
			PortletFileRepositoryUtil.getPortletFileEntriesCount(
				_group.getGroupId(), folder.getFolderId());

		Assert.assertEquals(
			initialFolderFileEntriesCount + 1, finalPortletFileEntriesCount);

		PortletFileRepositoryUtil.getPortletFileEntry(
			_group.getGroupId(), folder.getFolderId(), "image.jpg");
	}

	@Test
	public void testOriginalCoverImageNotDeletedWhenEmptyCoverImageSelector()
		throws Exception {

		Folder folder = BlogsEntryLocalServiceUtil.addAttachmentsFolder(
			_user.getUserId(), _group.getGroupId());

		int initialFolderFileEntriesCount =
			PortletFileRepositoryUtil.getPortletFileEntriesCount(
				_group.getGroupId(), folder.getFolderId());

		BlogsEntry entry = addBlogsEntry("image.jpg");

		ImageSelector coverImageSelector = new ImageSelector(
			0, StringPool.BLANK, StringPool.BLANK);

		updateBlogsEntry(entry.getEntryId(), coverImageSelector);

		int finalPortletFileEntriesCount =
			PortletFileRepositoryUtil.getPortletFileEntriesCount(
				_group.getGroupId(), folder.getFolderId());

		Assert.assertEquals(
			initialFolderFileEntriesCount + 1, finalPortletFileEntriesCount);

		PortletFileRepositoryUtil.getPortletFileEntry(
			_group.getGroupId(), folder.getFolderId(), "image.jpg");
	}

	@Test
	public void testOriginalCoverImageStored() throws Exception {
		Folder folder = BlogsEntryLocalServiceUtil.addAttachmentsFolder(
			_user.getUserId(), _group.getGroupId());

		int initialFolderFileEntriesCount =
			PortletFileRepositoryUtil.getPortletFileEntriesCount(
				_group.getGroupId(), folder.getFolderId());

		addBlogsEntry("image.jpg");

		int finalPortletFileEntriesCount =
			PortletFileRepositoryUtil.getPortletFileEntriesCount(
				_group.getGroupId(), folder.getFolderId());

		Assert.assertEquals(
			initialFolderFileEntriesCount + 1, finalPortletFileEntriesCount);

		PortletFileRepositoryUtil.getPortletFileEntry(
			_group.getGroupId(), folder.getFolderId(), "image.jpg");
	}

	@Test(expected = NoSuchFileEntryException.class)
	public void testPreviousCoverImageDeletedWhenChangingCoverImage()
		throws Exception {

		BlogsEntry entry = addBlogsEntry("image.jpg");

		FileEntry initialCoverImageFileEntry =
			PortletFileRepositoryUtil.getPortletFileEntry(
				entry.getCoverImageFileEntryId());

		updateBlogsEntry(entry.getEntryId(), "image2.jpg");

		FileEntry finalCoverImageFileEntry =
			PortletFileRepositoryUtil.getPortletFileEntry(
				entry.getCoverImageFileEntryId());

		Assert.assertEquals("image2.jpg", finalCoverImageFileEntry.getTitle());

		PortletFileRepositoryUtil.getPortletFileEntry(
			initialCoverImageFileEntry.getFileEntryId());
	}

	@Test
	public void testPreviousOriginalCoverImageNotDeletedWhenChangingCoverImage()
		throws Exception {

		Folder folder = BlogsEntryLocalServiceUtil.addAttachmentsFolder(
			_user.getUserId(), _group.getGroupId());

		int initialFolderFileEntriesCount =
			PortletFileRepositoryUtil.getPortletFileEntriesCount(
				_group.getGroupId(), folder.getFolderId());

		BlogsEntry entry = addBlogsEntry("image.jpg");

		updateBlogsEntry(entry.getEntryId(), "image2.jpg");

		int finalPortletFileEntriesCount =
			PortletFileRepositoryUtil.getPortletFileEntriesCount(
				_group.getGroupId(), folder.getFolderId());

		Assert.assertEquals(
			initialFolderFileEntriesCount + 2, finalPortletFileEntriesCount);

		PortletFileRepositoryUtil.getPortletFileEntry(
			_group.getGroupId(), folder.getFolderId(), "image.jpg");
		PortletFileRepositoryUtil.getPortletFileEntry(
			_group.getGroupId(), folder.getFolderId(), "image2.jpg");
	}

	protected BlogsEntry addBlogsEntry(String coverImageTitle)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), _user.getUserId());

		FileEntry fileEntry = getTempFileEntry(
			_user.getUserId(), coverImageTitle, serviceContext);

		ImageSelector coverImageSelector = new ImageSelector(
			fileEntry.getFileEntryId(), StringPool.BLANK, _IMAGE_CROP_REGION);
		ImageSelector smallImageSelector = null;

		return BlogsEntryLocalServiceUtil.addEntry(
			_user.getUserId(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), new Date(), true, true,
			new String[0], StringPool.BLANK, coverImageSelector,
			smallImageSelector, serviceContext);
	}

	protected BlogsEntry updateBlogsEntry(long entryId, String coverImageTitle)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), _user.getUserId());

		FileEntry fileEntry = getTempFileEntry(
			_user.getUserId(), coverImageTitle, serviceContext);

		ImageSelector coverImageSelector = new ImageSelector(
			fileEntry.getFileEntryId(), StringPool.BLANK, _IMAGE_CROP_REGION);

		return updateBlogsEntry(entryId, coverImageSelector);
	}

	protected BlogsEntry updateBlogsEntry(
			long entryId, ImageSelector coverImageSelector)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				_group.getGroupId(), _user.getUserId());

		ImageSelector smallImageSelector = null;

		return BlogsEntryLocalServiceUtil.updateEntry(
			_user.getUserId(), entryId, RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), new Date(), true, true,
			new String[0], StringPool.BLANK, coverImageSelector,
			smallImageSelector, serviceContext);
	}

	protected FileEntry getTempFileEntry(
			long userId, String title, ServiceContext serviceContext)
		throws PortalException {

		ClassLoader classLoader = getClass().getClassLoader();

		InputStream inputStream = classLoader.getResourceAsStream(
			"com/liferay/portal/util/dependencies/test.jpg");

		return TempFileEntryUtil.addTempFileEntry(
			serviceContext.getScopeGroupId(), userId,
			BlogsEntry.class.getName(), title, inputStream,
			MimeTypesUtil.getContentType(title));
	}

	private static final String _IMAGE_CROP_REGION =
		"{\"height\":10,\"width\":10,\"x\":0,\"y\":0}";

	@DeleteAfterTestRun
	private Group _group;

	private User _user;

}