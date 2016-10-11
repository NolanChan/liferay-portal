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
 * Provides the remote service utility for LCSPatchingAdvisor. This utility wraps
 * {@link com.liferay.osb.lcs.service.impl.LCSPatchingAdvisorServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Igor Beslic
 * @see LCSPatchingAdvisorService
 * @see com.liferay.osb.lcs.service.base.LCSPatchingAdvisorServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSPatchingAdvisorServiceImpl
 * @generated
 */
@ProviderType
public class LCSPatchingAdvisorServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSPatchingAdvisorServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Returns a list of patch IDs for patches that can be installed in the LCS
	* cluster node.
	*
	* <p>
	* For a particular portal build number and patching tool version, this
	* method compares available patches to those already installed in the LCS
	* cluster node. First, the patches already installed are removed from the
	* list of available patches. Dependency requirements for the remaining
	* available patches are then checked. If these requirements are met and
	* multiple versions of the same patch component are available, a third
	* check is performed that selects the highest patch component version.
	* </p>
	*
	* @param lcsClusterNode the LCS cluster node instance
	* @param patchingToolVersion the patching tool version of the LCS cluster
	node
	* @param installedPatchIds the IDs of the patches already installed in the
	LCS cluster node
	* @return the IDs of the patches that are safe to install in the LCS
	cluster node
	* @since LCS 0.1
	*/
	public static java.util.List<java.lang.String> getInstallablePatchIds(
		com.liferay.osb.lcs.model.LCSClusterNode lcsClusterNode,
		int patchingToolVersion, java.lang.String[] installedPatchIds) {
		return getService()
				   .getInstallablePatchIds(lcsClusterNode, patchingToolVersion,
			installedPatchIds);
	}

	/**
	* Returns a list of patch IDs for patches that can be installed in the LCS
	* cluster node matching the specified portal key.
	*
	* <p>
	* For a particular portal build number and patching tool version, this
	* method compares available patches to those already installed in the LCS
	* cluster node. First, the patches already installed are removed from the
	* list of available patches. Dependency requirements for the remaining
	* available patches are then checked. If these requirements are met and
	* multiple versions of the same patch component are available, a third
	* check is performed that selects the highest patch component version.
	* </p>
	*
	* @param key the portal key of the LCS cluster node
	* @param patchingToolVersion the patching tool version of the LCS cluster
	node
	* @param installedPatchIds the IDs of the patches already installed in the
	LCS cluster node
	* @return the IDs of the patches that are safe to install in the LCS
	cluster node
	* @since LCS 0.1
	*/
	public static java.util.List<java.lang.String> getInstallablePatchIds(
		java.lang.String key, int patchingToolVersion,
		java.lang.String[] installedPatchIds) {
		return getService()
				   .getInstallablePatchIds(key, patchingToolVersion,
			installedPatchIds);
	}

	public static LCSPatchingAdvisorService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSPatchingAdvisorService, LCSPatchingAdvisorService> _serviceTracker =
		ServiceTrackerFactory.open(LCSPatchingAdvisorService.class);
}