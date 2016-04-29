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

import com.liferay.mobile.device.rules.recognition.provider.fiftyone.internal.constants.FiftyOneDegreesPropertyConstants;
import com.liferay.portal.kernel.mobile.device.Capability;
import com.liferay.portal.kernel.mobile.device.KnownDevices;
import com.liferay.portal.kernel.mobile.device.NoKnownDevices;
import com.liferay.portal.kernel.mobile.device.VersionableName;
import com.liferay.portal.kernel.util.Validator;

import fiftyone.mobile.detection.Dataset;
import fiftyone.mobile.detection.entities.Profile;

import java.io.IOException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Greenwald
 * @author Prathima Shreenath
 */
@Component(
	immediate = true,
	service = {KnownDevices.class, FiftyOneDegreesKnownDevices.class}
)
public class FiftyOneDegreesKnownDevices implements KnownDevices {

	@Override
	public Set<VersionableName> getBrands() {
		if (!_initialized) {
			NoKnownDevices noKnownDevices = NoKnownDevices.getInstance();

			return noKnownDevices.getBrands();
		}

		return _brands;
	}

	@Override
	public Set<VersionableName> getBrowsers() {
		if (!_initialized) {
			NoKnownDevices noKnownDevices = NoKnownDevices.getInstance();

			return noKnownDevices.getBrowsers();
		}

		return _browswers;
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public Map<Capability, Set<String>> getDeviceIds() {
		return null;
	}

	@Override
	public Set<VersionableName> getOperatingSystems() {
		if (!_initialized) {
			NoKnownDevices noKnownDevices = NoKnownDevices.getInstance();

			return noKnownDevices.getOperatingSystems();
		}

		return _operatingSystems;
	}

	@Override
	public Set<String> getPointingMethods() {
		return _pointingMethods;
	}

	@Override
	public synchronized void reload() throws Exception {
		_initialized = false;

		Dataset dataset = _fiftyOneDegreesEngineProxy.getDataset();

		fiftyone.mobile.detection.entities.Component browsersComponent =
			dataset.getBrowsers();

		addProperties(
			_browswers, browsersComponent,
			FiftyOneDegreesPropertyConstants.BROWSER_NAME,
			FiftyOneDegreesPropertyConstants.BROWSER_VERSION, null);

		fiftyone.mobile.detection.entities.Component softwareComponent =
			dataset.getSoftware();

		addProperties(
			_operatingSystems, softwareComponent,
			FiftyOneDegreesPropertyConstants.PLATFORM_NAME,
			FiftyOneDegreesPropertyConstants.PLATFORM_VERSION, null);

		fiftyone.mobile.detection.entities.Component hardwareComponent =
			dataset.getHardware();

		addProperties(
			_brands, hardwareComponent,
			FiftyOneDegreesPropertyConstants.HARDWARE_VENDOR,
			FiftyOneDegreesPropertyConstants.HARDWARE_MODEL,
			FiftyOneDegreesPropertyConstants.HARDWARE_NAME);

		List<String> pointingMethodsList = Arrays.asList(
			FiftyOneDegreesPropertyConstants.TOUCH_EVENTS);

		_pointingMethods = new TreeSet<>(pointingMethodsList);

		_initialized = true;
	}

	protected void addProperties(
			Set<VersionableName> propertiesSet,
			fiftyone.mobile.detection.entities.Component propertiesComponent,
			String propertyName, String propertyVersionName,
			String propertySubVersionName)
		throws IOException {

		for (Profile profile : propertiesComponent.getProfiles()) {
			String propertyValue = profile.getValues(propertyName).toString();

			String propertyVersion = profile.getValues(
				propertyVersionName).toString();

			VersionableName versionableName = new VersionableName(
				propertyValue, propertyVersion);

			if (Validator.isNotNull(propertySubVersionName)) {
				String propertySubVersion = profile.getValues(
					propertySubVersionName).toString();

				versionableName.addVersion(propertySubVersion);
			}

			propertiesSet.add(versionableName);
		}
	}

	@Reference(unbind = "-")
	protected void setFiftyOneDegreesEngineProxy(
		FiftyOneDegreesEngineProxy fiftyOneDegreesEngineProxy) {

		_fiftyOneDegreesEngineProxy = fiftyOneDegreesEngineProxy;
	}

	private final Set<VersionableName> _brands = new TreeSet<>();
	private final Set<VersionableName> _browswers = new TreeSet<>();
	private FiftyOneDegreesEngineProxy _fiftyOneDegreesEngineProxy;
	private boolean _initialized;
	private final Set<VersionableName> _operatingSystems = new TreeSet<>();
	private Set<String> _pointingMethods;

}