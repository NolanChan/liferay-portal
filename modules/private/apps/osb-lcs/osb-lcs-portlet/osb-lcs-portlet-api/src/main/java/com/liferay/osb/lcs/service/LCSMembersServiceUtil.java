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
 * Provides the remote service utility for LCSMembers. This utility wraps
 * {@link com.liferay.osb.lcs.service.impl.LCSMembersServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Igor Beslic
 * @see LCSMembersService
 * @see com.liferay.osb.lcs.service.base.LCSMembersServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSMembersServiceImpl
 * @generated
 */
@ProviderType
public class LCSMembersServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSMembersServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Publishes a monitoring unavailable event that targets all watchers of the
	* portal instance specified by the key.
	*
	* @param key the portal instance key provided by the LCS key generator
	* @throws PortalException if a portal exception occurred
	* @since LCS 1.3
	*/
	public static void sendMonitoringUnavailableEmail(java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().sendMonitoringUnavailableEmail(key);
	}

	/**
	* Publishes a patching tool unavailable event that targets all watchers of
	* the portal instance specified by the key.
	*
	* @param key the portal instance key provided by the LCS key generator
	* @throws PortalException if a portal exception occurred
	* @since LCS 1.3
	*/
	public static void sendPatchingToolUnavailableEmail(java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().sendPatchingToolUnavailableEmail(key);
	}

	/**
	* Publishes a server manually shut down event that targets all watchers of
	* the portal instance identified by the key.
	*
	* @param key the portal instance key provided by the LCS key generator
	* @throws PortalException if a portal exception occurred
	* @since LCS 1.3
	*/
	public static void sendServerManuallyShutdownEmail(java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().sendServerManuallyShutdownEmail(key);
	}

	/**
	* Publishes a server unexpectedly shut down event that targets all watchers
	* of the portal instance specified by the key.
	*
	* @param key the portal instance key provided by the LCS key generator
	* @throws PortalException if a portal exception occurred
	* @since LCS 1.3
	*/
	public static void sendServerUnexpectedlyShutdownEmail(java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().sendServerUnexpectedlyShutdownEmail(key);
	}

	public static LCSMembersService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSMembersService, LCSMembersService> _serviceTracker =
		ServiceTrackerFactory.open(LCSMembersService.class);
}