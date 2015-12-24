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

package com.liferay.portal.servlet.jsp.compiler.internal;

import com.liferay.portal.kernel.util.ReflectionUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.URL;

/**
 * @author Shuyang Zhou
 */
public class TldURIUtil {

	public static String getTldURI(URL url) {
		try (InputStream inputStream = url.openStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
				inputStream);
			BufferedReader bufferedReader = new BufferedReader(
				inputStreamReader)) {

			StringBuilder sb = null;

			String line = null;

			while ((line = bufferedReader.readLine()) != null) {
				if (sb == null) {
					int x = line.indexOf("<uri>");

					if (x < 0) {
						continue;
					}

					x += 5;

					int y = line.indexOf("</uri>", x);

					if (y >= 0) {
						return line.substring(x, y);
					}

					sb = new StringBuilder(line.substring(x));
				}
				else {
					int y = line.indexOf("</uri>");

					if (y >= 0) {
						sb.append(line.substring(0, y));

						return sb.toString();
					}

					sb.append(line);
				}
			}

			return null;
		}
		catch (IOException ioe) {
			return ReflectionUtil.throwException(ioe);
		}
	}

}