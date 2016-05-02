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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeJDBCConnectionPoolMetrics;

import java.util.Date;
import java.util.List;

/**
 * @author Ivica Cardic
 */
public interface LCSClusterNodeJDBCConnectionPoolMetricsService {

	public LCSClusterNodeJDBCConnectionPoolMetrics
		addLCSClusterNodeJDBCConnectionPoolMetrics(
			String key, Date modifiedDate, String name, int numActive,
			int numIdle);

	public LCSClusterNodeJDBCConnectionPoolMetrics
		fetchLCSClusterNodeJDBCConnectionPoolMetrics(String key, String name);

	public List<LCSClusterNodeJDBCConnectionPoolMetrics>
		getLCSClusterNodeJDBCConnectionPoolMetricsList(String key);

	public LCSClusterNodeJDBCConnectionPoolMetrics
		updateLCSClusterNodeJDBCConnectionPoolMetrics(
			LCSClusterNodeJDBCConnectionPoolMetrics
				lcsClusterNodeJDBCConnectionPoolMetrics, Date modifiedDate,
			String name, int numActive, int numIdle);

}