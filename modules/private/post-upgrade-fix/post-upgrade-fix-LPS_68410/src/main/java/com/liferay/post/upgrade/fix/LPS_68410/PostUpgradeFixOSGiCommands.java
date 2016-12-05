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

package com.liferay.post.upgrade.fix.LPS_68410;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.db.DBType;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.osgi.service.component.annotations.Component;

/**
 * <p>
 * Post upgrade fix for https://issues.liferay.com/browse/LPS-68410
 * </p>
 *
 * @author Christopher Kian
 */
@Component(
	immediate = true,
	property = {
		"osgi.command.function=LPS_68410", "osgi.command.scope=postUpgradeFix"
	},
	service = PostUpgradeFixOSGiCommands.class
)
public class PostUpgradeFixOSGiCommands {

	public void LPS_68410() throws Exception {
		if (_log.isInfoEnabled()) {
			_log.info("Executing postUpgradeFix:LPS_68410");
		}

		DB db = DBManagerUtil.getDB();

		if (db.getDBType() == DBType.SQLSERVER) {
			alterNVarcharColumns();
		}
		else if (db.getDBType() == DBType.SYBASE) {
			alterVarcharColumns();
		}
		else {
			if (_log.isInfoEnabled()) {
				_log.info(
					"No changes occurred during postUpgradeFix:LPS_68410.  " +
						"Database type: " + db.getDBType() + " not affected " +
							"during upgrade.");
			}
		}
	}

	protected void alterNVarcharColumns() throws Exception {
		StringBundler sb1 = new StringBundler(3);

		sb1.append("select table_name, column_name from ");
		sb1.append("INFORMATION_SCHEMA.COLUMNS where DATA_TYPE = 'nvarchar' ");
		sb1.append("and character_maximum_length = 2000");

		Connection connection = DataAccess.getConnection();

		try (PreparedStatement ps1 = connection.prepareStatement(
				sb1.toString());
			ResultSet rs1 = ps1.executeQuery()) {

			while (rs1.next()) {
				String tableName = rs1.getString(1);
				String columnName = rs1.getString(2);

				StringBundler sb2 = new StringBundler(5);

				sb2.append("alter table ");
				sb2.append(tableName);
				sb2.append(" alter column ");
				sb2.append(columnName);
				sb2.append(" nvarchar(4000)");

				try (PreparedStatement ps2 =
						connection.prepareStatement(sb2.toString())) {

					ps2.execute();
				}
				catch (SQLException sqle) {
					if (sqle.getErrorCode() == 1441) {
						if (_log.isWarnEnabled()) {
							StringBundler warnSB = new StringBundler(6);

							warnSB.append("Unable to alter length of column ");
							warnSB.append(columnName);
							warnSB.append(" for table ");
							warnSB.append(tableName);
							warnSB.append(
								" because it contains values larger ");
							warnSB.append("than the new column length");

							_log.warn(warnSB.toString());
						}
					}
					else {
						_log.error(
							"An exception was thrown while executing" +
								"postUpgradeFix:LPS_68410",
							sqle);

						throw sqle;
					}
				}
			}

			if (_log.isInfoEnabled()) {
				_log.info("Finished executing postUpgradeFix:LPS_68410");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void alterVarcharColumns() throws Exception {
		StringBundler sb1 = new StringBundler(3);

		sb1.append("select o.name, c.name from sysobjects o, syscolumns c, ");
		sb1.append("systypes t where o.id = c.id and c.type = t.type and ");
		sb1.append("t.name = 'varchar' and c.length = 1000");

		Connection connection = DataAccess.getConnection();

		try (PreparedStatement ps1 = connection.prepareStatement(
				sb1.toString());
			ResultSet rs1 = ps1.executeQuery()) {

			while (rs1.next()) {
				String tableName = rs1.getString(1);
				String columnName = rs1.getString(2);

				StringBundler sb2 = new StringBundler(5);

				sb2.append("alter table ");
				sb2.append(tableName);
				sb2.append(" modify ");
				sb2.append(columnName);
				sb2.append(" varchar(4000)");

				try (PreparedStatement ps2 =
						connection.prepareStatement(sb2.toString())) {

					ps2.execute();
				}
				catch (SQLException sqle) {
					if (sqle.getErrorCode() == 1441) {
						if (_log.isWarnEnabled()) {
							StringBundler warnSB = new StringBundler(6);

							warnSB.append("Unable to alter length of column ");
							warnSB.append(columnName);
							warnSB.append(" for table ");
							warnSB.append(tableName);
							warnSB.append(
								" because it contains values larger ");
							warnSB.append("than the new column length");

							_log.warn(warnSB.toString());
						}
					}
					else {
						_log.error(
							"An exception was thrown while executing" +
								"postUpgradeFix:LPS_68410",
							sqle);

						throw sqle;
					}
				}
			}

			if (_log.isInfoEnabled()) {
				_log.info("Finished executing postUpgradeFix:LPS_68410");
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PostUpgradeFixOSGiCommands.class);

}