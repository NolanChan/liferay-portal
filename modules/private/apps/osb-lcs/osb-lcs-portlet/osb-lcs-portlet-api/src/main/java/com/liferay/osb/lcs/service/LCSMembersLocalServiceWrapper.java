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
 * Provides a wrapper for {@link LCSMembersLocalService}.
 *
 * @author Igor Beslic
 * @see LCSMembersLocalService
 * @generated
 */
@ProviderType
public class LCSMembersLocalServiceWrapper implements LCSMembersLocalService,
	ServiceWrapper<LCSMembersLocalService> {
	public LCSMembersLocalServiceWrapper(
		LCSMembersLocalService lcsMembersLocalService) {
		_lcsMembersLocalService = lcsMembersLocalService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _lcsMembersLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Publishes a message to the LCS event queue at the portal's message bus.
	*
	* @param key the portal instance key provided by the LCS key generator
	* @param lcsEventType the event type that occurred in the portal instance
	* @since LCS 1.3
	*/
	@Override
	public void fireLCSEvent(java.lang.String key,
		com.liferay.lcs.notification.LCSEventType lcsEventType) {
		_lcsMembersLocalService.fireLCSEvent(key, lcsEventType);
	}

	@Override
	public void invalidateLCSSiteMembership(long companyId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_lcsMembersLocalService.invalidateLCSSiteMembership(companyId, userId);
	}

	@Override
	public void validateCorpProjectUsers(long corpProjectId, long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		_lcsMembersLocalService.validateCorpProjectUsers(corpProjectId, userIds);
	}

	@Override
	public void validateLCSSiteMembership(long companyId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_lcsMembersLocalService.validateLCSSiteMembership(companyId, userId);
	}

	@Override
	public LCSMembersLocalService getWrappedService() {
		return _lcsMembersLocalService;
	}

	@Override
	public void setWrappedService(LCSMembersLocalService lcsMembersLocalService) {
		_lcsMembersLocalService = lcsMembersLocalService;
	}

	private LCSMembersLocalService _lcsMembersLocalService;
}