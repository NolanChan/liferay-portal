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

import com.liferay.osb.lcs.exception.NoSuchLCSNotificationAuditEntryException;
import com.liferay.osb.lcs.model.LCSNotificationAuditEntry;
import com.liferay.osb.lcs.service.LCSNotificationAuditEntryLocalServiceUtil;
import com.liferay.osb.lcs.service.persistence.LCSNotificationAuditEntryPersistence;
import com.liferay.osb.lcs.service.persistence.LCSNotificationAuditEntryUtil;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
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
public class LCSNotificationAuditEntryPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.osb.lcs.service"));

	@Before
	public void setUp() {
		_persistence = LCSNotificationAuditEntryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<LCSNotificationAuditEntry> iterator = _lcsNotificationAuditEntries.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSNotificationAuditEntry lcsNotificationAuditEntry = _persistence.create(pk);

		Assert.assertNotNull(lcsNotificationAuditEntry);

		Assert.assertEquals(lcsNotificationAuditEntry.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		LCSNotificationAuditEntry newLCSNotificationAuditEntry = addLCSNotificationAuditEntry();

		_persistence.remove(newLCSNotificationAuditEntry);

		LCSNotificationAuditEntry existingLCSNotificationAuditEntry = _persistence.fetchByPrimaryKey(newLCSNotificationAuditEntry.getPrimaryKey());

		Assert.assertNull(existingLCSNotificationAuditEntry);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addLCSNotificationAuditEntry();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSNotificationAuditEntry newLCSNotificationAuditEntry = _persistence.create(pk);

		newLCSNotificationAuditEntry.setUserId(RandomTestUtil.nextLong());

		newLCSNotificationAuditEntry.setCreateDate(RandomTestUtil.nextDate());

		newLCSNotificationAuditEntry.setLcsClusterNodeId(RandomTestUtil.nextLong());

		newLCSNotificationAuditEntry.setType(RandomTestUtil.nextInt());

		_lcsNotificationAuditEntries.add(_persistence.update(
				newLCSNotificationAuditEntry));

		LCSNotificationAuditEntry existingLCSNotificationAuditEntry = _persistence.findByPrimaryKey(newLCSNotificationAuditEntry.getPrimaryKey());

		Assert.assertEquals(existingLCSNotificationAuditEntry.getLcsNotificationAuditEntryId(),
			newLCSNotificationAuditEntry.getLcsNotificationAuditEntryId());
		Assert.assertEquals(existingLCSNotificationAuditEntry.getUserId(),
			newLCSNotificationAuditEntry.getUserId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingLCSNotificationAuditEntry.getCreateDate()),
			Time.getShortTimestamp(newLCSNotificationAuditEntry.getCreateDate()));
		Assert.assertEquals(existingLCSNotificationAuditEntry.getLcsClusterNodeId(),
			newLCSNotificationAuditEntry.getLcsClusterNodeId());
		Assert.assertEquals(existingLCSNotificationAuditEntry.getType(),
			newLCSNotificationAuditEntry.getType());
	}

	@Test
	public void testCountByLCSClusterNodeId() throws Exception {
		_persistence.countByLCSClusterNodeId(RandomTestUtil.nextLong());

		_persistence.countByLCSClusterNodeId(0L);
	}

	@Test
	public void testCountByLCSClusterNodeIdArrayable()
		throws Exception {
		_persistence.countByLCSClusterNodeId(new long[] {
				RandomTestUtil.nextLong(), 0L
			});
	}

	@Test
	public void testCountByU_LCNI_T() throws Exception {
		_persistence.countByU_LCNI_T(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextInt());

		_persistence.countByU_LCNI_T(0L, 0L, 0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		LCSNotificationAuditEntry newLCSNotificationAuditEntry = addLCSNotificationAuditEntry();

		LCSNotificationAuditEntry existingLCSNotificationAuditEntry = _persistence.findByPrimaryKey(newLCSNotificationAuditEntry.getPrimaryKey());

		Assert.assertEquals(existingLCSNotificationAuditEntry,
			newLCSNotificationAuditEntry);
	}

	@Test(expected = NoSuchLCSNotificationAuditEntryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<LCSNotificationAuditEntry> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("OSBLCS_LCSNotificationAuditEntry",
			"lcsNotificationAuditEntryId", true, "userId", true, "createDate",
			true, "lcsClusterNodeId", true, "type", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		LCSNotificationAuditEntry newLCSNotificationAuditEntry = addLCSNotificationAuditEntry();

		LCSNotificationAuditEntry existingLCSNotificationAuditEntry = _persistence.fetchByPrimaryKey(newLCSNotificationAuditEntry.getPrimaryKey());

		Assert.assertEquals(existingLCSNotificationAuditEntry,
			newLCSNotificationAuditEntry);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSNotificationAuditEntry missingLCSNotificationAuditEntry = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingLCSNotificationAuditEntry);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		LCSNotificationAuditEntry newLCSNotificationAuditEntry1 = addLCSNotificationAuditEntry();
		LCSNotificationAuditEntry newLCSNotificationAuditEntry2 = addLCSNotificationAuditEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSNotificationAuditEntry1.getPrimaryKey());
		primaryKeys.add(newLCSNotificationAuditEntry2.getPrimaryKey());

		Map<Serializable, LCSNotificationAuditEntry> lcsNotificationAuditEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, lcsNotificationAuditEntries.size());
		Assert.assertEquals(newLCSNotificationAuditEntry1,
			lcsNotificationAuditEntries.get(
				newLCSNotificationAuditEntry1.getPrimaryKey()));
		Assert.assertEquals(newLCSNotificationAuditEntry2,
			lcsNotificationAuditEntries.get(
				newLCSNotificationAuditEntry2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, LCSNotificationAuditEntry> lcsNotificationAuditEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(lcsNotificationAuditEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		LCSNotificationAuditEntry newLCSNotificationAuditEntry = addLCSNotificationAuditEntry();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSNotificationAuditEntry.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, LCSNotificationAuditEntry> lcsNotificationAuditEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, lcsNotificationAuditEntries.size());
		Assert.assertEquals(newLCSNotificationAuditEntry,
			lcsNotificationAuditEntries.get(
				newLCSNotificationAuditEntry.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, LCSNotificationAuditEntry> lcsNotificationAuditEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(lcsNotificationAuditEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		LCSNotificationAuditEntry newLCSNotificationAuditEntry = addLCSNotificationAuditEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSNotificationAuditEntry.getPrimaryKey());

		Map<Serializable, LCSNotificationAuditEntry> lcsNotificationAuditEntries =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, lcsNotificationAuditEntries.size());
		Assert.assertEquals(newLCSNotificationAuditEntry,
			lcsNotificationAuditEntries.get(
				newLCSNotificationAuditEntry.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = LCSNotificationAuditEntryLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<LCSNotificationAuditEntry>() {
				@Override
				public void performAction(
					LCSNotificationAuditEntry lcsNotificationAuditEntry) {
					Assert.assertNotNull(lcsNotificationAuditEntry);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		LCSNotificationAuditEntry newLCSNotificationAuditEntry = addLCSNotificationAuditEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSNotificationAuditEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"lcsNotificationAuditEntryId",
				newLCSNotificationAuditEntry.getLcsNotificationAuditEntryId()));

		List<LCSNotificationAuditEntry> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		LCSNotificationAuditEntry existingLCSNotificationAuditEntry = result.get(0);

		Assert.assertEquals(existingLCSNotificationAuditEntry,
			newLCSNotificationAuditEntry);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSNotificationAuditEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"lcsNotificationAuditEntryId", RandomTestUtil.nextLong()));

		List<LCSNotificationAuditEntry> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		LCSNotificationAuditEntry newLCSNotificationAuditEntry = addLCSNotificationAuditEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSNotificationAuditEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"lcsNotificationAuditEntryId"));

		Object newLcsNotificationAuditEntryId = newLCSNotificationAuditEntry.getLcsNotificationAuditEntryId();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"lcsNotificationAuditEntryId",
				new Object[] { newLcsNotificationAuditEntryId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingLcsNotificationAuditEntryId = result.get(0);

		Assert.assertEquals(existingLcsNotificationAuditEntryId,
			newLcsNotificationAuditEntryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSNotificationAuditEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"lcsNotificationAuditEntryId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"lcsNotificationAuditEntryId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected LCSNotificationAuditEntry addLCSNotificationAuditEntry()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSNotificationAuditEntry lcsNotificationAuditEntry = _persistence.create(pk);

		lcsNotificationAuditEntry.setUserId(RandomTestUtil.nextLong());

		lcsNotificationAuditEntry.setCreateDate(RandomTestUtil.nextDate());

		lcsNotificationAuditEntry.setLcsClusterNodeId(RandomTestUtil.nextLong());

		lcsNotificationAuditEntry.setType(RandomTestUtil.nextInt());

		_lcsNotificationAuditEntries.add(_persistence.update(
				lcsNotificationAuditEntry));

		return lcsNotificationAuditEntry;
	}

	private List<LCSNotificationAuditEntry> _lcsNotificationAuditEntries = new ArrayList<LCSNotificationAuditEntry>();
	private LCSNotificationAuditEntryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}