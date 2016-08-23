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

import java.util.Set;

/**
 * @author Ivica Cardic
 */
public interface CacheManager<T> extends MessageListener {

	public void addMessageListener(MessageListener messageListener);

	public void afterPropertiesSet();

	public boolean contains(String key);

	public void destroy();

	public T get(String key);

	public Set<String> getKeys();

	public Set<String> getKeys(String prefix);

	public void put(String key, T value);

	public void remove(String key);

	public void setCacheManager(CacheManager<T> cacheManager);

	public void setMasterHostName(String masterHostNameostName);

	public void setMasterHostPort(int masterHostPort);

	public void setSlaveHostName(String slaveHostName);

	public void setSlaveHostPort(int slaveHostPort);

}