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

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.GetResponse;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
public class RabbitMQQueueManagerImpl extends AbstractQueueManagerImpl {

	@Override
	public void deleteMessage(Message message) {
		List<Message> messages = new ArrayList<>();

		messages.add(message);

		deleteMessages(messages);
	}

	@Override
	public void deleteMessages(List<Message> messages) {
		for (Message message : messages) {
			Channel channel = getChannel(message.getQueueName());

			try {
				RabbitMQTransportMetadata trasnportMetadata =
					message.getTransportMetadata();

				channel.basicAck(trasnportMetadata.getDeliveryTag(), false);
			}
			catch (IOException ioe) {
				throw new RuntimeException(ioe);
			}
		}
	}

	@Override
	public <T extends Message> List<T> getMessages(
		String queueName, Class clazz) {

		List<T> messages = new ArrayList<>();

		try {
			Channel channel = getChannel(queueName);

			for (int i = 0; i < _PREFETCH_COUNT; i++) {
				GetResponse getResponse = channel.basicGet(queueName, false);

				if (getResponse == null) {
					continue;
				}

				String body = new String(getResponse.getBody());

				T message = Message.fromJSON(body, clazz);

				message.setQueueName(queueName);

				Envelope envelope = getResponse.getEnvelope();

				RabbitMQTransportMetadata rabbitMQTransportMetadata =
					new RabbitMQTransportMetadata();

				rabbitMQTransportMetadata.setDeliveryTag(
					envelope.getDeliveryTag());

				message.setTransportMetadata(rabbitMQTransportMetadata);

				messages.add(message);

				if (getResponse.getMessageCount() == 0) {
					break;
				}
			}
		}
		catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}

		return messages;
	}

	@Override
	public <T extends Message> void sendMessage(T message) {
		String queueName = getQueueName(message);

		try {
			Connection connection = getConnection();

			Channel channel = connection.createChannel();

			channel.queueDeclare(queueName, true, false, false, null);

			String messageJSON = message.toJSON();

			channel.basicPublish(
				"", queueName, MessageProperties.PERSISTENT_TEXT_PLAIN,
				messageJSON.getBytes());

			channel.close();
		}
		catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}

	@Override
	public void setHostName(String hostName) {
		_hostName = hostName;
	}

	@Override
	public void setHostPort(int hostPort) {
		_hostPort = hostPort;
	}

	protected Channel getChannel(String queueName) {
		Channel channel = _channels.get(queueName);

		if ((channel == null) || !channel.isOpen()) {
			try {
				Connection connection = getConnection();

				channel = connection.createChannel();

				channel.basicQos(_PREFETCH_COUNT);
				channel.queueDeclare(queueName, true, false, false, null);

				_channels.put(queueName, channel);
			}
			catch (IOException ioe) {
				throw new RuntimeException(ioe);
			}
		}

		return channel;
	}

	protected Connection getConnection() {
		if ((_connection == null) || !_connection.isOpen()) {
			ConnectionFactory connectionFactory = new ConnectionFactory();

			connectionFactory.setHost(_hostName);
			connectionFactory.setPort(_hostPort);

			try {
				_connection = connectionFactory.newConnection();
			}
			catch (IOException ioe) {
				throw new RuntimeException(ioe);
			}
		}

		return _connection;
	}

	private static final int _PREFETCH_COUNT = 10;

	private final Map<String, Channel> _channels = new HashMap<>();
	private Connection _connection;
	private String _hostName;
	private int _hostPort;

}