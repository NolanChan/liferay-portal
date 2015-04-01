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

package com.liferay.portal.sso.openid.internal;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.sso.openid.OpenIdProvider;
import com.liferay.portal.sso.openid.OpenIdProviderRegistry;

import java.net.URL;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true,
	property = {
		"open.id.ax.schema[default]=email,firstname,lastname",
		"open.id.ax.schema[yahoo]=email,fullname",
		"open.id.ax.type.email[default]=http://schema.openid.net/contact/email",
		"open.id.ax.type.email[yahoo]=http://axschema.org/contact/email",
		"open.id.ax.type.firstname[default]=http://schema.openid.net/namePerson/first",
		"open.id.ax.type.fullname[yahoo]=http://axschema.org/namePerson",
		"open.id.ax.type.lastname[default]=http://schema.openid.net/namePerson/last",
		"open.id.url[yahoo]=open.login.yahooapis.com"
	},
	service = OpenIdProviderRegistry.class
)
public class OpenIdProviderRegistryImpl implements OpenIdProviderRegistry {

	@Override
	public OpenIdProvider getOpenIdProvider(String name) {
		return _openIdProviders.get(name);
	}

	@Override
	public OpenIdProvider getOpenIdProvider(URL url) {
		for (OpenIdProvider openIdProvider : _openIdProviders.values()) {
			if (Validator.equals(openIdProvider.getUrl(), url.getHost())) {
				return openIdProvider;
			}
		}

		return _openIdProviders.get(OPEN_ID_PROVIDER_NAME_DEFAULT);
	}

	@Override
	public Collection<String> getOpenIdProviderNames() {
		return Collections.unmodifiableCollection(_openIdProviders.keySet());
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		Collection<OpenIdProvider> providers = initOpenIdProviders(properties);

		for (OpenIdProvider provider : providers) {
			setOpenIdProvider(provider);
		}
	}

	protected Collection<OpenIdProvider> initOpenIdProviders(
		Map<String, Object> properties) {

		Map<String, OpenIdProvider> result = new HashMap<>(2);

		for (String key : properties.keySet()) {
			int pos = key.indexOf("[");

			if (pos < 0) {
				continue;
			}

			String name = key.substring(pos + 1, key.length() - 1);

			OpenIdProvider openIdProvider = result.get(name);

			if (openIdProvider == null) {
				openIdProvider = new OpenIdProvider();
				openIdProvider.setName(name);

				result.put(name, openIdProvider);
			}

			String value = GetterUtil.getString(properties.get(key));

			if (key.startsWith(_OPEN_ID_AX_SCHEMA)) {
				openIdProvider.setAxSchema(StringUtil.split(value));
			}
			else if (key.startsWith(_OPEN_ID_AX_TYPE_URL)) {
				openIdProvider.setUrl(value);
			}
			else {
				String attributeName = key.substring(
					_OPEN_ID_AX_TYPE.length() + 1, pos);

				openIdProvider.setAxTypes(attributeName, value);
			}
		}

		return result.values();
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC
	)
	protected void setOpenIdProvider(OpenIdProvider openIdProvider) {
		_openIdProviders.put(openIdProvider.getName(), openIdProvider);
	}

	protected void unsetOpenIdProvider(OpenIdProvider openIdProvider) {
		_openIdProviders.remove(openIdProvider.getName());
	}

	private static final String _OPEN_ID_AX_SCHEMA = "open.id.ax.schema";

	private static final String _OPEN_ID_AX_TYPE = "open.id.ax.type";

	private static final String _OPEN_ID_AX_TYPE_URL = "open.id.url";

	private final Map<String, OpenIdProvider> _openIdProviders =
		new HashMap<>();

}