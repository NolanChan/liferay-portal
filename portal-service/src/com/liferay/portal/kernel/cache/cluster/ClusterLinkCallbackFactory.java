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

package com.liferay.portal.kernel.cache.cluster;

import com.liferay.portal.kernel.cache.PortalCacheBootstrapLoader;
import com.liferay.portal.kernel.cache.PortalCacheListener;
import com.liferay.portal.kernel.cache.PortalCacheManagerListener;
import com.liferay.portal.kernel.cache.CallbackFactory;
import com.liferay.portal.kernel.cache.bootstrap.ClusterLinkPortalCacheBootstrapLoader;

import java.io.Serializable;

import java.util.Properties;

/**
 * @author Tina Tian
 */
public class ClusterLinkCallbackFactory implements CallbackFactory {

	public static final CallbackFactory INSTANCE =
		new ClusterLinkCallbackFactory();

	@Override
	public PortalCacheBootstrapLoader createBootstrapLoader(Properties properties) {
		return new ClusterLinkPortalCacheBootstrapLoader(properties);
	}

	@Override
	public <K extends Serializable, V> PortalCacheListener<K, V> createCacheListener(
		Properties properties) {

		return (PortalCacheListener<K, V>)
			new ClusterLinkPortalCacheReplicator<K, Serializable>(properties);
	}

	@Override
	public PortalCacheManagerListener createCacheManagerListener(
		Properties properties) {

		throw new UnsupportedOperationException();
	}

	private ClusterLinkCallbackFactory() {
	}

}