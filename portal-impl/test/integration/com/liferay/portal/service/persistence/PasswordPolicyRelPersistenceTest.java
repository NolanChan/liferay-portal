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

package com.liferay.portal.service.persistence;

import com.liferay.portal.NoSuchPasswordPolicyRelException;
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
import com.liferay.portal.model.PasswordPolicyRel;
import com.liferay.portal.service.PasswordPolicyRelLocalServiceUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.util.PropsValues;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

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
public class PasswordPolicyRelPersistenceTest {
	@Rule
	public final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = PasswordPolicyRelUtil.getPersistence();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<PasswordPolicyRel> iterator = _passwordPolicyRels.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		PasswordPolicyRel passwordPolicyRel = _persistence.create(pk);

		Assert.assertNotNull(passwordPolicyRel);

		Assert.assertEquals(passwordPolicyRel.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		PasswordPolicyRel newPasswordPolicyRel = addPasswordPolicyRel();

		_persistence.remove(newPasswordPolicyRel);

		PasswordPolicyRel existingPasswordPolicyRel = _persistence.fetchByPrimaryKey(newPasswordPolicyRel.getPrimaryKey());

		Assert.assertNull(existingPasswordPolicyRel);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addPasswordPolicyRel();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		PasswordPolicyRel newPasswordPolicyRel = _persistence.create(pk);

		newPasswordPolicyRel.setMvccVersion(RandomTestUtil.nextLong());

		newPasswordPolicyRel.setPasswordPolicyId(RandomTestUtil.nextLong());

		newPasswordPolicyRel.setClassNameId(RandomTestUtil.nextLong());

		newPasswordPolicyRel.setClassPK(RandomTestUtil.nextLong());

		_passwordPolicyRels.add(_persistence.update(newPasswordPolicyRel));

		PasswordPolicyRel existingPasswordPolicyRel = _persistence.findByPrimaryKey(newPasswordPolicyRel.getPrimaryKey());

		Assert.assertEquals(existingPasswordPolicyRel.getMvccVersion(),
			newPasswordPolicyRel.getMvccVersion());
		Assert.assertEquals(existingPasswordPolicyRel.getPasswordPolicyRelId(),
			newPasswordPolicyRel.getPasswordPolicyRelId());
		Assert.assertEquals(existingPasswordPolicyRel.getPasswordPolicyId(),
			newPasswordPolicyRel.getPasswordPolicyId());
		Assert.assertEquals(existingPasswordPolicyRel.getClassNameId(),
			newPasswordPolicyRel.getClassNameId());
		Assert.assertEquals(existingPasswordPolicyRel.getClassPK(),
			newPasswordPolicyRel.getClassPK());
	}

	@Test
	public void testCountByPasswordPolicyId() {
		try {
			_persistence.countByPasswordPolicyId(RandomTestUtil.nextLong());

			_persistence.countByPasswordPolicyId(0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByC_C() {
		try {
			_persistence.countByC_C(RandomTestUtil.nextLong(),
				RandomTestUtil.nextLong());

			_persistence.countByC_C(0L, 0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		PasswordPolicyRel newPasswordPolicyRel = addPasswordPolicyRel();

		PasswordPolicyRel existingPasswordPolicyRel = _persistence.findByPrimaryKey(newPasswordPolicyRel.getPrimaryKey());

		Assert.assertEquals(existingPasswordPolicyRel, newPasswordPolicyRel);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail(
				"Missing entity did not throw NoSuchPasswordPolicyRelException");
		}
		catch (NoSuchPasswordPolicyRelException nsee) {
		}
	}

	@Test
	public void testFindAll() throws Exception {
		try {
			_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
				getOrderByComparator());
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	protected OrderByComparator<PasswordPolicyRel> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("PasswordPolicyRel",
			"mvccVersion", true, "passwordPolicyRelId", true,
			"passwordPolicyId", true, "classNameId", true, "classPK", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		PasswordPolicyRel newPasswordPolicyRel = addPasswordPolicyRel();

		PasswordPolicyRel existingPasswordPolicyRel = _persistence.fetchByPrimaryKey(newPasswordPolicyRel.getPrimaryKey());

		Assert.assertEquals(existingPasswordPolicyRel, newPasswordPolicyRel);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		PasswordPolicyRel missingPasswordPolicyRel = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingPasswordPolicyRel);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		PasswordPolicyRel newPasswordPolicyRel1 = addPasswordPolicyRel();
		PasswordPolicyRel newPasswordPolicyRel2 = addPasswordPolicyRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPasswordPolicyRel1.getPrimaryKey());
		primaryKeys.add(newPasswordPolicyRel2.getPrimaryKey());

		Map<Serializable, PasswordPolicyRel> passwordPolicyRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, passwordPolicyRels.size());
		Assert.assertEquals(newPasswordPolicyRel1,
			passwordPolicyRels.get(newPasswordPolicyRel1.getPrimaryKey()));
		Assert.assertEquals(newPasswordPolicyRel2,
			passwordPolicyRels.get(newPasswordPolicyRel2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, PasswordPolicyRel> passwordPolicyRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(passwordPolicyRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		PasswordPolicyRel newPasswordPolicyRel = addPasswordPolicyRel();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPasswordPolicyRel.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, PasswordPolicyRel> passwordPolicyRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, passwordPolicyRels.size());
		Assert.assertEquals(newPasswordPolicyRel,
			passwordPolicyRels.get(newPasswordPolicyRel.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, PasswordPolicyRel> passwordPolicyRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(passwordPolicyRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		PasswordPolicyRel newPasswordPolicyRel = addPasswordPolicyRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newPasswordPolicyRel.getPrimaryKey());

		Map<Serializable, PasswordPolicyRel> passwordPolicyRels = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, passwordPolicyRels.size());
		Assert.assertEquals(newPasswordPolicyRel,
			passwordPolicyRels.get(newPasswordPolicyRel.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = PasswordPolicyRelLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod() {
				@Override
				public void performAction(Object object) {
					PasswordPolicyRel passwordPolicyRel = (PasswordPolicyRel)object;

					Assert.assertNotNull(passwordPolicyRel);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		PasswordPolicyRel newPasswordPolicyRel = addPasswordPolicyRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PasswordPolicyRel.class,
				PasswordPolicyRel.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("passwordPolicyRelId",
				newPasswordPolicyRel.getPasswordPolicyRelId()));

		List<PasswordPolicyRel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		PasswordPolicyRel existingPasswordPolicyRel = result.get(0);

		Assert.assertEquals(existingPasswordPolicyRel, newPasswordPolicyRel);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PasswordPolicyRel.class,
				PasswordPolicyRel.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("passwordPolicyRelId",
				RandomTestUtil.nextLong()));

		List<PasswordPolicyRel> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		PasswordPolicyRel newPasswordPolicyRel = addPasswordPolicyRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PasswordPolicyRel.class,
				PasswordPolicyRel.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"passwordPolicyRelId"));

		Object newPasswordPolicyRelId = newPasswordPolicyRel.getPasswordPolicyRelId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("passwordPolicyRelId",
				new Object[] { newPasswordPolicyRelId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingPasswordPolicyRelId = result.get(0);

		Assert.assertEquals(existingPasswordPolicyRelId, newPasswordPolicyRelId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(PasswordPolicyRel.class,
				PasswordPolicyRel.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"passwordPolicyRelId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("passwordPolicyRelId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		if (!PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			return;
		}

		PasswordPolicyRel newPasswordPolicyRel = addPasswordPolicyRel();

		_persistence.clearCache();

		PasswordPolicyRel existingPasswordPolicyRel = _persistence.findByPrimaryKey(newPasswordPolicyRel.getPrimaryKey());

		Assert.assertEquals(existingPasswordPolicyRel.getClassNameId(),
			ReflectionTestUtil.invoke(existingPasswordPolicyRel,
				"getOriginalClassNameId", new Class<?>[0]));
		Assert.assertEquals(existingPasswordPolicyRel.getClassPK(),
			ReflectionTestUtil.invoke(existingPasswordPolicyRel,
				"getOriginalClassPK", new Class<?>[0]));
	}

	protected PasswordPolicyRel addPasswordPolicyRel()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		PasswordPolicyRel passwordPolicyRel = _persistence.create(pk);

		passwordPolicyRel.setMvccVersion(RandomTestUtil.nextLong());

		passwordPolicyRel.setPasswordPolicyId(RandomTestUtil.nextLong());

		passwordPolicyRel.setClassNameId(RandomTestUtil.nextLong());

		passwordPolicyRel.setClassPK(RandomTestUtil.nextLong());

		_passwordPolicyRels.add(_persistence.update(passwordPolicyRel));

		return passwordPolicyRel;
	}

	private List<PasswordPolicyRel> _passwordPolicyRels = new ArrayList<PasswordPolicyRel>();
	private PasswordPolicyRelPersistence _persistence;
}