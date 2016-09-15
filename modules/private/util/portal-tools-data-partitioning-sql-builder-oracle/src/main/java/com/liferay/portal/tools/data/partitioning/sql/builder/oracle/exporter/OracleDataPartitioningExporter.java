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

package com.liferay.portal.tools.data.partitioning.sql.builder.oracle.exporter;

import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.BaseDataPartitioningExporter;
import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.InsertSQLBuilder;
import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.context.ExportContext;
import com.liferay.portal.tools.data.partitioning.sql.builder.oracle.exporter.serializer.OracleFieldSerializer;

/**
 * @author Manuel de la Peña
 */
public class OracleDataPartitioningExporter
	extends BaseDataPartitioningExporter {

	public OracleDataPartitioningExporter() {
		super(new InsertSQLBuilder(new OracleFieldSerializer()));
	}

	@Override
	public String getControlTableNamesSQL(ExportContext exportContext) {
		StringBuilder sb = new StringBuilder(10);

		sb.append("select ");
		sb.append(getTableNameFieldName());
		sb.append(" from user_tables where ");
		sb.append(getTableNameFieldName());
		sb.append(" not in (");
		sb.append(getPartitionedTableNamesSQL(exportContext));
		sb.append(") group by ");
		sb.append(getTableNameFieldName());
		sb.append(" order by ");
		sb.append(getTableNameFieldName());

		return sb.toString();
	}

	@Override
	public String getPartitionedTableNamesSQL(ExportContext exportContext) {
		StringBuilder sb = new StringBuilder(4);

		sb.append("select ");
		sb.append(getTableNameFieldName());
		sb.append(" from cols where column_name = 'COMPANYID' group by ");
		sb.append(getTableNameFieldName());

		return sb.toString();
	}

	@Override
	public String getTableNameFieldName() {
		return "table_name";
	}

}