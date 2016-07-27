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

package com.liferay.saml.provider;

import com.liferay.saml.service.SamlIdpSpConnectionLocalService;
import com.liferay.saml.service.SamlSpIdpConnectionLocalService;

import org.opensaml.xml.parse.ParserPool;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(immediate = true)
public class DBMetadataProviderFactory {

	public DBMetadataProvider createDBMetadataProvider(ParserPool parserPool) {
		DBMetadataProvider dbMetadataProvider = new DBMetadataProvider(
			_samlSpIdpConnectionLocalService, _samlIdpSpConnectionLocalService);

		dbMetadataProvider.setParserPool(parserPool);

		return dbMetadataProvider;
	}

	@Reference
	private SamlIdpSpConnectionLocalService _samlIdpSpConnectionLocalService;

	@Reference
	private SamlSpIdpConnectionLocalService _samlSpIdpConnectionLocalService;

}