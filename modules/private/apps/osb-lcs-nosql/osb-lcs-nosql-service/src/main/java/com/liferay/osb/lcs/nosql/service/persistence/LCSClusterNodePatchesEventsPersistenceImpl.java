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

import com.datastax.driver.core.utils.UUIDs;

import com.liferay.osb.lcs.nosql.model.LCSClusterNodePatches;
import com.liferay.osb.lcs.nosql.util.DMLStatementExecutor;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class LCSClusterNodePatchesEventsPersistenceImpl
	extends LCSClusterNodePatchesPersistenceImpl
	implements LCSClusterNodePatchesEventsPersistence {

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
			lcsClusterNodePatches.getHashCode(), "patchIdsStatuses",
			lcsClusterNodePatches.getPatchIdsStatuses());

		dmlStatementExecutor.addPrimaryKeyColumns("uuid", UUIDs.timeBased());

		dmlStatementExecutor.addPrimaryKeys(
			"key_", lcsClusterNodePatches.getKey(), "modifiedDate",
			lcsClusterNodePatches.getModifiedDate());

		dmlStatementExecutor.execute(session);

		return lcsClusterNodePatches;
	}

	@Override
	protected String getTableName() {
		return _TABLE_NAME;
	}

	private static final String _TABLE_NAME = "LCSClusterNodePatchesEvents";

}