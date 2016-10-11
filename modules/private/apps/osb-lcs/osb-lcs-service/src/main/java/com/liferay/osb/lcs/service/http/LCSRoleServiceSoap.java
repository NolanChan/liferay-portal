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

package com.liferay.osb.lcs.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.lcs.service.LCSRoleServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link LCSRoleServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.osb.lcs.model.LCSRoleSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.osb.lcs.model.LCSRole}, that is translated to a
 * {@link com.liferay.osb.lcs.model.LCSRoleSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Igor Beslic
 * @see LCSRoleServiceHttp
 * @see com.liferay.osb.lcs.model.LCSRoleSoap
 * @see LCSRoleServiceUtil
 * @generated
 */
@ProviderType
public class LCSRoleServiceSoap {
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
	public static com.liferay.osb.lcs.model.LCSRoleSoap addLCSRole(
		long userId, long lcsProjectId, long lcsClusterEntryId, int role)
		throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSRole returnValue = LCSRoleServiceUtil.addLCSRole(userId,
					lcsProjectId, lcsClusterEntryId, role);

			return com.liferay.osb.lcs.model.LCSRoleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSRoleSoap addPendingLCSRole(
		long lcsProjectId, long lcsClusterEntryId) throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSRole returnValue = LCSRoleServiceUtil.addPendingLCSRole(lcsProjectId,
					lcsClusterEntryId);

			return com.liferay.osb.lcs.model.LCSRoleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static com.liferay.osb.lcs.model.LCSRoleSoap deleteLCSRole(
		long lcsRoleId) throws RemoteException {
		try {
			com.liferay.osb.lcs.model.LCSRole returnValue = LCSRoleServiceUtil.deleteLCSRole(lcsRoleId);

			return com.liferay.osb.lcs.model.LCSRoleSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static com.liferay.osb.lcs.model.LCSRoleSoap[] getLCSProjectLCSRoles(
		long lcsProjectId) throws RemoteException {
		try {
			java.util.List<com.liferay.osb.lcs.model.LCSRole> returnValue = LCSRoleServiceUtil.getLCSProjectLCSRoles(lcsProjectId);

			return com.liferay.osb.lcs.model.LCSRoleSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSRoleSoap[] getUserLCSRoles(
		long lcsProjectId) throws RemoteException {
		try {
			java.util.List<com.liferay.osb.lcs.model.LCSRole> returnValue = LCSRoleServiceUtil.getUserLCSRoles(lcsProjectId);

			return com.liferay.osb.lcs.model.LCSRoleSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.lcs.model.LCSRoleSoap[] getUserLCSRoles(
		long lcsProjectId, int role) throws RemoteException {
		try {
			java.util.List<com.liferay.osb.lcs.model.LCSRole> returnValue = LCSRoleServiceUtil.getUserLCSRoles(lcsProjectId,
					role);

			return com.liferay.osb.lcs.model.LCSRoleSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static boolean hasUserLCSAdministratorLCSRole(long lcsProjectId)
		throws RemoteException {
		try {
			boolean returnValue = LCSRoleServiceUtil.hasUserLCSAdministratorLCSRole(lcsProjectId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
		boolean manageLCSClusterEntry) throws RemoteException {
		try {
			boolean returnValue = LCSRoleServiceUtil.hasUserLCSRole(lcsProjectId,
					manageLCSClusterEntry);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LCSRoleServiceSoap.class);
}