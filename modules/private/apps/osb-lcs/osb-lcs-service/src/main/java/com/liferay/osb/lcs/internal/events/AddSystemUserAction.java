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
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 */
@Component(
	immediate = true, property = {"key=application.startup.events"},
	service = LifecycleAction.class
)
public class AddSystemUserAction implements LifecycleAction {

	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent)
		throws ActionException {

		String[] ids = lifecycleEvent.getIds();

		long companyId = GetterUtil.getLong(ids[0]);

		try {
			addSystemUser(companyId);
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	@Reference(bind = "-", unbind = "-")
	public void setRoleLocalService(RoleLocalService roleLocalService) {
		_roleLocalService = roleLocalService;
	}

	@Reference(bind = "-", unbind = "-")
	public void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	protected void addSystemUser(long companyId) throws Exception {
		String emailAddress = "system@liferay.com";

		User user = _userLocalService.fetchUserByEmailAddress(
			companyId, emailAddress);

		if (user != null) {
			return;
		}

		User creatorUser = getUser(companyId);

		_userLocalService.addUser(
			creatorUser.getUserId(), companyId, true, null, null, true, null,
			emailAddress, 0, null, null, "Liferay", null, "System", 0, 0, true,
			9, 9, 2003, null, new long[0], new long[0], new long[0],
			new long[0], false, null);
	}

	protected User getUser(long companyId) throws Exception {
		Role role = _roleLocalService.getRole(
			companyId, RoleConstants.ADMINISTRATOR);

		List<User> users = _userLocalService.getRoleUsers(role.getRoleId());

		return users.get(0);
	}

	private RoleLocalService _roleLocalService;
	private UserLocalService _userLocalService;

}