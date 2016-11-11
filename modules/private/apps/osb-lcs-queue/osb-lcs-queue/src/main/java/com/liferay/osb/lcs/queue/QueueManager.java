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
 * @author Igor Beslic
 */
public interface QueueManager {

	public void afterPropertiesSet();

	public void deleteMessage(Message message);

	public void deleteMessages(List<Message> messages);

	public <T extends Message> List<T> getMessages(String queueName);

	public <T extends Message> List<T> getMessages(
		String queueName, Class clazz);

	public <T extends Message> void moveMessageToDeadLetterQueue(T message);

	public <T extends Message> void sendMessage(T message);

	public void setAccessKey(String accessKey);

	public void setDynamicRouter(DynamicRouter dynamicRouter);

	public void setHostName(String hostName);

	public void setHostPort(int hostPort);

	public void setQueuePrefix(String queuePrefix);

	public void setRegion(String region);

	public void setSecretKey(String secretKey);

}