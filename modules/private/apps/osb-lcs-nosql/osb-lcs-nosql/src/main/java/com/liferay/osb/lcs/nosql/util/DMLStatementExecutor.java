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

package com.liferay.osb.lcs.nosql.util;

import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Update;

import com.liferay.osb.lcs.nosql.model.base.DMLModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Ivica Cardic
 */
public class DMLStatementExecutor {

	public DMLStatementExecutor(DMLModel dmlModel, String tableName) {
		_dmlModel = dmlModel;
		_tableName = tableName;
	}

	public DMLStatementExecutor addColumns(Object... columns) {
		Collections.addAll(_columns, columns);

		return this;
	}

	public DMLStatementExecutor addPrimaryKeyColumns(Object... columns) {
		if (_dmlModel.isNew()) {
			addColumns(columns);
		}

		return this;
	}

	public DMLStatementExecutor addPrimaryKeys(Object... primaryKeys) {
		Collections.addAll(_primaryKeys, primaryKeys);

		return this;
	}

	public void execute(Session session) {
		if ((_columns.size() == 0) || ((_columns.size() % 2) != 0)) {
			throw new IllegalArgumentException(
				"Columns length is not an even number");
		}

		if ((_primaryKeys.size() == 0) || ((_primaryKeys.size() % 2) != 0)) {
			throw new IllegalArgumentException(
				"Primary keys length is not an even number");
		}

		Statement statement = null;

		if (_dmlModel.isNew()) {
			Insert insert = QueryBuilder.insertInto(_tableName);

			for (int i = 0; i < _columns.size(); i += 2) {
				insert.value((String)_columns.get(i), _columns.get(i + 1));
			}

			for (int i = 0; i < _primaryKeys.size(); i += 2) {
				insert.value(
					(String)_primaryKeys.get(i), _primaryKeys.get(i + 1));
			}

			statement = insert;
		}
		else {
			Update update = QueryBuilder.update(_tableName);

			Update.Assignments assignments = update.with();

			for (int i = 0; i < _columns.size(); i += 2) {
				assignments.and(
					QueryBuilder.set(
						(String)_columns.get(i), _columns.get(i + 1)));
			}

			Update.Where where = update.where();

			for (int i = 0; i < _primaryKeys.size(); i += 2) {
				where.and(
					QueryBuilder.eq(
						(String)_primaryKeys.get(i), _primaryKeys.get(i + 1)));
			}

			statement = update;
		}

		session.execute(statement);

		_dmlModel.setNew(false);

		_columns.clear();
		_primaryKeys.clear();
	}

	private final List<Object> _columns = new ArrayList<>();
	private final DMLModel _dmlModel;
	private final List<Object> _primaryKeys = new ArrayList<>();
	private final String _tableName;

}