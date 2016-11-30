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
 * Provides a wrapper for {@link LCSClusterEntryTokenService}.
 *
 * @author Igor Beslic
 * @see LCSClusterEntryTokenService
 * @generated
 */
@ProviderType
public class LCSClusterEntryTokenServiceWrapper
	implements LCSClusterEntryTokenService,
		ServiceWrapper<LCSClusterEntryTokenService> {
	public LCSClusterEntryTokenServiceWrapper(
		LCSClusterEntryTokenService lcsClusterEntryTokenService) {
		_lcsClusterEntryTokenService = lcsClusterEntryTokenService;
	}

	@Override
	public boolean isValid(long lcsClusterEntryTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryTokenService.isValid(lcsClusterEntryTokenId);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntryToken addLCSClusterEntryToken(
		long lcsClusterEntryId,
		java.util.Map<java.lang.String, java.lang.String> lcsServicesConfiguration)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryTokenService.addLCSClusterEntryToken(lcsClusterEntryId,
			lcsServicesConfiguration);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntryToken deleteLCSClusterEntryToken(
		long lcsClusterEntryTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryTokenService.deleteLCSClusterEntryToken(lcsClusterEntryTokenId);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntryToken fetchLCSClusterEntryLCSClusterEntryToken(
		long lcsClusterEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryTokenService.fetchLCSClusterEntryLCSClusterEntryToken(lcsClusterEntryId);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntryToken fetchLCSClusterEntryToken(
		long lcsClusterEntryTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryTokenService.fetchLCSClusterEntryToken(lcsClusterEntryTokenId);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntryToken regenerateLCSClusterEntryToken(
		long lcsClusterEntryId,
		java.util.Map<java.lang.String, java.lang.String> lcsServicesConfiguration)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryTokenService.regenerateLCSClusterEntryToken(lcsClusterEntryId,
			lcsServicesConfiguration);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _lcsClusterEntryTokenService.getOSGiServiceIdentifier();
	}

	@Override
	public LCSClusterEntryTokenService getWrappedService() {
		return _lcsClusterEntryTokenService;
	}

	@Override
	public void setWrappedService(
		LCSClusterEntryTokenService lcsClusterEntryTokenService) {
		_lcsClusterEntryTokenService = lcsClusterEntryTokenService;
	}

	private LCSClusterEntryTokenService _lcsClusterEntryTokenService;
}