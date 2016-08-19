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

package com.liferay.osb.lcs.nosql.service;

import com.liferay.osb.lcs.nosql.model.LCSClusterNodePortletMetrics;

import java.util.Date;
import java.util.List;

/**
 * @author Ivica Cardic
 */
public interface LCSClusterNodePortletMetricsService {

	public LCSClusterNodePortletMetrics addLCSClusterNodePortletMetrics(
		int averageLoadTime, long companyId, String displayName, int frequency,
		long groupId, String key, String layoutName, Date modifiedDate,
		String name, String portletId, String requestType);

	public LCSClusterNodePortletMetrics fetchLCSClusterNodePortletMetrics(
		long companyId, long groupId, String key, String layoutName,
		String portletId, String requestType);

	public LCSClusterNodePortletMetrics fetchLCSClusterNodePortletMetrics(
		long companyId, String key, String layoutName, String portletId,
		String requestType);

	public int getLCSClusterNodePortletMetricsCount(
		long companyId, long groupId, String key, String layoutName,
		String requestType);

	public int getLCSClusterNodePortletMetricsCount(
		long companyId, String key, String layoutName, String requestType);

	public List<? extends LCSClusterNodePortletMetrics>
		getLCSClusterNodePortletMetricsList(
			long companyId, int end, long groupId, String key,
			String layoutName, String orderByCol, String orderByType,
			String requestType, int start);

	public List<? extends LCSClusterNodePortletMetrics>
		getLCSClusterNodePortletMetricsList(
			long companyId, int end, String key, String layoutName,
			String orderByCol, String orderByType, String requestType,
			int start);

	public LCSClusterNodePortletMetrics updateLCSClusterNodePortletMetrics(
		LCSClusterNodePortletMetrics lcsClusterNodePortletMetrics, int duration,
		int frequency, Date modifiedDate);

}