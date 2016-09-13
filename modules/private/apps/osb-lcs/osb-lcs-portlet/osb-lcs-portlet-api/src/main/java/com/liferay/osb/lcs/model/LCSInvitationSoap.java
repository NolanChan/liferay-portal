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
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.lcs.service.http.LCSInvitationServiceSoap}.
 *
 * @author Igor Beslic
 * @see com.liferay.osb.lcs.service.http.LCSInvitationServiceSoap
 * @generated
 */
@ProviderType
public class LCSInvitationSoap implements Serializable {
	public static LCSInvitationSoap toSoapModel(LCSInvitation model) {
		LCSInvitationSoap soapModel = new LCSInvitationSoap();

		soapModel.setLcsInvitationId(model.getLcsInvitationId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setLcsProjectId(model.getLcsProjectId());
		soapModel.setEmailAddress(model.getEmailAddress());
		soapModel.setLcsClusterEntryId(model.getLcsClusterEntryId());
		soapModel.setRole(model.getRole());

		return soapModel;
	}

	public static LCSInvitationSoap[] toSoapModels(LCSInvitation[] models) {
		LCSInvitationSoap[] soapModels = new LCSInvitationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LCSInvitationSoap[][] toSoapModels(LCSInvitation[][] models) {
		LCSInvitationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LCSInvitationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LCSInvitationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LCSInvitationSoap[] toSoapModels(List<LCSInvitation> models) {
		List<LCSInvitationSoap> soapModels = new ArrayList<LCSInvitationSoap>(models.size());

		for (LCSInvitation model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LCSInvitationSoap[soapModels.size()]);
	}

	public LCSInvitationSoap() {
	}

	public long getPrimaryKey() {
		return _lcsInvitationId;
	}

	public void setPrimaryKey(long pk) {
		setLcsInvitationId(pk);
	}

	public long getLcsInvitationId() {
		return _lcsInvitationId;
	}

	public void setLcsInvitationId(long lcsInvitationId) {
		_lcsInvitationId = lcsInvitationId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public long getLcsProjectId() {
		return _lcsProjectId;
	}

	public void setLcsProjectId(long lcsProjectId) {
		_lcsProjectId = lcsProjectId;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
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

	private long _lcsInvitationId;
	private long _userId;
	private Date _createDate;
	private long _lcsProjectId;
	private String _emailAddress;
	private long _lcsClusterEntryId;
	private int _role;
}