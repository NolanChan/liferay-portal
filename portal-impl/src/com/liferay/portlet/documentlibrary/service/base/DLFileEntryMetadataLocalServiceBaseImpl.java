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

import com.liferay.document.library.kernel.model.DLFileEntryMetadata;
import com.liferay.document.library.kernel.service.DLFileEntryMetadataLocalService;
import com.liferay.document.library.kernel.service.persistence.DLFileEntryMetadataFinder;
import com.liferay.document.library.kernel.service.persistence.DLFileEntryMetadataPersistence;
import com.liferay.document.library.kernel.service.persistence.DLFileEntryTypeFinder;
import com.liferay.document.library.kernel.service.persistence.DLFileEntryTypePersistence;

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
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the document library file entry metadata local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portlet.documentlibrary.service.impl.DLFileEntryMetadataLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.documentlibrary.service.impl.DLFileEntryMetadataLocalServiceImpl
 * @see com.liferay.document.library.kernel.service.DLFileEntryMetadataLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class DLFileEntryMetadataLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements DLFileEntryMetadataLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.document.library.kernel.service.DLFileEntryMetadataLocalServiceUtil} to access the document library file entry metadata local service.
	 */

	/**
	 * Adds the document library file entry metadata to the database. Also notifies the appropriate model listeners.
	 *
	 * @param dlFileEntryMetadata the document library file entry metadata
	 * @return the document library file entry metadata that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DLFileEntryMetadata addDLFileEntryMetadata(
		DLFileEntryMetadata dlFileEntryMetadata) {
		dlFileEntryMetadata.setNew(true);

		return dlFileEntryMetadataPersistence.update(dlFileEntryMetadata);
	}

	/**
	 * Creates a new document library file entry metadata with the primary key. Does not add the document library file entry metadata to the database.
	 *
	 * @param fileEntryMetadataId the primary key for the new document library file entry metadata
	 * @return the new document library file entry metadata
	 */
	@Override
	public DLFileEntryMetadata createDLFileEntryMetadata(
		long fileEntryMetadataId) {
		return dlFileEntryMetadataPersistence.create(fileEntryMetadataId);
	}

	/**
	 * Deletes the document library file entry metadata with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fileEntryMetadataId the primary key of the document library file entry metadata
	 * @return the document library file entry metadata that was removed
	 * @throws PortalException if a document library file entry metadata with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DLFileEntryMetadata deleteDLFileEntryMetadata(
		long fileEntryMetadataId) throws PortalException {
		return dlFileEntryMetadataPersistence.remove(fileEntryMetadataId);
	}

	/**
	 * Deletes the document library file entry metadata from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dlFileEntryMetadata the document library file entry metadata
	 * @return the document library file entry metadata that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DLFileEntryMetadata deleteDLFileEntryMetadata(
		DLFileEntryMetadata dlFileEntryMetadata) {
		return dlFileEntryMetadataPersistence.remove(dlFileEntryMetadata);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(DLFileEntryMetadata.class,
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
		return dlFileEntryMetadataPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFileEntryMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return dlFileEntryMetadataPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFileEntryMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return dlFileEntryMetadataPersistence.findWithDynamicQuery(dynamicQuery,
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
		return dlFileEntryMetadataPersistence.countWithDynamicQuery(dynamicQuery);
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
		return dlFileEntryMetadataPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public DLFileEntryMetadata fetchDLFileEntryMetadata(
		long fileEntryMetadataId) {
		return dlFileEntryMetadataPersistence.fetchByPrimaryKey(fileEntryMetadataId);
	}

	/**
	 * Returns the document library file entry metadata with the matching UUID and company.
	 *
	 * @param uuid the document library file entry metadata's UUID
	 * @param companyId the primary key of the company
	 * @return the matching document library file entry metadata, or <code>null</code> if a matching document library file entry metadata could not be found
	 */
	@Override
	public DLFileEntryMetadata fetchDLFileEntryMetadataByUuidAndCompanyId(
		String uuid, long companyId) {
		return dlFileEntryMetadataPersistence.fetchByUuid_C_First(uuid,
			companyId, null);
	}

	/**
	 * Returns the document library file entry metadata with the primary key.
	 *
	 * @param fileEntryMetadataId the primary key of the document library file entry metadata
	 * @return the document library file entry metadata
	 * @throws PortalException if a document library file entry metadata with the primary key could not be found
	 */
	@Override
	public DLFileEntryMetadata getDLFileEntryMetadata(long fileEntryMetadataId)
		throws PortalException {
		return dlFileEntryMetadataPersistence.findByPrimaryKey(fileEntryMetadataId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(dlFileEntryMetadataLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(DLFileEntryMetadata.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("fileEntryMetadataId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(dlFileEntryMetadataLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(DLFileEntryMetadata.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"fileEntryMetadataId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(dlFileEntryMetadataLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(DLFileEntryMetadata.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("fileEntryMetadataId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return dlFileEntryMetadataLocalService.deleteDLFileEntryMetadata((DLFileEntryMetadata)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return dlFileEntryMetadataPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the document library file entry metadata with the matching UUID and company.
	 *
	 * @param uuid the document library file entry metadata's UUID
	 * @param companyId the primary key of the company
	 * @return the matching document library file entry metadata
	 * @throws PortalException if a matching document library file entry metadata could not be found
	 */
	@Override
	public DLFileEntryMetadata getDLFileEntryMetadataByUuidAndCompanyId(
		String uuid, long companyId) throws PortalException {
		return dlFileEntryMetadataPersistence.findByUuid_C_First(uuid,
			companyId, null);
	}

	/**
	 * Returns a range of all the document library file entry metadatas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFileEntryMetadataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of document library file entry metadatas
	 * @param end the upper bound of the range of document library file entry metadatas (not inclusive)
	 * @return the range of document library file entry metadatas
	 */
	@Override
	public List<DLFileEntryMetadata> getDLFileEntryMetadatas(int start, int end) {
		return dlFileEntryMetadataPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of document library file entry metadatas.
	 *
	 * @return the number of document library file entry metadatas
	 */
	@Override
	public int getDLFileEntryMetadatasCount() {
		return dlFileEntryMetadataPersistence.countAll();
	}

	/**
	 * Updates the document library file entry metadata in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dlFileEntryMetadata the document library file entry metadata
	 * @return the document library file entry metadata that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DLFileEntryMetadata updateDLFileEntryMetadata(
		DLFileEntryMetadata dlFileEntryMetadata) {
		return dlFileEntryMetadataPersistence.update(dlFileEntryMetadata);
	}

	/**
	 * Returns the document library file entry metadata local service.
	 *
	 * @return the document library file entry metadata local service
	 */
	public DLFileEntryMetadataLocalService getDLFileEntryMetadataLocalService() {
		return dlFileEntryMetadataLocalService;
	}

	/**
	 * Sets the document library file entry metadata local service.
	 *
	 * @param dlFileEntryMetadataLocalService the document library file entry metadata local service
	 */
	public void setDLFileEntryMetadataLocalService(
		DLFileEntryMetadataLocalService dlFileEntryMetadataLocalService) {
		this.dlFileEntryMetadataLocalService = dlFileEntryMetadataLocalService;
	}

	/**
	 * Returns the document library file entry metadata persistence.
	 *
	 * @return the document library file entry metadata persistence
	 */
	public DLFileEntryMetadataPersistence getDLFileEntryMetadataPersistence() {
		return dlFileEntryMetadataPersistence;
	}

	/**
	 * Sets the document library file entry metadata persistence.
	 *
	 * @param dlFileEntryMetadataPersistence the document library file entry metadata persistence
	 */
	public void setDLFileEntryMetadataPersistence(
		DLFileEntryMetadataPersistence dlFileEntryMetadataPersistence) {
		this.dlFileEntryMetadataPersistence = dlFileEntryMetadataPersistence;
	}

	/**
	 * Returns the document library file entry metadata finder.
	 *
	 * @return the document library file entry metadata finder
	 */
	public DLFileEntryMetadataFinder getDLFileEntryMetadataFinder() {
		return dlFileEntryMetadataFinder;
	}

	/**
	 * Sets the document library file entry metadata finder.
	 *
	 * @param dlFileEntryMetadataFinder the document library file entry metadata finder
	 */
	public void setDLFileEntryMetadataFinder(
		DLFileEntryMetadataFinder dlFileEntryMetadataFinder) {
		this.dlFileEntryMetadataFinder = dlFileEntryMetadataFinder;
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

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the document library file entry type local service.
	 *
	 * @return the document library file entry type local service
	 */
	public com.liferay.document.library.kernel.service.DLFileEntryTypeLocalService getDLFileEntryTypeLocalService() {
		return dlFileEntryTypeLocalService;
	}

	/**
	 * Sets the document library file entry type local service.
	 *
	 * @param dlFileEntryTypeLocalService the document library file entry type local service
	 */
	public void setDLFileEntryTypeLocalService(
		com.liferay.document.library.kernel.service.DLFileEntryTypeLocalService dlFileEntryTypeLocalService) {
		this.dlFileEntryTypeLocalService = dlFileEntryTypeLocalService;
	}

	/**
	 * Returns the document library file entry type persistence.
	 *
	 * @return the document library file entry type persistence
	 */
	public DLFileEntryTypePersistence getDLFileEntryTypePersistence() {
		return dlFileEntryTypePersistence;
	}

	/**
	 * Sets the document library file entry type persistence.
	 *
	 * @param dlFileEntryTypePersistence the document library file entry type persistence
	 */
	public void setDLFileEntryTypePersistence(
		DLFileEntryTypePersistence dlFileEntryTypePersistence) {
		this.dlFileEntryTypePersistence = dlFileEntryTypePersistence;
	}

	/**
	 * Returns the document library file entry type finder.
	 *
	 * @return the document library file entry type finder
	 */
	public DLFileEntryTypeFinder getDLFileEntryTypeFinder() {
		return dlFileEntryTypeFinder;
	}

	/**
	 * Sets the document library file entry type finder.
	 *
	 * @param dlFileEntryTypeFinder the document library file entry type finder
	 */
	public void setDLFileEntryTypeFinder(
		DLFileEntryTypeFinder dlFileEntryTypeFinder) {
		this.dlFileEntryTypeFinder = dlFileEntryTypeFinder;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.document.library.kernel.model.DLFileEntryMetadata",
			dlFileEntryMetadataLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.document.library.kernel.model.DLFileEntryMetadata");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return DLFileEntryMetadataLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return DLFileEntryMetadata.class;
	}

	protected String getModelClassName() {
		return DLFileEntryMetadata.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = dlFileEntryMetadataPersistence.getDataSource();

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

	@BeanReference(type = DLFileEntryMetadataLocalService.class)
	protected DLFileEntryMetadataLocalService dlFileEntryMetadataLocalService;
	@BeanReference(type = DLFileEntryMetadataPersistence.class)
	protected DLFileEntryMetadataPersistence dlFileEntryMetadataPersistence;
	@BeanReference(type = DLFileEntryMetadataFinder.class)
	protected DLFileEntryMetadataFinder dlFileEntryMetadataFinder;
	@BeanReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = com.liferay.document.library.kernel.service.DLFileEntryTypeLocalService.class)
	protected com.liferay.document.library.kernel.service.DLFileEntryTypeLocalService dlFileEntryTypeLocalService;
	@BeanReference(type = DLFileEntryTypePersistence.class)
	protected DLFileEntryTypePersistence dlFileEntryTypePersistence;
	@BeanReference(type = DLFileEntryTypeFinder.class)
	protected DLFileEntryTypeFinder dlFileEntryTypeFinder;
	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}