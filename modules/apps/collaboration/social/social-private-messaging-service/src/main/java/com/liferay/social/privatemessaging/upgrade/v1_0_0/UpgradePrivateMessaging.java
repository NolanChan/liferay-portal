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

package com.liferay.social.privatemessaging.upgrade.v1_0_0;

import com.liferay.message.boards.kernel.service.MBThreadLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.social.privatemessaging.model.PrivateMessagingConstants;
import com.liferay.social.privatemessaging.model.UserThread;
import com.liferay.social.privatemessaging.service.UserThreadLocalService;

import java.util.List;

/**
 * @author Scott Lee
 */
public class UpgradePrivateMessaging extends UpgradeProcess {

	public UpgradePrivateMessaging(
		MBThreadLocalService mBThreadLocalService,
		UserThreadLocalService userThreadLocalService) {

		_mBThreadLocalService = mBThreadLocalService;
		_userThreadLocalService = userThreadLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		List<UserThread> userThreads = _userThreadLocalService.getUserThreads(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (UserThread userThread : userThreads) {
			_mBThreadLocalService.moveThread(
				GroupConstants.DEFAULT_PARENT_GROUP_ID,
				PrivateMessagingConstants.PRIVATE_MESSAGING_CATEGORY_ID,
				userThread.getMbThreadId());
		}
	}

	private final MBThreadLocalService _mBThreadLocalService;
	private final UserThreadLocalService _userThreadLocalService;

}