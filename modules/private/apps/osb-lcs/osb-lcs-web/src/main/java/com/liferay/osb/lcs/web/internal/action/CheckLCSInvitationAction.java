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

package com.liferay.osb.lcs.web.internal.action;

import com.liferay.lcs.notification.LCSEventType;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.osb.lcs.advisor.EmailAdvisor;
import com.liferay.osb.lcs.constants.LCSMessageConstants;
import com.liferay.osb.lcs.constants.NavigationConstants;
import com.liferay.osb.lcs.constants.OSBLCSConstants;
import com.liferay.osb.lcs.constants.OSBLCSPortletKeys;
import com.liferay.osb.lcs.constants.OSBPortletConstants;
import com.liferay.osb.lcs.email.EmailContext;
import com.liferay.osb.lcs.exception.NoSuchLCSInvitationException;
import com.liferay.osb.lcs.model.LCSInvitation;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.osbportlet.service.OSBPortletService;
import com.liferay.osb.lcs.service.LCSInvitationLocalService;
import com.liferay.osb.lcs.service.LCSMembersLocalService;
import com.liferay.osb.lcs.service.LCSMessageLocalService;
import com.liferay.osb.lcs.service.LCSProjectLocalService;
import com.liferay.osb.lcs.service.LCSRoleLocalService;
import com.liferay.osb.lcs.web.internal.advisor.PortletURLAdvisor;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 * @author Marko Cikos
 */
@Component(
	immediate = true,
	property = "path=" + OSBLCSConstants.PUBLIC_PATH_CHECK_LCS_INVITATION,
	service = StrutsAction.class
)
public class CheckLCSInvitationAction extends BaseStrutsAction {

	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug("Executing");
		}

		User user = PortalUtil.getUser(request);

		if (user == null) {
			return redirectToLogin(request, response);
		}

		long companyId = PortalUtil.getCompanyId(request);

		long lcsProjectId = ParamUtil.getLong(request, "lcsProjectId");

		if (lcsProjectId == 0) {
			LiferayPortletURL liferayPortletURL =
				_portletURLAdvisor.getPortletRenderURL(
					companyId, NavigationConstants.FRIENDLY_URL_INFO,
					OSBLCSPortletKeys.INFO, false, request, "errorMessage",
					"project-invitation-is-not-valid");

			response.sendRedirect(liferayPortletURL.toString());

			return null;
		}

		if (!_lcsInvitationLocalService.hasUserLCSInvitation(
				user.getUserId())) {

			LiferayPortletURL liferayPortletURL =
				_portletURLAdvisor.getPortletRenderURL(
					companyId, NavigationConstants.FRIENDLY_URL_INFO,
					OSBLCSPortletKeys.INFO, false, request, "errorMessage",
					"project-invitation-is-not-valid");

			response.sendRedirect(liferayPortletURL.toString());

			return null;
		}

		try {
			validateLCSInvitation(companyId, lcsProjectId, user);
		}
		catch (NoSuchLCSInvitationException nslcsie) {
			LiferayPortletURL liferayPortletURL =
				_portletURLAdvisor.getPortletRenderURL(
					companyId, NavigationConstants.FRIENDLY_URL_INFO,
					OSBLCSPortletKeys.INFO, false, request, "errorMessage",
					"project-invitation-is-not-valid");

			response.sendRedirect(liferayPortletURL.toString());

			return null;
		}

		String parameterName = _portletURLAdvisor.getPublicRenderParameterName(
			"layoutLCSProjectId");

		LiferayPortletURL liferayPortletURL =
			_portletURLAdvisor.getPortletRenderURL(
				companyId, NavigationConstants.FRIENDLY_URL_DASHBOARD,
				OSBLCSPortletKeys.NAVIGATION, true, request, parameterName,
				String.valueOf(lcsProjectId));

		response.sendRedirect(liferayPortletURL.toString());

		return null;
	}

	@Reference(unbind = "-")
	public void setEmailAdvisor(EmailAdvisor emailAdvisor) {
		_emailAdvisor = emailAdvisor;
	}

	@Reference(unbind = "-")
	public void setLCSInvitationLocalService(
		LCSInvitationLocalService lcsInvitationLocalService) {

		_lcsInvitationLocalService = lcsInvitationLocalService;
	}

	@Reference(unbind = "-")
	public void setLCSMembersLocalService(
		LCSMembersLocalService lcsMembersLocalService) {

		_lcsMembersLocalService = lcsMembersLocalService;
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
	public void setLCSRoleLocalService(
		LCSRoleLocalService lcsRoleLocalService) {

		_lcsRoleLocalService = lcsRoleLocalService;
	}

	@Reference(unbind = "-")
	public void setOsbPortletService(OSBPortletService osbPortletService) {
		_osbPortletService = osbPortletService;
	}

	@Reference(unbind = "-")
	public void setPortletURLAdvisor(PortletURLAdvisor portletURLAdvisor) {
		_portletURLAdvisor = portletURLAdvisor;
	}

	protected String redirectToLogin(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		StringBundler sb = new StringBundler(4);

		sb.append(themeDisplay.getPathMain());
		sb.append("/portal/login?redirect=");
		sb.append(HttpUtil.encodeURL(request.getRequestURI()));

		String queryString = request.getQueryString();

		if (Validator.isNotNull(queryString)) {
			sb.append(
				HttpUtil.encodeURL(StringPool.QUESTION.concat(queryString)));
		}

		response.sendRedirect(sb.toString());

		return null;
	}

	protected void validateLCSInvitation(
			long companyId, long lcsProjectId, User user)
		throws PortalException {

		EmailContext.EmailContextBuilder emailContextBuilder =
			new EmailContext.EmailContextBuilder(
				LCSEventType.MEMBERSHIP_INVITATION_ACCEPTED);

		emailContextBuilder.emailAddress(user.getEmailAddress());
		emailContextBuilder.lcsProject(
			_lcsProjectLocalService.getLCSProject(lcsProjectId));
		emailContextBuilder.user(user);

		_emailAdvisor.sendToLCSProjectAdminsEmail(emailContextBuilder.build());

		LCSEventType lcsEventType = LCSEventType.MEMBERSHIP_INVITATION_ACCEPTED;

		_lcsMessageLocalService.addLCSProjectLCSMessage(
			lcsProjectId, LCSMessageConstants.LCS_SOURCE_MESSAGE_ID,
			LCSConstants.SOURCE_SYSTEM_NAME_LCS, user.getFullName(),
			new Date(LCSMessageConstants.END_DATE_INDEFINITE), false,
			lcsEventType.getSeverityLevel(), lcsEventType.getType(), true,
			true);

		LCSInvitation lcsInvitation =
			_lcsInvitationLocalService.getLCSProjectLCSInvitation(
				lcsProjectId, user.getEmailAddress());

		_lcsRoleLocalService.toLCSRole(lcsInvitation);

		if (_log.isDebugEnabled()) {
			_log.debug("Converted LCS invitation to LCS role");
		}

		LCSProject lcsProject = _lcsProjectLocalService.getLCSProject(
			lcsProjectId);

		_osbPortletService.addCorpProjectUsers(
			lcsProject.getCorpProjectId(), new long[] {user.getUserId()});

		_osbPortletService.addUserCorpProjectRoles(
			lcsProject.getCorpProjectId(), new long[] {user.getUserId()},
			OSBPortletConstants.ROLE_OSB_CORP_LCS_USER);

		_lcsMembersLocalService.validateLCSSiteMembership(
			companyId, user.getUserId());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CheckLCSInvitationAction.class);

	private EmailAdvisor _emailAdvisor;
	private LCSInvitationLocalService _lcsInvitationLocalService;
	private LCSMembersLocalService _lcsMembersLocalService;
	private LCSMessageLocalService _lcsMessageLocalService;
	private LCSProjectLocalService _lcsProjectLocalService;
	private LCSRoleLocalService _lcsRoleLocalService;
	private OSBPortletService _osbPortletService;
	private PortletURLAdvisor _portletURLAdvisor;

}