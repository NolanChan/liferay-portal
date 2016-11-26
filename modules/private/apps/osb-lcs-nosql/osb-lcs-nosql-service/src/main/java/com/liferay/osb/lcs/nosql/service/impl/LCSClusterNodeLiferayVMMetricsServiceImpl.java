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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeLiferayVMMetrics;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeLiferayVMMetricsService;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodeLiferayVMMetricsPersistence;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ivica Cardic
 */
@Component(
	immediate = true, service = LCSClusterNodeLiferayVMMetricsService.class
)
public class LCSClusterNodeLiferayVMMetricsServiceImpl
	implements LCSClusterNodeLiferayVMMetricsService {

	@Override
	public LCSClusterNodeLiferayVMMetrics addLCSClusterNodeLiferayVMMetrics(
		int cacheHits, int cacheMisses, String key, Date modifiedDate,
		String name, int objectCount, String type) {

		LCSClusterNodeLiferayVMMetrics lcsClusterNodeLiferayVMMetrics =
			_lcsClusterNodeLiferayVMMetricsPersistence.create();

		lcsClusterNodeLiferayVMMetrics.setCacheHits(cacheHits);
		lcsClusterNodeLiferayVMMetrics.setCacheMisses(cacheMisses);
		lcsClusterNodeLiferayVMMetrics.setKey(key);
		lcsClusterNodeLiferayVMMetrics.setModifiedDate(modifiedDate);
		lcsClusterNodeLiferayVMMetrics.setName(name);
		lcsClusterNodeLiferayVMMetrics.setObjectCount(objectCount);
		lcsClusterNodeLiferayVMMetrics.setType(type);

		return _lcsClusterNodeLiferayVMMetricsPersistence.update(
			lcsClusterNodeLiferayVMMetrics);
	}

	@Override
	public LCSClusterNodeLiferayVMMetrics fetchLCSClusterNodeLiferayVmMetrics(
		String key, String name, String type) {

		return _lcsClusterNodeLiferayVMMetricsPersistence.fetchByK_N_T(
			key, name, type);
	}

	@Override
	public List<LCSClusterNodeLiferayVMMetrics>
		getLCSClusterNodeLiferayVMMetricsList(String key, String type) {

		List<LCSClusterNodeLiferayVMMetrics>
			lcsClusterNodeLiferayVMMetricsList =
				_lcsClusterNodeLiferayVMMetricsPersistence.findByK_T(key, type);

		return lcsClusterNodeLiferayVMMetricsList;
	}

	public void setLCSClusterNodeLiferayVMMetricsPersistence(
		LCSClusterNodeLiferayVMMetricsPersistence
			lcsClusterNodeLiferayVMMetricsPersistence) {

		_lcsClusterNodeLiferayVMMetricsPersistence =
			lcsClusterNodeLiferayVMMetricsPersistence;
	}

	@Override
	public LCSClusterNodeLiferayVMMetrics updateLCSClusterNodeLiferayVMMetrics(
		LCSClusterNodeLiferayVMMetrics lcsClusterNodeLiferayVMMetrics,
		int cacheHits, int cacheMisses, Date modifiedDate, String name,
		int objectCount, String type) {

		lcsClusterNodeLiferayVMMetrics.setCacheHits(cacheHits);
		lcsClusterNodeLiferayVMMetrics.setCacheMisses(cacheMisses);
		lcsClusterNodeLiferayVMMetrics.setModifiedDate(modifiedDate);
		lcsClusterNodeLiferayVMMetrics.setName(name);
		lcsClusterNodeLiferayVMMetrics.setObjectCount(objectCount);
		lcsClusterNodeLiferayVMMetrics.setType(type);

		return _lcsClusterNodeLiferayVMMetricsPersistence.update(
			lcsClusterNodeLiferayVMMetrics);
	}

	private LCSClusterNodeLiferayVMMetricsPersistence
		_lcsClusterNodeLiferayVMMetricsPersistence;

}