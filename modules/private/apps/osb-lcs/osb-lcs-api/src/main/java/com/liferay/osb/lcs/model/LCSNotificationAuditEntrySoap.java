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
 * This class is used by SOAP remote services.
 *
 * @author Igor Beslic
 * @generated
 */
@ProviderType
public class LCSNotificationAuditEntrySoap implements Serializable {
	public static LCSNotificationAuditEntrySoap toSoapModel(
		LCSNotificationAuditEntry model) {
		LCSNotificationAuditEntrySoap soapModel = new LCSNotificationAuditEntrySoap();

		soapModel.setLcsNotificationAuditEntryId(model.getLcsNotificationAuditEntryId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setLcsClusterNodeId(model.getLcsClusterNodeId());
		soapModel.setType(model.getType());

		return soapModel;
	}

	public static LCSNotificationAuditEntrySoap[] toSoapModels(
		LCSNotificationAuditEntry[] models) {
		LCSNotificationAuditEntrySoap[] soapModels = new LCSNotificationAuditEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LCSNotificationAuditEntrySoap[][] toSoapModels(
		LCSNotificationAuditEntry[][] models) {
		LCSNotificationAuditEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LCSNotificationAuditEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new LCSNotificationAuditEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LCSNotificationAuditEntrySoap[] toSoapModels(
		List<LCSNotificationAuditEntry> models) {
		List<LCSNotificationAuditEntrySoap> soapModels = new ArrayList<LCSNotificationAuditEntrySoap>(models.size());

		for (LCSNotificationAuditEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LCSNotificationAuditEntrySoap[soapModels.size()]);
	}

	public LCSNotificationAuditEntrySoap() {
	}

	public long getPrimaryKey() {
		return _lcsNotificationAuditEntryId;
	}

	public void setPrimaryKey(long pk) {
		setLcsNotificationAuditEntryId(pk);
	}

	public long getLcsNotificationAuditEntryId() {
		return _lcsNotificationAuditEntryId;
	}

	public void setLcsNotificationAuditEntryId(long lcsNotificationAuditEntryId) {
		_lcsNotificationAuditEntryId = lcsNotificationAuditEntryId;
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

	public long getLcsClusterNodeId() {
		return _lcsClusterNodeId;
	}

	public void setLcsClusterNodeId(long lcsClusterNodeId) {
		_lcsClusterNodeId = lcsClusterNodeId;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	private long _lcsNotificationAuditEntryId;
	private long _userId;
	private Date _createDate;
	private long _lcsClusterNodeId;
	private int _type;
}