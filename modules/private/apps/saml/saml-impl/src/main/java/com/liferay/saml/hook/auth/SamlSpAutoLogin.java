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

package com.liferay.saml.hook.auth;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auto.login.AutoLogin;
import com.liferay.portal.kernel.security.auto.login.AutoLoginException;
import com.liferay.portal.kernel.security.auto.login.BaseAutoLogin;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.saml.model.SamlSpSession;
import com.liferay.saml.runtime.profile.WebSsoProfile;
import com.liferay.saml.util.SamlUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Mika Koivisto
 */
@Component(immediate = true, service = AutoLogin.class)
public class SamlSpAutoLogin extends BaseAutoLogin {

	@Override
	protected String[] doLogin(
			HttpServletRequest request, HttpServletResponse response)
		throws AutoLoginException {

		try {
			if (!SamlUtil.isEnabled() || !SamlUtil.isRoleSp()) {
				return null;
			}

			SamlSpSession samlSpSession = _webSsoProfile.getSamlSpSession(
				request);

			if (samlSpSession == null) {
				return null;
			}

			User user = _userLocalService.fetchUser(samlSpSession.getUserId());

			if (user == null) {
				return null;
			}

			String[] credentials = new String[3];

			credentials[0] = String.valueOf(user.getUserId());
			credentials[1] = user.getPassword();
			credentials[2] = Boolean.TRUE.toString();

			return credentials;
		}
		catch (Exception e) {
			_log.warn(e, e);

			throw new AutoLoginException(e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SamlSpAutoLogin.class);

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private WebSsoProfile _webSsoProfile;

}