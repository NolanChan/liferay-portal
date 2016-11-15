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

package com.liferay.osb.lcs.nosql.service.persistence.base;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.Delete;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.datastax.driver.core.querybuilder.Update;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Igor Beslic
 * @author Ivica Cardic
 */
public abstract class BasePersistenceImpl<E> {

	public abstract E create();

	public void delete(String key) {
		delete(getTableName(), key);
	}

	public E fetchByArray(Object... columns) {
		if ((columns.length == 0) || ((columns.length % 2) != 0)) {
			throw new IllegalArgumentException(
				"Columns length is not an even number");
		}

		Select select = getSelect();

		Select.Where where = select.where();

		for (int i = 0; i < columns.length; i += 2) {
			where.and(QueryBuilder.eq((String)columns[i], columns[i + 1]));
		}

		ResultSet resultSet = session.execute(where);

		Iterator<Row> iterator = resultSet.iterator();

		if (iterator.hasNext()) {
			Row row = iterator.next();

			return getEntity(row);
		}

		return null;
	}

	public List<E> findByArray(Object... columns) {
		if ((columns.length == 0) || ((columns.length % 2) != 0)) {
			throw new IllegalArgumentException(
				"Columns length is not an even number");
		}

		Select select = getSelect();

		Select.Where where = select.where();

		for (int i = 0; i < columns.length; i += 2) {
			where.and(QueryBuilder.eq((String)columns[i], columns[i + 1]));
		}

		ResultSet resultSet = session.execute(select);

		Iterator<Row> iterator = resultSet.iterator();

		List<E> entities = new ArrayList<>();

		while (iterator.hasNext()) {
			Row row = iterator.next();

			E entity = getEntity(row);

			entities.add(entity);
		}

		return entities;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public void setTimeToLive(int timeToLive) {
		this.timeToLive = timeToLive;
	}

	public abstract E update(E entity);

	public void updateColumn(
		String column, Object value, Object... primaryKeys) {

		Update update = QueryBuilder.update(getTableName());

		Update.Assignments assignments = update.with();

		assignments.and(QueryBuilder.set(column, value));

		Update.Where where = update.where();

		for (int i = 0; i < primaryKeys.length; i += 2) {
			where.and(
				QueryBuilder.eq((String)primaryKeys[i], primaryKeys[i + 1]));
		}

		session.execute(update);
	}

	public void updateColumns(
		String[] columns, Object[] values, Object... primaryKeys) {

		Update update = QueryBuilder.update(getTableName());

		Update.Assignments assignments = update.with();

		for (int i = 0; i < columns.length; i++) {
			assignments.and(QueryBuilder.set(columns[i], values[i]));
		}

		Update.Where where = update.where();

		for (int i = 0; i < primaryKeys.length; i += 2) {
			where.and(
				QueryBuilder.eq((String)primaryKeys[i], primaryKeys[i + 1]));
		}

		session.execute(update);
	}

	public void updateMapColumn(
		String column, Object key, Object value, Object... primaryKeys) {

		Update update = QueryBuilder.update(getTableName());

		Update.Assignments assignments = update.with();

		assignments.and(QueryBuilder.put(column, key, value));

		Update.Where where = update.where();

		for (int i = 0; i < primaryKeys.length; i += 2) {
			where.and(
				QueryBuilder.eq((String)primaryKeys[i], primaryKeys[i + 1]));
		}

		session.execute(update);
	}

	protected <T> Map<String, String> convertGenericsMapToJSONMap(
		Map<String, Map<String, T>> genericsMap) {

		Map<String, String> jsonMap = new HashMap<>();

		Set<String> keys = genericsMap.keySet();

		for (String key : keys) {
			String json = toJSON(genericsMap.get(key));

			jsonMap.put(key, json);
		}

		return jsonMap;
	}

	protected <T extends Number> Map<String, Map<String, T>>
		convertJSONMapToGenericsMap(
			Map<String, String> jsonMap, Class<T> clazz) {

		Map<String, Map<String, T>> genericsMap = new HashMap<>();

		Set<String> keys = jsonMap.keySet();

		for (String key : keys) {
			Map<String, Number> oldValueMap = fromJSON(jsonMap.get(key));

			Set<String> valueKeys = oldValueMap.keySet();

			Map<String, T> newValueMap = new HashMap<>();

			for (String valueKey : valueKeys) {
				Number value = oldValueMap.get(valueKey);

				newValueMap.put(valueKey, (T)getNumber(clazz, value));
			}

			genericsMap.put(key, newValueMap);
		}

		return genericsMap;
	}

	protected void delete(String table, String key) {
		Delete.Selection selection = QueryBuilder.delete();

		Delete delete = selection.from(table);

		delete.where(QueryBuilder.eq("key_", key));

		session.execute(delete);
	}

	protected Map<String, Number> fromJSON(String json) {
		try {
			return _objectMapper.readValue(json, Map.class);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected abstract String[] getColumnNames();

	protected abstract E getEntity(Row row);

	protected E getEntity(Row row, String[] columnNames) {
		return null;
	}

	protected Number getNumber(Class<? extends Number> clazz, Number value) {
		if (value == null) {
			return null;
		}

		if (Byte.class.equals(clazz)) {
			return value.byteValue();
		}
		else if (Short.class.equals(clazz)) {
			return value.shortValue();
		}
		else if (Integer.class.equals(clazz)) {
			return value.intValue();
		}
		else if (Long.class.equals(clazz)) {
			return value.longValue();
		}
		else if (Float.class.equals(clazz)) {
			return value.floatValue();
		}
		else if (Double.class.equals(clazz)) {
			return value.doubleValue();
		}

		throw new TypeNotPresentException(clazz.getName(), null);
	}

	protected Select getSelect() {
		return getSelect(getColumnNames());
	}

	protected Select getSelect(String[] columnNames) {
		Select.Selection selection = QueryBuilder.select();

		for (String columnName : columnNames) {
			selection.column(columnName);
		}

		Select select = selection.from(getTableName());

		select.allowFiltering();

		return select;
	}

	protected abstract String getTableName();

	protected <T> String toJSON(Map<String, T> map) {
		try {
			return _objectMapper.writeValueAsString(map);
		}
		catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}

	protected Session session;
	protected int timeToLive;

	private final ObjectMapper _objectMapper = new ObjectMapper();

}