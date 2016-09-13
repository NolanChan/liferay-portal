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
import com.liferay.osb.lcs.advisor.CompanyAdvisor;
import com.liferay.osb.lcs.advisor.LCSMessageAdvisor;
import com.liferay.osb.lcs.email.EmailAdvisor;
import com.liferay.osb.lcs.email.EmailContext;
import com.liferay.osb.lcs.model.LCSMessage;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.model.LCSRole;
import com.liferay.osb.lcs.service.LCSInvitationServiceUtil;
import com.liferay.osb.lcs.service.LCSMembersLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSProjectServiceUtil;
import com.liferay.osb.lcs.service.LCSRoleLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSRoleServiceUtil;
import com.liferay.osb.lcs.service.UserLCSMessageLocalServiceUtil;
import com.liferay.osb.lcs.util.AuthUtil;
import com.liferay.osb.lcs.util.PortletKeys;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.util.bean.PortletBeanLocatorUtil;
import org.osgi.service.component.annotations.Component;

import java.io.IOException;

import java.util.List;
import java.util.Objects;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
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
		"javax.portlet.name=" + PortletKeys.MEMBERS,
		"javax.portlet.display-name=LCS Admin",
		"javax.portlet.init-param.copy-request-parameters=true",
		"javax.portlet.init-param.template-path=/members/",
		"javax.portlet.init-param.view-template=/members/view.jsp",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.mime-type=text/html",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.info.title=Members",
		"javax.portlet.info.short-title=Members",
		"javax.portlet.info.keywords=Members",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supported-public-render-parameter=layoutLCSClusterEntryId",
		"javax.portlet.supported-public-render-parameter=layoutLCSProjectId",
		"com.liferay.portlet.footer-portlet-javascript=/js/lcs-base.js",
		"com.liferay.portlet.footer-portlet-javascript=/js/lcs-members.js",
		"com.liferay.portlet.css-class-wrapper=osb-lcs-portlet osb-lcs-portlet-members",
		"com.liferay.portlet.display-category=category.lcs"
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

		LCSRoleServiceUtil.addLCSRole(
			userId, lcsProjectId, lcsClusterEntryId, role);

		LCSMembersLocalServiceUtil.validateLCSSiteMembership(
			_companyAdvisor.getCompanyId(), userId);
	}

	public void deleteLCSRole(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long lcsRoleId = ParamUtil.getLong(actionRequest, "lcsRoleId");

		LCSRoleServiceUtil.deleteLCSRole(lcsRoleId);
	}

	@Override
	public void destroy() {
		super.destroy();

		_companyAdvisor = null;
		_emailAdvisor = null;
		_lcsMessageAdvisor = null;
	}

	@Override
	public void init() throws PortletException {
		super.init();

		_companyAdvisor = (CompanyAdvisor)PortletBeanLocatorUtil.locate(
			"com.liferay.osb.lcs.advisor.CompanyAdvisor");
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
				resourceRequest.getLocale(),
				"your-request-failed-to-complete");

			jsonObject.put(LCSConstants.JSON_KEY_MESSAGE, message);
			jsonObject.put(
				LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_FAILURE);

			writeJSON(resourceRequest, resourceResponse, jsonObject);
		}
	}

	protected void addLCSRoles(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			resourceRequest);

		long lcsProjectId = ParamUtil.getLong(resourceRequest, "lcsProjectId");

		LCSMessage lcsMessage = _lcsMessageAdvisor.addLCSProjectLCSMessage(
			false, null, false, LCSEventType.MEMBERSHIP_REQUEST_ACCEPTED,
			lcsProjectId);

		long[] userIds = ParamUtil.getLongValues(resourceRequest, "userId");

		LCSProject lcsProject = LCSProjectServiceUtil.getLCSProject(
			lcsProjectId);

		LCSMembersLocalServiceUtil.validateCorpProjectUsers(
			lcsProject.getCorpProjectId(), userIds);

		long lcsClusterEntryId = ParamUtil.getLong(
			resourceRequest, "lcsClusterEntryId");
		int role = ParamUtil.getInteger(resourceRequest, "role");

		for (long userId : userIds) {
			LCSRole lcsRole = LCSRoleServiceUtil.addLCSRole(
				userId, lcsProjectId, lcsClusterEntryId, role);

			LCSMembersLocalServiceUtil.validateLCSSiteMembership(
				_companyAdvisor.getCompanyId(), userId);

			_emailAdvisor.sendEmail(
				new EmailContext(
					LCSEventType.MEMBERSHIP_REQUEST_ACCEPTED, lcsRole));

			UserLCSMessageLocalServiceUtil.addUserLCSMessage(
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

		LCSInvitationServiceUtil.deleteLCSInvitation(lcsInvitationId);

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
			List<LCSRole> lcsRoles = LCSRoleLocalServiceUtil.getUserLCSRoles(
				userId, lcsProjectId);

			for (LCSRole lcsRole : lcsRoles) {
				LCSRoleServiceUtil.deleteLCSRole(lcsRole.getLcsRoleId());
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

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			resourceRequest);

		String emailAddress = ParamUtil.getString(
			resourceRequest, "emailAddress");
		String invitationMessage = ParamUtil.getString(
			resourceRequest, "invitationMessage");
		long lcsClusterEntryId = ParamUtil.getLong(
			resourceRequest, "lcsClusterEntryId");
		long lcsProjectId = ParamUtil.getLong(resourceRequest, "lcsProjectId");
		int role = ParamUtil.getInteger(resourceRequest, "role");

		List<LCSRole> lcsRoles = LCSRoleServiceUtil.getLCSProjectLCSRoles(
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

		LCSInvitationServiceUtil.addLCSInvitation(
			lcsProjectId, emailAddress, lcsClusterEntryId, role);

		_emailAdvisor.sendEmail(
			new EmailContext(
				invitationMessage, emailAddress,
				LCSEventType.NEW_MEMBERSHIP_INVITATION, lcsProjectId,
				PortalUtil.getUserId(resourceRequest)));

		jsonObject.put(
			LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_SUCCESS);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	private static Log _log = LogFactoryUtil.getLog(MembersPortlet.class);

	private CompanyAdvisor _companyAdvisor;
	private EmailAdvisor _emailAdvisor;
	private LCSMessageAdvisor _lcsMessageAdvisor;

}