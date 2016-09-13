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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.lcs.service.http.LCSClusterEntryTokenServiceSoap}.
 *
 * @author Igor Beslic
 * @see com.liferay.osb.lcs.service.http.LCSClusterEntryTokenServiceSoap
 * @generated
 */
@ProviderType
public class LCSClusterEntryTokenSoap implements Serializable {
	public static LCSClusterEntryTokenSoap toSoapModel(
		LCSClusterEntryToken model) {
		LCSClusterEntryTokenSoap soapModel = new LCSClusterEntryTokenSoap();

		soapModel.setLcsClusterEntryTokenId(model.getLcsClusterEntryTokenId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setLcsClusterEntryId(model.getLcsClusterEntryId());
		soapModel.setContent(model.getContent());

		return soapModel;
	}

	public static LCSClusterEntryTokenSoap[] toSoapModels(
		LCSClusterEntryToken[] models) {
		LCSClusterEntryTokenSoap[] soapModels = new LCSClusterEntryTokenSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LCSClusterEntryTokenSoap[][] toSoapModels(
		LCSClusterEntryToken[][] models) {
		LCSClusterEntryTokenSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LCSClusterEntryTokenSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LCSClusterEntryTokenSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LCSClusterEntryTokenSoap[] toSoapModels(
		List<LCSClusterEntryToken> models) {
		List<LCSClusterEntryTokenSoap> soapModels = new ArrayList<LCSClusterEntryTokenSoap>(models.size());

		for (LCSClusterEntryToken model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LCSClusterEntryTokenSoap[soapModels.size()]);
	}

	public LCSClusterEntryTokenSoap() {
	}

	public long getPrimaryKey() {
		return _lcsClusterEntryTokenId;
	}

	public void setPrimaryKey(long pk) {
		setLcsClusterEntryTokenId(pk);
	}

	public long getLcsClusterEntryTokenId() {
		return _lcsClusterEntryTokenId;
	}

	public void setLcsClusterEntryTokenId(long lcsClusterEntryTokenId) {
		_lcsClusterEntryTokenId = lcsClusterEntryTokenId;
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

	public long getLcsClusterEntryId() {
		return _lcsClusterEntryId;
	}

	public void setLcsClusterEntryId(long lcsClusterEntryId) {
		_lcsClusterEntryId = lcsClusterEntryId;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	private long _lcsClusterEntryTokenId;
	private long _userId;
	private Date _createDate;
	private long _lcsClusterEntryId;
	private String _content;
}