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
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
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
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.model.ResourceAction;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.service.ResourceActionLocalService;
import com.liferay.portal.service.persistence.ResourceActionPersistence;
import com.liferay.portal.service.persistence.ResourcePermissionFinder;
import com.liferay.portal.service.persistence.ResourcePermissionPersistence;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the resource action local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.ResourceActionLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.ResourceActionLocalServiceImpl
 * @see com.liferay.portal.service.ResourceActionLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class ResourceActionLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements ResourceActionLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.portal.service.ResourceActionLocalServiceUtil} to access the resource action local service.
	 */

	/**
	 * Adds the resource action to the database. Also notifies the appropriate model listeners.
	 *
	 * @param resourceAction the resource action
	 * @return the resource action that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ResourceAction addResourceAction(ResourceAction resourceAction) {
		resourceAction.setNew(true);

		return resourceActionPersistence.update(resourceAction);
	}

	/**
	 * Creates a new resource action with the primary key. Does not add the resource action to the database.
	 *
	 * @param resourceActionId the primary key for the new resource action
	 * @return the new resource action
	 */
	@Override
	public ResourceAction createResourceAction(long resourceActionId) {
		return resourceActionPersistence.create(resourceActionId);
	}

	/**
	 * Deletes the resource action with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param resourceActionId the primary key of the resource action
	 * @return the resource action that was removed
	 * @throws PortalException if a resource action with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ResourceAction deleteResourceAction(long resourceActionId)
		throws PortalException {
		return resourceActionPersistence.remove(resourceActionId);
	}

	/**
	 * Deletes the resource action from the database. Also notifies the appropriate model listeners.
	 *
	 * @param resourceAction the resource action
	 * @return the resource action that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ResourceAction deleteResourceAction(ResourceAction resourceAction) {
		return resourceActionPersistence.remove(resourceAction);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(ResourceAction.class,
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
		return resourceActionPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.ResourceActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return resourceActionPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.ResourceActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return resourceActionPersistence.findWithDynamicQuery(dynamicQuery,
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
		return resourceActionPersistence.countWithDynamicQuery(dynamicQuery);
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
		return resourceActionPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public ResourceAction fetchResourceAction(long resourceActionId) {
		return resourceActionPersistence.fetchByPrimaryKey(resourceActionId);
	}

	/**
	 * Returns the resource action with the primary key.
	 *
	 * @param resourceActionId the primary key of the resource action
	 * @return the resource action
	 * @throws PortalException if a resource action with the primary key could not be found
	 */
	@Override
	public ResourceAction getResourceAction(long resourceActionId)
		throws PortalException {
		return resourceActionPersistence.findByPrimaryKey(resourceActionId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(com.liferay.portal.service.ResourceActionLocalServiceUtil.getService());
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(ResourceAction.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("resourceActionId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(com.liferay.portal.service.ResourceActionLocalServiceUtil.getService());
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(ResourceAction.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"resourceActionId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(com.liferay.portal.service.ResourceActionLocalServiceUtil.getService());
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(ResourceAction.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("resourceActionId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return resourceActionLocalService.deleteResourceAction((ResourceAction)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return resourceActionPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the resource actions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.ResourceActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of resource actions
	 * @param end the upper bound of the range of resource actions (not inclusive)
	 * @return the range of resource actions
	 */
	@Override
	public List<ResourceAction> getResourceActions(int start, int end) {
		return resourceActionPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of resource actions.
	 *
	 * @return the number of resource actions
	 */
	@Override
	public int getResourceActionsCount() {
		return resourceActionPersistence.countAll();
	}

	/**
	 * Updates the resource action in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param resourceAction the resource action
	 * @return the resource action that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ResourceAction updateResourceAction(ResourceAction resourceAction) {
		return resourceActionPersistence.update(resourceAction);
	}

	/**
	 * Returns the resource action local service.
	 *
	 * @return the resource action local service
	 */
	public ResourceActionLocalService getResourceActionLocalService() {
		return resourceActionLocalService;
	}

	/**
	 * Sets the resource action local service.
	 *
	 * @param resourceActionLocalService the resource action local service
	 */
	public void setResourceActionLocalService(
		ResourceActionLocalService resourceActionLocalService) {
		this.resourceActionLocalService = resourceActionLocalService;
	}

	/**
	 * Returns the resource action persistence.
	 *
	 * @return the resource action persistence
	 */
	public ResourceActionPersistence getResourceActionPersistence() {
		return resourceActionPersistence;
	}

	/**
	 * Sets the resource action persistence.
	 *
	 * @param resourceActionPersistence the resource action persistence
	 */
	public void setResourceActionPersistence(
		ResourceActionPersistence resourceActionPersistence) {
		this.resourceActionPersistence = resourceActionPersistence;
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
	 * Returns the resource permission local service.
	 *
	 * @return the resource permission local service
	 */
	public com.liferay.portal.service.ResourcePermissionLocalService getResourcePermissionLocalService() {
		return resourcePermissionLocalService;
	}

	/**
	 * Sets the resource permission local service.
	 *
	 * @param resourcePermissionLocalService the resource permission local service
	 */
	public void setResourcePermissionLocalService(
		com.liferay.portal.service.ResourcePermissionLocalService resourcePermissionLocalService) {
		this.resourcePermissionLocalService = resourcePermissionLocalService;
	}

	/**
	 * Returns the resource permission remote service.
	 *
	 * @return the resource permission remote service
	 */
	public com.liferay.portal.service.ResourcePermissionService getResourcePermissionService() {
		return resourcePermissionService;
	}

	/**
	 * Sets the resource permission remote service.
	 *
	 * @param resourcePermissionService the resource permission remote service
	 */
	public void setResourcePermissionService(
		com.liferay.portal.service.ResourcePermissionService resourcePermissionService) {
		this.resourcePermissionService = resourcePermissionService;
	}

	/**
	 * Returns the resource permission persistence.
	 *
	 * @return the resource permission persistence
	 */
	public ResourcePermissionPersistence getResourcePermissionPersistence() {
		return resourcePermissionPersistence;
	}

	/**
	 * Sets the resource permission persistence.
	 *
	 * @param resourcePermissionPersistence the resource permission persistence
	 */
	public void setResourcePermissionPersistence(
		ResourcePermissionPersistence resourcePermissionPersistence) {
		this.resourcePermissionPersistence = resourcePermissionPersistence;
	}

	/**
	 * Returns the resource permission finder.
	 *
	 * @return the resource permission finder
	 */
	public ResourcePermissionFinder getResourcePermissionFinder() {
		return resourcePermissionFinder;
	}

	/**
	 * Sets the resource permission finder.
	 *
	 * @param resourcePermissionFinder the resource permission finder
	 */
	public void setResourcePermissionFinder(
		ResourcePermissionFinder resourcePermissionFinder) {
		this.resourcePermissionFinder = resourcePermissionFinder;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.portal.model.ResourceAction",
			resourceActionLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portal.model.ResourceAction");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return ResourceActionLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return ResourceAction.class;
	}

	protected String getModelClassName() {
		return ResourceAction.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = resourceActionPersistence.getDataSource();

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

	@BeanReference(type = com.liferay.portal.service.ResourceActionLocalService.class)
	protected ResourceActionLocalService resourceActionLocalService;
	@BeanReference(type = ResourceActionPersistence.class)
	protected ResourceActionPersistence resourceActionPersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.ResourcePermissionLocalService.class)
	protected com.liferay.portal.service.ResourcePermissionLocalService resourcePermissionLocalService;
	@BeanReference(type = com.liferay.portal.service.ResourcePermissionService.class)
	protected com.liferay.portal.service.ResourcePermissionService resourcePermissionService;
	@BeanReference(type = ResourcePermissionPersistence.class)
	protected ResourcePermissionPersistence resourcePermissionPersistence;
	@BeanReference(type = ResourcePermissionFinder.class)
	protected ResourcePermissionFinder resourcePermissionFinder;
	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}