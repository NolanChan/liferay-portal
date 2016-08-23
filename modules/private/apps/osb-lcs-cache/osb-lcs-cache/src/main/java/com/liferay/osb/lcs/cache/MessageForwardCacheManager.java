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
public class MessageForwardCacheManager {

	public boolean contains(String key) {
		return _cacheManager.contains("MESSAGE_FORWARD_" + key);
	}

	public boolean isEnabled(String key) {
		Boolean enabled = (Boolean)_cacheManager.get("MESSAGE_FORWARD_" + key);

		if (enabled == null) {
			return false;
		}

		return enabled;
	}

	public void put(String key, boolean value) {
		_cacheManager.put("MESSAGE_FORWARD_" + key, value);
	}

	public void remove(String key) {
		_cacheManager.remove("MESSAGE_FORWARD_" + key);
	}

	public void setCacheManager(CacheManager cacheManager) {
		_cacheManager = cacheManager;
	}

	private CacheManager _cacheManager;

}