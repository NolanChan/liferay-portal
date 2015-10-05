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

package com.liferay.portal.lock.upgrade.v1_0_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.lock.upgrade.v1_0_0.util.LockTable;

import java.sql.SQLException;

/**
 * @author Miguel Pastor
 */
public class UpgradeLock extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		runSQL("alter table Lock_ add mvccVersion LONG default 0");

		try {
			runSQL("alter_column_type Lock_ owner VARCHAR(1024) null");
		}
		catch (SQLException sqle) {
			upgradeTable(
				LockTable.TABLE_NAME, LockTable.TABLE_COLUMNS,
				LockTable.TABLE_SQL_CREATE, LockTable.TABLE_SQL_ADD_INDEXES);
		}
	}

}