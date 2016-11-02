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

import java.util.Collection;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ryan Park
 */
@Component
public class SiteGeneratorRegistryUtil {

	public static SiteGenerator getSiteGenerator(String key) {
		return _siteGeneratorRegistry.getSiteGenerator(key);
	}

	public static Collection<SiteGenerator> getSiteGenerators() {
		return _siteGeneratorRegistry.getSiteGenerators();
	}

	@Reference(unbind = "-")
	protected void setSiteGeneratorRegistry(
		SiteGeneratorRegistry siteGeneratorRegistry) {

		_siteGeneratorRegistry = siteGeneratorRegistry;
	}

	private static SiteGeneratorRegistry _siteGeneratorRegistry;

}