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

import com.liferay.osb.lcs.nosql.cassandra.spring.SessionFactoryBean;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class SQLImporter {

	public void importSQL() throws Exception {
		SessionFactoryBean sessionFactoryBean = new SessionFactoryBean();

		sessionFactoryBean.setCompression(_compression);
		sessionFactoryBean.setHostNames(_hostNames);
		sessionFactoryBean.setHostPort(_hostPort);
		sessionFactoryBean.setKeyspace(_keyspace);
		sessionFactoryBean.setLoggingRetryPolicyEnabled(
			_loggingRetryPolicyEnabled);

		_session = sessionFactoryBean.getSession();

		_cluster = _session.getCluster();

		importKeyspace();

		importTables();

		importIndexes();

		sessionFactoryBean.destroy();
	}

	public void setCompression(String compression) {
		_compression = compression;
	}

	public void setHostNames(String hostNames) {
		_hostNames = hostNames;
	}

	public void setHostPort(int hostPort) {
		_hostPort = hostPort;
	}

	public void setKeyspace(String keyspace) {
		_keyspace = keyspace;
	}

	public void setLoggingRetryPolicyEnabled(
		boolean loggingRetryPolicyEnabled) {

		_loggingRetryPolicyEnabled = loggingRetryPolicyEnabled;
	}

	protected List<String> getSQLs(String fileName) throws IOException {
		Class<?> clazz = getClass();

		ClassLoader classLoader = clazz.getClassLoader();

		InputStream inputStream = classLoader.getResourceAsStream(
			"com/liferay/osb/lcs/nosql/dependencies/" + fileName + ".sql");

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

		List<String> sqls = getSQLs("indexes");

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
			List<String> sqls = getSQLs("keyspace");

			String sql = sqls.get(0);

			sql = sql.replace("[$OSB_LCS_KEYSPACE$]", _keyspace);

			_session.execute(sql);
		}

		_session.execute("use " + _keyspace);
	}

	protected void importTables() throws Exception {
		Metadata metadata = _cluster.getMetadata();

		KeyspaceMetadata keyspaceMetadata = metadata.getKeyspace(_keyspace);

		List<String> sqls = getSQLs("tables");

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
	private String _compression;
	private String _hostNames;
	private int _hostPort;
	private String _keyspace;
	private boolean _loggingRetryPolicyEnabled;
	private Session _session;

}