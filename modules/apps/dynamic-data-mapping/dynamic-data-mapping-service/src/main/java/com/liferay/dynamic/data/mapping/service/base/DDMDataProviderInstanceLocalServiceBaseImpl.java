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

import com.liferay.dynamic.data.mapping.model.DDMDataProviderInstance;
import com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceLocalService;
import com.liferay.dynamic.data.mapping.service.persistence.DDMDataProviderInstanceFinder;
import com.liferay.dynamic.data.mapping.service.persistence.DDMDataProviderInstancePersistence;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.GroupPersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the d d m data provider instance local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.dynamic.data.mapping.service.impl.DDMDataProviderInstanceLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.dynamic.data.mapping.service.impl.DDMDataProviderInstanceLocalServiceImpl
 * @see com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class DDMDataProviderInstanceLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements DDMDataProviderInstanceLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceLocalServiceUtil} to access the d d m data provider instance local service.
	 */

	/**
	 * Adds the d d m data provider instance to the database. Also notifies the appropriate model listeners.
	 *
	 * @param ddmDataProviderInstance the d d m data provider instance
	 * @return the d d m data provider instance that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DDMDataProviderInstance addDDMDataProviderInstance(
		DDMDataProviderInstance ddmDataProviderInstance) {
		ddmDataProviderInstance.setNew(true);

		return ddmDataProviderInstancePersistence.update(ddmDataProviderInstance);
	}

	/**
	 * Creates a new d d m data provider instance with the primary key. Does not add the d d m data provider instance to the database.
	 *
	 * @param dataProviderInstanceId the primary key for the new d d m data provider instance
	 * @return the new d d m data provider instance
	 */
	@Override
	public DDMDataProviderInstance createDDMDataProviderInstance(
		long dataProviderInstanceId) {
		return ddmDataProviderInstancePersistence.create(dataProviderInstanceId);
	}

	/**
	 * Deletes the d d m data provider instance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param dataProviderInstanceId the primary key of the d d m data provider instance
	 * @return the d d m data provider instance that was removed
	 * @throws PortalException if a d d m data provider instance with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DDMDataProviderInstance deleteDDMDataProviderInstance(
		long dataProviderInstanceId) throws PortalException {
		return ddmDataProviderInstancePersistence.remove(dataProviderInstanceId);
	}

	/**
	 * Deletes the d d m data provider instance from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ddmDataProviderInstance the d d m data provider instance
	 * @return the d d m data provider instance that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public DDMDataProviderInstance deleteDDMDataProviderInstance(
		DDMDataProviderInstance ddmDataProviderInstance) {
		return ddmDataProviderInstancePersistence.remove(ddmDataProviderInstance);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(DDMDataProviderInstance.class,
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
		return ddmDataProviderInstancePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.mapping.model.impl.DDMDataProviderInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return ddmDataProviderInstancePersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.mapping.model.impl.DDMDataProviderInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return ddmDataProviderInstancePersistence.findWithDynamicQuery(dynamicQuery,
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
		return ddmDataProviderInstancePersistence.countWithDynamicQuery(dynamicQuery);
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
		return ddmDataProviderInstancePersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public DDMDataProviderInstance fetchDDMDataProviderInstance(
		long dataProviderInstanceId) {
		return ddmDataProviderInstancePersistence.fetchByPrimaryKey(dataProviderInstanceId);
	}

	/**
	 * Returns the d d m data provider instance matching the UUID and group.
	 *
	 * @param uuid the d d m data provider instance's UUID
	 * @param groupId the primary key of the group
	 * @return the matching d d m data provider instance, or <code>null</code> if a matching d d m data provider instance could not be found
	 */
	@Override
	public DDMDataProviderInstance fetchDDMDataProviderInstanceByUuidAndGroupId(
		String uuid, long groupId) {
		return ddmDataProviderInstancePersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the d d m data provider instance with the primary key.
	 *
	 * @param dataProviderInstanceId the primary key of the d d m data provider instance
	 * @return the d d m data provider instance
	 * @throws PortalException if a d d m data provider instance with the primary key could not be found
	 */
	@Override
	public DDMDataProviderInstance getDDMDataProviderInstance(
		long dataProviderInstanceId) throws PortalException {
		return ddmDataProviderInstancePersistence.findByPrimaryKey(dataProviderInstanceId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceLocalServiceUtil.getService());
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(DDMDataProviderInstance.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"dataProviderInstanceId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceLocalServiceUtil.getService());
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(DDMDataProviderInstance.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"dataProviderInstanceId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceLocalServiceUtil.getService());
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(DDMDataProviderInstance.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"dataProviderInstanceId");
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

					manifestSummary.addModelAdditionCount(stagedModelType,
						modelAdditionCount);

					long modelDeletionCount = ExportImportHelperUtil.getModelDeletionCount(portletDataContext,
							stagedModelType);

					manifestSummary.addModelDeletionCount(stagedModelType,
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
				}
			});

		exportActionableDynamicQuery.setCompanyId(portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<DDMDataProviderInstance>() {
				@Override
				public void performAction(
					DDMDataProviderInstance ddmDataProviderInstance)
					throws PortalException {
					StagedModelDataHandlerUtil.exportStagedModel(portletDataContext,
						ddmDataProviderInstance);
				}
			});
		exportActionableDynamicQuery.setStagedModelType(new StagedModelType(
				PortalUtil.getClassNameId(
					DDMDataProviderInstance.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return ddmDataProviderInstanceLocalService.deleteDDMDataProviderInstance((DDMDataProviderInstance)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return ddmDataProviderInstancePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the d d m data provider instances matching the UUID and company.
	 *
	 * @param uuid the UUID of the d d m data provider instances
	 * @param companyId the primary key of the company
	 * @return the matching d d m data provider instances, or an empty list if no matches were found
	 */
	@Override
	public List<DDMDataProviderInstance> getDDMDataProviderInstancesByUuidAndCompanyId(
		String uuid, long companyId) {
		return ddmDataProviderInstancePersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of d d m data provider instances matching the UUID and company.
	 *
	 * @param uuid the UUID of the d d m data provider instances
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of d d m data provider instances
	 * @param end the upper bound of the range of d d m data provider instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching d d m data provider instances, or an empty list if no matches were found
	 */
	@Override
	public List<DDMDataProviderInstance> getDDMDataProviderInstancesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<DDMDataProviderInstance> orderByComparator) {
		return ddmDataProviderInstancePersistence.findByUuid_C(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	 * Returns the d d m data provider instance matching the UUID and group.
	 *
	 * @param uuid the d d m data provider instance's UUID
	 * @param groupId the primary key of the group
	 * @return the matching d d m data provider instance
	 * @throws PortalException if a matching d d m data provider instance could not be found
	 */
	@Override
	public DDMDataProviderInstance getDDMDataProviderInstanceByUuidAndGroupId(
		String uuid, long groupId) throws PortalException {
		return ddmDataProviderInstancePersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the d d m data provider instances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.dynamic.data.mapping.model.impl.DDMDataProviderInstanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of d d m data provider instances
	 * @param end the upper bound of the range of d d m data provider instances (not inclusive)
	 * @return the range of d d m data provider instances
	 */
	@Override
	public List<DDMDataProviderInstance> getDDMDataProviderInstances(
		int start, int end) {
		return ddmDataProviderInstancePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of d d m data provider instances.
	 *
	 * @return the number of d d m data provider instances
	 */
	@Override
	public int getDDMDataProviderInstancesCount() {
		return ddmDataProviderInstancePersistence.countAll();
	}

	/**
	 * Updates the d d m data provider instance in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param ddmDataProviderInstance the d d m data provider instance
	 * @return the d d m data provider instance that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public DDMDataProviderInstance updateDDMDataProviderInstance(
		DDMDataProviderInstance ddmDataProviderInstance) {
		return ddmDataProviderInstancePersistence.update(ddmDataProviderInstance);
	}

	/**
	 * Returns the d d m data provider instance local service.
	 *
	 * @return the d d m data provider instance local service
	 */
	public DDMDataProviderInstanceLocalService getDDMDataProviderInstanceLocalService() {
		return ddmDataProviderInstanceLocalService;
	}

	/**
	 * Sets the d d m data provider instance local service.
	 *
	 * @param ddmDataProviderInstanceLocalService the d d m data provider instance local service
	 */
	public void setDDMDataProviderInstanceLocalService(
		DDMDataProviderInstanceLocalService ddmDataProviderInstanceLocalService) {
		this.ddmDataProviderInstanceLocalService = ddmDataProviderInstanceLocalService;
	}

	/**
	 * Returns the d d m data provider instance persistence.
	 *
	 * @return the d d m data provider instance persistence
	 */
	public DDMDataProviderInstancePersistence getDDMDataProviderInstancePersistence() {
		return ddmDataProviderInstancePersistence;
	}

	/**
	 * Sets the d d m data provider instance persistence.
	 *
	 * @param ddmDataProviderInstancePersistence the d d m data provider instance persistence
	 */
	public void setDDMDataProviderInstancePersistence(
		DDMDataProviderInstancePersistence ddmDataProviderInstancePersistence) {
		this.ddmDataProviderInstancePersistence = ddmDataProviderInstancePersistence;
	}

	/**
	 * Returns the d d m data provider instance finder.
	 *
	 * @return the d d m data provider instance finder
	 */
	public DDMDataProviderInstanceFinder getDDMDataProviderInstanceFinder() {
		return ddmDataProviderInstanceFinder;
	}

	/**
	 * Sets the d d m data provider instance finder.
	 *
	 * @param ddmDataProviderInstanceFinder the d d m data provider instance finder
	 */
	public void setDDMDataProviderInstanceFinder(
		DDMDataProviderInstanceFinder ddmDataProviderInstanceFinder) {
		this.ddmDataProviderInstanceFinder = ddmDataProviderInstanceFinder;
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
	 * Returns the group local service.
	 *
	 * @return the group local service
	 */
	public com.liferay.portal.kernel.service.GroupLocalService getGroupLocalService() {
		return groupLocalService;
	}

	/**
	 * Sets the group local service.
	 *
	 * @param groupLocalService the group local service
	 */
	public void setGroupLocalService(
		com.liferay.portal.kernel.service.GroupLocalService groupLocalService) {
		this.groupLocalService = groupLocalService;
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
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
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

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.dynamic.data.mapping.model.DDMDataProviderInstance",
			ddmDataProviderInstanceLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.dynamic.data.mapping.model.DDMDataProviderInstance");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return DDMDataProviderInstanceLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return DDMDataProviderInstance.class;
	}

	protected String getModelClassName() {
		return DDMDataProviderInstance.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = ddmDataProviderInstancePersistence.getDataSource();

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

	@BeanReference(type = com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceLocalService.class)
	protected DDMDataProviderInstanceLocalService ddmDataProviderInstanceLocalService;
	@BeanReference(type = DDMDataProviderInstancePersistence.class)
	protected DDMDataProviderInstancePersistence ddmDataProviderInstancePersistence;
	@BeanReference(type = DDMDataProviderInstanceFinder.class)
	protected DDMDataProviderInstanceFinder ddmDataProviderInstanceFinder;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.GroupLocalService.class)
	protected com.liferay.portal.kernel.service.GroupLocalService groupLocalService;
	@ServiceReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}