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

package com.liferay.portal.kernel.process;

import com.liferay.portal.kernel.concurrent.NoticeableFuture;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.io.Serializable;

/**
 * @author Shuyang Zhou
 */
public class ProcessExecutorUtil {

	public static <T extends Serializable> NoticeableFuture<T> execute(
			ProcessConfig processConfig, ProcessCallable<T> processCallable)
		throws ProcessException {

		PortalRuntimePermission.checkGetBeanProperty(ProcessExecutorUtil.class);

		return _processExecutor.execute(processConfig, processCallable);
	}

	public void setProcessExecutor(ProcessExecutor processExecutor) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_processExecutor = processExecutor;
	}

	private static ProcessExecutor _processExecutor;

}