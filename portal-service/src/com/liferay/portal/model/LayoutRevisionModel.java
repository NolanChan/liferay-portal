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

import com.liferay.portal.LocaleException;
import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * The base model interface for the LayoutRevision service. Represents a row in the &quot;LayoutRevision&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.LayoutRevisionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.LayoutRevisionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutRevision
 * @see com.liferay.portal.model.impl.LayoutRevisionImpl
 * @see com.liferay.portal.model.impl.LayoutRevisionModelImpl
 * @generated
 */
@ProviderType
public interface LayoutRevisionModel extends BaseModel<LayoutRevision>,
	GroupedModel, LocalizedModel, MVCCModel, WorkflowedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a layout revision model instance should use the {@link LayoutRevision} interface instead.
	 */

	/**
	 * Returns the primary key of this layout revision.
	 *
	 * @return the primary key of this layout revision
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this layout revision.
	 *
	 * @param primaryKey the primary key of this layout revision
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this layout revision.
	 *
	 * @return the mvcc version of this layout revision
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this layout revision.
	 *
	 * @param mvccVersion the mvcc version of this layout revision
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the layout revision ID of this layout revision.
	 *
	 * @return the layout revision ID of this layout revision
	 */
	public long getLayoutRevisionId();

	/**
	 * Sets the layout revision ID of this layout revision.
	 *
	 * @param layoutRevisionId the layout revision ID of this layout revision
	 */
	public void setLayoutRevisionId(long layoutRevisionId);

	/**
	 * Returns the group ID of this layout revision.
	 *
	 * @return the group ID of this layout revision
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this layout revision.
	 *
	 * @param groupId the group ID of this layout revision
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this layout revision.
	 *
	 * @return the company ID of this layout revision
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this layout revision.
	 *
	 * @param companyId the company ID of this layout revision
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this layout revision.
	 *
	 * @return the user ID of this layout revision
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this layout revision.
	 *
	 * @param userId the user ID of this layout revision
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this layout revision.
	 *
	 * @return the user uuid of this layout revision
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this layout revision.
	 *
	 * @param userUuid the user uuid of this layout revision
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this layout revision.
	 *
	 * @return the user name of this layout revision
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this layout revision.
	 *
	 * @param userName the user name of this layout revision
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this layout revision.
	 *
	 * @return the create date of this layout revision
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this layout revision.
	 *
	 * @param createDate the create date of this layout revision
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this layout revision.
	 *
	 * @return the modified date of this layout revision
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this layout revision.
	 *
	 * @param modifiedDate the modified date of this layout revision
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the layout set branch ID of this layout revision.
	 *
	 * @return the layout set branch ID of this layout revision
	 */
	public long getLayoutSetBranchId();

	/**
	 * Sets the layout set branch ID of this layout revision.
	 *
	 * @param layoutSetBranchId the layout set branch ID of this layout revision
	 */
	public void setLayoutSetBranchId(long layoutSetBranchId);

	/**
	 * Returns the layout branch ID of this layout revision.
	 *
	 * @return the layout branch ID of this layout revision
	 */
	public long getLayoutBranchId();

	/**
	 * Sets the layout branch ID of this layout revision.
	 *
	 * @param layoutBranchId the layout branch ID of this layout revision
	 */
	public void setLayoutBranchId(long layoutBranchId);

	/**
	 * Returns the parent layout revision ID of this layout revision.
	 *
	 * @return the parent layout revision ID of this layout revision
	 */
	public long getParentLayoutRevisionId();

	/**
	 * Sets the parent layout revision ID of this layout revision.
	 *
	 * @param parentLayoutRevisionId the parent layout revision ID of this layout revision
	 */
	public void setParentLayoutRevisionId(long parentLayoutRevisionId);

	/**
	 * Returns the head of this layout revision.
	 *
	 * @return the head of this layout revision
	 */
	public boolean getHead();

	/**
	 * Returns <code>true</code> if this layout revision is head.
	 *
	 * @return <code>true</code> if this layout revision is head; <code>false</code> otherwise
	 */
	public boolean isHead();

	/**
	 * Sets whether this layout revision is head.
	 *
	 * @param head the head of this layout revision
	 */
	public void setHead(boolean head);

	/**
	 * Returns the major of this layout revision.
	 *
	 * @return the major of this layout revision
	 */
	public boolean getMajor();

	/**
	 * Returns <code>true</code> if this layout revision is major.
	 *
	 * @return <code>true</code> if this layout revision is major; <code>false</code> otherwise
	 */
	public boolean isMajor();

	/**
	 * Sets whether this layout revision is major.
	 *
	 * @param major the major of this layout revision
	 */
	public void setMajor(boolean major);

	/**
	 * Returns the plid of this layout revision.
	 *
	 * @return the plid of this layout revision
	 */
	public long getPlid();

	/**
	 * Sets the plid of this layout revision.
	 *
	 * @param plid the plid of this layout revision
	 */
	public void setPlid(long plid);

	/**
	 * Returns the private layout of this layout revision.
	 *
	 * @return the private layout of this layout revision
	 */
	public boolean getPrivateLayout();

	/**
	 * Returns <code>true</code> if this layout revision is private layout.
	 *
	 * @return <code>true</code> if this layout revision is private layout; <code>false</code> otherwise
	 */
	public boolean isPrivateLayout();

	/**
	 * Sets whether this layout revision is private layout.
	 *
	 * @param privateLayout the private layout of this layout revision
	 */
	public void setPrivateLayout(boolean privateLayout);

	/**
	 * Returns the name of this layout revision.
	 *
	 * @return the name of this layout revision
	 */
	public String getName();

	/**
	 * Returns the localized name of this layout revision in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized name of this layout revision
	 */
	@AutoEscape
	public String getName(Locale locale);

	/**
	 * Returns the localized name of this layout revision in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this layout revision. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getName(Locale locale, boolean useDefault);

	/**
	 * Returns the localized name of this layout revision in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized name of this layout revision
	 */
	@AutoEscape
	public String getName(String languageId);

	/**
	 * Returns the localized name of this layout revision in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized name of this layout revision
	 */
	@AutoEscape
	public String getName(String languageId, boolean useDefault);

	@AutoEscape
	public String getNameCurrentLanguageId();

	@AutoEscape
	public String getNameCurrentValue();

	/**
	 * Returns a map of the locales and localized names of this layout revision.
	 *
	 * @return the locales and localized names of this layout revision
	 */
	public Map<Locale, String> getNameMap();

	/**
	 * Sets the name of this layout revision.
	 *
	 * @param name the name of this layout revision
	 */
	public void setName(String name);

	/**
	 * Sets the localized name of this layout revision in the language.
	 *
	 * @param name the localized name of this layout revision
	 * @param locale the locale of the language
	 */
	public void setName(String name, Locale locale);

	/**
	 * Sets the localized name of this layout revision in the language, and sets the default locale.
	 *
	 * @param name the localized name of this layout revision
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setName(String name, Locale locale, Locale defaultLocale);

	public void setNameCurrentLanguageId(String languageId);

	/**
	 * Sets the localized names of this layout revision from the map of locales and localized names.
	 *
	 * @param nameMap the locales and localized names of this layout revision
	 */
	public void setNameMap(Map<Locale, String> nameMap);

	/**
	 * Sets the localized names of this layout revision from the map of locales and localized names, and sets the default locale.
	 *
	 * @param nameMap the locales and localized names of this layout revision
	 * @param defaultLocale the default locale
	 */
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale);

	/**
	 * Returns the title of this layout revision.
	 *
	 * @return the title of this layout revision
	 */
	public String getTitle();

	/**
	 * Returns the localized title of this layout revision in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized title of this layout revision
	 */
	@AutoEscape
	public String getTitle(Locale locale);

	/**
	 * Returns the localized title of this layout revision in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this layout revision. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getTitle(Locale locale, boolean useDefault);

	/**
	 * Returns the localized title of this layout revision in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized title of this layout revision
	 */
	@AutoEscape
	public String getTitle(String languageId);

	/**
	 * Returns the localized title of this layout revision in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized title of this layout revision
	 */
	@AutoEscape
	public String getTitle(String languageId, boolean useDefault);

	@AutoEscape
	public String getTitleCurrentLanguageId();

	@AutoEscape
	public String getTitleCurrentValue();

	/**
	 * Returns a map of the locales and localized titles of this layout revision.
	 *
	 * @return the locales and localized titles of this layout revision
	 */
	public Map<Locale, String> getTitleMap();

	/**
	 * Sets the title of this layout revision.
	 *
	 * @param title the title of this layout revision
	 */
	public void setTitle(String title);

	/**
	 * Sets the localized title of this layout revision in the language.
	 *
	 * @param title the localized title of this layout revision
	 * @param locale the locale of the language
	 */
	public void setTitle(String title, Locale locale);

	/**
	 * Sets the localized title of this layout revision in the language, and sets the default locale.
	 *
	 * @param title the localized title of this layout revision
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setTitle(String title, Locale locale, Locale defaultLocale);

	public void setTitleCurrentLanguageId(String languageId);

	/**
	 * Sets the localized titles of this layout revision from the map of locales and localized titles.
	 *
	 * @param titleMap the locales and localized titles of this layout revision
	 */
	public void setTitleMap(Map<Locale, String> titleMap);

	/**
	 * Sets the localized titles of this layout revision from the map of locales and localized titles, and sets the default locale.
	 *
	 * @param titleMap the locales and localized titles of this layout revision
	 * @param defaultLocale the default locale
	 */
	public void setTitleMap(Map<Locale, String> titleMap, Locale defaultLocale);

	/**
	 * Returns the description of this layout revision.
	 *
	 * @return the description of this layout revision
	 */
	public String getDescription();

	/**
	 * Returns the localized description of this layout revision in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this layout revision
	 */
	@AutoEscape
	public String getDescription(Locale locale);

	/**
	 * Returns the localized description of this layout revision in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this layout revision. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getDescription(Locale locale, boolean useDefault);

	/**
	 * Returns the localized description of this layout revision in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this layout revision
	 */
	@AutoEscape
	public String getDescription(String languageId);

	/**
	 * Returns the localized description of this layout revision in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this layout revision
	 */
	@AutoEscape
	public String getDescription(String languageId, boolean useDefault);

	@AutoEscape
	public String getDescriptionCurrentLanguageId();

	@AutoEscape
	public String getDescriptionCurrentValue();

	/**
	 * Returns a map of the locales and localized descriptions of this layout revision.
	 *
	 * @return the locales and localized descriptions of this layout revision
	 */
	public Map<Locale, String> getDescriptionMap();

	/**
	 * Sets the description of this layout revision.
	 *
	 * @param description the description of this layout revision
	 */
	public void setDescription(String description);

	/**
	 * Sets the localized description of this layout revision in the language.
	 *
	 * @param description the localized description of this layout revision
	 * @param locale the locale of the language
	 */
	public void setDescription(String description, Locale locale);

	/**
	 * Sets the localized description of this layout revision in the language, and sets the default locale.
	 *
	 * @param description the localized description of this layout revision
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setDescription(String description, Locale locale,
		Locale defaultLocale);

	public void setDescriptionCurrentLanguageId(String languageId);

	/**
	 * Sets the localized descriptions of this layout revision from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this layout revision
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap);

	/**
	 * Sets the localized descriptions of this layout revision from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this layout revision
	 * @param defaultLocale the default locale
	 */
	public void setDescriptionMap(Map<Locale, String> descriptionMap,
		Locale defaultLocale);

	/**
	 * Returns the keywords of this layout revision.
	 *
	 * @return the keywords of this layout revision
	 */
	public String getKeywords();

	/**
	 * Returns the localized keywords of this layout revision in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized keywords of this layout revision
	 */
	@AutoEscape
	public String getKeywords(Locale locale);

	/**
	 * Returns the localized keywords of this layout revision in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized keywords of this layout revision. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getKeywords(Locale locale, boolean useDefault);

	/**
	 * Returns the localized keywords of this layout revision in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized keywords of this layout revision
	 */
	@AutoEscape
	public String getKeywords(String languageId);

	/**
	 * Returns the localized keywords of this layout revision in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized keywords of this layout revision
	 */
	@AutoEscape
	public String getKeywords(String languageId, boolean useDefault);

	@AutoEscape
	public String getKeywordsCurrentLanguageId();

	@AutoEscape
	public String getKeywordsCurrentValue();

	/**
	 * Returns a map of the locales and localized keywordses of this layout revision.
	 *
	 * @return the locales and localized keywordses of this layout revision
	 */
	public Map<Locale, String> getKeywordsMap();

	/**
	 * Sets the keywords of this layout revision.
	 *
	 * @param keywords the keywords of this layout revision
	 */
	public void setKeywords(String keywords);

	/**
	 * Sets the localized keywords of this layout revision in the language.
	 *
	 * @param keywords the localized keywords of this layout revision
	 * @param locale the locale of the language
	 */
	public void setKeywords(String keywords, Locale locale);

	/**
	 * Sets the localized keywords of this layout revision in the language, and sets the default locale.
	 *
	 * @param keywords the localized keywords of this layout revision
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setKeywords(String keywords, Locale locale, Locale defaultLocale);

	public void setKeywordsCurrentLanguageId(String languageId);

	/**
	 * Sets the localized keywordses of this layout revision from the map of locales and localized keywordses.
	 *
	 * @param keywordsMap the locales and localized keywordses of this layout revision
	 */
	public void setKeywordsMap(Map<Locale, String> keywordsMap);

	/**
	 * Sets the localized keywordses of this layout revision from the map of locales and localized keywordses, and sets the default locale.
	 *
	 * @param keywordsMap the locales and localized keywordses of this layout revision
	 * @param defaultLocale the default locale
	 */
	public void setKeywordsMap(Map<Locale, String> keywordsMap,
		Locale defaultLocale);

	/**
	 * Returns the robots of this layout revision.
	 *
	 * @return the robots of this layout revision
	 */
	public String getRobots();

	/**
	 * Returns the localized robots of this layout revision in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized robots of this layout revision
	 */
	@AutoEscape
	public String getRobots(Locale locale);

	/**
	 * Returns the localized robots of this layout revision in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized robots of this layout revision. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@AutoEscape
	public String getRobots(Locale locale, boolean useDefault);

	/**
	 * Returns the localized robots of this layout revision in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized robots of this layout revision
	 */
	@AutoEscape
	public String getRobots(String languageId);

	/**
	 * Returns the localized robots of this layout revision in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized robots of this layout revision
	 */
	@AutoEscape
	public String getRobots(String languageId, boolean useDefault);

	@AutoEscape
	public String getRobotsCurrentLanguageId();

	@AutoEscape
	public String getRobotsCurrentValue();

	/**
	 * Returns a map of the locales and localized robotses of this layout revision.
	 *
	 * @return the locales and localized robotses of this layout revision
	 */
	public Map<Locale, String> getRobotsMap();

	/**
	 * Sets the robots of this layout revision.
	 *
	 * @param robots the robots of this layout revision
	 */
	public void setRobots(String robots);

	/**
	 * Sets the localized robots of this layout revision in the language.
	 *
	 * @param robots the localized robots of this layout revision
	 * @param locale the locale of the language
	 */
	public void setRobots(String robots, Locale locale);

	/**
	 * Sets the localized robots of this layout revision in the language, and sets the default locale.
	 *
	 * @param robots the localized robots of this layout revision
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	public void setRobots(String robots, Locale locale, Locale defaultLocale);

	public void setRobotsCurrentLanguageId(String languageId);

	/**
	 * Sets the localized robotses of this layout revision from the map of locales and localized robotses.
	 *
	 * @param robotsMap the locales and localized robotses of this layout revision
	 */
	public void setRobotsMap(Map<Locale, String> robotsMap);

	/**
	 * Sets the localized robotses of this layout revision from the map of locales and localized robotses, and sets the default locale.
	 *
	 * @param robotsMap the locales and localized robotses of this layout revision
	 * @param defaultLocale the default locale
	 */
	public void setRobotsMap(Map<Locale, String> robotsMap, Locale defaultLocale);

	/**
	 * Returns the type settings of this layout revision.
	 *
	 * @return the type settings of this layout revision
	 */
	@AutoEscape
	public String getTypeSettings();

	/**
	 * Sets the type settings of this layout revision.
	 *
	 * @param typeSettings the type settings of this layout revision
	 */
	public void setTypeSettings(String typeSettings);

	/**
	 * Returns the icon image ID of this layout revision.
	 *
	 * @return the icon image ID of this layout revision
	 */
	public long getIconImageId();

	/**
	 * Sets the icon image ID of this layout revision.
	 *
	 * @param iconImageId the icon image ID of this layout revision
	 */
	public void setIconImageId(long iconImageId);

	/**
	 * Returns the theme ID of this layout revision.
	 *
	 * @return the theme ID of this layout revision
	 */
	@AutoEscape
	public String getThemeId();

	/**
	 * Sets the theme ID of this layout revision.
	 *
	 * @param themeId the theme ID of this layout revision
	 */
	public void setThemeId(String themeId);

	/**
	 * Returns the color scheme ID of this layout revision.
	 *
	 * @return the color scheme ID of this layout revision
	 */
	@AutoEscape
	public String getColorSchemeId();

	/**
	 * Sets the color scheme ID of this layout revision.
	 *
	 * @param colorSchemeId the color scheme ID of this layout revision
	 */
	public void setColorSchemeId(String colorSchemeId);

	/**
	 * Returns the wap theme ID of this layout revision.
	 *
	 * @return the wap theme ID of this layout revision
	 */
	@AutoEscape
	public String getWapThemeId();

	/**
	 * Sets the wap theme ID of this layout revision.
	 *
	 * @param wapThemeId the wap theme ID of this layout revision
	 */
	public void setWapThemeId(String wapThemeId);

	/**
	 * Returns the wap color scheme ID of this layout revision.
	 *
	 * @return the wap color scheme ID of this layout revision
	 */
	@AutoEscape
	public String getWapColorSchemeId();

	/**
	 * Sets the wap color scheme ID of this layout revision.
	 *
	 * @param wapColorSchemeId the wap color scheme ID of this layout revision
	 */
	public void setWapColorSchemeId(String wapColorSchemeId);

	/**
	 * Returns the css of this layout revision.
	 *
	 * @return the css of this layout revision
	 */
	@AutoEscape
	public String getCss();

	/**
	 * Sets the css of this layout revision.
	 *
	 * @param css the css of this layout revision
	 */
	public void setCss(String css);

	/**
	 * Returns the status of this layout revision.
	 *
	 * @return the status of this layout revision
	 */
	@Override
	public int getStatus();

	/**
	 * Sets the status of this layout revision.
	 *
	 * @param status the status of this layout revision
	 */
	@Override
	public void setStatus(int status);

	/**
	 * Returns the status by user ID of this layout revision.
	 *
	 * @return the status by user ID of this layout revision
	 */
	@Override
	public long getStatusByUserId();

	/**
	 * Sets the status by user ID of this layout revision.
	 *
	 * @param statusByUserId the status by user ID of this layout revision
	 */
	@Override
	public void setStatusByUserId(long statusByUserId);

	/**
	 * Returns the status by user uuid of this layout revision.
	 *
	 * @return the status by user uuid of this layout revision
	 */
	@Override
	public String getStatusByUserUuid();

	/**
	 * Sets the status by user uuid of this layout revision.
	 *
	 * @param statusByUserUuid the status by user uuid of this layout revision
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid);

	/**
	 * Returns the status by user name of this layout revision.
	 *
	 * @return the status by user name of this layout revision
	 */
	@AutoEscape
	@Override
	public String getStatusByUserName();

	/**
	 * Sets the status by user name of this layout revision.
	 *
	 * @param statusByUserName the status by user name of this layout revision
	 */
	@Override
	public void setStatusByUserName(String statusByUserName);

	/**
	 * Returns the status date of this layout revision.
	 *
	 * @return the status date of this layout revision
	 */
	@Override
	public Date getStatusDate();

	/**
	 * Sets the status date of this layout revision.
	 *
	 * @param statusDate the status date of this layout revision
	 */
	@Override
	public void setStatusDate(Date statusDate);

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #isApproved()}
	 */
	@Deprecated
	@Override
	public boolean getApproved();

	/**
	 * Returns <code>true</code> if this layout revision is approved.
	 *
	 * @return <code>true</code> if this layout revision is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved();

	/**
	 * Returns <code>true</code> if this layout revision is denied.
	 *
	 * @return <code>true</code> if this layout revision is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied();

	/**
	 * Returns <code>true</code> if this layout revision is a draft.
	 *
	 * @return <code>true</code> if this layout revision is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft();

	/**
	 * Returns <code>true</code> if this layout revision is expired.
	 *
	 * @return <code>true</code> if this layout revision is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired();

	/**
	 * Returns <code>true</code> if this layout revision is inactive.
	 *
	 * @return <code>true</code> if this layout revision is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive();

	/**
	 * Returns <code>true</code> if this layout revision is incomplete.
	 *
	 * @return <code>true</code> if this layout revision is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete();

	/**
	 * Returns <code>true</code> if this layout revision is pending.
	 *
	 * @return <code>true</code> if this layout revision is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending();

	/**
	 * Returns <code>true</code> if this layout revision is scheduled.
	 *
	 * @return <code>true</code> if this layout revision is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled();

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
	public int compareTo(LayoutRevision layoutRevision);

	@Override
	public int hashCode();

	@Override
	public CacheModel<LayoutRevision> toCacheModel();

	@Override
	public LayoutRevision toEscapedModel();

	@Override
	public LayoutRevision toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}