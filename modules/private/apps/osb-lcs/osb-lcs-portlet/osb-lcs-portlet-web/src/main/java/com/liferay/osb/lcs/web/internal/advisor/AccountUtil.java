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

package com.liferay.osb.lcs.account.util;

import com.liferay.lcs.notification.LCSEventType;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.LCSMessage;
import com.liferay.osb.lcs.model.LCSNotification;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.model.LCSRoleConstants;
import com.liferay.osb.lcs.navigation.util.NavigationUtil;
import com.liferay.osb.lcs.service.LCSClusterEntryServiceUtil;
import com.liferay.osb.lcs.service.LCSClusterNodeServiceUtil;
import com.liferay.osb.lcs.service.LCSMessageService;
import com.liferay.osb.lcs.service.LCSNotificationServiceUtil;
import com.liferay.osb.lcs.service.LCSProjectServiceUtil;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ClassNameLocalServiceUtil;

import java.text.DateFormat;
import java.text.Format;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.portlet.PortletConfig;

import javax.servlet.jsp.PageContext;

/**
 * @author Marko Cikos
 */
public class AccountUtil {

	public static List<Object[]> getLCSNotificationsRulesObjectArrays(
			PageContext pageContext, List<LCSNotification> lcsNotifications)
		throws PortalException, SystemException {

		List<Object[]> lcsNotificationsRules = new ArrayList<Object[]>();

		String lcsClusterObjectNameAll = LanguageUtil.get(pageContext, "all");

		for (LCSNotification lcsNotification : lcsNotifications) {
			long lcsClusterEntryId = LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID;
			String lcsClusterEntryName = lcsClusterObjectNameAll;
			long lcsClusterNodeId = LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID;
			String lcsClusterNodeName = lcsClusterObjectNameAll;
			long lcsProjectId = 0;

			if (lcsNotification.isLCSProjectNotification()) {
				lcsProjectId = lcsNotification.getClassPK();
			}
			else if (lcsNotification.isLCSClusterEntryNotification()) {
				LCSClusterEntry lcsClusterEntry =
					LCSClusterEntryServiceUtil.getLCSClusterEntry(
						lcsNotification.getClassPK());

				lcsClusterEntryId = lcsClusterEntry.getLcsClusterEntryId();
				lcsClusterEntryName = lcsClusterEntry.getName();
				lcsProjectId = lcsClusterEntry.getLcsProjectId();
			}
			else {
				LCSClusterNode lcsClusterNode =
					LCSClusterNodeServiceUtil.getLCSClusterNode(
						lcsNotification.getClassPK());

				LCSClusterEntry lcsClusterEntry =
					LCSClusterEntryServiceUtil.getLCSClusterEntry(
						lcsClusterNode.getLcsClusterEntryId());

				lcsClusterEntryId = lcsClusterNode.getLcsClusterEntryId();
				lcsClusterEntryName = lcsClusterEntry.getName();
				lcsClusterNodeId = lcsClusterNode.getLcsClusterNodeId();
				lcsClusterNodeName = lcsClusterNode.getName();
				lcsProjectId = lcsClusterEntry.getLcsProjectId();
			}

			boolean duplicate = false;

			for (Object[] lcsNotificationsRule : lcsNotificationsRules) {
				if (((Long)lcsNotificationsRule[0] == lcsClusterEntryId) &&
					((Long)lcsNotificationsRule[2] == lcsClusterNodeId) &&
					((Long)lcsNotificationsRule[4] == lcsProjectId)) {

					duplicate = true;

					break;
				}
			}

			if (duplicate) {
				continue;
			}

			LCSProject lcsProject = LCSProjectServiceUtil.getLCSProject(
				lcsProjectId);

			Object[] lcsNotificationsRule = {
				lcsClusterEntryId, lcsClusterEntryName, lcsClusterNodeId,
				lcsClusterNodeName, lcsProjectId, lcsProject.getName()
			};

			lcsNotificationsRules.add(lcsNotificationsRule);
		}

		return ListUtil.sort(
			lcsNotificationsRules,
			new LCSNotificationsRulesComparator(lcsClusterObjectNameAll));
	}

	public static JSONArray getLCSProjectsLCSNotificationsJSONArray()
		throws PortalException, SystemException {

		JSONArray lcsProjectsJSONArray = JSONFactoryUtil.createJSONArray();

		List<LCSProject> lcsProjects = LCSProjectServiceUtil.getUserLCSProjects(
			false,
			LCSRoleConstants.ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER);

		for (LCSProject lcsProject : lcsProjects) {
			JSONObject lcsProjectJSON = JSONFactoryUtil.createJSONObject();

			lcsProjectJSON.put("id", lcsProject.getLcsProjectId());
			lcsProjectJSON.put("name", lcsProject.getName());

			JSONArray lcsNotificationsJSONArray =
				JSONFactoryUtil.createJSONArray();

			Map<Integer, Boolean> notificationTypesEnabled =
				new HashMap<Integer, Boolean>();

			for (LCSEventType lcsEventType : LCSEventType.getSupported()) {
				LCSNotification lcsNotification =
					LCSNotificationServiceUtil.fetchLCSProjectLCSNotification(
						lcsProject.getLcsProjectId(), lcsEventType.getType());

				boolean enabled = false;
				boolean inherited = true;

				if (lcsNotification != null) {
					enabled = lcsNotification.getEnabled();
					inherited = false;
				}

				notificationTypesEnabled.put(lcsEventType.getType(), enabled);

				JSONObject lcsNotificationJSONObject =
					getLCSNotificationJSONObject(
						enabled, inherited, lcsEventType.getType());

				lcsNotificationsJSONArray.put(lcsNotificationJSONObject);
			}

			JSONArray lcsClusterEntriesLCSNotificationsJSONArray =
				getLCSClusterEntriesLCSNotificationsJSONArray(
					lcsProject.getLcsProjectId(), notificationTypesEnabled);

			lcsProjectJSON.put(
				"environments", lcsClusterEntriesLCSNotificationsJSONArray);
			lcsProjectJSON.put("notifications", lcsNotificationsJSONArray);

			lcsProjectsJSONArray.put(lcsProjectJSON);
		}

		return lcsProjectsJSONArray;
	}

	public static JSONArray getUserLCSMessagesJSONArray(
			Date startDate, Date endDate, Locale locale, TimeZone timeZone,
			PortletConfig portletConfig)
		throws PortalException, SystemException {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<LCSMessage> lcsMessages = _lcsMessageService.getLCSMessages(
			startDate, endDate);

		for (LCSMessage lcsMessage : lcsMessages) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			long classNameId = lcsMessage.getClassNameId();
			long classPK = lcsMessage.getClassPK();

			String name = null;
			String url = null;

			if (classNameId == ClassNameLocalServiceUtil.getClassNameId(
					LCSProject.class)) {

				LCSProject lcsProject = LCSProjectServiceUtil.getLCSProject(
					classPK);

				name = lcsProject.getName();
				url = NavigationUtil.getLCSProjectURL(classPK);
			}

			if (classNameId == ClassNameLocalServiceUtil.getClassNameId(
					LCSClusterEntry.class)) {

				LCSClusterEntry lcsClusterEntry =
					LCSClusterEntryServiceUtil.getLCSClusterEntry(classPK);

				name = lcsClusterEntry.getName();
				url = NavigationUtil.getLCSClusterEntryURL(classPK);
			}

			if (classNameId == ClassNameLocalServiceUtil.getClassNameId(
					LCSClusterNode.class)) {

				LCSClusterNode lcsClusterNode =
					LCSClusterNodeServiceUtil.getLCSClusterNode(classPK);

				name = lcsClusterNode.getName();
				url = NavigationUtil.getLCSClusterNodeURL(classPK);
			}

			String content = lcsMessage.getContent();
			String from = LanguageUtil.get(
				portletConfig, locale, "liferay-support");

			LCSEventType lcsEventType = LCSEventType.valueOf(
				lcsMessage.getType());

			if (lcsEventType ==
					LCSEventType.OSB_SUBSCRIPTION_STATUS_RECEIVED) {

				content = getSubscriptionsStatusContent(
					content, classPK, locale, portletConfig);
			}

			if (Validator.equals(
					lcsMessage.getSourceSystemName(),
					LCSConstants.SOURCE_SYSTEM_NAME_LCS)) {

				content = LanguageUtil.format(
					portletConfig, locale, lcsEventType.getMessage(),
					new Object[] {url, name}, false);

				from = LanguageUtil.get(
					portletConfig, locale, "liferay-connected-services");
			}

			jsonObject.put("content", content);
			jsonObject.put("from", from);

			Format dateTimeFormat = FastDateFormatFactoryUtil.getDateTime(
				DateFormat.MEDIUM, DateFormat.MEDIUM, locale, timeZone);

			Date modifiedDate = lcsMessage.getModifiedDate();

			jsonObject.put("timeLabel", dateTimeFormat.format(modifiedDate));
			jsonObject.put("timestamp", modifiedDate.getTime());

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	protected static JSONArray getLCSClusterEntriesLCSNotificationsJSONArray(
			long lcsProjectId,
			Map<Integer, Boolean> parentLCSNotificationTypesEnabled)
		throws PortalException, SystemException {

		JSONArray lcsClusterEntriesJSONArray =
			JSONFactoryUtil.createJSONArray();

		List<LCSClusterEntry> lcsClusterEntries =
			LCSClusterEntryServiceUtil.getLCSProjectLCSClusterEntries(
				lcsProjectId);

		for (LCSClusterEntry lcsClusterEntry : lcsClusterEntries) {
			JSONObject lcsClusterEntryJSON = JSONFactoryUtil.createJSONObject();

			long lcsClusterEntryId = lcsClusterEntry.getLcsClusterEntryId();

			lcsClusterEntryJSON.put("cluster", lcsClusterEntry.isCluster());
			lcsClusterEntryJSON.put("id", lcsClusterEntryId);
			lcsClusterEntryJSON.put("name", lcsClusterEntry.getName());

			JSONArray lcsNotificationsJSONArray =
				JSONFactoryUtil.createJSONArray();

			Map<Integer, Boolean> notificationTypesEnabled =
				new HashMap<Integer, Boolean>();

			for (LCSEventType lcsEventType : LCSEventType.getSupported()) {
				LCSNotification lcsNotification =
					LCSNotificationServiceUtil.
						fetchLCSClusterEntryLCSNotification(
							lcsClusterEntryId, lcsEventType.getType());

				boolean enabled = parentLCSNotificationTypesEnabled.get(
					lcsEventType.getType());
				boolean inherited = true;

				if (lcsNotification != null) {
					enabled = lcsNotification.getEnabled();
					inherited = false;
				}

				notificationTypesEnabled.put(lcsEventType.getType(), enabled);

				JSONObject lcsNotificationJSONObject =
					getLCSNotificationJSONObject(
						enabled, inherited, lcsEventType.getType());

				lcsNotificationsJSONArray.put(lcsNotificationJSONObject);
			}

			lcsClusterEntryJSON.put("notifications", lcsNotificationsJSONArray);
			lcsClusterEntryJSON.put(
				"servers",
				getLCSClusterNodesLCSNotificationsJSONArray(
					lcsClusterEntryId, notificationTypesEnabled));

			lcsClusterEntriesJSONArray.put(lcsClusterEntryJSON);
		}

		return lcsClusterEntriesJSONArray;
	}

	protected static JSONArray getLCSClusterNodesLCSNotificationsJSONArray(
			long lcsClusterEntryId, Map<Integer,
			Boolean> parentLCSNotificationTypesEnabled)
		throws PortalException, SystemException {

		JSONArray lcsClusterNodesJSONArray = JSONFactoryUtil.createJSONArray();

		List<LCSClusterNode> lcsClusterNodes =
			LCSClusterNodeServiceUtil.getLCSClusterEntryLCSClusterNodes(
				lcsClusterEntryId);

		for (LCSClusterNode lcsClusterNode : lcsClusterNodes) {
			JSONObject lcsClusterNodeJSON = JSONFactoryUtil.createJSONObject();

			lcsClusterNodeJSON.put("id", lcsClusterNode.getLcsClusterNodeId());
			lcsClusterNodeJSON.put("name", lcsClusterNode.getName());

			JSONArray lcsNotificationsJSONArray =
				JSONFactoryUtil.createJSONArray();

			for (LCSEventType lcsEventType : LCSEventType.getSupported()) {
				LCSNotification lcsNotification =
					LCSNotificationServiceUtil.
						fetchLCSClusterNodeLCSNotification(
							lcsClusterNode.getLcsClusterNodeId(),
							lcsEventType.getType());

				boolean enabled = parentLCSNotificationTypesEnabled.get(
					lcsEventType.getType());
				boolean inherited = true;

				if (lcsNotification != null) {
					enabled = lcsNotification.getEnabled();
					inherited = false;
				}

				JSONObject lcsNotificationJSONObject =
					getLCSNotificationJSONObject(
						enabled, inherited, lcsEventType.getType());

				lcsNotificationsJSONArray.put(lcsNotificationJSONObject);
			}

			lcsClusterNodeJSON.put("notifications", lcsNotificationsJSONArray);

			lcsClusterNodesJSONArray.put(lcsClusterNodeJSON);
		}

		return lcsClusterNodesJSONArray;
	}

	protected static JSONObject getLCSNotificationJSONObject(
		boolean enabled, boolean inherited, int type) {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("enabled", enabled);
		jsonObject.put("inherited", inherited);
		jsonObject.put("type", type);

		return jsonObject;
	}

	protected static String getSubscriptionsStatusContent(
		String content, long lcsProjectId, Locale locale,
		PortletConfig portletConfig) {

		StringBundler sb = new StringBundler(6);

		sb.append(content);
		sb.append(" <a href='");
		sb.append(NavigationUtil.getSubscriptionsURL(lcsProjectId));
		sb.append("'>");
		sb.append(LanguageUtil.get(portletConfig, locale, "see-subscriptions"));
		sb.append("</a>");

		return sb.toString();
	}

	@BeanReference(type = LCSMessageService.class)
	private static LCSMessageService _lcsMessageService;

	private static class LCSNotificationsRulesComparator
		implements Comparator<Object[]> {

		public LCSNotificationsRulesComparator(String lcsClusterObjectNameAll) {
			_lcsClusterObjectNameAll = lcsClusterObjectNameAll;
		}

		@Override
		public int compare(Object[] objectArray1, Object[] objectArray2) {
			String lcsClusterEntryName1 = GetterUtil.getString(objectArray1[1]);
			String lcsClusterNodeName1 = GetterUtil.getString(objectArray1[3]);
			String lcsProjectName1 = GetterUtil.getString(objectArray1[5]);

			String lcsClusterEntryName2 = GetterUtil.getString(objectArray2[1]);
			String lcsClusterNodeName2 = GetterUtil.getString(objectArray2[3]);
			String lcsProjectName2 = GetterUtil.getString(objectArray2[5]);

			if (!lcsProjectName1.equals(lcsProjectName2)) {
				return lcsProjectName1.compareTo(lcsProjectName2);
			}

			if (!lcsClusterEntryName1.equals(lcsClusterEntryName2)) {
				if (lcsClusterEntryName1.equals(_lcsClusterObjectNameAll)) {
					return -1;
				}

				if (lcsClusterEntryName2.equals(_lcsClusterObjectNameAll)) {
					return 1;
				}

				return lcsClusterEntryName1.compareTo(lcsClusterEntryName2);
			}

			if (lcsClusterNodeName1.equals(_lcsClusterObjectNameAll)) {
				return -1;
			}

			if (lcsClusterNodeName2.equals(_lcsClusterObjectNameAll)) {
				return 1;
			}

			return lcsClusterNodeName1.compareTo(lcsClusterNodeName2);
		}

		private String _lcsClusterObjectNameAll;

	};

}