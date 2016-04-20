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

package com.liferay.portal.reports.internal;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.concurrent.CallerRunsPolicy;
import com.liferay.portal.kernel.concurrent.RejectedExecutionHandler;
import com.liferay.portal.kernel.concurrent.ThreadPoolExecutor;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationConfiguration;
import com.liferay.portal.kernel.messaging.DestinationFactory;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.reports.admin.messaging.AdminMessageListener;
import com.liferay.portal.reports.configuration.ReportsPortletMessagingConfiguration;
import com.liferay.portal.reports.messaging.DestinationNames;
import com.liferay.portal.reports.messaging.SchedulerEventMessageListener;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Prathima Shreenath
 */
@Component(
	configurationPid = "com.liferay.portal.reports.configuration.ReportsPortletMessagingConfiguration",
	immediate = true, service = ReportsPortletMessagingConfigurator.class
)
public class ReportsPortletMessagingConfigurator {

	@Activate
	protected void activate(ComponentContext componentContext) {
		_bundleContext = componentContext.getBundleContext();

		Dictionary<String, Object> properties =
			componentContext.getProperties();

		_reportsPortletMessagingConfiguration =
			ConfigurableUtil.createConfigurable(
				ReportsPortletMessagingConfiguration.class, properties);

		_registerReportsAdminDestination();

		_registerReportsSchedulerEventDestination();
	}

	@Deactivate
	protected void deactivate() {
		if (!_destinationServiceRegistrations.isEmpty()) {
			for (ServiceRegistration<Destination> destinationServiceRegistration
					: _destinationServiceRegistrations) {

				Destination destination = _bundleContext.getService(
					destinationServiceRegistration.getReference());

				destinationServiceRegistration.unregister();

				destination.destroy();
			}
		}

		if (!_messageListenerServiceRegistrations.isEmpty()) {
			for (ServiceRegistration<MessageListener> serviceRegistration :
					_messageListenerServiceRegistrations) {

				serviceRegistration.unregister();
			}
		}

		_messageListenerServiceRegistrations = null;

		_bundleContext = null;
	}

	@Modified
	protected void modified(ComponentContext componentContext) {
		deactivate();

		activate(componentContext);
	}

	private void _registerDestination(
		MessageListener reportMessageListener, String destinationType,
		String destinationName) {

		DestinationConfiguration destinationConfiguration =
			new DestinationConfiguration(destinationType, destinationName);

		destinationConfiguration.setMaximumQueueSize(
			_reportsPortletMessagingConfiguration.reportMessageQueueSize());

		RejectedExecutionHandler rejectedExecutionHandler =
			new CallerRunsPolicy() {

				@Override
				public void rejectedExecution(
					Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {

					if (_log.isWarnEnabled()) {
						_log.warn(
							"The current thread will handle the request " +
								"because the report console's task queue is " +
									"at its maximum capacity");
					}

					super.rejectedExecution(runnable, threadPoolExecutor);
				}

			};

		destinationConfiguration.setRejectedExecutionHandler(
			rejectedExecutionHandler);

		Destination destination = _destinationFactory.createDestination(
			destinationConfiguration);

		Dictionary<String, Object> destinationProperties =
			new HashMapDictionary<>();

		destinationProperties.put("destination.name", destination.getName());

		ServiceRegistration<MessageListener>
			messageListenerServiceRegistration = _bundleContext.registerService(
				MessageListener.class, reportMessageListener,
				destinationProperties);

		_messageListenerServiceRegistrations.add(
			messageListenerServiceRegistration);

		ServiceRegistration<Destination> destinationServiceRegistration =
			_bundleContext.registerService(
				Destination.class, destination, destinationProperties);

		_destinationServiceRegistrations.add(destinationServiceRegistration);

		destination.register(reportMessageListener);
	}

	private void _registerReportsAdminDestination() {
		MessageListener adminMessageListener = new AdminMessageListener();

		_registerDestination(
			adminMessageListener,
			DestinationConfiguration.DESTINATION_TYPE_SERIAL,
			DestinationNames.REPORTS_ADMIN);
	}

	private void _registerReportsSchedulerEventDestination() {
		MessageListener schedulerEventMessageListener =
			new SchedulerEventMessageListener();

		_registerDestination(
			schedulerEventMessageListener,
			DestinationConfiguration.DESTINATION_TYPE_PARALLEL,
			DestinationNames.REPORTS_SCHEDULER_EVENT);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ReportsPortletMessagingConfigurator.class);

	private volatile BundleContext _bundleContext;

	@Reference
	private DestinationFactory _destinationFactory;

	private final List<ServiceRegistration<Destination>>
		_destinationServiceRegistrations = new ArrayList<>();
	private List<ServiceRegistration<MessageListener>>
		_messageListenerServiceRegistrations = new ArrayList<>();
	private ReportsPortletMessagingConfiguration
		_reportsPortletMessagingConfiguration;

}