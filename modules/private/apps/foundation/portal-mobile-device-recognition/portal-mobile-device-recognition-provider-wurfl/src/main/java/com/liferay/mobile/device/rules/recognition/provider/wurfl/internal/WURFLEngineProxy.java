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

import com.liferay.mobile.device.rules.recognition.provider.wurfl.configuration.WURFLEngineConfiguration;
import com.liferay.mobile.device.rules.recognition.provider.wurfl.internal.util.WURFLUtil;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.Props;
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

import net.sourceforge.wurfl.core.Device;
import net.sourceforge.wurfl.core.EngineTarget;
import net.sourceforge.wurfl.core.GeneralWURFLEngine;
import net.sourceforge.wurfl.core.WURFLEngine;
import net.sourceforge.wurfl.core.WURFLUtils;
import net.sourceforge.wurfl.core.cache.CacheProvider;
import net.sourceforge.wurfl.core.resource.WURFLResources;
import net.sourceforge.wurfl.core.resource.XMLResource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	configurationPid = "com.liferay.mobile.device.rules.recognition.provider.wurfl.configuration.WURFLEngineConfiguration",
	immediate = true, service = WURFLEngineProxy.class
)
public class WURFLEngineProxy {

	public Device getDeviceForRequest(HttpServletRequest httpServletRequest) {
		return _wurflEngine.getDeviceForRequest(httpServletRequest);
	}

	public WURFLUtils getWURFLUtils() {
		return _wurflEngine.getWURFLUtils();
	}

	public void reload() throws IOException {
		List<InputStream> inputStreams = new ArrayList<>();

		try {
			XMLResource xmlResource = getXMLResource(inputStreams);

			WURFLResources wurflResources = getWURFLResources(inputStreams);

			_wurflEngine.reload(xmlResource, wurflResources);
		}
		finally {
			for (InputStream inputStream : inputStreams) {
				StreamUtil.cleanUp(inputStream);
			}
		}
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_wurflEngineConfiguration = ConfigurableUtil.createConfigurable(
			WURFLEngineConfiguration.class, properties);

		List<InputStream> inputStreams = new ArrayList<>();

		try {
			XMLResource xmlResource = getXMLResource(inputStreams);

			WURFLResources wurflResources = getWURFLResources(inputStreams);

			_wurflEngine = new GeneralWURFLEngine(xmlResource, wurflResources);

			_wurflEngine.setCacheProvider(_cacheProvider);
			_wurflEngine.setCapabilityFilter(
				_wurflEngineConfiguration.capabilityFilter());
			_wurflEngine.setEngineTarget(
				EngineTarget.valueOf(_wurflEngineConfiguration.engineTarget()));

			_wurflEngine.getWURFLUtils();
		}
		catch (Exception e) {
			throw new IllegalStateException("Unable to initialize", e);
		}
		finally {
			for (InputStream inputStream : inputStreams) {
				StreamUtil.cleanUp(inputStream);
			}
		}
	}

	protected WURFLResources getWURFLResources(List<InputStream> inputStreams)
		throws FileNotFoundException {

		WURFLResources wurflResources = new WURFLResources();

		String wurflDatabasePatchDirName =
			WURFLUtil.getWURFLDatabasePatchDirName(
				_wurflEngineConfiguration, _props);

		String[] fileNames = FileUtil.listFiles(wurflDatabasePatchDirName);

		for (String fileName : fileNames) {
			File file = new File(wurflDatabasePatchDirName, fileName);

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

		String wurflDatabaseFileName =
			_wurflEngineConfiguration.wurflDatabaseFileName();

		InputStream inputStream = clazz.getResourceAsStream(
			wurflDatabaseFileName);

		if (inputStream == null) {
			throw new IllegalStateException(
				"Unable to find " +
					_wurflEngineConfiguration.wurflDatabaseFileName());
		}

		if (wurflDatabaseFileName.endsWith(".gz")) {
			inputStream = new GZIPInputStream(inputStream);
		}
		else if (wurflDatabaseFileName.endsWith(".jar") ||
				 wurflDatabaseFileName.endsWith(".zip")) {

			ZipInputStream zipInputStream = new ZipInputStream(inputStream);

			inputStream = zipInputStream;

			zipInputStream.getNextEntry();
		}

		XMLResource xmlResource = new XMLResource(
			inputStream, _wurflEngineConfiguration.wurflDatabaseFileName());

		inputStreams.add(inputStream);

		return xmlResource;
	}

	@Modified
	protected void modified(Map<String, Object> properties) {
		try {
			_wurflEngineConfiguration = ConfigurableUtil.createConfigurable(
				WURFLEngineConfiguration.class, properties);

			_wurflEngine.setCacheProvider(_cacheProvider);
			_wurflEngine.setCapabilityFilter(
				_wurflEngineConfiguration.capabilityFilter());
			_wurflEngine.setEngineTarget(
				EngineTarget.valueOf(_wurflEngineConfiguration.engineTarget()));

			reload();
		}
		catch (Exception e) {
			throw new IllegalStateException("Unable to initialize", e);
		}
	}

	@Reference(unbind = "-")
	protected void setCacheProvider(CacheProvider cacheProvider) {
		_cacheProvider = cacheProvider;
	}

	@Reference(unbind = "-")
	protected void setProps(Props props) {
		_props = props;
	}

	private CacheProvider _cacheProvider;
	private Props _props;
	private WURFLEngine _wurflEngine;
	private volatile WURFLEngineConfiguration _wurflEngineConfiguration;

}