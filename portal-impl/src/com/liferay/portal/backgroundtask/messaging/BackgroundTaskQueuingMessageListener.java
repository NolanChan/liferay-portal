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

package com.liferay.portal.backgroundtask.messaging;

import com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskExecutor;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BackgroundTask;
import com.liferay.portal.service.BackgroundTaskLocalService;
import com.liferay.portal.util.ClassLoaderUtil;

/**
 * @author Michael C. Han
 */
public class BackgroundTaskQueuingMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		String taskExecutorClassName = (String)message.get(
			"taskExecutorClassName");

		if (Validator.isNull(taskExecutorClassName)) {
			return;
		}

		int status = (Integer)message.get("status");

		if ((status == BackgroundTaskConstants.STATUS_CANCELLED) ||
			(status == BackgroundTaskConstants.STATUS_FAILED) ||
			(status == BackgroundTaskConstants.STATUS_SUCCESSFUL)) {

			executeQueuedBackgroundTasks(taskExecutorClassName);
		}
		else if (status == BackgroundTaskConstants.STATUS_QUEUED) {
			long backgroundTaskId = (Long)message.get("backgroundTaskId");

			BackgroundTask backgroundTask =
				_backgroundTaskLocalService.fetchBackgroundTask(
					backgroundTaskId);

			BackgroundTaskExecutor backgroundTaskExecutor =
				getBackgroundTaskExecutor(backgroundTask);

			boolean locked = backgroundTaskExecutor.isLocked(backgroundTask);

			if (!locked) {
				executeQueuedBackgroundTasks(taskExecutorClassName);
			}
		}
	}

	private void executeQueuedBackgroundTasks(String taskExecutorClassName) {
		BackgroundTask backgroundTask =
			_backgroundTaskLocalService.fetchFirstBackgroundTask(
				taskExecutorClassName, BackgroundTaskConstants.STATUS_QUEUED);

		if (backgroundTask == null) {
			return;
		}

		_backgroundTaskLocalService.resumeBackgroundTask(
			backgroundTask.getBackgroundTaskId());
	}

	private BackgroundTaskExecutor getBackgroundTaskExecutor(
			BackgroundTask backgroundTask)
		throws Exception {

		ClassLoader classLoader = ClassLoaderUtil.getPortalClassLoader();

		String servletContextNames = backgroundTask.getServletContextNames();

		if (Validator.isNotNull(servletContextNames)) {
			classLoader = ClassLoaderUtil.getAggregatePluginsClassLoader(
				StringUtil.split(servletContextNames), false);
		}

		BackgroundTaskExecutor backgroundTaskExecutor =
			(BackgroundTaskExecutor)InstanceFactory.newInstance(
				classLoader, backgroundTask.getTaskExecutorClassName());

		return backgroundTaskExecutor;
	}

	@BeanReference(type = BackgroundTaskLocalService.class)
	private BackgroundTaskLocalService _backgroundTaskLocalService;

}