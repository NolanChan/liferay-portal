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

package com.liferay.osb.lcs.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.lcs.service.http.LCSRoleServiceSoap}.
 *
 * @author Igor Beslic
 * @see com.liferay.osb.lcs.service.http.LCSRoleServiceSoap
 * @generated
 */
@ProviderType
public class LCSRoleSoap implements Serializable {
	public static LCSRoleSoap toSoapModel(LCSRole model) {
		LCSRoleSoap soapModel = new LCSRoleSoap();

		soapModel.setLcsRoleId(model.getLcsRoleId());
		soapModel.setUserId(model.getUserId());
		soapModel.setLcsProjectId(model.getLcsProjectId());
		soapModel.setLcsClusterEntryId(model.getLcsClusterEntryId());
		soapModel.setRole(model.getRole());

		return soapModel;
	}

	public static LCSRoleSoap[] toSoapModels(LCSRole[] models) {
		LCSRoleSoap[] soapModels = new LCSRoleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LCSRoleSoap[][] toSoapModels(LCSRole[][] models) {
		LCSRoleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LCSRoleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LCSRoleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LCSRoleSoap[] toSoapModels(List<LCSRole> models) {
		List<LCSRoleSoap> soapModels = new ArrayList<LCSRoleSoap>(models.size());

		for (LCSRole model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LCSRoleSoap[soapModels.size()]);
	}

	public LCSRoleSoap() {
	}

	public long getPrimaryKey() {
		return _lcsRoleId;
	}

	public void setPrimaryKey(long pk) {
		setLcsRoleId(pk);
	}

	public long getLcsRoleId() {
		return _lcsRoleId;
	}

	public void setLcsRoleId(long lcsRoleId) {
		_lcsRoleId = lcsRoleId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public long getLcsProjectId() {
		return _lcsProjectId;
	}

	public void setLcsProjectId(long lcsProjectId) {
		_lcsProjectId = lcsProjectId;
	}

	public long getLcsClusterEntryId() {
		return _lcsClusterEntryId;
	}

	public void setLcsClusterEntryId(long lcsClusterEntryId) {
		_lcsClusterEntryId = lcsClusterEntryId;
	}

	public int getRole() {
		return _role;
	}

	public void setRole(int role) {
		_role = role;
	}

	private long _lcsRoleId;
	private long _userId;
	private long _lcsProjectId;
	private long _lcsClusterEntryId;
	private int _role;
}