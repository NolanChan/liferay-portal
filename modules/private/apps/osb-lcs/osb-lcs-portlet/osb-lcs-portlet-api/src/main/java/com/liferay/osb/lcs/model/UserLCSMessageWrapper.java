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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link UserLCSMessage}.
 * </p>
 *
 * @author Igor Beslic
 * @see UserLCSMessage
 * @generated
 */
@ProviderType
public class UserLCSMessageWrapper implements UserLCSMessage,
	ModelWrapper<UserLCSMessage> {
	public UserLCSMessageWrapper(UserLCSMessage userLCSMessage) {
		_userLCSMessage = userLCSMessage;
	}

	@Override
	public Class<?> getModelClass() {
		return UserLCSMessage.class;
	}

	@Override
	public String getModelClassName() {
		return UserLCSMessage.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("userLcsMessageId", getUserLcsMessageId());
		attributes.put("userId", getUserId());
		attributes.put("lcsMessageId", getLcsMessageId());
		attributes.put("hidden", getHidden());
		attributes.put("read", getRead());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long userLcsMessageId = (Long)attributes.get("userLcsMessageId");

		if (userLcsMessageId != null) {
			setUserLcsMessageId(userLcsMessageId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long lcsMessageId = (Long)attributes.get("lcsMessageId");

		if (lcsMessageId != null) {
			setLcsMessageId(lcsMessageId);
		}

		Boolean hidden = (Boolean)attributes.get("hidden");

		if (hidden != null) {
			setHidden(hidden);
		}

		Boolean read = (Boolean)attributes.get("read");

		if (read != null) {
			setRead(read);
		}
	}

	@Override
	public LCSMessage getLcsMessage()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _userLCSMessage.getLcsMessage();
	}

	@Override
	public UserLCSMessage toEscapedModel() {
		return new UserLCSMessageWrapper(_userLCSMessage.toEscapedModel());
	}

	@Override
	public UserLCSMessage toUnescapedModel() {
		return new UserLCSMessageWrapper(_userLCSMessage.toUnescapedModel());
	}

	/**
	* Returns the hidden of this user l c s message.
	*
	* @return the hidden of this user l c s message
	*/
	@Override
	public boolean getHidden() {
		return _userLCSMessage.getHidden();
	}

	/**
	* Returns the read of this user l c s message.
	*
	* @return the read of this user l c s message
	*/
	@Override
	public boolean getRead() {
		return _userLCSMessage.getRead();
	}

	@Override
	public boolean isCachedModel() {
		return _userLCSMessage.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _userLCSMessage.isEscapedModel();
	}

	/**
	* Returns <code>true</code> if this user l c s message is hidden.
	*
	* @return <code>true</code> if this user l c s message is hidden; <code>false</code> otherwise
	*/
	@Override
	public boolean isHidden() {
		return _userLCSMessage.isHidden();
	}

	@Override
	public boolean isNew() {
		return _userLCSMessage.isNew();
	}

	/**
	* Returns <code>true</code> if this user l c s message is read.
	*
	* @return <code>true</code> if this user l c s message is read; <code>false</code> otherwise
	*/
	@Override
	public boolean isRead() {
		return _userLCSMessage.isRead();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _userLCSMessage.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<UserLCSMessage> toCacheModel() {
		return _userLCSMessage.toCacheModel();
	}

	@Override
	public int compareTo(UserLCSMessage userLCSMessage) {
		return _userLCSMessage.compareTo(userLCSMessage);
	}

	@Override
	public int hashCode() {
		return _userLCSMessage.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _userLCSMessage.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new UserLCSMessageWrapper((UserLCSMessage)_userLCSMessage.clone());
	}

	/**
	* Returns the user uuid of this user l c s message.
	*
	* @return the user uuid of this user l c s message
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _userLCSMessage.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _userLCSMessage.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _userLCSMessage.toXmlString();
	}

	/**
	* Returns the lcs message ID of this user l c s message.
	*
	* @return the lcs message ID of this user l c s message
	*/
	@Override
	public long getLcsMessageId() {
		return _userLCSMessage.getLcsMessageId();
	}

	/**
	* Returns the primary key of this user l c s message.
	*
	* @return the primary key of this user l c s message
	*/
	@Override
	public long getPrimaryKey() {
		return _userLCSMessage.getPrimaryKey();
	}

	/**
	* Returns the user ID of this user l c s message.
	*
	* @return the user ID of this user l c s message
	*/
	@Override
	public long getUserId() {
		return _userLCSMessage.getUserId();
	}

	/**
	* Returns the user lcs message ID of this user l c s message.
	*
	* @return the user lcs message ID of this user l c s message
	*/
	@Override
	public long getUserLcsMessageId() {
		return _userLCSMessage.getUserLcsMessageId();
	}

	@Override
	public void persist() {
		_userLCSMessage.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_userLCSMessage.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_userLCSMessage.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_userLCSMessage.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_userLCSMessage.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets whether this user l c s message is hidden.
	*
	* @param hidden the hidden of this user l c s message
	*/
	@Override
	public void setHidden(boolean hidden) {
		_userLCSMessage.setHidden(hidden);
	}

	/**
	* Sets the lcs message ID of this user l c s message.
	*
	* @param lcsMessageId the lcs message ID of this user l c s message
	*/
	@Override
	public void setLcsMessageId(long lcsMessageId) {
		_userLCSMessage.setLcsMessageId(lcsMessageId);
	}

	@Override
	public void setNew(boolean n) {
		_userLCSMessage.setNew(n);
	}

	/**
	* Sets the primary key of this user l c s message.
	*
	* @param primaryKey the primary key of this user l c s message
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_userLCSMessage.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_userLCSMessage.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets whether this user l c s message is read.
	*
	* @param read the read of this user l c s message
	*/
	@Override
	public void setRead(boolean read) {
		_userLCSMessage.setRead(read);
	}

	/**
	* Sets the user ID of this user l c s message.
	*
	* @param userId the user ID of this user l c s message
	*/
	@Override
	public void setUserId(long userId) {
		_userLCSMessage.setUserId(userId);
	}

	/**
	* Sets the user lcs message ID of this user l c s message.
	*
	* @param userLcsMessageId the user lcs message ID of this user l c s message
	*/
	@Override
	public void setUserLcsMessageId(long userLcsMessageId) {
		_userLCSMessage.setUserLcsMessageId(userLcsMessageId);
	}

	/**
	* Sets the user uuid of this user l c s message.
	*
	* @param userUuid the user uuid of this user l c s message
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_userLCSMessage.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserLCSMessageWrapper)) {
			return false;
		}

		UserLCSMessageWrapper userLCSMessageWrapper = (UserLCSMessageWrapper)obj;

		if (Objects.equals(_userLCSMessage,
					userLCSMessageWrapper._userLCSMessage)) {
			return true;
		}

		return false;
	}

	@Override
	public UserLCSMessage getWrappedModel() {
		return _userLCSMessage;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _userLCSMessage.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _userLCSMessage.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_userLCSMessage.resetOriginalValues();
	}

	private final UserLCSMessage _userLCSMessage;
}