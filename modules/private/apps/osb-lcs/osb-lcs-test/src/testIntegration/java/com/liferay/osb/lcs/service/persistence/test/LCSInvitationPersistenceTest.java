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

import com.liferay.osb.lcs.exception.NoSuchLCSInvitationException;
import com.liferay.osb.lcs.model.LCSInvitation;
import com.liferay.osb.lcs.service.LCSInvitationLocalServiceUtil;
import com.liferay.osb.lcs.service.persistence.LCSInvitationPersistence;
import com.liferay.osb.lcs.service.persistence.LCSInvitationUtil;

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
public class LCSInvitationPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.osb.lcs.service"));

	@Before
	public void setUp() {
		_persistence = LCSInvitationUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<LCSInvitation> iterator = _lcsInvitations.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSInvitation lcsInvitation = _persistence.create(pk);

		Assert.assertNotNull(lcsInvitation);

		Assert.assertEquals(lcsInvitation.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		LCSInvitation newLCSInvitation = addLCSInvitation();

		_persistence.remove(newLCSInvitation);

		LCSInvitation existingLCSInvitation = _persistence.fetchByPrimaryKey(newLCSInvitation.getPrimaryKey());

		Assert.assertNull(existingLCSInvitation);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addLCSInvitation();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSInvitation newLCSInvitation = _persistence.create(pk);

		newLCSInvitation.setUserId(RandomTestUtil.nextLong());

		newLCSInvitation.setCreateDate(RandomTestUtil.nextDate());

		newLCSInvitation.setLcsProjectId(RandomTestUtil.nextLong());

		newLCSInvitation.setEmailAddress(RandomTestUtil.randomString());

		newLCSInvitation.setLcsClusterEntryId(RandomTestUtil.nextLong());

		newLCSInvitation.setRole(RandomTestUtil.nextInt());

		_lcsInvitations.add(_persistence.update(newLCSInvitation));

		LCSInvitation existingLCSInvitation = _persistence.findByPrimaryKey(newLCSInvitation.getPrimaryKey());

		Assert.assertEquals(existingLCSInvitation.getLcsInvitationId(),
			newLCSInvitation.getLcsInvitationId());
		Assert.assertEquals(existingLCSInvitation.getUserId(),
			newLCSInvitation.getUserId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingLCSInvitation.getCreateDate()),
			Time.getShortTimestamp(newLCSInvitation.getCreateDate()));
		Assert.assertEquals(existingLCSInvitation.getLcsProjectId(),
			newLCSInvitation.getLcsProjectId());
		Assert.assertEquals(existingLCSInvitation.getEmailAddress(),
			newLCSInvitation.getEmailAddress());
		Assert.assertEquals(existingLCSInvitation.getLcsClusterEntryId(),
			newLCSInvitation.getLcsClusterEntryId());
		Assert.assertEquals(existingLCSInvitation.getRole(),
			newLCSInvitation.getRole());
	}

	@Test
	public void testCountByLCSProjectId() throws Exception {
		_persistence.countByLCSProjectId(RandomTestUtil.nextLong());

		_persistence.countByLCSProjectId(0L);
	}

	@Test
	public void testCountByEmailAddress() throws Exception {
		_persistence.countByEmailAddress(StringPool.BLANK);

		_persistence.countByEmailAddress(StringPool.NULL);

		_persistence.countByEmailAddress((String)null);
	}

	@Test
	public void testCountByLPI_EA() throws Exception {
		_persistence.countByLPI_EA(RandomTestUtil.nextLong(), StringPool.BLANK);

		_persistence.countByLPI_EA(0L, StringPool.NULL);

		_persistence.countByLPI_EA(0L, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		LCSInvitation newLCSInvitation = addLCSInvitation();

		LCSInvitation existingLCSInvitation = _persistence.findByPrimaryKey(newLCSInvitation.getPrimaryKey());

		Assert.assertEquals(existingLCSInvitation, newLCSInvitation);
	}

	@Test(expected = NoSuchLCSInvitationException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<LCSInvitation> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("OSBLCS_LCSInvitation",
			"lcsInvitationId", true, "userId", true, "createDate", true,
			"lcsProjectId", true, "emailAddress", true, "lcsClusterEntryId",
			true, "role", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		LCSInvitation newLCSInvitation = addLCSInvitation();

		LCSInvitation existingLCSInvitation = _persistence.fetchByPrimaryKey(newLCSInvitation.getPrimaryKey());

		Assert.assertEquals(existingLCSInvitation, newLCSInvitation);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSInvitation missingLCSInvitation = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingLCSInvitation);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		LCSInvitation newLCSInvitation1 = addLCSInvitation();
		LCSInvitation newLCSInvitation2 = addLCSInvitation();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSInvitation1.getPrimaryKey());
		primaryKeys.add(newLCSInvitation2.getPrimaryKey());

		Map<Serializable, LCSInvitation> lcsInvitations = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, lcsInvitations.size());
		Assert.assertEquals(newLCSInvitation1,
			lcsInvitations.get(newLCSInvitation1.getPrimaryKey()));
		Assert.assertEquals(newLCSInvitation2,
			lcsInvitations.get(newLCSInvitation2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, LCSInvitation> lcsInvitations = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(lcsInvitations.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		LCSInvitation newLCSInvitation = addLCSInvitation();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSInvitation.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, LCSInvitation> lcsInvitations = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, lcsInvitations.size());
		Assert.assertEquals(newLCSInvitation,
			lcsInvitations.get(newLCSInvitation.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, LCSInvitation> lcsInvitations = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(lcsInvitations.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		LCSInvitation newLCSInvitation = addLCSInvitation();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSInvitation.getPrimaryKey());

		Map<Serializable, LCSInvitation> lcsInvitations = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, lcsInvitations.size());
		Assert.assertEquals(newLCSInvitation,
			lcsInvitations.get(newLCSInvitation.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = LCSInvitationLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<LCSInvitation>() {
				@Override
				public void performAction(LCSInvitation lcsInvitation) {
					Assert.assertNotNull(lcsInvitation);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		LCSInvitation newLCSInvitation = addLCSInvitation();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSInvitation.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("lcsInvitationId",
				newLCSInvitation.getLcsInvitationId()));

		List<LCSInvitation> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		LCSInvitation existingLCSInvitation = result.get(0);

		Assert.assertEquals(existingLCSInvitation, newLCSInvitation);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSInvitation.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("lcsInvitationId",
				RandomTestUtil.nextLong()));

		List<LCSInvitation> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		LCSInvitation newLCSInvitation = addLCSInvitation();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSInvitation.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"lcsInvitationId"));

		Object newLcsInvitationId = newLCSInvitation.getLcsInvitationId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("lcsInvitationId",
				new Object[] { newLcsInvitationId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingLcsInvitationId = result.get(0);

		Assert.assertEquals(existingLcsInvitationId, newLcsInvitationId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSInvitation.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"lcsInvitationId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("lcsInvitationId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		LCSInvitation newLCSInvitation = addLCSInvitation();

		_persistence.clearCache();

		LCSInvitation existingLCSInvitation = _persistence.findByPrimaryKey(newLCSInvitation.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(
				existingLCSInvitation.getLcsProjectId()),
			ReflectionTestUtil.<Long>invoke(existingLCSInvitation,
				"getOriginalLcsProjectId", new Class<?>[0]));
		Assert.assertTrue(Objects.equals(
				existingLCSInvitation.getEmailAddress(),
				ReflectionTestUtil.invoke(existingLCSInvitation,
					"getOriginalEmailAddress", new Class<?>[0])));
	}

	protected LCSInvitation addLCSInvitation() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSInvitation lcsInvitation = _persistence.create(pk);

		lcsInvitation.setUserId(RandomTestUtil.nextLong());

		lcsInvitation.setCreateDate(RandomTestUtil.nextDate());

		lcsInvitation.setLcsProjectId(RandomTestUtil.nextLong());

		lcsInvitation.setEmailAddress(RandomTestUtil.randomString());

		lcsInvitation.setLcsClusterEntryId(RandomTestUtil.nextLong());

		lcsInvitation.setRole(RandomTestUtil.nextInt());

		_lcsInvitations.add(_persistence.update(lcsInvitation));

		return lcsInvitation;
	}

	private List<LCSInvitation> _lcsInvitations = new ArrayList<LCSInvitation>();
	private LCSInvitationPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}