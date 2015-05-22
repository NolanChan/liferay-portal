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

package com.liferay.portal.cache.ehcache.internal;

import com.liferay.portal.kernel.io.Deserializer;
import com.liferay.portal.kernel.io.Serializer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.nio.ByteBuffer;

/**
 * @author Tina Tian
 */
public class SerializableObjectWrapper implements Serializable {

	public static Object unwrap(Object object) {
		if (!(object instanceof SerializableObjectWrapper)) {
			return object;
		}

		SerializableObjectWrapper serializableWrapper =
			(SerializableObjectWrapper)object;

		return serializableWrapper._serializable;
	}

	public SerializableObjectWrapper(Serializable serializable) {
		_serializable = serializable;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof SerializableObjectWrapper)) {
			return false;
		}

		SerializableObjectWrapper serializableWrapper =
			(SerializableObjectWrapper)object;

		return _serializable.equals(serializableWrapper._serializable);
	}

	@Override
	public int hashCode() {
		return _serializable.hashCode();
	}

	private void readObject(ObjectInputStream objectInputStream)
		throws ClassNotFoundException, IOException {

		int size = objectInputStream.readInt();

		byte[] data = new byte[size];

		objectInputStream.readFully(data);

		Deserializer deserializer = new Deserializer(ByteBuffer.wrap(data));

		_serializable = deserializer.readObject();
	}

	private void writeObject(ObjectOutputStream objectOutputStream)
		throws IOException {

		Serializer serializer = new Serializer();

		serializer.writeObject(_serializable);

		ByteBuffer byteBuffer = serializer.toByteBuffer();

		objectOutputStream.writeInt(byteBuffer.remaining());
		objectOutputStream.write(
			byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining());
	}

	private Serializable _serializable;

}