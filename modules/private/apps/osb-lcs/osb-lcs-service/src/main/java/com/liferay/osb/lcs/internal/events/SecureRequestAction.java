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

package com.liferay.osb.lcs.internal.events;

import com.liferay.osb.lcs.configuration.OSBLCSConfiguration;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

/**
 * @author Ivica Cardic
 */
@Component(
	configurationPid = "com.liferay.osb.lcs.configuration.OSBLCSConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true,
	property = {"key=servlet.service.events.pre"},
	service = LifecycleAction.class
)
public class SecureRequestAction implements LifecycleAction {

	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent)
		throws ActionException {

		HttpServletRequest request = lifecycleEvent.getRequest();

		try {
			if (request.isSecure()) {
				return;
			}

			// Amazon ELB specific header

			String forwardedProto = request.getHeader("X-Forwarded-Proto");

			if ((forwardedProto != null) && forwardedProto.equals(Http.HTTPS)) {
				return;
			}

			if (!isRequiresSecure(request)) {
				return;
			}

			HttpServletResponse response = lifecycleEvent.getResponse();

			if (response.isCommitted()) {
				return;
			}

			String redirect = getRedirect(request);

			if (_log.isDebugEnabled()) {
				_log.debug("Redirect " + redirect);
			}

			if (redirect != null) {
				response.sendRedirect(redirect);
			}
		}
		catch (Exception e) {
			throw new ActionException(e);
		}
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_osbLCSConfiguration = ConfigurableUtil.createConfigurable(
			OSBLCSConfiguration.class, properties);
	}

	protected String getRedirect(HttpServletRequest request) {
		String unsecureCompleteURL = PortalUtil.getCurrentCompleteURL(request);

		if (_log.isDebugEnabled()) {
			_log.debug("Unsecure URL " + unsecureCompleteURL);
		}

		String secureCompleteURL = StringUtil.replaceFirst(
			unsecureCompleteURL, Http.HTTP_WITH_SLASH, Http.HTTPS_WITH_SLASH);

		if (_log.isDebugEnabled()) {
			_log.debug("Secure URL " + secureCompleteURL);
		}

		if (unsecureCompleteURL.equals(secureCompleteURL)) {
			return null;
		}
		else {
			return secureCompleteURL;
		}
	}

	protected boolean isRequiresSecure(HttpServletRequest request) {
		StringBuffer sb = request.getRequestURL();

		String requestURL = sb.toString();

		boolean securedDomain = false;

		for (String domain :
				_osbLCSConfiguration.osbLcsPortalSecuredDomains()) {

			if (requestURL.contains(domain)) {
				securedDomain = true;

				break;
			}
		}

		if (!securedDomain) {
			return false;
		}

		for (String path : _osbLCSConfiguration.osbLcsPortalUnsecuredPaths()) {
			if (requestURL.contains(path)) {
				return false;
			}
		}

		return _REQUIRES_SECURE;
	}

	private static final boolean _REQUIRES_SECURE = true;

	private static final Log _log = LogFactoryUtil.getLog(
		SecureRequestAction.class);

	private static volatile OSBLCSConfiguration _osbLCSConfiguration;

}