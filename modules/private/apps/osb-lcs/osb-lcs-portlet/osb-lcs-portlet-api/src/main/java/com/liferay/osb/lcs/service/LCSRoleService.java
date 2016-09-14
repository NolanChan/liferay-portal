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

import com.liferay.osb.lcs.model.LCSRole;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;

/**
 * Provides the remote service interface for LCSRole. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Igor Beslic
 * @see LCSRoleServiceUtil
 * @see com.liferay.osb.lcs.service.base.LCSRoleServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSRoleServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=osblcs", "json.web.service.context.path=LCSRole"}, service = LCSRoleService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface LCSRoleService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LCSRoleServiceUtil} to access the l c s role remote service. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSRoleServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Returns <code>true</code> if the user has the LCS Administrator role in
	* the LCS project, <code>false</code> otherwise.
	*
	* @param lcsProjectId the primary key of the LCS project
	* @return <code>true</code> if the user has the LCS Administrator role in
	the LCS project, <code>false</code> otherwise
	* @throws PortalException if the operation wasn't allowed by the LCS
	project's membership policy
	* @since LCS 1.0
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasUserLCSAdministratorLCSRole(long lcsProjectId)
		throws PortalException;

	/**
	* Returns <code>true</code> if the user has an LCS role in the LCS project,
	* <code>false</code> otherwise.
	*
	* <p>
	* If <code>manageLCSClusterEntry</code> is <code>true</code>, this method
	* checks whether the role is adequate for environment management tasks.
	* </p>
	*
	* @param lcsProjectId the primary key of the LCS project
	* @param manageLCSClusterEntry whether the user can manage project
	environments
	* @return <code>true</code> if the user has a role in the LCS project or a
	role that lets them manage environments
	* @throws PortalException if the operation wasn't allowed by the LCS
	project's membership policy
	* @since LCS 0.1
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasUserLCSRole(long lcsProjectId,
		boolean manageLCSClusterEntry) throws PortalException;

	/**
	* Assigns the LCS role to the user.
	*
	* @param userId the primary key of the user being assigned the role
	* @param lcsProjectId the primary key of the LCS project the role is
	scoped to
	* @param lcsClusterEntryId the primary key of the environment
	* @param role the LCS role identifier
	* @return the LCS role
	* @throws PortalException if any of the LCS role attributes were invalid,
	or an operation wasn't allowed by the LCS project's membership
	policy
	* @since LCS 0.1
	*/
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	public LCSRole addLCSRole(long userId, long lcsProjectId,
		long lcsClusterEntryId, int role) throws PortalException;

	/**
	* Deletes the LCS role matching the LCS role identifier.
	*
	* @param lcsRoleId the primary key of the LCS role
	* @return the deleted LCS role
	* @throws PortalException if the operation wasn't allowed by the LCS
	project's membership policy
	* @since LCS 0.1
	*/
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	public LCSRole deleteLCSRole(long lcsRoleId) throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Returns all LCS roles scoped to the LCS project.
	*
	* @param lcsProjectId the primary key of the LCS project
	* @return the LCS roles scoped to the LCS project
	* @throws PortalException if the operation wasn't allowed by the LCS
	project's membership policy
	* @since LCS 0.1
	*/
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSRole> getLCSProjectLCSRoles(long lcsProjectId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSRole> getUserLCSRoles(long lcsProjectId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LCSRole> getUserLCSRoles(long lcsProjectId, int role)
		throws PortalException;
}