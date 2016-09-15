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

package com.liferay.osb.lcs.service.permission;

import com.liferay.osb.lcs.constants.LCSRoleConstants;
import com.liferay.osb.lcs.constants.OSBLCSActionKeys;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSRole;
import com.liferay.osb.lcs.service.LCSClusterEntryLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSRoleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import java.util.Objects;

/**
 * @author Igor Beslic
 */
public class LCSClusterEntryPermission {

	public static void check(
			PermissionChecker permissionChecker,
			LCSClusterEntry lcsClusterEntry, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, lcsClusterEntry, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long lcsClusterEntryId,
			String actionId)
		throws PortalException {

		LCSClusterEntry lcsClusterEntry =
			LCSClusterEntryLocalServiceUtil.getLCSClusterEntry(
				lcsClusterEntryId);

		if (!contains(permissionChecker, lcsClusterEntry, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, LCSClusterEntry lcsClusterEntry,
		String actionId) {

		LCSRole lcsRole = LCSRoleLocalServiceUtil.fetchLCSRole(
			permissionChecker.getUserId(), lcsClusterEntry.getLcsProjectId(),
			lcsClusterEntry.getLcsClusterEntryId());

		if (lcsRole != null) {
			if (lcsRole.getRole() ==
					LCSRoleConstants.ROLE_LCS_ENVIRONMENT_MANAGER) {

				if (actionId.equals(OSBLCSActionKeys.MANAGE_USERS)) {
					return false;
				}

				return true;
			}

			if (actionId.equals(OSBLCSActionKeys.VIEW)) {
				return true;
			}

			return false;
		}

		if (LCSRoleLocalServiceUtil.hasUserLCSAdministratorLCSRole(
				permissionChecker.getUserId(),
				lcsClusterEntry.getLcsProjectId())) {

			return true;
		}

		User user = permissionChecker.getUser();

		if (Objects.equals("system@liferay.com", user.getEmailAddress())) {
			return true;
		}

		return false;
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long lcsClusterEntryId,
		String actionId) {

		LCSClusterEntry lcsClusterEntry =
			LCSClusterEntryLocalServiceUtil.fetchLCSClusterEntry(
				lcsClusterEntryId);

		if (lcsClusterEntry == null) {
			return false;
		}

		return contains(permissionChecker, lcsClusterEntry, actionId);
	}

}