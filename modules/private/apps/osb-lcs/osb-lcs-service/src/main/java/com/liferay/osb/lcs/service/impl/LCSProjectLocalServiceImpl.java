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

package com.liferay.osb.lcs.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.lcs.notification.LCSEventType;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.osb.lcs.advisor.EmailAdvisor;
import com.liferay.osb.lcs.advisor.LCSMessageAdvisor;
import com.liferay.osb.lcs.advisor.StringAdvisor;
import com.liferay.osb.lcs.advisor.UserAdvisor;
import com.liferay.osb.lcs.configuration.OSBLCSConfiguration;
import com.liferay.osb.lcs.constants.LCSMessageConstants;
import com.liferay.osb.lcs.constants.LCSRoleConstants;
import com.liferay.osb.lcs.email.EmailContext;
import com.liferay.osb.lcs.exception.DuplicateLCSProjectCorpProjectIdException;
import com.liferay.osb.lcs.exception.DuplicateLCSProjectNameException;
import com.liferay.osb.lcs.exception.NoSuchLCSProjectException;
import com.liferay.osb.lcs.model.CorpProject;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSMessage;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.model.LCSRole;
import com.liferay.osb.lcs.osbportlet.service.OSBPortletService;
import com.liferay.osb.lcs.service.base.LCSProjectLocalServiceBaseImpl;
import com.liferay.osb.lcs.util.ApplicationProfile;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UniqueList;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author Igor Beslic
 * @see    LCSProjectLocalServiceBaseImpl
 * @see    com.liferay.osb.lcs.service.LCSProjectLocalServiceUtil
 */
@ProviderType
public class LCSProjectLocalServiceImpl extends LCSProjectLocalServiceBaseImpl {

	@Override
	public LCSProject addLCSProject(CorpProject corpProject, long userId)
		throws PortalException {

		LCSProject currentLCSProject =
			lcsProjectPersistence.fetchByCorpProjectId(
				corpProject.getCorpProjectId());

		if (currentLCSProject != null) {
			throw new DuplicateLCSProjectCorpProjectIdException();
		}

		LCSProject lcsProject = createLCSProject(
			counterLocalService.increment(LCSProject.class.getName()));

		lcsProject.setCreateDate(new Date());
		lcsProject.setModifiedDate(new Date());
		lcsProject.setSourceSystemName(LCSConstants.SOURCE_SYSTEM_NAME_LRDCOM);
		lcsProject.setName(corpProject.getName());
		lcsProject.setOrganizationId(corpProject.getOrganizationId());
		lcsProject.setCorpProjectId(corpProject.getCorpProjectId());

		lcsProject = addLCSProject(lcsProject);

		lcsRoleLocalService.addLCSRole(
			userId, lcsProject.getLcsProjectId(), 0,
			LCSRoleConstants.ROLE_LCS_ADMINISTRATOR);

		return lcsProject;
	}

	@Override
	public LCSProject addLCSProject(long corpProjectId) throws PortalException {
		LCSProject currentLCSProject =
			lcsProjectPersistence.fetchByCorpProjectId(corpProjectId);

		if (currentLCSProject != null) {
			throw new DuplicateLCSProjectCorpProjectIdException();
		}

		CorpProject corpProject = _osbPortletService.getCorpProject(
			corpProjectId);

		List<User> users = new ArrayList<>();

		if (_log.isDebugEnabled()) {
			_log.debug(
				_stringAdvisor.concat(
					"Resolving users for corp project", corpProjectId,
					"using account entry"));
		}

		List<String> uuids =
			_osbPortletService.getCorpProjectAccountCustomerUUIDs(
				corpProjectId);

		if (uuids.isEmpty()) {
			throw new UnsupportedOperationException(
				_stringAdvisor.concat(
					"There are no remote account customer users to join LCS",
					"project with the corp project ID", corpProjectId));
		}

		Company company = companyLocalService.getCompanyByWebId("liferay.com");

		for (String uuid : uuids) {
			User user = userLocalService.fetchUserByUuidAndCompanyId(
				uuid, company.getCompanyId());

			if (user != null) {
				users.add(user);
			}
			else {
				users.add(_userAdvisor.addLDAPUser(uuid));
			}
		}

		if (users.isEmpty()) {
			throw new UnsupportedOperationException(
				_stringAdvisor.concat(
					"There are no local users to join LCS project with the",
					"corp project ID", corpProjectId));
		}

		User user = users.get(0);

		if (_log.isDebugEnabled()) {
			_log.debug(
				_stringAdvisor.concat(
					"Creating LCS project for corp project ID", corpProjectId,
					"using user with UUID", user.getUserUuid()));
		}

		LCSProject lcsProject = addLCSProject(corpProject, user.getUserId());

		LCSMessage lcsMessage = lcsMessageLocalService.addLCSProjectLCSMessage(
			lcsProject.getLcsProjectId(),
			LCSMessageConstants.LCS_SOURCE_MESSAGE_ID,
			lcsProject.getSourceSystemName(), null,
			new Date(LCSMessageConstants.END_DATE_INDEFINITE), false,
			LCSEventType.NEW_LCS_PROJECT_AVAILABLE.getSeverityLevel(),
			LCSEventType.NEW_LCS_PROJECT_AVAILABLE.getType(), true, true);

		if (_log.isDebugEnabled()) {
			_log.debug(
				_stringAdvisor.concat(
					"LCS project created. Setting up LCS project for",
					users.size() - 1, "other corp project members."));
		}

		for (int i = 1; i < users.size(); i++) {
			user = users.get(i);

			setUpLCSAdministratorUserLCSProject(
				user, lcsMessage.getLcsMessageId(),
				lcsProject.getLcsProjectId());
		}

		return lcsProject;
	}

	@Override
	public LCSProject addLCSProject(
			String sourceSystemName, String name, long userId)
		throws PortalException {

		OSBLCSConfiguration configuration = getConfiguration();

		if (configuration.applicationProfile() ==
				ApplicationProfile.PRODUCTION) {

			throw new UnsupportedOperationException(
				_stringAdvisor.concat(
					"Manual LCS project creation is not allowed in",
					ApplicationProfile.PRODUCTION, "runtime environment"));
		}

		List<LCSProject> lcsProjects = getUserLCSProjects(userId);

		for (LCSProject lcsProject : lcsProjects) {
			if (Objects.equals(name, lcsProject.getName())) {
				throw new DuplicateLCSProjectNameException();
			}
		}

		LCSProject lcsProject = createLCSProject(
			counterLocalService.increment(LCSProject.class.getName()));

		lcsProject.setCreateDate(new Date());
		lcsProject.setModifiedDate(new Date());
		lcsProject.setSourceSystemName(sourceSystemName);
		lcsProject.setName(name);

		lcsProject = addLCSProject(lcsProject);

		CorpProject corpProject = _osbPortletService.addCorpProject(
			userId, name);

		lcsProject.setOrganizationId(corpProject.getOrganizationId());
		lcsProject.setCorpProjectId(corpProject.getCorpProjectId());

		lcsProject = updateLCSProject(lcsProject);

		lcsRoleLocalService.addLCSRole(
			userId, lcsProject.getLcsProjectId(), 0,
			LCSRoleConstants.ROLE_LCS_ADMINISTRATOR);

		return lcsProject;
	}

	@Override
	public boolean checkUserAccountEntryLCSProject(User user)
		throws PortalException {

		String userUUID = user.getUserUuid();

		for (CorpProject corpProject :
				_osbPortletService.getUserCorpProjects(user.getUserId())) {

			LCSProject lcsProject = fetchByCorpProject(
				corpProject.getCorpProjectId());

			if (lcsProject == null) {
				continue;
			}

			if (!lcsSubscriptionEntryLocalService.
					hasLCSProjectLCSSubscriptionEntry(
						lcsProject.getLcsProjectId())) {

				continue;
			}

			if (lcsRoleLocalService.hasUserLCSRole(
					user.getUserId(), lcsProject.getLcsProjectId(), false)) {

				continue;
			}

			List<String> uuids =
				_osbPortletService.getCorpProjectAccountCustomerUUIDs(
					corpProject.getCorpProjectId());

			for (String uuid : uuids) {
				if (!userUUID.equals(uuid)) {
					continue;
				}

				LCSMessage lcsMessage =
					lcsMessageLocalService.fetchLastLCSProjectLCSMessage(
						lcsProject.getLcsProjectId(),
						LCSEventType.NEW_LCS_PROJECT_AVAILABLE.getType());

				if (lcsMessage == null) {
					int severityLevel =
						LCSEventType.
							NEW_LCS_PROJECT_AVAILABLE.getSeverityLevel();

					lcsMessage = lcsMessageLocalService.addLCSProjectLCSMessage(
						lcsProject.getLcsProjectId(),
						LCSMessageConstants.LCS_SOURCE_MESSAGE_ID,
						lcsProject.getSourceSystemName(), null,
						new Date(LCSMessageConstants.END_DATE_INDEFINITE),
						false, severityLevel,
						LCSEventType.NEW_LCS_PROJECT_AVAILABLE.getType(), true,
						true);
				}

				setUpLCSAdministratorUserLCSProject(
					user, lcsMessage.getLcsMessageId(),
					lcsProject.getLcsProjectId());

				return true;
			}
		}

		return false;
	}

	@Override
	public LCSProject deleteLCSProject(LCSProject lcsProject) {
		lcsProject = super.deleteLCSProject(lcsProject);

		try {
			List<LCSClusterEntry> lcsClusterEntries =
				lcsClusterEntryPersistence.findByLCSProjectId(
					lcsProject.getLcsProjectId());

			for (LCSClusterEntry lcsClusterEntry : lcsClusterEntries) {
				lcsClusterEntryLocalService.deleteLCSClusterEntry(
					lcsClusterEntry);
			}

			lcsMessageLocalService.deleteLCSProjectLCSMessages(
				lcsProject.getLcsProjectId());

			lcsNotificationLocalService.deleteLCSProjectLCSNotification(
				lcsProject.getLcsProjectId());

			lcsSubscriptionEntryLocalService.
				deleteLCSProjectLCSSubscriptionEntries(
					lcsProject.getLcsProjectId());

			List<LCSRole> lcsRoles = lcsRolePersistence.findByLCSProjectId(
				lcsProject.getLcsProjectId());

			for (LCSRole lcsRole : lcsRoles) {
				lcsRoleLocalService.deleteLCSRole(lcsRole, true);
			}
		}
		catch (PortalException pe) {
			_log.error("Unable to delete LCS project", pe);

			throw new SystemException();
		}

		return lcsProject;
	}

	@Override
	public LCSProject deleteLCSProject(long lcsProjectId)
		throws PortalException {

		LCSProject lcsProject = lcsProjectPersistence.findByPrimaryKey(
			lcsProjectId);

		return deleteLCSProject(lcsProject);
	}

	@Override
	public LCSProject fetchByCorpProject(long corpProjectId) {
		LCSProject lcsProject = lcsProjectPersistence.fetchByCorpProjectId(
			corpProjectId);

		if ((lcsProject != null) && lcsProject.isArchived()) {
			return null;
		}

		return lcsProject;
	}

	@Override
	public LCSProject findByCorpProject(long corpProjectId)
		throws PortalException {

		LCSProject lcsProject = lcsProjectPersistence.findByCorpProjectId(
			corpProjectId);

		if (lcsProject.getArchived()) {
			throw new NoSuchLCSProjectException(
				_stringAdvisor.concat(
					"LCS project with corp project ID", corpProjectId,
					"is archived"));
		}

		return lcsProject;
	}

	@Override
	public List<LCSProject> findByName(String name) {
		return lcsProjectPersistence.findByName(name);
	}

	@Override
	public long[] getLocalCorpProjectIds() {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			LCSProject.class, this.getClassLoader());

		dynamicQuery.add(RestrictionsFactoryUtil.eq("archived", false));

		Projection projection = ProjectionFactoryUtil.distinct(
			ProjectionFactoryUtil.property("corpProjectId"));

		dynamicQuery.setProjection(projection);

		List<Long> corpProjectIds = dynamicQuery(dynamicQuery);

		return ArrayUtil.toLongArray(corpProjectIds);
	}

	@Override
	public List<LCSProject> getUserDomainLCSProjects(User user) {
		String emailAddress = user.getEmailAddress();

		String domain = emailAddress.substring(
			emailAddress.indexOf(StringPool.AT));

		domain = domain.replaceFirst("\\..+$", StringPool.BLANK);

		if (_EMAIL_ADDRESS_SUBDOMAINS.contains(domain)) {
			return Collections.emptyList();
		}

		List<Long> userIds = getUserIdsByEmailAddressDomain(
			user.getUserId(), domain);

		if (userIds.isEmpty()) {
			return Collections.emptyList();
		}

		DynamicQuery query = DynamicQueryFactoryUtil.forClass(LCSRole.class);

		query.add(
			RestrictionsFactoryUtil.eq(
				"role", LCSRoleConstants.ROLE_LCS_ADMINISTRATOR));
		query.add(RestrictionsFactoryUtil.in("userId", userIds));

		List<LCSRole> lcsRoles = dynamicQuery(query);

		if (lcsRoles.isEmpty()) {
			return Collections.emptyList();
		}

		Set<LCSProject> lcsProjects = new HashSet<>();

		for (LCSRole lcsRole : lcsRoles) {
			int count = lcsRolePersistence.countByU_LPI(
				user.getUserId(), lcsRole.getLcsProjectId());

			if (count != 0) {
				continue;
			}

			LCSProject lcsProject = lcsProjectPersistence.fetchByPrimaryKey(
				lcsRole.getLcsProjectId());

			if (lcsProject == null) {
				continue;
			}

			lcsProjects.add(lcsProject);
		}

		return ListUtil.fromCollection(lcsProjects);
	}

	@Override
	public List<LCSProject> getUserLCSProjects(long userId)
		throws PortalException {

		return getUserLCSProjects(
			userId, false,
			LCSRoleConstants.ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER);
	}

	@Override
	public List<LCSProject> getUserLCSProjects(long userId, boolean lcsRole)
		throws PortalException {

		List<LCSProject> lcsProjects = new ArrayList<>();

		for (CorpProject corpProject :
				_osbPortletService.getUserCorpProjects(userId)) {

			LCSProject lcsProject = null;

			try {
				lcsProject = findByCorpProject(corpProject.getCorpProjectId());
			}
			catch (PortalException pe) {
				_log.error(
					"No LCS project with corp project ID " +
						corpProject.getCorpProjectId(),
					pe);

				continue;
			}

			List<LCSRole> lcsRoles = lcsRoleLocalService.getUserLCSRoles(
				userId, lcsProject.getLcsProjectId());

			if ((lcsRole && !lcsRoles.isEmpty()) ||
				(!lcsRole && lcsRoles.isEmpty())) {

				lcsProjects.add(lcsProject);
			}
		}

		return lcsProjects;
	}

	@Override
	public List<LCSProject> getUserLCSProjects(
			long userId, boolean lcsRole, int role)
		throws PortalException {

		List<LCSProject> lcsProjects = new UniqueList<>();

		List<LCSRole> lcsRoles = lcsRoleLocalService.getUserLCSRoles(userId);

		for (LCSRole curLCSRole : lcsRoles) {
			if ((lcsRole && (role == curLCSRole.getRole())) ||
				(!lcsRole && (role != curLCSRole.getRole()))) {

				LCSProject lcsProject = lcsProjectPersistence.fetchByPrimaryKey(
					curLCSRole.getLcsProjectId());

				if (lcsProject != null) {
					lcsProjects.add(lcsProject);
				}
			}
		}

		return lcsProjects;
	}

	@Override
	public LCSProject updateSubscriptionActive(
			long lcsProjectId, boolean subscriptionActive)
		throws PortalException {

		LCSProject lcsProject = getLCSProject(lcsProjectId);

		lcsProject.setSubscriptionActive(subscriptionActive);

		return updateLCSProject(lcsProject);
	}

	protected OSBLCSConfiguration getConfiguration()
		throws ConfigurationException {

		return _configurationProvider.getCompanyConfiguration(
			OSBLCSConfiguration.class, 0);
	}

	protected List<Long> getUserIdsByEmailAddressDomain(
		long userId, String domain) {

		String emailAddressWildcard =
			StringPool.PERCENT + domain + StringPool.PERCENT;

		DynamicQuery query = DynamicQueryFactoryUtil.forClass(User.class);

		query.add(
			RestrictionsFactoryUtil.like("emailAddress", emailAddressWildcard));
		query.add(RestrictionsFactoryUtil.ne("userId", userId));

		List<User> users = dynamicQuery(query);

		if (users.isEmpty()) {
			return Collections.emptyList();
		}

		List<Long> userIds = new ArrayList<>();

		for (User user : users) {
			userIds.add(user.getUserId());
		}

		return userIds;
	}

	protected void setUpLCSAdministratorUserLCSProject(
			User user, long lcsMessageId, long lcsProjectId)
		throws PortalException {

		EmailContext.EmailContextBuilder emailContextBuilder =
			new EmailContext.EmailContextBuilder(
				LCSEventType.NEW_PROJECT_MEMBER);

		emailContextBuilder.lcsProject(getLCSProject(lcsProjectId));
		emailContextBuilder.emailAddress(user.getEmailAddress());

		_emailAdvisor.sendToLCSProjectAdminsEmail(emailContextBuilder.build());

		_lcsMessageAdvisor.addLCSProjectLCSMessage(
			true, user.getFullName(), true, LCSEventType.NEW_PROJECT_MEMBER,
			lcsProjectId);

		lcsRoleLocalService.addLCSRole(
			user.getUserId(), lcsProjectId, 0,
			LCSRoleConstants.ROLE_LCS_ADMINISTRATOR);

		userLCSMessageLocalService.addUserLCSMessage(
			user.getUserId(), lcsMessageId);

		if (_log.isDebugEnabled()) {
			_log.debug(
				_stringAdvisor.concat(
					"LCS project setup and ready for user with UUID",
					user.getUserUuid()));
		}
	}

	private static final String _EMAIL_ADDRESS_SUBDOMAINS =
		"aol,gmail,hotmail,mail,outlook,yahoo";

	private static final Log _log = LogFactoryUtil.getLog(
		LCSProjectLocalServiceImpl.class);

	@ServiceReference(type = ConfigurationProvider.class)
	private ConfigurationProvider _configurationProvider;

	@ServiceReference(type = EmailAdvisor.class)
	private EmailAdvisor _emailAdvisor;

	@ServiceReference(type = LCSMessageAdvisor.class)
	private LCSMessageAdvisor _lcsMessageAdvisor;

	@ServiceReference(type = OSBPortletService.class)
	private OSBPortletService _osbPortletService;

	@ServiceReference(type = StringAdvisor.class)
	private StringAdvisor _stringAdvisor;

	@ServiceReference(type = UserAdvisor.class)
	private UserAdvisor _userAdvisor;

}