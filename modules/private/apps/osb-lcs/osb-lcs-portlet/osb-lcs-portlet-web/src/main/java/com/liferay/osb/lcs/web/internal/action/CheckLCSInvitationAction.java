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
import com.liferay.osb.lcs.exception.NoSuchLCSInvitationException;
import com.liferay.osb.lcs.advisor.LCSMessageAdvisor;
import com.liferay.osb.lcs.email.EmailAdvisor;
import com.liferay.osb.lcs.email.EmailContext;
import com.liferay.osb.lcs.model.LCSInvitation;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.navigation.util.NavigationUtil;
import com.liferay.osb.lcs.osbportlet.service.OSBPortletServiceUtil;
import com.liferay.osb.lcs.osbportlet.util.OSBPortletUtil;
import com.liferay.osb.lcs.service.LCSInvitationLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSMembersLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSProjectLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSRoleLocalServiceUtil;
import com.liferay.osb.lcs.util.PortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.util.bean.PortletBeanLocatorUtil;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Igor Beslic
 * @author Marko Cikos
 */
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
				NavigationUtil.getPortletRenderURL(
					companyId, NavigationUtil.FRIENDLY_URL_INFO,
					PortletKeys.INFO, false, request, "errorMessage",
					"project-invitation-is-not-valid");

			response.sendRedirect(liferayPortletURL.toString());

			return null;
		}

		if (!LCSInvitationLocalServiceUtil.hasUserLCSInvitation(
				user.getUserId())) {

			LiferayPortletURL liferayPortletURL =
				NavigationUtil.getPortletRenderURL(
					companyId, NavigationUtil.FRIENDLY_URL_INFO,
					PortletKeys.INFO, false, request, "errorMessage",
					"project-invitation-is-not-valid");

			response.sendRedirect(liferayPortletURL.toString());

			return null;
		}

		try {
			validateLCSInvitation(companyId, lcsProjectId, user);
		}
		catch (NoSuchLCSInvitationException nslcsie) {
			LiferayPortletURL liferayPortletURL =
				NavigationUtil.getPortletRenderURL(
					companyId, NavigationUtil.FRIENDLY_URL_INFO,
					PortletKeys.INFO, false, request, "errorMessage",
					"project-invitation-is-not-valid");

			response.sendRedirect(liferayPortletURL.toString());

			return null;
		}

		String parameterName = NavigationUtil.getPublicRenderParameterName(
			"layoutLCSProjectId");

		LiferayPortletURL liferayPortletURL =
			NavigationUtil.getPortletRenderURL(
				companyId, NavigationUtil.FRIENDLY_URL_DASHBOARD,
				PortletKeys.NAVIGATION, true, request, parameterName,
				String.valueOf(lcsProjectId));

		response.sendRedirect(liferayPortletURL.toString());

		return null;
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

		if (_emailAdvisor == null) {
			_emailAdvisor = (EmailAdvisor)PortletBeanLocatorUtil.locate(
				"com.liferay.osb.lcs.email.EmailAdvisor");
		}

		_emailAdvisor.sendToLCSProjectAdminsEmail(
			new EmailContext(
				null, user.getEmailAddress(),
				LCSEventType.MEMBERSHIP_INVITATION_ACCEPTED, lcsProjectId,
				user.getUserId()));

		if (_lcsMessageAdvisor == null) {
			_lcsMessageAdvisor =
				(LCSMessageAdvisor)PortletBeanLocatorUtil.locate(
					"com.liferay.osb.lcs.advisor.LCSMessageAdvisor");
		}

		_lcsMessageAdvisor.addLCSProjectLCSMessage(
			true, user.getFullName(), true,
			LCSEventType.MEMBERSHIP_INVITATION_ACCEPTED, lcsProjectId);

		LCSInvitation lcsInvitation =
			LCSInvitationLocalServiceUtil.getLCSProjectLCSInvitation(
				lcsProjectId, user.getEmailAddress());

		LCSRoleLocalServiceUtil.toLCSRole(lcsInvitation);

		if (_log.isDebugEnabled()) {
			_log.debug("Converted LCS invitation to LCS role");
		}

		LCSProject lcsProject = LCSProjectLocalServiceUtil.getLCSProject(
			lcsProjectId);

		OSBPortletServiceUtil.addCorpProjectUsers(
			lcsProject.getCorpProjectId(), new long[] {user.getUserId()});

		OSBPortletServiceUtil.addUserCorpProjectRoles(
			lcsProject.getCorpProjectId(), new long[] {user.getUserId()},
			OSBPortletUtil.ROLE_OSB_CORP_LCS_USER);

		LCSMembersLocalServiceUtil.validateLCSSiteMembership(
			companyId, user.getUserId());
	}

	private static Log _log = LogFactoryUtil.getLog(
		CheckLCSInvitationAction.class);

	private EmailAdvisor _emailAdvisor;
	private LCSMessageAdvisor _lcsMessageAdvisor;

}