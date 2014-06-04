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

package com.liferay.portlet.messageboards.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.StagedGroupedModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the MBThreadFlag service. Represents a row in the &quot;MBThreadFlag&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portlet.messageboards.model.impl.MBThreadFlagModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portlet.messageboards.model.impl.MBThreadFlagImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MBThreadFlag
 * @see com.liferay.portlet.messageboards.model.impl.MBThreadFlagImpl
 * @see com.liferay.portlet.messageboards.model.impl.MBThreadFlagModelImpl
 * @generated
 */
@ProviderType
public interface MBThreadFlagModel extends BaseModel<MBThreadFlag>,
	StagedGroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a message boards thread flag model instance should use the {@link MBThreadFlag} interface instead.
	 */

	/**
	 * Returns the primary key of this message boards thread flag.
	 *
	 * @return the primary key of this message boards thread flag
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this message boards thread flag.
	 *
	 * @param primaryKey the primary key of this message boards thread flag
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this message boards thread flag.
	 *
	 * @return the uuid of this message boards thread flag
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this message boards thread flag.
	 *
	 * @param uuid the uuid of this message boards thread flag
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the thread flag ID of this message boards thread flag.
	 *
	 * @return the thread flag ID of this message boards thread flag
	 */
	public long getThreadFlagId();

	/**
	 * Sets the thread flag ID of this message boards thread flag.
	 *
	 * @param threadFlagId the thread flag ID of this message boards thread flag
	 */
	public void setThreadFlagId(long threadFlagId);

	/**
	 * Returns the group ID of this message boards thread flag.
	 *
	 * @return the group ID of this message boards thread flag
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this message boards thread flag.
	 *
	 * @param groupId the group ID of this message boards thread flag
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this message boards thread flag.
	 *
	 * @return the company ID of this message boards thread flag
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this message boards thread flag.
	 *
	 * @param companyId the company ID of this message boards thread flag
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this message boards thread flag.
	 *
	 * @return the user ID of this message boards thread flag
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this message boards thread flag.
	 *
	 * @param userId the user ID of this message boards thread flag
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this message boards thread flag.
	 *
	 * @return the user uuid of this message boards thread flag
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this message boards thread flag.
	 *
	 * @param userUuid the user uuid of this message boards thread flag
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this message boards thread flag.
	 *
	 * @return the user name of this message boards thread flag
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this message boards thread flag.
	 *
	 * @param userName the user name of this message boards thread flag
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this message boards thread flag.
	 *
	 * @return the create date of this message boards thread flag
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this message boards thread flag.
	 *
	 * @param createDate the create date of this message boards thread flag
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this message boards thread flag.
	 *
	 * @return the modified date of this message boards thread flag
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this message boards thread flag.
	 *
	 * @param modifiedDate the modified date of this message boards thread flag
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the thread ID of this message boards thread flag.
	 *
	 * @return the thread ID of this message boards thread flag
	 */
	public long getThreadId();

	/**
	 * Sets the thread ID of this message boards thread flag.
	 *
	 * @param threadId the thread ID of this message boards thread flag
	 */
	public void setThreadId(long threadId);

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
	public int compareTo(MBThreadFlag mbThreadFlag);

	@Override
	public int hashCode();

	@Override
	public CacheModel<MBThreadFlag> toCacheModel();

	@Override
	public MBThreadFlag toEscapedModel();

	@Override
	public MBThreadFlag toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}