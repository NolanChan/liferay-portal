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

import com.liferay.osb.lcs.exception.NoSuchLCSClusterNodeException;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.service.LCSClusterNodeLocalServiceUtil;
import com.liferay.osb.lcs.service.persistence.LCSClusterNodePersistence;
import com.liferay.osb.lcs.service.persistence.LCSClusterNodeUtil;

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
public class LCSClusterNodePersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.liferay.osb.lcs.service"));

	@Before
	public void setUp() {
		_persistence = LCSClusterNodeUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<LCSClusterNode> iterator = _lcsClusterNodes.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSClusterNode lcsClusterNode = _persistence.create(pk);

		Assert.assertNotNull(lcsClusterNode);

		Assert.assertEquals(lcsClusterNode.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		LCSClusterNode newLCSClusterNode = addLCSClusterNode();

		_persistence.remove(newLCSClusterNode);

		LCSClusterNode existingLCSClusterNode = _persistence.fetchByPrimaryKey(newLCSClusterNode.getPrimaryKey());

		Assert.assertNull(existingLCSClusterNode);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addLCSClusterNode();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSClusterNode newLCSClusterNode = _persistence.create(pk);

		newLCSClusterNode.setLcsClusterEntryId(RandomTestUtil.nextLong());

		newLCSClusterNode.setInstallationId(RandomTestUtil.nextLong());

		newLCSClusterNode.setName(RandomTestUtil.randomString());

		newLCSClusterNode.setDescription(RandomTestUtil.randomString());

		newLCSClusterNode.setBuildNumber(RandomTestUtil.nextInt());

		newLCSClusterNode.setKey(RandomTestUtil.randomString());

		newLCSClusterNode.setLocation(RandomTestUtil.randomString());

		newLCSClusterNode.setProcessorCoresTotal(RandomTestUtil.nextInt());

		newLCSClusterNode.setArchived(RandomTestUtil.randomBoolean());

		newLCSClusterNode.setStatus(RandomTestUtil.nextInt());

		_lcsClusterNodes.add(_persistence.update(newLCSClusterNode));

		LCSClusterNode existingLCSClusterNode = _persistence.findByPrimaryKey(newLCSClusterNode.getPrimaryKey());

		Assert.assertEquals(existingLCSClusterNode.getLcsClusterNodeId(),
			newLCSClusterNode.getLcsClusterNodeId());
		Assert.assertEquals(existingLCSClusterNode.getLcsClusterEntryId(),
			newLCSClusterNode.getLcsClusterEntryId());
		Assert.assertEquals(existingLCSClusterNode.getInstallationId(),
			newLCSClusterNode.getInstallationId());
		Assert.assertEquals(existingLCSClusterNode.getName(),
			newLCSClusterNode.getName());
		Assert.assertEquals(existingLCSClusterNode.getDescription(),
			newLCSClusterNode.getDescription());
		Assert.assertEquals(existingLCSClusterNode.getBuildNumber(),
			newLCSClusterNode.getBuildNumber());
		Assert.assertEquals(existingLCSClusterNode.getKey(),
			newLCSClusterNode.getKey());
		Assert.assertEquals(existingLCSClusterNode.getLocation(),
			newLCSClusterNode.getLocation());
		Assert.assertEquals(existingLCSClusterNode.getProcessorCoresTotal(),
			newLCSClusterNode.getProcessorCoresTotal());
		Assert.assertEquals(existingLCSClusterNode.getArchived(),
			newLCSClusterNode.getArchived());
		Assert.assertEquals(existingLCSClusterNode.getStatus(),
			newLCSClusterNode.getStatus());
	}

	@Test
	public void testCountByLCSClusterEntryId() throws Exception {
		_persistence.countByLCSClusterEntryId(RandomTestUtil.nextLong());

		_persistence.countByLCSClusterEntryId(0L);
	}

	@Test
	public void testCountByLCSClusterEntryIdArrayable()
		throws Exception {
		_persistence.countByLCSClusterEntryId(new long[] {
				RandomTestUtil.nextLong(), 0L
			});
	}

	@Test
	public void testCountByBuildNumber() throws Exception {
		_persistence.countByBuildNumber(RandomTestUtil.nextInt());

		_persistence.countByBuildNumber(0);
	}

	@Test
	public void testCountByKey() throws Exception {
		_persistence.countByKey(StringPool.BLANK);

		_persistence.countByKey(StringPool.NULL);

		_persistence.countByKey((String)null);
	}

	@Test
	public void testCountByLCEI_N() throws Exception {
		_persistence.countByLCEI_N(RandomTestUtil.nextLong(), StringPool.BLANK);

		_persistence.countByLCEI_N(0L, StringPool.NULL);

		_persistence.countByLCEI_N(0L, (String)null);
	}

	@Test
	public void testCountByLCEI_A() throws Exception {
		_persistence.countByLCEI_A(RandomTestUtil.nextLong(),
			RandomTestUtil.randomBoolean());

		_persistence.countByLCEI_A(0L, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByLCEI_AArrayable() throws Exception {
		_persistence.countByLCEI_A(new long[] { RandomTestUtil.nextLong(), 0L },
			RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByBN_A() throws Exception {
		_persistence.countByBN_A(RandomTestUtil.nextInt(),
			RandomTestUtil.randomBoolean());

		_persistence.countByBN_A(0, RandomTestUtil.randomBoolean());
	}

	@Test
	public void testCountByLCEI_N_A() throws Exception {
		_persistence.countByLCEI_N_A(RandomTestUtil.nextLong(),
			StringPool.BLANK, RandomTestUtil.randomBoolean());

		_persistence.countByLCEI_N_A(0L, StringPool.NULL,
			RandomTestUtil.randomBoolean());

		_persistence.countByLCEI_N_A(0L, (String)null,
			RandomTestUtil.randomBoolean());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		LCSClusterNode newLCSClusterNode = addLCSClusterNode();

		LCSClusterNode existingLCSClusterNode = _persistence.findByPrimaryKey(newLCSClusterNode.getPrimaryKey());

		Assert.assertEquals(existingLCSClusterNode, newLCSClusterNode);
	}

	@Test(expected = NoSuchLCSClusterNodeException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<LCSClusterNode> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("OSBLCS_LCSClusterNode",
			"lcsClusterNodeId", true, "lcsClusterEntryId", true,
			"installationId", true, "name", true, "description", true,
			"buildNumber", true, "key", true, "location", true,
			"processorCoresTotal", true, "archived", true, "status", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		LCSClusterNode newLCSClusterNode = addLCSClusterNode();

		LCSClusterNode existingLCSClusterNode = _persistence.fetchByPrimaryKey(newLCSClusterNode.getPrimaryKey());

		Assert.assertEquals(existingLCSClusterNode, newLCSClusterNode);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSClusterNode missingLCSClusterNode = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingLCSClusterNode);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		LCSClusterNode newLCSClusterNode1 = addLCSClusterNode();
		LCSClusterNode newLCSClusterNode2 = addLCSClusterNode();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSClusterNode1.getPrimaryKey());
		primaryKeys.add(newLCSClusterNode2.getPrimaryKey());

		Map<Serializable, LCSClusterNode> lcsClusterNodes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, lcsClusterNodes.size());
		Assert.assertEquals(newLCSClusterNode1,
			lcsClusterNodes.get(newLCSClusterNode1.getPrimaryKey()));
		Assert.assertEquals(newLCSClusterNode2,
			lcsClusterNodes.get(newLCSClusterNode2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, LCSClusterNode> lcsClusterNodes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(lcsClusterNodes.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		LCSClusterNode newLCSClusterNode = addLCSClusterNode();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSClusterNode.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, LCSClusterNode> lcsClusterNodes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, lcsClusterNodes.size());
		Assert.assertEquals(newLCSClusterNode,
			lcsClusterNodes.get(newLCSClusterNode.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, LCSClusterNode> lcsClusterNodes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(lcsClusterNodes.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		LCSClusterNode newLCSClusterNode = addLCSClusterNode();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newLCSClusterNode.getPrimaryKey());

		Map<Serializable, LCSClusterNode> lcsClusterNodes = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, lcsClusterNodes.size());
		Assert.assertEquals(newLCSClusterNode,
			lcsClusterNodes.get(newLCSClusterNode.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = LCSClusterNodeLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<LCSClusterNode>() {
				@Override
				public void performAction(LCSClusterNode lcsClusterNode) {
					Assert.assertNotNull(lcsClusterNode);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		LCSClusterNode newLCSClusterNode = addLCSClusterNode();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSClusterNode.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("lcsClusterNodeId",
				newLCSClusterNode.getLcsClusterNodeId()));

		List<LCSClusterNode> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		LCSClusterNode existingLCSClusterNode = result.get(0);

		Assert.assertEquals(existingLCSClusterNode, newLCSClusterNode);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSClusterNode.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("lcsClusterNodeId",
				RandomTestUtil.nextLong()));

		List<LCSClusterNode> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		LCSClusterNode newLCSClusterNode = addLCSClusterNode();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSClusterNode.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"lcsClusterNodeId"));

		Object newLcsClusterNodeId = newLCSClusterNode.getLcsClusterNodeId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("lcsClusterNodeId",
				new Object[] { newLcsClusterNodeId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingLcsClusterNodeId = result.get(0);

		Assert.assertEquals(existingLcsClusterNodeId, newLcsClusterNodeId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LCSClusterNode.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"lcsClusterNodeId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("lcsClusterNodeId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		LCSClusterNode newLCSClusterNode = addLCSClusterNode();

		_persistence.clearCache();

		LCSClusterNode existingLCSClusterNode = _persistence.findByPrimaryKey(newLCSClusterNode.getPrimaryKey());

		Assert.assertTrue(Objects.equals(existingLCSClusterNode.getKey(),
				ReflectionTestUtil.invoke(existingLCSClusterNode,
					"getOriginalKey", new Class<?>[0])));
	}

	protected LCSClusterNode addLCSClusterNode() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LCSClusterNode lcsClusterNode = _persistence.create(pk);

		lcsClusterNode.setLcsClusterEntryId(RandomTestUtil.nextLong());

		lcsClusterNode.setInstallationId(RandomTestUtil.nextLong());

		lcsClusterNode.setName(RandomTestUtil.randomString());

		lcsClusterNode.setDescription(RandomTestUtil.randomString());

		lcsClusterNode.setBuildNumber(RandomTestUtil.nextInt());

		lcsClusterNode.setKey(RandomTestUtil.randomString());

		lcsClusterNode.setLocation(RandomTestUtil.randomString());

		lcsClusterNode.setProcessorCoresTotal(RandomTestUtil.nextInt());

		lcsClusterNode.setArchived(RandomTestUtil.randomBoolean());

		lcsClusterNode.setStatus(RandomTestUtil.nextInt());

		_lcsClusterNodes.add(_persistence.update(lcsClusterNode));

		return lcsClusterNode;
	}

	private List<LCSClusterNode> _lcsClusterNodes = new ArrayList<LCSClusterNode>();
	private LCSClusterNodePersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}