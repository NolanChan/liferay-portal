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

package com.liferay.portal.kernel.cache;

import com.liferay.portal.kernel.util.InitialThreadLocal;

import java.io.Serializable;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Tina Tian
 */
public class AggregatedPortalCacheListener<K extends Serializable, V>
	implements PortalCacheListener<K, V> {

	public static boolean isRemoteInvoke() {
		return _remoteInvokeThreadLocal.get();
	}

	public static void setRemoteInvoke(boolean remoteInvoke) {
		_remoteInvokeThreadLocal.set(remoteInvoke);
	}

	public void addPortalCacheListener(PortalCacheListener<K, V> cacheListener) {
		addPortalCacheListener(cacheListener, PortalCacheListenerScope.ALL);
	}

	public void addPortalCacheListener(
		PortalCacheListener<K, V> cacheListener,
		PortalCacheListenerScope cacheListenerScope) {

		_cacheListeners.putIfAbsent(cacheListener, cacheListenerScope);
	}

	public void clearAll() {
		dispose();

		_cacheListeners.clear();
	}

	@Override
	public void dispose() {
		for (PortalCacheListener<K, V> cacheListener : _cacheListeners.keySet()) {
			cacheListener.dispose();
		}
	}

	public Map<PortalCacheListener<K, V>, PortalCacheListenerScope> getPortalCacheListeners() {
		return Collections.unmodifiableMap(_cacheListeners);
	}

	public boolean isEmpty() {
		return _cacheListeners.isEmpty();
	}

	@Override
	public void notifyEntryEvicted(
			PortalCache<K, V> portalCache, K key, V value, int timeToLive)
		throws PortalCacheException {

		for (Map.Entry<PortalCacheListener<K, V>, PortalCacheListenerScope> entry :
				_cacheListeners.entrySet()) {

			PortalCacheListener<K, V> cacheListener = entry.getKey();

			if (_shouldDeliver(cacheListener, entry.getValue())) {
				cacheListener.notifyEntryEvicted(
					portalCache, key, value, timeToLive);
			}
		}
	}

	@Override
	public void notifyEntryExpired(
			PortalCache<K, V> portalCache, K key, V value, int timeToLive)
		throws PortalCacheException {

		for (Map.Entry<PortalCacheListener<K, V>, PortalCacheListenerScope> entry :
				_cacheListeners.entrySet()) {

			PortalCacheListener<K, V> cacheListener = entry.getKey();

			if (_shouldDeliver(cacheListener, entry.getValue())) {
				cacheListener.notifyEntryExpired(
					portalCache, key, value, timeToLive);
			}
		}
	}

	@Override
	public void notifyEntryPut(
			PortalCache<K, V> portalCache, K key, V value, int timeToLive)
		throws PortalCacheException {

		for (Map.Entry<PortalCacheListener<K, V>, PortalCacheListenerScope> entry :
				_cacheListeners.entrySet()) {

			PortalCacheListener<K, V> cacheListener = entry.getKey();

			if (_shouldDeliver(cacheListener, entry.getValue())) {
				cacheListener.notifyEntryPut(
					portalCache, key, value, timeToLive);
			}
		}
	}

	@Override
	public void notifyEntryRemoved(
			PortalCache<K, V> portalCache, K key, V value, int timeToLive)
		throws PortalCacheException {

		for (Map.Entry<PortalCacheListener<K, V>, PortalCacheListenerScope> entry :
				_cacheListeners.entrySet()) {

			PortalCacheListener<K, V> cacheListener = entry.getKey();

			if (_shouldDeliver(cacheListener, entry.getValue())) {
				cacheListener.notifyEntryRemoved(
					portalCache, key, value, timeToLive);
			}
		}
	}

	@Override
	public void notifyEntryUpdated(
			PortalCache<K, V> portalCache, K key, V value, int timeToLive)
		throws PortalCacheException {

		for (Map.Entry<PortalCacheListener<K, V>, PortalCacheListenerScope> entry :
				_cacheListeners.entrySet()) {

			PortalCacheListener<K, V> cacheListener = entry.getKey();

			if (_shouldDeliver(cacheListener, entry.getValue())) {
				cacheListener.notifyEntryUpdated(
					portalCache, key, value, timeToLive);
			}
		}
	}

	@Override
	public void notifyRemoveAll(PortalCache<K, V> portalCache)
		throws PortalCacheException {

		for (Map.Entry<PortalCacheListener<K, V>, PortalCacheListenerScope> entry :
				_cacheListeners.entrySet()) {

			PortalCacheListener<K, V> cacheListener = entry.getKey();

			if (_shouldDeliver(cacheListener, entry.getValue())) {
				cacheListener.notifyRemoveAll(portalCache);
			}
		}
	}

	public void removePortalCacheListener(PortalCacheListener<K, V> cacheListener) {
		cacheListener.dispose();

		_cacheListeners.remove(cacheListener);
	}

	private boolean _shouldDeliver(
		PortalCacheListener<K, V> cacheListener,
		PortalCacheListenerScope cacheListenerScope) {

		if (_remoteInvokeThreadLocal.get()) {
			if (cacheListener instanceof PortalCacheReplicator) {
				return false;
			}

			if (cacheListenerScope.equals(PortalCacheListenerScope.ALL) ||
				cacheListenerScope.equals(PortalCacheListenerScope.REMOTE)) {

				return true;
			}

			return false;
		}

		if (cacheListenerScope.equals(PortalCacheListenerScope.ALL) ||
			cacheListenerScope.equals(PortalCacheListenerScope.LOCAL)) {

			return true;
		}

		return false;
	}

	private static final ThreadLocal<Boolean> _remoteInvokeThreadLocal =
		new InitialThreadLocal<>(
			AggregatedPortalCacheListener.class + "._remoteInvokeThreadLocal", false);

	private final ConcurrentMap<PortalCacheListener<K, V>, PortalCacheListenerScope>
		_cacheListeners = new ConcurrentHashMap<>();

}