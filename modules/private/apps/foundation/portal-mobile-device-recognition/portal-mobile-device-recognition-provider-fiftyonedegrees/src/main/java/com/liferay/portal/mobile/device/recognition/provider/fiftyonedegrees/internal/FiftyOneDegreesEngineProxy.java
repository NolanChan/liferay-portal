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

package com.liferay.portal.mobile.device.recognition.provider.fiftyonedegrees.internal;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mobile.device.Device;
import com.liferay.portal.kernel.mobile.device.UnknownDevice;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.mobile.device.recognition.provider.fiftyonedegrees.configuration.FiftyOneDegreesConfiguration;

import fiftyone.mobile.detection.Dataset;
import fiftyone.mobile.detection.Match;
import fiftyone.mobile.detection.Provider;
import fiftyone.mobile.detection.factories.StreamFactory;

import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import java.util.Dictionary;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Brian Greenwald
 * @author Prathima Shreenath
 */
@Component(
	configurationPid = "com.liferay.portal.mobile.device.recognition.provider.fiftyonedegrees.configuration.FiftyOneDegreesConfiguration",
	immediate = true, service = FiftyOneDegreesEngineProxy.class
)
public class FiftyOneDegreesEngineProxy {

	public Dataset getDataset() {
		return _provider.dataSet;
	}

	public Device getDevice(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent");

		try {
			Match match = _provider.match(userAgent);

			return new FiftyOneDegreesDevice(match);
		}
		catch (IOException ioe) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to get match for user agent: " + userAgent, ioe);
			}

			return UnknownDevice.getInstance();
		}
	}

	@Activate
	@Modified
	protected void activate(ComponentContext componentContext) {
		BundleContext bundleContext = componentContext.getBundleContext();

		Bundle bundle = bundleContext.getBundle();

		Dictionary<String, Object> properties =
			componentContext.getProperties();

		_fiftyOneDegreesConfiguration = ConfigurableUtil.createConfigurable(
			FiftyOneDegreesConfiguration.class, properties);

		String fileName =
			_fiftyOneDegreesConfiguration.fiftyOneDegreesDataFileName();

		URL url = bundle.getResource(fileName);

		try (InputStream urlInputStream = url.openStream()) {
			InputStream inputStream = urlInputStream;

			try {
				if (fileName.endsWith(".gz")) {
					inputStream = new GZIPInputStream(urlInputStream);
				}
				else if (fileName.endsWith(".jar") ||
						 fileName.endsWith(".zip")) {

					inputStream = new ZipInputStream(urlInputStream);
				}

				Dataset dataset = StreamFactory.create(
					IOUtils.toByteArray(inputStream));

				_provider = new Provider(dataset);
			}
			finally {
				StreamUtil.cleanUp(inputStream);
			}
		}
		catch (IOException ioe) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to create provider with file " + fileName, ioe);
			}

			throw new IllegalStateException(ioe);
		}
	}

	@Deactivate
	protected void deactivate() {
		_fiftyOneDegreesConfiguration = null;
		_provider = null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		FiftyOneDegreesEngineProxy.class);

	private volatile FiftyOneDegreesConfiguration _fiftyOneDegreesConfiguration;
	private Provider _provider;

}