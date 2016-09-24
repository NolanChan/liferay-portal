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
 * Provides a wrapper for {@link LCSRoleService}.
 *
 * @author Igor Beslic
 * @see LCSRoleService
 * @generated
 */
@ProviderType
public class LCSRoleServiceWrapper implements LCSRoleService,
	ServiceWrapper<LCSRoleService> {
	public LCSRoleServiceWrapper(LCSRoleService lcsRoleService) {
		_lcsRoleService = lcsRoleService;
	}

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
	@Override
	public boolean hasUserLCSAdministratorLCSRole(long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsRoleService.hasUserLCSAdministratorLCSRole(lcsProjectId);
	}

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
	@Override
	public boolean hasUserLCSRole(long lcsProjectId,
		boolean manageLCSClusterEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsRoleService.hasUserLCSRole(lcsProjectId,
			manageLCSClusterEntry);
	}

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
	@Override
	public com.liferay.osb.lcs.model.LCSRole addLCSRole(long userId,
		long lcsProjectId, long lcsClusterEntryId, int role)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsRoleService.addLCSRole(userId, lcsProjectId,
			lcsClusterEntryId, role);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSRole addPendingLCSRole(
		long lcsProjectId, long lcsClusterEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsRoleService.addPendingLCSRole(lcsProjectId, lcsClusterEntryId);
	}

	/**
	* Deletes the LCS role matching the LCS role identifier.
	*
	* @param lcsRoleId the primary key of the LCS role
	* @return the deleted LCS role
	* @throws PortalException if the operation wasn't allowed by the LCS
	project's membership policy
	* @since LCS 0.1
	*/
	@Override
	public com.liferay.osb.lcs.model.LCSRole deleteLCSRole(long lcsRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsRoleService.deleteLCSRole(lcsRoleId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _lcsRoleService.getOSGiServiceIdentifier();
	}

	/**
	* Returns all LCS roles scoped to the LCS project.
	*
	* @param lcsProjectId the primary key of the LCS project
	* @return the LCS roles scoped to the LCS project
	* @throws PortalException if the operation wasn't allowed by the LCS
	project's membership policy
	* @since LCS 0.1
	*/
	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSRole> getLCSProjectLCSRoles(
		long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsRoleService.getLCSProjectLCSRoles(lcsProjectId);
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSRole> getUserLCSRoles(
		long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsRoleService.getUserLCSRoles(lcsProjectId);
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSRole> getUserLCSRoles(
		long lcsProjectId, int role)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsRoleService.getUserLCSRoles(lcsProjectId, role);
	}

	@Override
	public LCSRoleService getWrappedService() {
		return _lcsRoleService;
	}

	@Override
	public void setWrappedService(LCSRoleService lcsRoleService) {
		_lcsRoleService = lcsRoleService;
	}

	private LCSRoleService _lcsRoleService;
}