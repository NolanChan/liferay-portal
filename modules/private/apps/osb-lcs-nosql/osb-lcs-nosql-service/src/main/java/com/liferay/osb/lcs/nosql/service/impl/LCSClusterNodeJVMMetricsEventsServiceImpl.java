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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeJVMMetricsEvents;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeJVMMetricsEventsService;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodeJVMMetricsEventsPersistence;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Ferrari
 */
@Component(
	immediate = true, service = LCSClusterNodeJVMMetricsEventsService.class
)
public class LCSClusterNodeJVMMetricsEventsServiceImpl
	implements LCSClusterNodeJVMMetricsEventsService {

	@Override
	public LCSClusterNodeJVMMetricsEvents addLCSClusterNodeJVMMetricsEvents(
		Map<String, Map<String, Long>> bufferPoolMetrics, int daemonThreadCount,
		Set<String> deadlockedThreads, double fileDescriptorUsage,
		Map<String, Map<String, Long>> garbageCollectorMetrics,
		double heapCommitted, double heapInit, double heapMax, double heapUsage,
		double heapUsed, String key, Map<String, Double> memoryPoolUsage,
		Date modifiedDate, String name, double nonHeapUsage, int threadCount,
		Map<String, Double> threadStatePercentages, double totalCommitted,
		double totalInit, double totalMax, double totalUsed, int uptime,
		String version) {

		LCSClusterNodeJVMMetricsEvents lcsClusterNodeJVMMetricsEvents =
			_lcsClusterNodeJVMMetricsEventsPersistence.create();

		lcsClusterNodeJVMMetricsEvents.setBufferPoolMetrics(bufferPoolMetrics);
		lcsClusterNodeJVMMetricsEvents.setDaemonThreadCount(daemonThreadCount);
		lcsClusterNodeJVMMetricsEvents.setDeadlockedThreads(deadlockedThreads);
		lcsClusterNodeJVMMetricsEvents.setFileDescriptorUsage(
			fileDescriptorUsage);
		lcsClusterNodeJVMMetricsEvents.setGarbageCollectorMetrics(
			garbageCollectorMetrics);
		lcsClusterNodeJVMMetricsEvents.setHeapCommitted(heapCommitted);
		lcsClusterNodeJVMMetricsEvents.setHeapInit(heapInit);
		lcsClusterNodeJVMMetricsEvents.setHeapMax(heapMax);
		lcsClusterNodeJVMMetricsEvents.setHeapUsage(heapUsage);
		lcsClusterNodeJVMMetricsEvents.setHeapUsed(heapUsed);
		lcsClusterNodeJVMMetricsEvents.setKey(key);
		lcsClusterNodeJVMMetricsEvents.setMemoryPoolUsage(memoryPoolUsage);
		lcsClusterNodeJVMMetricsEvents.setModifiedDate(modifiedDate);
		lcsClusterNodeJVMMetricsEvents.setName(name);
		lcsClusterNodeJVMMetricsEvents.setNonHeapUsage(nonHeapUsage);
		lcsClusterNodeJVMMetricsEvents.setPartitionKey(
			getPartitionKey(modifiedDate));
		lcsClusterNodeJVMMetricsEvents.setThreadCount(threadCount);
		lcsClusterNodeJVMMetricsEvents.setThreadStatePercentages(
			threadStatePercentages);
		lcsClusterNodeJVMMetricsEvents.setTotalCommitted(totalCommitted);
		lcsClusterNodeJVMMetricsEvents.setTotalInit(totalInit);
		lcsClusterNodeJVMMetricsEvents.setTotalMax(totalMax);
		lcsClusterNodeJVMMetricsEvents.setTotalUsed(totalUsed);
		lcsClusterNodeJVMMetricsEvents.setUptime(uptime);
		lcsClusterNodeJVMMetricsEvents.setVersion(version);

		return _lcsClusterNodeJVMMetricsEventsPersistence.update(
			lcsClusterNodeJVMMetricsEvents);
	}

	public void setLCSClusterNodeJVMMetricsEventsPersistence(
		LCSClusterNodeJVMMetricsEventsPersistence
			lcsClusterNodeJVMMetricsEventsPersistence) {

		_lcsClusterNodeJVMMetricsEventsPersistence =
			lcsClusterNodeJVMMetricsEventsPersistence;
	}

	protected String getPartitionKey(Date modifiedDate) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-");

		String partitionKey = dateFormat.format(modifiedDate);

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(modifiedDate);

		int minutes = calendar.get(Calendar.MINUTE);

		int quarter = minutes / 15;

		partitionKey = partitionKey.concat(String.valueOf(quarter));

		return partitionKey;
	}

	private LCSClusterNodeJVMMetricsEventsPersistence
		_lcsClusterNodeJVMMetricsEventsPersistence;

}