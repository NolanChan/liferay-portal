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

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;

import com.liferay.lcs.util.LCSClusterNodeStatus;
import com.liferay.osb.lcs.nosql.model.LCSClusterNodeDetails;
import com.liferay.osb.lcs.nosql.model.impl.LCSClusterNodeDetailsImpl;
import com.liferay.osb.lcs.nosql.service.persistence.base.BasePersistenceImpl;
import com.liferay.osb.lcs.nosql.util.DMLStatementExecutor;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class LCSClusterNodeDetailsPersistenceImpl
	extends BasePersistenceImpl<LCSClusterNodeDetails>
	implements LCSClusterNodeDetailsPersistence {

	@Override
	public LCSClusterNodeDetails create() {
		LCSClusterNodeDetails lcsClusterNodeDetails =
			new LCSClusterNodeDetailsImpl();

		lcsClusterNodeDetails.setNew(true);

		return lcsClusterNodeDetails;
	}

	@Override
	public LCSClusterNodeDetails fetchByKey(String key) {
		return fetchByArray("key_", key);
	}

	@Override
	public List<LCSClusterNodeDetails> findByActive() {
		Select select = getSelect();

		select.setFetchSize(100);

		ResultSet resultSet = session.execute(select);

		Iterator<Row> iterator = resultSet.iterator();

		List<LCSClusterNodeDetails> lcsClusterNodeDetailsList =
			new ArrayList<>();

		while (iterator.hasNext()) {
			Row row = iterator.next();

			LCSClusterNodeDetails lcsClusterNodeDetails = getEntity(row);

			if (LCSClusterNodeStatus.isInactive(
					lcsClusterNodeDetails.getStatus())) {

				continue;
			}

			lcsClusterNodeDetailsList.add(lcsClusterNodeDetails);
		}

		return lcsClusterNodeDetailsList;
	}

	@Override
	public List<LCSClusterNodeDetails> findByStatus(int status) {
		Select select = getSelect();

		Select.Where where = select.where(QueryBuilder.eq("status", status));

		ResultSet resultSet = session.execute(where);

		Iterator<Row> iterator = resultSet.iterator();

		List<LCSClusterNodeDetails> lcsClusterNodeDetailsList =
			new ArrayList<>();

		while (iterator.hasNext()) {
			Row row = iterator.next();

			LCSClusterNodeDetails lcsClusterNodeDetails = getEntity(row);

			lcsClusterNodeDetailsList.add(lcsClusterNodeDetails);
		}

		return lcsClusterNodeDetailsList;
	}

	@Override
	public LCSClusterNodeDetails update(
		LCSClusterNodeDetails lcsClusterNodeDetails) {

		DMLStatementExecutor dmlStatementExecutor = new DMLStatementExecutor(
			lcsClusterNodeDetails, _TABLE_NAME);

		dmlStatementExecutor.addColumns(
			"buildNumber", lcsClusterNodeDetails.getBuildNumber(),
			"companyIdsWebIds", lcsClusterNodeDetails.getCompanyIdsWebIds());

		if (lcsClusterNodeDetails.getConfigurationModifiedDate() != null) {
			dmlStatementExecutor.addColumns(
				"configurationModifiedDate",
				lcsClusterNodeDetails.getConfigurationModifiedDate());
		}

		dmlStatementExecutor.addColumns(
			"heartbeatInterval", lcsClusterNodeDetails.getHeartbeatInterval(),
			"lastHeartbeat", lcsClusterNodeDetails.getLastHeartbeat(),
			"modifiedDate", lcsClusterNodeDetails.getModifiedDate(),
			"patchingToolVersion",
			lcsClusterNodeDetails.getPatchingToolVersion());

		String portalEdition = lcsClusterNodeDetails.getPortalEdition();

		if (portalEdition == null) {
			portalEdition = "";
		}

		dmlStatementExecutor.addColumns("portalEdition", portalEdition);

		String protocolVersion = lcsClusterNodeDetails.getProtocolVersion();

		if (protocolVersion == null) {
			protocolVersion = "";
		}

		dmlStatementExecutor.addColumns("protocolVersion", protocolVersion);

		dmlStatementExecutor.addColumns(
			"status", lcsClusterNodeDetails.getStatus());

		dmlStatementExecutor.addPrimaryKeys(
			"key_", lcsClusterNodeDetails.getKey());

		dmlStatementExecutor.execute(session);

		return lcsClusterNodeDetails;
	}

	@Override
	public void updateConfigurationModifiedDate(
		Date configurationModifiedDate, String key, Date modifiedDate) {

		updateColumns(
			new String[] {"configurationModifiedDate", "modifiedDate"},
			new Object[] {configurationModifiedDate, modifiedDate}, "key_",
			key);
	}

	@Override
	public void updateLastHeartbeatColumn(
		String key, long lastHeartbeat, Date modifiedDate) {

		updateColumns(
			new String[] {"lastHeartbeat", "modifiedDate"},
			new Object[] {lastHeartbeat, modifiedDate}, "key_", key);
	}

	@Override
	public void updateStatusColumn(String key, Date modifiedDate, int status) {
		updateColumns(
			new String[] {"status", "modifiedDate"},
			new Object[] {status, modifiedDate}, "key_", key);
	}

	@Override
	protected String[] getColumnNames() {
		return _COLUMN_NAMES;
	}

	@Override
	protected LCSClusterNodeDetails getEntity(Row row) {
		LCSClusterNodeDetails lcsClusterNodeDetails =
			new LCSClusterNodeDetailsImpl();

		lcsClusterNodeDetails.setBuildNumber(row.getInt("buildNumber"));
		lcsClusterNodeDetails.setCompanyIdsWebIds(
			row.getMap("companyIdsWebIds", Integer.class, String.class));
		lcsClusterNodeDetails.setConfigurationModifiedDate(
			row.getTimestamp("configurationModifiedDate"));
		lcsClusterNodeDetails.setHeartbeatInterval(
			row.getLong("heartbeatInterval"));
		lcsClusterNodeDetails.setKey(row.getString("key_"));
		lcsClusterNodeDetails.setLastHeartbeat(row.getLong("lastHeartbeat"));
		lcsClusterNodeDetails.setModifiedDate(row.getTimestamp("modifiedDate"));
		lcsClusterNodeDetails.setPatchingToolVersion(
			row.getInt("patchingToolVersion"));
		lcsClusterNodeDetails.setPortalEdition(row.getString("portalEdition"));
		lcsClusterNodeDetails.setProtocolVersion(
			row.getString("protocolVersion"));
		lcsClusterNodeDetails.setStatus(row.getInt("status"));

		return lcsClusterNodeDetails;
	}

	@Override
	protected String getTableName() {
		return _TABLE_NAME;
	}

	private static final String[] _COLUMN_NAMES = {
		"buildNumber", "companyIdsWebIds", "configurationModifiedDate",
		"heartbeatInterval", "key_", "lastHeartbeat", "modifiedDate",
		"patchingToolVersion", "portalEdition", "protocolVersion", "status"
	};

	private static final String _TABLE_NAME = "LCSClusterNodeDetails";

}