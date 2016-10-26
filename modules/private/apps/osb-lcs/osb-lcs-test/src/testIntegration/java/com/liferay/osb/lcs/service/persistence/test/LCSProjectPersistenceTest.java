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

import com.liferay.osb.lcs.exception.NoSuchLCSProjectException;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.service.LCSProjectLocalServiceUtil;
import com.liferay.osb.lcs.service.persistence.LCSProjectPersistence;
import com.liferay.osb.lcs.service.persistence.LCSProjectUtil;

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
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class LCSProjectPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.osb.lcs.service"));

	@Before
	public void setUp() {
		_persistence = LCSProjectUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<LCSProject> iterator = _lcsProjects.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSProject lcsProject = _persistence.create(pk);

		Assert.assertNotNull(lcsProject);

		Assert.assertEquals(lcsProject.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		LCSProject newLCSProject = addLCSProject();

		_persistence.remove(newLCSProject);

		LCSProject existingLCSProject = _persistence.fetchByPrimaryKey(newLCSProject.getPrimaryKey());

		Assert.assertNull(existingLCSProject);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addLCSProject();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSProject newLCSProject = _persistence.create(pk);

		newLCSProject.setCreateDate(RandomTestUtil.nextDate());

		newLCSProject.setModifiedDate(RandomTestUtil.nextDate());

		newLCSProject.setSourceSystemName(RandomTestUtil.randomString());

		newLCSProject.setName(RandomTestUtil.randomString());

		newLCSProject.setOrganizationId(RandomTestUtil.nextLong());

		newLCSProject.setAddressId(RandomTestUtil.nextLong());

		newLCSProject.setAccountEntryId(RandomTestUtil.nextLong());

		newLCSProject.setCorpProjectId(RandomTestUtil.nextLong());

		newLCSProject.setContactEmailAddress(RandomTestUtil.randomString());

		newLCSProject.setPhoneNumber(RandomTestUtil.randomString());

		newLCSProject.setSubscriptionActive(RandomTestUtil.randomBoolean());

		newLCSProject.setArchived(RandomTestUtil.randomBoolean());

		_lcsProjects.add(_persistence.update(newLCSProject));

		LCSProject existingLCSProject = _persistence.findByPrimaryKey(newLCSProject.getPrimaryKey());

		Assert.assertEquals(existingLCSProject.getLcsProjectId(),
			newLCSProject.getLcsProjectId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingLCSProject.getCreateDate()),
			Time.getShortTimestamp(newLCSProject.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingLCSProject.getModifiedDate()),
			Time.getShortTimestamp(newLCSProject.getModifiedDate()));
		Assert.assertEquals(existingLCSProject.getSourceSystemName(),
			newLCSProject.getSourceSystemName());
		Assert.assertEquals(existingLCSProject.getName(),
			newLCSProject.getName());
		Assert.assertEquals(existingLCSProject.getOrganizationId(),
			newLCSProject.getOrganizationId());
		Assert.assertEquals(existingLCSProject.getAddressId(),
			newLCSProject.getAddressId());
		Assert.assertEquals(existingLCSProject.getAccountEntryId(),
			newLCSProject.getAccountEntryId());
		Assert.assertEquals(existingLCSProject.getCorpProjectId(),
			newLCSProject.getCorpProjectId());
		Assert.assertEquals(existingLCSProject.getContactEmailAddress(),
			newLCSProject.getContactEmailAddress());
		Assert.assertEquals(existingLCSProject.getPhoneNumber(),
			newLCSProject.getPhoneNumber());
		Assert.assertEquals(existingLCSProject.getSubscriptionActive(),
			newLCSProject.getSubscriptionActive());
		Assert.assertEquals(existingLCSProject.getArchived(),
			newLCSProject.getArchived());
	}

	@Test
	public void testCountByName() throws Exception {
		_persistence.countByName(StringPool.BLANK);

		_persistence.countByName(StringPool.NULL);

		_persistence.countByName((String)null);
	}

	@Test
	public void testCountByCorpProjectId() throws Exception {
		_persistence.countByCorpProjectId(RandomTestUtil.nextLong());

		_persistence.countByCorpProjectId(0L);
	}

	@Test
	public void testCountByCPI_A() throws Exception {
		_persistence.countByCPI_A(RandomTestUtil.nextLong(),
			RandomTestUtil.randomBoolean());

		_persistence.countByCPI_A(0L, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByCPI_N_A() throws Exception {
		_persistence.countByCPI_N_A(RandomTestUtil.nextLong(),
			StringPool.BLANK, RandomTestUtil.randomBoolean());

		_persistence.countByCPI_N_A(0L, StringPool.NULL,
			RandomTestUtil.randomBoolean());

		_persistence.countByCPI_N_A(0L, (String)null,
			RandomTestUtil.randomBoolean());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		LCSProject newLCSProject = addLCSProject();

		LCSProject existingLCSProject = _persistence.findByPrimaryKey(newLCSProject.getPrimaryKey());

		Assert.assertEquals(existingLCSProject, newLCSProject);
	}

	@Test(expected = NoSuchLCSProjectException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<LCSProject> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("OSBLCS_LCSProject",
			"lcsProjectId", true, "createDate", true, "modifiedDate", true,
			"sourceSystemName", true, "name", true, "organizationId", true,
			"addressId", true, "accountEntryId", true, "corpProjectId", true,
			"contactEmailAddress", true, "phoneNumber", true,
			"subscriptionActive", true, "archived", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		LCSProject newLCSProject = addLCSProject();

		LCSProject existingLCSProject = _persistence.fetchByPrimaryKey(newLCSProject.getPrimaryKey());

		Assert.assertEquals(existingLCSProject, newLCSProject);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSProject missingLCSProject = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingLCSProject);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		LCSProject newLCSProject1 = addLCSProject();
		LCSProject newLCSProject2 = addLCSProject();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSProject1.getPrimaryKey());
		primaryKeys.add(newLCSProject2.getPrimaryKey());

		Map<Serializable, LCSProject> lcsProjects = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, lcsProjects.size());
		Assert.assertEquals(newLCSProject1,
			lcsProjects.get(newLCSProject1.getPrimaryKey()));
		Assert.assertEquals(newLCSProject2,
			lcsProjects.get(newLCSProject2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, LCSProject> lcsProjects = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(lcsProjects.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		LCSProject newLCSProject = addLCSProject();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSProject.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, LCSProject> lcsProjects = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, lcsProjects.size());
		Assert.assertEquals(newLCSProject,
			lcsProjects.get(newLCSProject.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, LCSProject> lcsProjects = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(lcsProjects.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		LCSProject newLCSProject = addLCSProject();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSProject.getPrimaryKey());

		Map<Serializable, LCSProject> lcsProjects = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, lcsProjects.size());
		Assert.assertEquals(newLCSProject,
			lcsProjects.get(newLCSProject.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = LCSProjectLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<LCSProject>() {
				@Override
				public void performAction(LCSProject lcsProject) {
					Assert.assertNotNull(lcsProject);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		LCSProject newLCSProject = addLCSProject();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSProject.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("lcsProjectId",
				newLCSProject.getLcsProjectId()));

		List<LCSProject> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		LCSProject existingLCSProject = result.get(0);

		Assert.assertEquals(existingLCSProject, newLCSProject);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSProject.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("lcsProjectId",
				RandomTestUtil.nextLong()));

		List<LCSProject> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		LCSProject newLCSProject = addLCSProject();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSProject.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"lcsProjectId"));

		Object newLcsProjectId = newLCSProject.getLcsProjectId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("lcsProjectId",
				new Object[] { newLcsProjectId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingLcsProjectId = result.get(0);

		Assert.assertEquals(existingLcsProjectId, newLcsProjectId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSProject.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"lcsProjectId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("lcsProjectId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		LCSProject newLCSProject = addLCSProject();

		_persistence.clearCache();

		LCSProject existingLCSProject = _persistence.findByPrimaryKey(newLCSProject.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(existingLCSProject.getCorpProjectId()),
			ReflectionTestUtil.<Long>invoke(existingLCSProject,
				"getOriginalCorpProjectId", new Class<?>[0]));
	}

	protected LCSProject addLCSProject() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSProject lcsProject = _persistence.create(pk);

		lcsProject.setCreateDate(RandomTestUtil.nextDate());

		lcsProject.setModifiedDate(RandomTestUtil.nextDate());

		lcsProject.setSourceSystemName(RandomTestUtil.randomString());

		lcsProject.setName(RandomTestUtil.randomString());

		lcsProject.setOrganizationId(RandomTestUtil.nextLong());

		lcsProject.setAddressId(RandomTestUtil.nextLong());

		lcsProject.setAccountEntryId(RandomTestUtil.nextLong());

		lcsProject.setCorpProjectId(RandomTestUtil.nextLong());

		lcsProject.setContactEmailAddress(RandomTestUtil.randomString());

		lcsProject.setPhoneNumber(RandomTestUtil.randomString());

		lcsProject.setSubscriptionActive(RandomTestUtil.randomBoolean());

		lcsProject.setArchived(RandomTestUtil.randomBoolean());

		_lcsProjects.add(_persistence.update(lcsProject));

		return lcsProject;
	}

	private List<LCSProject> _lcsProjects = new ArrayList<LCSProject>();
	private LCSProjectPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}