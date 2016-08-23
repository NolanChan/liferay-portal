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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Ivica Cardic
 */
public class ActiveLCSCLusterNodeCacheManager {

	public boolean contains(String key) {
		return _cacheManager.contains("ACTIVE_" + key);
	}

	public Map<String, Object> get(String key) {
		return (Map<String, Object>)_cacheManager.get("ACTIVE_" + key);
	}

	public List<String> getKeys() {
		List<String> keys = new ArrayList<>();

		Set<String> cacheKeys = _cacheManager.getKeys("ACTIVE_");

		for (String cacheKey : cacheKeys) {
			keys.add(cacheKey.replace("ACTIVE_", ""));
		}

		return keys;
	}

	public void put(String key, Map<String, Object> value) {
		_cacheManager.put("ACTIVE_" + key, value);
	}

	public void remove(String key) {
		_cacheManager.remove("ACTIVE_" + key);
	}

	public void setCacheManager(CacheManager cacheManager) {
		_cacheManager = cacheManager;
	}

	private CacheManager _cacheManager;

}