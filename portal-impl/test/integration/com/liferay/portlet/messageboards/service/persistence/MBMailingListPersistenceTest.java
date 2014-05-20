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

package com.liferay.portlet.messageboards.service.persistence;

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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BasePersistence;
import com.liferay.portal.service.persistence.PersistenceExecutionTestListener;
import com.liferay.portal.test.LiferayPersistenceIntegrationJUnitTestRunner;
import com.liferay.portal.test.persistence.TransactionalPersistenceAdvice;
import com.liferay.portal.util.PropsValues;

import com.liferay.portlet.messageboards.NoSuchMailingListException;
import com.liferay.portlet.messageboards.model.MBMailingList;
import com.liferay.portlet.messageboards.model.impl.MBMailingListModelImpl;
import com.liferay.portlet.messageboards.service.MBMailingListLocalServiceUtil;

import com.liferay.test.portal.util.RandomTestUtil;
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
public class MBMailingListPersistenceTest {
	@Before
	public void setUp() {
		_modelListeners = _persistence.getListeners();

		for (ModelListener<MBMailingList> modelListener : _modelListeners) {
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

		for (ModelListener<MBMailingList> modelListener : _modelListeners) {
			_persistence.registerListener(modelListener);
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		MBMailingList mbMailingList = _persistence.create(pk);

		Assert.assertNotNull(mbMailingList);

		Assert.assertEquals(mbMailingList.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		MBMailingList newMBMailingList = addMBMailingList();

		_persistence.remove(newMBMailingList);

		MBMailingList existingMBMailingList = _persistence.fetchByPrimaryKey(newMBMailingList.getPrimaryKey());

		Assert.assertNull(existingMBMailingList);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addMBMailingList();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		MBMailingList newMBMailingList = _persistence.create(pk);

		newMBMailingList.setUuid(RandomTestUtil.randomString());

		newMBMailingList.setGroupId(RandomTestUtil.nextLong());

		newMBMailingList.setCompanyId(RandomTestUtil.nextLong());

		newMBMailingList.setUserId(RandomTestUtil.nextLong());

		newMBMailingList.setUserName(RandomTestUtil.randomString());

		newMBMailingList.setCreateDate(RandomTestUtil.nextDate());

		newMBMailingList.setModifiedDate(RandomTestUtil.nextDate());

		newMBMailingList.setCategoryId(RandomTestUtil.nextLong());

		newMBMailingList.setEmailAddress(RandomTestUtil.randomString());

		newMBMailingList.setInProtocol(RandomTestUtil.randomString());

		newMBMailingList.setInServerName(RandomTestUtil.randomString());

		newMBMailingList.setInServerPort(RandomTestUtil.nextInt());

		newMBMailingList.setInUseSSL(RandomTestUtil.randomBoolean());

		newMBMailingList.setInUserName(RandomTestUtil.randomString());

		newMBMailingList.setInPassword(RandomTestUtil.randomString());

		newMBMailingList.setInReadInterval(RandomTestUtil.nextInt());

		newMBMailingList.setOutEmailAddress(RandomTestUtil.randomString());

		newMBMailingList.setOutCustom(RandomTestUtil.randomBoolean());

		newMBMailingList.setOutServerName(RandomTestUtil.randomString());

		newMBMailingList.setOutServerPort(RandomTestUtil.nextInt());

		newMBMailingList.setOutUseSSL(RandomTestUtil.randomBoolean());

		newMBMailingList.setOutUserName(RandomTestUtil.randomString());

		newMBMailingList.setOutPassword(RandomTestUtil.randomString());

		newMBMailingList.setAllowAnonymous(RandomTestUtil.randomBoolean());

		newMBMailingList.setActive(RandomTestUtil.randomBoolean());

		_persistence.update(newMBMailingList);

		MBMailingList existingMBMailingList = _persistence.findByPrimaryKey(newMBMailingList.getPrimaryKey());

		Assert.assertEquals(existingMBMailingList.getUuid(),
			newMBMailingList.getUuid());
		Assert.assertEquals(existingMBMailingList.getMailingListId(),
			newMBMailingList.getMailingListId());
		Assert.assertEquals(existingMBMailingList.getGroupId(),
			newMBMailingList.getGroupId());
		Assert.assertEquals(existingMBMailingList.getCompanyId(),
			newMBMailingList.getCompanyId());
		Assert.assertEquals(existingMBMailingList.getUserId(),
			newMBMailingList.getUserId());
		Assert.assertEquals(existingMBMailingList.getUserName(),
			newMBMailingList.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingMBMailingList.getCreateDate()),
			Time.getShortTimestamp(newMBMailingList.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingMBMailingList.getModifiedDate()),
			Time.getShortTimestamp(newMBMailingList.getModifiedDate()));
		Assert.assertEquals(existingMBMailingList.getCategoryId(),
			newMBMailingList.getCategoryId());
		Assert.assertEquals(existingMBMailingList.getEmailAddress(),
			newMBMailingList.getEmailAddress());
		Assert.assertEquals(existingMBMailingList.getInProtocol(),
			newMBMailingList.getInProtocol());
		Assert.assertEquals(existingMBMailingList.getInServerName(),
			newMBMailingList.getInServerName());
		Assert.assertEquals(existingMBMailingList.getInServerPort(),
			newMBMailingList.getInServerPort());
		Assert.assertEquals(existingMBMailingList.getInUseSSL(),
			newMBMailingList.getInUseSSL());
		Assert.assertEquals(existingMBMailingList.getInUserName(),
			newMBMailingList.getInUserName());
		Assert.assertEquals(existingMBMailingList.getInPassword(),
			newMBMailingList.getInPassword());
		Assert.assertEquals(existingMBMailingList.getInReadInterval(),
			newMBMailingList.getInReadInterval());
		Assert.assertEquals(existingMBMailingList.getOutEmailAddress(),
			newMBMailingList.getOutEmailAddress());
		Assert.assertEquals(existingMBMailingList.getOutCustom(),
			newMBMailingList.getOutCustom());
		Assert.assertEquals(existingMBMailingList.getOutServerName(),
			newMBMailingList.getOutServerName());
		Assert.assertEquals(existingMBMailingList.getOutServerPort(),
			newMBMailingList.getOutServerPort());
		Assert.assertEquals(existingMBMailingList.getOutUseSSL(),
			newMBMailingList.getOutUseSSL());
		Assert.assertEquals(existingMBMailingList.getOutUserName(),
			newMBMailingList.getOutUserName());
		Assert.assertEquals(existingMBMailingList.getOutPassword(),
			newMBMailingList.getOutPassword());
		Assert.assertEquals(existingMBMailingList.getAllowAnonymous(),
			newMBMailingList.getAllowAnonymous());
		Assert.assertEquals(existingMBMailingList.getActive(),
			newMBMailingList.getActive());
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
	public void testCountByActive() {
		try {
			_persistence.countByActive(RandomTestUtil.randomBoolean());

			_persistence.countByActive(RandomTestUtil.randomBoolean());
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByG_C() {
		try {
			_persistence.countByG_C(RandomTestUtil.nextLong(),
				RandomTestUtil.nextLong());

			_persistence.countByG_C(0L, 0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		MBMailingList newMBMailingList = addMBMailingList();

		MBMailingList existingMBMailingList = _persistence.findByPrimaryKey(newMBMailingList.getPrimaryKey());

		Assert.assertEquals(existingMBMailingList, newMBMailingList);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail(
				"Missing entity did not throw NoSuchMailingListException");
		}
		catch (NoSuchMailingListException nsee) {
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
		return OrderByComparatorFactoryUtil.create("MBMailingList", "uuid",
			true, "mailingListId", true, "groupId", true, "companyId", true,
			"userId", true, "userName", true, "createDate", true,
			"modifiedDate", true, "categoryId", true, "emailAddress", true,
			"inProtocol", true, "inServerName", true, "inServerPort", true,
			"inUseSSL", true, "inUserName", true, "inPassword", true,
			"inReadInterval", true, "outEmailAddress", true, "outCustom", true,
			"outServerName", true, "outServerPort", true, "outUseSSL", true,
			"outUserName", true, "outPassword", true, "allowAnonymous", true,
			"active", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		MBMailingList newMBMailingList = addMBMailingList();

		MBMailingList existingMBMailingList = _persistence.fetchByPrimaryKey(newMBMailingList.getPrimaryKey());

		Assert.assertEquals(existingMBMailingList, newMBMailingList);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		MBMailingList missingMBMailingList = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingMBMailingList);
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = MBMailingListLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod() {
				@Override
				public void performAction(Object object) {
					MBMailingList mbMailingList = (MBMailingList)object;

					Assert.assertNotNull(mbMailingList);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		MBMailingList newMBMailingList = addMBMailingList();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MBMailingList.class,
				MBMailingList.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("mailingListId",
				newMBMailingList.getMailingListId()));

		List<MBMailingList> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		MBMailingList existingMBMailingList = result.get(0);

		Assert.assertEquals(existingMBMailingList, newMBMailingList);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MBMailingList.class,
				MBMailingList.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("mailingListId",
				RandomTestUtil.nextLong()));

		List<MBMailingList> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		MBMailingList newMBMailingList = addMBMailingList();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MBMailingList.class,
				MBMailingList.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"mailingListId"));

		Object newMailingListId = newMBMailingList.getMailingListId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("mailingListId",
				new Object[] { newMailingListId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingMailingListId = result.get(0);

		Assert.assertEquals(existingMailingListId, newMailingListId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MBMailingList.class,
				MBMailingList.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"mailingListId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("mailingListId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		if (!PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			return;
		}

		MBMailingList newMBMailingList = addMBMailingList();

		_persistence.clearCache();

		MBMailingListModelImpl existingMBMailingListModelImpl = (MBMailingListModelImpl)_persistence.findByPrimaryKey(newMBMailingList.getPrimaryKey());

		Assert.assertTrue(Validator.equals(
				existingMBMailingListModelImpl.getUuid(),
				existingMBMailingListModelImpl.getOriginalUuid()));
		Assert.assertEquals(existingMBMailingListModelImpl.getGroupId(),
			existingMBMailingListModelImpl.getOriginalGroupId());

		Assert.assertEquals(existingMBMailingListModelImpl.getGroupId(),
			existingMBMailingListModelImpl.getOriginalGroupId());
		Assert.assertEquals(existingMBMailingListModelImpl.getCategoryId(),
			existingMBMailingListModelImpl.getOriginalCategoryId());
	}

	protected MBMailingList addMBMailingList() throws Exception {
		long pk = RandomTestUtil.nextLong();

		MBMailingList mbMailingList = _persistence.create(pk);

		mbMailingList.setUuid(RandomTestUtil.randomString());

		mbMailingList.setGroupId(RandomTestUtil.nextLong());

		mbMailingList.setCompanyId(RandomTestUtil.nextLong());

		mbMailingList.setUserId(RandomTestUtil.nextLong());

		mbMailingList.setUserName(RandomTestUtil.randomString());

		mbMailingList.setCreateDate(RandomTestUtil.nextDate());

		mbMailingList.setModifiedDate(RandomTestUtil.nextDate());

		mbMailingList.setCategoryId(RandomTestUtil.nextLong());

		mbMailingList.setEmailAddress(RandomTestUtil.randomString());

		mbMailingList.setInProtocol(RandomTestUtil.randomString());

		mbMailingList.setInServerName(RandomTestUtil.randomString());

		mbMailingList.setInServerPort(RandomTestUtil.nextInt());

		mbMailingList.setInUseSSL(RandomTestUtil.randomBoolean());

		mbMailingList.setInUserName(RandomTestUtil.randomString());

		mbMailingList.setInPassword(RandomTestUtil.randomString());

		mbMailingList.setInReadInterval(RandomTestUtil.nextInt());

		mbMailingList.setOutEmailAddress(RandomTestUtil.randomString());

		mbMailingList.setOutCustom(RandomTestUtil.randomBoolean());

		mbMailingList.setOutServerName(RandomTestUtil.randomString());

		mbMailingList.setOutServerPort(RandomTestUtil.nextInt());

		mbMailingList.setOutUseSSL(RandomTestUtil.randomBoolean());

		mbMailingList.setOutUserName(RandomTestUtil.randomString());

		mbMailingList.setOutPassword(RandomTestUtil.randomString());

		mbMailingList.setAllowAnonymous(RandomTestUtil.randomBoolean());

		mbMailingList.setActive(RandomTestUtil.randomBoolean());

		_persistence.update(mbMailingList);

		return mbMailingList;
	}

	private static Log _log = LogFactoryUtil.getLog(MBMailingListPersistenceTest.class);
	private ModelListener<MBMailingList>[] _modelListeners;
	private MBMailingListPersistence _persistence = (MBMailingListPersistence)PortalBeanLocatorUtil.locate(MBMailingListPersistence.class.getName());
	private TransactionalPersistenceAdvice _transactionalPersistenceAdvice = (TransactionalPersistenceAdvice)PortalBeanLocatorUtil.locate(TransactionalPersistenceAdvice.class.getName());
}