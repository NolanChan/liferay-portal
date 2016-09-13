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

package com.liferay.osb.lcs.account.portlet;

import com.liferay.lcs.util.LCSConstants;
import com.liferay.osb.lcs.account.util.AccountUtil;
import com.liferay.osb.lcs.advisor.UserAdvisor;
import com.liferay.osb.lcs.model.LCSNotification;
import com.liferay.osb.lcs.service.LCSNotificationServiceUtil;
import com.liferay.osb.lcs.util.AuthUtil;
import com.liferay.osb.lcs.util.PortletKeys;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.util.bean.PortletBeanLocatorUtil;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Ivica Cardic
 * @author Marko Cikos
 * @author Matija Petanjek
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + PortletKeys.ACCOUNT,
		"javax.portlet.display-name=Account",
		"javax.portlet.init-param.copy-request-parameters=true",
		"javax.portlet.init-param.template-path=/account/",
		"javax.portlet.init-param.view-template=/account/view.jsp",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.mime-type=text/html",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.info.title=Account",
		"javax.portlet.info.short-title=Account",
		"javax.portlet.info.keywords=Account",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supported-public-render-parameter=layoutLCSClusterEntryId",
		"javax.portlet.supported-public-render-parameter=layoutLCSClusterNodeId",
		"javax.portlet.supported-public-render-parameter=layoutLCSProjectId",
		"com.liferay.portlet.footer-portlet-javascript=/js/lcs-account.js",
		"com.liferay.portlet.footer-portlet-javascript=/js/lcs-base.js",
		"com.liferay.portlet.css-class-wrapper=osb-lcs-portlet osb-lcs-portlet-account",
		"com.liferay.portlet.display-category=category.lcs"
	},
	service = Portlet.class
)
public class AccountPortlet extends MVCPortlet {

	public void deleteLCSNotifications(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long lcsClusterEntryId = ParamUtil.getLong(
			actionRequest, "lcsClusterEntryId");
		long lcsClusterNodeId = ParamUtil.getLong(
			actionRequest, "lcsClusterNodeId");
		long lcsProjectId = ParamUtil.getLong(actionRequest, "lcsProjectId");

		List<LCSNotification> lcsNotifications = new ArrayList<>();

		if (lcsClusterNodeId != LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID) {
			lcsNotifications =
				LCSNotificationServiceUtil.getLCSClusterNodeLCSNotifications(
					lcsClusterNodeId);
		}
		else if (lcsClusterEntryId != LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID) {
			lcsNotifications =
				LCSNotificationServiceUtil.getLCSClusterEntryLCSNotifications(
					lcsClusterEntryId);
		}
		else {
			lcsNotifications =
				LCSNotificationServiceUtil.getLCSProjectLCSNotifications(
					lcsProjectId);
		}

		for (LCSNotification lcsNotification : lcsNotifications) {
			LCSNotificationServiceUtil.deleteLCSNotification(
				lcsNotification.getLcsNotificationId());
		}
	}

	@Override
	public void destroy() {
		super.destroy();

		_userAdvisor = null;
	}

	@Override
	public void init() throws PortletException {
		super.init();

		_userAdvisor = (UserAdvisor)PortletBeanLocatorUtil.locate(
			"com.liferay.osb.lcs.advisor.UserAdvisor");
	}

	public void saveLCSNotifications(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long lcsClusterEntryId = ParamUtil.getLong(
			actionRequest, "lcsClusterEntryId");
		long lcsClusterNodeId = ParamUtil.getLong(
			actionRequest, "lcsClusterNodeId");
		long lcsProjectId = ParamUtil.getLong(actionRequest, "lcsProjectId");
		int[] notificationTypes = ParamUtil.getIntegerValues(
			actionRequest, "notificationTypes");
		boolean[] notifications = ParamUtil.getBooleanValues(
			actionRequest, "notifications");

		for (int i = 0; i < notifications.length; i++) {
			boolean enabled = notifications[i];
			int type = notificationTypes[i];

			if ((lcsClusterEntryId ==
					LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID) &&
				(lcsClusterNodeId == LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID)) {

				LCSNotification lcsNotification =
					LCSNotificationServiceUtil.fetchLCSProjectLCSNotification(
						lcsProjectId, type);

				if (lcsNotification == null) {
					LCSNotificationServiceUtil.addLCSProjectLCSNotification(
						lcsProjectId, enabled, type);
				}
				else {
					lcsNotification.setEnabled(enabled);

					LCSNotificationServiceUtil.updateLCSNotification(
						lcsNotification);
				}
			}
			else if (lcsClusterNodeId ==
						LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID) {

				LCSNotification lcsNotification =
					LCSNotificationServiceUtil.
						fetchLCSClusterEntryLCSNotification(
							lcsClusterEntryId, type);

				if (lcsNotification == null) {
					LCSNotificationServiceUtil.
						addLCSClusterEntryLCSNotification(
							lcsClusterEntryId, enabled, type);
				}
				else {
					lcsNotification.setEnabled(enabled);

					LCSNotificationServiceUtil.updateLCSNotification(
						lcsNotification);
				}
			}
			else {
				LCSNotification lcsNotification =
					LCSNotificationServiceUtil.
						fetchLCSClusterNodeLCSNotification(
							lcsClusterNodeId, type);

				if (lcsNotification == null) {
					LCSNotificationServiceUtil.addLCSClusterNodeLCSNotification(
						lcsClusterNodeId, enabled, type);
				}
				else {
					lcsNotification.setEnabled(enabled);

					LCSNotificationServiceUtil.updateLCSNotification(
						lcsNotification);
				}
			}
		}
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		try {
			AuthUtil.checkAuthToken(resourceRequest);

			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("getUserLCSMessages")) {
				getUserLCSMessages(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			String message = LanguageUtil.get(
				resourceRequest.getLocale(), "your-request-failed-to-complete");

			jsonObject.put(LCSConstants.JSON_KEY_MESSAGE, message);
			jsonObject.put(
				LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_FAILURE);

			writeJSON(resourceRequest, resourceResponse, jsonObject);
		}
	}

	public void updateUser(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			return;
		}

		User user = themeDisplay.getUser();

		String languageId = ParamUtil.getString(actionRequest, "languageId");
		String timeZoneId = ParamUtil.getString(actionRequest, "timeZoneId");

		user.setLanguageId(languageId);
		user.setTimeZoneId(timeZoneId);

		UserLocalServiceUtil.updateUser(user);

		long defaultLCSProjectId = ParamUtil.getLong(
			actionRequest, "defaultLCSProjectId");

		_userAdvisor.updateDefaultLCSProject(
			user.getUserId(), defaultLCSProjectId);

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		actionResponse.sendRedirect(redirect);
	}

	protected void getUserLCSMessages(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		int endDay = ParamUtil.getInteger(resourceRequest, "endDay");
		int endMonth = ParamUtil.getInteger(resourceRequest, "endMonth");
		int endYear = ParamUtil.getInteger(resourceRequest, "endYear");
		int startDay = ParamUtil.getInteger(resourceRequest, "startDay");
		int startMonth = ParamUtil.getInteger(resourceRequest, "startMonth");
		int startYear = ParamUtil.getInteger(resourceRequest, "startYear");

		Calendar calendar = Calendar.getInstance();

		calendar.setTimeZone(themeDisplay.getTimeZone());

		calendar.set(endYear, endMonth, endDay, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		calendar.add(Calendar.DATE, 1);

		calendar.add(Calendar.MILLISECOND, -1);

		Date endDate = calendar.getTime();

		calendar.set(startYear, startMonth, startDay, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Date startDate = calendar.getTime();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONArray jsonArray = AccountUtil.getUserLCSMessagesJSONArray(
			startDate, endDate, themeDisplay.getLocale(),
			themeDisplay.getTimeZone());

		jsonObject.put(LCSConstants.JSON_KEY_DATA, jsonArray);
		jsonObject.put(
			LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_SUCCESS);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	private static Log _log = LogFactoryUtil.getLog(AccountPortlet.class);

	private UserAdvisor _userAdvisor;

}