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
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.model.UserGroupGroupRole;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.service.UserGroupGroupRoleLocalService;
import com.liferay.portal.service.persistence.RoleFinder;
import com.liferay.portal.service.persistence.RolePersistence;
import com.liferay.portal.service.persistence.UserGroupFinder;
import com.liferay.portal.service.persistence.UserGroupGroupRoleFinder;
import com.liferay.portal.service.persistence.UserGroupGroupRolePK;
import com.liferay.portal.service.persistence.UserGroupGroupRolePersistence;
import com.liferay.portal.service.persistence.UserGroupPersistence;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the user group group role local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.UserGroupGroupRoleLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.UserGroupGroupRoleLocalServiceImpl
 * @see com.liferay.portal.service.UserGroupGroupRoleLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class UserGroupGroupRoleLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements UserGroupGroupRoleLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.portal.service.UserGroupGroupRoleLocalServiceUtil} to access the user group group role local service.
	 */

	/**
	 * Adds the user group group role to the database. Also notifies the appropriate model listeners.
	 *
	 * @param userGroupGroupRole the user group group role
	 * @return the user group group role that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public UserGroupGroupRole addUserGroupGroupRole(
		UserGroupGroupRole userGroupGroupRole) {
		userGroupGroupRole.setNew(true);

		return userGroupGroupRolePersistence.update(userGroupGroupRole);
	}

	/**
	 * Creates a new user group group role with the primary key. Does not add the user group group role to the database.
	 *
	 * @param userGroupGroupRolePK the primary key for the new user group group role
	 * @return the new user group group role
	 */
	@Override
	public UserGroupGroupRole createUserGroupGroupRole(
		UserGroupGroupRolePK userGroupGroupRolePK) {
		return userGroupGroupRolePersistence.create(userGroupGroupRolePK);
	}

	/**
	 * Deletes the user group group role with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userGroupGroupRolePK the primary key of the user group group role
	 * @return the user group group role that was removed
	 * @throws PortalException if a user group group role with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public UserGroupGroupRole deleteUserGroupGroupRole(
		UserGroupGroupRolePK userGroupGroupRolePK) throws PortalException {
		return userGroupGroupRolePersistence.remove(userGroupGroupRolePK);
	}

	/**
	 * Deletes the user group group role from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userGroupGroupRole the user group group role
	 * @return the user group group role that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public UserGroupGroupRole deleteUserGroupGroupRole(
		UserGroupGroupRole userGroupGroupRole) {
		return userGroupGroupRolePersistence.remove(userGroupGroupRole);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(UserGroupGroupRole.class,
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
		return userGroupGroupRolePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserGroupGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return userGroupGroupRolePersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserGroupGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return userGroupGroupRolePersistence.findWithDynamicQuery(dynamicQuery,
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
		return userGroupGroupRolePersistence.countWithDynamicQuery(dynamicQuery);
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
		return userGroupGroupRolePersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public UserGroupGroupRole fetchUserGroupGroupRole(
		UserGroupGroupRolePK userGroupGroupRolePK) {
		return userGroupGroupRolePersistence.fetchByPrimaryKey(userGroupGroupRolePK);
	}

	/**
	 * Returns the user group group role with the primary key.
	 *
	 * @param userGroupGroupRolePK the primary key of the user group group role
	 * @return the user group group role
	 * @throws PortalException if a user group group role with the primary key could not be found
	 */
	@Override
	public UserGroupGroupRole getUserGroupGroupRole(
		UserGroupGroupRolePK userGroupGroupRolePK) throws PortalException {
		return userGroupGroupRolePersistence.findByPrimaryKey(userGroupGroupRolePK);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(com.liferay.portal.service.UserGroupGroupRoleLocalServiceUtil.getService());
		actionableDynamicQuery.setClass(UserGroupGroupRole.class);
		actionableDynamicQuery.setClassLoader(getClassLoader());

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"primaryKey.userGroupId");

		actionableDynamicQuery.setGroupIdPropertyName("primaryKey.groupId");

		return actionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(com.liferay.portal.service.UserGroupGroupRoleLocalServiceUtil.getService());
		actionableDynamicQuery.setClass(UserGroupGroupRole.class);
		actionableDynamicQuery.setClassLoader(getClassLoader());

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"primaryKey.userGroupId");

		actionableDynamicQuery.setGroupIdPropertyName("primaryKey.groupId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return userGroupGroupRoleLocalService.deleteUserGroupGroupRole((UserGroupGroupRole)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return userGroupGroupRolePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the user group group roles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.UserGroupGroupRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of user group group roles
	 * @param end the upper bound of the range of user group group roles (not inclusive)
	 * @return the range of user group group roles
	 */
	@Override
	public List<UserGroupGroupRole> getUserGroupGroupRoles(int start, int end) {
		return userGroupGroupRolePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of user group group roles.
	 *
	 * @return the number of user group group roles
	 */
	@Override
	public int getUserGroupGroupRolesCount() {
		return userGroupGroupRolePersistence.countAll();
	}

	/**
	 * Updates the user group group role in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param userGroupGroupRole the user group group role
	 * @return the user group group role that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public UserGroupGroupRole updateUserGroupGroupRole(
		UserGroupGroupRole userGroupGroupRole) {
		return userGroupGroupRolePersistence.update(userGroupGroupRole);
	}

	/**
	 * Returns the user group group role local service.
	 *
	 * @return the user group group role local service
	 */
	public UserGroupGroupRoleLocalService getUserGroupGroupRoleLocalService() {
		return userGroupGroupRoleLocalService;
	}

	/**
	 * Sets the user group group role local service.
	 *
	 * @param userGroupGroupRoleLocalService the user group group role local service
	 */
	public void setUserGroupGroupRoleLocalService(
		UserGroupGroupRoleLocalService userGroupGroupRoleLocalService) {
		this.userGroupGroupRoleLocalService = userGroupGroupRoleLocalService;
	}

	/**
	 * Returns the user group group role remote service.
	 *
	 * @return the user group group role remote service
	 */
	public com.liferay.portal.service.UserGroupGroupRoleService getUserGroupGroupRoleService() {
		return userGroupGroupRoleService;
	}

	/**
	 * Sets the user group group role remote service.
	 *
	 * @param userGroupGroupRoleService the user group group role remote service
	 */
	public void setUserGroupGroupRoleService(
		com.liferay.portal.service.UserGroupGroupRoleService userGroupGroupRoleService) {
		this.userGroupGroupRoleService = userGroupGroupRoleService;
	}

	/**
	 * Returns the user group group role persistence.
	 *
	 * @return the user group group role persistence
	 */
	public UserGroupGroupRolePersistence getUserGroupGroupRolePersistence() {
		return userGroupGroupRolePersistence;
	}

	/**
	 * Sets the user group group role persistence.
	 *
	 * @param userGroupGroupRolePersistence the user group group role persistence
	 */
	public void setUserGroupGroupRolePersistence(
		UserGroupGroupRolePersistence userGroupGroupRolePersistence) {
		this.userGroupGroupRolePersistence = userGroupGroupRolePersistence;
	}

	/**
	 * Returns the user group group role finder.
	 *
	 * @return the user group group role finder
	 */
	public UserGroupGroupRoleFinder getUserGroupGroupRoleFinder() {
		return userGroupGroupRoleFinder;
	}

	/**
	 * Sets the user group group role finder.
	 *
	 * @param userGroupGroupRoleFinder the user group group role finder
	 */
	public void setUserGroupGroupRoleFinder(
		UserGroupGroupRoleFinder userGroupGroupRoleFinder) {
		this.userGroupGroupRoleFinder = userGroupGroupRoleFinder;
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
	 * Returns the role local service.
	 *
	 * @return the role local service
	 */
	public com.liferay.portal.service.RoleLocalService getRoleLocalService() {
		return roleLocalService;
	}

	/**
	 * Sets the role local service.
	 *
	 * @param roleLocalService the role local service
	 */
	public void setRoleLocalService(
		com.liferay.portal.service.RoleLocalService roleLocalService) {
		this.roleLocalService = roleLocalService;
	}

	/**
	 * Returns the role remote service.
	 *
	 * @return the role remote service
	 */
	public com.liferay.portal.service.RoleService getRoleService() {
		return roleService;
	}

	/**
	 * Sets the role remote service.
	 *
	 * @param roleService the role remote service
	 */
	public void setRoleService(
		com.liferay.portal.service.RoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * Returns the role persistence.
	 *
	 * @return the role persistence
	 */
	public RolePersistence getRolePersistence() {
		return rolePersistence;
	}

	/**
	 * Sets the role persistence.
	 *
	 * @param rolePersistence the role persistence
	 */
	public void setRolePersistence(RolePersistence rolePersistence) {
		this.rolePersistence = rolePersistence;
	}

	/**
	 * Returns the role finder.
	 *
	 * @return the role finder
	 */
	public RoleFinder getRoleFinder() {
		return roleFinder;
	}

	/**
	 * Sets the role finder.
	 *
	 * @param roleFinder the role finder
	 */
	public void setRoleFinder(RoleFinder roleFinder) {
		this.roleFinder = roleFinder;
	}

	/**
	 * Returns the user group local service.
	 *
	 * @return the user group local service
	 */
	public com.liferay.portal.service.UserGroupLocalService getUserGroupLocalService() {
		return userGroupLocalService;
	}

	/**
	 * Sets the user group local service.
	 *
	 * @param userGroupLocalService the user group local service
	 */
	public void setUserGroupLocalService(
		com.liferay.portal.service.UserGroupLocalService userGroupLocalService) {
		this.userGroupLocalService = userGroupLocalService;
	}

	/**
	 * Returns the user group remote service.
	 *
	 * @return the user group remote service
	 */
	public com.liferay.portal.service.UserGroupService getUserGroupService() {
		return userGroupService;
	}

	/**
	 * Sets the user group remote service.
	 *
	 * @param userGroupService the user group remote service
	 */
	public void setUserGroupService(
		com.liferay.portal.service.UserGroupService userGroupService) {
		this.userGroupService = userGroupService;
	}

	/**
	 * Returns the user group persistence.
	 *
	 * @return the user group persistence
	 */
	public UserGroupPersistence getUserGroupPersistence() {
		return userGroupPersistence;
	}

	/**
	 * Sets the user group persistence.
	 *
	 * @param userGroupPersistence the user group persistence
	 */
	public void setUserGroupPersistence(
		UserGroupPersistence userGroupPersistence) {
		this.userGroupPersistence = userGroupPersistence;
	}

	/**
	 * Returns the user group finder.
	 *
	 * @return the user group finder
	 */
	public UserGroupFinder getUserGroupFinder() {
		return userGroupFinder;
	}

	/**
	 * Sets the user group finder.
	 *
	 * @param userGroupFinder the user group finder
	 */
	public void setUserGroupFinder(UserGroupFinder userGroupFinder) {
		this.userGroupFinder = userGroupFinder;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register("com.liferay.portal.model.UserGroupGroupRole",
			userGroupGroupRoleLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portal.model.UserGroupGroupRole");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return UserGroupGroupRoleLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return UserGroupGroupRole.class;
	}

	protected String getModelClassName() {
		return UserGroupGroupRole.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = userGroupGroupRolePersistence.getDataSource();

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

	@BeanReference(type = com.liferay.portal.service.UserGroupGroupRoleLocalService.class)
	protected UserGroupGroupRoleLocalService userGroupGroupRoleLocalService;
	@BeanReference(type = com.liferay.portal.service.UserGroupGroupRoleService.class)
	protected com.liferay.portal.service.UserGroupGroupRoleService userGroupGroupRoleService;
	@BeanReference(type = UserGroupGroupRolePersistence.class)
	protected UserGroupGroupRolePersistence userGroupGroupRolePersistence;
	@BeanReference(type = UserGroupGroupRoleFinder.class)
	protected UserGroupGroupRoleFinder userGroupGroupRoleFinder;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.RoleLocalService.class)
	protected com.liferay.portal.service.RoleLocalService roleLocalService;
	@BeanReference(type = com.liferay.portal.service.RoleService.class)
	protected com.liferay.portal.service.RoleService roleService;
	@BeanReference(type = RolePersistence.class)
	protected RolePersistence rolePersistence;
	@BeanReference(type = RoleFinder.class)
	protected RoleFinder roleFinder;
	@BeanReference(type = com.liferay.portal.service.UserGroupLocalService.class)
	protected com.liferay.portal.service.UserGroupLocalService userGroupLocalService;
	@BeanReference(type = com.liferay.portal.service.UserGroupService.class)
	protected com.liferay.portal.service.UserGroupService userGroupService;
	@BeanReference(type = UserGroupPersistence.class)
	protected UserGroupPersistence userGroupPersistence;
	@BeanReference(type = UserGroupFinder.class)
	protected UserGroupFinder userGroupFinder;
	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry persistedModelLocalServiceRegistry;
}