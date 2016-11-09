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

package com.liferay.osb.lcs.management;

import com.liferay.util.portlet.PortletProps;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Matija Petanjek
 */
public class PortletPropertiesExporter {

	public Map<String, String> getProperties() {
		Properties properties = PortletProps.getProperties();

		for (Map.Entry<Object, Object> entry : properties.entrySet()) {
			_properties.put((String)entry.getKey(), (String)entry.getValue());
		}

		return _properties;
	}

	private Map<String, String> _properties = new HashMap<String, String>();

}