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

import com.liferay.portal.NoSuchLayoutSetException;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.model.impl.LayoutSetModelImpl;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.test.LiferayPersistenceIntegrationJUnitTestRunner;
import com.liferay.portal.test.persistence.test.TransactionalPersistenceAdvice;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.test.RandomTestUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
@ExecutionTestListeners(listeners =  {
	PersistenceExecutionTestListener.class})
@RunWith(LiferayPersistenceIntegrationJUnitTestRunner.class)
public class LayoutSetPersistenceTest {
	@Before
	public void setUp() {
		_modelListeners = _persistence.getListeners();

		for (ModelListener<LayoutSet> modelListener : _modelListeners) {
			_persistence.unregisterListener(modelListener);
		}
	}

	@After
	public void tearDown() throws Exception {
		Map<Serializable, BasePersistence<?>> basePersistences = _transactionalPersistenceAdvice.getBasePersistences();

		Set<Serializable> primaryKeys = basePersistences.keySet();

		for (Serializable primaryKey : primaryKeys) {
			BasePersistence<?> basePersistence = basePersistences.get(primaryKey);

			try {
				basePersistence.remove(primaryKey);
			}
			catch (Exception e) {
				if (_log.isDebugEnabled()) {
					_log.debug("The model with primary key " + primaryKey +
						" was already deleted");
				}
			}
		}

		_transactionalPersistenceAdvice.reset();

		for (ModelListener<LayoutSet> modelListener : _modelListeners) {
			_persistence.registerListener(modelListener);
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LayoutSet layoutSet = _persistence.create(pk);

		Assert.assertNotNull(layoutSet);

		Assert.assertEquals(layoutSet.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		LayoutSet newLayoutSet = addLayoutSet();

		_persistence.remove(newLayoutSet);

		LayoutSet existingLayoutSet = _persistence.fetchByPrimaryKey(newLayoutSet.getPrimaryKey());

		Assert.assertNull(existingLayoutSet);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addLayoutSet();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LayoutSet newLayoutSet = _persistence.create(pk);

		newLayoutSet.setMvccVersion(RandomTestUtil.nextLong());

		newLayoutSet.setGroupId(RandomTestUtil.nextLong());

		newLayoutSet.setCompanyId(RandomTestUtil.nextLong());

		newLayoutSet.setCreateDate(RandomTestUtil.nextDate());

		newLayoutSet.setModifiedDate(RandomTestUtil.nextDate());

		newLayoutSet.setPrivateLayout(RandomTestUtil.randomBoolean());

		newLayoutSet.setLogoId(RandomTestUtil.nextLong());

		newLayoutSet.setThemeId(RandomTestUtil.randomString());

		newLayoutSet.setColorSchemeId(RandomTestUtil.randomString());

		newLayoutSet.setWapThemeId(RandomTestUtil.randomString());

		newLayoutSet.setWapColorSchemeId(RandomTestUtil.randomString());

		newLayoutSet.setCss(RandomTestUtil.randomString());

		newLayoutSet.setPageCount(RandomTestUtil.nextInt());

		newLayoutSet.setSettings(RandomTestUtil.randomString());

		newLayoutSet.setLayoutSetPrototypeUuid(RandomTestUtil.randomString());

		newLayoutSet.setLayoutSetPrototypeLinkEnabled(RandomTestUtil.randomBoolean());

		_persistence.update(newLayoutSet);

		LayoutSet existingLayoutSet = _persistence.findByPrimaryKey(newLayoutSet.getPrimaryKey());

		Assert.assertEquals(existingLayoutSet.getMvccVersion(),
			newLayoutSet.getMvccVersion());
		Assert.assertEquals(existingLayoutSet.getLayoutSetId(),
			newLayoutSet.getLayoutSetId());
		Assert.assertEquals(existingLayoutSet.getGroupId(),
			newLayoutSet.getGroupId());
		Assert.assertEquals(existingLayoutSet.getCompanyId(),
			newLayoutSet.getCompanyId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingLayoutSet.getCreateDate()),
			Time.getShortTimestamp(newLayoutSet.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingLayoutSet.getModifiedDate()),
			Time.getShortTimestamp(newLayoutSet.getModifiedDate()));
		Assert.assertEquals(existingLayoutSet.getPrivateLayout(),
			newLayoutSet.getPrivateLayout());
		Assert.assertEquals(existingLayoutSet.getLogoId(),
			newLayoutSet.getLogoId());
		Assert.assertEquals(existingLayoutSet.getThemeId(),
			newLayoutSet.getThemeId());
		Assert.assertEquals(existingLayoutSet.getColorSchemeId(),
			newLayoutSet.getColorSchemeId());
		Assert.assertEquals(existingLayoutSet.getWapThemeId(),
			newLayoutSet.getWapThemeId());
		Assert.assertEquals(existingLayoutSet.getWapColorSchemeId(),
			newLayoutSet.getWapColorSchemeId());
		Assert.assertEquals(existingLayoutSet.getCss(), newLayoutSet.getCss());
		Assert.assertEquals(existingLayoutSet.getPageCount(),
			newLayoutSet.getPageCount());
		Assert.assertEquals(existingLayoutSet.getSettings(),
			newLayoutSet.getSettings());
		Assert.assertEquals(existingLayoutSet.getLayoutSetPrototypeUuid(),
			newLayoutSet.getLayoutSetPrototypeUuid());
		Assert.assertEquals(existingLayoutSet.getLayoutSetPrototypeLinkEnabled(),
			newLayoutSet.getLayoutSetPrototypeLinkEnabled());
	}

	@Test
	public void testCountByGroupId() {
		try {
			_persistence.countByGroupId(RandomTestUtil.nextLong());

			_persistence.countByGroupId(0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByLayoutSetPrototypeUuid() {
		try {
			_persistence.countByLayoutSetPrototypeUuid(StringPool.BLANK);

			_persistence.countByLayoutSetPrototypeUuid(StringPool.NULL);

			_persistence.countByLayoutSetPrototypeUuid((String)null);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByG_P() {
		try {
			_persistence.countByG_P(RandomTestUtil.nextLong(),
				RandomTestUtil.randomBoolean());

			_persistence.countByG_P(0L, RandomTestUtil.randomBoolean());
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		LayoutSet newLayoutSet = addLayoutSet();

		LayoutSet existingLayoutSet = _persistence.findByPrimaryKey(newLayoutSet.getPrimaryKey());

		Assert.assertEquals(existingLayoutSet, newLayoutSet);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail("Missing entity did not throw NoSuchLayoutSetException");
		}
		catch (NoSuchLayoutSetException nsee) {
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

	protected OrderByComparator getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("LayoutSet", "mvccVersion",
			true, "layoutSetId", true, "groupId", true, "companyId", true,
			"createDate", true, "modifiedDate", true, "privateLayout", true,
			"logoId", true, "themeId", true, "colorSchemeId", true,
			"wapThemeId", true, "wapColorSchemeId", true, "css", true,
			"pageCount", true, "settings", true, "layoutSetPrototypeUuid",
			true, "layoutSetPrototypeLinkEnabled", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		LayoutSet newLayoutSet = addLayoutSet();

		LayoutSet existingLayoutSet = _persistence.fetchByPrimaryKey(newLayoutSet.getPrimaryKey());

		Assert.assertEquals(existingLayoutSet, newLayoutSet);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LayoutSet missingLayoutSet = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingLayoutSet);
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = LayoutSetLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod() {
				@Override
				public void performAction(Object object) {
					LayoutSet layoutSet = (LayoutSet)object;

					Assert.assertNotNull(layoutSet);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		LayoutSet newLayoutSet = addLayoutSet();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutSet.class,
				LayoutSet.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("layoutSetId",
				newLayoutSet.getLayoutSetId()));

		List<LayoutSet> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		LayoutSet existingLayoutSet = result.get(0);

		Assert.assertEquals(existingLayoutSet, newLayoutSet);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutSet.class,
				LayoutSet.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("layoutSetId",
				RandomTestUtil.nextLong()));

		List<LayoutSet> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		LayoutSet newLayoutSet = addLayoutSet();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutSet.class,
				LayoutSet.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("layoutSetId"));

		Object newLayoutSetId = newLayoutSet.getLayoutSetId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("layoutSetId",
				new Object[] { newLayoutSetId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingLayoutSetId = result.get(0);

		Assert.assertEquals(existingLayoutSetId, newLayoutSetId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(LayoutSet.class,
				LayoutSet.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("layoutSetId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("layoutSetId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		if (!PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			return;
		}

		LayoutSet newLayoutSet = addLayoutSet();

		_persistence.clearCache();

		LayoutSetModelImpl existingLayoutSetModelImpl = (LayoutSetModelImpl)_persistence.findByPrimaryKey(newLayoutSet.getPrimaryKey());

		Assert.assertEquals(existingLayoutSetModelImpl.getGroupId(),
			existingLayoutSetModelImpl.getOriginalGroupId());
		Assert.assertEquals(existingLayoutSetModelImpl.getPrivateLayout(),
			existingLayoutSetModelImpl.getOriginalPrivateLayout());
	}

	protected LayoutSet addLayoutSet() throws Exception {
		long pk = RandomTestUtil.nextLong();

		LayoutSet layoutSet = _persistence.create(pk);

		layoutSet.setMvccVersion(RandomTestUtil.nextLong());

		layoutSet.setGroupId(RandomTestUtil.nextLong());

		layoutSet.setCompanyId(RandomTestUtil.nextLong());

		layoutSet.setCreateDate(RandomTestUtil.nextDate());

		layoutSet.setModifiedDate(RandomTestUtil.nextDate());

		layoutSet.setPrivateLayout(RandomTestUtil.randomBoolean());

		layoutSet.setLogoId(RandomTestUtil.nextLong());

		layoutSet.setThemeId(RandomTestUtil.randomString());

		layoutSet.setColorSchemeId(RandomTestUtil.randomString());

		layoutSet.setWapThemeId(RandomTestUtil.randomString());

		layoutSet.setWapColorSchemeId(RandomTestUtil.randomString());

		layoutSet.setCss(RandomTestUtil.randomString());

		layoutSet.setPageCount(RandomTestUtil.nextInt());

		layoutSet.setSettings(RandomTestUtil.randomString());

		layoutSet.setLayoutSetPrototypeUuid(RandomTestUtil.randomString());

		layoutSet.setLayoutSetPrototypeLinkEnabled(RandomTestUtil.randomBoolean());

		_persistence.update(layoutSet);

		return layoutSet;
	}

	private static Log _log = LogFactoryUtil.getLog(LayoutSetPersistenceTest.class);
	private ModelListener<LayoutSet>[] _modelListeners;
	private LayoutSetPersistence _persistence = (LayoutSetPersistence)PortalBeanLocatorUtil.locate(LayoutSetPersistence.class.getName());
	private TransactionalPersistenceAdvice _transactionalPersistenceAdvice = (TransactionalPersistenceAdvice)PortalBeanLocatorUtil.locate(TransactionalPersistenceAdvice.class.getName());
}