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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodePortletMetricsEvents;
import com.liferay.osb.lcs.nosql.model.impl.LCSClusterNodePortletMetricsEventsImpl;
import com.liferay.osb.lcs.nosql.service.persistence.base.BasePersistenceImpl;
import com.liferay.osb.lcs.nosql.util.DMLStatementExecutor;

/**
 * @author Riccardo Ferrari
 */
public class LCSClusterNodePortletMetricsEventsPersistenceImpl
	extends BasePersistenceImpl<LCSClusterNodePortletMetricsEvents>
	implements LCSClusterNodePortletMetricsEventsPersistence {

	@Override
	public LCSClusterNodePortletMetricsEvents create() {
		LCSClusterNodePortletMetricsEvents lcsClusterNodePortletMetricsEvents =
			new LCSClusterNodePortletMetricsEventsImpl();

		lcsClusterNodePortletMetricsEvents.setNew(true);

		return lcsClusterNodePortletMetricsEvents;
	}

	@Override
	public LCSClusterNodePortletMetricsEvents update(
		LCSClusterNodePortletMetricsEvents lcsClusterNodePortletMetricsEvents) {

		DMLStatementExecutor dmlStatementExecutor = new DMLStatementExecutor(
			lcsClusterNodePortletMetricsEvents, _TABLE_NAME);

		dmlStatementExecutor.addColumns(
			"averageLoadTime",
			lcsClusterNodePortletMetricsEvents.getAverageLoadTime(),
			"displayName", lcsClusterNodePortletMetricsEvents.getDisplayName(),
			"frequency", lcsClusterNodePortletMetricsEvents.getFrequency(),
			"name", lcsClusterNodePortletMetricsEvents.getName());

		dmlStatementExecutor.addPrimaryKeyColumns("uuid", UUIDs.timeBased());

		dmlStatementExecutor.addPrimaryKeys(
			"partitionKey",
			lcsClusterNodePortletMetricsEvents.getPartitionKey(),
			"modifiedDate",
			lcsClusterNodePortletMetricsEvents.getModifiedDate(), "key_",
			lcsClusterNodePortletMetricsEvents.getKey(), "companyId",
			lcsClusterNodePortletMetricsEvents.getCompanyId(), "groupId",
			lcsClusterNodePortletMetricsEvents.getGroupId(), "layoutName",
			lcsClusterNodePortletMetricsEvents.getLayoutName(), "portletId",
			lcsClusterNodePortletMetricsEvents.getPortletId(), "requestType",
			lcsClusterNodePortletMetricsEvents.getRequestType());

		dmlStatementExecutor.execute(session);

		return lcsClusterNodePortletMetricsEvents;
	}

	@Override
	protected String[] getColumnNames() {
		return _COLUMN_NAMES;
	}

	@Override
	protected LCSClusterNodePortletMetricsEvents getEntity(Row row) {
		LCSClusterNodePortletMetricsEvents lcsClusterNodePortletMetricsEvents =
			new LCSClusterNodePortletMetricsEventsImpl();

		lcsClusterNodePortletMetricsEvents.setAverageLoadTime(
			row.getInt("averageLoadTime"));
		lcsClusterNodePortletMetricsEvents.setCompanyId(
			row.getLong("companyId"));
		lcsClusterNodePortletMetricsEvents.setDisplayName(
			row.getString("displayName"));
		lcsClusterNodePortletMetricsEvents.setFrequency(
			row.getInt("frequency"));
		lcsClusterNodePortletMetricsEvents.setGroupId(row.getLong("groupId"));
		lcsClusterNodePortletMetricsEvents.setKey(row.getString("key_"));
		lcsClusterNodePortletMetricsEvents.setLayoutName(
			row.getString("layoutName"));
		lcsClusterNodePortletMetricsEvents.setModifiedDate(
			row.getTimestamp("modifiedDate"));
		lcsClusterNodePortletMetricsEvents.setName(row.getString("name"));
		lcsClusterNodePortletMetricsEvents.setPartitionKey(
			row.getString("partitionKey"));
		lcsClusterNodePortletMetricsEvents.setPortletId(
			row.getString("portletId"));
		lcsClusterNodePortletMetricsEvents.setRequestType(
			row.getString("requestType"));
		lcsClusterNodePortletMetricsEvents.setUUID(
			String.valueOf(row.getUUID("uuid")));

		return lcsClusterNodePortletMetricsEvents;
	}

	@Override
	protected String getTableName() {
		return _TABLE_NAME;
	}

	private static final String[] _COLUMN_NAMES = {
		"averageLoadTime", "companyId", "displayName", "frequency", "groupId",
		"key_", "layoutName", "modifiedDate", "name", "partitionKey",
		"portletId", "requestType", "uuid"
	};

	private static final String _TABLE_NAME =
		"LCSClusterNodePortletMetricsEvents";

}