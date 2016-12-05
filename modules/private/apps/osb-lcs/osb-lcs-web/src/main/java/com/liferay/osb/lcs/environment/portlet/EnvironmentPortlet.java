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

package com.liferay.osb.lcs.environment.portlet;

import com.liferay.lcs.subscription.SubscriptionType;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.osb.lcs.advisor.LCSClusterEntryTokenAdvisor;
import com.liferay.osb.lcs.constants.OSBLCSPortletKeys;
import com.liferay.osb.lcs.exception.DuplicateLCSClusterEntryNameException;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSClusterEntryToken;
import com.liferay.osb.lcs.service.LCSClusterEntryService;
import com.liferay.osb.lcs.service.LCSClusterEntryTokenService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 * @author Marko Cikos
 * @author Matija Petanjek
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=osb-lcs-portlet osb-lcs-portlet-environment" + OSBLCSPortletKeys.ENVIRONMENT,
		"com.liferay.portlet.display-category=category.lcs",
		"com.liferay.portlet.footer-portlet-javascript=/js/lcs-base.js",
		"com.liferay.portlet.footer-portlet-javascript=/js/lcs-environment.js",
		"com.liferay.portlet.footer-portlet-javascript=/js/lcs-notifications.js",
		"javax.portlet.display-name=Environment",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.info.keywords=Environment",
		"javax.portlet.info.short-title=Environment",
		"javax.portlet.info.title=Environment",
		"javax.portlet.init-param.copy-request-parameters=true",
		"javax.portlet.init-param.template-path=/environment/",
		"javax.portlet.init-param.view-template=/environment/view.jsp",
		"javax.portlet.mime-type=text/html", "javax.portlet.name=",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supported-public-render-parameter=environmentPage",
		"javax.portlet.supported-public-render-parameter=layoutLCSClusterEntryId",
		"javax.portlet.supported-public-render-parameter=layoutLCSProjectId"
	},
	service = Portlet.class
)
public class EnvironmentPortlet extends MVCPortlet {

	public void addLCSClusterEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long layoutLCSProjectId = ParamUtil.getLong(
			actionRequest, "layoutLCSProjectId");
		String name = ParamUtil.getString(actionRequest, "name");
		String description = ParamUtil.getString(actionRequest, "description");
		String location = ParamUtil.getString(actionRequest, "location");

		int lcsClusterEntryType =
			LCSConstants.LCS_CLUSTER_ENTRY_TYPE_ENVIRONMENT;

		boolean cluster = ParamUtil.getBoolean(actionRequest, "cluster");

		if (cluster) {
			lcsClusterEntryType = LCSConstants.LCS_CLUSTER_ENTRY_TYPE_CLUSTER;
		}

		LCSClusterEntry lscClusterEntry =
			_lcsClusterEntryService.addLCSClusterEntry(
				layoutLCSProjectId, name, description, location,
				SubscriptionType.UNDEFINED.name(), lcsClusterEntryType);

		PortletURL redirectURL = PortletURLFactoryUtil.create(
			actionRequest, OSBLCSPortletKeys.ENVIRONMENT,
			themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

		redirectURL.setParameter(
			"layoutLCSClusterEntryId",
			String.valueOf(lscClusterEntry.getLcsClusterEntryId()));
		redirectURL.setParameter(
			"layoutLCSProjectId",
			String.valueOf(lscClusterEntry.getLcsProjectId()));

		actionResponse.sendRedirect(redirectURL.toString());
	}

	public void deleteLCSClusterEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long lcsClusterEntryId = ParamUtil.getLong(
			actionRequest, "lcsClusterEntryId");

		_lcsClusterEntryService.deleteLCSClusterEntry(lcsClusterEntryId);
	}

	public void generateLCSClusterEntryToken(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long lcsClusterEntryId = ParamUtil.getLong(
			actionRequest, "lcsClusterEntryId");

		Map<String, String> lcsServicesConfiguration =
			getLCSServicesConfiguration(actionRequest);

		_lcsClusterEntryTokenService.addLCSClusterEntryToken(
			lcsClusterEntryId, lcsServicesConfiguration);
	}

	public void regenerateLCSClusterEntryToken(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long lcsClusterEntryId = ParamUtil.getLong(
			actionRequest, "lcsClusterEntryId");

		Map<String, String> lcsServicesConfiguration =
			getLCSServicesConfiguration(actionRequest);

		_lcsClusterEntryTokenService.regenerateLCSClusterEntryToken(
			lcsClusterEntryId, lcsServicesConfiguration);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		try {
			AuthTokenUtil.checkCSRFToken(
				PortalUtil.getHttpServletRequest(resourceRequest),
				EnvironmentPortlet.class.getName());

			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("downloadLCSEntryToken")) {
				downloadLCSEntryToken(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Reference(unbind = "-")
	public void setLCSClusterEntryService(
		LCSClusterEntryService lcsClusterEntryService) {

		_lcsClusterEntryService = lcsClusterEntryService;
	}

	@Reference(unbind = "-")
	public void setLCSClusterEntryTokenAdvisor(
		LCSClusterEntryTokenAdvisor lcsClusterEntryTokenAdvisor) {

		_lcsClusterEntryTokenAdvisor = lcsClusterEntryTokenAdvisor;
	}

	@Reference(unbind = "-")
	public void setLCSClusterEntryTokenService(
		LCSClusterEntryTokenService lcsClusterEntryTokenService) {

		_lcsClusterEntryTokenService = lcsClusterEntryTokenService;
	}

	public void updateLCSClusterEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long lcsClusterEntryId = ParamUtil.getLong(
			actionRequest, "lcsClusterEntryId");

		String name = ParamUtil.getString(actionRequest, "name");
		String description = ParamUtil.getString(actionRequest, "description");
		String location = ParamUtil.getString(actionRequest, "location");

		try {
			_lcsClusterEntryService.updateLCSClusterEntry(
				lcsClusterEntryId, name, description, location);
		}
		catch (DuplicateLCSClusterEntryNameException dlcscene) {
			SessionErrors.add(actionRequest, "duplicateLCSClusterEntryName");

			String redirect = ParamUtil.getString(actionRequest, "redirect");

			actionResponse.sendRedirect(redirect);
		}
	}

	protected void downloadLCSEntryToken(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long lcsClusterEntryId = ParamUtil.getLong(
			resourceRequest, "lcsClusterEntryId");

		LCSClusterEntryToken lcsClusterEntryToken =
			_lcsClusterEntryTokenService.
				fetchLCSClusterEntryLCSClusterEntryToken(lcsClusterEntryId);

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse,
			"lcs-aatf-" + lcsClusterEntryId + ".aatf",
			_lcsClusterEntryTokenAdvisor.getLCSEntryTokenEncryptedBytes(
				lcsClusterEntryToken),
			ContentTypes.APPLICATION_OCTET_STREAM,
			HttpHeaders.CONTENT_DISPOSITION_ATTACHMENT);
	}

	protected Map<String, String> getLCSServicesConfiguration(
		ActionRequest actionRequest) {

		Map<String, String> lcsServicesConfiguration = new HashMap<>();

		lcsServicesConfiguration.put(
			LCSConstants.METRICS_LCS_SERVICE_ENABLED,
			String.valueOf(
				ParamUtil.getBoolean(
					actionRequest, LCSConstants.METRICS_LCS_SERVICE_ENABLED)));

		lcsServicesConfiguration.put(
			LCSConstants.PATCHES_LCS_SERVICE_ENABLED,
			String.valueOf(
				ParamUtil.getBoolean(
					actionRequest, LCSConstants.PATCHES_LCS_SERVICE_ENABLED)));

		lcsServicesConfiguration.put(
			LCSConstants.PORTAL_PROPERTIES_LCS_SERVICE_ENABLED,
			String.valueOf(
				ParamUtil.getBoolean(
					actionRequest,
					LCSConstants.PORTAL_PROPERTIES_LCS_SERVICE_ENABLED)));

		String blacklistedPropertyKeysString = ParamUtil.getString(
			actionRequest, LCSConstants.PORTAL_PROPERTIES_BLACKLIST);

		if (Validator.isNull(blacklistedPropertyKeysString)) {
			return lcsServicesConfiguration;
		}

		lcsServicesConfiguration.put(
			LCSConstants.PORTAL_PROPERTIES_BLACKLIST,
			blacklistedPropertyKeysString);

		return lcsServicesConfiguration;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EnvironmentPortlet.class);

	private LCSClusterEntryService _lcsClusterEntryService;
	private LCSClusterEntryTokenAdvisor _lcsClusterEntryTokenAdvisor;
	private LCSClusterEntryTokenService _lcsClusterEntryTokenService;

}