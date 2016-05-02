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

package com.liferay.osb.lcs.nosql.service;

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeDetails;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public interface LCSClusterNodeDetailsService {

	public LCSClusterNodeDetails addLCSClusterNodeDetails(
		int buildNumber, Map<Integer, String> companyIdsWebIds,
		Date configurationModifiedDate, long heartbeatInterval, String key,
		long lastHeartbeat, Date modifiedDate, int patchingToolVersion,
		String portalEdition, String protocolVersion, int status);

	public void deleteLCSClusterNodeDetails(String key);

	public LCSClusterNodeDetails fetchLCSClusterNodeDetails(String key);

	public List<? extends LCSClusterNodeDetails>
		getActiveLCSClusterNodeDetailsList();

	public List<? extends LCSClusterNodeDetails> getLCSClusterNodeDetailsList(
		int status);

	public List<? extends LCSClusterNodeDetails> getLCSClusterNodeDetailsList(
		List<String> keys);

	public int getStatus(String key);

	public void update(LCSClusterNodeDetails lcsClusterNodeDetails);

	public void updateConfigurationModifiedDate(
		String key, Date configurationModifiedDate);

	public void updateLastHeartbeat(String key, long lastHeartbeat);

	public void updateStatus(String key, int status);

}