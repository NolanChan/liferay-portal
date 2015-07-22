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

package com.liferay.calendar.model;

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
 * The base model interface for the CalendarNotificationTemplate service. Represents a row in the &quot;CalendarNotificationTemplate&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.calendar.model.impl.CalendarNotificationTemplateModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.calendar.model.impl.CalendarNotificationTemplateImpl}.
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarNotificationTemplate
 * @see com.liferay.calendar.model.impl.CalendarNotificationTemplateImpl
 * @see com.liferay.calendar.model.impl.CalendarNotificationTemplateModelImpl
 * @generated
 */
@ProviderType
public interface CalendarNotificationTemplateModel extends BaseModel<CalendarNotificationTemplate>,
	StagedGroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a calendar notification template model instance should use the {@link CalendarNotificationTemplate} interface instead.
	 */

	/**
	 * Returns the primary key of this calendar notification template.
	 *
	 * @return the primary key of this calendar notification template
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this calendar notification template.
	 *
	 * @param primaryKey the primary key of this calendar notification template
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this calendar notification template.
	 *
	 * @return the uuid of this calendar notification template
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this calendar notification template.
	 *
	 * @param uuid the uuid of this calendar notification template
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the calendar notification template ID of this calendar notification template.
	 *
	 * @return the calendar notification template ID of this calendar notification template
	 */
	public long getCalendarNotificationTemplateId();

	/**
	 * Sets the calendar notification template ID of this calendar notification template.
	 *
	 * @param calendarNotificationTemplateId the calendar notification template ID of this calendar notification template
	 */
	public void setCalendarNotificationTemplateId(
		long calendarNotificationTemplateId);

	/**
	 * Returns the group ID of this calendar notification template.
	 *
	 * @return the group ID of this calendar notification template
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this calendar notification template.
	 *
	 * @param groupId the group ID of this calendar notification template
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this calendar notification template.
	 *
	 * @return the company ID of this calendar notification template
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this calendar notification template.
	 *
	 * @param companyId the company ID of this calendar notification template
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this calendar notification template.
	 *
	 * @return the user ID of this calendar notification template
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this calendar notification template.
	 *
	 * @param userId the user ID of this calendar notification template
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this calendar notification template.
	 *
	 * @return the user uuid of this calendar notification template
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this calendar notification template.
	 *
	 * @param userUuid the user uuid of this calendar notification template
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this calendar notification template.
	 *
	 * @return the user name of this calendar notification template
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this calendar notification template.
	 *
	 * @param userName the user name of this calendar notification template
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this calendar notification template.
	 *
	 * @return the create date of this calendar notification template
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this calendar notification template.
	 *
	 * @param createDate the create date of this calendar notification template
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this calendar notification template.
	 *
	 * @return the modified date of this calendar notification template
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this calendar notification template.
	 *
	 * @param modifiedDate the modified date of this calendar notification template
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the calendar ID of this calendar notification template.
	 *
	 * @return the calendar ID of this calendar notification template
	 */
	public long getCalendarId();

	/**
	 * Sets the calendar ID of this calendar notification template.
	 *
	 * @param calendarId the calendar ID of this calendar notification template
	 */
	public void setCalendarId(long calendarId);

	/**
	 * Returns the notification type of this calendar notification template.
	 *
	 * @return the notification type of this calendar notification template
	 */
	@AutoEscape
	public String getNotificationType();

	/**
	 * Sets the notification type of this calendar notification template.
	 *
	 * @param notificationType the notification type of this calendar notification template
	 */
	public void setNotificationType(String notificationType);

	/**
	 * Returns the notification type settings of this calendar notification template.
	 *
	 * @return the notification type settings of this calendar notification template
	 */
	@AutoEscape
	public String getNotificationTypeSettings();

	/**
	 * Sets the notification type settings of this calendar notification template.
	 *
	 * @param notificationTypeSettings the notification type settings of this calendar notification template
	 */
	public void setNotificationTypeSettings(String notificationTypeSettings);

	/**
	 * Returns the notification template type of this calendar notification template.
	 *
	 * @return the notification template type of this calendar notification template
	 */
	@AutoEscape
	public String getNotificationTemplateType();

	/**
	 * Sets the notification template type of this calendar notification template.
	 *
	 * @param notificationTemplateType the notification template type of this calendar notification template
	 */
	public void setNotificationTemplateType(String notificationTemplateType);

	/**
	 * Returns the subject of this calendar notification template.
	 *
	 * @return the subject of this calendar notification template
	 */
	@AutoEscape
	public String getSubject();

	/**
	 * Sets the subject of this calendar notification template.
	 *
	 * @param subject the subject of this calendar notification template
	 */
	public void setSubject(String subject);

	/**
	 * Returns the body of this calendar notification template.
	 *
	 * @return the body of this calendar notification template
	 */
	@AutoEscape
	public String getBody();

	/**
	 * Sets the body of this calendar notification template.
	 *
	 * @param body the body of this calendar notification template
	 */
	public void setBody(String body);

	/**
	 * Returns the last publish date of this calendar notification template.
	 *
	 * @return the last publish date of this calendar notification template
	 */
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this calendar notification template.
	 *
	 * @param lastPublishDate the last publish date of this calendar notification template
	 */
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
	public int compareTo(
		com.liferay.calendar.model.CalendarNotificationTemplate calendarNotificationTemplate);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.calendar.model.CalendarNotificationTemplate> toCacheModel();

	@Override
	public com.liferay.calendar.model.CalendarNotificationTemplate toEscapedModel();

	@Override
	public com.liferay.calendar.model.CalendarNotificationTemplate toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}