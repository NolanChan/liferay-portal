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

package com.liferay.mobile.device.rules.recognition.provider.fiftyone.internal;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mobile.device.Device;
import com.liferay.portal.kernel.mobile.device.DeviceCapabilityFilter;
import com.liferay.portal.kernel.mobile.device.DeviceRecognitionProvider;
import com.liferay.portal.kernel.mobile.device.KnownDevices;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Greenwald
 * @author Prathima Shreenath
 */
@Component(immediate = true, service = DeviceRecognitionProvider.class)
public class FiftyOneDegreesDeviceRecognitionProvider
	implements DeviceRecognitionProvider {

	@Override
	public Device detectDevice(HttpServletRequest request) {
		return _fiftyOneDegreesEngineProxy.getDeviceForRequest(request);
	}

	@Override
	public KnownDevices getKnownDevices() {
		return _fiftyOneDegreesKnownDevices;
	}

	@Override
	public void reload() throws Exception {
		_fiftyOneDegreesKnownDevices.reload();
	}

	@Deprecated
	public void setDeviceCapabilityFilter(
		DeviceCapabilityFilter deviceCapabilityFilter) {
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		try {
			reload();
		}
		catch (Exception e) {
			_log.error("Unable to initialize 51 Degrees Known Devices: " + e);
		}
	}

	@Reference(unbind = "-")
	protected void set_fiftyOneDegreesKnownDevices(
		FiftyOneDegreesKnownDevices fiftyOneDegreesKnownDevices) {

		_fiftyOneDegreesKnownDevices = fiftyOneDegreesKnownDevices;
	}

	@Reference(unbind = "-")
	protected void setFiftyOneDegreesEngineProxy(
		FiftyOneDegreesEngineProxy fiftyOneDegreesEngineProxy) {

		_fiftyOneDegreesEngineProxy = fiftyOneDegreesEngineProxy;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		FiftyOneDegreesDeviceRecognitionProvider.class);

	private FiftyOneDegreesEngineProxy _fiftyOneDegreesEngineProxy;
	private FiftyOneDegreesKnownDevices _fiftyOneDegreesKnownDevices;

}