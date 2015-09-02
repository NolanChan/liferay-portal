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

package com.liferay.dynamic.data.mapping.io;

import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Pablo Carvalho
 */
public class DDMFormXSDDeserializerUtil {

	public static DDMForm deserialize(String serializedDDMForm)
		throws PortalException {

		return getDDMFormXSDDeserializer().deserialize(serializedDDMForm);
	}

	public static DDMFormXSDDeserializer getDDMFormXSDDeserializer() {
		PortalRuntimePermission.checkGetBeanProperty(
			DDMFormXSDDeserializerUtil.class);

		return _serviceTracker.getService();
	}

	private static final ServiceTracker
		<DDMFormXSDDeserializer, DDMFormXSDDeserializer> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			DDMFormXSDDeserializerUtil.class);

		_serviceTracker = new ServiceTracker<>(
			bundle.getBundleContext(), DDMFormXSDDeserializer.class, null);

		_serviceTracker.open();
	}

}