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

import com.liferay.osb.lcs.exception.NoSuchUserLCSMessageException;
import com.liferay.osb.lcs.model.UserLCSMessage;
import com.liferay.osb.lcs.service.UserLCSMessageLocalServiceUtil;
import com.liferay.osb.lcs.service.persistence.UserLCSMessagePersistence;
import com.liferay.osb.lcs.service.persistence.UserLCSMessageUtil;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.TransactionalTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
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
public class UserLCSMessagePersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = UserLCSMessageUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<UserLCSMessage> iterator = _userLCSMessages.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UserLCSMessage userLCSMessage = _persistence.create(pk);

		Assert.assertNotNull(userLCSMessage);

		Assert.assertEquals(userLCSMessage.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		UserLCSMessage newUserLCSMessage = addUserLCSMessage();

		_persistence.remove(newUserLCSMessage);

		UserLCSMessage existingUserLCSMessage = _persistence.fetchByPrimaryKey(newUserLCSMessage.getPrimaryKey());

		Assert.assertNull(existingUserLCSMessage);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addUserLCSMessage();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UserLCSMessage newUserLCSMessage = _persistence.create(pk);

		newUserLCSMessage.setUserId(RandomTestUtil.nextLong());

		newUserLCSMessage.setLcsMessageId(RandomTestUtil.nextLong());

		newUserLCSMessage.setHidden(RandomTestUtil.randomBoolean());

		newUserLCSMessage.setRead(RandomTestUtil.randomBoolean());

		_userLCSMessages.add(_persistence.update(newUserLCSMessage));

		UserLCSMessage existingUserLCSMessage = _persistence.findByPrimaryKey(newUserLCSMessage.getPrimaryKey());

		Assert.assertEquals(existingUserLCSMessage.getUserLcsMessageId(),
			newUserLCSMessage.getUserLcsMessageId());
		Assert.assertEquals(existingUserLCSMessage.getUserId(),
			newUserLCSMessage.getUserId());
		Assert.assertEquals(existingUserLCSMessage.getLcsMessageId(),
			newUserLCSMessage.getLcsMessageId());
		Assert.assertEquals(existingUserLCSMessage.getHidden(),
			newUserLCSMessage.getHidden());
		Assert.assertEquals(existingUserLCSMessage.getRead(),
			newUserLCSMessage.getRead());
	}

	@Test
	public void testCountByUserId() throws Exception {
		_persistence.countByUserId(RandomTestUtil.nextLong());

		_persistence.countByUserId(0L);
	}

	@Test
	public void testCountByLCSMessageId() throws Exception {
		_persistence.countByLCSMessageId(RandomTestUtil.nextLong());

		_persistence.countByLCSMessageId(0L);
	}

	@Test
	public void testCountByU_LMI() throws Exception {
		_persistence.countByU_LMI(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByU_LMI(0L, 0L);
	}

	@Test
	public void testCountByU_H() throws Exception {
		_persistence.countByU_H(RandomTestUtil.nextLong(),
			RandomTestUtil.randomBoolean());

		_persistence.countByU_H(0L, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByU_R() throws Exception {
		_persistence.countByU_R(RandomTestUtil.nextLong(),
			RandomTestUtil.randomBoolean());

		_persistence.countByU_R(0L, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		UserLCSMessage newUserLCSMessage = addUserLCSMessage();

		UserLCSMessage existingUserLCSMessage = _persistence.findByPrimaryKey(newUserLCSMessage.getPrimaryKey());

		Assert.assertEquals(existingUserLCSMessage, newUserLCSMessage);
	}

	@Test(expected = NoSuchUserLCSMessageException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<UserLCSMessage> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("OSBLCS_UserLCSMessage",
			"userLcsMessageId", true, "userId", true, "lcsMessageId", true,
			"hidden", true, "read", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		UserLCSMessage newUserLCSMessage = addUserLCSMessage();

		UserLCSMessage existingUserLCSMessage = _persistence.fetchByPrimaryKey(newUserLCSMessage.getPrimaryKey());

		Assert.assertEquals(existingUserLCSMessage, newUserLCSMessage);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UserLCSMessage missingUserLCSMessage = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingUserLCSMessage);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		UserLCSMessage newUserLCSMessage1 = addUserLCSMessage();
		UserLCSMessage newUserLCSMessage2 = addUserLCSMessage();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUserLCSMessage1.getPrimaryKey());
		primaryKeys.add(newUserLCSMessage2.getPrimaryKey());

		Map<Serializable, UserLCSMessage> userLCSMessages = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, userLCSMessages.size());
		Assert.assertEquals(newUserLCSMessage1,
			userLCSMessages.get(newUserLCSMessage1.getPrimaryKey()));
		Assert.assertEquals(newUserLCSMessage2,
			userLCSMessages.get(newUserLCSMessage2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, UserLCSMessage> userLCSMessages = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(userLCSMessages.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		UserLCSMessage newUserLCSMessage = addUserLCSMessage();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUserLCSMessage.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, UserLCSMessage> userLCSMessages = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, userLCSMessages.size());
		Assert.assertEquals(newUserLCSMessage,
			userLCSMessages.get(newUserLCSMessage.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, UserLCSMessage> userLCSMessages = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(userLCSMessages.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		UserLCSMessage newUserLCSMessage = addUserLCSMessage();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newUserLCSMessage.getPrimaryKey());

		Map<Serializable, UserLCSMessage> userLCSMessages = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, userLCSMessages.size());
		Assert.assertEquals(newUserLCSMessage,
			userLCSMessages.get(newUserLCSMessage.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = UserLCSMessageLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<UserLCSMessage>() {
				@Override
				public void performAction(UserLCSMessage userLCSMessage) {
					Assert.assertNotNull(userLCSMessage);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		UserLCSMessage newUserLCSMessage = addUserLCSMessage();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UserLCSMessage.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("userLcsMessageId",
				newUserLCSMessage.getUserLcsMessageId()));

		List<UserLCSMessage> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		UserLCSMessage existingUserLCSMessage = result.get(0);

		Assert.assertEquals(existingUserLCSMessage, newUserLCSMessage);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UserLCSMessage.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("userLcsMessageId",
				RandomTestUtil.nextLong()));

		List<UserLCSMessage> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		UserLCSMessage newUserLCSMessage = addUserLCSMessage();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UserLCSMessage.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"userLcsMessageId"));

		Object newUserLcsMessageId = newUserLCSMessage.getUserLcsMessageId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("userLcsMessageId",
				new Object[] { newUserLcsMessageId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingUserLcsMessageId = result.get(0);

		Assert.assertEquals(existingUserLcsMessageId, newUserLcsMessageId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(UserLCSMessage.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"userLcsMessageId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("userLcsMessageId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		UserLCSMessage newUserLCSMessage = addUserLCSMessage();

		_persistence.clearCache();

		UserLCSMessage existingUserLCSMessage = _persistence.findByPrimaryKey(newUserLCSMessage.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(existingUserLCSMessage.getUserId()),
			ReflectionTestUtil.<Long>invoke(existingUserLCSMessage,
				"getOriginalUserId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(
				existingUserLCSMessage.getLcsMessageId()),
			ReflectionTestUtil.<Long>invoke(existingUserLCSMessage,
				"getOriginalLcsMessageId", new Class<?>[0]));
	}

	protected UserLCSMessage addUserLCSMessage() throws Exception {
		long pk = RandomTestUtil.nextLong();

		UserLCSMessage userLCSMessage = _persistence.create(pk);

		userLCSMessage.setUserId(RandomTestUtil.nextLong());

		userLCSMessage.setLcsMessageId(RandomTestUtil.nextLong());

		userLCSMessage.setHidden(RandomTestUtil.randomBoolean());

		userLCSMessage.setRead(RandomTestUtil.randomBoolean());

		_userLCSMessages.add(_persistence.update(userLCSMessage));

		return userLCSMessage;
	}

	private List<UserLCSMessage> _userLCSMessages = new ArrayList<UserLCSMessage>();
	private UserLCSMessagePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}