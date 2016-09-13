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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.lcs.service.http.UserLCSMessageServiceSoap}.
 *
 * @author Igor Beslic
 * @see com.liferay.osb.lcs.service.http.UserLCSMessageServiceSoap
 * @generated
 */
@ProviderType
public class UserLCSMessageSoap implements Serializable {
	public static UserLCSMessageSoap toSoapModel(UserLCSMessage model) {
		UserLCSMessageSoap soapModel = new UserLCSMessageSoap();

		soapModel.setUserLcsMessageId(model.getUserLcsMessageId());
		soapModel.setUserId(model.getUserId());
		soapModel.setLcsMessageId(model.getLcsMessageId());
		soapModel.setHidden(model.getHidden());
		soapModel.setRead(model.getRead());

		return soapModel;
	}

	public static UserLCSMessageSoap[] toSoapModels(UserLCSMessage[] models) {
		UserLCSMessageSoap[] soapModels = new UserLCSMessageSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserLCSMessageSoap[][] toSoapModels(UserLCSMessage[][] models) {
		UserLCSMessageSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UserLCSMessageSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserLCSMessageSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserLCSMessageSoap[] toSoapModels(List<UserLCSMessage> models) {
		List<UserLCSMessageSoap> soapModels = new ArrayList<UserLCSMessageSoap>(models.size());

		for (UserLCSMessage model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserLCSMessageSoap[soapModels.size()]);
	}

	public UserLCSMessageSoap() {
	}

	public long getPrimaryKey() {
		return _userLcsMessageId;
	}

	public void setPrimaryKey(long pk) {
		setUserLcsMessageId(pk);
	}

	public long getUserLcsMessageId() {
		return _userLcsMessageId;
	}

	public void setUserLcsMessageId(long userLcsMessageId) {
		_userLcsMessageId = userLcsMessageId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public long getLcsMessageId() {
		return _lcsMessageId;
	}

	public void setLcsMessageId(long lcsMessageId) {
		_lcsMessageId = lcsMessageId;
	}

	public boolean getHidden() {
		return _hidden;
	}

	public boolean isHidden() {
		return _hidden;
	}

	public void setHidden(boolean hidden) {
		_hidden = hidden;
	}

	public boolean getRead() {
		return _read;
	}

	public boolean isRead() {
		return _read;
	}

	public void setRead(boolean read) {
		_read = read;
	}

	private long _userLcsMessageId;
	private long _userId;
	private long _lcsMessageId;
	private boolean _hidden;
	private boolean _read;
}