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

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Mika Koivisto
 */
@Component(immediate = true)
public class WebSsoProfileUtil {

	public static SamlSpSession getSamlSpSession(HttpServletRequest request) {
		return getWebSsoProfile().getSamlSpSession(request);
	}

	public static WebSsoProfile getWebSsoProfile() {
		return _webSsoProfile;
	}

	public static void processAuthnRequest(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException {

		getWebSsoProfile().processAuthnRequest(request, response);
	}

	public static void processResponse(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException {

		getWebSsoProfile().processResponse(request, response);
	}

	public static void sendAuthnRequest(
			HttpServletRequest request, HttpServletResponse response,
			String relayState)
		throws PortalException {

		getWebSsoProfile().sendAuthnRequest(request, response, relayState);
	}

	public static void updateSamlSpSession(
		HttpServletRequest request, HttpServletResponse response) {

		getWebSsoProfile().updateSamlSpSession(request, response);
	}

	@Reference(
		cardinality = ReferenceCardinality.OPTIONAL,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	public void setWebSsoProfile(WebSsoProfile webSsoProfile) {
		_webSsoProfile = webSsoProfile;
	}

	public void unsetWebSsoProfile(WebSsoProfile webSsoProfile) {
		_webSsoProfile = null;
	}

	private static WebSsoProfile _webSsoProfile;

}