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

package com.liferay.osb.lcs.nosql.service.impl;

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeDetails;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeDetailsService;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodeCurrentThreadsMetricsPersistence;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodeDetailsPersistence;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodeHibernateMetricsPersistence;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodeInstallationEnvironmentPersistence;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodeJDBCConnectionPoolMetricsPersistence;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodeJVMMetricsPersistence;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodeLayoutMetricsCounterPersistence;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodeLayoutMetricsPersistence;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodeLiferayVMMetricsPersistence;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodePatchesEventsPersistence;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodePatchesPersistence;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodePortletMetricsCounterPersistence;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodePortletMetricsPersistence;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodePropertiesPersistence;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodeScriptPersistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@Component(immediate = true, service = LCSClusterNodeDetailsService.class)
public class LCSClusterNodeDetailsServiceImpl
	implements LCSClusterNodeDetailsService {

	@Override
	public LCSClusterNodeDetails addLCSClusterNodeDetails(
		int buildNumber, Map<Integer, String> companyIdsWebIds,
		Date configurationModifiedDate, long heartbeatInterval, String key,
		long lastHeartbeat, Date modifiedDate, int patchingToolVersion,
		String portalEdition, String protocolVersion, int status) {

		LCSClusterNodeDetails lcsClusterNodeDetails =
			_lcsClusterNodeDetailsPersistence.create();

		lcsClusterNodeDetails.setBuildNumber(buildNumber);
		lcsClusterNodeDetails.setCompanyIdsWebIds(companyIdsWebIds);
		lcsClusterNodeDetails.setConfigurationModifiedDate(
			configurationModifiedDate);
		lcsClusterNodeDetails.setHeartbeatInterval(heartbeatInterval);
		lcsClusterNodeDetails.setKey(key);
		lcsClusterNodeDetails.setLastHeartbeat(lastHeartbeat);
		lcsClusterNodeDetails.setModifiedDate(modifiedDate);
		lcsClusterNodeDetails.setPatchingToolVersion(patchingToolVersion);
		lcsClusterNodeDetails.setPortalEdition(portalEdition);
		lcsClusterNodeDetails.setProtocolVersion(protocolVersion);
		lcsClusterNodeDetails.setStatus(status);

		return _lcsClusterNodeDetailsPersistence.update(lcsClusterNodeDetails);
	}

	@Override
	public void deleteLCSClusterNodeDetails(String key) {
		_lcsClusterNodeCurrentThreadsMetricsPersistence.delete(key);
		_lcsClusterNodeDetailsPersistence.delete(key);
		_lcsClusterNodeHibernateMetricsPersistence.delete(key);
		_lcsClusterNodeInstallationEnvironmentPersistence.delete(key);
		_lcsClusterNodeJDBCConnectionPoolMetricsPersistence.delete(key);
		_lcsClusterNodeJVMMetricsPersistence.delete(key);
		_lcsClusterNodeLayoutMetricsCounterPersistence.delete(key);
		_lcsClusterNodeLayoutMetricsPersistence.delete(key);
		_lcsClusterNodeLiferayVMMetricsPersistence.delete(key);
		_lcsClusterNodePatchesEventsPersistence.delete(key);
		_lcsClusterNodePatchesPersistence.delete(key);
		_lcsClusterNodePortletMetricsCounterPersistence.reset(key);
		_lcsClusterNodePortletMetricsPersistence.delete(key);
		_lcsClusterNodePropertiesPersistence.delete(key);
		_lcsClusterNodeScriptPersistence.delete(key);
	}

	@Override
	public LCSClusterNodeDetails fetchLCSClusterNodeDetails(String key) {
		return _lcsClusterNodeDetailsPersistence.fetchByKey(key);
	}

	@Override
	public List<? extends LCSClusterNodeDetails>
		getActiveLCSClusterNodeDetailsList() {

		return _lcsClusterNodeDetailsPersistence.findByActive();
	}

	@Override
	public List<LCSClusterNodeDetails> getLCSClusterNodeDetailsList(
		int status) {

		return _lcsClusterNodeDetailsPersistence.findByStatus(status);
	}

	@Override
	public List<LCSClusterNodeDetails> getLCSClusterNodeDetailsList(
		List<String> keys) {

		List<LCSClusterNodeDetails> lcsClusterNodeDetailsList =
			new ArrayList<>();

		for (String key : keys) {
			LCSClusterNodeDetails lcsClusterNodeDetails =
				_lcsClusterNodeDetailsPersistence.fetchByKey(key);

			if (lcsClusterNodeDetails == null) {
				continue;
			}

			lcsClusterNodeDetailsList.add(lcsClusterNodeDetails);
		}

		return lcsClusterNodeDetailsList;
	}

	@Override
	public int getStatus(String key) {
		LCSClusterNodeDetails lcsClusterNodeDetails =
			_lcsClusterNodeDetailsPersistence.fetchByKey(key);

		return lcsClusterNodeDetails.getStatus();
	}

	public void setLCSClusterNodeCurrentThreadsMetricsPersistence(
		LCSClusterNodeCurrentThreadsMetricsPersistence
			lcsClusterNodeCurrentThreadsMetricsPersistence) {

		_lcsClusterNodeCurrentThreadsMetricsPersistence =
			lcsClusterNodeCurrentThreadsMetricsPersistence;
	}

	public void setLCSClusterNodeDetailsPersistence(
		LCSClusterNodeDetailsPersistence lcsClusterNodeDetailsPersistence) {

		_lcsClusterNodeDetailsPersistence = lcsClusterNodeDetailsPersistence;
	}

	public void setLCSClusterNodeHibernateMetricsPersistence(
		LCSClusterNodeHibernateMetricsPersistence
			lcsClusterNodeHibernateMetricsPersistence) {

		_lcsClusterNodeHibernateMetricsPersistence =
			lcsClusterNodeHibernateMetricsPersistence;
	}

	public void setLCSClusterNodeInstallationEnvironmentPersistence(
		LCSClusterNodeInstallationEnvironmentPersistence
			lcsClusterNodeInstallationEnvironmentPersistence) {

		_lcsClusterNodeInstallationEnvironmentPersistence =
			lcsClusterNodeInstallationEnvironmentPersistence;
	}

	public void setLCSClusterNodeJDBCConnectionPoolMetricsPersistence(
		LCSClusterNodeJDBCConnectionPoolMetricsPersistence
			lcsClusterNodeJDBCConnectionPoolMetricsPersistence) {

		_lcsClusterNodeJDBCConnectionPoolMetricsPersistence =
			lcsClusterNodeJDBCConnectionPoolMetricsPersistence;
	}

	public void setLCSClusterNodeJVMMetricsPersistence(
		LCSClusterNodeJVMMetricsPersistence
			lcsClusterNodeJVMMetricsPersistence) {

		_lcsClusterNodeJVMMetricsPersistence =
			lcsClusterNodeJVMMetricsPersistence;
	}

	public void setLCSClusterNodeLayoutMetricsCounterPersistence(
		LCSClusterNodeLayoutMetricsCounterPersistence
			lcsClusterNodeLayoutMetricsCounterPersistence) {

		_lcsClusterNodeLayoutMetricsCounterPersistence =
			lcsClusterNodeLayoutMetricsCounterPersistence;
	}

	public void setLCSClusterNodeLayoutMetricsPersistence(
		LCSClusterNodeLayoutMetricsPersistence
			lcsClusterNodeLayoutMetricsPersistence) {

		_lcsClusterNodeLayoutMetricsPersistence =
			lcsClusterNodeLayoutMetricsPersistence;
	}

	public void setLCSClusterNodeLiferayVMMetricsPersistence(
		LCSClusterNodeLiferayVMMetricsPersistence
			lcsClusterNodeLiferayVMMetricsPersistence) {

		_lcsClusterNodeLiferayVMMetricsPersistence =
			lcsClusterNodeLiferayVMMetricsPersistence;
	}

	public void setLCSClusterNodePatchesEventsPersistence(
		LCSClusterNodePatchesEventsPersistence
			lcsClusterNodePatchesEventsPersistence) {

		_lcsClusterNodePatchesEventsPersistence =
			lcsClusterNodePatchesEventsPersistence;
	}

	public void setLCSClusterNodePatchesPersistence(
		LCSClusterNodePatchesPersistence lcsClusterNodePatchesPersistence) {

		_lcsClusterNodePatchesPersistence = lcsClusterNodePatchesPersistence;
	}

	public void setLCSClusterNodePortletMetricsCounterPersistence(
		LCSClusterNodePortletMetricsCounterPersistence
			lcsClusterNodePortletMetricsCounterPersistence) {

		_lcsClusterNodePortletMetricsCounterPersistence =
			lcsClusterNodePortletMetricsCounterPersistence;
	}

	public void setLCSClusterNodePortletMetricsPersistence(
		LCSClusterNodePortletMetricsPersistence
			lcsClusterNodePortletMetricsPersistence) {

		_lcsClusterNodePortletMetricsPersistence =
			lcsClusterNodePortletMetricsPersistence;
	}

	public void setLCSClusterNodePropertiesPersistence(
		LCSClusterNodePropertiesPersistence
			lcsClusterNodePropertiesPersistence) {

		_lcsClusterNodePropertiesPersistence =
			lcsClusterNodePropertiesPersistence;
	}

	public void setLCSClusterNodeScriptPersistence(
		LCSClusterNodeScriptPersistence lcsClusterNodeScriptPersistence) {

		_lcsClusterNodeScriptPersistence = lcsClusterNodeScriptPersistence;
	}

	@Override
	public synchronized void update(
		LCSClusterNodeDetails lcsClusterNodeDetails) {

		LCSClusterNodeDetails curLCSClusterNodeDetails =
			_lcsClusterNodeDetailsPersistence.fetchByKey(
				lcsClusterNodeDetails.getKey());

		curLCSClusterNodeDetails.setBuildNumber(
			lcsClusterNodeDetails.getBuildNumber());
		curLCSClusterNodeDetails.setCompanyIdsWebIds(
			lcsClusterNodeDetails.getCompanyIdsWebIds());
		curLCSClusterNodeDetails.setConfigurationModifiedDate(
			lcsClusterNodeDetails.getConfigurationModifiedDate());
		curLCSClusterNodeDetails.setHeartbeatInterval(
			lcsClusterNodeDetails.getHeartbeatInterval());
		curLCSClusterNodeDetails.setLastHeartbeat(
			lcsClusterNodeDetails.getLastHeartbeat());
		curLCSClusterNodeDetails.setModifiedDate(new Date());
		curLCSClusterNodeDetails.setNew(lcsClusterNodeDetails.isNew());
		curLCSClusterNodeDetails.setPatchingToolVersion(
			lcsClusterNodeDetails.getPatchingToolVersion());
		curLCSClusterNodeDetails.setPortalEdition(
			lcsClusterNodeDetails.getPortalEdition());
		curLCSClusterNodeDetails.setProtocolVersion(
			lcsClusterNodeDetails.getProtocolVersion());
		curLCSClusterNodeDetails.setStatus(lcsClusterNodeDetails.getStatus());

		_lcsClusterNodeDetailsPersistence.update(curLCSClusterNodeDetails);
	}

	@Override
	public synchronized void updateConfigurationModifiedDate(
		String key, Date configurationModifiedDate) {

		_lcsClusterNodeDetailsPersistence.updateConfigurationModifiedDate(
			configurationModifiedDate, key, new Date());
	}

	@Override
	public synchronized void updateLastHeartbeat(
		String key, long lastHeartbeat) {

		_lcsClusterNodeDetailsPersistence.updateLastHeartbeatColumn(
			key, lastHeartbeat, new Date());
	}

	@Override
	public synchronized void updateStatus(String key, int status) {
		_lcsClusterNodeDetailsPersistence.updateStatusColumn(
			key, new Date(), status);
	}

	private LCSClusterNodeCurrentThreadsMetricsPersistence
		_lcsClusterNodeCurrentThreadsMetricsPersistence;
	private LCSClusterNodeDetailsPersistence _lcsClusterNodeDetailsPersistence;
	private LCSClusterNodeHibernateMetricsPersistence
		_lcsClusterNodeHibernateMetricsPersistence;
	private LCSClusterNodeInstallationEnvironmentPersistence
		_lcsClusterNodeInstallationEnvironmentPersistence;
	private LCSClusterNodeJDBCConnectionPoolMetricsPersistence
		_lcsClusterNodeJDBCConnectionPoolMetricsPersistence;
	private LCSClusterNodeJVMMetricsPersistence
		_lcsClusterNodeJVMMetricsPersistence;
	private LCSClusterNodeLayoutMetricsCounterPersistence
		_lcsClusterNodeLayoutMetricsCounterPersistence;
	private LCSClusterNodeLayoutMetricsPersistence
		_lcsClusterNodeLayoutMetricsPersistence;
	private LCSClusterNodeLiferayVMMetricsPersistence
		_lcsClusterNodeLiferayVMMetricsPersistence;
	private LCSClusterNodePatchesEventsPersistence
		_lcsClusterNodePatchesEventsPersistence;
	private LCSClusterNodePatchesPersistence _lcsClusterNodePatchesPersistence;
	private LCSClusterNodePortletMetricsCounterPersistence
		_lcsClusterNodePortletMetricsCounterPersistence;
	private LCSClusterNodePortletMetricsPersistence
		_lcsClusterNodePortletMetricsPersistence;
	private LCSClusterNodePropertiesPersistence
		_lcsClusterNodePropertiesPersistence;
	private LCSClusterNodeScriptPersistence _lcsClusterNodeScriptPersistence;

}