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

package com.liferay.portal.verify.model.audited;

/**
 * @author Miguel Pastor
 */
public class OrganizationVerifiableAuditedModel
	implements VerifiableAuditedModel {

	@Override
	public String getJoinByTableName() {
		return null;
	}

	@Override
	public String getTableName() {
		return "Organization_";
	}

	@Override
	public String getPrimaryKeyColumnName() {
		return "organizationId";
	}

	@Override
	public String getRelatedModelName() {
		return null;
	}

	@Override
	public String getRelatedPKColumnName() {
		return null;
	}

	@Override
	public boolean isUpdateDates() {
		return true;
	}

}