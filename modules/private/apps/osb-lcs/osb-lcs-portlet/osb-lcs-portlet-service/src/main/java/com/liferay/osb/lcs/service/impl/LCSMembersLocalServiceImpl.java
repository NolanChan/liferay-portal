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

package com.liferay.osb.lcs.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.lcs.notification.LCSEventType;
import com.liferay.osb.lcs.osbportlet.service.OSBPortletServiceUtil;
import com.liferay.osb.lcs.osbportlet.util.OSBPortletUtil;
import com.liferay.osb.lcs.service.base.LCSMembersLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Beslic
 * @see LCSMembersLocalServiceBaseImpl
 * @see com.liferay.osb.lcs.service.LCSMembersLocalServiceUtil
 */
@ProviderType
public class LCSMembersLocalServiceImpl extends LCSMembersLocalServiceBaseImpl {

	/**
	 * Publishes a message to the LCS event queue at the portal's message bus.
	 *
	 * @param key the portal instance key provided by the LCS key generator
	 * @param lcsEventType the event type that occurred in the portal instance
	 * @since LCS 1.3
	 */
	@Override
	public void fireLCSEvent(String key, LCSEventType lcsEventType) {
		Message message = new Message();

		message.put("key", key);

		message.setPayload(lcsEventType);

		MessageBusUtil.sendMessage("liferay/osb_lcs_events", message);
	}

	@Override
	public void invalidateLCSSiteMembership(long companyId, long userId)
		throws PortalException {

		Group group = GroupLocalServiceUtil.getGroup(
			companyId, GroupConstants.GUEST);

		if (GroupLocalServiceUtil.hasUserGroup(userId, group.getGroupId())) {
			GroupLocalServiceUtil.unsetUserGroups(
				userId, new long[] {group.getGroupId()});
		}
	}

	@Override
	public void validateCorpProjectUsers(long corpProjectId, long[] userIds)
		throws PortalException {

		List<Long> noCorpProjectUserIds = new ArrayList<>();
		List<Long> noCorpProjectRoleUserIds = new ArrayList<>();

		for (long userId : userIds) {
			if (!OSBPortletServiceUtil.hasUserCorpProject(
				userId, corpProjectId)) {

				noCorpProjectUserIds.add(userId);

				continue;
			}

			if (OSBPortletServiceUtil.hasUserCorpProjectRole(
					userId, corpProjectId,
					OSBPortletUtil.ROLE_OSB_CORP_LCS_USER)) {

				continue;
			}

			noCorpProjectRoleUserIds.add(userId);
		}

		OSBPortletServiceUtil.addCorpProjectUsers(
			corpProjectId, ArrayUtil.toLongArray(noCorpProjectUserIds));

		noCorpProjectRoleUserIds.addAll(noCorpProjectUserIds);

		OSBPortletServiceUtil.addUserCorpProjectRoles(
			corpProjectId, ArrayUtil.toLongArray(noCorpProjectRoleUserIds),
			OSBPortletUtil.ROLE_OSB_CORP_LCS_USER);
	}

	@Override
	public void validateLCSSiteMembership(long companyId, long userId)
		throws PortalException {

		Group group = GroupLocalServiceUtil.getGroup(
			companyId, GroupConstants.GUEST);

		Role role = RoleLocalServiceUtil.getRole(
			group.getCompanyId(), RoleConstants.SITE_MEMBER);

		if (UserGroupRoleLocalServiceUtil.hasUserGroupRole(
			userId, group.getGroupId(), role.getRoleId())) {

			return;
		}

		UserGroupRoleLocalServiceUtil.addUserGroupRoles(
			userId, group.getGroupId(), new long[] {role.getRoleId()});

		UserLocalServiceUtil.addGroupUsers(
			group.getGroupId(), new long[]{userId});
	}

}