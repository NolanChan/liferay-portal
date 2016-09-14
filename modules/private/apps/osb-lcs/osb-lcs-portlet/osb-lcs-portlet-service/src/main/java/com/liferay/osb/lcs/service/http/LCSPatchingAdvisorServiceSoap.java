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

package com.liferay.osb.lcs.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.lcs.service.LCSPatchingAdvisorServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link LCSPatchingAdvisorServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Igor Beslic
 * @see LCSPatchingAdvisorServiceHttp
 * @see LCSPatchingAdvisorServiceUtil
 * @generated
 */
@ProviderType
public class LCSPatchingAdvisorServiceSoap {
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
	public static java.lang.String[] getInstallablePatchIds(
		com.liferay.osb.lcs.model.LCSClusterNodeSoap lcsClusterNode,
		int patchingToolVersion, java.lang.String[] installedPatchIds)
		throws RemoteException {
		try {
			java.util.List<java.lang.String> returnValue = LCSPatchingAdvisorServiceUtil.getInstallablePatchIds(com.liferay.osb.lcs.model.impl.LCSClusterNodeModelImpl.toModel(
						lcsClusterNode), patchingToolVersion, installedPatchIds);

			return returnValue.toArray(new java.lang.String[returnValue.size()]);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
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
	public static java.lang.String[] getInstallablePatchIds(
		java.lang.String key, int patchingToolVersion,
		java.lang.String[] installedPatchIds) throws RemoteException {
		try {
			java.util.List<java.lang.String> returnValue = LCSPatchingAdvisorServiceUtil.getInstallablePatchIds(key,
					patchingToolVersion, installedPatchIds);

			return returnValue.toArray(new java.lang.String[returnValue.size()]);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LCSPatchingAdvisorServiceSoap.class);
}