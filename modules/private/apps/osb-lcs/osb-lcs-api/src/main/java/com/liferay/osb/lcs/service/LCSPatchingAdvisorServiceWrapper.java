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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LCSPatchingAdvisorService}.
 *
 * @author Igor Beslic
 * @see LCSPatchingAdvisorService
 * @generated
 */
@ProviderType
public class LCSPatchingAdvisorServiceWrapper
	implements LCSPatchingAdvisorService,
		ServiceWrapper<LCSPatchingAdvisorService> {
	public LCSPatchingAdvisorServiceWrapper(
		LCSPatchingAdvisorService lcsPatchingAdvisorService) {
		_lcsPatchingAdvisorService = lcsPatchingAdvisorService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _lcsPatchingAdvisorService.getOSGiServiceIdentifier();
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
	@Override
	public java.util.List<java.lang.String> getInstallablePatchIds(
		com.liferay.osb.lcs.model.LCSClusterNode lcsClusterNode,
		int patchingToolVersion, java.lang.String[] installedPatchIds) {
		return _lcsPatchingAdvisorService.getInstallablePatchIds(lcsClusterNode,
			patchingToolVersion, installedPatchIds);
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
	@Override
	public java.util.List<java.lang.String> getInstallablePatchIds(
		java.lang.String key, int patchingToolVersion,
		java.lang.String[] installedPatchIds) {
		return _lcsPatchingAdvisorService.getInstallablePatchIds(key,
			patchingToolVersion, installedPatchIds);
	}

	@Override
	public LCSPatchingAdvisorService getWrappedService() {
		return _lcsPatchingAdvisorService;
	}

	@Override
	public void setWrappedService(
		LCSPatchingAdvisorService lcsPatchingAdvisorService) {
		_lcsPatchingAdvisorService = lcsPatchingAdvisorService;
	}

	private LCSPatchingAdvisorService _lcsPatchingAdvisorService;
}