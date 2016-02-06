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

package com.liferay.social.networking.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the MeetupsEntry service. Represents a row in the &quot;MeetupsEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.social.networking.model.impl.MeetupsEntryModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.social.networking.model.impl.MeetupsEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MeetupsEntry
 * @see com.liferay.social.networking.model.impl.MeetupsEntryImpl
 * @see com.liferay.social.networking.model.impl.MeetupsEntryModelImpl
 * @generated
 */
@ProviderType
public interface MeetupsEntryModel extends AuditedModel, BaseModel<MeetupsEntry>,
	ShardedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a meetups entry model instance should use the {@link MeetupsEntry} interface instead.
	 */

	/**
	 * Returns the primary key of this meetups entry.
	 *
	 * @return the primary key of this meetups entry
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this meetups entry.
	 *
	 * @param primaryKey the primary key of this meetups entry
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the meetups entry ID of this meetups entry.
	 *
	 * @return the meetups entry ID of this meetups entry
	 */
	public long getMeetupsEntryId();

	/**
	 * Sets the meetups entry ID of this meetups entry.
	 *
	 * @param meetupsEntryId the meetups entry ID of this meetups entry
	 */
	public void setMeetupsEntryId(long meetupsEntryId);

	/**
	 * Returns the company ID of this meetups entry.
	 *
	 * @return the company ID of this meetups entry
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this meetups entry.
	 *
	 * @param companyId the company ID of this meetups entry
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this meetups entry.
	 *
	 * @return the user ID of this meetups entry
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this meetups entry.
	 *
	 * @param userId the user ID of this meetups entry
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this meetups entry.
	 *
	 * @return the user uuid of this meetups entry
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this meetups entry.
	 *
	 * @param userUuid the user uuid of this meetups entry
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this meetups entry.
	 *
	 * @return the user name of this meetups entry
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this meetups entry.
	 *
	 * @param userName the user name of this meetups entry
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this meetups entry.
	 *
	 * @return the create date of this meetups entry
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this meetups entry.
	 *
	 * @param createDate the create date of this meetups entry
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this meetups entry.
	 *
	 * @return the modified date of this meetups entry
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this meetups entry.
	 *
	 * @param modifiedDate the modified date of this meetups entry
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the title of this meetups entry.
	 *
	 * @return the title of this meetups entry
	 */
	@AutoEscape
	public String getTitle();

	/**
	 * Sets the title of this meetups entry.
	 *
	 * @param title the title of this meetups entry
	 */
	public void setTitle(String title);

	/**
	 * Returns the description of this meetups entry.
	 *
	 * @return the description of this meetups entry
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this meetups entry.
	 *
	 * @param description the description of this meetups entry
	 */
	public void setDescription(String description);

	/**
	 * Returns the start date of this meetups entry.
	 *
	 * @return the start date of this meetups entry
	 */
	public Date getStartDate();

	/**
	 * Sets the start date of this meetups entry.
	 *
	 * @param startDate the start date of this meetups entry
	 */
	public void setStartDate(Date startDate);

	/**
	 * Returns the end date of this meetups entry.
	 *
	 * @return the end date of this meetups entry
	 */
	public Date getEndDate();

	/**
	 * Sets the end date of this meetups entry.
	 *
	 * @param endDate the end date of this meetups entry
	 */
	public void setEndDate(Date endDate);

	/**
	 * Returns the total attendees of this meetups entry.
	 *
	 * @return the total attendees of this meetups entry
	 */
	public int getTotalAttendees();

	/**
	 * Sets the total attendees of this meetups entry.
	 *
	 * @param totalAttendees the total attendees of this meetups entry
	 */
	public void setTotalAttendees(int totalAttendees);

	/**
	 * Returns the max attendees of this meetups entry.
	 *
	 * @return the max attendees of this meetups entry
	 */
	public int getMaxAttendees();

	/**
	 * Sets the max attendees of this meetups entry.
	 *
	 * @param maxAttendees the max attendees of this meetups entry
	 */
	public void setMaxAttendees(int maxAttendees);

	/**
	 * Returns the price of this meetups entry.
	 *
	 * @return the price of this meetups entry
	 */
	public double getPrice();

	/**
	 * Sets the price of this meetups entry.
	 *
	 * @param price the price of this meetups entry
	 */
	public void setPrice(double price);

	/**
	 * Returns the thumbnail ID of this meetups entry.
	 *
	 * @return the thumbnail ID of this meetups entry
	 */
	public long getThumbnailId();

	/**
	 * Sets the thumbnail ID of this meetups entry.
	 *
	 * @param thumbnailId the thumbnail ID of this meetups entry
	 */
	public void setThumbnailId(long thumbnailId);

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
		com.liferay.social.networking.model.MeetupsEntry meetupsEntry);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.social.networking.model.MeetupsEntry> toCacheModel();

	@Override
	public com.liferay.social.networking.model.MeetupsEntry toEscapedModel();

	@Override
	public com.liferay.social.networking.model.MeetupsEntry toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}