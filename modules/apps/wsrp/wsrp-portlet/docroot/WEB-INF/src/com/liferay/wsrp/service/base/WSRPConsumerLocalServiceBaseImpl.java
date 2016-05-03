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

package com.liferay.wsrp.service.base;

import aQute.bnd.annotation.ProviderType;

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
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.CompanyPersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import com.liferay.wsrp.model.WSRPConsumer;
import com.liferay.wsrp.service.WSRPConsumerLocalService;
import com.liferay.wsrp.service.persistence.WSRPConsumerPersistence;
import com.liferay.wsrp.service.persistence.WSRPConsumerPortletPersistence;
import com.liferay.wsrp.service.persistence.WSRPProducerPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the w s r p consumer local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.wsrp.service.impl.WSRPConsumerLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.wsrp.service.impl.WSRPConsumerLocalServiceImpl
 * @see com.liferay.wsrp.service.WSRPConsumerLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class WSRPConsumerLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements WSRPConsumerLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.wsrp.service.WSRPConsumerLocalServiceUtil} to access the w s r p consumer local service.
	 */

	/**
	 * Adds the w s r p consumer to the database. Also notifies the appropriate model listeners.
	 *
	 * @param wsrpConsumer the w s r p consumer
	 * @return the w s r p consumer that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public WSRPConsumer addWSRPConsumer(WSRPConsumer wsrpConsumer) {
		wsrpConsumer.setNew(true);

		return wsrpConsumerPersistence.update(wsrpConsumer);
	}

	/**
	 * Creates a new w s r p consumer with the primary key. Does not add the w s r p consumer to the database.
	 *
	 * @param wsrpConsumerId the primary key for the new w s r p consumer
	 * @return the new w s r p consumer
	 */
	@Override
	public WSRPConsumer createWSRPConsumer(long wsrpConsumerId) {
		return wsrpConsumerPersistence.create(wsrpConsumerId);
	}

	/**
	 * Deletes the w s r p consumer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param wsrpConsumerId the primary key of the w s r p consumer
	 * @return the w s r p consumer that was removed
	 * @throws PortalException if a w s r p consumer with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public WSRPConsumer deleteWSRPConsumer(long wsrpConsumerId)
		throws PortalException {
		return wsrpConsumerPersistence.remove(wsrpConsumerId);
	}

	/**
	 * Deletes the w s r p consumer from the database. Also notifies the appropriate model listeners.
	 *
	 * @param wsrpConsumer the w s r p consumer
	 * @return the w s r p consumer that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public WSRPConsumer deleteWSRPConsumer(WSRPConsumer wsrpConsumer)
		throws PortalException {
		return wsrpConsumerPersistence.remove(wsrpConsumer);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(WSRPConsumer.class,
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
		return wsrpConsumerPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wsrp.model.impl.WSRPConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return wsrpConsumerPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wsrp.model.impl.WSRPConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return wsrpConsumerPersistence.findWithDynamicQuery(dynamicQuery,
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
		return wsrpConsumerPersistence.countWithDynamicQuery(dynamicQuery);
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
		return wsrpConsumerPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public WSRPConsumer fetchWSRPConsumer(long wsrpConsumerId) {
		return wsrpConsumerPersistence.fetchByPrimaryKey(wsrpConsumerId);
	}

	/**
	 * Returns the w s r p consumer with the matching UUID and company.
	 *
	 * @param uuid the w s r p consumer's UUID
	 * @param companyId the primary key of the company
	 * @return the matching w s r p consumer, or <code>null</code> if a matching w s r p consumer could not be found
	 */
	@Override
	public WSRPConsumer fetchWSRPConsumerByUuidAndCompanyId(String uuid,
		long companyId) {
		return wsrpConsumerPersistence.fetchByUuid_C_First(uuid, companyId, null);
	}

	/**
	 * Returns the w s r p consumer with the primary key.
	 *
	 * @param wsrpConsumerId the primary key of the w s r p consumer
	 * @return the w s r p consumer
	 * @throws PortalException if a w s r p consumer with the primary key could not be found
	 */
	@Override
	public WSRPConsumer getWSRPConsumer(long wsrpConsumerId)
		throws PortalException {
		return wsrpConsumerPersistence.findByPrimaryKey(wsrpConsumerId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(wsrpConsumerLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(WSRPConsumer.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("wsrpConsumerId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(wsrpConsumerLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(WSRPConsumer.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"wsrpConsumerId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(wsrpConsumerLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(WSRPConsumer.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("wsrpConsumerId");
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

		exportActionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<WSRPConsumer>() {
				@Override
				public void performAction(WSRPConsumer wsrpConsumer)
					throws PortalException {
					StagedModelDataHandlerUtil.exportStagedModel(portletDataContext,
						wsrpConsumer);
				}
			});
		exportActionableDynamicQuery.setStagedModelType(new StagedModelType(
				PortalUtil.getClassNameId(WSRPConsumer.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return wsrpConsumerLocalService.deleteWSRPConsumer((WSRPConsumer)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return wsrpConsumerPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the w s r p consumer with the matching UUID and company.
	 *
	 * @param uuid the w s r p consumer's UUID
	 * @param companyId the primary key of the company
	 * @return the matching w s r p consumer
	 * @throws PortalException if a matching w s r p consumer could not be found
	 */
	@Override
	public WSRPConsumer getWSRPConsumerByUuidAndCompanyId(String uuid,
		long companyId) throws PortalException {
		return wsrpConsumerPersistence.findByUuid_C_First(uuid, companyId, null);
	}

	/**
	 * Returns a range of all the w s r p consumers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wsrp.model.impl.WSRPConsumerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of w s r p consumers
	 * @param end the upper bound of the range of w s r p consumers (not inclusive)
	 * @return the range of w s r p consumers
	 */
	@Override
	public List<WSRPConsumer> getWSRPConsumers(int start, int end) {
		return wsrpConsumerPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of w s r p consumers.
	 *
	 * @return the number of w s r p consumers
	 */
	@Override
	public int getWSRPConsumersCount() {
		return wsrpConsumerPersistence.countAll();
	}

	/**
	 * Updates the w s r p consumer in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param wsrpConsumer the w s r p consumer
	 * @return the w s r p consumer that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public WSRPConsumer updateWSRPConsumer(WSRPConsumer wsrpConsumer) {
		return wsrpConsumerPersistence.update(wsrpConsumer);
	}

	/**
	 * Returns the w s r p consumer local service.
	 *
	 * @return the w s r p consumer local service
	 */
	public WSRPConsumerLocalService getWSRPConsumerLocalService() {
		return wsrpConsumerLocalService;
	}

	/**
	 * Sets the w s r p consumer local service.
	 *
	 * @param wsrpConsumerLocalService the w s r p consumer local service
	 */
	public void setWSRPConsumerLocalService(
		WSRPConsumerLocalService wsrpConsumerLocalService) {
		this.wsrpConsumerLocalService = wsrpConsumerLocalService;
	}

	/**
	 * Returns the w s r p consumer persistence.
	 *
	 * @return the w s r p consumer persistence
	 */
	public WSRPConsumerPersistence getWSRPConsumerPersistence() {
		return wsrpConsumerPersistence;
	}

	/**
	 * Sets the w s r p consumer persistence.
	 *
	 * @param wsrpConsumerPersistence the w s r p consumer persistence
	 */
	public void setWSRPConsumerPersistence(
		WSRPConsumerPersistence wsrpConsumerPersistence) {
		this.wsrpConsumerPersistence = wsrpConsumerPersistence;
	}

	/**
	 * Returns the w s r p consumer portlet local service.
	 *
	 * @return the w s r p consumer portlet local service
	 */
	public com.liferay.wsrp.service.WSRPConsumerPortletLocalService getWSRPConsumerPortletLocalService() {
		return wsrpConsumerPortletLocalService;
	}

	/**
	 * Sets the w s r p consumer portlet local service.
	 *
	 * @param wsrpConsumerPortletLocalService the w s r p consumer portlet local service
	 */
	public void setWSRPConsumerPortletLocalService(
		com.liferay.wsrp.service.WSRPConsumerPortletLocalService wsrpConsumerPortletLocalService) {
		this.wsrpConsumerPortletLocalService = wsrpConsumerPortletLocalService;
	}

	/**
	 * Returns the w s r p consumer portlet persistence.
	 *
	 * @return the w s r p consumer portlet persistence
	 */
	public WSRPConsumerPortletPersistence getWSRPConsumerPortletPersistence() {
		return wsrpConsumerPortletPersistence;
	}

	/**
	 * Sets the w s r p consumer portlet persistence.
	 *
	 * @param wsrpConsumerPortletPersistence the w s r p consumer portlet persistence
	 */
	public void setWSRPConsumerPortletPersistence(
		WSRPConsumerPortletPersistence wsrpConsumerPortletPersistence) {
		this.wsrpConsumerPortletPersistence = wsrpConsumerPortletPersistence;
	}

	/**
	 * Returns the w s r p producer local service.
	 *
	 * @return the w s r p producer local service
	 */
	public com.liferay.wsrp.service.WSRPProducerLocalService getWSRPProducerLocalService() {
		return wsrpProducerLocalService;
	}

	/**
	 * Sets the w s r p producer local service.
	 *
	 * @param wsrpProducerLocalService the w s r p producer local service
	 */
	public void setWSRPProducerLocalService(
		com.liferay.wsrp.service.WSRPProducerLocalService wsrpProducerLocalService) {
		this.wsrpProducerLocalService = wsrpProducerLocalService;
	}

	/**
	 * Returns the w s r p producer persistence.
	 *
	 * @return the w s r p producer persistence
	 */
	public WSRPProducerPersistence getWSRPProducerPersistence() {
		return wsrpProducerPersistence;
	}

	/**
	 * Sets the w s r p producer persistence.
	 *
	 * @param wsrpProducerPersistence the w s r p producer persistence
	 */
	public void setWSRPProducerPersistence(
		WSRPProducerPersistence wsrpProducerPersistence) {
		this.wsrpProducerPersistence = wsrpProducerPersistence;
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
	 * Returns the company local service.
	 *
	 * @return the company local service
	 */
	public com.liferay.portal.kernel.service.CompanyLocalService getCompanyLocalService() {
		return companyLocalService;
	}

	/**
	 * Sets the company local service.
	 *
	 * @param companyLocalService the company local service
	 */
	public void setCompanyLocalService(
		com.liferay.portal.kernel.service.CompanyLocalService companyLocalService) {
		this.companyLocalService = companyLocalService;
	}

	/**
	 * Returns the company persistence.
	 *
	 * @return the company persistence
	 */
	public CompanyPersistence getCompanyPersistence() {
		return companyPersistence;
	}

	/**
	 * Sets the company persistence.
	 *
	 * @param companyPersistence the company persistence
	 */
	public void setCompanyPersistence(CompanyPersistence companyPersistence) {
		this.companyPersistence = companyPersistence;
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
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();

		PersistedModelLocalServiceRegistryUtil.register("com.liferay.wsrp.model.WSRPConsumer",
			wsrpConsumerLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.liferay.wsrp.model.WSRPConsumer");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return WSRPConsumerLocalService.class.getName();
	}

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected Class<?> getModelClass() {
		return WSRPConsumer.class;
	}

	protected String getModelClassName() {
		return WSRPConsumer.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = wsrpConsumerPersistence.getDataSource();

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

	@BeanReference(type = WSRPConsumerLocalService.class)
	protected WSRPConsumerLocalService wsrpConsumerLocalService;
	@BeanReference(type = WSRPConsumerPersistence.class)
	protected WSRPConsumerPersistence wsrpConsumerPersistence;
	@BeanReference(type = com.liferay.wsrp.service.WSRPConsumerPortletLocalService.class)
	protected com.liferay.wsrp.service.WSRPConsumerPortletLocalService wsrpConsumerPortletLocalService;
	@BeanReference(type = WSRPConsumerPortletPersistence.class)
	protected WSRPConsumerPortletPersistence wsrpConsumerPortletPersistence;
	@BeanReference(type = com.liferay.wsrp.service.WSRPProducerLocalService.class)
	protected com.liferay.wsrp.service.WSRPProducerLocalService wsrpProducerLocalService;
	@BeanReference(type = WSRPProducerPersistence.class)
	protected WSRPProducerPersistence wsrpProducerPersistence;
	@BeanReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.kernel.service.ClassNameLocalService.class)
	protected com.liferay.portal.kernel.service.ClassNameLocalService classNameLocalService;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = com.liferay.portal.kernel.service.CompanyLocalService.class)
	protected com.liferay.portal.kernel.service.CompanyLocalService companyLocalService;
	@BeanReference(type = CompanyPersistence.class)
	protected CompanyPersistence companyPersistence;
	@BeanReference(type = com.liferay.portal.kernel.service.ResourceLocalService.class)
	protected com.liferay.portal.kernel.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.kernel.service.UserLocalService.class)
	protected com.liferay.portal.kernel.service.UserLocalService userLocalService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private ClassLoader _classLoader;
	private WSRPConsumerLocalServiceClpInvoker _clpInvoker = new WSRPConsumerLocalServiceClpInvoker();
}