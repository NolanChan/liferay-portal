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

package com.liferay.portal.upgrade.v7_0_0;

import com.liferay.portal.dao.jdbc.spring.DataSourceFactoryBean;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.util.UpgradeTable;
import com.liferay.portal.kernel.upgrade.util.UpgradeTableFactoryUtil;
import com.liferay.portal.upgrade.v7_0_0.util.ClassNameTable;
import com.liferay.portal.upgrade.v7_0_0.util.ClusterGroupTable;
import com.liferay.portal.upgrade.v7_0_0.util.CounterTable;
import com.liferay.portal.upgrade.v7_0_0.util.CountryTable;
import com.liferay.portal.upgrade.v7_0_0.util.PortalPreferencesTable;
import com.liferay.portal.upgrade.v7_0_0.util.RegionTable;
import com.liferay.portal.upgrade.v7_0_0.util.ReleaseTable;
import com.liferay.portal.upgrade.v7_0_0.util.ResourceActionTable;
import com.liferay.portal.upgrade.v7_0_0.util.VirtualHostTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

/**
 * @author Manuel de la Peña
 */
public class UpgradeShardingControlTables extends UpgradeProcess {

	protected void copyControlTablesToShard(String shardName) throws Exception {
		DataSourceFactoryBean dataSourceFactoryBean =
			new DataSourceFactoryBean();

		dataSourceFactoryBean.setPropertyPrefix("jdbc." + shardName + ".");

		DataSource dataSource = dataSourceFactoryBean.createInstance();

		Connection targetConnection = dataSource.getConnection();

		Connection sourceConnection =
			DataAccess.getUpgradeOptimizedConnection();

		updateUpgradeTable(
			sourceConnection, targetConnection, ClassNameTable.TABLE_NAME,
			ClassNameTable.TABLE_COLUMNS, ClassNameTable.TABLE_SQL_CREATE);
		updateUpgradeTable(
			sourceConnection, targetConnection, ClusterGroupTable.TABLE_NAME,
			ClusterGroupTable.TABLE_COLUMNS,
			ClusterGroupTable.TABLE_SQL_CREATE);
		updateUpgradeTable(
			sourceConnection, targetConnection, CounterTable.TABLE_NAME,
			CounterTable.TABLE_COLUMNS, CounterTable.TABLE_SQL_CREATE);
		updateUpgradeTable(
			sourceConnection, targetConnection, CountryTable.TABLE_NAME,
			CountryTable.TABLE_COLUMNS, CountryTable.TABLE_SQL_CREATE);
		updateUpgradeTable(
			sourceConnection, targetConnection,
			PortalPreferencesTable.TABLE_NAME,
			PortalPreferencesTable.TABLE_COLUMNS,
			PortalPreferencesTable.TABLE_SQL_CREATE);
		updateUpgradeTable(
			sourceConnection, targetConnection, RegionTable.TABLE_NAME,
			RegionTable.TABLE_COLUMNS, RegionTable.TABLE_SQL_CREATE);
		updateUpgradeTable(
			sourceConnection, targetConnection, ReleaseTable.TABLE_NAME,
			ReleaseTable.TABLE_COLUMNS, ReleaseTable.TABLE_SQL_CREATE);
		updateUpgradeTable(
			sourceConnection, targetConnection, ResourceActionTable.TABLE_NAME,
			ResourceActionTable.TABLE_COLUMNS,
			ResourceActionTable.TABLE_SQL_CREATE);
		updateUpgradeTable(
			sourceConnection, targetConnection, VirtualHostTable.TABLE_NAME,
			VirtualHostTable.TABLE_COLUMNS, VirtualHostTable.TABLE_SQL_CREATE);
	}

	protected void copyControlTablesToShards(List<String> shardNames)
		throws Exception {

		for (String shardName : shardNames) {
			copyControlTablesToShard(shardName);
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		List<String> shardNames = getShardNames();

		if (shardNames.size() <= 1) {
			return;
		}

		copyControlTablesToShards(shardNames);
	}

	protected List<String> getShardNames() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<String> shardNames;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			String sql = "select count(*) from Shard";

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			int shardsCount = 0;

			if (rs.next()) {
				shardsCount = rs.getInt(1);
			}

			shardNames = new ArrayList<>(shardsCount);

			if (shardsCount == 0) {
				return shardNames;
			}

			sql = "select name from Shard";

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				shardNames.add(rs.getString(1));
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		return shardNames;
	}

	protected void updateUpgradeTable(
			Connection sourceConnection, Connection targetConnection,
			String tableName, Object[][] columns, String createTable)
		throws Exception {

		UpgradeTable upgradeTable = UpgradeTableFactoryUtil.getUpgradeTable(
			tableName, columns);

		upgradeTable.setCreateSQL(createTable);

		upgradeTable.updateTable(sourceConnection, targetConnection, false);
	}

}