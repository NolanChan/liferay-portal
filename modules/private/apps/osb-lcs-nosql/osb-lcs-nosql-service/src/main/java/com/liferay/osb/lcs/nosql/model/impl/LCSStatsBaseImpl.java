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

package com.liferay.osb.lcs.nosql.model.impl;

import java.util.Date;

import com.liferay.osb.lcs.nosql.model.LCSStats;

/**
 * @author Riccardo Ferrari
 */
public abstract class LCSStatsBaseImpl implements LCSStats {

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public String getKey() {
		return _key;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public String getPartitionKey() {
		return _partitionKey;
	}

	@Override
	public boolean isNew() {
		return _new;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@Override
	public void setKey(String key) {
		_key = key;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	@Override
	public void setNew(boolean n) {
		_new = n;
	}

	@Override
	public void setPartitionKey(String partitionKey) {
		_partitionKey = partitionKey;
	}

	private long _companyId;
	private long _groupId;
	private String _key;
	private Date _modifiedDate;
	private boolean _new;
	private String _partitionKey;

}
