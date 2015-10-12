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

package com.liferay.portal.security.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserGroupLocalServiceUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Preston Crary
 */
public class UserBagFactoryImpl implements UserBagFactory {

	@Override
	public UserPermissionCheckerBag create(long userId) throws PortalException {
		UserPermissionCheckerBag userPermissionCheckerBag =
			PermissionCacheUtil.getUserBag(userId);

		if (userPermissionCheckerBag != null) {
			return userPermissionCheckerBag;
		}

		try {
			List<Group> userGroups = GroupLocalServiceUtil.getUserGroups(
				userId, true);

			List<Organization> userOrgs = getUserOrgs(userId);

			Set<Group> userOrgGroups = SetUtil.fromList(
				GroupLocalServiceUtil.getOrganizationsGroups(userOrgs));

			List<UserGroup> userUserGroups =
				UserGroupLocalServiceUtil.getUserUserGroups(userId);

			List<Group> userUserGroupGroups =
				GroupLocalServiceUtil.getUserGroupsGroups(userUserGroups);

			Set<Role> userRoles = new HashSet<>();

			if (!userGroups.isEmpty()) {
				List<Role> userRelatedRoles =
					RoleLocalServiceUtil.getUserRelatedRoles(
						userId, userGroups);

				userRoles.addAll(userRelatedRoles);
			}
			else {
				userRoles.addAll(RoleLocalServiceUtil.getUserRoles(userId));
			}

			userPermissionCheckerBag = new UserPermissionCheckerBagImpl(
				userId, SetUtil.fromList(userGroups), userOrgs, userOrgGroups,
				userUserGroupGroups, userRoles);

			PermissionCacheUtil.putUserBag(userId, userPermissionCheckerBag);

			return userPermissionCheckerBag;
		}
		catch (Exception e) {
			PermissionCacheUtil.removeUserBag(userId);

			throw e;
		}
	}

	protected List<Organization> getUserOrgs(long userId)
		throws PortalException {

		List<Organization> userOrgs =
			OrganizationLocalServiceUtil.getUserOrganizations(userId);

		if (userOrgs.isEmpty()) {
			return userOrgs;
		}

		Set<Organization> organizations = new LinkedHashSet<>();

		for (Organization organization : userOrgs) {
			if (!organizations.contains(organization)) {
				organizations.add(organization);

				List<Organization> ancestorOrganizations =
					OrganizationLocalServiceUtil.getParentOrganizations(
						organization.getOrganizationId());

				organizations.addAll(ancestorOrganizations);
			}
		}

		return new ArrayList<>(organizations);
	}

}