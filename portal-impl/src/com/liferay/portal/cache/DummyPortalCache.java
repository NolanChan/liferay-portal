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

package com.liferay.portal.cache;

import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.PortalCacheListener;
import com.liferay.portal.kernel.cache.PortalCacheListenerScope;
import com.liferay.portal.kernel.cache.PortalCacheManager;

import java.io.Serializable;

import java.util.Collections;
import java.util.List;

/**
 * @author Shuyang Zhou
 */
public class DummyPortalCache<K extends Serializable, V>
	implements PortalCache<K, V> {

	public DummyPortalCache(String portalCacheName) {
		_portalCacheName = portalCacheName;
	}

	@Override
	public V get(K key) {
		return null;
	}

	@Override
	public List<K> getKeys() {
		return Collections.emptyList();
	}

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #getPortalCacheName()}
	 */
	@Deprecated
	@Override
	public String getName() {
		return getPortalCacheName();
	}

	@Override
	public PortalCacheManager<K, V> getPortalCacheManager() {
		return null;
	}

	@Override
	public String getPortalCacheName() {
		return _portalCacheName;
	}

	@Override
	public void put(K key, V value) {
	}

	@Override
	public void put(K key, V value, int timeToLive) {
	}

	@Override
	public void registerPortalCacheListener(
		PortalCacheListener<K, V> portalCacheListener) {
	}

	@Override
	public void registerPortalCacheListener(
		PortalCacheListener<K, V> portalCacheListener,
		PortalCacheListenerScope portalCacheListenerScope) {
	}

	@Override
	public void remove(K key) {
	}

	@Override
	public void removeAll() {
	}

	@Override
	public void unregisterPortalCacheListener(
		PortalCacheListener<K, V> portalCacheListener) {
	}

	@Override
	public void unregisterPortalCacheListeners() {
	}

	private final String _portalCacheName;

}