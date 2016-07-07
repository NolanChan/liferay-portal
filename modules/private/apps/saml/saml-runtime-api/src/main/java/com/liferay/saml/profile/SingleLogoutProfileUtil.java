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
public class SingleLogoutProfileUtil {

	public static SamlSpSession getSamlSpSession(HttpServletRequest request) {
		return getSingleLogoutProfile().getSamlSpSession(request);
	}

	public static SingleLogoutProfile getSingleLogoutProfile() {
		return _singleLogoutProfile;
	}

	public static boolean isSingleLogoutSupported(HttpServletRequest request) {
		return getSingleLogoutProfile().isSingleLogoutSupported(request);
	}

	public static void logout(
		HttpServletRequest request, HttpServletResponse response) {

		getSingleLogoutProfile().logout(request, response);
	}

	public static void processIdpLogout(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException {

		getSingleLogoutProfile().processIdpLogout(request, response);
	}

	public static void processSingleLogout(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException {

		getSingleLogoutProfile().processSingleLogout(request, response);
	}

	public static void processSpLogout(
			HttpServletRequest request, HttpServletResponse response)
		throws PortalException {

		getSingleLogoutProfile().processSpLogout(request, response);
	}

	public static void terminateSpSession(
		HttpServletRequest request, HttpServletResponse response) {

		getSingleLogoutProfile().terminateSpSession(request, response);
	}

	public static void terminateSsoSession(
		HttpServletRequest request, HttpServletResponse response) {

		getSingleLogoutProfile().terminateSsoSession(request, response);
	}

	@Reference(
		cardinality = ReferenceCardinality.OPTIONAL,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	public void setSingleLogoutProfile(
		SingleLogoutProfile singleLogoutProfile) {

		_singleLogoutProfile = singleLogoutProfile;
	}

	public void unsetSingleLogoutProfile(
		SingleLogoutProfile singleLogoutProfile) {

		_singleLogoutProfile = null;
	}

	private static SingleLogoutProfile _singleLogoutProfile;

}