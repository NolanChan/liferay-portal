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

package com.liferay.osb.lcs.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UserLCSMessageService}.
 *
 * @author Igor Beslic
 * @see UserLCSMessageService
 * @generated
 */
@ProviderType
public class UserLCSMessageServiceWrapper implements UserLCSMessageService,
	ServiceWrapper<UserLCSMessageService> {
	public UserLCSMessageServiceWrapper(
		UserLCSMessageService userLCSMessageService) {
		_userLCSMessageService = userLCSMessageService;
	}

	@Override
	public com.liferay.osb.lcs.model.UserLCSMessage deleteUserLCSMessage(
		long userLCSMessageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userLCSMessageService.deleteUserLCSMessage(userLCSMessageId);
	}

	@Override
	public com.liferay.osb.lcs.model.UserLCSMessage updateRead(
		long userLCSMessageId, boolean read)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userLCSMessageService.updateRead(userLCSMessageId, read);
	}

	@Override
	public int getUserLCSMessagesCount()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userLCSMessageService.getUserLCSMessagesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _userLCSMessageService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.UserLCSMessage> getUserLCSMessages(
		boolean hidden)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userLCSMessageService.getUserLCSMessages(hidden);
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.UserLCSMessage> getUserLCSMessages(
		int max) throws com.liferay.portal.kernel.exception.PortalException {
		return _userLCSMessageService.getUserLCSMessages(max);
	}

	@Override
	public void deleteUserLCSMessages()
		throws com.liferay.portal.kernel.exception.PortalException {
		_userLCSMessageService.deleteUserLCSMessages();
	}

	@Override
	public UserLCSMessageService getWrappedService() {
		return _userLCSMessageService;
	}

	@Override
	public void setWrappedService(UserLCSMessageService userLCSMessageService) {
		_userLCSMessageService = userLCSMessageService;
	}

	private UserLCSMessageService _userLCSMessageService;
}