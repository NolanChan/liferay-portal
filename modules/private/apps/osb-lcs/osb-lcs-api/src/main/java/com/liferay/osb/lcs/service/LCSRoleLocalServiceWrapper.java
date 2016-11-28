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

package com.liferay.osb.lcs.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LCSRoleLocalService}.
 *
 * @author Igor Beslic
 * @see LCSRoleLocalService
 * @generated
 */
@ProviderType
public class LCSRoleLocalServiceWrapper implements LCSRoleLocalService,
	ServiceWrapper<LCSRoleLocalService> {
	public LCSRoleLocalServiceWrapper(LCSRoleLocalService lcsRoleLocalService) {
		_lcsRoleLocalService = lcsRoleLocalService;
	}

	/**
	* Returns <code>true</code> if the user has the LCS Administrator role in
	* the specified LCS project.
	*
	* @param userId the primary key of the user
	* @param lcsProjectId the primary key of the LCS project
	* @return <code>true</code> if the user has the LCS Administrator role in
	the LCS project; <code>false</code> otherwise
	* @since LCS 1.0
	*/
	@Override
	public boolean hasUserLCSAdministratorLCSRole(long userId, long lcsProjectId) {
		return _lcsRoleLocalService.hasUserLCSAdministratorLCSRole(userId,
			lcsProjectId);
	}

	/**
	* Returns <code>true</code> if the user has an LCS role.
	*
	* <p>
	* This method checks for the presence of any LCS role except the role
	* {@link LCSRoleConstants#ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER}.
	* </p>
	*
	* @param userId the primary key of the user
	* @return <code>true</code> if the user has an LCS role; <code>false</code>
	otherwise
	* @since LCS 0.1
	*/
	@Override
	public boolean hasUserLCSRole(long userId) {
		return _lcsRoleLocalService.hasUserLCSRole(userId);
	}

	/**
	* Returns <code>true</code> if the user has an LCS role in the specified
	* LCS project, or a role that lets the user manage environments.
	*
	* <p>
	* If <code>manageLCSClusterEntry</code> is <code>true</code>, this method
	* checks whether the role is adequate for environment management tasks.
	* </p>
	*
	* @param userId the primary key of the user
	* @param lcsProjectId the primary key of the LCS project
	* @param manageLCSClusterEntry whether the user can manage project
	environments
	* @return <code>true</code> if the user has an LCS role in the LCS project,
	or a role that lets the user manage environments;
	<code>false</code> otherwise
	* @since LCS 0.1
	*/
	@Override
	public boolean hasUserLCSRole(long userId, long lcsProjectId,
		boolean manageLCSClusterEntry) {
		return _lcsRoleLocalService.hasUserLCSRole(userId, lcsProjectId,
			manageLCSClusterEntry);
	}

	/**
	* Adds the l c s role to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsRole the l c s role
	* @return the l c s role that was added
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSRole addLCSRole(
		com.liferay.osb.lcs.model.LCSRole lcsRole) {
		return _lcsRoleLocalService.addLCSRole(lcsRole);
	}

	/**
	* Assigns the LCS role to the user.
	*
	* @param userId the primary key of the user the role is assigned to
	* @param lcsProjectId the primary key of the LCS project the role is
	scoped to
	* @param lcsClusterEntryId the primary key of the environment
	* @param role the LCS role identifier
	* @return the LCS role
	* @throws PortalException
	* @since LCS 0.1
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSRole addLCSRole(long userId,
		long lcsProjectId, long lcsClusterEntryId, int role)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsRoleLocalService.addLCSRole(userId, lcsProjectId,
			lcsClusterEntryId, role);
	}

	/**
	* Creates a new l c s role with the primary key. Does not add the l c s role to the database.
	*
	* @param lcsRoleId the primary key for the new l c s role
	* @return the new l c s role
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSRole createLCSRole(long lcsRoleId) {
		return _lcsRoleLocalService.createLCSRole(lcsRoleId);
	}

	/**
	* Deletes the l c s role from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsRole the l c s role
	* @return the l c s role that was removed
	* @throws PortalException
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSRole deleteLCSRole(
		com.liferay.osb.lcs.model.LCSRole lcsRole)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsRoleLocalService.deleteLCSRole(lcsRole);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSRole deleteLCSRole(
		com.liferay.osb.lcs.model.LCSRole lcsRole, boolean force)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsRoleLocalService.deleteLCSRole(lcsRole, force);
	}

	/**
	* Deletes the l c s role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsRoleId the primary key of the l c s role
	* @return the l c s role that was removed
	* @throws PortalException if a l c s role with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSRole deleteLCSRole(long lcsRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsRoleLocalService.deleteLCSRole(lcsRoleId);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSRole fetchLCSRole(long lcsRoleId) {
		return _lcsRoleLocalService.fetchLCSRole(lcsRoleId);
	}

	/**
	* Returns the user's LCS role in the LCS project and environment.
	*
	* @param userId the primary key of the user
	* @param lcsProjectId the primary key of the LCS project
	* @param lcsClusterEntryId the primary key of the environment
	* @return the LCS role matching the criteria, or <code>null</code> if no
	matching LCS role is found
	* @since LCS 0.1
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSRole fetchLCSRole(long userId,
		long lcsProjectId, long lcsClusterEntryId) {
		return _lcsRoleLocalService.fetchLCSRole(userId, lcsProjectId,
			lcsClusterEntryId);
	}

	/**
	* Returns the l c s role with the primary key.
	*
	* @param lcsRoleId the primary key of the l c s role
	* @return the l c s role
	* @throws PortalException if a l c s role with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSRole getLCSRole(long lcsRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsRoleLocalService.getLCSRole(lcsRoleId);
	}

	/**
	* Converts the LCS invitation into the LCS role.
	*
	* <p>
	* This method converts the LCS invitation into the LCS role, using
	* attributes common to both classes. The new role is persisted and the
	* invitation is then removed.
	* </p>
	*
	* @param lcsInvitation the LCS invitation
	* @return the LCS role
	* @throws PortalException
	* @since LCS 0.1
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSRole toLCSRole(
		com.liferay.osb.lcs.model.LCSInvitation lcsInvitation)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsRoleLocalService.toLCSRole(lcsInvitation);
	}

	/**
	* Updates the l c s role in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsRole the l c s role
	* @return the l c s role that was updated
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSRole updateLCSRole(
		com.liferay.osb.lcs.model.LCSRole lcsRole) {
		return _lcsRoleLocalService.updateLCSRole(lcsRole);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _lcsRoleLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _lcsRoleLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _lcsRoleLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsRoleLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsRoleLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of LCS roles in the LCS project.
	*
	* @param lcsProjectId the primary key of the LCS project
	* @return the number of LCS roles in the LCS project
	*/
	@Override
	public int getLCSProjectLCSRolesCount(long lcsProjectId) {
		return _lcsRoleLocalService.getLCSProjectLCSRolesCount(lcsProjectId);
	}

	/**
	* Returns the number of l c s roles.
	*
	* @return the number of l c s roles
	*/
	@Override
	public int getLCSRolesCount() {
		return _lcsRoleLocalService.getLCSRolesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _lcsRoleLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _lcsRoleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _lcsRoleLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _lcsRoleLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns all LCS roles that allow access to the environment.
	*
	* <p>
	* This method is provided to get the LCS roles that have access to the
	* environment specified by the LCS cluster entry ID.
	* </p>
	*
	* @param lcsClusterEntryId the primary key of the environment
	* @return the LCS roles that allow access to the environment
	* @since LCS 1.1
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSRole> getLCSClusterEntryLCSRoles(
		long lcsClusterEntryId) {
		return _lcsRoleLocalService.getLCSClusterEntryLCSRoles(lcsClusterEntryId);
	}

	/**
	* Returns all the LCS project's LCS roles.
	*
	* @param lcsProjectId the primary key of the LCS project
	* @return the LCS roles in the LCS project
	* @since LCS 0.1
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSRole> getLCSProjectLCSRoles(
		long lcsProjectId) {
		return _lcsRoleLocalService.getLCSProjectLCSRoles(lcsProjectId);
	}

	/**
	* Returns all the LCS project's LCS roles, that also match the role
	* identifier.
	*
	* @param lcsProjectId the primary key of the LCS project
	* @param role the role identifier
	* @return the LCS roles in the LCS project, that also match the role
	identifier
	* @since LCS 0.1
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSRole> getLCSProjectLCSRoles(
		long lcsProjectId, int role) {
		return _lcsRoleLocalService.getLCSProjectLCSRoles(lcsProjectId, role);
	}

	/**
	* Returns a range of all the l c s roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.lcs.model.impl.LCSRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of l c s roles
	* @param end the upper bound of the range of l c s roles (not inclusive)
	* @return the range of l c s roles
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSRole> getLCSRoles(
		int start, int end) {
		return _lcsRoleLocalService.getLCSRoles(start, end);
	}

	/**
	* Returns all the user's LCS roles.
	*
	* <p>
	* This method finds the user's LCS roles in all their LCS projects.
	* </p>
	*
	* @param userId the primary key of the user
	* @return the user's LCS roles
	* @since LCS 0.1
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSRole> getUserLCSRoles(
		long userId) {
		return _lcsRoleLocalService.getUserLCSRoles(userId);
	}

	/**
	* Returns all the user's LCS roles that also match the role identifier.
	*
	* <p>
	* This method finds the user's LCS roles in all their LCS projects, that
	* also match the role identifier.
	* </p>
	*
	* @param userId the primary key of the user
	* @param role the role identifier
	* @return the user's LCS roles, that also match the role identifier
	* @since LCS 0.1
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSRole> getUserLCSRoles(
		long userId, int role) {
		return _lcsRoleLocalService.getUserLCSRoles(userId, role);
	}

	/**
	* Returns all the user's LCS roles in the LCS project.
	*
	* @param userId the primary key of the user
	* @param lcsProjectId the primary key of the LCS project
	* @return the user's LCS roles in the LCS project
	* @since LCS 0.1
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSRole> getUserLCSRoles(
		long userId, long lcsProjectId) {
		return _lcsRoleLocalService.getUserLCSRoles(userId, lcsProjectId);
	}

	/**
	* Converts all the LCS invitations into LCS roles.
	*
	* <p>
	* This method converts each LCS invitation into its corresponding LCS role,
	* using attributes common to both classes. Each new role is persisted and
	* each invitation is then removed.
	* </p>
	*
	* @param lcsInvitations the LCS invitations
	* @return the LCS roles converted from the LCS invitations
	* @throws PortalException
	* @since LCS 0.1
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSRole> toLCSRoles(
		java.util.List<com.liferay.osb.lcs.model.LCSInvitation> lcsInvitations)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsRoleLocalService.toLCSRoles(lcsInvitations);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _lcsRoleLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _lcsRoleLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public LCSRoleLocalService getWrappedService() {
		return _lcsRoleLocalService;
	}

	@Override
	public void setWrappedService(LCSRoleLocalService lcsRoleLocalService) {
		_lcsRoleLocalService = lcsRoleLocalService;
	}

	private LCSRoleLocalService _lcsRoleLocalService;
}