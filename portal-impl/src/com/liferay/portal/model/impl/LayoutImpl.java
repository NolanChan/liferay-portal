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

package com.liferay.portal.model.impl;

import com.liferay.portal.LayoutFriendlyURLException;
import com.liferay.portal.NoSuchGroupException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.CookieKeys;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ColorScheme;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.model.LayoutFriendlyURL;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutType;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.LayoutTypePortletConstants;
import com.liferay.portal.model.Theme;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutFriendlyURLLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.service.ThemeLocalServiceUtil;
import com.liferay.portal.service.permission.LayoutPermissionUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.LayoutClone;
import com.liferay.portal.util.LayoutCloneFactory;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.PortletURLImpl;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;
import javax.portlet.WindowState;

import javax.servlet.http.HttpServletRequest;

/**
 * The UI name for a layout is "page". Thus a Layout represents a Page in the
 * Portal. Layouts can be organized hierarchically and are summarized in {@link
 * LayoutSet}s. A single page is either part of the public or the private
 * LayoutSet of a group (site)
 *
 * @author Brian Wing Shun Chan
 */
public class LayoutImpl extends LayoutBaseImpl {

	public static boolean hasFriendlyURLKeyword(String friendlyURL) {
		String keyword = _getFriendlyURLKeyword(friendlyURL);

		if (Validator.isNotNull(keyword)) {
			return true;
		}

		return false;
	}

	public static int validateFriendlyURL(String friendlyURL) {
		return validateFriendlyURL(friendlyURL, true);
	}

	/**
	 * Checks if the given friendlyURL is considered valid - e.g. length and
	 * certain syntactic restrictions are met.
	 *
	 * @param  friendlyURL the URL to be checked
	 * @param  checkMaxLength
	 * @return error code, can be found as constants in {@link
	 *         LayoutFriendlyURLException}, -1 if URL validates correctly
	 */
	public static int validateFriendlyURL(
		String friendlyURL, boolean checkMaxLength) {

		if (friendlyURL.length() < 2) {
			return LayoutFriendlyURLException.TOO_SHORT;
		}

		if (checkMaxLength &&
			(friendlyURL.length() > LayoutConstants.FRIENDLY_URL_MAX_LENGTH)) {

			return LayoutFriendlyURLException.TOO_LONG;
		}

		if (!friendlyURL.startsWith(StringPool.SLASH)) {
			return LayoutFriendlyURLException.DOES_NOT_START_WITH_SLASH;
		}

		if (friendlyURL.endsWith(StringPool.SLASH)) {
			return LayoutFriendlyURLException.ENDS_WITH_SLASH;
		}

		if (friendlyURL.contains(StringPool.DOUBLE_SLASH)) {
			return LayoutFriendlyURLException.ADJACENT_SLASHES;
		}

		for (char c : friendlyURL.toCharArray()) {
			if (!Validator.isChar(c) && !Validator.isDigit(c) &&
				(c != CharPool.DASH) && (c != CharPool.PERCENT) &&
				(c != CharPool.PERIOD) && (c != CharPool.PLUS) &&
				(c != CharPool.SLASH) && (c != CharPool.STAR) &&
				(c != CharPool.UNDERLINE)) {

				return LayoutFriendlyURLException.INVALID_CHARACTERS;
			}
		}

		return -1;
	}

	public static void validateFriendlyURLKeyword(String friendlyURL)
		throws LayoutFriendlyURLException {

		String keyword = _getFriendlyURLKeyword(friendlyURL);

		if (Validator.isNotNull(keyword)) {
			LayoutFriendlyURLException lfurle = new LayoutFriendlyURLException(
				LayoutFriendlyURLException.KEYWORD_CONFLICT);

			lfurle.setKeywordConflict(keyword);

			throw lfurle;
		}
	}

	public LayoutImpl() {
	}

	/**
	 * Retrieve all layouts that are direct or indirect children of this Layout.
	 */
	@Override
	public List<Layout> getAllChildren() {
		List<Layout> layouts = new ArrayList<Layout>();

		for (Layout layout : getChildren()) {
			layouts.add(layout);
			layouts.addAll(layout.getAllChildren());
		}

		return layouts;
	}

	/**
	 * Retrieve the ID of the toplevel layout, e.g. n-th parent of this layout,
	 * that does not have any parent itself
	 *
	 * @return the ID of the topmost parent layout of this layout
	 */
	@Override
	public long getAncestorLayoutId() throws PortalException {
		long layoutId = 0;

		Layout layout = this;

		while (true) {
			if (!layout.isRootLayout()) {
				layout = LayoutLocalServiceUtil.getLayout(
					layout.getGroupId(), layout.isPrivateLayout(),
					layout.getParentLayoutId());
			}
			else {
				layoutId = layout.getLayoutId();

				break;
			}
		}

		return layoutId;
	}

	/**
	 * Retrieve the plid of the toplevel layout, e.g. n-th parent of this
	 * layout, that does not have any parent itself
	 *
	 * @return the plid of the topmost parent layout of this layout
	 */
	@Override
	public long getAncestorPlid() throws PortalException {
		long plid = 0;

		Layout layout = this;

		while (true) {
			if (!layout.isRootLayout()) {
				layout = LayoutLocalServiceUtil.getLayout(
					layout.getGroupId(), layout.isPrivateLayout(),
					layout.getParentLayoutId());
			}
			else {
				plid = layout.getPlid();

				break;
			}
		}

		return plid;
	}

	/**
	 * recursively retrieve the list of parents of this layout. Ordered as
	 * direct parent layout first, most distant last
	 */
	@Override
	public List<Layout> getAncestors() throws PortalException {
		List<Layout> layouts = new ArrayList<Layout>();

		Layout layout = this;

		while (!layout.isRootLayout()) {
			layout = LayoutLocalServiceUtil.getLayout(
				layout.getGroupId(), layout.isPrivateLayout(),
				layout.getParentLayoutId());

			layouts.add(layout);
		}

		return layouts;
	}

	/**
	 * Retrieve all child layouts of this layout, independent of access
	 * permissions
	 *
	 * @return the list of all child layouts
	 */
	@Override
	public List<Layout> getChildren() {
		return LayoutLocalServiceUtil.getLayouts(
			getGroupId(), isPrivateLayout(), getLayoutId());
	}

	/**
	 * Retrieve all child layouts of this layout that the permission checker
	 * gives access to.
	 *
	 * @param  permissionChecker user-specific context to check permissions
	 * @return the child layouts that the given user has access to
	 */
	@Override
	public List<Layout> getChildren(PermissionChecker permissionChecker)
		throws PortalException {

		List<Layout> layouts = ListUtil.copy(getChildren());

		Iterator<Layout> itr = layouts.iterator();

		while (itr.hasNext()) {
			Layout layout = itr.next();

			if (layout.isHidden() ||
				!LayoutPermissionUtil.contains(
					permissionChecker, layout, ActionKeys.VIEW)) {

				itr.remove();
			}
		}

		return layouts;
	}

	/**
	 * Retrieve the color scheme that is configured for this layout or - if
	 * unonfigured - of the layout set that contains this layout.
	 *
	 * @return the color scheme to be used for this layout
	 */
	@Override
	public ColorScheme getColorScheme() throws PortalException {
		if (isInheritLookAndFeel()) {
			return getLayoutSet().getColorScheme();
		}
		else {
			return ThemeLocalServiceUtil.getColorScheme(
				getCompanyId(), getTheme().getThemeId(), getColorSchemeId(),
				false);
		}
	}

	/**
	 * Layouts and {@link LayoutSet}s can configure CSS that's applied in
	 * addition to the theme's CSS. Retrieve this CSS text, either for this page
	 * or for the whole Layoutset (if unconfigured for this layout)
	 *
	 * @return the extra CSS to be used on this layout
	 */
	@Override
	public String getCssText() throws PortalException {
		if (isInheritLookAndFeel()) {
			return getLayoutSet().getCss();
		}
		else {
			return getCss();
		}
	}

	@Override
	public String getDefaultThemeSetting(
		String key, String device, boolean inheritLookAndFeel) {

		if (!inheritLookAndFeel) {
			try {
				Theme theme = getTheme(device);

				return theme.getSetting(key);
			}
			catch (Exception e) {
			}
		}

		try {
			LayoutSet layoutSet = getLayoutSet();

			return layoutSet.getThemeSetting(key, device);
		}
		catch (Exception e) {
		}

		return StringPool.BLANK;
	}

	/**
	 * retrieve this layout's friendly URL value for the given locale
	 *
	 * @param  locale the locale that the URL value should be retrieved for
	 * @return the matching friendly URL value
	 */
	@Override
	public String getFriendlyURL(Locale locale) {
		Layout layout = this;

		String friendlyURL = layout.getFriendlyURL();

		try {
			LayoutFriendlyURL layoutFriendlyURL =
				LayoutFriendlyURLLocalServiceUtil.getLayoutFriendlyURL(
					layout.getPlid(), LocaleUtil.toLanguageId(locale));

			friendlyURL = layoutFriendlyURL.getFriendlyURL();
		}
		catch (Exception e) {
		}

		return friendlyURL;
	}

	/**
	 * Retrieve the friendly URLs for all configured locales
	 */
	@Override
	public Map<Locale, String> getFriendlyURLMap() {
		Map<Locale, String> friendlyURLMap = new HashMap<Locale, String>();

		List<LayoutFriendlyURL> layoutFriendlyURLs =
			LayoutFriendlyURLLocalServiceUtil.getLayoutFriendlyURLs(getPlid());

		for (LayoutFriendlyURL layoutFriendlyURL : layoutFriendlyURLs) {
			friendlyURLMap.put(
				LocaleUtil.fromLanguageId(layoutFriendlyURL.getLanguageId()),
				layoutFriendlyURL.getFriendlyURL());
		}

		return friendlyURLMap;
	}

	@Override
	public String getFriendlyURLsXML() {
		Map<Locale, String> friendlyURLMap = getFriendlyURLMap();

		return LocalizationUtil.updateLocalization(
			friendlyURLMap, StringPool.BLANK, "FriendlyURL",
			LocaleUtil.toLanguageId(LocaleUtil.getDefault()));
	}

	/**
	 * Retrieve this layout's group. Think of group as the technical name for a
	 * Site.
	 */
	@Override
	public Group getGroup() throws PortalException {
		return GroupLocalServiceUtil.getGroup(getGroupId());
	}

	/**
	 * Retrieve the HTML title for the given locale. Returns the layout's name
	 * if HTML title is unconfigured.
	 *
	 * @param  locale the locale that the HTML title should be retrieved for
	 * @return the matching HTML title
	 */
	@Override
	public String getHTMLTitle(Locale locale) {
		String localeLanguageId = LocaleUtil.toLanguageId(locale);

		return getHTMLTitle(localeLanguageId);
	}

	/**
	 * Retrieve the HTML title for the given locale ID. Returns the layout's
	 * name if HTML title is unconfigured.
	 *
	 * @param  localeLanguageId the locale that the HTML title should be
	 *         retrieved for
	 * @return the matching HTML title
	 */
	@Override
	public String getHTMLTitle(String localeLanguageId) {
		String htmlTitle = getTitle(localeLanguageId);

		if (Validator.isNull(htmlTitle)) {
			htmlTitle = getName(localeLanguageId);
		}

		return htmlTitle;
	}

	/**
	 * Determine if this layout has a configured icon
	 *
	 * @return <code>true</code> if an icon has been configured,
	 *         <code>false</code> otherwise
	 */
	@Override
	public boolean getIconImage() {
		if (getIconImageId() > 0) {
			return true;
		}

		return false;
	}

	/**
	 * retrieve this layout's {@link LayoutSet}
	 *
	 * @return this layout's LayoutSet
	 */
	@Override
	public LayoutSet getLayoutSet() throws PortalException {
		if (_layoutSet == null) {
			_layoutSet = LayoutSetLocalServiceUtil.getLayoutSet(
				getGroupId(), isPrivateLayout());
		}

		return _layoutSet;
	}

	/**
	 * retrieve this layout's {@link LayoutType}
	 *
	 * @return this layout's LayoutType
	 */
	@Override
	public LayoutType getLayoutType() {
		if (_layoutType == null) {
			_layoutType = new LayoutTypePortletImpl(this);
		}

		return _layoutType;
	}

	/**
	 * if this layout links to another layout, retrieve the target layout.
	 * <code>Null</code> otherwise
	 *
	 * @return the target layout or <code>null</code> if not applicable
	 */
	@Override
	public Layout getLinkedToLayout() {
		long linkToLayoutId = GetterUtil.getLong(
			getTypeSettingsProperty("linkToLayoutId"));

		if (linkToLayoutId <= 0) {
			return null;
		}

		return LayoutLocalServiceUtil.fetchLayout(
			getGroupId(), isPrivateLayout(), linkToLayoutId);
	}

	/**
	 * retrieve the parent's plid or 0 if current layout is already a toplevel
	 * layout
	 *
	 * @return the parent layout's plid or 0 if this is a toplevel layout
	 */
	@Override
	public long getParentPlid() throws PortalException {
		if (getParentLayoutId() == LayoutConstants.DEFAULT_PARENT_LAYOUT_ID) {
			return 0;
		}

		Layout layout = LayoutLocalServiceUtil.getLayout(
			getGroupId(), isPrivateLayout(), getParentLayoutId());

		return layout.getPlid();
	}

	@Override
	public String getRegularURL(HttpServletRequest request)
		throws PortalException {

		return _getURL(request, false, false);
	}

	@Override
	public String getResetLayoutURL(HttpServletRequest request)
		throws PortalException {

		return _getURL(request, true, true);
	}

	@Override
	public String getResetMaxStateURL(HttpServletRequest request)
		throws PortalException {

		return _getURL(request, true, false);
	}

	@Override
	public Group getScopeGroup() throws PortalException {
		Group group = null;

		try {
			group = GroupLocalServiceUtil.getLayoutGroup(
				getCompanyId(), getPlid());
		}
		catch (NoSuchGroupException nsge) {
		}

		return group;
	}

	@Override
	public String getTarget() {
		return PortalUtil.getLayoutTarget(this);
	}

	/**
	 * retrieve this layout's theme. If not configured individually, this is the
	 * layoutSet's theme.
	 *
	 * @return the theme to be used on this layout
	 */
	@Override
	public Theme getTheme() throws PortalException {
		if (isInheritLookAndFeel()) {
			return getLayoutSet().getTheme();
		}
		else {
			return ThemeLocalServiceUtil.getTheme(
				getCompanyId(), getThemeId(), false);
		}
	}

	@Override
	public String getThemeSetting(String key, String device) {
		return getThemeSetting(key, device, isInheritLookAndFeel());
	}

	@Override
	public String getThemeSetting(
		String key, String device, boolean inheritLookAndFeel) {

		UnicodeProperties typeSettingsProperties = getTypeSettingsProperties();

		String value = typeSettingsProperties.getProperty(
			ThemeSettingImpl.namespaceProperty(device, key));

		if (value != null) {
			return value;
		}

		return getDefaultThemeSetting(key, device, inheritLookAndFeel);
	}

	@Override
	public String getTypeSettings() {
		if (_typeSettingsProperties == null) {
			return super.getTypeSettings();
		}
		else {
			return _typeSettingsProperties.toString();
		}
	}

	@Override
	public UnicodeProperties getTypeSettingsProperties() {
		if (_typeSettingsProperties == null) {
			_typeSettingsProperties = new UnicodeProperties(true);

			_typeSettingsProperties.fastLoad(super.getTypeSettings());
		}

		return _typeSettingsProperties;
	}

	@Override
	public String getTypeSettingsProperty(String key) {
		UnicodeProperties typeSettingsProperties = getTypeSettingsProperties();

		return typeSettingsProperties.getProperty(key);
	}

	@Override
	public String getTypeSettingsProperty(String key, String defaultValue) {
		UnicodeProperties typeSettingsProperties = getTypeSettingsProperties();

		return typeSettingsProperties.getProperty(key, defaultValue);
	}

	@Override
	public ColorScheme getWapColorScheme() throws PortalException {
		if (isInheritLookAndFeel()) {
			return getLayoutSet().getWapColorScheme();
		}
		else {
			return ThemeLocalServiceUtil.getColorScheme(
				getCompanyId(), getWapTheme().getThemeId(),
				getWapColorSchemeId(), true);
		}
	}

	/**
	 * Once upon a time there was a technology...
	 */
	@Override
	public Theme getWapTheme() throws PortalException {
		if (isInheritWapLookAndFeel()) {
			return getLayoutSet().getWapTheme();
		}
		else {
			return ThemeLocalServiceUtil.getTheme(
				getCompanyId(), getWapThemeId(), true);
		}
	}

	/**
	 * determine if the given ID describes one of the (hierarchical) parents of
	 * this layout
	 *
	 * @param  layoutId the ID that the parent list will be searched for
	 * @return <code>true</code> if the given layoutId describes one of the
	 *         layout's parents.
	 */
	@Override
	public boolean hasAncestor(long layoutId) throws PortalException {
		long parentLayoutId = getParentLayoutId();

		while (parentLayoutId != LayoutConstants.DEFAULT_PARENT_LAYOUT_ID) {
			if (parentLayoutId == layoutId) {
				return true;
			}

			Layout parentLayout = LayoutLocalServiceUtil.getLayout(
				getGroupId(), isPrivateLayout(), parentLayoutId);

			parentLayoutId = parentLayout.getParentLayoutId();
		}

		return false;
	}

	/**
	 * Determine if this layout has child layouts
	 *
	 * @return <code>true</code> if this layout has children, <code>false</code>
	 *         otherwise
	 */
	@Override
	public boolean hasChildren() {
		return LayoutLocalServiceUtil.hasLayouts(
			getGroupId(), isPrivateLayout(), getLayoutId());
	}

	@Override
	public boolean hasScopeGroup() throws PortalException {
		Group group = getScopeGroup();

		if (group != null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isChildSelected(boolean selectable, Layout layout)
		throws PortalException {

		if (selectable) {
			long plid = getPlid();

			List<Layout> ancestors = layout.getAncestors();

			for (Layout curLayout : ancestors) {
				if (plid == curLayout.getPlid()) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Determine if this layout can be used as a content display page. A content
	 * display page must have an AssetPublisher portlet that is configured as
	 * the default AssetPublisher for this page.
	 *
	 * @return <code>true</code> if an appropriately configured AssetPublisher
	 *         is present on the layout
	 */
	@Override
	public boolean isContentDisplayPage() {
		UnicodeProperties typeSettingsProperties = getTypeSettingsProperties();

		String defaultAssetPublisherPortletId =
			typeSettingsProperties.getProperty(
				LayoutTypePortletConstants.DEFAULT_ASSET_PUBLISHER_PORTLET_ID);

		if (Validator.isNotNull(defaultAssetPublisherPortletId)) {
			return true;
		}

		return false;
	}

	/**
	 * determine if this is the first layout in its parent's list of childs
	 *
	 * @return <code>true</code> if this is the first layout in the list of its
	 *         parent's childs
	 */
	@Override
	public boolean isFirstChild() {
		if (getPriority() == 0) {
			return true;
		}

		return false;
	}

	/**
	 * Determine if this is the first toplevel layout
	 *
	 * @return <code>true</code> if this layout is the first toplevel page
	 */
	@Override
	public boolean isFirstParent() {
		if (isFirstChild() && isRootLayout()) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isIconImage() {
		return getIconImage();
	}

	/**
	 * Determine if this layout utilizes its {@link LayoutSet}s look&feel
	 * options, e.g. theme, color scheme.
	 */
	@Override
	public boolean isInheritLookAndFeel() {
		if (Validator.isNull(getThemeId()) ||
			Validator.isNull(getColorSchemeId())) {

			return true;
		}

		return false;
	}

	/**
	 * Once upon a time...
	 */
	@Override
	public boolean isInheritWapLookAndFeel() {
		if (Validator.isNull(getWapThemeId()) ||
			Validator.isNull(getWapColorSchemeId())) {

			return true;
		}

		return false;
	}

	/**
	 * Pages can be linked to PageTemplates ({@link LayoutPrototype}). Determine
	 * if this is the case for this layout.
	 *
	 * @return <code>true</code> if this layout is built from a PageTemplate
	 *         that still maintains an active connection
	 */
	@Override
	public boolean isLayoutPrototypeLinkActive() {
		if (isLayoutPrototypeLinkEnabled() &&
			Validator.isNotNull(getLayoutPrototypeUuid())) {

			return true;
		}

		return false;
	}

	/**
	 * Determine if this layout is part of the public {@link LayoutSet}. Note
	 * that this does not give any information about the actual access
	 * permissions, just about the default access options.
	 */
	@Override
	public boolean isPublicLayout() {
		return !isPrivateLayout();
	}

	/**
	 * determine if this layout is a root layout, e.g. does not have any parent
	 *
	 * @return <code>true</code> if this layout is a root layout,
	 *         <code>false</code> otherwise
	 */
	@Override
	public boolean isRootLayout() {
		if (getParentLayoutId() == LayoutConstants.DEFAULT_PARENT_LAYOUT_ID) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isSelected(
		boolean selectable, Layout layout, long ancestorPlid) {

		if (selectable) {
			long plid = getPlid();

			if ((plid == layout.getPlid()) || (plid == ancestorPlid)) {
				return true;
			}
		}

		return false;
	}
	
	/**
	 * @return <code>true</code> if this layout can hold embedded portlets
	 */
	@Override
	public boolean isSupportsEmbeddedPortlets() {
		if (isTypeArticle() || isTypeEmbedded() || isTypePanel() ||
			isTypePortlet()) {

			return true;
		}

		return false;
	}

	@Override
	public boolean isTypeArticle() {
		if (getType().equals(LayoutConstants.TYPE_ARTICLE)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isTypeControlPanel() {
		if (getType().equals(LayoutConstants.TYPE_CONTROL_PANEL)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isTypeEmbedded() {
		if (getType().equals(LayoutConstants.TYPE_EMBEDDED)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isTypeLinkToLayout() {
		if (getType().equals(LayoutConstants.TYPE_LINK_TO_LAYOUT)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isTypePanel() {
		if (getType().equals(LayoutConstants.TYPE_PANEL)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isTypePortlet() {
		if (getType().equals(LayoutConstants.TYPE_PORTLET)) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isTypeURL() {
		if (getType().equals(LayoutConstants.TYPE_URL)) {
			return true;
		}

		return false;
	}

	@Override
	public void setGroupId(long groupId) {
		super.setGroupId(groupId);

		_layoutSet = null;
	}

	@Override
	public void setLayoutSet(LayoutSet layoutSet) {
		_layoutSet = layoutSet;
	}

	@Override
	public void setPrivateLayout(boolean privateLayout) {
		super.setPrivateLayout(privateLayout);

		_layoutSet = null;
	}

	@Override
	public void setTypeSettings(String typeSettings) {
		_typeSettingsProperties = null;

		super.setTypeSettings(typeSettings);
	}

	@Override
	public void setTypeSettingsProperties(
		UnicodeProperties typeSettingsProperties) {

		_typeSettingsProperties = typeSettingsProperties;

		super.setTypeSettings(_typeSettingsProperties.toString());
	}

	protected Theme getTheme(String device) throws PortalException {
		if (device.equals("regular")) {
			return getTheme();
		}
		else {
			return getWapTheme();
		}
	}

	private static String _getFriendlyURLKeyword(String friendlyURL) {
		friendlyURL = StringUtil.toLowerCase(friendlyURL);

		for (String keyword : _friendlyURLKeywords) {
			if (friendlyURL.startsWith(keyword)) {
				return keyword;
			}

			if (keyword.equals(friendlyURL + StringPool.SLASH)) {
				return friendlyURL;
			}
		}

		return null;
	}

	private static void _initFriendlyURLKeywords() {
		_friendlyURLKeywords =
			new String[PropsValues.LAYOUT_FRIENDLY_URL_KEYWORDS.length];

		for (int i = 0; i < PropsValues.LAYOUT_FRIENDLY_URL_KEYWORDS.length;
				i++) {

			String keyword = PropsValues.LAYOUT_FRIENDLY_URL_KEYWORDS[i];

			keyword = StringPool.SLASH + keyword;

			if (!keyword.contains(StringPool.PERIOD)) {
				if (keyword.endsWith(StringPool.STAR)) {
					keyword = keyword.substring(0, keyword.length() - 1);
				}
				else {
					keyword = keyword + StringPool.SLASH;
				}
			}

			_friendlyURLKeywords[i] = StringUtil.toLowerCase(keyword);
		}
	}

	private LayoutTypePortlet _getLayoutTypePortletClone(
			HttpServletRequest request)
		throws IOException {

		LayoutTypePortlet layoutTypePortlet = null;

		LayoutClone layoutClone = LayoutCloneFactory.getInstance();

		if (layoutClone != null) {
			String typeSettings = layoutClone.get(request, getPlid());

			if (typeSettings != null) {
				UnicodeProperties typeSettingsProperties =
					new UnicodeProperties(true);

				typeSettingsProperties.load(typeSettings);

				String stateMax = typeSettingsProperties.getProperty(
					LayoutTypePortletConstants.STATE_MAX);
				String stateMin = typeSettingsProperties.getProperty(
					LayoutTypePortletConstants.STATE_MIN);

				Layout layout = (Layout)this.clone();

				layoutTypePortlet = (LayoutTypePortlet)layout.getLayoutType();

				layoutTypePortlet.setStateMax(stateMax);
				layoutTypePortlet.setStateMin(stateMin);
			}
		}

		if (layoutTypePortlet == null) {
			layoutTypePortlet = (LayoutTypePortlet)getLayoutType();
		}

		return layoutTypePortlet;
	}

	private String _getURL(
			HttpServletRequest request, boolean resetMaxState,
			boolean resetRenderParameters)
		throws PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (resetMaxState) {
			Layout layout = themeDisplay.getLayout();

			LayoutTypePortlet layoutTypePortlet = null;

			if (layout.equals(this)) {
				layoutTypePortlet = themeDisplay.getLayoutTypePortlet();
			}
			else {
				try {
					layoutTypePortlet = _getLayoutTypePortletClone(request);
				}
				catch (IOException ioe) {
					_log.error("Unable to clone layout settings", ioe);

					layoutTypePortlet = (LayoutTypePortlet)getLayoutType();
				}
			}

			if (layoutTypePortlet.hasStateMax()) {
				String portletId = StringUtil.split(
					layoutTypePortlet.getStateMax())[0];

				PortletURLImpl portletURLImpl = new PortletURLImpl(
					request, portletId, getPlid(), PortletRequest.ACTION_PHASE);

				try {
					portletURLImpl.setWindowState(WindowState.NORMAL);
					portletURLImpl.setPortletMode(PortletMode.VIEW);
				}
				catch (PortletException pe) {
					throw new SystemException(pe);
				}

				portletURLImpl.setAnchor(false);

				if (PropsValues.LAYOUT_DEFAULT_P_L_RESET &&
					!resetRenderParameters) {

					portletURLImpl.setParameter("p_l_reset", "0");
				}
				else if (!PropsValues.LAYOUT_DEFAULT_P_L_RESET &&
						 resetRenderParameters) {

					portletURLImpl.setParameter("p_l_reset", "1");
				}

				return portletURLImpl.toString();
			}
		}

		String portalURL = PortalUtil.getPortalURL(request);

		String url = PortalUtil.getLayoutURL(this, themeDisplay);

		if (!CookieKeys.hasSessionId(request) &&
			(url.startsWith(portalURL) || url.startsWith(StringPool.SLASH))) {

			url = PortalUtil.getURLWithSessionId(
				url, request.getSession().getId());
		}

		if (!resetMaxState) {
			return url;
		}

		if (PropsValues.LAYOUT_DEFAULT_P_L_RESET && !resetRenderParameters) {
			url = HttpUtil.addParameter(url, "p_l_reset", 0);
		}
		else if (!PropsValues.LAYOUT_DEFAULT_P_L_RESET &&
				 resetRenderParameters) {

			url = HttpUtil.addParameter(url, "p_l_reset", 1);
		}

		return url;
	}

	private static Log _log = LogFactoryUtil.getLog(LayoutImpl.class);

	private static String[] _friendlyURLKeywords;

	private LayoutSet _layoutSet;
	private LayoutType _layoutType;
	private UnicodeProperties _typeSettingsProperties;

	static {
		_initFriendlyURLKeywords();
	}

}