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

package com.liferay.portal.cache.ehcache;

import com.liferay.portal.kernel.cache.AbstractPortalCache;
import com.liferay.portal.kernel.cache.CacheListener;
import com.liferay.portal.kernel.cache.CacheListenerScope;

import java.io.Serializable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.NotificationScope;
import net.sf.ehcache.event.RegisteredEventListeners;

/**
 * @author Brian Wing Shun Chan
 * @author Edward Han
 * @author Shuyang Zhou
 */
public class EhcachePortalCache<K extends Serializable, V>
	extends AbstractPortalCache<K, V> {

	public EhcachePortalCache(Ehcache ehcache) {
		this.ehcache = ehcache;
	}

	@Override
	public String getName() {
		return ehcache.getName();
	}

	@Override
	public void registerCacheListener(CacheListener<K, V> cacheListener) {
		registerCacheListener(cacheListener, CacheListenerScope.ALL);
	}

	@Override
	public void registerCacheListener(
		CacheListener<K, V> cacheListener,
		CacheListenerScope cacheListenerScope) {

		if (_cacheEventListeners.containsKey(cacheListener)) {
			return;
		}

		CacheEventListener cacheEventListener =
			new PortalCacheCacheEventListener<K, V>(cacheListener, this);

		NotificationScope notificationScope = getNotificationScope(
			cacheListenerScope);

		_cacheEventListeners.put(
			cacheListener,
			new RegistrationPair(cacheEventListener, notificationScope));

		RegisteredEventListeners registeredEventListeners =
			ehcache.getCacheEventNotificationService();

		registeredEventListeners.registerListener(
			cacheEventListener, notificationScope);
	}

	@Override
	public void removeAll() {
		ehcache.removeAll();
	}

	public void setEhcache(Ehcache ehcache) {
		this.ehcache = ehcache;

		RegisteredEventListeners registeredEventListeners =
			ehcache.getCacheEventNotificationService();

		for (RegistrationPair registrationPair :
				_cacheEventListeners.values()) {

			registeredEventListeners.registerListener(
				registrationPair._cacheEventListener,
				registrationPair._notificationScope);
		}
	}

	@Override
	public void unregisterCacheListener(CacheListener<K, V> cacheListener) {
		RegistrationPair registrationPair = _cacheEventListeners.remove(
			cacheListener);

		if (registrationPair == null) {
			return;
		}

		RegisteredEventListeners registeredEventListeners =
			ehcache.getCacheEventNotificationService();

		registeredEventListeners.unregisterListener(
			registrationPair._cacheEventListener);
	}

	@Override
	public void unregisterCacheListeners() {
		RegisteredEventListeners registeredEventListeners =
			ehcache.getCacheEventNotificationService();

		for (RegistrationPair registrationPair :
				_cacheEventListeners.values()) {

			registeredEventListeners.unregisterListener(
				registrationPair._cacheEventListener);
		}

		_cacheEventListeners.clear();
	}

	@Override
	protected V doGet(K key) {
		Element element = ehcache.get(key);

		if (element == null) {
			return null;
		}
		else {
			return (V)element.getObjectValue();
		}
	}

	@Override
	protected void doPut(K key, V value, int timeToLive, boolean quiet) {
		Element element = new Element(key, value);

		if (timeToLive != DEFAULT_TIME_TO_LIVE) {
			element.setTimeToLive(timeToLive);
		}

		if (quiet) {
			ehcache.putQuiet(element);
		}
		else {
			ehcache.put(element);
		}
	}

	@Override
	protected void doRemove(K key) {
		ehcache.remove(key);
	}

	protected NotificationScope getNotificationScope(
		CacheListenerScope cacheListenerScope) {

		if (cacheListenerScope.equals(CacheListenerScope.ALL)) {
			return NotificationScope.ALL;
		}
		else if (cacheListenerScope.equals(CacheListenerScope.LOCAL)) {
			return NotificationScope.LOCAL;
		}
		else {
			return NotificationScope.REMOTE;
		}
	}

	protected Ehcache ehcache;

	private Map<CacheListener<K, V>, RegistrationPair> _cacheEventListeners =
		new ConcurrentHashMap<CacheListener<K, V>, RegistrationPair>();

	private static class RegistrationPair {

		public RegistrationPair(
			CacheEventListener cacheEventListener,
			NotificationScope notificationScope) {

			_cacheEventListener = cacheEventListener;
			_notificationScope = notificationScope;
		}

		private CacheEventListener _cacheEventListener;
		private NotificationScope _notificationScope;

	}

}