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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodePortletMetrics;
import com.liferay.osb.lcs.nosql.model.impl.LCSClusterNodePortletMetricsImpl;
import com.liferay.osb.lcs.nosql.service.persistence.base.BasePersistenceImpl;
import com.liferay.osb.lcs.nosql.util.DMLStatementExecutor;

import java.util.Iterator;
import java.util.List;

/**
 * @author Ivica Cardic
 */
public class LCSClusterNodePortletMetricsPersistenceImpl
	extends BasePersistenceImpl<LCSClusterNodePortletMetrics>
	implements LCSClusterNodePortletMetricsPersistence {

	@Override
	public LCSClusterNodePortletMetrics create() {
		LCSClusterNodePortletMetrics lcsClusterNodePortletMetrics =
			new LCSClusterNodePortletMetricsImpl();

		lcsClusterNodePortletMetrics.setNew(true);

		return lcsClusterNodePortletMetrics;
	}

	@Override
	public LCSClusterNodePortletMetrics fetchByC_G_K_LN_PI_RT(
		long companyId, long groupId, String key, String layoutName,
		String portletId, String requestType) {

		Select select = getSelect();

		Select.Where where = select.where(QueryBuilder.eq("key_", key));

		where.and(QueryBuilder.eq("companyId", companyId));
		where.and(QueryBuilder.eq("groupId", groupId));
		where.and(QueryBuilder.eq("layoutName", layoutName));
		where.and(QueryBuilder.eq("portletId", portletId));
		where.and(QueryBuilder.eq("requestType", requestType));

		ResultSet resultSet = session.execute(where);

		Iterator<Row> iterator = resultSet.iterator();

		if (iterator.hasNext()) {
			Row row = iterator.next();

			return getEntity(row);
		}

		return null;
	}

	@Override
	public List<LCSClusterNodePortletMetrics> findByC_G_K_LN_RT(
		long companyId, long groupId, String key, String layoutName,
		String requestType) {

		return findByArray(
			"key_", key, "companyId", companyId, "groupId", groupId,
			"layoutName", layoutName, "requestType", requestType);
	}

	@Override
	public LCSClusterNodePortletMetrics update(
		LCSClusterNodePortletMetrics lcsClusterNodePortletMetrics) {

		DMLStatementExecutor dmlStatementExecutor = new DMLStatementExecutor(
			lcsClusterNodePortletMetrics, _TABLE_NAME);

		dmlStatementExecutor.addColumns(
			"averageLoadTime",
			lcsClusterNodePortletMetrics.getAverageLoadTime(), "count",
			lcsClusterNodePortletMetrics.getCount(), "displayName",
			lcsClusterNodePortletMetrics.getDisplayName(), "frequency",
			lcsClusterNodePortletMetrics.getFrequency(), "maxLoadTime",
			lcsClusterNodePortletMetrics.getMaxLoadTime(), "minLoadTime",
			lcsClusterNodePortletMetrics.getMinLoadTime(), "modifiedDate",
			lcsClusterNodePortletMetrics.getModifiedDate(), "name",
			lcsClusterNodePortletMetrics.getName());

		dmlStatementExecutor.addPrimaryKeyColumns("uuid", UUIDs.timeBased());

		dmlStatementExecutor.addPrimaryKeys(
			"key_", lcsClusterNodePortletMetrics.getKey(), "companyId",
			lcsClusterNodePortletMetrics.getCompanyId(), "groupId",
			lcsClusterNodePortletMetrics.getGroupId(), "layoutName",
			lcsClusterNodePortletMetrics.getLayoutName(), "portletId",
			lcsClusterNodePortletMetrics.getPortletId(), "requestType",
			lcsClusterNodePortletMetrics.getRequestType());

		dmlStatementExecutor.execute(session);

		return lcsClusterNodePortletMetrics;
	}

	@Override
	protected String[] getColumnNames() {
		return _COLUMN_NAMES;
	}

	@Override
	protected LCSClusterNodePortletMetrics getEntity(Row row) {
		LCSClusterNodePortletMetrics lcsClusterNodePortletMetrics =
			new LCSClusterNodePortletMetricsImpl();

		lcsClusterNodePortletMetrics.setAverageLoadTime(
			row.getInt("averageLoadTime"));
		lcsClusterNodePortletMetrics.setCompanyId(row.getLong("companyId"));
		lcsClusterNodePortletMetrics.setCount(row.getLong("count"));
		lcsClusterNodePortletMetrics.setDisplayName(
			row.getString("displayName"));
		lcsClusterNodePortletMetrics.setFrequency(row.getInt("frequency"));
		lcsClusterNodePortletMetrics.setGroupId(row.getLong("groupId"));
		lcsClusterNodePortletMetrics.setKey(row.getString("key_"));
		lcsClusterNodePortletMetrics.setLayoutName(row.getString("layoutName"));
		lcsClusterNodePortletMetrics.setMaxLoadTime(row.getInt("maxLoadTime"));
		lcsClusterNodePortletMetrics.setMinLoadTime(row.getInt("minLoadTime"));
		lcsClusterNodePortletMetrics.setModifiedDate(
			row.getTimestamp("modifiedDate"));
		lcsClusterNodePortletMetrics.setName(row.getString("name"));
		lcsClusterNodePortletMetrics.setPortletId(row.getString("portletId"));
		lcsClusterNodePortletMetrics.setRequestType(
			row.getString("requestType"));
		lcsClusterNodePortletMetrics.setUUID(
			String.valueOf(row.getUUID("uuid")));

		return lcsClusterNodePortletMetrics;
	}

	@Override
	protected String getTableName() {
		return _TABLE_NAME;
	}

	private static final String[] _COLUMN_NAMES = {
		"averageLoadTime", "companyId", "count", "displayName", "frequency",
		"groupId", "key_", "layoutName", "maxLoadTime", "minLoadTime",
		"modifiedDate", "name", "portletId", "requestType", "uuid"
	};

	private static final String _TABLE_NAME = "LCSClusterNodePortletMetrics";

}