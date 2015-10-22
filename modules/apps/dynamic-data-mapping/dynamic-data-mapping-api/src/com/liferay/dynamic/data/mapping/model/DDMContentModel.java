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

import com.liferay.portal.LocaleException;
import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.GroupedModel;
import com.liferay.portal.model.LocalizedModel;
import com.liferay.portal.model.PartitionedModel;
import com.liferay.portal.model.StagedAuditedModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * The base model interface for the DDMContent service. Represents a row in the &quot;DDMContent&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.dynamic.data.mapping.model.impl.DDMContentModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.dynamic.data.mapping.model.impl.DDMContentImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMContent
 * @see com.liferay.dynamic.data.mapping.model.impl.DDMContentImpl
 * @see com.liferay.dynamic.data.mapping.model.impl.DDMContentModelImpl
 * @generated
 */
@ProviderType
public interface DDMContentModel extends BaseModel<DDMContent>, GroupedModel,
	LocalizedModel, PartitionedModel, StagedAuditedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a d d m content model instance should use the {@link DDMContent} interface instead.
	 */

	/**
	 * Returns the primary key of this d d m content.
	 *
	 * @return the primary key of this d d m content
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this d d m content.
	 *
	 * @param primaryKey the primary key of this d d m content
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this d d m content.
	 *
	 * @return the uuid of this d d m content
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this d d m content.
	 *
	 * @param uuid the uuid of this d d m content
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the content ID of this d d m content.
	 *
	 * @return the content ID of this d d m content
	 */
	public long getContentId();

	/**
	 * Sets the content ID of this d d m content.
	 *
	 * @param contentId the content ID of this d d m content
	 */
	public void setContentId(long contentId);

	/**
	 * Returns the group ID of this d d m content.
	 *
	 * @return the group ID of this d d m content
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this d d m content.
	 *
	 * @param groupId the group ID of this d d m content
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this d d m content.
	 *
	 * @return the company ID of this d d m content
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this d d m content.
	 *
	 * @param companyId the company ID of this d d m content
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this d d m content.
	 *
	 * @return the user ID of this d d m content
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this d d m content.
	 *
	 * @param userId the user ID of this d d m content
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this d d m content.
	 *
	 * @return the user uuid of this d d m content
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this d d m content.
	 *
	 * @param userUuid the user uuid of this d d m content
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this d d m content.
	 *
	 * @return the user name of this d d m content
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this d d m content.
	 *
	 * @param userName the user name of this d d m content
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this d d m content.
	 *
	 * @return the create date of this d d m content
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this d d m content.
	 *
	 * @param createDate the create date of this d d m content
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this d d m content.
	 *
	 * @return the modified date of this d d m content
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this d d m content.
	 *
	 * @param modifiedDate the modified date of this d d m content
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the name of this d d m content.
	 *
	 * @return the name of this d d m content
	 */
	public String getName();

	/**
	 * Returns the localized name of this d d m content in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized name of this d d m content
	 */
	@AutoEscape
	public String getName(Locale locale);

	/**
	 * Returns the localized name of this d d m content in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this d d m content. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getName(Locale locale, boolean useDefault);

	/**
	 * Returns the localized name of this d d m content in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized name of this d d m content
	 */
	@AutoEscape
	public String getName(String languageId);

	/**
	 * Returns the localized name of this d d m content in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this d d m content
	 */
	@AutoEscape
	public String getName(String languageId, boolean useDefault);

	@AutoEscape
	public String getNameCurrentLanguageId();

	@AutoEscape
	public String getNameCurrentValue();

	/**
	 * Returns a map of the locales and localized names of this d d m content.
	 *
	 * @return the locales and localized names of this d d m content
	 */
	public Map<Locale, String> getNameMap();

	/**
	 * Sets the name of this d d m content.
	 *
	 * @param name the name of this d d m content
	 */
	public void setName(String name);

	/**
	 * Sets the localized name of this d d m content in the language.
	 *
	 * @param name the localized name of this d d m content
	 * @param locale the locale of the language
	 */
	public void setName(String name, Locale locale);

	/**
	 * Sets the localized name of this d d m content in the language, and sets the default locale.
	 *
	 * @param name the localized name of this d d m content
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setName(String name, Locale locale, Locale defaultLocale);

	public void setNameCurrentLanguageId(String languageId);

	/**
	 * Sets the localized names of this d d m content from the map of locales and localized names.
	 *
	 * @param nameMap the locales and localized names of this d d m content
	 */
	public void setNameMap(Map<Locale, String> nameMap);

	/**
	 * Sets the localized names of this d d m content from the map of locales and localized names, and sets the default locale.
	 *
	 * @param nameMap the locales and localized names of this d d m content
	 * @param defaultLocale the default locale
	 */
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale);

	/**
	 * Returns the description of this d d m content.
	 *
	 * @return the description of this d d m content
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this d d m content.
	 *
	 * @param description the description of this d d m content
	 */
	public void setDescription(String description);

	/**
	 * Returns the data of this d d m content.
	 *
	 * @return the data of this d d m content
	 */
	@AutoEscape
	public String getData();

	/**
	 * Sets the data of this d d m content.
	 *
	 * @param data the data of this d d m content
	 */
	public void setData(String data);

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
		com.liferay.dynamic.data.mapping.model.DDMContent ddmContent);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.dynamic.data.mapping.model.DDMContent> toCacheModel();

	@Override
	public com.liferay.dynamic.data.mapping.model.DDMContent toEscapedModel();

	@Override
	public com.liferay.dynamic.data.mapping.model.DDMContent toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}