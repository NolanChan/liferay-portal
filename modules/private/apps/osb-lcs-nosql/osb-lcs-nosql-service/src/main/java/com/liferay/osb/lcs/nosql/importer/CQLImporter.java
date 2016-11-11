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

package com.liferay.osb.lcs.nosql.importer;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.IndexMetadata;
import com.datastax.driver.core.KeyspaceMetadata;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.TableMetadata;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 * @author Riccardo Ferrari
 */
public class CQLImporter {

	public void importCQL() throws Exception {
		_session = _cluster.connect();

		importKeyspace();

		importTables();

		importMaterializedViews();

		importIndexes();

		_session.close();
	}

	public void setCluster(Cluster cluster) {
		_cluster = cluster;
	}

	public void setKeyspace(String keyspace) {
		_keyspace = keyspace;
	}

	protected List<String> getCQLs(String fileName) throws IOException {
		Class<?> clazz = getClass();

		ClassLoader classLoader = clazz.getClassLoader();

		InputStream inputStream = classLoader.getResourceAsStream(
			"com/liferay/osb/lcs/nosql/dependencies/" + fileName + ".cql");

		if (inputStream == null) {
			Collections.emptyList();
		}

		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		byte[] bytes = null;

		try {
			int next = inputStream.read();

			while (next > -1) {
				byteArrayOutputStream.write(next);
				next = inputStream.read();
			}

			byteArrayOutputStream.flush();

			bytes = byteArrayOutputStream.toByteArray();
		}
		catch (NullPointerException npe) {
			return Collections.emptyList();
		}
		finally {
			if (byteArrayOutputStream != null) {
				byteArrayOutputStream.close();
			}

			if (inputStream != null) {
				inputStream.close();
			}
		}

		List<String> sqls = new ArrayList<>();

		String content = new String(bytes);

		content = content.replace("\n", " ");

		StringTokenizer stringTokenizer = new StringTokenizer(content, ";");

		while (stringTokenizer.hasMoreTokens()) {
			String sql = stringTokenizer.nextToken().trim();

			if (sql.length() > 0) {
				sqls.add(sql);
			}
		}

		return sqls;
	}

	protected void importIndexes() throws Exception {
		Metadata metadata = _cluster.getMetadata();

		KeyspaceMetadata keyspaceMetadata = metadata.getKeyspace(_keyspace);

		List<String> sqls = getCQLs("indexes");

		for (String sql : sqls) {
			int beginIndex = sql.indexOf(" on ") + 4;
			int endIndex = sql.indexOf("(");

			String tableName = sql.substring(beginIndex, endIndex);

			tableName = tableName.toLowerCase();

			TableMetadata tableMetadata = keyspaceMetadata.getTable(tableName);

			beginIndex = sql.indexOf("create index ") + 13;
			endIndex = sql.indexOf(" on");

			String indexName = sql.substring(beginIndex, endIndex);

			indexName = indexName.toLowerCase();

			Collection<IndexMetadata> indexMetadatas =
				tableMetadata.getIndexes();

			boolean indexExists = false;

			for (IndexMetadata indexMetadata : indexMetadatas) {
				if (indexName.equalsIgnoreCase(indexMetadata.getName())) {
					indexExists = true;

					break;
				}
			}

			if (!indexExists) {
				_session.execute(sql);
			}
		}
	}

	protected void importKeyspace() throws Exception {
		Metadata metadata = _cluster.getMetadata();

		if (metadata.getKeyspace(_keyspace) == null) {
			List<String> sqls = getCQLs("keyspace");

			String sql = sqls.get(0);

			sql = sql.replace("[$KEYSPACE_PLACEHOLDER$]", _keyspace);

			_session.execute(sql);
		}

		_session.execute("use " + _keyspace);
	}

	protected void importMaterializedViews() throws Exception {
		Metadata metadata = _cluster.getMetadata();

		KeyspaceMetadata keyspaceMetadata = metadata.getKeyspace(_keyspace);

		List<String> sqls = getCQLs("views");

		for (String sql : sqls) {
			int index = sql.indexOf(" as");

			String materializedViewName = sql.substring(25, index);

			materializedViewName = materializedViewName.toLowerCase();

			if (keyspaceMetadata.getMaterializedView(materializedViewName) ==
					null) {

				_session.execute(sql);
			}
		}
	}

	protected void importTables() throws Exception {
		Metadata metadata = _cluster.getMetadata();

		KeyspaceMetadata keyspaceMetadata = metadata.getKeyspace(_keyspace);

		List<String> sqls = getCQLs("tables");

		for (String sql : sqls) {
			int index = sql.indexOf(" (");

			String tableName = sql.substring(13, index);

			tableName = tableName.toLowerCase();

			if (keyspaceMetadata.getTable(tableName) == null) {
				_session.execute(sql);
			}
		}
	}

	private Cluster _cluster;
	private String _keyspace;
	private Session _session;

}