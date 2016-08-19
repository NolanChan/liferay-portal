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

import com.liferay.osb.lcs.nosql.model.LCSStatsPortletMetricsEvents;
import com.liferay.osb.lcs.nosql.model.impl.LCSStatsPortletMetricsEventsImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Riccardo Ferrari
 */
public class LCSStatsPortletMetricsEventsPersistenceImpl
	extends BaseLCSStatsPersistenceImpl<LCSStatsPortletMetricsEvents>
	implements LCSStatsPortletMetricsEventsPersistence {

	@Override
	public LCSStatsPortletMetricsEvents create() {
		LCSStatsPortletMetricsEvents lcsStatsPortletMetricsEvents =
			new LCSStatsPortletMetricsEventsImpl();

		lcsStatsPortletMetricsEvents.setNew(true);

		return lcsStatsPortletMetricsEvents;
	}

	@Override
	public List<LCSStatsPortletMetricsEvents> fetchByC_E_G_K_P(
		long companyId, Date endDate, long groupId, String key,
		String portletId) {

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
		where.and(QueryBuilder.eq("portletId", portletId));
		where.and(QueryBuilder.gte("modifiedDate", startDate));
		where.and(QueryBuilder.lte("modifiedDate", endDate));

		ResultSet resultSet = session.execute(select);

		Iterator<Row> iterator = resultSet.iterator();

		List<LCSStatsPortletMetricsEvents> entities = new ArrayList<>();

		while (iterator.hasNext()) {
			Row row = iterator.next();

			LCSStatsPortletMetricsEvents entity = getEntity(row);

			entities.add(entity);
		}

		return entities;
	}

	@Override
	protected String[] getColumnNames() {
		return _COLUMN_NAMES;
	}

	@Override
	protected LCSStatsPortletMetricsEvents getEntity(Row row) {
		LCSStatsPortletMetricsEvents lcsStatsPortletMetricsEvents =
			new LCSStatsPortletMetricsEventsImpl();

		lcsStatsPortletMetricsEvents.setAverage(row.getInt("averageLoadTime"));
		lcsStatsPortletMetricsEvents.setCompanyId(row.getLong("companyId"));
		lcsStatsPortletMetricsEvents.setDisplayName(
			row.getString("displayName"));
		lcsStatsPortletMetricsEvents.setGroupId(row.getLong("groupId"));
		lcsStatsPortletMetricsEvents.setJobDate(row.getTimestamp("jobDate"));
		lcsStatsPortletMetricsEvents.setJobIdentifier(
			row.getString("jobIdentifier"));
		lcsStatsPortletMetricsEvents.setKey(row.getString("key_"));
		lcsStatsPortletMetricsEvents.setLayoutName(row.getString("layoutName"));
		lcsStatsPortletMetricsEvents.setMax(row.getInt("maxLoadTime"));
		lcsStatsPortletMetricsEvents.setMin(row.getInt("minLoadTime"));
		lcsStatsPortletMetricsEvents.setModifiedDate(
			row.getTimestamp("modifiedDate"));
		lcsStatsPortletMetricsEvents.setName(row.getString("name"));
		lcsStatsPortletMetricsEvents.setPartitionKey("partitionKey");
		lcsStatsPortletMetricsEvents.setPortletId(row.getString("portletId"));
		lcsStatsPortletMetricsEvents.setRequestType(
			row.getString("requestType"));
		lcsStatsPortletMetricsEvents.setSampleCount(row.getInt("sampleCount"));
		lcsStatsPortletMetricsEvents.setStandardDeviation(
			row.getInt("standardDeviation"));

		return lcsStatsPortletMetricsEvents;
	}

	private static final String[] _COLUMN_NAMES = {
		"averageLoadTime", "companyId", "displayName", "groupId", "jobDate",
		"jobIdentifier", "key_", "layoutName", "maxLoadTime", "minLoadTime",
		"modifiedDate", "name", "partitionKey", "portletId", "requestType",
		"sampleCount", "standardDeviation"
	};

}