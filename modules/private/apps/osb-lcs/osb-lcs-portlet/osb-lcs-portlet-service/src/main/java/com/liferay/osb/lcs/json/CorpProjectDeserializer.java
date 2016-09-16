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

import com.liferay.osb.lcs.model.impl.CorpProjectImpl;

import java.io.IOException;

/**
 * @author Ivica Cardic
 */
public class CorpProjectDeserializer extends JsonDeserializer<CorpProjectImpl> {

	@Override
	public CorpProjectImpl deserialize(
			JsonParser jsonParser,
			DeserializationContext deserializationContext)
		throws IOException {

		CorpProjectImpl corpProjectImpl = new CorpProjectImpl();

		ObjectCodec objectCodec = jsonParser.getCodec();

		JsonNode jsonNode = objectCodec.readTree(jsonParser);

		JsonNode corpProjectIdJsonNode = jsonNode.get("corpProjectId");

		corpProjectImpl.setCorpProjectId(corpProjectIdJsonNode.asLong());

		JsonNode nameJsonNode = jsonNode.get("name");

		corpProjectImpl.setName(nameJsonNode.asText());

		JsonNode organizationIdJsonNode = jsonNode.get("organizationId");

		corpProjectImpl.setOrganizationId(organizationIdJsonNode.asLong());

		return corpProjectImpl;
	}

}