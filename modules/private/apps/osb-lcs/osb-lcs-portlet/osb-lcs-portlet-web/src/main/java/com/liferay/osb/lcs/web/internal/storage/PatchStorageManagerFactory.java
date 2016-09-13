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
public class PatchStorageManagerFactory {

	public PatchStorageManager getInstance() throws Exception {
		if (_patchStorageManager != null) {
			return _patchStorageManager;
		}

		Class<?> storageManagerClass = Class.forName(_storageManagerClassName);

		PatchStorageManager storageManager =
			(PatchStorageManager)storageManagerClass.newInstance();

		storageManager.setAccessKey(_accessKey);
		storageManager.setBucketName(_bucketName);
		storageManager.setContextPath(_contextPath);
		storageManager.setDownloadHostName(_downloadHostName);
		storageManager.setDownloadHostPort(_downloadHostPort);
		storageManager.setHostName(_hostName);
		storageManager.setHostPort(_hostPort);
		storageManager.setPath(_path);
		storageManager.setPrefix(_prefix);
		storageManager.setSecretKey(_secretKey);

		_patchStorageManager = storageManager;

		return _patchStorageManager;
	}

	public void setAccessKey(String accessKey) {
		_accessKey = accessKey;
	}

	public void setBucketName(String bucketName) {
		_bucketName = bucketName;
	}

	public void setContextPath(String contextPath) {
		_contextPath = contextPath;
	}

	public void setDownloadHostName(String downloadHostName) {
		_downloadHostName = downloadHostName;
	}

	public void setDownloadHostPort(int downloadHostPort) {
		_downloadHostPort = downloadHostPort;
	}

	public void setHostName(String hostName) {
		_hostName = hostName;
	}

	public void setHostPort(int hostPort) {
		_hostPort = hostPort;
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
	private String _contextPath;
	private String _downloadHostName;
	private int _downloadHostPort;
	private String _hostName;
	private int _hostPort;
	private PatchStorageManager _patchStorageManager;
	private String _path;
	private String _prefix;
	private String _secretKey;
	private String _storageManagerClassName;

}