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

package com.liferay.portal.messaging.internal;

import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationConfiguration;
import com.liferay.portal.kernel.messaging.SynchronousDestination;
import com.liferay.portal.messaging.DestinationPrototype;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true, property = { "type=synchronous" },
	service = DestinationPrototype.class
)
public class SynchronousDestinationPrototype implements DestinationPrototype {

	@Override
	public Destination createDestination(DestinationConfiguration destinationConfig) {
		SynchronousDestination synchronousDestination =
			new SynchronousDestination();

		synchronousDestination.setName(destinationConfig.getDestinationName());

		synchronousDestination.afterPropertiesSet();

		return synchronousDestination;
	}

}