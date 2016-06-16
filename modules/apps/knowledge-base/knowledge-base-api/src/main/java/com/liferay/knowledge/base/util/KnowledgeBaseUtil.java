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

package com.liferay.knowledge.base.util;

import com.liferay.knowledge.base.constants.KBCommentConstants;
import com.liferay.knowledge.base.constants.KBFolderConstants;
import com.liferay.knowledge.base.constants.KBPortletKeys;
import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.knowledge.base.model.KBFolder;
import com.liferay.knowledge.base.service.KBArticleLocalServiceUtil;
import com.liferay.knowledge.base.service.KBFolderServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

/**
 * @author Peter Shin
 * @author Brian Wing Shun Chan
 */
public class KnowledgeBaseUtil {

	public static List<KBFolder> getAlternateRootKBFolders(
			long groupId, long kbFolderId)
		throws PortalException {

		List<KBFolder> kbFolders = KBFolderServiceUtil.getKBFolders(
			groupId, kbFolderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		kbFolders = new ArrayList<>(kbFolders);

		Iterator<KBFolder> itr = kbFolders.iterator();

		while (itr.hasNext()) {
			KBFolder kbFolder = itr.next();

			if (kbFolder.isEmpty()) {
				itr.remove();
			}
		}

		return ListUtil.sort(
			kbFolders,
			new Comparator<KBFolder>() {

				@Override
				public int compare(KBFolder kbFolder1, KBFolder kbFolder2) {
					String name1 = kbFolder1.getName();
					String name2 = kbFolder2.getName();

					return name1.compareTo(name2) * -1;
				}

			});
	}

	public static Sort[] getKBArticleSorts(
		String orderByCol, String orderByType) {

		if (Validator.isNull(orderByCol) || Validator.isNull(orderByType)) {
			return SortFactoryUtil.getDefaultSorts();
		}

		boolean reverse = true;

		if (orderByType.equals("asc")) {
			reverse = false;
		}

		if (orderByCol.equals("create-date")) {
			String fieldName = Field.CREATE_DATE;

			return new Sort[] {
				SortFactoryUtil.create(fieldName, Sort.LONG_TYPE, reverse),
				SortFactoryUtil.create(null, Sort.SCORE_TYPE, false)
			};
		}
		else if (orderByCol.equals("modified-date")) {
			String fieldName = Field.MODIFIED_DATE;

			return new Sort[] {
				SortFactoryUtil.create(fieldName, Sort.LONG_TYPE, reverse),
				SortFactoryUtil.create(null, Sort.SCORE_TYPE, false)
			};
		}
		else if (orderByCol.equals("score")) {
			String fieldName = null;

			return new Sort[] {
				SortFactoryUtil.create(fieldName, Sort.SCORE_TYPE, !reverse),
				SortFactoryUtil.create(
					Field.MODIFIED_DATE, Sort.LONG_TYPE, true)
			};
		}
		else if (orderByCol.equals("title")) {
			String fieldName = "titleKeyword";

			return new Sort[] {
				SortFactoryUtil.create(fieldName, Sort.STRING_TYPE, reverse),
				SortFactoryUtil.create(null, Sort.SCORE_TYPE, false)
			};
		}
		else if (orderByCol.equals("user-name")) {
			String fieldName = Field.USER_NAME;

			return new Sort[] {
				SortFactoryUtil.create(fieldName, Sort.STRING_TYPE, reverse),
				SortFactoryUtil.create(null, Sort.SCORE_TYPE, false)
			};
		}

		return SortFactoryUtil.getDefaultSorts();
	}

	public static String getKBArticleURL(
		long plid, long resourcePrimKey, int status, String portalURL,
		boolean maximized) {

		StringBundler sb = new StringBundler(11);

		sb.append(portalURL);
		sb.append(PortalUtil.getPathMain());
		sb.append("/portal/knowledge_base/find_kb_article");
		sb.append(StringPool.QUESTION);
		sb.append("plid");
		sb.append(StringPool.EQUAL);
		sb.append(String.valueOf(plid));
		sb.append(StringPool.AMPERSAND);
		sb.append("resourcePrimKey");
		sb.append(StringPool.EQUAL);
		sb.append(String.valueOf(resourcePrimKey));

		String url = sb.toString();

		if (status != WorkflowConstants.STATUS_APPROVED) {
			url = url.concat(StringPool.AMPERSAND).concat("status").concat(
				StringPool.EQUAL).concat(String.valueOf(status));
		}

		if (maximized) {
			url = url.concat(StringPool.AMPERSAND).concat("maximized").concat(
				StringPool.EQUAL).concat(String.valueOf(maximized));
		}

		return url;
	}

	public static long getKBFolderId(
			long parentResourceClassNameId, long parentResourcePrimKey)
		throws PortalException {

		long kbFolderClassNameId = PortalUtil.getClassNameId(
			KBFolderConstants.getClassName());

		if (parentResourceClassNameId == kbFolderClassNameId) {
			return parentResourcePrimKey;
		}

		KBArticle kbArticle = KBArticleLocalServiceUtil.getLatestKBArticle(
			parentResourcePrimKey, WorkflowConstants.STATUS_ANY);

		return kbArticle.getKbFolderId();
	}

	public static String getMimeType(byte[] bytes, String fileName) {
		InputStream inputStream = new UnsyncByteArrayInputStream(bytes);

		try {
			return MimeTypesUtil.getContentType(inputStream, fileName);
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

	public static final int getNextStatus(int status) {
		if (status == KBCommentConstants.STATUS_IN_PROGRESS) {
			return KBCommentConstants.STATUS_COMPLETED;
		}
		else if (status == KBCommentConstants.STATUS_NEW) {
			return KBCommentConstants.STATUS_IN_PROGRESS;
		}
		else {
			return KBCommentConstants.STATUS_NONE;
		}
	}

	public static Long[][] getParams(Long[] params) {
		if (ArrayUtil.isEmpty(params)) {
			return null;
		}

		if (params.length <= _SQL_DATA_MAX_PARAMETERS) {
			return new Long[][] {new Long[0], params};
		}

		return new Long[][] {
			ArrayUtil.subset(params, _SQL_DATA_MAX_PARAMETERS, params.length),
			ArrayUtil.subset(params, 0, _SQL_DATA_MAX_PARAMETERS)
		};
	}

	public static String getPreferredKBFolderURLTitle(
			PortalPreferences portalPreferences, String contentRootPrefix)
		throws JSONException {

		String preferredKBFolderURLTitle = portalPreferences.getValue(
			KBPortletKeys.KNOWLEDGE_BASE_DISPLAY, "preferredKBFolderURLTitle",
			"{}");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			preferredKBFolderURLTitle);

		return jsonObject.getString(contentRootPrefix, StringPool.BLANK);
	}

	public static final int getPreviousStatus(int status) {
		if (status == KBCommentConstants.STATUS_COMPLETED) {
			return KBCommentConstants.STATUS_IN_PROGRESS;
		}
		else if (status == KBCommentConstants.STATUS_IN_PROGRESS) {
			return KBCommentConstants.STATUS_NEW;
		}
		else {
			return KBCommentConstants.STATUS_NONE;
		}
	}

	public static long getRootResourcePrimKey(
			PortletRequest portletRequest, long groupId,
			long resourceClassNameId, long resourcePrimKey)
		throws PortalException {

		long kbFolderClassNameId = PortalUtil.getClassNameId(
			KBFolderConstants.getClassName());

		if (resourceClassNameId == kbFolderClassNameId) {
			return _getCurrentRootKBFolder(
				portletRequest, groupId, resourcePrimKey);
		}

		return getKBFolderId(resourceClassNameId, resourcePrimKey);
	}

	public static final String getStatusLabel(int status) {
		if (status == KBCommentConstants.STATUS_COMPLETED) {
			return "resolved";
		}
		else if (status == KBCommentConstants.STATUS_IN_PROGRESS) {
			return "in-progress";
		}
		else if (status == KBCommentConstants.STATUS_NEW) {
			return "new";
		}
		else {
			throw new IllegalArgumentException(
				String.format("Invalid suggestion status %s", status));
		}
	}

	public static final String getStatusTransitionLabel(int status) {
		if (status == KBCommentConstants.STATUS_COMPLETED) {
			return "resolve";
		}
		else if (status == KBCommentConstants.STATUS_IN_PROGRESS) {
			return "move-to-in-progress";
		}
		else if (status == KBCommentConstants.STATUS_NEW) {
			return "move-to-new";
		}
		else {
			throw new IllegalArgumentException(
				String.format("Invalid suggestion status %s", status));
		}
	}

	public static String getUrlTitle(long id, String title) {
		if (title == null) {
			return String.valueOf(id);
		}

		title = StringUtil.toLowerCase(title.trim());

		if (Validator.isNull(title) || Validator.isNumber(title) ||
			title.equals("rss")) {

			title = String.valueOf(id);
		}
		else {
			title = FriendlyURLNormalizerUtil.normalize(
				title, _normalizationFriendlyUrlPattern);
		}

		return ModelHintsUtil.trimString(
			KBArticle.class.getName(), "urlTitle", title);
	}

	public static boolean isValidUrlTitle(String urlTitle) {
		Matcher matcher = _validFriendlyUrlPattern.matcher(urlTitle);

		return matcher.matches();
	}

	public static void setPreferredKBFolderURLTitle(
			PortalPreferences portalPreferences, String contentRootPrefix,
			String value)
		throws JSONException {

		String preferredKBFolderURLTitle = portalPreferences.getValue(
			KBPortletKeys.KNOWLEDGE_BASE_DISPLAY, "preferredKBFolderURLTitle",
			"{}");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			preferredKBFolderURLTitle);

		jsonObject.put(contentRootPrefix, value);

		portalPreferences.setValue(
			KBPortletKeys.KNOWLEDGE_BASE_DISPLAY, "preferredKBFolderURLTitle",
			jsonObject.toString());
	}

	public static List<KBArticle> sort(
		long[] resourcePrimKeys, List<KBArticle> kbArticles) {

		Map<Long, KBArticle> map = new HashMap<>();

		for (KBArticle kbArticle : kbArticles) {
			map.put(kbArticle.getResourcePrimKey(), kbArticle);
		}

		kbArticles.clear();

		for (long resourcePrimKey : resourcePrimKeys) {
			if (map.containsKey(resourcePrimKey)) {
				kbArticles.add(map.get(resourcePrimKey));
			}
		}

		return kbArticles;
	}

	public static String[] splitKeywords(String keywords) {
		Set<String> keywordsSet = new LinkedHashSet<>();

		StringBundler sb = new StringBundler();

		for (char c : keywords.toCharArray()) {
			if (Character.isWhitespace(c)) {
				if (sb.length() > 0) {
					keywordsSet.add(sb.toString());

					sb = new StringBundler();
				}
			}
			else if (Character.isLetterOrDigit(c)) {
				sb.append(c);
			}
			else {
				return new String[] {keywords};
			}
		}

		if (sb.length() > 0) {
			keywordsSet.add(sb.toString());
		}

		return StringUtil.split(StringUtil.merge(keywordsSet));
	}

	public static String trimLeadingSlash(String s) {
		if (Validator.isNull(s)) {
			return s;
		}

		int x = 0;

		for (char c : s.toCharArray()) {
			if ((c != CharPool.BACK_SLASH) && (c != CharPool.FORWARD_SLASH)) {
				break;
			}

			x = x + 1;
		}

		return s.substring(x, s.length());
	}

	private static long _getCurrentRootKBFolder(
			PortletRequest portletRequest, long groupId, long kbFolderId)
		throws PortalException {

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(portletRequest);

		PortletPreferences portletPreferences = portletRequest.getPreferences();

		String contentRootPrefix = GetterUtil.getString(
			portletPreferences.getValue("contentRootPrefix", null));

		String kbFolderURLTitle = getPreferredKBFolderURLTitle(
			portalPreferences, contentRootPrefix);

		long childKbFolderId = KBFolderConstants.DEFAULT_PARENT_FOLDER_ID;

		if (kbFolderURLTitle == null) {
			List<KBFolder> kbFolders = getAlternateRootKBFolders(
				groupId, kbFolderId);

			if (!kbFolders.isEmpty()) {
				KBFolder kbFolder = kbFolders.get(0);

				childKbFolderId = kbFolder.getKbFolderId();
			}
		}
		else {
			KBFolder kbFolder = KBFolderServiceUtil.fetchKBFolderByUrlTitle(
				groupId, kbFolderId, kbFolderURLTitle);

			if (kbFolder != null) {
				childKbFolderId = kbFolder.getKbFolderId();
			}
		}

		return childKbFolderId;
	}

	private static final int _SQL_DATA_MAX_PARAMETERS = GetterUtil.getInteger(
		PropsUtil.get(PropsKeys.SQL_DATA_MAX_PARAMETERS));

	private static final Pattern _normalizationFriendlyUrlPattern =
		Pattern.compile("[^a-z0-9_-]");
	private static final Pattern _validFriendlyUrlPattern = Pattern.compile(
		"/[a-z0-9_-]+");

}