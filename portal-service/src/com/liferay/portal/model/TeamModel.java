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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.GroupedModel;
import com.liferay.portal.model.MVCCModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the Team service. Represents a row in the &quot;Team&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.TeamModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.TeamImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Team
 * @see com.liferay.portal.model.impl.TeamImpl
 * @see com.liferay.portal.model.impl.TeamModelImpl
 * @generated
 */
@ProviderType
public interface TeamModel extends BaseModel<Team>, GroupedModel, MVCCModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a team model instance should use the {@link Team} interface instead.
	 */

	/**
	 * Returns the primary key of this team.
	 *
	 * @return the primary key of this team
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this team.
	 *
	 * @param primaryKey the primary key of this team
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this team.
	 *
	 * @return the mvcc version of this team
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this team.
	 *
	 * @param mvccVersion the mvcc version of this team
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the team ID of this team.
	 *
	 * @return the team ID of this team
	 */
	public long getTeamId();

	/**
	 * Sets the team ID of this team.
	 *
	 * @param teamId the team ID of this team
	 */
	public void setTeamId(long teamId);

	/**
	 * Returns the company ID of this team.
	 *
	 * @return the company ID of this team
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this team.
	 *
	 * @param companyId the company ID of this team
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this team.
	 *
	 * @return the user ID of this team
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this team.
	 *
	 * @param userId the user ID of this team
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this team.
	 *
	 * @return the user uuid of this team
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this team.
	 *
	 * @param userUuid the user uuid of this team
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this team.
	 *
	 * @return the user name of this team
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this team.
	 *
	 * @param userName the user name of this team
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this team.
	 *
	 * @return the create date of this team
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this team.
	 *
	 * @param createDate the create date of this team
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this team.
	 *
	 * @return the modified date of this team
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this team.
	 *
	 * @param modifiedDate the modified date of this team
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the group ID of this team.
	 *
	 * @return the group ID of this team
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this team.
	 *
	 * @param groupId the group ID of this team
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the name of this team.
	 *
	 * @return the name of this team
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this team.
	 *
	 * @param name the name of this team
	 */
	public void setName(String name);

	/**
	 * Returns the description of this team.
	 *
	 * @return the description of this team
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this team.
	 *
	 * @param description the description of this team
	 */
	public void setDescription(String description);

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
	public int compareTo(com.liferay.portal.model.Team team);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.portal.model.Team> toCacheModel();

	@Override
	public com.liferay.portal.model.Team toEscapedModel();

	@Override
	public com.liferay.portal.model.Team toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}