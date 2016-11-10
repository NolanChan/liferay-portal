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

import com.liferay.osb.lcs.advisor.DevelopmentAdvisor;
import com.liferay.osb.lcs.configuration.OSBLCSConfiguration;
import com.liferay.osb.lcs.constants.OSBPortletConstants;
import com.liferay.osb.lcs.util.ApplicationProfile;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(
	configurationPid = "com.liferay.osb.lcs.configuration.OSBLCSConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true,
	property = {"key=application.startup.events"},
	service = DevelopmentAdvisor.class
)
public class DevelopmentAdvisorImpl implements DevelopmentAdvisor {

	public Role addLCSUserRole(long companyId) throws PortalException {
		_checkApplicationProfile();

		Role role = _roleLocalService.fetchRole(
			companyId, OSBPortletConstants.ROLE_OSB_CORP_LCS_USER);

		if (role != null) {
			return role;
		}

		Group group = _groupLocalService.getGroup(
			companyId, GroupConstants.GUEST);
		Map<Locale, String> titleMap = Collections.emptyMap();
		Map<Locale, String> descriptionMap = Collections.emptyMap();

		return _roleLocalService.addRole(
			group.getCreatorUserId(), null, 0,
			OSBPortletConstants.ROLE_OSB_CORP_LCS_USER, titleMap,
			descriptionMap, RoleConstants.TYPE_ORGANIZATION, null, null);
	}

	public User addUser(
			long creatorUserId, long companyId, String firstName,
			String lastName)
		throws Exception {

		_checkApplicationProfile();

		StringBundler sb = new StringBundler(4);

		sb.append(StringUtil.toLowerCase(firstName));
		sb.append(".");
		sb.append(StringUtil.toLowerCase(lastName));
		sb.append("@osblcsmock.com");

		String emailAddress = sb.toString();

		User user = _userLocalService.fetchUserByEmailAddress(
			companyId, emailAddress);

		if (user != null) {
			return user;
		}

		user = _userLocalService.addUser(
			creatorUserId, companyId, true, null, null, true, null,
			emailAddress, 0, null, null, firstName, null, lastName, 0, 0, true,
			9, 9, 2003, null, new long[0], new long[0], new long[0],
			new long[0], false, null);

		return user;
	}

	@Reference(bind = "-", unbind = "-")
	public void setGroupLocalService(GroupLocalService groupLocalService) {
		_groupLocalService = groupLocalService;
	}

	@Reference(bind = "-", unbind = "-")
	public void setRoleLocalService(RoleLocalService roleLocalService) {
		_roleLocalService = roleLocalService;
	}

	@Reference(bind = "-", unbind = "-")
	public void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_osbLCSConfiguration = ConfigurableUtil.createConfigurable(
			OSBLCSConfiguration.class, properties);
	}

	private void _checkApplicationProfile() {
		if (_osbLCSConfiguration.applicationProfile() ==
				ApplicationProfile.PRODUCTION) {

			throw new UnsupportedOperationException("");
		}
	}

	private static volatile OSBLCSConfiguration _osbLCSConfiguration;

	private GroupLocalService _groupLocalService;
	private RoleLocalService _roleLocalService;
	private UserLocalService _userLocalService;

}