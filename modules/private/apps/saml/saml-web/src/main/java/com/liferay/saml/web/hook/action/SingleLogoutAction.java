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

package com.liferay.saml.web.hook.action;

import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.saml.runtime.profile.SingleLogoutProfile;
import com.liferay.saml.util.SamlUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Mika Koivisto
 */
@Component(
	immediate = true,
	property = {
		"path=/portal/saml/slo", "path=/portal/saml/slo_logout",
		"path=/portal/saml/slo_soap"
	},
	service = StrutsAction.class
)
public class SingleLogoutAction extends BaseSamlStrutsAction {

	@Override
	protected String doExecute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		String requestURI = request.getRequestURI();

		if (SamlUtil.isRoleIdp() && requestURI.endsWith("/slo_logout")) {
			_singleLogoutProfile.processIdpLogout(request, response);
		}
		else {
			_singleLogoutProfile.processSingleLogout(request, response);
		}

		return null;
	}

	@Reference
	private SingleLogoutProfile _singleLogoutProfile;

}