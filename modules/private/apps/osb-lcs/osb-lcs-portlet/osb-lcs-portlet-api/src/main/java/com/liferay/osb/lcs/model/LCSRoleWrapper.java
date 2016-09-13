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
 * This class is a wrapper for {@link LCSRole}.
 * </p>
 *
 * @author Igor Beslic
 * @see LCSRole
 * @generated
 */
@ProviderType
public class LCSRoleWrapper implements LCSRole, ModelWrapper<LCSRole> {
	public LCSRoleWrapper(LCSRole lcsRole) {
		_lcsRole = lcsRole;
	}

	@Override
	public Class<?> getModelClass() {
		return LCSRole.class;
	}

	@Override
	public String getModelClassName() {
		return LCSRole.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lcsRoleId", getLcsRoleId());
		attributes.put("userId", getUserId());
		attributes.put("lcsProjectId", getLcsProjectId());
		attributes.put("lcsClusterEntryId", getLcsClusterEntryId());
		attributes.put("role", getRole());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long lcsRoleId = (Long)attributes.get("lcsRoleId");

		if (lcsRoleId != null) {
			setLcsRoleId(lcsRoleId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long lcsProjectId = (Long)attributes.get("lcsProjectId");

		if (lcsProjectId != null) {
			setLcsProjectId(lcsProjectId);
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
	public LCSRole toEscapedModel() {
		return new LCSRoleWrapper(_lcsRole.toEscapedModel());
	}

	@Override
	public LCSRole toUnescapedModel() {
		return new LCSRoleWrapper(_lcsRole.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _lcsRole.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _lcsRole.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _lcsRole.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _lcsRole.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LCSRole> toCacheModel() {
		return _lcsRole.toCacheModel();
	}

	@Override
	public int compareTo(LCSRole lcsRole) {
		return _lcsRole.compareTo(lcsRole);
	}

	/**
	* Returns the role of this l c s role.
	*
	* @return the role of this l c s role
	*/
	@Override
	public int getRole() {
		return _lcsRole.getRole();
	}

	@Override
	public int hashCode() {
		return _lcsRole.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _lcsRole.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new LCSRoleWrapper((LCSRole)_lcsRole.clone());
	}

	/**
	* Returns the user uuid of this l c s role.
	*
	* @return the user uuid of this l c s role
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _lcsRole.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _lcsRole.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _lcsRole.toXmlString();
	}

	/**
	* Returns the lcs cluster entry ID of this l c s role.
	*
	* @return the lcs cluster entry ID of this l c s role
	*/
	@Override
	public long getLcsClusterEntryId() {
		return _lcsRole.getLcsClusterEntryId();
	}

	/**
	* Returns the lcs project ID of this l c s role.
	*
	* @return the lcs project ID of this l c s role
	*/
	@Override
	public long getLcsProjectId() {
		return _lcsRole.getLcsProjectId();
	}

	/**
	* Returns the lcs role ID of this l c s role.
	*
	* @return the lcs role ID of this l c s role
	*/
	@Override
	public long getLcsRoleId() {
		return _lcsRole.getLcsRoleId();
	}

	/**
	* Returns the primary key of this l c s role.
	*
	* @return the primary key of this l c s role
	*/
	@Override
	public long getPrimaryKey() {
		return _lcsRole.getPrimaryKey();
	}

	/**
	* Returns the user ID of this l c s role.
	*
	* @return the user ID of this l c s role
	*/
	@Override
	public long getUserId() {
		return _lcsRole.getUserId();
	}

	@Override
	public void persist() {
		_lcsRole.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_lcsRole.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_lcsRole.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_lcsRole.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_lcsRole.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the lcs cluster entry ID of this l c s role.
	*
	* @param lcsClusterEntryId the lcs cluster entry ID of this l c s role
	*/
	@Override
	public void setLcsClusterEntryId(long lcsClusterEntryId) {
		_lcsRole.setLcsClusterEntryId(lcsClusterEntryId);
	}

	/**
	* Sets the lcs project ID of this l c s role.
	*
	* @param lcsProjectId the lcs project ID of this l c s role
	*/
	@Override
	public void setLcsProjectId(long lcsProjectId) {
		_lcsRole.setLcsProjectId(lcsProjectId);
	}

	/**
	* Sets the lcs role ID of this l c s role.
	*
	* @param lcsRoleId the lcs role ID of this l c s role
	*/
	@Override
	public void setLcsRoleId(long lcsRoleId) {
		_lcsRole.setLcsRoleId(lcsRoleId);
	}

	@Override
	public void setNew(boolean n) {
		_lcsRole.setNew(n);
	}

	/**
	* Sets the primary key of this l c s role.
	*
	* @param primaryKey the primary key of this l c s role
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_lcsRole.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_lcsRole.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the role of this l c s role.
	*
	* @param role the role of this l c s role
	*/
	@Override
	public void setRole(int role) {
		_lcsRole.setRole(role);
	}

	/**
	* Sets the user ID of this l c s role.
	*
	* @param userId the user ID of this l c s role
	*/
	@Override
	public void setUserId(long userId) {
		_lcsRole.setUserId(userId);
	}

	/**
	* Sets the user uuid of this l c s role.
	*
	* @param userUuid the user uuid of this l c s role
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_lcsRole.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LCSRoleWrapper)) {
			return false;
		}

		LCSRoleWrapper lcsRoleWrapper = (LCSRoleWrapper)obj;

		if (Objects.equals(_lcsRole, lcsRoleWrapper._lcsRole)) {
			return true;
		}

		return false;
	}

	@Override
	public LCSRole getWrappedModel() {
		return _lcsRole;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _lcsRole.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _lcsRole.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_lcsRole.resetOriginalValues();
	}

	private final LCSRole _lcsRole;
}