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

package com.liferay.osb.lcs.osbportlet.service;

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.CorpProject;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;

import java.util.List;

/**
 * @author Igor Beslic
 */
public interface OSBPortletService {

	public void addAccountCustomers(long accountEntryId, long[] userIds)
		throws PortalException, SystemException;

	public AccountEntry addAccountEntry(long corpProjectId, String name)
		throws PortalException, SystemException;

	public CorpProject addCorpProject(long userId, String name)
		throws PortalException, SystemException;

	public CorpProject addCorpProject(String name)
		throws PortalException, SystemException;

	public void addCorpProjectUsers(long corpProjectId, long[] userIds)
		throws PortalException, SystemException;

	public void addOrganizationUsers(long organizationId, long[] userIds)
		throws PortalException, SystemException;

	public void addRole(String name)
		throws PortalException, SystemException;

	public void addUserCorpProjectRoles(
			long corpProjectId, long[] userIds, String roleName)
		throws PortalException, SystemException;

	public void clearCache();

	public void deleteUserCorpProjectRoles(long corpProjectId, long[] userIds)
		throws PortalException, SystemException;

	public CorpProject fetchCorpProject(long corpProjectId)
		throws PortalException, SystemException;

	public AccountEntry fetchCorpProjectAccountEntry(long corpProjectId)
		throws PortalException, SystemException;

	public Role fetchRole(String name) throws PortalException, SystemException;

	public CorpProject getCorpProject(long corpProjectId)
		throws PortalException, SystemException;

	public List<String> getCorpProjectAccountCustomerUUIDs(long corpProjectId)
		throws PortalException, SystemException;

	public String getCorpProjectLCSSubscriptionEntriesJSON(long corpProjectId)
		throws PortalException, SystemException;

	public List<User> getCorpProjectUsers(long corpProjectId)
		throws PortalException, SystemException;

	public List<User> getCorpProjectUsers(long corpProjectId, boolean active)
		throws PortalException, SystemException;

	public User getRemoteUser(String emailAddress)
		throws PortalException, SystemException;

	public Role getRole(String name)
		throws PortalException, SystemException;

	public List<? extends CorpProject> getUserCorpProjects(long userId)
		throws PortalException, SystemException;

	public boolean hasUserCorpProject(long userId, long corpProjectId)
		throws PortalException, SystemException;

	public boolean hasUserCorpProjectRole(
			long userId, long corpProjectId, String roleName)
		throws PortalException, SystemException;

	public boolean isCorpProjectLicenseKeyActive(long corpProjectId, String key)
		throws PortalException, SystemException;

	public void unsetCorpProjectUsers(long corpProjectId, long[] userIds)
		throws PortalException, SystemException;

	public CorpProject updateCorpProject(long corpProjectId, String name)
		throws PortalException, SystemException;

}