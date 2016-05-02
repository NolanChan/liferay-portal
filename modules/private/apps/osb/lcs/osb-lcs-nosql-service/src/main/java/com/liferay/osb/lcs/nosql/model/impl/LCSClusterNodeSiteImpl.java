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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeSite;

import java.util.Date;

/**
 * @author Riccardo Ferrari
 */
public class LCSClusterNodeSiteImpl implements LCSClusterNodeSite {

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public String getFriendlyURL() {
		return _friendlyURL;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public String getGroupUUID() {
		return _groupUUID;
	}

	@Override
	public long getInstallationId() {
		return _installationId;
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
	public String getName() {
		return _name;
	}

	@Override
	public int getType() {
		return _type;
	}

	@Override
	public String getUUID() {
		return _uuid;
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
	public void setFriendlyURL(String friendlyURL) {
		_friendlyURL = friendlyURL;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	@Override
	public void setGroupUUID(String groupUUID) {
		_groupUUID = groupUUID;
	}

	@Override
	public void setInstallationId(long installationId) {
		_installationId = installationId;
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
	public void setName(String name) {
		_name = name;
	}

	@Override
	public void setNew(boolean n) {
		_new = n;
	}

	@Override
	public void setType(int type) {
		_type = type;
	}

	@Override
	public void setUUID(String uuid) {
		_uuid = uuid;
	}

	private long _companyId;
	private String _friendlyURL;
	private long _groupId;
	private String _groupUUID;
	private long _installationId;
	private String _key;
	private Date _modifiedDate;
	private String _name;
	private boolean _new;
	private int _type;
	private String _uuid;

}