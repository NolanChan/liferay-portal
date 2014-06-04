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

/**
 * The base model interface for the VirtualHost service. Represents a row in the &quot;VirtualHost&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.VirtualHostModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.VirtualHostImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see VirtualHost
 * @see com.liferay.portal.model.impl.VirtualHostImpl
 * @see com.liferay.portal.model.impl.VirtualHostModelImpl
 * @generated
 */
@ProviderType
public interface VirtualHostModel extends BaseModel<VirtualHost>, MVCCModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a virtual host model instance should use the {@link VirtualHost} interface instead.
	 */

	/**
	 * Returns the primary key of this virtual host.
	 *
	 * @return the primary key of this virtual host
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this virtual host.
	 *
	 * @param primaryKey the primary key of this virtual host
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this virtual host.
	 *
	 * @return the mvcc version of this virtual host
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this virtual host.
	 *
	 * @param mvccVersion the mvcc version of this virtual host
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the virtual host ID of this virtual host.
	 *
	 * @return the virtual host ID of this virtual host
	 */
	public long getVirtualHostId();

	/**
	 * Sets the virtual host ID of this virtual host.
	 *
	 * @param virtualHostId the virtual host ID of this virtual host
	 */
	public void setVirtualHostId(long virtualHostId);

	/**
	 * Returns the company ID of this virtual host.
	 *
	 * @return the company ID of this virtual host
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this virtual host.
	 *
	 * @param companyId the company ID of this virtual host
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the layout set ID of this virtual host.
	 *
	 * @return the layout set ID of this virtual host
	 */
	public long getLayoutSetId();

	/**
	 * Sets the layout set ID of this virtual host.
	 *
	 * @param layoutSetId the layout set ID of this virtual host
	 */
	public void setLayoutSetId(long layoutSetId);

	/**
	 * Returns the hostname of this virtual host.
	 *
	 * @return the hostname of this virtual host
	 */
	@AutoEscape
	public String getHostname();

	/**
	 * Sets the hostname of this virtual host.
	 *
	 * @param hostname the hostname of this virtual host
	 */
	public void setHostname(String hostname);

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
	public int compareTo(VirtualHost virtualHost);

	@Override
	public int hashCode();

	@Override
	public CacheModel<VirtualHost> toCacheModel();

	@Override
	public VirtualHost toEscapedModel();

	@Override
	public VirtualHost toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}