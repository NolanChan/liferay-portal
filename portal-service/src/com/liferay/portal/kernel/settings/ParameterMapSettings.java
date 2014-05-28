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

package com.liferay.portal.kernel.settings;

import com.liferay.portal.kernel.util.StringPool;

import java.util.Map;

/**
 * @author Iván Zaera
 */
public class ParameterMapSettings extends BaseSettings {

	public static final String PREFERENCES_PREFIX = "preferences--";

	public static final String SETTINGS_PREFIX = "settings--";

	public ParameterMapSettings(
		Map<String, String[]> parameterMap, Settings parentSettings) {

		super(parentSettings);

		_parameterMap = parameterMap;
	}

	@Override
	public String getValue(String key, String defaultValue) {
		String[] values = getParameterValue(key);

		if (values != null) {
			return values[0];
		}

		return parentSettings.getValue(key, defaultValue);
	}

	@Override
	public String[] getValues(String key, String[] defaultValue) {
		String[] values = getParameterValue(key);

		if (values != null) {
			return values;
		}

		return parentSettings.getValues(key, defaultValue);
	}

	private Map<String, String[]> _parameterMap;

}