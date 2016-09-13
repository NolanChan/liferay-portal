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

import com.liferay.osb.lcs.model.LCSMetadata;
import com.liferay.osb.lcs.service.base.LCSMetadataServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import java.util.List;

/**
 * Provides the remote service for accessing LCS metadata. Its methods include
 * permission checks.
 *
 * @author  Igor Beslic
 * @version LCS 1.7.1
 * @since   LCS 1.1
 */
@ProviderType
public class LCSMetadataServiceImpl extends LCSMetadataServiceBaseImpl {

	/**
	 * Returns all LCS metadata matching the portal instance's build number and
	 * edition.
	 *
	 * @param  buildNumber the portal instance's build number
	 * @param  portalEdition the portal instance's edition
	 * @return the matching LCS metadata
	 * @throws PortalException if the caller was not signed in to the LCS portal
	 *         instance
	 * @since  LCS 1.1
	 */
	@Override
	public List<LCSMetadata> getLCSMetadatas(
			int buildNumber, String portalEdition)
		throws PortalException {

		PermissionChecker permissionChecker = getPermissionChecker();

		if (!permissionChecker.isSignedIn()) {
			throw new PrincipalException();
		}

		return lcsMetadataLocalService.getLCSMetadatas(
			buildNumber, portalEdition);
	}

}