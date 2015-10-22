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
 * The base model interface for the WebDAVProps service. Represents a row in the &quot;WebDAVProps&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.WebDAVPropsModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.WebDAVPropsImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WebDAVProps
 * @see com.liferay.portal.model.impl.WebDAVPropsImpl
 * @see com.liferay.portal.model.impl.WebDAVPropsModelImpl
 * @generated
 */
@ProviderType
public interface WebDAVPropsModel extends AttachedModel, BaseModel<WebDAVProps>,
	MVCCModel, PartitionedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a web d a v props model instance should use the {@link WebDAVProps} interface instead.
	 */

	/**
	 * Returns the primary key of this web d a v props.
	 *
	 * @return the primary key of this web d a v props
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this web d a v props.
	 *
	 * @param primaryKey the primary key of this web d a v props
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this web d a v props.
	 *
	 * @return the mvcc version of this web d a v props
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this web d a v props.
	 *
	 * @param mvccVersion the mvcc version of this web d a v props
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the web dav props ID of this web d a v props.
	 *
	 * @return the web dav props ID of this web d a v props
	 */
	public long getWebDavPropsId();

	/**
	 * Sets the web dav props ID of this web d a v props.
	 *
	 * @param webDavPropsId the web dav props ID of this web d a v props
	 */
	public void setWebDavPropsId(long webDavPropsId);

	/**
	 * Returns the company ID of this web d a v props.
	 *
	 * @return the company ID of this web d a v props
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this web d a v props.
	 *
	 * @param companyId the company ID of this web d a v props
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the create date of this web d a v props.
	 *
	 * @return the create date of this web d a v props
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this web d a v props.
	 *
	 * @param createDate the create date of this web d a v props
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this web d a v props.
	 *
	 * @return the modified date of this web d a v props
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this web d a v props.
	 *
	 * @param modifiedDate the modified date of this web d a v props
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the fully qualified class name of this web d a v props.
	 *
	 * @return the fully qualified class name of this web d a v props
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this web d a v props.
	 *
	 * @return the class name ID of this web d a v props
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this web d a v props.
	 *
	 * @param classNameId the class name ID of this web d a v props
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class p k of this web d a v props.
	 *
	 * @return the class p k of this web d a v props
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class p k of this web d a v props.
	 *
	 * @param classPK the class p k of this web d a v props
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the props of this web d a v props.
	 *
	 * @return the props of this web d a v props
	 */
	@AutoEscape
	public String getProps();

	/**
	 * Sets the props of this web d a v props.
	 *
	 * @param props the props of this web d a v props
	 */
	public void setProps(String props);

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
	public int compareTo(com.liferay.portal.model.WebDAVProps webDAVProps);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.portal.model.WebDAVProps> toCacheModel();

	@Override
	public com.liferay.portal.model.WebDAVProps toEscapedModel();

	@Override
	public com.liferay.portal.model.WebDAVProps toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}