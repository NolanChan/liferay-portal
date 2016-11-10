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

package com.liferay.osb.lcs.internal.events;

import com.liferay.osb.lcs.DuplicateLCSProjectCorpProjectIdException;
import com.liferay.osb.lcs.NoSuchLCSRoleException;
import com.liferay.osb.lcs.advisor.CompanyAdvisor;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.model.LCSRoleConstants;
import com.liferay.osb.lcs.osbportlet.service.OSBPortletServiceUtil;
import com.liferay.osb.lcs.osbportlet.util.OSBPortletUtil;
import com.liferay.osb.lcs.service.LCSMembersServiceUtil;
import com.liferay.osb.lcs.service.LCSProjectLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSRoleLocalServiceUtil;
import com.liferay.osb.lcs.util.ApplicationProfile;
import com.liferay.osb.lcs.util.PortletPropsValues;
import com.liferay.osb.model.CorpProject;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Igor Beslic
 */
public class SetUpQualityAssuranceEnvironmentAction extends SimpleAction {

	public static final String[] CORP_PROJECT_NAMES = {
		"Liferay QA"
	};

	public static final String[] USER_EMAIL_ADDRESSES = {
		"liferay.qa.cloud1@gmail.com", "liferay.qa.cloud2@gmail.com",
		"liferay.qa.cloud3@gmail.com"
	};

	public static final String[] USER_NAMES = {
		"Liferay Cloud1", "Liferay Cloud2", "Liferay Cloud3"
	};

	@Override
	public void run(String[] ids) throws ActionException {
		if (PortletPropsValues.APPLICATION_PROFILE !=
				ApplicationProfile.QUALITY_ASSURANCE) {

			return;
		}

		_scheduledExecutorService.schedule(
			new Runnable() {

				@Override
				public void run() {
					if (_log.isInfoEnabled()) {
						_log.info("Syncing Quality Assurance environment");
					}

					try {
						checkUsers();
					}
					catch (Exception e) {
						_log.error(e.getMessage(), e);
					}
				}

			}, 5, TimeUnit.MINUTES);
	}

	protected void checkUserCorpProject(User user) throws Exception {
		List<CorpProject> corpProjects =
			(List<CorpProject>)OSBPortletServiceUtil.getUserCorpProjects(
				user.getUserId());

		for (CorpProject corpProject : corpProjects) {
			if (StringUtil.equalsIgnoreCase(
					corpProject.getName(), CORP_PROJECT_NAMES[0])) {

				return;
			}
		}

		CorpProject corpProject = OSBPortletServiceUtil.addCorpProject(
			user.getUserId(), CORP_PROJECT_NAMES[0]);

		OSBPortletServiceUtil.addUserCorpProjectRoles(
			corpProject.getCorpProjectId(), new long[] {user.getUserId()},
			OSBPortletUtil.ROLE_OSB_CORP_LCS_USER);

		LCSProject lcsProject = null;

		try {
			lcsProject = LCSProjectLocalServiceUtil.addLCSProject(
				corpProject, user.getUserId());
		}
		catch (DuplicateLCSProjectCorpProjectIdException dlcspcpide) {
			if (_log.isWarnEnabled()) {
				_log.warn("Reusing existing LCS project");
			}

			lcsProject = LCSProjectLocalServiceUtil.fetchByCorpProject(
				corpProject.getCorpProjectId());
		}

		LCSRoleLocalServiceUtil.addLCSRole(
			user.getUserId(), lcsProject.getLcsProjectId(), 0,
			LCSRoleConstants.ROLE_LCS_ADMINISTRATOR);
	}

	protected void checkUserLCSRole(User user) throws Exception {
		List<CorpProject> corpProjects =
			(List<CorpProject>)OSBPortletServiceUtil.getUserCorpProjects(
				user.getUserId());

		for (CorpProject corpProject : corpProjects) {
			if (!StringUtil.equalsIgnoreCase(
					corpProject.getName(), CORP_PROJECT_NAMES[0])) {

				continue;
			}

			LCSProject lcsProject =
				LCSProjectLocalServiceUtil.fetchByCorpProject(
					corpProject.getCorpProjectId());

			if (lcsProject == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("QA setup completed without LCS role setup");
				}

				return;
			}

			boolean hasUserLCSAdministratorRole =
				LCSRoleLocalServiceUtil.hasUserLCSAdministratorLCSRole(
					user.getUserId(), lcsProject.getLcsProjectId());

			if (!hasUserLCSAdministratorRole) {
				LCSRoleLocalServiceUtil.addLCSRole(
					user.getUserId(), lcsProject.getLcsProjectId(), 0,
					LCSRoleConstants.ROLE_LCS_ADMINISTRATOR);
			}

			LCSMembersServiceUtil.validateLCSSiteMembership(
				user.getCompanyId(), user.getUserId());

			return;
		}

		throw new NoSuchLCSRoleException(
			LCSRoleConstants.getRoleLabel(
				LCSRoleConstants.ROLE_LCS_ADMINISTRATOR));
	}

	protected void checkUsers() throws Exception {
		User user = getUser(_companyAdvisor.getCompanyId());

		User localLCSAdministratorUser = null;

		for (int i = 0; i < USER_EMAIL_ADDRESSES.length; i++) {
			String emailAddress = USER_EMAIL_ADDRESSES[i];

			try {
				User remoteUser = OSBPortletServiceUtil.getRemoteUser(
					emailAddress);

				User localUser = UserLocalServiceUtil.fetchUserByEmailAddress(
					_companyAdvisor.getCompanyId(),
					remoteUser.getEmailAddress());

				if (localUser == null) {
					String userFullName = USER_NAMES[i];

					String[] userFullNameParts = userFullName.split("\\s");

					localUser = UserLocalServiceUtil.addUser(
						user.getUserId(), _companyAdvisor.getCompanyId(), false,
						"liferayqa", "liferayqa", true, null, emailAddress, 0,
						null, null, userFullNameParts[0], null,
						userFullNameParts[1], 0, 0, true, 9, 9, 2003, null,
						new long[0], new long[0], new long[0], new long[0],
						false, null);
				}

				String remoteUserUuid = remoteUser.getUuid();

				if (!remoteUserUuid.equals(localUser.getUuid())) {
					localUser.setUuid(remoteUserUuid);

					localUser = UserLocalServiceUtil.updateUser(localUser);
				}

				if (!StringUtil.equalsIgnoreCase(
						localUser.getEmailAddress(), USER_EMAIL_ADDRESSES[0])) {

					continue;
				}

				localLCSAdministratorUser = localUser;
			}
			catch (Exception e) {
				_log.error("No such remote user " + emailAddress);
			}
		}

		if (localLCSAdministratorUser == null) {
			_log.error("No Liferay QA LCS Administrator user");

			throw new NoSuchUserException();
		}

		checkUserCorpProject(localLCSAdministratorUser);

		checkUserLCSRole(localLCSAdministratorUser);
	}

	protected User getUser(long companyId) throws Exception {
		Role role = RoleLocalServiceUtil.getRole(
			companyId, RoleConstants.ADMINISTRATOR);

		List<User> users = UserLocalServiceUtil.getRoleUsers(role.getRoleId());

		return users.get(0);
	}

	private static Log _log = LogFactoryUtil.getLog(
		SetUpQualityAssuranceEnvironmentAction.class);

	@BeanReference(type = CompanyAdvisor.class)
	private static CompanyAdvisor _companyAdvisor;

	private static ScheduledExecutorService _scheduledExecutorService =
		Executors.newScheduledThreadPool(1);

}