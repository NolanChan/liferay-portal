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

package com.liferay.websocket.whiteboard;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.ServletContext;

import javax.websocket.Endpoint;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceObjects;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.log.LogService;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Cristina González
 * @author Manuel de la Peña
 */
@Component(immediate = true)
public class WebSocketEndpointTracker
	implements ServiceTrackerCustomizer<Endpoint, ServerEndpointConfigWrapper> {

	@Override
	public ServerEndpointConfigWrapper addingService(
		ServiceReference<Endpoint> serviceReference) {

		String path = (String)serviceReference.getProperty(_WEBSOCKET_PATH);

		if ((path == null) || path.isEmpty()) {
			return null;
		}

		final ServiceObjects<Endpoint> serviceObjects =
			_bundleContext.getServiceObjects(serviceReference);

		ServerEndpointConfigWrapper serverEndpointConfig =
			_webSocketEndpointRegistrations.get(path);

		if (serverEndpointConfig == null) {
			serverEndpointConfig = new ServerEndpointConfigWrapper(path, _log);
		}

		serverEndpointConfig.setConfigurator(
			serviceReference,
			new ServiceObjectsConfigurator(serviceObjects, _log));

		return serverEndpointConfig;
	}

	@Override
	public void modifiedService(
		ServiceReference<Endpoint> serviceReference,
		ServerEndpointConfigWrapper endpointWrapper) {

		removedService(serviceReference, endpointWrapper);

		addingService(serviceReference);
	}

	@Override
	public void removedService(
		ServiceReference<Endpoint> serviceReference,
		ServerEndpointConfigWrapper serverEndpointConfig) {

	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		_webSocketEndpointServiceTracker = new ServiceTracker<>(
			bundleContext, Endpoint.class, this);

		_webSocketEndpointServiceTracker.open();
	}

	@Deactivate
	protected void deactivate() {
		_webSocketEndpointServiceTracker.close();
	}

	private static final String _WEBSOCKET_PATH =
		"org.osgi.http.websocket.endpoint.path";

	private BundleContext _bundleContext;

	@Reference
	private LogService _log;

	private final ConcurrentMap<String, ServerEndpointConfigWrapper>
		_webSocketEndpointRegistrations = new ConcurrentHashMap<>();
	private ServiceTracker<Endpoint, ServerEndpointConfigWrapper>
		_webSocketEndpointServiceTracker;

}