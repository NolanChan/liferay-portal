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
 * Provides the remote service utility for LCSClusterEntryToken. This utility wraps
 * {@link com.liferay.osb.lcs.service.impl.LCSClusterEntryTokenServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Igor Beslic
 * @see LCSClusterEntryTokenService
 * @see com.liferay.osb.lcs.service.base.LCSClusterEntryTokenServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSClusterEntryTokenServiceImpl
 * @generated
 */
@ProviderType
public class LCSClusterEntryTokenServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSClusterEntryTokenServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean isValid(long lcsClusterEntryTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().isValid(lcsClusterEntryTokenId);
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntryToken addLCSClusterEntryToken(
		long lcsClusterEntryId,
		java.util.Map<java.lang.String, java.lang.String> lcsServicesConfiguration)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addLCSClusterEntryToken(lcsClusterEntryId,
			lcsServicesConfiguration);
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntryToken deleteLCSClusterEntryToken(
		long lcsClusterEntryTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLCSClusterEntryToken(lcsClusterEntryTokenId);
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntryToken fetchLCSClusterEntryLCSClusterEntryToken(
		long lcsClusterEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .fetchLCSClusterEntryLCSClusterEntryToken(lcsClusterEntryId);
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntryToken fetchLCSClusterEntryToken(
		long lcsClusterEntryTokenId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().fetchLCSClusterEntryToken(lcsClusterEntryTokenId);
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntryToken regenerateLCSClusterEntryToken(
		long lcsClusterEntryId,
		java.util.Map<java.lang.String, java.lang.String> lcsServicesConfiguration)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .regenerateLCSClusterEntryToken(lcsClusterEntryId,
			lcsServicesConfiguration);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static LCSClusterEntryTokenService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSClusterEntryTokenService, LCSClusterEntryTokenService> _serviceTracker =
		ServiceTrackerFactory.open(LCSClusterEntryTokenService.class);
}