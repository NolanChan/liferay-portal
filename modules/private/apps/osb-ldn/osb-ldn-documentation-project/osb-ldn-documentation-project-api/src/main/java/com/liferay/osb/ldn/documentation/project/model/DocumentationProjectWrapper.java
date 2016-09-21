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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link DocumentationProject}.
 * </p>
 *
 * @author Ryan Park
 * @see DocumentationProject
 * @generated
 */
@ProviderType
public class DocumentationProjectWrapper implements DocumentationProject,
	ModelWrapper<DocumentationProject> {
	public DocumentationProjectWrapper(
		DocumentationProject documentationProject) {
		_documentationProject = documentationProject;
	}

	@Override
	public Class<?> getModelClass() {
		return DocumentationProject.class;
	}

	@Override
	public String getModelClassName() {
		return DocumentationProject.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("documentationProjectId", getDocumentationProjectId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("iconFileName", getIconFileName());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long documentationProjectId = (Long)attributes.get(
				"documentationProjectId");

		if (documentationProjectId != null) {
			setDocumentationProjectId(documentationProjectId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String iconFileName = (String)attributes.get("iconFileName");

		if (iconFileName != null) {
			setIconFileName(iconFileName);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public DocumentationProject toEscapedModel() {
		return new DocumentationProjectWrapper(_documentationProject.toEscapedModel());
	}

	@Override
	public DocumentationProject toUnescapedModel() {
		return new DocumentationProjectWrapper(_documentationProject.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _documentationProject.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _documentationProject.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _documentationProject.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _documentationProject.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<DocumentationProject> toCacheModel() {
		return _documentationProject.toCacheModel();
	}

	@Override
	public int compareTo(DocumentationProject documentationProject) {
		return _documentationProject.compareTo(documentationProject);
	}

	/**
	* Returns the status of this documentation project.
	*
	* @return the status of this documentation project
	*/
	@Override
	public int getStatus() {
		return _documentationProject.getStatus();
	}

	@Override
	public int hashCode() {
		return _documentationProject.hashCode();
	}

	@Override
	public java.io.InputStream getIconInputStream()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _documentationProject.getIconInputStream();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _documentationProject.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new DocumentationProjectWrapper((DocumentationProject)_documentationProject.clone());
	}

	/**
	* Returns the description of this documentation project.
	*
	* @return the description of this documentation project
	*/
	@Override
	public java.lang.String getDescription() {
		return _documentationProject.getDescription();
	}

	/**
	* Returns the icon file name of this documentation project.
	*
	* @return the icon file name of this documentation project
	*/
	@Override
	public java.lang.String getIconFileName() {
		return _documentationProject.getIconFileName();
	}

	/**
	* Returns the name of this documentation project.
	*
	* @return the name of this documentation project
	*/
	@Override
	public java.lang.String getName() {
		return _documentationProject.getName();
	}

	/**
	* Returns the user name of this documentation project.
	*
	* @return the user name of this documentation project
	*/
	@Override
	public java.lang.String getUserName() {
		return _documentationProject.getUserName();
	}

	/**
	* Returns the user uuid of this documentation project.
	*
	* @return the user uuid of this documentation project
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _documentationProject.getUserUuid();
	}

	/**
	* Returns the uuid of this documentation project.
	*
	* @return the uuid of this documentation project
	*/
	@Override
	public java.lang.String getUuid() {
		return _documentationProject.getUuid();
	}

	@Override
	public java.lang.String toString() {
		return _documentationProject.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _documentationProject.toXmlString();
	}

	/**
	* Returns the create date of this documentation project.
	*
	* @return the create date of this documentation project
	*/
	@Override
	public Date getCreateDate() {
		return _documentationProject.getCreateDate();
	}

	/**
	* Returns the modified date of this documentation project.
	*
	* @return the modified date of this documentation project
	*/
	@Override
	public Date getModifiedDate() {
		return _documentationProject.getModifiedDate();
	}

	/**
	* Returns the company ID of this documentation project.
	*
	* @return the company ID of this documentation project
	*/
	@Override
	public long getCompanyId() {
		return _documentationProject.getCompanyId();
	}

	/**
	* Returns the documentation project ID of this documentation project.
	*
	* @return the documentation project ID of this documentation project
	*/
	@Override
	public long getDocumentationProjectId() {
		return _documentationProject.getDocumentationProjectId();
	}

	/**
	* Returns the primary key of this documentation project.
	*
	* @return the primary key of this documentation project
	*/
	@Override
	public long getPrimaryKey() {
		return _documentationProject.getPrimaryKey();
	}

	/**
	* Returns the user ID of this documentation project.
	*
	* @return the user ID of this documentation project
	*/
	@Override
	public long getUserId() {
		return _documentationProject.getUserId();
	}

	@Override
	public void persist() {
		_documentationProject.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_documentationProject.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this documentation project.
	*
	* @param companyId the company ID of this documentation project
	*/
	@Override
	public void setCompanyId(long companyId) {
		_documentationProject.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this documentation project.
	*
	* @param createDate the create date of this documentation project
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_documentationProject.setCreateDate(createDate);
	}

	/**
	* Sets the description of this documentation project.
	*
	* @param description the description of this documentation project
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_documentationProject.setDescription(description);
	}

	/**
	* Sets the documentation project ID of this documentation project.
	*
	* @param documentationProjectId the documentation project ID of this documentation project
	*/
	@Override
	public void setDocumentationProjectId(long documentationProjectId) {
		_documentationProject.setDocumentationProjectId(documentationProjectId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_documentationProject.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_documentationProject.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_documentationProject.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the icon file name of this documentation project.
	*
	* @param iconFileName the icon file name of this documentation project
	*/
	@Override
	public void setIconFileName(java.lang.String iconFileName) {
		_documentationProject.setIconFileName(iconFileName);
	}

	/**
	* Sets the modified date of this documentation project.
	*
	* @param modifiedDate the modified date of this documentation project
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_documentationProject.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this documentation project.
	*
	* @param name the name of this documentation project
	*/
	@Override
	public void setName(java.lang.String name) {
		_documentationProject.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_documentationProject.setNew(n);
	}

	/**
	* Sets the primary key of this documentation project.
	*
	* @param primaryKey the primary key of this documentation project
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_documentationProject.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_documentationProject.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this documentation project.
	*
	* @param status the status of this documentation project
	*/
	@Override
	public void setStatus(int status) {
		_documentationProject.setStatus(status);
	}

	/**
	* Sets the user ID of this documentation project.
	*
	* @param userId the user ID of this documentation project
	*/
	@Override
	public void setUserId(long userId) {
		_documentationProject.setUserId(userId);
	}

	/**
	* Sets the user name of this documentation project.
	*
	* @param userName the user name of this documentation project
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_documentationProject.setUserName(userName);
	}

	/**
	* Sets the user uuid of this documentation project.
	*
	* @param userUuid the user uuid of this documentation project
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_documentationProject.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this documentation project.
	*
	* @param uuid the uuid of this documentation project
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_documentationProject.setUuid(uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DocumentationProjectWrapper)) {
			return false;
		}

		DocumentationProjectWrapper documentationProjectWrapper = (DocumentationProjectWrapper)obj;

		if (Objects.equals(_documentationProject,
					documentationProjectWrapper._documentationProject)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _documentationProject.getStagedModelType();
	}

	@Override
	public DocumentationProject getWrappedModel() {
		return _documentationProject;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _documentationProject.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _documentationProject.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_documentationProject.resetOriginalValues();
	}

	private final DocumentationProject _documentationProject;
}