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

package com.liferay.portal.search.elasticsearch.shield.internal;

import aQute.bnd.annotation.metatype.Configurable;

import com.liferay.portal.search.elasticsearch.settings.BaseSettingsContributor;
import com.liferay.portal.search.elasticsearch.settings.SettingsContributor;
import com.liferay.portal.search.elasticsearch.shield.configuration.ShieldConfiguration;

import java.util.Map;

import org.elasticsearch.common.settings.ImmutableSettings.Builder;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * @author André de Oliveira
 */
@Component(
	configurationPid = "com.liferay.portal.search.elasticsearch.shield.configuration.ShieldConfiguration",
	immediate = true, property = {"operation.mode=REMOTE"},
	service = SettingsContributor.class
)
public class ShieldRemoteSettingsContributor extends BaseSettingsContributor {

	public ShieldRemoteSettingsContributor() {
		super(1);
	}

	@Override
	public void populate(Builder builder) {
		if (!shieldConfiguration.authentication()) {
			return;
		}

		String user =
			shieldConfiguration.username() + ":" +
			shieldConfiguration.password();

		builder.put("shield.user", user);

		if (shieldConfiguration.ssl()) {
			builder.put("shield.http.ssl", "true");
			builder.put(
				"shield.ssl.keystore.path",
				shieldConfiguration.sslKeystorePath());
			builder.put(
				"shield.ssl.keystore.password",
				shieldConfiguration.sslKeystorePassword());
			builder.put("shield.transport.ssl", "true");

			String sslKeystoreKeyPassword =
				shieldConfiguration.sslKeystoreKeyPassword();

			if (sslKeystoreKeyPassword != null) {
				builder.put(
					"shield.ssl.keystore.key_password",
					sslKeystoreKeyPassword);
			}
		}
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		shieldConfiguration = Configurable.createConfigurable(
			ShieldConfiguration.class, properties);
	}

	protected volatile ShieldConfiguration shieldConfiguration;

}