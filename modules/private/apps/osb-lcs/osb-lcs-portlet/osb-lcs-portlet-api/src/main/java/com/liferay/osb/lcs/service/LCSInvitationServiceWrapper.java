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
 * Provides a wrapper for {@link LCSInvitationService}.
 *
 * @author Igor Beslic
 * @see LCSInvitationService
 * @generated
 */
@ProviderType
public class LCSInvitationServiceWrapper implements LCSInvitationService,
	ServiceWrapper<LCSInvitationService> {
	public LCSInvitationServiceWrapper(
		LCSInvitationService lcsInvitationService) {
		_lcsInvitationService = lcsInvitationService;
	}

	@Override
	public com.liferay.osb.lcs.model.LCSInvitation addLCSInvitation(
		long lcsProjectId, java.lang.String emailAddress,
		long lcsClusterEntryId, int role)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsInvitationService.addLCSInvitation(lcsProjectId,
			emailAddress, lcsClusterEntryId, role);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSInvitation deleteLCSInvitation(
		long lcsInvitationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsInvitationService.deleteLCSInvitation(lcsInvitationId);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSInvitation getLCSProjectLCSInvitation(
		long lcsProjectId, java.lang.String emailAddress)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsInvitationService.getLCSProjectLCSInvitation(lcsProjectId,
			emailAddress);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _lcsInvitationService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSInvitation> getLCSProjectLCSInvitations(
		long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsInvitationService.getLCSProjectLCSInvitations(lcsProjectId);
	}

	@Override
	public LCSInvitationService getWrappedService() {
		return _lcsInvitationService;
	}

	@Override
	public void setWrappedService(LCSInvitationService lcsInvitationService) {
		_lcsInvitationService = lcsInvitationService;
	}

	private LCSInvitationService _lcsInvitationService;
}