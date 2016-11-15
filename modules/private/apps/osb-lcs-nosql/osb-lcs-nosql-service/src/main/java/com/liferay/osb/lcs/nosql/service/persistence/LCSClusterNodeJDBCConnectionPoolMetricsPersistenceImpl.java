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

import com.datastax.driver.core.Row;
import com.datastax.driver.core.utils.UUIDs;

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeJDBCConnectionPoolMetrics;
import com.liferay.osb.lcs.nosql.model.impl.LCSClusterNodeJDBCConnectionPoolMetricsImpl;
import com.liferay.osb.lcs.nosql.service.persistence.base.BasePersistenceImpl;
import com.liferay.osb.lcs.nosql.util.DMLStatementExecutor;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class LCSClusterNodeJDBCConnectionPoolMetricsPersistenceImpl
	extends BasePersistenceImpl<LCSClusterNodeJDBCConnectionPoolMetrics>
	implements LCSClusterNodeJDBCConnectionPoolMetricsPersistence {

	@Override
	public LCSClusterNodeJDBCConnectionPoolMetrics create() {
		LCSClusterNodeJDBCConnectionPoolMetrics
			lcsClusterNodeJDBCConnectionPoolMetrics =
				new LCSClusterNodeJDBCConnectionPoolMetricsImpl();

		lcsClusterNodeJDBCConnectionPoolMetrics.setNew(true);

		return lcsClusterNodeJDBCConnectionPoolMetrics;
	}

	@Override
	public LCSClusterNodeJDBCConnectionPoolMetrics fetchByK_N(
		String key, String name) {

		return fetchByArray("key_", key, "name", name);
	}

	@Override
	public List<LCSClusterNodeJDBCConnectionPoolMetrics> findByKey(String key) {
		return findByArray("key_", key);
	}

	@Override
	public LCSClusterNodeJDBCConnectionPoolMetrics update(
		LCSClusterNodeJDBCConnectionPoolMetrics
			lcsClusterNodeJDBCConnectionPoolMetrics) {

		DMLStatementExecutor dmlStatementExecutor = new DMLStatementExecutor(
			lcsClusterNodeJDBCConnectionPoolMetrics, _TABLE_NAME);

		dmlStatementExecutor.addColumns(
			"modifiedDate",
			lcsClusterNodeJDBCConnectionPoolMetrics.getModifiedDate(),
			"numActive", lcsClusterNodeJDBCConnectionPoolMetrics.getNumActive(),
			"numIdle", lcsClusterNodeJDBCConnectionPoolMetrics.getNumIdle());

		dmlStatementExecutor.addPrimaryKeyColumns("uuid", UUIDs.timeBased());

		dmlStatementExecutor.addPrimaryKeys(
			"key_", lcsClusterNodeJDBCConnectionPoolMetrics.getKey(), "name",
			lcsClusterNodeJDBCConnectionPoolMetrics.getName());

		dmlStatementExecutor.execute(session);

		return lcsClusterNodeJDBCConnectionPoolMetrics;
	}

	@Override
	protected String[] getColumnNames() {
		return _COLUMN_NAMES;
	}

	@Override
	protected LCSClusterNodeJDBCConnectionPoolMetrics getEntity(Row row) {
		return getEntity(row, _COLUMN_NAMES);
	}

	@Override
	protected LCSClusterNodeJDBCConnectionPoolMetrics getEntity(
		Row row, String[] columnNames) {

		LCSClusterNodeJDBCConnectionPoolMetrics
			lcsClusterNodeJDBCConnectionPoolMetrics =
				new LCSClusterNodeJDBCConnectionPoolMetricsImpl();

		List<String> columnNamesList = Arrays.asList(columnNames);

		if (columnNamesList.contains("key_")) {
			lcsClusterNodeJDBCConnectionPoolMetrics.setKey(
				row.getString("key_"));
		}

		if (columnNamesList.contains("modifiedDate")) {
			lcsClusterNodeJDBCConnectionPoolMetrics.setModifiedDate(
				row.getTimestamp("modifiedDate"));
		}

		if (columnNamesList.contains("name")) {
			lcsClusterNodeJDBCConnectionPoolMetrics.setName(
				row.getString("name"));
		}

		if (columnNamesList.contains("numActive")) {
			lcsClusterNodeJDBCConnectionPoolMetrics.setNumActive(
				row.getInt("numActive"));
		}

		if (columnNamesList.contains("numIdle")) {
			lcsClusterNodeJDBCConnectionPoolMetrics.setNumIdle(
				row.getInt("numIdle"));
		}

		if (columnNamesList.contains("uuid")) {
			lcsClusterNodeJDBCConnectionPoolMetrics.setUUID(
				String.valueOf(row.getUUID("uuid")));
		}

		return lcsClusterNodeJDBCConnectionPoolMetrics;
	}

	@Override
	protected String getTableName() {
		return _TABLE_NAME;
	}

	private static final String[] _COLUMN_NAMES =
		{"key_", "modifiedDate", "name", "numActive", "numIdle", "uuid"};

	private static final String _TABLE_NAME =
		"LCSClusterNodeJDBCConnectionPoolMetrics";

}