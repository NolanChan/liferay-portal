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

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public enum Queue {

	CLOUD_ANALYTICS_EVENTS(-1, "cloud-analytics-events", -1, -1),
	CLOUD_HANDSHAKE(60, "cloud-handshake", 20, -1),
	CLOUD_HEARTBEAT(60, "cloud-heartbeat", 20, -1),
	CLOUD_IN(60, "cloud-in-", -1, -1),
	CLOUD_LCS_MESSAGE(-1, "cloud-lcs-message", -1, -1),
	CLOUD_LCS_SUBSCRIPTION_ENTRY(-1, "cloud-lcs-subscription-entry", -1, -1),
	CLOUD_MESSAGE_BUS(-1, "cloud-message-bus", -1, -1),
	CLOUD_OUT(-1, "cloud-out", 20, 300),
	CLOUD_OUT_DEAD_LETTER(-1, "cloud-out-dead-letter", -1, -1),
	CLOUD_TEST(-1, "cloud-test", -1, -1);

	Queue(
		int messageRetentionPeriod, String name,
		int receiveMessageWaitTimeSeconds, int visibilityTimeout) {

		_messageRetentionPeriod = messageRetentionPeriod;
		_name = name;
		_receiveMessageWaitTimeSeconds = receiveMessageWaitTimeSeconds;
		_visibilityTimeout = visibilityTimeout;
	}

	public static Queue toQueue(String name) {
		if (name.equals(CLOUD_ANALYTICS_EVENTS.getName())) {
			return CLOUD_ANALYTICS_EVENTS;
		}
		else if (name.endsWith(CLOUD_HANDSHAKE.getName())) {
			return CLOUD_HANDSHAKE;
		}
		else if (name.endsWith(CLOUD_HEARTBEAT.getName())) {
			return CLOUD_HEARTBEAT;
		}
		else if (name.contains(CLOUD_IN.getName())) {
			return CLOUD_IN;
		}
		else if (name.endsWith(CLOUD_LCS_MESSAGE.getName())) {
			return CLOUD_LCS_MESSAGE;
		}
		else if (name.endsWith(CLOUD_LCS_SUBSCRIPTION_ENTRY.getName())) {
			return CLOUD_LCS_SUBSCRIPTION_ENTRY;
		}
		else if (name.endsWith(CLOUD_MESSAGE_BUS.getName())) {
			return Queue.CLOUD_MESSAGE_BUS;
		}
		else if (name.endsWith(CLOUD_OUT.getName())) {
			return CLOUD_OUT;
		}
		else if (name.endsWith(CLOUD_OUT_DEAD_LETTER.getName())) {
			return CLOUD_OUT_DEAD_LETTER;
		}
		else if (name.endsWith(CLOUD_TEST.getName())) {
			return CLOUD_TEST;
		}

		throw new RuntimeException(
			"Queue with name " + name + " does not exists");
	}

	public String concat(String key) {
		return _name.concat(key);
	}

	public int getMessageRetentionPeriod() {
		return _messageRetentionPeriod;
	}

	public String getName() {
		return _name;
	}

	public int getReceiveMessageWaitTimeSeconds() {
		return _receiveMessageWaitTimeSeconds;
	}

	public int getVisibilityTimeout() {
		return _visibilityTimeout;
	}

	private int _messageRetentionPeriod;
	private String _name;
	private int _receiveMessageWaitTimeSeconds;
	private int _visibilityTimeout;

}