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
public class LCSProjectPermission {

	public static void check(
			PermissionChecker permissionChecker, long lcsProjectId,
			String actionId)
		throws PortalException, SystemException {

		if (!contains(permissionChecker, lcsProjectId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long lcsProjectId,
			String actionId)
		throws SystemException {

		if (LCSRoleLocalServiceUtil.hasUserLCSAdministratorLCSRole(
				permissionChecker.getUserId(), lcsProjectId)) {

			return true;
		}

		if (actionId.equals(ActionKeys.VIEW)) {
			if (LCSRoleLocalServiceUtil.hasUserLCSRole(
					permissionChecker.getUserId(), lcsProjectId, false)) {

				return true;
			}
		}

		User user = permissionChecker.getUser();

		if (Validator.equals("system@liferay.com", user.getEmailAddress())) {
			return true;
		}

		return false;
	}

}