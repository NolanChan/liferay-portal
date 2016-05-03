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

import com.liferay.osb.lcs.nosql.model.LCSStatsLayoutMetricsEvents;
import com.liferay.osb.lcs.nosql.model.impl.LCSStatsLayoutMetricsEventsImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Riccardo Ferrari
 */
public class LCSStatsLayoutMetricsEventsPersistenceImpl
	extends BaseLCSStatsPersistenceImpl<LCSStatsLayoutMetricsEvents>
	implements LCSStatsLayoutMetricsEventsPersistence {

	@Override
	public LCSStatsLayoutMetricsEvents create() {
		LCSStatsLayoutMetricsEvents lcsStatsLayoutMetricsEvents =
			new LCSStatsLayoutMetricsEventsImpl();

		lcsStatsLayoutMetricsEvents.setNew(true);

		return lcsStatsLayoutMetricsEvents;
	}

	@Override
	public List<LCSStatsLayoutMetricsEvents> fetchByC_E_G_K_L(
		long companyId, Date endDate, long groupId, String key,
		String layoutName) {

		Date startDate = getStartDate(endDate);

		String[] partitionKeys = getPartitionKeys(endDate, startDate);

		Select select = getSelect();

		Select.Where where = select.where(QueryBuilder.eq("key_", key));

		if (partitionKeys.length > 1) {
			where.and(QueryBuilder.in("partitionKey", partitionKeys));
		}
		else {
			where.and(QueryBuilder.eq("partitionKey", partitionKeys[0]));
		}

		where.and(QueryBuilder.eq("companyId", companyId));
		where.and(QueryBuilder.eq("groupId", groupId));
		where.and(QueryBuilder.eq("name", layoutName));
		where.and(QueryBuilder.gte("modifiedDate", startDate));
		where.and(QueryBuilder.lte("modifiedDate", endDate));

		ResultSet resultSet = session.execute(select);

		Iterator<Row> iterator = resultSet.iterator();

		List<LCSStatsLayoutMetricsEvents> entities = new ArrayList<>();

		while (iterator.hasNext()) {
			Row row = iterator.next();

			LCSStatsLayoutMetricsEvents entity = getEntity(row);

			entities.add(entity);
		}

		return entities;
	}

	@Override
	protected String[] getColumnNames() {
		return _COLUMN_NAMES;
	}

	@Override
	protected LCSStatsLayoutMetricsEvents getEntity(Row row) {
		LCSStatsLayoutMetricsEvents lcsStatsLayoutMetricsEvents =
			new LCSStatsLayoutMetricsEventsImpl();

		lcsStatsLayoutMetricsEvents.setAverage(row.getInt("averageLoadTime"));
		lcsStatsLayoutMetricsEvents.setCompanyId(row.getLong("companyId"));
		lcsStatsLayoutMetricsEvents.setGroupId(row.getLong("groupId"));
		lcsStatsLayoutMetricsEvents.setJobDate(row.getTimestamp("jobDate"));
		lcsStatsLayoutMetricsEvents.setJobIdentifier(
			row.getString("jobIdentifier"));
		lcsStatsLayoutMetricsEvents.setKey(row.getString("key_"));
		lcsStatsLayoutMetricsEvents.setMax(row.getInt("maxLoadTime"));
		lcsStatsLayoutMetricsEvents.setMin(row.getInt("minLoadTime"));
		lcsStatsLayoutMetricsEvents.setModifiedDate(
			row.getTimestamp("modifiedDate"));
		lcsStatsLayoutMetricsEvents.setName(row.getString("name"));
		lcsStatsLayoutMetricsEvents.setPartitionKey("partitionKey");
		lcsStatsLayoutMetricsEvents.setRequestStatus(
			row.getString("requestStatus"));
		lcsStatsLayoutMetricsEvents.setRequestURL(row.getString("requestURL"));
		lcsStatsLayoutMetricsEvents.setSampleCount(row.getInt("sampleCount"));
		lcsStatsLayoutMetricsEvents.setStandardDeviation(
			row.getInt("standardDeviation"));

		return lcsStatsLayoutMetricsEvents;
	}

	private static final String[] _COLUMN_NAMES = {
		"averageLoadTime", "companyId", "groupId", "jobDate", "jobIdentifier",
		"key_", "maxLoadTime", "minLoadTime", "modifiedDate", "name",
		"partitionKey", "requestStatus", "requestURL", "sampleCount",
		"standardDeviation"
	};

}