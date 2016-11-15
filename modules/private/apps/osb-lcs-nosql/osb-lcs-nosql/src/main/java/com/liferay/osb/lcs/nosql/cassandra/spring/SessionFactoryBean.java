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

package com.liferay.osb.lcs.nosql.cassandra.spring;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ProtocolOptions;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.LoggingRetryPolicy;
import com.datastax.driver.core.policies.Policies;

import com.liferay.osb.lcs.nosql.importer.CQLImporter;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 * @author Riccardo Ferrari
 */
public class SessionFactoryBean {

	public void afterPropertiesSet() throws Exception {
		if (_cqlImporterEnabled) {
			CQLImporter cqlImporter = new CQLImporter();

			cqlImporter.setCluster(getCluster());
			cqlImporter.setKeyspace(_keyspace);

			cqlImporter.importCQL();
		}
	}

	public void destroy() {
		_cluster.close();
	}

	public Cluster getCluster() throws Exception {
		if (_cluster != null) {
			return _cluster;
		}

		Cluster.Builder builder = Cluster.builder();

		for (String hostName : _hostNames) {
			builder.addContactPoint(hostName);
		}

		if (!_compression.equalsIgnoreCase(
				ProtocolOptions.Compression.NONE.name())) {

			if (_compression.equalsIgnoreCase(
					ProtocolOptions.Compression.LZ4.name())) {

				builder.withCompression(ProtocolOptions.Compression.LZ4);
			}
			else if (_compression.equalsIgnoreCase(
						ProtocolOptions.Compression.SNAPPY.name())) {

				builder.withCompression(ProtocolOptions.Compression.SNAPPY);
			}
		}

		builder.withoutMetrics();
		builder.withPort(_hostPort);

		if (_loggingRetryPolicyEnabled) {
			builder.withRetryPolicy(
				new LoggingRetryPolicy(Policies.defaultRetryPolicy()));
		}

		_cluster = builder.build();

		return _cluster;
	}

	public Session getSession() throws Exception {
		Cluster cluster = getCluster();

		return cluster.connect(_keyspace);
	}

	public void setCompression(String compression) {
		_compression = compression;
	}

	public void setCqlImporterEnabled(boolean cqlImporterEnabled) {
		_cqlImporterEnabled = cqlImporterEnabled;
	}

	public void setHostNames(String hostNames) {
		_hostNames = hostNames.split(",");
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

	private Cluster _cluster;
	private String _compression;
	private boolean _cqlImporterEnabled;
	private String[] _hostNames;
	private int _hostPort;
	private String _keyspace;
	private boolean _loggingRetryPolicyEnabled;

}