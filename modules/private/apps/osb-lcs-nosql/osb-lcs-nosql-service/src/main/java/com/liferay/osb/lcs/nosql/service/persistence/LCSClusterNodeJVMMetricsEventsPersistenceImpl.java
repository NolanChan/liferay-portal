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

package com.liferay.osb.lcs.nosql.service.persistence;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.utils.UUIDs;

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeJVMMetricsEvents;
import com.liferay.osb.lcs.nosql.model.impl.LCSClusterNodeJVMMetricsEventsImpl;
import com.liferay.osb.lcs.nosql.service.persistence.base.BasePersistenceImpl;
import com.liferay.osb.lcs.nosql.util.DMLStatementExecutor;

import java.util.Map;

/**
 * @author Riccardo Ferrari
 */
public class LCSClusterNodeJVMMetricsEventsPersistenceImpl
	extends BasePersistenceImpl<LCSClusterNodeJVMMetricsEvents>
	implements LCSClusterNodeJVMMetricsEventsPersistence {

	@Override
	public LCSClusterNodeJVMMetricsEvents create() {
		LCSClusterNodeJVMMetricsEvents lcsClusterNodeJVMMetricsEvents =
			new LCSClusterNodeJVMMetricsEventsImpl();

		lcsClusterNodeJVMMetricsEvents.setNew(true);

		return lcsClusterNodeJVMMetricsEvents;
	}

	@Override
	public LCSClusterNodeJVMMetricsEvents update(
		LCSClusterNodeJVMMetricsEvents lcsClusterNodeJVMMetricsEvents) {

		DMLStatementExecutor dmlStatementExecutor = new DMLStatementExecutor(
			lcsClusterNodeJVMMetricsEvents, _TABLE_NAME);

		dmlStatementExecutor.addColumns(
			"bufferPoolMetrics",
			convertGenericsMapToJSONMap(
				lcsClusterNodeJVMMetricsEvents.getBufferPoolMetrics()),
			"daemonThreadCount",
			lcsClusterNodeJVMMetricsEvents.getDaemonThreadCount(),
			"deadlockedThreads",
			lcsClusterNodeJVMMetricsEvents.getDeadlockedThreads(),
			"fileDescriptorUsage",
			lcsClusterNodeJVMMetricsEvents.getFileDescriptorUsage(),
			"garbageCollectorMetrics",
			convertGenericsMapToJSONMap(
				lcsClusterNodeJVMMetricsEvents.getGarbageCollectorMetrics()),
			"heapCommitted", lcsClusterNodeJVMMetricsEvents.getHeapCommitted(),
			"heapInit", lcsClusterNodeJVMMetricsEvents.getHeapInit(), "heapMax",
			lcsClusterNodeJVMMetricsEvents.getHeapMax(), "heapUsage",
			lcsClusterNodeJVMMetricsEvents.getHeapUsage(), "heapUsed",
			lcsClusterNodeJVMMetricsEvents.getHeapUsed(), "memoryPoolUsage",
			lcsClusterNodeJVMMetricsEvents.getMemoryPoolUsage(), "modifiedDate",
			lcsClusterNodeJVMMetricsEvents.getModifiedDate(), "name",
			lcsClusterNodeJVMMetricsEvents.getName(), "nonHeapUsage",
			lcsClusterNodeJVMMetricsEvents.getNonHeapUsage(), "partitionKey",
			lcsClusterNodeJVMMetricsEvents.getPartitionKey(), "threadCount",
			lcsClusterNodeJVMMetricsEvents.getThreadCount(),
			"threadStatePercentages",
			lcsClusterNodeJVMMetricsEvents.getThreadStatePercentages(),
			"totalCommitted",
			lcsClusterNodeJVMMetricsEvents.getTotalCommitted(), "totalInit",
			lcsClusterNodeJVMMetricsEvents.getTotalInit(), "totalMax",
			lcsClusterNodeJVMMetricsEvents.getTotalMax(), "totalUsed",
			lcsClusterNodeJVMMetricsEvents.getTotalUsed(), "uptime",
			lcsClusterNodeJVMMetricsEvents.getUptime(), "version",
			lcsClusterNodeJVMMetricsEvents.getVersion());

		dmlStatementExecutor.addPrimaryKeyColumns("uuid", UUIDs.timeBased());

		dmlStatementExecutor.addPrimaryKeys(
			"key_", lcsClusterNodeJVMMetricsEvents.getKey());

		dmlStatementExecutor.execute(session);

		return lcsClusterNodeJVMMetricsEvents;
	}

	@Override
	protected String[] getColumnNames() {
		return _COLUMN_NAMES;
	}

	@Override
	protected LCSClusterNodeJVMMetricsEvents getEntity(Row row) {
		LCSClusterNodeJVMMetricsEvents lcsClusterNodeJVMMetricsEvents =
			new LCSClusterNodeJVMMetricsEventsImpl();

		Map<String, Map<String, Long>> bufferPoolMetrics =
			convertJSONMapToGenericsMap(
				row.getMap("bufferPoolMetrics", String.class, String.class),
				Long.class);

		lcsClusterNodeJVMMetricsEvents.setBufferPoolMetrics(bufferPoolMetrics);

		lcsClusterNodeJVMMetricsEvents.setDaemonThreadCount(
			row.getInt("daemonThreadCount"));
		lcsClusterNodeJVMMetricsEvents.setDeadlockedThreads(
			row.getSet("deadlockedThreads", String.class));
		lcsClusterNodeJVMMetricsEvents.setFileDescriptorUsage(
			row.getDouble("fileDescriptorUsage"));

		Map<String, Map<String, Long>> garbageCollectorMetrics =
			convertJSONMapToGenericsMap(
				row.getMap(
					"garbageCollectorMetrics", String.class, String.class),
				Long.class);

		lcsClusterNodeJVMMetricsEvents.setGarbageCollectorMetrics(
			garbageCollectorMetrics);

		lcsClusterNodeJVMMetricsEvents.setHeapCommitted(
			row.getDouble("heapCommitted"));
		lcsClusterNodeJVMMetricsEvents.setHeapInit(row.getDouble("heapInit"));
		lcsClusterNodeJVMMetricsEvents.setHeapMax(row.getDouble("heapMax"));
		lcsClusterNodeJVMMetricsEvents.setHeapUsage(row.getDouble("heapUsage"));
		lcsClusterNodeJVMMetricsEvents.setHeapUsed(row.getDouble("heapUsed"));
		lcsClusterNodeJVMMetricsEvents.setKey(row.getString("key_"));
		lcsClusterNodeJVMMetricsEvents.setMemoryPoolUsage(
			row.getMap("memoryPoolUsage", String.class, Double.class));
		lcsClusterNodeJVMMetricsEvents.setModifiedDate(
			row.getTimestamp("modifiedDate"));
		lcsClusterNodeJVMMetricsEvents.setName(row.getString("name"));
		lcsClusterNodeJVMMetricsEvents.setNonHeapUsage(
			row.getDouble("nonHeapUsage"));
		lcsClusterNodeJVMMetricsEvents.setPartitionKey(
			row.getString("partitionKey"));
		lcsClusterNodeJVMMetricsEvents.setThreadCount(
			row.getInt("threadCount"));
		lcsClusterNodeJVMMetricsEvents.setThreadStatePercentages(
			row.getMap("threadStatePercentages", String.class, Double.class));
		lcsClusterNodeJVMMetricsEvents.setTotalCommitted(
			row.getDouble("totalCommitted"));
		lcsClusterNodeJVMMetricsEvents.setTotalInit(row.getDouble("totalInit"));
		lcsClusterNodeJVMMetricsEvents.setTotalMax(row.getDouble("totalMax"));
		lcsClusterNodeJVMMetricsEvents.setTotalUsed(row.getDouble("totalUsed"));
		lcsClusterNodeJVMMetricsEvents.setUptime(row.getInt("uptime"));
		lcsClusterNodeJVMMetricsEvents.setUUID(
			String.valueOf(row.getUUID("uuid")));
		lcsClusterNodeJVMMetricsEvents.setVersion(row.getString("version"));

		return lcsClusterNodeJVMMetricsEvents;
	}

	@Override
	protected String getTableName() {
		return _TABLE_NAME;
	}

	private static final String[] _COLUMN_NAMES = {
		"bufferPoolMetrics", "daemonThreadCount", "deadlockedThreads",
		"fileDescriptorUsage", "garbageCollectorMetrics", "heapCommitted",
		"heapInit", "heapMax", "heapUsage", "heapUsed", "key_",
		"memoryPoolUsage", "modifiedDate", "name", "nonHeapUsage",
		"partitionKey", "threadCount", "threadStatePercentages",
		"totalCommitted", "totalInit", "totalMax", "totalUsed", "uptime",
		"uuid", "version"
	};

	private static final String _TABLE_NAME = "LCSClusterNodeJVMMetricsEvents";

}