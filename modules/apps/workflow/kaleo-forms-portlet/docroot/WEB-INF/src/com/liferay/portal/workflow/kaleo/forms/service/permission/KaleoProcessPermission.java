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

package com.liferay.portal.workflow.kaleo.forms.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess;
import com.liferay.portal.workflow.kaleo.forms.service.KaleoProcessLocalServiceUtil;

/**
 * @author Marcellus Tavares
 */
public class KaleoProcessPermission {

	public static void check(
			PermissionChecker permissionChecker, KaleoProcess kaleoProcess,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, kaleoProcess, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long kaleoProcessId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, kaleoProcessId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, KaleoProcess kaleoProcess,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
				kaleoProcess.getCompanyId(), KaleoProcess.class.getName(),
				kaleoProcess.getKaleoProcessId(), kaleoProcess.getUserId(),
				actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			kaleoProcess.getGroupId(), KaleoProcess.class.getName(),
			kaleoProcess.getKaleoProcessId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long kaleoProcessId,
			String actionId)
		throws PortalException {

		KaleoProcess kaleoProcess =
			KaleoProcessLocalServiceUtil.getKaleoProcess(kaleoProcessId);

		return contains(permissionChecker, kaleoProcess, actionId);
	}

}