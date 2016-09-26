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

package com.liferay.osb.lcs.storage.impl;

import com.liferay.osb.lcs.storage.StorageManager;

/**
 * @author Ivica Cardic
 */
public abstract class BaseStorageManagerImpl implements StorageManager {

	@Override
	public void afterPropertiesSet() {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void setAccessKey(String accessKey) {
	}

	@Override
	public void setBucketName(String bucketName) {
	}

	@Override
	public void setPath(String path) {
	}

	@Override
	public void setPrefix(String prefix) {
	}

	@Override
	public void setSecretKey(String secretKey) {
	}

}