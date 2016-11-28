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

import com.liferay.osb.lcs.model.LCSInvitation;
import com.liferay.osb.lcs.model.LCSRole;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for LCSRole. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Igor Beslic
 * @see LCSRoleLocalServiceUtil
 * @see com.liferay.osb.lcs.service.base.LCSRoleLocalServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSRoleLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface LCSRoleLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSRoleLocalServiceUtil} to access the l c s role local service. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSRoleLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasUserLCSAdministratorLCSRole(long userId, long lcsProjectId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasUserLCSRole(long userId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasUserLCSRole(long userId, long lcsProjectId,
		boolean manageLCSClusterEntry);

	/**
	* Adds the l c s role to the database. Also notifies the appropriate model listeners.
	*
	* @param lcsRole the l c s role
	* @return the l c s role that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public LCSRole addLCSRole(LCSRole lcsRole);

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
	public LCSRole addLCSRole(long userId, long lcsProjectId,
		long lcsClusterEntryId, int role) throws PortalException;

	/**
	* Creates a new l c s role with the primary key. Does not add the l c s role to the database.
	*
	* @param lcsRoleId the primary key for the new l c s role
	* @return the new l c s role
	*/
	public LCSRole createLCSRole(long lcsRoleId);

	/**
	* Deletes the l c s role from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsRole the l c s role
	* @return the l c s role that was removed
	* @throws PortalException
	*/
	@Indexable(type = IndexableType.DELETE)
	public LCSRole deleteLCSRole(LCSRole lcsRole) throws PortalException;

	public LCSRole deleteLCSRole(LCSRole lcsRole, boolean force)
		throws PortalException;

	/**
	* Deletes the l c s role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param lcsRoleId the primary key of the l c s role
	* @return the l c s role that was removed
	* @throws PortalException if a l c s role with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public LCSRole deleteLCSRole(long lcsRoleId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSRole fetchLCSRole(long lcsRoleId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSRole fetchLCSRole(long userId, long lcsProjectId,
		long lcsClusterEntryId);

	/**
	* Returns the l c s role with the primary key.
	*
	* @param lcsRoleId the primary key of the l c s role
	* @return the l c s role
	* @throws PortalException if a l c s role with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LCSRole getLCSRole(long lcsRoleId) throws PortalException;

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
	public LCSRole toLCSRole(LCSInvitation lcsInvitation)
		throws PortalException;

	/**
	* Updates the l c s role in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param lcsRole the l c s role
	* @return the l c s role that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public LCSRole updateLCSRole(LCSRole lcsRole);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the number of LCS roles in the LCS project.
	*
	* @param lcsProjectId the primary key of the LCS project
	* @return the number of LCS roles in the LCS project
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLCSProjectLCSRolesCount(long lcsProjectId);

	/**
	* Returns the number of l c s roles.
	*
	* @return the number of l c s roles
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLCSRolesCount();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSRole> getLCSClusterEntryLCSRoles(long lcsClusterEntryId);

	/**
	* Returns all the LCS project's LCS roles.
	*
	* @param lcsProjectId the primary key of the LCS project
	* @return the LCS roles in the LCS project
	* @since LCS 0.1
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSRole> getLCSProjectLCSRoles(long lcsProjectId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSRole> getLCSProjectLCSRoles(long lcsProjectId, int role);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSRole> getLCSRoles(int start, int end);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSRole> getUserLCSRoles(long userId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSRole> getUserLCSRoles(long userId, int role);

	/**
	* Returns all the user's LCS roles in the LCS project.
	*
	* @param userId the primary key of the user
	* @param lcsProjectId the primary key of the LCS project
	* @return the user's LCS roles in the LCS project
	* @since LCS 0.1
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSRole> getUserLCSRoles(long userId, long lcsProjectId);

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
	public List<LCSRole> toLCSRoles(List<LCSInvitation> lcsInvitations)
		throws PortalException;

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);
}