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

package com.liferay.websocket.whiteboard.test.encode.data;

import java.io.StringReader;

import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 * @author Cristina González
 */
public class ExampleDecoder implements Decoder.Text<Example> {

	@Override
	public Example decode(String message) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Example.class);

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			StringReader reader = new StringReader(message);

			return (Example)unmarshaller.unmarshal(reader);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void destroy() {
		//NO OP
	}

	@Override
	public void init(EndpointConfig endpointConfig) {
		//NO OP
	}

	@Override
	public boolean willDecode(String message) {
		if (message != null) {
			return true;
		}

		return false;
	}

}