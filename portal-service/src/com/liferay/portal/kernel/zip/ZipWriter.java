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

package com.liferay.portal.kernel.zip;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public interface ZipWriter {

	public void addEntry(String name, byte[] bytes) throws IOException;

	public void addEntry(String name, InputStream inputStream)
		throws IOException;

	public void addEntry(String name, String s) throws IOException;

	public void addEntry(String name, StringBuilder sb) throws IOException;

	public void close();

	public byte[] finish() throws IOException;

	public File getFile();

	public String getPath();

}