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

import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the SCProductScreenshot service. Represents a row in the &quot;SCProductScreenshot&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portlet.softwarecatalog.model.impl.SCProductScreenshotModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portlet.softwarecatalog.model.impl.SCProductScreenshotImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SCProductScreenshot
 * @see com.liferay.portlet.softwarecatalog.model.impl.SCProductScreenshotImpl
 * @see com.liferay.portlet.softwarecatalog.model.impl.SCProductScreenshotModelImpl
 * @generated
 */
@ProviderType
public interface SCProductScreenshotModel extends BaseModel<SCProductScreenshot> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a s c product screenshot model instance should use the {@link SCProductScreenshot} interface instead.
	 */

	/**
	 * Returns the primary key of this s c product screenshot.
	 *
	 * @return the primary key of this s c product screenshot
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this s c product screenshot.
	 *
	 * @param primaryKey the primary key of this s c product screenshot
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the product screenshot ID of this s c product screenshot.
	 *
	 * @return the product screenshot ID of this s c product screenshot
	 */
	public long getProductScreenshotId();

	/**
	 * Sets the product screenshot ID of this s c product screenshot.
	 *
	 * @param productScreenshotId the product screenshot ID of this s c product screenshot
	 */
	public void setProductScreenshotId(long productScreenshotId);

	/**
	 * Returns the company ID of this s c product screenshot.
	 *
	 * @return the company ID of this s c product screenshot
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this s c product screenshot.
	 *
	 * @param companyId the company ID of this s c product screenshot
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the group ID of this s c product screenshot.
	 *
	 * @return the group ID of this s c product screenshot
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this s c product screenshot.
	 *
	 * @param groupId the group ID of this s c product screenshot
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the product entry ID of this s c product screenshot.
	 *
	 * @return the product entry ID of this s c product screenshot
	 */
	public long getProductEntryId();

	/**
	 * Sets the product entry ID of this s c product screenshot.
	 *
	 * @param productEntryId the product entry ID of this s c product screenshot
	 */
	public void setProductEntryId(long productEntryId);

	/**
	 * Returns the thumbnail ID of this s c product screenshot.
	 *
	 * @return the thumbnail ID of this s c product screenshot
	 */
	public long getThumbnailId();

	/**
	 * Sets the thumbnail ID of this s c product screenshot.
	 *
	 * @param thumbnailId the thumbnail ID of this s c product screenshot
	 */
	public void setThumbnailId(long thumbnailId);

	/**
	 * Returns the full image ID of this s c product screenshot.
	 *
	 * @return the full image ID of this s c product screenshot
	 */
	public long getFullImageId();

	/**
	 * Sets the full image ID of this s c product screenshot.
	 *
	 * @param fullImageId the full image ID of this s c product screenshot
	 */
	public void setFullImageId(long fullImageId);

	/**
	 * Returns the priority of this s c product screenshot.
	 *
	 * @return the priority of this s c product screenshot
	 */
	public int getPriority();

	/**
	 * Sets the priority of this s c product screenshot.
	 *
	 * @param priority the priority of this s c product screenshot
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
	public int compareTo(
		com.liferay.portlet.softwarecatalog.model.SCProductScreenshot scProductScreenshot);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.portlet.softwarecatalog.model.SCProductScreenshot> toCacheModel();

	@Override
	public com.liferay.portlet.softwarecatalog.model.SCProductScreenshot toEscapedModel();

	@Override
	public com.liferay.portlet.softwarecatalog.model.SCProductScreenshot toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}