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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeJDBCConnectionPoolMetrics;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeJDBCConnectionPoolMetricsService;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodeJDBCConnectionPoolMetricsPersistence;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ivica Cardic
 */
@Component(
	immediate = true,
	service = LCSClusterNodeJDBCConnectionPoolMetricsService.class
)
public class LCSClusterNodeJDBCConnectionPoolMetricsServiceImpl
	implements LCSClusterNodeJDBCConnectionPoolMetricsService {

	@Override
	public LCSClusterNodeJDBCConnectionPoolMetrics
		addLCSClusterNodeJDBCConnectionPoolMetrics(
			String key, Date modifiedDate, String name, int numActive,
			int numIdle) {

		LCSClusterNodeJDBCConnectionPoolMetrics
			lcsClusterNodeJDBCConnectionPoolMetrics =
				_lcsClusterNodeJDBCConnectionPoolMetricsPersistence.create();

		lcsClusterNodeJDBCConnectionPoolMetrics.setKey(key);
		lcsClusterNodeJDBCConnectionPoolMetrics.setModifiedDate(modifiedDate);
		lcsClusterNodeJDBCConnectionPoolMetrics.setName(name);
		lcsClusterNodeJDBCConnectionPoolMetrics.setNumActive(numActive);
		lcsClusterNodeJDBCConnectionPoolMetrics.setNumIdle(numIdle);

		return _lcsClusterNodeJDBCConnectionPoolMetricsPersistence.update(
			lcsClusterNodeJDBCConnectionPoolMetrics);
	}

	@Override
	public LCSClusterNodeJDBCConnectionPoolMetrics
		fetchLCSClusterNodeJDBCConnectionPoolMetrics(String key, String name) {

		return _lcsClusterNodeJDBCConnectionPoolMetricsPersistence.fetchByK_N(
			key, name);
	}

	@Override
	public List<LCSClusterNodeJDBCConnectionPoolMetrics>
		getLCSClusterNodeJDBCConnectionPoolMetricsList(String key) {

		List<LCSClusterNodeJDBCConnectionPoolMetrics>
			lcsClusterNodeJDBCConnectionPoolMetricsList =
				_lcsClusterNodeJDBCConnectionPoolMetricsPersistence.findByKey(
					key);

		return lcsClusterNodeJDBCConnectionPoolMetricsList;
	}

	public void setLCSClusterNodeJDBCConnectionPoolMetricsPersistence(
		LCSClusterNodeJDBCConnectionPoolMetricsPersistence
			lcsClusterNodeJDBCConnectionPoolMetricsPersistence) {

		_lcsClusterNodeJDBCConnectionPoolMetricsPersistence =
			lcsClusterNodeJDBCConnectionPoolMetricsPersistence;
	}

	@Override
	public LCSClusterNodeJDBCConnectionPoolMetrics
		updateLCSClusterNodeJDBCConnectionPoolMetrics(
			LCSClusterNodeJDBCConnectionPoolMetrics
				lcsClusterNodeJDBCConnectionPoolMetrics, Date modifiedDate,
			String name, int numActive, int numIdle) {

		lcsClusterNodeJDBCConnectionPoolMetrics.setModifiedDate(modifiedDate);
		lcsClusterNodeJDBCConnectionPoolMetrics.setNumActive(numActive);
		lcsClusterNodeJDBCConnectionPoolMetrics.setNumIdle(numIdle);

		return _lcsClusterNodeJDBCConnectionPoolMetricsPersistence.update(
			lcsClusterNodeJDBCConnectionPoolMetrics);
	}

	private LCSClusterNodeJDBCConnectionPoolMetricsPersistence
		_lcsClusterNodeJDBCConnectionPoolMetricsPersistence;

}