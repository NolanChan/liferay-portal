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
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the Repository service. Represents a row in the &quot;Repository&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.RepositoryModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.RepositoryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Repository
 * @see com.liferay.portal.model.impl.RepositoryImpl
 * @see com.liferay.portal.model.impl.RepositoryModelImpl
 * @generated
 */
@ProviderType
public interface RepositoryModel extends BaseModel<Repository>, MVCCModel,
	StagedGroupedModel, TypedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a repository model instance should use the {@link Repository} interface instead.
	 */

	/**
	 * Returns the primary key of this repository.
	 *
	 * @return the primary key of this repository
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this repository.
	 *
	 * @param primaryKey the primary key of this repository
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this repository.
	 *
	 * @return the mvcc version of this repository
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this repository.
	 *
	 * @param mvccVersion the mvcc version of this repository
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this repository.
	 *
	 * @return the uuid of this repository
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this repository.
	 *
	 * @param uuid the uuid of this repository
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the repository ID of this repository.
	 *
	 * @return the repository ID of this repository
	 */
	public long getRepositoryId();

	/**
	 * Sets the repository ID of this repository.
	 *
	 * @param repositoryId the repository ID of this repository
	 */
	public void setRepositoryId(long repositoryId);

	/**
	 * Returns the group ID of this repository.
	 *
	 * @return the group ID of this repository
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this repository.
	 *
	 * @param groupId the group ID of this repository
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this repository.
	 *
	 * @return the company ID of this repository
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this repository.
	 *
	 * @param companyId the company ID of this repository
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this repository.
	 *
	 * @return the user ID of this repository
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this repository.
	 *
	 * @param userId the user ID of this repository
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this repository.
	 *
	 * @return the user uuid of this repository
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this repository.
	 *
	 * @param userUuid the user uuid of this repository
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this repository.
	 *
	 * @return the user name of this repository
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this repository.
	 *
	 * @param userName the user name of this repository
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this repository.
	 *
	 * @return the create date of this repository
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this repository.
	 *
	 * @param createDate the create date of this repository
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this repository.
	 *
	 * @return the modified date of this repository
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this repository.
	 *
	 * @param modifiedDate the modified date of this repository
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the fully qualified class name of this repository.
	 *
	 * @return the fully qualified class name of this repository
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this repository.
	 *
	 * @return the class name ID of this repository
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this repository.
	 *
	 * @param classNameId the class name ID of this repository
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the name of this repository.
	 *
	 * @return the name of this repository
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this repository.
	 *
	 * @param name the name of this repository
	 */
	public void setName(String name);

	/**
	 * Returns the description of this repository.
	 *
	 * @return the description of this repository
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this repository.
	 *
	 * @param description the description of this repository
	 */
	public void setDescription(String description);

	/**
	 * Returns the portlet ID of this repository.
	 *
	 * @return the portlet ID of this repository
	 */
	@AutoEscape
	public String getPortletId();

	/**
	 * Sets the portlet ID of this repository.
	 *
	 * @param portletId the portlet ID of this repository
	 */
	public void setPortletId(String portletId);

	/**
	 * Returns the type settings of this repository.
	 *
	 * @return the type settings of this repository
	 */
	@AutoEscape
	public String getTypeSettings();

	/**
	 * Sets the type settings of this repository.
	 *
	 * @param typeSettings the type settings of this repository
	 */
	public void setTypeSettings(String typeSettings);

	/**
	 * Returns the dl folder ID of this repository.
	 *
	 * @return the dl folder ID of this repository
	 */
	public long getDlFolderId();

	/**
	 * Sets the dl folder ID of this repository.
	 *
	 * @param dlFolderId the dl folder ID of this repository
	 */
	public void setDlFolderId(long dlFolderId);

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
	public int compareTo(Repository repository);

	@Override
	public int hashCode();

	@Override
	public CacheModel<Repository> toCacheModel();

	@Override
	public Repository toEscapedModel();

	@Override
	public Repository toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}