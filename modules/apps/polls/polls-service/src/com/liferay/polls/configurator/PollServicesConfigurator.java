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

package com.liferay.polls.configurator;

import com.liferay.polls.verify.Verifier;
import com.liferay.portal.service.configuration.ServiceComponentConfiguration;
import com.liferay.portal.service.configuration.configurator.ServiceConfigurator;
import com.liferay.portal.spring.extender.loader.ModuleResourceLoader;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import org.springframework.context.ApplicationContext;

/**
 * @author Miguel Pastor
 */
@Component(
	immediate = true, service = PollServicesConfigurator.class
)
public class PollServicesConfigurator {

	public static final String BUNDLE_SYMBOLYC_NAME =
		"com.liferay.polls.service";

	@Activate
	protected void activate() throws Exception {
		_serviceConfigurator.initServices(getConfiguration(), getClassLoader());

		Verifier verifier = new Verifier();

		verifier.verify();
	}

	@Deactivate
	protected void deactivate() throws Exception {
		_serviceConfigurator.destroyServices(
			getConfiguration(), getClassLoader());
	}

	protected ClassLoader getClassLoader() {
		Class<? extends PollServicesConfigurator> clazz = getClass();

		return clazz.getClassLoader();
	}

	protected ServiceComponentConfiguration getConfiguration() {
		Bundle bundle = FrameworkUtil.getBundle(getClass());

		return new ModuleResourceLoader(bundle);
	}

	@Reference(
		target =
			"(org.springframework.context.service.name=" +
				BUNDLE_SYMBOLYC_NAME + ")",
		unbind = "-"
	)
	protected void setApplicationContext(ApplicationContext applicationContext)
		throws Exception {
	}

	@Reference(unbind = "-")
	protected void setServiceConfigurator(
		ServiceConfigurator serviceConfigurator) {

		_serviceConfigurator = serviceConfigurator;
	}

	private ServiceConfigurator _serviceConfigurator;

}