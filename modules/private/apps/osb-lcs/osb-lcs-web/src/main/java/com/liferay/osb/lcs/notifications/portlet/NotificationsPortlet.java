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

package com.liferay.osb.lcs.notifications.portlet;

import com.liferay.lcs.util.LCSConstants;
import com.liferay.osb.lcs.advisor.PatchAdvisor;
import com.liferay.osb.lcs.constants.OSBLCSPortletKeys;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.IOException;

import javax.portlet.Portlet;
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
		"com.liferay.portlet.add-default-resource=true" + OSBLCSPortletKeys.NOTIFICATIONS,
		"com.liferay.portlet.css-class-wrapper=osb-lcs-portlet osb-lcs-portlet-notifications",
		"com.liferay.portlet.display-category=category.lcs",
		"com.liferay.portlet.footer-portlet-javascript=/js/lcs-base.js",
		"com.liferay.portlet.footer-portlet-javascript=/js/lcs-notifications.js",
		"javax.portlet.display-name=Notifications",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.info.keywords=Notifications",
		"javax.portlet.info.short-title=Notifications",
		"javax.portlet.info.title=Notifications",
		"javax.portlet.init-param.copy-request-parameters=true",
		"javax.portlet.init-param.template-path=/notifications/",
		"javax.portlet.init-param.view-template=/notifications/view.jsp",
		"javax.portlet.mime-type=text/html", "javax.portlet.name=",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supported-public-render-parameter=layoutLCSClusterEntryId",
		"javax.portlet.supported-public-render-parameter=layoutLCSClusterNodeId",
		"javax.portlet.supported-public-render-parameter=layoutLCSProjectId"
	},
	service = Portlet.class
)
public class NotificationsPortlet extends MVCPortlet {

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			AuthTokenUtil.checkCSRFToken(
				PortalUtil.getHttpServletRequest(resourceRequest),
				NotificationsPortlet.class.getName());

			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("downloadPatch")) {
				downloadPatch(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("downloadPatchStatus")) {
				jsonObject.put(
					LCSConstants.JSON_KEY_DATA,
					downloadPatchStatus(resourceRequest, resourceResponse));
			}

			jsonObject.put(
				LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_SUCCESS);
		}
		catch (Exception e) {
			_log.error(e);

			jsonObject.put(
				LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_FAILURE);
		}

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	@Reference(unbind = "-")
	public void setPatchAdvisor(PatchAdvisor patchAdvisor) {
		_patchAdvisor = patchAdvisor;
	}

	protected void downloadPatch(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long[] lcsClusterNodeIds = StringUtil.split(
			ParamUtil.getString(resourceRequest, "lcsClusterNodeIds"), 0L);

		if (lcsClusterNodeIds.length == 0) {
			return;
		}

		String patchName = ParamUtil.getString(resourceRequest, "patchName");

		_patchAdvisor.downloadPatch(lcsClusterNodeIds, patchName);
	}

	protected String downloadPatchStatus(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long[] lcsClusterNodeIds = StringUtil.split(
			ParamUtil.getString(resourceRequest, "lcsClusterNodeIds"), 0L);
		String lcsClusterNodeKeys = ParamUtil.getString(
			resourceRequest, "lcsClusterNodeKeys");
		String patchId = ParamUtil.getString(resourceRequest, "patchId");

		return _patchAdvisor.getDownloadPatchStatus(
			lcsClusterNodeIds, lcsClusterNodeKeys, patchId);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		NotificationsPortlet.class);

	private PatchAdvisor _patchAdvisor;

}