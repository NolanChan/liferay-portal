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

package com.liferay.osb.lcs.osbportlet;

import com.liferay.osb.lcs.configuration.OSBLCSConfiguration;
import com.liferay.osb.lcs.model.AccountEntry;
import com.liferay.osb.lcs.model.CorpProject;
import com.liferay.osb.lcs.osbportlet.service.OSBPortletService;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;

/**
 * @author Igor Beslic
 */
@Component(
	configurationPid = "com.liferay.osb.lcs.configuration.OSBLCSConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true,
	service = OSBPortletServiceProxy.class
)
public class OSBPortletServiceProxy implements OSBPortletService {

	@Override
	public void addAccountCustomers(long accountEntryId, long[] userIds)
		throws PortalException {

		_osbPortletService.addAccountCustomers(accountEntryId, userIds);
	}

	@Override
	public AccountEntry addAccountEntry(long corpProjectId, String name)
		throws PortalException {

		return _osbPortletService.addAccountEntry(corpProjectId, name);
	}

	@Override
	public CorpProject addCorpProject(long userId, String name)
		throws PortalException {

		return _osbPortletService.addCorpProject(userId, name);
	}

	@Override
	public CorpProject addCorpProject(String name) throws PortalException {
		return _osbPortletService.addCorpProject(name);
	}

	@Override
	public void addCorpProjectUsers(long corpProjectId, long[] userIds)
		throws PortalException {

		_osbPortletService.addCorpProjectUsers(corpProjectId, userIds);
	}

	@Override
	public void addOrganizationUsers(long organizationId, long[] userIds)
		throws PortalException {

		_osbPortletService.addOrganizationUsers(organizationId, userIds);
	}

	@Override
	public void addRole(String name) throws PortalException {
		_osbPortletService.addRole(name);
	}

	@Override
	public void addUserCorpProjectRoles(
			long corpProjectId, long[] userIds, String roleName)
		throws PortalException {

		_osbPortletService.addUserCorpProjectRoles(
			corpProjectId, userIds, roleName);
	}

	@Override
	public void clearCache() {
		_osbPortletService.clearCache();
	}

	@Override
	public void deleteUserCorpProjectRoles(long corpProjectId, long[] userIds)
		throws PortalException {

		_osbPortletService.deleteUserCorpProjectRoles(corpProjectId, userIds);
	}

	@Override
	public CorpProject fetchCorpProject(long corpProjectId)
		throws PortalException {

		return _osbPortletService.fetchCorpProject(corpProjectId);
	}

	@Override
	public AccountEntry fetchCorpProjectAccountEntry(long corpProjectId)
		throws PortalException {

		return _osbPortletService.fetchCorpProjectAccountEntry(corpProjectId);
	}

	@Override
	public Role fetchRole(String name) throws PortalException {
		return _osbPortletService.fetchRole(name);
	}

	@Override
	public CorpProject getCorpProject(long corpProjectId)
		throws PortalException {

		return _osbPortletService.getCorpProject(corpProjectId);
	}

	@Override
	public List<String> getCorpProjectAccountCustomerUUIDs(long corpProjectId)
		throws PortalException {

		return _osbPortletService.getCorpProjectAccountCustomerUUIDs(
			corpProjectId);
	}

	@Override
	public String getCorpProjectLCSSubscriptionEntriesJSON(long corpProjectId)
		throws PortalException {

		return _osbPortletService.getCorpProjectLCSSubscriptionEntriesJSON(
			corpProjectId);
	}

	@Override
	public List<User> getCorpProjectUsers(long corpProjectId)
		throws PortalException {

		return _osbPortletService.getCorpProjectUsers(corpProjectId);
	}

	@Override
	public List<User> getCorpProjectUsers(long corpProjectId, boolean active)
		throws PortalException {

		return _osbPortletService.getCorpProjectUsers(corpProjectId, active);
	}

	@Override
	public int getInvalidRemoteCorpProjectsCount(long[] localCorpProjectIds)
		throws PortalException {

		return _osbPortletService.getInvalidRemoteCorpProjectsCount(
			localCorpProjectIds);
	}

	@Override
	public User getRemoteUser(String emailAddress) throws PortalException {
		return _osbPortletService.getRemoteUser(emailAddress);
	}

	@Override
	public Role getRole(String name) throws PortalException {
		return _osbPortletService.getRole(name);
	}

	@Override
	public List<? extends CorpProject> getUserCorpProjects(long userId)
		throws PortalException {

		return _osbPortletService.getUserCorpProjects(userId);
	}

	@Override
	public boolean hasUserCorpProject(long userId, long corpProjectId)
		throws PortalException {

		return _osbPortletService.hasUserCorpProject(userId, corpProjectId);
	}

	@Override
	public boolean hasUserCorpProjectRole(
			long userId, long corpProjectId, String roleName)
		throws PortalException {

		return _osbPortletService.hasUserCorpProjectRole(
			userId, corpProjectId, roleName);
	}

	@Override
	public boolean isCorpProjectLicenseKeyActive(long corpProjectId, String key)
		throws PortalException {

		return _osbPortletService.isCorpProjectLicenseKeyActive(
			corpProjectId, key);
	}

	@Override
	public void unsetCorpProjectUsers(long corpProjectId, long[] userIds)
		throws PortalException {

		_osbPortletService.unsetCorpProjectUsers(corpProjectId, userIds);
	}

	@Override
	public CorpProject updateCorpProject(long corpProjectId, String name)
		throws PortalException {

		return _osbPortletService.updateCorpProject(corpProjectId, name);
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_osbLCSConfiguration = ConfigurableUtil.createConfigurable(
			OSBLCSConfiguration.class, properties);

		try {
			Class<?> osbPortletServiceImplClass = Class.forName(
				_osbLCSConfiguration.osbPortletServiceClassName());

			_osbPortletService =
				(OSBPortletService)osbPortletServiceImplClass.newInstance();
		}
		catch (Exception e) {
			throw new SystemException("Activation failed");
		}
	}

	private static volatile OSBLCSConfiguration _osbLCSConfiguration;

	private OSBPortletService _osbPortletService;

}