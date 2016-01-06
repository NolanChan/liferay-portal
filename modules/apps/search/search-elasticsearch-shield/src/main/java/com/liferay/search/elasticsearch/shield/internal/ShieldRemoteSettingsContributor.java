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

package com.liferay.search.elasticsearch.shield.internal;

import aQute.bnd.annotation.metatype.Configurable;

import com.liferay.search.elasticsearch.settings.BaseSettingsContributor;
import com.liferay.search.elasticsearch.settings.SettingsContributor;
import com.liferay.search.elasticsearch.shield.configuration.ShieldConfiguration;

import java.util.Map;

import org.elasticsearch.common.settings.Settings.Builder;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author André de Oliveira
 */
@Component(
	configurationPid = "com.liferay.search.elasticsearch.shield.configuration.ShieldConfiguration",
	immediate = true, property = {"operation.mode=REMOTE"},
	service = SettingsContributor.class
)
public class ShieldRemoteSettingsContributor extends BaseSettingsContributor {

	public ShieldRemoteSettingsContributor() {
		super(1);
	}

	@Override
	public void populate(Builder builder) {
		if (!shieldConfiguration.requiresAuthentication()) {
			return;
		}

		builder.extendArray(
			"plugin.types", "org.elasticsearch.shield.ShieldPlugin");

		configureAuthentication(builder);
		configureSSL(builder);
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		shieldConfiguration = Configurable.createConfigurable(
			ShieldConfiguration.class, properties);
	}

	protected void configureAuthentication(Builder builder) {
		String user =
			shieldConfiguration.username() + ":" +
				shieldConfiguration.password();

		builder.put("shield.user", user);
	}

	protected void configureSSL(Builder builder) {
		if (!shieldConfiguration.requiresSSL()) {
			return;
		}

		builder.put("shield.http.ssl", "true");
		builder.put(
			"shield.ssl.keystore.path", shieldConfiguration.sslKeystorePath());
		builder.put(
			"shield.ssl.keystore.password",
			shieldConfiguration.sslKeystorePassword());
		builder.put("shield.transport.ssl", "true");

		String sslKeystoreKeyPassword =
			shieldConfiguration.sslKeystoreKeyPassword();

		if (sslKeystoreKeyPassword != null) {
			builder.put(
				"shield.ssl.keystore.key_password", sslKeystoreKeyPassword);
		}
	}

	protected volatile ShieldConfiguration shieldConfiguration;

}