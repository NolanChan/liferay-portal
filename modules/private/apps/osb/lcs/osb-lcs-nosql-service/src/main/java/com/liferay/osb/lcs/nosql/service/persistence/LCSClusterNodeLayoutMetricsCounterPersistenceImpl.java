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
public class LCSClusterNodeLayoutMetricsCounterPersistenceImpl
	extends BaseLCSClusterNodeCounterPersistenceImpl
	implements LCSClusterNodeLayoutMetricsCounterPersistence {

	@Override
	public long countByC_G_K(long companyId, long groupId, String key) {
		return count("key_", key, "companyId", companyId, "groupId", groupId);
	}

	@Override
	public void delete(String key) {
		ResultSet resultSet = findByKey(key);

		Iterator<Row> iterator = resultSet.iterator();

		while (iterator.hasNext()) {
			Row row = iterator.next();

			updateValue(
				-row.getLong("count"), "key_", key, "companyId",
				row.getLong("companyId"), "groupId", row.getLong("groupId"));
		}
	}

	@Override
	public void updateByC_G_K(long companyId, long groupId, String key) {
		update("key_", key, "companyId", companyId, "groupId", groupId);
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
		"companyid", "count", "groupId", "key_"
	};

	private static final String _TABLE_NAME =
		"LCSClusterNodeLayoutMetricsCounter";

}