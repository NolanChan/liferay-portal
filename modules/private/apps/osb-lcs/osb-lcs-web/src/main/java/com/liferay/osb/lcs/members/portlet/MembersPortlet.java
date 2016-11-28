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

package com.liferay.osb.lcs.members.portlet;

import com.liferay.lcs.notification.LCSEventType;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.osb.lcs.advisor.EmailAdvisor;
import com.liferay.osb.lcs.advisor.impl.CompanyAdvisorImpl;
import com.liferay.osb.lcs.constants.LCSMessageConstants;
import com.liferay.osb.lcs.constants.OSBLCSPortletKeys;
import com.liferay.osb.lcs.email.EmailContext;
import com.liferay.osb.lcs.model.LCSMessage;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.model.LCSRole;
import com.liferay.osb.lcs.service.LCSInvitationService;
import com.liferay.osb.lcs.service.LCSMembersLocalService;
import com.liferay.osb.lcs.service.LCSMessageLocalService;
import com.liferay.osb.lcs.service.LCSProjectService;
import com.liferay.osb.lcs.service.LCSRoleLocalService;
import com.liferay.osb.lcs.service.LCSRoleService;
import com.liferay.osb.lcs.service.UserLCSMessageLocalService;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
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
		"com.liferay.portlet.css-class-wrapper=osb-lcs-portlet osb-lcs-portlet-members" + OSBLCSPortletKeys.MEMBERS,
		"com.liferay.portlet.display-category=category.lcs",
		"com.liferay.portlet.footer-portlet-javascript=/js/lcs-base.js",
		"com.liferay.portlet.footer-portlet-javascript=/js/lcs-members.js",
		"javax.portlet.display-name=Members",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.info.keywords=Members",
		"javax.portlet.info.short-title=Members",
		"javax.portlet.info.title=Members",
		"javax.portlet.init-param.copy-request-parameters=true",
		"javax.portlet.init-param.template-path=/members/",
		"javax.portlet.init-param.view-template=/members/view.jsp",
		"javax.portlet.mime-type=text/html", "javax.portlet.name=",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supported-public-render-parameter=layoutLCSClusterEntryId",
		"javax.portlet.supported-public-render-parameter=layoutLCSProjectId"
	},
	service = Portlet.class
)
public class MembersPortlet extends MVCPortlet {

	public void addLCSRole(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long userId = ParamUtil.getLong(actionRequest, "userId");
		long lcsClusterEntryId = ParamUtil.getLong(
			actionRequest, "lcsClusterEntryId");
		long lcsProjectId = ParamUtil.getLong(actionRequest, "lcsProjectId");
		int role = ParamUtil.getInteger(actionRequest, "role");

		_lcsRoleService.addLCSRole(
			userId, lcsProjectId, lcsClusterEntryId, role);

		_lcsMembersLocalService.validateLCSSiteMembership(
			_companyAdvisor.getCompanyId(), userId);
	}

	public void deleteLCSRole(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long lcsRoleId = ParamUtil.getLong(actionRequest, "lcsRoleId");

		_lcsRoleService.deleteLCSRole(lcsRoleId);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		try {
			AuthTokenUtil.checkCSRFToken(
				PortalUtil.getHttpServletRequest(resourceRequest),
				MembersPortlet.class.getName());

			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("addLCSRoles")) {
				addLCSRoles(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("cancelInvitation")) {
				cancelInvitation(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("deleteLCSRoles")) {
				deleteLCSRoles(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("invite")) {
				invite(resourceRequest, resourceResponse);
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
	public void setCompanyAdvisor(CompanyAdvisorImpl companyAdvisor) {
		_companyAdvisor = companyAdvisor;
	}

	@Reference(unbind = "-")
	public void setEmailAdvisor(EmailAdvisor emailAdvisor) {
		_emailAdvisor = emailAdvisor;
	}

	@Reference(unbind = "-")
	public void setLCSInvitationService(
		LCSInvitationService lcsInvitationService) {

		_lcsInvitationService = lcsInvitationService;
	}

	@Reference(unbind = "-")
	public void setLCSMembersLocalService(
		LCSMembersLocalService lcsMembersLocalService) {

		_lcsMembersLocalService = lcsMembersLocalService;
	}

	@Reference(unbind = "-")
	public void setLCSMessageLocalService(
		LCSMessageLocalService lcsMessageLocalServce) {

		_lcsMessageLocalService = lcsMessageLocalServce;
	}

	@Reference(unbind = "-")
	public void setLCSProjectService(LCSProjectService lcsProjectService) {
		_lcsProjectService = lcsProjectService;
	}

	@Reference(unbind = "-")
	public void setLCSRoleLocalService(
		LCSRoleLocalService lcsRoleLocalService) {

		_lcsRoleLocalService = lcsRoleLocalService;
	}

	@Reference(unbind = "-")
	public void setLCSRoleService(LCSRoleService lcsRoleService) {
		_lcsRoleService = lcsRoleService;
	}

	@Reference(unbind = "-")
	public void setUserLCSMessageLocalService(
		UserLCSMessageLocalService userLCSMessageLocalService) {

		_userLCSMessageLocalService = userLCSMessageLocalService;
	}

	@Reference(unbind = "-")
	public void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	protected void addLCSRoles(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long lcsProjectId = ParamUtil.getLong(resourceRequest, "lcsProjectId");

		LCSEventType lcsEventType = LCSEventType.MEMBERSHIP_REQUEST_ACCEPTED;

		LCSMessage lcsMessage = _lcsMessageLocalService.addLCSProjectLCSMessage(
			lcsProjectId, LCSMessageConstants.LCS_SOURCE_MESSAGE_ID,
			LCSConstants.SOURCE_SYSTEM_NAME_LCS, null,
			new Date(LCSMessageConstants.END_DATE_INDEFINITE), false,
			lcsEventType.getSeverityLevel(), lcsEventType.getType(), false,
			false);

		long[] userIds = ParamUtil.getLongValues(resourceRequest, "userId");

		LCSProject lcsProject = _lcsProjectService.getLCSProject(lcsProjectId);

		_lcsMembersLocalService.validateCorpProjectUsers(
			lcsProject.getCorpProjectId(), userIds);

		long lcsClusterEntryId = ParamUtil.getLong(
			resourceRequest, "lcsClusterEntryId");
		int role = ParamUtil.getInteger(resourceRequest, "role");

		EmailContext.EmailContextBuilder emailContextBuilder;

		for (long userId : userIds) {
			emailContextBuilder = new EmailContext.EmailContextBuilder(
				LCSEventType.MEMBERSHIP_REQUEST_ACCEPTED);

			User user = _userLocalService.getUser(userId);

			emailContextBuilder.emailAddress(user.getEmailAddress());
			emailContextBuilder.user(user);
			emailContextBuilder.locale(user.getLocale());

			_lcsRoleService.addLCSRole(
				userId, lcsProjectId, lcsClusterEntryId, role);

			_lcsMembersLocalService.validateLCSSiteMembership(
				_companyAdvisor.getCompanyId(), userId);

			_emailAdvisor.sendEmail(emailContextBuilder.build());

			_userLCSMessageLocalService.addUserLCSMessage(
				userId, lcsMessage.getLcsMessageId());
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_SUCCESS);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void cancelInvitation(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long lcsInvitationId = ParamUtil.getLong(
			resourceRequest, "lcsInvitationId");

		_lcsInvitationService.deleteLCSInvitation(lcsInvitationId);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_SUCCESS);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void deleteLCSRoles(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long lcsProjectId = ParamUtil.getLong(resourceRequest, "lcsProjectId");
		long[] userIds = ParamUtil.getLongValues(resourceRequest, "userId");

		for (long userId : userIds) {
			List<LCSRole> lcsRoles = _lcsRoleLocalService.getUserLCSRoles(
				userId, lcsProjectId);

			for (LCSRole lcsRole : lcsRoles) {
				_lcsRoleService.deleteLCSRole(lcsRole.getLcsRoleId());
			}
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_SUCCESS);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void invite(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		String emailAddress = ParamUtil.getString(
			resourceRequest, "emailAddress");
		String invitationMessage = ParamUtil.getString(
			resourceRequest, "invitationMessage");
		long lcsClusterEntryId = ParamUtil.getLong(
			resourceRequest, "lcsClusterEntryId");
		long lcsProjectId = ParamUtil.getLong(resourceRequest, "lcsProjectId");
		int role = ParamUtil.getInteger(resourceRequest, "role");

		List<LCSRole> lcsRoles = _lcsRoleService.getLCSProjectLCSRoles(
			lcsProjectId);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		for (LCSRole lcsRole : lcsRoles) {
			String userEmailAddress = PortalUtil.getUserEmailAddress(
				lcsRole.getUserId());

			if (Objects.equals(emailAddress, userEmailAddress)) {
				String message = LanguageUtil.get(
					resourceRequest.getLocale(),
					"you-cannot-invite-a-user-who-is-already-a-project-member");

				jsonObject.put(LCSConstants.JSON_KEY_MESSAGE, message);

				jsonObject.put(
					LCSConstants.JSON_KEY_RESULT,
					LCSConstants.JSON_VALUE_FAILURE);

				writeJSON(resourceRequest, resourceResponse, jsonObject);

				return;
			}
		}

		_lcsInvitationService.addLCSInvitation(
			lcsProjectId, emailAddress, lcsClusterEntryId, role);

		EmailContext.EmailContextBuilder emailContextBuilder =
			new EmailContext.EmailContextBuilder(
				LCSEventType.NEW_MEMBERSHIP_INVITATION);

		emailContextBuilder.emailAddress(emailAddress);
		emailContextBuilder.customMessage(invitationMessage);
		emailContextBuilder.lcsProject(
			_lcsProjectService.getLCSProject(lcsProjectId));
		emailContextBuilder.user(PortalUtil.getUser(resourceRequest));

		_emailAdvisor.sendEmail(emailContextBuilder.build());

		jsonObject.put(
			LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_SUCCESS);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	private static final Log _log = LogFactoryUtil.getLog(MembersPortlet.class);

	private CompanyAdvisorImpl _companyAdvisor;
	private EmailAdvisor _emailAdvisor;
	private LCSInvitationService _lcsInvitationService;
	private LCSMembersLocalService _lcsMembersLocalService;
	private LCSMessageLocalService _lcsMessageLocalService;
	private LCSProjectService _lcsProjectService;
	private LCSRoleLocalService _lcsRoleLocalService;
	private LCSRoleService _lcsRoleService;
	private UserLCSMessageLocalService _userLCSMessageLocalService;
	private UserLocalService _userLocalService;

}