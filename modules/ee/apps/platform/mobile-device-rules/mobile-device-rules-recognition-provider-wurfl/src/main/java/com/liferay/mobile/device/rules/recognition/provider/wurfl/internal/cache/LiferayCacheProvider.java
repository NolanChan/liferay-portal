/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation; version 3.0 of the License.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 */

package com.liferay.mobile.device.rules.recognition.provider.wurfl.internal.cache;

import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.SingleVMPool;

import net.sourceforge.wurfl.core.InternalDevice;
import net.sourceforge.wurfl.core.cache.CacheProvider;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = CacheProvider.class)
public class LiferayCacheProvider implements CacheProvider {

	@Override
	public void clear() {
		_portalCache.removeAll();
	}

	@Override
	public InternalDevice getDevice(String deviceId) {
		return _portalCache.get(deviceId);
	}

	@Override
	public InternalDevice getInternalDeviceFromDeviceId(String deviceId) {
		return _portalCache.get(deviceId);
	}

	@Override
	public void putDevice(String deviceId, InternalDevice internalDevice) {
		_portalCache.put(deviceId, internalDevice);
	}

	@Reference(unbind = "-")
	protected void setSingleVMPool(SingleVMPool singleVMPool) {
		_portalCache =
			(PortalCache<String, InternalDevice>)singleVMPool.getPortalCache(
				LiferayCacheProvider.class.getName());
	}

	private PortalCache<String, InternalDevice> _portalCache;

}