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

package com.liferay.portal.kernel.mobile.device;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceTracker;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Milen Dyankov
 * @author Raymond Augé
 */
public class DeviceDetectionUtil {

	public static Device detectDevice(HttpServletRequest request) {
		return getDeviceRecognitionProvider().detectDevice(request);
	}

	public static DeviceRecognitionProvider getDeviceRecognitionProvider() {
		PortalRuntimePermission.checkGetBeanProperty(DeviceDetectionUtil.class);

		return _instance._serviceTracker.getService();
	}

	public static Set<VersionableName> getKnownBrands() {
		KnownDevices knownDevices =
			getDeviceRecognitionProvider().getKnownDevices();

		return knownDevices.getBrands();
	}

	public static Set<VersionableName> getKnownBrowsers() {
		KnownDevices knownDevices =
			getDeviceRecognitionProvider().getKnownDevices();

		return knownDevices.getBrowsers();
	}

	public static Set<String> getKnownDeviceIdsByCapability(
		Capability capability) {

		KnownDevices knownDevices =
			getDeviceRecognitionProvider().getKnownDevices();

		Map<Capability, Set<String>> deviceIds = knownDevices.getDeviceIds();

		return deviceIds.get(capability);
	}

	public static Set<VersionableName> getKnownOperatingSystems() {
		KnownDevices knownDevices =
			getDeviceRecognitionProvider().getKnownDevices();

		return knownDevices.getOperatingSystems();
	}

	public static Set<String> getKnownPointingMethods() {
		KnownDevices knownDevices =
			getDeviceRecognitionProvider().getKnownDevices();

		return knownDevices.getPointingMethods();
	}

	public DeviceDetectionUtil() {
		Registry registry = RegistryUtil.getRegistry();

		_serviceTracker = registry.trackServices(
			DeviceRecognitionProvider.class);

		_serviceTracker.open();
	}

	private static final DeviceDetectionUtil _instance =
		new DeviceDetectionUtil();

	private final ServiceTracker
		<DeviceRecognitionProvider, DeviceRecognitionProvider> _serviceTracker;

}