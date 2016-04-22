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

package com.liferay.polls.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.LocalizedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedGroupedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * The base model interface for the PollsQuestion service. Represents a row in the &quot;PollsQuestion&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.polls.model.impl.PollsQuestionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.polls.model.impl.PollsQuestionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PollsQuestion
 * @see com.liferay.polls.model.impl.PollsQuestionImpl
 * @see com.liferay.polls.model.impl.PollsQuestionModelImpl
 * @generated
 */
@ProviderType
public interface PollsQuestionModel extends BaseModel<PollsQuestion>,
	LocalizedModel, ShardedModel, StagedGroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a polls question model instance should use the {@link PollsQuestion} interface instead.
	 */

	/**
	 * Returns the primary key of this polls question.
	 *
	 * @return the primary key of this polls question
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this polls question.
	 *
	 * @param primaryKey the primary key of this polls question
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this polls question.
	 *
	 * @return the uuid of this polls question
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this polls question.
	 *
	 * @param uuid the uuid of this polls question
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the question ID of this polls question.
	 *
	 * @return the question ID of this polls question
	 */
	public long getQuestionId();

	/**
	 * Sets the question ID of this polls question.
	 *
	 * @param questionId the question ID of this polls question
	 */
	public void setQuestionId(long questionId);

	/**
	 * Returns the group ID of this polls question.
	 *
	 * @return the group ID of this polls question
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this polls question.
	 *
	 * @param groupId the group ID of this polls question
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this polls question.
	 *
	 * @return the company ID of this polls question
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this polls question.
	 *
	 * @param companyId the company ID of this polls question
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this polls question.
	 *
	 * @return the user ID of this polls question
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this polls question.
	 *
	 * @param userId the user ID of this polls question
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this polls question.
	 *
	 * @return the user uuid of this polls question
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this polls question.
	 *
	 * @param userUuid the user uuid of this polls question
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this polls question.
	 *
	 * @return the user name of this polls question
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this polls question.
	 *
	 * @param userName the user name of this polls question
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this polls question.
	 *
	 * @return the create date of this polls question
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this polls question.
	 *
	 * @param createDate the create date of this polls question
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this polls question.
	 *
	 * @return the modified date of this polls question
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this polls question.
	 *
	 * @param modifiedDate the modified date of this polls question
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the title of this polls question.
	 *
	 * @return the title of this polls question
	 */
	public String getTitle();

	/**
	 * Returns the localized title of this polls question in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized title of this polls question
	 */
	@AutoEscape
	public String getTitle(Locale locale);

	/**
	 * Returns the localized title of this polls question in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this polls question. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getTitle(Locale locale, boolean useDefault);

	/**
	 * Returns the localized title of this polls question in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized title of this polls question
	 */
	@AutoEscape
	public String getTitle(String languageId);

	/**
	 * Returns the localized title of this polls question in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this polls question
	 */
	@AutoEscape
	public String getTitle(String languageId, boolean useDefault);

	@AutoEscape
	public String getTitleCurrentLanguageId();

	@AutoEscape
	public String getTitleCurrentValue();

	/**
	 * Returns a map of the locales and localized titles of this polls question.
	 *
	 * @return the locales and localized titles of this polls question
	 */
	public Map<Locale, String> getTitleMap();

	/**
	 * Sets the title of this polls question.
	 *
	 * @param title the title of this polls question
	 */
	public void setTitle(String title);

	/**
	 * Sets the localized title of this polls question in the language.
	 *
	 * @param title the localized title of this polls question
	 * @param locale the locale of the language
	 */
	public void setTitle(String title, Locale locale);

	/**
	 * Sets the localized title of this polls question in the language, and sets the default locale.
	 *
	 * @param title the localized title of this polls question
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setTitle(String title, Locale locale, Locale defaultLocale);

	public void setTitleCurrentLanguageId(String languageId);

	/**
	 * Sets the localized titles of this polls question from the map of locales and localized titles.
	 *
	 * @param titleMap the locales and localized titles of this polls question
	 */
	public void setTitleMap(Map<Locale, String> titleMap);

	/**
	 * Sets the localized titles of this polls question from the map of locales and localized titles, and sets the default locale.
	 *
	 * @param titleMap the locales and localized titles of this polls question
	 * @param defaultLocale the default locale
	 */
	public void setTitleMap(Map<Locale, String> titleMap, Locale defaultLocale);

	/**
	 * Returns the description of this polls question.
	 *
	 * @return the description of this polls question
	 */
	public String getDescription();

	/**
	 * Returns the localized description of this polls question in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this polls question
	 */
	@AutoEscape
	public String getDescription(Locale locale);

	/**
	 * Returns the localized description of this polls question in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this polls question. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getDescription(Locale locale, boolean useDefault);

	/**
	 * Returns the localized description of this polls question in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this polls question
	 */
	@AutoEscape
	public String getDescription(String languageId);

	/**
	 * Returns the localized description of this polls question in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this polls question
	 */
	@AutoEscape
	public String getDescription(String languageId, boolean useDefault);

	@AutoEscape
	public String getDescriptionCurrentLanguageId();

	@AutoEscape
	public String getDescriptionCurrentValue();

	/**
	 * Returns a map of the locales and localized descriptions of this polls question.
	 *
	 * @return the locales and localized descriptions of this polls question
	 */
	public Map<Locale, String> getDescriptionMap();

	/**
	 * Sets the description of this polls question.
	 *
	 * @param description the description of this polls question
	 */
	public void setDescription(String description);

	/**
	 * Sets the localized description of this polls question in the language.
	 *
	 * @param description the localized description of this polls question
	 * @param locale the locale of the language
	 */
	public void setDescription(String description, Locale locale);

	/**
	 * Sets the localized description of this polls question in the language, and sets the default locale.
	 *
	 * @param description the localized description of this polls question
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setDescription(String description, Locale locale,
		Locale defaultLocale);

	public void setDescriptionCurrentLanguageId(String languageId);

	/**
	 * Sets the localized descriptions of this polls question from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this polls question
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap);

	/**
	 * Sets the localized descriptions of this polls question from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this polls question
	 * @param defaultLocale the default locale
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap,
		Locale defaultLocale);

	/**
	 * Returns the expiration date of this polls question.
	 *
	 * @return the expiration date of this polls question
	 */
	public Date getExpirationDate();

	/**
	 * Sets the expiration date of this polls question.
	 *
	 * @param expirationDate the expiration date of this polls question
	 */
	public void setExpirationDate(Date expirationDate);

	/**
	 * Returns the last publish date of this polls question.
	 *
	 * @return the last publish date of this polls question
	 */
	@Override
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this polls question.
	 *
	 * @param lastPublishDate the last publish date of this polls question
	 */
	@Override
	public void setLastPublishDate(Date lastPublishDate);

	/**
	 * Returns the last vote date of this polls question.
	 *
	 * @return the last vote date of this polls question
	 */
	public Date getLastVoteDate();

	/**
	 * Sets the last vote date of this polls question.
	 *
	 * @param lastVoteDate the last vote date of this polls question
	 */
	public void setLastVoteDate(Date lastVoteDate);

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
	public int compareTo(com.liferay.polls.model.PollsQuestion pollsQuestion);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.polls.model.PollsQuestion> toCacheModel();

	@Override
	public com.liferay.polls.model.PollsQuestion toEscapedModel();

	@Override
	public com.liferay.polls.model.PollsQuestion toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}