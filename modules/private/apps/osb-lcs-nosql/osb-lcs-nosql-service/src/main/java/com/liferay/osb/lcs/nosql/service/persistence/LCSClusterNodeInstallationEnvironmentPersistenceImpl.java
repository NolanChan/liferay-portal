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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeInstallationEnvironment;
import com.liferay.osb.lcs.nosql.model.impl.LCSClusterNodeInstallationEnvironmentImpl;
import com.liferay.osb.lcs.nosql.service.persistence.base.BasePersistenceImpl;
import com.liferay.osb.lcs.nosql.util.DMLStatementExecutor;

/**
 * @author Igor Beslic
 */
public class LCSClusterNodeInstallationEnvironmentPersistenceImpl
	extends BasePersistenceImpl<LCSClusterNodeInstallationEnvironment>
	implements LCSClusterNodeInstallationEnvironmentPersistence {

	@Override
	public LCSClusterNodeInstallationEnvironment create() {
		LCSClusterNodeInstallationEnvironment
			lcsClusterNodeInstallationEnvironment =
				new LCSClusterNodeInstallationEnvironmentImpl();

		lcsClusterNodeInstallationEnvironment.setNew(true);

		return lcsClusterNodeInstallationEnvironment;
	}

	@Override
	public LCSClusterNodeInstallationEnvironment fetchByKey(String key) {
		return fetchByArray("key_", key);
	}

	@Override
	public LCSClusterNodeInstallationEnvironment update(
		LCSClusterNodeInstallationEnvironment
			lcsClusterNodeInstallationEnvironment) {

		DMLStatementExecutor dmlStatementExecutor = new DMLStatementExecutor(
			lcsClusterNodeInstallationEnvironment, _TABLE_NAME);

		dmlStatementExecutor.addColumns(
			"hardwareMetadata",
			lcsClusterNodeInstallationEnvironment.getHardwareMetadata(),
			"modifiedDate",
			lcsClusterNodeInstallationEnvironment.getModifiedDate(),
			"softwareMetadata",
			lcsClusterNodeInstallationEnvironment.getSoftwareMetadata());

		dmlStatementExecutor.addPrimaryKeys(
			"key_", lcsClusterNodeInstallationEnvironment.getKey());

		dmlStatementExecutor.execute(session);

		return lcsClusterNodeInstallationEnvironment;
	}

	@Override
	protected String[] getColumnNames() {
		return _COLUMN_NAMES;
	}

	@Override
	protected LCSClusterNodeInstallationEnvironment getEntity(Row row) {
		LCSClusterNodeInstallationEnvironment
			lcsClusterNodeInstallationEnvironment =
				new LCSClusterNodeInstallationEnvironmentImpl();

		lcsClusterNodeInstallationEnvironment.setHardwareMetadata(
			row.getMap("hardwareMetadata", String.class, String.class));
		lcsClusterNodeInstallationEnvironment.setKey(row.getString("key_"));
		lcsClusterNodeInstallationEnvironment.setModifiedDate(
			row.getTimestamp("modifiedDate"));
		lcsClusterNodeInstallationEnvironment.setSoftwareMetadata(
			row.getMap("softwareMetadata", String.class, String.class));

		return lcsClusterNodeInstallationEnvironment;
	}

	@Override
	protected String getTableName() {
		return _TABLE_NAME;
	}

	private static final String[] _COLUMN_NAMES =
		{"hardwareMetadata", "key_", "modifiedDate", "softwareMetadata"};

	private static final String _TABLE_NAME =
		"LCSClusterNodeInstallationEnvironment";

}