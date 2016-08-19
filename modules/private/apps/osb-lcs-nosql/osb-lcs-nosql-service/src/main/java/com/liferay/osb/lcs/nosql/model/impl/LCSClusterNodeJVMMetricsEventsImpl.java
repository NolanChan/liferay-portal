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

package com.liferay.osb.lcs.nosql.model.impl;

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeJVMMetricsEvents;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author Riccardo Ferrari
 */
public class LCSClusterNodeJVMMetricsEventsImpl
	implements LCSClusterNodeJVMMetricsEvents {

	@Override
	public Map<String, Map<String, Long>> getBufferPoolMetrics() {
		return _bufferPoolMetrics;
	}

	@Override
	public int getDaemonThreadCount() {
		return _daemonThreadCount;
	}

	@Override
	public Set<String> getDeadlockedThreads() {
		return _deadlockedThreads;
	}

	@Override
	public double getFileDescriptorUsage() {
		return _fileDescriptorUsage;
	}

	@Override
	public Map<String, Map<String, Long>> getGarbageCollectorMetrics() {
		return _garbageCollectorMetrics;
	}

	@Override
	public double getHeapCommitted() {
		return _heapCommitted;
	}

	@Override
	public double getHeapInit() {
		return _heapInit;
	}

	@Override
	public double getHeapMax() {
		return _heapMax;
	}

	@Override
	public double getHeapUsage() {
		return _heapUsage;
	}

	@Override
	public double getHeapUsed() {
		return _heapUsed;
	}

	@Override
	public String getKey() {
		return _key;
	}

	@Override
	public Map<String, Double> getMemoryPoolUsage() {
		return _memoryPoolUsage;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public double getNonHeapUsage() {
		return _nonHeapUsage;
	}

	@Override
	public String getPartitionKey() {
		return _partitionKey;
	}

	@Override
	public int getThreadCount() {
		return _threadCount;
	}

	@Override
	public Map<String, Double> getThreadStatePercentages() {
		return _threadStatePercentages;
	}

	@Override
	public double getTotalCommitted() {
		return _totalCommitted;
	}

	@Override
	public double getTotalInit() {
		return _totalInit;
	}

	@Override
	public double getTotalMax() {
		return _totalMax;
	}

	@Override
	public double getTotalUsed() {
		return _totalUsed;
	}

	@Override
	public int getUptime() {
		return _uptime;
	}

	@Override
	public String getUUID() {
		return _uuid;
	}

	@Override
	public String getVersion() {
		return _version;
	}

	@Override
	public boolean isNew() {
		return _new;
	}

	@Override
	public void setBufferPoolMetrics(
		Map<String, Map<String, Long>> bufferPoolMetrics) {

		_bufferPoolMetrics = bufferPoolMetrics;
	}

	@Override
	public void setDaemonThreadCount(int daemonThreadCount) {
		_daemonThreadCount = daemonThreadCount;
	}

	@Override
	public void setDeadlockedThreads(Set<String> deadlockedThreads) {
		_deadlockedThreads = deadlockedThreads;
	}

	@Override
	public void setFileDescriptorUsage(double fileDescriptorUsage) {
		_fileDescriptorUsage = fileDescriptorUsage;
	}

	@Override
	public void setGarbageCollectorMetrics(
		Map<String, Map<String, Long>> garbageCollectorMetrics) {

		_garbageCollectorMetrics = garbageCollectorMetrics;
	}

	@Override
	public void setHeapCommitted(double heapCommitted) {
		_heapCommitted = heapCommitted;
	}

	@Override
	public void setHeapInit(double heapInit) {
		_heapInit = heapInit;
	}

	@Override
	public void setHeapMax(double heapMax) {
		_heapMax = heapMax;
	}

	@Override
	public void setHeapUsage(double heapUsage) {
		_heapUsage = heapUsage;
	}

	@Override
	public void setHeapUsed(double heapUsed) {
		_heapUsed = heapUsed;
	}

	@Override
	public void setKey(String key) {
		_key = key;
	}

	@Override
	public void setMemoryPoolUsage(Map<String, Double> memoryPoolUsage) {
		_memoryPoolUsage = memoryPoolUsage;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@Override
	public void setNew(boolean n) {
		_new = n;
	}

	@Override
	public void setNonHeapUsage(double nonHeapUsage) {
		_nonHeapUsage = nonHeapUsage;
	}

	@Override
	public void setPartitionKey(String partitionKey) {
		_partitionKey = partitionKey;
	}

	@Override
	public void setThreadCount(int threadCount) {
		_threadCount = threadCount;
	}

	@Override
	public void setThreadStatePercentages(
		Map<String, Double> threadStatePercentages) {

		_threadStatePercentages = threadStatePercentages;
	}

	@Override
	public void setTotalCommitted(double totalCommitted) {
		_totalCommitted = totalCommitted;
	}

	@Override
	public void setTotalInit(double totalInit) {
		_totalInit = totalInit;
	}

	@Override
	public void setTotalMax(double totalMax) {
		_totalMax = totalMax;
	}

	@Override
	public void setTotalUsed(double totalUsed) {
		_totalUsed = totalUsed;
	}

	@Override
	public void setUptime(int uptime) {
		_uptime = uptime;
	}

	@Override
	public void setUUID(String uuid) {
		_uuid = uuid;
	}

	@Override
	public void setVersion(String version) {
		_version = version;
	}

	private Map<String, Map<String, Long>> _bufferPoolMetrics;
	private int _daemonThreadCount;
	private Set<String> _deadlockedThreads;
	private double _fileDescriptorUsage;
	private Map<String, Map<String, Long>> _garbageCollectorMetrics;
	private double _heapCommitted;
	private double _heapInit;
	private double _heapMax;
	private double _heapUsage;
	private double _heapUsed;
	private String _key;
	private Map<String, Double> _memoryPoolUsage;
	private Date _modifiedDate;
	private String _name;
	private boolean _new;
	private double _nonHeapUsage;
	private String _partitionKey;
	private int _threadCount;
	private Map<String, Double> _threadStatePercentages;
	private double _totalCommitted;
	private double _totalInit;
	private double _totalMax;
	private double _totalUsed;
	private int _uptime;
	private String _uuid;
	private String _version;

}