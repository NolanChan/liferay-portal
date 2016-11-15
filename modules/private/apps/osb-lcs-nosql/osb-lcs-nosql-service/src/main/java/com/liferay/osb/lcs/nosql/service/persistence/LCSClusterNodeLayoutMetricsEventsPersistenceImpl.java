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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeLayoutMetricsEvents;
import com.liferay.osb.lcs.nosql.model.impl.LCSClusterNodeLayoutMetricsEventsImpl;
import com.liferay.osb.lcs.nosql.service.persistence.base.BasePersistenceImpl;
import com.liferay.osb.lcs.nosql.util.DMLStatementExecutor;

/**
 * @author Riccardo Ferrari
 */
public class LCSClusterNodeLayoutMetricsEventsPersistenceImpl
	extends BasePersistenceImpl<LCSClusterNodeLayoutMetricsEvents>
	implements LCSClusterNodeLayoutMetricsEventsPersistence {

	@Override
	public LCSClusterNodeLayoutMetricsEvents create() {
		LCSClusterNodeLayoutMetricsEvents lcsClusterNodeLayoutMetricsEvents =
			new LCSClusterNodeLayoutMetricsEventsImpl();

		lcsClusterNodeLayoutMetricsEvents.setNew(true);

		return lcsClusterNodeLayoutMetricsEvents;
	}

	@Override
	public LCSClusterNodeLayoutMetricsEvents update(
		LCSClusterNodeLayoutMetricsEvents lcsClusterNodeLayoutMetricsEvents) {

		DMLStatementExecutor dmlStatementExecutor = new DMLStatementExecutor(
			lcsClusterNodeLayoutMetricsEvents, _TABLE_NAME);

		dmlStatementExecutor.addColumns(
			"averageLoadTime",
			lcsClusterNodeLayoutMetricsEvents.getAverageLoadTime(), "frequency",
			lcsClusterNodeLayoutMetricsEvents.getFrequency(), "referer",
			lcsClusterNodeLayoutMetricsEvents.getReferer(), "remoteAddr",
			lcsClusterNodeLayoutMetricsEvents.getRemoteAddr(), "requestStatus",
			lcsClusterNodeLayoutMetricsEvents.getRequestStatus(), "requestURL",
			lcsClusterNodeLayoutMetricsEvents.getRequestURL(), "statusCode",
			lcsClusterNodeLayoutMetricsEvents.getStatusCode(), "userAgent",
			lcsClusterNodeLayoutMetricsEvents.getUserAgent(), "userId",
			lcsClusterNodeLayoutMetricsEvents.getUserId());

		dmlStatementExecutor.addPrimaryKeyColumns("uuid", UUIDs.timeBased());

		dmlStatementExecutor.addPrimaryKeys(
			"partitionKey", lcsClusterNodeLayoutMetricsEvents.getPartitionKey(),
			"modifiedDate", lcsClusterNodeLayoutMetricsEvents.getModifiedDate(),
			"key_", lcsClusterNodeLayoutMetricsEvents.getKey(), "companyId",
			lcsClusterNodeLayoutMetricsEvents.getCompanyId(), "groupId",
			lcsClusterNodeLayoutMetricsEvents.getGroupId(), "name",
			lcsClusterNodeLayoutMetricsEvents.getName());

		dmlStatementExecutor.execute(session);

		return lcsClusterNodeLayoutMetricsEvents;
	}

	@Override
	protected String[] getColumnNames() {
		return _COLUMN_NAMES;
	}

	@Override
	protected LCSClusterNodeLayoutMetricsEvents getEntity(Row row) {
		LCSClusterNodeLayoutMetricsEvents lcsClusterNodeLayoutMetricsEvents =
			new LCSClusterNodeLayoutMetricsEventsImpl();

		lcsClusterNodeLayoutMetricsEvents.setAverageLoadTime(
			row.getInt("averageLoadTime"));
		lcsClusterNodeLayoutMetricsEvents.setCompanyId(
			row.getLong("companyId"));
		lcsClusterNodeLayoutMetricsEvents.setFrequency(row.getInt("frequency"));
		lcsClusterNodeLayoutMetricsEvents.setGroupId(row.getLong("groupId"));
		lcsClusterNodeLayoutMetricsEvents.setKey(row.getString("key_"));
		lcsClusterNodeLayoutMetricsEvents.setModifiedDate(
			row.getTimestamp("modifiedDate"));
		lcsClusterNodeLayoutMetricsEvents.setName(row.getString("name"));
		lcsClusterNodeLayoutMetricsEvents.setPartitionKey(
			row.getString("partitionKey"));
		lcsClusterNodeLayoutMetricsEvents.setReferer(row.getString("referer"));
		lcsClusterNodeLayoutMetricsEvents.setRemoteAddr(
			row.getString("remoteAddr"));
		lcsClusterNodeLayoutMetricsEvents.setRequestStatus(
			row.getString("requestStatus"));
		lcsClusterNodeLayoutMetricsEvents.setRequestURL(
			row.getString("requestURL"));
		lcsClusterNodeLayoutMetricsEvents.setStatusCode(
			row.getInt("statusCode"));
		lcsClusterNodeLayoutMetricsEvents.setUserAgent(
			row.getString("userAgent"));
		lcsClusterNodeLayoutMetricsEvents.setUserId(row.getLong("userId"));

		return lcsClusterNodeLayoutMetricsEvents;
	}

	@Override
	protected String getTableName() {
		return _TABLE_NAME;
	}

	private static final String[] _COLUMN_NAMES = {
		"averageLoadTime", "companyId", "frequency", "groupId", "key_",
		"modifiedDate", "name", "partitionKey", "referer", "remoteAddr",
		"requestStatus", "requestURL", "statusCode", "userAgent", "userId"
	};

	private static final String _TABLE_NAME =
		"LCSClusterNodeLayoutMetricsEvents";

}