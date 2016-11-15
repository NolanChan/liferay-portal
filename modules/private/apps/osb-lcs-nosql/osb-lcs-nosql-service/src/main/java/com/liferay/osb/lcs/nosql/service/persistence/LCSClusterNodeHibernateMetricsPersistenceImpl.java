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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeHibernateMetrics;
import com.liferay.osb.lcs.nosql.model.impl.LCSClusterNodeHibernateMetricsImpl;
import com.liferay.osb.lcs.nosql.service.persistence.base.BasePersistenceImpl;
import com.liferay.osb.lcs.nosql.util.DMLStatementExecutor;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class LCSClusterNodeHibernateMetricsPersistenceImpl
	extends BasePersistenceImpl<LCSClusterNodeHibernateMetrics>
	implements LCSClusterNodeHibernateMetricsPersistence {

	@Override
	public LCSClusterNodeHibernateMetrics create() {
		LCSClusterNodeHibernateMetrics lcsClusterNodeHibernateMetrics =
			new LCSClusterNodeHibernateMetricsImpl();

		lcsClusterNodeHibernateMetrics.setNew(true);

		return lcsClusterNodeHibernateMetrics;
	}

	@Override
	public LCSClusterNodeHibernateMetrics fetchByKey(String key) {
		return fetchByArray("key_", key);
	}

	@Override
	public LCSClusterNodeHibernateMetrics update(
		LCSClusterNodeHibernateMetrics lcsClusterNodeHibernateMetrics) {

		DMLStatementExecutor dmlStatementExecutor = new DMLStatementExecutor(
			lcsClusterNodeHibernateMetrics, _TABLE_NAME);

		dmlStatementExecutor.addColumns(
			"modifiedDate", lcsClusterNodeHibernateMetrics.getModifiedDate(),
			"queryCacheHitCount",
			lcsClusterNodeHibernateMetrics.getQueryCacheHitCount(),
			"queryCacheMissCount",
			lcsClusterNodeHibernateMetrics.getQueryCacheMissCount(),
			"queryCacheExecutionCount",
			lcsClusterNodeHibernateMetrics.getQueryExecutionCount(),
			"queryCacheExecutionMaxTime",
			lcsClusterNodeHibernateMetrics.getQueryExecutionMaxTime());

		dmlStatementExecutor.addPrimaryKeyColumns("uuid", UUIDs.timeBased());

		dmlStatementExecutor.addPrimaryKeys(
			"key_", lcsClusterNodeHibernateMetrics.getKey());

		dmlStatementExecutor.execute(session);

		return lcsClusterNodeHibernateMetrics;
	}

	@Override
	protected String[] getColumnNames() {
		return _COLUMN_NAMES;
	}

	@Override
	protected LCSClusterNodeHibernateMetrics getEntity(Row row) {
		LCSClusterNodeHibernateMetrics lcsClusterNodeHibernateMetrics =
			new LCSClusterNodeHibernateMetricsImpl();

		lcsClusterNodeHibernateMetrics.setKey(row.getString("key_"));
		lcsClusterNodeHibernateMetrics.setModifiedDate(
			row.getTimestamp("modifiedDate"));
		lcsClusterNodeHibernateMetrics.setQueryCacheHitCount(
			row.getInt("queryCacheHitCount"));
		lcsClusterNodeHibernateMetrics.setQueryCacheMissCount(
			row.getInt("queryCacheMissCount"));
		lcsClusterNodeHibernateMetrics.setQueryExecutionCount(
			row.getInt("queryCacheExecutionCount"));
		lcsClusterNodeHibernateMetrics.setQueryExecutionMaxTime(
			row.getInt("queryCacheExecutionMaxTime"));
		lcsClusterNodeHibernateMetrics.setUUID(
			String.valueOf(row.getUUID("uuid")));

		return lcsClusterNodeHibernateMetrics;
	}

	@Override
	protected String getTableName() {
		return _TABLE_NAME;
	}

	private static final String[] _COLUMN_NAMES = {
		"key_", "modifiedDate", "queryCacheHitCount", "queryCacheMissCount",
		"queryCacheExecutionCount", "queryCacheExecutionMaxTime", "uuid"
	};

	private static final String _TABLE_NAME = "LCSClusterNodeHibernateMetrics";

}