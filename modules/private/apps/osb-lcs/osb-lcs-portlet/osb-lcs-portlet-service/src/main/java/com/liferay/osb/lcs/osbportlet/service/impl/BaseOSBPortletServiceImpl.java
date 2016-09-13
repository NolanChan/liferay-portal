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

import com.liferay.jsonwebserviceclient.BaseJSONWebServiceClientHandler;
import com.liferay.osb.NoSuchCorpProjectException;
import com.liferay.osb.lcs.advisor.StringAdvisor;
import com.liferay.osb.lcs.osbportlet.service.OSBPortletService;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.CorpProject;
import com.liferay.portal.NoSuchRoleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Beslic
 */
public abstract class BaseOSBPortletServiceImpl
	extends BaseJSONWebServiceClientHandler implements OSBPortletService {

	@Override
	public void addAccountCustomers(long accountEntryId, long[] userIds)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public AccountEntry addAccountEntry(long corpProjectId, String name)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public CorpProject addCorpProject(long userId, String name)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public CorpProject addCorpProject(String name)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void addCorpProjectUsers(long corpProjectId, long[] userIds)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void addOrganizationUsers(long organizationId, long[] userIds)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void addRole(String name) throws PortalException, SystemException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addUserCorpProjectRoles(
			long corpProjectId, long[] userIds, String roleName)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void clearCache() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteUserCorpProjectRoles(long corpProjectId, long[] userIds)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public CorpProject fetchCorpProject(long corpProjectId)
		throws PortalException, SystemException {

		try {
			return getCorpProject(corpProjectId);
		}
		catch (NoSuchCorpProjectException nscpe) {
			return null;
		}
	}

	@Override
	public AccountEntry fetchCorpProjectAccountEntry(long corpProjectId)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public Role fetchRole(String name) throws PortalException, SystemException {
		try {
			return getRole(name);
		}
		catch (NoSuchRoleException nsre) {
			return null;
		}
	}

	@Override
	public CorpProject getCorpProject(long corpProjectId)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public List<String> getCorpProjectAccountCustomerUUIDs(long corpProjectId)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public String getCorpProjectLCSSubscriptionEntriesJSON(long corpProjectId)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public List<User> getCorpProjectUsers(long corpProjectId)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public List<User> getCorpProjectUsers(long corpProjectId, boolean active)
		throws PortalException, SystemException {

		CorpProject corpProject = getCorpProject(corpProjectId);

		if (_log.isDebugEnabled()) {
			_log.debug(
				StringAdvisor.concat(
					"Get remote users for corp project ID", corpProjectId));
		}

		List<User> users = getOrganizationUsers(
			corpProject.getOrganizationId());

		if (!active) {
			return users;
		}

		List<User> corpProjectUsers = new ArrayList<User>(users.size());

		for (User user : users) {
			if (hasUserCorpProject(user.getUserId(), corpProjectId)) {
				corpProjectUsers.add(user);
			}
		}

		return corpProjectUsers;
	}

	@Override
	public User getRemoteUser(String emailAddress)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public Role getRole(String name) throws PortalException, SystemException {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<? extends CorpProject> getUserCorpProjects(long userId)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasUserCorpProject(long userId, long corpProjectId)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasUserCorpProjectRole(
			long userId, long corpProjectId, String roleName)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isCorpProjectLicenseKeyActive(long corpProjectId, String key)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	@Override
	public void unsetCorpProjectUsers(long corpProjectId, long[] userIds)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	public CorpProject updateCorpProject(long corpProjectId, String name)
		throws PortalException, SystemException {

		throw new UnsupportedOperationException();
	}

	protected abstract List<User> getOrganizationUsers(long organizationId)
		throws PortalException, SystemException;

	protected String[] getUserUuids(long[] userIds)
		throws PortalException, SystemException {

		String[] userUuids = new String[userIds.length];

		for (int i = 0; i < userIds.length; i++) {
			User user = UserLocalServiceUtil.getUserById(userIds[i]);

			userUuids[i] = user.getUserUuid();
		}

		return userUuids;
	}

	private static Log _log = LogFactoryUtil.getLog(
		BaseOSBPortletServiceImpl.class);

}