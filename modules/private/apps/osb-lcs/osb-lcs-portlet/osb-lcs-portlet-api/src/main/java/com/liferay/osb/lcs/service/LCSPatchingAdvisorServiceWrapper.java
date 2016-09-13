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
 * Provides a wrapper for {@link LCSPatchingAdvisorService}.
 *
 * @author Igor Beslic
 * @see LCSPatchingAdvisorService
 * @generated
 */
@ProviderType
public class LCSPatchingAdvisorServiceWrapper
	implements LCSPatchingAdvisorService,
		ServiceWrapper<LCSPatchingAdvisorService> {
	public LCSPatchingAdvisorServiceWrapper(
		LCSPatchingAdvisorService lcsPatchingAdvisorService) {
		_lcsPatchingAdvisorService = lcsPatchingAdvisorService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _lcsPatchingAdvisorService.getOSGiServiceIdentifier();
	}

	@Override
	public LCSPatchingAdvisorService getWrappedService() {
		return _lcsPatchingAdvisorService;
	}

	@Override
	public void setWrappedService(
		LCSPatchingAdvisorService lcsPatchingAdvisorService) {
		_lcsPatchingAdvisorService = lcsPatchingAdvisorService;
	}

	private LCSPatchingAdvisorService _lcsPatchingAdvisorService;
}