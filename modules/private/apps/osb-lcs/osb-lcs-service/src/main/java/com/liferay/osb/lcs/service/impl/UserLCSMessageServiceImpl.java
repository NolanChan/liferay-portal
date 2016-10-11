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

import com.liferay.osb.lcs.constants.OSBLCSActionKeys;
import com.liferay.osb.lcs.model.UserLCSMessage;
import com.liferay.osb.lcs.service.base.UserLCSMessageServiceBaseImpl;
import com.liferay.osb.lcs.service.permission.UserLCSMessagePermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import java.util.List;

/**
 * @author  Igor Beslic
 * @version LCS 1.7.1
 * @since   LCS 1.6
 */
@ProviderType
public class UserLCSMessageServiceImpl extends UserLCSMessageServiceBaseImpl {

	@Override
	public UserLCSMessage deleteUserLCSMessage(long userLCSMessageId)
		throws PortalException {

		UserLCSMessagePermission.check(
			getPermissionChecker(), userLCSMessageId, OSBLCSActionKeys.MANAGE);

		return
			userLCSMessageLocalService.deleteUserLCSMessage(userLCSMessageId);
	}

	@Override
	public void deleteUserLCSMessages() throws PortalException {
		PermissionChecker permissionChecker = getPermissionChecker();

		if (!permissionChecker.isSignedIn()) {
			throw new PrincipalException();
		}

		userLCSMessageLocalService.deleteUserLCSMessages(getUserId());
	}

	@Override
	public List<UserLCSMessage> getUserLCSMessages(boolean hidden)
		throws PortalException {

		PermissionChecker permissionChecker = getPermissionChecker();

		if (!permissionChecker.isSignedIn()) {
			throw new PrincipalException();
		}

		return userLCSMessageLocalService.getUserLCSMessages(
			getUserId(), hidden);
	}

	@Override
	public List<UserLCSMessage> getUserLCSMessages(int max)
		throws PortalException {

		PermissionChecker permissionChecker = getPermissionChecker();

		if (!permissionChecker.isSignedIn()) {
			throw new PrincipalException();
		}

		return userLCSMessageLocalService.getUserLCSMessages(getUserId(), max);
	}

	@Override
	public int getUserLCSMessagesCount() throws PortalException {
		PermissionChecker permissionChecker = getPermissionChecker();

		if (!permissionChecker.isSignedIn()) {
			throw new PrincipalException();
		}

		return userLCSMessageLocalService.getUserLCSMessagesCount(getUserId());
	}

	@Override
	public UserLCSMessage updateRead(long userLCSMessageId, boolean read)
		throws PortalException {

		UserLCSMessagePermission.check(
			getPermissionChecker(), userLCSMessageId, OSBLCSActionKeys.MANAGE);

		return userLCSMessageLocalService.updateRead(userLCSMessageId, read);
	}

}