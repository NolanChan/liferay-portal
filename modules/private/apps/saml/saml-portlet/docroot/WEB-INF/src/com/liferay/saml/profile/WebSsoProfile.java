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

package com.liferay.saml.profile;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.saml.model.SamlSpSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mika Koivisto
 */
public interface WebSsoProfile {

	public SamlSpSession getSamlSpSession(HttpServletRequest request);

	public void processAuthnRequest(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException;

	public void processResponse(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException;

	public void sendAuthnRequest(
			HttpServletRequest request, HttpServletResponse response,
			String relayState)
		throws PortalException;

	public void updateSamlSpSession(
		HttpServletRequest request, HttpServletResponse httpServletResponse);

}