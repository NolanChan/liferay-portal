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

package com.liferay.portal.test.rule.callback;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.db.DBType;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.test.rule.callback.BaseTestCallback;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.runner.Description;

/**
 * @author Shuyang Zhou
 */
public class SybaseDumpTransactionLogTestCallback
	extends BaseTestCallback<Void, Void> {

	public static final SybaseDumpTransactionLogTestCallback INSTANCE =
		new SybaseDumpTransactionLogTestCallback();

	@Override
	public Void beforeClass(Description description) throws SQLException {
		SybaseDumpTransactionLog sybaseDumpTransactionLog =
			description.getAnnotation(SybaseDumpTransactionLog.class);

		if (sybaseDumpTransactionLog != null) {
			SybaseDump sybaseDump = sybaseDumpTransactionLog.dumpBefore();

			if (!sybaseDump.equals(SybaseDump.CLASS) &&
					!sybaseDump.equals(SybaseDump.CLASS_AND_METHOD)) {

				return null;
			}
		}

		dumpTransactionLog();

		return null;
	}

	@Override
	public Void beforeMethod(Description description, Object target)
		throws SQLException {

		SybaseDumpTransactionLog sybaseDumpTransactionLog =
			description.getAnnotation(SybaseDumpTransactionLog.class);

		if (sybaseDumpTransactionLog == null) {
			Class<?> testClass = description.getTestClass();

			sybaseDumpTransactionLog = testClass.getAnnotation(
				SybaseDumpTransactionLog.class);
		}

		if (sybaseDumpTransactionLog != null) {
			SybaseDump sybaseDump = sybaseDumpTransactionLog.dumpBefore();

			if (sybaseDump.equals(SybaseDump.CLASS_AND_METHOD) ||
					sybaseDump.equals(SybaseDump.METHOD)) {

				dumpTransactionLog();
			}
		}

		return null;
	}

	private void dumpTransactionLog() throws SQLException {
		DB db = DBManagerUtil.getDB();

		if (db.getDBType() != DBType.SYBASE) {
			return;
		}

		try (Connection connection = DataAccess.getConnection();
			Statement statement = connection.createStatement()) {

			statement.execute(
				"dump transaction " + connection.getCatalog() + " with no_log");
		}
	}

	private SybaseDumpTransactionLogTestCallback() {
	}

}