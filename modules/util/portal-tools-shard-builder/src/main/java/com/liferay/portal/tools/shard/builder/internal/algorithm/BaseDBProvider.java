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

package com.liferay.portal.tools.shard.builder.internal.algorithm;

import com.liferay.portal.tools.shard.builder.exporter.ShardExporter;
import com.liferay.portal.tools.shard.builder.exporter.context.ExportContext;
import com.liferay.portal.tools.shard.builder.internal.DBProvider;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.OutputStream;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Manuel de la Peña
 */
public abstract class BaseDBProvider
	implements DBExporter, DBProvider, ShardExporter {

	public BaseDBProvider(Properties properties) {
		this.properties = properties;

		HikariConfig hikariConfig = new HikariConfig(this.properties);

		_dataSource = new HikariDataSource(hikariConfig);
	}

	@Override
	public void export(ExportContext exportContext) {
		ExportProcess exportProcess = new ExportProcess(this);

		try {
			exportProcess.export(exportContext);
		}
		catch (IOException ioe) {
			_logger.error("Unable to export", ioe);
		}
	}

	protected void generateInsertSQL(
			OutputStream outputStream, String tableName, String[] fields)
		throws IOException {

		if ((fields == null) || (fields.length == 0)) {
			throw new IllegalArgumentException("Fields are null");
		}

		StringBuilder sb = new StringBuilder();

		sb.append("INSERT INTO ");
		sb.append(tableName);
		sb.append(" VALUES (");

		for (int i = 0; i < fields.length; i++) {
			String field = fields[i];

			sb.append(field);

			if (i != (fields.length - 1)) {
				sb.append(", ");
			}
		}

		sb.append(")");

		String sql = sb.toString() + ";\n";

		outputStream.write(sql.getBytes());
	}

	@Override
	public List<String> getControlTableNames(String schemaName) {
		return getSchemaTableNames(getControlTablesSql(schemaName));
	}

	public abstract String getControlTablesSql(String schema);

	@Override
	public DataSource getDataSource() {
		return _dataSource;
	}

	@Override
	public String getDateTimeFormat() {
		return "YYYY-MM-DD HH:MM:SS";
	}

	@Override
	public int getFetchSize() {
		return 0;
	}

	@Override
	public List<String> getPartitionedTableNames(String schemaName) {
		return getSchemaTableNames(getPartitionedTablesSql(schemaName));
	}

	public abstract String getPartitionedTablesSql(String schema);

	@Override
	public String serializeTableField(Object field) {
		StringBuilder sb = new StringBuilder();

		if (field == null) {
			sb.append("null");
		}
		else if ((field instanceof Date) || (field instanceof Timestamp)) {
			sb.append("'");
			sb.append(formatDateTime(field));
			sb.append("'");
		}
		else if (field instanceof String) {
			String value = (String)field;

			value = value.replace("'", "\\'");

			sb.append("'");
			sb.append(value);
			sb.append("'");
		}
		else {
			sb.append("'");
			sb.append(field);
			sb.append("'");
		}

		return sb.toString();
	}


	@Override
	public void write(String tableName, OutputStream outputStream) {
		write(0, tableName, outputStream);
	}

	protected String formatDateTime(Object date) {
		DateFormat simpleDateFormat = new SimpleDateFormat(getDateTimeFormat());

		return simpleDateFormat.format(date);
	}

	protected final Properties properties;

	protected PreparedStatement buildPreparedStatement(
			Connection connection, String sql, long companyId)
		throws SQLException {

		PreparedStatement preparedStatement = connection.prepareStatement(
			sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

		preparedStatement.setFetchSize(getFetchSize());

		if (companyId > 0) {
			preparedStatement.setLong(1, companyId);
		}

		return preparedStatement;
	}

	protected List<String> getSchemaTableNames(String sql) {
		List<String> tableNames = new ArrayList<>();

		DataSource dataSource = getDataSource();

		try (Connection con = dataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
					ResultSet rs = ps.executeQuery() ) {

			while (rs.next()) {
				tableNames.add(rs.getString(getTableNameFieldName()));
			}
		}
		catch (SQLException sqle) {
			_logger.error(
				"Error retrieving the table names of the schema using " +
					"the query " + sql,
				sqle);
		}

		return tableNames;
	}

	@Override
	public void write(
		long companyId, String tableName, OutputStream outputStream) {

		DataSource dataSource = getDataSource();

		String sql = "select * from " + tableName;

		if (companyId > 0) {
			sql += " where companyId = ?";
		}

		try (Connection con = dataSource.getConnection();
				PreparedStatement ps = buildPreparedStatement(
					con, sql, companyId);
				ResultSet rs = ps.executeQuery() ) {

			ResultSetMetaData resultSetMetaData = rs.getMetaData();

			int columnCount = resultSetMetaData.getColumnCount();

			while (rs.next()) {
				String[] fields = new String[columnCount];

				for (int i = 0; i < columnCount; i++) {
					fields[i] = serializeTableField(rs.getObject(i + 1));
				}

				generateInsertSQL(outputStream, tableName, fields);
			}
		}
		catch (IOException | SQLException e) {
			_logger.error("Unable to export " + tableName, e);
		}
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		BaseDBProvider.class);

	private final DataSource _dataSource;

}