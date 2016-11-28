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

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.osb.lcs.configuration.OSBLCSConfiguration;
import com.liferay.osb.lcs.constants.LCSRoleConstants;
import com.liferay.osb.lcs.constants.OSBLCSActionKeys;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.model.LCSRole;
import com.liferay.osb.lcs.osbportlet.service.OSBPortletService;
import com.liferay.osb.lcs.service.base.LCSProjectServiceBaseImpl;
import com.liferay.osb.lcs.service.permission.LCSProjectPermission;
import com.liferay.osb.lcs.util.ApplicationProfile;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UniqueList;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Collections;
import java.util.List;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Igor Beslic
 * @see    LCSProjectServiceBaseImpl
 * @see    LCSProjectServiceUtil
 */
@ProviderType
public class LCSProjectServiceImpl extends LCSProjectServiceBaseImpl {

	@Override
	public LCSProject addDefaultLCSProject() throws PortalException {
		checkSignedIn();

		User user = getUser();

		LCSProject lcsProject = lcsProjectLocalService.addLCSProject(
			"LCS", LCSConstants.CORP_PROJECT_DEFAULT_NAME, user.getUserId());

		lcsClusterEntryLocalService.addLCSClusterEntry(
			lcsProject.getLcsProjectId(),
			LCSConstants.LCS_CLUSTER_ENTRY_DEFAULT_NAME, null, null, null,
			LCSConstants.LCS_CLUSTER_ENTRY_TYPE_ENVIRONMENT);

		return lcsProject;
	}

	@Override
	public LCSProject addLCSProject(String sourceSystemName, String name)
		throws PortalException {

		checkSignedIn();

		return lcsProjectLocalService.addLCSProject(
			sourceSystemName, name, getUserId());
	}

	@Override
	public boolean checkUserAccountEntryLCSProject() throws PortalException {
		checkSignedIn();

		return lcsProjectLocalService.checkUserAccountEntryLCSProject(
			getUser());
	}

	@Override
	public LCSProject deleteLCSProject(long lcsProjectId)
		throws PortalException {

		OSBLCSConfiguration configuration = getConfiguration();

		if (configuration.applicationProfile() ==
				ApplicationProfile.PRODUCTION) {

			throw new UnsupportedOperationException();
		}

		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId, OSBLCSActionKeys.MANAGE);

		return lcsProjectLocalService.deleteLCSProject(lcsProjectId);
	}

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public LCSProject getLCSProject(long lcsProjectId) throws PortalException {
		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId, OSBLCSActionKeys.VIEW);

		return lcsProjectPersistence.findByPrimaryKey(lcsProjectId);
	}

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public String getLCSProjectAdministratorEmailAddress(long lcsProjectId)
		throws PortalException {

		List<LCSRole> lcsProjectLCSRoles = lcsRolePersistence.findByLPI_R(
			lcsProjectId, LCSRoleConstants.ROLE_LCS_ADMINISTRATOR);

		LCSRole lcsRole = lcsProjectLCSRoles.get(0);

		User user = userLocalService.getUser(lcsRole.getUserId());

		return user.getEmailAddress();
	}

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public long getUserDefaultLCSProjectId() throws PortalException {
		checkSignedIn();

		User user = userLocalService.getUser(getUserId());

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		long defaultLCSProjectId = GetterUtil.getLong(
			expandoBridge.getAttribute("defaultLCSProjectId", false));

		if (defaultLCSProjectId > 0) {
			LCSProject lcsProject = getLCSProject(defaultLCSProjectId);

			if (lcsProject != null) {
				return defaultLCSProjectId;
			}
		}

		List<LCSProject> lcsProjects = getUserLCSProjects();

		if (lcsProjects.isEmpty()) {
			return 0;
		}

		for (LCSProject lcsProject : lcsProjects) {
			if (StringUtil.equalsIgnoreCase(
					lcsProject.getName(),
					LCSConstants.CORP_PROJECT_DEFAULT_NAME)) {

				return lcsProject.getLcsProjectId();
			}
		}

		LCSProject defaultLCSProject = lcsProjects.get(0);

		return defaultLCSProject.getLcsProjectId();
	}

	@Override
	public List<LCSProject> getUserDomainLCSProjects() throws PortalException {
		checkSignedIn();

		return lcsProjectLocalService.getUserDomainLCSProjects(getUser());
	}

	@Override
	public List<LCSProject> getUserLCSProjects() throws PortalException {
		return getUserLCSProjects(
			false,
			LCSRoleConstants.ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER);
	}

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public List<LCSProject> getUserLCSProjects(boolean lcsRole)
		throws PortalException {

		checkSignedIn();

		return lcsProjectLocalService.getUserLCSProjects(getUserId(), lcsRole);
	}

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public List<LCSProject> getUserLCSProjects(boolean lcsRole, int role)
		throws PortalException {

		User user = getUser();

		if (user.isDefaultUser()) {
			return Collections.emptyList();
		}

		return lcsProjectLocalService.getUserLCSProjects(
			getUserId(), lcsRole, role);
	}

	@Override
	public List<LCSProject> getUserManageableLCSProjects()
		throws PortalException {

		List<LCSProject> manageableLCSProjects = new UniqueList<>();

		manageableLCSProjects.addAll(
			getUserLCSProjects(true, LCSRoleConstants.ROLE_LCS_ADMINISTRATOR));
		manageableLCSProjects.addAll(
			getUserLCSProjects(
				true, LCSRoleConstants.ROLE_LCS_ENVIRONMENT_MANAGER));

		return manageableLCSProjects;
	}

	@Reference(bind = "-")
	public void setOSBPortletService(OSBPortletService osbPortletService) {
		_osbPortletService = osbPortletService;
	}

	@Override
	public LCSProject updateLCSProjectName(long lcsProjectId, String name)
		throws PortalException {

		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId, OSBLCSActionKeys.MANAGE);

		LCSProject lcsProject = lcsProjectPersistence.findByPrimaryKey(
			lcsProjectId);

		lcsProject.setName(name);

		lcsProject = lcsProjectLocalService.updateLCSProject(lcsProject);

		_osbPortletService.updateCorpProject(
			lcsProject.getCorpProjectId(), name);

		return lcsProject;
	}

	protected void checkSignedIn() throws PrincipalException {
		PermissionChecker permissionChecker = getPermissionChecker();

		if (!permissionChecker.isSignedIn()) {
			throw new PrincipalException();
		}
	}

	protected OSBLCSConfiguration getConfiguration()
		throws ConfigurationException {

		return _configurationProvider.getCompanyConfiguration(
			OSBLCSConfiguration.class, 0);
	}

	@ServiceReference(type = ConfigurationProvider.class)
	private ConfigurationProvider _configurationProvider;

	private OSBPortletService _osbPortletService;

}