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

package com.liferay.osb.lcs.nosql.service.impl;

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeJVMMetrics;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeJVMMetricsService;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodeJVMMetricsPersistence;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author Ivica Cardic
 */
public class LCSClusterNodeJVMMetricsServiceImpl
	implements LCSClusterNodeJVMMetricsService {

	@Override
	public LCSClusterNodeJVMMetrics addLCSClusterNodeJVMMetrics(
		Map<String, Map<String, Long>> bufferPoolMetrics, int daemonThreadCount,
		Set<String> deadlockedThreads, double fileDescriptorUsage,
		Map<String, Map<String, Long>> garbageCollectorMetrics,
		double heapCommitted, double heapInit, double heapMax, double heapUsage,
		double heapUsed, String key, Map<String, Double> memoryPoolUsage,
		Date modifiedDate, String name, double nonHeapUsage, int threadCount,
		Map<String, Double> threadStatePercentages, double totalCommitted,
		double totalInit, double totalMax, double totalUsed, int uptime,
		String version) {

		LCSClusterNodeJVMMetrics lcsClusterNodeJVMMetrics =
			_lcsClusterNodeJVMMetricsPersistence.create();

		lcsClusterNodeJVMMetrics.setBufferPoolMetrics(bufferPoolMetrics);
		lcsClusterNodeJVMMetrics.setDaemonThreadCount(daemonThreadCount);
		lcsClusterNodeJVMMetrics.setDeadlockedThreads(deadlockedThreads);
		lcsClusterNodeJVMMetrics.setFileDescriptorUsage(fileDescriptorUsage);
		lcsClusterNodeJVMMetrics.setGarbageCollectorMetrics(
			garbageCollectorMetrics);
		lcsClusterNodeJVMMetrics.setHeapCommitted(heapCommitted);
		lcsClusterNodeJVMMetrics.setHeapInit(heapInit);
		lcsClusterNodeJVMMetrics.setHeapMax(heapMax);
		lcsClusterNodeJVMMetrics.setHeapUsage(heapUsage);
		lcsClusterNodeJVMMetrics.setHeapUsed(heapUsed);
		lcsClusterNodeJVMMetrics.setKey(key);
		lcsClusterNodeJVMMetrics.setMemoryPoolUsage(memoryPoolUsage);
		lcsClusterNodeJVMMetrics.setModifiedDate(modifiedDate);
		lcsClusterNodeJVMMetrics.setName(name);
		lcsClusterNodeJVMMetrics.setNonHeapUsage(nonHeapUsage);
		lcsClusterNodeJVMMetrics.setThreadCount(threadCount);
		lcsClusterNodeJVMMetrics.setThreadStatePercentages(
			threadStatePercentages);
		lcsClusterNodeJVMMetrics.setTotalCommitted(totalCommitted);
		lcsClusterNodeJVMMetrics.setTotalInit(totalInit);
		lcsClusterNodeJVMMetrics.setTotalMax(totalMax);
		lcsClusterNodeJVMMetrics.setTotalUsed(totalUsed);
		lcsClusterNodeJVMMetrics.setUptime(uptime);
		lcsClusterNodeJVMMetrics.setVersion(version);

		return _lcsClusterNodeJVMMetricsPersistence.update(
			lcsClusterNodeJVMMetrics);
	}

	@Override
	public LCSClusterNodeJVMMetrics fetchLCSClusterNodeJVMMetrics(String key) {
		LCSClusterNodeJVMMetrics lcsClusterNodeJVMMetrics =
			_lcsClusterNodeJVMMetricsPersistence.fetchByKey(key);

		return lcsClusterNodeJVMMetrics;
	}

	public void setLCSClusterNodeJVMMetricsPersistence(
		LCSClusterNodeJVMMetricsPersistence
			lcsClusterNodeJVMMetricsPersistence) {

		_lcsClusterNodeJVMMetricsPersistence =
			lcsClusterNodeJVMMetricsPersistence;
	}

	@Override
	public LCSClusterNodeJVMMetrics updateLCSClusterNodeJVMMetrics(
		LCSClusterNodeJVMMetrics lcsClusterNodeJVMMetrics,
		Map<String, Map<String, Long>> bufferPoolMetrics, int daemonThreadCount,
		Set<String> deadlockedThreads, double fileDescriptorUsage,
		Map<String, Map<String, Long>> garbageCollectorMetrics,
		double heapCommitted, double heapInit, double heapMax, double heapUsage,
		double heapUsed, Map<String, Double> memoryPoolUsage, Date modifiedDate,
		String name, double nonHeapUsage, int threadCount,
		Map<String, Double> threadStatePercentages, double totalCommitted,
		double totalInit, double totalMax, double totalUsed, int uptime,
		String version) {

		lcsClusterNodeJVMMetrics.setBufferPoolMetrics(bufferPoolMetrics);
		lcsClusterNodeJVMMetrics.setDaemonThreadCount(daemonThreadCount);
		lcsClusterNodeJVMMetrics.setDeadlockedThreads(deadlockedThreads);
		lcsClusterNodeJVMMetrics.setFileDescriptorUsage(fileDescriptorUsage);
		lcsClusterNodeJVMMetrics.setGarbageCollectorMetrics(
			garbageCollectorMetrics);
		lcsClusterNodeJVMMetrics.setHeapCommitted(heapCommitted);
		lcsClusterNodeJVMMetrics.setHeapInit(heapInit);
		lcsClusterNodeJVMMetrics.setHeapMax(heapMax);
		lcsClusterNodeJVMMetrics.setHeapUsage(heapUsage);
		lcsClusterNodeJVMMetrics.setHeapUsed(heapUsed);
		lcsClusterNodeJVMMetrics.setMemoryPoolUsage(memoryPoolUsage);
		lcsClusterNodeJVMMetrics.setModifiedDate(modifiedDate);
		lcsClusterNodeJVMMetrics.setName(name);
		lcsClusterNodeJVMMetrics.setNonHeapUsage(nonHeapUsage);
		lcsClusterNodeJVMMetrics.setThreadCount(threadCount);
		lcsClusterNodeJVMMetrics.setThreadStatePercentages(
			threadStatePercentages);
		lcsClusterNodeJVMMetrics.setTotalCommitted(totalCommitted);
		lcsClusterNodeJVMMetrics.setTotalInit(totalInit);
		lcsClusterNodeJVMMetrics.setTotalMax(totalMax);
		lcsClusterNodeJVMMetrics.setTotalUsed(totalUsed);
		lcsClusterNodeJVMMetrics.setUptime(uptime);
		lcsClusterNodeJVMMetrics.setVersion(version);

		_lcsClusterNodeJVMMetricsPersistence.update(lcsClusterNodeJVMMetrics);

		return lcsClusterNodeJVMMetrics;
	}

	private LCSClusterNodeJVMMetricsPersistence
		_lcsClusterNodeJVMMetricsPersistence;

}