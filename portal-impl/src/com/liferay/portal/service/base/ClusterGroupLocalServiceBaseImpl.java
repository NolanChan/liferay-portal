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
import com.liferay.portal.model.ClusterGroup;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.ClusterGroupLocalService;
import com.liferay.portal.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.service.persistence.ClusterGroupPersistence;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the cluster group local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.ClusterGroupLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.ClusterGroupLocalServiceImpl
 * @see com.liferay.portal.service.ClusterGroupLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class ClusterGroupLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements ClusterGroupLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.portal.service.ClusterGroupLocalServiceUtil} to access the cluster group local service.
	 */

	/**
	 * Adds the cluster group to the database. Also notifies the appropriate model listeners.
	 *
	 * @param clusterGroup the cluster group
	 * @return the cluster group that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ClusterGroup addClusterGroup(ClusterGroup clusterGroup) {
		clusterGroup.setNew(true);

		return clusterGroupPersistence.update(clusterGroup);
	}

	/**
	 * Creates a new cluster group with the primary key. Does not add the cluster group to the database.
	 *
	 * @param clusterGroupId the primary key for the new cluster group
	 * @return the new cluster group
	 */
	@Override
	public ClusterGroup createClusterGroup(long clusterGroupId) {
		return clusterGroupPersistence.create(clusterGroupId);
	}

	/**
	 * Deletes the cluster group with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param clusterGroupId the primary key of the cluster group
	 * @return the cluster group that was removed
	 * @throws PortalException if a cluster group with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ClusterGroup deleteClusterGroup(long clusterGroupId)
		throws PortalException {
		return clusterGroupPersistence.remove(clusterGroupId);
	}

	/**
	 * Deletes the cluster group from the database. Also notifies the appropriate model listeners.
	 *
	 * @param clusterGroup the cluster group
	 * @return the cluster group that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public ClusterGroup deleteClusterGroup(ClusterGroup clusterGroup) {
		return clusterGroupPersistence.remove(clusterGroup);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(ClusterGroup.class,
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
		return clusterGroupPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.ClusterGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return clusterGroupPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.ClusterGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return clusterGroupPersistence.findWithDynamicQuery(dynamicQuery,
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
		return clusterGroupPersistence.countWithDynamicQuery(dynamicQuery);
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
		return clusterGroupPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public ClusterGroup fetchClusterGroup(long clusterGroupId) {
		return clusterGroupPersistence.fetchByPrimaryKey(clusterGroupId);
	}

	/**
	 * Returns the cluster group with the primary key.
	 *
	 * @param clusterGroupId the primary key of the cluster group
	 * @return the cluster group
	 * @throws PortalException if a cluster group with the primary key could not be found
	 */
	@Override
	public ClusterGroup getClusterGroup(long clusterGroupId)
		throws PortalException {
		return clusterGroupPersistence.findByPrimaryKey(clusterGroupId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(com.liferay.portal.service.ClusterGroupLocalServiceUtil.getService());
		actionableDynamicQuery.setClass(ClusterGroup.class);
		actionableDynamicQuery.setClassLoader(getClassLoader());

		actionableDynamicQuery.setPrimaryKeyPropertyName("clusterGroupId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(com.liferay.portal.service.ClusterGroupLocalServiceUtil.getService());
		indexableActionableDynamicQuery.setClass(ClusterGroup.class);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"clusterGroupId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(com.liferay.portal.service.ClusterGroupLocalServiceUtil.getService());
		actionableDynamicQuery.setClass(ClusterGroup.class);
		actionableDynamicQuery.setClassLoader(getClassLoader());

		actionableDynamicQuery.setPrimaryKeyPropertyName("clusterGroupId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return clusterGroupLocalService.deleteClusterGroup((ClusterGroup)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return clusterGroupPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the cluster groups.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.ClusterGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cluster groups
	 * @param end the upper bound of the range of cluster groups (not inclusive)
	 * @return the range of cluster groups
	 */
	@Override
	public List<ClusterGroup> getClusterGroups(int start, int end) {
		return clusterGroupPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of cluster groups.
	 *
	 * @return the number of cluster groups
	 */
	@Override
	public int getClusterGroupsCount() {
		return clusterGroupPersistence.countAll();
	}

	/**
	 * Updates the cluster group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param clusterGroup the cluster group
	 * @return the cluster group that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public ClusterGroup updateClusterGroup(ClusterGroup clusterGroup) {
		return clusterGroupPersistence.update(clusterGroup);
	}

	/**
	 * Returns the cluster group local service.
	 *
	 * @return the cluster group local service
	 */
	public ClusterGroupLocalService getClusterGroupLocalService() {
		return clusterGroupLocalService;
	}

	/**
	 * Sets the cluster group local service.
	 *
	 * @param clusterGroupLocalService the cluster group local service
	 */
	public void setClusterGroupLocalService(
		ClusterGroupLocalService clusterGroupLocalService) {
		this.clusterGroupLocalService = clusterGroupLocalService;
	}

	/**
	 * Returns the cluster group persistence.
	 *
	 * @return the cluster group persistence
	 */
	public ClusterGroupPersistence getClusterGroupPersistence() {
		return clusterGroupPersistence;
	}

	/**
	 * Sets the cluster group persistence.
	 *
	 * @param clusterGroupPersistence the cluster group persistence
	 */
	public void setClusterGroupPersistence(
		ClusterGroupPersistence clusterGroupPersistence) {
		this.clusterGroupPersistence = clusterGroupPersistence;
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

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.portal.model.ClusterGroup",
			clusterGroupLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portal.model.ClusterGroup");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return ClusterGroupLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return ClusterGroup.class;
	}

	protected String getModelClassName() {
		return ClusterGroup.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = clusterGroupPersistence.getDataSource();

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

	@BeanReference(type = com.liferay.portal.service.ClusterGroupLocalService.class)
	protected ClusterGroupLocalService clusterGroupLocalService;
	@BeanReference(type = ClusterGroupPersistence.class)
	protected ClusterGroupPersistence clusterGroupPersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}