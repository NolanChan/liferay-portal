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
import com.liferay.portal.model.OrgLabor;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.OrgLaborLocalService;
import com.liferay.portal.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.service.persistence.ListTypePersistence;
import com.liferay.portal.service.persistence.OrgLaborPersistence;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the org labor local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.OrgLaborLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.OrgLaborLocalServiceImpl
 * @see com.liferay.portal.service.OrgLaborLocalServiceUtil
 * @generated
 */
public abstract class OrgLaborLocalServiceBaseImpl extends BaseLocalServiceImpl
	implements OrgLaborLocalService, IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.portal.service.OrgLaborLocalServiceUtil} to access the org labor local service.
	 */

	/**
	 * Adds the org labor to the database. Also notifies the appropriate model listeners.
	 *
	 * @param orgLabor the org labor
	 * @return the org labor that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public OrgLabor addOrgLabor(OrgLabor orgLabor) {
		orgLabor.setNew(true);

		return orgLaborPersistence.update(orgLabor);
	}

	/**
	 * Creates a new org labor with the primary key. Does not add the org labor to the database.
	 *
	 * @param orgLaborId the primary key for the new org labor
	 * @return the new org labor
	 */
	@Override
	public OrgLabor createOrgLabor(long orgLaborId) {
		return orgLaborPersistence.create(orgLaborId);
	}

	/**
	 * Deletes the org labor with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param orgLaborId the primary key of the org labor
	 * @return the org labor that was removed
	 * @throws PortalException if a org labor with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public OrgLabor deleteOrgLabor(long orgLaborId) throws PortalException {
		return orgLaborPersistence.remove(orgLaborId);
	}

	/**
	 * Deletes the org labor from the database. Also notifies the appropriate model listeners.
	 *
	 * @param orgLabor the org labor
	 * @return the org labor that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public OrgLabor deleteOrgLabor(OrgLabor orgLabor) {
		return orgLaborPersistence.remove(orgLabor);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(OrgLabor.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery) {
		return orgLaborPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrgLaborModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end) {
		return orgLaborPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrgLaborModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) {
		return orgLaborPersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return orgLaborPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows that match the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return orgLaborPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public OrgLabor fetchOrgLabor(long orgLaborId) {
		return orgLaborPersistence.fetchByPrimaryKey(orgLaborId);
	}

	/**
	 * Returns the org labor with the primary key.
	 *
	 * @param orgLaborId the primary key of the org labor
	 * @return the org labor
	 * @throws PortalException if a org labor with the primary key could not be found
	 */
	@Override
	public OrgLabor getOrgLabor(long orgLaborId) throws PortalException {
		return orgLaborPersistence.findByPrimaryKey(orgLaborId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(com.liferay.portal.service.OrgLaborLocalServiceUtil.getService());
		actionableDynamicQuery.setClass(OrgLabor.class);
		actionableDynamicQuery.setClassLoader(getClassLoader());

		actionableDynamicQuery.setPrimaryKeyPropertyName("orgLaborId");

		return actionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(com.liferay.portal.service.OrgLaborLocalServiceUtil.getService());
		actionableDynamicQuery.setClass(OrgLabor.class);
		actionableDynamicQuery.setClassLoader(getClassLoader());

		actionableDynamicQuery.setPrimaryKeyPropertyName("orgLaborId");
	}

	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return deleteOrgLabor((OrgLabor)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return orgLaborPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the org labors.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.OrgLaborModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of org labors
	 * @param end the upper bound of the range of org labors (not inclusive)
	 * @return the range of org labors
	 */
	@Override
	public List<OrgLabor> getOrgLabors(int start, int end) {
		return orgLaborPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of org labors.
	 *
	 * @return the number of org labors
	 */
	@Override
	public int getOrgLaborsCount() {
		return orgLaborPersistence.countAll();
	}

	/**
	 * Updates the org labor in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param orgLabor the org labor
	 * @return the org labor that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public OrgLabor updateOrgLabor(OrgLabor orgLabor) {
		return orgLaborPersistence.update(orgLabor);
	}

	/**
	 * Returns the org labor local service.
	 *
	 * @return the org labor local service
	 */
	public com.liferay.portal.service.OrgLaborLocalService getOrgLaborLocalService() {
		return orgLaborLocalService;
	}

	/**
	 * Sets the org labor local service.
	 *
	 * @param orgLaborLocalService the org labor local service
	 */
	public void setOrgLaborLocalService(
		com.liferay.portal.service.OrgLaborLocalService orgLaborLocalService) {
		this.orgLaborLocalService = orgLaborLocalService;
	}

	/**
	 * Returns the org labor remote service.
	 *
	 * @return the org labor remote service
	 */
	public com.liferay.portal.service.OrgLaborService getOrgLaborService() {
		return orgLaborService;
	}

	/**
	 * Sets the org labor remote service.
	 *
	 * @param orgLaborService the org labor remote service
	 */
	public void setOrgLaborService(
		com.liferay.portal.service.OrgLaborService orgLaborService) {
		this.orgLaborService = orgLaborService;
	}

	/**
	 * Returns the org labor persistence.
	 *
	 * @return the org labor persistence
	 */
	public OrgLaborPersistence getOrgLaborPersistence() {
		return orgLaborPersistence;
	}

	/**
	 * Sets the org labor persistence.
	 *
	 * @param orgLaborPersistence the org labor persistence
	 */
	public void setOrgLaborPersistence(OrgLaborPersistence orgLaborPersistence) {
		this.orgLaborPersistence = orgLaborPersistence;
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
	 * Returns the list type remote service.
	 *
	 * @return the list type remote service
	 */
	public com.liferay.portal.service.ListTypeService getListTypeService() {
		return listTypeService;
	}

	/**
	 * Sets the list type remote service.
	 *
	 * @param listTypeService the list type remote service
	 */
	public void setListTypeService(
		com.liferay.portal.service.ListTypeService listTypeService) {
		this.listTypeService = listTypeService;
	}

	/**
	 * Returns the list type persistence.
	 *
	 * @return the list type persistence
	 */
	public ListTypePersistence getListTypePersistence() {
		return listTypePersistence;
	}

	/**
	 * Sets the list type persistence.
	 *
	 * @param listTypePersistence the list type persistence
	 */
	public void setListTypePersistence(ListTypePersistence listTypePersistence) {
		this.listTypePersistence = listTypePersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.portal.model.OrgLabor",
			orgLaborLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portal.model.OrgLabor");
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
		return OrgLabor.class;
	}

	protected String getModelClassName() {
		return OrgLabor.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = orgLaborPersistence.getDataSource();

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

	@BeanReference(type = com.liferay.portal.service.OrgLaborLocalService.class)
	protected com.liferay.portal.service.OrgLaborLocalService orgLaborLocalService;
	@BeanReference(type = com.liferay.portal.service.OrgLaborService.class)
	protected com.liferay.portal.service.OrgLaborService orgLaborService;
	@BeanReference(type = OrgLaborPersistence.class)
	protected OrgLaborPersistence orgLaborPersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.ListTypeService.class)
	protected com.liferay.portal.service.ListTypeService listTypeService;
	@BeanReference(type = ListTypePersistence.class)
	protected ListTypePersistence listTypePersistence;
	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
	private String _beanIdentifier;
}