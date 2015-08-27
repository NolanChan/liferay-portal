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

package com.liferay.portal.cache.cluster.internal.clusterlink;

import com.liferay.portal.cache.cluster.internal.PortalCacheClusterEvent;
import com.liferay.portal.kernel.cluster.ClusterLink;
import com.liferay.portal.kernel.cluster.Priority;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.util.SerializableUtil;

/**
 * @author Shuyang Zhou
 */
public class ClusterLinkPortalCacheClusterChannel
	extends BasePortalCacheClusterChannel {

	public ClusterLinkPortalCacheClusterChannel(
		ClusterLink clusterLink, String destinationName, Priority priority) {

		_clusterLink = clusterLink;
		_destinationName = destinationName;
		_priority = priority;
	}

	@Override
	public void dispatchEvent(PortalCacheClusterEvent portalCacheClusterEvent) {
		Message message = new Message();

		message.setDestinationName(_destinationName);
		message.setPayload(SerializableUtil.serialize(portalCacheClusterEvent));

		_clusterLink.sendMulticastMessage(message, _priority);
	}

	private final ClusterLink _clusterLink;
	private final String _destinationName;
	private final Priority _priority;

}