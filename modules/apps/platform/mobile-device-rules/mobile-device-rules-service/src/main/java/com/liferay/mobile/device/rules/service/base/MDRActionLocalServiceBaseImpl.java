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

package com.liferay.mobile.device.rules.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.mobile.device.rules.model.MDRAction;
import com.liferay.mobile.device.rules.service.MDRActionLocalService;
import com.liferay.mobile.device.rules.service.persistence.MDRActionPersistence;
import com.liferay.mobile.device.rules.service.persistence.MDRRuleGroupInstancePersistence;

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
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.SystemEventPersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the m d r action local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.mobile.device.rules.service.impl.MDRActionLocalServiceImpl}.
 * </p>
 *
 * @author Edward C. Han
 * @see com.liferay.mobile.device.rules.service.impl.MDRActionLocalServiceImpl
 * @see com.liferay.mobile.device.rules.service.MDRActionLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class MDRActionLocalServiceBaseImpl extends BaseLocalServiceImpl
	implements MDRActionLocalService, IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.mobile.device.rules.service.MDRActionLocalServiceUtil} to access the m d r action local service.
	 */

	/**
	 * Adds the m d r action to the database. Also notifies the appropriate model listeners.
	 *
	 * @param mdrAction the m d r action
	 * @return the m d r action that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public MDRAction addMDRAction(MDRAction mdrAction) {
		mdrAction.setNew(true);

		return mdrActionPersistence.update(mdrAction);
	}

	/**
	 * Creates a new m d r action with the primary key. Does not add the m d r action to the database.
	 *
	 * @param actionId the primary key for the new m d r action
	 * @return the new m d r action
	 */
	@Override
	public MDRAction createMDRAction(long actionId) {
		return mdrActionPersistence.create(actionId);
	}

	/**
	 * Deletes the m d r action with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param actionId the primary key of the m d r action
	 * @return the m d r action that was removed
	 * @throws PortalException if a m d r action with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public MDRAction deleteMDRAction(long actionId) throws PortalException {
		return mdrActionPersistence.remove(actionId);
	}

	/**
	 * Deletes the m d r action from the database. Also notifies the appropriate model listeners.
	 *
	 * @param mdrAction the m d r action
	 * @return the m d r action that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public MDRAction deleteMDRAction(MDRAction mdrAction) {
		return mdrActionPersistence.remove(mdrAction);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(MDRAction.class,
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
		return mdrActionPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.mobile.device.rules.model.impl.MDRActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return mdrActionPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.mobile.device.rules.model.impl.MDRActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return mdrActionPersistence.findWithDynamicQuery(dynamicQuery, start,
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
		return mdrActionPersistence.countWithDynamicQuery(dynamicQuery);
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
		return mdrActionPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public MDRAction fetchMDRAction(long actionId) {
		return mdrActionPersistence.fetchByPrimaryKey(actionId);
	}

	/**
	 * Returns the m d r action matching the UUID and group.
	 *
	 * @param uuid the m d r action's UUID
	 * @param groupId the primary key of the group
	 * @return the matching m d r action, or <code>null</code> if a matching m d r action could not be found
	 */
	@Override
	public MDRAction fetchMDRActionByUuidAndGroupId(String uuid, long groupId) {
		return mdrActionPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the m d r action with the primary key.
	 *
	 * @param actionId the primary key of the m d r action
	 * @return the m d r action
	 * @throws PortalException if a m d r action with the primary key could not be found
	 */
	@Override
	public MDRAction getMDRAction(long actionId) throws PortalException {
		return mdrActionPersistence.findByPrimaryKey(actionId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(com.liferay.mobile.device.rules.service.MDRActionLocalServiceUtil.getService());
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(MDRAction.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("actionId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(com.liferay.mobile.device.rules.service.MDRActionLocalServiceUtil.getService());
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(MDRAction.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("actionId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(com.liferay.mobile.device.rules.service.MDRActionLocalServiceUtil.getService());
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(MDRAction.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("actionId");
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

					StagedModelType stagedModelType = exportActionableDynamicQuery.getStagedModelType();

					long referrerClassNameId = stagedModelType.getReferrerClassNameId();

					Property classNameIdProperty = PropertyFactoryUtil.forName(
							"classNameId");

					if ((referrerClassNameId != StagedModelType.REFERRER_CLASS_NAME_ID_ALL) &&
							(referrerClassNameId != StagedModelType.REFERRER_CLASS_NAME_ID_ANY)) {
						dynamicQuery.add(classNameIdProperty.eq(
								stagedModelType.getReferrerClassNameId()));
					}
					else if (referrerClassNameId == StagedModelType.REFERRER_CLASS_NAME_ID_ANY) {
						dynamicQuery.add(classNameIdProperty.isNotNull());
					}
				}
			});

		exportActionableDynamicQuery.setCompanyId(portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setGroupId(portletDataContext.getScopeGroupId());

		exportActionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<MDRAction>() {
				@Override
				public void performAction(MDRAction mdrAction)
					throws PortalException {
					StagedModelDataHandlerUtil.exportStagedModel(portletDataContext,
						mdrAction);
				}
			});
		exportActionableDynamicQuery.setStagedModelType(new StagedModelType(
				PortalUtil.getClassNameId(MDRAction.class.getName()),
				StagedModelType.REFERRER_CLASS_NAME_ID_ALL));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return mdrActionLocalService.deleteMDRAction((MDRAction)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return mdrActionPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the m d r actions matching the UUID and company.
	 *
	 * @param uuid the UUID of the m d r actions
	 * @param companyId the primary key of the company
	 * @return the matching m d r actions, or an empty list if no matches were found
	 */
	@Override
	public List<MDRAction> getMDRActionsByUuidAndCompanyId(String uuid,
		long companyId) {
		return mdrActionPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of m d r actions matching the UUID and company.
	 *
	 * @param uuid the UUID of the m d r actions
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of m d r actions
	 * @param end the upper bound of the range of m d r actions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching m d r actions, or an empty list if no matches were found
	 */
	@Override
	public List<MDRAction> getMDRActionsByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<MDRAction> orderByComparator) {
		return mdrActionPersistence.findByUuid_C(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	 * Returns the m d r action matching the UUID and group.
	 *
	 * @param uuid the m d r action's UUID
	 * @param groupId the primary key of the group
	 * @return the matching m d r action
	 * @throws PortalException if a matching m d r action could not be found
	 */
	@Override
	public MDRAction getMDRActionByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {
		return mdrActionPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the m d r actions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.mobile.device.rules.model.impl.MDRActionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of m d r actions
	 * @param end the upper bound of the range of m d r actions (not inclusive)
	 * @return the range of m d r actions
	 */
	@Override
	public List<MDRAction> getMDRActions(int start, int end) {
		return mdrActionPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of m d r actions.
	 *
	 * @return the number of m d r actions
	 */
	@Override
	public int getMDRActionsCount() {
		return mdrActionPersistence.countAll();
	}

	/**
	 * Updates the m d r action in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param mdrAction the m d r action
	 * @return the m d r action that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public MDRAction updateMDRAction(MDRAction mdrAction) {
		return mdrActionPersistence.update(mdrAction);
	}

	/**
	 * Returns the m d r action local service.
	 *
	 * @return the m d r action local service
	 */
	public MDRActionLocalService getMDRActionLocalService() {
		return mdrActionLocalService;
	}

	/**
	 * Sets the m d r action local service.
	 *
	 * @param mdrActionLocalService the m d r action local service
	 */
	public void setMDRActionLocalService(
		MDRActionLocalService mdrActionLocalService) {
		this.mdrActionLocalService = mdrActionLocalService;
	}

	/**
	 * Returns the m d r action persistence.
	 *
	 * @return the m d r action persistence
	 */
	public MDRActionPersistence getMDRActionPersistence() {
		return mdrActionPersistence;
	}

	/**
	 * Sets the m d r action persistence.
	 *
	 * @param mdrActionPersistence the m d r action persistence
	 */
	public void setMDRActionPersistence(
		MDRActionPersistence mdrActionPersistence) {
		this.mdrActionPersistence = mdrActionPersistence;
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
	 * Returns the system event local service.
	 *
	 * @return the system event local service
	 */
	public com.liferay.portal.kernel.service.SystemEventLocalService getSystemEventLocalService() {
		return systemEventLocalService;
	}

	/**
	 * Sets the system event local service.
	 *
	 * @param systemEventLocalService the system event local service
	 */
	public void setSystemEventLocalService(
		com.liferay.portal.kernel.service.SystemEventLocalService systemEventLocalService) {
		this.systemEventLocalService = systemEventLocalService;
	}

	/**
	 * Returns the system event persistence.
	 *
	 * @return the system event persistence
	 */
	public SystemEventPersistence getSystemEventPersistence() {
		return systemEventPersistence;
	}

	/**
	 * Sets the system event persistence.
	 *
	 * @param systemEventPersistence the system event persistence
	 */
	public void setSystemEventPersistence(
		SystemEventPersistence systemEventPersistence) {
		this.systemEventPersistence = systemEventPersistence;
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

	/**
	 * Returns the m d r rule group instance local service.
	 *
	 * @return the m d r rule group instance local service
	 */
	public com.liferay.mobile.device.rules.service.MDRRuleGroupInstanceLocalService getMDRRuleGroupInstanceLocalService() {
		return mdrRuleGroupInstanceLocalService;
	}

	/**
	 * Sets the m d r rule group instance local service.
	 *
	 * @param mdrRuleGroupInstanceLocalService the m d r rule group instance local service
	 */
	public void setMDRRuleGroupInstanceLocalService(
		com.liferay.mobile.device.rules.service.MDRRuleGroupInstanceLocalService mdrRuleGroupInstanceLocalService) {
		this.mdrRuleGroupInstanceLocalService = mdrRuleGroupInstanceLocalService;
	}

	/**
	 * Returns the m d r rule group instance persistence.
	 *
	 * @return the m d r rule group instance persistence
	 */
	public MDRRuleGroupInstancePersistence getMDRRuleGroupInstancePersistence() {
		return mdrRuleGroupInstancePersistence;
	}

	/**
	 * Sets the m d r rule group instance persistence.
	 *
	 * @param mdrRuleGroupInstancePersistence the m d r rule group instance persistence
	 */
	public void setMDRRuleGroupInstancePersistence(
		MDRRuleGroupInstancePersistence mdrRuleGroupInstancePersistence) {
		this.mdrRuleGroupInstancePersistence = mdrRuleGroupInstancePersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.mobile.device.rules.model.MDRAction",
			mdrActionLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.mobile.device.rules.model.MDRAction");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return MDRActionLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return MDRAction.class;
	}

	protected String getModelClassName() {
		return MDRAction.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = mdrActionPersistence.getDataSource();

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

	@BeanReference(type = com.liferay.mobile.device.rules.service.MDRActionLocalService.class)
	protected MDRActionLocalService mdrActionLocalService;
	@BeanReference(type = MDRActionPersistence.class)
	protected MDRActionPersistence mdrActionPersistence;
	@ServiceReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@ServiceReference(type = com.liferay.portal.kernel.service.SystemEventLocalService.class)
	protected com.liferay.portal.kernel.service.SystemEventLocalService systemEventLocalService;
	@ServiceReference(type = SystemEventPersistence.class)
	protected SystemEventPersistence systemEventPersistence;
	@ServiceReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	@BeanReference(type = com.liferay.mobile.device.rules.service.MDRRuleGroupInstanceLocalService.class)
	protected com.liferay.mobile.device.rules.service.MDRRuleGroupInstanceLocalService mdrRuleGroupInstanceLocalService;
	@BeanReference(type = MDRRuleGroupInstancePersistence.class)
	protected MDRRuleGroupInstancePersistence mdrRuleGroupInstancePersistence;
	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}