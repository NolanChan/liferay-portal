/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation; version 3.0 of the License.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 */

package com.liferay.mobile.device.rules.recognition.provider.wurfl.internal;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mobile.device.Device;
import com.liferay.portal.kernel.mobile.device.DeviceCapabilityFilter;
import com.liferay.portal.kernel.mobile.device.DeviceRecognitionProvider;
import com.liferay.portal.kernel.mobile.device.KnownDevices;
import com.liferay.portal.kernel.mobile.device.UnknownDevice;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Milen Dyankov
 * @author Michael C. Han
 */
@Component(immediate = true, service = DeviceRecognitionProvider.class)
public class WURFLDeviceRecognitionProvider
	implements DeviceRecognitionProvider {

	@Override
	public Device detectDevice(HttpServletRequest request) {
		net.sourceforge.wurfl.core.Device wurflDevice =
			_wurflEngineProxy.getDeviceForRequest(request);

		Device device = null;

		if (wurflDevice != null) {
			Map<String, String> capabilities = wurflDevice.getCapabilities();

			if ((capabilities != null) && !capabilities.isEmpty()) {
				device = new WURFLDevice(capabilities, _deviceCapabilityFilter);
			}
			else {
				device = UnknownDevice.getInstance();
			}
		}

		return device;
	}

	@Override
	public KnownDevices getKnownDevices() {
		return _knownDevices;
	}

	@Override
	public void reload() throws Exception {
		_wurflEngineProxy.reload();

		_knownDevices.reload();

		if (_log.isDebugEnabled()) {
			_log.debug("Initialised");
		}
	}

	@Reference(
		cardinality = ReferenceCardinality.OPTIONAL,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	public void setDeviceCapabilityFilter(
		DeviceCapabilityFilter deviceCapabilityFilter) {

		_deviceCapabilityFilter = deviceCapabilityFilter;
	}

	@Reference(unbind = "-")
	protected void setKnownDevices(KnownDevices knownDevices) {
		_knownDevices = knownDevices;
	}

	@Reference(unbind = "-")
	protected void setWurflEngineProxy(WURFLEngineProxy wurflEngineProxy) {
		_wurflEngineProxy = wurflEngineProxy;
	}

	protected void unsetDeviceCapabilityFilter(
		DeviceCapabilityFilter deviceCapabilityFilter) {

		_deviceCapabilityFilter = null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		WURFLDeviceRecognitionProvider.class);

	private DeviceCapabilityFilter _deviceCapabilityFilter;
	private KnownDevices _knownDevices;
	private WURFLEngineProxy _wurflEngineProxy;

}