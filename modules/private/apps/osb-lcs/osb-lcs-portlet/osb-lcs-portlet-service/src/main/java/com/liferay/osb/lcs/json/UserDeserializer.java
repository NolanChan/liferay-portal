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
import com.liferay.portal.model.User;

import java.io.IOException;

/**
 * @author Ivica Cardic
 */
public class UserDeserializer extends JsonDeserializer {

	@Override
	public Object deserialize(
			JsonParser jsonParser,
			DeserializationContext deserializationContext)
		throws IOException {

		Class<User> userClass = getUserClass();

		User user = null;

		try {
			user = userClass.newInstance();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}

		ObjectCodec objectCodec = jsonParser.getCodec();

		JsonNode jsonNode = objectCodec.readTree(jsonParser);

		JsonNode uuidJsonNode = jsonNode.get("uuid");

		user.setUuid(uuidJsonNode.asText());

		return user;
	}

	protected Class<User> getUserClass() {
		if (_userClass == null) {
			try {
				_userClass = Class.forName(
					"com.liferay.portal.model.impl.UserImpl", false,
					PortalClassLoaderUtil.getClassLoader());
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		return _userClass;
	}

	private Class _userClass;

}