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

package com.liferay.dynamic.data.mapping.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.dynamic.data.mapping.model.DDMStructureVersion;
import com.liferay.dynamic.data.mapping.service.DDMStructureVersionLocalService;
import com.liferay.dynamic.data.mapping.service.persistence.DDMStructureVersionPersistence;

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
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the d d m structure version local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.dynamic.data.mapping.service.impl.DDMStructureVersionLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.dynamic.data.mapping.service.impl.DDMStructureVersionLocalServiceImpl
 * @see com.liferay.dynamic.data.mapping.service.DDMStructureVersionLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class DDMStructureVersionLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements DDMStructureVersionLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.dynamic.data.mapping.service.DDMStructureVersionLocalServiceUtil} to access the d d m structure version local service.
	 */

	/**
	 * Adds the d d m structure version to the database. Also notifies the appropriate model listeners.
	 *
	 * @param ddmStructureVersion the d d m structure version
	 * @return the d d m structure version that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DDMStructureVersion addDDMStructureVersion(
		DDMStructureVersion ddmStructureVersion) {
		ddmStructureVersion.setNew(true);

		return ddmStructureVersionPersistence.update(ddmStructureVersion);
	}

	/**
	 * Creates a new d d m structure version with the primary key. Does not add the d d m structure version to the database.
	 *
	 * @param structureVersionId the primary key for the new d d m structure version
	 * @return the new d d m structure version
	 */
	@Override
	public DDMStructureVersion createDDMStructureVersion(
		long structureVersionId) {
		return ddmStructureVersionPersistence.create(structureVersionId);
	}

	/**
	 * Deletes the d d m structure version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param structureVersionId the primary key of the d d m structure version
	 * @return the d d m structure version that was removed
	 * @throws PortalException if a d d m structure version with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DDMStructureVersion deleteDDMStructureVersion(
		long structureVersionId) throws PortalException {
		return ddmStructureVersionPersistence.remove(structureVersionId);
	}

	/**
	 * Deletes the d d m structure version from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ddmStructureVersion the d d m structure version
	 * @return the d d m structure version that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DDMStructureVersion deleteDDMStructureVersion(
		DDMStructureVersion ddmStructureVersion) {
		return ddmStructureVersionPersistence.remove(ddmStructureVersion);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(DDMStructureVersion.class,
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
		return ddmStructureVersionPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.mapping.model.impl.DDMStructureVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return ddmStructureVersionPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.mapping.model.impl.DDMStructureVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return ddmStructureVersionPersistence.findWithDynamicQuery(dynamicQuery,
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
		return ddmStructureVersionPersistence.countWithDynamicQuery(dynamicQuery);
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
		return ddmStructureVersionPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public DDMStructureVersion fetchDDMStructureVersion(long structureVersionId) {
		return ddmStructureVersionPersistence.fetchByPrimaryKey(structureVersionId);
	}

	/**
	 * Returns the d d m structure version with the primary key.
	 *
	 * @param structureVersionId the primary key of the d d m structure version
	 * @return the d d m structure version
	 * @throws PortalException if a d d m structure version with the primary key could not be found
	 */
	@Override
	public DDMStructureVersion getDDMStructureVersion(long structureVersionId)
		throws PortalException {
		return ddmStructureVersionPersistence.findByPrimaryKey(structureVersionId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(com.liferay.dynamic.data.mapping.service.DDMStructureVersionLocalServiceUtil.getService());
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(DDMStructureVersion.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("structureVersionId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(com.liferay.dynamic.data.mapping.service.DDMStructureVersionLocalServiceUtil.getService());
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(DDMStructureVersion.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"structureVersionId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(com.liferay.dynamic.data.mapping.service.DDMStructureVersionLocalServiceUtil.getService());
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(DDMStructureVersion.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("structureVersionId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return ddmStructureVersionLocalService.deleteDDMStructureVersion((DDMStructureVersion)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return ddmStructureVersionPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the d d m structure versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.mapping.model.impl.DDMStructureVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of d d m structure versions
	 * @param end the upper bound of the range of d d m structure versions (not inclusive)
	 * @return the range of d d m structure versions
	 */
	@Override
	public List<DDMStructureVersion> getDDMStructureVersions(int start, int end) {
		return ddmStructureVersionPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of d d m structure versions.
	 *
	 * @return the number of d d m structure versions
	 */
	@Override
	public int getDDMStructureVersionsCount() {
		return ddmStructureVersionPersistence.countAll();
	}

	/**
	 * Updates the d d m structure version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param ddmStructureVersion the d d m structure version
	 * @return the d d m structure version that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DDMStructureVersion updateDDMStructureVersion(
		DDMStructureVersion ddmStructureVersion) {
		return ddmStructureVersionPersistence.update(ddmStructureVersion);
	}

	/**
	 * Returns the d d m structure version local service.
	 *
	 * @return the d d m structure version local service
	 */
	public DDMStructureVersionLocalService getDDMStructureVersionLocalService() {
		return ddmStructureVersionLocalService;
	}

	/**
	 * Sets the d d m structure version local service.
	 *
	 * @param ddmStructureVersionLocalService the d d m structure version local service
	 */
	public void setDDMStructureVersionLocalService(
		DDMStructureVersionLocalService ddmStructureVersionLocalService) {
		this.ddmStructureVersionLocalService = ddmStructureVersionLocalService;
	}

	/**
	 * Returns the d d m structure version persistence.
	 *
	 * @return the d d m structure version persistence
	 */
	public DDMStructureVersionPersistence getDDMStructureVersionPersistence() {
		return ddmStructureVersionPersistence;
	}

	/**
	 * Sets the d d m structure version persistence.
	 *
	 * @param ddmStructureVersionPersistence the d d m structure version persistence
	 */
	public void setDDMStructureVersionPersistence(
		DDMStructureVersionPersistence ddmStructureVersionPersistence) {
		this.ddmStructureVersionPersistence = ddmStructureVersionPersistence;
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
		persistedModelLocalServiceRegistry.register("com.liferay.dynamic.data.mapping.model.DDMStructureVersion",
			ddmStructureVersionLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.dynamic.data.mapping.model.DDMStructureVersion");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return DDMStructureVersionLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return DDMStructureVersion.class;
	}

	protected String getModelClassName() {
		return DDMStructureVersion.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = ddmStructureVersionPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

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

	@BeanReference(type = com.liferay.dynamic.data.mapping.service.DDMStructureVersionLocalService.class)
	protected DDMStructureVersionLocalService ddmStructureVersionLocalService;
	@BeanReference(type = DDMStructureVersionPersistence.class)
	protected DDMStructureVersionPersistence ddmStructureVersionPersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}