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
import com.liferay.osb.lcs.model.LCSRole;
import com.liferay.osb.lcs.service.LCSRoleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

/**
 * @author Igor Beslic
 */
public class CorpProjectPermission {

	public static void check(
			PermissionChecker permissionChecker, long corpProjectId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, corpProjectId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long corpProjectId,
		String actionId) {

		LCSRole lcsRole = LCSRoleLocalServiceUtil.fetchLCSRole(
			permissionChecker.getUserId(), corpProjectId);

		if (lcsRole == null) {
			return false;
		}

		int role = lcsRole.getRole();

		if (role ==
				LCSRoleConstants.ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER) {

			return false;
		}

		if (actionId.equals(OSBLCSActionKeys.VIEW)) {
			return true;
		}

		if (role == LCSRoleConstants.ROLE_LCS_ADMINISTRATOR) {
			return true;
		}

		return false;
	}

}