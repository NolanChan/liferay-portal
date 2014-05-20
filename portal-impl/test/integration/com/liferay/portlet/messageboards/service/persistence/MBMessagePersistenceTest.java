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

package com.liferay.portlet.messageboards.service.persistence;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.test.AssertUtils;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelListener;
import com.liferay.test.portal.service.ServiceTestUtil;
import com.liferay.portal.service.persistence.BasePersistence;
import com.liferay.portal.service.persistence.PersistenceExecutionTestListener;
import com.liferay.portal.test.LiferayPersistenceIntegrationJUnitTestRunner;
import com.liferay.portal.test.persistence.TransactionalPersistenceAdvice;
import com.liferay.portal.util.PropsValues;

import com.liferay.portlet.messageboards.NoSuchMessageException;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.impl.MBMessageModelImpl;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;

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
public class MBMessagePersistenceTest {
	@Before
	public void setUp() {
		_modelListeners = _persistence.getListeners();

		for (ModelListener<MBMessage> modelListener : _modelListeners) {
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

		for (ModelListener<MBMessage> modelListener : _modelListeners) {
			_persistence.registerListener(modelListener);
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		MBMessage mbMessage = _persistence.create(pk);

		Assert.assertNotNull(mbMessage);

		Assert.assertEquals(mbMessage.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		MBMessage newMBMessage = addMBMessage();

		_persistence.remove(newMBMessage);

		MBMessage existingMBMessage = _persistence.fetchByPrimaryKey(newMBMessage.getPrimaryKey());

		Assert.assertNull(existingMBMessage);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addMBMessage();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		MBMessage newMBMessage = _persistence.create(pk);

		newMBMessage.setUuid(ServiceTestUtil.randomString());

		newMBMessage.setGroupId(ServiceTestUtil.nextLong());

		newMBMessage.setCompanyId(ServiceTestUtil.nextLong());

		newMBMessage.setUserId(ServiceTestUtil.nextLong());

		newMBMessage.setUserName(ServiceTestUtil.randomString());

		newMBMessage.setCreateDate(ServiceTestUtil.nextDate());

		newMBMessage.setModifiedDate(ServiceTestUtil.nextDate());

		newMBMessage.setClassNameId(ServiceTestUtil.nextLong());

		newMBMessage.setClassPK(ServiceTestUtil.nextLong());

		newMBMessage.setCategoryId(ServiceTestUtil.nextLong());

		newMBMessage.setThreadId(ServiceTestUtil.nextLong());

		newMBMessage.setRootMessageId(ServiceTestUtil.nextLong());

		newMBMessage.setParentMessageId(ServiceTestUtil.nextLong());

		newMBMessage.setSubject(ServiceTestUtil.randomString());

		newMBMessage.setBody(ServiceTestUtil.randomString());

		newMBMessage.setFormat(ServiceTestUtil.randomString());

		newMBMessage.setAnonymous(ServiceTestUtil.randomBoolean());

		newMBMessage.setPriority(ServiceTestUtil.nextDouble());

		newMBMessage.setAllowPingbacks(ServiceTestUtil.randomBoolean());

		newMBMessage.setAnswer(ServiceTestUtil.randomBoolean());

		newMBMessage.setStatus(ServiceTestUtil.nextInt());

		newMBMessage.setStatusByUserId(ServiceTestUtil.nextLong());

		newMBMessage.setStatusByUserName(ServiceTestUtil.randomString());

		newMBMessage.setStatusDate(ServiceTestUtil.nextDate());

		_persistence.update(newMBMessage);

		MBMessage existingMBMessage = _persistence.findByPrimaryKey(newMBMessage.getPrimaryKey());

		Assert.assertEquals(existingMBMessage.getUuid(), newMBMessage.getUuid());
		Assert.assertEquals(existingMBMessage.getMessageId(),
			newMBMessage.getMessageId());
		Assert.assertEquals(existingMBMessage.getGroupId(),
			newMBMessage.getGroupId());
		Assert.assertEquals(existingMBMessage.getCompanyId(),
			newMBMessage.getCompanyId());
		Assert.assertEquals(existingMBMessage.getUserId(),
			newMBMessage.getUserId());
		Assert.assertEquals(existingMBMessage.getUserName(),
			newMBMessage.getUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingMBMessage.getCreateDate()),
			Time.getShortTimestamp(newMBMessage.getCreateDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingMBMessage.getModifiedDate()),
			Time.getShortTimestamp(newMBMessage.getModifiedDate()));
		Assert.assertEquals(existingMBMessage.getClassNameId(),
			newMBMessage.getClassNameId());
		Assert.assertEquals(existingMBMessage.getClassPK(),
			newMBMessage.getClassPK());
		Assert.assertEquals(existingMBMessage.getCategoryId(),
			newMBMessage.getCategoryId());
		Assert.assertEquals(existingMBMessage.getThreadId(),
			newMBMessage.getThreadId());
		Assert.assertEquals(existingMBMessage.getRootMessageId(),
			newMBMessage.getRootMessageId());
		Assert.assertEquals(existingMBMessage.getParentMessageId(),
			newMBMessage.getParentMessageId());
		Assert.assertEquals(existingMBMessage.getSubject(),
			newMBMessage.getSubject());
		Assert.assertEquals(existingMBMessage.getBody(), newMBMessage.getBody());
		Assert.assertEquals(existingMBMessage.getFormat(),
			newMBMessage.getFormat());
		Assert.assertEquals(existingMBMessage.getAnonymous(),
			newMBMessage.getAnonymous());
		AssertUtils.assertEquals(existingMBMessage.getPriority(),
			newMBMessage.getPriority());
		Assert.assertEquals(existingMBMessage.getAllowPingbacks(),
			newMBMessage.getAllowPingbacks());
		Assert.assertEquals(existingMBMessage.getAnswer(),
			newMBMessage.getAnswer());
		Assert.assertEquals(existingMBMessage.getStatus(),
			newMBMessage.getStatus());
		Assert.assertEquals(existingMBMessage.getStatusByUserId(),
			newMBMessage.getStatusByUserId());
		Assert.assertEquals(existingMBMessage.getStatusByUserName(),
			newMBMessage.getStatusByUserName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingMBMessage.getStatusDate()),
			Time.getShortTimestamp(newMBMessage.getStatusDate()));
	}

	@Test
	public void testCountByUuid() {
		try {
			_persistence.countByUuid(StringPool.BLANK);

			_persistence.countByUuid(StringPool.NULL);

			_persistence.countByUuid((String)null);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByUUID_G() {
		try {
			_persistence.countByUUID_G(StringPool.BLANK,
				ServiceTestUtil.nextLong());

			_persistence.countByUUID_G(StringPool.NULL, 0L);

			_persistence.countByUUID_G((String)null, 0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByUuid_C() {
		try {
			_persistence.countByUuid_C(StringPool.BLANK,
				ServiceTestUtil.nextLong());

			_persistence.countByUuid_C(StringPool.NULL, 0L);

			_persistence.countByUuid_C((String)null, 0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByGroupId() {
		try {
			_persistence.countByGroupId(ServiceTestUtil.nextLong());

			_persistence.countByGroupId(0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByCompanyId() {
		try {
			_persistence.countByCompanyId(ServiceTestUtil.nextLong());

			_persistence.countByCompanyId(0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByThreadId() {
		try {
			_persistence.countByThreadId(ServiceTestUtil.nextLong());

			_persistence.countByThreadId(0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByThreadReplies() {
		try {
			_persistence.countByThreadReplies(ServiceTestUtil.nextLong());

			_persistence.countByThreadReplies(0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByUserId() {
		try {
			_persistence.countByUserId(ServiceTestUtil.nextLong());

			_persistence.countByUserId(0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByG_U() {
		try {
			_persistence.countByG_U(ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextLong());

			_persistence.countByG_U(0L, 0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByG_C() {
		try {
			_persistence.countByG_C(ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextLong());

			_persistence.countByG_C(0L, 0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByG_S() {
		try {
			_persistence.countByG_S(ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextInt());

			_persistence.countByG_S(0L, 0);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByC_S() {
		try {
			_persistence.countByC_S(ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextInt());

			_persistence.countByC_S(0L, 0);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByU_C() {
		try {
			_persistence.countByU_C(ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextLong());

			_persistence.countByU_C(0L, 0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByU_CArrayable() {
		try {
			_persistence.countByU_C(ServiceTestUtil.nextLong(),
				new long[] { ServiceTestUtil.nextLong(), 0L });
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByC_C() {
		try {
			_persistence.countByC_C(ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextLong());

			_persistence.countByC_C(0L, 0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByT_P() {
		try {
			_persistence.countByT_P(ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextLong());

			_persistence.countByT_P(0L, 0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByT_A() {
		try {
			_persistence.countByT_A(ServiceTestUtil.nextLong(),
				ServiceTestUtil.randomBoolean());

			_persistence.countByT_A(0L, ServiceTestUtil.randomBoolean());
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByT_S() {
		try {
			_persistence.countByT_S(ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextInt());

			_persistence.countByT_S(0L, 0);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByTR_S() {
		try {
			_persistence.countByTR_S(ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextInt());

			_persistence.countByTR_S(0L, 0);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByG_U_S() {
		try {
			_persistence.countByG_U_S(ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextLong(), ServiceTestUtil.nextInt());

			_persistence.countByG_U_S(0L, 0L, 0);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByG_C_T() {
		try {
			_persistence.countByG_C_T(ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextLong(), ServiceTestUtil.nextLong());

			_persistence.countByG_C_T(0L, 0L, 0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByG_C_S() {
		try {
			_persistence.countByG_C_S(ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextLong(), ServiceTestUtil.nextInt());

			_persistence.countByG_C_S(0L, 0L, 0);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByU_C_C() {
		try {
			_persistence.countByU_C_C(ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextLong(), ServiceTestUtil.nextLong());

			_persistence.countByU_C_C(0L, 0L, 0L);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByU_C_S() {
		try {
			_persistence.countByU_C_S(ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextLong(), ServiceTestUtil.nextInt());

			_persistence.countByU_C_S(0L, 0L, 0);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByU_C_SArrayable() {
		try {
			_persistence.countByU_C_S(ServiceTestUtil.nextLong(),
				new long[] { ServiceTestUtil.nextLong(), 0L },
				ServiceTestUtil.nextInt());
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByC_C_S() {
		try {
			_persistence.countByC_C_S(ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextLong(), ServiceTestUtil.nextInt());

			_persistence.countByC_C_S(0L, 0L, 0);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByG_C_T_A() {
		try {
			_persistence.countByG_C_T_A(ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextLong(), ServiceTestUtil.nextLong(),
				ServiceTestUtil.randomBoolean());

			_persistence.countByG_C_T_A(0L, 0L, 0L,
				ServiceTestUtil.randomBoolean());
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByG_C_T_S() {
		try {
			_persistence.countByG_C_T_S(ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextLong(), ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextInt());

			_persistence.countByG_C_T_S(0L, 0L, 0L, 0);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testCountByU_C_C_S() {
		try {
			_persistence.countByU_C_C_S(ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextLong(), ServiceTestUtil.nextLong(),
				ServiceTestUtil.nextInt());

			_persistence.countByU_C_C_S(0L, 0L, 0L, 0);
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		MBMessage newMBMessage = addMBMessage();

		MBMessage existingMBMessage = _persistence.findByPrimaryKey(newMBMessage.getPrimaryKey());

		Assert.assertEquals(existingMBMessage, newMBMessage);
	}

	@Test
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		try {
			_persistence.findByPrimaryKey(pk);

			Assert.fail("Missing entity did not throw NoSuchMessageException");
		}
		catch (NoSuchMessageException nsee) {
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

	@Test
	public void testFilterFindByGroupId() throws Exception {
		try {
			_persistence.filterFindByGroupId(0, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, getOrderByComparator());
		}
		catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	protected OrderByComparator getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("MBMessage", "uuid", true,
			"messageId", true, "groupId", true, "companyId", true, "userId",
			true, "userName", true, "createDate", true, "modifiedDate", true,
			"classNameId", true, "classPK", true, "categoryId", true,
			"threadId", true, "rootMessageId", true, "parentMessageId", true,
			"subject", true, "body", true, "format", true, "anonymous", true,
			"priority", true, "allowPingbacks", true, "answer", true, "status",
			true, "statusByUserId", true, "statusByUserName", true,
			"statusDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		MBMessage newMBMessage = addMBMessage();

		MBMessage existingMBMessage = _persistence.fetchByPrimaryKey(newMBMessage.getPrimaryKey());

		Assert.assertEquals(existingMBMessage, newMBMessage);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		MBMessage missingMBMessage = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingMBMessage);
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = MBMessageLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod() {
				@Override
				public void performAction(Object object) {
					MBMessage mbMessage = (MBMessage)object;

					Assert.assertNotNull(mbMessage);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		MBMessage newMBMessage = addMBMessage();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MBMessage.class,
				MBMessage.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("messageId",
				newMBMessage.getMessageId()));

		List<MBMessage> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		MBMessage existingMBMessage = result.get(0);

		Assert.assertEquals(existingMBMessage, newMBMessage);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MBMessage.class,
				MBMessage.class.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("messageId",
				ServiceTestUtil.nextLong()));

		List<MBMessage> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		MBMessage newMBMessage = addMBMessage();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MBMessage.class,
				MBMessage.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("messageId"));

		Object newMessageId = newMBMessage.getMessageId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("messageId",
				new Object[] { newMessageId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingMessageId = result.get(0);

		Assert.assertEquals(existingMessageId, newMessageId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(MBMessage.class,
				MBMessage.class.getClassLoader());

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("messageId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("messageId",
				new Object[] { ServiceTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		if (!PropsValues.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			return;
		}

		MBMessage newMBMessage = addMBMessage();

		_persistence.clearCache();

		MBMessageModelImpl existingMBMessageModelImpl = (MBMessageModelImpl)_persistence.findByPrimaryKey(newMBMessage.getPrimaryKey());

		Assert.assertTrue(Validator.equals(
				existingMBMessageModelImpl.getUuid(),
				existingMBMessageModelImpl.getOriginalUuid()));
		Assert.assertEquals(existingMBMessageModelImpl.getGroupId(),
			existingMBMessageModelImpl.getOriginalGroupId());
	}

	protected MBMessage addMBMessage() throws Exception {
		long pk = ServiceTestUtil.nextLong();

		MBMessage mbMessage = _persistence.create(pk);

		mbMessage.setUuid(ServiceTestUtil.randomString());

		mbMessage.setGroupId(ServiceTestUtil.nextLong());

		mbMessage.setCompanyId(ServiceTestUtil.nextLong());

		mbMessage.setUserId(ServiceTestUtil.nextLong());

		mbMessage.setUserName(ServiceTestUtil.randomString());

		mbMessage.setCreateDate(ServiceTestUtil.nextDate());

		mbMessage.setModifiedDate(ServiceTestUtil.nextDate());

		mbMessage.setClassNameId(ServiceTestUtil.nextLong());

		mbMessage.setClassPK(ServiceTestUtil.nextLong());

		mbMessage.setCategoryId(ServiceTestUtil.nextLong());

		mbMessage.setThreadId(ServiceTestUtil.nextLong());

		mbMessage.setRootMessageId(ServiceTestUtil.nextLong());

		mbMessage.setParentMessageId(ServiceTestUtil.nextLong());

		mbMessage.setSubject(ServiceTestUtil.randomString());

		mbMessage.setBody(ServiceTestUtil.randomString());

		mbMessage.setFormat(ServiceTestUtil.randomString());

		mbMessage.setAnonymous(ServiceTestUtil.randomBoolean());

		mbMessage.setPriority(ServiceTestUtil.nextDouble());

		mbMessage.setAllowPingbacks(ServiceTestUtil.randomBoolean());

		mbMessage.setAnswer(ServiceTestUtil.randomBoolean());

		mbMessage.setStatus(ServiceTestUtil.nextInt());

		mbMessage.setStatusByUserId(ServiceTestUtil.nextLong());

		mbMessage.setStatusByUserName(ServiceTestUtil.randomString());

		mbMessage.setStatusDate(ServiceTestUtil.nextDate());

		_persistence.update(mbMessage);

		return mbMessage;
	}

	private static Log _log = LogFactoryUtil.getLog(MBMessagePersistenceTest.class);
	private ModelListener<MBMessage>[] _modelListeners;
	private MBMessagePersistence _persistence = (MBMessagePersistence)PortalBeanLocatorUtil.locate(MBMessagePersistence.class.getName());
	private TransactionalPersistenceAdvice _transactionalPersistenceAdvice = (TransactionalPersistenceAdvice)PortalBeanLocatorUtil.locate(TransactionalPersistenceAdvice.class.getName());
}