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

package com.liferay.portlet.expando.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.PartitionedModel;
import com.liferay.portal.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the ExpandoRow service. Represents a row in the &quot;ExpandoRow&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portlet.expando.model.impl.ExpandoRowModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portlet.expando.model.impl.ExpandoRowImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExpandoRow
 * @see com.liferay.portlet.expando.model.impl.ExpandoRowImpl
 * @see com.liferay.portlet.expando.model.impl.ExpandoRowModelImpl
 * @generated
 */
@ProviderType
public interface ExpandoRowModel extends BaseModel<ExpandoRow>, PartitionedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a expando row model instance should use the {@link ExpandoRow} interface instead.
	 */

	/**
	 * Returns the primary key of this expando row.
	 *
	 * @return the primary key of this expando row
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this expando row.
	 *
	 * @param primaryKey the primary key of this expando row
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the row ID of this expando row.
	 *
	 * @return the row ID of this expando row
	 */
	public long getRowId();

	/**
	 * Sets the row ID of this expando row.
	 *
	 * @param rowId the row ID of this expando row
	 */
	public void setRowId(long rowId);

	/**
	 * Returns the company ID of this expando row.
	 *
	 * @return the company ID of this expando row
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this expando row.
	 *
	 * @param companyId the company ID of this expando row
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the modified date of this expando row.
	 *
	 * @return the modified date of this expando row
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this expando row.
	 *
	 * @param modifiedDate the modified date of this expando row
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the table ID of this expando row.
	 *
	 * @return the table ID of this expando row
	 */
	public long getTableId();

	/**
	 * Sets the table ID of this expando row.
	 *
	 * @param tableId the table ID of this expando row
	 */
	public void setTableId(long tableId);

	/**
	 * Returns the class p k of this expando row.
	 *
	 * @return the class p k of this expando row
	 */
	public long getClassPK();

	/**
	 * Sets the class p k of this expando row.
	 *
	 * @param classPK the class p k of this expando row
	 */
	public void setClassPK(long classPK);

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
		com.liferay.portlet.expando.model.ExpandoRow expandoRow);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.portlet.expando.model.ExpandoRow> toCacheModel();

	@Override
	public com.liferay.portlet.expando.model.ExpandoRow toEscapedModel();

	@Override
	public com.liferay.portlet.expando.model.ExpandoRow toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}