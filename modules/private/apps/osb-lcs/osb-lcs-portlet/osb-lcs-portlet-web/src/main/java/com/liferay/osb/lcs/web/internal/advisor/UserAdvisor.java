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

package com.liferay.osb.lcs.advisor;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;

import java.util.List;

/**
 * @author Igor Beslic
 */
public class UserAdvisor {

	public User addLDAPUser(String uuid)
		throws PortalException, SystemException {

		User user = _userLocalService.fetchUserByUuidAndCompanyId(
			uuid, _companyAdvisor.getCompanyId());

		if (user != null) {
			throw new UnsupportedOperationException(
				StringAdvisor.concat("User with UUID", uuid, "already exists"));
		}

		User creatorUser = getAdminUser(_companyAdvisor.getCompanyId());

		String emailAddress = uuid + "@lcs.liferay.com";

		user = _userLocalService.addUser(
			creatorUser.getUserId(), _companyAdvisor.getCompanyId(), true, null,
			null, true, null, emailAddress, 0, null, null, uuid, null, uuid, 0,
			0, true, 9, 9, 2003, null, new long[] {}, new long[] {},
			new long[] {}, new long[] {}, false, null);

		user.setUuid(uuid);
		user.setUserUuid(uuid);

		user = _userLocalService.updateUser(user);

		if (_log.isInfoEnabled()) {
			_log.info("Created new LDAP user with UUID " + uuid);
		}

		return user;
	}

	public User getAdminUser(long companyId)
		throws PortalException, SystemException {

		Role role = _roleLocalService.getRole(
			companyId, RoleConstants.ADMINISTRATOR);

		List<User> users = _userLocalService.getRoleUsers(role.getRoleId());

		return users.get(0);
	}

	public boolean hasUserDefaultLCSProject(long userId)
		throws PortalException, SystemException {

		User user = _userLocalService.getUser(userId);

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		if (expandoBridge.getAttribute("defaultLCSProjectId", false) != null) {
			return true;
		}

		return false;
	}

	public void updateDefaultLCSProject(long userId, long lcsProjectId)
		throws PortalException, SystemException {

		User user = _userLocalService.getUser(userId);

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		if (expandoBridge.getAttribute("defaultLCSProjectId", false) == null) {
			expandoBridge.addAttribute(
				"defaultLCSProjectId", ExpandoColumnConstants.LONG,
				lcsProjectId, false);
		}
		else {
			expandoBridge.setAttribute(
				"defaultLCSProjectId", lcsProjectId, false);
		}
	}

	@BeanReference(type = CompanyAdvisor.class)
	protected CompanyAdvisor _companyAdvisor;

	@BeanReference(type = RoleLocalService.class)
	protected RoleLocalService _roleLocalService;

	@BeanReference(type = UserLocalService.class)
	protected UserLocalService _userLocalService;

	private static Log _log = LogFactoryUtil.getLog(UserAdvisor.class);

}