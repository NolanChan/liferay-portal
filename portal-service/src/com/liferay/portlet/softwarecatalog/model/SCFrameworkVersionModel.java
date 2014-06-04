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

package com.liferay.portlet.softwarecatalog.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.GroupedModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the SCFrameworkVersion service. Represents a row in the &quot;SCFrameworkVersion&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portlet.softwarecatalog.model.impl.SCFrameworkVersionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portlet.softwarecatalog.model.impl.SCFrameworkVersionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SCFrameworkVersion
 * @see com.liferay.portlet.softwarecatalog.model.impl.SCFrameworkVersionImpl
 * @see com.liferay.portlet.softwarecatalog.model.impl.SCFrameworkVersionModelImpl
 * @generated
 */
@ProviderType
public interface SCFrameworkVersionModel extends BaseModel<SCFrameworkVersion>,
	GroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a s c framework version model instance should use the {@link SCFrameworkVersion} interface instead.
	 */

	/**
	 * Returns the primary key of this s c framework version.
	 *
	 * @return the primary key of this s c framework version
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this s c framework version.
	 *
	 * @param primaryKey the primary key of this s c framework version
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the framework version ID of this s c framework version.
	 *
	 * @return the framework version ID of this s c framework version
	 */
	public long getFrameworkVersionId();

	/**
	 * Sets the framework version ID of this s c framework version.
	 *
	 * @param frameworkVersionId the framework version ID of this s c framework version
	 */
	public void setFrameworkVersionId(long frameworkVersionId);

	/**
	 * Returns the group ID of this s c framework version.
	 *
	 * @return the group ID of this s c framework version
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this s c framework version.
	 *
	 * @param groupId the group ID of this s c framework version
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this s c framework version.
	 *
	 * @return the company ID of this s c framework version
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this s c framework version.
	 *
	 * @param companyId the company ID of this s c framework version
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this s c framework version.
	 *
	 * @return the user ID of this s c framework version
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this s c framework version.
	 *
	 * @param userId the user ID of this s c framework version
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this s c framework version.
	 *
	 * @return the user uuid of this s c framework version
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this s c framework version.
	 *
	 * @param userUuid the user uuid of this s c framework version
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this s c framework version.
	 *
	 * @return the user name of this s c framework version
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this s c framework version.
	 *
	 * @param userName the user name of this s c framework version
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this s c framework version.
	 *
	 * @return the create date of this s c framework version
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this s c framework version.
	 *
	 * @param createDate the create date of this s c framework version
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this s c framework version.
	 *
	 * @return the modified date of this s c framework version
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this s c framework version.
	 *
	 * @param modifiedDate the modified date of this s c framework version
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the name of this s c framework version.
	 *
	 * @return the name of this s c framework version
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this s c framework version.
	 *
	 * @param name the name of this s c framework version
	 */
	public void setName(String name);

	/**
	 * Returns the url of this s c framework version.
	 *
	 * @return the url of this s c framework version
	 */
	@AutoEscape
	public String getUrl();

	/**
	 * Sets the url of this s c framework version.
	 *
	 * @param url the url of this s c framework version
	 */
	public void setUrl(String url);

	/**
	 * Returns the active of this s c framework version.
	 *
	 * @return the active of this s c framework version
	 */
	public boolean getActive();

	/**
	 * Returns <code>true</code> if this s c framework version is active.
	 *
	 * @return <code>true</code> if this s c framework version is active; <code>false</code> otherwise
	 */
	public boolean isActive();

	/**
	 * Sets whether this s c framework version is active.
	 *
	 * @param active the active of this s c framework version
	 */
	public void setActive(boolean active);

	/**
	 * Returns the priority of this s c framework version.
	 *
	 * @return the priority of this s c framework version
	 */
	public int getPriority();

	/**
	 * Sets the priority of this s c framework version.
	 *
	 * @param priority the priority of this s c framework version
	 */
	public void setPriority(int priority);

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
	public int compareTo(SCFrameworkVersion scFrameworkVersion);

	@Override
	public int hashCode();

	@Override
	public CacheModel<SCFrameworkVersion> toCacheModel();

	@Override
	public SCFrameworkVersion toEscapedModel();

	@Override
	public SCFrameworkVersion toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}