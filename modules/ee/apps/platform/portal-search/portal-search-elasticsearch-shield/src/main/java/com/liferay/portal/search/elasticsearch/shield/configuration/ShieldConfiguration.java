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

package com.liferay.portal.search.elasticsearch.shield.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author André de Oliveira
 */
@ExtendedObjectClassDefinition(category = "foundation")
@Meta.OCD(
	id = "com.liferay.portal.search.elasticsearch.shield.configuration.ShieldConfiguration"
)
public interface ShieldConfiguration {

	@Meta.AD(deflt = "liferay", required = false)
	public String password();

	@Meta.AD(deflt = "true", required = false)
	public boolean requiresAuthentication();

	@Meta.AD(deflt = "true", required = false)
	public boolean requiresSSL();

	@Meta.AD(required = false)
	public String sslKeystoreKeyPassword();

	@Meta.AD(deflt = "liferay", required = false)
	public String sslKeystorePassword();

	@Meta.AD(deflt = "/path/to/keystore.jks", required = false)
	public String sslKeystorePath();

	@Meta.AD(deflt = "liferay", required = false)
	public String username();

}