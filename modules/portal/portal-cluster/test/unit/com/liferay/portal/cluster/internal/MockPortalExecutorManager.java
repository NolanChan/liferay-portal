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

package com.liferay.portal.cluster.internal;

import com.liferay.portal.kernel.concurrent.ThreadPoolExecutor;
import com.liferay.portal.kernel.executor.PortalExecutorManager;

/**
 * @author Michael C. Han
 */
public class MockPortalExecutorManager implements PortalExecutorManager {

	@Override
	public ThreadPoolExecutor getPortalExecutor(String name) {
		return new ThreadPoolExecutor(0, 1) {
			@Override
			public void execute(Runnable runnable) {
				runnable.run();
			}

		};
	}

	@Override
	public ThreadPoolExecutor getPortalExecutor(
		String name, boolean createIfAbsent) {

		return new ThreadPoolExecutor(0, 1) {
			@Override
			public void execute(Runnable runnable) {
				runnable.run();
			}

		};
	}

	@Override
	public ThreadPoolExecutor registerPortalExecutor(
		String name, ThreadPoolExecutor threadPoolExecutor) {

		return null;
	}

	@Override
	public void shutdown() {
	}

	@Override
	public void shutdown(boolean interrupt) {
	}

}