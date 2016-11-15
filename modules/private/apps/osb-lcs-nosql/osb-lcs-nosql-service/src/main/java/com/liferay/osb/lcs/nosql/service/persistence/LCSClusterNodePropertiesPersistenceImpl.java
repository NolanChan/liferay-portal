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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeProperties;
import com.liferay.osb.lcs.nosql.model.impl.LCSClusterNodePropertiesImpl;
import com.liferay.osb.lcs.nosql.service.persistence.base.BasePersistenceImpl;

import java.util.Date;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class LCSClusterNodePropertiesPersistenceImpl
	extends BasePersistenceImpl<LCSClusterNodeProperties>
	implements LCSClusterNodePropertiesPersistence {

	@Override
	public LCSClusterNodeProperties create() {
		LCSClusterNodeProperties lcsClusterNodeProperties =
			new LCSClusterNodePropertiesImpl();

		lcsClusterNodeProperties.setNew(true);

		return lcsClusterNodeProperties;
	}

	@Override
	public LCSClusterNodeProperties fetchByKey(String key) {
		return fetchByArray("key_", key);
	}

	@Override
	public LCSClusterNodeProperties update(LCSClusterNodeProperties entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateHashCode(String hashCode, String key, Date modifiedDate) {
		updateColumns(
			new String[] {"hashCode", "modifiedDate"},
			new Object[] {hashCode, modifiedDate}, "key_", key);
	}

	@Override
	public void updatePropertiesMapColumn(
		String key, Object mapKey, Object mapValue) {

		updateMapColumn("properties", mapKey, mapValue, "key_", key);
	}

	@Override
	protected String[] getColumnNames() {
		return _COLUMN_NAMES;
	}

	@Override
	protected LCSClusterNodeProperties getEntity(Row row) {
		LCSClusterNodeProperties lcsClusterNodeProperties =
			new LCSClusterNodePropertiesImpl();

		lcsClusterNodeProperties.setHashCode(row.getString("hashCode"));
		lcsClusterNodeProperties.setKey(row.getString("key_"));
		lcsClusterNodeProperties.setModifiedDate(
			row.getTimestamp("modifiedDate"));
		lcsClusterNodeProperties.setProperties(
			row.getMap("properties", String.class, String.class));
		lcsClusterNodeProperties.setUUID(String.valueOf(row.getUUID("uuid")));

		return lcsClusterNodeProperties;
	}

	@Override
	protected String getTableName() {
		return _TABLE_NAME;
	}

	private static final String[] _COLUMN_NAMES =
		{"hashCode", "key_", "modifiedDate", "properties", "uuid"};

	private static final String _TABLE_NAME = "LCSClusterNodeProperties";

}