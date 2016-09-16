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

package com.liferay.portal.tools.data.partitioning.sql.builder.db2.exporter.serializer;

import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.serializer.FieldSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

import java.nio.charset.Charset;

import java.sql.Clob;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author Manuel de la Peña
 */
public class DB2FieldSerializer implements FieldSerializer {

	@Override
	public String serialize(Object object) {
		StringBuilder sb = new StringBuilder();

		if (object == null) {
			sb.append("null");
		}
		else if (object instanceof Clob) {
			sb.append("TO_CLOB(");

			try (InputStream inputStream = ((Clob)object).getAsciiStream()) {
				Reader reader = new InputStreamReader(
					inputStream, Charset.forName("UTF-8"));

				StringWriter stringWriter = new StringWriter();

				int c = -1;

				while ((c = reader.read()) != -1) {
					stringWriter.write(c);
				}

				String value = stringWriter.toString();

				sb.append(value);
			}
			catch (IOException | SQLException e) {
				throw new RuntimeException("Unable to read the CLOB value", e);
			}

			sb.append(")");
		}
		else if ((object instanceof Date) || (object instanceof Timestamp)) {
			sb.append("'");
			sb.append(_dateFormat.format(object));
			sb.append("'");
		}
		else if (object instanceof String) {
			String value = (String)object;

			sb.append(value);
		}
		else {
			sb.append("'");
			sb.append(object);
			sb.append("'");
		}

		return sb.toString();
	}

	private final DateFormat _dateFormat = new SimpleDateFormat(
		"YYYY-MM-dd hh:mm:ss.SSS");

}