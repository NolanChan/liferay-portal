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

package com.liferay.dynamic.data.mapping.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

/**
 * The base model interface for the DDMStorageLink service. Represents a row in the &quot;DDMStorageLink&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.dynamic.data.mapping.model.impl.DDMStorageLinkModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.dynamic.data.mapping.model.impl.DDMStorageLinkImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMStorageLink
 * @see com.liferay.dynamic.data.mapping.model.impl.DDMStorageLinkImpl
 * @see com.liferay.dynamic.data.mapping.model.impl.DDMStorageLinkModelImpl
 * @generated
 */
@ProviderType
public interface DDMStorageLinkModel extends AttachedModel,
	BaseModel<DDMStorageLink>, ShardedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a d d m storage link model instance should use the {@link DDMStorageLink} interface instead.
	 */

	/**
	 * Returns the primary key of this d d m storage link.
	 *
	 * @return the primary key of this d d m storage link
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this d d m storage link.
	 *
	 * @param primaryKey the primary key of this d d m storage link
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this d d m storage link.
	 *
	 * @return the uuid of this d d m storage link
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this d d m storage link.
	 *
	 * @param uuid the uuid of this d d m storage link
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the storage link ID of this d d m storage link.
	 *
	 * @return the storage link ID of this d d m storage link
	 */
	public long getStorageLinkId();

	/**
	 * Sets the storage link ID of this d d m storage link.
	 *
	 * @param storageLinkId the storage link ID of this d d m storage link
	 */
	public void setStorageLinkId(long storageLinkId);

	/**
	 * Returns the company ID of this d d m storage link.
	 *
	 * @return the company ID of this d d m storage link
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this d d m storage link.
	 *
	 * @param companyId the company ID of this d d m storage link
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the fully qualified class name of this d d m storage link.
	 *
	 * @return the fully qualified class name of this d d m storage link
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this d d m storage link.
	 *
	 * @return the class name ID of this d d m storage link
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this d d m storage link.
	 *
	 * @param classNameId the class name ID of this d d m storage link
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class p k of this d d m storage link.
	 *
	 * @return the class p k of this d d m storage link
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class p k of this d d m storage link.
	 *
	 * @param classPK the class p k of this d d m storage link
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the structure ID of this d d m storage link.
	 *
	 * @return the structure ID of this d d m storage link
	 */
	public long getStructureId();

	/**
	 * Sets the structure ID of this d d m storage link.
	 *
	 * @param structureId the structure ID of this d d m storage link
	 */
	public void setStructureId(long structureId);

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
		com.liferay.dynamic.data.mapping.model.DDMStorageLink ddmStorageLink);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.dynamic.data.mapping.model.DDMStorageLink> toCacheModel();

	@Override
	public com.liferay.dynamic.data.mapping.model.DDMStorageLink toEscapedModel();

	@Override
	public com.liferay.dynamic.data.mapping.model.DDMStorageLink toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}