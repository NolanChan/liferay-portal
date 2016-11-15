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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeJVMMetrics;
import com.liferay.osb.lcs.nosql.model.impl.LCSClusterNodeJVMMetricsImpl;
import com.liferay.osb.lcs.nosql.service.persistence.base.BasePersistenceImpl;
import com.liferay.osb.lcs.nosql.util.DMLStatementExecutor;

import java.util.Map;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class LCSClusterNodeJVMMetricsPersistenceImpl
	extends BasePersistenceImpl<LCSClusterNodeJVMMetrics>
	implements LCSClusterNodeJVMMetricsPersistence {

	@Override
	public LCSClusterNodeJVMMetrics create() {
		LCSClusterNodeJVMMetrics lcsClusterNodeJVMMetrics =
			new LCSClusterNodeJVMMetricsImpl();

		lcsClusterNodeJVMMetrics.setNew(true);

		return lcsClusterNodeJVMMetrics;
	}

	@Override
	public LCSClusterNodeJVMMetrics fetchByKey(String key) {
		return fetchByArray("key_", key);
	}

	@Override
	public LCSClusterNodeJVMMetrics update(
		LCSClusterNodeJVMMetrics lcsClusterNodeJVMMetrics) {

		DMLStatementExecutor dmlStatementExecutor = new DMLStatementExecutor(
			lcsClusterNodeJVMMetrics, _TABLE_NAME);

		dmlStatementExecutor.addColumns(
			"bufferPoolMetrics",
			convertGenericsMapToJSONMap(
				lcsClusterNodeJVMMetrics.getBufferPoolMetrics()),
			"daemonThreadCount",
			lcsClusterNodeJVMMetrics.getDaemonThreadCount(),
			"deadlockedThreads",
			lcsClusterNodeJVMMetrics.getDeadlockedThreads(),
			"fileDescriptorUsage",
			lcsClusterNodeJVMMetrics.getFileDescriptorUsage(),
			"garbageCollectorMetrics",
			convertGenericsMapToJSONMap(
				lcsClusterNodeJVMMetrics.getGarbageCollectorMetrics()),
			"heapCommitted", lcsClusterNodeJVMMetrics.getHeapCommitted(),
			"heapInit", lcsClusterNodeJVMMetrics.getHeapInit(), "heapMax",
			lcsClusterNodeJVMMetrics.getHeapMax(), "heapUsage",
			lcsClusterNodeJVMMetrics.getHeapUsage(), "heapUsed",
			lcsClusterNodeJVMMetrics.getHeapUsed(), "memoryPoolUsage",
			lcsClusterNodeJVMMetrics.getMemoryPoolUsage(), "modifiedDate",
			lcsClusterNodeJVMMetrics.getModifiedDate(), "name",
			lcsClusterNodeJVMMetrics.getName(), "nonHeapUsage",
			lcsClusterNodeJVMMetrics.getNonHeapUsage(), "threadCount",
			lcsClusterNodeJVMMetrics.getThreadCount(), "threadStatePercentages",
			lcsClusterNodeJVMMetrics.getThreadStatePercentages(),
			"totalCommitted", lcsClusterNodeJVMMetrics.getTotalCommitted(),
			"totalInit", lcsClusterNodeJVMMetrics.getTotalInit(), "totalMax",
			lcsClusterNodeJVMMetrics.getTotalMax(), "totalUsed",
			lcsClusterNodeJVMMetrics.getTotalUsed(), "uptime",
			lcsClusterNodeJVMMetrics.getUptime(), "version",
			lcsClusterNodeJVMMetrics.getVersion());

		dmlStatementExecutor.addPrimaryKeyColumns("uuid", UUIDs.timeBased());

		dmlStatementExecutor.addPrimaryKeys(
			"key_", lcsClusterNodeJVMMetrics.getKey());

		dmlStatementExecutor.execute(session);

		return lcsClusterNodeJVMMetrics;
	}

	@Override
	protected String[] getColumnNames() {
		return _COLUMN_NAMES;
	}

	@Override
	protected LCSClusterNodeJVMMetrics getEntity(Row row) {
		LCSClusterNodeJVMMetrics lcsClusterNodeHibernateMetrics =
			new LCSClusterNodeJVMMetricsImpl();

		Map<String, Map<String, Long>> bufferPoolMetrics =
			convertJSONMapToGenericsMap(
				row.getMap("bufferPoolMetrics", String.class, String.class),
				Long.class);

		lcsClusterNodeHibernateMetrics.setBufferPoolMetrics(bufferPoolMetrics);

		lcsClusterNodeHibernateMetrics.setDaemonThreadCount(
			row.getInt("daemonThreadCount"));
		lcsClusterNodeHibernateMetrics.setDeadlockedThreads(
			row.getSet("deadlockedThreads", String.class));
		lcsClusterNodeHibernateMetrics.setFileDescriptorUsage(
			row.getDouble("fileDescriptorUsage"));

		Map<String, Map<String, Long>> garbageCollectorMetrics =
			convertJSONMapToGenericsMap(
				row.getMap(
					"garbageCollectorMetrics", String.class, String.class),
				Long.class);

		lcsClusterNodeHibernateMetrics.setGarbageCollectorMetrics(
			garbageCollectorMetrics);

		lcsClusterNodeHibernateMetrics.setHeapCommitted(
			row.getDouble("heapCommitted"));
		lcsClusterNodeHibernateMetrics.setHeapInit(row.getDouble("heapInit"));
		lcsClusterNodeHibernateMetrics.setHeapMax(row.getDouble("heapMax"));
		lcsClusterNodeHibernateMetrics.setHeapUsage(row.getDouble("heapUsage"));
		lcsClusterNodeHibernateMetrics.setHeapUsed(row.getDouble("heapUsed"));
		lcsClusterNodeHibernateMetrics.setKey(row.getString("key_"));
		lcsClusterNodeHibernateMetrics.setMemoryPoolUsage(
			row.getMap("memoryPoolUsage", String.class, Double.class));
		lcsClusterNodeHibernateMetrics.setModifiedDate(
			row.getTimestamp("modifiedDate"));
		lcsClusterNodeHibernateMetrics.setName(row.getString("name"));
		lcsClusterNodeHibernateMetrics.setNonHeapUsage(
			row.getDouble("nonHeapUsage"));
		lcsClusterNodeHibernateMetrics.setThreadCount(
			row.getInt("threadCount"));
		lcsClusterNodeHibernateMetrics.setThreadStatePercentages(
			row.getMap("threadStatePercentages", String.class, Double.class));
		lcsClusterNodeHibernateMetrics.setTotalCommitted(
			row.getDouble("totalCommitted"));
		lcsClusterNodeHibernateMetrics.setTotalInit(row.getDouble("totalInit"));
		lcsClusterNodeHibernateMetrics.setTotalMax(row.getDouble("totalMax"));
		lcsClusterNodeHibernateMetrics.setTotalUsed(row.getDouble("totalUsed"));
		lcsClusterNodeHibernateMetrics.setUptime(row.getInt("uptime"));
		lcsClusterNodeHibernateMetrics.setUUID(
			String.valueOf(row.getUUID("uuid")));
		lcsClusterNodeHibernateMetrics.setVersion(row.getString("version"));

		return lcsClusterNodeHibernateMetrics;
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
		"threadCount", "threadStatePercentages", "totalCommitted", "totalInit",
		"totalMax", "totalUsed", "uptime", "uuid", "version"
	};

	private static final String _TABLE_NAME = "LCSClusterNodeJVMMetrics";

}