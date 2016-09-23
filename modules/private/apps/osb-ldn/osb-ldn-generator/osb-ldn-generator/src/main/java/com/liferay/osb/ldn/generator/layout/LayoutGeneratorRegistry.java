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

package com.liferay.osb.ldn.generator.layout;

import com.liferay.osgi.service.tracker.collections.map.ServiceReferenceMapper;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Ryan Park
 */
@Component(immediate = true, service = LayoutGeneratorRegistry.class)
public class LayoutGeneratorRegistry {

	public Collection<List<LayoutGenerator>> getAllLayoutGenerators() {
		return _layoutGeneratorServiceTrackerMap.values();
	}

	public List<LayoutGenerator> getLayoutGenerators(String key) {
		return _layoutGeneratorServiceTrackerMap.getService(key);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_layoutGeneratorServiceTrackerMap =
			ServiceTrackerMapFactory.openMultiValueMap(
				bundleContext, LayoutGenerator.class,
				"(osb.ldn.site.generator.key=*)",
				new LayoutGeneratorServiceReferenceMapper(),
				new LayoutGeneratorOrderComparator());
	}

	private ServiceTrackerMap<String, List<LayoutGenerator>>
		_layoutGeneratorServiceTrackerMap;

	private class LayoutGeneratorOrderComparator
		implements Comparator<ServiceReference<LayoutGenerator>>, Serializable {

		@Override
		public int compare(
			ServiceReference serviceReference1,
			ServiceReference serviceReference2) {

			if (serviceReference1 == null) {
				if (serviceReference2 == null) {
					return 0;
				}
				else {
					return 1;
				}
			}
			else if (serviceReference2 == null) {
				return -1;
			}

			Object propertyValue1 = serviceReference1.getProperty(
				"osb.ldn.layout.order");
			Object propertyValue2 = serviceReference2.getProperty(
				"osb.ldn.layout.order");

			if (propertyValue1 == null) {
				if (propertyValue2 == null) {
					return 0;
				}
				else {
					return 1;
				}
			}
			else if (propertyValue2 == null) {
				return -1;
			}

			if (!(propertyValue2 instanceof Comparable)) {
				return -serviceReference2.compareTo(serviceReference1);
			}

			Comparable<Object> propertyValueComparable2 =
				(Comparable<Object>)propertyValue2;

			return -propertyValueComparable2.compareTo(propertyValue1);
		}

	}

	private class LayoutGeneratorServiceReferenceMapper
		implements ServiceReferenceMapper<String, LayoutGenerator> {

		@Override
		public void map(
			ServiceReference<LayoutGenerator> serviceReference,
			Emitter<String> emitter) {

			String siteGeneratorKey =
				(String)serviceReference.getProperty(
					"osb.ldn.site.generator.key");

			if (Validator.isNotNull(siteGeneratorKey)) {
				emitter.emit(siteGeneratorKey);
			}
		}

	}

}