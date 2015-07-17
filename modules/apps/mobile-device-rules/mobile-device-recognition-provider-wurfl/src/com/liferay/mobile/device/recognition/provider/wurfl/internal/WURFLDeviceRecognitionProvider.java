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

package com.liferay.mobile.device.recognition.provider.wurfl.internal;

import com.liferay.mobile.device.recognition.provider.wurfl.internal.util.WURFLPropsValues;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mobile.device.Device;
import com.liferay.portal.kernel.mobile.device.DeviceCapabilityFilter;
import com.liferay.portal.kernel.mobile.device.DeviceRecognitionProvider;
import com.liferay.portal.kernel.mobile.device.KnownDevices;
import com.liferay.portal.kernel.mobile.device.UnknownDevice;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StreamUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;

import net.sourceforge.wurfl.core.WURFLEngine;
import net.sourceforge.wurfl.core.resource.WURFLResources;
import net.sourceforge.wurfl.core.resource.XMLResource;

import org.osgi.service.component.annotations.Activate;
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
			_wurflEngine.getDeviceForRequest(request);

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
		List<InputStream> inputStreams = new ArrayList<>();

		try {
			XMLResource xmlResource = getXMLResource(inputStreams);

			WURFLResources wurflResources = getWURFLResources(inputStreams);

			_wurflEngine.reload(xmlResource, wurflResources);

			_knownDevices.reload();
		}
		finally {
			for (InputStream inputStream : inputStreams) {
				StreamUtil.cleanUp(inputStream);
			}
		}

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

	@Activate
	protected void activate() throws Exception {
		reload();
	}

	protected WURFLResources getWURFLResources(List<InputStream> inputStreams)
		throws FileNotFoundException {

		WURFLResources wurflResources = new WURFLResources();

		String[] fileNames = FileUtil.listFiles(
			WURFLPropsValues.WURFL_DATABASE_PATCHES);

		for (String fileName : fileNames) {
			File file = new File(
				WURFLPropsValues.WURFL_DATABASE_PATCHES, fileName);

			FileInputStream fileInputStream = new FileInputStream(file);

			inputStreams.add(fileInputStream);

			XMLResource xmlResource = new XMLResource(file);

			wurflResources.add(xmlResource);
		}

		return wurflResources;
	}

	protected XMLResource getXMLResource(List<InputStream> inputStreams)
		throws IOException {

		Class<?> clazz = getClass();

		InputStream inputStream = clazz.getResourceAsStream(
			WURFLPropsValues.WURFL_DATABASE_PRIMARY);

		if (inputStream == null) {
			throw new IllegalStateException(
				"Unable to find " + WURFLPropsValues.WURFL_DATABASE_PRIMARY);
		}

		if (WURFLPropsValues.WURFL_DATABASE_PRIMARY.endsWith(".gz")) {
			inputStream = new GZIPInputStream(inputStream);
		}
		else if (WURFLPropsValues.WURFL_DATABASE_PRIMARY.endsWith(".jar") ||
				 WURFLPropsValues.WURFL_DATABASE_PRIMARY.endsWith(".zip")) {

			ZipInputStream zipInputStream = new ZipInputStream(inputStream);

			inputStream = zipInputStream;

			zipInputStream.getNextEntry();
		}

		XMLResource xmlResource = new XMLResource(
			inputStream, WURFLPropsValues.WURFL_DATABASE_PRIMARY);

		inputStreams.add(inputStream);

		return xmlResource;
	}

	@Reference(unbind = "-")
	protected void setKnownDevices(KnownDevices knownDevices) {
		_knownDevices = knownDevices;
	}

	@Reference(unbind = "-")
	protected void setWURFLEngine(WURFLEngine wurflEngine) {
		_wurflEngine = wurflEngine;
	}

	protected void unsetDeviceCapabilityFilter(
		DeviceCapabilityFilter deviceCapabilityFilter) {

		_deviceCapabilityFilter = null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		WURFLDeviceRecognitionProvider.class);

	private DeviceCapabilityFilter _deviceCapabilityFilter;
	private KnownDevices _knownDevices;
	private WURFLEngine _wurflEngine;

}