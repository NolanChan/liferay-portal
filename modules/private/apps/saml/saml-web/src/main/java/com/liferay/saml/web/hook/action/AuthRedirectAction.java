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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.saml.util.SamlUtil;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Tomas Polesovsky
 */
@Component(
	immediate = true, property = {"path=/portal/saml/auth_redirect"},
	service = StrutsAction.class
)
public class AuthRedirectAction extends BaseSamlStrutsAction {

	@Override
	public boolean isEnabled() {
		if (SamlUtil.isEnabled() && SamlUtil.isRoleSp()) {
			return true;
		}

		return false;
	}

	@Override
	protected String doExecute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		String redirect = ParamUtil.getString(request, "redirect");

		redirect = PortalUtil.escapeRedirect(redirect);

		if (Validator.isNull(redirect)) {
			redirect = PortalUtil.getHomeURL(request);
		}

		try {
			response.sendRedirect(redirect);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}

		return null;
	}

}