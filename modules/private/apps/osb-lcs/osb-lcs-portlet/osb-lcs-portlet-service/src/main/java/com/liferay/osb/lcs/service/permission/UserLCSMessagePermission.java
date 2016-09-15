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
import com.liferay.osb.lcs.model.UserLCSMessage;
import com.liferay.osb.lcs.service.UserLCSMessageLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

/**
 * @author Matija Petanjek
 */
public class UserLCSMessagePermission {

	public static void check(
			PermissionChecker permissionChecker, long userLCSMessageId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, userLCSMessageId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long userLCSMessageId,
		String actionId) {

		UserLCSMessage userLCSMessage =
			UserLCSMessageLocalServiceUtil.fetchUserLCSMessage(
				userLCSMessageId);

		if ((permissionChecker.getUserId() == userLCSMessage.getUserId()) &&
			(actionId.equals(OSBLCSActionKeys.MANAGE_ENTRY) ||
			 actionId.equals(OSBLCSActionKeys.VIEW))) {

			return true;
		}

		return false;
	}

}