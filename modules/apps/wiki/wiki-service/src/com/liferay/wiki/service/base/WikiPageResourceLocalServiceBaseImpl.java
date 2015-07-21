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

package com.liferay.wiki.service.base;

import aQute.bnd.annotation.ProviderType;

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
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.util.PortalUtil;

import com.liferay.wiki.model.WikiPageResource;
import com.liferay.wiki.service.WikiPageResourceLocalService;
import com.liferay.wiki.service.persistence.WikiPageResourcePersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the wiki page resource local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.wiki.service.impl.WikiPageResourceLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.wiki.service.impl.WikiPageResourceLocalServiceImpl
 * @see com.liferay.wiki.service.WikiPageResourceLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class WikiPageResourceLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements WikiPageResourceLocalService,
		IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.wiki.service.WikiPageResourceLocalServiceUtil} to access the wiki page resource local service.
	 */

	/**
	 * Adds the wiki page resource to the database. Also notifies the appropriate model listeners.
	 *
	 * @param wikiPageResource the wiki page resource
	 * @return the wiki page resource that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public WikiPageResource addWikiPageResource(
		WikiPageResource wikiPageResource) {
		wikiPageResource.setNew(true);

		return wikiPageResourcePersistence.update(wikiPageResource);
	}

	/**
	 * Creates a new wiki page resource with the primary key. Does not add the wiki page resource to the database.
	 *
	 * @param resourcePrimKey the primary key for the new wiki page resource
	 * @return the new wiki page resource
	 */
	@Override
	public WikiPageResource createWikiPageResource(long resourcePrimKey) {
		return wikiPageResourcePersistence.create(resourcePrimKey);
	}

	/**
	 * Deletes the wiki page resource with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param resourcePrimKey the primary key of the wiki page resource
	 * @return the wiki page resource that was removed
	 * @throws PortalException if a wiki page resource with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public WikiPageResource deleteWikiPageResource(long resourcePrimKey)
		throws PortalException {
		return wikiPageResourcePersistence.remove(resourcePrimKey);
	}

	/**
	 * Deletes the wiki page resource from the database. Also notifies the appropriate model listeners.
	 *
	 * @param wikiPageResource the wiki page resource
	 * @return the wiki page resource that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public WikiPageResource deleteWikiPageResource(
		WikiPageResource wikiPageResource) {
		return wikiPageResourcePersistence.remove(wikiPageResource);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(WikiPageResource.class,
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
		return wikiPageResourcePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wiki.model.impl.WikiPageResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return wikiPageResourcePersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wiki.model.impl.WikiPageResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return wikiPageResourcePersistence.findWithDynamicQuery(dynamicQuery,
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
		return wikiPageResourcePersistence.countWithDynamicQuery(dynamicQuery);
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
		return wikiPageResourcePersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public WikiPageResource fetchWikiPageResource(long resourcePrimKey) {
		return wikiPageResourcePersistence.fetchByPrimaryKey(resourcePrimKey);
	}

	/**
	 * Returns the wiki page resource matching the UUID and group.
	 *
	 * @param uuid the wiki page resource's UUID
	 * @param groupId the primary key of the group
	 * @return the matching wiki page resource, or <code>null</code> if a matching wiki page resource could not be found
	 */
	@Override
	public WikiPageResource fetchWikiPageResourceByUuidAndGroupId(String uuid,
		long groupId) {
		return wikiPageResourcePersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the wiki page resource with the primary key.
	 *
	 * @param resourcePrimKey the primary key of the wiki page resource
	 * @return the wiki page resource
	 * @throws PortalException if a wiki page resource with the primary key could not be found
	 */
	@Override
	public WikiPageResource getWikiPageResource(long resourcePrimKey)
		throws PortalException {
		return wikiPageResourcePersistence.findByPrimaryKey(resourcePrimKey);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(com.liferay.wiki.service.WikiPageResourceLocalServiceUtil.getService());
		actionableDynamicQuery.setClass(WikiPageResource.class);
		actionableDynamicQuery.setClassLoader(getClassLoader());

		actionableDynamicQuery.setPrimaryKeyPropertyName("resourcePrimKey");

		return actionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(com.liferay.wiki.service.WikiPageResourceLocalServiceUtil.getService());
		actionableDynamicQuery.setClass(WikiPageResource.class);
		actionableDynamicQuery.setClassLoader(getClassLoader());

		actionableDynamicQuery.setPrimaryKeyPropertyName("resourcePrimKey");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return wikiPageResourceLocalService.deleteWikiPageResource((WikiPageResource)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return wikiPageResourcePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the wiki page resource matching the UUID and group.
	 *
	 * @param uuid the wiki page resource's UUID
	 * @param groupId the primary key of the group
	 * @return the matching wiki page resource
	 * @throws PortalException if a matching wiki page resource could not be found
	 */
	@Override
	public WikiPageResource getWikiPageResourceByUuidAndGroupId(String uuid,
		long groupId) throws PortalException {
		return wikiPageResourcePersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the wiki page resources.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.wiki.model.impl.WikiPageResourceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of wiki page resources
	 * @param end the upper bound of the range of wiki page resources (not inclusive)
	 * @return the range of wiki page resources
	 */
	@Override
	public List<WikiPageResource> getWikiPageResources(int start, int end) {
		return wikiPageResourcePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of wiki page resources.
	 *
	 * @return the number of wiki page resources
	 */
	@Override
	public int getWikiPageResourcesCount() {
		return wikiPageResourcePersistence.countAll();
	}

	/**
	 * Updates the wiki page resource in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param wikiPageResource the wiki page resource
	 * @return the wiki page resource that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public WikiPageResource updateWikiPageResource(
		WikiPageResource wikiPageResource) {
		return wikiPageResourcePersistence.update(wikiPageResource);
	}

	/**
	 * Returns the wiki page resource local service.
	 *
	 * @return the wiki page resource local service
	 */
	public com.liferay.wiki.service.WikiPageResourceLocalService getWikiPageResourceLocalService() {
		return wikiPageResourceLocalService;
	}

	/**
	 * Sets the wiki page resource local service.
	 *
	 * @param wikiPageResourceLocalService the wiki page resource local service
	 */
	public void setWikiPageResourceLocalService(
		com.liferay.wiki.service.WikiPageResourceLocalService wikiPageResourceLocalService) {
		this.wikiPageResourceLocalService = wikiPageResourceLocalService;
	}

	/**
	 * Returns the wiki page resource persistence.
	 *
	 * @return the wiki page resource persistence
	 */
	public WikiPageResourcePersistence getWikiPageResourcePersistence() {
		return wikiPageResourcePersistence;
	}

	/**
	 * Sets the wiki page resource persistence.
	 *
	 * @param wikiPageResourcePersistence the wiki page resource persistence
	 */
	public void setWikiPageResourcePersistence(
		WikiPageResourcePersistence wikiPageResourcePersistence) {
		this.wikiPageResourcePersistence = wikiPageResourcePersistence;
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
		persistedModelLocalServiceRegistry.register("com.liferay.wiki.model.WikiPageResource",
			wikiPageResourceLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.wiki.model.WikiPageResource");
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
		return WikiPageResource.class;
	}

	protected String getModelClassName() {
		return WikiPageResource.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = wikiPageResourcePersistence.getDataSource();

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

	@BeanReference(type = com.liferay.wiki.service.WikiPageResourceLocalService.class)
	protected com.liferay.wiki.service.WikiPageResourceLocalService wikiPageResourceLocalService;
	@BeanReference(type = WikiPageResourcePersistence.class)
	protected WikiPageResourcePersistence wikiPageResourcePersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
	private String _beanIdentifier;
}