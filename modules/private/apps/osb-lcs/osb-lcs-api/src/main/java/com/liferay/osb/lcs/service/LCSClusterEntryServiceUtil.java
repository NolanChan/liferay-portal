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
 * Provides the remote service utility for LCSClusterEntry. This utility wraps
 * {@link com.liferay.osb.lcs.service.impl.LCSClusterEntryServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Igor Beslic
 * @see LCSClusterEntryService
 * @see com.liferay.osb.lcs.service.base.LCSClusterEntryServiceBaseImpl
 * @see com.liferay.osb.lcs.service.impl.LCSClusterEntryServiceImpl
 * @generated
 */
@ProviderType
public class LCSClusterEntryServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.lcs.service.impl.LCSClusterEntryServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static byte[] exportLCSClusterEntryToken(long lcsProjectId,
		java.lang.String lcsClusterEntryName,
		java.lang.String subscriptionType, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .exportLCSClusterEntryToken(lcsProjectId,
			lcsClusterEntryName, subscriptionType, type);
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntry addLCSClusterEntry(
		long lcsProjectId, java.lang.String name, java.lang.String description,
		java.lang.String location, java.lang.String subscriptionType, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addLCSClusterEntry(lcsProjectId, name, description,
			location, subscriptionType, type);
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntry deleteLCSClusterEntry(
		long lcsClusterEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLCSClusterEntry(lcsClusterEntryId);
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntry getLCSClusterEntry(
		long lcsClusterEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLCSClusterEntry(lcsClusterEntryId);
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntry updateElastic(
		long lcsClusterEntryId, boolean elastic)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateElastic(lcsClusterEntryId, elastic);
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntry updateHighPageLoadTime(
		long lcsClusterEntryId, int highPageLoadTime)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateHighPageLoadTime(lcsClusterEntryId, highPageLoadTime);
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntry updateLCSClusterEntry(
		long lcsClusterEntryId, java.lang.String name,
		java.lang.String description, java.lang.String location)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateLCSClusterEntry(lcsClusterEntryId, name, description,
			location);
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntry updateMediumPageLoadTime(
		long lcsClusterEntryId, int mediumPageLoadTime)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateMediumPageLoadTime(lcsClusterEntryId,
			mediumPageLoadTime);
	}

	public static com.liferay.osb.lcs.model.LCSClusterEntry updateSubscriptionType(
		long lcsClusterEntryId, java.lang.String subscriptionType)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateSubscriptionType(lcsClusterEntryId, subscriptionType);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> getArchivedLCSProjectLCSClusterEntries(
		long lcsProjectId, java.lang.String subscriptionType)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getArchivedLCSProjectLCSClusterEntries(lcsProjectId,
			subscriptionType);
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> getLCSProjectLCSClusterEntries(
		long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLCSProjectLCSClusterEntries(lcsProjectId);
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> getLCSProjectLCSClusterEntries(
		long lcsProjectId, java.lang.String subscriptionType)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getLCSProjectLCSClusterEntries(lcsProjectId,
			subscriptionType);
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> getLCSProjectManageableLCSClusterEntries(
		long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getLCSProjectManageableLCSClusterEntries(lcsProjectId);
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> getUserLCSClusterEntries()
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserLCSClusterEntries();
	}

	public static java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> getUserLCSClusterEntries(
		long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserLCSClusterEntries(lcsProjectId);
	}

	public static void deleteLCSProjectClusters(long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteLCSProjectClusters(lcsProjectId);
	}

	public static LCSClusterEntryService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LCSClusterEntryService, LCSClusterEntryService> _serviceTracker =
		ServiceTrackerFactory.open(LCSClusterEntryService.class);
}