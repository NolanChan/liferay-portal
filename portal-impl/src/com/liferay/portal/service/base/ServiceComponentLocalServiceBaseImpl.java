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
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.ServiceComponent;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.ServiceComponentLocalService;
import com.liferay.portal.kernel.service.persistence.ServiceComponentFinder;
import com.liferay.portal.kernel.service.persistence.ServiceComponentPersistence;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the service component local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.ServiceComponentLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.ServiceComponentLocalServiceImpl
 * @see com.liferay.portal.kernel.service.ServiceComponentLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class ServiceComponentLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements ServiceComponentLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.portal.kernel.service.ServiceComponentLocalServiceUtil} to access the service component local service.
	 */

	/**
	 * Adds the service component to the database. Also notifies the appropriate model listeners.
	 *
	 * @param serviceComponent the service component
	 * @return the service component that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ServiceComponent addServiceComponent(
		ServiceComponent serviceComponent) {
		serviceComponent.setNew(true);

		return serviceComponentPersistence.update(serviceComponent);
	}

	/**
	 * Creates a new service component with the primary key. Does not add the service component to the database.
	 *
	 * @param serviceComponentId the primary key for the new service component
	 * @return the new service component
	 */
	@Override
	public ServiceComponent createServiceComponent(long serviceComponentId) {
		return serviceComponentPersistence.create(serviceComponentId);
	}

	/**
	 * Deletes the service component with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param serviceComponentId the primary key of the service component
	 * @return the service component that was removed
	 * @throws PortalException if a service component with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ServiceComponent deleteServiceComponent(long serviceComponentId)
		throws PortalException {
		return serviceComponentPersistence.remove(serviceComponentId);
	}

	/**
	 * Deletes the service component from the database. Also notifies the appropriate model listeners.
	 *
	 * @param serviceComponent the service component
	 * @return the service component that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ServiceComponent deleteServiceComponent(
		ServiceComponent serviceComponent) {
		return serviceComponentPersistence.remove(serviceComponent);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(ServiceComponent.class,
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
		return serviceComponentPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.ServiceComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return serviceComponentPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.ServiceComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return serviceComponentPersistence.findWithDynamicQuery(dynamicQuery,
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
		return serviceComponentPersistence.countWithDynamicQuery(dynamicQuery);
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
		return serviceComponentPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public ServiceComponent fetchServiceComponent(long serviceComponentId) {
		return serviceComponentPersistence.fetchByPrimaryKey(serviceComponentId);
	}

	/**
	 * Returns the service component with the primary key.
	 *
	 * @param serviceComponentId the primary key of the service component
	 * @return the service component
	 * @throws PortalException if a service component with the primary key could not be found
	 */
	@Override
	public ServiceComponent getServiceComponent(long serviceComponentId)
		throws PortalException {
		return serviceComponentPersistence.findByPrimaryKey(serviceComponentId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(serviceComponentLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(ServiceComponent.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("serviceComponentId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(serviceComponentLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(ServiceComponent.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"serviceComponentId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(serviceComponentLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(ServiceComponent.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("serviceComponentId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return serviceComponentLocalService.deleteServiceComponent((ServiceComponent)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return serviceComponentPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the service components.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.ServiceComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of service components
	 * @param end the upper bound of the range of service components (not inclusive)
	 * @return the range of service components
	 */
	@Override
	public List<ServiceComponent> getServiceComponents(int start, int end) {
		return serviceComponentPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of service components.
	 *
	 * @return the number of service components
	 */
	@Override
	public int getServiceComponentsCount() {
		return serviceComponentPersistence.countAll();
	}

	/**
	 * Updates the service component in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param serviceComponent the service component
	 * @return the service component that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ServiceComponent updateServiceComponent(
		ServiceComponent serviceComponent) {
		return serviceComponentPersistence.update(serviceComponent);
	}

	/**
	 * Returns the service component local service.
	 *
	 * @return the service component local service
	 */
	public ServiceComponentLocalService getServiceComponentLocalService() {
		return serviceComponentLocalService;
	}

	/**
	 * Sets the service component local service.
	 *
	 * @param serviceComponentLocalService the service component local service
	 */
	public void setServiceComponentLocalService(
		ServiceComponentLocalService serviceComponentLocalService) {
		this.serviceComponentLocalService = serviceComponentLocalService;
	}

	/**
	 * Returns the service component persistence.
	 *
	 * @return the service component persistence
	 */
	public ServiceComponentPersistence getServiceComponentPersistence() {
		return serviceComponentPersistence;
	}

	/**
	 * Sets the service component persistence.
	 *
	 * @param serviceComponentPersistence the service component persistence
	 */
	public void setServiceComponentPersistence(
		ServiceComponentPersistence serviceComponentPersistence) {
		this.serviceComponentPersistence = serviceComponentPersistence;
	}

	/**
	 * Returns the service component finder.
	 *
	 * @return the service component finder
	 */
	public ServiceComponentFinder getServiceComponentFinder() {
		return serviceComponentFinder;
	}

	/**
	 * Sets the service component finder.
	 *
	 * @param serviceComponentFinder the service component finder
	 */
	public void setServiceComponentFinder(
		ServiceComponentFinder serviceComponentFinder) {
		this.serviceComponentFinder = serviceComponentFinder;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.portal.kernel.model.ServiceComponent",
			serviceComponentLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portal.kernel.model.ServiceComponent");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return ServiceComponentLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return ServiceComponent.class;
	}

	protected String getModelClassName() {
		return ServiceComponent.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = serviceComponentPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.portal.kernel.service.ServiceComponentLocalService.class)
	protected ServiceComponentLocalService serviceComponentLocalService;
	@BeanReference(type = ServiceComponentPersistence.class)
	protected ServiceComponentPersistence serviceComponentPersistence;
	@BeanReference(type = ServiceComponentFinder.class)
	protected ServiceComponentFinder serviceComponentFinder;
	@BeanReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}