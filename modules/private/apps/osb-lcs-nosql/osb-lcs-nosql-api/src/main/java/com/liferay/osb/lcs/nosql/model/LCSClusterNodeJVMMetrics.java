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

package com.liferay.osb.lcs.nosql.model;

import java.util.Map;
import java.util.Set;

/**
 * @author Ivica Cardic
 */
public interface LCSClusterNodeJVMMetrics extends LCSClusterNode {

	public Map<String, Map<String, Long>> getBufferPoolMetrics();

	public int getDaemonThreadCount();

	public Set<String> getDeadlockedThreads();

	public double getFileDescriptorUsage();

	public Map<String, Map<String, Long>> getGarbageCollectorMetrics();

	public double getHeapCommitted();

	public double getHeapInit();

	public double getHeapMax();

	public double getHeapUsage();

	public double getHeapUsed();

	public Map<String, Double> getMemoryPoolUsage();

	public String getName();

	public double getNonHeapUsage();

	public int getThreadCount();

	public Map<String, Double> getThreadStatePercentages();

	public double getTotalCommitted();

	public double getTotalInit();

	public double getTotalMax();

	public double getTotalUsed();

	public int getUptime();

	public String getVersion();

	public void setBufferPoolMetrics(
		Map<String, Map<String, Long>> bufferPoolMetrics);

	public void setDaemonThreadCount(int daemonThreadCount);

	public void setDeadlockedThreads(Set<String> deadlockedThreads);

	public void setFileDescriptorUsage(double fileDescriptorUsage);

	public void setGarbageCollectorMetrics(
		Map<String, Map<String, Long>> garbageCollectorMetrics);

	public void setHeapCommitted(double heapCommitted);

	public void setHeapInit(double heapInit);

	public void setHeapMax(double heapMax);

	public void setHeapUsage(double heapUsage);

	public void setHeapUsed(double heapUsed);

	public void setMemoryPoolUsage(Map<String, Double> memoryPoolUsage);

	public void setName(String name);

	public void setNonHeapUsage(double nonHeapUsage);

	public void setThreadCount(int threadCount);

	public void setThreadStatePercentages(
		Map<String, Double> threadStatePercentages);

	public void setTotalCommitted(double totalCommitted);

	public void setTotalInit(double totalInit);

	public void setTotalMax(double totalMax);

	public void setTotalUsed(double totalUsed);

	public void setUptime(int uptime);

	public void setVersion(String version);

}