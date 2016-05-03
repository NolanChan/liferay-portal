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

package com.liferay.saml.transport;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.httpclient.methods.RequestEntity;

/**
 * @author Mika Koivisto
 */
public class OutputStreamRequestEntity implements RequestEntity {

	public OutputStreamRequestEntity(
		ByteArrayOutputStream byteArrayOutputStream) {

		this(byteArrayOutputStream, null);
	}

	public OutputStreamRequestEntity(
		ByteArrayOutputStream byteArrayOutputStream, String contentType) {

		_byteArrayOutputStream = byteArrayOutputStream;
		_contentType = contentType;
	}

	@Override
	public long getContentLength() {
		return _byteArrayOutputStream.size();
	}

	@Override
	public String getContentType() {
		return _contentType;
	}

	@Override
	public boolean isRepeatable() {
		return true;
	}

	@Override
	public void writeRequest(OutputStream outputStream) throws IOException {
		_byteArrayOutputStream.writeTo(outputStream);
	}

	private ByteArrayOutputStream _byteArrayOutputStream;
	private String _contentType;

}