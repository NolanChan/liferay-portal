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

import com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeUptimeException;
import com.liferay.osb.lcs.model.LCSClusterNodeUptime;
import com.liferay.osb.lcs.service.LCSClusterNodeUptimeLocalServiceUtil;
import com.liferay.osb.lcs.service.persistence.LCSClusterNodeUptimePersistence;
import com.liferay.osb.lcs.service.persistence.LCSClusterNodeUptimeUtil;

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
public class LCSClusterNodeUptimePersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.osb.lcs.service"));

	@Before
	public void setUp() {
		_persistence = LCSClusterNodeUptimeUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<LCSClusterNodeUptime> iterator = _lcsClusterNodeUptimes.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSClusterNodeUptime lcsClusterNodeUptime = _persistence.create(pk);

		Assert.assertNotNull(lcsClusterNodeUptime);

		Assert.assertEquals(lcsClusterNodeUptime.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		LCSClusterNodeUptime newLCSClusterNodeUptime = addLCSClusterNodeUptime();

		_persistence.remove(newLCSClusterNodeUptime);

		LCSClusterNodeUptime existingLCSClusterNodeUptime = _persistence.fetchByPrimaryKey(newLCSClusterNodeUptime.getPrimaryKey());

		Assert.assertNull(existingLCSClusterNodeUptime);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addLCSClusterNodeUptime();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSClusterNodeUptime newLCSClusterNodeUptime = _persistence.create(pk);

		newLCSClusterNodeUptime.setLcsClusterNodeId(RandomTestUtil.nextLong());

		newLCSClusterNodeUptime.setStartTime(RandomTestUtil.nextLong());

		newLCSClusterNodeUptime.setEndTime(RandomTestUtil.nextLong());

		_lcsClusterNodeUptimes.add(_persistence.update(newLCSClusterNodeUptime));

		LCSClusterNodeUptime existingLCSClusterNodeUptime = _persistence.findByPrimaryKey(newLCSClusterNodeUptime.getPrimaryKey());

		Assert.assertEquals(existingLCSClusterNodeUptime.getLcsClusterNodeUptimeId(),
			newLCSClusterNodeUptime.getLcsClusterNodeUptimeId());
		Assert.assertEquals(existingLCSClusterNodeUptime.getLcsClusterNodeId(),
			newLCSClusterNodeUptime.getLcsClusterNodeId());
		Assert.assertEquals(existingLCSClusterNodeUptime.getStartTime(),
			newLCSClusterNodeUptime.getStartTime());
		Assert.assertEquals(existingLCSClusterNodeUptime.getEndTime(),
			newLCSClusterNodeUptime.getEndTime());
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
	public void testCountByLCNI_ST() throws Exception {
		_persistence.countByLCNI_ST(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByLCNI_ST(0L, 0L);
	}

	@Test
	public void testCountByLCNI_ET() throws Exception {
		_persistence.countByLCNI_ET(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByLCNI_ET(0L, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		LCSClusterNodeUptime newLCSClusterNodeUptime = addLCSClusterNodeUptime();

		LCSClusterNodeUptime existingLCSClusterNodeUptime = _persistence.findByPrimaryKey(newLCSClusterNodeUptime.getPrimaryKey());

		Assert.assertEquals(existingLCSClusterNodeUptime,
			newLCSClusterNodeUptime);
	}

	@Test(expected = NoSuchLCSClusterNodeUptimeException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<LCSClusterNodeUptime> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("OSBLCS_LCSClusterNodeUptime",
			"lcsClusterNodeUptimeId", true, "lcsClusterNodeId", true,
			"startTime", true, "endTime", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		LCSClusterNodeUptime newLCSClusterNodeUptime = addLCSClusterNodeUptime();

		LCSClusterNodeUptime existingLCSClusterNodeUptime = _persistence.fetchByPrimaryKey(newLCSClusterNodeUptime.getPrimaryKey());

		Assert.assertEquals(existingLCSClusterNodeUptime,
			newLCSClusterNodeUptime);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSClusterNodeUptime missingLCSClusterNodeUptime = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingLCSClusterNodeUptime);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		LCSClusterNodeUptime newLCSClusterNodeUptime1 = addLCSClusterNodeUptime();
		LCSClusterNodeUptime newLCSClusterNodeUptime2 = addLCSClusterNodeUptime();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSClusterNodeUptime1.getPrimaryKey());
		primaryKeys.add(newLCSClusterNodeUptime2.getPrimaryKey());

		Map<Serializable, LCSClusterNodeUptime> lcsClusterNodeUptimes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, lcsClusterNodeUptimes.size());
		Assert.assertEquals(newLCSClusterNodeUptime1,
			lcsClusterNodeUptimes.get(newLCSClusterNodeUptime1.getPrimaryKey()));
		Assert.assertEquals(newLCSClusterNodeUptime2,
			lcsClusterNodeUptimes.get(newLCSClusterNodeUptime2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, LCSClusterNodeUptime> lcsClusterNodeUptimes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(lcsClusterNodeUptimes.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		LCSClusterNodeUptime newLCSClusterNodeUptime = addLCSClusterNodeUptime();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSClusterNodeUptime.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, LCSClusterNodeUptime> lcsClusterNodeUptimes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, lcsClusterNodeUptimes.size());
		Assert.assertEquals(newLCSClusterNodeUptime,
			lcsClusterNodeUptimes.get(newLCSClusterNodeUptime.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, LCSClusterNodeUptime> lcsClusterNodeUptimes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(lcsClusterNodeUptimes.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		LCSClusterNodeUptime newLCSClusterNodeUptime = addLCSClusterNodeUptime();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSClusterNodeUptime.getPrimaryKey());

		Map<Serializable, LCSClusterNodeUptime> lcsClusterNodeUptimes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, lcsClusterNodeUptimes.size());
		Assert.assertEquals(newLCSClusterNodeUptime,
			lcsClusterNodeUptimes.get(newLCSClusterNodeUptime.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = LCSClusterNodeUptimeLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<LCSClusterNodeUptime>() {
				@Override
				public void performAction(
					LCSClusterNodeUptime lcsClusterNodeUptime) {
					Assert.assertNotNull(lcsClusterNodeUptime);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		LCSClusterNodeUptime newLCSClusterNodeUptime = addLCSClusterNodeUptime();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSClusterNodeUptime.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("lcsClusterNodeUptimeId",
				newLCSClusterNodeUptime.getLcsClusterNodeUptimeId()));

		List<LCSClusterNodeUptime> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		LCSClusterNodeUptime existingLCSClusterNodeUptime = result.get(0);

		Assert.assertEquals(existingLCSClusterNodeUptime,
			newLCSClusterNodeUptime);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSClusterNodeUptime.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("lcsClusterNodeUptimeId",
				RandomTestUtil.nextLong()));

		List<LCSClusterNodeUptime> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		LCSClusterNodeUptime newLCSClusterNodeUptime = addLCSClusterNodeUptime();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSClusterNodeUptime.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"lcsClusterNodeUptimeId"));

		Object newLcsClusterNodeUptimeId = newLCSClusterNodeUptime.getLcsClusterNodeUptimeId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("lcsClusterNodeUptimeId",
				new Object[] { newLcsClusterNodeUptimeId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingLcsClusterNodeUptimeId = result.get(0);

		Assert.assertEquals(existingLcsClusterNodeUptimeId,
			newLcsClusterNodeUptimeId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSClusterNodeUptime.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"lcsClusterNodeUptimeId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("lcsClusterNodeUptimeId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		LCSClusterNodeUptime newLCSClusterNodeUptime = addLCSClusterNodeUptime();

		_persistence.clearCache();

		LCSClusterNodeUptime existingLCSClusterNodeUptime = _persistence.findByPrimaryKey(newLCSClusterNodeUptime.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(
				existingLCSClusterNodeUptime.getLcsClusterNodeId()),
			ReflectionTestUtil.<Long>invoke(existingLCSClusterNodeUptime,
				"getOriginalLcsClusterNodeId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(
				existingLCSClusterNodeUptime.getStartTime()),
			ReflectionTestUtil.<Long>invoke(existingLCSClusterNodeUptime,
				"getOriginalStartTime", new Class<?>[0]));

		Assert.assertEquals(Long.valueOf(
				existingLCSClusterNodeUptime.getLcsClusterNodeId()),
			ReflectionTestUtil.<Long>invoke(existingLCSClusterNodeUptime,
				"getOriginalLcsClusterNodeId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(
				existingLCSClusterNodeUptime.getEndTime()),
			ReflectionTestUtil.<Long>invoke(existingLCSClusterNodeUptime,
				"getOriginalEndTime", new Class<?>[0]));
	}

	protected LCSClusterNodeUptime addLCSClusterNodeUptime()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSClusterNodeUptime lcsClusterNodeUptime = _persistence.create(pk);

		lcsClusterNodeUptime.setLcsClusterNodeId(RandomTestUtil.nextLong());

		lcsClusterNodeUptime.setStartTime(RandomTestUtil.nextLong());

		lcsClusterNodeUptime.setEndTime(RandomTestUtil.nextLong());

		_lcsClusterNodeUptimes.add(_persistence.update(lcsClusterNodeUptime));

		return lcsClusterNodeUptime;
	}

	private List<LCSClusterNodeUptime> _lcsClusterNodeUptimes = new ArrayList<LCSClusterNodeUptime>();
	private LCSClusterNodeUptimePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}