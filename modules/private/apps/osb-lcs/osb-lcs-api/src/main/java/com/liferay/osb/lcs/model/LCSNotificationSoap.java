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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.lcs.service.http.LCSNotificationServiceSoap}.
 *
 * @author Igor Beslic
 * @see com.liferay.osb.lcs.service.http.LCSNotificationServiceSoap
 * @generated
 */
@ProviderType
public class LCSNotificationSoap implements Serializable {
	public static LCSNotificationSoap toSoapModel(LCSNotification model) {
		LCSNotificationSoap soapModel = new LCSNotificationSoap();

		soapModel.setLcsNotificationId(model.getLcsNotificationId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setEnabled(model.getEnabled());
		soapModel.setType(model.getType());

		return soapModel;
	}

	public static LCSNotificationSoap[] toSoapModels(LCSNotification[] models) {
		LCSNotificationSoap[] soapModels = new LCSNotificationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LCSNotificationSoap[][] toSoapModels(
		LCSNotification[][] models) {
		LCSNotificationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LCSNotificationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LCSNotificationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LCSNotificationSoap[] toSoapModels(
		List<LCSNotification> models) {
		List<LCSNotificationSoap> soapModels = new ArrayList<LCSNotificationSoap>(models.size());

		for (LCSNotification model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LCSNotificationSoap[soapModels.size()]);
	}

	public LCSNotificationSoap() {
	}

	public long getPrimaryKey() {
		return _lcsNotificationId;
	}

	public void setPrimaryKey(long pk) {
		setLcsNotificationId(pk);
	}

	public long getLcsNotificationId() {
		return _lcsNotificationId;
	}

	public void setLcsNotificationId(long lcsNotificationId) {
		_lcsNotificationId = lcsNotificationId;
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

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public boolean getEnabled() {
		return _enabled;
	}

	public boolean isEnabled() {
		return _enabled;
	}

	public void setEnabled(boolean enabled) {
		_enabled = enabled;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	private long _lcsNotificationId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _classNameId;
	private long _classPK;
	private boolean _enabled;
	private int _type;
}