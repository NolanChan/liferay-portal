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

package com.liferay.osb.lcs.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.lcs.constants.LCSRoleConstants;
import com.liferay.osb.lcs.constants.OSBLCSActionKeys;
import com.liferay.osb.lcs.constants.OSBPortletConstants;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.model.LCSRole;
import com.liferay.osb.lcs.osbportlet.OSBPortletServiceProxy;
import com.liferay.osb.lcs.service.base.LCSRoleServiceBaseImpl;
import com.liferay.osb.lcs.service.permission.LCSProjectPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Provides the remote service for accessing, adding, deleting, updating, and
 * validating LCS roles. Its methods include permission checks.
 *
 * @author  Igor Beslic
 * @version LCS 1.7.1
 * @since   LCS 0.1
 */
@ProviderType
public class LCSRoleServiceImpl extends LCSRoleServiceBaseImpl {

	/**
	 * Assigns the LCS role to the user.
	 *
	 * @param  userId the primary key of the user being assigned the role
	 * @param  lcsProjectId the primary key of the LCS project the role is
	 *         scoped to
	 * @param  lcsClusterEntryId the primary key of the environment
	 * @param  role the LCS role identifier
	 * @return the LCS role
	 * @throws PortalException if any of the LCS role attributes were invalid,
	 *         or an operation wasn't allowed by the LCS project's membership
	 *         policy
	 * @since  LCS 0.1
	 */
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public LCSRole addLCSRole(
			long userId, long lcsProjectId, long lcsClusterEntryId, int role)
		throws PortalException {

		if ((userId == getUserId()) && isFirstLCSRole(userId, lcsProjectId)) {
			if (role != LCSRoleConstants.ROLE_LCS_ADMINISTRATOR) {
				throw new UnsupportedOperationException();
			}

			return lcsRoleLocalService.addLCSRole(
				userId, lcsProjectId, lcsClusterEntryId, role);
		}

		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId,
			OSBLCSActionKeys.MANAGE_USERS);

		return lcsRoleLocalService.addLCSRole(
			userId, lcsProjectId, lcsClusterEntryId, role);
	}

	@Override
	public LCSRole addPendingLCSRole(long lcsProjectId, long lcsClusterEntryId)
		throws PortalException {

		PermissionChecker permissionChecker = getPermissionChecker();

		if (!permissionChecker.isSignedIn()) {
			throw new PrincipalException();
		}

		return lcsRoleLocalService.addLCSRole(
			getUserId(), lcsProjectId, lcsClusterEntryId,
			LCSRoleConstants.ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER);
	}

	/**
	 * Deletes the LCS role matching the LCS role identifier.
	 *
	 * @param  lcsRoleId the primary key of the LCS role
	 * @return the deleted LCS role
	 * @throws PortalException if the operation wasn't allowed by the LCS
	 *         project's membership policy
	 * @since  LCS 0.1
	 */
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public LCSRole deleteLCSRole(long lcsRoleId) throws PortalException {
		LCSRole lcsRole = lcsRolePersistence.findByPrimaryKey(lcsRoleId);

		LCSProjectPermission.check(
			getPermissionChecker(), lcsRole.getLcsProjectId(),
			OSBLCSActionKeys.MANAGE_USERS);

		return lcsRoleLocalService.deleteLCSRole(lcsRole);
	}

	/**
	 * Returns all LCS roles scoped to the LCS project.
	 *
	 * @param  lcsProjectId the primary key of the LCS project
	 * @return the LCS roles scoped to the LCS project
	 * @throws PortalException if the operation wasn't allowed by the LCS
	 *         project's membership policy
	 * @since  LCS 0.1
	 */
	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public List<LCSRole> getLCSProjectLCSRoles(long lcsProjectId)
		throws PortalException {

		PermissionChecker permissionChecker = getPermissionChecker();

		if (!permissionChecker.isCompanyAdmin()) {
			LCSProjectPermission.check(
				getPermissionChecker(), lcsProjectId,
				OSBLCSActionKeys.MANAGE_USERS);
		}

		return lcsRoleLocalService.getLCSProjectLCSRoles(lcsProjectId);
	}

	@Override
	public List<LCSRole> getUserLCSRoles(long lcsProjectId)
		throws PortalException {

		return lcsRoleLocalService.getUserLCSRoles(getUserId(), lcsProjectId);
	}

	@Override
	public List<LCSRole> getUserLCSRoles(long lcsProjectId, int role)
		throws PortalException {

		List<LCSRole> lcsRoles = new ArrayList<>();

		lcsRoles.addAll(
			lcsRoleLocalService.getUserLCSRoles(getUserId(), lcsProjectId));

		Iterator<LCSRole> iterator = lcsRoles.iterator();

		while (iterator.hasNext()) {
			LCSRole lcsRole = iterator.next();

			if (role == lcsRole.getRole()) {
				continue;
			}

			iterator.remove();
		}

		return lcsRoles;
	}

	/**
	 * Returns <code>true</code> if the user has the LCS Administrator role in
	 * the LCS project, <code>false</code> otherwise.
	 *
	 * @param  lcsProjectId the primary key of the LCS project
	 * @return <code>true</code> if the user has the LCS Administrator role in
	 *         the LCS project, <code>false</code> otherwise
	 * @throws PortalException if the operation wasn't allowed by the LCS
	 *         project's membership policy
	 * @since  LCS 1.0
	 */
	@Override
	public boolean hasUserLCSAdministratorLCSRole(long lcsProjectId)
		throws PortalException {

		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId, OSBLCSActionKeys.VIEW);

		return lcsRoleLocalService.hasUserLCSAdministratorLCSRole(
			getUserId(), lcsProjectId);
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
	 * @param  lcsProjectId the primary key of the LCS project
	 * @param  manageLCSClusterEntry whether the user can manage project
	 *         environments
	 * @return <code>true</code> if the user has a role in the LCS project or a
	 *         role that lets them manage environments
	 * @throws PortalException if the operation wasn't allowed by the LCS
	 *         project's membership policy
	 * @since  LCS 0.1
	 */
	@Override
	public boolean hasUserLCSRole(
			long lcsProjectId, boolean manageLCSClusterEntry)
		throws PortalException {

		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId, OSBLCSActionKeys.VIEW);

		return lcsRoleLocalService.hasUserLCSRole(
			getUserId(), lcsProjectId, manageLCSClusterEntry);
	}

	/**
	 * Returns <code>true</code> if the user can be the first LCS role owner in
	 * the LCS project.
	 *
	 * <p>
	 * This method checks the LCS project for the existence of any LCS role. If
	 * no LCS role is found and the user is a LCS project member, the method
	 * returns <code>true</code>. Otherwise it returns <code>false</code>.
	 * </p>
	 *
	 * @param  userId the primary key of the user
	 * @param  lcsProjectId the primary key of the LCS project
	 * @return <code>true</code> if the user can be the first LCS role owner in
	 *         the LCS project, <code>false</code> otherwise
	 * @throws PortalException if the operation wasn't allowed by the LCS
	 *         project's membership policy
	 * @since  LCS 0.1
	 */
	protected boolean isFirstLCSRole(long userId, long lcsProjectId)
		throws PortalException {

		if (lcsRolePersistence.countByLCSProjectId(lcsProjectId) > 0) {
			return false;
		}

		LCSProject lcsProject = lcsProjectPersistence.findByPrimaryKey(
			lcsProjectId);

		return _osbPortletServiceProxy.hasUserCorpProjectRole(
			userId, lcsProject.getCorpProjectId(),
			OSBPortletConstants.ROLE_OSB_CORP_LCS_USER);
	}

	@ServiceReference(type = OSBPortletServiceProxy.class)
	private OSBPortletServiceProxy _osbPortletServiceProxy;

}