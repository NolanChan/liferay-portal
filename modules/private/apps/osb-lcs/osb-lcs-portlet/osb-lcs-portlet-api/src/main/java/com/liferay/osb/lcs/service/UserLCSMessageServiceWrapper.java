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
	public UserLCSMessageService getWrappedService() {
		return _userLCSMessageService;
	}

	@Override
	public void setWrappedService(UserLCSMessageService userLCSMessageService) {
		_userLCSMessageService = userLCSMessageService;
	}

	private UserLCSMessageService _userLCSMessageService;
}