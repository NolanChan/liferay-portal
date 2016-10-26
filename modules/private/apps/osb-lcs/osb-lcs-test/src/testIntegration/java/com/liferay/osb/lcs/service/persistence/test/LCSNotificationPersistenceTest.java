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

package com.liferay.osb.lcs.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.osb.lcs.exception.NoSuchLCSNotificationException;
import com.liferay.osb.lcs.model.LCSNotification;
import com.liferay.osb.lcs.service.LCSNotificationLocalServiceUtil;
import com.liferay.osb.lcs.service.persistence.LCSNotificationPersistence;
import com.liferay.osb.lcs.service.persistence.LCSNotificationUtil;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
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
 * @generated
 */
@RunWith(Arquillian.class)
public class LCSNotificationPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.osb.lcs.service"));

	@Before
	public void setUp() {
		_persistence = LCSNotificationUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<LCSNotification> iterator = _lcsNotifications.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSNotification lcsNotification = _persistence.create(pk);

		Assert.assertNotNull(lcsNotification);

		Assert.assertEquals(lcsNotification.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		LCSNotification newLCSNotification = addLCSNotification();

		_persistence.remove(newLCSNotification);

		LCSNotification existingLCSNotification = _persistence.fetchByPrimaryKey(newLCSNotification.getPrimaryKey());

		Assert.assertNull(existingLCSNotification);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addLCSNotification();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSNotification newLCSNotification = _persistence.create(pk);

		newLCSNotification.setUserId(RandomTestUtil.nextLong());

		newLCSNotification.setCreateDate(RandomTestUtil.nextDate());

		newLCSNotification.setModifiedDate(RandomTestUtil.nextDate());

		newLCSNotification.setClassNameId(RandomTestUtil.nextLong());

		newLCSNotification.setClassPK(RandomTestUtil.nextLong());

		newLCSNotification.setEnabled(RandomTestUtil.randomBoolean());

		newLCSNotification.setType(RandomTestUtil.nextInt());

		_lcsNotifications.add(_persistence.update(newLCSNotification));

		LCSNotification existingLCSNotification = _persistence.findByPrimaryKey(newLCSNotification.getPrimaryKey());

		Assert.assertEquals(existingLCSNotification.getLcsNotificationId(),
			newLCSNotification.getLcsNotificationId());
		Assert.assertEquals(existingLCSNotification.getUserId(),
			newLCSNotification.getUserId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingLCSNotification.getCreateDate()),
			Time.getShortTimestamp(newLCSNotification.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingLCSNotification.getModifiedDate()),
			Time.getShortTimestamp(newLCSNotification.getModifiedDate()));
		Assert.assertEquals(existingLCSNotification.getClassNameId(),
			newLCSNotification.getClassNameId());
		Assert.assertEquals(existingLCSNotification.getClassPK(),
			newLCSNotification.getClassPK());
		Assert.assertEquals(existingLCSNotification.getEnabled(),
			newLCSNotification.getEnabled());
		Assert.assertEquals(existingLCSNotification.getType(),
			newLCSNotification.getType());
	}

	@Test
	public void testCountByUserId() throws Exception {
		_persistence.countByUserId(RandomTestUtil.nextLong());

		_persistence.countByUserId(0L);
	}

	@Test
	public void testCountByC_C() throws Exception {
		_persistence.countByC_C(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByC_C(0L, 0L);
	}

	@Test
	public void testCountByU_C_C() throws Exception {
		_persistence.countByU_C_C(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByU_C_C(0L, 0L, 0L);
	}

	@Test
	public void testCountByU_C_C_T() throws Exception {
		_persistence.countByU_C_C_T(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countByU_C_C_T(0L, 0L, 0L, 0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		LCSNotification newLCSNotification = addLCSNotification();

		LCSNotification existingLCSNotification = _persistence.findByPrimaryKey(newLCSNotification.getPrimaryKey());

		Assert.assertEquals(existingLCSNotification, newLCSNotification);
	}

	@Test(expected = NoSuchLCSNotificationException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<LCSNotification> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("OSBLCS_LCSNotification",
			"lcsNotificationId", true, "userId", true, "createDate", true,
			"modifiedDate", true, "classNameId", true, "classPK", true,
			"enabled", true, "type", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		LCSNotification newLCSNotification = addLCSNotification();

		LCSNotification existingLCSNotification = _persistence.fetchByPrimaryKey(newLCSNotification.getPrimaryKey());

		Assert.assertEquals(existingLCSNotification, newLCSNotification);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSNotification missingLCSNotification = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingLCSNotification);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		LCSNotification newLCSNotification1 = addLCSNotification();
		LCSNotification newLCSNotification2 = addLCSNotification();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSNotification1.getPrimaryKey());
		primaryKeys.add(newLCSNotification2.getPrimaryKey());

		Map<Serializable, LCSNotification> lcsNotifications = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, lcsNotifications.size());
		Assert.assertEquals(newLCSNotification1,
			lcsNotifications.get(newLCSNotification1.getPrimaryKey()));
		Assert.assertEquals(newLCSNotification2,
			lcsNotifications.get(newLCSNotification2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, LCSNotification> lcsNotifications = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(lcsNotifications.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		LCSNotification newLCSNotification = addLCSNotification();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSNotification.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, LCSNotification> lcsNotifications = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, lcsNotifications.size());
		Assert.assertEquals(newLCSNotification,
			lcsNotifications.get(newLCSNotification.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, LCSNotification> lcsNotifications = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(lcsNotifications.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		LCSNotification newLCSNotification = addLCSNotification();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSNotification.getPrimaryKey());

		Map<Serializable, LCSNotification> lcsNotifications = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, lcsNotifications.size());
		Assert.assertEquals(newLCSNotification,
			lcsNotifications.get(newLCSNotification.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = LCSNotificationLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<LCSNotification>() {
				@Override
				public void performAction(LCSNotification lcsNotification) {
					Assert.assertNotNull(lcsNotification);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		LCSNotification newLCSNotification = addLCSNotification();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSNotification.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("lcsNotificationId",
				newLCSNotification.getLcsNotificationId()));

		List<LCSNotification> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		LCSNotification existingLCSNotification = result.get(0);

		Assert.assertEquals(existingLCSNotification, newLCSNotification);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSNotification.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("lcsNotificationId",
				RandomTestUtil.nextLong()));

		List<LCSNotification> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		LCSNotification newLCSNotification = addLCSNotification();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSNotification.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"lcsNotificationId"));

		Object newLcsNotificationId = newLCSNotification.getLcsNotificationId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("lcsNotificationId",
				new Object[] { newLcsNotificationId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingLcsNotificationId = result.get(0);

		Assert.assertEquals(existingLcsNotificationId, newLcsNotificationId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSNotification.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"lcsNotificationId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("lcsNotificationId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		LCSNotification newLCSNotification = addLCSNotification();

		_persistence.clearCache();

		LCSNotification existingLCSNotification = _persistence.findByPrimaryKey(newLCSNotification.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(existingLCSNotification.getUserId()),
			ReflectionTestUtil.<Long>invoke(existingLCSNotification,
				"getOriginalUserId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(
				existingLCSNotification.getClassNameId()),
			ReflectionTestUtil.<Long>invoke(existingLCSNotification,
				"getOriginalClassNameId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(existingLCSNotification.getClassPK()),
			ReflectionTestUtil.<Long>invoke(existingLCSNotification,
				"getOriginalClassPK", new Class<?>[0]));
		Assert.assertEquals(Integer.valueOf(existingLCSNotification.getType()),
			ReflectionTestUtil.<Integer>invoke(existingLCSNotification,
				"getOriginalType", new Class<?>[0]));
	}

	protected LCSNotification addLCSNotification() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSNotification lcsNotification = _persistence.create(pk);

		lcsNotification.setUserId(RandomTestUtil.nextLong());

		lcsNotification.setCreateDate(RandomTestUtil.nextDate());

		lcsNotification.setModifiedDate(RandomTestUtil.nextDate());

		lcsNotification.setClassNameId(RandomTestUtil.nextLong());

		lcsNotification.setClassPK(RandomTestUtil.nextLong());

		lcsNotification.setEnabled(RandomTestUtil.randomBoolean());

		lcsNotification.setType(RandomTestUtil.nextInt());

		_lcsNotifications.add(_persistence.update(lcsNotification));

		return lcsNotification;
	}

	private List<LCSNotification> _lcsNotifications = new ArrayList<LCSNotification>();
	private LCSNotificationPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}