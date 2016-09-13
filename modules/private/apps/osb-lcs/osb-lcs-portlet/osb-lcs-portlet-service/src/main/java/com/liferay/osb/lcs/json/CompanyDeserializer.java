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

package com.liferay.osb.lcs.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.model.Company;

import java.io.IOException;

/**
 * @author Ivica Cardic
 */
public class CompanyDeserializer extends JsonDeserializer {

	@Override
	public Object deserialize(
			JsonParser jsonParser,
			DeserializationContext deserializationContext)
		throws IOException {

		Class<Company> companyClass = getCompanyClass();

		Company company = null;

		try {
			company = companyClass.newInstance();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}

		ObjectCodec objectCodec = jsonParser.getCodec();

		JsonNode jsonNode = objectCodec.readTree(jsonParser);

		JsonNode companyIdJsonNode = jsonNode.get("companyId");

		company.setCompanyId(companyIdJsonNode.asLong());

		return company;
	}

	protected Class<Company> getCompanyClass() {
		if (_companyClass == null) {
			try {
				_companyClass = Class.forName(
					"com.liferay.portal.model.impl.CompanyImpl", false,
					PortalClassLoaderUtil.getClassLoader());
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		return _companyClass;
	}

	private Class _companyClass;

}