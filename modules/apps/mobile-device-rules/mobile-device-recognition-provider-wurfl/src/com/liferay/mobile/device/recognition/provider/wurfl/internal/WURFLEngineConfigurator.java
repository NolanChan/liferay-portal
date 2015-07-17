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

import aQute.bnd.annotation.metatype.Configurable;

import com.liferay.mobile.device.recognition.provider.wurfl.configuration.WURFLEngineConfiguration;
import com.liferay.portal.kernel.util.HashMapDictionary;

import net.sourceforge.wurfl.core.EngineTarget;
import net.sourceforge.wurfl.core.GeneralWURFLEngine;
import net.sourceforge.wurfl.core.WURFLEngine;
import net.sourceforge.wurfl.core.cache.CacheProvider;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	configurationPid = "com.liferay.mobile.device.recognition.provider.wurfl.configuration.WURFLEngineConfiguration",
	immediate = true
)
public class WURFLEngineConfigurator {

	@Activate
	protected void activate(ComponentContext componentContext) {
		_wurflEngineConfiguration = Configurable.createConfigurable(
			WURFLEngineConfiguration.class, componentContext.getProperties());

		_bundleContext = componentContext.getBundleContext();

		GeneralWURFLEngine generalWURFLEngine = new GeneralWURFLEngine(
			_wurflEngineConfiguration.wurflDatabaseFile());

		generalWURFLEngine.setCacheProvider(_cacheProvider);

		generalWURFLEngine.setCapabilityFilter(
			_wurflEngineConfiguration.capabilityFilter());

		generalWURFLEngine.setEngineTarget(
			EngineTarget.valueOf(_wurflEngineConfiguration.engineTarget()));

		_serviceRegistration = _bundleContext.registerService(
			WURFLEngine.class, generalWURFLEngine,
			new HashMapDictionary<String, Object>());
	}

	@Deactivate
	protected void deactivate() {
		if (_serviceRegistration != null) {
			_serviceRegistration.unregister();
		}

		_serviceRegistration = null;

		_bundleContext = null;
	}

	@Reference(unbind = "-")
	protected void setCacheProvider(CacheProvider cacheProvider) {
		_cacheProvider = cacheProvider;
	}

	private BundleContext _bundleContext;
	private CacheProvider _cacheProvider;
	private ServiceRegistration<WURFLEngine> _serviceRegistration;
	private volatile WURFLEngineConfiguration _wurflEngineConfiguration;

}