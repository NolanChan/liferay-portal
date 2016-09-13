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

package com.liferay.osb.lcs.enrollment.portlet;

import com.liferay.osb.lcs.util.PortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.lcs.notification.LCSEventType;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.osb.lcs.advisor.LCSMessageAdvisor;
import com.liferay.osb.lcs.email.EmailAdvisor;
import com.liferay.osb.lcs.email.EmailContext;
import com.liferay.osb.lcs.enrollment.util.EnrollmentUtil;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.model.LCSRole;
import com.liferay.osb.lcs.constants.LCSRoleConstants;
import com.liferay.osb.lcs.service.LCSProjectServiceUtil;
import com.liferay.osb.lcs.service.LCSRoleLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSRoleServiceUtil;
import com.liferay.osb.lcs.service.permission.LCSProjectPermission;
import com.liferay.osb.lcs.util.ActionKeys;
import com.liferay.osb.lcs.util.AuthUtil;
import com.liferay.osb.lcs.util.WebKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.util.bean.PortletBeanLocatorUtil;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;

import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Igor Beslic
 * @author Marko Cikos
 * @author Matija Petanjek
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + PortletKeys.ENROLLMENT,
		"javax.portlet.display-name=Enrollment",
		"javax.portlet.init-param.copy-request-parameters=true",
		"javax.portlet.init-param.template-path=/enrollment/",
		"javax.portlet.init-param.view-template=/enrollment/view.jsp",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.mime-type=text/html",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.info.title=Enrollment",
		"javax.portlet.info.short-title=Enrollment",
		"javax.portlet.info.keywords=Enrollment",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supported-public-render-parameter=layoutLCSProjectId",
		"com.liferay.portlet.header-portlet-css=/css/lcs-admin.css",
		"com.liferay.portlet.footer-portlet-javascript=/js/lcs-base.js",
		"com.liferay.portlet.footer-portlet-javascript=/js/lcs-enrollment.js",
		"com.liferay.portlet.css-class-wrapper=osb-lcs-portlet osb-lcs-portlet-enrollment",
		"com.liferay.portlet.display-category=category.lcs"
	},
	service = Portlet.class
)
public class EnrollmentPortlet extends MVCPortlet {

	@Override
	public void destroy() {
		super.destroy();

		_emailAdvisor = null;
		_lcsMessageAdvisor = null;
	}

	@Override
	public void init() throws PortletException {
		super.init();

		_emailAdvisor = (EmailAdvisor)PortletBeanLocatorUtil.locate(
			"com.liferay.osb.lcs.email.EmailAdvisor");
		_lcsMessageAdvisor = (LCSMessageAdvisor)PortletBeanLocatorUtil.locate(
			"com.liferay.osb.lcs.advisor.LCSMessageAdvisor");
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		try {
			AuthUtil.checkAuthToken(resourceRequest);

			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("addLCSAdministratorRole")) {
				addLCSAdministratorRole(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals(
						"addLCSEnvironmentMembershipPendingUserRole")) {

				addLCSEnvironmentMembershipPendingUserRole(
					resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("getUserLCSProjects")) {
				getUserLCSProjects(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("updateLCSProjectName")) {
				updateLCSProjectName(resourceRequest, resourceResponse);
			}
			else {
				super.serveResource(resourceRequest, resourceResponse);
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

	protected void addLCSAdministratorRole(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long lcsProjectId = ParamUtil.getLong(resourceRequest, "lcsProjectId");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			resourceRequest);

		LCSRoleServiceUtil.addLCSRole(
			serviceContext.getUserId(), lcsProjectId, 0,
			LCSRoleConstants.ROLE_LCS_ADMINISTRATOR);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject dataJSONObject = JSONFactoryUtil.createJSONObject();

		List<LCSProject> lcsProjects = LCSProjectServiceUtil.getUserLCSProjects(
			false,
			LCSRoleConstants.ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER);

		ThemeDisplay themeDisplay = serviceContext.getThemeDisplay();

		dataJSONObject.put(
			"active",
			toJSONArray(
				lcsProjects, true, themeDisplay.getPermissionChecker()));

		lcsProjects = EnrollmentUtil.getLCSProjects(false);

		dataJSONObject.put(
			"unadministered", toJSONArray(lcsProjects, false, null));

		jsonObject.put(LCSConstants.JSON_KEY_DATA, dataJSONObject);
		jsonObject.put(
			LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_SUCCESS);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void addLCSEnvironmentMembershipPendingUserRole(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			resourceRequest);

		long lcsProjectId = ParamUtil.getLong(resourceRequest, "lcsProjectId");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			resourceRequest);

		LCSRole lcsEnvironmentMembershipPendingUserLCSRole =
			LCSRoleLocalServiceUtil.addLCSRole(
				serviceContext.getUserId(), lcsProjectId, 0,
				LCSRoleConstants.ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER);

		_emailAdvisor.sendToLCSProjectAdminsEmail(
			new EmailContext(
				LCSEventType.NEW_MEMBERSHIP_REQUEST,
				lcsEnvironmentMembershipPendingUserLCSRole));

		_lcsMessageAdvisor.addLCSProjectLCSMessage(
			true, null, true, LCSEventType.NEW_MEMBERSHIP_REQUEST,
			lcsProjectId);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject dataJSONObject = JSONFactoryUtil.createJSONObject();

		List<LCSProject> lcsProjects = EnrollmentUtil.getLCSProjects(true);

		dataJSONObject.put(
			"administered", toJSONArray(lcsProjects, false, null));

		lcsProjects = EnrollmentUtil.getCompanyLCSProjects(
			serviceContext.getUserId());

		dataJSONObject.put("company", toJSONArray(lcsProjects, false, null));

		lcsProjects = LCSProjectServiceUtil.getUserLCSProjects(
			true,
			LCSRoleConstants.ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER);

		dataJSONObject.put("pending", toJSONArray(lcsProjects, false, null));

		lcsProjects = EnrollmentUtil.getLCSProjects(false);

		dataJSONObject.put("unadministeredCount", lcsProjects.size());

		jsonObject.put(LCSConstants.JSON_KEY_DATA, dataJSONObject);
		jsonObject.put(
			LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_SUCCESS);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void getUserLCSProjects(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject dataJSONObject = JSONFactoryUtil.createJSONObject();

		List<LCSProject> lcsProjects = LCSProjectServiceUtil.getUserLCSProjects(
			false,
			LCSRoleConstants.ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER);

		dataJSONObject.put(
			"active",
			toJSONArray(
				lcsProjects, true, themeDisplay.getPermissionChecker()));

		lcsProjects = EnrollmentUtil.getLCSProjects(true);

		dataJSONObject.put(
			"administered", toJSONArray(lcsProjects, false, null));

		lcsProjects = EnrollmentUtil.getCompanyLCSProjects(
			themeDisplay.getUserId());

		dataJSONObject.put("company", toJSONArray(lcsProjects, false, null));

		lcsProjects = LCSProjectServiceUtil.getUserLCSProjects(
			true,
			LCSRoleConstants.ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER);

		dataJSONObject.put("pending", toJSONArray(lcsProjects, false, null));

		lcsProjects = EnrollmentUtil.getLCSProjects(false);

		dataJSONObject.put(
			"unadministered", toJSONArray(lcsProjects, false, null));

		jsonObject.put(LCSConstants.JSON_KEY_DATA, dataJSONObject);
		jsonObject.put(
			LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_SUCCESS);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected JSONArray toJSONArray(
		List<LCSProject> lcsProjects, boolean permissions,
		PermissionChecker permissionChecker) {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (LCSProject lcsProject : lcsProjects) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			List<String> emailAddresses =
				EnrollmentUtil.getAdministratorEmailAddresses(
					lcsProject.getLcsProjectId());

			jsonObject.put(
				"administratorEmailAddresses",
				StringUtil.merge(emailAddresses));

			if (permissions) {
				jsonObject.put(
					"editable",
					LCSProjectPermission.contains(
						permissionChecker, lcsProject.getLcsProjectId(),
						ActionKeys.MANAGE_ENTRY));
			}

			jsonObject.put("lcsProjectId", lcsProject.getLcsProjectId());
			jsonObject.put("name", lcsProject.getName());

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	protected void updateLCSProjectName(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortalException {

		long lcsProjectId = ParamUtil.getLong(resourceRequest, "lcsProjectId");
		String lcsProjectName = ParamUtil.getString(
			resourceRequest, "lcsProjectName");

		LCSProject lcsProject = LCSProjectServiceUtil.updateLCSProjectName(
			lcsProjectId, lcsProjectName);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(LCSConstants.JSON_KEY_DATA, lcsProject.getName());
		jsonObject.put(
			LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_SUCCESS);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	private static Log _log = LogFactoryUtil.getLog(EnrollmentPortlet.class);

	private EmailAdvisor _emailAdvisor;
	private LCSMessageAdvisor _lcsMessageAdvisor;

}