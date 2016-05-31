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
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.search.elasticsearch.marvel.web.configuration.MarvelWebConfiguration;

import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;

import org.mitre.dsmiley.httpproxy.ProxyServlet;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

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

	@Override
	protected void copyRequestHeaders(
		HttpServletRequest servletRequest, HttpRequest proxyRequest) {

		super.copyRequestHeaders(servletRequest, proxyRequest);

		proxyRequest.removeHeaders(HttpHeaders.ACCEPT_ENCODING);
	}

	@Override
	protected String getConfigParam(String key) {
		if (key.equals(ProxyServlet.P_TARGET_URI)) {
			return _marvelWebConfiguration.kibanaURL();
		}

		if (key.equals(ProxyServlet.P_LOG)) {
			return String.valueOf(
				_marvelWebConfiguration.proxyServletLogEnable());
		}

		return super.getConfigParam(key);
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

	private MarvelWebConfiguration _marvelWebConfiguration;

}