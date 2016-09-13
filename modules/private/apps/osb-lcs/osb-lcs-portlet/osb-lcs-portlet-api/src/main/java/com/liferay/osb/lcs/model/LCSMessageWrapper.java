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

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link LCSMessage}.
 * </p>
 *
 * @author Igor Beslic
 * @see LCSMessage
 * @generated
 */
@ProviderType
public class LCSMessageWrapper implements LCSMessage, ModelWrapper<LCSMessage> {
	public LCSMessageWrapper(LCSMessage lcsMessage) {
		_lcsMessage = lcsMessage;
	}

	@Override
	public Class<?> getModelClass() {
		return LCSMessage.class;
	}

	@Override
	public String getModelClassName() {
		return LCSMessage.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lcsMessageId", getLcsMessageId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("sourceMessageId", getSourceMessageId());
		attributes.put("sourceSystemName", getSourceSystemName());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("content", getContent());
		attributes.put("endDate", getEndDate());
		attributes.put("global", getGlobal());
		attributes.put("severityLevel", getSeverityLevel());
		attributes.put("type", getType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long lcsMessageId = (Long)attributes.get("lcsMessageId");

		if (lcsMessageId != null) {
			setLcsMessageId(lcsMessageId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long sourceMessageId = (Long)attributes.get("sourceMessageId");

		if (sourceMessageId != null) {
			setSourceMessageId(sourceMessageId);
		}

		String sourceSystemName = (String)attributes.get("sourceSystemName");

		if (sourceSystemName != null) {
			setSourceSystemName(sourceSystemName);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Boolean global = (Boolean)attributes.get("global");

		if (global != null) {
			setGlobal(global);
		}

		Integer severityLevel = (Integer)attributes.get("severityLevel");

		if (severityLevel != null) {
			setSeverityLevel(severityLevel);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}
	}

	@Override
	public LCSMessage toEscapedModel() {
		return new LCSMessageWrapper(_lcsMessage.toEscapedModel());
	}

	@Override
	public LCSMessage toUnescapedModel() {
		return new LCSMessageWrapper(_lcsMessage.toUnescapedModel());
	}

	/**
	* Returns the global of this l c s message.
	*
	* @return the global of this l c s message
	*/
	@Override
	public boolean getGlobal() {
		return _lcsMessage.getGlobal();
	}

	@Override
	public boolean isCachedModel() {
		return _lcsMessage.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _lcsMessage.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this l c s message is global.
	*
	* @return <code>true</code> if this l c s message is global; <code>false</code> otherwise
	*/
	@Override
	public boolean isGlobal() {
		return _lcsMessage.isGlobal();
	}

	@Override
	public boolean isNew() {
		return _lcsMessage.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _lcsMessage.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LCSMessage> toCacheModel() {
		return _lcsMessage.toCacheModel();
	}

	@Override
	public int compareTo(LCSMessage lcsMessage) {
		return _lcsMessage.compareTo(lcsMessage);
	}

	/**
	* Returns the severity level of this l c s message.
	*
	* @return the severity level of this l c s message
	*/
	@Override
	public int getSeverityLevel() {
		return _lcsMessage.getSeverityLevel();
	}

	/**
	* Returns the type of this l c s message.
	*
	* @return the type of this l c s message
	*/
	@Override
	public int getType() {
		return _lcsMessage.getType();
	}

	@Override
	public int hashCode() {
		return _lcsMessage.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _lcsMessage.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new LCSMessageWrapper((LCSMessage)_lcsMessage.clone());
	}

	/**
	* Returns the fully qualified class name of this l c s message.
	*
	* @return the fully qualified class name of this l c s message
	*/
	@Override
	public java.lang.String getClassName() {
		return _lcsMessage.getClassName();
	}

	/**
	* Returns the content of this l c s message.
	*
	* @return the content of this l c s message
	*/
	@Override
	public java.lang.String getContent() {
		return _lcsMessage.getContent();
	}

	/**
	* Returns the source system name of this l c s message.
	*
	* @return the source system name of this l c s message
	*/
	@Override
	public java.lang.String getSourceSystemName() {
		return _lcsMessage.getSourceSystemName();
	}

	@Override
	public java.lang.String toString() {
		return _lcsMessage.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _lcsMessage.toXmlString();
	}

	/**
	* Returns the create date of this l c s message.
	*
	* @return the create date of this l c s message
	*/
	@Override
	public Date getCreateDate() {
		return _lcsMessage.getCreateDate();
	}

	/**
	* Returns the end date of this l c s message.
	*
	* @return the end date of this l c s message
	*/
	@Override
	public Date getEndDate() {
		return _lcsMessage.getEndDate();
	}

	/**
	* Returns the modified date of this l c s message.
	*
	* @return the modified date of this l c s message
	*/
	@Override
	public Date getModifiedDate() {
		return _lcsMessage.getModifiedDate();
	}

	/**
	* Returns the class name ID of this l c s message.
	*
	* @return the class name ID of this l c s message
	*/
	@Override
	public long getClassNameId() {
		return _lcsMessage.getClassNameId();
	}

	/**
	* Returns the class p k of this l c s message.
	*
	* @return the class p k of this l c s message
	*/
	@Override
	public long getClassPK() {
		return _lcsMessage.getClassPK();
	}

	/**
	* Returns the lcs message ID of this l c s message.
	*
	* @return the lcs message ID of this l c s message
	*/
	@Override
	public long getLcsMessageId() {
		return _lcsMessage.getLcsMessageId();
	}

	/**
	* Returns the primary key of this l c s message.
	*
	* @return the primary key of this l c s message
	*/
	@Override
	public long getPrimaryKey() {
		return _lcsMessage.getPrimaryKey();
	}

	/**
	* Returns the source message ID of this l c s message.
	*
	* @return the source message ID of this l c s message
	*/
	@Override
	public long getSourceMessageId() {
		return _lcsMessage.getSourceMessageId();
	}

	@Override
	public void persist() {
		_lcsMessage.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_lcsMessage.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(java.lang.String className) {
		_lcsMessage.setClassName(className);
	}

	/**
	* Sets the class name ID of this l c s message.
	*
	* @param classNameId the class name ID of this l c s message
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_lcsMessage.setClassNameId(classNameId);
	}

	/**
	* Sets the class p k of this l c s message.
	*
	* @param classPK the class p k of this l c s message
	*/
	@Override
	public void setClassPK(long classPK) {
		_lcsMessage.setClassPK(classPK);
	}

	/**
	* Sets the content of this l c s message.
	*
	* @param content the content of this l c s message
	*/
	@Override
	public void setContent(java.lang.String content) {
		_lcsMessage.setContent(content);
	}

	/**
	* Sets the create date of this l c s message.
	*
	* @param createDate the create date of this l c s message
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_lcsMessage.setCreateDate(createDate);
	}

	/**
	* Sets the end date of this l c s message.
	*
	* @param endDate the end date of this l c s message
	*/
	@Override
	public void setEndDate(Date endDate) {
		_lcsMessage.setEndDate(endDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_lcsMessage.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_lcsMessage.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_lcsMessage.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets whether this l c s message is global.
	*
	* @param global the global of this l c s message
	*/
	@Override
	public void setGlobal(boolean global) {
		_lcsMessage.setGlobal(global);
	}

	/**
	* Sets the lcs message ID of this l c s message.
	*
	* @param lcsMessageId the lcs message ID of this l c s message
	*/
	@Override
	public void setLcsMessageId(long lcsMessageId) {
		_lcsMessage.setLcsMessageId(lcsMessageId);
	}

	/**
	* Sets the modified date of this l c s message.
	*
	* @param modifiedDate the modified date of this l c s message
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_lcsMessage.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_lcsMessage.setNew(n);
	}

	/**
	* Sets the primary key of this l c s message.
	*
	* @param primaryKey the primary key of this l c s message
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_lcsMessage.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_lcsMessage.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the severity level of this l c s message.
	*
	* @param severityLevel the severity level of this l c s message
	*/
	@Override
	public void setSeverityLevel(int severityLevel) {
		_lcsMessage.setSeverityLevel(severityLevel);
	}

	/**
	* Sets the source message ID of this l c s message.
	*
	* @param sourceMessageId the source message ID of this l c s message
	*/
	@Override
	public void setSourceMessageId(long sourceMessageId) {
		_lcsMessage.setSourceMessageId(sourceMessageId);
	}

	/**
	* Sets the source system name of this l c s message.
	*
	* @param sourceSystemName the source system name of this l c s message
	*/
	@Override
	public void setSourceSystemName(java.lang.String sourceSystemName) {
		_lcsMessage.setSourceSystemName(sourceSystemName);
	}

	/**
	* Sets the type of this l c s message.
	*
	* @param type the type of this l c s message
	*/
	@Override
	public void setType(int type) {
		_lcsMessage.setType(type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LCSMessageWrapper)) {
			return false;
		}

		LCSMessageWrapper lcsMessageWrapper = (LCSMessageWrapper)obj;

		if (Objects.equals(_lcsMessage, lcsMessageWrapper._lcsMessage)) {
			return true;
		}

		return false;
	}

	@Override
	public LCSMessage getWrappedModel() {
		return _lcsMessage;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _lcsMessage.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _lcsMessage.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_lcsMessage.resetOriginalValues();
	}

	private final LCSMessage _lcsMessage;
}