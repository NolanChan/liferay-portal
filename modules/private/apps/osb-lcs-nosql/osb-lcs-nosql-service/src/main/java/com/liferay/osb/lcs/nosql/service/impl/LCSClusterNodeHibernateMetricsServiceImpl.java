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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeHibernateMetrics;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeHibernateMetricsService;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodeHibernateMetricsPersistence;

import java.util.Date;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ivica Cardic
 */
@Component(
	immediate = true, service = LCSClusterNodeHibernateMetricsService.class
)
public class LCSClusterNodeHibernateMetricsServiceImpl
	implements LCSClusterNodeHibernateMetricsService {

	@Override
	public LCSClusterNodeHibernateMetrics addLCSClusterNodeHibernateMetrics(
		String key, Date modifiedDate, int queryCacheHitCount,
		int queryCacheMissCount, int queryExecutionCount,
		int queryExecutionMaxTime) {

		LCSClusterNodeHibernateMetrics lcsClusterNodeHibernateMetrics =
			_lcsClusterNodeHibernateMetricsPersistence.create();

		lcsClusterNodeHibernateMetrics.setKey(key);
		lcsClusterNodeHibernateMetrics.setModifiedDate(modifiedDate);
		lcsClusterNodeHibernateMetrics.setQueryCacheHitCount(
			queryCacheHitCount);
		lcsClusterNodeHibernateMetrics.setQueryCacheMissCount(
			queryCacheMissCount);
		lcsClusterNodeHibernateMetrics.setQueryExecutionCount(
			queryExecutionCount);
		lcsClusterNodeHibernateMetrics.setQueryExecutionMaxTime(
			queryExecutionMaxTime);

		return _lcsClusterNodeHibernateMetricsPersistence.update(
			lcsClusterNodeHibernateMetrics);
	}

	@Override
	public LCSClusterNodeHibernateMetrics fetchLCSClusterNodeHibernateMetrics(
		String key) {

		LCSClusterNodeHibernateMetrics lcsClusterNodeHibernateMetrics =
			_lcsClusterNodeHibernateMetricsPersistence.fetchByKey(key);

		return lcsClusterNodeHibernateMetrics;
	}

	public void setLCSClusterNodeHibernateMetricsPersistence(
		LCSClusterNodeHibernateMetricsPersistence
			lcsClusterNodeHibernateMetricsPersistence) {

		_lcsClusterNodeHibernateMetricsPersistence =
			lcsClusterNodeHibernateMetricsPersistence;
	}

	@Override
	public LCSClusterNodeHibernateMetrics updateLCSClusterNodeHibernateMetrics(
		LCSClusterNodeHibernateMetrics lcsClusterNodeHibernateMetrics,
		Date modifiedDate, int queryCacheHitCount, int queryCacheMissCount,
		int queryExecutionCount, int queryExecutionMaxTime) {

		lcsClusterNodeHibernateMetrics.setModifiedDate(modifiedDate);
		lcsClusterNodeHibernateMetrics.setQueryCacheHitCount(
			queryCacheHitCount);
		lcsClusterNodeHibernateMetrics.setQueryCacheMissCount(
			queryCacheMissCount);
		lcsClusterNodeHibernateMetrics.setQueryExecutionCount(
			queryExecutionCount);
		lcsClusterNodeHibernateMetrics.setQueryExecutionMaxTime(
			queryExecutionMaxTime);

		return _lcsClusterNodeHibernateMetricsPersistence.update(
			lcsClusterNodeHibernateMetrics);
	}

	private LCSClusterNodeHibernateMetricsPersistence
		_lcsClusterNodeHibernateMetricsPersistence;

}