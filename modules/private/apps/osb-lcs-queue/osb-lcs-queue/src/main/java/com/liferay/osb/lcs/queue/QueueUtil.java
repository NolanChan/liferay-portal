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
public class QueueUtil {

	public static String getHandshakeQueueName() {
		return _CLOUD_HANDSHAKE;
	}

	public static String getHeartbeatQueueName() {
		return _CLOUD_HEARTBEAT;
	}

	public static String getInQueueName(String key) {
		return _CLOUD_IN.concat(key);
	}

	public static String getLCSMesageQueueName() {
		return _CLOUD_LCS_MESSAGE;
	}

	public static String getLCSSubscriptionEntryQueueName() {
		return _CLOUD_LCS_SUBSCRIPTION_ENTRY;
	}

	public static String getOutDeadLetterQueueName() {
		return _CLOUD_OUT_DEAD_LETTER;
	}

	public static String getOutQueueName() {
		return _CLOUD_OUT;
	}

	public static String getOutQueueName(String key) {
		return _CLOUD_OUT.concat(key);
	}

	public static String getTestQueueName() {
		return _CLOUD_TEST;
	}

	public static boolean isHandshakeQueue(String queueName) {
		return _CLOUD_HANDSHAKE.equals(queueName);
	}

	public static boolean isHeartbeatQueue(String queueName) {
		return _CLOUD_HEARTBEAT.equals(queueName);
	}

	public static boolean isInQueue(String queueName) {
		if (queueName == null) {
			return false;
		}

		return queueName.contains(_CLOUD_IN);
	}

	public static boolean isOutQueue(String queueName) {
		if (queueName == null) {
			return false;
		}

		return queueName.contains(_CLOUD_OUT);
	}

	public static boolean isTestQueue(String queueName) {
		if (queueName == null) {
			return false;
		}

		return queueName.contains(_CLOUD_TEST);
	}

	private static final String _CLOUD_HANDSHAKE = "cloud-handshake";

	private static final String _CLOUD_HEARTBEAT = "cloud-heartbeat";

	private static final String _CLOUD_IN = "cloud-in-";

	private static final String _CLOUD_LCS_MESSAGE = "cloud-lcs-message";

	private static final String _CLOUD_LCS_SUBSCRIPTION_ENTRY =
		"cloud-lcs-subscription-entry";

	private static final String _CLOUD_OUT = "cloud-out";

	private static final String _CLOUD_OUT_DEAD_LETTER =
		"cloud-out-dead-letter";

	private static final String _CLOUD_TEST = "cloud-test";

}