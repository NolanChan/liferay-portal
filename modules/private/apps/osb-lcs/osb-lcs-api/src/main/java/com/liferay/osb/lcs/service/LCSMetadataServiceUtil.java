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
 * Provides the remote service utility for LCSMetadata. This utility wraps
 * {@link com.liferay.osb.lcs.service.impl.LCSMetadataServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Igor Beslic
 * @see LCSMetadataService
 * @see com.liferay.osb.lcs.service.base.LCSMetadataServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSMetadataServiceImpl
 * @generated
 */
@ProviderType
public class LCSMetadataServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSMetadataServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Returns all LCS metadata matching the portal instance's build number and
	* edition.
	*
	* @param buildNumber the portal instance's build number
	* @param portalEdition the portal instance's edition
	* @return the matching LCS metadata
	* @throws PortalException if the caller was not signed in to the LCS portal
	instance
	* @since LCS 1.1
	*/
	public static java.util.List<com.liferay.osb.lcs.model.LCSMetadata> getLCSMetadatas(
		int buildNumber, java.lang.String portalEdition)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLCSMetadatas(buildNumber, portalEdition);
	}

	public static LCSMetadataService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSMetadataService, LCSMetadataService> _serviceTracker =
		ServiceTrackerFactory.open(LCSMetadataService.class);
}