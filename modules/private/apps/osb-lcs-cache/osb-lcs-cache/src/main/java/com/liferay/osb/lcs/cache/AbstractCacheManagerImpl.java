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

package com.liferay.osb.lcs.cache;

/**
 * @author Ivica Cardic
 */
public abstract class AbstractCacheManagerImpl<T> implements CacheManager<T> {

	@Override
	public boolean contains(String key) {
		if (get(key) != null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void setCacheManager(CacheManager<T> cacheManager) {
	}

	@Override
	public void setMasterHostName(String masterHostName) {
	}

	@Override
	public void setMasterHostPort(int masterHostPort) {
	}

	@Override
	public void setSlaveHostName(String slaveHostName) {
	}

	@Override
	public void setSlaveHostPort(int slaveHostPort) {
	}

}