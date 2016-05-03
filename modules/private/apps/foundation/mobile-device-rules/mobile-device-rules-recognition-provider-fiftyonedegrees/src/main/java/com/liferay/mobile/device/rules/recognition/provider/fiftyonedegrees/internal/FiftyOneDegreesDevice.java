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

package com.liferay.mobile.device.rules.recognition.provider.fiftyonedegrees.internal;

import com.liferay.mobile.device.rules.recognition.provider.fiftyonedegrees.internal.constants.FiftyOneDegreesConstants;
import com.liferay.mobile.device.rules.recognition.provider.fiftyonedegrees.internal.constants.FiftyOneDegreesPropertyNames;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mobile.device.AbstractDevice;
import com.liferay.portal.kernel.mobile.device.Capability;
import com.liferay.portal.kernel.mobile.device.Dimensions;
import com.liferay.portal.kernel.mobile.device.VersionableName;
import com.liferay.portal.kernel.util.StringUtil;

import fiftyone.mobile.detection.Match;

import java.io.IOException;

import java.util.Map;

/**
 * @author Brian Greenwald
 * @author Prathima Shreenath
 */
public class FiftyOneDegreesDevice extends AbstractDevice {

	public FiftyOneDegreesDevice(Match match) {
		_match = match;
	}

	@Override
	public String getBrand() {
		return getValue(FiftyOneDegreesPropertyNames.HARDWARE_VENDOR);
	}

	@Override
	public String getBrowser() {
		return getValue(FiftyOneDegreesPropertyNames.BROWSER_NAME);
	}

	@Override
	public String getBrowserVersion() {
		return getValue(FiftyOneDegreesPropertyNames.BROWSER_VERSION);
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public Map<String, Capability> getCapabilities() {
		return null;
	}

	/**
	 * @deprecated As of 7.0.0, with no direct replacement
	 */
	@Deprecated
	@Override
	public String getCapability(String name) {
		return null;
	}

	@Override
	public String getModel() {
		return getValue(FiftyOneDegreesPropertyNames.HARDWARE_MODEL);
	}

	@Override
	public String getOS() {
		return getValue(FiftyOneDegreesPropertyNames.PLATFORM_NAME);
	}

	@Override
	public String getOSVersion() {
		return getValue(FiftyOneDegreesPropertyNames.PLATFORM_VERSION);
	}

	@Override
	public String getPointingMethod() {
		for (String pointingMethod :
				FiftyOneDegreesPropertyNames.TOUCH_EVENTS) {

			boolean hasPointingMethod = getBoolean(pointingMethod);

			if (hasPointingMethod) {
				return pointingMethod;
			}
		}

		return FiftyOneDegreesConstants.UNKNOWN;
	}

	@Override
	public Dimensions getScreenPhysicalSize() {
		return getDimensions(
			FiftyOneDegreesPropertyNames.SCREEN_MM_HEIGHT,
			FiftyOneDegreesPropertyNames.SCREEN_MM_WIDTH);
	}

	@Override
	public Dimensions getScreenResolution() {
		return getDimensions(
			FiftyOneDegreesPropertyNames.SCREEN_PIXELS_HEIGHT,
			FiftyOneDegreesPropertyNames.SCREEN_PIXELS_WIDTH);
	}

	@Override
	public boolean hasQwertyKeyboard() {
		boolean hasPhysicalQwerty = getBoolean(
			FiftyOneDegreesPropertyNames.HAS_QWERTY_PAD);
		boolean hasVirtualQwerty = getBoolean(
			FiftyOneDegreesPropertyNames.HAS_VIRTUAL_QWERTY);

		return (hasPhysicalQwerty || hasVirtualQwerty);
	}

	@Override
	public boolean isTablet() {
		return getBoolean(FiftyOneDegreesPropertyNames.IS_TABLET);
	}

	protected boolean getBoolean(String property) {
		boolean value = false;

		try {
			value = _match.getValues(property).toBool();
		}
		catch (IOException ioe) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to retrieve device information for " + property +
						" property: ",
					ioe);
			}
		}

		return value;
	}

	protected Dimensions getDimensions(
		String heightProperty, String widthProperty) {

		float height = 0f;
		float width = 0f;

		try {
			height = (float)_match.getValues(heightProperty).toDouble();
			width = (float)_match.getValues(widthProperty).toDouble();
		}
		catch (IOException ioe) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to retrieve device's dimension information: ", ioe);
			}
		}

		if ((height == 0f) || (width == 0f)) {
			return Dimensions.UNKNOWN;
		}

		return new Dimensions(height, width);
	}

	protected String getValue(String property) {
		String value = VersionableName.UNKNOWN.getName();

		try {
			String matchValue = _match.getValues(property).toString();

			if (!StringUtil.equalsIgnoreCase(
					matchValue, FiftyOneDegreesConstants.UNKNOWN)) {

				value = matchValue;
			}
		}
		catch (IOException ioe) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to retrieve device information for " + property +
						" property: ",
					ioe);
			}
		}

		return value;
	}



	private static final Log _log = LogFactoryUtil.getLog(
		FiftyOneDegreesDevice.class);

	private final Match _match;

}