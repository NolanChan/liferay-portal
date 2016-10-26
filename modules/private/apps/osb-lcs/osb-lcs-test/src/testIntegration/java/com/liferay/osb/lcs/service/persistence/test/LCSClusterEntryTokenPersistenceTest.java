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

import com.liferay.osb.lcs.exception.NoSuchLCSClusterEntryTokenException;
import com.liferay.osb.lcs.model.LCSClusterEntryToken;
import com.liferay.osb.lcs.service.LCSClusterEntryTokenLocalServiceUtil;
import com.liferay.osb.lcs.service.persistence.LCSClusterEntryTokenPersistence;
import com.liferay.osb.lcs.service.persistence.LCSClusterEntryTokenUtil;

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
public class LCSClusterEntryTokenPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.osb.lcs.service"));

	@Before
	public void setUp() {
		_persistence = LCSClusterEntryTokenUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<LCSClusterEntryToken> iterator = _lcsClusterEntryTokens.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSClusterEntryToken lcsClusterEntryToken = _persistence.create(pk);

		Assert.assertNotNull(lcsClusterEntryToken);

		Assert.assertEquals(lcsClusterEntryToken.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		LCSClusterEntryToken newLCSClusterEntryToken = addLCSClusterEntryToken();

		_persistence.remove(newLCSClusterEntryToken);

		LCSClusterEntryToken existingLCSClusterEntryToken = _persistence.fetchByPrimaryKey(newLCSClusterEntryToken.getPrimaryKey());

		Assert.assertNull(existingLCSClusterEntryToken);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addLCSClusterEntryToken();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSClusterEntryToken newLCSClusterEntryToken = _persistence.create(pk);

		newLCSClusterEntryToken.setUserId(RandomTestUtil.nextLong());

		newLCSClusterEntryToken.setCreateDate(RandomTestUtil.nextDate());

		newLCSClusterEntryToken.setLcsClusterEntryId(RandomTestUtil.nextLong());

		newLCSClusterEntryToken.setContent(RandomTestUtil.randomString());

		_lcsClusterEntryTokens.add(_persistence.update(newLCSClusterEntryToken));

		LCSClusterEntryToken existingLCSClusterEntryToken = _persistence.findByPrimaryKey(newLCSClusterEntryToken.getPrimaryKey());

		Assert.assertEquals(existingLCSClusterEntryToken.getLcsClusterEntryTokenId(),
			newLCSClusterEntryToken.getLcsClusterEntryTokenId());
		Assert.assertEquals(existingLCSClusterEntryToken.getUserId(),
			newLCSClusterEntryToken.getUserId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingLCSClusterEntryToken.getCreateDate()),
			Time.getShortTimestamp(newLCSClusterEntryToken.getCreateDate()));
		Assert.assertEquals(existingLCSClusterEntryToken.getLcsClusterEntryId(),
			newLCSClusterEntryToken.getLcsClusterEntryId());
		Assert.assertEquals(existingLCSClusterEntryToken.getContent(),
			newLCSClusterEntryToken.getContent());
	}

	@Test
	public void testCountByLCSClusterEntryId() throws Exception {
		_persistence.countByLCSClusterEntryId(RandomTestUtil.nextLong());

		_persistence.countByLCSClusterEntryId(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		LCSClusterEntryToken newLCSClusterEntryToken = addLCSClusterEntryToken();

		LCSClusterEntryToken existingLCSClusterEntryToken = _persistence.findByPrimaryKey(newLCSClusterEntryToken.getPrimaryKey());

		Assert.assertEquals(existingLCSClusterEntryToken,
			newLCSClusterEntryToken);
	}

	@Test(expected = NoSuchLCSClusterEntryTokenException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<LCSClusterEntryToken> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("OSBLCS_LCSClusterEntryToken",
			"lcsClusterEntryTokenId", true, "userId", true, "createDate", true,
			"lcsClusterEntryId", true, "content", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		LCSClusterEntryToken newLCSClusterEntryToken = addLCSClusterEntryToken();

		LCSClusterEntryToken existingLCSClusterEntryToken = _persistence.fetchByPrimaryKey(newLCSClusterEntryToken.getPrimaryKey());

		Assert.assertEquals(existingLCSClusterEntryToken,
			newLCSClusterEntryToken);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSClusterEntryToken missingLCSClusterEntryToken = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingLCSClusterEntryToken);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		LCSClusterEntryToken newLCSClusterEntryToken1 = addLCSClusterEntryToken();
		LCSClusterEntryToken newLCSClusterEntryToken2 = addLCSClusterEntryToken();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSClusterEntryToken1.getPrimaryKey());
		primaryKeys.add(newLCSClusterEntryToken2.getPrimaryKey());

		Map<Serializable, LCSClusterEntryToken> lcsClusterEntryTokens = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, lcsClusterEntryTokens.size());
		Assert.assertEquals(newLCSClusterEntryToken1,
			lcsClusterEntryTokens.get(newLCSClusterEntryToken1.getPrimaryKey()));
		Assert.assertEquals(newLCSClusterEntryToken2,
			lcsClusterEntryTokens.get(newLCSClusterEntryToken2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, LCSClusterEntryToken> lcsClusterEntryTokens = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(lcsClusterEntryTokens.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		LCSClusterEntryToken newLCSClusterEntryToken = addLCSClusterEntryToken();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSClusterEntryToken.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, LCSClusterEntryToken> lcsClusterEntryTokens = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, lcsClusterEntryTokens.size());
		Assert.assertEquals(newLCSClusterEntryToken,
			lcsClusterEntryTokens.get(newLCSClusterEntryToken.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, LCSClusterEntryToken> lcsClusterEntryTokens = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(lcsClusterEntryTokens.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		LCSClusterEntryToken newLCSClusterEntryToken = addLCSClusterEntryToken();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSClusterEntryToken.getPrimaryKey());

		Map<Serializable, LCSClusterEntryToken> lcsClusterEntryTokens = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, lcsClusterEntryTokens.size());
		Assert.assertEquals(newLCSClusterEntryToken,
			lcsClusterEntryTokens.get(newLCSClusterEntryToken.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = LCSClusterEntryTokenLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<LCSClusterEntryToken>() {
				@Override
				public void performAction(
					LCSClusterEntryToken lcsClusterEntryToken) {
					Assert.assertNotNull(lcsClusterEntryToken);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		LCSClusterEntryToken newLCSClusterEntryToken = addLCSClusterEntryToken();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSClusterEntryToken.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("lcsClusterEntryTokenId",
				newLCSClusterEntryToken.getLcsClusterEntryTokenId()));

		List<LCSClusterEntryToken> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		LCSClusterEntryToken existingLCSClusterEntryToken = result.get(0);

		Assert.assertEquals(existingLCSClusterEntryToken,
			newLCSClusterEntryToken);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSClusterEntryToken.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("lcsClusterEntryTokenId",
				RandomTestUtil.nextLong()));

		List<LCSClusterEntryToken> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		LCSClusterEntryToken newLCSClusterEntryToken = addLCSClusterEntryToken();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSClusterEntryToken.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"lcsClusterEntryTokenId"));

		Object newLcsClusterEntryTokenId = newLCSClusterEntryToken.getLcsClusterEntryTokenId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("lcsClusterEntryTokenId",
				new Object[] { newLcsClusterEntryTokenId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingLcsClusterEntryTokenId = result.get(0);

		Assert.assertEquals(existingLcsClusterEntryTokenId,
			newLcsClusterEntryTokenId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSClusterEntryToken.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"lcsClusterEntryTokenId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("lcsClusterEntryTokenId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		LCSClusterEntryToken newLCSClusterEntryToken = addLCSClusterEntryToken();

		_persistence.clearCache();

		LCSClusterEntryToken existingLCSClusterEntryToken = _persistence.findByPrimaryKey(newLCSClusterEntryToken.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(
				existingLCSClusterEntryToken.getLcsClusterEntryId()),
			ReflectionTestUtil.<Long>invoke(existingLCSClusterEntryToken,
				"getOriginalLcsClusterEntryId", new Class<?>[0]));
	}

	protected LCSClusterEntryToken addLCSClusterEntryToken()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSClusterEntryToken lcsClusterEntryToken = _persistence.create(pk);

		lcsClusterEntryToken.setUserId(RandomTestUtil.nextLong());

		lcsClusterEntryToken.setCreateDate(RandomTestUtil.nextDate());

		lcsClusterEntryToken.setLcsClusterEntryId(RandomTestUtil.nextLong());

		lcsClusterEntryToken.setContent(RandomTestUtil.randomString());

		_lcsClusterEntryTokens.add(_persistence.update(lcsClusterEntryToken));

		return lcsClusterEntryToken;
	}

	private List<LCSClusterEntryToken> _lcsClusterEntryTokens = new ArrayList<LCSClusterEntryToken>();
	private LCSClusterEntryTokenPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}