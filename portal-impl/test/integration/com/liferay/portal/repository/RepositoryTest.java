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

package com.liferay.portal.repository;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.repository.LocalRepository;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Repository;
import com.liferay.portal.repository.liferayrepository.LiferayRepository;
import com.liferay.portal.service.RepositoryLocalServiceUtil;
import com.liferay.portal.service.RepositoryServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.test.DeleteAfterTestRun;
import com.liferay.portal.test.MainServletTestRule;
import com.liferay.portal.test.runners.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.util.test.GroupTestUtil;
import com.liferay.portal.util.test.RandomTestUtil;
import com.liferay.portal.util.test.TestPropsValues;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFolderServiceUtil;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Alexander Chow
 */
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class RepositoryTest {

	@ClassRule
	public static final MainServletTestRule mainServletTestRule =
		MainServletTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@Test
	public void testAddFileEntryInRepository() throws Exception {
		long classNameId = PortalUtil.getClassNameId(LiferayRepository.class);

		Repository repository = RepositoryLocalServiceUtil.addRepository(
			TestPropsValues.getUserId(), _group.getGroupId(), classNameId,
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			PortletKeys.DOCUMENT_LIBRARY, new UnicodeProperties(), true,
			new ServiceContext());

		long[] primaryKeys = populateRepository(repository.getRepositoryId());

		Assert.assertEquals(
			1,
			DLAppServiceUtil.getFoldersCount(
				repository.getRepositoryId(),
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID));
		Assert.assertEquals(
			1,
			DLAppServiceUtil.getFileEntriesAndFileShortcutsCount(
				repository.getRepositoryId(),
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
				WorkflowConstants.STATUS_ANY));
		Assert.assertEquals(
			1,
			DLAppServiceUtil.getFileEntriesAndFileShortcutsCount(
				repository.getRepositoryId(), primaryKeys[1],
				WorkflowConstants.STATUS_ANY));
	}

	@Test
	public void testDeleteAllRepositories() throws Exception {
		long[] repositoryIds = new long[2];

		long classNameId = PortalUtil.getClassNameId(LiferayRepository.class);

		Repository repository = RepositoryLocalServiceUtil.addRepository(
			TestPropsValues.getUserId(), _group.getGroupId(), classNameId,
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			PortletKeys.DOCUMENT_LIBRARY, new UnicodeProperties(), true,
			new ServiceContext());

		repositoryIds[0] = repository.getRepositoryId();

		DLFolder dlFolder = DLFolderServiceUtil.addFolder(
			_group.getGroupId(), _group.getGroupId(), false,
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			new ServiceContext());

		repository = RepositoryLocalServiceUtil.addRepository(
			TestPropsValues.getUserId(), _group.getGroupId(), classNameId,
			dlFolder.getFolderId(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), PortletKeys.DOCUMENT_LIBRARY,
			new UnicodeProperties(), true, new ServiceContext());

		repositoryIds[1] = repository.getRepositoryId();

		DLAppLocalServiceUtil.deleteAllRepositories(_group.getGroupId());

		for (long repositoryId : repositoryIds) {
			try {
				RepositoryServiceUtil.getLocalRepositoryImpl(repositoryId);

				Assert.fail(
					"Should not be able to access repository " + repositoryId);
			}
			catch (Exception e) {
			}
		}
	}

	@Test
	public void testFileEntriesAreDeletedWhenDeletingAllRepositories()
		throws Exception {

		long classNameId = PortalUtil.getClassNameId(LiferayRepository.class);

		Repository dlRepository = RepositoryLocalServiceUtil.addRepository(
			TestPropsValues.getUserId(), _group.getGroupId(), classNameId,
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			PortletKeys.DOCUMENT_LIBRARY, new UnicodeProperties(), true,
			new ServiceContext());

		long[] fileEntryIds = new long[2];

		long[] primaryKeys = populateRepository(dlRepository.getRepositoryId());

		fileEntryIds[0] = primaryKeys[0];
		fileEntryIds[1] = primaryKeys[2];

		DLAppLocalServiceUtil.deleteAllRepositories(_group.getGroupId());

		for (int i = 0; i < fileEntryIds.length; i++) {
			try {
				LocalRepository localRepository =
					RepositoryServiceUtil.getLocalRepositoryImpl(
						dlRepository.getRepositoryId());

				localRepository.getFileEntry(fileEntryIds[i]);

				Assert.fail(
					"Should not be able to get file entry " + fileEntryIds[i] +
						" from repository " + dlRepository.getRepositoryId());
			}
			catch (Exception e) {
			}
		}
	}

	@Test
	public void testGetMountFoldersCountWithHiddenRepository()
		throws Exception {

		long classNameId = PortalUtil.getClassNameId(LiferayRepository.class);

		RepositoryLocalServiceUtil.addRepository(
			TestPropsValues.getUserId(), _group.getGroupId(), classNameId,
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			PortletKeys.DOCUMENT_LIBRARY, new UnicodeProperties(), true,
			new ServiceContext());

		Assert.assertEquals(
			0,
			DLFolderServiceUtil.getMountFoldersCount(
				_group.getGroupId(),
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID));
	}

	@Test
	public void testGetMountFoldersCountWithNotHiddenRepository()
		throws Exception {

		long classNameId = PortalUtil.getClassNameId(LiferayRepository.class);

		RepositoryLocalServiceUtil.addRepository(
			TestPropsValues.getUserId(), _group.getGroupId(), classNameId,
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			PortletKeys.DOCUMENT_LIBRARY, new UnicodeProperties(), false,
			new ServiceContext());

		Assert.assertEquals(
			1,
			DLFolderServiceUtil.getMountFoldersCount(
				_group.getGroupId(),
				DLFolderConstants.DEFAULT_PARENT_FOLDER_ID));
	}

	@Test
	public void
			testRepositoryFileEntriesAreDeletedWhenDeletingLiferayRepository()
		throws Exception {

		long[] fileEntryIds = new long[4];

		long[] primaryKeys = populateRepository(_group.getGroupId());

		fileEntryIds[0] = primaryKeys[0];
		fileEntryIds[1] = primaryKeys[2];

		Repository repository = RepositoryLocalServiceUtil.addRepository(
			TestPropsValues.getUserId(), _group.getGroupId(),
			PortalUtil.getClassNameId(LiferayRepository.class),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			PortletKeys.DOCUMENT_LIBRARY, new UnicodeProperties(), true,
			new ServiceContext());

		primaryKeys = populateRepository(repository.getRepositoryId());

		fileEntryIds[2] = primaryKeys[0];
		fileEntryIds[3] = primaryKeys[2];

		DLAppLocalServiceUtil.deleteAll(_group.getGroupId());

		try {
			LocalRepository localRepository =
				RepositoryServiceUtil.getLocalRepositoryImpl(
					_group.getGroupId());

			localRepository.getFileEntry(fileEntryIds[0]);
			localRepository.getFileEntry(fileEntryIds[1]);

			Assert.fail(
				"Should not be able to get file entry from repository " +
					_group.getGroupId());
		}
		catch (Exception e) {
		}

		try {
			LocalRepository localRepository =
				RepositoryServiceUtil.getLocalRepositoryImpl(
					repository.getRepositoryId());

			localRepository.getFileEntry(fileEntryIds[2]);
			localRepository.getFileEntry(fileEntryIds[3]);
		}
		catch (Exception e) {
			Assert.fail(
				"Should not be able to get file entry from repository " +
					repository.getRepositoryId());
		}
	}

	@Test
	public void testRepositoryFileEntriesAreDeletedWhenDeletingRepository()
		throws Exception {

		long classNameId = PortalUtil.getClassNameId(LiferayRepository.class);

		Repository repository1 = RepositoryLocalServiceUtil.addRepository(
			TestPropsValues.getUserId(), _group.getGroupId(), classNameId,
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			PortletKeys.DOCUMENT_LIBRARY, new UnicodeProperties(), true,
			new ServiceContext());

		long[] fileEntryIds = new long[4];

		long[] primaryKeys = populateRepository(repository1.getRepositoryId());

		fileEntryIds[0] = primaryKeys[0];
		fileEntryIds[1] = primaryKeys[2];

		Repository repository2 = RepositoryLocalServiceUtil.addRepository(
			TestPropsValues.getUserId(), _group.getGroupId(), classNameId,
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			PortletKeys.DOCUMENT_LIBRARY, new UnicodeProperties(), true,
			new ServiceContext());

		primaryKeys = populateRepository(repository2.getRepositoryId());

		fileEntryIds[2] = primaryKeys[0];
		fileEntryIds[3] = primaryKeys[2];

		DLAppLocalServiceUtil.deleteAll(repository1.getRepositoryId());

		try {
			LocalRepository localRepository =
				RepositoryServiceUtil.getLocalRepositoryImpl(
					repository2.getRepositoryId());

			localRepository.getFileEntry(fileEntryIds[0]);
			localRepository.getFileEntry(fileEntryIds[1]);

			Assert.fail(
				"Should be able to get file entry from repository " +
					repository2.getRepositoryId());
		}
		catch (Exception e) {
		}

		try {
			LocalRepository localRepository =
				RepositoryServiceUtil.getLocalRepositoryImpl(
					repository2.getRepositoryId());

			localRepository.getFileEntry(fileEntryIds[2]);
			localRepository.getFileEntry(fileEntryIds[3]);
		}
		catch (Exception e) {
			Assert.fail(
				"Should not be able to get file entry from repository " +
					repository2.getRepositoryId());
		}
	}

	protected long[] populateRepository(long repositoryId) throws Exception {
		InputStream inputStream = new UnsyncByteArrayInputStream(
			_TEST_CONTENT.getBytes());

		LocalRepository localRepository =
			RepositoryServiceUtil.getLocalRepositoryImpl(repositoryId);

		FileEntry fileEntry = localRepository.addFileEntry(
			TestPropsValues.getUserId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(), ContentTypes.TEXT_PLAIN,
			RandomTestUtil.randomString(), StringPool.BLANK, StringPool.BLANK,
			inputStream, _TEST_CONTENT.length(), new ServiceContext());

		Folder folder = localRepository.addFolder(
			TestPropsValues.getUserId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			new ServiceContext());

		FileEntry folderFileEntry = localRepository.addFileEntry(
			TestPropsValues.getUserId(), folder.getFolderId(),
			RandomTestUtil.randomString(), ContentTypes.TEXT_PLAIN,
			RandomTestUtil.randomString(), StringPool.BLANK, StringPool.BLANK,
			inputStream, _TEST_CONTENT.length(), new ServiceContext());

		return new long[] {
			fileEntry.getFileEntryId(), folder.getFolderId(),
			folderFileEntry.getFileEntryId()
		};
	}

	private static final String _TEST_CONTENT =
		"LIFERAY\nEnterprise. Open Source. For Life.";

	@DeleteAfterTestRun
	private Group _group;

}