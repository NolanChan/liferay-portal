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
import com.liferay.osb.lcs.constants.OSBPortletConstants;
import com.liferay.osb.lcs.osbportlet.OSBPortletServiceProxy;
import com.liferay.osb.lcs.service.base.LCSMembersLocalServiceBaseImpl;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Beslic
 * @see    LCSMembersLocalServiceBaseImpl
 * @see    com.liferay.osb.lcs.service.LCSMembersLocalServiceUtil
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

		Group group = _groupLocalService.getGroup(
			companyId, GroupConstants.GUEST);

		if (_groupLocalService.hasUserGroup(userId, group.getGroupId())) {
			_groupLocalService.unsetUserGroups(
				userId, new long[] {group.getGroupId()});
		}
	}

	@Override
	public void validateCorpProjectUsers(long corpProjectId, long[] userIds)
		throws PortalException {

		List<Long> noCorpProjectUserIds = new ArrayList<>();
		List<Long> noCorpProjectRoleUserIds = new ArrayList<>();

		for (long userId : userIds) {
			if (!_osbPortletServiceProxy.hasUserCorpProject(
					userId, corpProjectId)) {

				noCorpProjectUserIds.add(userId);

				continue;
			}

			if (_osbPortletServiceProxy.hasUserCorpProjectRole(
					userId, corpProjectId,
					OSBPortletConstants.ROLE_OSB_CORP_LCS_USER)) {

				continue;
			}

			noCorpProjectRoleUserIds.add(userId);
		}

		_osbPortletServiceProxy.addCorpProjectUsers(
			corpProjectId, ArrayUtil.toLongArray(noCorpProjectUserIds));

		noCorpProjectRoleUserIds.addAll(noCorpProjectUserIds);

		_osbPortletServiceProxy.addUserCorpProjectRoles(
			corpProjectId, ArrayUtil.toLongArray(noCorpProjectRoleUserIds),
			OSBPortletConstants.ROLE_OSB_CORP_LCS_USER);
	}

	@Override
	public void validateLCSSiteMembership(long companyId, long userId)
		throws PortalException {

		Group group = _groupLocalService.getGroup(
			companyId, GroupConstants.GUEST);

		Role role = _roleLocalService.getRole(
			group.getCompanyId(), RoleConstants.SITE_MEMBER);

		if (_userGroupRoleLocalService.hasUserGroupRole(
				userId, group.getGroupId(), role.getRoleId())) {

			return;
		}

		_userGroupRoleLocalService.addUserGroupRoles(
			userId, group.getGroupId(), new long[] {role.getRoleId()});

		_userLocalService.addGroupUsers(
			group.getGroupId(), new long[] {userId});
	}

	@BeanReference(type = GroupLocalService.class)
	private GroupLocalService _groupLocalService;

	@ServiceReference(type = OSBPortletServiceProxy.class)
	private OSBPortletServiceProxy _osbPortletServiceProxy;

	@BeanReference(type = RoleLocalService.class)
	private RoleLocalService _roleLocalService;

	@BeanReference(type = UserGroupRoleLocalService.class)
	private UserGroupRoleLocalService _userGroupRoleLocalService;

	@BeanReference(type = UserLocalService.class)
	private UserLocalService _userLocalService;

}