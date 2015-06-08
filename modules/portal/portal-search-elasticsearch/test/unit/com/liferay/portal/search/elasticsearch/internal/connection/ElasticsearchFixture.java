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

package com.liferay.portal.search.elasticsearch.internal.connection;

import com.liferay.portal.kernel.util.Props;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.elasticsearch.configuration.ElasticsearchConfiguration;
import com.liferay.portal.search.elasticsearch.connection.ElasticsearchConnection;
import com.liferay.portal.search.elasticsearch.internal.cluster.ClusterSettingsContext;
import com.liferay.portal.search.elasticsearch.internal.cluster.UnicastSettingsContributor;

import java.io.File;

import java.util.HashMap;

import org.apache.commons.io.FileUtils;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequestBuilder;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequestBuilder;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.ClusterAdminClient;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.indices.IndexMissingException;

import org.mockito.Mockito;

/**
 * @author André de Oliveira
 */
public class ElasticsearchFixture {

	public ElasticsearchFixture(String subdirName) {
		_tmpDirName = "tmp/" + subdirName;
	}

	public Index createIndex(String indexName) {
		indexName = StringUtil.toLowerCase(indexName);

		IndicesAdminClient indicesAdminClient = getIndicesAdminClient();

		DeleteIndexRequestBuilder deleteIndexRequestBuilder =
			indicesAdminClient.prepareDelete(indexName);

		try {
			deleteIndexRequestBuilder.get();
		}
		catch (IndexMissingException ime) {
		}

		CreateIndexRequestBuilder createIndexRequestBuilder =
			indicesAdminClient.prepareCreate(indexName);

		createIndexRequestBuilder.get();

		return new Index(indexName);
	}

	public void createNode() throws Exception {
		deleteTmpDir();

		_elasticsearchConnection = createElasticsearchConnection();
	}

	public void destroyNode() throws Exception {
		_elasticsearchConnection.close();

		deleteTmpDir();
	}

	public ClusterHealthResponse getClusterHealthResponse() {
		Client client = _elasticsearchConnection.getClient();

		AdminClient adminClient = client.admin();

		ClusterAdminClient clusterAdminClient = adminClient.cluster();

		ClusterHealthRequestBuilder clusterHealthRequestBuilder =
			clusterAdminClient.prepareHealth();

		ClusterHealthRequest clusterHealthRequest =
			clusterHealthRequestBuilder.request();

		ActionFuture<ClusterHealthResponse> health = clusterAdminClient.health(
			clusterHealthRequest);

		return health.actionGet();
	}

	public IndicesAdminClient getIndicesAdminClient() {
		Client client = _elasticsearchConnection.getClient();

		AdminClient adminClient = client.admin();

		return adminClient.indices();
	}

	public void setClusterSettingsContext(
		ClusterSettingsContext clusterSettingsContext) {

		_clusterSettingsContext = clusterSettingsContext;
	}

	public void setUp() throws Exception {
		createNode();
	}

	public void tearDown() throws Exception {
		destroyNode();
	}

	public class Index {

		public Index(String name) {
			_name = name;
		}

		public String getName() {
			return _name;
		}

		private final String _name;

	}

	protected void addUnicast(
		EmbeddedElasticsearchConnection embeddedElasticsearchConnection) {

		if (_clusterSettingsContext == null) {
			return;
		}

		UnicastSettingsContributor unicastSettingsContributor =
			new UnicastSettingsContributor();

		unicastSettingsContributor.setClusterSettingsContext(
			_clusterSettingsContext);

		embeddedElasticsearchConnection.addSettingsContributor(
			unicastSettingsContributor);
	}

	protected ElasticsearchConnection createElasticsearchConnection() {
		EmbeddedElasticsearchConnection embeddedElasticsearchConnection =
			new EmbeddedElasticsearchConnection();

		addUnicast(embeddedElasticsearchConnection);

		Props props = Mockito.mock(Props.class);

		Mockito.when(
			props.get(PropsKeys.LIFERAY_HOME)
		).thenReturn(
			_tmpDirName
		);

		embeddedElasticsearchConnection.setProps(props);

		HashMap<String, Object> properties = new HashMap<>();

		properties.put(
			"configurationPid", ElasticsearchConfiguration.class.getName());

		embeddedElasticsearchConnection.activate(properties);

		return embeddedElasticsearchConnection;
	}

	protected void deleteTmpDir() throws Exception {
		FileUtils.deleteDirectory(new File(_tmpDirName));
	}

	private ClusterSettingsContext _clusterSettingsContext;
	private ElasticsearchConnection _elasticsearchConnection;
	private final String _tmpDirName;

}