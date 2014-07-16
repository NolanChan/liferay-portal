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

package com.liferay.portlet.bookmarks.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.template.TemplateException;
import com.liferay.portal.kernel.template.TemplateManagerUtil;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.test.LiferayPersistenceIntegrationJUnitTestRunner;
import com.liferay.portal.test.TransactionalTestRule;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.test.RandomTestUtil;

import com.liferay.portlet.bookmarks.NoSuchFolderException;
import com.liferay.portlet.bookmarks.model.BookmarksFolder;
import com.liferay.portlet.bookmarks.model.impl.BookmarksFolderModelImpl;
import com.liferay.portlet.bookmarks.service.BookmarksFolderLocalServiceUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import org.junit.runner.RunWith;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
@RunWith(LiferayPersistenceIntegrationJUnitTestRunner.class)
public class BookmarksFolderPersistenceTest {
	@BeforeClass
	public static void setupClass() throws TemplateException {
		TemplateManagerUtil.init();

		PropsValues.SPRING_HIBERNATE_SESSION_DELEGATED = false;
	}

	public static void tearDownClass() {
		PropsValues.SPRING_HIBERNATE_SESSION_DELEGATED = true;
	}

	@ClassRule
	public static TransactionalTestRule transactionalTestRule = new TransactionalTestRule();

	@Before
	public void setUp() {
		_modelListeners = _persistence.getListeners();

		for (ModelListener<BookmarksFolder> modelListener : _modelListeners) {
			_persistence.unregisterListener(modelListener);
		}
	}

	@After
	public void tearDown() throws Exception {
		Iterator<BookmarksFolder> iterator = _models.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}

		for (ModelListener<BookmarksFolder> modelListener : _modelListeners) {
			_persistence.registerListener(modelListener);
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		BookmarksFolder bookmarksFolder = _persistence.create(pk);

		Assert.assertNotNull(bookmarksFolder);

		Assert.assertEquals(bookmarksFolder.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		BookmarksFolder newBookmarksFolder = addBookmarksFolder();

		_persistence.remove(newBookmarksFolder);

		BookmarksFolder existingBookmarksFolder = _persistence.fetchByPrimaryKey(newBookmarksFolder.getPrimaryKey());

		Assert.assertNull(existingBookmarksFolder);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addBookmarksFolder();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		BookmarksFolder newBookmarksFolder = _persistence.create(pk);

		newBookmarksFolder.setUuid(RandomTestUtil.randomString());

		newBookmarksFolder.setGroupId(RandomTestUtil.nextLong());

		newBookmarksFolder.setCompanyId(RandomTestUtil.nextLong());

		newBookmarksFolder.setUserId(RandomTestUtil.nextLong());

		newBookmarksFolder.setUserName(RandomTestUtil.randomString());

		newBookmarksFolder.setCreateDate(RandomTestUtil.nextDate());

		newBookmarksFolder.setModifiedDate(RandomTestUtil.nextDate());

		newBookmarksFolder.setResourceBlockId(RandomTestUtil.nextLong());

		newBookmarksFolder.setParentFolderId(RandomTestUtil.nextLong());

		newBookmarksFolder.setTreePath(RandomTestUtil.randomString());

		newBookmarksFolder.setName(RandomTestUtil.randomString());

		newBookmarksFolder.setDescription(RandomTestUtil.randomString());

		newBookmarksFolder.setStatus(RandomTestUtil.nextInt());

		newBookmarksFolder.setStatusByUserId(RandomTestUtil.nextLong());

		newBookmarksFolder.setStatusByUserName(RandomTestUtil.randomString());

		newBookmarksFolder.setStatusDate(RandomTestUtil.nextDate());

		_models.add(_persistence.update(newBookmarksFolder));

		BookmarksFolder existingBookmarksFolder = _persistence.findByPrimaryKey(newBookmarksFolder.getPrimaryKey());

		Assert.assertEquals(existingBookmarksFolder.getUuid(),
			newBookmarksFolder.getUuid());
		Assert.assertEquals(existingBookmarksFolder.getFolderId(),
			newBookmarksFolder.getFolderId());
		Assert.assertEquals(existingBookmarksFolder.getGroupId(),
			newBookmarksFolder.getGroupId());
		Assert.assertEquals(existingBookmarksFolder.getCompanyId(),
			newBookmarksFolder.getCompanyId());
		Assert.assertEquals(existingBookmarksFolder.getUserId(),
			newBookmarksFolder.getUserId());
		Assert.assertEquals(existingBookmarksFolder.getUserName(),
			newBookmarksFolder.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingBookmarksFolder.getCreateDate()),
			Time.getShortTimestamp(newBookmarksFolder.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingBookmarksFolder.getModifiedDate()),
			Time.getShortTimestamp(newBookmarksFolder.getModifiedDate()));
		Assert.assertEquals(existingBookmarksFolder.getResourceBlockId(),
			newBookmarksFolder.getResourceBlockId());
		Assert.assertEquals(existingBookmarksFolder.getParentFolderId(),
			newBookmarksFolder.getParentFolderId());
		Assert.assertEquals(existingBookmarksFolder.getTreePath(),
			newBookmarksFolder.getTreePath());
		Assert.assertEquals(existingBookmarksFolder.getName(),
			newBookmarksFolder.getName());
		Assert.assertEquals(existingBookmarksFolder.getDescription(),
			newBookmarksFolder.getDescription());
		Assert.assertEquals(existingBookmarksFolder.getStatus(),
			newBookmarksFolder.getStatus());
		Assert.assertEquals(existingBookmarksFolder.getStatusByUserId(),
			newBookmarksFolder.getStatusByUserId());
		Assert.assertEquals(existingBookmarksFolder.getStatusByUserName(),
			newBookmarksFolder.getStatusByUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingBookmarksFolder.getStatusDate()),
			Time.getShortTimestamp(newBookmarksFolder.getStatusDate()));
	}

	@Test
	public void testCountByResourceBlockId() {
		try {
			_persistence.countByResourceBlockId(RandomTestUtil.nextLong());

			_persistence.countByResourceBlockId(0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByUuid() {
		try {
			_persistence.countByUuid(StringPool.BLANK);

			_persistence.countByUuid(StringPool.NULL);

			_persistence.countByUuid((String)null);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByUUID_G() {
		try {
			_persistence.countByUUID_G(StringPool.BLANK,
				RandomTestUtil.nextLong());

			_persistence.countByUUID_G(StringPool.NULL, 0L);

			_persistence.countByUUID_G((String)null, 0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByUuid_C() {
		try {
			_persistence.countByUuid_C(StringPool.BLANK,
				RandomTestUtil.nextLong());

			_persistence.countByUuid_C(StringPool.NULL, 0L);

			_persistence.countByUuid_C((String)null, 0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByGroupId() {
		try {
			_persistence.countByGroupId(RandomTestUtil.nextLong());

			_persistence.countByGroupId(0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByCompanyId() {
		try {
			_persistence.countByCompanyId(RandomTestUtil.nextLong());

			_persistence.countByCompanyId(0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByG_P() {
		try {
			_persistence.countByG_P(RandomTestUtil.nextLong(),
				RandomTestUtil.nextLong());

			_persistence.countByG_P(0L, 0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByC_NotS() {
		try {
			_persistence.countByC_NotS(RandomTestUtil.nextLong(),
				RandomTestUtil.nextInt());

			_persistence.countByC_NotS(0L, 0);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByF_C_P_NotS() {
		try {
			_persistence.countByF_C_P_NotS(RandomTestUtil.nextLong(),
				RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
				RandomTestUtil.nextInt());

			_persistence.countByF_C_P_NotS(0L, 0L, 0L, 0);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByG_P_S() {
		try {
			_persistence.countByG_P_S(RandomTestUtil.nextLong(),
				RandomTestUtil.nextLong(), RandomTestUtil.nextInt());

			_persistence.countByG_P_S(0L, 0L, 0);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByG_P_NotS() {
		try {
			_persistence.countByG_P_NotS(RandomTestUtil.nextLong(),
				RandomTestUtil.nextLong(), RandomTestUtil.nextInt());

			_persistence.countByG_P_NotS(0L, 0L, 0);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		BookmarksFolder newBookmarksFolder = addBookmarksFolder();

		BookmarksFolder existingBookmarksFolder = _persistence.findByPrimaryKey(newBookmarksFolder.getPrimaryKey());

		Assert.assertEquals(existingBookmarksFolder, newBookmarksFolder);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail("Missing entity did not throw NoSuchFolderException");
		}
		catch (NoSuchFolderException nsee) {
		}
	}

	@Test
	public void testFindAll() throws Exception {
		try {
			_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				getOrderByComparator());
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testFilterFindByGroupId() throws Exception {
		try {
			_persistence.filterFindByGroupId(0, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, getOrderByComparator());
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	protected OrderByComparator<BookmarksFolder> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("BookmarksFolder", "uuid",
			true, "folderId", true, "groupId", true, "companyId", true,
			"userId", true, "userName", true, "createDate", true,
			"modifiedDate", true, "resourceBlockId", true, "parentFolderId",
			true, "treePath", true, "name", true, "description", true,
			"status", true, "statusByUserId", true, "statusByUserName", true,
			"statusDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		BookmarksFolder newBookmarksFolder = addBookmarksFolder();

		BookmarksFolder existingBookmarksFolder = _persistence.fetchByPrimaryKey(newBookmarksFolder.getPrimaryKey());

		Assert.assertEquals(existingBookmarksFolder, newBookmarksFolder);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		BookmarksFolder missingBookmarksFolder = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingBookmarksFolder);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		BookmarksFolder newBookmarksFolder1 = addBookmarksFolder();
		BookmarksFolder newBookmarksFolder2 = addBookmarksFolder();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newBookmarksFolder1.getPrimaryKey());
		primaryKeys.add(newBookmarksFolder2.getPrimaryKey());

		Map<Serializable, BookmarksFolder> bookmarksFolders = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, bookmarksFolders.size());
		Assert.assertEquals(newBookmarksFolder1,
			bookmarksFolders.get(newBookmarksFolder1.getPrimaryKey()));
		Assert.assertEquals(newBookmarksFolder2,
			bookmarksFolders.get(newBookmarksFolder2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, BookmarksFolder> bookmarksFolders = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(bookmarksFolders.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		BookmarksFolder newBookmarksFolder = addBookmarksFolder();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newBookmarksFolder.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, BookmarksFolder> bookmarksFolders = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, bookmarksFolders.size());
		Assert.assertEquals(newBookmarksFolder,
			bookmarksFolders.get(newBookmarksFolder.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, BookmarksFolder> bookmarksFolders = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(bookmarksFolders.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		BookmarksFolder newBookmarksFolder = addBookmarksFolder();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newBookmarksFolder.getPrimaryKey());

		Map<Serializable, BookmarksFolder> bookmarksFolders = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, bookmarksFolders.size());
		Assert.assertEquals(newBookmarksFolder,
			bookmarksFolders.get(newBookmarksFolder.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = BookmarksFolderLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod() {
				@Override
				public void performAction(Object object) {
					BookmarksFolder bookmarksFolder = (BookmarksFolder)object;

					Assert.assertNotNull(bookmarksFolder);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		BookmarksFolder newBookmarksFolder = addBookmarksFolder();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BookmarksFolder.class,
				BookmarksFolder.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("folderId",
				newBookmarksFolder.getFolderId()));

		List<BookmarksFolder> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		BookmarksFolder existingBookmarksFolder = result.get(0);

		Assert.assertEquals(existingBookmarksFolder, newBookmarksFolder);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BookmarksFolder.class,
				BookmarksFolder.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("folderId",
				RandomTestUtil.nextLong()));

		List<BookmarksFolder> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		BookmarksFolder newBookmarksFolder = addBookmarksFolder();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BookmarksFolder.class,
				BookmarksFolder.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("folderId"));

		Object newFolderId = newBookmarksFolder.getFolderId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("folderId",
				new Object[] { newFolderId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingFolderId = result.get(0);

		Assert.assertEquals(existingFolderId, newFolderId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BookmarksFolder.class,
				BookmarksFolder.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("folderId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("folderId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		if (!PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			return;
		}

		BookmarksFolder newBookmarksFolder = addBookmarksFolder();

		_persistence.clearCache();

		BookmarksFolderModelImpl existingBookmarksFolderModelImpl = (BookmarksFolderModelImpl)_persistence.findByPrimaryKey(newBookmarksFolder.getPrimaryKey());

		Assert.assertTrue(Validator.equals(
				existingBookmarksFolderModelImpl.getUuid(),
				existingBookmarksFolderModelImpl.getOriginalUuid()));
		Assert.assertEquals(existingBookmarksFolderModelImpl.getGroupId(),
			existingBookmarksFolderModelImpl.getOriginalGroupId());
	}

	protected BookmarksFolder addBookmarksFolder() throws Exception {
		long pk = RandomTestUtil.nextLong();

		BookmarksFolder bookmarksFolder = _persistence.create(pk);

		bookmarksFolder.setUuid(RandomTestUtil.randomString());

		bookmarksFolder.setGroupId(RandomTestUtil.nextLong());

		bookmarksFolder.setCompanyId(RandomTestUtil.nextLong());

		bookmarksFolder.setUserId(RandomTestUtil.nextLong());

		bookmarksFolder.setUserName(RandomTestUtil.randomString());

		bookmarksFolder.setCreateDate(RandomTestUtil.nextDate());

		bookmarksFolder.setModifiedDate(RandomTestUtil.nextDate());

		bookmarksFolder.setResourceBlockId(RandomTestUtil.nextLong());

		bookmarksFolder.setParentFolderId(RandomTestUtil.nextLong());

		bookmarksFolder.setTreePath(RandomTestUtil.randomString());

		bookmarksFolder.setName(RandomTestUtil.randomString());

		bookmarksFolder.setDescription(RandomTestUtil.randomString());

		bookmarksFolder.setStatus(RandomTestUtil.nextInt());

		bookmarksFolder.setStatusByUserId(RandomTestUtil.nextLong());

		bookmarksFolder.setStatusByUserName(RandomTestUtil.randomString());

		bookmarksFolder.setStatusDate(RandomTestUtil.nextDate());

		_models.add(_persistence.update(bookmarksFolder));

		return bookmarksFolder;
	}

	private static Log _log = LogFactoryUtil.getLog(BookmarksFolderPersistenceTest.class);
	private List<BookmarksFolder> _models = new ArrayList<BookmarksFolder>();
	private ModelListener<BookmarksFolder>[] _modelListeners;
	private BookmarksFolderPersistence _persistence = (BookmarksFolderPersistence)PortalBeanLocatorUtil.locate(BookmarksFolderPersistence.class.getName());
}