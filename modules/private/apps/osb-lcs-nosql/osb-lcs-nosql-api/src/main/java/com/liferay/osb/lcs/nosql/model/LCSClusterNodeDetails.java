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

package com.liferay.osb.lcs.nosql.model;

import java.util.Date;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
public interface LCSClusterNodeDetails extends LCSClusterNode {

	public int getBuildNumber();

	public Map<Integer, String> getCompanyIdsWebIds();

	public Date getConfigurationModifiedDate();

	public long getHeartbeatInterval();

	public long getLastHeartbeat();

	public int getMetricsLCSServiceStatus();

	public int getMonitoringStatus();

	public int getPatchesLCSServiceStatus();

	public int getPatchingToolStatus();

	public int getPatchingToolVersion();

	public String getPortalEdition();

	public int getPortalPropertiesLCSServiceStatus();

	public String getProtocolVersion();

	public int getStatus();

	public void setBuildNumber(int buildNumber);

	public void setCompanyIdsWebIds(Map<Integer, String> companyIdsWebIds);

	public void setConfigurationModifiedDate(Date configurationModifiedDate);

	public void setHeartbeatInterval(long heartbeatInterval);

	public void setLastHeartbeat(long lastHeartbeat);

	public void setPatchingToolVersion(int patchingToolVersion);

	public void setPortalEdition(String portalEdition);

	public void setProtocolVersion(String protocolVersion);

	public void setStatus(int status);

}