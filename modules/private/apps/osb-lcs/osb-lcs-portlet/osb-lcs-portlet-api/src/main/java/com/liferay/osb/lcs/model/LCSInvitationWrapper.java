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
 * This class is a wrapper for {@link LCSInvitation}.
 * </p>
 *
 * @author Igor Beslic
 * @see LCSInvitation
 * @generated
 */
@ProviderType
public class LCSInvitationWrapper implements LCSInvitation,
	ModelWrapper<LCSInvitation> {
	public LCSInvitationWrapper(LCSInvitation lcsInvitation) {
		_lcsInvitation = lcsInvitation;
	}

	@Override
	public Class<?> getModelClass() {
		return LCSInvitation.class;
	}

	@Override
	public String getModelClassName() {
		return LCSInvitation.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lcsInvitationId", getLcsInvitationId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("lcsProjectId", getLcsProjectId());
		attributes.put("emailAddress", getEmailAddress());
		attributes.put("lcsClusterEntryId", getLcsClusterEntryId());
		attributes.put("role", getRole());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long lcsInvitationId = (Long)attributes.get("lcsInvitationId");

		if (lcsInvitationId != null) {
			setLcsInvitationId(lcsInvitationId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long lcsProjectId = (Long)attributes.get("lcsProjectId");

		if (lcsProjectId != null) {
			setLcsProjectId(lcsProjectId);
		}

		String emailAddress = (String)attributes.get("emailAddress");

		if (emailAddress != null) {
			setEmailAddress(emailAddress);
		}

		Long lcsClusterEntryId = (Long)attributes.get("lcsClusterEntryId");

		if (lcsClusterEntryId != null) {
			setLcsClusterEntryId(lcsClusterEntryId);
		}

		Integer role = (Integer)attributes.get("role");

		if (role != null) {
			setRole(role);
		}
	}

	@Override
	public LCSInvitation toEscapedModel() {
		return new LCSInvitationWrapper(_lcsInvitation.toEscapedModel());
	}

	@Override
	public LCSInvitation toUnescapedModel() {
		return new LCSInvitationWrapper(_lcsInvitation.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _lcsInvitation.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _lcsInvitation.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _lcsInvitation.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _lcsInvitation.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LCSInvitation> toCacheModel() {
		return _lcsInvitation.toCacheModel();
	}

	@Override
	public int compareTo(LCSInvitation lcsInvitation) {
		return _lcsInvitation.compareTo(lcsInvitation);
	}

	/**
	* Returns the role of this l c s invitation.
	*
	* @return the role of this l c s invitation
	*/
	@Override
	public int getRole() {
		return _lcsInvitation.getRole();
	}

	@Override
	public int hashCode() {
		return _lcsInvitation.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _lcsInvitation.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new LCSInvitationWrapper((LCSInvitation)_lcsInvitation.clone());
	}

	/**
	* Returns the email address of this l c s invitation.
	*
	* @return the email address of this l c s invitation
	*/
	@Override
	public java.lang.String getEmailAddress() {
		return _lcsInvitation.getEmailAddress();
	}

	/**
	* Returns the user uuid of this l c s invitation.
	*
	* @return the user uuid of this l c s invitation
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _lcsInvitation.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _lcsInvitation.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _lcsInvitation.toXmlString();
	}

	/**
	* Returns the create date of this l c s invitation.
	*
	* @return the create date of this l c s invitation
	*/
	@Override
	public Date getCreateDate() {
		return _lcsInvitation.getCreateDate();
	}

	/**
	* Returns the lcs cluster entry ID of this l c s invitation.
	*
	* @return the lcs cluster entry ID of this l c s invitation
	*/
	@Override
	public long getLcsClusterEntryId() {
		return _lcsInvitation.getLcsClusterEntryId();
	}

	/**
	* Returns the lcs invitation ID of this l c s invitation.
	*
	* @return the lcs invitation ID of this l c s invitation
	*/
	@Override
	public long getLcsInvitationId() {
		return _lcsInvitation.getLcsInvitationId();
	}

	/**
	* Returns the lcs project ID of this l c s invitation.
	*
	* @return the lcs project ID of this l c s invitation
	*/
	@Override
	public long getLcsProjectId() {
		return _lcsInvitation.getLcsProjectId();
	}

	/**
	* Returns the primary key of this l c s invitation.
	*
	* @return the primary key of this l c s invitation
	*/
	@Override
	public long getPrimaryKey() {
		return _lcsInvitation.getPrimaryKey();
	}

	/**
	* Returns the user ID of this l c s invitation.
	*
	* @return the user ID of this l c s invitation
	*/
	@Override
	public long getUserId() {
		return _lcsInvitation.getUserId();
	}

	@Override
	public void persist() {
		_lcsInvitation.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_lcsInvitation.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this l c s invitation.
	*
	* @param createDate the create date of this l c s invitation
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_lcsInvitation.setCreateDate(createDate);
	}

	/**
	* Sets the email address of this l c s invitation.
	*
	* @param emailAddress the email address of this l c s invitation
	*/
	@Override
	public void setEmailAddress(java.lang.String emailAddress) {
		_lcsInvitation.setEmailAddress(emailAddress);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_lcsInvitation.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_lcsInvitation.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_lcsInvitation.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the lcs cluster entry ID of this l c s invitation.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID of this l c s invitation
	*/
	@Override
	public void setLcsClusterEntryId(long lcsClusterEntryId) {
		_lcsInvitation.setLcsClusterEntryId(lcsClusterEntryId);
	}

	/**
	* Sets the lcs invitation ID of this l c s invitation.
	*
	* @param lcsInvitationId the lcs invitation ID of this l c s invitation
	*/
	@Override
	public void setLcsInvitationId(long lcsInvitationId) {
		_lcsInvitation.setLcsInvitationId(lcsInvitationId);
	}

	/**
	* Sets the lcs project ID of this l c s invitation.
	*
	* @param lcsProjectId the lcs project ID of this l c s invitation
	*/
	@Override
	public void setLcsProjectId(long lcsProjectId) {
		_lcsInvitation.setLcsProjectId(lcsProjectId);
	}

	@Override
	public void setNew(boolean n) {
		_lcsInvitation.setNew(n);
	}

	/**
	* Sets the primary key of this l c s invitation.
	*
	* @param primaryKey the primary key of this l c s invitation
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_lcsInvitation.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_lcsInvitation.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the role of this l c s invitation.
	*
	* @param role the role of this l c s invitation
	*/
	@Override
	public void setRole(int role) {
		_lcsInvitation.setRole(role);
	}

	/**
	* Sets the user ID of this l c s invitation.
	*
	* @param userId the user ID of this l c s invitation
	*/
	@Override
	public void setUserId(long userId) {
		_lcsInvitation.setUserId(userId);
	}

	/**
	* Sets the user uuid of this l c s invitation.
	*
	* @param userUuid the user uuid of this l c s invitation
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_lcsInvitation.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LCSInvitationWrapper)) {
			return false;
		}

		LCSInvitationWrapper lcsInvitationWrapper = (LCSInvitationWrapper)obj;

		if (Objects.equals(_lcsInvitation, lcsInvitationWrapper._lcsInvitation)) {
			return true;
		}

		return false;
	}

	@Override
	public LCSInvitation getWrappedModel() {
		return _lcsInvitation;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _lcsInvitation.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _lcsInvitation.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_lcsInvitation.resetOriginalValues();
	}

	private final LCSInvitation _lcsInvitation;
}