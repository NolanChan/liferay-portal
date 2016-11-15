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
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Update;

import com.liferay.osb.lcs.nosql.model.LCSMetadataDetails;
import com.liferay.osb.lcs.nosql.model.impl.LCSMetadataDetailsImpl;
import com.liferay.osb.lcs.nosql.service.persistence.base.BasePersistenceImpl;

/**
 * @author Ivica Cardic
 */
public class LCSMetadataDetailsPersistenceImpl
	extends BasePersistenceImpl<LCSMetadataDetails>
	implements LCSMetadataDetailsPersistence {

	@Override
	public LCSMetadataDetails create() {
		LCSMetadataDetails lcsMetadataDetails = new LCSMetadataDetailsImpl();

		lcsMetadataDetails.setNew(true);

		return lcsMetadataDetails;
	}

	@Override
	public LCSMetadataDetails fetchByBN_GT_PE(
		int buildNumber, String gitTag, String portalEdition) {

		return fetchByArray(
			"buildNumber", buildNumber, "gitTag", gitTag, "portalEdition",
			portalEdition);
	}

	@Override
	public LCSMetadataDetails update(LCSMetadataDetails lcsMetadataDetails) {
		Statement statement = null;

		if (lcsMetadataDetails.isNew()) {
			Insert insert = QueryBuilder.insertInto(_TABLE_NAME);

			insert.value("buildNumber", lcsMetadataDetails.getBuildNumber());
			insert.value("gitTag", lcsMetadataDetails.getGitTag());
			insert.value(
				"portalEdition", lcsMetadataDetails.getPortalEdition());
			insert.value(
				"portalProperties", lcsMetadataDetails.getPortalProperties());
			insert.value("properties", lcsMetadataDetails.getProperties());

			statement = insert;
		}
		else {
			Update update = QueryBuilder.update(_TABLE_NAME);

			Update.Assignments assignments = update.with();

			assignments.and(
				QueryBuilder.set(
					"portalProperties",
					lcsMetadataDetails.getPortalProperties()));
			assignments.and(
				QueryBuilder.set(
					"properties", lcsMetadataDetails.getProperties()));

			Update.Where where = update.where();

			where.and(
				QueryBuilder.eq(
					"buildNumber", lcsMetadataDetails.getBuildNumber()));
			where.and(
				QueryBuilder.eq("gitTag", lcsMetadataDetails.getGitTag()));
			where.and(
				QueryBuilder.eq(
					"portalEdition", lcsMetadataDetails.getPortalEdition()));

			statement = update;
		}

		session.execute(statement);

		lcsMetadataDetails.setNew(false);

		return lcsMetadataDetails;
	}

	@Override
	protected String[] getColumnNames() {
		return _COLUMN_NAMES;
	}

	@Override
	protected LCSMetadataDetails getEntity(Row row) {
		LCSMetadataDetails lcsMetadataDetails = new LCSMetadataDetailsImpl();

		lcsMetadataDetails.setBuildNumber(row.getInt("buildNumber"));
		lcsMetadataDetails.setGitTag(row.getString("gitTag"));
		lcsMetadataDetails.setPortalEdition(row.getString("portalEdition"));
		lcsMetadataDetails.setPortalProperties(
			row.getMap("portalProperties", String.class, String.class));
		lcsMetadataDetails.setProperties(
			row.getMap("properties", String.class, String.class));

		return lcsMetadataDetails;
	}

	@Override
	protected String getTableName() {
		return _TABLE_NAME;
	}

	private static final String[] _COLUMN_NAMES = {
		"buildNumber", "gitTag", "portalEdition", "portalProperties",
		"properties"
	};

	private static final String _TABLE_NAME = "LCSMetadataDetails";

}