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

package com.liferay.portal.security.audit.router;

import aQute.bnd.annotation.metatype.Configurable;

import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.security.audit.AuditMessageProcessor;
import com.liferay.portal.security.audit.router.configuration.LogAuditRouterProcessorConfiguration;
import com.liferay.portal.security.audit.router.constants.AuditConstants;

import java.util.Dictionary;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Mika Koivisto
 * @author Brian Wing Shun Chan
 * @author Brian Greenwald
 * @author Prathima Shreenath
 */
@Component(
	configurationPid = "com.liferay.portal.security.audit.router.configuration.LogAuditRouterProcessorConfiguration",
	immediate = true, property = "eventTypes=*",
	service = {AuditMessageProcessor.class, LogAuditRouterProcessor.class}
)
public class LogAuditRouterProcessor implements AuditMessageProcessor {

	@Override
	public void process(AuditMessage auditMessage) {
		try {
			doProcess(auditMessage);
		}
		catch (Exception e) {
			_log.fatal("Unable to process audit message " + auditMessage, e);
		}
	}

	@Activate
	@Modified
	protected void activate(ComponentContext componentContext) {
		Dictionary<String, Object> properties =
			componentContext.getProperties();

		_logAuditRouterProcessorConfiguration = Configurable.createConfigurable(
			LogAuditRouterProcessorConfiguration.class, properties);

		_outputToConsole =
			_logAuditRouterProcessorConfiguration.outputToConsole();

		BundleContext bundleContext = componentContext.getBundleContext();

		String configuredFormatter =
			_logAuditRouterProcessorConfiguration.logMessageFormatter();

		if (StringUtil.equalsIgnoreCase(
				configuredFormatter, AuditConstants.CSV)) {

			_serviceReference = bundleContext.getServiceReference(
				AuditConstants.CSVLogMessageFormatter);
		}
		else if (StringUtil.equalsIgnoreCase(
					configuredFormatter, AuditConstants.JSON)) {

			_serviceReference = bundleContext.getServiceReference(
				AuditConstants.JSONLogMessageFormatter);
		}

		_logMessageFormatter = (LogMessageFormatter)bundleContext.getService(
			_serviceReference);
	}

	protected void doProcess(AuditMessage auditMessage) throws Exception {
		if (_log.isInfoEnabled() || _outputToConsole) {
			String logMessage = _logMessageFormatter.format(auditMessage);

			if (_log.isInfoEnabled()) {
				_log.info(logMessage);
			}

			if (_outputToConsole) {
				System.out.println("LogAuditRouterProcessor: " + logMessage);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LogAuditRouterProcessor.class);

	private volatile LogAuditRouterProcessorConfiguration
		_logAuditRouterProcessorConfiguration;
	private LogMessageFormatter _logMessageFormatter;
	private boolean _outputToConsole;
	private ServiceReference<?> _serviceReference;

}