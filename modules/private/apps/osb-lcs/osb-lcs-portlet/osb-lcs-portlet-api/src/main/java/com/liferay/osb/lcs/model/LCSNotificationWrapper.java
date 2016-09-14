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
 * This class is a wrapper for {@link LCSNotification}.
 * </p>
 *
 * @author Igor Beslic
 * @see LCSNotification
 * @generated
 */
@ProviderType
public class LCSNotificationWrapper implements LCSNotification,
	ModelWrapper<LCSNotification> {
	public LCSNotificationWrapper(LCSNotification lcsNotification) {
		_lcsNotification = lcsNotification;
	}

	@Override
	public Class<?> getModelClass() {
		return LCSNotification.class;
	}

	@Override
	public String getModelClassName() {
		return LCSNotification.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lcsNotificationId", getLcsNotificationId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("enabled", getEnabled());
		attributes.put("type", getType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long lcsNotificationId = (Long)attributes.get("lcsNotificationId");

		if (lcsNotificationId != null) {
			setLcsNotificationId(lcsNotificationId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Boolean enabled = (Boolean)attributes.get("enabled");

		if (enabled != null) {
			setEnabled(enabled);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}
	}

	@Override
	public LCSNotification toEscapedModel() {
		return new LCSNotificationWrapper(_lcsNotification.toEscapedModel());
	}

	@Override
	public LCSNotification toUnescapedModel() {
		return new LCSNotificationWrapper(_lcsNotification.toUnescapedModel());
	}

	/**
	* Returns the enabled of this l c s notification.
	*
	* @return the enabled of this l c s notification
	*/
	@Override
	public boolean getEnabled() {
		return _lcsNotification.getEnabled();
	}

	@Override
	public boolean isCachedModel() {
		return _lcsNotification.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this l c s notification is enabled.
	*
	* @return <code>true</code> if this l c s notification is enabled; <code>false</code> otherwise
	*/
	@Override
	public boolean isEnabled() {
		return _lcsNotification.isEnabled();
	}

	@Override
	public boolean isEscapedModel() {
		return _lcsNotification.isEscapedModel();
	}

	@Override
	public boolean isLCSClusterEntryNotification() {
		return _lcsNotification.isLCSClusterEntryNotification();
	}

	@Override
	public boolean isLCSClusterNodeNotification() {
		return _lcsNotification.isLCSClusterNodeNotification();
	}

	@Override
	public boolean isLCSProjectNotification() {
		return _lcsNotification.isLCSProjectNotification();
	}

	@Override
	public boolean isNew() {
		return _lcsNotification.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _lcsNotification.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LCSNotification> toCacheModel() {
		return _lcsNotification.toCacheModel();
	}

	@Override
	public int compareTo(LCSNotification lcsNotification) {
		return _lcsNotification.compareTo(lcsNotification);
	}

	/**
	* Returns the type of this l c s notification.
	*
	* @return the type of this l c s notification
	*/
	@Override
	public int getType() {
		return _lcsNotification.getType();
	}

	@Override
	public int hashCode() {
		return _lcsNotification.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _lcsNotification.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new LCSNotificationWrapper((LCSNotification)_lcsNotification.clone());
	}

	/**
	* Returns the fully qualified class name of this l c s notification.
	*
	* @return the fully qualified class name of this l c s notification
	*/
	@Override
	public java.lang.String getClassName() {
		return _lcsNotification.getClassName();
	}

	/**
	* Returns the user uuid of this l c s notification.
	*
	* @return the user uuid of this l c s notification
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _lcsNotification.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _lcsNotification.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _lcsNotification.toXmlString();
	}

	/**
	* Returns the create date of this l c s notification.
	*
	* @return the create date of this l c s notification
	*/
	@Override
	public Date getCreateDate() {
		return _lcsNotification.getCreateDate();
	}

	/**
	* Returns the modified date of this l c s notification.
	*
	* @return the modified date of this l c s notification
	*/
	@Override
	public Date getModifiedDate() {
		return _lcsNotification.getModifiedDate();
	}

	/**
	* Returns the class name ID of this l c s notification.
	*
	* @return the class name ID of this l c s notification
	*/
	@Override
	public long getClassNameId() {
		return _lcsNotification.getClassNameId();
	}

	/**
	* Returns the class p k of this l c s notification.
	*
	* @return the class p k of this l c s notification
	*/
	@Override
	public long getClassPK() {
		return _lcsNotification.getClassPK();
	}

	/**
	* Returns the lcs notification ID of this l c s notification.
	*
	* @return the lcs notification ID of this l c s notification
	*/
	@Override
	public long getLcsNotificationId() {
		return _lcsNotification.getLcsNotificationId();
	}

	/**
	* Returns the primary key of this l c s notification.
	*
	* @return the primary key of this l c s notification
	*/
	@Override
	public long getPrimaryKey() {
		return _lcsNotification.getPrimaryKey();
	}

	/**
	* Returns the user ID of this l c s notification.
	*
	* @return the user ID of this l c s notification
	*/
	@Override
	public long getUserId() {
		return _lcsNotification.getUserId();
	}

	@Override
	public void persist() {
		_lcsNotification.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_lcsNotification.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(java.lang.String className) {
		_lcsNotification.setClassName(className);
	}

	/**
	* Sets the class name ID of this l c s notification.
	*
	* @param classNameId the class name ID of this l c s notification
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_lcsNotification.setClassNameId(classNameId);
	}

	/**
	* Sets the class p k of this l c s notification.
	*
	* @param classPK the class p k of this l c s notification
	*/
	@Override
	public void setClassPK(long classPK) {
		_lcsNotification.setClassPK(classPK);
	}

	/**
	* Sets the create date of this l c s notification.
	*
	* @param createDate the create date of this l c s notification
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_lcsNotification.setCreateDate(createDate);
	}

	/**
	* Sets whether this l c s notification is enabled.
	*
	* @param enabled the enabled of this l c s notification
	*/
	@Override
	public void setEnabled(boolean enabled) {
		_lcsNotification.setEnabled(enabled);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_lcsNotification.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_lcsNotification.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_lcsNotification.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the lcs notification ID of this l c s notification.
	*
	* @param lcsNotificationId the lcs notification ID of this l c s notification
	*/
	@Override
	public void setLcsNotificationId(long lcsNotificationId) {
		_lcsNotification.setLcsNotificationId(lcsNotificationId);
	}

	/**
	* Sets the modified date of this l c s notification.
	*
	* @param modifiedDate the modified date of this l c s notification
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_lcsNotification.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_lcsNotification.setNew(n);
	}

	/**
	* Sets the primary key of this l c s notification.
	*
	* @param primaryKey the primary key of this l c s notification
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_lcsNotification.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_lcsNotification.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the type of this l c s notification.
	*
	* @param type the type of this l c s notification
	*/
	@Override
	public void setType(int type) {
		_lcsNotification.setType(type);
	}

	/**
	* Sets the user ID of this l c s notification.
	*
	* @param userId the user ID of this l c s notification
	*/
	@Override
	public void setUserId(long userId) {
		_lcsNotification.setUserId(userId);
	}

	/**
	* Sets the user uuid of this l c s notification.
	*
	* @param userUuid the user uuid of this l c s notification
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_lcsNotification.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LCSNotificationWrapper)) {
			return false;
		}

		LCSNotificationWrapper lcsNotificationWrapper = (LCSNotificationWrapper)obj;

		if (Objects.equals(_lcsNotification,
					lcsNotificationWrapper._lcsNotification)) {
			return true;
		}

		return false;
	}

	@Override
	public LCSNotification getWrappedModel() {
		return _lcsNotification;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _lcsNotification.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _lcsNotification.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_lcsNotification.resetOriginalValues();
	}

	private final LCSNotification _lcsNotification;
}