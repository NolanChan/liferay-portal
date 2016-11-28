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

import com.liferay.lcs.subscription.SubscriptionType;
import com.liferay.osb.lcs.constants.OSBLCSActionKeys;
import com.liferay.osb.lcs.model.LCSClusterNodeUptime;
import com.liferay.osb.lcs.service.base.LCSClusterNodeUptimeServiceBaseImpl;
import com.liferay.osb.lcs.service.permission.LCSProjectPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Igor Beslic
 * @see    LCSClusterNodeUptimeServiceBaseImpl
 * @see    com.liferay.osb.lcs.service.LCSClusterNodeUptimeServiceUtil
 */
@ProviderType
public class LCSClusterNodeUptimeServiceImpl
	extends LCSClusterNodeUptimeServiceBaseImpl {

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public double getMonthlyElasticLCSClusterNodeUptimeTotal(
			long lcsProjectId, int month, int year)
		throws PortalException {

		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId, OSBLCSActionKeys.VIEW);

		return lcsClusterNodeUptimeLocalService.
			getMonthlyElasticLCSClusterNodeUptimeTotal(
				lcsProjectId, month, year);
	}

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public Map<Date, Double> getMonthlyElasticLCSClusterNodeUptimeTotalMap(
			long lcsProjectId, int startMonth, int startYear, int endMonth,
			int endYear)
		throws PortalException {

		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId, OSBLCSActionKeys.VIEW);

		return lcsClusterNodeUptimeLocalService.
			getMonthlyElasticLCSClusterNodeUptimeTotalMap(
				lcsProjectId, startMonth, startYear, endMonth, endYear);
	}

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public List<LCSClusterNodeUptime>
		getMonthlyElasticTotalLCSClusterNodeUptimes(
			long lcsProjectId, int month, int year)
		throws PortalException {

		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId, OSBLCSActionKeys.VIEW);

		return lcsClusterNodeUptimeLocalService.
			getMonthlyElasticTotalLCSClusterNodeUptimes(
				lcsProjectId, month, year);
	}

	@JSONWebService(mode = JSONWebServiceMode.IGNORE)
	@Override
	public List<LCSClusterNodeUptime> getMonthlyLCSClusterNodeUptimes(
			long lcsClusterEntryId, long lcsClusterNodeId, long lcsProjectId,
			int month, int year, boolean details, boolean elastic)
		throws PortalException {

		LCSProjectPermission.check(
			getPermissionChecker(), lcsProjectId, OSBLCSActionKeys.VIEW);

		return lcsClusterNodeUptimeLocalService.getMonthlyLCSClusterNodeUptimes(
			lcsClusterEntryId, lcsClusterNodeId, lcsProjectId, month, year,
			details, elastic, SubscriptionType.UNDEFINED);
	}

	@Override
	public void updateLCSClusterNodeUptime(String key) throws PortalException {
		checkPermission();

		lcsClusterNodeUptimeLocalService.updateLCSClusterNodeUptime(key);
	}

	@Override
	public void updateLCSClusterNodeUptimes(String key, String uptimesJSON)
		throws PortalException {

		checkPermission();

		lcsClusterNodeUptimeLocalService.updateLCSClusterNodeUptimes(
			key, uptimesJSON);
	}

	protected void checkPermission() throws PortalException {
		User user = getUser();

		if (StringUtil.equalsIgnoreCase(
				user.getEmailAddress(), "system@liferay.com")) {

			return;
		}

		throw new PrincipalException();
	}

}