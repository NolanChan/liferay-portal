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
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.List;

/**
 * @author Ivica Cardic
 */
public class AddSystemUserAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {
		long companyId = GetterUtil.getLong(ids[0]);

		try {
			addSystemUser(companyId);
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	protected void addSystemUser(long companyId) throws Exception {
		String emailAddress = "system@liferay.com";

		User user = UserLocalServiceUtil.fetchUserByEmailAddress(
			companyId, emailAddress);

		if (user != null) {
			return;
		}

		User creatorUser = getUser(companyId);

		UserLocalServiceUtil.addUser(
			creatorUser.getUserId(), companyId, true, null, null, true, null,
			emailAddress, 0, null, null, "Liferay", null, "System", 0, 0, true,
			9, 9, 2003, null, new long[] {}, new long[] {}, new long[] {},
			new long[] {}, false, null);
	}

	protected User getUser(long companyId) throws Exception {
		Role role = RoleLocalServiceUtil.getRole(
			companyId, RoleConstants.ADMINISTRATOR);

		List<User> users = UserLocalServiceUtil.getRoleUsers(role.getRoleId());

		return users.get(0);
	}

}