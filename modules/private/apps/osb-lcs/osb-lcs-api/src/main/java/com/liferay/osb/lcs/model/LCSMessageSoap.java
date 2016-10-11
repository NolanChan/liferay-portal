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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.lcs.service.http.LCSMessageServiceSoap}.
 *
 * @author Igor Beslic
 * @see com.liferay.osb.lcs.service.http.LCSMessageServiceSoap
 * @generated
 */
@ProviderType
public class LCSMessageSoap implements Serializable {
	public static LCSMessageSoap toSoapModel(LCSMessage model) {
		LCSMessageSoap soapModel = new LCSMessageSoap();

		soapModel.setLcsMessageId(model.getLcsMessageId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setSourceMessageId(model.getSourceMessageId());
		soapModel.setSourceSystemName(model.getSourceSystemName());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setContent(model.getContent());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setGlobal(model.getGlobal());
		soapModel.setSeverityLevel(model.getSeverityLevel());
		soapModel.setType(model.getType());

		return soapModel;
	}

	public static LCSMessageSoap[] toSoapModels(LCSMessage[] models) {
		LCSMessageSoap[] soapModels = new LCSMessageSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LCSMessageSoap[][] toSoapModels(LCSMessage[][] models) {
		LCSMessageSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LCSMessageSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LCSMessageSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LCSMessageSoap[] toSoapModels(List<LCSMessage> models) {
		List<LCSMessageSoap> soapModels = new ArrayList<LCSMessageSoap>(models.size());

		for (LCSMessage model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LCSMessageSoap[soapModels.size()]);
	}

	public LCSMessageSoap() {
	}

	public long getPrimaryKey() {
		return _lcsMessageId;
	}

	public void setPrimaryKey(long pk) {
		setLcsMessageId(pk);
	}

	public long getLcsMessageId() {
		return _lcsMessageId;
	}

	public void setLcsMessageId(long lcsMessageId) {
		_lcsMessageId = lcsMessageId;
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

	public long getSourceMessageId() {
		return _sourceMessageId;
	}

	public void setSourceMessageId(long sourceMessageId) {
		_sourceMessageId = sourceMessageId;
	}

	public String getSourceSystemName() {
		return _sourceSystemName;
	}

	public void setSourceSystemName(String sourceSystemName) {
		_sourceSystemName = sourceSystemName;
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

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public boolean getGlobal() {
		return _global;
	}

	public boolean isGlobal() {
		return _global;
	}

	public void setGlobal(boolean global) {
		_global = global;
	}

	public int getSeverityLevel() {
		return _severityLevel;
	}

	public void setSeverityLevel(int severityLevel) {
		_severityLevel = severityLevel;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	private long _lcsMessageId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _sourceMessageId;
	private String _sourceSystemName;
	private long _classNameId;
	private long _classPK;
	private String _content;
	private Date _endDate;
	private boolean _global;
	private int _severityLevel;
	private int _type;
}