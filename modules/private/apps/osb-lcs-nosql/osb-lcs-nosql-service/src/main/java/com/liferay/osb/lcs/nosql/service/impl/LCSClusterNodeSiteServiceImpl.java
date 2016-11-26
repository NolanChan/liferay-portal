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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeSite;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeSiteService;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodeSitePersistence;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Ferrari
 */
@Component(immediate = true, service = LCSClusterNodeSiteService.class)
public class LCSClusterNodeSiteServiceImpl
	implements LCSClusterNodeSiteService {

	@Override
	public LCSClusterNodeSite addLCSClusterNodeSite(
		long companyId, String friendlyURL, long groupId, String groupUUID,
		long installationId, String key, Date modifiedDate, String name,
		int type) {

		LCSClusterNodeSite lcsClusterNodeSite =
			_lcsClusterNodeSitePersistence.create();

		lcsClusterNodeSite.setCompanyId(companyId);
		lcsClusterNodeSite.setFriendlyURL(friendlyURL);
		lcsClusterNodeSite.setGroupId(groupId);
		lcsClusterNodeSite.setGroupUUID(groupUUID);
		lcsClusterNodeSite.setInstallationId(installationId);
		lcsClusterNodeSite.setKey(key);
		lcsClusterNodeSite.setModifiedDate(modifiedDate);
		lcsClusterNodeSite.setName(name);
		lcsClusterNodeSite.setType(type);

		return _lcsClusterNodeSitePersistence.update(lcsClusterNodeSite);
	}

	@Override
	public LCSClusterNodeSite getLCSClusterNodeSite(
		long companyId, long groupId, long installationId) {

		return _lcsClusterNodeSitePersistence.fetchByC_G_I(
			companyId, groupId, installationId);
	}

	@Override
	public List<? extends LCSClusterNodeSite> getLCSClusterNodeSites(
		long installationId) {

		return _lcsClusterNodeSitePersistence.findByInstallationId(
			installationId);
	}

	@Override
	public List<? extends LCSClusterNodeSite> getLCSClusterNodeSites(
		long companyId, long installationId) {

		return _lcsClusterNodeSitePersistence.findByC_I(
			companyId, installationId);
	}

	public void setLCSClusterNodeSitePersistence(
		LCSClusterNodeSitePersistence lcsClusterNodeSitePersistence) {

		_lcsClusterNodeSitePersistence = lcsClusterNodeSitePersistence;
	}

	private LCSClusterNodeSitePersistence _lcsClusterNodeSitePersistence;

}