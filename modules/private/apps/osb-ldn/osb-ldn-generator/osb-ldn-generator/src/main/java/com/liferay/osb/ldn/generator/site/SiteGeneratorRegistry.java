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

package com.liferay.osb.ldn.generator.site;

import com.liferay.osgi.service.tracker.collections.map.ServiceReferenceMapper;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.util.Validator;

import java.util.Collection;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Ryan Park
 */
@Component(immediate = true, service = SiteGeneratorRegistry.class)
public class SiteGeneratorRegistry {

	public SiteGenerator getSiteGenerator(String key) {
		return _siteGeneratorServiceTrackerMap.getService(key);
	}

	public Collection<SiteGenerator> getSiteGenerators() {
		return _siteGeneratorServiceTrackerMap.values();
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_siteGeneratorServiceTrackerMap =
			ServiceTrackerMapFactory.openSingleValueMap(
				bundleContext, SiteGenerator.class,
				"(osb.ldn.site.generator.key=*)",
				new SiteGeneratorServiceReferenceMapper());
	}

	private ServiceTrackerMap<String, SiteGenerator>
		_siteGeneratorServiceTrackerMap;

	private class SiteGeneratorServiceReferenceMapper
		implements ServiceReferenceMapper<String, SiteGenerator> {

		@Override
		public void map(
			ServiceReference<SiteGenerator> serviceReference,
			Emitter<String> emitter) {

			String siteGeneratorKey = (String)serviceReference.getProperty(
				"osb.ldn.site.generator.key");

			if (Validator.isNotNull(siteGeneratorKey)) {
				emitter.emit(siteGeneratorKey);
			}
		}

	}

}