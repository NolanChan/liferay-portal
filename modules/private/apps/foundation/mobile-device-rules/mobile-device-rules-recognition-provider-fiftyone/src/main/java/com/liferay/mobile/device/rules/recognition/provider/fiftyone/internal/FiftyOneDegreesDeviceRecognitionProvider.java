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

import com.liferay.portal.kernel.mobile.device.Device;
import com.liferay.portal.kernel.mobile.device.DeviceCapabilityFilter;
import com.liferay.portal.kernel.mobile.device.DeviceRecognitionProvider;
import com.liferay.portal.kernel.mobile.device.KnownDevices;

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
	protected void activate() {
		try {
			reload();
		}
		catch (Exception e) {
			throw new IllegalStateException(
				"Unable to load 51Degrees device data", e);
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

	private FiftyOneDegreesEngineProxy _fiftyOneDegreesEngineProxy;
	private FiftyOneDegreesKnownDevices _fiftyOneDegreesKnownDevices;

}