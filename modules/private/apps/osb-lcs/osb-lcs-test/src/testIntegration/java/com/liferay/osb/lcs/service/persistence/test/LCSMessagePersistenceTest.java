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

import com.liferay.osb.lcs.exception.NoSuchLCSMessageException;
import com.liferay.osb.lcs.model.LCSMessage;
import com.liferay.osb.lcs.service.LCSMessageLocalServiceUtil;
import com.liferay.osb.lcs.service.persistence.LCSMessagePersistence;
import com.liferay.osb.lcs.service.persistence.LCSMessageUtil;

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
import java.util.Objects;
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class LCSMessagePersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.osb.lcs.service"));

	@Before
	public void setUp() {
		_persistence = LCSMessageUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<LCSMessage> iterator = _lcsMessages.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSMessage lcsMessage = _persistence.create(pk);

		Assert.assertNotNull(lcsMessage);

		Assert.assertEquals(lcsMessage.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		LCSMessage newLCSMessage = addLCSMessage();

		_persistence.remove(newLCSMessage);

		LCSMessage existingLCSMessage = _persistence.fetchByPrimaryKey(newLCSMessage.getPrimaryKey());

		Assert.assertNull(existingLCSMessage);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addLCSMessage();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSMessage newLCSMessage = _persistence.create(pk);

		newLCSMessage.setCreateDate(RandomTestUtil.nextDate());

		newLCSMessage.setModifiedDate(RandomTestUtil.nextDate());

		newLCSMessage.setSourceMessageId(RandomTestUtil.nextLong());

		newLCSMessage.setSourceSystemName(RandomTestUtil.randomString());

		newLCSMessage.setClassNameId(RandomTestUtil.nextLong());

		newLCSMessage.setClassPK(RandomTestUtil.nextLong());

		newLCSMessage.setContent(RandomTestUtil.randomString());

		newLCSMessage.setEndDate(RandomTestUtil.nextDate());

		newLCSMessage.setGlobal(RandomTestUtil.randomBoolean());

		newLCSMessage.setSeverityLevel(RandomTestUtil.nextInt());

		newLCSMessage.setType(RandomTestUtil.nextInt());

		_lcsMessages.add(_persistence.update(newLCSMessage));

		LCSMessage existingLCSMessage = _persistence.findByPrimaryKey(newLCSMessage.getPrimaryKey());

		Assert.assertEquals(existingLCSMessage.getLcsMessageId(),
			newLCSMessage.getLcsMessageId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingLCSMessage.getCreateDate()),
			Time.getShortTimestamp(newLCSMessage.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingLCSMessage.getModifiedDate()),
			Time.getShortTimestamp(newLCSMessage.getModifiedDate()));
		Assert.assertEquals(existingLCSMessage.getSourceMessageId(),
			newLCSMessage.getSourceMessageId());
		Assert.assertEquals(existingLCSMessage.getSourceSystemName(),
			newLCSMessage.getSourceSystemName());
		Assert.assertEquals(existingLCSMessage.getClassNameId(),
			newLCSMessage.getClassNameId());
		Assert.assertEquals(existingLCSMessage.getClassPK(),
			newLCSMessage.getClassPK());
		Assert.assertEquals(existingLCSMessage.getContent(),
			newLCSMessage.getContent());
		Assert.assertEquals(Time.getShortTimestamp(
				existingLCSMessage.getEndDate()),
			Time.getShortTimestamp(newLCSMessage.getEndDate()));
		Assert.assertEquals(existingLCSMessage.getGlobal(),
			newLCSMessage.getGlobal());
		Assert.assertEquals(existingLCSMessage.getSeverityLevel(),
			newLCSMessage.getSeverityLevel());
		Assert.assertEquals(existingLCSMessage.getType(),
			newLCSMessage.getType());
	}

	@Test
	public void testCountByC_C() throws Exception {
		_persistence.countByC_C(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByC_C(0L, 0L);
	}

	@Test
	public void testCountByED_G() throws Exception {
		_persistence.countByED_G(RandomTestUtil.nextDate(),
			RandomTestUtil.randomBoolean());

		_persistence.countByED_G(RandomTestUtil.nextDate(),
			RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByS_C_C() throws Exception {
		_persistence.countByS_C_C(StringPool.BLANK, RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByS_C_C(StringPool.NULL, 0L, 0L);

		_persistence.countByS_C_C((String)null, 0L, 0L);
	}

	@Test
	public void testCountByC_C_T() throws Exception {
		_persistence.countByC_C_T(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextInt());

		_persistence.countByC_C_T(0L, 0L, 0);
	}

	@Test
	public void testCountByS_S_C_C() throws Exception {
		_persistence.countByS_S_C_C(RandomTestUtil.nextLong(),
			StringPool.BLANK, RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByS_S_C_C(0L, StringPool.NULL, 0L, 0L);

		_persistence.countByS_S_C_C(0L, (String)null, 0L, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		LCSMessage newLCSMessage = addLCSMessage();

		LCSMessage existingLCSMessage = _persistence.findByPrimaryKey(newLCSMessage.getPrimaryKey());

		Assert.assertEquals(existingLCSMessage, newLCSMessage);
	}

	@Test(expected = NoSuchLCSMessageException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<LCSMessage> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("OSBLCS_LCSMessage",
			"lcsMessageId", true, "createDate", true, "modifiedDate", true,
			"sourceMessageId", true, "sourceSystemName", true, "classNameId",
			true, "classPK", true, "content", true, "endDate", true, "global",
			true, "severityLevel", true, "type", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		LCSMessage newLCSMessage = addLCSMessage();

		LCSMessage existingLCSMessage = _persistence.fetchByPrimaryKey(newLCSMessage.getPrimaryKey());

		Assert.assertEquals(existingLCSMessage, newLCSMessage);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSMessage missingLCSMessage = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingLCSMessage);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		LCSMessage newLCSMessage1 = addLCSMessage();
		LCSMessage newLCSMessage2 = addLCSMessage();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSMessage1.getPrimaryKey());
		primaryKeys.add(newLCSMessage2.getPrimaryKey());

		Map<Serializable, LCSMessage> lcsMessages = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, lcsMessages.size());
		Assert.assertEquals(newLCSMessage1,
			lcsMessages.get(newLCSMessage1.getPrimaryKey()));
		Assert.assertEquals(newLCSMessage2,
			lcsMessages.get(newLCSMessage2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, LCSMessage> lcsMessages = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(lcsMessages.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		LCSMessage newLCSMessage = addLCSMessage();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSMessage.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, LCSMessage> lcsMessages = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, lcsMessages.size());
		Assert.assertEquals(newLCSMessage,
			lcsMessages.get(newLCSMessage.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, LCSMessage> lcsMessages = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(lcsMessages.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		LCSMessage newLCSMessage = addLCSMessage();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSMessage.getPrimaryKey());

		Map<Serializable, LCSMessage> lcsMessages = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, lcsMessages.size());
		Assert.assertEquals(newLCSMessage,
			lcsMessages.get(newLCSMessage.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = LCSMessageLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<LCSMessage>() {
				@Override
				public void performAction(LCSMessage lcsMessage) {
					Assert.assertNotNull(lcsMessage);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		LCSMessage newLCSMessage = addLCSMessage();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSMessage.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("lcsMessageId",
				newLCSMessage.getLcsMessageId()));

		List<LCSMessage> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		LCSMessage existingLCSMessage = result.get(0);

		Assert.assertEquals(existingLCSMessage, newLCSMessage);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSMessage.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("lcsMessageId",
				RandomTestUtil.nextLong()));

		List<LCSMessage> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		LCSMessage newLCSMessage = addLCSMessage();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSMessage.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"lcsMessageId"));

		Object newLcsMessageId = newLCSMessage.getLcsMessageId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("lcsMessageId",
				new Object[] { newLcsMessageId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingLcsMessageId = result.get(0);

		Assert.assertEquals(existingLcsMessageId, newLcsMessageId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSMessage.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"lcsMessageId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("lcsMessageId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		LCSMessage newLCSMessage = addLCSMessage();

		_persistence.clearCache();

		LCSMessage existingLCSMessage = _persistence.findByPrimaryKey(newLCSMessage.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(
				existingLCSMessage.getSourceMessageId()),
			ReflectionTestUtil.<Long>invoke(existingLCSMessage,
				"getOriginalSourceMessageId", new Class<?>[0]));
		Assert.assertTrue(Objects.equals(
				existingLCSMessage.getSourceSystemName(),
				ReflectionTestUtil.invoke(existingLCSMessage,
					"getOriginalSourceSystemName", new Class<?>[0])));
		Assert.assertEquals(Long.valueOf(existingLCSMessage.getClassNameId()),
			ReflectionTestUtil.<Long>invoke(existingLCSMessage,
				"getOriginalClassNameId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(existingLCSMessage.getClassPK()),
			ReflectionTestUtil.<Long>invoke(existingLCSMessage,
				"getOriginalClassPK", new Class<?>[0]));
	}

	protected LCSMessage addLCSMessage() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSMessage lcsMessage = _persistence.create(pk);

		lcsMessage.setCreateDate(RandomTestUtil.nextDate());

		lcsMessage.setModifiedDate(RandomTestUtil.nextDate());

		lcsMessage.setSourceMessageId(RandomTestUtil.nextLong());

		lcsMessage.setSourceSystemName(RandomTestUtil.randomString());

		lcsMessage.setClassNameId(RandomTestUtil.nextLong());

		lcsMessage.setClassPK(RandomTestUtil.nextLong());

		lcsMessage.setContent(RandomTestUtil.randomString());

		lcsMessage.setEndDate(RandomTestUtil.nextDate());

		lcsMessage.setGlobal(RandomTestUtil.randomBoolean());

		lcsMessage.setSeverityLevel(RandomTestUtil.nextInt());

		lcsMessage.setType(RandomTestUtil.nextInt());

		_lcsMessages.add(_persistence.update(lcsMessage));

		return lcsMessage;
	}

	private List<LCSMessage> _lcsMessages = new ArrayList<LCSMessage>();
	private LCSMessagePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}