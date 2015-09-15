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

package com.liferay.portal.ldap.configuration;

import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationFactory;
import com.liferay.portal.kernel.settings.CompanyServiceSettingsLocator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(immediate = true)
public class LDAPConfigurationUtil {

	public static final String SERVICE_NAME = "com.liferay.portal.ldap";

	public static LDAPConfiguration getLDAPConfiguration(long companyId) {
		try {
			LDAPConfiguration ldapConfiguration =
				_configurationFactory.getConfiguration(
					LDAPConfiguration.class,
					new CompanyServiceSettingsLocator(companyId, SERVICE_NAME));

			return ldapConfiguration;
		}
		catch (ConfigurationException e) {
			throw new IllegalStateException(e);
		}
	}

	@Reference(unbind = "-")
	protected void setConfigurationFactory(
		ConfigurationFactory configurationFactory) {

		_configurationFactory = configurationFactory;
	}

	private static ConfigurationFactory _configurationFactory;

}