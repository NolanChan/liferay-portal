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
import com.datastax.driver.core.utils.UUIDs;

import com.liferay.osb.lcs.nosql.model.LCSClusterNodePatches;
import com.liferay.osb.lcs.nosql.model.impl.LCSClusterNodePatchesImpl;
import com.liferay.osb.lcs.nosql.service.persistence.base.BasePersistenceImpl;
import com.liferay.osb.lcs.nosql.util.DMLStatementExecutor;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class LCSClusterNodePatchesPersistenceImpl
	extends BasePersistenceImpl<LCSClusterNodePatches>
	implements LCSClusterNodePatchesPersistence {

	@Override
	public LCSClusterNodePatches create() {
		LCSClusterNodePatches lcsClusterNodePatches =
			new LCSClusterNodePatchesImpl();

		lcsClusterNodePatches.setNew(true);

		return lcsClusterNodePatches;
	}

	@Override
	public LCSClusterNodePatches fetchByKey(String key) {
		return fetchByArray("key_", key);
	}

	@Override
	public LCSClusterNodePatches update(
		LCSClusterNodePatches lcsClusterNodePatches) {

		DMLStatementExecutor dmlStatementExecutor = new DMLStatementExecutor(
			lcsClusterNodePatches, _TABLE_NAME);

		if (lcsClusterNodePatches.getError() != null) {
			dmlStatementExecutor.addColumns(
				"error", lcsClusterNodePatches.getError());
		}

		dmlStatementExecutor.addColumns(
			"fixedIssues", lcsClusterNodePatches.getFixedIssues(), "hashCode",
			lcsClusterNodePatches.getHashCode(), "installablePatchIds",
			lcsClusterNodePatches.getInstallablePatchIds(), "modifiedDate",
			lcsClusterNodePatches.getModifiedDate(), "patchIdsStatuses",
			lcsClusterNodePatches.getPatchIdsStatuses());

		dmlStatementExecutor.addPrimaryKeyColumns("uuid", UUIDs.timeBased());

		dmlStatementExecutor.addPrimaryKeys(
			"key_", lcsClusterNodePatches.getKey());

		dmlStatementExecutor.execute(session);

		return lcsClusterNodePatches;
	}

	@Override
	public void updateErrorColumn(Object key, Object value) {
		updateColumn("error", value, "key_", key);
	}

	@Override
	public void updateModifiedDateColumn(Object key, Object value) {
		updateColumn("modifiedDate", value, "key_", key);
	}

	@Override
	public void updatePatchIdsStatusesColumn(
		String key, Object mapKey, Object mapValue) {

		updateMapColumn("patchIdsStatuses", mapKey, mapValue, "key_", key);
	}

	@Override
	protected String[] getColumnNames() {
		return _COLUMN_NAMES;
	}

	@Override
	protected LCSClusterNodePatches getEntity(Row row) {
		LCSClusterNodePatches lcsClusterNodePatches =
			new LCSClusterNodePatchesImpl();

		lcsClusterNodePatches.setError(row.getString("error"));
		lcsClusterNodePatches.setFixedIssues(
			row.getList("fixedIssues", String.class));
		lcsClusterNodePatches.setHashCode(row.getString("hashCode"));
		lcsClusterNodePatches.setInstallablePatchIds(
			row.getList("installablePatchIds", String.class));
		lcsClusterNodePatches.setKey(row.getString("key_"));
		lcsClusterNodePatches.setModifiedDate(row.getTimestamp("modifiedDate"));
		lcsClusterNodePatches.setPatchIdsStatuses(
			row.getMap("patchIdsStatuses", String.class, Integer.class));
		lcsClusterNodePatches.setUUID(String.valueOf(row.getUUID("uuid")));

		return lcsClusterNodePatches;
	}

	@Override
	protected String getTableName() {
		return _TABLE_NAME;
	}

	private static final String[] _COLUMN_NAMES = {
		"error", "fixedIssues", "hashCode", "installablePatchIds", "key_",
		"modifiedDate", "patchIdsStatuses", "uuid"
	};

	private static final String _TABLE_NAME = "LCSClusterNodePatches";

}