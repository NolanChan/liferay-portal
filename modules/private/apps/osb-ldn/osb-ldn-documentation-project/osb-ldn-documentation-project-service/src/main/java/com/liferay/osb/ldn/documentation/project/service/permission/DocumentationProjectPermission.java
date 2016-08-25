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

import com.liferay.osb.ldn.documentation.project.model.DocumentationProject;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.BaseResourcePermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.ResourcePermissionChecker;

import org.osgi.service.component.annotations.Component;

/**
 * @author Howie Chou
 */
@Component(
	immediate = true,
	property = {"resource.name=" + DocumentationProjectPermission.RESOURCE_NAME},
	service = ResourcePermissionChecker.class
)
public class DocumentationProjectPermission
	extends BaseResourcePermissionChecker {

	public static final String RESOURCE_NAME =
		"com.liferay.osb.ldn.documentation.project";

	public static void check(
			PermissionChecker permissionChecker, long groupId, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, groupId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, RESOURCE_NAME, groupId, actionId);
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker, long classPK, String actionId) {

		if (permissionChecker.isOmniadmin()) {
			return true;
		}

		String portletId = null;

		if (actionId.equals(ActionKeys.ADD_DOCUMENTATION_PROJECT)) {
			portletId = PortletProviderUtil.getPortletId(
				DocumentationProject.class.getName(),
				PortletProvider.Action.ADD);
		}
		else if (actionId.equals(ActionKeys.UPDATE)) {
			portletId = PortletProviderUtil.getPortletId(
				DocumentationProject.class.getName(),
				PortletProvider.Action.EDIT);
		}

		return contains(
			permissionChecker, RESOURCE_NAME, portletId, classPK, actionId);
	}

	@Override
	public Boolean checkResource(
		PermissionChecker permissionChecker, long classPK, String actionId) {

		return contains(permissionChecker, classPK, actionId);
	}

}