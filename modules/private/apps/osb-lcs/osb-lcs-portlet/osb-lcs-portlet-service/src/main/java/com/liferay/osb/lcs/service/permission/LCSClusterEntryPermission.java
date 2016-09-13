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

import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSRole;
import com.liferay.osb.lcs.model.LCSRoleConstants;
import com.liferay.osb.lcs.service.LCSClusterEntryLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSRoleLocalServiceUtil;
import com.liferay.osb.lcs.util.ActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;

/**
 * @author Igor Beslic
 */
public class LCSClusterEntryPermission {

	public static void check(
			PermissionChecker permissionChecker,
			LCSClusterEntry lcsClusterEntry, String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, lcsClusterEntry, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long lcsClusterEntryId,
			String actionId)
		throws PortalException, SystemException {

		LCSClusterEntry lcsClusterEntry =
			LCSClusterEntryLocalServiceUtil.getLCSClusterEntry(
				lcsClusterEntryId);

		if (!contains(permissionChecker, lcsClusterEntry, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker,
			LCSClusterEntry lcsClusterEntry, String actionId)
		throws SystemException {

		LCSRole lcsRole = LCSRoleLocalServiceUtil.fetchLCSRole(
			permissionChecker.getUserId(), lcsClusterEntry.getLcsProjectId(),
			lcsClusterEntry.getLcsClusterEntryId());

		if (lcsRole != null) {
			if (lcsRole.getRole() ==
					LCSRoleConstants.ROLE_LCS_ENVIRONMENT_MANAGER) {

				if (actionId.equals(ActionKeys.MANAGE_USERS)) {
					return false;
				}

				return true;
			}

			if (actionId.equals(ActionKeys.VIEW)) {
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

		if (Validator.equals("system@liferay.com", user.getEmailAddress())) {
			return true;
		}

		return false;
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long lcsClusterEntryId,
			String actionId)
		throws SystemException {

		LCSClusterEntry lcsClusterEntry =
			LCSClusterEntryLocalServiceUtil.fetchLCSClusterEntry(
				lcsClusterEntryId);

		if (lcsClusterEntry == null) {
			return false;
		}

		return contains(permissionChecker, lcsClusterEntry, actionId);
	}

}