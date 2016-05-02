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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeJVMMetricsEvents;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author Riccardo Ferrari
 */
public interface LCSClusterNodeJVMMetricsEventsService {

	public LCSClusterNodeJVMMetricsEvents addLCSClusterNodeJVMMetricsEvents(
		Map<String, Map<String, Long>> bufferPoolMetrics, int daemonThreadCount,
		Set<String> deadlockedThreads, double fileDescriptorUsage,
		Map<String, Map<String, Long>> garbageCollectorMetrics,
		double heapCommitted, double heapInit, double heapMax, double heapUsage,
		double heapUsed, String key, Map<String, Double> memoryPoolUsage,
		Date modifiedDate, String name, double nonHeapUsage, int threadCount,
		Map<String, Double> threadStatePercentages, double totalCommitted,
		double totalInit, double totalMax, double totalUsed, int uptime,
		String version);

}