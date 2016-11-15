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
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.utils.UUIDs;

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeLiferayVMMetrics;
import com.liferay.osb.lcs.nosql.model.impl.LCSClusterNodeLiferayVMMetricsImpl;
import com.liferay.osb.lcs.nosql.service.persistence.base.BasePersistenceImpl;
import com.liferay.osb.lcs.nosql.util.DMLStatementExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class LCSClusterNodeLiferayVMMetricsPersistenceImpl
	extends BasePersistenceImpl<LCSClusterNodeLiferayVMMetrics>
	implements LCSClusterNodeLiferayVMMetricsPersistence {

	@Override
	public LCSClusterNodeLiferayVMMetrics create() {
		LCSClusterNodeLiferayVMMetrics lcsClusterNodeLiferayVMMetrics =
			new LCSClusterNodeLiferayVMMetricsImpl();

		lcsClusterNodeLiferayVMMetrics.setNew(true);

		return lcsClusterNodeLiferayVMMetrics;
	}

	@Override
	public LCSClusterNodeLiferayVMMetrics fetchByK_N_T(
		String key, String name, String type) {

		Select select = getSelect();

		Select.Where where = select.where(QueryBuilder.eq("key_", key));

		where.and(QueryBuilder.eq("name", name));
		where.and(QueryBuilder.eq("type", type));

		ResultSet resultSet = session.execute(where);

		Iterator<Row> iterator = resultSet.iterator();

		if (iterator.hasNext()) {
			Row row = iterator.next();

			LCSClusterNodeLiferayVMMetrics lcsClusterNodeLiferayVMMetrics =
				getEntity(row);

			return lcsClusterNodeLiferayVMMetrics;
		}

		return null;
	}

	@Override
	public List<LCSClusterNodeLiferayVMMetrics> findByK_T(
		String key, String type) {

		Select select = getSelect();

		Select.Where where = select.where(QueryBuilder.eq("key_", key));

		where.and(QueryBuilder.eq("type", type));

		ResultSet resultSet = session.execute(where);

		Iterator<Row> iterator = resultSet.iterator();

		List<LCSClusterNodeLiferayVMMetrics>
			lcsClusterNodeLiferayVMMetricsList = new ArrayList<>();

		while (iterator.hasNext()) {
			Row row = iterator.next();

			LCSClusterNodeLiferayVMMetrics lcsClusterNodeLiferayVMMetrics =
				getEntity(row);

			lcsClusterNodeLiferayVMMetricsList.add(
				lcsClusterNodeLiferayVMMetrics);
		}

		return lcsClusterNodeLiferayVMMetricsList;
	}

	@Override
	public LCSClusterNodeLiferayVMMetrics update(
		LCSClusterNodeLiferayVMMetrics lcsClusterNodeLiferayVMMetrics) {

		DMLStatementExecutor dmlStatementExecutor = new DMLStatementExecutor(
			lcsClusterNodeLiferayVMMetrics, _TABLE_NAME);

		dmlStatementExecutor.addColumns(
			"cacheHits", lcsClusterNodeLiferayVMMetrics.getCacheHits(),
			"cacheMisses", lcsClusterNodeLiferayVMMetrics.getCacheMisses(),
			"modifiedDate", lcsClusterNodeLiferayVMMetrics.getModifiedDate(),
			"objectCount", lcsClusterNodeLiferayVMMetrics.getObjectCount());

		dmlStatementExecutor.addPrimaryKeyColumns("uuid", UUIDs.timeBased());

		dmlStatementExecutor.addPrimaryKeys(
			"key_", lcsClusterNodeLiferayVMMetrics.getKey(), "name",
			lcsClusterNodeLiferayVMMetrics.getName(), "type",
			lcsClusterNodeLiferayVMMetrics.getType());

		dmlStatementExecutor.execute(session);

		return lcsClusterNodeLiferayVMMetrics;
	}

	@Override
	protected String[] getColumnNames() {
		return _COLUMN_NAMES;
	}

	@Override
	protected LCSClusterNodeLiferayVMMetrics getEntity(Row row) {
		return getEntity(row, _COLUMN_NAMES);
	}

	@Override
	protected LCSClusterNodeLiferayVMMetrics getEntity(
		Row row, String[] columnNames) {

		LCSClusterNodeLiferayVMMetrics lcsClusterNodeLiferayVMMetrics =
			new LCSClusterNodeLiferayVMMetricsImpl();

		List<String> includeColumnList = Arrays.asList(columnNames);

		if (includeColumnList.contains("cacheHits")) {
			lcsClusterNodeLiferayVMMetrics.setCacheHits(
				row.getInt("cacheHits"));
		}

		if (includeColumnList.contains("cacheMisses")) {
			lcsClusterNodeLiferayVMMetrics.setCacheMisses(
				row.getInt("cacheMisses"));
		}

		if (includeColumnList.contains("key_")) {
			lcsClusterNodeLiferayVMMetrics.setKey(row.getString("key_"));
		}

		if (includeColumnList.contains("modifiedDate")) {
			lcsClusterNodeLiferayVMMetrics.setModifiedDate(
				row.getTimestamp("modifiedDate"));
		}

		if (includeColumnList.contains("name")) {
			lcsClusterNodeLiferayVMMetrics.setName(row.getString("name"));
		}

		if (includeColumnList.contains("objectCount")) {
			lcsClusterNodeLiferayVMMetrics.setObjectCount(
				row.getInt("objectCount"));
		}

		if (includeColumnList.contains("type")) {
			lcsClusterNodeLiferayVMMetrics.setType(row.getString("type"));
		}

		if (includeColumnList.contains("uuid")) {
			lcsClusterNodeLiferayVMMetrics.setUUID(
				String.valueOf(row.getUUID("uuid")));
		}

		return lcsClusterNodeLiferayVMMetrics;
	}

	@Override
	protected String getTableName() {
		return _TABLE_NAME;
	}

	private static final String[] _COLUMN_NAMES = {
		"cacheHits", "cacheMisses", "key_", "modifiedDate", "name",
		"objectCount", "type", "uuid"
	};

	private static final String _TABLE_NAME = "LCSClusterNodeLiferayVMMetrics";

}