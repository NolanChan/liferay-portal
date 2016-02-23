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

package com.liferay.reports.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.reports.model.Definition;
import com.liferay.reports.service.DefinitionLocalServiceUtil;

/**
 * @author Michael C. Han
 */
public class DefinitionPermission {

	public static void check(
			PermissionChecker permissionChecker, Definition definition,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, definition, actionId)) {
			throw new PrincipalException();
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long definitionId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, definitionId, actionId)) {
			throw new PrincipalException();
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, Definition definition,
		String actionId) {

		if (permissionChecker.hasOwnerPermission(
				definition.getCompanyId(), Definition.class.getName(),
				definition.getDefinitionId(), definition.getUserId(),
				actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			definition.getGroupId(), Definition.class.getName(),
			definition.getDefinitionId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long definitionId,
			String actionId)
		throws PortalException {

		Definition definition = DefinitionLocalServiceUtil.getDefinition(
			definitionId);

		return contains(permissionChecker, definition, actionId);
	}

}