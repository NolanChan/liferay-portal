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

package com.liferay.osb.lcs.nosql.service.persistence;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.utils.UUIDs;

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeScript;
import com.liferay.osb.lcs.nosql.model.impl.LCSClusterNodeScriptImpl;
import com.liferay.osb.lcs.nosql.service.persistence.base.BasePersistenceImpl;
import com.liferay.osb.lcs.nosql.util.DMLStatementExecutor;

import java.util.Iterator;
import java.util.List;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class LCSClusterNodeScriptPersistenceImpl
	extends BasePersistenceImpl<LCSClusterNodeScript>
	implements LCSClusterNodeScriptPersistence {

	@Override
	public LCSClusterNodeScript create() {
		LCSClusterNodeScript lcsClusterNodeScript =
			new LCSClusterNodeScriptImpl();

		lcsClusterNodeScript.setNew(true);

		return lcsClusterNodeScript;
	}

	@Override
	public LCSClusterNodeScript findByCorrelationKey(String correlationKey) {
		Select select = getSelect();

		Statement statement = select.where(
			QueryBuilder.eq("correlationKey", correlationKey));

		ResultSet resultSet = session.execute(statement);

		Iterator<Row> iterator = resultSet.iterator();

		if (iterator.hasNext()) {
			Row row = iterator.next();

			return getEntity(row);
		}

		return null;
	}

	@Override
	public List<LCSClusterNodeScript> findByKey(String key) {
		return findByArray("key_", key);
	}

	@Override
	public LCSClusterNodeScript update(
		LCSClusterNodeScript lcsClusterNodeScript) {

		DMLStatementExecutor dmlStatementExecutor = new DMLStatementExecutor(
			lcsClusterNodeScript, _TABLE_NAME);

		if (lcsClusterNodeScript.getError() != null) {
			dmlStatementExecutor.addColumns(
				"error", lcsClusterNodeScript.getError());
		}

		dmlStatementExecutor.addColumns(
			"command", lcsClusterNodeScript.getCommand(), "correlationKey",
			lcsClusterNodeScript.getCorrelationKey(), "modifiedDate",
			lcsClusterNodeScript.getModifiedDate());

		if (lcsClusterNodeScript.getResult() != null) {
			dmlStatementExecutor.addColumns(
				"result", lcsClusterNodeScript.getResult());
		}

		dmlStatementExecutor.addPrimaryKeyColumns("uuid", UUIDs.timeBased());

		dmlStatementExecutor.addPrimaryKeys(
			"key_", lcsClusterNodeScript.getKey(), "uuid",
			lcsClusterNodeScript.getUUID());

		dmlStatementExecutor.execute(session);

		return lcsClusterNodeScript;
	}

	@Override
	protected String[] getColumnNames() {
		return _COLUMN_NAMES;
	}

	@Override
	protected LCSClusterNodeScript getEntity(Row row) {
		LCSClusterNodeScript lcsClusterNodeScript =
			new LCSClusterNodeScriptImpl();

		lcsClusterNodeScript.setCommand(row.getString("command"));
		lcsClusterNodeScript.setCorrelationKey(row.getString("correlationKey"));
		lcsClusterNodeScript.setError(row.getString("error"));
		lcsClusterNodeScript.setKey(row.getString("key_"));
		lcsClusterNodeScript.setModifiedDate(row.getTimestamp("modifiedDate"));
		lcsClusterNodeScript.setResult(row.getString("result"));
		lcsClusterNodeScript.setUUID(String.valueOf(row.getUUID("uuid")));

		return lcsClusterNodeScript;
	}

	@Override
	protected String getTableName() {
		return _TABLE_NAME;
	}

	private static final String[] _COLUMN_NAMES = {
		"command", "correlationKey", "error", "key_", "modifiedDate", "result",
		"uuid"
	};

	private static final String _TABLE_NAME = "LCSClusterNodeScript";

}