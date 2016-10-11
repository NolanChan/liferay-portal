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
 * This class is a wrapper for {@link LCSNotificationAuditEntry}.
 * </p>
 *
 * @author Igor Beslic
 * @see LCSNotificationAuditEntry
 * @generated
 */
@ProviderType
public class LCSNotificationAuditEntryWrapper
	implements LCSNotificationAuditEntry,
		ModelWrapper<LCSNotificationAuditEntry> {
	public LCSNotificationAuditEntryWrapper(
		LCSNotificationAuditEntry lcsNotificationAuditEntry) {
		_lcsNotificationAuditEntry = lcsNotificationAuditEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return LCSNotificationAuditEntry.class;
	}

	@Override
	public String getModelClassName() {
		return LCSNotificationAuditEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lcsNotificationAuditEntryId",
			getLcsNotificationAuditEntryId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("lcsClusterNodeId", getLcsClusterNodeId());
		attributes.put("type", getType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long lcsNotificationAuditEntryId = (Long)attributes.get(
				"lcsNotificationAuditEntryId");

		if (lcsNotificationAuditEntryId != null) {
			setLcsNotificationAuditEntryId(lcsNotificationAuditEntryId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long lcsClusterNodeId = (Long)attributes.get("lcsClusterNodeId");

		if (lcsClusterNodeId != null) {
			setLcsClusterNodeId(lcsClusterNodeId);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}
	}

	@Override
	public LCSNotificationAuditEntry toEscapedModel() {
		return new LCSNotificationAuditEntryWrapper(_lcsNotificationAuditEntry.toEscapedModel());
	}

	@Override
	public LCSNotificationAuditEntry toUnescapedModel() {
		return new LCSNotificationAuditEntryWrapper(_lcsNotificationAuditEntry.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _lcsNotificationAuditEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _lcsNotificationAuditEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _lcsNotificationAuditEntry.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _lcsNotificationAuditEntry.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LCSNotificationAuditEntry> toCacheModel() {
		return _lcsNotificationAuditEntry.toCacheModel();
	}

	@Override
	public int compareTo(LCSNotificationAuditEntry lcsNotificationAuditEntry) {
		return _lcsNotificationAuditEntry.compareTo(lcsNotificationAuditEntry);
	}

	/**
	* Returns the type of this l c s notification audit entry.
	*
	* @return the type of this l c s notification audit entry
	*/
	@Override
	public int getType() {
		return _lcsNotificationAuditEntry.getType();
	}

	@Override
	public int hashCode() {
		return _lcsNotificationAuditEntry.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _lcsNotificationAuditEntry.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new LCSNotificationAuditEntryWrapper((LCSNotificationAuditEntry)_lcsNotificationAuditEntry.clone());
	}

	@Override
	public java.lang.String getLcsClusterEntryName() {
		return _lcsNotificationAuditEntry.getLcsClusterEntryName();
	}

	@Override
	public java.lang.String getLcsClusterNodeKey() {
		return _lcsNotificationAuditEntry.getLcsClusterNodeKey();
	}

	@Override
	public java.lang.String getLcsClusterNodeName() {
		return _lcsNotificationAuditEntry.getLcsClusterNodeName();
	}

	@Override
	public java.lang.String getLcsProjectName() {
		return _lcsNotificationAuditEntry.getLcsProjectName();
	}

	@Override
	public java.lang.String getUserEmailAddress() {
		return _lcsNotificationAuditEntry.getUserEmailAddress();
	}

	/**
	* Returns the user uuid of this l c s notification audit entry.
	*
	* @return the user uuid of this l c s notification audit entry
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _lcsNotificationAuditEntry.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _lcsNotificationAuditEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _lcsNotificationAuditEntry.toXmlString();
	}

	/**
	* Returns the create date of this l c s notification audit entry.
	*
	* @return the create date of this l c s notification audit entry
	*/
	@Override
	public Date getCreateDate() {
		return _lcsNotificationAuditEntry.getCreateDate();
	}

	/**
	* Returns the lcs cluster node ID of this l c s notification audit entry.
	*
	* @return the lcs cluster node ID of this l c s notification audit entry
	*/
	@Override
	public long getLcsClusterNodeId() {
		return _lcsNotificationAuditEntry.getLcsClusterNodeId();
	}

	/**
	* Returns the lcs notification audit entry ID of this l c s notification audit entry.
	*
	* @return the lcs notification audit entry ID of this l c s notification audit entry
	*/
	@Override
	public long getLcsNotificationAuditEntryId() {
		return _lcsNotificationAuditEntry.getLcsNotificationAuditEntryId();
	}

	/**
	* Returns the primary key of this l c s notification audit entry.
	*
	* @return the primary key of this l c s notification audit entry
	*/
	@Override
	public long getPrimaryKey() {
		return _lcsNotificationAuditEntry.getPrimaryKey();
	}

	/**
	* Returns the user ID of this l c s notification audit entry.
	*
	* @return the user ID of this l c s notification audit entry
	*/
	@Override
	public long getUserId() {
		return _lcsNotificationAuditEntry.getUserId();
	}

	@Override
	public void persist() {
		_lcsNotificationAuditEntry.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_lcsNotificationAuditEntry.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this l c s notification audit entry.
	*
	* @param createDate the create date of this l c s notification audit entry
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_lcsNotificationAuditEntry.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_lcsNotificationAuditEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_lcsNotificationAuditEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_lcsNotificationAuditEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setLcsClusterEntryName(java.lang.String lcsClusterEntryName) {
		_lcsNotificationAuditEntry.setLcsClusterEntryName(lcsClusterEntryName);
	}

	/**
	* Sets the lcs cluster node ID of this l c s notification audit entry.
	*
	* @param lcsClusterNodeId the lcs cluster node ID of this l c s notification audit entry
	*/
	@Override
	public void setLcsClusterNodeId(long lcsClusterNodeId) {
		_lcsNotificationAuditEntry.setLcsClusterNodeId(lcsClusterNodeId);
	}

	@Override
	public void setLcsClusterNodeKey(java.lang.String lcsClusterNodeKey) {
		_lcsNotificationAuditEntry.setLcsClusterNodeKey(lcsClusterNodeKey);
	}

	@Override
	public void setLcsClusterNodeName(java.lang.String lcsClusterNodeName) {
		_lcsNotificationAuditEntry.setLcsClusterNodeName(lcsClusterNodeName);
	}

	/**
	* Sets the lcs notification audit entry ID of this l c s notification audit entry.
	*
	* @param lcsNotificationAuditEntryId the lcs notification audit entry ID of this l c s notification audit entry
	*/
	@Override
	public void setLcsNotificationAuditEntryId(long lcsNotificationAuditEntryId) {
		_lcsNotificationAuditEntry.setLcsNotificationAuditEntryId(lcsNotificationAuditEntryId);
	}

	@Override
	public void setLcsProjectName(java.lang.String lcsProjectName) {
		_lcsNotificationAuditEntry.setLcsProjectName(lcsProjectName);
	}

	@Override
	public void setNew(boolean n) {
		_lcsNotificationAuditEntry.setNew(n);
	}

	/**
	* Sets the primary key of this l c s notification audit entry.
	*
	* @param primaryKey the primary key of this l c s notification audit entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_lcsNotificationAuditEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_lcsNotificationAuditEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the type of this l c s notification audit entry.
	*
	* @param type the type of this l c s notification audit entry
	*/
	@Override
	public void setType(int type) {
		_lcsNotificationAuditEntry.setType(type);
	}

	@Override
	public void setUserEmailAddress(java.lang.String userEmailAddress) {
		_lcsNotificationAuditEntry.setUserEmailAddress(userEmailAddress);
	}

	/**
	* Sets the user ID of this l c s notification audit entry.
	*
	* @param userId the user ID of this l c s notification audit entry
	*/
	@Override
	public void setUserId(long userId) {
		_lcsNotificationAuditEntry.setUserId(userId);
	}

	/**
	* Sets the user uuid of this l c s notification audit entry.
	*
	* @param userUuid the user uuid of this l c s notification audit entry
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_lcsNotificationAuditEntry.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LCSNotificationAuditEntryWrapper)) {
			return false;
		}

		LCSNotificationAuditEntryWrapper lcsNotificationAuditEntryWrapper = (LCSNotificationAuditEntryWrapper)obj;

		if (Objects.equals(_lcsNotificationAuditEntry,
					lcsNotificationAuditEntryWrapper._lcsNotificationAuditEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public LCSNotificationAuditEntry getWrappedModel() {
		return _lcsNotificationAuditEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _lcsNotificationAuditEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _lcsNotificationAuditEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_lcsNotificationAuditEntry.resetOriginalValues();
	}

	private final LCSNotificationAuditEntry _lcsNotificationAuditEntry;
}