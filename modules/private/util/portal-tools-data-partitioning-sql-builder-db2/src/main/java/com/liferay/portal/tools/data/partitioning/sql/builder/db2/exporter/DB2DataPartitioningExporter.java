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

package com.liferay.portal.tools.data.partitioning.sql.builder.db2.exporter;

import com.liferay.portal.tools.data.partitioning.sql.builder.db2.exporter.serializer.DB2FieldSerializer;
import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.BaseDataPartitioningExporter;
import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.InsertSQLBuilder;
import com.liferay.portal.tools.data.partitioning.sql.builder.exporter.context.ExportContext;

/**
 * @author Manuel de la Peña
 */
public class DB2DataPartitioningExporter extends BaseDataPartitioningExporter {

	public DB2DataPartitioningExporter() {
		super(new InsertSQLBuilder(new DB2FieldSerializer()));
	}

	@Override
	public String getControlTableNamesSQL(ExportContext exportContext) {
		StringBuilder sb = new StringBuilder(14);

		sb.append("select t1.");
		sb.append(getTableNameFieldName());
		sb.append(" from sysibm.tables t1 where t1.table_catalog = '");
		sb.append(exportContext.getCatalogName());
		sb.append("' and t1.table_schema = '");
		sb.append(exportContext.getSchemaName());
		sb.append("' and t1.");
		sb.append(getTableNameFieldName());
		sb.append(" not in (");
		sb.append(getPartitionedTableNamesSQL(exportContext));
		sb.append(") group by t1.");
		sb.append(getTableNameFieldName());
		sb.append(" order by t1.");
		sb.append(getTableNameFieldName());

		return sb.toString();
	}

	@Override
	public String getPartitionedTableNamesSQL(ExportContext exportContext) {
		StringBuilder sb = new StringBuilder(11);

		sb.append("select t2.");
		sb.append(getTableNameFieldName());
		sb.append(" from syscat.columns c2, sysibm.tables t2 where ");
		sb.append("c2.tabschema=t2.table_schema and t2.");
		sb.append(getTableNameFieldName());
		sb.append("=c2.tabname and t2.table_catalog = '");
		sb.append(exportContext.getCatalogName());
		sb.append("' and c2.colname = 'COMPANYID' group by t2.");
		sb.append(getTableNameFieldName());
		sb.append(" order by t2.");
		sb.append(getTableNameFieldName());

		return sb.toString();
	}

	@Override
	public String getTableNameFieldName() {
		return "table_name";
	}

}