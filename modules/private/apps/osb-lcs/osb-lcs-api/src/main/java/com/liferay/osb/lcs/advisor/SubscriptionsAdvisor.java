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

package com.liferay.osb.lcs.advisor;

import com.liferay.osb.lcs.model.LCSSubscriptionEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Matija Petanjek
 */
public interface SubscriptionsAdvisor {

	public String formatUptime(long startTime, long endTime, Locale locale);

	public JSONObject getBillsJSONObject(
			long lcsProjectId, int month, int year, Locale locale)
		throws Exception;

	public String getDuration(long start, long end, Locale locale);

	public JSONArray getLCSClusterEntriesJSONArray(
			long lcsProjectId, Locale locale)
		throws PortalException;

	public JSONArray getLCSClusterNodesJSONArray(
			long lcsProjectId, Locale locale)
		throws PortalException;

	public JSONObject getLCSClusterNodeUptimesJSONObject(
			long lcsClusterEntryId, long lcsProjectId, int month, int year,
			Locale locale)
		throws Exception;

	public JSONArray getLCSSubscriptionEntriesJSONArray(
		List<LCSSubscriptionEntry> lcsOrderEntries, Locale locale,
		TimeZone timeZone);

	public JSONArray getPaymentsJSONArray(
			long lcsProjectId, int startMonth, int startYear, int endMonth,
			int endYear)
		throws Exception;

	public JSONArray getSubscriptionsUsageJSONArray(
			long lcsProjectId, Locale locale)
		throws PortalException;

	public boolean hasElasticSubscription(long lcsProjectId)
		throws PortalException;

	public int[] parsePeriod(String period);

}