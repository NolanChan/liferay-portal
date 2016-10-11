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

import com.liferay.osb.lcs.exception.NoSuchLCSPatchEntryException;
import com.liferay.osb.lcs.model.LCSPatchEntry;
import com.liferay.osb.lcs.service.LCSPatchEntryLocalServiceUtil;
import com.liferay.osb.lcs.service.persistence.LCSPatchEntryPersistence;
import com.liferay.osb.lcs.service.persistence.LCSPatchEntryUtil;

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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
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
import java.util.Objects;
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class LCSPatchEntryPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = LCSPatchEntryUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<LCSPatchEntry> iterator = _lcsPatchEntries.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSPatchEntry lcsPatchEntry = _persistence.create(pk);

		Assert.assertNotNull(lcsPatchEntry);

		Assert.assertEquals(lcsPatchEntry.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		LCSPatchEntry newLCSPatchEntry = addLCSPatchEntry();

		_persistence.remove(newLCSPatchEntry);

		LCSPatchEntry existingLCSPatchEntry = _persistence.fetchByPrimaryKey(newLCSPatchEntry.getPrimaryKey());

		Assert.assertNull(existingLCSPatchEntry);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addLCSPatchEntry();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSPatchEntry newLCSPatchEntry = _persistence.create(pk);

		newLCSPatchEntry.setPatchId(RandomTestUtil.randomString());

		newLCSPatchEntry.setName(RandomTestUtil.randomString());

		newLCSPatchEntry.setDescription(RandomTestUtil.randomString());

		newLCSPatchEntry.setPatchingToolVersion(RandomTestUtil.nextInt());

		newLCSPatchEntry.setIncremental(RandomTestUtil.randomBoolean());

		newLCSPatchEntry.setSingular(RandomTestUtil.randomBoolean());

		newLCSPatchEntry.setVersion(RandomTestUtil.nextInt());

		newLCSPatchEntry.setSize(RandomTestUtil.nextLong());

		newLCSPatchEntry.setRank(RandomTestUtil.nextLong());

		newLCSPatchEntry.setRequirements(RandomTestUtil.randomString());

		newLCSPatchEntry.setComponent(RandomTestUtil.randomString());

		newLCSPatchEntry.setCompatibleBuild(RandomTestUtil.randomString());

		newLCSPatchEntry.setProduct(RandomTestUtil.randomString());

		newLCSPatchEntry.setFixedIssues(RandomTestUtil.randomString());

		newLCSPatchEntry.setModuleName(RandomTestUtil.randomString());

		newLCSPatchEntry.setModuleId(RandomTestUtil.randomString());

		newLCSPatchEntry.setTunnelWeb(RandomTestUtil.randomBoolean());

		newLCSPatchEntry.setBuildDate(RandomTestUtil.nextDate());

		newLCSPatchEntry.setBuiltFor(RandomTestUtil.randomString());

		_lcsPatchEntries.add(_persistence.update(newLCSPatchEntry));

		LCSPatchEntry existingLCSPatchEntry = _persistence.findByPrimaryKey(newLCSPatchEntry.getPrimaryKey());

		Assert.assertEquals(existingLCSPatchEntry.getLcsPatchEntryId(),
			newLCSPatchEntry.getLcsPatchEntryId());
		Assert.assertEquals(existingLCSPatchEntry.getPatchId(),
			newLCSPatchEntry.getPatchId());
		Assert.assertEquals(existingLCSPatchEntry.getName(),
			newLCSPatchEntry.getName());
		Assert.assertEquals(existingLCSPatchEntry.getDescription(),
			newLCSPatchEntry.getDescription());
		Assert.assertEquals(existingLCSPatchEntry.getPatchingToolVersion(),
			newLCSPatchEntry.getPatchingToolVersion());
		Assert.assertEquals(existingLCSPatchEntry.getIncremental(),
			newLCSPatchEntry.getIncremental());
		Assert.assertEquals(existingLCSPatchEntry.getSingular(),
			newLCSPatchEntry.getSingular());
		Assert.assertEquals(existingLCSPatchEntry.getVersion(),
			newLCSPatchEntry.getVersion());
		Assert.assertEquals(existingLCSPatchEntry.getSize(),
			newLCSPatchEntry.getSize());
		Assert.assertEquals(existingLCSPatchEntry.getRank(),
			newLCSPatchEntry.getRank());
		Assert.assertEquals(existingLCSPatchEntry.getRequirements(),
			newLCSPatchEntry.getRequirements());
		Assert.assertEquals(existingLCSPatchEntry.getComponent(),
			newLCSPatchEntry.getComponent());
		Assert.assertEquals(existingLCSPatchEntry.getCompatibleBuild(),
			newLCSPatchEntry.getCompatibleBuild());
		Assert.assertEquals(existingLCSPatchEntry.getProduct(),
			newLCSPatchEntry.getProduct());
		Assert.assertEquals(existingLCSPatchEntry.getFixedIssues(),
			newLCSPatchEntry.getFixedIssues());
		Assert.assertEquals(existingLCSPatchEntry.getModuleName(),
			newLCSPatchEntry.getModuleName());
		Assert.assertEquals(existingLCSPatchEntry.getModuleId(),
			newLCSPatchEntry.getModuleId());
		Assert.assertEquals(existingLCSPatchEntry.getTunnelWeb(),
			newLCSPatchEntry.getTunnelWeb());
		Assert.assertEquals(Time.getShortTimestamp(
				existingLCSPatchEntry.getBuildDate()),
			Time.getShortTimestamp(newLCSPatchEntry.getBuildDate()));
		Assert.assertEquals(existingLCSPatchEntry.getBuiltFor(),
			newLCSPatchEntry.getBuiltFor());
	}

	@Test
	public void testCountByPatchId() throws Exception {
		_persistence.countByPatchId(StringPool.BLANK);

		_persistence.countByPatchId(StringPool.NULL);

		_persistence.countByPatchId((String)null);
	}

	@Test
	public void testCountByPTV_Product() throws Exception {
		_persistence.countByPTV_Product(RandomTestUtil.nextInt(),
			StringPool.BLANK);

		_persistence.countByPTV_Product(0, StringPool.NULL);

		_persistence.countByPTV_Product(0, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		LCSPatchEntry newLCSPatchEntry = addLCSPatchEntry();

		LCSPatchEntry existingLCSPatchEntry = _persistence.findByPrimaryKey(newLCSPatchEntry.getPrimaryKey());

		Assert.assertEquals(existingLCSPatchEntry, newLCSPatchEntry);
	}

	@Test(expected = NoSuchLCSPatchEntryException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<LCSPatchEntry> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("OSBLCS_LCSPatchEntry",
			"lcsPatchEntryId", true, "patchId", true, "name", true,
			"description", true, "patchingToolVersion", true, "incremental",
			true, "singular", true, "version", true, "size", true, "rank",
			true, "requirements", true, "component", true, "compatibleBuild",
			true, "product", true, "fixedIssues", true, "moduleName", true,
			"moduleId", true, "tunnelWeb", true, "buildDate", true, "builtFor",
			true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		LCSPatchEntry newLCSPatchEntry = addLCSPatchEntry();

		LCSPatchEntry existingLCSPatchEntry = _persistence.fetchByPrimaryKey(newLCSPatchEntry.getPrimaryKey());

		Assert.assertEquals(existingLCSPatchEntry, newLCSPatchEntry);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSPatchEntry missingLCSPatchEntry = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingLCSPatchEntry);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		LCSPatchEntry newLCSPatchEntry1 = addLCSPatchEntry();
		LCSPatchEntry newLCSPatchEntry2 = addLCSPatchEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSPatchEntry1.getPrimaryKey());
		primaryKeys.add(newLCSPatchEntry2.getPrimaryKey());

		Map<Serializable, LCSPatchEntry> lcsPatchEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, lcsPatchEntries.size());
		Assert.assertEquals(newLCSPatchEntry1,
			lcsPatchEntries.get(newLCSPatchEntry1.getPrimaryKey()));
		Assert.assertEquals(newLCSPatchEntry2,
			lcsPatchEntries.get(newLCSPatchEntry2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, LCSPatchEntry> lcsPatchEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(lcsPatchEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		LCSPatchEntry newLCSPatchEntry = addLCSPatchEntry();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSPatchEntry.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, LCSPatchEntry> lcsPatchEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, lcsPatchEntries.size());
		Assert.assertEquals(newLCSPatchEntry,
			lcsPatchEntries.get(newLCSPatchEntry.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, LCSPatchEntry> lcsPatchEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(lcsPatchEntries.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		LCSPatchEntry newLCSPatchEntry = addLCSPatchEntry();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSPatchEntry.getPrimaryKey());

		Map<Serializable, LCSPatchEntry> lcsPatchEntries = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, lcsPatchEntries.size());
		Assert.assertEquals(newLCSPatchEntry,
			lcsPatchEntries.get(newLCSPatchEntry.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = LCSPatchEntryLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<LCSPatchEntry>() {
				@Override
				public void performAction(LCSPatchEntry lcsPatchEntry) {
					Assert.assertNotNull(lcsPatchEntry);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		LCSPatchEntry newLCSPatchEntry = addLCSPatchEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSPatchEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("lcsPatchEntryId",
				newLCSPatchEntry.getLcsPatchEntryId()));

		List<LCSPatchEntry> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		LCSPatchEntry existingLCSPatchEntry = result.get(0);

		Assert.assertEquals(existingLCSPatchEntry, newLCSPatchEntry);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSPatchEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("lcsPatchEntryId",
				RandomTestUtil.nextLong()));

		List<LCSPatchEntry> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		LCSPatchEntry newLCSPatchEntry = addLCSPatchEntry();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSPatchEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"lcsPatchEntryId"));

		Object newLcsPatchEntryId = newLCSPatchEntry.getLcsPatchEntryId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("lcsPatchEntryId",
				new Object[] { newLcsPatchEntryId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingLcsPatchEntryId = result.get(0);

		Assert.assertEquals(existingLcsPatchEntryId, newLcsPatchEntryId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSPatchEntry.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"lcsPatchEntryId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("lcsPatchEntryId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		LCSPatchEntry newLCSPatchEntry = addLCSPatchEntry();

		_persistence.clearCache();

		LCSPatchEntry existingLCSPatchEntry = _persistence.findByPrimaryKey(newLCSPatchEntry.getPrimaryKey());

		Assert.assertTrue(Objects.equals(existingLCSPatchEntry.getPatchId(),
				ReflectionTestUtil.invoke(existingLCSPatchEntry,
					"getOriginalPatchId", new Class<?>[0])));
	}

	protected LCSPatchEntry addLCSPatchEntry() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSPatchEntry lcsPatchEntry = _persistence.create(pk);

		lcsPatchEntry.setPatchId(RandomTestUtil.randomString());

		lcsPatchEntry.setName(RandomTestUtil.randomString());

		lcsPatchEntry.setDescription(RandomTestUtil.randomString());

		lcsPatchEntry.setPatchingToolVersion(RandomTestUtil.nextInt());

		lcsPatchEntry.setIncremental(RandomTestUtil.randomBoolean());

		lcsPatchEntry.setSingular(RandomTestUtil.randomBoolean());

		lcsPatchEntry.setVersion(RandomTestUtil.nextInt());

		lcsPatchEntry.setSize(RandomTestUtil.nextLong());

		lcsPatchEntry.setRank(RandomTestUtil.nextLong());

		lcsPatchEntry.setRequirements(RandomTestUtil.randomString());

		lcsPatchEntry.setComponent(RandomTestUtil.randomString());

		lcsPatchEntry.setCompatibleBuild(RandomTestUtil.randomString());

		lcsPatchEntry.setProduct(RandomTestUtil.randomString());

		lcsPatchEntry.setFixedIssues(RandomTestUtil.randomString());

		lcsPatchEntry.setModuleName(RandomTestUtil.randomString());

		lcsPatchEntry.setModuleId(RandomTestUtil.randomString());

		lcsPatchEntry.setTunnelWeb(RandomTestUtil.randomBoolean());

		lcsPatchEntry.setBuildDate(RandomTestUtil.nextDate());

		lcsPatchEntry.setBuiltFor(RandomTestUtil.randomString());

		_lcsPatchEntries.add(_persistence.update(lcsPatchEntry));

		return lcsPatchEntry;
	}

	private List<LCSPatchEntry> _lcsPatchEntries = new ArrayList<LCSPatchEntry>();
	private LCSPatchEntryPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}