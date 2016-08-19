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

package com.liferay.osb.lcs.nosql.service.persistence;

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeSite;

import java.util.List;

/**
 * @author Riccardo Ferrari
 */
public interface LCSClusterNodeSitePersistence {

	public LCSClusterNodeSite create();

	public LCSClusterNodeSite fetchByC_G_I(
		long companyId, long groupId, long installationId);

	public List<LCSClusterNodeSite> findByInstallationId(long installationId);

	public List<LCSClusterNodeSite> findByC_I(
		long companyId, long installationId);

	public LCSClusterNodeSite update(LCSClusterNodeSite lcsClusterNodeSite);

}