/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.saml.binding.impl;

import com.liferay.saml.binding.SamlBinding;
import com.liferay.saml.velocity.VelocityEngineFactory;

import java.util.Hashtable;
import java.util.Map;

import org.opensaml.xml.parse.ParserPool;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Carlos Sierra Andrés
 */
@Component(immediate = true)
public class HttpBindingsRegistrator {

	private ServiceRegistration<SamlBinding>
		_samlHttpPostBindingServiceRegistration;
	private ServiceRegistration<SamlBinding>
		_samlHttpRedirectBindingServiceRegistration;
	private ServiceRegistration<SamlBinding>
		_samlHttpSoap11BindingServiceRegistration;

	@Activate
	protected void activate(
		BundleContext bundleContext, Map<String, Object> propertiesMap) {

		Hashtable<String, Object> properties = new Hashtable<>(propertiesMap);

		HttpPostBinding httpPostBinding = new HttpPostBinding(
			_parserPool, _velocityEngineFactory.getVelocityEngine());

		HttpRedirectBinding httpRedirectBinding = new HttpRedirectBinding(
			_parserPool);

		HttpSoap11Binding httpSoap11Binding = new HttpSoap11Binding(
			_parserPool, _httpClient);

		_samlHttpPostBindingServiceRegistration = bundleContext.registerService(
			SamlBinding.class, httpPostBinding, properties);

		_samlHttpRedirectBindingServiceRegistration =
			bundleContext.registerService(
				SamlBinding.class, httpRedirectBinding, properties);

		_samlHttpSoap11BindingServiceRegistration =
			bundleContext.registerService(
				SamlBinding.class, httpSoap11Binding, properties);
	}

	@Deactivate
	protected void deactivate() {
		_samlHttpPostBindingServiceRegistration.unregister();

		_samlHttpRedirectBindingServiceRegistration.unregister();

		_samlHttpSoap11BindingServiceRegistration.unregister();
	}

	@Reference
	org.apache.commons.httpclient.HttpClient _httpClient;

	@Reference ParserPool _parserPool;

	@Reference VelocityEngineFactory _velocityEngineFactory;
}