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

package com.liferay.osb.ldn.documentation.project.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.ldn.documentation.project.service.http.DocumentationProjectServiceSoap}.
 *
 * @author Ryan Park
 * @see com.liferay.osb.ldn.documentation.project.service.http.DocumentationProjectServiceSoap
 * @generated
 */
@ProviderType
public class DocumentationProjectSoap implements Serializable {
	public static DocumentationProjectSoap toSoapModel(
		DocumentationProject model) {
		DocumentationProjectSoap soapModel = new DocumentationProjectSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setDocumentationProjectId(model.getDocumentationProjectId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setIconFileName(model.getIconFileName());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static DocumentationProjectSoap[] toSoapModels(
		DocumentationProject[] models) {
		DocumentationProjectSoap[] soapModels = new DocumentationProjectSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DocumentationProjectSoap[][] toSoapModels(
		DocumentationProject[][] models) {
		DocumentationProjectSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DocumentationProjectSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DocumentationProjectSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DocumentationProjectSoap[] toSoapModels(
		List<DocumentationProject> models) {
		List<DocumentationProjectSoap> soapModels = new ArrayList<DocumentationProjectSoap>(models.size());

		for (DocumentationProject model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DocumentationProjectSoap[soapModels.size()]);
	}

	public DocumentationProjectSoap() {
	}

	public long getPrimaryKey() {
		return _documentationProjectId;
	}

	public void setPrimaryKey(long pk) {
		setDocumentationProjectId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getDocumentationProjectId() {
		return _documentationProjectId;
	}

	public void setDocumentationProjectId(long documentationProjectId) {
		_documentationProjectId = documentationProjectId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getIconFileName() {
		return _iconFileName;
	}

	public void setIconFileName(String iconFileName) {
		_iconFileName = iconFileName;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private String _uuid;
	private long _documentationProjectId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _description;
	private String _iconFileName;
	private int _status;
}