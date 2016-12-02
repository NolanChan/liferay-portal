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

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.osb.lcs.advisor.CompanyAdvisor;
import com.liferay.osb.lcs.advisor.UserAdvisor;
import com.liferay.osb.lcs.constants.LCSRoleConstants;
import com.liferay.osb.lcs.constants.OSBPortletConstants;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSInvitation;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.model.LCSRole;
import com.liferay.osb.lcs.osbportlet.service.OSBPortletService;
import com.liferay.osb.lcs.service.base.LCSRoleLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Provides the local service for accessing, adding, deleting, updating, and
 * validating LCS roles.
 *
 * @author  Igor Beslic
 * @version LCS 1.7.1
 * @since   LCS 0.1
 */
@ProviderType
public class LCSRoleLocalServiceImpl extends LCSRoleLocalServiceBaseImpl {

	/**
	 * Assigns the LCS role to the user.
	 *
	 * @param  userId the primary key of the user the role is assigned to
	 * @param  lcsProjectId the primary key of the LCS project the role is
	 *         scoped to
	 * @param  lcsClusterEntryId the primary key of the environment
	 * @param  role the LCS role identifier
	 * @return the LCS role
	 * @throws PortalException
	 * @since  LCS 0.1
	 */
	@Override
	public LCSRole addLCSRole(
			long userId, long lcsProjectId, long lcsClusterEntryId, int role)
		throws PortalException {

		if (role == LCSRoleConstants.ROLE_LCS_ADMINISTRATOR) {
			deleteLCSRoles(userId, lcsProjectId);
		}
		else {
			LCSRole lcsRole = lcsRolePersistence.fetchByU_LPI_LCEI(
				userId, lcsProjectId, 0);

			if (lcsRole != null) {
				if (lcsRole.getRole() ==
						LCSRoleConstants.
							ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER) {

					lcsRolePersistence.remove(lcsRole);
				}

				if (lcsRole.getRole() ==
						LCSRoleConstants.ROLE_LCS_ADMINISTRATOR) {

					throw new UnsupportedOperationException();
				}
			}
		}

		if (((role == LCSRoleConstants.ROLE_LCS_ENVIRONMENT_MANAGER) ||
			 (role == LCSRoleConstants.ROLE_LCS_ENVIRONMENT_VIEWER)) &&
			(lcsClusterEntryId == 0)) {

			throw new UnsupportedOperationException();
		}

		LCSRole lcsRole = lcsRoleLocalService.createLCSRole(
			counterLocalService.increment());

		lcsRole.setUserId(userId);
		lcsRole.setLcsProjectId(lcsProjectId);
		lcsRole.setLcsClusterEntryId(lcsClusterEntryId);
		lcsRole.setRole(role);

		lcsRole = lcsRolePersistence.update(lcsRole);

		if (role ==
				LCSRoleConstants.ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER) {

			return lcsRole;
		}

		lcsMembersLocalService.validateLCSSiteMembership(
			_companyAdvisor.getCompanyId(), userId);

		LCSProject lcsProject = lcsProjectPersistence.findByPrimaryKey(
			lcsProjectId);

		long corpProjectId = lcsProject.getCorpProjectId();

		if (!_osbPortletService.hasUserCorpProject(userId, corpProjectId)) {
			_osbPortletService.addCorpProjectUsers(
				corpProjectId, new long[] {userId});
		}

		if (!_osbPortletService.hasUserCorpProjectRole(
				userId, corpProjectId,
				OSBPortletConstants.ROLE_OSB_CORP_LCS_USER)) {

			_osbPortletService.addUserCorpProjectRoles(
				lcsProject.getCorpProjectId(), new long[] {userId},
				OSBPortletConstants.ROLE_OSB_CORP_LCS_USER);
		}

		if (!_userAdvisor.hasUserDefaultLCSProject(userId)) {
			_userAdvisor.updateDefaultLCSProject(userId, lcsProjectId);
		}

		return lcsRole;
	}

	/**
	 * Deletes the LCS role from the database.
	 *
	 * <p>
	 * If the LCS role is {@link LCSRoleConstants#ROLE_LCS_ADMINISTRATOR}, this
	 * method first checks to make sure that there is at least one other LCS
	 * Administrator in the LCS project. If there is no other LCS Administrator,
	 * an {@link UnsupportedOperationException} is thrown and the LCS
	 * Administrator is not deleted.
	 * </p>
	 *
	 * @param  lcsRole the LCS role
	 * @return the deleted LCS role
	 * @throws PortalException
	 * @since  LCS 1.3
	 */
	@Override
	public LCSRole deleteLCSRole(LCSRole lcsRole) throws PortalException {
		if (lcsRole.getRole() == LCSRoleConstants.ROLE_LCS_ADMINISTRATOR) {
			int count = lcsRolePersistence.countByLPI_R(
				lcsRole.getLcsProjectId(),
				LCSRoleConstants.ROLE_LCS_ADMINISTRATOR);

			if (count == 1) {
				throw new UnsupportedOperationException();
			}
		}

		userLCSMessageLocalService.deleteUserLCSMessages(lcsRole);

		lcsRole = super.deleteLCSRole(lcsRole);

		if (!hasUserLCSRole(
				lcsRole.getUserId(), lcsRole.getLcsProjectId(), false)) {

			LCSProject lcsProject = lcsProjectPersistence.findByPrimaryKey(
				lcsRole.getLcsProjectId());

			_osbPortletService.deleteUserCorpProjectRoles(
				lcsProject.getCorpProjectId(),
				new long[] {lcsRole.getUserId()});

			User user = userLocalService.getUser(lcsRole.getUserId());

			ExpandoBridge expandoBridge = user.getExpandoBridge();

			long defaultLCSProjectId = (Long)expandoBridge.getAttribute(
				"defaultLCSProjectId", false);

			if (defaultLCSProjectId == lcsRole.getLcsProjectId()) {
				expandoBridge.setAttribute("defaultLCSProjectId", 0L, false);
			}
		}

		List<LCSProject> lcsProjects =
			lcsProjectLocalService.getUserLCSProjects(lcsRole.getUserId());

		if (!lcsProjects.isEmpty()) {
			return lcsRole;
		}

		lcsNotificationLocalService.deleteUserLCSNotifications(
			lcsRole.getUserId());

		lcsMembersLocalService.invalidateLCSSiteMembership(
			_companyAdvisor.getCompanyId(), lcsRole.getUserId());

		return lcsRole;
	}

	@Override
	public LCSRole deleteLCSRole(LCSRole lcsRole, boolean force)
		throws PortalException {

		if (force) {
			return super.deleteLCSRole(lcsRole);
		}

		return deleteLCSRole(lcsRole);
	}

	/**
	 * Returns the user's LCS role in the LCS project and environment.
	 *
	 * @param  userId the primary key of the user
	 * @param  lcsProjectId the primary key of the LCS project
	 * @param  lcsClusterEntryId the primary key of the environment
	 * @return the LCS role matching the criteria, or <code>null</code> if no
	 *         matching LCS role is found
	 * @since  LCS 0.1
	 */
	@Override
	public LCSRole fetchLCSRole(
		long userId, long lcsProjectId, long lcsClusterEntryId) {

		return lcsRolePersistence.fetchByU_LPI_LCEI(
			userId, lcsProjectId, lcsClusterEntryId);
	}

	/**
	 * Returns all LCS roles that allow access to the environment.
	 *
	 * <p>
	 * This method is provided to get the LCS roles that have access to the
	 * environment specified by the LCS cluster entry ID.
	 * </p>
	 *
	 * @param  lcsClusterEntryId the primary key of the environment
	 * @return the LCS roles that allow access to the environment
	 * @since  LCS 1.1
	 */
	@Override
	public List<LCSRole> getLCSClusterEntryLCSRoles(long lcsClusterEntryId) {
		Set<LCSRole> lcsRoles = new HashSet<>();

		lcsRoles.addAll(
			lcsRolePersistence.findByLCSClusterEntryId(lcsClusterEntryId));

		LCSClusterEntry lcsClusterEntry =
			lcsClusterEntryPersistence.fetchByPrimaryKey(lcsClusterEntryId);

		if (lcsClusterEntry == null) {
			return ListUtil.fromCollection(lcsRoles);
		}

		// The LCS Administrator role has access to all of the LCS
		// project's LCS cluster entries, and we find it using LCS project
		// ID.

		lcsRoles.addAll(
			lcsRolePersistence.findByLPI_R(
				lcsClusterEntry.getLcsProjectId(),
				LCSRoleConstants.ROLE_LCS_ADMINISTRATOR));

		return ListUtil.fromCollection(lcsRoles);
	}

	/**
	 * Returns all the LCS project's LCS roles.
	 *
	 * @param  lcsProjectId the primary key of the LCS project
	 * @return the LCS roles in the LCS project
	 * @since  LCS 0.1
	 */
	@Override
	public List<LCSRole> getLCSProjectLCSRoles(long lcsProjectId) {
		return lcsRolePersistence.findByLCSProjectId(lcsProjectId);
	}

	/**
	 * Returns all the LCS project's LCS roles, that also match the role
	 * identifier.
	 *
	 * @param  lcsProjectId the primary key of the LCS project
	 * @param  role the role identifier
	 * @return the LCS roles in the LCS project, that also match the role
	 *         identifier
	 * @since  LCS 0.1
	 */
	@Override
	public List<LCSRole> getLCSProjectLCSRoles(long lcsProjectId, int role) {
		return lcsRolePersistence.findByLPI_R(lcsProjectId, role);
	}

	/**
	 * Returns the number of LCS roles in the LCS project.
	 *
	 * @param  lcsProjectId the primary key of the LCS project
	 * @return the number of LCS roles in the LCS project
	 */
	@Override
	public int getLCSProjectLCSRolesCount(long lcsProjectId) {
		return lcsRolePersistence.countByLCSProjectId(lcsProjectId);
	}

	/**
	 * Returns all the user's LCS roles.
	 *
	 * <p>
	 * This method finds the user's LCS roles in all their LCS projects.
	 * </p>
	 *
	 * @param  userId the primary key of the user
	 * @return the user's LCS roles
	 * @since  LCS 0.1
	 */
	@Override
	public List<LCSRole> getUserLCSRoles(long userId) {
		return lcsRolePersistence.findByUserId(userId);
	}

	/**
	 * Returns all the user's LCS roles that also match the role identifier.
	 *
	 * <p>
	 * This method finds the user's LCS roles in all their LCS projects, that
	 * also match the role identifier.
	 * </p>
	 *
	 * @param  userId the primary key of the user
	 * @param  role the role identifier
	 * @return the user's LCS roles, that also match the role identifier
	 * @since  LCS 0.1
	 */
	@Override
	public List<LCSRole> getUserLCSRoles(long userId, int role) {
		return lcsRolePersistence.findByU_R(userId, role);
	}

	/**
	 * Returns all the user's LCS roles in the LCS project.
	 *
	 * @param  userId the primary key of the user
	 * @param  lcsProjectId the primary key of the LCS project
	 * @return the user's LCS roles in the LCS project
	 * @since  LCS 0.1
	 */
	@Override
	public List<LCSRole> getUserLCSRoles(long userId, long lcsProjectId) {
		return lcsRolePersistence.findByU_LPI(userId, lcsProjectId);
	}

	/**
	 * Returns <code>true</code> if the user has the LCS Administrator role in
	 * the specified LCS project.
	 *
	 * @param  userId the primary key of the user
	 * @param  lcsProjectId the primary key of the LCS project
	 * @return <code>true</code> if the user has the LCS Administrator role in
	 *         the LCS project; <code>false</code> otherwise
	 * @since  LCS 1.0
	 */
	@Override
	public boolean hasUserLCSAdministratorLCSRole(
		long userId, long lcsProjectId) {

		List<LCSRole> lcsRoles = lcsRolePersistence.findByU_LPI(
			userId, lcsProjectId);

		for (LCSRole lcsRole : lcsRoles) {
			if (lcsRole.getRole() == LCSRoleConstants.ROLE_LCS_ADMINISTRATOR) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns <code>true</code> if the user has an LCS role.
	 *
	 * <p>
	 * This method checks for the presence of any LCS role except the role
	 * {@link LCSRoleConstants#ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER}.
	 * </p>
	 *
	 * @param  userId the primary key of the user
	 * @return <code>true</code> if the user has an LCS role; <code>false</code>
	 *         otherwise
	 * @since  LCS 0.1
	 */
	@Override
	public boolean hasUserLCSRole(long userId) {
		List<LCSRole> lcsRoles = lcsRolePersistence.findByUserId(userId);

		for (LCSRole lcsRole : lcsRoles) {
			if (lcsRole.getRole() !=
					LCSRoleConstants.
						ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER) {

				return true;
			}
		}

		return false;
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
	 * @param  userId the primary key of the user
	 * @param  lcsProjectId the primary key of the LCS project
	 * @param  manageLCSClusterEntry whether the user can manage project
	 *         environments
	 * @return <code>true</code> if the user has an LCS role in the LCS project,
	 *         or a role that lets the user manage environments;
	 *         <code>false</code> otherwise
	 * @since  LCS 0.1
	 */
	@Override
	public boolean hasUserLCSRole(
		long userId, long lcsProjectId, boolean manageLCSClusterEntry) {

		List<LCSRole> lcsRoles = lcsRolePersistence.findByU_LPI(
			userId, lcsProjectId);

		int[] ignoredRoles =
			{LCSRoleConstants.ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER};

		if (manageLCSClusterEntry) {
			ignoredRoles = new int[] {
				LCSRoleConstants.ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER,
				LCSRoleConstants.ROLE_LCS_ENVIRONMENT_VIEWER
			};
		}

		for (LCSRole lcsRole : lcsRoles) {
			if (ArrayUtil.contains(ignoredRoles, lcsRole.getRole())) {
				continue;
			}

			return true;
		}

		return false;
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
	 * @param  lcsInvitation the LCS invitation
	 * @return the LCS role
	 * @throws PortalException
	 * @since  LCS 0.1
	 */
	@Override
	public LCSRole toLCSRole(LCSInvitation lcsInvitation)
		throws PortalException {

		User user = userLocalService.fetchUserByEmailAddress(
			_companyAdvisor.getCompanyId(), lcsInvitation.getEmailAddress());

		if (user == null) {
			return null;
		}

		LCSRole lcsRole = addLCSRole(
			user.getUserId(), lcsInvitation.getLcsProjectId(),
			lcsInvitation.getLcsClusterEntryId(), lcsInvitation.getRole());

		lcsInvitationPersistence.remove(lcsInvitation);

		return lcsRole;
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
	 * @param  lcsInvitations the LCS invitations
	 * @return the LCS roles converted from the LCS invitations
	 * @throws PortalException
	 * @since  LCS 0.1
	 */
	@Override
	public List<LCSRole> toLCSRoles(List<LCSInvitation> lcsInvitations)
		throws PortalException {

		List<LCSRole> lcsRoles = new ArrayList<>();

		for (LCSInvitation lcsInvitation : lcsInvitations) {
			lcsRoles.add(toLCSRole(lcsInvitation));
		}

		return lcsRoles;
	}

	/**
	 * Deletes the user's LCS roles in the LCS project.
	 *
	 * @param userId the primary key of the user
	 * @param lcsProjectId the primary key of the LCS project
	 * @since LCS 0.1
	 */
	protected void deleteLCSRoles(long userId, long lcsProjectId) {
		List<LCSRole> lcsRoles = lcsRolePersistence.findByU_LPI(
			userId, lcsProjectId);

		for (LCSRole lcsRole : lcsRoles) {
			lcsRolePersistence.remove(lcsRole);
		}
	}

	@ServiceReference(type = CompanyAdvisor.class)
	private CompanyAdvisor _companyAdvisor;

	@ServiceReference(type = OSBPortletService.class)
	private OSBPortletService _osbPortletService;

	@ServiceReference(type = UserAdvisor.class)
	private UserAdvisor _userAdvisor;

}