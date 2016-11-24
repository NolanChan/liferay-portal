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

import com.liferay.osb.lcs.exception.NoSuchLCSSubscriptionEntryException;
import com.liferay.osb.lcs.model.LCSSubscriptionEntry;
import com.liferay.osb.lcs.service.LCSSubscriptionEntryLocalServiceUtil;
import com.liferay.osb.lcs.service.persistence.LCSSubscriptionEntryPersistence;
import com.liferay.osb.lcs.service.persistence.LCSSubscriptionEntryUtil;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.AssertUtils;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
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
public class LCSSubscriptionEntryPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.osb.lcs.service"));

	@Before
	public void setUp() {
		_persistence = LCSSubscriptionEntryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<LCSSubscriptionEntry> iterator = _lcsSubscriptionEntries.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSSubscriptionEntry lcsSubscriptionEntry = _persistence.create(pk);

		Assert.assertNotNull(lcsSubscriptionEntry);

		Assert.assertEquals(lcsSubscriptionEntry.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		LCSSubscriptionEntry newLCSSubscriptionEntry = addLCSSubscriptionEntry();

		_persistence.remove(newLCSSubscriptionEntry);

		LCSSubscriptionEntry existingLCSSubscriptionEntry = _persistence.fetchByPrimaryKey(newLCSSubscriptionEntry.getPrimaryKey());

		Assert.assertNull(existingLCSSubscriptionEntry);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addLCSSubscriptionEntry();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSSubscriptionEntry newLCSSubscriptionEntry = _persistence.create(pk);

		newLCSSubscriptionEntry.setLcsProjectId(RandomTestUtil.nextLong());

		newLCSSubscriptionEntry.setActualPrice(RandomTestUtil.nextDouble());

		newLCSSubscriptionEntry.setCurrencyCode(RandomTestUtil.randomString());

		newLCSSubscriptionEntry.setInstanceSize(RandomTestUtil.nextInt());

		newLCSSubscriptionEntry.setType(RandomTestUtil.randomString());

		newLCSSubscriptionEntry.setPlatform(RandomTestUtil.randomString());

		newLCSSubscriptionEntry.setPlatformVersion(RandomTestUtil.nextInt());

		newLCSSubscriptionEntry.setProcessorCoresAllowed(RandomTestUtil.nextInt());

		newLCSSubscriptionEntry.setProduct(RandomTestUtil.randomString());

		newLCSSubscriptionEntry.setProductVersion(RandomTestUtil.nextInt());

		newLCSSubscriptionEntry.setServersAllowed(RandomTestUtil.nextInt());

		newLCSSubscriptionEntry.setServersUsed(RandomTestUtil.nextInt());

		newLCSSubscriptionEntry.setStartDate(RandomTestUtil.nextDate());

		newLCSSubscriptionEntry.setEndDate(RandomTestUtil.nextDate());

		newLCSSubscriptionEntry.setSupportStartDate(RandomTestUtil.nextDate());

		newLCSSubscriptionEntry.setSupportEndDate(RandomTestUtil.nextDate());

		newLCSSubscriptionEntry.setActive(RandomTestUtil.randomBoolean());

		_lcsSubscriptionEntries.add(_persistence.update(newLCSSubscriptionEntry));

		LCSSubscriptionEntry existingLCSSubscriptionEntry = _persistence.findByPrimaryKey(newLCSSubscriptionEntry.getPrimaryKey());

		Assert.assertEquals(existingLCSSubscriptionEntry.getLcsSubscriptionEntryId(),
			newLCSSubscriptionEntry.getLcsSubscriptionEntryId());
		Assert.assertEquals(existingLCSSubscriptionEntry.getLcsProjectId(),
			newLCSSubscriptionEntry.getLcsProjectId());
		AssertUtils.assertEquals(existingLCSSubscriptionEntry.getActualPrice(),
			newLCSSubscriptionEntry.getActualPrice());
		Assert.assertEquals(existingLCSSubscriptionEntry.getCurrencyCode(),
			newLCSSubscriptionEntry.getCurrencyCode());
		Assert.assertEquals(existingLCSSubscriptionEntry.getInstanceSize(),
			newLCSSubscriptionEntry.getInstanceSize());
		Assert.assertEquals(existingLCSSubscriptionEntry.getType(),
			newLCSSubscriptionEntry.getType());
		Assert.assertEquals(existingLCSSubscriptionEntry.getPlatform(),
			newLCSSubscriptionEntry.getPlatform());
		Assert.assertEquals(existingLCSSubscriptionEntry.getPlatformVersion(),
			newLCSSubscriptionEntry.getPlatformVersion());
		Assert.assertEquals(existingLCSSubscriptionEntry.getProcessorCoresAllowed(),
			newLCSSubscriptionEntry.getProcessorCoresAllowed());
		Assert.assertEquals(existingLCSSubscriptionEntry.getProduct(),
			newLCSSubscriptionEntry.getProduct());
		Assert.assertEquals(existingLCSSubscriptionEntry.getProductVersion(),
			newLCSSubscriptionEntry.getProductVersion());
		Assert.assertEquals(existingLCSSubscriptionEntry.getServersAllowed(),
			newLCSSubscriptionEntry.getServersAllowed());
		Assert.assertEquals(existingLCSSubscriptionEntry.getServersUsed(),
			newLCSSubscriptionEntry.getServersUsed());
		Assert.assertEquals(Time.getShortTimestamp(
				existingLCSSubscriptionEntry.getStartDate()),
			Time.getShortTimestamp(newLCSSubscriptionEntry.getStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingLCSSubscriptionEntry.getEndDate()),
			Time.getShortTimestamp(newLCSSubscriptionEntry.getEndDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingLCSSubscriptionEntry.getSupportStartDate()),
			Time.getShortTimestamp(
				newLCSSubscriptionEntry.getSupportStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingLCSSubscriptionEntry.getSupportEndDate()),
			Time.getShortTimestamp(newLCSSubscriptionEntry.getSupportEndDate()));
		Assert.assertEquals(existingLCSSubscriptionEntry.getActive(),
			newLCSSubscriptionEntry.getActive());
	}

	@Test
	public void testCountByActive() throws Exception {
		_persistence.countByActive(RandomTestUtil.randomBoolean());

		_persistence.countByActive(RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByLCSProjectId() throws Exception {
		_persistence.countByLCSProjectId(RandomTestUtil.nextLong());

		_persistence.countByLCSProjectId(0L);
	}

	@Test
	public void testCountByLPI_A() throws Exception {
		_persistence.countByLPI_A(RandomTestUtil.nextLong(),
			RandomTestUtil.randomBoolean());

		_persistence.countByLPI_A(0L, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByLPI_T_A() throws Exception {
		_persistence.countByLPI_T_A(RandomTestUtil.nextLong(),
			StringPool.BLANK, RandomTestUtil.randomBoolean());

		_persistence.countByLPI_T_A(0L, StringPool.NULL,
			RandomTestUtil.randomBoolean());

		_persistence.countByLPI_T_A(0L, (String)null,
			RandomTestUtil.randomBoolean());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		LCSSubscriptionEntry newLCSSubscriptionEntry = addLCSSubscriptionEntry();

		LCSSubscriptionEntry existingLCSSubscriptionEntry = _persistence.findByPrimaryKey(newLCSSubscriptionEntry.getPrimaryKey());

		Assert.assertEquals(existingLCSSubscriptionEntry,
			newLCSSubscriptionEntry);
	}

	@Test(expected = NoSuchLCSSubscriptionEntryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<LCSSubscriptionEntry> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("OSBLCS_LCSSubscriptionEntry",
			"lcsSubscriptionEntryId", true, "lcsProjectId", true,
			"actualPrice", true, "currencyCode", true, "instanceSize", true,
			"type", true, "platform", true, "platformVersion", true,
			"processorCoresAllowed", true, "product", true, "productVersion",
			true, "serversAllowed", true, "serversUsed", true, "startDate",
			true, "endDate", true, "supportStartDate", true, "supportEndDate",
			true, "active", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		LCSSubscriptionEntry newLCSSubscriptionEntry = addLCSSubscriptionEntry();

		LCSSubscriptionEntry existingLCSSubscriptionEntry = _persistence.fetchByPrimaryKey(newLCSSubscriptionEntry.getPrimaryKey());

		Assert.assertEquals(existingLCSSubscriptionEntry,
			newLCSSubscriptionEntry);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSSubscriptionEntry missingLCSSubscriptionEntry = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingLCSSubscriptionEntry);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		LCSSubscriptionEntry newLCSSubscriptionEntry1 = addLCSSubscriptionEntry();
		LCSSubscriptionEntry newLCSSubscriptionEntry2 = addLCSSubscriptionEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSSubscriptionEntry1.getPrimaryKey());
		primaryKeys.add(newLCSSubscriptionEntry2.getPrimaryKey());

		Map<Serializable, LCSSubscriptionEntry> lcsSubscriptionEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, lcsSubscriptionEntries.size());
		Assert.assertEquals(newLCSSubscriptionEntry1,
			lcsSubscriptionEntries.get(newLCSSubscriptionEntry1.getPrimaryKey()));
		Assert.assertEquals(newLCSSubscriptionEntry2,
			lcsSubscriptionEntries.get(newLCSSubscriptionEntry2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, LCSSubscriptionEntry> lcsSubscriptionEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(lcsSubscriptionEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		LCSSubscriptionEntry newLCSSubscriptionEntry = addLCSSubscriptionEntry();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSSubscriptionEntry.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, LCSSubscriptionEntry> lcsSubscriptionEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, lcsSubscriptionEntries.size());
		Assert.assertEquals(newLCSSubscriptionEntry,
			lcsSubscriptionEntries.get(newLCSSubscriptionEntry.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, LCSSubscriptionEntry> lcsSubscriptionEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(lcsSubscriptionEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		LCSSubscriptionEntry newLCSSubscriptionEntry = addLCSSubscriptionEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSSubscriptionEntry.getPrimaryKey());

		Map<Serializable, LCSSubscriptionEntry> lcsSubscriptionEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, lcsSubscriptionEntries.size());
		Assert.assertEquals(newLCSSubscriptionEntry,
			lcsSubscriptionEntries.get(newLCSSubscriptionEntry.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = LCSSubscriptionEntryLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<LCSSubscriptionEntry>() {
				@Override
				public void performAction(
					LCSSubscriptionEntry lcsSubscriptionEntry) {
					Assert.assertNotNull(lcsSubscriptionEntry);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		LCSSubscriptionEntry newLCSSubscriptionEntry = addLCSSubscriptionEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSSubscriptionEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("lcsSubscriptionEntryId",
				newLCSSubscriptionEntry.getLcsSubscriptionEntryId()));

		List<LCSSubscriptionEntry> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		LCSSubscriptionEntry existingLCSSubscriptionEntry = result.get(0);

		Assert.assertEquals(existingLCSSubscriptionEntry,
			newLCSSubscriptionEntry);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSSubscriptionEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("lcsSubscriptionEntryId",
				RandomTestUtil.nextLong()));

		List<LCSSubscriptionEntry> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		LCSSubscriptionEntry newLCSSubscriptionEntry = addLCSSubscriptionEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSSubscriptionEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"lcsSubscriptionEntryId"));

		Object newLcsSubscriptionEntryId = newLCSSubscriptionEntry.getLcsSubscriptionEntryId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("lcsSubscriptionEntryId",
				new Object[] { newLcsSubscriptionEntryId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingLcsSubscriptionEntryId = result.get(0);

		Assert.assertEquals(existingLcsSubscriptionEntryId,
			newLcsSubscriptionEntryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSSubscriptionEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"lcsSubscriptionEntryId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("lcsSubscriptionEntryId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected LCSSubscriptionEntry addLCSSubscriptionEntry()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSSubscriptionEntry lcsSubscriptionEntry = _persistence.create(pk);

		lcsSubscriptionEntry.setLcsProjectId(RandomTestUtil.nextLong());

		lcsSubscriptionEntry.setActualPrice(RandomTestUtil.nextDouble());

		lcsSubscriptionEntry.setCurrencyCode(RandomTestUtil.randomString());

		lcsSubscriptionEntry.setInstanceSize(RandomTestUtil.nextInt());

		lcsSubscriptionEntry.setType(RandomTestUtil.randomString());

		lcsSubscriptionEntry.setPlatform(RandomTestUtil.randomString());

		lcsSubscriptionEntry.setPlatformVersion(RandomTestUtil.nextInt());

		lcsSubscriptionEntry.setProcessorCoresAllowed(RandomTestUtil.nextInt());

		lcsSubscriptionEntry.setProduct(RandomTestUtil.randomString());

		lcsSubscriptionEntry.setProductVersion(RandomTestUtil.nextInt());

		lcsSubscriptionEntry.setServersAllowed(RandomTestUtil.nextInt());

		lcsSubscriptionEntry.setServersUsed(RandomTestUtil.nextInt());

		lcsSubscriptionEntry.setStartDate(RandomTestUtil.nextDate());

		lcsSubscriptionEntry.setEndDate(RandomTestUtil.nextDate());

		lcsSubscriptionEntry.setSupportStartDate(RandomTestUtil.nextDate());

		lcsSubscriptionEntry.setSupportEndDate(RandomTestUtil.nextDate());

		lcsSubscriptionEntry.setActive(RandomTestUtil.randomBoolean());

		_lcsSubscriptionEntries.add(_persistence.update(lcsSubscriptionEntry));

		return lcsSubscriptionEntry;
	}

	private List<LCSSubscriptionEntry> _lcsSubscriptionEntries = new ArrayList<LCSSubscriptionEntry>();
	private LCSSubscriptionEntryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}