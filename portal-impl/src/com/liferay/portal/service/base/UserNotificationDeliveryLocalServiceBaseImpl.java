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

package com.liferay.portal.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.model.UserNotificationDelivery;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.service.UserNotificationDeliveryLocalService;
import com.liferay.portal.service.persistence.UserFinder;
import com.liferay.portal.service.persistence.UserNotificationDeliveryPersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the user notification delivery local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.UserNotificationDeliveryLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.UserNotificationDeliveryLocalServiceImpl
 * @see com.liferay.portal.service.UserNotificationDeliveryLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class UserNotificationDeliveryLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements UserNotificationDeliveryLocalService,
		IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.portal.service.UserNotificationDeliveryLocalServiceUtil} to access the user notification delivery local service.
	 */

	/**
	 * Adds the user notification delivery to the database. Also notifies the appropriate model listeners.
	 *
	 * @param userNotificationDelivery the user notification delivery
	 * @return the user notification delivery that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public UserNotificationDelivery addUserNotificationDelivery(
		UserNotificationDelivery userNotificationDelivery) {
		userNotificationDelivery.setNew(true);

		return userNotificationDeliveryPersistence.update(userNotificationDelivery);
	}

	/**
	 * Creates a new user notification delivery with the primary key. Does not add the user notification delivery to the database.
	 *
	 * @param userNotificationDeliveryId the primary key for the new user notification delivery
	 * @return the new user notification delivery
	 */
	@Override
	public UserNotificationDelivery createUserNotificationDelivery(
		long userNotificationDeliveryId) {
		return userNotificationDeliveryPersistence.create(userNotificationDeliveryId);
	}

	/**
	 * Deletes the user notification delivery with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userNotificationDeliveryId the primary key of the user notification delivery
	 * @return the user notification delivery that was removed
	 * @throws PortalException if a user notification delivery with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public UserNotificationDelivery deleteUserNotificationDelivery(
		long userNotificationDeliveryId) throws PortalException {
		return userNotificationDeliveryPersistence.remove(userNotificationDeliveryId);
	}

	/**
	 * Deletes the user notification delivery from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userNotificationDelivery the user notification delivery
	 * @return the user notification delivery that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public UserNotificationDelivery deleteUserNotificationDelivery(
		UserNotificationDelivery userNotificationDelivery) {
		return userNotificationDeliveryPersistence.remove(userNotificationDelivery);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(UserNotificationDelivery.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return userNotificationDeliveryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserNotificationDeliveryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return userNotificationDeliveryPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserNotificationDeliveryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return userNotificationDeliveryPersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return userNotificationDeliveryPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return userNotificationDeliveryPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public UserNotificationDelivery fetchUserNotificationDelivery(
		long userNotificationDeliveryId) {
		return userNotificationDeliveryPersistence.fetchByPrimaryKey(userNotificationDeliveryId);
	}

	/**
	 * Returns the user notification delivery with the primary key.
	 *
	 * @param userNotificationDeliveryId the primary key of the user notification delivery
	 * @return the user notification delivery
	 * @throws PortalException if a user notification delivery with the primary key could not be found
	 */
	@Override
	public UserNotificationDelivery getUserNotificationDelivery(
		long userNotificationDeliveryId) throws PortalException {
		return userNotificationDeliveryPersistence.findByPrimaryKey(userNotificationDeliveryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(com.liferay.portal.service.UserNotificationDeliveryLocalServiceUtil.getService());
		actionableDynamicQuery.setClass(UserNotificationDelivery.class);
		actionableDynamicQuery.setClassLoader(getClassLoader());

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"userNotificationDeliveryId");

		return actionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(com.liferay.portal.service.UserNotificationDeliveryLocalServiceUtil.getService());
		actionableDynamicQuery.setClass(UserNotificationDelivery.class);
		actionableDynamicQuery.setClassLoader(getClassLoader());

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"userNotificationDeliveryId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return userNotificationDeliveryLocalService.deleteUserNotificationDelivery((UserNotificationDelivery)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return userNotificationDeliveryPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the user notification deliveries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserNotificationDeliveryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of user notification deliveries
	 * @param end the upper bound of the range of user notification deliveries (not inclusive)
	 * @return the range of user notification deliveries
	 */
	@Override
	public List<UserNotificationDelivery> getUserNotificationDeliveries(
		int start, int end) {
		return userNotificationDeliveryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of user notification deliveries.
	 *
	 * @return the number of user notification deliveries
	 */
	@Override
	public int getUserNotificationDeliveriesCount() {
		return userNotificationDeliveryPersistence.countAll();
	}

	/**
	 * Updates the user notification delivery in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param userNotificationDelivery the user notification delivery
	 * @return the user notification delivery that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public UserNotificationDelivery updateUserNotificationDelivery(
		UserNotificationDelivery userNotificationDelivery) {
		return userNotificationDeliveryPersistence.update(userNotificationDelivery);
	}

	/**
	 * Returns the user notification delivery local service.
	 *
	 * @return the user notification delivery local service
	 */
	public UserNotificationDeliveryLocalService getUserNotificationDeliveryLocalService() {
		return userNotificationDeliveryLocalService;
	}

	/**
	 * Sets the user notification delivery local service.
	 *
	 * @param userNotificationDeliveryLocalService the user notification delivery local service
	 */
	public void setUserNotificationDeliveryLocalService(
		UserNotificationDeliveryLocalService userNotificationDeliveryLocalService) {
		this.userNotificationDeliveryLocalService = userNotificationDeliveryLocalService;
	}

	/**
	 * Returns the user notification delivery persistence.
	 *
	 * @return the user notification delivery persistence
	 */
	public UserNotificationDeliveryPersistence getUserNotificationDeliveryPersistence() {
		return userNotificationDeliveryPersistence;
	}

	/**
	 * Sets the user notification delivery persistence.
	 *
	 * @param userNotificationDeliveryPersistence the user notification delivery persistence
	 */
	public void setUserNotificationDeliveryPersistence(
		UserNotificationDeliveryPersistence userNotificationDeliveryPersistence) {
		this.userNotificationDeliveryPersistence = userNotificationDeliveryPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Returns the user finder.
	 *
	 * @return the user finder
	 */
	public UserFinder getUserFinder() {
		return userFinder;
	}

	/**
	 * Sets the user finder.
	 *
	 * @param userFinder the user finder
	 */
	public void setUserFinder(UserFinder userFinder) {
		this.userFinder = userFinder;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.portal.model.UserNotificationDelivery",
			userNotificationDeliveryLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portal.model.UserNotificationDelivery");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	protected Class<?> getModelClass() {
		return UserNotificationDelivery.class;
	}

	protected String getModelClassName() {
		return UserNotificationDelivery.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = userNotificationDeliveryPersistence.getDataSource();

			DB db = DBFactoryUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = UserNotificationDeliveryLocalService.class)
	protected UserNotificationDeliveryLocalService userNotificationDeliveryLocalService;
	@BeanReference(type = UserNotificationDeliveryPersistence.class)
	protected UserNotificationDeliveryPersistence userNotificationDeliveryPersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = UserFinder.class)
	protected UserFinder userFinder;
	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
	private String _beanIdentifier;
}