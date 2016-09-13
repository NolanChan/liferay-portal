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
 * Provides a wrapper for {@link LCSProjectService}.
 *
 * @author Igor Beslic
 * @see LCSProjectService
 * @generated
 */
@ProviderType
public class LCSProjectServiceWrapper implements LCSProjectService,
	ServiceWrapper<LCSProjectService> {
	public LCSProjectServiceWrapper(LCSProjectService lcsProjectService) {
		_lcsProjectService = lcsProjectService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _lcsProjectService.getOSGiServiceIdentifier();
	}

	@Override
	public LCSProjectService getWrappedService() {
		return _lcsProjectService;
	}

	@Override
	public void setWrappedService(LCSProjectService lcsProjectService) {
		_lcsProjectService = lcsProjectService;
	}

	private LCSProjectService _lcsProjectService;
}