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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for LCSRole. This utility wraps
 * {@link com.liferay.osb.lcs.service.impl.LCSRoleServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Igor Beslic
 * @see LCSRoleService
 * @see com.liferay.osb.lcs.service.base.LCSRoleServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSRoleServiceImpl
 * @generated
 */
@ProviderType
public class LCSRoleServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSRoleServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	public static boolean hasUserLCSAdministratorLCSRole(long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().hasUserLCSAdministratorLCSRole(lcsProjectId);
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
	public static boolean hasUserLCSRole(long lcsProjectId,
		boolean manageLCSClusterEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().hasUserLCSRole(lcsProjectId, manageLCSClusterEntry);
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
	public static com.liferay.osb.lcs.model.LCSRole addLCSRole(long userId,
		long lcsProjectId, long lcsClusterEntryId, int role)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addLCSRole(userId, lcsProjectId, lcsClusterEntryId, role);
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
	public static com.liferay.osb.lcs.model.LCSRole deleteLCSRole(
		long lcsRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLCSRole(lcsRoleId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
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
	public static java.util.List<com.liferay.osb.lcs.model.LCSRole> getLCSProjectLCSRoles(
		long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLCSProjectLCSRoles(lcsProjectId);
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSRole> getUserLCSRoles(
		long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserLCSRoles(lcsProjectId);
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSRole> getUserLCSRoles(
		long lcsProjectId, int role)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserLCSRoles(lcsProjectId, role);
	}

	public static LCSRoleService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSRoleService, LCSRoleService> _serviceTracker =
		ServiceTrackerFactory.open(LCSRoleService.class);
}