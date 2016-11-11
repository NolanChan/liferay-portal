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

package com.liferay.osb.lcs.queue;

import com.liferay.lcs.messaging.Message;

import java.util.List;

/**
 * @author Ivica Cardic
 */
public abstract class AbstractQueueManagerImpl implements QueueManager {

	@Override
	public void afterPropertiesSet() {
	}

	public <T extends Message> List<T> getMessages(String queueName) {
		return getMessages(queueName, Message.class);
	}

	@Override
	public <T extends Message> void moveMessageToDeadLetterQueue(T message) {
	}

	@Override
	public void setAccessKey(String accessKey) {
	}

	@Override
	public void setDynamicRouter(DynamicRouter dynamicRouter) {
		_dynamicRouter = dynamicRouter;
	}

	@Override
	public void setHostName(String hostName) {
	}

	@Override
	public void setHostPort(int hostPort) {
	}

	@Override
	public void setQueuePrefix(String queuePrefix) {
		_queuePrefix = queuePrefix;
	}

	@Override
	public void setRegion(String region) {
	}

	@Override
	public void setSecretKey(String secretKey) {
	}

	protected String getPrefixedQueueName(String queueName) {
		if ((_queuePrefix == null) || _queuePrefix.equals("")) {
			return queueName;
		}

		return _queuePrefix.concat("-").concat(queueName);
	}

	protected <T> String getQueueName(T message) {
		return _dynamicRouter.getQueueName(message);
	}

	private DynamicRouter _dynamicRouter;
	private String _queuePrefix;

}