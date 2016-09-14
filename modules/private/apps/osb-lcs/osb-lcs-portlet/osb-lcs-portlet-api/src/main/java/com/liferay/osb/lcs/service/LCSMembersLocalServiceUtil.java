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
 * Provides the local service utility for LCSMembers. This utility wraps
 * {@link com.liferay.osb.lcs.service.impl.LCSMembersLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Igor Beslic
 * @see LCSMembersLocalService
 * @see com.liferay.osb.lcs.service.base.LCSMembersLocalServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSMembersLocalServiceImpl
 * @generated
 */
@ProviderType
public class LCSMembersLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSMembersLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Publishes a message to the LCS event queue at the portal's message bus.
	*
	* @param key the portal instance key provided by the LCS key generator
	* @param lcsEventType the event type that occurred in the portal instance
	* @since LCS 1.3
	*/
	public static void fireLCSEvent(java.lang.String key,
		com.liferay.lcs.notification.LCSEventType lcsEventType) {
		getService().fireLCSEvent(key, lcsEventType);
	}

	public static void invalidateLCSSiteMembership(long companyId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().invalidateLCSSiteMembership(companyId, userId);
	}

	public static void validateCorpProjectUsers(long corpProjectId,
		long[] userIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().validateCorpProjectUsers(corpProjectId, userIds);
	}

	public static void validateLCSSiteMembership(long companyId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().validateLCSSiteMembership(companyId, userId);
	}

	public static LCSMembersLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSMembersLocalService, LCSMembersLocalService> _serviceTracker =
		ServiceTrackerFactory.open(LCSMembersLocalService.class);
}