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

package com.liferay.portal.workflow.kaleo.hook.upgrade;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.workflow.kaleo.hook.upgrade.v1_2_0.UpgradeKaleoLog;
import com.liferay.portal.workflow.kaleo.hook.upgrade.v1_2_0.UpgradeKaleoNotificationRecipient;

/**
 * @author Michael C. Han
 */
public class UpgradeProcess_1_2_0 extends UpgradeProcess {

	@Override
	public int getThreshold() {
		return 120;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgrade(UpgradeKaleoLog.class);
		upgrade(UpgradeKaleoNotificationRecipient.class);
	}

}