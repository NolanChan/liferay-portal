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

package com.liferay.osb.ldn.documentation.project.service.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

/**
 * @author Howie Chou
 */
public class DocumentationProjectPermission {

	public static final String RESOURCE_NAME =
		"com.liferay.osb.ldn.documentation.project";

	public static void check(
			PermissionChecker permissionChecker, String actionKey)
		throws PortalException {

		if (!contains(permissionChecker, actionKey)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, RESOURCE_NAME, 0, actionKey);
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, String actionKey) {

		if (permissionChecker.isCompanyAdmin()) {
			return true;
		}

		return false;
	}

}