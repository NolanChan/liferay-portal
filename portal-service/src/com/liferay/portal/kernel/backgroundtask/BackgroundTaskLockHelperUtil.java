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

package com.liferay.portal.kernel.backgroundtask;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.BackgroundTask;
import com.liferay.portal.model.Lock;
import com.liferay.portal.service.LockLocalServiceUtil;

/**
 * @author Daniel Kocsis
 */
@ProviderType
public class BackgroundTaskLockHelperUtil {

	public static boolean isLockedBackgroundTask(BackgroundTask backgroundTask)
		throws PortalException {

		return LockLocalServiceUtil.isLocked(
			BackgroundTaskExecutor.class.getName(), getLockKey(backgroundTask));
	}

	public static Lock lockBackgroundTask(BackgroundTask backgroundTask) {
		String owner =
			backgroundTask.getName() + StringPool.POUND +
				backgroundTask.getBackgroundTaskId();

		return LockLocalServiceUtil.lock(
			BackgroundTaskExecutor.class.getName(), getLockKey(backgroundTask),
			owner);
	}

	public static void unlockBackgroundTask(BackgroundTask backgroundTask)
		throws PortalException {

		String owner =
			backgroundTask.getName() + StringPool.POUND +
				backgroundTask.getBackgroundTaskId();

		LockLocalServiceUtil.unlock(
			BackgroundTaskExecutor.class.getName(), getLockKey(backgroundTask),
			owner);
	}

	protected static String getLockKey(BackgroundTask backgroundTask) {
		BackgroundTaskExecutor backgroundTaskExecutor =
			backgroundTask.getBackgroundTaskExecutor();

		String lockKey = StringPool.BLANK;

		if (backgroundTaskExecutor.getIsolationLevel() ==
				BackgroundTaskConstants.ISOLATION_LEVEL_CLASS) {

			lockKey = backgroundTask.getTaskExecutorClassName();
		}
		else if (backgroundTaskExecutor.getIsolationLevel() ==
					BackgroundTaskConstants.ISOLATION_LEVEL_COMPANY) {

			lockKey =
				backgroundTask.getTaskExecutorClassName() +
					StringPool.POUND + backgroundTask.getCompanyId();
		}
		else if (backgroundTaskExecutor.getIsolationLevel() ==
					BackgroundTaskConstants.ISOLATION_LEVEL_GROUP) {

			lockKey =
				backgroundTask.getTaskExecutorClassName() +
					StringPool.POUND + backgroundTask.getGroupId();
		}
		else {
			lockKey =
				backgroundTask.getTaskExecutorClassName() +
					StringPool.POUND +
						backgroundTaskExecutor.getIsolationLevel();
		}

		return lockKey;
	}

}