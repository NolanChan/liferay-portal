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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeCurrentThreadsMetrics;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeCurrentThreadsMetricsService;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodeCurrentThreadsMetricsPersistence;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ivica Cardic
 */
@Component(
	immediate = true, service = LCSClusterNodeCurrentThreadsMetricsService.class
)
public class LCSClusterNodeCurrentThreadsMetricsServiceImpl
	implements LCSClusterNodeCurrentThreadsMetricsService {

	@Override
	public LCSClusterNodeCurrentThreadsMetrics
		addLCSClusterNodeCurrentThreadsMetrics(
			int currentThreadCount, int currentThreadsBusy, String key,
			Date modifiedDate, String name) {

		LCSClusterNodeCurrentThreadsMetrics
			lcsClusterNodeCurrentThreadsMetrics =
				_lcsClusterNodeCurrentThreadsMetricsPersistence.create();

		lcsClusterNodeCurrentThreadsMetrics.setCurrentThreadCount(
			currentThreadCount);
		lcsClusterNodeCurrentThreadsMetrics.setCurrentThreadsBusy(
			currentThreadsBusy);
		lcsClusterNodeCurrentThreadsMetrics.setKey(key);
		lcsClusterNodeCurrentThreadsMetrics.setModifiedDate(modifiedDate);
		lcsClusterNodeCurrentThreadsMetrics.setName(name);

		return _lcsClusterNodeCurrentThreadsMetricsPersistence.update(
			lcsClusterNodeCurrentThreadsMetrics);
	}

	@Override
	public LCSClusterNodeCurrentThreadsMetrics
		fetchLCSClusterNodeCurrentThreadsMetrics(String key, String name) {

		return _lcsClusterNodeCurrentThreadsMetricsPersistence.fetchByK_N(
			key, name);
	}

	@Override
	public List<LCSClusterNodeCurrentThreadsMetrics>
		getLCSClusterNodeCurrentThreadsMetricsList(String key) {

		List<LCSClusterNodeCurrentThreadsMetrics>
			lcsClusterNodeLiferayVMMetricsList =
				_lcsClusterNodeCurrentThreadsMetricsPersistence.findByKey(key);

		return lcsClusterNodeLiferayVMMetricsList;
	}

	public void setLCSClusterNodeCurrentThreadsMetricsPersistence(
		LCSClusterNodeCurrentThreadsMetricsPersistence
			lcsClusterNodeCurrentThreadsMetricsPersistence) {

		_lcsClusterNodeCurrentThreadsMetricsPersistence =
			lcsClusterNodeCurrentThreadsMetricsPersistence;
	}

	@Override
	public LCSClusterNodeCurrentThreadsMetrics
		updateLCSClusterNodeCurrentThreadsMetrics(
			LCSClusterNodeCurrentThreadsMetrics
				lcsClusterNodeCurrentThreadsMetrics, int currentThreadCount,
			int currentThreadsBusy, Date modifiedDate) {

		lcsClusterNodeCurrentThreadsMetrics.setCurrentThreadCount(
			currentThreadCount);
		lcsClusterNodeCurrentThreadsMetrics.setCurrentThreadsBusy(
			currentThreadsBusy);
		lcsClusterNodeCurrentThreadsMetrics.setModifiedDate(modifiedDate);

		return _lcsClusterNodeCurrentThreadsMetricsPersistence.update(
			lcsClusterNodeCurrentThreadsMetrics);
	}

	private LCSClusterNodeCurrentThreadsMetricsPersistence
		_lcsClusterNodeCurrentThreadsMetricsPersistence;

}