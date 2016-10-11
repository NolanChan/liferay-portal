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
 * Provides a wrapper for {@link LCSMembersService}.
 *
 * @author Igor Beslic
 * @see LCSMembersService
 * @generated
 */
@ProviderType
public class LCSMembersServiceWrapper implements LCSMembersService,
	ServiceWrapper<LCSMembersService> {
	public LCSMembersServiceWrapper(LCSMembersService lcsMembersService) {
		_lcsMembersService = lcsMembersService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _lcsMembersService.getOSGiServiceIdentifier();
	}

	/**
	* Publishes a monitoring unavailable event that targets all watchers of the
	* portal instance specified by the key.
	*
	* @param key the portal instance key provided by the LCS key generator
	* @throws PortalException if a portal exception occurred
	* @since LCS 1.3
	*/
	@Override
	public void sendMonitoringUnavailableEmail(java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		_lcsMembersService.sendMonitoringUnavailableEmail(key);
	}

	/**
	* Publishes a patching tool unavailable event that targets all watchers of
	* the portal instance specified by the key.
	*
	* @param key the portal instance key provided by the LCS key generator
	* @throws PortalException if a portal exception occurred
	* @since LCS 1.3
	*/
	@Override
	public void sendPatchingToolUnavailableEmail(java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		_lcsMembersService.sendPatchingToolUnavailableEmail(key);
	}

	/**
	* Publishes a server manually shut down event that targets all watchers of
	* the portal instance identified by the key.
	*
	* @param key the portal instance key provided by the LCS key generator
	* @throws PortalException if a portal exception occurred
	* @since LCS 1.3
	*/
	@Override
	public void sendServerManuallyShutdownEmail(java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		_lcsMembersService.sendServerManuallyShutdownEmail(key);
	}

	/**
	* Publishes a server unexpectedly shut down event that targets all watchers
	* of the portal instance specified by the key.
	*
	* @param key the portal instance key provided by the LCS key generator
	* @throws PortalException if a portal exception occurred
	* @since LCS 1.3
	*/
	@Override
	public void sendServerUnexpectedlyShutdownEmail(java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		_lcsMembersService.sendServerUnexpectedlyShutdownEmail(key);
	}

	@Override
	public LCSMembersService getWrappedService() {
		return _lcsMembersService;
	}

	@Override
	public void setWrappedService(LCSMembersService lcsMembersService) {
		_lcsMembersService = lcsMembersService;
	}

	private LCSMembersService _lcsMembersService;
}