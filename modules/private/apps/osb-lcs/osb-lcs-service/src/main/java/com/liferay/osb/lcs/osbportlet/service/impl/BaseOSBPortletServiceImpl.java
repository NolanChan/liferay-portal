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

package com.liferay.osb.lcs.osbportlet.service.impl;

import com.liferay.osb.lcs.advisor.StringAdvisor;
import com.liferay.osb.lcs.model.AccountEntry;
import com.liferay.osb.lcs.model.CorpProject;
import com.liferay.osb.lcs.osbportlet.service.OSBPortletService;
import com.liferay.petra.json.web.service.client.BaseJSONWebServiceClientHandler;
import com.liferay.portal.kernel.exception.NoSuchModelException;
import com.liferay.portal.kernel.exception.NoSuchRoleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
public abstract class BaseOSBPortletServiceImpl
	extends BaseJSONWebServiceClientHandler implements OSBPortletService {

	@Override
	public void addAccountCustomers(long accountEntryId, long[] userIds)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public AccountEntry addAccountEntry(long corpProjectId, String name)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public CorpProject addCorpProject(long userId, String name)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public CorpProject addCorpProject(String name) throws PortalException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addCorpProjectUsers(long corpProjectId, long[] userIds)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void addOrganizationUsers(long organizationId, long[] userIds)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void addRole(String name) throws PortalException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addUserCorpProjectRoles(
			long corpProjectId, long[] userIds, String roleName)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void clearCache() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteUserCorpProjectRoles(long corpProjectId, long[] userIds)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public CorpProject fetchCorpProject(long corpProjectId)
		throws PortalException {

		try {
			return getCorpProject(corpProjectId);
		}
		catch (NoSuchModelException nsme) {

			// LPS-52675

			if (_log.isDebugEnabled()) {
				_log.debug(nsme, nsme);
			}

			return null;
		}
	}

	@Override
	public AccountEntry fetchCorpProjectAccountEntry(long corpProjectId)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public Role fetchRole(String name) throws PortalException {
		try {
			return getRole(name);
		}
		catch (NoSuchRoleException nsre) {

			// LPS-52675

			if (_log.isDebugEnabled()) {
				_log.debug(nsre, nsre);
			}

			return null;
		}
	}

	@Override
	public CorpProject getCorpProject(long corpProjectId)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public List<String> getCorpProjectAccountCustomerUUIDs(long corpProjectId)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public String getCorpProjectLCSSubscriptionEntriesJSON(long corpProjectId)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public List<User> getCorpProjectUsers(long corpProjectId)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public List<User> getCorpProjectUsers(long corpProjectId, boolean active)
		throws PortalException {

		CorpProject corpProject = getCorpProject(corpProjectId);

		if (_log.isDebugEnabled()) {
			_log.debug(
				stringAdvisor.concat(
					"Get remote users for corp project ID", corpProjectId));
		}

		List<User> users = getOrganizationUsers(
			corpProject.getOrganizationId());

		if (!active) {
			return users;
		}

		List<User> corpProjectUsers = new ArrayList<>(users.size());

		for (User user : users) {
			if (hasUserCorpProject(user.getUserId(), corpProjectId)) {
				corpProjectUsers.add(user);
			}
		}

		return corpProjectUsers;
	}

	@Override
	public int getInvalidRemoteCorpProjectsCount(long[] localCorpProjectIds)
		throws PortalException {

		int invalidRemoteCorpProjectsCount = 0;

		for (long localCorpProjectId : localCorpProjectIds) {
			CorpProject remoteCorpProject = fetchCorpProject(
				localCorpProjectId);

			if ((remoteCorpProject == null) ||
				(remoteCorpProject.getCorpProjectId() == 0)) {

				invalidRemoteCorpProjectsCount++;
			}
		}

		return invalidRemoteCorpProjectsCount;
	}

	@Override
	public User getRemoteUser(String emailAddress) throws PortalException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Role getRole(String name) throws PortalException {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<? extends CorpProject> getUserCorpProjects(long userId)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasUserCorpProject(long userId, long corpProjectId)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasUserCorpProjectRole(
			long userId, long corpProjectId, String roleName)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isCorpProjectLicenseKeyActive(long corpProjectId, String key)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Reference(bind = "-")
	public void setStringAdvisor(StringAdvisor stringAdvisor) {
		this.stringAdvisor = stringAdvisor;
	}

	@Reference(bind = "-")
	public void setUserLocalService(UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	@Override
	public void unsetCorpProjectUsers(long corpProjectId, long[] userIds)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public CorpProject updateCorpProject(long corpProjectId, String name)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	protected abstract List<User> getOrganizationUsers(long organizationId)
		throws PortalException;

	protected String[] getUserUuids(long[] userIds) throws PortalException {
		String[] userUuids = new String[userIds.length];

		for (int i = 0; i < userIds.length; i++) {
			User user = userLocalService.getUserById(userIds[i]);

			userUuids[i] = user.getUserUuid();
		}

		return userUuids;
	}

	protected StringAdvisor stringAdvisor;
	protected UserLocalService userLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseOSBPortletServiceImpl.class);

}