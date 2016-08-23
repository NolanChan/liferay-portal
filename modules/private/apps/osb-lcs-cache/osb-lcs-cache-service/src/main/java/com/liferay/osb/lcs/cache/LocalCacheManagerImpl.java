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

import com.liferay.portal.kernel.messaging.BaseDestination;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.messaging.ParallelDestination;
import com.liferay.portal.kernel.servlet.ServletContextPool;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

/**
 * @author Ivica Cardic
 */
public class LocalCacheManagerImpl<T> extends AbstractCacheManagerImpl<T> {

	public LocalCacheManagerImpl() {
		_servletContext = ServletContextPool.get("");

		MessageBus messageBus = MessageBusUtil.getMessageBus();

		if (!messageBus.hasDestination("liferay/osb_lcs_cache")) {
			BaseDestination baseDestination = new ParallelDestination();

			baseDestination.setName("liferay/osb_lcs_cache");

			messageBus.addDestination(baseDestination);
		}

		messageBus.registerMessageListener(
			"liferay/osb_lcs_cache",
			new com.liferay.portal.kernel.messaging.MessageListener() {

				@Override
				public void receive(Message message)
					throws MessageListenerException {

					for (MessageListener messageListener : _messageListeners) {
						try {
							messageListener.onMessage(
								"remove", (String)message.getPayload());
						}
						catch (Exception e) {
						}
					}
				}

			});
	}

	@Override
	public void addMessageListener(MessageListener messageListener) {
		_messageListeners.add(messageListener);
	}

	public void afterPropertiesSet() {
	}

	@Override
	public void destroy() {
		_messageListeners.clear();
	}

	@Override
	public T get(String key) {
		return (T)_servletContext.getAttribute(_getAttributeName(key));
	}

	@Override
	public Set<String> getKeys() {
		Set<String> keys = new HashSet<>();

		Enumeration<String> enumeration = _servletContext.getAttributeNames();

		while (enumeration.hasMoreElements()) {
			String element = enumeration.nextElement();

			if (element.contains("#key#")) {
				keys.add(_getKey(element));
			}
		}

		return keys;
	}

	@Override
	public Set<String> getKeys(String prefix) {
		Set<String> keys = new HashSet<>();

		Enumeration<String> enumeration = _servletContext.getAttributeNames();

		while (enumeration.hasMoreElements()) {
			String element = enumeration.nextElement();

			if (element.startsWith(prefix) && element.contains("#key#")) {
				keys.add(_getKey(element));
			}
		}

		return keys;
	}

	@Override
	public void onMessage(String channelName, String message) {
	}

	@Override
	public void put(String key, T value) {
		_servletContext.setAttribute(_getAttributeName(key), value);

		MessageBusUtil.sendMessage(
			"liferay/osb_lcs_cache", _getAttributeName(key));
	}

	@Override
	public void remove(String key) {
		_servletContext.removeAttribute(_getAttributeName(key));

		MessageBusUtil.sendMessage(
			"liferay/osb_lcs_cache", _getAttributeName(key));
	}

	private String _getAttributeName(String key) {
		return LocalCacheManagerImpl.class.getName() + "#key#" + key;
	}

	private String _getKey(String attributeName) {
		return attributeName.replace(
			LocalCacheManagerImpl.class.getName() + "#key#", "");
	}

	private final List<MessageListener> _messageListeners = new ArrayList<>();
	private final ServletContext _servletContext;

}