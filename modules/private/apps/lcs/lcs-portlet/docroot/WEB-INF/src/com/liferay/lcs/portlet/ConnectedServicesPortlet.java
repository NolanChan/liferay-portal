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

package com.liferay.lcs.portlet;

import com.liferay.lcs.util.ClusterNodeUtil;
import com.liferay.lcs.util.LCSConnectionManagerUtil;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.lcs.util.LCSUtil;
import com.liferay.portal.kernel.cluster.ClusterExecutorUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 * @author Marko Cikos
 * @author Peter Shin
 */
public class ConnectedServicesPortlet extends MVCPortlet {

	public void saveLCSServicesPreferences(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		Boolean hasLCSServicesPreferences = LCSUtil.hasLCSServicesPreferences();

		PortletPreferences portletPreferences =
			LCSUtil.fetchJxPortletPreferences();

		Boolean metricsServiceEnabled = ParamUtil.getBoolean(
			actionRequest, LCSConstants.METRICS_LCS_SERVICE_ENABLED, true);
		Boolean patchesServiceEnabled = ParamUtil.getBoolean(
			actionRequest, LCSConstants.PATCHES_LCS_SERVICE_ENABLED, true);
		Boolean portalPropertiesServiceEnabled = ParamUtil.getBoolean(
			actionRequest, LCSConstants.PORTAL_PROPERTIES_LCS_SERVICE_ENABLED,
			true);
		String portalPropertiesBlacklist = ParamUtil.getString(
			actionRequest, LCSConstants.PORTAL_PROPERTIES_BLACKLIST);

		portletPreferences.setValue(
			LCSConstants.METRICS_LCS_SERVICE_ENABLED,
			String.valueOf(metricsServiceEnabled));

		portletPreferences.setValue(
			LCSConstants.PATCHES_LCS_SERVICE_ENABLED,
			String.valueOf(patchesServiceEnabled));

		portletPreferences.setValue(
			LCSConstants.PORTAL_PROPERTIES_LCS_SERVICE_ENABLED,
			String.valueOf(portalPropertiesServiceEnabled));

		portletPreferences.setValue(
			LCSConstants.PORTAL_PROPERTIES_BLACKLIST,
			portalPropertiesBlacklist);

		portletPreferences.store();

		if (hasLCSServicesPreferences &&
			LCSUtil.isLCSPortletAuthorized(actionRequest)) {

			ClusterNodeUtil.restartPosts(true);
		}
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("connect")) {
				connect(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("disconnect")) {
				disconnect(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("serveConnectionStatus")) {
				serveConnectionStatus(resourceRequest, resourceResponse);
			}
			else {
				super.serveResource(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put(
				LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_FAILURE);

			_log.error(e, e);

			writeJSON(resourceRequest, resourceResponse, jsonObject);
		}
	}

	protected void connect(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		if (ClusterExecutorUtil.isEnabled()) {
			boolean applyToSiblingClusterNodes = ParamUtil.getBoolean(
				resourceRequest, "applyToSiblingClusterNodes");

			ClusterNodeUtil.startPosts(applyToSiblingClusterNodes);
		}
		else {
			LCSConnectionManagerUtil.start();
		}

		jsonObject.put(
			LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_SUCCESS);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void disconnect(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		if (ClusterExecutorUtil.isEnabled()) {
			boolean applyToSiblingClusterNodes = ParamUtil.getBoolean(
				resourceRequest, "applyToSiblingClusterNodes");

			ClusterNodeUtil.stopPosts(applyToSiblingClusterNodes);
		}
		else {
			LCSConnectionManagerUtil.stop();
		}

		jsonObject.put(
			LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_SUCCESS);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void serveConnectionStatus(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			"heartbeatExpiredError",
			LCSConnectionManagerUtil.isHandshakeExpired());
		jsonObject.put(
			"lcsGatewayAvailable",
			LCSConnectionManagerUtil.isLCSGatewayAvailable());
		jsonObject.put("pending", LCSConnectionManagerUtil.isPending());
		jsonObject.put("ready", LCSConnectionManagerUtil.isReady());

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ConnectedServicesPortlet.class);

}