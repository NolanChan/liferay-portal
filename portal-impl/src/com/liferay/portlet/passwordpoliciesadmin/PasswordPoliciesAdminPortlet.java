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

package com.liferay.portlet.passwordpoliciesadmin;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import com.liferay.portal.DuplicatePasswordPolicyException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.PasswordPolicy;
import com.liferay.portal.NoSuchPasswordPolicyException;
import com.liferay.portal.PasswordPolicyNameException;
import com.liferay.portal.RequiredPasswordPolicyException;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.OrganizationServiceUtil;
import com.liferay.portal.service.PasswordPolicyServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Drew Brokke
 */
public class PasswordPoliciesAdminPortlet extends MVCPortlet {

	public void deletePasswordPolicy(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long passwordPolicyId = ParamUtil.getLong(
			actionRequest, "passwordPolicyId");

		PasswordPolicyServiceUtil.deletePasswordPolicy(passwordPolicyId);
	}

	public void editPasswordPolicy(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long passwordPolicyId = ParamUtil.getLong(
			actionRequest, "passwordPolicyId");

		String name = ParamUtil.getString(actionRequest, "name");
		String description = ParamUtil.getString(actionRequest, "description");
		boolean changeable = ParamUtil.getBoolean(actionRequest, "changeable");

		boolean changeRequired = false;
		long minAge = 0;

		if (changeable) {
			changeRequired = ParamUtil.getBoolean(
				actionRequest, "changeRequired");
			minAge = ParamUtil.getLong(actionRequest, "minAge");
		}

		boolean checkSyntax = ParamUtil.getBoolean(
			actionRequest, "checkSyntax");
		boolean allowDictionaryWords = ParamUtil.getBoolean(
			actionRequest, "allowDictionaryWords");
		int minAlphanumeric = ParamUtil.getInteger(
			actionRequest, "minAlphanumeric");
		int minLength = ParamUtil.getInteger(actionRequest, "minLength");
		int minLowerCase = ParamUtil.getInteger(actionRequest, "minLowerCase");
		int minNumbers = ParamUtil.getInteger(actionRequest, "minNumbers");
		int minSymbols = ParamUtil.getInteger(actionRequest, "minSymbols");
		int minUpperCase = ParamUtil.getInteger(actionRequest, "minUpperCase");
		String regex = ParamUtil.getString(actionRequest, "regex");
		boolean history = ParamUtil.getBoolean(actionRequest, "history");
		int historyCount = ParamUtil.getInteger(actionRequest, "historyCount");
		boolean expireable = ParamUtil.getBoolean(actionRequest, "expireable");
		long maxAge = ParamUtil.getLong(actionRequest, "maxAge");
		long warningTime = ParamUtil.getLong(actionRequest, "warningTime");
		int graceLimit = ParamUtil.getInteger(actionRequest, "graceLimit");
		boolean lockout = ParamUtil.getBoolean(actionRequest, "lockout");
		int maxFailure = ParamUtil.getInteger(actionRequest, "maxFailure");
		long lockoutDuration = ParamUtil.getLong(
			actionRequest, "lockoutDuration");
		long resetFailureCount = ParamUtil.getLong(
			actionRequest, "resetFailureCount");
		long resetTicketMaxAge = ParamUtil.getLong(
			actionRequest, "resetTicketMaxAge");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			PasswordPolicy.class.getName(), actionRequest);

		if (passwordPolicyId <= 0) {

			// Add password policy

			PasswordPolicyServiceUtil.addPasswordPolicy(
				name, description, changeable, changeRequired, minAge,
				checkSyntax, allowDictionaryWords, minAlphanumeric, minLength,
				minLowerCase, minNumbers, minSymbols, minUpperCase, regex,
				history, historyCount, expireable, maxAge, warningTime,
				graceLimit, lockout, maxFailure, lockoutDuration,
				resetFailureCount, resetTicketMaxAge, serviceContext);
		}
		else {

			// Update password policy

			PasswordPolicyServiceUtil.updatePasswordPolicy(
				passwordPolicyId, name, description, changeable, changeRequired,
				minAge, checkSyntax, allowDictionaryWords, minAlphanumeric,
				minLength, minLowerCase, minNumbers, minSymbols, minUpperCase,
				regex, history, historyCount, expireable, maxAge, warningTime,
				graceLimit, lockout, maxFailure, lockoutDuration,
				resetFailureCount, resetTicketMaxAge, serviceContext);
		}
	}

	public void editPasswordPolicyOrganizations(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long passwordPolicyId = ParamUtil.getLong(
			actionRequest, "passwordPolicyId");

		long[] addOrganizationIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "addOrganizationIds"), 0L);
		long[] removeOrganizationIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "removeOrganizationIds"), 0L);

		OrganizationServiceUtil.addPasswordPolicyOrganizations(
			passwordPolicyId, addOrganizationIds);
		OrganizationServiceUtil.unsetPasswordPolicyOrganizations(
			passwordPolicyId, removeOrganizationIds);
	}

	public void editPasswordPolicyUsers(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long passwordPolicyId = ParamUtil.getLong(
			actionRequest, "passwordPolicyId");

		long[] addUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "addUserIds"), 0L);
		long[] removeUserIds = StringUtil.split(
			ParamUtil.getString(actionRequest, "removeUserIds"), 0L);

		UserServiceUtil.addPasswordPolicyUsers(passwordPolicyId, addUserIds);
		UserServiceUtil.unsetPasswordPolicyUsers(
			passwordPolicyId, removeUserIds);
	}

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		if (SessionErrors.contains(
				renderRequest, NoSuchPasswordPolicyException.class.getName()) ||
				SessionErrors.contains(
					renderRequest, PrincipalException.class.getName())) {

			include("/error.jsp", renderRequest, renderResponse);
		}
		else {
			super.doDispatch(renderRequest, renderResponse);
		}
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof DuplicatePasswordPolicyException ||
				cause instanceof NoSuchPasswordPolicyException ||
				cause instanceof PasswordPolicyNameException ||
				cause instanceof PrincipalException ||
				cause instanceof RequiredPasswordPolicyException) {

			return true;
		}

		return false;
	}

}