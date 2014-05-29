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

package com.liferay.portal.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.MVCCModel;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.persistence.UserGroupRolePK;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the UserGroupRole service. Represents a row in the &quot;UserGroupRole&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.UserGroupRoleModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.UserGroupRoleImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserGroupRole
 * @see com.liferay.portal.model.impl.UserGroupRoleImpl
 * @see com.liferay.portal.model.impl.UserGroupRoleModelImpl
 * @generated
 */
@ProviderType
public interface UserGroupRoleModel extends BaseModel<UserGroupRole>, MVCCModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a user group role model instance should use the {@link UserGroupRole} interface instead.
	 */

	/**
	 * Returns the primary key of this user group role.
	 *
	 * @return the primary key of this user group role
	 */
	public UserGroupRolePK getPrimaryKey();

	/**
	 * Sets the primary key of this user group role.
	 *
	 * @param primaryKey the primary key of this user group role
	 */
	public void setPrimaryKey(UserGroupRolePK primaryKey);

	/**
	 * Returns the mvcc version of this user group role.
	 *
	 * @return the mvcc version of this user group role
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this user group role.
	 *
	 * @param mvccVersion the mvcc version of this user group role
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the user ID of this user group role.
	 *
	 * @return the user ID of this user group role
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this user group role.
	 *
	 * @param userId the user ID of this user group role
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this user group role.
	 *
	 * @return the user uuid of this user group role
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this user group role.
	 *
	 * @param userUuid the user uuid of this user group role
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the group ID of this user group role.
	 *
	 * @return the group ID of this user group role
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this user group role.
	 *
	 * @param groupId the group ID of this user group role
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the role ID of this user group role.
	 *
	 * @return the role ID of this user group role
	 */
	public long getRoleId();

	/**
	 * Sets the role ID of this user group role.
	 *
	 * @param roleId the role ID of this user group role
	 */
	public void setRoleId(long roleId);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(UserGroupRole userGroupRole);

	@Override
	public int hashCode();

	@Override
	public CacheModel<UserGroupRole> toCacheModel();

	@Override
	public UserGroupRole toEscapedModel();

	@Override
	public UserGroupRole toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}