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

import com.liferay.lcs.subscription.SubscriptionType;
import com.liferay.osb.lcs.constants.OSBPortletConstants;
import com.liferay.osb.lcs.model.AccountEntry;
import com.liferay.osb.lcs.model.CorpProject;
import com.liferay.osb.lcs.model.impl.AccountEntryImpl;
import com.liferay.osb.lcs.model.impl.CorpProjectImpl;
import com.liferay.osb.lcs.osbportlet.service.OSBPortletService;
import com.liferay.petra.json.web.service.client.JSONWebServiceClient;
import com.liferay.portal.kernel.exception.NoSuchModelException;
import com.liferay.portal.kernel.exception.NoSuchOrganizationException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 */
@Component(
	configurationPid = "com.liferay.osb.lcs.configuration.OSBLCSDevelopmentConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true,
	service = OSBPortletService.class
)
public class MockOSBPortletServiceImpl extends BaseOSBPortletServiceImpl {

	@Override
	public void addAccountCustomers(long accountEntryId, long[] userIds)
		throws PortalException {

		userLocalService.addOrganizationUsers(accountEntryId, userIds);
	}

	@Override
	public AccountEntry addAccountEntry(long corpProjectId, String name)
		throws PortalException {

		Organization organization = _organizationLocalService.addOrganization(
			getUser().getUserId(), 0, getOSBOrganizationName(name),
			OrganizationConstants.TYPE_REGULAR_ORGANIZATION, 0, 0,
			ListTypeConstants.ORGANIZATION_STATUS_DEFAULT, StringPool.BLANK,
			false, new ServiceContext());

		AccountEntry accountEntry = getAccountEntry(organization);

		accountEntry.setCorpProjectId(corpProjectId);

		_allAccountEntries.put(accountEntry.getAccountEntryId(), accountEntry);

		return accountEntry;
	}

	@Override
	public CorpProject addCorpProject(long userId, String name)
		throws PortalException {

		Organization organization = _organizationLocalService.addOrganization(
			getUser().getUserId(), 0, getLCSOrganizationName(name),
			OrganizationConstants.TYPE_REGULAR_ORGANIZATION, 0, 0,
			ListTypeConstants.ORGANIZATION_STATUS_DEFAULT, StringPool.BLANK,
			false, new ServiceContext());

		userLocalService.setOrganizationUsers(
			organization.getOrganizationId(), new long[] {userId});

		CorpProject corpProject = getCorpProject(organization);

		_allCorpProjects.put(corpProject.getCorpProjectId(), corpProject);

		return corpProject;
	}

	@Override
	public CorpProject addCorpProject(String name) throws PortalException {
		Organization organization = _organizationLocalService.addOrganization(
			getUser().getUserId(), 0, getLCSOrganizationName(name),
			OrganizationConstants.TYPE_REGULAR_ORGANIZATION, 0, 0,
			ListTypeConstants.ORGANIZATION_STATUS_DEFAULT, StringPool.BLANK,
			false, new ServiceContext());

		CorpProject corpProject = getCorpProject(organization);

		_allCorpProjects.put(corpProject.getCorpProjectId(), corpProject);

		return corpProject;
	}

	@Override
	public void addCorpProjectUsers(long corpProjectId, long[] userIds)
		throws PortalException {

		CorpProject corpProject = getCorpProject(corpProjectId);

		userLocalService.addOrganizationUsers(
			corpProject.getOrganizationId(), userIds);
	}

	@Override
	public void addUserCorpProjectRoles(
			long corpProjectId, long[] userIds, String roleName)
		throws PortalException {

		CorpProject corpProject = getCorpProject(corpProjectId);

		Organization organization = _organizationLocalService.getOrganization(
			corpProject.getOrganizationId());

		_userGroupRoleLocalService.addUserGroupRoles(
			userIds, organization.getGroupId(), getRoleId());
	}

	@Override
	public void clearCache() {
		_allCorpProjects.clear();

		_organizationCorpProjects.clear();
	}

	@Override
	public void deleteUserCorpProjectRoles(long corpProjectId, long[] userIds)
		throws PortalException {

		CorpProject corpProject = getCorpProject(corpProjectId);

		Organization organization = _organizationLocalService.getOrganization(
			corpProject.getOrganizationId());

		_userGroupRoleLocalService.deleteUserGroupRoles(
			userIds, organization.getGroupId(), getRoleId());
	}

	@Override
	public AccountEntry fetchCorpProjectAccountEntry(long corpProjectId)
		throws PortalException {

		try {
			CorpProject corpProject = getCorpProject(corpProjectId);

			return getAccountEntry(corpProject);
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
	public CorpProject getCorpProject(long corpProjectId)
		throws PortalException {

		CorpProject corpProject = _allCorpProjects.get(corpProjectId);

		if (corpProject != null) {
			return corpProject;
		}

		try {
			Organization organization =
				_organizationLocalService.getOrganization(corpProjectId);

			corpProject = getCorpProject(organization);
		}
		catch (NoSuchOrganizationException nsoe) {
			throw new NoSuchModelException(nsoe);
		}
		catch (SystemException se) {
			throw new NoSuchModelException(se);
		}

		_allCorpProjects.put(corpProjectId, corpProject);
		_organizationCorpProjects.put(corpProjectId, corpProject);

		return corpProject;
	}

	@Override
	public List<String> getCorpProjectAccountCustomerUUIDs(long corpProjectId)
		throws PortalException {

		List<String> uuids = new ArrayList<>();

		AccountEntry corpProjectAccountEntry = null;

		for (AccountEntry accountEntry : _allAccountEntries.values()) {
			if (corpProjectId == accountEntry.getCorpProjectId()) {
				corpProjectAccountEntry = accountEntry;

				break;
			}
		}

		if (corpProjectAccountEntry == null) {
			return Collections.emptyList();
		}

		for (User user :
				getOrganizationUsers(
					corpProjectAccountEntry.getAccountEntryId())) {

			uuids.add(user.getUuid());
		}

		return uuids;
	}

	@Override
	public String getCorpProjectLCSSubscriptionEntriesJSON(long corpProjectId)
		throws PortalException {

		JSONArray lcsSubscriptionEntriesJSONArray =
			JSONFactoryUtil.createJSONArray();

		for (SubscriptionType subscriptionType : SubscriptionType.values()) {
			JSONObject subscriptionEntryJSONObject =
				JSONFactoryUtil.createJSONObject();

			subscriptionEntryJSONObject.put("actualPrice", 400);
			subscriptionEntryJSONObject.put("currencyCode", "USD");
			subscriptionEntryJSONObject.put("instanceSize", 2);

			Date endDate = new Date();

			subscriptionEntryJSONObject.put("endDate", endDate.getTime());

			subscriptionEntryJSONObject.put("platform", "portal");
			subscriptionEntryJSONObject.put("platformVersion", "6.2.0");
			subscriptionEntryJSONObject.put("processorCoresAllowed", 12);
			subscriptionEntryJSONObject.put("product", "portal");
			subscriptionEntryJSONObject.put("productVersion", "6.2");
			subscriptionEntryJSONObject.put("serversAllowed", 20);
			subscriptionEntryJSONObject.put("serversUsed", 5);

			Date startDate = new Date();

			subscriptionEntryJSONObject.put("startDate", startDate.getTime());

			Date supportEndDate = new Date();

			subscriptionEntryJSONObject.put(
				"supportEndDate", supportEndDate.getTime());

			Date supportStartDate = new Date();

			if (supportStartDate != null) {
				subscriptionEntryJSONObject.put(
					"supportStartDate", supportStartDate.getTime());
			}

			subscriptionEntryJSONObject.put(
				"type", subscriptionType.getLicenseEntryType());

			lcsSubscriptionEntriesJSONArray.put(subscriptionEntryJSONObject);
		}

		return lcsSubscriptionEntriesJSONArray.toString();
	}

	@Override
	public JSONWebServiceClient getJSONWebServiceClient() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<? extends CorpProject> getUserCorpProjects(long userId)
		throws PortalException {

		List<Organization> userLCSOrganizations = getUserLCSOrganizations(
			userId);

		List<CorpProject> corpProjects = new ArrayList<>();

		for (Organization organization : userLCSOrganizations) {
			long organizationId = organization.getOrganizationId();

			CorpProject corpProject = _organizationCorpProjects.get(
				organizationId);

			if (corpProject != null) {
				if (!_allCorpProjects.containsKey(
						corpProject.getCorpProjectId())) {

					_allCorpProjects.put(
						corpProject.getCorpProjectId(), corpProject);
				}

				corpProjects.add(corpProject);
			}
			else {
				corpProject = getCorpProject(organization);

				_allCorpProjects.put(
					corpProject.getCorpProjectId(), corpProject);
				_organizationCorpProjects.put(organizationId, corpProject);

				corpProjects.add(corpProject);
			}
		}

		return Collections.unmodifiableList(corpProjects);
	}

	@Override
	public boolean hasUserCorpProject(long userId, long corpProjectId)
		throws PortalException {

		CorpProject corpProject = getCorpProject(corpProjectId);

		return _organizationLocalService.hasUserOrganization(
			userId, corpProject.getOrganizationId());
	}

	@Override
	public boolean hasUserCorpProjectRole(
			long userId, long corpProjectId, String roleName)
		throws PortalException {

		CorpProject corpProject = getCorpProject(corpProjectId);

		Organization organization = _organizationLocalService.getOrganization(
			corpProject.getOrganizationId());

		long osbCorpRoleId = getRoleId();

		if (!OSBPortletConstants.ROLE_OSB_CORP_LCS_USER.equals(roleName)) {
			Role role = getRole(roleName);

			osbCorpRoleId = role.getRoleId();
		}

		return _userGroupRoleLocalService.hasUserGroupRole(
			userId, organization.getGroupId(), osbCorpRoleId);
	}

	@Reference(unbind = "-")
	public void setOrganizationLocalService(
		OrganizationLocalService organizationLocalService) {

		_organizationLocalService = organizationLocalService;
	}

	@Reference(unbind = "-")
	public void setRoleLocalService(RoleLocalService roleLocalService) {
		_roleLocalService = roleLocalService;
	}

	@Reference(unbind = "-")
	public void setUserGroupRoleLocalService(
		UserGroupRoleLocalService userGroupRoleLocalService) {

		_userGroupRoleLocalService = userGroupRoleLocalService;
	}

	@Override
	public void unsetCorpProjectUsers(long corpProjectId, long[] userIds)
		throws PortalException {

		CorpProject corpProject = getCorpProject(corpProjectId);

		userLocalService.unsetOrganizationUsers(
			corpProject.getOrganizationId(), userIds);
	}

	@Override
	public CorpProject updateCorpProject(long corpProjectId, String name)
		throws PortalException {

		CorpProject corpProject = getCorpProject(corpProjectId);

		corpProject.setName(getLCSOrganizationName(name));

		Organization organization = _organizationLocalService.getOrganization(
			corpProject.getOrganizationId());

		_organizationLocalService.updateOrganization(
			organization.getCompanyId(), organization.getOrganizationId(),
			organization.getParentOrganizationId(),
			getLCSOrganizationName(name), organization.getType(),
			organization.getRegionId(), organization.getCountryId(),
			organization.getStatusId(), organization.getComments(), false,
			ServiceContextThreadLocal.getServiceContext());

		return corpProject;
	}

	protected AccountEntry getAccountEntry(CorpProject corpProject) {
		for (AccountEntry accountEntry : _allAccountEntries.values()) {
			if (corpProject.getCorpProjectId() ==
					accountEntry.getCorpProjectId()) {

				return accountEntry;
			}
		}

		return null;
	}

	protected AccountEntry getAccountEntry(long accountEntryId)
		throws PortalException {

		AccountEntry accountEntry = _allAccountEntries.get(accountEntryId);

		if (accountEntry != null) {
			return accountEntry;
		}

		try {
			Organization organization =
				_organizationLocalService.getOrganization(accountEntryId);

			accountEntry = getAccountEntry(organization);
		}
		catch (NoSuchOrganizationException nsoe) {
			throw new NoSuchModelException(nsoe);
		}
		catch (SystemException se) {
			throw new NoSuchModelException(se);
		}

		_allAccountEntries.put(accountEntryId, accountEntry);

		return accountEntry;
	}

	protected AccountEntry getAccountEntry(Organization organization) {
		AccountEntry accountEntry = new AccountEntryImpl();

		accountEntry.setAccountEntryId(organization.getOrganizationId());
		accountEntry.setName(organization.getName());

		return accountEntry;
	}

	protected CorpProject getCorpProject(Organization organization) {
		CorpProject corpProject = new CorpProjectImpl();

		corpProject.setCorpProjectId(organization.getOrganizationId());
		corpProject.setName(organization.getName());
		corpProject.setOrganizationId(organization.getOrganizationId());

		return corpProject;
	}

	protected String getLCSOrganizationName(String name) {
		return _ORGANIZATION_NAMESPACE_LCS + StringPool.SPACE + name;
	}

	@Override
	protected List<User> getOrganizationUsers(long organizationId) {
		return ListUtil.copy(
			userLocalService.getOrganizationUsers(organizationId));
	}

	protected String getOSBOrganizationName(String name) {
		return _ORGANIZATION_NAMESPACE_OSB + StringPool.SPACE + name;
	}

	protected long getRoleId() throws PortalException {
		if (_roleId == 0) {
			Role lcsUserRole = _roleLocalService.getRole(
				PortalUtil.getDefaultCompanyId(),
				OSBPortletConstants.ROLE_OSB_CORP_LCS_USER);

			_roleId = lcsUserRole.getRoleId();
		}

		return _roleId;
	}

	protected User getUser() throws PortalException {
		if (_user != null) {
			return _user;
		}

		Role role = _roleLocalService.getRole(
			PortalUtil.getDefaultCompanyId(), RoleConstants.ADMINISTRATOR);

		List<User> users = userLocalService.getRoleUsers(role.getRoleId());

		_user = users.get(0);

		return _user;
	}

	protected List<Organization> getUserLCSOrganizations(long userId)
		throws PortalException {

		List<Organization> organizations = new ArrayList<>();

		List<Organization> userOrganizations =
			_organizationLocalService.getUserOrganizations(userId);

		for (Organization organization : userOrganizations) {
			String organizationName = organization.getName();

			if (organizationName.startsWith(_ORGANIZATION_NAMESPACE_LCS)) {
				organizations.add(organization);
			}
		}

		return organizations;
	}

	private static final String _ORGANIZATION_NAMESPACE_LCS = "LCSORG";

	private static final String _ORGANIZATION_NAMESPACE_OSB = "OSBORG";

	private static final Log _log = LogFactoryUtil.getLog(
		MockOSBPortletServiceImpl.class);

	private final Map<Long, AccountEntry> _allAccountEntries = new HashMap<>();
	private final Map<Long, CorpProject> _allCorpProjects = new HashMap<>();
	private final Map<Long, CorpProject> _organizationCorpProjects =
		new HashMap<>();
	private OrganizationLocalService _organizationLocalService;
	private long _roleId;
	private RoleLocalService _roleLocalService;
	private User _user;
	private UserGroupRoleLocalService _userGroupRoleLocalService;

}