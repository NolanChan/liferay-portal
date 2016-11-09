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

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

/**
 * @author Ivica Cardic
 */
public class MBeanAttributeProvider {

	public Map<String, Object> getObjectNamesAttributes(
		String hostName, Pattern pattern, String attributeName) {

		Map<String, Object> objectNamesAttributes =
			new HashMap<String, Object>();

		try {
			JMXServiceURL jmxServiceURL =
				new JMXServiceURL(
					"service:jmx:rmi:///jndi/rmi://" +
						hostName + ":" + _jmxRemotePort + "/jmxrmi");

			JMXConnector jmxConnector = JMXConnectorFactory.connect(
				jmxServiceURL, null);

			MBeanServerConnection mBeanServerConnection =
				jmxConnector.getMBeanServerConnection();

			Set<ObjectName> objectNames = mBeanServerConnection.queryNames(
				null, null);

			for (ObjectName objectName : objectNames) {
				String canonicalName = objectName.getCanonicalName();

				Matcher matcher = pattern.matcher(canonicalName);

				if (matcher.find()) {
					objectNamesAttributes.put(
						canonicalName,
						getObjectNameAttribute(
							canonicalName, attributeName,
							mBeanServerConnection));
				}
			}

			jmxConnector.close();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}

		return objectNamesAttributes;
	}

	public void setJmxRemotePort(int jmxRemotePort) {
		_jmxRemotePort = jmxRemotePort;
	}

	protected Object getObjectNameAttribute(
			String objectName, String attributeName,
			MBeanServerConnection mBeanServerConnection)
		throws IOException {

		try {
			return mBeanServerConnection.getAttribute(
				new ObjectName(objectName), attributeName);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private int _jmxRemotePort;

}