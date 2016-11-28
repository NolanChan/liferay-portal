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

import com.liferay.lcs.notification.LCSEventType;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.osb.lcs.advisor.EmailAdvisor;
import com.liferay.osb.lcs.constants.LCSMessageConstants;
import com.liferay.osb.lcs.constants.LCSRoleConstants;
import com.liferay.osb.lcs.constants.OSBLCSActionKeys;
import com.liferay.osb.lcs.constants.OSBLCSPortletKeys;
import com.liferay.osb.lcs.email.EmailContext;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.service.LCSMessageLocalService;
import com.liferay.osb.lcs.service.LCSProjectLocalService;
import com.liferay.osb.lcs.service.LCSProjectService;
import com.liferay.osb.lcs.service.LCSRoleService;
import com.liferay.osb.lcs.service.permission.LCSProjectPermission;
import com.liferay.osb.lcs.web.internal.advisor.EnrollmentAdvisor;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.Date;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 * @author Marko Cikos
 * @author Matija Petanjek
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=osb-lcs-portlet osb-lcs-portlet-enrollment" + OSBLCSPortletKeys.ENROLLMENT,
		"com.liferay.portlet.display-category=category.lcs",
		"com.liferay.portlet.footer-portlet-javascript=/js/lcs-base.js",
		"com.liferay.portlet.footer-portlet-javascript=/js/lcs-enrollment.js",
		"com.liferay.portlet.header-portlet-css=/css/lcs-admin.css",
		"javax.portlet.display-name=Enrollment",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.info.keywords=Enrollment",
		"javax.portlet.info.short-title=Enrollment",
		"javax.portlet.info.title=Enrollment",
		"javax.portlet.init-param.copy-request-parameters=true",
		"javax.portlet.init-param.template-path=/enrollment/",
		"javax.portlet.init-param.view-template=/enrollment/view.jsp",
		"javax.portlet.mime-type=text/html", "javax.portlet.name=",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supported-public-render-parameter=layoutLCSProjectId"
	},
	service = Portlet.class
)
public class EnrollmentPortlet extends MVCPortlet {

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		try {
			AuthTokenUtil.checkCSRFToken(
				PortalUtil.getHttpServletRequest(resourceRequest),
				EnrollmentPortlet.class.getName());

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

	@Reference(unbind = "-")
	public void setEmailAdvisor(EmailAdvisor emailAdvisor) {
		_emailAdvisor = emailAdvisor;
	}

	@Reference(unbind = "-")
	public void setEnrollmentAdvisor(EnrollmentAdvisor enrollmentAdvisor) {
		_enrollmentAdvisor = enrollmentAdvisor;
	}

	@Reference(unbind = "-")
	public void setLCSMessageLocalService(
		LCSMessageLocalService lcsMessageLocalService) {

		_lcsMessageLocalService = lcsMessageLocalService;
	}

	@Reference(unbind = "-")
	public void setLCSProjectLocalService(
		LCSProjectLocalService lcsProjectLocalService) {

		_lcsProjectLocalService = lcsProjectLocalService;
	}

	@Reference(unbind = "-")
	public void setLCSProjectService(LCSProjectService lcsProjectService) {
		_lcsProjectService = lcsProjectService;
	}

	@Reference(unbind = "-")
	public void setLCSRoleService(LCSRoleService lcsRoleService) {
		_lcsRoleService = lcsRoleService;
	}

	@Reference(unbind = "-")
	public void setUserService(UserService userService) {
		_userService = userService;
	}

	protected void addLCSAdministratorRole(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long lcsProjectId = ParamUtil.getLong(resourceRequest, "lcsProjectId");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			resourceRequest);

		_lcsRoleService.addLCSRole(
			serviceContext.getUserId(), lcsProjectId, 0,
			LCSRoleConstants.ROLE_LCS_ADMINISTRATOR);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject dataJSONObject = JSONFactoryUtil.createJSONObject();

		List<LCSProject> lcsProjects = _lcsProjectService.getUserLCSProjects(
			false,
			LCSRoleConstants.ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER);

		ThemeDisplay themeDisplay = serviceContext.getThemeDisplay();

		dataJSONObject.put(
			"active",
			toJSONArray(
				lcsProjects, true, themeDisplay.getPermissionChecker()));

		jsonObject.put(LCSConstants.JSON_KEY_DATA, dataJSONObject);
		jsonObject.put(
			LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_SUCCESS);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void addLCSEnvironmentMembershipPendingUserRole(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long lcsProjectId = ParamUtil.getLong(resourceRequest, "lcsProjectId");

		User currentUser = _userService.getCurrentUser();

		_lcsRoleService.addPendingLCSRole(lcsProjectId, 0);

		EmailContext.EmailContextBuilder emailContextBuilder =
			new EmailContext.EmailContextBuilder(
				LCSEventType.NEW_MEMBERSHIP_REQUEST);

		emailContextBuilder.user(currentUser);

		LCSProject lcsProject = _lcsProjectLocalService.getLCSProject(
			lcsProjectId);

		emailContextBuilder.lcsProject(lcsProject);

		_emailAdvisor.sendToLCSProjectAdminsEmail(emailContextBuilder.build());

		LCSEventType lcsEventType = LCSEventType.NEW_MEMBERSHIP_REQUEST;

		_lcsMessageLocalService.addLCSProjectLCSMessage(
			lcsProjectId, LCSMessageConstants.LCS_SOURCE_MESSAGE_ID,
			LCSConstants.SOURCE_SYSTEM_NAME_LCS, null,
			new Date(LCSMessageConstants.END_DATE_INDEFINITE), false,
			lcsEventType.getSeverityLevel(), lcsEventType.getType(), true,
			true);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject dataJSONObject = JSONFactoryUtil.createJSONObject();

		List<LCSProject> lcsProjects = _lcsProjectService.getUserLCSProjects(
			false);

		dataJSONObject.put(
			"administered", toJSONArray(lcsProjects, false, null));

		lcsProjects = _lcsProjectService.getUserDomainLCSProjects();

		dataJSONObject.put("company", toJSONArray(lcsProjects, false, null));

		lcsProjects = _lcsProjectService.getUserLCSProjects(
			true,
			LCSRoleConstants.ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER);

		dataJSONObject.put("pending", toJSONArray(lcsProjects, false, null));

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

		List<LCSProject> lcsProjects = _lcsProjectService.getUserLCSProjects(
			false,
			LCSRoleConstants.ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER);

		dataJSONObject.put(
			"active",
			toJSONArray(
				lcsProjects, true, themeDisplay.getPermissionChecker()));

		lcsProjects = _lcsProjectService.getUserLCSProjects(false);

		dataJSONObject.put(
			"administered", toJSONArray(lcsProjects, false, null));

		lcsProjects = _lcsProjectService.getUserDomainLCSProjects();

		dataJSONObject.put("company", toJSONArray(lcsProjects, false, null));

		lcsProjects = _lcsProjectService.getUserLCSProjects(
			true,
			LCSRoleConstants.ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER);

		dataJSONObject.put("pending", toJSONArray(lcsProjects, false, null));

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
				_enrollmentAdvisor.getAdministratorEmailAddresses(
					lcsProject.getLcsProjectId());

			jsonObject.put(
				"administratorEmailAddresses",
				StringUtil.merge(emailAddresses));

			if (permissions) {
				jsonObject.put(
					"editable",
					LCSProjectPermission.contains(
						permissionChecker, lcsProject.getLcsProjectId(),
						OSBLCSActionKeys.MANAGE_ENTRY));
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

		LCSProject lcsProject = _lcsProjectService.updateLCSProjectName(
			lcsProjectId, lcsProjectName);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(LCSConstants.JSON_KEY_DATA, lcsProject.getName());
		jsonObject.put(
			LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_SUCCESS);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EnrollmentPortlet.class);

	private EmailAdvisor _emailAdvisor;
	private EnrollmentAdvisor _enrollmentAdvisor;
	private LCSMessageLocalService _lcsMessageLocalService;
	private LCSProjectLocalService _lcsProjectLocalService;
	private LCSProjectService _lcsProjectService;
	private LCSRoleService _lcsRoleService;
	private UserService _userService;

}