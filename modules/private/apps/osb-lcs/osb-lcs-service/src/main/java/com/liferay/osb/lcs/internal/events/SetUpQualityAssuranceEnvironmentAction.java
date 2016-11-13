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

import com.liferay.osb.lcs.advisor.CompanyAdvisor;
import com.liferay.osb.lcs.advisor.impl.CompanyAdvisorImpl;
import com.liferay.osb.lcs.configuration.OSBLCSConfiguration;
import com.liferay.osb.lcs.constants.LCSRoleConstants;
import com.liferay.osb.lcs.constants.OSBPortletConstants;
import com.liferay.osb.lcs.exception.DuplicateLCSProjectCorpProjectIdException;
import com.liferay.osb.lcs.exception.NoSuchLCSRoleException;
import com.liferay.osb.lcs.model.CorpProject;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.osbportlet.service.OSBPortletService;
import com.liferay.osb.lcs.service.LCSMembersLocalService;
import com.liferay.osb.lcs.service.LCSProjectLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSRoleLocalServiceUtil;
import com.liferay.osb.lcs.util.ApplicationProfile;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(
	configurationPid = "com.liferay.osb.lcs.configuration.OSBLCSConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true,
	property = {"key=application.startup.events"},
	service = LifecycleAction.class
)
public class SetUpQualityAssuranceEnvironmentAction implements LifecycleAction {

	public static final String[] CORP_PROJECT_NAMES = {"Liferay QA"};

	public static final String[] USER_EMAIL_ADDRESSES = {
		"liferay.qa.cloud1@gmail.com", "liferay.qa.cloud2@gmail.com",
		"liferay.qa.cloud3@gmail.com"
	};

	public static final String[] USER_NAMES = {
		"Liferay Cloud1", "Liferay Cloud2", "Liferay Cloud3"
	};

	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent)
		throws ActionException {

		if (_osbLCSConfiguration.applicationProfile() !=
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

			},
			5, TimeUnit.MINUTES);
	}

	@Reference(bind = "-", unbind = "-")
	public void setCompanyAdvisor(CompanyAdvisorImpl companyAdvisor) {
		_companyAdvisor = companyAdvisor;
	}

	@Reference(bind = "-", unbind = "-")
	public void setLCSMembersLocalService(
		LCSMembersLocalService lcsMembersLocalService) {

		_lcsMembersLocalService = lcsMembersLocalService;
	}

	@Reference(bind = "-", unbind = "-")
	public void setOSBPortletService(OSBPortletService osbPortletService) {
		_osbPortletService = osbPortletService;
	}

	@Reference(bind = "-", unbind = "-")
	public void setRoleLocalService(RoleLocalService roleLocalService) {
		_roleLocalService = roleLocalService;
	}

	@Reference(bind = "-", unbind = "-")
	public void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_osbLCSConfiguration = ConfigurableUtil.createConfigurable(
			OSBLCSConfiguration.class, properties);
	}

	protected void checkUserCorpProject(User user) throws Exception {
		List<CorpProject> corpProjects =
			(List<CorpProject>)_osbPortletService.getUserCorpProjects(
				user.getUserId());

		for (CorpProject corpProject : corpProjects) {
			if (StringUtil.equalsIgnoreCase(
					corpProject.getName(), CORP_PROJECT_NAMES[0])) {

				return;
			}
		}

		CorpProject corpProject = _osbPortletService.addCorpProject(
			user.getUserId(), CORP_PROJECT_NAMES[0]);

		_osbPortletService.addUserCorpProjectRoles(
			corpProject.getCorpProjectId(), new long[] {user.getUserId()},
			OSBPortletConstants.ROLE_OSB_CORP_LCS_USER);

		LCSProject lcsProject = null;

		try {
			lcsProject = LCSProjectLocalServiceUtil.addLCSProject(
				corpProject, user.getUserId());
		}
		catch (DuplicateLCSProjectCorpProjectIdException dlcspcpie) {
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
			(List<CorpProject>)_osbPortletService.getUserCorpProjects(
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

			_lcsMembersLocalService.validateLCSSiteMembership(
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
				User remoteUser = _osbPortletService.getRemoteUser(
					emailAddress);

				User localUser = _userLocalService.fetchUserByEmailAddress(
					_companyAdvisor.getCompanyId(),
					remoteUser.getEmailAddress());

				if (localUser == null) {
					String userFullName = USER_NAMES[i];

					String[] userFullNameParts = userFullName.split("\\s");

					localUser = _userLocalService.addUser(
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

					localUser = _userLocalService.updateUser(localUser);
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
		Role role = _roleLocalService.getRole(
			companyId, RoleConstants.ADMINISTRATOR);

		List<User> users = _userLocalService.getRoleUsers(role.getRoleId());

		return users.get(0);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SetUpQualityAssuranceEnvironmentAction.class);

	private static volatile OSBLCSConfiguration _osbLCSConfiguration;
	private static final ScheduledExecutorService _scheduledExecutorService =
		Executors.newScheduledThreadPool(1);

	private CompanyAdvisor _companyAdvisor;
	private LCSMembersLocalService _lcsMembersLocalService;
	private OSBPortletService _osbPortletService;
	private RoleLocalService _roleLocalService;
	private UserLocalService _userLocalService;

}