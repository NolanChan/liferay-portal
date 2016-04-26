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

package com.liferay.server.manager.internal;

import com.liferay.server.manager.Executor;

import org.osgi.service.component.annotations.Component;

/**
 * @author Cindy Li
 */
@Component(
	immediate = true,
	property = {"server.manager.executor.path=/server/freemarker/debug-port"},
	service = Executor.class
)
public class FreeMarkerDebugPortExecutor extends DebugPortExecutor {

	@Override
	protected String getDebugPort() {
		return System.getProperty("freemarker.debug.port");
	}

}