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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeLayoutMetrics;

import java.util.Date;
import java.util.List;

/**
 * @author Ivica Cardic
 */
public interface LCSClusterNodeLayoutMetricsService {

	public LCSClusterNodeLayoutMetrics addLCSClusterNodeLayoutMetrics(
		int averageLoadTime, long companyId, String description, int frequency,
		long groupId, String key, Date modifiedDate, String name,
		String requestStatus, String requestURL);

	public LCSClusterNodeLayoutMetrics fetchLCSClusterNodeLayoutMetrics(
		long companyId, long groupId, String key, String name);

	public LCSClusterNodeLayoutMetrics fetchLCSClusterNodeLayoutMetrics(
		long companyId, String key, String name);

	public int getLCSClusterNodeLayoutMetricsCount(
		long companyId, long groupId, String key);

	public int getLCSClusterNodeLayoutMetricsCount(long companyId, String key);

	public List<? extends LCSClusterNodeLayoutMetrics>
		getLCSClusterNodeLayoutMetricsList(
			long companyId, int end, long groupId, String key,
			String orderByCol, String orderByType, int start);

	public List<? extends LCSClusterNodeLayoutMetrics>
		getLCSClusterNodeLayoutMetricsList(
			long companyId, int end, String key, String orderByCol,
			String orderByType, int start);

	public LCSClusterNodeLayoutMetrics updateLCSClusterNodeLayoutMetrics(
		LCSClusterNodeLayoutMetrics lcsClusterNodeLayoutMetrics, int duration,
		int frequency, Date modifiedDate);

}