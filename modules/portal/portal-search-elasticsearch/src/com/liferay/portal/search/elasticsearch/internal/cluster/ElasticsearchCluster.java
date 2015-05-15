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

package com.liferay.portal.search.elasticsearch.internal.cluster;

import com.liferay.portal.kernel.cluster.ClusterExecutor;
import com.liferay.portal.kernel.cluster.ClusterMasterExecutor;
import com.liferay.portal.kernel.cluster.ClusterNode;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.search.elasticsearch.connection.ElasticsearchConnection;
import com.liferay.portal.search.elasticsearch.connection.ElasticsearchConnectionManager;
import com.liferay.portal.search.elasticsearch.connection.OperationMode;
import com.liferay.portal.service.CompanyLocalService;

import java.util.List;

import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.Client;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author André de Oliveira
 */
@Component(immediate = true)
public class ElasticsearchCluster {

	@Activate
	protected void activate() {
		_replicasClusterListener = new ReplicasClusterListener(
			new ReplicasClusterContextImpl());

		_clusterExecutor.addClusterEventListener(_replicasClusterListener);

		_clusterMasterExecutor.addClusterMasterTokenTransitionListener(
			_replicasClusterListener);
	}

	@Deactivate
	protected void deactivate() {
		_clusterExecutor.removeClusterEventListener(_replicasClusterListener);

		_clusterMasterExecutor.removeClusterMasterTokenTransitionListener(
			_replicasClusterListener);

		_replicasClusterListener = null;
	}

	@Reference
	protected void setClusterExecutor(ClusterExecutor clusterExecutor) {
		_clusterExecutor = clusterExecutor;
	}

	@Reference
	protected void setClusterMasterExecutor(
		ClusterMasterExecutor clusterMasterExecutor) {

		_clusterMasterExecutor = clusterMasterExecutor;
	}

	@Reference
	protected void setCompanyLocalService(
		CompanyLocalService companyLocalService) {

		_companyLocalService = companyLocalService;
	}

	@Reference
	protected void setElasticsearchConnectionManager(
		ElasticsearchConnectionManager elasticsearchConnectionManager) {

		_elasticsearchConnectionManager = elasticsearchConnectionManager;
	}

	protected class ReplicasClusterContextImpl
		implements ReplicasClusterContext {

		@Override
		public int getClusterSize() {
			List<ClusterNode> clusterNodes = _clusterExecutor.getClusterNodes();

			return clusterNodes.size();
		}

		@Override
		public ReplicasManager getReplicasManager() {
			ElasticsearchConnection elasticsearchConnection =
				getActiveElasticsearchConnection();

			Client client = elasticsearchConnection.getClient();

			AdminClient adminClient = client.admin();

			return new ReplicasManagerImpl(adminClient.indices());
		}

		@Override
		public String[] getTargetIndexNames() {
			return ArrayUtil.toStringArray(getCompanyIdsWithActiveIndexes());
		}

		@Override
		public boolean isEmbeddedOperationMode() {
			ElasticsearchConnection elasticsearchConnection =
				getActiveElasticsearchConnection();

			OperationMode operationMode =
				elasticsearchConnection.getOperationMode();

			return operationMode == OperationMode.EMBEDDED;
		}

		@Override
		public boolean isOnMasterExecutor() {
			return _clusterMasterExecutor.isMaster();
		}

		protected ElasticsearchConnection getActiveElasticsearchConnection() {
			return _elasticsearchConnectionManager.getElasticsearchConnection();
		}

		private long[] getCompanyIdsWithActiveIndexes() {
			List<Company> companies = _companyLocalService.getCompanies();

			long[] companyIds = new long[companies.size()];

			for (int i = 0; i < companyIds.length; i++) {
				Company company = companies.get(i);
				companyIds[i] = company.getCompanyId();
			}

			return companyIds;
		}

	}

	private ClusterExecutor _clusterExecutor;
	private ClusterMasterExecutor _clusterMasterExecutor;
	private CompanyLocalService _companyLocalService;
	private ElasticsearchConnectionManager _elasticsearchConnectionManager;
	private ReplicasClusterListener _replicasClusterListener;

}