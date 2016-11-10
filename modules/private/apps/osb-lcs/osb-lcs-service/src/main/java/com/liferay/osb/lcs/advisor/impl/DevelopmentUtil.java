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

import com.liferay.osb.lcs.osbportlet.util.OSBPortletUtil;
import com.liferay.osb.lcs.util.ApplicationProfile;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;

/**
 * @author Igor Beslic
 */
public class DevelopmentUtil {

	public static Role addLCSUserRole(long companyId)
		throws PortalException, SystemException {

		_checkApplicationProfile();

		Role role = RoleLocalServiceUtil.fetchRole(
			companyId, OSBPortletUtil.ROLE_OSB_CORP_LCS_USER);

		if (role != null) {
			return role;
		}

		Group group = GroupLocalServiceUtil.getGroup(
			companyId, GroupConstants.GUEST);
		Map<Locale, String> titleMap = Collections.emptyMap();
		Map<Locale, String> descriptionMap = Collections.emptyMap();

		return RoleLocalServiceUtil.addRole(
			group.getCreatorUserId(), null, 0,
			OSBPortletUtil.ROLE_OSB_CORP_LCS_USER, titleMap, descriptionMap,
			RoleConstants.TYPE_ORGANIZATION, null, null);
	}

	public static User addUser(
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

		User user = UserLocalServiceUtil.fetchUserByEmailAddress(
			companyId, emailAddress);

		if (user != null) {
			return user;
		}

		user = UserLocalServiceUtil.addUser(
			creatorUserId, companyId, true, null, null, true, null,
			emailAddress, 0, null, null, firstName, null, lastName, 0, 0, true,
			9, 9, 2003, null, new long[] {}, new long[] {}, new long[] {},
			new long[] {}, false, null);

		return user;
	}

	private static void _checkApplicationProfile() {
		if (PortletPropsValues.APPLICATION_PROFILE ==
				ApplicationProfile.PRODUCTION) {

			throw new UnsupportedOperationException("");
		}
	}

}