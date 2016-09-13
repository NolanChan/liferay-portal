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

package com.liferay.osb.lcs.model.impl;

import aQute.bnd.annotation.ProviderType;
import com.liferay.lcs.util.LCSClusterNodeStatus;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSNotification;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.service.LCSClusterEntryLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSNotificationLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSProjectLocalServiceUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Igor Beslic
 * @author Peter Shin
 */
@ProviderType
public class LCSClusterNodeImpl extends LCSClusterNodeBaseImpl {

	public LCSClusterNodeImpl() {
	}

	@Override
	public Date getConfigurationModifiedDate() {
		return _configurationModifiedDate;
	}

	@Override
	public List<String> getInstallablePatchNames() {
		return _installablePatchNames;
	}

	@Override
	public int getLCSClusterNodeClusterLinkStatus() {
		if (LCSClusterNodeStatus.
			LCS_CLUSTER_NODE_CLUSTER_LINK_HEALTHY.hasStatus(_status)) {

			return LCSConstants.LCS_CLUSTER_NODE_CLUSTER_LINK_HEALTHY;
		}

		return LCSConstants.LCS_CLUSTER_NODE_CLUSTER_LINK_BROKEN;
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
	public Map<String, Integer> getPatchIdsStatuses() {
		return _patchIdsStatuses;
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
	public boolean hasStatus(int status) {
		if ((status & getStatus()) == status) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isLCSCLusterNodeClusterLinkHealthy() {
		if (LCSClusterNodeStatus.
			LCS_CLUSTER_NODE_CLUSTER_LINK_HEALTHY.hasStatus(_status)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean isLCSNotificationEnabled(long userId, int type) {
		LCSNotification lcsNotification =
			LCSNotificationLocalServiceUtil.fetchLCSClusterNodeLCSNotification(
				userId, getLcsClusterNodeId(), type);

		if (lcsNotification != null) {
			return lcsNotification.isEnabled();
		}

		lcsNotification =
			LCSNotificationLocalServiceUtil.fetchLCSClusterEntryLCSNotification(
				userId, getLcsClusterEntryId(), type);

		if (lcsNotification != null) {
			return lcsNotification.isEnabled();
		}

		LCSClusterEntry lcsClusterEntry =
			LCSClusterEntryLocalServiceUtil.fetchLCSClusterEntry(
				getLcsClusterEntryId());

		if (lcsClusterEntry == null) {
			return false;
		}

		LCSProject lcsProject = LCSProjectLocalServiceUtil.fetchLCSProject(
			lcsClusterEntry.getLcsProjectId());

		if ((lcsProject == null) || lcsProject.isArchived()) {
			return false;
		}

		lcsNotification =
			LCSNotificationLocalServiceUtil.fetchLCSProjectLCSNotification(
				userId, lcsProject.getLcsProjectId(), type);

		if (lcsNotification != null) {
			return lcsNotification.isEnabled();
		}

		return false;
	}

	@Override
	public boolean isMetricsLCSServiceEnabled() {
		if (LCSClusterNodeStatus.METRICS_LCS_SERVICE_ENABLED.hasStatus(
			getStatus())) {

			return true;
		}

		return false;
	}

	@Override
	public boolean isMonitoringUnavailable() {
		if (getMonitoringStatus() == LCSConstants.MONITORING_UNAVAILABLE) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isOffline() {
		return LCSClusterNodeStatus.isInactive(getStatus());
	}

	@Override
	public boolean isPatchesLCSServiceEnabled() {
		if (LCSClusterNodeStatus.PATCHES_LCS_SERVICE_ENABLED.hasStatus(
			getStatus())) {

			return true;
		}

		return false;
	}

	@Override
	public boolean isPatchingToolUnavailable() {
		if (getPatchingToolStatus() == LCSConstants.PATCHING_TOOL_UNAVAILABLE) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isPortalPropertiesLCSServiceEnabled() {
		if (LCSClusterNodeStatus.PORTAL_PROPERTIES_LCS_SERVICE_ENABLED.
			hasStatus(getStatus())) {

			return true;
		}

		return false;
	}

	@Override
	public void setConfigurationModifiedDate(Date configurationModifiedDate) {
		_configurationModifiedDate = configurationModifiedDate;
	}

	@Override
	public void setInstallablePatchNames(List<String> installablePatchNames) {
		_installablePatchNames = installablePatchNames;
	}

	@Override
	public void setPatchIdsStatuses(Map<String, Integer> patchIdsStatuses) {
		_patchIdsStatuses = patchIdsStatuses;
	}

	@Override
	public void setPatchingToolVersion(int patchingToolVersion) {
		_patchingToolVersion = patchingToolVersion;
	}

	@Override
	public void setPortalEdition(String portalEdition) {
		_portalEdition = portalEdition;
	}

	private Date _configurationModifiedDate;
	private List<String> _installablePatchNames = new ArrayList<>();
	private Map<String, Integer> _patchIdsStatuses = new HashMap<>();
	private int _patchingToolVersion;
	private String _portalEdition;
	private int _status;

}