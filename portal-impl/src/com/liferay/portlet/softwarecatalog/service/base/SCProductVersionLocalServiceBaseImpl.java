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

package com.liferay.portlet.softwarecatalog.service.base;

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
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.service.persistence.UserFinder;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.softwarecatalog.model.SCProductVersion;
import com.liferay.portlet.softwarecatalog.service.SCProductVersionLocalService;
import com.liferay.portlet.softwarecatalog.service.persistence.SCFrameworkVersionPersistence;
import com.liferay.portlet.softwarecatalog.service.persistence.SCProductEntryPersistence;
import com.liferay.portlet.softwarecatalog.service.persistence.SCProductVersionPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the s c product version local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portlet.softwarecatalog.service.impl.SCProductVersionLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.softwarecatalog.service.impl.SCProductVersionLocalServiceImpl
 * @see com.liferay.portlet.softwarecatalog.service.SCProductVersionLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class SCProductVersionLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements SCProductVersionLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.portlet.softwarecatalog.service.SCProductVersionLocalServiceUtil} to access the s c product version local service.
	 */

	/**
	 * Adds the s c product version to the database. Also notifies the appropriate model listeners.
	 *
	 * @param scProductVersion the s c product version
	 * @return the s c product version that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public SCProductVersion addSCProductVersion(
		SCProductVersion scProductVersion) {
		scProductVersion.setNew(true);

		return scProductVersionPersistence.update(scProductVersion);
	}

	/**
	 * Creates a new s c product version with the primary key. Does not add the s c product version to the database.
	 *
	 * @param productVersionId the primary key for the new s c product version
	 * @return the new s c product version
	 */
	@Override
	public SCProductVersion createSCProductVersion(long productVersionId) {
		return scProductVersionPersistence.create(productVersionId);
	}

	/**
	 * Deletes the s c product version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param productVersionId the primary key of the s c product version
	 * @return the s c product version that was removed
	 * @throws PortalException if a s c product version with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public SCProductVersion deleteSCProductVersion(long productVersionId)
		throws PortalException {
		return scProductVersionPersistence.remove(productVersionId);
	}

	/**
	 * Deletes the s c product version from the database. Also notifies the appropriate model listeners.
	 *
	 * @param scProductVersion the s c product version
	 * @return the s c product version that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public SCProductVersion deleteSCProductVersion(
		SCProductVersion scProductVersion) {
		return scProductVersionPersistence.remove(scProductVersion);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(SCProductVersion.class,
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
		return scProductVersionPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.softwarecatalog.model.impl.SCProductVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return scProductVersionPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.softwarecatalog.model.impl.SCProductVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return scProductVersionPersistence.findWithDynamicQuery(dynamicQuery,
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
		return scProductVersionPersistence.countWithDynamicQuery(dynamicQuery);
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
		return scProductVersionPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public SCProductVersion fetchSCProductVersion(long productVersionId) {
		return scProductVersionPersistence.fetchByPrimaryKey(productVersionId);
	}

	/**
	 * Returns the s c product version with the primary key.
	 *
	 * @param productVersionId the primary key of the s c product version
	 * @return the s c product version
	 * @throws PortalException if a s c product version with the primary key could not be found
	 */
	@Override
	public SCProductVersion getSCProductVersion(long productVersionId)
		throws PortalException {
		return scProductVersionPersistence.findByPrimaryKey(productVersionId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(com.liferay.portlet.softwarecatalog.service.SCProductVersionLocalServiceUtil.getService());
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(SCProductVersion.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("productVersionId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(com.liferay.portlet.softwarecatalog.service.SCProductVersionLocalServiceUtil.getService());
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(SCProductVersion.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"productVersionId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(com.liferay.portlet.softwarecatalog.service.SCProductVersionLocalServiceUtil.getService());
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(SCProductVersion.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("productVersionId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return scProductVersionLocalService.deleteSCProductVersion((SCProductVersion)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return scProductVersionPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the s c product versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.softwarecatalog.model.impl.SCProductVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of s c product versions
	 * @param end the upper bound of the range of s c product versions (not inclusive)
	 * @return the range of s c product versions
	 */
	@Override
	public List<SCProductVersion> getSCProductVersions(int start, int end) {
		return scProductVersionPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of s c product versions.
	 *
	 * @return the number of s c product versions
	 */
	@Override
	public int getSCProductVersionsCount() {
		return scProductVersionPersistence.countAll();
	}

	/**
	 * Updates the s c product version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param scProductVersion the s c product version
	 * @return the s c product version that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public SCProductVersion updateSCProductVersion(
		SCProductVersion scProductVersion) {
		return scProductVersionPersistence.update(scProductVersion);
	}

	/**
	 */
	@Override
	public void addSCFrameworkVersionSCProductVersion(long frameworkVersionId,
		long productVersionId) {
		scFrameworkVersionPersistence.addSCProductVersion(frameworkVersionId,
			productVersionId);
	}

	/**
	 */
	@Override
	public void addSCFrameworkVersionSCProductVersion(long frameworkVersionId,
		SCProductVersion scProductVersion) {
		scFrameworkVersionPersistence.addSCProductVersion(frameworkVersionId,
			scProductVersion);
	}

	/**
	 */
	@Override
	public void addSCFrameworkVersionSCProductVersions(
		long frameworkVersionId, long[] productVersionIds) {
		scFrameworkVersionPersistence.addSCProductVersions(frameworkVersionId,
			productVersionIds);
	}

	/**
	 */
	@Override
	public void addSCFrameworkVersionSCProductVersions(
		long frameworkVersionId, List<SCProductVersion> SCProductVersions) {
		scFrameworkVersionPersistence.addSCProductVersions(frameworkVersionId,
			SCProductVersions);
	}

	/**
	 */
	@Override
	public void clearSCFrameworkVersionSCProductVersions(
		long frameworkVersionId) {
		scFrameworkVersionPersistence.clearSCProductVersions(frameworkVersionId);
	}

	/**
	 */
	@Override
	public void deleteSCFrameworkVersionSCProductVersion(
		long frameworkVersionId, long productVersionId) {
		scFrameworkVersionPersistence.removeSCProductVersion(frameworkVersionId,
			productVersionId);
	}

	/**
	 */
	@Override
	public void deleteSCFrameworkVersionSCProductVersion(
		long frameworkVersionId, SCProductVersion scProductVersion) {
		scFrameworkVersionPersistence.removeSCProductVersion(frameworkVersionId,
			scProductVersion);
	}

	/**
	 */
	@Override
	public void deleteSCFrameworkVersionSCProductVersions(
		long frameworkVersionId, long[] productVersionIds) {
		scFrameworkVersionPersistence.removeSCProductVersions(frameworkVersionId,
			productVersionIds);
	}

	/**
	 */
	@Override
	public void deleteSCFrameworkVersionSCProductVersions(
		long frameworkVersionId, List<SCProductVersion> SCProductVersions) {
		scFrameworkVersionPersistence.removeSCProductVersions(frameworkVersionId,
			SCProductVersions);
	}

	/**
	 * Returns the frameworkVersionIds of the s c framework versions associated with the s c product version.
	 *
	 * @param productVersionId the productVersionId of the s c product version
	 * @return long[] the frameworkVersionIds of s c framework versions associated with the s c product version
	 */
	@Override
	public long[] getSCFrameworkVersionPrimaryKeys(long productVersionId) {
		return scProductVersionPersistence.getSCFrameworkVersionPrimaryKeys(productVersionId);
	}

	/**
	 */
	@Override
	public List<SCProductVersion> getSCFrameworkVersionSCProductVersions(
		long frameworkVersionId) {
		return scFrameworkVersionPersistence.getSCProductVersions(frameworkVersionId);
	}

	/**
	 */
	@Override
	public List<SCProductVersion> getSCFrameworkVersionSCProductVersions(
		long frameworkVersionId, int start, int end) {
		return scFrameworkVersionPersistence.getSCProductVersions(frameworkVersionId,
			start, end);
	}

	/**
	 */
	@Override
	public List<SCProductVersion> getSCFrameworkVersionSCProductVersions(
		long frameworkVersionId, int start, int end,
		OrderByComparator<SCProductVersion> orderByComparator) {
		return scFrameworkVersionPersistence.getSCProductVersions(frameworkVersionId,
			start, end, orderByComparator);
	}

	/**
	 */
	@Override
	public int getSCFrameworkVersionSCProductVersionsCount(
		long frameworkVersionId) {
		return scFrameworkVersionPersistence.getSCProductVersionsSize(frameworkVersionId);
	}

	/**
	 */
	@Override
	public boolean hasSCFrameworkVersionSCProductVersion(
		long frameworkVersionId, long productVersionId) {
		return scFrameworkVersionPersistence.containsSCProductVersion(frameworkVersionId,
			productVersionId);
	}

	/**
	 */
	@Override
	public boolean hasSCFrameworkVersionSCProductVersions(
		long frameworkVersionId) {
		return scFrameworkVersionPersistence.containsSCProductVersions(frameworkVersionId);
	}

	/**
	 */
	@Override
	public void setSCFrameworkVersionSCProductVersions(
		long frameworkVersionId, long[] productVersionIds) {
		scFrameworkVersionPersistence.setSCProductVersions(frameworkVersionId,
			productVersionIds);
	}

	/**
	 * Returns the s c product version local service.
	 *
	 * @return the s c product version local service
	 */
	public SCProductVersionLocalService getSCProductVersionLocalService() {
		return scProductVersionLocalService;
	}

	/**
	 * Sets the s c product version local service.
	 *
	 * @param scProductVersionLocalService the s c product version local service
	 */
	public void setSCProductVersionLocalService(
		SCProductVersionLocalService scProductVersionLocalService) {
		this.scProductVersionLocalService = scProductVersionLocalService;
	}

	/**
	 * Returns the s c product version remote service.
	 *
	 * @return the s c product version remote service
	 */
	public com.liferay.portlet.softwarecatalog.service.SCProductVersionService getSCProductVersionService() {
		return scProductVersionService;
	}

	/**
	 * Sets the s c product version remote service.
	 *
	 * @param scProductVersionService the s c product version remote service
	 */
	public void setSCProductVersionService(
		com.liferay.portlet.softwarecatalog.service.SCProductVersionService scProductVersionService) {
		this.scProductVersionService = scProductVersionService;
	}

	/**
	 * Returns the s c product version persistence.
	 *
	 * @return the s c product version persistence
	 */
	public SCProductVersionPersistence getSCProductVersionPersistence() {
		return scProductVersionPersistence;
	}

	/**
	 * Sets the s c product version persistence.
	 *
	 * @param scProductVersionPersistence the s c product version persistence
	 */
	public void setSCProductVersionPersistence(
		SCProductVersionPersistence scProductVersionPersistence) {
		this.scProductVersionPersistence = scProductVersionPersistence;
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

	/**
	 * Returns the s c framework version local service.
	 *
	 * @return the s c framework version local service
	 */
	public com.liferay.portlet.softwarecatalog.service.SCFrameworkVersionLocalService getSCFrameworkVersionLocalService() {
		return scFrameworkVersionLocalService;
	}

	/**
	 * Sets the s c framework version local service.
	 *
	 * @param scFrameworkVersionLocalService the s c framework version local service
	 */
	public void setSCFrameworkVersionLocalService(
		com.liferay.portlet.softwarecatalog.service.SCFrameworkVersionLocalService scFrameworkVersionLocalService) {
		this.scFrameworkVersionLocalService = scFrameworkVersionLocalService;
	}

	/**
	 * Returns the s c framework version remote service.
	 *
	 * @return the s c framework version remote service
	 */
	public com.liferay.portlet.softwarecatalog.service.SCFrameworkVersionService getSCFrameworkVersionService() {
		return scFrameworkVersionService;
	}

	/**
	 * Sets the s c framework version remote service.
	 *
	 * @param scFrameworkVersionService the s c framework version remote service
	 */
	public void setSCFrameworkVersionService(
		com.liferay.portlet.softwarecatalog.service.SCFrameworkVersionService scFrameworkVersionService) {
		this.scFrameworkVersionService = scFrameworkVersionService;
	}

	/**
	 * Returns the s c framework version persistence.
	 *
	 * @return the s c framework version persistence
	 */
	public SCFrameworkVersionPersistence getSCFrameworkVersionPersistence() {
		return scFrameworkVersionPersistence;
	}

	/**
	 * Sets the s c framework version persistence.
	 *
	 * @param scFrameworkVersionPersistence the s c framework version persistence
	 */
	public void setSCFrameworkVersionPersistence(
		SCFrameworkVersionPersistence scFrameworkVersionPersistence) {
		this.scFrameworkVersionPersistence = scFrameworkVersionPersistence;
	}

	/**
	 * Returns the s c product entry local service.
	 *
	 * @return the s c product entry local service
	 */
	public com.liferay.portlet.softwarecatalog.service.SCProductEntryLocalService getSCProductEntryLocalService() {
		return scProductEntryLocalService;
	}

	/**
	 * Sets the s c product entry local service.
	 *
	 * @param scProductEntryLocalService the s c product entry local service
	 */
	public void setSCProductEntryLocalService(
		com.liferay.portlet.softwarecatalog.service.SCProductEntryLocalService scProductEntryLocalService) {
		this.scProductEntryLocalService = scProductEntryLocalService;
	}

	/**
	 * Returns the s c product entry remote service.
	 *
	 * @return the s c product entry remote service
	 */
	public com.liferay.portlet.softwarecatalog.service.SCProductEntryService getSCProductEntryService() {
		return scProductEntryService;
	}

	/**
	 * Sets the s c product entry remote service.
	 *
	 * @param scProductEntryService the s c product entry remote service
	 */
	public void setSCProductEntryService(
		com.liferay.portlet.softwarecatalog.service.SCProductEntryService scProductEntryService) {
		this.scProductEntryService = scProductEntryService;
	}

	/**
	 * Returns the s c product entry persistence.
	 *
	 * @return the s c product entry persistence
	 */
	public SCProductEntryPersistence getSCProductEntryPersistence() {
		return scProductEntryPersistence;
	}

	/**
	 * Sets the s c product entry persistence.
	 *
	 * @param scProductEntryPersistence the s c product entry persistence
	 */
	public void setSCProductEntryPersistence(
		SCProductEntryPersistence scProductEntryPersistence) {
		this.scProductEntryPersistence = scProductEntryPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.portlet.softwarecatalog.model.SCProductVersion",
			scProductVersionLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portlet.softwarecatalog.model.SCProductVersion");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return SCProductVersionLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return SCProductVersion.class;
	}

	protected String getModelClassName() {
		return SCProductVersion.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = scProductVersionPersistence.getDataSource();

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

	@BeanReference(type = com.liferay.portlet.softwarecatalog.service.SCProductVersionLocalService.class)
	protected SCProductVersionLocalService scProductVersionLocalService;
	@BeanReference(type = com.liferay.portlet.softwarecatalog.service.SCProductVersionService.class)
	protected com.liferay.portlet.softwarecatalog.service.SCProductVersionService scProductVersionService;
	@BeanReference(type = SCProductVersionPersistence.class)
	protected SCProductVersionPersistence scProductVersionPersistence;
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
	@BeanReference(type = com.liferay.portlet.softwarecatalog.service.SCFrameworkVersionLocalService.class)
	protected com.liferay.portlet.softwarecatalog.service.SCFrameworkVersionLocalService scFrameworkVersionLocalService;
	@BeanReference(type = com.liferay.portlet.softwarecatalog.service.SCFrameworkVersionService.class)
	protected com.liferay.portlet.softwarecatalog.service.SCFrameworkVersionService scFrameworkVersionService;
	@BeanReference(type = SCFrameworkVersionPersistence.class)
	protected SCFrameworkVersionPersistence scFrameworkVersionPersistence;
	@BeanReference(type = com.liferay.portlet.softwarecatalog.service.SCProductEntryLocalService.class)
	protected com.liferay.portlet.softwarecatalog.service.SCProductEntryLocalService scProductEntryLocalService;
	@BeanReference(type = com.liferay.portlet.softwarecatalog.service.SCProductEntryService.class)
	protected com.liferay.portlet.softwarecatalog.service.SCProductEntryService scProductEntryService;
	@BeanReference(type = SCProductEntryPersistence.class)
	protected SCProductEntryPersistence scProductEntryPersistence;
	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}