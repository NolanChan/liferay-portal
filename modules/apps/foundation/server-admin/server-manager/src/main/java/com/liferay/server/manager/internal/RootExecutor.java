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

import com.liferay.server.manager.BaseExecutor;
import com.liferay.server.manager.Executor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class RootExecutor extends BaseExecutor {

	@Override
	protected Map<String, Executor> initNextExecutors() {
		Map<String, Executor> executors = new HashMap<>();

		executors.put("plugins", new PluginsExecutor());
		executors.put("server", new ServerExecutor());
		executors.put("status", new StatusExecutor());

		return executors;
	}

}