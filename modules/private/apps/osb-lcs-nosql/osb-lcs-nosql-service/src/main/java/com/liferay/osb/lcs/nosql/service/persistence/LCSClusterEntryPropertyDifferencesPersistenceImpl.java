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
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.Delete;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Update;

import com.liferay.osb.lcs.nosql.model.LCSClusterEntryPropertyDifferences;
import com.liferay.osb.lcs.nosql.model.impl.LCSClusterEntryPropertyDifferencesImpl;

import java.util.List;

/**
 * @author Matija Petanjek
 */
public class LCSClusterEntryPropertyDifferencesPersistenceImpl
	extends BaseLCSClusterNodePersistenceImpl
		<LCSClusterEntryPropertyDifferences>
	implements LCSClusterEntryPropertyDifferencesPersistence {

	@Override
	public LCSClusterEntryPropertyDifferences create() {
		LCSClusterEntryPropertyDifferences lcsClusterEntryPropertyDifferences =
			new LCSClusterEntryPropertyDifferencesImpl();

		lcsClusterEntryPropertyDifferences.setNew(true);

		return lcsClusterEntryPropertyDifferences;
	}

	@Override
	public void delete(long lcsClusterEntryId, String propertyName) {
		Delete.Selection selection = QueryBuilder.delete();

		Delete delete = selection.from(getTableName());

		delete.where(QueryBuilder.eq("lcsClusterEntryId", lcsClusterEntryId));
		delete.where(QueryBuilder.eq("propertyName", propertyName));

		session.execute(delete);
	}

	@Override
	public LCSClusterEntryPropertyDifferences fetchByLCEI_PN(
		long lcsClusterEntryId, String propertyName) {

		return fetchByArray(
			"lcsClusterEntryId", lcsClusterEntryId, "propertyName",
			propertyName);
	}

	@Override
	public List<LCSClusterEntryPropertyDifferences> findByLCSClusterEntryId(
		long lcsClusterEntryId) {

		return findByArray("lcsClusterEntryId", lcsClusterEntryId);
	}

	@Override
	public LCSClusterEntryPropertyDifferences update(
		LCSClusterEntryPropertyDifferences
			lcsClusterEntryPropertiesDifference) {

		Statement statement = null;

		if (lcsClusterEntryPropertiesDifference.isNew()) {
			Insert insert = QueryBuilder.insertInto(getTableName());

			insert.values(
				getColumnNames(),
				new Object[] {
					lcsClusterEntryPropertiesDifference.getLCSClusterEntryId(),
					lcsClusterEntryPropertiesDifference.getPropertyName(),
					lcsClusterEntryPropertiesDifference.getPropertyValues()
				});

			statement = insert;
		}
		else {
			Update update = QueryBuilder.update(getTableName());

			Update.Assignments assignments = update.with();

			assignments.and(
				QueryBuilder.set(
					"propertyValues",
					lcsClusterEntryPropertiesDifference.getPropertyValues()));

			assignments.where(
				QueryBuilder.eq(
					"lcsClusterEntryId",
					lcsClusterEntryPropertiesDifference.
						getLCSClusterEntryId()));
			assignments.where(
				QueryBuilder.eq(
					"propertyName",
					lcsClusterEntryPropertiesDifference.getPropertyName()));

			statement = update;
		}

		session.execute(statement);

		lcsClusterEntryPropertiesDifference.setNew(false);

		return lcsClusterEntryPropertiesDifference;
	}

	protected String[] getColumnNames() {
		return _COLUMN_NAMES;
	}

	protected LCSClusterEntryPropertyDifferences getEntity(Row row) {
		LCSClusterEntryPropertyDifferences lcsClusterEntryPropertyDifferences =
			new LCSClusterEntryPropertyDifferencesImpl();

		lcsClusterEntryPropertyDifferences.setLCSClusterEntryId(
			row.getLong("lcsClusterEntryId"));
		lcsClusterEntryPropertyDifferences.setPropertyName(
			row.getString("propertyName"));
		lcsClusterEntryPropertyDifferences.setPropertyValues(
			row.getMap("propertyValues", String.class, String.class));

		return lcsClusterEntryPropertyDifferences;
	}

	protected String getTableName() {
		return _TABLE_NAME;
	}

	private static final String[] _COLUMN_NAMES = {
		"lcsClusterEntryId", "propertyName", "propertyValues"
	};

	private static final String _TABLE_NAME =
		"LCSClusterEntryPropertyDifferences";

}