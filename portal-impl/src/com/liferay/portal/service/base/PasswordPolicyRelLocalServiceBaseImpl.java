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
import com.liferay.portal.model.PasswordPolicyRel;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PasswordPolicyRelLocalService;
import com.liferay.portal.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.service.persistence.ClassNamePersistence;
import com.liferay.portal.service.persistence.PasswordPolicyRelPersistence;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the password policy rel local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.PasswordPolicyRelLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.PasswordPolicyRelLocalServiceImpl
 * @see com.liferay.portal.service.PasswordPolicyRelLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class PasswordPolicyRelLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements PasswordPolicyRelLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.portal.service.PasswordPolicyRelLocalServiceUtil} to access the password policy rel local service.
	 */

	/**
	 * Adds the password policy rel to the database. Also notifies the appropriate model listeners.
	 *
	 * @param passwordPolicyRel the password policy rel
	 * @return the password policy rel that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public PasswordPolicyRel addPasswordPolicyRel(
		PasswordPolicyRel passwordPolicyRel) {
		passwordPolicyRel.setNew(true);

		return passwordPolicyRelPersistence.update(passwordPolicyRel);
	}

	/**
	 * Creates a new password policy rel with the primary key. Does not add the password policy rel to the database.
	 *
	 * @param passwordPolicyRelId the primary key for the new password policy rel
	 * @return the new password policy rel
	 */
	@Override
	public PasswordPolicyRel createPasswordPolicyRel(long passwordPolicyRelId) {
		return passwordPolicyRelPersistence.create(passwordPolicyRelId);
	}

	/**
	 * Deletes the password policy rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param passwordPolicyRelId the primary key of the password policy rel
	 * @return the password policy rel that was removed
	 * @throws PortalException if a password policy rel with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public PasswordPolicyRel deletePasswordPolicyRel(long passwordPolicyRelId)
		throws PortalException {
		return passwordPolicyRelPersistence.remove(passwordPolicyRelId);
	}

	/**
	 * Deletes the password policy rel from the database. Also notifies the appropriate model listeners.
	 *
	 * @param passwordPolicyRel the password policy rel
	 * @return the password policy rel that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public PasswordPolicyRel deletePasswordPolicyRel(
		PasswordPolicyRel passwordPolicyRel) {
		return passwordPolicyRelPersistence.remove(passwordPolicyRel);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(PasswordPolicyRel.class,
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
		return passwordPolicyRelPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.PasswordPolicyRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return passwordPolicyRelPersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.PasswordPolicyRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return passwordPolicyRelPersistence.findWithDynamicQuery(dynamicQuery,
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
		return passwordPolicyRelPersistence.countWithDynamicQuery(dynamicQuery);
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
		return passwordPolicyRelPersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public PasswordPolicyRel fetchPasswordPolicyRel(long passwordPolicyRelId) {
		return passwordPolicyRelPersistence.fetchByPrimaryKey(passwordPolicyRelId);
	}

	/**
	 * Returns the password policy rel with the primary key.
	 *
	 * @param passwordPolicyRelId the primary key of the password policy rel
	 * @return the password policy rel
	 * @throws PortalException if a password policy rel with the primary key could not be found
	 */
	@Override
	public PasswordPolicyRel getPasswordPolicyRel(long passwordPolicyRelId)
		throws PortalException {
		return passwordPolicyRelPersistence.findByPrimaryKey(passwordPolicyRelId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(com.liferay.portal.service.PasswordPolicyRelLocalServiceUtil.getService());
		actionableDynamicQuery.setClass(PasswordPolicyRel.class);
		actionableDynamicQuery.setClassLoader(getClassLoader());

		actionableDynamicQuery.setPrimaryKeyPropertyName("passwordPolicyRelId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(com.liferay.portal.service.PasswordPolicyRelLocalServiceUtil.getService());
		indexableActionableDynamicQuery.setClass(PasswordPolicyRel.class);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"passwordPolicyRelId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(com.liferay.portal.service.PasswordPolicyRelLocalServiceUtil.getService());
		actionableDynamicQuery.setClass(PasswordPolicyRel.class);
		actionableDynamicQuery.setClassLoader(getClassLoader());

		actionableDynamicQuery.setPrimaryKeyPropertyName("passwordPolicyRelId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return passwordPolicyRelLocalService.deletePasswordPolicyRel((PasswordPolicyRel)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return passwordPolicyRelPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the password policy rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.PasswordPolicyRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of password policy rels
	 * @param end the upper bound of the range of password policy rels (not inclusive)
	 * @return the range of password policy rels
	 */
	@Override
	public List<PasswordPolicyRel> getPasswordPolicyRels(int start, int end) {
		return passwordPolicyRelPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of password policy rels.
	 *
	 * @return the number of password policy rels
	 */
	@Override
	public int getPasswordPolicyRelsCount() {
		return passwordPolicyRelPersistence.countAll();
	}

	/**
	 * Updates the password policy rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param passwordPolicyRel the password policy rel
	 * @return the password policy rel that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public PasswordPolicyRel updatePasswordPolicyRel(
		PasswordPolicyRel passwordPolicyRel) {
		return passwordPolicyRelPersistence.update(passwordPolicyRel);
	}

	/**
	 * Returns the password policy rel local service.
	 *
	 * @return the password policy rel local service
	 */
	public PasswordPolicyRelLocalService getPasswordPolicyRelLocalService() {
		return passwordPolicyRelLocalService;
	}

	/**
	 * Sets the password policy rel local service.
	 *
	 * @param passwordPolicyRelLocalService the password policy rel local service
	 */
	public void setPasswordPolicyRelLocalService(
		PasswordPolicyRelLocalService passwordPolicyRelLocalService) {
		this.passwordPolicyRelLocalService = passwordPolicyRelLocalService;
	}

	/**
	 * Returns the password policy rel persistence.
	 *
	 * @return the password policy rel persistence
	 */
	public PasswordPolicyRelPersistence getPasswordPolicyRelPersistence() {
		return passwordPolicyRelPersistence;
	}

	/**
	 * Sets the password policy rel persistence.
	 *
	 * @param passwordPolicyRelPersistence the password policy rel persistence
	 */
	public void setPasswordPolicyRelPersistence(
		PasswordPolicyRelPersistence passwordPolicyRelPersistence) {
		this.passwordPolicyRelPersistence = passwordPolicyRelPersistence;
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

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.portal.model.PasswordPolicyRel",
			passwordPolicyRelLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portal.model.PasswordPolicyRel");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return PasswordPolicyRelLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return PasswordPolicyRel.class;
	}

	protected String getModelClassName() {
		return PasswordPolicyRel.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = passwordPolicyRelPersistence.getDataSource();

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

	@BeanReference(type = com.liferay.portal.service.PasswordPolicyRelLocalService.class)
	protected PasswordPolicyRelLocalService passwordPolicyRelLocalService;
	@BeanReference(type = PasswordPolicyRelPersistence.class)
	protected PasswordPolicyRelPersistence passwordPolicyRelPersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.ClassNameLocalService.class)
	protected com.liferay.portal.service.ClassNameLocalService classNameLocalService;
	@BeanReference(type = com.liferay.portal.service.ClassNameService.class)
	protected com.liferay.portal.service.ClassNameService classNameService;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}