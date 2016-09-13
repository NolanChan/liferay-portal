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

package com.liferay.osb.lcs.web.internal.storage;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class LogStorageManagerFactory {

	public LogStorageManager getInstance() throws Exception {
		if (_logStorageManager != null) {
			return _logStorageManager;
		}

		Class<?> storageManagerClass = Class.forName(_storageManagerClassName);

		LogStorageManager storageManager =
			(LogStorageManager)storageManagerClass.newInstance();

		storageManager.setAccessKey(_accessKey);
		storageManager.setBucketName(_bucketName);
		storageManager.setPath(_path);
		storageManager.setPrefix(_prefix);
		storageManager.setSecretKey(_secretKey);

		_logStorageManager = storageManager;

		return _logStorageManager;
	}

	public void setAccessKey(String accessKey) {
		_accessKey = accessKey;
	}

	public void setBucketName(String bucketName) {
		_bucketName = bucketName;
	}

	public void setPath(String path) {
		_path = path;
	}

	public void setPrefix(String prefix) {
		_prefix = prefix;
	}

	public void setSecretKey(String secretKey) {
		_secretKey = secretKey;
	}

	public void setStorageManagerClassName(String storageManagerClassName) {
		_storageManagerClassName = storageManagerClassName;
	}

	private String _accessKey;
	private String _bucketName;
	private LogStorageManager _logStorageManager;
	private String _path;
	private String _prefix;
	private String _secretKey;
	private String _storageManagerClassName;

}