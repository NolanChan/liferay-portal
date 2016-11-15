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

import com.datastax.driver.core.Row;

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeSite;
import com.liferay.osb.lcs.nosql.model.impl.LCSClusterNodeSiteImpl;
import com.liferay.osb.lcs.nosql.service.persistence.base.BasePersistenceImpl;
import com.liferay.osb.lcs.nosql.util.DMLStatementExecutor;

import java.util.List;

/**
 * @author Riccardo Ferrari
 */
public class LCSClusterNodeSitePersistenceImpl
	extends BasePersistenceImpl<LCSClusterNodeSite>
	implements LCSClusterNodeSitePersistence {

	@Override
	public LCSClusterNodeSite create() {
		LCSClusterNodeSite lcsClusterNodeSite = new LCSClusterNodeSiteImpl();

		lcsClusterNodeSite.setNew(true);

		return lcsClusterNodeSite;
	}

	@Override
	public LCSClusterNodeSite fetchByC_G_I(
		long companyId, long groupId, long installationId) {

		return fetchByArray(
			"companyId", companyId, "groupId", groupId, "installationId",
			installationId);
	}

	@Override
	public List<LCSClusterNodeSite> findByC_I(
		long companyId, long installationId) {

		return findByArray(
			"companyId", companyId, "installationId", installationId);
	}

	@Override
	public List<LCSClusterNodeSite> findByInstallationId(long installationId) {
		return findByArray("installationId", installationId);
	}

	@Override
	public LCSClusterNodeSite update(LCSClusterNodeSite lcsClusterNodeSite) {
		DMLStatementExecutor dmlStatementExecutor = new DMLStatementExecutor(
			lcsClusterNodeSite, _TABLE_NAME);

		dmlStatementExecutor.addColumns(
			"friendlyURL", lcsClusterNodeSite.getFriendlyURL(), "groupUUID",
			lcsClusterNodeSite.getGroupUUID(), "key_",
			lcsClusterNodeSite.getKey(), "modifiedDate",
			lcsClusterNodeSite.getModifiedDate(), "name",
			lcsClusterNodeSite.getName(), "type_",
			lcsClusterNodeSite.getType());

		dmlStatementExecutor.addPrimaryKeys(
			"companyId", lcsClusterNodeSite.getCompanyId(), "groupId",
			lcsClusterNodeSite.getGroupId(), "installationId",
			lcsClusterNodeSite.getInstallationId());

		dmlStatementExecutor.execute(session);

		return lcsClusterNodeSite;
	}

	@Override
	protected String[] getColumnNames() {
		return _COLUMN_NAMES;
	}

	@Override
	protected LCSClusterNodeSite getEntity(Row row) {
		LCSClusterNodeSite lcsClusterNodeSite = new LCSClusterNodeSiteImpl();

		lcsClusterNodeSite.setCompanyId(row.getLong("companyId"));
		lcsClusterNodeSite.setFriendlyURL(row.getString("friendlyURL"));
		lcsClusterNodeSite.setGroupId(row.getLong("groupId"));
		lcsClusterNodeSite.setGroupUUID(row.getString("groupUUID"));
		lcsClusterNodeSite.setInstallationId(row.getLong("installationId"));
		lcsClusterNodeSite.setKey(row.getString("key_"));
		lcsClusterNodeSite.setModifiedDate(row.getTimestamp("modifiedDate"));
		lcsClusterNodeSite.setName(row.getString("name"));
		lcsClusterNodeSite.setType(row.getInt("type_"));

		return lcsClusterNodeSite;
	}

	@Override
	protected String getTableName() {
		return _TABLE_NAME;
	}

	private static final String[] _COLUMN_NAMES = {
		"companyId", "friendlyURL", "groupId", "groupUUID", "installationId",
		"key_", "modifiedDate", "name", "type_"
	};

	private static final String _TABLE_NAME = "LCSClusterNodeSite";

}