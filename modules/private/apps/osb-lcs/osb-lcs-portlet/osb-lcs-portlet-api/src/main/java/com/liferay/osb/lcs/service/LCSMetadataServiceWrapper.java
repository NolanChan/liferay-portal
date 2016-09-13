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
 * Provides a wrapper for {@link LCSMetadataService}.
 *
 * @author Igor Beslic
 * @see LCSMetadataService
 * @generated
 */
@ProviderType
public class LCSMetadataServiceWrapper implements LCSMetadataService,
	ServiceWrapper<LCSMetadataService> {
	public LCSMetadataServiceWrapper(LCSMetadataService lcsMetadataService) {
		_lcsMetadataService = lcsMetadataService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _lcsMetadataService.getOSGiServiceIdentifier();
	}

	@Override
	public LCSMetadataService getWrappedService() {
		return _lcsMetadataService;
	}

	@Override
	public void setWrappedService(LCSMetadataService lcsMetadataService) {
		_lcsMetadataService = lcsMetadataService;
	}

	private LCSMetadataService _lcsMetadataService;
}