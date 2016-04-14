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

package com.liferay.portlet.announcements.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.announcements.kernel.model.AnnouncementsFlag;
import com.liferay.announcements.kernel.service.AnnouncementsFlagLocalService;
import com.liferay.announcements.kernel.service.persistence.AnnouncementsFlagPersistence;

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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the announcements flag local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portlet.announcements.service.impl.AnnouncementsFlagLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.announcements.service.impl.AnnouncementsFlagLocalServiceImpl
 * @see com.liferay.announcements.kernel.service.AnnouncementsFlagLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class AnnouncementsFlagLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements AnnouncementsFlagLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.announcements.kernel.service.AnnouncementsFlagLocalServiceUtil} to access the announcements flag local service.
	 */

	/**
	 * Adds the announcements flag to the database. Also notifies the appropriate model listeners.
	 *
	 * @param announcementsFlag the announcements flag
	 * @return the announcements flag that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AnnouncementsFlag addAnnouncementsFlag(
		AnnouncementsFlag announcementsFlag) {
		announcementsFlag.setNew(true);

		return announcementsFlagPersistence.update(announcementsFlag);
	}

	/**
	 * Creates a new announcements flag with the primary key. Does not add the announcements flag to the database.
	 *
	 * @param flagId the primary key for the new announcements flag
	 * @return the new announcements flag
	 */
	@Override
	public AnnouncementsFlag createAnnouncementsFlag(long flagId) {
		return announcementsFlagPersistence.create(flagId);
	}

	/**
	 * Deletes the announcements flag with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param flagId the primary key of the announcements flag
	 * @return the announcements flag that was removed
	 * @throws PortalException if a announcements flag with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public AnnouncementsFlag deleteAnnouncementsFlag(long flagId)
		throws PortalException {
		return announcementsFlagPersistence.remove(flagId);
	}

	/**
	 * Deletes the announcements flag from the database. Also notifies the appropriate model listeners.
	 *
	 * @param announcementsFlag the announcements flag
	 * @return the announcements flag that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public AnnouncementsFlag deleteAnnouncementsFlag(
		AnnouncementsFlag announcementsFlag) {
		return announcementsFlagPersistence.remove(announcementsFlag);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(AnnouncementsFlag.class,
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
		return announcementsFlagPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.announcements.model.impl.AnnouncementsFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return announcementsFlagPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.announcements.model.impl.AnnouncementsFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return announcementsFlagPersistence.findWithDynamicQuery(dynamicQuery,
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
		return announcementsFlagPersistence.countWithDynamicQuery(dynamicQuery);
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
		return announcementsFlagPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public AnnouncementsFlag fetchAnnouncementsFlag(long flagId) {
		return announcementsFlagPersistence.fetchByPrimaryKey(flagId);
	}

	/**
	 * Returns the announcements flag with the primary key.
	 *
	 * @param flagId the primary key of the announcements flag
	 * @return the announcements flag
	 * @throws PortalException if a announcements flag with the primary key could not be found
	 */
	@Override
	public AnnouncementsFlag getAnnouncementsFlag(long flagId)
		throws PortalException {
		return announcementsFlagPersistence.findByPrimaryKey(flagId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(announcementsFlagLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(AnnouncementsFlag.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("flagId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(announcementsFlagLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(AnnouncementsFlag.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("flagId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(announcementsFlagLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(AnnouncementsFlag.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("flagId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return announcementsFlagLocalService.deleteAnnouncementsFlag((AnnouncementsFlag)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return announcementsFlagPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the announcements flags.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portlet.announcements.model.impl.AnnouncementsFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of announcements flags
	 * @param end the upper bound of the range of announcements flags (not inclusive)
	 * @return the range of announcements flags
	 */
	@Override
	public List<AnnouncementsFlag> getAnnouncementsFlags(int start, int end) {
		return announcementsFlagPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of announcements flags.
	 *
	 * @return the number of announcements flags
	 */
	@Override
	public int getAnnouncementsFlagsCount() {
		return announcementsFlagPersistence.countAll();
	}

	/**
	 * Updates the announcements flag in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param announcementsFlag the announcements flag
	 * @return the announcements flag that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AnnouncementsFlag updateAnnouncementsFlag(
		AnnouncementsFlag announcementsFlag) {
		return announcementsFlagPersistence.update(announcementsFlag);
	}

	/**
	 * Returns the announcements flag local service.
	 *
	 * @return the announcements flag local service
	 */
	public AnnouncementsFlagLocalService getAnnouncementsFlagLocalService() {
		return announcementsFlagLocalService;
	}

	/**
	 * Sets the announcements flag local service.
	 *
	 * @param announcementsFlagLocalService the announcements flag local service
	 */
	public void setAnnouncementsFlagLocalService(
		AnnouncementsFlagLocalService announcementsFlagLocalService) {
		this.announcementsFlagLocalService = announcementsFlagLocalService;
	}

	/**
	 * Returns the announcements flag persistence.
	 *
	 * @return the announcements flag persistence
	 */
	public AnnouncementsFlagPersistence getAnnouncementsFlagPersistence() {
		return announcementsFlagPersistence;
	}

	/**
	 * Sets the announcements flag persistence.
	 *
	 * @param announcementsFlagPersistence the announcements flag persistence
	 */
	public void setAnnouncementsFlagPersistence(
		AnnouncementsFlagPersistence announcementsFlagPersistence) {
		this.announcementsFlagPersistence = announcementsFlagPersistence;
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
		persistedModelLocalServiceRegistry.register("com.liferay.announcements.kernel.model.AnnouncementsFlag",
			announcementsFlagLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.announcements.kernel.model.AnnouncementsFlag");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return AnnouncementsFlagLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return AnnouncementsFlag.class;
	}

	protected String getModelClassName() {
		return AnnouncementsFlag.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = announcementsFlagPersistence.getDataSource();

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

	@BeanReference(type = com.liferay.announcements.kernel.service.AnnouncementsFlagLocalService.class)
	protected AnnouncementsFlagLocalService announcementsFlagLocalService;
	@BeanReference(type = AnnouncementsFlagPersistence.class)
	protected AnnouncementsFlagPersistence announcementsFlagPersistence;
	@BeanReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}