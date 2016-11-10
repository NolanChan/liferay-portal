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

import com.liferay.osb.lcs.util.WebKeys;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleServiceUtil;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.service.UserServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ivica Cardic
 */
public class SecureControlPanelAction extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response)
		throws ActionException {

		try {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			Layout layout = themeDisplay.getLayout();

			Group group = GroupLocalServiceUtil.getGroup(layout.getGroupId());

			if (!group.isControlPanel()) {
				return;
			}

			User user = UserServiceUtil.getUserById(themeDisplay.getUserId());

			if (RoleServiceUtil.hasUserRole(
					user.getUserId(), user.getCompanyId(),
					RoleConstants.ADMINISTRATOR, true)) {

				return;
			}

			if (UserGroupRoleLocalServiceUtil.hasUserGroupRole(
					themeDisplay.getUserId(), themeDisplay.getScopeGroupId(),
					RoleConstants.SITE_ADMINISTRATOR, true)) {

				return;
			}

			if (UserGroupRoleLocalServiceUtil.hasUserGroupRole(
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

}