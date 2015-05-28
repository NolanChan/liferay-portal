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

package com.liferay.dynamic.data.lists.model;

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
 * The base model interface for the DDLRecord service. Represents a row in the &quot;DDLRecord&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.dynamic.data.lists.model.impl.DDLRecordModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.dynamic.data.lists.model.impl.DDLRecordImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDLRecord
 * @see com.liferay.dynamic.data.lists.model.impl.DDLRecordImpl
 * @see com.liferay.dynamic.data.lists.model.impl.DDLRecordModelImpl
 * @generated
 */
@ProviderType
public interface DDLRecordModel extends BaseModel<DDLRecord>, StagedGroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a d d l record model instance should use the {@link DDLRecord} interface instead.
	 */

	/**
	 * Returns the primary key of this d d l record.
	 *
	 * @return the primary key of this d d l record
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this d d l record.
	 *
	 * @param primaryKey the primary key of this d d l record
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this d d l record.
	 *
	 * @return the uuid of this d d l record
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this d d l record.
	 *
	 * @param uuid the uuid of this d d l record
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the record ID of this d d l record.
	 *
	 * @return the record ID of this d d l record
	 */
	public long getRecordId();

	/**
	 * Sets the record ID of this d d l record.
	 *
	 * @param recordId the record ID of this d d l record
	 */
	public void setRecordId(long recordId);

	/**
	 * Returns the group ID of this d d l record.
	 *
	 * @return the group ID of this d d l record
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this d d l record.
	 *
	 * @param groupId the group ID of this d d l record
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this d d l record.
	 *
	 * @return the company ID of this d d l record
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this d d l record.
	 *
	 * @param companyId the company ID of this d d l record
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this d d l record.
	 *
	 * @return the user ID of this d d l record
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this d d l record.
	 *
	 * @param userId the user ID of this d d l record
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this d d l record.
	 *
	 * @return the user uuid of this d d l record
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this d d l record.
	 *
	 * @param userUuid the user uuid of this d d l record
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this d d l record.
	 *
	 * @return the user name of this d d l record
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this d d l record.
	 *
	 * @param userName the user name of this d d l record
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the version user ID of this d d l record.
	 *
	 * @return the version user ID of this d d l record
	 */
	public long getVersionUserId();

	/**
	 * Sets the version user ID of this d d l record.
	 *
	 * @param versionUserId the version user ID of this d d l record
	 */
	public void setVersionUserId(long versionUserId);

	/**
	 * Returns the version user uuid of this d d l record.
	 *
	 * @return the version user uuid of this d d l record
	 */
	public String getVersionUserUuid();

	/**
	 * Sets the version user uuid of this d d l record.
	 *
	 * @param versionUserUuid the version user uuid of this d d l record
	 */
	public void setVersionUserUuid(String versionUserUuid);

	/**
	 * Returns the version user name of this d d l record.
	 *
	 * @return the version user name of this d d l record
	 */
	@AutoEscape
	public String getVersionUserName();

	/**
	 * Sets the version user name of this d d l record.
	 *
	 * @param versionUserName the version user name of this d d l record
	 */
	public void setVersionUserName(String versionUserName);

	/**
	 * Returns the create date of this d d l record.
	 *
	 * @return the create date of this d d l record
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this d d l record.
	 *
	 * @param createDate the create date of this d d l record
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this d d l record.
	 *
	 * @return the modified date of this d d l record
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this d d l record.
	 *
	 * @param modifiedDate the modified date of this d d l record
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the d d m storage ID of this d d l record.
	 *
	 * @return the d d m storage ID of this d d l record
	 */
	public long getDDMStorageId();

	/**
	 * Sets the d d m storage ID of this d d l record.
	 *
	 * @param DDMStorageId the d d m storage ID of this d d l record
	 */
	public void setDDMStorageId(long DDMStorageId);

	/**
	 * Returns the record set ID of this d d l record.
	 *
	 * @return the record set ID of this d d l record
	 */
	public long getRecordSetId();

	/**
	 * Sets the record set ID of this d d l record.
	 *
	 * @param recordSetId the record set ID of this d d l record
	 */
	public void setRecordSetId(long recordSetId);

	/**
	 * Returns the version of this d d l record.
	 *
	 * @return the version of this d d l record
	 */
	@AutoEscape
	public String getVersion();

	/**
	 * Sets the version of this d d l record.
	 *
	 * @param version the version of this d d l record
	 */
	public void setVersion(String version);

	/**
	 * Returns the display index of this d d l record.
	 *
	 * @return the display index of this d d l record
	 */
	public int getDisplayIndex();

	/**
	 * Sets the display index of this d d l record.
	 *
	 * @param displayIndex the display index of this d d l record
	 */
	public void setDisplayIndex(int displayIndex);

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
	public int compareTo(
		com.liferay.dynamic.data.lists.model.DDLRecord ddlRecord);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.dynamic.data.lists.model.DDLRecord> toCacheModel();

	@Override
	public com.liferay.dynamic.data.lists.model.DDLRecord toEscapedModel();

	@Override
	public com.liferay.dynamic.data.lists.model.DDLRecord toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}