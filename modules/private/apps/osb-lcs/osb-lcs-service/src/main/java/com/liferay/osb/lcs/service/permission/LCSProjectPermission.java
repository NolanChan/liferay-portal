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

import com.liferay.osb.lcs.constants.OSBLCSActionKeys;
import com.liferay.osb.lcs.service.LCSRoleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import java.util.Objects;

/**
 * @author Igor Beslic
 */
public class LCSProjectPermission {

	public static void check(
			PermissionChecker permissionChecker, long lcsProjectId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, lcsProjectId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long lcsProjectId,
		String actionId) {

		if (LCSRoleLocalServiceUtil.hasUserLCSAdministratorLCSRole(
				permissionChecker.getUserId(), lcsProjectId)) {

			return true;
		}

		if (actionId.equals(OSBLCSActionKeys.VIEW)) {
			if (LCSRoleLocalServiceUtil.hasUserLCSRole(
					permissionChecker.getUserId(), lcsProjectId, false)) {

				return true;
			}
		}

		User user = permissionChecker.getUser();

		if (Objects.equals("system@liferay.com", user.getEmailAddress())) {
			return true;
		}

		return false;
	}

}