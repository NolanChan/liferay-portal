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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeSite;

import java.util.Date;
import java.util.List;

/**
 * @author Riccardo Ferrari
 */
public interface LCSClusterNodeSiteService {

	public LCSClusterNodeSite addLCSClusterNodeSite(
		long companyId, String friendlyURL, long groupId, String groupUUID,
		long installationId, String key, Date modifiedDate, String name,
		int type);

	public LCSClusterNodeSite getLCSClusterNodeSite(
		long companyId, long groupId, long installationId);

	public List<? extends LCSClusterNodeSite> getLCSClusterNodeSites(
		long installationId);

	public List<? extends LCSClusterNodeSite> getLCSClusterNodeSites(
		long companyId, long installationId);

}