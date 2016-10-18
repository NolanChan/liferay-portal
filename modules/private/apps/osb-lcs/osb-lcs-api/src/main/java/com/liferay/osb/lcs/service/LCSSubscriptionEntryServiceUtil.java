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
 * Provides the remote service utility for LCSSubscriptionEntry. This utility wraps
 * {@link com.liferay.osb.lcs.service.impl.LCSSubscriptionEntryServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Igor Beslic
 * @see LCSSubscriptionEntryService
 * @see com.liferay.osb.lcs.service.base.LCSSubscriptionEntryServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSSubscriptionEntryServiceImpl
 * @generated
 */
@ProviderType
public class LCSSubscriptionEntryServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSSubscriptionEntryServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasLCSProjectElasticLCSSubscriptionEntry(
		long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .hasLCSProjectElasticLCSSubscriptionEntry(lcsProjectId);
	}

	public static com.liferay.osb.lcs.model.LCSSubscriptionEntry fetchLCSClusterNodeActiveLCSSubscriptionEntry(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().fetchLCSClusterNodeActiveLCSSubscriptionEntry(key);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSSubscriptionEntry> getLCSProjectLCSSubscriptionEntries(
		long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLCSProjectLCSSubscriptionEntries(lcsProjectId);
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSSubscriptionEntry> getLCSProjectLCSSubscriptionEntries(
		long lcsProjectId, boolean status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getLCSProjectLCSSubscriptionEntries(lcsProjectId, status);
	}

	public static void addCorpProjectLCSSubscriptionEntries(
		long corpProjectId, java.lang.String lcsSubscriptionEntriesJSON)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.addCorpProjectLCSSubscriptionEntries(corpProjectId,
			lcsSubscriptionEntriesJSON);
	}

	public static void addLCSSubscriptionEntries(long lcsProjectId,
		java.lang.String lcsSubscriptionEntriesJSON)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.addLCSSubscriptionEntries(lcsProjectId, lcsSubscriptionEntriesJSON);
	}

	public static void refreshLCSProjectLCSSubscriptionEntries()
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().refreshLCSProjectLCSSubscriptionEntries();
	}

	public static void refreshLCSProjectLCSSubscriptionEntries(
		long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().refreshLCSProjectLCSSubscriptionEntries(lcsProjectId);
	}

	public static LCSSubscriptionEntryService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSSubscriptionEntryService, LCSSubscriptionEntryService> _serviceTracker =
		ServiceTrackerFactory.open(LCSSubscriptionEntryService.class);
}