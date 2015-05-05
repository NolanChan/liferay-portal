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

package com.liferay.portal.messaging.internal.jmx;

import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.MessageBus;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

/**
 * @author Michael C. Han
 * @author Brian Wing Shun Chan
 */
public class MessageBusManager implements MessageBusManagerMBean {

	public static ObjectName createObjectName() {
		try {
			return new ObjectName(_OBJECT_NAME);
		}
		catch (MalformedObjectNameException mone) {
			throw new IllegalStateException(mone);
		}
	}

	public MessageBusManager(MessageBus messageBus) {
		_messageBus = messageBus;
	}

	@Override
	public int getDestinationCount() {
		return _messageBus.getDestinationCount();
	}

	@Override
	public int getMessageListenerCount(String destinationName) {
		Destination destination = _messageBus.getDestination(destinationName);

		if (destination == null) {
			return 0;
		}

		return destination.getMessageListenerCount();
	}

	private static final String _OBJECT_NAME =
		"Liferay:product=Portal,type=MessageBusManager,host=localhost";

	private final MessageBus _messageBus;

}