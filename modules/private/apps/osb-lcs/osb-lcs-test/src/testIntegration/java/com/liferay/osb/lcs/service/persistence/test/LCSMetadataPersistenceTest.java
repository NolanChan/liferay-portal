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

import com.liferay.osb.lcs.exception.NoSuchLCSMetadataException;
import com.liferay.osb.lcs.model.LCSMetadata;
import com.liferay.osb.lcs.service.LCSMetadataLocalServiceUtil;
import com.liferay.osb.lcs.service.persistence.LCSMetadataPersistence;
import com.liferay.osb.lcs.service.persistence.LCSMetadataUtil;

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
public class LCSMetadataPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.osb.lcs.service"));

	@Before
	public void setUp() {
		_persistence = LCSMetadataUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<LCSMetadata> iterator = _lcsMetadatas.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSMetadata lcsMetadata = _persistence.create(pk);

		Assert.assertNotNull(lcsMetadata);

		Assert.assertEquals(lcsMetadata.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		LCSMetadata newLCSMetadata = addLCSMetadata();

		_persistence.remove(newLCSMetadata);

		LCSMetadata existingLCSMetadata = _persistence.fetchByPrimaryKey(newLCSMetadata.getPrimaryKey());

		Assert.assertNull(existingLCSMetadata);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addLCSMetadata();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSMetadata newLCSMetadata = _persistence.create(pk);

		newLCSMetadata.setAvailabilityIndex(RandomTestUtil.nextLong());

		newLCSMetadata.setBuildNumber(RandomTestUtil.nextInt());

		newLCSMetadata.setGitTag(RandomTestUtil.randomString());

		newLCSMetadata.setPortalEdition(RandomTestUtil.randomString());

		newLCSMetadata.setSupportedLCSPortlet(RandomTestUtil.nextInt());

		newLCSMetadata.setSupportedPatchingTool(RandomTestUtil.nextInt());

		_lcsMetadatas.add(_persistence.update(newLCSMetadata));

		LCSMetadata existingLCSMetadata = _persistence.findByPrimaryKey(newLCSMetadata.getPrimaryKey());

		Assert.assertEquals(existingLCSMetadata.getLcsMetadataId(),
			newLCSMetadata.getLcsMetadataId());
		Assert.assertEquals(existingLCSMetadata.getAvailabilityIndex(),
			newLCSMetadata.getAvailabilityIndex());
		Assert.assertEquals(existingLCSMetadata.getBuildNumber(),
			newLCSMetadata.getBuildNumber());
		Assert.assertEquals(existingLCSMetadata.getGitTag(),
			newLCSMetadata.getGitTag());
		Assert.assertEquals(existingLCSMetadata.getPortalEdition(),
			newLCSMetadata.getPortalEdition());
		Assert.assertEquals(existingLCSMetadata.getSupportedLCSPortlet(),
			newLCSMetadata.getSupportedLCSPortlet());
		Assert.assertEquals(existingLCSMetadata.getSupportedPatchingTool(),
			newLCSMetadata.getSupportedPatchingTool());
	}

	@Test
	public void testCountByBN_PE() throws Exception {
		_persistence.countByBN_PE(RandomTestUtil.nextInt(), StringPool.BLANK);

		_persistence.countByBN_PE(0, StringPool.NULL);

		_persistence.countByBN_PE(0, (String)null);
	}

	@Test
	public void testCountByBN_GT_PE() throws Exception {
		_persistence.countByBN_GT_PE(RandomTestUtil.nextInt(),
			StringPool.BLANK, StringPool.BLANK);

		_persistence.countByBN_GT_PE(0, StringPool.NULL, StringPool.NULL);

		_persistence.countByBN_GT_PE(0, (String)null, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		LCSMetadata newLCSMetadata = addLCSMetadata();

		LCSMetadata existingLCSMetadata = _persistence.findByPrimaryKey(newLCSMetadata.getPrimaryKey());

		Assert.assertEquals(existingLCSMetadata, newLCSMetadata);
	}

	@Test(expected = NoSuchLCSMetadataException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<LCSMetadata> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("OSBLCS_LCSMetadata",
			"lcsMetadataId", true, "availabilityIndex", true, "buildNumber",
			true, "gitTag", true, "portalEdition", true, "supportedLCSPortlet",
			true, "supportedPatchingTool", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		LCSMetadata newLCSMetadata = addLCSMetadata();

		LCSMetadata existingLCSMetadata = _persistence.fetchByPrimaryKey(newLCSMetadata.getPrimaryKey());

		Assert.assertEquals(existingLCSMetadata, newLCSMetadata);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSMetadata missingLCSMetadata = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingLCSMetadata);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		LCSMetadata newLCSMetadata1 = addLCSMetadata();
		LCSMetadata newLCSMetadata2 = addLCSMetadata();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSMetadata1.getPrimaryKey());
		primaryKeys.add(newLCSMetadata2.getPrimaryKey());

		Map<Serializable, LCSMetadata> lcsMetadatas = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, lcsMetadatas.size());
		Assert.assertEquals(newLCSMetadata1,
			lcsMetadatas.get(newLCSMetadata1.getPrimaryKey()));
		Assert.assertEquals(newLCSMetadata2,
			lcsMetadatas.get(newLCSMetadata2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, LCSMetadata> lcsMetadatas = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(lcsMetadatas.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		LCSMetadata newLCSMetadata = addLCSMetadata();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSMetadata.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, LCSMetadata> lcsMetadatas = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, lcsMetadatas.size());
		Assert.assertEquals(newLCSMetadata,
			lcsMetadatas.get(newLCSMetadata.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, LCSMetadata> lcsMetadatas = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(lcsMetadatas.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		LCSMetadata newLCSMetadata = addLCSMetadata();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSMetadata.getPrimaryKey());

		Map<Serializable, LCSMetadata> lcsMetadatas = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, lcsMetadatas.size());
		Assert.assertEquals(newLCSMetadata,
			lcsMetadatas.get(newLCSMetadata.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = LCSMetadataLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<LCSMetadata>() {
				@Override
				public void performAction(LCSMetadata lcsMetadata) {
					Assert.assertNotNull(lcsMetadata);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		LCSMetadata newLCSMetadata = addLCSMetadata();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSMetadata.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("lcsMetadataId",
				newLCSMetadata.getLcsMetadataId()));

		List<LCSMetadata> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		LCSMetadata existingLCSMetadata = result.get(0);

		Assert.assertEquals(existingLCSMetadata, newLCSMetadata);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSMetadata.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("lcsMetadataId",
				RandomTestUtil.nextLong()));

		List<LCSMetadata> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		LCSMetadata newLCSMetadata = addLCSMetadata();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSMetadata.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"lcsMetadataId"));

		Object newLcsMetadataId = newLCSMetadata.getLcsMetadataId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("lcsMetadataId",
				new Object[] { newLcsMetadataId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingLcsMetadataId = result.get(0);

		Assert.assertEquals(existingLcsMetadataId, newLcsMetadataId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSMetadata.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"lcsMetadataId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("lcsMetadataId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		LCSMetadata newLCSMetadata = addLCSMetadata();

		_persistence.clearCache();

		LCSMetadata existingLCSMetadata = _persistence.findByPrimaryKey(newLCSMetadata.getPrimaryKey());

		Assert.assertEquals(Integer.valueOf(
				existingLCSMetadata.getBuildNumber()),
			ReflectionTestUtil.<Integer>invoke(existingLCSMetadata,
				"getOriginalBuildNumber", new Class<?>[0]));
		Assert.assertTrue(Objects.equals(existingLCSMetadata.getGitTag(),
				ReflectionTestUtil.invoke(existingLCSMetadata,
					"getOriginalGitTag", new Class<?>[0])));
		Assert.assertTrue(Objects.equals(
				existingLCSMetadata.getPortalEdition(),
				ReflectionTestUtil.invoke(existingLCSMetadata,
					"getOriginalPortalEdition", new Class<?>[0])));
	}

	protected LCSMetadata addLCSMetadata() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSMetadata lcsMetadata = _persistence.create(pk);

		lcsMetadata.setAvailabilityIndex(RandomTestUtil.nextLong());

		lcsMetadata.setBuildNumber(RandomTestUtil.nextInt());

		lcsMetadata.setGitTag(RandomTestUtil.randomString());

		lcsMetadata.setPortalEdition(RandomTestUtil.randomString());

		lcsMetadata.setSupportedLCSPortlet(RandomTestUtil.nextInt());

		lcsMetadata.setSupportedPatchingTool(RandomTestUtil.nextInt());

		_lcsMetadatas.add(_persistence.update(lcsMetadata));

		return lcsMetadata;
	}

	private List<LCSMetadata> _lcsMetadatas = new ArrayList<LCSMetadata>();
	private LCSMetadataPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}