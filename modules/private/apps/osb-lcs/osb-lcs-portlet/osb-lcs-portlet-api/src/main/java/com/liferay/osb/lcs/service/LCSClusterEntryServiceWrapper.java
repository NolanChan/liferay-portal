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
 * Provides a wrapper for {@link LCSClusterEntryService}.
 *
 * @author Igor Beslic
 * @see LCSClusterEntryService
 * @generated
 */
@ProviderType
public class LCSClusterEntryServiceWrapper implements LCSClusterEntryService,
	ServiceWrapper<LCSClusterEntryService> {
	public LCSClusterEntryServiceWrapper(
		LCSClusterEntryService lcsClusterEntryService) {
		_lcsClusterEntryService = lcsClusterEntryService;
	}

	@Override
	public byte[] exportLCSClusterEntryToken(long lcsProjectId,
		java.lang.String lcsClusterEntryName,
		java.lang.String subscriptionType, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryService.exportLCSClusterEntryToken(lcsProjectId,
			lcsClusterEntryName, subscriptionType, type);
	}

	@Deprecated
	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntry addLCSClusterEntry(
		long lcsProjectId, java.lang.String name, java.lang.String description,
		java.lang.String location, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryService.addLCSClusterEntry(lcsProjectId, name,
			description, location, type);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntry addLCSClusterEntry(
		long lcsProjectId, java.lang.String name, java.lang.String description,
		java.lang.String location, java.lang.String subscriptionType, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryService.addLCSClusterEntry(lcsProjectId, name,
			description, location, subscriptionType, type);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntry deleteLCSClusterEntry(
		long lcsClusterEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryService.deleteLCSClusterEntry(lcsClusterEntryId);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntry getLCSClusterEntry(
		long lcsClusterEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryService.getLCSClusterEntry(lcsClusterEntryId);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntry updateElastic(
		long lcsClusterEntryId, boolean elastic)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryService.updateElastic(lcsClusterEntryId, elastic);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntry updateHighPageLoadTime(
		long lcsClusterEntryId, int highPageLoadTime)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryService.updateHighPageLoadTime(lcsClusterEntryId,
			highPageLoadTime);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntry updateLCSClusterEntry(
		long lcsClusterEntryId, java.lang.String name,
		java.lang.String description, java.lang.String location)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryService.updateLCSClusterEntry(lcsClusterEntryId,
			name, description, location);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntry updateMediumPageLoadTime(
		long lcsClusterEntryId, int mediumPageLoadTime)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryService.updateMediumPageLoadTime(lcsClusterEntryId,
			mediumPageLoadTime);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSClusterEntry updateSubscriptionType(
		long lcsClusterEntryId, java.lang.String subscriptionType)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryService.updateSubscriptionType(lcsClusterEntryId,
			subscriptionType);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _lcsClusterEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> getArchivedLCSProjectLCSClusterEntries(
		long lcsProjectId, java.lang.String subscriptionType)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryService.getArchivedLCSProjectLCSClusterEntries(lcsProjectId,
			subscriptionType);
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> getLCSProjectLCSClusterEntries(
		long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryService.getLCSProjectLCSClusterEntries(lcsProjectId);
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> getLCSProjectLCSClusterEntries(
		long lcsProjectId, java.lang.String subscriptionType)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryService.getLCSProjectLCSClusterEntries(lcsProjectId,
			subscriptionType);
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> getLCSProjectManageableLCSClusterEntries(
		long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryService.getLCSProjectManageableLCSClusterEntries(lcsProjectId);
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> getUserLCSClusterEntries()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryService.getUserLCSClusterEntries();
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterEntry> getUserLCSClusterEntries(
		long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterEntryService.getUserLCSClusterEntries(lcsProjectId);
	}

	@Override
	public void deleteLCSProjectClusters(long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_lcsClusterEntryService.deleteLCSProjectClusters(lcsProjectId);
	}

	@Override
	public LCSClusterEntryService getWrappedService() {
		return _lcsClusterEntryService;
	}

	@Override
	public void setWrappedService(LCSClusterEntryService lcsClusterEntryService) {
		_lcsClusterEntryService = lcsClusterEntryService;
	}

	private LCSClusterEntryService _lcsClusterEntryService;
}