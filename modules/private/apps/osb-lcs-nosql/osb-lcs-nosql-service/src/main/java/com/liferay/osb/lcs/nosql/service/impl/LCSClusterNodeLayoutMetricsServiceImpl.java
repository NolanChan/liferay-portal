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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeLayoutMetrics;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeLayoutMetricsService;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodeLayoutMetricsCounterPersistence;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodeLayoutMetricsPersistence;
import com.liferay.osb.lcs.nosql.util.comparator.LCSClusterNodeLayoutMetricsComparator;

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ivica Cardic
 */
@Component(immediate = true, service = LCSClusterNodeLayoutMetricsService.class)
public class LCSClusterNodeLayoutMetricsServiceImpl
	implements LCSClusterNodeLayoutMetricsService {

	@Override
	public LCSClusterNodeLayoutMetrics addLCSClusterNodeLayoutMetrics(
		int averageLoadTime, long companyId, String description, int frequency,
		long groupId, String key, Date modifiedDate, String name,
		String requestStatus, String requestURL) {

		LCSClusterNodeLayoutMetrics lcsClusterNodeLayoutMetrics =
			_lcsClusterNodeLayoutMetricsPersistence.create();

		lcsClusterNodeLayoutMetrics.setAverageLoadTime(averageLoadTime);
		lcsClusterNodeLayoutMetrics.setCompanyId(companyId);
		lcsClusterNodeLayoutMetrics.setFrequency(frequency);
		lcsClusterNodeLayoutMetrics.setGroupId(groupId);
		lcsClusterNodeLayoutMetrics.setKey(key);
		lcsClusterNodeLayoutMetrics.setMaxLoadTime(averageLoadTime);
		lcsClusterNodeLayoutMetrics.setMinLoadTime(averageLoadTime);
		lcsClusterNodeLayoutMetrics.setModifiedDate(modifiedDate);
		lcsClusterNodeLayoutMetrics.setName(name);

		lcsClusterNodeLayoutMetrics =
			_lcsClusterNodeLayoutMetricsPersistence.update(
				lcsClusterNodeLayoutMetrics);

		_lcsClusterNodeLayoutMetricsCounterPersistence.updateByC_G_K(0, 0, key);

		_lcsClusterNodeLayoutMetricsCounterPersistence.updateByC_G_K(
			companyId, groupId, key);

		return lcsClusterNodeLayoutMetrics;
	}

	@Override
	public LCSClusterNodeLayoutMetrics fetchLCSClusterNodeLayoutMetrics(
		long companyId, long groupId, String key, String name) {

		return _lcsClusterNodeLayoutMetricsPersistence.fetchByC_G_K_N(
			companyId, groupId, key, name);
	}

	@Override
	public LCSClusterNodeLayoutMetrics fetchLCSClusterNodeLayoutMetrics(
		long companyId, String key, String name) {

		return fetchLCSClusterNodeLayoutMetrics(companyId, 0, key, name);
	}

	@Override
	public int getLCSClusterNodeLayoutMetricsCount(
		long companyId, long groupId, String key) {

		return (int)_lcsClusterNodeLayoutMetricsCounterPersistence.countByC_G_K(
			companyId, groupId, key);
	}

	@Override
	public int getLCSClusterNodeLayoutMetricsCount(long companyId, String key) {
		return getLCSClusterNodeLayoutMetricsCount(companyId, 0, key);
	}

	@Override
	public List<LCSClusterNodeLayoutMetrics> getLCSClusterNodeLayoutMetricsList(
		long companyId, int end, long groupId, String key, String orderByCol,
		String orderByType, int start) {

		List<LCSClusterNodeLayoutMetrics> lcsClusterNodeLayoutMetricsList =
			null;

		if (companyId == 0) {
			lcsClusterNodeLayoutMetricsList =
				_lcsClusterNodeLayoutMetricsPersistence.findByKey(key);

			Iterator<LCSClusterNodeLayoutMetrics> iterator =
				lcsClusterNodeLayoutMetricsList.iterator();

			while (iterator.hasNext()) {
				LCSClusterNodeLayoutMetrics lcsClusterNodeLayoutMetrics =
					iterator.next();

				if (lcsClusterNodeLayoutMetrics.getGroupId() != groupId) {
					iterator.remove();
				}
			}
		}
		else {
			lcsClusterNodeLayoutMetricsList =
				_lcsClusterNodeLayoutMetricsPersistence.findByC_G_K(
					companyId, groupId, key);
		}

		if ((end == -1) || (start == -1)) {
			return lcsClusterNodeLayoutMetricsList;
		}

		if (end > lcsClusterNodeLayoutMetricsList.size()) {
			end = lcsClusterNodeLayoutMetricsList.size();
		}

		Collections.sort(
			lcsClusterNodeLayoutMetricsList,
			new LCSClusterNodeLayoutMetricsComparator(orderByCol, orderByType));

		try {
			return lcsClusterNodeLayoutMetricsList.subList(start, end);
		}
		catch (IndexOutOfBoundsException ioobe) {
			return Collections.emptyList();
		}
	}

	@Override
	public List<LCSClusterNodeLayoutMetrics> getLCSClusterNodeLayoutMetricsList(
		long companyId, int end, String key, String orderByCol,
		String orderByType, int start) {

		return getLCSClusterNodeLayoutMetricsList(
			companyId, end, 0, key, orderByCol, orderByType, start);
	}

	public void setLCSClusterNodeLayoutMetricsCounterPersistence(
		LCSClusterNodeLayoutMetricsCounterPersistence
			lcsClusterNodeLayoutMetricsCounterPersistence) {

		_lcsClusterNodeLayoutMetricsCounterPersistence =
			lcsClusterNodeLayoutMetricsCounterPersistence;
	}

	public void setLCSClusterNodeLayoutMetricsPersistence(
		LCSClusterNodeLayoutMetricsPersistence
			lcsClusterNodeLayoutMetricsPersistence) {

		_lcsClusterNodeLayoutMetricsPersistence =
			lcsClusterNodeLayoutMetricsPersistence;
	}

	@Override
	public LCSClusterNodeLayoutMetrics updateLCSClusterNodeLayoutMetrics(
		LCSClusterNodeLayoutMetrics lcsClusterNodeLayoutMetrics, int duration,
		int frequency, Date modifiedDate) {

		int averageLoadTime = lcsClusterNodeLayoutMetrics.getAverageLoadTime();
		long count = lcsClusterNodeLayoutMetrics.getCount() + 1;

		lcsClusterNodeLayoutMetrics.setAverageLoadTime(
			(int)(((averageLoadTime * count) + duration) / (count + 1)));
		lcsClusterNodeLayoutMetrics.setCount(count);

		lcsClusterNodeLayoutMetrics.setFrequency(
			lcsClusterNodeLayoutMetrics.getFrequency() + frequency);

		if (duration > lcsClusterNodeLayoutMetrics.getMaxLoadTime()) {
			lcsClusterNodeLayoutMetrics.setMaxLoadTime(duration);
		}

		if (duration < lcsClusterNodeLayoutMetrics.getMinLoadTime()) {
			lcsClusterNodeLayoutMetrics.setMinLoadTime(duration);
		}

		lcsClusterNodeLayoutMetrics.setModifiedDate(modifiedDate);

		return _lcsClusterNodeLayoutMetricsPersistence.update(
			lcsClusterNodeLayoutMetrics);
	}

	private LCSClusterNodeLayoutMetricsCounterPersistence
		_lcsClusterNodeLayoutMetricsCounterPersistence;
	private LCSClusterNodeLayoutMetricsPersistence
		_lcsClusterNodeLayoutMetricsPersistence;

}