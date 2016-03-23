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

package com.liferay.portal.rules.engine.drools;

import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationConfiguration;
import com.liferay.portal.kernel.messaging.DestinationFactory;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.proxy.ProxyMessageListener;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.rules.engine.RulesEngine;

import java.util.Dictionary;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 */
@Component(immediate = true, service = RulesEngineMessagingConfigurator.class)
public class RulesEngineMessagingConfigurator {

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		registerRulesEngineDestination();

		wireRulesEngineMessageListener();
	}

	@Deactivate
	protected void deactivate() {
		unregisterRulesEngineDestination();

		_bundleContext = null;
	}

	protected void registerDestination(
		DestinationConfiguration kaleoGraphWalkerDestinationConfiguration) {

		Destination destination = _destinationFactory.createDestination(
			kaleoGraphWalkerDestinationConfiguration);

		Dictionary<String, Object> properties = new HashMapDictionary<>();

		properties.put("destination.name", destination.getName());

		_rulesEngineServiceRegistration = _bundleContext.registerService(
			Destination.class, destination, properties);
	}

	protected MessageListener registerProxyMessageListener(
		Object manager, String destinationName) {

		ProxyMessageListener proxyMessageListener = new ProxyMessageListener();

		proxyMessageListener.setManager(manager);
		proxyMessageListener.setMessageBus(_messageBus);

		_messageBus.registerMessageListener(
			destinationName, proxyMessageListener);

		return proxyMessageListener;
	}

	protected void registerRulesEngineDestination() {
		DestinationConfiguration destinationConfiguration =
			new DestinationConfiguration(
				DestinationConfiguration.DESTINATION_TYPE_SYNCHRONOUS,
				"liferay/rules_engine");

		registerDestination(destinationConfiguration);
	}

	protected void unregisterRulesEngineDestination() {
		if (_rulesEngineServiceRegistration == null) {
			return;
		}

		Destination destination = _bundleContext.getService(
			_rulesEngineServiceRegistration.getReference());

		_rulesEngineServiceRegistration.unregister();

		destination.destroy();
	}

	protected void wireRulesEngineMessageListener() {
		registerProxyMessageListener(_rulesEngine, "liferay/rules_engine");
	}

	private BundleContext _bundleContext;

	@Reference
	private DestinationFactory _destinationFactory;

	@Reference
	private MessageBus _messageBus;

	@Reference(target = "(proxy.bean=false)")
	private RulesEngine _rulesEngine;

	private ServiceRegistration<Destination> _rulesEngineServiceRegistration;

}