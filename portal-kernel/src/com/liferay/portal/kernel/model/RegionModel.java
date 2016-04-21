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

package com.liferay.portal.kernel.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

/**
 * The base model interface for the Region service. Represents a row in the &quot;Region&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.RegionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.RegionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Region
 * @see com.liferay.portal.model.impl.RegionImpl
 * @see com.liferay.portal.model.impl.RegionModelImpl
 * @generated
 */
@ProviderType
public interface RegionModel extends BaseModel<Region>, MVCCModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a region model instance should use the {@link Region} interface instead.
	 */

	/**
	 * Returns the primary key of this region.
	 *
	 * @return the primary key of this region
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this region.
	 *
	 * @param primaryKey the primary key of this region
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this region.
	 *
	 * @return the mvcc version of this region
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this region.
	 *
	 * @param mvccVersion the mvcc version of this region
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the region ID of this region.
	 *
	 * @return the region ID of this region
	 */
	public long getRegionId();

	/**
	 * Sets the region ID of this region.
	 *
	 * @param regionId the region ID of this region
	 */
	public void setRegionId(long regionId);

	/**
	 * Returns the country ID of this region.
	 *
	 * @return the country ID of this region
	 */
	public long getCountryId();

	/**
	 * Sets the country ID of this region.
	 *
	 * @param countryId the country ID of this region
	 */
	public void setCountryId(long countryId);

	/**
	 * Returns the region code of this region.
	 *
	 * @return the region code of this region
	 */
	@AutoEscape
	public String getRegionCode();

	/**
	 * Sets the region code of this region.
	 *
	 * @param regionCode the region code of this region
	 */
	public void setRegionCode(String regionCode);

	/**
	 * Returns the name of this region.
	 *
	 * @return the name of this region
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this region.
	 *
	 * @param name the name of this region
	 */
	public void setName(String name);

	/**
	 * Returns the active of this region.
	 *
	 * @return the active of this region
	 */
	public boolean getActive();

	/**
	 * Returns <code>true</code> if this region is active.
	 *
	 * @return <code>true</code> if this region is active; <code>false</code> otherwise
	 */
	public boolean isActive();

	/**
	 * Sets whether this region is active.
	 *
	 * @param active the active of this region
	 */
	public void setActive(boolean active);

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
	public int compareTo(Region region);

	@Override
	public int hashCode();

	@Override
	public CacheModel<Region> toCacheModel();

	@Override
	public Region toEscapedModel();

	@Override
	public Region toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}