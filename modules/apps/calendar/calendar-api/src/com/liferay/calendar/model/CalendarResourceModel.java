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

import com.liferay.portal.LocaleException;
import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.AttachedModel;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.LocalizedModel;
import com.liferay.portal.model.PartitionedModel;
import com.liferay.portal.model.StagedGroupedModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * The base model interface for the CalendarResource service. Represents a row in the &quot;CalendarResource&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.calendar.model.impl.CalendarResourceModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.calendar.model.impl.CalendarResourceImpl}.
 * </p>
 *
 * @author Eduardo Lundgren
 * @see CalendarResource
 * @see com.liferay.calendar.model.impl.CalendarResourceImpl
 * @see com.liferay.calendar.model.impl.CalendarResourceModelImpl
 * @generated
 */
@ProviderType
public interface CalendarResourceModel extends AttachedModel,
	BaseModel<CalendarResource>, LocalizedModel, PartitionedModel,
	StagedGroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a calendar resource model instance should use the {@link CalendarResource} interface instead.
	 */

	/**
	 * Returns the primary key of this calendar resource.
	 *
	 * @return the primary key of this calendar resource
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this calendar resource.
	 *
	 * @param primaryKey the primary key of this calendar resource
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this calendar resource.
	 *
	 * @return the uuid of this calendar resource
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this calendar resource.
	 *
	 * @param uuid the uuid of this calendar resource
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the calendar resource ID of this calendar resource.
	 *
	 * @return the calendar resource ID of this calendar resource
	 */
	public long getCalendarResourceId();

	/**
	 * Sets the calendar resource ID of this calendar resource.
	 *
	 * @param calendarResourceId the calendar resource ID of this calendar resource
	 */
	public void setCalendarResourceId(long calendarResourceId);

	/**
	 * Returns the group ID of this calendar resource.
	 *
	 * @return the group ID of this calendar resource
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this calendar resource.
	 *
	 * @param groupId the group ID of this calendar resource
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this calendar resource.
	 *
	 * @return the company ID of this calendar resource
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this calendar resource.
	 *
	 * @param companyId the company ID of this calendar resource
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this calendar resource.
	 *
	 * @return the user ID of this calendar resource
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this calendar resource.
	 *
	 * @param userId the user ID of this calendar resource
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this calendar resource.
	 *
	 * @return the user uuid of this calendar resource
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this calendar resource.
	 *
	 * @param userUuid the user uuid of this calendar resource
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this calendar resource.
	 *
	 * @return the user name of this calendar resource
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this calendar resource.
	 *
	 * @param userName the user name of this calendar resource
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this calendar resource.
	 *
	 * @return the create date of this calendar resource
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this calendar resource.
	 *
	 * @param createDate the create date of this calendar resource
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this calendar resource.
	 *
	 * @return the modified date of this calendar resource
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this calendar resource.
	 *
	 * @param modifiedDate the modified date of this calendar resource
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the resource block ID of this calendar resource.
	 *
	 * @return the resource block ID of this calendar resource
	 */
	public long getResourceBlockId();

	/**
	 * Sets the resource block ID of this calendar resource.
	 *
	 * @param resourceBlockId the resource block ID of this calendar resource
	 */
	public void setResourceBlockId(long resourceBlockId);

	/**
	 * Returns the fully qualified class name of this calendar resource.
	 *
	 * @return the fully qualified class name of this calendar resource
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this calendar resource.
	 *
	 * @return the class name ID of this calendar resource
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this calendar resource.
	 *
	 * @param classNameId the class name ID of this calendar resource
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class p k of this calendar resource.
	 *
	 * @return the class p k of this calendar resource
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class p k of this calendar resource.
	 *
	 * @param classPK the class p k of this calendar resource
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the class uuid of this calendar resource.
	 *
	 * @return the class uuid of this calendar resource
	 */
	@AutoEscape
	public String getClassUuid();

	/**
	 * Sets the class uuid of this calendar resource.
	 *
	 * @param classUuid the class uuid of this calendar resource
	 */
	public void setClassUuid(String classUuid);

	/**
	 * Returns the code of this calendar resource.
	 *
	 * @return the code of this calendar resource
	 */
	@AutoEscape
	public String getCode();

	/**
	 * Sets the code of this calendar resource.
	 *
	 * @param code the code of this calendar resource
	 */
	public void setCode(String code);

	/**
	 * Returns the name of this calendar resource.
	 *
	 * @return the name of this calendar resource
	 */
	public String getName();

	/**
	 * Returns the localized name of this calendar resource in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized name of this calendar resource
	 */
	@AutoEscape
	public String getName(Locale locale);

	/**
	 * Returns the localized name of this calendar resource in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this calendar resource. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getName(Locale locale, boolean useDefault);

	/**
	 * Returns the localized name of this calendar resource in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized name of this calendar resource
	 */
	@AutoEscape
	public String getName(String languageId);

	/**
	 * Returns the localized name of this calendar resource in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this calendar resource
	 */
	@AutoEscape
	public String getName(String languageId, boolean useDefault);

	@AutoEscape
	public String getNameCurrentLanguageId();

	@AutoEscape
	public String getNameCurrentValue();

	/**
	 * Returns a map of the locales and localized names of this calendar resource.
	 *
	 * @return the locales and localized names of this calendar resource
	 */
	public Map<Locale, String> getNameMap();

	/**
	 * Sets the name of this calendar resource.
	 *
	 * @param name the name of this calendar resource
	 */
	public void setName(String name);

	/**
	 * Sets the localized name of this calendar resource in the language.
	 *
	 * @param name the localized name of this calendar resource
	 * @param locale the locale of the language
	 */
	public void setName(String name, Locale locale);

	/**
	 * Sets the localized name of this calendar resource in the language, and sets the default locale.
	 *
	 * @param name the localized name of this calendar resource
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setName(String name, Locale locale, Locale defaultLocale);

	public void setNameCurrentLanguageId(String languageId);

	/**
	 * Sets the localized names of this calendar resource from the map of locales and localized names.
	 *
	 * @param nameMap the locales and localized names of this calendar resource
	 */
	public void setNameMap(Map<Locale, String> nameMap);

	/**
	 * Sets the localized names of this calendar resource from the map of locales and localized names, and sets the default locale.
	 *
	 * @param nameMap the locales and localized names of this calendar resource
	 * @param defaultLocale the default locale
	 */
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale);

	/**
	 * Returns the description of this calendar resource.
	 *
	 * @return the description of this calendar resource
	 */
	public String getDescription();

	/**
	 * Returns the localized description of this calendar resource in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this calendar resource
	 */
	@AutoEscape
	public String getDescription(Locale locale);

	/**
	 * Returns the localized description of this calendar resource in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this calendar resource. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getDescription(Locale locale, boolean useDefault);

	/**
	 * Returns the localized description of this calendar resource in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this calendar resource
	 */
	@AutoEscape
	public String getDescription(String languageId);

	/**
	 * Returns the localized description of this calendar resource in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this calendar resource
	 */
	@AutoEscape
	public String getDescription(String languageId, boolean useDefault);

	@AutoEscape
	public String getDescriptionCurrentLanguageId();

	@AutoEscape
	public String getDescriptionCurrentValue();

	/**
	 * Returns a map of the locales and localized descriptions of this calendar resource.
	 *
	 * @return the locales and localized descriptions of this calendar resource
	 */
	public Map<Locale, String> getDescriptionMap();

	/**
	 * Sets the description of this calendar resource.
	 *
	 * @param description the description of this calendar resource
	 */
	public void setDescription(String description);

	/**
	 * Sets the localized description of this calendar resource in the language.
	 *
	 * @param description the localized description of this calendar resource
	 * @param locale the locale of the language
	 */
	public void setDescription(String description, Locale locale);

	/**
	 * Sets the localized description of this calendar resource in the language, and sets the default locale.
	 *
	 * @param description the localized description of this calendar resource
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setDescription(String description, Locale locale,
		Locale defaultLocale);

	public void setDescriptionCurrentLanguageId(String languageId);

	/**
	 * Sets the localized descriptions of this calendar resource from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this calendar resource
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap);

	/**
	 * Sets the localized descriptions of this calendar resource from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this calendar resource
	 * @param defaultLocale the default locale
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap,
		Locale defaultLocale);

	/**
	 * Returns the active of this calendar resource.
	 *
	 * @return the active of this calendar resource
	 */
	public boolean getActive();

	/**
	 * Returns <code>true</code> if this calendar resource is active.
	 *
	 * @return <code>true</code> if this calendar resource is active; <code>false</code> otherwise
	 */
	public boolean isActive();

	/**
	 * Sets whether this calendar resource is active.
	 *
	 * @param active the active of this calendar resource
	 */
	public void setActive(boolean active);

	/**
	 * Returns the last publish date of this calendar resource.
	 *
	 * @return the last publish date of this calendar resource
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this calendar resource.
	 *
	 * @param lastPublishDate the last publish date of this calendar resource
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
	public String[] getAvailableLanguageIds();

	@Override
	public String getDefaultLanguageId();

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException;

	@Override
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException;

	@Override
	public Object clone();

	@Override
	public int compareTo(
		com.liferay.calendar.model.CalendarResource calendarResource);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.calendar.model.CalendarResource> toCacheModel();

	@Override
	public com.liferay.calendar.model.CalendarResource toEscapedModel();

	@Override
	public com.liferay.calendar.model.CalendarResource toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}