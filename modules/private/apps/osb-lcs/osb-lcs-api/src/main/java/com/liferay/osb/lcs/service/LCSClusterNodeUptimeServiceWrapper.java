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
 * Provides a wrapper for {@link LCSClusterNodeUptimeService}.
 *
 * @author Igor Beslic
 * @see LCSClusterNodeUptimeService
 * @generated
 */
@ProviderType
public class LCSClusterNodeUptimeServiceWrapper
	implements LCSClusterNodeUptimeService,
		ServiceWrapper<LCSClusterNodeUptimeService> {
	public LCSClusterNodeUptimeServiceWrapper(
		LCSClusterNodeUptimeService lcsClusterNodeUptimeService) {
		_lcsClusterNodeUptimeService = lcsClusterNodeUptimeService;
	}

	@Override
	public double getMonthlyElasticLCSClusterNodeUptimeTotal(
		long lcsProjectId, int month, int year)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeUptimeService.getMonthlyElasticLCSClusterNodeUptimeTotal(lcsProjectId,
			month, year);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _lcsClusterNodeUptimeService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterNodeUptime> getMonthlyElasticTotalLCSClusterNodeUptimes(
		long lcsProjectId, int month, int year)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeUptimeService.getMonthlyElasticTotalLCSClusterNodeUptimes(lcsProjectId,
			month, year);
	}

	@Override
	public java.util.List<com.liferay.osb.lcs.model.LCSClusterNodeUptime> getMonthlyLCSClusterNodeUptimes(
		long lcsClusterEntryId, long lcsClusterNodeId, long lcsProjectId,
		int month, int year, boolean details, boolean elastic)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeUptimeService.getMonthlyLCSClusterNodeUptimes(lcsClusterEntryId,
			lcsClusterNodeId, lcsProjectId, month, year, details, elastic);
	}

	@Override
	public java.util.Map<java.util.Date, java.lang.Double> getMonthlyElasticLCSClusterNodeUptimeTotalMap(
		long lcsProjectId, int startMonth, int startYear, int endMonth,
		int endYear) throws com.liferay.portal.kernel.exception.PortalException {
		return _lcsClusterNodeUptimeService.getMonthlyElasticLCSClusterNodeUptimeTotalMap(lcsProjectId,
			startMonth, startYear, endMonth, endYear);
	}

	@Override
	public void updateLCSClusterNodeUptime(java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		_lcsClusterNodeUptimeService.updateLCSClusterNodeUptime(key);
	}

	@Override
	public void updateLCSClusterNodeUptimes(java.lang.String key,
		java.lang.String uptimesJSON)
		throws com.liferay.portal.kernel.exception.PortalException {
		_lcsClusterNodeUptimeService.updateLCSClusterNodeUptimes(key,
			uptimesJSON);
	}

	@Override
	public LCSClusterNodeUptimeService getWrappedService() {
		return _lcsClusterNodeUptimeService;
	}

	@Override
	public void setWrappedService(
		LCSClusterNodeUptimeService lcsClusterNodeUptimeService) {
		_lcsClusterNodeUptimeService = lcsClusterNodeUptimeService;
	}

	private LCSClusterNodeUptimeService _lcsClusterNodeUptimeService;
}