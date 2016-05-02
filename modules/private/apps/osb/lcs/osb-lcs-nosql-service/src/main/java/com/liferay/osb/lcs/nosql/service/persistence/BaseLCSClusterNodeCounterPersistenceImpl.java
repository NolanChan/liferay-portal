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
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.querybuilder.Update;

import java.util.Iterator;

/**
 * @author Igor Beslic
 * @author Ivica Cardic
 */
public abstract class BaseLCSClusterNodeCounterPersistenceImpl {

	public void setSession(Session session) {
		this.session = session;
	}

	protected long count(Object... columns) {
		Select select = getSelect("count");

		Select.Where where = select.where();

		for (int i = 0; i < columns.length; i += 2) {
			where.and(QueryBuilder.eq((String)columns[i], columns[i + 1]));
		}

		ResultSet resultSet = session.execute(select);

		Iterator<Row> iterator = resultSet.iterator();

		if (iterator.hasNext()) {
			Row row = iterator.next();

			return row.getLong(0);
		}

		return 0;
	}

	protected ResultSet findByKey(String key) {
		Select select = getSelect(getColumnNames());

		Select.Where where = select.where();

		where.and(QueryBuilder.eq("key_", key));

		return session.execute(select);
	}

	protected abstract String[] getColumnNames();

	protected Select getSelect(String... columnNames) {
		Select.Selection selection = QueryBuilder.select();

		for (String columnName : columnNames) {
			selection.column(columnName);
		}

		Select select = selection.from(getTableName());

		select.allowFiltering();

		return select;
	}

	protected abstract String getTableName();

	protected void update(Object... columns) {
		updateValue(Integer.MIN_VALUE, columns);
	}

	protected void updateValue(long value, Object... columns) {
		Update update = QueryBuilder.update(getTableName());

		Update.Assignments assignments = update.with();

		if (value == Integer.MIN_VALUE) {
			assignments.and(QueryBuilder.incr("count"));
		}
		else {
			assignments.and(QueryBuilder.incr("count", value));
		}

		Update.Where where = update.where();

		for (int i = 0; i < columns.length; i += 2) {
			where.and(QueryBuilder.eq((String)columns[i], columns[i + 1]));
		}

		session.execute(update);
	}

	protected Session session;

}