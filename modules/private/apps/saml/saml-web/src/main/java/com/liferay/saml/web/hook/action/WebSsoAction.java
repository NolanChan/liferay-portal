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
import com.liferay.saml.profile.WebSsoProfile;
import com.liferay.saml.profile.WebSsoProfileUtil;
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
		"path=/portal/saml/sso"
	},
	service = StrutsAction.class
)
public class WebSsoAction extends BaseSamlStrutsAction {

	@Override
	public boolean isEnabled() {
		if (SamlUtil.isEnabled() && SamlUtil.isRoleIdp()) {
			return true;
		}

		return false;
	}

	@Override
	protected String doExecute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		_webSsoProfile.processAuthnRequest(request, response);

		return null;
	}

	@Reference(unbind = "-")
	private WebSsoProfile _webSsoProfile;

}