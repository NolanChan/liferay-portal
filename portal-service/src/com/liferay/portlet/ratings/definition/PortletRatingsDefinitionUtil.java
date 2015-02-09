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

package com.liferay.portlet.ratings.definition;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.ratings.RatingsType;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.ServiceTrackerCustomizer;
import com.liferay.registry.collections.ServiceTrackerCollections;
import com.liferay.registry.collections.ServiceTrackerMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Roberto Díaz
 * @author Sergio González
 */
public class PortletRatingsDefinitionUtil {

	public static RatingsType getDefaultRatingsType(String className) {
		PortletRatingsDefinitionValues portletRatingsDefinitionValues =
			_serviceTrackerMap.getService(className);

		if (portletRatingsDefinitionValues == null) {
			return null;
		}

		return portletRatingsDefinitionValues.getDefaultRatingsType();
	}

	public static Map<String, PortletRatingsDefinitionValues>
		getPortletRatingsDefinitionValuesMap() {

		Map<String, PortletRatingsDefinitionValues>
			portletRatingsDefinitionValuesMap = new HashMap<>();

		for (String className : _serviceTrackerMap.keySet()) {
			portletRatingsDefinitionValuesMap.put(
				className, _serviceTrackerMap.getService(className));
		}

		return Collections.unmodifiableMap(portletRatingsDefinitionValuesMap);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PortletRatingsDefinitionUtil.class);

	private static final
		ServiceTrackerMap<String, PortletRatingsDefinitionValues>
			_serviceTrackerMap = ServiceTrackerCollections.singleValueMap(
				PortletRatingsDefinition.class, "model.class.name",
				new ServiceTrackerCustomizer
					<PortletRatingsDefinition,
						PortletRatingsDefinitionValues>() {

				@Override
				public PortletRatingsDefinitionValues addingService(
					ServiceReference<PortletRatingsDefinition>
						serviceReference) {

					String portletId = (String)serviceReference.getProperty(
						"javax.portlet.name");

					if (Validator.isNull(portletId)) {
						if (_log.isWarnEnabled()) {
							_log.warn(
								"PortletRatingsDefinition must contain " +
									"javax.portlet.name, model.class.name, " +
										"ratings.type.default properties");
						}

						return null;
					}

					String[] classNames = null;

					Object modelClassName = serviceReference.getProperty(
						"model.class.name");

					if (modelClassName instanceof Object[]) {
						classNames = (String[])modelClassName;
					}
					else {
						classNames = new String[] {(String)modelClassName};
					}

					RatingsType defaultRatingsType = RatingsType.parse(
						(String)serviceReference.getProperty(
							"ratings.type.default"));

					if (Validator.isNull(portletId) ||
						ArrayUtil.isEmpty(classNames) ||
						(defaultRatingsType == null)) {

						if (_log.isWarnEnabled()) {
							_log.warn(
								"PortletRatingsDefinition must contain " +
									"javax.portlet.name, model.class.name, " +
										"ratings.type.default properties");
						}

						return null;
					}

					return new PortletRatingsDefinitionValues(
						classNames, defaultRatingsType, portletId);
				}

				@Override
				public void modifiedService(
					ServiceReference<PortletRatingsDefinition> serviceReference,
					PortletRatingsDefinitionValues
						portletRatingsDefinitionValues) {
				}

				@Override
				public void removedService(
					ServiceReference<PortletRatingsDefinition> serviceReference,
					PortletRatingsDefinitionValues
						portletRatingsDefinitionValues) {

					Registry registry = RegistryUtil.getRegistry();

					registry.ungetService(serviceReference);
				}

			});

	static {
		_serviceTrackerMap.open();
	}

}