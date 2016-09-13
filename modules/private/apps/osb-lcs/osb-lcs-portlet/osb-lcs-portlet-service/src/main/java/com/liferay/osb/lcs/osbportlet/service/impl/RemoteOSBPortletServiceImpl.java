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

import com.fasterxml.jackson.databind.module.SimpleModule;

import com.liferay.jsonwebserviceclient.JSONWebServiceClient;
import com.liferay.jsonwebserviceclient.JSONWebServiceInvocationException;
import com.liferay.jsonwebserviceclient.JSONWebServiceTransportException;
import com.liferay.osb.NoSuchCorpProjectException;
import com.liferay.osb.lcs.advisor.StringAdvisor;
import com.liferay.osb.lcs.json.CompanyDeserializer;
import com.liferay.osb.lcs.json.CorpProjectDeserializer;
import com.liferay.osb.lcs.json.UserDeserializer;
import com.liferay.osb.lcs.osbportlet.util.OSBPortletUtil;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryClp;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.model.CorpProjectClp;
import com.liferay.portal.NoSuchOrganizationException;
import com.liferay.portal.NoSuchRoleException;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Igor Beslic
 */
public class RemoteOSBPortletServiceImpl extends BaseOSBPortletServiceImpl {

	public RemoteOSBPortletServiceImpl() {
		SimpleModule simpleModule = new SimpleModule();

		try {
			simpleModule.addDeserializer(
				getClass("com.liferay.portal.model.impl.CompanyImpl"),
				new CompanyDeserializer());
			simpleModule.addDeserializer(
				getClass("com.liferay.portal.model.impl.UserImpl"),
				new UserDeserializer());
		}
		catch (SystemException e) {
			throw new RuntimeException(e);
		}

		simpleModule.addDeserializer(
			CorpProjectClp.class, new CorpProjectDeserializer());

		objectMapper.registerModule(simpleModule);
	}

	@Override
	public CorpProject addCorpProject(long userId, String name)
		throws PortalException, SystemException {

		String serviceURL =
			_URL_API_JSONWS_OSB_PORTLET_CORP_PROJECT +
				"/add-corp-project";

		User user = UserLocalServiceUtil.getUserById(userId);

		try {
			CorpProject corpProject = doGetToObject(
				CorpProjectClp.class, serviceURL, "name", name, "userUuid",
				user.getUserUuid());

			_portalCache.put(corpProject.getCorpProjectId(), corpProject);

			return corpProject;
		}
		catch (Exception e) {
			processException(
				e, serviceURL, "name", name, "userUuid", user.getUserUuid());

			throw new SystemException(e);
		}
	}

	@Override
	public CorpProject addCorpProject(String name)
		throws PortalException, SystemException {

		String serviceURL =
			_URL_API_JSONWS_OSB_PORTLET_CORP_PROJECT +
				"/add-corp-project";

		try {
			CorpProject corpProject = doGetToObject(
				CorpProjectClp.class, serviceURL, "name", name);

			_portalCache.put(corpProject.getCorpProjectId(), corpProject);

			return corpProject;
		}
		catch (Exception e) {
			processException(e, serviceURL, "name", name);

			throw new SystemException(e);
		}
	}

	@Override
	public void addCorpProjectUsers(long corpProjectId, long[] userIds)
		throws PortalException, SystemException {

		String serviceURL =
			_URL_API_JSONWS_OSB_PORTLET_CORP_PROJECT +
				"/add-corp-project-users";

		String[] userUuids = getUserUuids(userIds);

		try {
			doGet(
				serviceURL, "corpProjectId", String.valueOf(corpProjectId),
				"userUuids", StringUtil.merge(userUuids));
		}
		catch (Exception e) {
			processException(
				e, serviceURL, "corpProjectId", String.valueOf(corpProjectId),
				"userUuids", StringUtil.merge(userUuids));

			throw new SystemException(e);
		}
	}

	@Override
	public void addOrganizationUsers(long organizationId, long[] userIds)
		throws PortalException, SystemException {

		String serviceURL = _URL_API_JSONWS_ORGANIZATION + "/get-organization";

		checkOrganizationId(organizationId, serviceURL);

		try {
			Organization remoteOrganization = (Organization)doGetToObject(
				getClass("com.liferay.portal.model.impl.OrganizationImpl"),
				serviceURL, "organizationId", String.valueOf(organizationId));

			List<Long> remoteUserIds = new ArrayList<Long>();
			serviceURL = _URL_API_JSONWS_USER + "/get-user-by-email-address";

			for (int i = 0; i < userIds.length; i++) {
				User localUser = UserLocalServiceUtil.getUser(userIds[i]);

				User remoteUser = (User)doGetToObject(
					getClass("com.liferay.portal.model.impl.UserImpl"),
					serviceURL, "companyId",
					String.valueOf(remoteOrganization.getCompanyId()),
					"emailAddress", localUser.getEmailAddress());

				if ((remoteUser == null) || (remoteUser.getUserId() == 0)) {
					continue;
				}

				remoteUserIds.add(remoteUser.getUserId());
			}

			serviceURL = _URL_API_JSONWS_USER + "/add-organization-users";

			doGet(
				serviceURL, "organizationId", String.valueOf(organizationId),
				"userIds", StringUtil.merge(remoteUserIds));
		}
		catch (Exception e) {
			processException(
				e, serviceURL, "organizationId", String.valueOf(organizationId),
				"local userIds", StringUtil.merge(userIds));

			throw new SystemException(e);
		}
	}

	@Override
	public void addRole(String name) throws PortalException, SystemException {
		String serviceURL = _URL_API_JSONWS_ROLE + "/add-role";

		try {
			doGet(
				serviceURL, "className", Role.class.getName(), "classPK", "0",
				"name", name, "titleMap", null, "descriptionMap", null, "type",
				String.valueOf(RoleConstants.TYPE_ORGANIZATION), "subType",
				null);
		}
		catch (Exception e) {
			processException(
				e, serviceURL, "className", Role.class.getName(), "classPK",
				"0", "name", name, "titleMap", StringPool.DOUBLE_DASH,
				"descriptionMap", StringPool.DOUBLE_DASH, "type",
				String.valueOf(RoleConstants.TYPE_ORGANIZATION), "subType",
				StringPool.DOUBLE_DASH);

			throw new SystemException(e);
		}
	}

	@Override
	public void addUserCorpProjectRoles(
			long corpProjectId, long[] userIds, String roleName)
		throws PortalException, SystemException {

		String serviceURL =
			_URL_API_JSONWS_OSB_PORTLET_CORP_PROJECT +
				"/add-user-corp-project-roles";
		String[] userUuids = getUserUuids(userIds);

		try {
			doGet(
				serviceURL, "corpProjectId", String.valueOf(corpProjectId),
				"userUuids", StringUtil.merge(userUuids), "roleName", roleName);
		}
		catch (Exception e) {
			processException(
				e, serviceURL, "corpProjectId", String.valueOf(corpProjectId),
				"userUuids", StringUtil.merge(userUuids), "roleName", roleName);

			throw new SystemException(e);
		}
	}

	@Override
	public void clearCache() {
		_portalCache.removeAll();
	}

	@Override
	public void deleteUserCorpProjectRoles(long corpProjectId, long[] userIds)
		throws PortalException, SystemException {

		Role remoteRole = getRole(OSBPortletUtil.ROLE_OSB_CORP_LCS_USER);
		String serviceURL =
			_URL_API_JSONWS_OSB_PORTLET_CORP_PROJECT +
				"/delete-user-corp-project-roles";
		String[] userUuids = getUserUuids(userIds);

		try {
			doGet(
				serviceURL, "corpProjectId", String.valueOf(corpProjectId),
				"roleId", String.valueOf(remoteRole.getRoleId()), "userUuids",
				StringUtil.merge(userUuids));
		}
		catch (Exception e) {
			processException(
				e, serviceURL, "corpProjectId", String.valueOf(corpProjectId),
				"roleId", String.valueOf(remoteRole.getRoleId()), "userUuids",
				StringUtil.merge(userUuids));

			throw new SystemException(e);
		}
	}

	@Override
	public AccountEntry fetchCorpProjectAccountEntry(long corpProjectId)
		throws PortalException, SystemException {

		String serviceURL =
			_URL_API_JSONWS_OSB_PORTLET_ACCOUNT_ENTRY +
				"/fetch-corp-project-account-entry";

		try {
			return doGetToObject(
				AccountEntryClp.class, serviceURL, "corpProjectId",
				String.valueOf(corpProjectId));
		}
		catch (JSONWebServiceInvocationException jsonwsie) {
			_log.error(jsonwsie, jsonwsie);
		}
		catch (Exception e) {
			processException(
				e, serviceURL, "corpProjectId", String.valueOf(corpProjectId));

			throw new SystemException(e);
		}

		return null;
	}

	@Override
	public CorpProject getCorpProject(long corpProjectId)
		throws PortalException, SystemException {

		String serviceURL =
			_URL_API_JSONWS_OSB_PORTLET_CORP_PROJECT + "/get-corp-project";

		try {
			CorpProject corpProject = (CorpProject)_portalCache.get(
				corpProjectId);

			if (corpProject != null) {
				if (_log.isDebugEnabled()) {
					_log.debug(
						StringAdvisor.concat(
							"Returning cached corp project", corpProject));
				}

				return corpProject;
			}

			corpProject = doGetToObject(
				CorpProjectClp.class, serviceURL, "corpProjectId",
				String.valueOf(corpProjectId));

			_portalCache.put(corpProjectId, corpProject);

			if (_log.isDebugEnabled()) {
				_log.debug(
					StringAdvisor.concat(
						"Returning corp project: ", corpProject));
			}

			return corpProject;
		}
		catch (Exception e) {
			processException(
				e, serviceURL, "corpProjectId", String.valueOf(corpProjectId));

			throw new SystemException(e);
		}
	}

	@Override
	public List<String> getCorpProjectAccountCustomerUUIDs(long corpProjectId)
		throws PortalException, SystemException {

		String serviceURL =
			_URL_API_JSONWS_OSB_PORTLET_ACCOUNT_CUSTOMER +
				"/get-corp-project-account-customer-uui-ds";

		try {
			return doGetToList(
				String.class, serviceURL, "corpProjectId",
				String.valueOf(corpProjectId));
		}
		catch (Exception e) {
			processException(
				e, serviceURL, "corpProjectId", String.valueOf(corpProjectId));

			throw new SystemException(e);
		}
	}

	@Override
	public String getCorpProjectLCSSubscriptionEntriesJSON(long corpProjectId)
		throws PortalException, SystemException {

		String serviceURL =
			_URL_API_JSONWS_OSB_PORTLET_LCS_SUBSCRIPTION_ENTRY +
				"/get-lcs-subscription-entries";

		try {
			String json = doGet(
				serviceURL, "corpProjectId", String.valueOf(corpProjectId));

			if ((json == null) || json.equals("") || json.equals("{}")) {
				return null;
			}

			if (json.contains("exception\":\"")) {
				_log.error(getExceptionMessage(json));

				return null;
			}

			return json;
		}
		catch (Exception e) {
			processException(
				e, serviceURL, "corpProjectId", String.valueOf(corpProjectId));

			throw new SystemException(e);
		}
	}

	@Override
	public JSONWebServiceClient getJSONWebServiceClient() {
		return _jsonWebServiceClient;
	}

	@Override
	public User getRemoteUser(String emailAddress)
		throws PortalException, SystemException {

		String serviceURL = _URL_API_JSONWS_USER + "/get-user-by-email-address";

		try {
			Company company = getCompany("liferay.com");

			User user = (User)doGetToObject(
				getClass("com.liferay.portal.model.impl.UserImpl"), serviceURL,
				"companyId", String.valueOf(company.getCompanyId()),
				"emailAddress", emailAddress);

			user.setEmailAddress(emailAddress);

			return user;
		}
		catch (Exception e) {
			processException(e, serviceURL, "emailAddress", emailAddress);

			throw new SystemException(e);
		}
	}

	@Override
	public Role getRole(String name) throws PortalException, SystemException {
		Company company = getCompany("liferay.com");

		String serviceURL = _URL_API_JSONWS_ROLE + "/get-role/";

		try {
			return (Role)doGetToObject(
				getClass("com.liferay.portal.model.impl.RoleImpl"), serviceURL,
				"companyId", String.valueOf(company.getCompanyId()), "name",
				name);
		}
		catch (Exception e) {
			processException(
				e, serviceURL, "companyId",
				String.valueOf(company.getCompanyId()), "name", name);

			throw new SystemException(e);
		}
	}

	@Override
	public List<? extends CorpProject> getUserCorpProjects(long userId)
		throws PortalException, SystemException {

		String serviceURL =
			_URL_API_JSONWS_OSB_PORTLET_CORP_PROJECT +
				"/get-user-corp-projects";
		User user = UserLocalServiceUtil.getUser(userId);

		try {
			return doGetToList(
				CorpProjectClp.class, serviceURL, "roleName",
				OSBPortletUtil.ROLE_OSB_CORP_LCS_USER, "userUuid",
				user.getUuid());
		}
		catch (Exception e) {
			processException(
				e, serviceURL, "roleName",
				OSBPortletUtil.ROLE_OSB_CORP_LCS_USER, "userUuid",
				user.getUuid());

			throw new SystemException(e);
		}
	}

	@Override
	public boolean hasUserCorpProject(long userId, long corpProjectId)
		throws PortalException, SystemException {

		String serviceURL =
			_URL_API_JSONWS_OSB_PORTLET_CORP_PROJECT +
				"/has-user-corp-project";
		User user = UserLocalServiceUtil.getUser(userId);

		try {
			return doGetToObject(
				Boolean.class, serviceURL, "corpProjectId",
				String.valueOf(corpProjectId), "userUuid", user.getUserUuid());
		}
		catch (Exception e) {
			processException(
				e, serviceURL, "corpProjectId", String.valueOf(corpProjectId),
				"userUuid", user.getUserUuid());

			throw new SystemException(e);
		}
	}

	@Override
	public boolean hasUserCorpProjectRole(
			long userId, long corpProjectId, String roleName)
		throws PortalException, SystemException {

		String serviceURL =
			_URL_API_JSONWS_OSB_PORTLET_CORP_PROJECT +
				"/has-user-corp-project-role";
		User user = UserLocalServiceUtil.getUser(userId);

		try {
			return doGetToObject(
				Boolean.class, serviceURL, "corpProjectId",
				String.valueOf(corpProjectId), "userUuid", user.getUserUuid(),
				"roleName", roleName);
		}
		catch (Exception e) {
			processException(
				e, serviceURL, "corpProjectId", String.valueOf(corpProjectId),
				"userUuid", user.getUserUuid(), "roleName", roleName);

			throw new SystemException(e);
		}
	}

	@Override
	public boolean isCorpProjectLicenseKeyActive(long corpProjectId, String key)
		throws PortalException, SystemException {

		String serviceURL =
			_URL_API_JSONWS_OSB_PORTLET_LICENSE_KEY + "/is-active";

		try {
			return doGetToObject(
				Boolean.class, serviceURL, "corpProjectId",
				String.valueOf(corpProjectId), "key", key);
		}
		catch (Exception e) {
			processException(
				e, serviceURL, "corpProjectId", String.valueOf(corpProjectId),
				"key", key);

			throw new SystemException(e);
		}
	}

	public void setJSONWebServiceClient(
		JSONWebServiceClient jsonWebServiceClient) {

		_jsonWebServiceClient = jsonWebServiceClient;
	}

	@Override
	public void unsetCorpProjectUsers(long corpProjectId, long[] userIds)
		throws PortalException, SystemException {

		String serviceURL =
			_URL_API_JSONWS_OSB_PORTLET_CORP_PROJECT +
				"/unset-corp-project-users";
		String[] userUuids = getUserUuids(userIds);

		try {
			doGet(
				serviceURL, "corpProjectId", String.valueOf(corpProjectId),
				"userUuids", StringUtil.merge(userUuids));
		}
		catch (Exception e) {
			processException(
				e, serviceURL, "corpProjectId", String.valueOf(corpProjectId),
				"userUuids", StringUtil.merge(userUuids));

			throw new SystemException(e);
		}
	}

	@Override
	public CorpProject updateCorpProject(long corpProjectId, String name)
		throws PortalException, SystemException {

		String serviceURL =
			_URL_API_JSONWS_OSB_PORTLET_CORP_PROJECT +
				"/update-corp-project";

		try {
			CorpProject corpProject = doGetToObject(
				CorpProjectClp.class, serviceURL, "corpProjectId",
				String.valueOf(corpProjectId), "name", name);

			_portalCache.put(corpProjectId, corpProject);

			return corpProject;
		}
		catch (Exception e) {
			processException(
				e, serviceURL, "corpProjectId", String.valueOf(corpProjectId),
				"name", name);

			throw new SystemException(e);
		}
	}

	protected void checkOrganizationId(long organizationId, String url) {
		if (organizationId == 0) {
			throw new UnsupportedOperationException(
				StringAdvisor.concat(
					"Organization ID value of 0 is invalid for invoking", url));
		}
	}

	protected Class<?> getClass(String className) throws SystemException {
		Class<?> clazz = _classes.get(className);

		if (clazz == null) {
			try {
				clazz = Class.forName(
					className, false, PortalClassLoaderUtil.getClassLoader());

				_classes.put(className, clazz);
			}
			catch (Exception e) {
				throw new SystemException(e);
			}
		}

		return clazz;
	}

	protected Company getCompany(String webId)
		throws PortalException, SystemException {

		try {
			return (Company)doGetToObject(
				getClass("com.liferay.portal.model.impl.CompanyImpl"),
				_URL_API_JSONWS_COMPANY + "/get-company-by-web-id/", "webId",
				webId);
		}
		catch (JSONWebServiceTransportException jsonwste) {
			if (jsonwste instanceof
					JSONWebServiceTransportException.AuthenticationFailure) {

				throw new PrincipalException(jsonwste);
			}

			throw new SystemException(jsonwste);
		}
		catch (Exception e) {
			String message = e.getMessage();

			if (_log.isDebugEnabled()) {
				_log.debug(message);
			}

			if (message.contains("NoSuchCompanyException")) {
				return null;
			}

			throw new SystemException(e);
		}
	}

	@Override
	protected List<User> getOrganizationUsers(long organizationId)
		throws PortalException, SystemException {

		List<User> localUsers = new ArrayList<User>();

		List<User> remoteUsers = null;

		String serviceURL = _URL_API_JSONWS_USER + "/get-organization-users";

		checkOrganizationId(organizationId, serviceURL);

		try {
			remoteUsers = (List<User>)doGetToList(
				getClass("com.liferay.portal.model.impl.UserImpl"), serviceURL,
				"organizationId", String.valueOf(organizationId));
		}
		catch (Exception e) {
			processException(
				e, serviceURL, "organizationId",
				String.valueOf(organizationId));
		}

		for (User remoteUser : remoteUsers) {
			try {
				User localUser = UserLocalServiceUtil.getUserByUuid(
					remoteUser.getUuid());

				localUsers.add(localUser);
			}
			catch (Exception e) {
				_log.error("Unable to find user " + remoteUser.getUuid());
			}
		}

		return localUsers;
	}

	protected PortalException getPortalException(
		JSONWebServiceInvocationException jsonwsie) {

		String message = jsonwsie.getMessage();

		if (message.contains("NoSuchCorpProjectException")) {
			return new NoSuchCorpProjectException(jsonwsie);
		}
		else if (message.contains("NoSuchUserException")) {
			return new NoSuchUserException(jsonwsie);
		}
		else if (message.contains("NoSuchRoleException")) {
			return new NoSuchRoleException(jsonwsie);
		}
		else if (message.contains("NoSuchOrganizationException")) {
			return new NoSuchOrganizationException(jsonwsie);
		}

		return new PortalException(jsonwsie);
	}

	protected void processException(
			Exception e, String url, String... parameters)
		throws PortalException, SystemException {

		String[] strings = new String[parameters.length + 1];

		strings[0] = "Remote call " + url + " failed for";

		for (int i = 0; i < parameters.length; i++) {
			strings[i + 1] = parameters[i];
		}

		_log.error(StringAdvisor.concat(strings));

		if (e instanceof JSONWebServiceInvocationException) {
			throw getPortalException((JSONWebServiceInvocationException)e);
		}

		if (e instanceof
				JSONWebServiceTransportException.AuthenticationFailure) {

			throw new PrincipalException(e);
		}

		throw new SystemException(e);
	}

	private static final String _URL_API_JSONWS = "/api/jsonws";

	private static final String _URL_API_JSONWS_COMPANY =
		_URL_API_JSONWS + "/company";

	private static final String _URL_API_JSONWS_ORGANIZATION =
		_URL_API_JSONWS + "/organization";

	private static final String
		_URL_API_JSONWS_OSB_PORTLET_ACCOUNT_CUSTOMER =
			"/osb-portlet" + _URL_API_JSONWS + "/accountcustomer";

	private static final String
		_URL_API_JSONWS_OSB_PORTLET_ACCOUNT_ENTRY =
			"/osb-portlet" + _URL_API_JSONWS + "/accountentry";

	private static final String _URL_API_JSONWS_OSB_PORTLET_CORP_PROJECT =
		"/osb-portlet" + _URL_API_JSONWS + "/corpproject";

	private static final String
		_URL_API_JSONWS_OSB_PORTLET_LCS_SUBSCRIPTION_ENTRY =
			"/osb-portlet" + _URL_API_JSONWS + "/lcssubscriptionentry";

	private static final String _URL_API_JSONWS_OSB_PORTLET_LICENSE_KEY =
		"/osb-portlet" + _URL_API_JSONWS + "/licensekey";

	private static final String _URL_API_JSONWS_ROLE =
		_URL_API_JSONWS + "/role";

	private static final String _URL_API_JSONWS_USER =
		_URL_API_JSONWS + "/user";

	private static Log _log = LogFactoryUtil.getLog(
		RemoteOSBPortletServiceImpl.class);

	private static PortalCache _portalCache = MultiVMPoolUtil.getCache(
		RemoteOSBPortletServiceImpl.class.getName());

	private Map<String, Class<?>> _classes = new HashMap<String, Class<?>>();

	@BeanReference(name = "osbPortletJSONWebServiceClient")
	private JSONWebServiceClient _jsonWebServiceClient;

}