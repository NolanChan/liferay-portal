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

package com.liferay.saml.hook.filter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.util.CookieKeys;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.saml.profile.SingleLogoutProfile;
import com.liferay.saml.util.PortletWebKeys;
import com.liferay.saml.util.SamlUtil;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
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
		"after-filter=Virtual Host Filter", "dispatcher=FORWARD",
		"dispatcher=REQUEST", "servlet-context-name=",
		"servlet-filter-name=SSO SAML IdP Filter",
		"url-pattern=/c/portal/logout"
	},
	service = Filter.class
)
public class SamlIdpSsoFilter extends BaseFilter {

	@Override
	public boolean isFilterEnabled() {
		if (SamlUtil.isEnabled() && SamlUtil.isRoleIdp()) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isFilterEnabled(
		HttpServletRequest request, HttpServletResponse response) {

		if (!SamlUtil.isEnabled() || !SamlUtil.isRoleIdp()) {
			return false;
		}

		try {
			User user = PortalUtil.getUser(request);

			if (user != null) {
				return true;
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		String requestPath = SamlUtil.getRequestPath(request);

		if (requestPath.equals("/c/portal/logout")) {
			return true;
		}

		return false;
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		String requestPath = SamlUtil.getRequestPath(request);

		if (requestPath.equals("/c/portal/logout")) {
			String samlSsoSessionId = CookieKeys.getCookie(
				request, PortletWebKeys.SAML_SSO_SESSION_ID);

			if (Validator.isNotNull(samlSsoSessionId)) {
				_singleLogoutProfile.processIdpLogout(request, response);
			}
			else {
				filterChain.doFilter(request, response);
			}
		}
		else {
			filterChain.doFilter(request, response);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SamlIdpSsoFilter.class);

	@Reference
	private SingleLogoutProfile _singleLogoutProfile;

}