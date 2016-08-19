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

import java.util.Iterator;

/**
 * @author Ivica Cardic
 */
public class LCSClusterNodePortletMetricsCounterPersistenceImpl
	extends BaseLCSClusterNodeCounterPersistenceImpl
	implements LCSClusterNodePortletMetricsCounterPersistence {

	@Override
	public long countByC_G_K_LN_RT(
		long companyId, long groupId, String key, String layoutName,
		String requestType) {

		return count(
			"key_", key, "companyId", companyId, "groupId", groupId,
			"layoutName", layoutName, "requestType", requestType);
	}

	@Override
	public void reset(String key) {
		ResultSet resultSet = findByKey(key);

		Iterator<Row> iterator = resultSet.iterator();

		while (iterator.hasNext()) {
			Row row = iterator.next();

			updateValue(
				-row.getLong("count"), "key_", key, "companyId",
				row.getLong("companyId"), "groupId", row.getLong("groupId"),
				"layoutName", row.getString("layoutName"), "requestType",
				row.getString("requestType"));
		}
	}

	@Override
	public void updateByC_G_K_LN_RT(
		long companyId, long groupId, String key, String layoutName,
		String requestType) {

		update(
			"key_", key, "companyId", companyId, "groupId", groupId,
			"layoutName", layoutName, "requestType", requestType);
	}

	@Override
	protected String[] getColumnNames() {
		return _COLUMN_NAMES;
	}

	@Override
	protected String getTableName() {
		return _TABLE_NAME;
	}

	private static final String[] _COLUMN_NAMES = {
		"companyid", "count", "groupId", "key_", "layoutName", "requestType"
	};

	private static final String _TABLE_NAME =
		"LCSClusterNodePortletMetricsCounter";

}