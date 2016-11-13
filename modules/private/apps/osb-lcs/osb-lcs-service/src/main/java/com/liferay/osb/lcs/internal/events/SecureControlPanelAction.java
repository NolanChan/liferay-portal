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

package com.liferay.osb.lcs.internal.events;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleService;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 */
@Component(
	immediate = true, property = {"key=servlet.service.events.pre"},
	service = LifecycleAction.class
)
public class SecureControlPanelAction implements LifecycleAction {

	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent)
		throws ActionException {

		HttpServletRequest request = lifecycleEvent.getRequest();

		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			Layout layout = themeDisplay.getLayout();

			Group group = _groupLocalService.getGroup(layout.getGroupId());

			if (!group.isControlPanel()) {
				return;
			}

			User user = _userService.getUserById(themeDisplay.getUserId());

			if (_roleService.hasUserRole(
					user.getUserId(), user.getCompanyId(),
					RoleConstants.ADMINISTRATOR, true)) {

				return;
			}

			if (_userGroupRoleLocalService.hasUserGroupRole(
					themeDisplay.getUserId(), themeDisplay.getScopeGroupId(),
					RoleConstants.SITE_ADMINISTRATOR, true)) {

				return;
			}

			if (_userGroupRoleLocalService.hasUserGroupRole(
					themeDisplay.getUserId(), themeDisplay.getScopeGroupId(),
					RoleConstants.SITE_OWNER, true)) {

				return;
			}

			throw new PrincipalException(
				"User " + request.getRemoteUser() +
					" does not have access to the Control Panel");
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	@Reference(bind = "-", unbind = "-")
	public void setGroupLocalService(GroupLocalService groupLocalService) {
		_groupLocalService = groupLocalService;
	}

	@Reference(bind = "-", unbind = "-")
	public void setRoleService(RoleService roleService) {
		_roleService = roleService;
	}

	@Reference(bind = "-", unbind = "-")
	public void setUserGroupRoleLocalService(
		UserGroupRoleLocalService userGroupRoleLocalService) {

		_userGroupRoleLocalService = userGroupRoleLocalService;
	}

	@Reference(bind = "-", unbind = "-")
	public void setUserService(UserService userService) {
		_userService = userService;
	}

	private GroupLocalService _groupLocalService;
	private RoleService _roleService;
	private UserGroupRoleLocalService _userGroupRoleLocalService;
	private UserService _userService;

}