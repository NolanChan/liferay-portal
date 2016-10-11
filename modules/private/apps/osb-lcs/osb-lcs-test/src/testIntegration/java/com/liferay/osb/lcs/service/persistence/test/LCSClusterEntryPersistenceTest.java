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

import com.liferay.osb.lcs.exception.NoSuchLCSClusterEntryException;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.service.LCSClusterEntryLocalServiceUtil;
import com.liferay.osb.lcs.service.persistence.LCSClusterEntryPersistence;
import com.liferay.osb.lcs.service.persistence.LCSClusterEntryUtil;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.TransactionalTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;

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
public class LCSClusterEntryPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = LCSClusterEntryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<LCSClusterEntry> iterator = _lcsClusterEntries.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSClusterEntry lcsClusterEntry = _persistence.create(pk);

		Assert.assertNotNull(lcsClusterEntry);

		Assert.assertEquals(lcsClusterEntry.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		LCSClusterEntry newLCSClusterEntry = addLCSClusterEntry();

		_persistence.remove(newLCSClusterEntry);

		LCSClusterEntry existingLCSClusterEntry = _persistence.fetchByPrimaryKey(newLCSClusterEntry.getPrimaryKey());

		Assert.assertNull(existingLCSClusterEntry);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addLCSClusterEntry();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSClusterEntry newLCSClusterEntry = _persistence.create(pk);

		newLCSClusterEntry.setLcsProjectId(RandomTestUtil.nextLong());

		newLCSClusterEntry.setName(RandomTestUtil.randomString());

		newLCSClusterEntry.setDescription(RandomTestUtil.randomString());

		newLCSClusterEntry.setElastic(RandomTestUtil.randomBoolean());

		newLCSClusterEntry.setHighPageLoadTime(RandomTestUtil.nextInt());

		newLCSClusterEntry.setLocation(RandomTestUtil.randomString());

		newLCSClusterEntry.setMediumPageLoadTime(RandomTestUtil.nextInt());

		newLCSClusterEntry.setSubscriptionType(RandomTestUtil.randomString());

		newLCSClusterEntry.setType(RandomTestUtil.nextInt());

		newLCSClusterEntry.setArchived(RandomTestUtil.randomBoolean());

		_lcsClusterEntries.add(_persistence.update(newLCSClusterEntry));

		LCSClusterEntry existingLCSClusterEntry = _persistence.findByPrimaryKey(newLCSClusterEntry.getPrimaryKey());

		Assert.assertEquals(existingLCSClusterEntry.getLcsClusterEntryId(),
			newLCSClusterEntry.getLcsClusterEntryId());
		Assert.assertEquals(existingLCSClusterEntry.getLcsProjectId(),
			newLCSClusterEntry.getLcsProjectId());
		Assert.assertEquals(existingLCSClusterEntry.getName(),
			newLCSClusterEntry.getName());
		Assert.assertEquals(existingLCSClusterEntry.getDescription(),
			newLCSClusterEntry.getDescription());
		Assert.assertEquals(existingLCSClusterEntry.getElastic(),
			newLCSClusterEntry.getElastic());
		Assert.assertEquals(existingLCSClusterEntry.getHighPageLoadTime(),
			newLCSClusterEntry.getHighPageLoadTime());
		Assert.assertEquals(existingLCSClusterEntry.getLocation(),
			newLCSClusterEntry.getLocation());
		Assert.assertEquals(existingLCSClusterEntry.getMediumPageLoadTime(),
			newLCSClusterEntry.getMediumPageLoadTime());
		Assert.assertEquals(existingLCSClusterEntry.getSubscriptionType(),
			newLCSClusterEntry.getSubscriptionType());
		Assert.assertEquals(existingLCSClusterEntry.getType(),
			newLCSClusterEntry.getType());
		Assert.assertEquals(existingLCSClusterEntry.getArchived(),
			newLCSClusterEntry.getArchived());
	}

	@Test
	public void testCountByLCSProjectId() throws Exception {
		_persistence.countByLCSProjectId(RandomTestUtil.nextLong());

		_persistence.countByLCSProjectId(0L);
	}

	@Test
	public void testCountByLPI_N() throws Exception {
		_persistence.countByLPI_N(RandomTestUtil.nextLong(), StringPool.BLANK);

		_persistence.countByLPI_N(0L, StringPool.NULL);

		_persistence.countByLPI_N(0L, (String)null);
	}

	@Test
	public void testCountByLPI_ST() throws Exception {
		_persistence.countByLPI_ST(RandomTestUtil.nextLong(), StringPool.BLANK);

		_persistence.countByLPI_ST(0L, StringPool.NULL);

		_persistence.countByLPI_ST(0L, (String)null);
	}

	@Test
	public void testCountByLPI_N_A() throws Exception {
		_persistence.countByLPI_N_A(RandomTestUtil.nextLong(),
			StringPool.BLANK, RandomTestUtil.randomBoolean());

		_persistence.countByLPI_N_A(0L, StringPool.NULL,
			RandomTestUtil.randomBoolean());

		_persistence.countByLPI_N_A(0L, (String)null,
			RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByLPI_ST_A() throws Exception {
		_persistence.countByLPI_ST_A(RandomTestUtil.nextLong(),
			StringPool.BLANK, RandomTestUtil.randomBoolean());

		_persistence.countByLPI_ST_A(0L, StringPool.NULL,
			RandomTestUtil.randomBoolean());

		_persistence.countByLPI_ST_A(0L, (String)null,
			RandomTestUtil.randomBoolean());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		LCSClusterEntry newLCSClusterEntry = addLCSClusterEntry();

		LCSClusterEntry existingLCSClusterEntry = _persistence.findByPrimaryKey(newLCSClusterEntry.getPrimaryKey());

		Assert.assertEquals(existingLCSClusterEntry, newLCSClusterEntry);
	}

	@Test(expected = NoSuchLCSClusterEntryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<LCSClusterEntry> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("OSBLCS_LCSClusterEntry",
			"lcsClusterEntryId", true, "lcsProjectId", true, "name", true,
			"description", true, "elastic", true, "highPageLoadTime", true,
			"location", true, "mediumPageLoadTime", true, "subscriptionType",
			true, "type", true, "archived", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		LCSClusterEntry newLCSClusterEntry = addLCSClusterEntry();

		LCSClusterEntry existingLCSClusterEntry = _persistence.fetchByPrimaryKey(newLCSClusterEntry.getPrimaryKey());

		Assert.assertEquals(existingLCSClusterEntry, newLCSClusterEntry);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSClusterEntry missingLCSClusterEntry = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingLCSClusterEntry);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		LCSClusterEntry newLCSClusterEntry1 = addLCSClusterEntry();
		LCSClusterEntry newLCSClusterEntry2 = addLCSClusterEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSClusterEntry1.getPrimaryKey());
		primaryKeys.add(newLCSClusterEntry2.getPrimaryKey());

		Map<Serializable, LCSClusterEntry> lcsClusterEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, lcsClusterEntries.size());
		Assert.assertEquals(newLCSClusterEntry1,
			lcsClusterEntries.get(newLCSClusterEntry1.getPrimaryKey()));
		Assert.assertEquals(newLCSClusterEntry2,
			lcsClusterEntries.get(newLCSClusterEntry2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, LCSClusterEntry> lcsClusterEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(lcsClusterEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		LCSClusterEntry newLCSClusterEntry = addLCSClusterEntry();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSClusterEntry.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, LCSClusterEntry> lcsClusterEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, lcsClusterEntries.size());
		Assert.assertEquals(newLCSClusterEntry,
			lcsClusterEntries.get(newLCSClusterEntry.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, LCSClusterEntry> lcsClusterEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(lcsClusterEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		LCSClusterEntry newLCSClusterEntry = addLCSClusterEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSClusterEntry.getPrimaryKey());

		Map<Serializable, LCSClusterEntry> lcsClusterEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, lcsClusterEntries.size());
		Assert.assertEquals(newLCSClusterEntry,
			lcsClusterEntries.get(newLCSClusterEntry.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = LCSClusterEntryLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<LCSClusterEntry>() {
				@Override
				public void performAction(LCSClusterEntry lcsClusterEntry) {
					Assert.assertNotNull(lcsClusterEntry);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		LCSClusterEntry newLCSClusterEntry = addLCSClusterEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSClusterEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("lcsClusterEntryId",
				newLCSClusterEntry.getLcsClusterEntryId()));

		List<LCSClusterEntry> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		LCSClusterEntry existingLCSClusterEntry = result.get(0);

		Assert.assertEquals(existingLCSClusterEntry, newLCSClusterEntry);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSClusterEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("lcsClusterEntryId",
				RandomTestUtil.nextLong()));

		List<LCSClusterEntry> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		LCSClusterEntry newLCSClusterEntry = addLCSClusterEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSClusterEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"lcsClusterEntryId"));

		Object newLcsClusterEntryId = newLCSClusterEntry.getLcsClusterEntryId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("lcsClusterEntryId",
				new Object[] { newLcsClusterEntryId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingLcsClusterEntryId = result.get(0);

		Assert.assertEquals(existingLcsClusterEntryId, newLcsClusterEntryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSClusterEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"lcsClusterEntryId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("lcsClusterEntryId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected LCSClusterEntry addLCSClusterEntry() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSClusterEntry lcsClusterEntry = _persistence.create(pk);

		lcsClusterEntry.setLcsProjectId(RandomTestUtil.nextLong());

		lcsClusterEntry.setName(RandomTestUtil.randomString());

		lcsClusterEntry.setDescription(RandomTestUtil.randomString());

		lcsClusterEntry.setElastic(RandomTestUtil.randomBoolean());

		lcsClusterEntry.setHighPageLoadTime(RandomTestUtil.nextInt());

		lcsClusterEntry.setLocation(RandomTestUtil.randomString());

		lcsClusterEntry.setMediumPageLoadTime(RandomTestUtil.nextInt());

		lcsClusterEntry.setSubscriptionType(RandomTestUtil.randomString());

		lcsClusterEntry.setType(RandomTestUtil.nextInt());

		lcsClusterEntry.setArchived(RandomTestUtil.randomBoolean());

		_lcsClusterEntries.add(_persistence.update(lcsClusterEntry));

		return lcsClusterEntry;
	}

	private List<LCSClusterEntry> _lcsClusterEntries = new ArrayList<LCSClusterEntry>();
	private LCSClusterEntryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}