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

package com.liferay.portal.search.elasticsearch.marvel.web.servlet;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactory;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.GroupThreadLocal;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.search.elasticsearch.marvel.web.configuration.MarvelWebConfiguration;
import com.liferay.portal.search.elasticsearch.marvel.web.constants.MarvelPortletKeys;

import java.io.IOException;

import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.xml.bind.DatatypeConverter;

import org.apache.http.HttpRequest;

import org.mitre.dsmiley.httpproxy.ProxyServlet;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 * @author Miguel Angelo Caldas Gallindo
 * @author Artur Aquino
 * @author André de Oliveira
*/
@Component(
	configurationPid = "com.liferay.portal.search.elasticsearch.marvel.web.configuration.MarvelWebConfiguration",
	immediate = true,
	property = {
		"osgi.http.whiteboard.context.select=portal-search-elasticsearch-marvel-web",
		"osgi.http.whiteboard.servlet.name=com.liferay.portal.search.elasticsearch.marvel.web.servlet.MarvelProxyServlet",
		"osgi.http.whiteboard.servlet.pattern=/marvel-proxy/*"
	},
	service = Servlet.class
)
public class MarvelProxyServlet extends ProxyServlet {

	@Override
	public String getServletInfo() {
		return "Liferay Portal Search Elasticsearch Marvel Web Proxy Servlet";
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		replaceConfiguration(properties);
	}

	protected void checkPermission(HttpServletRequest httpServletRequest)
		throws Exception {

		User user = portal.getUser(httpServletRequest);

		if (user == null) {
			throw new PrincipalException.MustBeAuthenticated(StringPool.BLANK);
		}

		PermissionChecker permissionChecker = permissionCheckerFactory.create(
			user);

		String actionKey = ActionKeys.VIEW;
		String portletKey = MarvelPortletKeys.MARVEL_PORTLET;

		boolean hasPermission = permissionChecker.hasPermission(
			GroupThreadLocal.getGroupId(), portletKey, portletKey, actionKey);

		if (!hasPermission) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, actionKey);
		}
	}

	@Override
	protected void copyRequestHeaders(
		HttpServletRequest servletRequest, HttpRequest proxyRequest) {

		super.copyRequestHeaders(servletRequest, proxyRequest);

		proxyRequest.addHeader(
			HttpHeaders.AUTHORIZATION, getShieldAuthorization());

		proxyRequest.removeHeaders(HttpHeaders.ACCEPT_ENCODING);
	}

	@Override
	protected String getConfigParam(String key) {
		if (key.equals(ProxyServlet.P_TARGET_URI)) {
			return GetterUtil.getString(_marvelWebConfiguration.kibanaURL());
		}

		if (key.equals(ProxyServlet.P_LOG)) {
			return String.valueOf(
				_marvelWebConfiguration.proxyServletLogEnable());
		}

		return super.getConfigParam(key);
	}

	protected String getShieldAuthorization() {
		String username = GetterUtil.getString(
			_marvelWebConfiguration.shieldUserName());

		String password = GetterUtil.getString(
			_marvelWebConfiguration.shieldPassword());

		String credential = username + ":" + password;

		String base64Encoded = DatatypeConverter.printBase64Binary(
			credential.getBytes());

		return "Basic " + base64Encoded;
	}

	@Modified
	protected void modified(Map<String, Object> properties) throws Exception {
		replaceConfiguration(properties);

		init();
	}

	protected void replaceConfiguration(Map<String, Object> properties) {
		_marvelWebConfiguration = ConfigurableUtil.createConfigurable(
			MarvelWebConfiguration.class, properties);
	}

	protected void sendError(
			Exception e, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		int status = HttpServletResponse.SC_BAD_REQUEST;

		if (e instanceof PrincipalException) {
			status = HttpServletResponse.SC_FORBIDDEN;
		}

		httpServletResponse.sendError(status, e.getMessage());
	}

	@Override
	protected void service(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException, ServletException {

		try {
			checkPermission(httpServletRequest);
		}
		catch (Exception e) {
			sendError(e, httpServletRequest, httpServletResponse);

			return;
		}

		super.service(httpServletRequest, httpServletResponse);
	}

	@Reference
	protected PermissionCheckerFactory permissionCheckerFactory;

	@Reference
	protected Portal portal;

	private MarvelWebConfiguration _marvelWebConfiguration;

}