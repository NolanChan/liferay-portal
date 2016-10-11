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

import com.liferay.osb.lcs.exception.NoSuchLCSRoleException;
import com.liferay.osb.lcs.model.LCSRole;
import com.liferay.osb.lcs.service.LCSRoleLocalServiceUtil;
import com.liferay.osb.lcs.service.persistence.LCSRolePersistence;
import com.liferay.osb.lcs.service.persistence.LCSRoleUtil;

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
public class LCSRolePersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = LCSRoleUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<LCSRole> iterator = _lcsRoles.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSRole lcsRole = _persistence.create(pk);

		Assert.assertNotNull(lcsRole);

		Assert.assertEquals(lcsRole.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		LCSRole newLCSRole = addLCSRole();

		_persistence.remove(newLCSRole);

		LCSRole existingLCSRole = _persistence.fetchByPrimaryKey(newLCSRole.getPrimaryKey());

		Assert.assertNull(existingLCSRole);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addLCSRole();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSRole newLCSRole = _persistence.create(pk);

		newLCSRole.setUserId(RandomTestUtil.nextLong());

		newLCSRole.setLcsProjectId(RandomTestUtil.nextLong());

		newLCSRole.setLcsClusterEntryId(RandomTestUtil.nextLong());

		newLCSRole.setRole(RandomTestUtil.nextInt());

		_lcsRoles.add(_persistence.update(newLCSRole));

		LCSRole existingLCSRole = _persistence.findByPrimaryKey(newLCSRole.getPrimaryKey());

		Assert.assertEquals(existingLCSRole.getLcsRoleId(),
			newLCSRole.getLcsRoleId());
		Assert.assertEquals(existingLCSRole.getUserId(), newLCSRole.getUserId());
		Assert.assertEquals(existingLCSRole.getLcsProjectId(),
			newLCSRole.getLcsProjectId());
		Assert.assertEquals(existingLCSRole.getLcsClusterEntryId(),
			newLCSRole.getLcsClusterEntryId());
		Assert.assertEquals(existingLCSRole.getRole(), newLCSRole.getRole());
	}

	@Test
	public void testCountByUserId() throws Exception {
		_persistence.countByUserId(RandomTestUtil.nextLong());

		_persistence.countByUserId(0L);
	}

	@Test
	public void testCountByLCSProjectId() throws Exception {
		_persistence.countByLCSProjectId(RandomTestUtil.nextLong());

		_persistence.countByLCSProjectId(0L);
	}

	@Test
	public void testCountByLCSClusterEntryId() throws Exception {
		_persistence.countByLCSClusterEntryId(RandomTestUtil.nextLong());

		_persistence.countByLCSClusterEntryId(0L);
	}

	@Test
	public void testCountByU_LPI() throws Exception {
		_persistence.countByU_LPI(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByU_LPI(0L, 0L);
	}

	@Test
	public void testCountByU_R() throws Exception {
		_persistence.countByU_R(RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countByU_R(0L, 0);
	}

	@Test
	public void testCountByLPI_R() throws Exception {
		_persistence.countByLPI_R(RandomTestUtil.nextLong(),
			RandomTestUtil.nextInt());

		_persistence.countByLPI_R(0L, 0);
	}

	@Test
	public void testCountByU_LPI_LCEI() throws Exception {
		_persistence.countByU_LPI_LCEI(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByU_LPI_LCEI(0L, 0L, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		LCSRole newLCSRole = addLCSRole();

		LCSRole existingLCSRole = _persistence.findByPrimaryKey(newLCSRole.getPrimaryKey());

		Assert.assertEquals(existingLCSRole, newLCSRole);
	}

	@Test(expected = NoSuchLCSRoleException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<LCSRole> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("OSBLCS_LCSRole",
			"lcsRoleId", true, "userId", true, "lcsProjectId", true,
			"lcsClusterEntryId", true, "role", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		LCSRole newLCSRole = addLCSRole();

		LCSRole existingLCSRole = _persistence.fetchByPrimaryKey(newLCSRole.getPrimaryKey());

		Assert.assertEquals(existingLCSRole, newLCSRole);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSRole missingLCSRole = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingLCSRole);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		LCSRole newLCSRole1 = addLCSRole();
		LCSRole newLCSRole2 = addLCSRole();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSRole1.getPrimaryKey());
		primaryKeys.add(newLCSRole2.getPrimaryKey());

		Map<Serializable, LCSRole> lcsRoles = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, lcsRoles.size());
		Assert.assertEquals(newLCSRole1,
			lcsRoles.get(newLCSRole1.getPrimaryKey()));
		Assert.assertEquals(newLCSRole2,
			lcsRoles.get(newLCSRole2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, LCSRole> lcsRoles = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(lcsRoles.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		LCSRole newLCSRole = addLCSRole();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSRole.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, LCSRole> lcsRoles = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, lcsRoles.size());
		Assert.assertEquals(newLCSRole, lcsRoles.get(newLCSRole.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, LCSRole> lcsRoles = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(lcsRoles.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		LCSRole newLCSRole = addLCSRole();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSRole.getPrimaryKey());

		Map<Serializable, LCSRole> lcsRoles = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, lcsRoles.size());
		Assert.assertEquals(newLCSRole, lcsRoles.get(newLCSRole.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = LCSRoleLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<LCSRole>() {
				@Override
				public void performAction(LCSRole lcsRole) {
					Assert.assertNotNull(lcsRole);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		LCSRole newLCSRole = addLCSRole();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSRole.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("lcsRoleId",
				newLCSRole.getLcsRoleId()));

		List<LCSRole> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		LCSRole existingLCSRole = result.get(0);

		Assert.assertEquals(existingLCSRole, newLCSRole);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSRole.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("lcsRoleId",
				RandomTestUtil.nextLong()));

		List<LCSRole> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		LCSRole newLCSRole = addLCSRole();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSRole.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("lcsRoleId"));

		Object newLcsRoleId = newLCSRole.getLcsRoleId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("lcsRoleId",
				new Object[] { newLcsRoleId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingLcsRoleId = result.get(0);

		Assert.assertEquals(existingLcsRoleId, newLcsRoleId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSRole.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("lcsRoleId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("lcsRoleId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		LCSRole newLCSRole = addLCSRole();

		_persistence.clearCache();

		LCSRole existingLCSRole = _persistence.findByPrimaryKey(newLCSRole.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(existingLCSRole.getUserId()),
			ReflectionTestUtil.<Long>invoke(existingLCSRole,
				"getOriginalUserId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(existingLCSRole.getLcsProjectId()),
			ReflectionTestUtil.<Long>invoke(existingLCSRole,
				"getOriginalLcsProjectId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(existingLCSRole.getLcsClusterEntryId()),
			ReflectionTestUtil.<Long>invoke(existingLCSRole,
				"getOriginalLcsClusterEntryId", new Class<?>[0]));
	}

	protected LCSRole addLCSRole() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSRole lcsRole = _persistence.create(pk);

		lcsRole.setUserId(RandomTestUtil.nextLong());

		lcsRole.setLcsProjectId(RandomTestUtil.nextLong());

		lcsRole.setLcsClusterEntryId(RandomTestUtil.nextLong());

		lcsRole.setRole(RandomTestUtil.nextInt());

		_lcsRoles.add(_persistence.update(lcsRole));

		return lcsRole;
	}

	private List<LCSRole> _lcsRoles = new ArrayList<LCSRole>();
	private LCSRolePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}