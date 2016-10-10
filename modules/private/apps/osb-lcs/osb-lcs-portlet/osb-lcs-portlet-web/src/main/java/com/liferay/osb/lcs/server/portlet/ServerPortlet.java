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

package com.liferay.osb.lcs.server.portlet;

import com.liferay.lcs.util.LCSConstants;
import com.liferay.osb.lcs.constants.OSBLCSPortletKeys;
import com.liferay.osb.lcs.exception.DuplicateLCSClusterNodeNameException;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeCurrentThreadsMetricsService;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeJDBCConnectionPoolMetricsService;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeJVMMetricsService;
import com.liferay.osb.lcs.service.LCSClusterNodeService;
import com.liferay.osb.lcs.web.internal.advisor.PortalInstanceAdvisor;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.Calendar;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletConfig;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 * @author Ivica Cardic
 * @author Marko Cikos
 * @author Matija Petanjek
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=osb-lcs-portlet osb-lcs-portlet-server" + OSBLCSPortletKeys.SERVER,
		"com.liferay.portlet.display-category=category.lcs",
		"com.liferay.portlet.footer-portlet-javascript=/js/jquery.min.js",
		"com.liferay.portlet.footer-portlet-javascript=/js/highcharts-base.min.js",
		"com.liferay.portlet.footer-portlet-javascript=/js/highcharts-more.min.js",
		"com.liferay.portlet.footer-portlet-javascript=/js/lcs-base.js",
		"com.liferay.portlet.footer-portlet-javascript=/js/lcs-notifications.js",
		"com.liferay.portlet.footer-portlet-javascript=/js/lcs-server.js",
		"javax.portlet.display-name=Server", "javax.portlet.expiration-cache=0",
		"javax.portlet.info.keywords=Server",
		"javax.portlet.info.short-title=Server",
		"javax.portlet.info.title=Server",
		"javax.portlet.init-param.copy-request-parameters=true",
		"javax.portlet.init-param.template-path=/server/",
		"javax.portlet.init-param.view-template=/server/view.jsp",
		"javax.portlet.mime-type=text/html", "javax.portlet.name=",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supported-public-render-parameter=layoutLCSClusterEntryId",
		"javax.portlet.supported-public-render-parameter=layoutLCSClusterNodeId",
		"javax.portlet.supported-public-render-parameter=layoutLCSProjectId"
	},
	service = Portlet.class
)
public class ServerPortlet extends MVCPortlet {

	public void deleteLCSClusterNode(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long lcsClusterNodeId = ParamUtil.getLong(
			actionRequest, "lcsClusterNodeId");

		_lcsClusterNodeService.deleteLCSClusterNode(lcsClusterNodeId);

		PortletConfig portletConfig = getPortletConfig();

		SessionMessages.add(
			actionRequest,
			portletConfig.getPortletName() +
				_KEY_SUFFIX_HIDE_DEFAULT_SUCCESS_MESSAGE);

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		actionResponse.sendRedirect(redirect);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		try {
			AuthTokenUtil.checkCSRFToken(
				PortalUtil.getHttpServletRequest(resourceRequest),
				ServerPortlet.class.getName());

			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("getLCSClusterNodeCurrentThreadsMetrics")) {
				getLCSClusterNodeCurrentThreadsMetrics(
					resourceRequest, resourceResponse);
			}
			else if (resourceID.equals(
						"getLCSClusterNodeJDBCConnectionPoolMetrics")) {

				getLCSClusterNodeJDBCConnectionPoolMetrics(
					resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("getLCSClusterNodeJVMMetrics")) {
				getLCSClusterNodeJVMMetrics(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("getPagesMetrics")) {
				getPagesMetrics(resourceRequest, resourceResponse);
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

	@Reference(unbind = "-")
	public void setLcsClusterNodeCurrentThreadsMetricsService(
		LCSClusterNodeCurrentThreadsMetricsService
			lcsClusterNodeCurrentThreadsMetricsService) {

		_lcsClusterNodeCurrentThreadsMetricsService =
			lcsClusterNodeCurrentThreadsMetricsService;
	}

	@Reference(unbind = "-")
	public void setLcsClusterNodeJDBCConnectionPoolMetricsService(
		LCSClusterNodeJDBCConnectionPoolMetricsService
			lcsClusterNodeJDBCConnectionPoolMetricsService) {

		_lcsClusterNodeJDBCConnectionPoolMetricsService =
			lcsClusterNodeJDBCConnectionPoolMetricsService;
	}

	@Reference(unbind = "-")
	public void setLcsClusterNodeJVMMetricsService(
		LCSClusterNodeJVMMetricsService lcsClusterNodeJVMMetricsService) {

		_lcsClusterNodeJVMMetricsService = lcsClusterNodeJVMMetricsService;
	}

	@Reference(unbind = "-")
	public void setLcsClusterNodeService(
		LCSClusterNodeService lcsClusterNodeService) {

		_lcsClusterNodeService = lcsClusterNodeService;
	}

	@Reference(unbind = "-")
	public void setPortalInstanceAdvisor(
		PortalInstanceAdvisor portalInstanceAdvisor) {

		_portalInstanceAdvisor = portalInstanceAdvisor;
	}

	public void updateLCSClusterNode(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long lcsClusterNodeId = ParamUtil.getLong(
			actionRequest, "lcsClusterNodeId");

		String name = ParamUtil.getString(actionRequest, "name");
		String description = ParamUtil.getString(actionRequest, "description");
		String location = ParamUtil.getString(actionRequest, "location");

		try {
			_lcsClusterNodeService.updateLCSClusterNode(
				lcsClusterNodeId, name, description, location);
		}
		catch (DuplicateLCSClusterNodeNameException dlcscnne) {
			SessionErrors.add(actionRequest, "duplicateLCSClusterNodeName");

			String redirect = ParamUtil.getString(actionRequest, "redirect");

			actionResponse.sendRedirect(redirect);
		}
	}

	protected void getLCSClusterNodeCurrentThreadsMetrics(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		String key = ParamUtil.getString(resourceRequest, "key");

		JSONArray lcsClusterNodeCurrentThreadsMetricsJSONArray =
			JSONFactoryUtil.createJSONArray(
				JSONFactoryUtil.looseSerialize(
					_lcsClusterNodeCurrentThreadsMetricsService.
						getLCSClusterNodeCurrentThreadsMetricsList(key)));

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			LCSConstants.JSON_KEY_DATA,
			lcsClusterNodeCurrentThreadsMetricsJSONArray);
		jsonObject.put(
			LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_SUCCESS);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void getLCSClusterNodeJDBCConnectionPoolMetrics(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		String key = ParamUtil.getString(resourceRequest, "key");

		JSONArray lcsClusterNodeJDBCConnectionPoolMetricsJSONArray =
			JSONFactoryUtil.createJSONArray(
				JSONFactoryUtil.looseSerialize(
					_lcsClusterNodeJDBCConnectionPoolMetricsService.
						getLCSClusterNodeJDBCConnectionPoolMetricsList(key)));

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			LCSConstants.JSON_KEY_DATA,
			lcsClusterNodeJDBCConnectionPoolMetricsJSONArray);
		jsonObject.put(
			LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_SUCCESS);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void getLCSClusterNodeJVMMetrics(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		String key = ParamUtil.getString(resourceRequest, "key");

		JSONObject lcsClusterNodeJVMMetricsJSON =
			JSONFactoryUtil.createJSONObject(
				JSONFactoryUtil.looseSerializeDeep(
					_lcsClusterNodeJVMMetricsService.
						fetchLCSClusterNodeJVMMetrics(key)));

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			LCSConstants.JSON_KEY_DATA, lcsClusterNodeJVMMetricsJSON);
		jsonObject.put(
			LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_SUCCESS);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void getPagesMetrics(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long companyId = ParamUtil.getLong(resourceRequest, "companyId");
		int endAmPm = ParamUtil.getInteger(resourceRequest, "endAmPm");
		int endDay = ParamUtil.getInteger(resourceRequest, "endDay");
		int endHour = ParamUtil.getInteger(resourceRequest, "endHour");
		int endMonth = ParamUtil.getInteger(resourceRequest, "endMonth");
		int endYear = ParamUtil.getInteger(resourceRequest, "endYear");
		long groupId = ParamUtil.getLong(resourceRequest, "groupId");
		String key = ParamUtil.getString(resourceRequest, "key");
		String layoutName = ParamUtil.getString(
			resourceRequest, "layoutName",
			LCSConstants.ALL_PORTAL_OBJECTS_NAME);
		int period = ParamUtil.getInteger(resourceRequest, "period");

		Calendar calendar = Calendar.getInstance();

		calendar.setTimeZone(themeDisplay.getTimeZone());

		if (endAmPm == Calendar.PM) {
			endHour += 12;
		}

		calendar.set(endYear, endMonth, endDay, endHour, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			LCSConstants.JSON_KEY_DATA,
			_portalInstanceAdvisor.getPagesMetricsJSONObject(
				companyId, calendar.getTime(), groupId, key, layoutName, period,
				themeDisplay.getTimeZone()));
		jsonObject.put(
			LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_SUCCESS);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	private static final String _KEY_SUFFIX_HIDE_DEFAULT_SUCCESS_MESSAGE =
		".hideDefaultSuccessMessage";

	private static final Log _log = LogFactoryUtil.getLog(ServerPortlet.class);

	private LCSClusterNodeCurrentThreadsMetricsService
		_lcsClusterNodeCurrentThreadsMetricsService;
	private LCSClusterNodeJDBCConnectionPoolMetricsService
		_lcsClusterNodeJDBCConnectionPoolMetricsService;
	private LCSClusterNodeJVMMetricsService _lcsClusterNodeJVMMetricsService;
	private LCSClusterNodeService _lcsClusterNodeService;
	private PortalInstanceAdvisor _portalInstanceAdvisor;

}