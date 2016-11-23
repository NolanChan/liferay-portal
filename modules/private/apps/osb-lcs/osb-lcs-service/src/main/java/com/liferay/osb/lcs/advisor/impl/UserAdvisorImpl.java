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

package com.liferay.osb.lcs.advisor.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.osb.lcs.advisor.StringAdvisor;
import com.liferay.osb.lcs.advisor.UserAdvisor;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(immediate = true, service = UserAdvisor.class)
public class UserAdvisorImpl implements UserAdvisor {

	@Override
	public User addLDAPUser(String uuid) throws PortalException {
		User user = _userLocalService.fetchUserByUuidAndCompanyId(
			uuid, _companyAdvisor.getCompanyId());

		if (user != null) {
			throw new UnsupportedOperationException(
				_stringAdvisor.concat(
					"User with UUID", uuid, "already exists"));
		}

		User creatorUser = getAdminUser(_companyAdvisor.getCompanyId());

		String emailAddress = uuid + "@lcs.liferay.com";

		user = _userLocalService.addUser(
			creatorUser.getUserId(), _companyAdvisor.getCompanyId(), true, null,
			null, true, null, emailAddress, 0, null, null, uuid, null, uuid, 0,
			0, true, 9, 9, 2003, null, new long[0], new long[0], new long[0],
			new long[0], false, null);

		user.setUuid(uuid);
		user.setUserUuid(uuid);

		user = _userLocalService.updateUser(user);

		if (_log.isInfoEnabled()) {
			_log.info("Created new LDAP user with UUID " + uuid);
		}

		return user;
	}

	@Override
	public User getAdminUser(long companyId) throws PortalException {
		Role role = _roleLocalService.getRole(
			companyId, RoleConstants.ADMINISTRATOR);

		List<User> users = _userLocalService.getRoleUsers(role.getRoleId());

		return users.get(0);
	}

	@Override
	public boolean hasUserDefaultLCSProject(long userId)
		throws PortalException {

		User user = _userLocalService.getUser(userId);

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		if (expandoBridge.getAttribute("defaultLCSProjectId", false) != null) {
			return true;
		}

		return false;
	}

	@Reference(bind = "-", unbind = "-")
	public void setCompanyAdvisor(CompanyAdvisorImpl companyAdvisor) {
		_companyAdvisor = companyAdvisor;
	}

	@Reference(bind = "-", unbind = "-")
	public void setRoleLocalService(RoleLocalService roleLocalService) {
		_roleLocalService = roleLocalService;
	}

	@Reference(bind = "-", unbind = "-")
	public void setStringAdvisor(StringAdvisor stringAdvisor) {
		_stringAdvisor = stringAdvisor;
	}

	@Reference(bind = "-", unbind = "-")
	public void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	@Override
	public void updateDefaultLCSProject(long userId, long lcsProjectId)
		throws PortalException {

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

	private static final Log _log = LogFactoryUtil.getLog(
		UserAdvisorImpl.class);

	private CompanyAdvisorImpl _companyAdvisor;
	private RoleLocalService _roleLocalService;
	private StringAdvisor _stringAdvisor;
	private UserLocalService _userLocalService;

}