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

package com.liferay.osb.ldn.documentation.project.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.osb.ldn.documentation.project.exception.NoSuchDocumentationProjectException;
import com.liferay.osb.ldn.documentation.project.model.DocumentationProject;
import com.liferay.osb.ldn.documentation.project.service.DocumentationProjectLocalServiceUtil;
import com.liferay.osb.ldn.documentation.project.service.persistence.DocumentationProjectPersistence;
import com.liferay.osb.ldn.documentation.project.service.persistence.DocumentationProjectUtil;

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
public class DocumentationProjectPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = DocumentationProjectUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<DocumentationProject> iterator = _documentationProjects.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DocumentationProject documentationProject = _persistence.create(pk);

		Assert.assertNotNull(documentationProject);

		Assert.assertEquals(documentationProject.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		DocumentationProject newDocumentationProject = addDocumentationProject();

		_persistence.remove(newDocumentationProject);

		DocumentationProject existingDocumentationProject = _persistence.fetchByPrimaryKey(newDocumentationProject.getPrimaryKey());

		Assert.assertNull(existingDocumentationProject);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addDocumentationProject();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DocumentationProject newDocumentationProject = _persistence.create(pk);

		newDocumentationProject.setUuid(RandomTestUtil.randomString());

		newDocumentationProject.setCompanyId(RandomTestUtil.nextLong());

		newDocumentationProject.setUserId(RandomTestUtil.nextLong());

		newDocumentationProject.setUserName(RandomTestUtil.randomString());

		newDocumentationProject.setCreateDate(RandomTestUtil.nextDate());

		newDocumentationProject.setModifiedDate(RandomTestUtil.nextDate());

		newDocumentationProject.setName(RandomTestUtil.randomString());

		newDocumentationProject.setDescription(RandomTestUtil.randomString());

		newDocumentationProject.setIconFileName(RandomTestUtil.randomString());

		newDocumentationProject.setStatus(RandomTestUtil.nextInt());

		_documentationProjects.add(_persistence.update(newDocumentationProject));

		DocumentationProject existingDocumentationProject = _persistence.findByPrimaryKey(newDocumentationProject.getPrimaryKey());

		Assert.assertEquals(existingDocumentationProject.getUuid(),
			newDocumentationProject.getUuid());
		Assert.assertEquals(existingDocumentationProject.getDocumentationProjectId(),
			newDocumentationProject.getDocumentationProjectId());
		Assert.assertEquals(existingDocumentationProject.getCompanyId(),
			newDocumentationProject.getCompanyId());
		Assert.assertEquals(existingDocumentationProject.getUserId(),
			newDocumentationProject.getUserId());
		Assert.assertEquals(existingDocumentationProject.getUserName(),
			newDocumentationProject.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingDocumentationProject.getCreateDate()),
			Time.getShortTimestamp(newDocumentationProject.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingDocumentationProject.getModifiedDate()),
			Time.getShortTimestamp(newDocumentationProject.getModifiedDate()));
		Assert.assertEquals(existingDocumentationProject.getName(),
			newDocumentationProject.getName());
		Assert.assertEquals(existingDocumentationProject.getDescription(),
			newDocumentationProject.getDescription());
		Assert.assertEquals(existingDocumentationProject.getIconFileName(),
			newDocumentationProject.getIconFileName());
		Assert.assertEquals(existingDocumentationProject.getStatus(),
			newDocumentationProject.getStatus());
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid(StringPool.BLANK);

		_persistence.countByUuid(StringPool.NULL);

		_persistence.countByUuid((String)null);
	}

	@Test
	public void testCountByUuid_C() throws Exception {
		_persistence.countByUuid_C(StringPool.BLANK, RandomTestUtil.nextLong());

		_persistence.countByUuid_C(StringPool.NULL, 0L);

		_persistence.countByUuid_C((String)null, 0L);
	}

	@Test
	public void testCountByName() throws Exception {
		_persistence.countByName(StringPool.BLANK);

		_persistence.countByName(StringPool.NULL);

		_persistence.countByName((String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		DocumentationProject newDocumentationProject = addDocumentationProject();

		DocumentationProject existingDocumentationProject = _persistence.findByPrimaryKey(newDocumentationProject.getPrimaryKey());

		Assert.assertEquals(existingDocumentationProject,
			newDocumentationProject);
	}

	@Test(expected = NoSuchDocumentationProjectException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<DocumentationProject> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("OSB_LDN_DocumentationProject",
			"uuid", true, "documentationProjectId", true, "companyId", true,
			"userId", true, "userName", true, "createDate", true,
			"modifiedDate", true, "name", true, "description", true,
			"iconFileName", true, "status", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		DocumentationProject newDocumentationProject = addDocumentationProject();

		DocumentationProject existingDocumentationProject = _persistence.fetchByPrimaryKey(newDocumentationProject.getPrimaryKey());

		Assert.assertEquals(existingDocumentationProject,
			newDocumentationProject);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		DocumentationProject missingDocumentationProject = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingDocumentationProject);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		DocumentationProject newDocumentationProject1 = addDocumentationProject();
		DocumentationProject newDocumentationProject2 = addDocumentationProject();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDocumentationProject1.getPrimaryKey());
		primaryKeys.add(newDocumentationProject2.getPrimaryKey());

		Map<Serializable, DocumentationProject> documentationProjects = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, documentationProjects.size());
		Assert.assertEquals(newDocumentationProject1,
			documentationProjects.get(newDocumentationProject1.getPrimaryKey()));
		Assert.assertEquals(newDocumentationProject2,
			documentationProjects.get(newDocumentationProject2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, DocumentationProject> documentationProjects = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(documentationProjects.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		DocumentationProject newDocumentationProject = addDocumentationProject();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDocumentationProject.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, DocumentationProject> documentationProjects = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, documentationProjects.size());
		Assert.assertEquals(newDocumentationProject,
			documentationProjects.get(newDocumentationProject.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, DocumentationProject> documentationProjects = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(documentationProjects.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		DocumentationProject newDocumentationProject = addDocumentationProject();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newDocumentationProject.getPrimaryKey());

		Map<Serializable, DocumentationProject> documentationProjects = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, documentationProjects.size());
		Assert.assertEquals(newDocumentationProject,
			documentationProjects.get(newDocumentationProject.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = DocumentationProjectLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<DocumentationProject>() {
				@Override
				public void performAction(
					DocumentationProject documentationProject) {
					Assert.assertNotNull(documentationProject);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		DocumentationProject newDocumentationProject = addDocumentationProject();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DocumentationProject.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("documentationProjectId",
				newDocumentationProject.getDocumentationProjectId()));

		List<DocumentationProject> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		DocumentationProject existingDocumentationProject = result.get(0);

		Assert.assertEquals(existingDocumentationProject,
			newDocumentationProject);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DocumentationProject.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("documentationProjectId",
				RandomTestUtil.nextLong()));

		List<DocumentationProject> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		DocumentationProject newDocumentationProject = addDocumentationProject();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DocumentationProject.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"documentationProjectId"));

		Object newDocumentationProjectId = newDocumentationProject.getDocumentationProjectId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("documentationProjectId",
				new Object[] { newDocumentationProjectId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingDocumentationProjectId = result.get(0);

		Assert.assertEquals(existingDocumentationProjectId,
			newDocumentationProjectId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(DocumentationProject.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"documentationProjectId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("documentationProjectId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		DocumentationProject newDocumentationProject = addDocumentationProject();

		_persistence.clearCache();

		DocumentationProject existingDocumentationProject = _persistence.findByPrimaryKey(newDocumentationProject.getPrimaryKey());

		Assert.assertTrue(Objects.equals(
				existingDocumentationProject.getName(),
				ReflectionTestUtil.invoke(existingDocumentationProject,
					"getOriginalName", new Class<?>[0])));
	}

	protected DocumentationProject addDocumentationProject()
		throws Exception {
		long pk = RandomTestUtil.nextLong();

		DocumentationProject documentationProject = _persistence.create(pk);

		documentationProject.setUuid(RandomTestUtil.randomString());

		documentationProject.setCompanyId(RandomTestUtil.nextLong());

		documentationProject.setUserId(RandomTestUtil.nextLong());

		documentationProject.setUserName(RandomTestUtil.randomString());

		documentationProject.setCreateDate(RandomTestUtil.nextDate());

		documentationProject.setModifiedDate(RandomTestUtil.nextDate());

		documentationProject.setName(RandomTestUtil.randomString());

		documentationProject.setDescription(RandomTestUtil.randomString());

		documentationProject.setIconFileName(RandomTestUtil.randomString());

		documentationProject.setStatus(RandomTestUtil.nextInt());

		_documentationProjects.add(_persistence.update(documentationProject));

		return documentationProject;
	}

	private List<DocumentationProject> _documentationProjects = new ArrayList<DocumentationProject>();
	private DocumentationProjectPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}