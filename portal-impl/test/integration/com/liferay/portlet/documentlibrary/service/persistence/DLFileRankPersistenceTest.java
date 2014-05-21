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

package com.liferay.portlet.documentlibrary.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BasePersistence;
import com.liferay.portal.service.persistence.PersistenceExecutionTestListener;
import com.liferay.portal.test.LiferayPersistenceIntegrationJUnitTestRunner;
import com.liferay.portal.test.persistence.TransactionalPersistenceAdvice;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.test.RandomTestUtil;

import com.liferay.portlet.documentlibrary.NoSuchFileRankException;
import com.liferay.portlet.documentlibrary.model.DLFileRank;
import com.liferay.portlet.documentlibrary.model.impl.DLFileRankModelImpl;
import com.liferay.portlet.documentlibrary.service.DLFileRankLocalServiceUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
@ExecutionTestListeners(listeners =  {
	PersistenceExecutionTestListener.class})
@RunWith(LiferayPersistenceIntegrationJUnitTestRunner.class)
public class DLFileRankPersistenceTest {
	@Before
	public void setUp() {
		_modelListeners = _persistence.getListeners();

		for (ModelListener<DLFileRank> modelListener : _modelListeners) {
			_persistence.unregisterListener(modelListener);
		}
	}

	@After
	public void tearDown() throws Exception {
		Map<Serializable, BasePersistence<?>> basePersistences = _transactionalPersistenceAdvice.getBasePersistences();

		Set<Serializable> primaryKeys = basePersistences.keySet();

		for (Serializable primaryKey : primaryKeys) {
			BasePersistence<?> basePersistence = basePersistences.get(primaryKey);

			try {
				basePersistence.remove(primaryKey);
			}
			catch (Exception e) {
				if (_log.isDebugEnabled()) {
					_log.debug("The model with primary key " + primaryKey +
						" was already deleted");
				}
			}
		}

		_transactionalPersistenceAdvice.reset();

		for (ModelListener<DLFileRank> modelListener : _modelListeners) {
			_persistence.registerListener(modelListener);
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DLFileRank dlFileRank = _persistence.create(pk);

		Assert.assertNotNull(dlFileRank);

		Assert.assertEquals(dlFileRank.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		DLFileRank newDLFileRank = addDLFileRank();

		_persistence.remove(newDLFileRank);

		DLFileRank existingDLFileRank = _persistence.fetchByPrimaryKey(newDLFileRank.getPrimaryKey());

		Assert.assertNull(existingDLFileRank);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addDLFileRank();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DLFileRank newDLFileRank = _persistence.create(pk);

		newDLFileRank.setGroupId(RandomTestUtil.nextLong());

		newDLFileRank.setCompanyId(RandomTestUtil.nextLong());

		newDLFileRank.setUserId(RandomTestUtil.nextLong());

		newDLFileRank.setCreateDate(RandomTestUtil.nextDate());

		newDLFileRank.setFileEntryId(RandomTestUtil.nextLong());

		newDLFileRank.setActive(RandomTestUtil.randomBoolean());

		_persistence.update(newDLFileRank);

		DLFileRank existingDLFileRank = _persistence.findByPrimaryKey(newDLFileRank.getPrimaryKey());

		Assert.assertEquals(existingDLFileRank.getFileRankId(),
			newDLFileRank.getFileRankId());
		Assert.assertEquals(existingDLFileRank.getGroupId(),
			newDLFileRank.getGroupId());
		Assert.assertEquals(existingDLFileRank.getCompanyId(),
			newDLFileRank.getCompanyId());
		Assert.assertEquals(existingDLFileRank.getUserId(),
			newDLFileRank.getUserId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingDLFileRank.getCreateDate()),
			Time.getShortTimestamp(newDLFileRank.getCreateDate()));
		Assert.assertEquals(existingDLFileRank.getFileEntryId(),
			newDLFileRank.getFileEntryId());
		Assert.assertEquals(existingDLFileRank.getActive(),
			newDLFileRank.getActive());
	}

	@Test
	public void testCountByUserId() {
		try {
			_persistence.countByUserId(RandomTestUtil.nextLong());

			_persistence.countByUserId(0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByFileEntryId() {
		try {
			_persistence.countByFileEntryId(RandomTestUtil.nextLong());

			_persistence.countByFileEntryId(0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByG_U() {
		try {
			_persistence.countByG_U(RandomTestUtil.nextLong(),
				RandomTestUtil.nextLong());

			_persistence.countByG_U(0L, 0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByG_U_A() {
		try {
			_persistence.countByG_U_A(RandomTestUtil.nextLong(),
				RandomTestUtil.nextLong(), RandomTestUtil.randomBoolean());

			_persistence.countByG_U_A(0L, 0L, RandomTestUtil.randomBoolean());
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByC_U_F() {
		try {
			_persistence.countByC_U_F(RandomTestUtil.nextLong(),
				RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

			_persistence.countByC_U_F(0L, 0L, 0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		DLFileRank newDLFileRank = addDLFileRank();

		DLFileRank existingDLFileRank = _persistence.findByPrimaryKey(newDLFileRank.getPrimaryKey());

		Assert.assertEquals(existingDLFileRank, newDLFileRank);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail("Missing entity did not throw NoSuchFileRankException");
		}
		catch (NoSuchFileRankException nsee) {
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

	protected OrderByComparator getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("DLFileRank", "fileRankId",
			true, "groupId", true, "companyId", true, "userId", true,
			"createDate", true, "fileEntryId", true, "active", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		DLFileRank newDLFileRank = addDLFileRank();

		DLFileRank existingDLFileRank = _persistence.fetchByPrimaryKey(newDLFileRank.getPrimaryKey());

		Assert.assertEquals(existingDLFileRank, newDLFileRank);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DLFileRank missingDLFileRank = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingDLFileRank);
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = DLFileRankLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod() {
				@Override
				public void performAction(Object object) {
					DLFileRank dlFileRank = (DLFileRank)object;

					Assert.assertNotNull(dlFileRank);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		DLFileRank newDLFileRank = addDLFileRank();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DLFileRank.class,
				DLFileRank.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("fileRankId",
				newDLFileRank.getFileRankId()));

		List<DLFileRank> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		DLFileRank existingDLFileRank = result.get(0);

		Assert.assertEquals(existingDLFileRank, newDLFileRank);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DLFileRank.class,
				DLFileRank.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("fileRankId",
				RandomTestUtil.nextLong()));

		List<DLFileRank> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		DLFileRank newDLFileRank = addDLFileRank();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DLFileRank.class,
				DLFileRank.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("fileRankId"));

		Object newFileRankId = newDLFileRank.getFileRankId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("fileRankId",
				new Object[] { newFileRankId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingFileRankId = result.get(0);

		Assert.assertEquals(existingFileRankId, newFileRankId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DLFileRank.class,
				DLFileRank.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("fileRankId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("fileRankId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		if (!PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			return;
		}

		DLFileRank newDLFileRank = addDLFileRank();

		_persistence.clearCache();

		DLFileRankModelImpl existingDLFileRankModelImpl = (DLFileRankModelImpl)_persistence.findByPrimaryKey(newDLFileRank.getPrimaryKey());

		Assert.assertEquals(existingDLFileRankModelImpl.getCompanyId(),
			existingDLFileRankModelImpl.getOriginalCompanyId());
		Assert.assertEquals(existingDLFileRankModelImpl.getUserId(),
			existingDLFileRankModelImpl.getOriginalUserId());
		Assert.assertEquals(existingDLFileRankModelImpl.getFileEntryId(),
			existingDLFileRankModelImpl.getOriginalFileEntryId());
	}

	protected DLFileRank addDLFileRank() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DLFileRank dlFileRank = _persistence.create(pk);

		dlFileRank.setGroupId(RandomTestUtil.nextLong());

		dlFileRank.setCompanyId(RandomTestUtil.nextLong());

		dlFileRank.setUserId(RandomTestUtil.nextLong());

		dlFileRank.setCreateDate(RandomTestUtil.nextDate());

		dlFileRank.setFileEntryId(RandomTestUtil.nextLong());

		dlFileRank.setActive(RandomTestUtil.randomBoolean());

		_persistence.update(dlFileRank);

		return dlFileRank;
	}

	private static Log _log = LogFactoryUtil.getLog(DLFileRankPersistenceTest.class);
	private ModelListener<DLFileRank>[] _modelListeners;
	private DLFileRankPersistence _persistence = (DLFileRankPersistence)PortalBeanLocatorUtil.locate(DLFileRankPersistence.class.getName());
	private TransactionalPersistenceAdvice _transactionalPersistenceAdvice = (TransactionalPersistenceAdvice)PortalBeanLocatorUtil.locate(TransactionalPersistenceAdvice.class.getName());
}