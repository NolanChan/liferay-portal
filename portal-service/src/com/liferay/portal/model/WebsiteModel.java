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
 * The base model interface for the Website service. Represents a row in the &quot;Website&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.WebsiteModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.WebsiteImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Website
 * @see com.liferay.portal.model.impl.WebsiteImpl
 * @see com.liferay.portal.model.impl.WebsiteModelImpl
 * @generated
 */
@ProviderType
public interface WebsiteModel extends AttachedModel, BaseModel<Website>,
	MVCCModel, StagedAuditedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a website model instance should use the {@link Website} interface instead.
	 */

	/**
	 * Returns the primary key of this website.
	 *
	 * @return the primary key of this website
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this website.
	 *
	 * @param primaryKey the primary key of this website
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this website.
	 *
	 * @return the mvcc version of this website
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this website.
	 *
	 * @param mvccVersion the mvcc version of this website
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the uuid of this website.
	 *
	 * @return the uuid of this website
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this website.
	 *
	 * @param uuid the uuid of this website
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the website ID of this website.
	 *
	 * @return the website ID of this website
	 */
	public long getWebsiteId();

	/**
	 * Sets the website ID of this website.
	 *
	 * @param websiteId the website ID of this website
	 */
	public void setWebsiteId(long websiteId);

	/**
	 * Returns the company ID of this website.
	 *
	 * @return the company ID of this website
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this website.
	 *
	 * @param companyId the company ID of this website
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this website.
	 *
	 * @return the user ID of this website
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this website.
	 *
	 * @param userId the user ID of this website
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this website.
	 *
	 * @return the user uuid of this website
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this website.
	 *
	 * @param userUuid the user uuid of this website
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this website.
	 *
	 * @return the user name of this website
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this website.
	 *
	 * @param userName the user name of this website
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this website.
	 *
	 * @return the create date of this website
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this website.
	 *
	 * @param createDate the create date of this website
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this website.
	 *
	 * @return the modified date of this website
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this website.
	 *
	 * @param modifiedDate the modified date of this website
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the fully qualified class name of this website.
	 *
	 * @return the fully qualified class name of this website
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this website.
	 *
	 * @return the class name ID of this website
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this website.
	 *
	 * @param classNameId the class name ID of this website
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class p k of this website.
	 *
	 * @return the class p k of this website
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class p k of this website.
	 *
	 * @param classPK the class p k of this website
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the url of this website.
	 *
	 * @return the url of this website
	 */
	@AutoEscape
	public String getUrl();

	/**
	 * Sets the url of this website.
	 *
	 * @param url the url of this website
	 */
	public void setUrl(String url);

	/**
	 * Returns the type ID of this website.
	 *
	 * @return the type ID of this website
	 */
	public long getTypeId();

	/**
	 * Sets the type ID of this website.
	 *
	 * @param typeId the type ID of this website
	 */
	public void setTypeId(long typeId);

	/**
	 * Returns the primary of this website.
	 *
	 * @return the primary of this website
	 */
	public boolean getPrimary();

	/**
	 * Returns <code>true</code> if this website is primary.
	 *
	 * @return <code>true</code> if this website is primary; <code>false</code> otherwise
	 */
	public boolean isPrimary();

	/**
	 * Sets whether this website is primary.
	 *
	 * @param primary the primary of this website
	 */
	public void setPrimary(boolean primary);

	/**
	 * Returns the last publish date of this website.
	 *
	 * @return the last publish date of this website
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this website.
	 *
	 * @param lastPublishDate the last publish date of this website
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

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
	public int compareTo(com.liferay.portal.model.Website website);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.portal.model.Website> toCacheModel();

	@Override
	public com.liferay.portal.model.Website toEscapedModel();

	@Override
	public com.liferay.portal.model.Website toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}