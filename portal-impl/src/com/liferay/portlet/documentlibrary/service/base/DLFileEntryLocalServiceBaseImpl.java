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
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.ExportImportHelperUtil;
import com.liferay.portal.kernel.lar.ManifestSummary;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.service.persistence.ClassNamePersistence;
import com.liferay.portal.service.persistence.GroupFinder;
import com.liferay.portal.service.persistence.GroupPersistence;
import com.liferay.portal.service.persistence.ImagePersistence;
import com.liferay.portal.service.persistence.LockFinder;
import com.liferay.portal.service.persistence.LockPersistence;
import com.liferay.portal.service.persistence.RepositoryPersistence;
import com.liferay.portal.service.persistence.UserFinder;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.WebDAVPropsPersistence;
import com.liferay.portal.service.persistence.WorkflowInstanceLinkPersistence;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.asset.service.persistence.AssetCategoryFinder;
import com.liferay.portlet.asset.service.persistence.AssetCategoryPersistence;
import com.liferay.portlet.asset.service.persistence.AssetEntryFinder;
import com.liferay.portlet.asset.service.persistence.AssetEntryPersistence;
import com.liferay.portlet.asset.service.persistence.AssetLinkPersistence;
import com.liferay.portlet.asset.service.persistence.AssetTagFinder;
import com.liferay.portlet.asset.service.persistence.AssetTagPersistence;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalService;
import com.liferay.portlet.documentlibrary.service.persistence.DLFileEntryFinder;
import com.liferay.portlet.documentlibrary.service.persistence.DLFileEntryMetadataPersistence;
import com.liferay.portlet.documentlibrary.service.persistence.DLFileEntryPersistence;
import com.liferay.portlet.documentlibrary.service.persistence.DLFileEntryTypeFinder;
import com.liferay.portlet.documentlibrary.service.persistence.DLFileEntryTypePersistence;
import com.liferay.portlet.documentlibrary.service.persistence.DLFileVersionPersistence;
import com.liferay.portlet.documentlibrary.service.persistence.DLFolderFinder;
import com.liferay.portlet.documentlibrary.service.persistence.DLFolderPersistence;
import com.liferay.portlet.dynamicdatamapping.service.persistence.DDMStructureFinder;
import com.liferay.portlet.dynamicdatamapping.service.persistence.DDMStructurePersistence;
import com.liferay.portlet.expando.service.persistence.ExpandoRowPersistence;
import com.liferay.portlet.expando.service.persistence.ExpandoTablePersistence;
import com.liferay.portlet.trash.service.persistence.TrashEntryPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the document library file entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portlet.documentlibrary.service.impl.DLFileEntryLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.documentlibrary.service.impl.DLFileEntryLocalServiceImpl
 * @see com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil
 * @generated
 */
public abstract class DLFileEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements DLFileEntryLocalService,
		IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil} to access the document library file entry local service.
	 */

	/**
	 * Adds the document library file entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param dlFileEntry the document library file entry
	 * @return the document library file entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DLFileEntry addDLFileEntry(DLFileEntry dlFileEntry) {
		dlFileEntry.setNew(true);

		return dlFileEntryPersistence.update(dlFileEntry);
	}

	/**
	 * Creates a new document library file entry with the primary key. Does not add the document library file entry to the database.
	 *
	 * @param fileEntryId the primary key for the new document library file entry
	 * @return the new document library file entry
	 */
	@Override
	public DLFileEntry createDLFileEntry(long fileEntryId) {
		return dlFileEntryPersistence.create(fileEntryId);
	}

	/**
	 * Deletes the document library file entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fileEntryId the primary key of the document library file entry
	 * @return the document library file entry that was removed
	 * @throws PortalException if a document library file entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DLFileEntry deleteDLFileEntry(long fileEntryId)
		throws PortalException {
		return dlFileEntryPersistence.remove(fileEntryId);
	}

	/**
	 * Deletes the document library file entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dlFileEntry the document library file entry
	 * @return the document library file entry that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DLFileEntry deleteDLFileEntry(DLFileEntry dlFileEntry) {
		return dlFileEntryPersistence.remove(dlFileEntry);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(DLFileEntry.class,
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
		return dlFileEntryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFileEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return dlFileEntryPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFileEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return dlFileEntryPersistence.findWithDynamicQuery(dynamicQuery, start,
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
		return dlFileEntryPersistence.countWithDynamicQuery(dynamicQuery);
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
		return dlFileEntryPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public DLFileEntry fetchDLFileEntry(long fileEntryId) {
		return dlFileEntryPersistence.fetchByPrimaryKey(fileEntryId);
	}

	/**
	 * Returns the document library file entry with the matching UUID and company.
	 *
	 * @param uuid the document library file entry's UUID
	 * @param  companyId the primary key of the company
	 * @return the matching document library file entry, or <code>null</code> if a matching document library file entry could not be found
	 */
	@Override
	public DLFileEntry fetchDLFileEntryByUuidAndCompanyId(String uuid,
		long companyId) {
		return dlFileEntryPersistence.fetchByUuid_C_First(uuid, companyId, null);
	}

	/**
	 * Returns the document library file entry matching the UUID and group.
	 *
	 * @param uuid the document library file entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching document library file entry, or <code>null</code> if a matching document library file entry could not be found
	 */
	@Override
	public DLFileEntry fetchDLFileEntryByUuidAndGroupId(String uuid,
		long groupId) {
		return dlFileEntryPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the document library file entry with the primary key.
	 *
	 * @param fileEntryId the primary key of the document library file entry
	 * @return the document library file entry
	 * @throws PortalException if a document library file entry with the primary key could not be found
	 */
	@Override
	public DLFileEntry getDLFileEntry(long fileEntryId)
		throws PortalException {
		return dlFileEntryPersistence.findByPrimaryKey(fileEntryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil.getService());
		actionableDynamicQuery.setClass(DLFileEntry.class);
		actionableDynamicQuery.setClassLoader(getClassLoader());

		actionableDynamicQuery.setPrimaryKeyPropertyName("fileEntryId");

		return actionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil.getService());
		actionableDynamicQuery.setClass(DLFileEntry.class);
		actionableDynamicQuery.setClassLoader(getClassLoader());

		actionableDynamicQuery.setPrimaryKeyPropertyName("fileEntryId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {
		final ExportActionableDynamicQuery exportActionableDynamicQuery = new ExportActionableDynamicQuery() {
				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary = portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(stagedModelType.toString(),
						modelAdditionCount);

					long modelDeletionCount = ExportImportHelperUtil.getModelDeletionCount(portletDataContext,
							stagedModelType);

					manifestSummary.addModelDeletionCount(stagedModelType.toString(),
						modelDeletionCount);

					return modelAdditionCount;
				}
			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(new ActionableDynamicQuery.AddCriteriaMethod() {
				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(dynamicQuery,
						"modifiedDate");

					StagedModelType stagedModelType = exportActionableDynamicQuery.getStagedModelType();

					if (stagedModelType.getReferrerClassNameId() >= 0) {
						Property classNameIdProperty = PropertyFactoryUtil.forName(
								"classNameId");

						dynamicQuery.add(classNameIdProperty.eq(
								stagedModelType.getReferrerClassNameId()));
					}
				}
			});

		exportActionableDynamicQuery.setCompanyId(portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setGroupId(portletDataContext.getScopeGroupId());

		exportActionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod() {
				@Override
				public void performAction(Object object)
					throws PortalException {
					DLFileEntry stagedModel = (DLFileEntry)object;

					StagedModelDataHandlerUtil.exportStagedModel(portletDataContext,
						stagedModel);
				}
			});
		exportActionableDynamicQuery.setStagedModelType(new StagedModelType(
				PortalUtil.getClassNameId(DLFileEntry.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return dlFileEntryLocalService.deleteDLFileEntry((DLFileEntry)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return dlFileEntryPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the document library file entry with the matching UUID and company.
	 *
	 * @param uuid the document library file entry's UUID
	 * @param  companyId the primary key of the company
	 * @return the matching document library file entry
	 * @throws PortalException if a matching document library file entry could not be found
	 */
	@Override
	public DLFileEntry getDLFileEntryByUuidAndCompanyId(String uuid,
		long companyId) throws PortalException {
		return dlFileEntryPersistence.findByUuid_C_First(uuid, companyId, null);
	}

	/**
	 * Returns the document library file entry matching the UUID and group.
	 *
	 * @param uuid the document library file entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching document library file entry
	 * @throws PortalException if a matching document library file entry could not be found
	 */
	@Override
	public DLFileEntry getDLFileEntryByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {
		return dlFileEntryPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the document library file entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.documentlibrary.model.impl.DLFileEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of document library file entries
	 * @param end the upper bound of the range of document library file entries (not inclusive)
	 * @return the range of document library file entries
	 */
	@Override
	public List<DLFileEntry> getDLFileEntries(int start, int end) {
		return dlFileEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of document library file entries.
	 *
	 * @return the number of document library file entries
	 */
	@Override
	public int getDLFileEntriesCount() {
		return dlFileEntryPersistence.countAll();
	}

	/**
	 * Updates the document library file entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param dlFileEntry the document library file entry
	 * @return the document library file entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DLFileEntry updateDLFileEntry(DLFileEntry dlFileEntry) {
		return dlFileEntryPersistence.update(dlFileEntry);
	}

	/**
	 * Returns the document library file entry local service.
	 *
	 * @return the document library file entry local service
	 */
	public com.liferay.portlet.documentlibrary.service.DLFileEntryLocalService getDLFileEntryLocalService() {
		return dlFileEntryLocalService;
	}

	/**
	 * Sets the document library file entry local service.
	 *
	 * @param dlFileEntryLocalService the document library file entry local service
	 */
	public void setDLFileEntryLocalService(
		com.liferay.portlet.documentlibrary.service.DLFileEntryLocalService dlFileEntryLocalService) {
		this.dlFileEntryLocalService = dlFileEntryLocalService;
	}

	/**
	 * Returns the document library file entry remote service.
	 *
	 * @return the document library file entry remote service
	 */
	public com.liferay.portlet.documentlibrary.service.DLFileEntryService getDLFileEntryService() {
		return dlFileEntryService;
	}

	/**
	 * Sets the document library file entry remote service.
	 *
	 * @param dlFileEntryService the document library file entry remote service
	 */
	public void setDLFileEntryService(
		com.liferay.portlet.documentlibrary.service.DLFileEntryService dlFileEntryService) {
		this.dlFileEntryService = dlFileEntryService;
	}

	/**
	 * Returns the document library file entry persistence.
	 *
	 * @return the document library file entry persistence
	 */
	public DLFileEntryPersistence getDLFileEntryPersistence() {
		return dlFileEntryPersistence;
	}

	/**
	 * Sets the document library file entry persistence.
	 *
	 * @param dlFileEntryPersistence the document library file entry persistence
	 */
	public void setDLFileEntryPersistence(
		DLFileEntryPersistence dlFileEntryPersistence) {
		this.dlFileEntryPersistence = dlFileEntryPersistence;
	}

	/**
	 * Returns the document library file entry finder.
	 *
	 * @return the document library file entry finder
	 */
	public DLFileEntryFinder getDLFileEntryFinder() {
		return dlFileEntryFinder;
	}

	/**
	 * Sets the document library file entry finder.
	 *
	 * @param dlFileEntryFinder the document library file entry finder
	 */
	public void setDLFileEntryFinder(DLFileEntryFinder dlFileEntryFinder) {
		this.dlFileEntryFinder = dlFileEntryFinder;
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
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name remote service.
	 *
	 * @return the class name remote service
	 */
	public com.liferay.portal.service.ClassNameService getClassNameService() {
		return classNameService;
	}

	/**
	 * Sets the class name remote service.
	 *
	 * @param classNameService the class name remote service
	 */
	public void setClassNameService(
		com.liferay.portal.service.ClassNameService classNameService) {
		this.classNameService = classNameService;
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
	 * Returns the group local service.
	 *
	 * @return the group local service
	 */
	public com.liferay.portal.service.GroupLocalService getGroupLocalService() {
		return groupLocalService;
	}

	/**
	 * Sets the group local service.
	 *
	 * @param groupLocalService the group local service
	 */
	public void setGroupLocalService(
		com.liferay.portal.service.GroupLocalService groupLocalService) {
		this.groupLocalService = groupLocalService;
	}

	/**
	 * Returns the group remote service.
	 *
	 * @return the group remote service
	 */
	public com.liferay.portal.service.GroupService getGroupService() {
		return groupService;
	}

	/**
	 * Sets the group remote service.
	 *
	 * @param groupService the group remote service
	 */
	public void setGroupService(
		com.liferay.portal.service.GroupService groupService) {
		this.groupService = groupService;
	}

	/**
	 * Returns the group persistence.
	 *
	 * @return the group persistence
	 */
	public GroupPersistence getGroupPersistence() {
		return groupPersistence;
	}

	/**
	 * Sets the group persistence.
	 *
	 * @param groupPersistence the group persistence
	 */
	public void setGroupPersistence(GroupPersistence groupPersistence) {
		this.groupPersistence = groupPersistence;
	}

	/**
	 * Returns the group finder.
	 *
	 * @return the group finder
	 */
	public GroupFinder getGroupFinder() {
		return groupFinder;
	}

	/**
	 * Sets the group finder.
	 *
	 * @param groupFinder the group finder
	 */
	public void setGroupFinder(GroupFinder groupFinder) {
		this.groupFinder = groupFinder;
	}

	/**
	 * Returns the image local service.
	 *
	 * @return the image local service
	 */
	public com.liferay.portal.service.ImageLocalService getImageLocalService() {
		return imageLocalService;
	}

	/**
	 * Sets the image local service.
	 *
	 * @param imageLocalService the image local service
	 */
	public void setImageLocalService(
		com.liferay.portal.service.ImageLocalService imageLocalService) {
		this.imageLocalService = imageLocalService;
	}

	/**
	 * Returns the image remote service.
	 *
	 * @return the image remote service
	 */
	public com.liferay.portal.service.ImageService getImageService() {
		return imageService;
	}

	/**
	 * Sets the image remote service.
	 *
	 * @param imageService the image remote service
	 */
	public void setImageService(
		com.liferay.portal.service.ImageService imageService) {
		this.imageService = imageService;
	}

	/**
	 * Returns the image persistence.
	 *
	 * @return the image persistence
	 */
	public ImagePersistence getImagePersistence() {
		return imagePersistence;
	}

	/**
	 * Sets the image persistence.
	 *
	 * @param imagePersistence the image persistence
	 */
	public void setImagePersistence(ImagePersistence imagePersistence) {
		this.imagePersistence = imagePersistence;
	}

	/**
	 * Returns the lock local service.
	 *
	 * @return the lock local service
	 */
	public com.liferay.portal.service.LockLocalService getLockLocalService() {
		return lockLocalService;
	}

	/**
	 * Sets the lock local service.
	 *
	 * @param lockLocalService the lock local service
	 */
	public void setLockLocalService(
		com.liferay.portal.service.LockLocalService lockLocalService) {
		this.lockLocalService = lockLocalService;
	}

	/**
	 * Returns the lock persistence.
	 *
	 * @return the lock persistence
	 */
	public LockPersistence getLockPersistence() {
		return lockPersistence;
	}

	/**
	 * Sets the lock persistence.
	 *
	 * @param lockPersistence the lock persistence
	 */
	public void setLockPersistence(LockPersistence lockPersistence) {
		this.lockPersistence = lockPersistence;
	}

	/**
	 * Returns the lock finder.
	 *
	 * @return the lock finder
	 */
	public LockFinder getLockFinder() {
		return lockFinder;
	}

	/**
	 * Sets the lock finder.
	 *
	 * @param lockFinder the lock finder
	 */
	public void setLockFinder(LockFinder lockFinder) {
		this.lockFinder = lockFinder;
	}

	/**
	 * Returns the repository local service.
	 *
	 * @return the repository local service
	 */
	public com.liferay.portal.service.RepositoryLocalService getRepositoryLocalService() {
		return repositoryLocalService;
	}

	/**
	 * Sets the repository local service.
	 *
	 * @param repositoryLocalService the repository local service
	 */
	public void setRepositoryLocalService(
		com.liferay.portal.service.RepositoryLocalService repositoryLocalService) {
		this.repositoryLocalService = repositoryLocalService;
	}

	/**
	 * Returns the repository remote service.
	 *
	 * @return the repository remote service
	 */
	public com.liferay.portal.service.RepositoryService getRepositoryService() {
		return repositoryService;
	}

	/**
	 * Sets the repository remote service.
	 *
	 * @param repositoryService the repository remote service
	 */
	public void setRepositoryService(
		com.liferay.portal.service.RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	/**
	 * Returns the repository persistence.
	 *
	 * @return the repository persistence
	 */
	public RepositoryPersistence getRepositoryPersistence() {
		return repositoryPersistence;
	}

	/**
	 * Sets the repository persistence.
	 *
	 * @param repositoryPersistence the repository persistence
	 */
	public void setRepositoryPersistence(
		RepositoryPersistence repositoryPersistence) {
		this.repositoryPersistence = repositoryPersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
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
	 * Returns the web d a v props local service.
	 *
	 * @return the web d a v props local service
	 */
	public com.liferay.portal.service.WebDAVPropsLocalService getWebDAVPropsLocalService() {
		return webDAVPropsLocalService;
	}

	/**
	 * Sets the web d a v props local service.
	 *
	 * @param webDAVPropsLocalService the web d a v props local service
	 */
	public void setWebDAVPropsLocalService(
		com.liferay.portal.service.WebDAVPropsLocalService webDAVPropsLocalService) {
		this.webDAVPropsLocalService = webDAVPropsLocalService;
	}

	/**
	 * Returns the web d a v props persistence.
	 *
	 * @return the web d a v props persistence
	 */
	public WebDAVPropsPersistence getWebDAVPropsPersistence() {
		return webDAVPropsPersistence;
	}

	/**
	 * Sets the web d a v props persistence.
	 *
	 * @param webDAVPropsPersistence the web d a v props persistence
	 */
	public void setWebDAVPropsPersistence(
		WebDAVPropsPersistence webDAVPropsPersistence) {
		this.webDAVPropsPersistence = webDAVPropsPersistence;
	}

	/**
	 * Returns the workflow instance link local service.
	 *
	 * @return the workflow instance link local service
	 */
	public com.liferay.portal.service.WorkflowInstanceLinkLocalService getWorkflowInstanceLinkLocalService() {
		return workflowInstanceLinkLocalService;
	}

	/**
	 * Sets the workflow instance link local service.
	 *
	 * @param workflowInstanceLinkLocalService the workflow instance link local service
	 */
	public void setWorkflowInstanceLinkLocalService(
		com.liferay.portal.service.WorkflowInstanceLinkLocalService workflowInstanceLinkLocalService) {
		this.workflowInstanceLinkLocalService = workflowInstanceLinkLocalService;
	}

	/**
	 * Returns the workflow instance link persistence.
	 *
	 * @return the workflow instance link persistence
	 */
	public WorkflowInstanceLinkPersistence getWorkflowInstanceLinkPersistence() {
		return workflowInstanceLinkPersistence;
	}

	/**
	 * Sets the workflow instance link persistence.
	 *
	 * @param workflowInstanceLinkPersistence the workflow instance link persistence
	 */
	public void setWorkflowInstanceLinkPersistence(
		WorkflowInstanceLinkPersistence workflowInstanceLinkPersistence) {
		this.workflowInstanceLinkPersistence = workflowInstanceLinkPersistence;
	}

	/**
	 * Returns the asset category local service.
	 *
	 * @return the asset category local service
	 */
	public com.liferay.portlet.asset.service.AssetCategoryLocalService getAssetCategoryLocalService() {
		return assetCategoryLocalService;
	}

	/**
	 * Sets the asset category local service.
	 *
	 * @param assetCategoryLocalService the asset category local service
	 */
	public void setAssetCategoryLocalService(
		com.liferay.portlet.asset.service.AssetCategoryLocalService assetCategoryLocalService) {
		this.assetCategoryLocalService = assetCategoryLocalService;
	}

	/**
	 * Returns the asset category remote service.
	 *
	 * @return the asset category remote service
	 */
	public com.liferay.portlet.asset.service.AssetCategoryService getAssetCategoryService() {
		return assetCategoryService;
	}

	/**
	 * Sets the asset category remote service.
	 *
	 * @param assetCategoryService the asset category remote service
	 */
	public void setAssetCategoryService(
		com.liferay.portlet.asset.service.AssetCategoryService assetCategoryService) {
		this.assetCategoryService = assetCategoryService;
	}

	/**
	 * Returns the asset category persistence.
	 *
	 * @return the asset category persistence
	 */
	public AssetCategoryPersistence getAssetCategoryPersistence() {
		return assetCategoryPersistence;
	}

	/**
	 * Sets the asset category persistence.
	 *
	 * @param assetCategoryPersistence the asset category persistence
	 */
	public void setAssetCategoryPersistence(
		AssetCategoryPersistence assetCategoryPersistence) {
		this.assetCategoryPersistence = assetCategoryPersistence;
	}

	/**
	 * Returns the asset category finder.
	 *
	 * @return the asset category finder
	 */
	public AssetCategoryFinder getAssetCategoryFinder() {
		return assetCategoryFinder;
	}

	/**
	 * Sets the asset category finder.
	 *
	 * @param assetCategoryFinder the asset category finder
	 */
	public void setAssetCategoryFinder(AssetCategoryFinder assetCategoryFinder) {
		this.assetCategoryFinder = assetCategoryFinder;
	}

	/**
	 * Returns the asset entry local service.
	 *
	 * @return the asset entry local service
	 */
	public com.liferay.portlet.asset.service.AssetEntryLocalService getAssetEntryLocalService() {
		return assetEntryLocalService;
	}

	/**
	 * Sets the asset entry local service.
	 *
	 * @param assetEntryLocalService the asset entry local service
	 */
	public void setAssetEntryLocalService(
		com.liferay.portlet.asset.service.AssetEntryLocalService assetEntryLocalService) {
		this.assetEntryLocalService = assetEntryLocalService;
	}

	/**
	 * Returns the asset entry remote service.
	 *
	 * @return the asset entry remote service
	 */
	public com.liferay.portlet.asset.service.AssetEntryService getAssetEntryService() {
		return assetEntryService;
	}

	/**
	 * Sets the asset entry remote service.
	 *
	 * @param assetEntryService the asset entry remote service
	 */
	public void setAssetEntryService(
		com.liferay.portlet.asset.service.AssetEntryService assetEntryService) {
		this.assetEntryService = assetEntryService;
	}

	/**
	 * Returns the asset entry persistence.
	 *
	 * @return the asset entry persistence
	 */
	public AssetEntryPersistence getAssetEntryPersistence() {
		return assetEntryPersistence;
	}

	/**
	 * Sets the asset entry persistence.
	 *
	 * @param assetEntryPersistence the asset entry persistence
	 */
	public void setAssetEntryPersistence(
		AssetEntryPersistence assetEntryPersistence) {
		this.assetEntryPersistence = assetEntryPersistence;
	}

	/**
	 * Returns the asset entry finder.
	 *
	 * @return the asset entry finder
	 */
	public AssetEntryFinder getAssetEntryFinder() {
		return assetEntryFinder;
	}

	/**
	 * Sets the asset entry finder.
	 *
	 * @param assetEntryFinder the asset entry finder
	 */
	public void setAssetEntryFinder(AssetEntryFinder assetEntryFinder) {
		this.assetEntryFinder = assetEntryFinder;
	}

	/**
	 * Returns the asset link local service.
	 *
	 * @return the asset link local service
	 */
	public com.liferay.portlet.asset.service.AssetLinkLocalService getAssetLinkLocalService() {
		return assetLinkLocalService;
	}

	/**
	 * Sets the asset link local service.
	 *
	 * @param assetLinkLocalService the asset link local service
	 */
	public void setAssetLinkLocalService(
		com.liferay.portlet.asset.service.AssetLinkLocalService assetLinkLocalService) {
		this.assetLinkLocalService = assetLinkLocalService;
	}

	/**
	 * Returns the asset link persistence.
	 *
	 * @return the asset link persistence
	 */
	public AssetLinkPersistence getAssetLinkPersistence() {
		return assetLinkPersistence;
	}

	/**
	 * Sets the asset link persistence.
	 *
	 * @param assetLinkPersistence the asset link persistence
	 */
	public void setAssetLinkPersistence(
		AssetLinkPersistence assetLinkPersistence) {
		this.assetLinkPersistence = assetLinkPersistence;
	}

	/**
	 * Returns the asset tag local service.
	 *
	 * @return the asset tag local service
	 */
	public com.liferay.portlet.asset.service.AssetTagLocalService getAssetTagLocalService() {
		return assetTagLocalService;
	}

	/**
	 * Sets the asset tag local service.
	 *
	 * @param assetTagLocalService the asset tag local service
	 */
	public void setAssetTagLocalService(
		com.liferay.portlet.asset.service.AssetTagLocalService assetTagLocalService) {
		this.assetTagLocalService = assetTagLocalService;
	}

	/**
	 * Returns the asset tag remote service.
	 *
	 * @return the asset tag remote service
	 */
	public com.liferay.portlet.asset.service.AssetTagService getAssetTagService() {
		return assetTagService;
	}

	/**
	 * Sets the asset tag remote service.
	 *
	 * @param assetTagService the asset tag remote service
	 */
	public void setAssetTagService(
		com.liferay.portlet.asset.service.AssetTagService assetTagService) {
		this.assetTagService = assetTagService;
	}

	/**
	 * Returns the asset tag persistence.
	 *
	 * @return the asset tag persistence
	 */
	public AssetTagPersistence getAssetTagPersistence() {
		return assetTagPersistence;
	}

	/**
	 * Sets the asset tag persistence.
	 *
	 * @param assetTagPersistence the asset tag persistence
	 */
	public void setAssetTagPersistence(AssetTagPersistence assetTagPersistence) {
		this.assetTagPersistence = assetTagPersistence;
	}

	/**
	 * Returns the asset tag finder.
	 *
	 * @return the asset tag finder
	 */
	public AssetTagFinder getAssetTagFinder() {
		return assetTagFinder;
	}

	/**
	 * Sets the asset tag finder.
	 *
	 * @param assetTagFinder the asset tag finder
	 */
	public void setAssetTagFinder(AssetTagFinder assetTagFinder) {
		this.assetTagFinder = assetTagFinder;
	}

	/**
	 * Returns the d l app helper local service.
	 *
	 * @return the d l app helper local service
	 */
	public com.liferay.portlet.documentlibrary.service.DLAppHelperLocalService getDLAppHelperLocalService() {
		return dlAppHelperLocalService;
	}

	/**
	 * Sets the d l app helper local service.
	 *
	 * @param dlAppHelperLocalService the d l app helper local service
	 */
	public void setDLAppHelperLocalService(
		com.liferay.portlet.documentlibrary.service.DLAppHelperLocalService dlAppHelperLocalService) {
		this.dlAppHelperLocalService = dlAppHelperLocalService;
	}

	/**
	 * Returns the d d m structure local service.
	 *
	 * @return the d d m structure local service
	 */
	public com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalService getDDMStructureLocalService() {
		return ddmStructureLocalService;
	}

	/**
	 * Sets the d d m structure local service.
	 *
	 * @param ddmStructureLocalService the d d m structure local service
	 */
	public void setDDMStructureLocalService(
		com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalService ddmStructureLocalService) {
		this.ddmStructureLocalService = ddmStructureLocalService;
	}

	/**
	 * Returns the d d m structure remote service.
	 *
	 * @return the d d m structure remote service
	 */
	public com.liferay.portlet.dynamicdatamapping.service.DDMStructureService getDDMStructureService() {
		return ddmStructureService;
	}

	/**
	 * Sets the d d m structure remote service.
	 *
	 * @param ddmStructureService the d d m structure remote service
	 */
	public void setDDMStructureService(
		com.liferay.portlet.dynamicdatamapping.service.DDMStructureService ddmStructureService) {
		this.ddmStructureService = ddmStructureService;
	}

	/**
	 * Returns the d d m structure persistence.
	 *
	 * @return the d d m structure persistence
	 */
	public DDMStructurePersistence getDDMStructurePersistence() {
		return ddmStructurePersistence;
	}

	/**
	 * Sets the d d m structure persistence.
	 *
	 * @param ddmStructurePersistence the d d m structure persistence
	 */
	public void setDDMStructurePersistence(
		DDMStructurePersistence ddmStructurePersistence) {
		this.ddmStructurePersistence = ddmStructurePersistence;
	}

	/**
	 * Returns the d d m structure finder.
	 *
	 * @return the d d m structure finder
	 */
	public DDMStructureFinder getDDMStructureFinder() {
		return ddmStructureFinder;
	}

	/**
	 * Sets the d d m structure finder.
	 *
	 * @param ddmStructureFinder the d d m structure finder
	 */
	public void setDDMStructureFinder(DDMStructureFinder ddmStructureFinder) {
		this.ddmStructureFinder = ddmStructureFinder;
	}

	/**
	 * Returns the expando row local service.
	 *
	 * @return the expando row local service
	 */
	public com.liferay.portlet.expando.service.ExpandoRowLocalService getExpandoRowLocalService() {
		return expandoRowLocalService;
	}

	/**
	 * Sets the expando row local service.
	 *
	 * @param expandoRowLocalService the expando row local service
	 */
	public void setExpandoRowLocalService(
		com.liferay.portlet.expando.service.ExpandoRowLocalService expandoRowLocalService) {
		this.expandoRowLocalService = expandoRowLocalService;
	}

	/**
	 * Returns the expando row persistence.
	 *
	 * @return the expando row persistence
	 */
	public ExpandoRowPersistence getExpandoRowPersistence() {
		return expandoRowPersistence;
	}

	/**
	 * Sets the expando row persistence.
	 *
	 * @param expandoRowPersistence the expando row persistence
	 */
	public void setExpandoRowPersistence(
		ExpandoRowPersistence expandoRowPersistence) {
		this.expandoRowPersistence = expandoRowPersistence;
	}

	/**
	 * Returns the expando table local service.
	 *
	 * @return the expando table local service
	 */
	public com.liferay.portlet.expando.service.ExpandoTableLocalService getExpandoTableLocalService() {
		return expandoTableLocalService;
	}

	/**
	 * Sets the expando table local service.
	 *
	 * @param expandoTableLocalService the expando table local service
	 */
	public void setExpandoTableLocalService(
		com.liferay.portlet.expando.service.ExpandoTableLocalService expandoTableLocalService) {
		this.expandoTableLocalService = expandoTableLocalService;
	}

	/**
	 * Returns the expando table persistence.
	 *
	 * @return the expando table persistence
	 */
	public ExpandoTablePersistence getExpandoTablePersistence() {
		return expandoTablePersistence;
	}

	/**
	 * Sets the expando table persistence.
	 *
	 * @param expandoTablePersistence the expando table persistence
	 */
	public void setExpandoTablePersistence(
		ExpandoTablePersistence expandoTablePersistence) {
		this.expandoTablePersistence = expandoTablePersistence;
	}

	/**
	 * Returns the trash entry local service.
	 *
	 * @return the trash entry local service
	 */
	public com.liferay.portlet.trash.service.TrashEntryLocalService getTrashEntryLocalService() {
		return trashEntryLocalService;
	}

	/**
	 * Sets the trash entry local service.
	 *
	 * @param trashEntryLocalService the trash entry local service
	 */
	public void setTrashEntryLocalService(
		com.liferay.portlet.trash.service.TrashEntryLocalService trashEntryLocalService) {
		this.trashEntryLocalService = trashEntryLocalService;
	}

	/**
	 * Returns the trash entry remote service.
	 *
	 * @return the trash entry remote service
	 */
	public com.liferay.portlet.trash.service.TrashEntryService getTrashEntryService() {
		return trashEntryService;
	}

	/**
	 * Sets the trash entry remote service.
	 *
	 * @param trashEntryService the trash entry remote service
	 */
	public void setTrashEntryService(
		com.liferay.portlet.trash.service.TrashEntryService trashEntryService) {
		this.trashEntryService = trashEntryService;
	}

	/**
	 * Returns the trash entry persistence.
	 *
	 * @return the trash entry persistence
	 */
	public TrashEntryPersistence getTrashEntryPersistence() {
		return trashEntryPersistence;
	}

	/**
	 * Sets the trash entry persistence.
	 *
	 * @param trashEntryPersistence the trash entry persistence
	 */
	public void setTrashEntryPersistence(
		TrashEntryPersistence trashEntryPersistence) {
		this.trashEntryPersistence = trashEntryPersistence;
	}

	/**
	 * Returns the document library file entry metadata local service.
	 *
	 * @return the document library file entry metadata local service
	 */
	public com.liferay.portlet.documentlibrary.service.DLFileEntryMetadataLocalService getDLFileEntryMetadataLocalService() {
		return dlFileEntryMetadataLocalService;
	}

	/**
	 * Sets the document library file entry metadata local service.
	 *
	 * @param dlFileEntryMetadataLocalService the document library file entry metadata local service
	 */
	public void setDLFileEntryMetadataLocalService(
		com.liferay.portlet.documentlibrary.service.DLFileEntryMetadataLocalService dlFileEntryMetadataLocalService) {
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
	 * Returns the document library file entry type local service.
	 *
	 * @return the document library file entry type local service
	 */
	public com.liferay.portlet.documentlibrary.service.DLFileEntryTypeLocalService getDLFileEntryTypeLocalService() {
		return dlFileEntryTypeLocalService;
	}

	/**
	 * Sets the document library file entry type local service.
	 *
	 * @param dlFileEntryTypeLocalService the document library file entry type local service
	 */
	public void setDLFileEntryTypeLocalService(
		com.liferay.portlet.documentlibrary.service.DLFileEntryTypeLocalService dlFileEntryTypeLocalService) {
		this.dlFileEntryTypeLocalService = dlFileEntryTypeLocalService;
	}

	/**
	 * Returns the document library file entry type remote service.
	 *
	 * @return the document library file entry type remote service
	 */
	public com.liferay.portlet.documentlibrary.service.DLFileEntryTypeService getDLFileEntryTypeService() {
		return dlFileEntryTypeService;
	}

	/**
	 * Sets the document library file entry type remote service.
	 *
	 * @param dlFileEntryTypeService the document library file entry type remote service
	 */
	public void setDLFileEntryTypeService(
		com.liferay.portlet.documentlibrary.service.DLFileEntryTypeService dlFileEntryTypeService) {
		this.dlFileEntryTypeService = dlFileEntryTypeService;
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

	/**
	 * Returns the document library file version local service.
	 *
	 * @return the document library file version local service
	 */
	public com.liferay.portlet.documentlibrary.service.DLFileVersionLocalService getDLFileVersionLocalService() {
		return dlFileVersionLocalService;
	}

	/**
	 * Sets the document library file version local service.
	 *
	 * @param dlFileVersionLocalService the document library file version local service
	 */
	public void setDLFileVersionLocalService(
		com.liferay.portlet.documentlibrary.service.DLFileVersionLocalService dlFileVersionLocalService) {
		this.dlFileVersionLocalService = dlFileVersionLocalService;
	}

	/**
	 * Returns the document library file version remote service.
	 *
	 * @return the document library file version remote service
	 */
	public com.liferay.portlet.documentlibrary.service.DLFileVersionService getDLFileVersionService() {
		return dlFileVersionService;
	}

	/**
	 * Sets the document library file version remote service.
	 *
	 * @param dlFileVersionService the document library file version remote service
	 */
	public void setDLFileVersionService(
		com.liferay.portlet.documentlibrary.service.DLFileVersionService dlFileVersionService) {
		this.dlFileVersionService = dlFileVersionService;
	}

	/**
	 * Returns the document library file version persistence.
	 *
	 * @return the document library file version persistence
	 */
	public DLFileVersionPersistence getDLFileVersionPersistence() {
		return dlFileVersionPersistence;
	}

	/**
	 * Sets the document library file version persistence.
	 *
	 * @param dlFileVersionPersistence the document library file version persistence
	 */
	public void setDLFileVersionPersistence(
		DLFileVersionPersistence dlFileVersionPersistence) {
		this.dlFileVersionPersistence = dlFileVersionPersistence;
	}

	/**
	 * Returns the document library folder local service.
	 *
	 * @return the document library folder local service
	 */
	public com.liferay.portlet.documentlibrary.service.DLFolderLocalService getDLFolderLocalService() {
		return dlFolderLocalService;
	}

	/**
	 * Sets the document library folder local service.
	 *
	 * @param dlFolderLocalService the document library folder local service
	 */
	public void setDLFolderLocalService(
		com.liferay.portlet.documentlibrary.service.DLFolderLocalService dlFolderLocalService) {
		this.dlFolderLocalService = dlFolderLocalService;
	}

	/**
	 * Returns the document library folder remote service.
	 *
	 * @return the document library folder remote service
	 */
	public com.liferay.portlet.documentlibrary.service.DLFolderService getDLFolderService() {
		return dlFolderService;
	}

	/**
	 * Sets the document library folder remote service.
	 *
	 * @param dlFolderService the document library folder remote service
	 */
	public void setDLFolderService(
		com.liferay.portlet.documentlibrary.service.DLFolderService dlFolderService) {
		this.dlFolderService = dlFolderService;
	}

	/**
	 * Returns the document library folder persistence.
	 *
	 * @return the document library folder persistence
	 */
	public DLFolderPersistence getDLFolderPersistence() {
		return dlFolderPersistence;
	}

	/**
	 * Sets the document library folder persistence.
	 *
	 * @param dlFolderPersistence the document library folder persistence
	 */
	public void setDLFolderPersistence(DLFolderPersistence dlFolderPersistence) {
		this.dlFolderPersistence = dlFolderPersistence;
	}

	/**
	 * Returns the document library folder finder.
	 *
	 * @return the document library folder finder
	 */
	public DLFolderFinder getDLFolderFinder() {
		return dlFolderFinder;
	}

	/**
	 * Sets the document library folder finder.
	 *
	 * @param dlFolderFinder the document library folder finder
	 */
	public void setDLFolderFinder(DLFolderFinder dlFolderFinder) {
		this.dlFolderFinder = dlFolderFinder;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.portlet.documentlibrary.model.DLFileEntry",
			dlFileEntryLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portlet.documentlibrary.model.DLFileEntry");
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
		return DLFileEntry.class;
	}

	protected String getModelClassName() {
		return DLFileEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = dlFileEntryPersistence.getDataSource();

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

	@BeanReference(type = com.liferay.portlet.documentlibrary.service.DLFileEntryLocalService.class)
	protected com.liferay.portlet.documentlibrary.service.DLFileEntryLocalService dlFileEntryLocalService;
	@BeanReference(type = com.liferay.portlet.documentlibrary.service.DLFileEntryService.class)
	protected com.liferay.portlet.documentlibrary.service.DLFileEntryService dlFileEntryService;
	@BeanReference(type = DLFileEntryPersistence.class)
	protected DLFileEntryPersistence dlFileEntryPersistence;
	@BeanReference(type = DLFileEntryFinder.class)
	protected DLFileEntryFinder dlFileEntryFinder;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.ClassNameLocalService.class)
	protected com.liferay.portal.service.ClassNameLocalService classNameLocalService;
	@BeanReference(type = com.liferay.portal.service.ClassNameService.class)
	protected com.liferay.portal.service.ClassNameService classNameService;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = com.liferay.portal.service.GroupLocalService.class)
	protected com.liferay.portal.service.GroupLocalService groupLocalService;
	@BeanReference(type = com.liferay.portal.service.GroupService.class)
	protected com.liferay.portal.service.GroupService groupService;
	@BeanReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;
	@BeanReference(type = GroupFinder.class)
	protected GroupFinder groupFinder;
	@BeanReference(type = com.liferay.portal.service.ImageLocalService.class)
	protected com.liferay.portal.service.ImageLocalService imageLocalService;
	@BeanReference(type = com.liferay.portal.service.ImageService.class)
	protected com.liferay.portal.service.ImageService imageService;
	@BeanReference(type = ImagePersistence.class)
	protected ImagePersistence imagePersistence;
	@BeanReference(type = com.liferay.portal.service.LockLocalService.class)
	protected com.liferay.portal.service.LockLocalService lockLocalService;
	@BeanReference(type = LockPersistence.class)
	protected LockPersistence lockPersistence;
	@BeanReference(type = LockFinder.class)
	protected LockFinder lockFinder;
	@BeanReference(type = com.liferay.portal.service.RepositoryLocalService.class)
	protected com.liferay.portal.service.RepositoryLocalService repositoryLocalService;
	@BeanReference(type = com.liferay.portal.service.RepositoryService.class)
	protected com.liferay.portal.service.RepositoryService repositoryService;
	@BeanReference(type = RepositoryPersistence.class)
	protected RepositoryPersistence repositoryPersistence;
	@BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
	protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = UserFinder.class)
	protected UserFinder userFinder;
	@BeanReference(type = com.liferay.portal.service.WebDAVPropsLocalService.class)
	protected com.liferay.portal.service.WebDAVPropsLocalService webDAVPropsLocalService;
	@BeanReference(type = WebDAVPropsPersistence.class)
	protected WebDAVPropsPersistence webDAVPropsPersistence;
	@BeanReference(type = com.liferay.portal.service.WorkflowInstanceLinkLocalService.class)
	protected com.liferay.portal.service.WorkflowInstanceLinkLocalService workflowInstanceLinkLocalService;
	@BeanReference(type = WorkflowInstanceLinkPersistence.class)
	protected WorkflowInstanceLinkPersistence workflowInstanceLinkPersistence;
	@BeanReference(type = com.liferay.portlet.asset.service.AssetCategoryLocalService.class)
	protected com.liferay.portlet.asset.service.AssetCategoryLocalService assetCategoryLocalService;
	@BeanReference(type = com.liferay.portlet.asset.service.AssetCategoryService.class)
	protected com.liferay.portlet.asset.service.AssetCategoryService assetCategoryService;
	@BeanReference(type = AssetCategoryPersistence.class)
	protected AssetCategoryPersistence assetCategoryPersistence;
	@BeanReference(type = AssetCategoryFinder.class)
	protected AssetCategoryFinder assetCategoryFinder;
	@BeanReference(type = com.liferay.portlet.asset.service.AssetEntryLocalService.class)
	protected com.liferay.portlet.asset.service.AssetEntryLocalService assetEntryLocalService;
	@BeanReference(type = com.liferay.portlet.asset.service.AssetEntryService.class)
	protected com.liferay.portlet.asset.service.AssetEntryService assetEntryService;
	@BeanReference(type = AssetEntryPersistence.class)
	protected AssetEntryPersistence assetEntryPersistence;
	@BeanReference(type = AssetEntryFinder.class)
	protected AssetEntryFinder assetEntryFinder;
	@BeanReference(type = com.liferay.portlet.asset.service.AssetLinkLocalService.class)
	protected com.liferay.portlet.asset.service.AssetLinkLocalService assetLinkLocalService;
	@BeanReference(type = AssetLinkPersistence.class)
	protected AssetLinkPersistence assetLinkPersistence;
	@BeanReference(type = com.liferay.portlet.asset.service.AssetTagLocalService.class)
	protected com.liferay.portlet.asset.service.AssetTagLocalService assetTagLocalService;
	@BeanReference(type = com.liferay.portlet.asset.service.AssetTagService.class)
	protected com.liferay.portlet.asset.service.AssetTagService assetTagService;
	@BeanReference(type = AssetTagPersistence.class)
	protected AssetTagPersistence assetTagPersistence;
	@BeanReference(type = AssetTagFinder.class)
	protected AssetTagFinder assetTagFinder;
	@BeanReference(type = com.liferay.portlet.documentlibrary.service.DLAppHelperLocalService.class)
	protected com.liferay.portlet.documentlibrary.service.DLAppHelperLocalService dlAppHelperLocalService;
	@BeanReference(type = com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalService.class)
	protected com.liferay.portlet.dynamicdatamapping.service.DDMStructureLocalService ddmStructureLocalService;
	@BeanReference(type = com.liferay.portlet.dynamicdatamapping.service.DDMStructureService.class)
	protected com.liferay.portlet.dynamicdatamapping.service.DDMStructureService ddmStructureService;
	@BeanReference(type = DDMStructurePersistence.class)
	protected DDMStructurePersistence ddmStructurePersistence;
	@BeanReference(type = DDMStructureFinder.class)
	protected DDMStructureFinder ddmStructureFinder;
	@BeanReference(type = com.liferay.portlet.expando.service.ExpandoRowLocalService.class)
	protected com.liferay.portlet.expando.service.ExpandoRowLocalService expandoRowLocalService;
	@BeanReference(type = ExpandoRowPersistence.class)
	protected ExpandoRowPersistence expandoRowPersistence;
	@BeanReference(type = com.liferay.portlet.expando.service.ExpandoTableLocalService.class)
	protected com.liferay.portlet.expando.service.ExpandoTableLocalService expandoTableLocalService;
	@BeanReference(type = ExpandoTablePersistence.class)
	protected ExpandoTablePersistence expandoTablePersistence;
	@BeanReference(type = com.liferay.portlet.trash.service.TrashEntryLocalService.class)
	protected com.liferay.portlet.trash.service.TrashEntryLocalService trashEntryLocalService;
	@BeanReference(type = com.liferay.portlet.trash.service.TrashEntryService.class)
	protected com.liferay.portlet.trash.service.TrashEntryService trashEntryService;
	@BeanReference(type = TrashEntryPersistence.class)
	protected TrashEntryPersistence trashEntryPersistence;
	@BeanReference(type = com.liferay.portlet.documentlibrary.service.DLFileEntryMetadataLocalService.class)
	protected com.liferay.portlet.documentlibrary.service.DLFileEntryMetadataLocalService dlFileEntryMetadataLocalService;
	@BeanReference(type = DLFileEntryMetadataPersistence.class)
	protected DLFileEntryMetadataPersistence dlFileEntryMetadataPersistence;
	@BeanReference(type = com.liferay.portlet.documentlibrary.service.DLFileEntryTypeLocalService.class)
	protected com.liferay.portlet.documentlibrary.service.DLFileEntryTypeLocalService dlFileEntryTypeLocalService;
	@BeanReference(type = com.liferay.portlet.documentlibrary.service.DLFileEntryTypeService.class)
	protected com.liferay.portlet.documentlibrary.service.DLFileEntryTypeService dlFileEntryTypeService;
	@BeanReference(type = DLFileEntryTypePersistence.class)
	protected DLFileEntryTypePersistence dlFileEntryTypePersistence;
	@BeanReference(type = DLFileEntryTypeFinder.class)
	protected DLFileEntryTypeFinder dlFileEntryTypeFinder;
	@BeanReference(type = com.liferay.portlet.documentlibrary.service.DLFileVersionLocalService.class)
	protected com.liferay.portlet.documentlibrary.service.DLFileVersionLocalService dlFileVersionLocalService;
	@BeanReference(type = com.liferay.portlet.documentlibrary.service.DLFileVersionService.class)
	protected com.liferay.portlet.documentlibrary.service.DLFileVersionService dlFileVersionService;
	@BeanReference(type = DLFileVersionPersistence.class)
	protected DLFileVersionPersistence dlFileVersionPersistence;
	@BeanReference(type = com.liferay.portlet.documentlibrary.service.DLFolderLocalService.class)
	protected com.liferay.portlet.documentlibrary.service.DLFolderLocalService dlFolderLocalService;
	@BeanReference(type = com.liferay.portlet.documentlibrary.service.DLFolderService.class)
	protected com.liferay.portlet.documentlibrary.service.DLFolderService dlFolderService;
	@BeanReference(type = DLFolderPersistence.class)
	protected DLFolderPersistence dlFolderPersistence;
	@BeanReference(type = DLFolderFinder.class)
	protected DLFolderFinder dlFolderFinder;
	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
	private String _beanIdentifier;
}