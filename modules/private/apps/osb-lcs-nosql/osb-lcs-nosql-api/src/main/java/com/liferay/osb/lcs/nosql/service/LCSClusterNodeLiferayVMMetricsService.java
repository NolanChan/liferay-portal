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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeLiferayVMMetrics;

import java.util.Date;
import java.util.List;

/**
 * @author Ivica Cardic
 */
public interface LCSClusterNodeLiferayVMMetricsService {

	public LCSClusterNodeLiferayVMMetrics addLCSClusterNodeLiferayVMMetrics(
		int cacheHits, int cacheMisses, String key, Date modifiedDate,
		String name, int objectCount, String type);

	public LCSClusterNodeLiferayVMMetrics fetchLCSClusterNodeLiferayVmMetrics(
		String key, String name, String type);

	public List<? extends LCSClusterNodeLiferayVMMetrics>
		getLCSClusterNodeLiferayVMMetricsList(String key, String type);

	public LCSClusterNodeLiferayVMMetrics updateLCSClusterNodeLiferayVMMetrics(
		LCSClusterNodeLiferayVMMetrics lcsClusterNodeLiferayVMMetrics,
		int cacheHits, int cacheMisses, Date modifiedDate, String name,
		int objectCount, String type);

}