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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeLayoutMetrics;
import com.liferay.osb.lcs.nosql.model.impl.LCSClusterNodeLayoutMetricsImpl;
import com.liferay.osb.lcs.nosql.service.persistence.base.BasePersistenceImpl;
import com.liferay.osb.lcs.nosql.util.DMLStatementExecutor;

import java.util.List;

/**
 * @author Ivica Cardic
 */
public class LCSClusterNodeLayoutMetricsPersistenceImpl
	extends BasePersistenceImpl<LCSClusterNodeLayoutMetrics>
	implements LCSClusterNodeLayoutMetricsPersistence {

	@Override
	public LCSClusterNodeLayoutMetrics create() {
		LCSClusterNodeLayoutMetrics lcsClusterNodeLayoutMetrics =
			new LCSClusterNodeLayoutMetricsImpl();

		lcsClusterNodeLayoutMetrics.setNew(true);

		return lcsClusterNodeLayoutMetrics;
	}

	@Override
	public LCSClusterNodeLayoutMetrics fetchByC_G_K_N(
		long companyId, long groupId, String key, String name) {

		return fetchByArray(
			"key_", key, "companyId", companyId, "groupId", groupId, "name",
			name);
	}

	@Override
	public List<LCSClusterNodeLayoutMetrics> findByC_G_K(
		long companyId, long groupId, String key) {

		return findByArray(
			"key_", key, "companyId", companyId, "groupId", groupId);
	}

	@Override
	public List<LCSClusterNodeLayoutMetrics> findByKey(String key) {
		return findByArray("key_", key);
	}

	@Override
	public LCSClusterNodeLayoutMetrics update(
		LCSClusterNodeLayoutMetrics lcsClusterNodeLayoutMetrics) {

		DMLStatementExecutor dmlStatementExecutor = new DMLStatementExecutor(
			lcsClusterNodeLayoutMetrics, _TABLE_NAME);

		dmlStatementExecutor.addColumns(
			"averageLoadTime", lcsClusterNodeLayoutMetrics.getAverageLoadTime(),
			"count", lcsClusterNodeLayoutMetrics.getCount(), "frequency",
			lcsClusterNodeLayoutMetrics.getFrequency(), "maxLoadTime",
			lcsClusterNodeLayoutMetrics.getMaxLoadTime(), "minLoadTime",
			lcsClusterNodeLayoutMetrics.getMinLoadTime(), "modifiedDate",
			lcsClusterNodeLayoutMetrics.getModifiedDate());

		dmlStatementExecutor.addPrimaryKeyColumns("uuid", UUIDs.timeBased());

		dmlStatementExecutor.addPrimaryKeys(
			"key_", lcsClusterNodeLayoutMetrics.getKey(), "companyId",
			lcsClusterNodeLayoutMetrics.getCompanyId(), "groupId",
			lcsClusterNodeLayoutMetrics.getGroupId(), "name",
			lcsClusterNodeLayoutMetrics.getName());

		dmlStatementExecutor.execute(session);

		return lcsClusterNodeLayoutMetrics;
	}

	@Override
	protected String[] getColumnNames() {
		return _COLUMN_NAMES;
	}

	@Override
	protected LCSClusterNodeLayoutMetrics getEntity(Row row) {
		LCSClusterNodeLayoutMetrics lcsClusterNodeLayoutMetrics =
			new LCSClusterNodeLayoutMetricsImpl();

		lcsClusterNodeLayoutMetrics.setAverageLoadTime(
			row.getInt("averageLoadTime"));
		lcsClusterNodeLayoutMetrics.setCompanyId(row.getLong("companyId"));
		lcsClusterNodeLayoutMetrics.setCount(row.getLong("count"));
		lcsClusterNodeLayoutMetrics.setFrequency(row.getInt("frequency"));
		lcsClusterNodeLayoutMetrics.setGroupId(row.getLong("groupId"));
		lcsClusterNodeLayoutMetrics.setKey(row.getString("key_"));
		lcsClusterNodeLayoutMetrics.setMaxLoadTime(row.getInt("maxLoadTime"));
		lcsClusterNodeLayoutMetrics.setMinLoadTime(row.getInt("minLoadTime"));
		lcsClusterNodeLayoutMetrics.setModifiedDate(
			row.getTimestamp("modifiedDate"));
		lcsClusterNodeLayoutMetrics.setName(row.getString("name"));
		lcsClusterNodeLayoutMetrics.setUUID(
			String.valueOf(row.getUUID("uuid")));

		return lcsClusterNodeLayoutMetrics;
	}

	@Override
	protected String getTableName() {
		return _TABLE_NAME;
	}

	private static final String[] _COLUMN_NAMES = {
		"averageLoadTime", "companyId", "count", "frequency", "groupId", "key_",
		"maxLoadTime", "minLoadTime", "modifiedDate", "name", "uuid"
	};

	private static final String _TABLE_NAME = "LCSClusterNodeLayoutMetrics";

}