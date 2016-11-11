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
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.SingleVMPoolUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class LocalQueueManagerImpl extends AbstractQueueManagerImpl {

	public LocalQueueManagerImpl() {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		try {
			currentThread.setContextClassLoader(
				PortalClassLoaderUtil.getClassLoader());

			_portalCache = SingleVMPoolUtil.getPortalCache(
				LocalQueueManagerImpl.class.getName());
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}
	}

	@Override
	public void deleteMessage(Message message) {
	}

	@Override
	public void deleteMessages(List<Message> messages) {
	}

	@Override
	public <T extends Message> List<T> getMessages(
		String queueName, Class clazz) {

		List<T> messages = new ArrayList<>();

		Queue<String> queue = getQueue(queueName);

		if (!queue.isEmpty()) {
			messages = new ArrayList<>();

			int counter = 0;

			while (!queue.isEmpty() && (counter < Integer.MAX_VALUE)) {
				T message = Message.fromJSON(queue.poll(), clazz);

				messages.add(message);
			}
		}

		try {
			Thread.sleep(500);
		}
		catch (InterruptedException ie) {
		}

		return messages;
	}

	@Override
	public <T extends Message> void sendMessage(T message) {
		String queueName = getQueueName(message);

		Queue<String> queue = getQueue(queueName);

		queue.add(message.toJSON());
	}

	protected Queue<String> getQueue(String queueName) {
		Queue<String> queue = _portalCache.get(queueName);

		if (queue == null) {
			queue = new ConcurrentLinkedQueue<>();

			_portalCache.put(queueName, queue);
		}

		return queue;
	}

	private final PortalCache<String, Queue<String>> _portalCache;

}