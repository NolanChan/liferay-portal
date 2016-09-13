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

package com.liferay.osb.lcs.storage;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.File;

/**
 * @author Mladen Cikara
 */
public class LogFileSystemStorageManagerImpl
	extends BaseStorageManagerImpl implements LogStorageManager {

	@Override
	public void setPath(String path) {
		_path = path;
	}

	@Override
	public void writeLogFile(String key, String message, String throwable) {
		StringBundler sb = new StringBundler(_path);

		sb.append(File.separator);
		sb.append(key);
		sb.append("_");
		sb.append(String.valueOf(System.currentTimeMillis()));
		sb.append(".log");

		File file = new File(sb.toString());

		try {
			FileUtil.write(file, "[" + key + "]-", true, true);
			FileUtil.write(file, message + "-", true, true);
			FileUtil.write(file, throwable, true, true);
		}
		catch (Exception e) {
			_log.error(e.getMessage());

			throw new RuntimeException(e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		LogFileSystemStorageManagerImpl.class);

	private String _path;

}