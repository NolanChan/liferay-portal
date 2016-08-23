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
public class CacheManagerFactory {

	public void destroy() {
		_cacheManager.destroy();
	}

	public CacheManager getCacheManager() {
		try {
			Class<?> clazz = Class.forName(_cacheManagerClassName);

			_cacheManager = wrapCacheManager((CacheManager)clazz.newInstance());

			_cacheManager.setMasterHostName(_masterHostName);
			_cacheManager.setMasterHostPort(_masterHostPort);
			_cacheManager.setSlaveHostName(_slaveHostName);
			_cacheManager.setSlaveHostPort(_slaveHostPort);

			return _cacheManager;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void setCacheManagerClass(
		Class<? extends CacheManager> cacheManagerClass) {

		try {
			_cacheManager = cacheManagerClass.newInstance();
		}
		catch (IllegalAccessException iae) {
			throw new RuntimeException(iae);
		}
		catch (InstantiationException ie) {
			throw new RuntimeException(ie);
		}
	}

	public void setCacheManagerClassName(String cacheManagerClassName) {
		_cacheManagerClassName = cacheManagerClassName;
	}

	public void setMasterHostName(String masterHostName) {
		_masterHostName = masterHostName;
	}

	public void setMasterHostPort(int masterHostPort) {
		_masterHostPort = masterHostPort;
	}

	public void setSlaveHostName(String slaveHostName) {
		_slaveHostName = slaveHostName;
	}

	public void setSlaveHostPort(int slaveHostPort) {
		_slaveHostPort = slaveHostPort;
	}

	private CacheManager wrapCacheManager(CacheManager cacheManager) {
		if (_cacheManager == null) {
			return cacheManager;
		}

		_cacheManager.setCacheManager(cacheManager);

		cacheManager.addMessageListener(_cacheManager);

		return _cacheManager;
	}

	private CacheManager<?> _cacheManager;
	private String _cacheManagerClassName;
	private String _masterHostName;
	private int _masterHostPort;
	private String _slaveHostName;
	private int _slaveHostPort;

}