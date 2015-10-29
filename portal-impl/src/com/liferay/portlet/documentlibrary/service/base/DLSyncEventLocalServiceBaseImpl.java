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

package com.liferay.portlet.documentlibrary.service.base;

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
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.documentlibrary.model.DLSyncEvent;
import com.liferay.portlet.documentlibrary.service.DLSyncEventLocalService;
import com.liferay.portlet.documentlibrary.service.persistence.DLSyncEventPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the d l sync event local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portlet.documentlibrary.service.impl.DLSyncEventLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.documentlibrary.service.impl.DLSyncEventLocalServiceImpl
 * @see com.liferay.portlet.documentlibrary.service.DLSyncEventLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class DLSyncEventLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements DLSyncEventLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.portlet.documentlibrary.service.DLSyncEventLocalServiceUtil} to access the d l sync event local service.
	 */

	/**
	 * Adds the d l sync event to the database. Also notifies the appropriate model listeners.
	 *
	 * @param dlSyncEvent the d l sync event
	 * @return the d l sync event that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DLSyncEvent addDLSyncEvent(DLSyncEvent dlSyncEvent) {
		dlSyncEvent.setNew(true);

		return dlSyncEventPersistence.update(dlSyncEvent);
	}

	/**
	 * Creates a new d l sync event with the primary key. Does not add the d l sync event to the database.
	 *
	 * @param syncEventId the primary key for the new d l sync event
	 * @return the new d l sync event
	 */
	@Override
	public DLSyncEvent createDLSyncEvent(long syncEventId) {
		return dlSyncEventPersistence.create(syncEventId);
	}

	/**
	 * Deletes the d l sync event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param syncEventId the primary key of the d l sync event
	 * @return the d l sync event that was removed
	 * @throws PortalException if a d l sync event with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DLSyncEvent deleteDLSyncEvent(long syncEventId)
		throws PortalException {
		return dlSyncEventPersistence.remove(syncEventId);
	}

	/**
	 * Deletes the d l sync event from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dlSyncEvent the d l sync event
	 * @return the d l sync event that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DLSyncEvent deleteDLSyncEvent(DLSyncEvent dlSyncEvent) {
		return dlSyncEventPersistence.remove(dlSyncEvent);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(DLSyncEvent.class,
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
		return dlSyncEventPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLSyncEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return dlSyncEventPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLSyncEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return dlSyncEventPersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return dlSyncEventPersistence.countWithDynamicQuery(dynamicQuery);
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
		return dlSyncEventPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public DLSyncEvent fetchDLSyncEvent(long syncEventId) {
		return dlSyncEventPersistence.fetchByPrimaryKey(syncEventId);
	}

	/**
	 * Returns the d l sync event with the primary key.
	 *
	 * @param syncEventId the primary key of the d l sync event
	 * @return the d l sync event
	 * @throws PortalException if a d l sync event with the primary key could not be found
	 */
	@Override
	public DLSyncEvent getDLSyncEvent(long syncEventId)
		throws PortalException {
		return dlSyncEventPersistence.findByPrimaryKey(syncEventId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(com.liferay.portlet.documentlibrary.service.DLSyncEventLocalServiceUtil.getService());
		actionableDynamicQuery.setClass(DLSyncEvent.class);
		actionableDynamicQuery.setClassLoader(getClassLoader());

		actionableDynamicQuery.setPrimaryKeyPropertyName("syncEventId");

		return actionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(com.liferay.portlet.documentlibrary.service.DLSyncEventLocalServiceUtil.getService());
		actionableDynamicQuery.setClass(DLSyncEvent.class);
		actionableDynamicQuery.setClassLoader(getClassLoader());

		actionableDynamicQuery.setPrimaryKeyPropertyName("syncEventId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return dlSyncEventLocalService.deleteDLSyncEvent((DLSyncEvent)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return dlSyncEventPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the d l sync events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLSyncEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of d l sync events
	 * @param end the upper bound of the range of d l sync events (not inclusive)
	 * @return the range of d l sync events
	 */
	@Override
	public List<DLSyncEvent> getDLSyncEvents(int start, int end) {
		return dlSyncEventPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of d l sync events.
	 *
	 * @return the number of d l sync events
	 */
	@Override
	public int getDLSyncEventsCount() {
		return dlSyncEventPersistence.countAll();
	}

	/**
	 * Updates the d l sync event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dlSyncEvent the d l sync event
	 * @return the d l sync event that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DLSyncEvent updateDLSyncEvent(DLSyncEvent dlSyncEvent) {
		return dlSyncEventPersistence.update(dlSyncEvent);
	}

	/**
	 * Returns the d l sync event local service.
	 *
	 * @return the d l sync event local service
	 */
	public DLSyncEventLocalService getDLSyncEventLocalService() {
		return dlSyncEventLocalService;
	}

	/**
	 * Sets the d l sync event local service.
	 *
	 * @param dlSyncEventLocalService the d l sync event local service
	 */
	public void setDLSyncEventLocalService(
		DLSyncEventLocalService dlSyncEventLocalService) {
		this.dlSyncEventLocalService = dlSyncEventLocalService;
	}

	/**
	 * Returns the d l sync event persistence.
	 *
	 * @return the d l sync event persistence
	 */
	public DLSyncEventPersistence getDLSyncEventPersistence() {
		return dlSyncEventPersistence;
	}

	/**
	 * Sets the d l sync event persistence.
	 *
	 * @param dlSyncEventPersistence the d l sync event persistence
	 */
	public void setDLSyncEventPersistence(
		DLSyncEventPersistence dlSyncEventPersistence) {
		this.dlSyncEventPersistence = dlSyncEventPersistence;
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
		persistedModelLocalServiceRegistry.register("com.liferay.portlet.documentlibrary.model.DLSyncEvent",
			dlSyncEventLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portlet.documentlibrary.model.DLSyncEvent");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return DLSyncEventLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return DLSyncEvent.class;
	}

	protected String getModelClassName() {
		return DLSyncEvent.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = dlSyncEventPersistence.getDataSource();

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

	@BeanReference(type = com.liferay.portlet.documentlibrary.service.DLSyncEventLocalService.class)
	protected DLSyncEventLocalService dlSyncEventLocalService;
	@BeanReference(type = DLSyncEventPersistence.class)
	protected DLSyncEventPersistence dlSyncEventPersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}