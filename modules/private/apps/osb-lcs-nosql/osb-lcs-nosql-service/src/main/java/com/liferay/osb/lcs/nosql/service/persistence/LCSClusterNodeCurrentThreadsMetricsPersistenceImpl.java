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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeCurrentThreadsMetrics;
import com.liferay.osb.lcs.nosql.model.impl.LCSClusterNodeCurrentThreadsMetricsImpl;
import com.liferay.osb.lcs.nosql.service.persistence.base.BasePersistenceImpl;
import com.liferay.osb.lcs.nosql.util.DMLStatementExecutor;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class LCSClusterNodeCurrentThreadsMetricsPersistenceImpl
	extends BasePersistenceImpl<LCSClusterNodeCurrentThreadsMetrics>
	implements LCSClusterNodeCurrentThreadsMetricsPersistence {

	@Override
	public LCSClusterNodeCurrentThreadsMetrics create() {
		LCSClusterNodeCurrentThreadsMetrics
			lcsClusterNodeCurrentThreadsMetrics =
				new LCSClusterNodeCurrentThreadsMetricsImpl();

		lcsClusterNodeCurrentThreadsMetrics.setNew(true);

		return lcsClusterNodeCurrentThreadsMetrics;
	}

	@Override
	public LCSClusterNodeCurrentThreadsMetrics fetchByK_N(
		String key, String name) {

		return fetchByArray("key_", key, "name", name);
	}

	@Override
	public List<LCSClusterNodeCurrentThreadsMetrics> findByKey(String key) {
		return findByArray("key_", key);
	}

	@Override
	public LCSClusterNodeCurrentThreadsMetrics update(
		LCSClusterNodeCurrentThreadsMetrics
			lcsClusterNodeCurrentThreadsMetrics) {

		DMLStatementExecutor dmlStatementExecutor = new DMLStatementExecutor(
			lcsClusterNodeCurrentThreadsMetrics, _TABLE_NAME);

		dmlStatementExecutor.addColumns(
			"currentThreadCount",
			lcsClusterNodeCurrentThreadsMetrics.getCurrentThreadCount(),
			"currentThreadsBusy",
			lcsClusterNodeCurrentThreadsMetrics.getCurrentThreadsBusy(),
			"modifiedDate",
			lcsClusterNodeCurrentThreadsMetrics.getModifiedDate());

		dmlStatementExecutor.addPrimaryKeyColumns("uuid", UUIDs.timeBased());

		dmlStatementExecutor.addPrimaryKeys(
			"key_", lcsClusterNodeCurrentThreadsMetrics.getKey(), "name",
			lcsClusterNodeCurrentThreadsMetrics.getName());

		dmlStatementExecutor.execute(session);

		return lcsClusterNodeCurrentThreadsMetrics;
	}

	@Override
	protected String[] getColumnNames() {
		return _COLUMN_NAMES;
	}

	@Override
	protected LCSClusterNodeCurrentThreadsMetrics getEntity(Row row) {
		return getEntity(row, _COLUMN_NAMES);
	}

	@Override
	protected LCSClusterNodeCurrentThreadsMetrics getEntity(
		Row row, String[] columnNames) {

		LCSClusterNodeCurrentThreadsMetrics
			lcsClusterNodeCurrentThreadsMetrics =
				new LCSClusterNodeCurrentThreadsMetricsImpl();

		List<String> columnNamesList = Arrays.asList(columnNames);

		if (columnNamesList.contains("currentThreadCount")) {
			lcsClusterNodeCurrentThreadsMetrics.setCurrentThreadCount(
				row.getInt("currentThreadCount"));
		}

		if (columnNamesList.contains("currentThreadsBusy")) {
			lcsClusterNodeCurrentThreadsMetrics.setCurrentThreadsBusy(
				row.getInt("currentThreadsBusy"));
		}

		if (columnNamesList.contains("key_")) {
			lcsClusterNodeCurrentThreadsMetrics.setKey(row.getString("key_"));
		}

		if (columnNamesList.contains("modifiedDate")) {
			lcsClusterNodeCurrentThreadsMetrics.setModifiedDate(
				row.getTimestamp("modifiedDate"));
		}

		if (columnNamesList.contains("name")) {
			lcsClusterNodeCurrentThreadsMetrics.setName(row.getString("name"));
		}

		if (columnNamesList.contains("uuid")) {
			lcsClusterNodeCurrentThreadsMetrics.setUUID(
				String.valueOf(row.getUUID("uuid")));
		}

		return lcsClusterNodeCurrentThreadsMetrics;
	}

	@Override
	protected String getTableName() {
		return _TABLE_NAME;
	}

	private static final String[] _COLUMN_NAMES = {
		"currentThreadCount", "currentThreadsBusy", "key_", "modifiedDate",
		"name", "uuid"
	};

	private static final String _TABLE_NAME =
		"LCSClusterNodeCurrentThreadsMetrics";

}