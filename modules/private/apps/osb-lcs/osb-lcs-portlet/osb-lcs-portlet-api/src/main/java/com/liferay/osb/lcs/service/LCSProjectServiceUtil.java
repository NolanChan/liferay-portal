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

package com.liferay.osb.lcs.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for LCSProject. This utility wraps
 * {@link com.liferay.osb.lcs.service.impl.LCSProjectServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Igor Beslic
 * @see LCSProjectService
 * @see com.liferay.osb.lcs.service.base.LCSProjectServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSProjectServiceImpl
 * @generated
 */
@ProviderType
public class LCSProjectServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSProjectServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean checkUserAccountEntryLCSProject()
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().checkUserAccountEntryLCSProject();
	}

	public static com.liferay.osb.lcs.model.LCSProject addDefaultLCSProject()
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().addDefaultLCSProject();
	}

	public static com.liferay.osb.lcs.model.LCSProject addLCSProject(
		java.lang.String sourceSystemName, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().addLCSProject(sourceSystemName, name);
	}

	public static com.liferay.osb.lcs.model.LCSProject deleteLCSProject(
		long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLCSProject(lcsProjectId);
	}

	public static com.liferay.osb.lcs.model.LCSProject getLCSProject(
		long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLCSProject(lcsProjectId);
	}

	public static com.liferay.osb.lcs.model.LCSProject updateLCSProjectName(
		long lcsProjectId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateLCSProjectName(lcsProjectId, name);
	}

	public static java.lang.String getLCSProjectAdministratorEmailAddress(
		long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLCSProjectAdministratorEmailAddress(lcsProjectId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSProject> getUserDomainLCSProjects()
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserDomainLCSProjects();
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSProject> getUserLCSProjects()
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserLCSProjects();
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSProject> getUserLCSProjects(
		boolean lcsRole)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserLCSProjects(lcsRole);
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSProject> getUserLCSProjects(
		boolean lcsRole, int role)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserLCSProjects(lcsRole, role);
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSProject> getUserManageableLCSProjects()
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserManageableLCSProjects();
	}

	public static long getUserDefaultLCSProjectId()
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserDefaultLCSProjectId();
	}

	public static LCSProjectService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSProjectService, LCSProjectService> _serviceTracker =
		ServiceTrackerFactory.open(LCSProjectService.class);
}