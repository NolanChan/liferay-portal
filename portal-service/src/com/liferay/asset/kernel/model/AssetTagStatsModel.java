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

package com.liferay.asset.kernel.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.TypedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

/**
 * The base model interface for the AssetTagStats service. Represents a row in the &quot;AssetTagStats&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portlet.asset.model.impl.AssetTagStatsModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portlet.asset.model.impl.AssetTagStatsImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetTagStats
 * @see com.liferay.portlet.asset.model.impl.AssetTagStatsImpl
 * @see com.liferay.portlet.asset.model.impl.AssetTagStatsModelImpl
 * @generated
 */
@ProviderType
public interface AssetTagStatsModel extends BaseModel<AssetTagStats>,
	ShardedModel, TypedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a asset tag stats model instance should use the {@link AssetTagStats} interface instead.
	 */

	/**
	 * Returns the primary key of this asset tag stats.
	 *
	 * @return the primary key of this asset tag stats
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this asset tag stats.
	 *
	 * @param primaryKey the primary key of this asset tag stats
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the tag stats ID of this asset tag stats.
	 *
	 * @return the tag stats ID of this asset tag stats
	 */
	public long getTagStatsId();

	/**
	 * Sets the tag stats ID of this asset tag stats.
	 *
	 * @param tagStatsId the tag stats ID of this asset tag stats
	 */
	public void setTagStatsId(long tagStatsId);

	/**
	 * Returns the company ID of this asset tag stats.
	 *
	 * @return the company ID of this asset tag stats
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this asset tag stats.
	 *
	 * @param companyId the company ID of this asset tag stats
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the tag ID of this asset tag stats.
	 *
	 * @return the tag ID of this asset tag stats
	 */
	public long getTagId();

	/**
	 * Sets the tag ID of this asset tag stats.
	 *
	 * @param tagId the tag ID of this asset tag stats
	 */
	public void setTagId(long tagId);

	/**
	 * Returns the fully qualified class name of this asset tag stats.
	 *
	 * @return the fully qualified class name of this asset tag stats
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this asset tag stats.
	 *
	 * @return the class name ID of this asset tag stats
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this asset tag stats.
	 *
	 * @param classNameId the class name ID of this asset tag stats
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the asset count of this asset tag stats.
	 *
	 * @return the asset count of this asset tag stats
	 */
	public int getAssetCount();

	/**
	 * Sets the asset count of this asset tag stats.
	 *
	 * @param assetCount the asset count of this asset tag stats
	 */
	public void setAssetCount(int assetCount);

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
		com.liferay.asset.kernel.model.AssetTagStats assetTagStats);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.asset.kernel.model.AssetTagStats> toCacheModel();

	@Override
	public com.liferay.asset.kernel.model.AssetTagStats toEscapedModel();

	@Override
	public com.liferay.asset.kernel.model.AssetTagStats toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}