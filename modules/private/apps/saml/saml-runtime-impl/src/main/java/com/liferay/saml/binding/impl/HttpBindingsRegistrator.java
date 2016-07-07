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

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.saml.binding.SamlBinding;

import java.util.Hashtable;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.log.Log4JLogChute;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import org.opensaml.xml.parse.ParserPool;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.framework.wiring.BundleWiring;
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

		Bundle bundle = bundleContext.getBundle();

		BundleWiring bundleWiring = bundle.adapt(BundleWiring.class);

		HttpPostBinding httpPostBinding = new HttpPostBinding(
			_parserPool, getVelocityEngine(bundleWiring.getClassLoader()));

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

	private VelocityEngine getVelocityEngine(ClassLoader classLoader) {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(classLoader);

			VelocityEngine velocityEngine = new VelocityEngine();

			velocityEngine.setProperty(
				RuntimeConstants.ENCODING_DEFAULT, StringPool.UTF8);
			velocityEngine.setProperty(
				RuntimeConstants.OUTPUT_ENCODING, StringPool.UTF8);
			velocityEngine.setProperty(
				RuntimeConstants.RESOURCE_LOADER, "classpath");
			velocityEngine.setProperty(
				RuntimeConstants.RUNTIME_LOG_LOGSYSTEM + ".log4j.category",
				"org.apache.velocity");
			velocityEngine.setProperty(
				RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS,
				Log4JLogChute.class.getName());
			velocityEngine.setProperty(
				"classpath.resource.loader.class",
				ClasspathResourceLoader.class.getName());

			velocityEngine.init();

			return velocityEngine;
		}
		catch (Exception e) {
			throw new RuntimeException(
				"Unable to initialize velocity engine", e);
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}
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

}