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

import com.liferay.osb.lcs.model.AccountEntry;
import com.liferay.osb.lcs.model.CorpProject;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;

import java.util.List;

/**
 * @author Igor Beslic
 */
public interface OSBPortletService {

	public void addAccountCustomers(long accountEntryId, long[] userIds)
		throws PortalException;

	public AccountEntry addAccountEntry(long corpProjectId, String name)
		throws PortalException;

	public CorpProject addCorpProject(long userId, String name)
		throws PortalException;

	public CorpProject addCorpProject(String name) throws PortalException;

	public void addCorpProjectUsers(long corpProjectId, long[] userIds)
		throws PortalException;

	public void addOrganizationUsers(long organizationId, long[] userIds)
		throws PortalException;

	public void addRole(String name) throws PortalException;

	public void addUserCorpProjectRoles(
			long corpProjectId, long[] userIds, String roleName)
		throws PortalException;

	public void clearCache();

	public void deleteUserCorpProjectRoles(long corpProjectId, long[] userIds)
		throws PortalException;

	public CorpProject fetchCorpProject(long corpProjectId)
		throws PortalException;

	public AccountEntry fetchCorpProjectAccountEntry(long corpProjectId)
		throws PortalException;

	public Role fetchRole(String name) throws PortalException;

	public CorpProject getCorpProject(long corpProjectId)
		throws PortalException;

	public List<String> getCorpProjectAccountCustomerUUIDs(long corpProjectId)
		throws PortalException;

	public String getCorpProjectLCSSubscriptionEntriesJSON(long corpProjectId)
		throws PortalException;

	public List<User> getCorpProjectUsers(long corpProjectId)
		throws PortalException;

	public List<User> getCorpProjectUsers(long corpProjectId, boolean active)
		throws PortalException;

	public int getInvalidRemoteCorpProjectsCount(long[] localCorpProjectIds)
		throws PortalException;

	public User getRemoteUser(String emailAddress) throws PortalException;

	public Role getRole(String name) throws PortalException;

	public List<? extends CorpProject> getUserCorpProjects(long userId)
		throws PortalException;

	public boolean hasUserCorpProject(long userId, long corpProjectId)
		throws PortalException;

	public boolean hasUserCorpProjectRole(
			long userId, long corpProjectId, String roleName)
		throws PortalException;

	public boolean isCorpProjectLicenseKeyActive(long corpProjectId, String key)
		throws PortalException;

	public void unsetCorpProjectUsers(long corpProjectId, long[] userIds)
		throws PortalException;

	public CorpProject updateCorpProject(long corpProjectId, String name)
		throws PortalException;

}