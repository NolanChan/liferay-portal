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
 * Provides a wrapper for {@link LCSSubscriptionEntryService}.
 *
 * @author Igor Beslic
 * @see LCSSubscriptionEntryService
 * @generated
 */
@ProviderType
public class LCSSubscriptionEntryServiceWrapper
	implements LCSSubscriptionEntryService,
		ServiceWrapper<LCSSubscriptionEntryService> {
	public LCSSubscriptionEntryServiceWrapper(
		LCSSubscriptionEntryService lcsSubscriptionEntryService) {
		_lcsSubscriptionEntryService = lcsSubscriptionEntryService;
	}

	@Override
	public boolean hasLCSProjectElasticLCSSubscriptionEntry(long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsSubscriptionEntryService.hasLCSProjectElasticLCSSubscriptionEntry(lcsProjectId);
	}

	@Override
	public com.liferay.osb.lcs.model.LCSSubscriptionEntry fetchLCSClusterNodeActiveLCSSubscriptionEntry(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsSubscriptionEntryService.fetchLCSClusterNodeActiveLCSSubscriptionEntry(key);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _lcsSubscriptionEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSSubscriptionEntry> getLCSProjectLCSSubscriptionEntries(
		long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsSubscriptionEntryService.getLCSProjectLCSSubscriptionEntries(lcsProjectId);
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSSubscriptionEntry> getLCSProjectLCSSubscriptionEntries(
		long lcsProjectId, boolean status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsSubscriptionEntryService.getLCSProjectLCSSubscriptionEntries(lcsProjectId,
			status);
	}

	@Override
	public void addCorpProjectLCSSubscriptionEntries(long corpProjectId,
		java.lang.String lcsSubscriptionEntriesJSON)
		throws com.liferay.portal.kernel.exception.PortalException {
		_lcsSubscriptionEntryService.addCorpProjectLCSSubscriptionEntries(corpProjectId,
			lcsSubscriptionEntriesJSON);
	}

	@Override
	public void addLCSSubscriptionEntries(long lcsProjectId,
		java.lang.String lcsSubscriptionEntriesJSON)
		throws com.liferay.portal.kernel.exception.PortalException {
		_lcsSubscriptionEntryService.addLCSSubscriptionEntries(lcsProjectId,
			lcsSubscriptionEntriesJSON);
	}

	@Override
	public void refreshLCSProjectLCSSubscriptionEntries()
		throws com.liferay.portal.kernel.exception.PortalException {
		_lcsSubscriptionEntryService.refreshLCSProjectLCSSubscriptionEntries();
	}

	@Override
	public void refreshLCSProjectLCSSubscriptionEntries(long lcsProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_lcsSubscriptionEntryService.refreshLCSProjectLCSSubscriptionEntries(lcsProjectId);
	}

	@Override
	public LCSSubscriptionEntryService getWrappedService() {
		return _lcsSubscriptionEntryService;
	}

	@Override
	public void setWrappedService(
		LCSSubscriptionEntryService lcsSubscriptionEntryService) {
		_lcsSubscriptionEntryService = lcsSubscriptionEntryService;
	}

	private LCSSubscriptionEntryService _lcsSubscriptionEntryService;
}