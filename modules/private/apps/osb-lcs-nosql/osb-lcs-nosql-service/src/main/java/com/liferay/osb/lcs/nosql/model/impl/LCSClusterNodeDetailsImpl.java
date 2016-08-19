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

package com.liferay.osb.lcs.nosql.model.impl;

import com.liferay.lcs.util.LCSClusterNodeStatus;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.osb.lcs.nosql.model.LCSClusterNodeDetails;

import java.util.Date;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
public class LCSClusterNodeDetailsImpl implements LCSClusterNodeDetails {

	@Override
	public int getBuildNumber() {
		return _buildNumber;
	}

	@Override
	public Map<Integer, String> getCompanyIdsWebIds() {
		return _companyIdsWebIds;
	}

	@Override
	public Date getConfigurationModifiedDate() {
		return _configurationModifiedDate;
	}

	@Override
	public long getHeartbeatInterval() {
		return _heartbeatInterval;
	}

	@Override
	public String getKey() {
		return _key;
	}

	@Override
	public long getLastHeartbeat() {
		return _lastHeartbeat;
	}

	@Override
	public int getMetricsLCSServiceStatus() {
		if (LCSClusterNodeStatus.METRICS_LCS_SERVICE_ENABLED.hasStatus(
				_status)) {

			return LCSConstants.METRICS_LCS_SERVICE_AVAILABLE;
		}

		return LCSConstants.METRICS_LCS_SERVICE_UNAVAILABLE;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public int getMonitoringStatus() {
		if (LCSClusterNodeStatus.MONITORING_ENABLED.hasStatus(_status)) {
			return LCSConstants.MONITORING_AVAILABLE;
		}

		return LCSConstants.MONITORING_UNAVAILABLE;
	}

	@Override
	public int getPatchesLCSServiceStatus() {
		if (LCSClusterNodeStatus.PATCHES_LCS_SERVICE_ENABLED.hasStatus(
				_status)) {

			return LCSConstants.PATCHES_LCS_SERVICE_AVAILABLE;
		}

		return LCSConstants.PATCHES_LCS_SERVICE_UNAVAILABLE;
	}

	@Override
	public int getPatchingToolStatus() {
		if (LCSClusterNodeStatus.PATCHING_TOOL_ENABLED.hasStatus(_status)) {
			return LCSConstants.PATCHING_TOOL_AVAILABLE;
		}

		return LCSConstants.PATCHING_TOOL_UNAVAILABLE;
	}

	@Override
	public int getPatchingToolVersion() {
		return _patchingToolVersion;
	}

	@Override
	public String getPortalEdition() {
		return _portalEdition;
	}

	@Override
	public int getPortalPropertiesLCSServiceStatus() {
		if (LCSClusterNodeStatus.PORTAL_PROPERTIES_LCS_SERVICE_ENABLED.
				hasStatus(_status)) {

			return LCSConstants.PORTAL_PROPERTIES_LCS_SERVICE_AVAILABLE;
		}

		return LCSConstants.PORTAL_PROPERTIES_LCS_SERVICE_UNAVAILABLE;
	}

	@Override
	public String getProtocolVersion() {
		return _protocolVersion;
	}

	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public String getUUID() {
		return null;
	}

	@Override
	public boolean isNew() {
		return _new;
	}

	@Override
	public void setBuildNumber(int buildNumber) {
		_buildNumber = buildNumber;
	}

	@Override
	public void setCompanyIdsWebIds(Map<Integer, String> companyIdsWebIds) {
		_companyIdsWebIds = companyIdsWebIds;
	}

	@Override
	public void setConfigurationModifiedDate(Date configurationModifiedDate) {
		_configurationModifiedDate = configurationModifiedDate;
	}

	@Override
	public void setHeartbeatInterval(long heartbeatInterval) {
		_heartbeatInterval = heartbeatInterval;
	}

	@Override
	public void setKey(String key) {
		_key = key;
	}

	@Override
	public void setLastHeartbeat(long lastHeartbeat) {
		_lastHeartbeat = lastHeartbeat;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	@Override
	public void setNew(boolean n) {
		_new = n;
	}

	@Override
	public void setPatchingToolVersion(int patchingToolVersion) {
		_patchingToolVersion = patchingToolVersion;
	}

	@Override
	public void setPortalEdition(String portalEdition) {
		_portalEdition = portalEdition;
	}

	@Override
	public void setProtocolVersion(String protocolVersion) {
		_protocolVersion = protocolVersion;
	}

	@Override
	public void setStatus(int status) {
		_status = status;
	}

	@Override
	public void setUUID(String s) {
	}

	private int _buildNumber;
	private Map<Integer, String> _companyIdsWebIds;
	private Date _configurationModifiedDate;
	private long _heartbeatInterval;
	private String _key;
	private long _lastHeartbeat;
	private Date _modifiedDate;
	private boolean _new;
	private int _patchingToolVersion;
	private String _portalEdition;
	private String _protocolVersion;
	private int _status;

}