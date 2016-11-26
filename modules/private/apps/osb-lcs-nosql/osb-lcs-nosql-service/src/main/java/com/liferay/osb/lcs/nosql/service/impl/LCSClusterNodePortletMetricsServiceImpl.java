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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeDetails;
import com.liferay.osb.lcs.nosql.model.LCSClusterNodePortletMetrics;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodePortletMetricsService;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodeDetailsPersistence;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodePortletMetricsCounterPersistence;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodePortletMetricsPersistence;
import com.liferay.osb.lcs.nosql.util.comparator.LCSClusterNodePortletMetricsComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ivica Cardic
 */
@Component(
	immediate = true, service = LCSClusterNodePortletMetricsService.class
)
public class LCSClusterNodePortletMetricsServiceImpl
	implements LCSClusterNodePortletMetricsService {

	@Override
	public LCSClusterNodePortletMetrics addLCSClusterNodePortletMetrics(
		int averageLoadTime, long companyId, String displayName, int frequency,
		long groupId, String key, String layoutName, Date modifiedDate,
		String name, String portletId, String requestType) {

		LCSClusterNodePortletMetrics lcsClusterNodePortletMetrics =
			_lcsClusterNodePortletMetricsPersistence.create();

		lcsClusterNodePortletMetrics.setAverageLoadTime(averageLoadTime);
		lcsClusterNodePortletMetrics.setCompanyId(companyId);
		lcsClusterNodePortletMetrics.setDisplayName(displayName);
		lcsClusterNodePortletMetrics.setFrequency(frequency);
		lcsClusterNodePortletMetrics.setGroupId(groupId);
		lcsClusterNodePortletMetrics.setKey(key);
		lcsClusterNodePortletMetrics.setLayoutName(layoutName);
		lcsClusterNodePortletMetrics.setMaxLoadTime(averageLoadTime);
		lcsClusterNodePortletMetrics.setMinLoadTime(averageLoadTime);
		lcsClusterNodePortletMetrics.setModifiedDate(modifiedDate);
		lcsClusterNodePortletMetrics.setName(name);
		lcsClusterNodePortletMetrics.setPortletId(portletId);
		lcsClusterNodePortletMetrics.setRequestType(requestType);

		lcsClusterNodePortletMetrics =
			_lcsClusterNodePortletMetricsPersistence.update(
				lcsClusterNodePortletMetrics);

		_lcsClusterNodePortletMetricsCounterPersistence.updateByC_G_K_LN_RT(
			0, 0, key, layoutName, requestType);

		_lcsClusterNodePortletMetricsCounterPersistence.updateByC_G_K_LN_RT(
			companyId, groupId, key, layoutName, requestType);

		return lcsClusterNodePortletMetrics;
	}

	@Override
	public LCSClusterNodePortletMetrics fetchLCSClusterNodePortletMetrics(
		long companyId, long groupId, String key, String layoutName,
		String portletId, String requestType) {

		return _lcsClusterNodePortletMetricsPersistence.fetchByC_G_K_LN_PI_RT(
			companyId, groupId, key, layoutName, portletId, requestType);
	}

	@Override
	public LCSClusterNodePortletMetrics fetchLCSClusterNodePortletMetrics(
		long companyId, String key, String layoutName, String portletId,
		String requestType) {

		return fetchLCSClusterNodePortletMetrics(
			companyId, 0, key, layoutName, portletId, requestType);
	}

	@Override
	public int getLCSClusterNodePortletMetricsCount(
		long companyId, long groupId, String key, String layoutName,
		String requestType) {

		return (int)_lcsClusterNodePortletMetricsCounterPersistence.
			countByC_G_K_LN_RT(
				companyId, groupId, key, layoutName, requestType);
	}

	@Override
	public int getLCSClusterNodePortletMetricsCount(
		long companyId, String key, String layoutName, String requestType) {

		return getLCSClusterNodePortletMetricsCount(
			companyId, 0, key, layoutName, requestType);
	}

	@Override
	public List<LCSClusterNodePortletMetrics>
		getLCSClusterNodePortletMetricsList(
			long companyId, int end, long groupId, String key,
			String layoutName, String orderByCol, String orderByType,
			String requestType, int start) {

		List<LCSClusterNodePortletMetrics> lcsClusterNodePortletMetricsList =
			null;

		if (companyId == 0) {
			lcsClusterNodePortletMetricsList = new ArrayList<>();

			LCSClusterNodeDetails lcsClusterNodeDetails =
				_lcsClusterNodeDetailsPersistence.fetchByKey(key);

			Map<Integer, String> companyIdsWebIds =
				lcsClusterNodeDetails.getCompanyIdsWebIds();

			for (Integer companyIdKey : companyIdsWebIds.keySet()) {
				lcsClusterNodePortletMetricsList.addAll(
					_lcsClusterNodePortletMetricsPersistence.findByC_G_K_LN_RT(
						companyIdKey, 0, key, layoutName, requestType));
			}
		}
		else {
			lcsClusterNodePortletMetricsList =
				_lcsClusterNodePortletMetricsPersistence.findByC_G_K_LN_RT(
					companyId, groupId, key, layoutName, requestType);
		}

		if ((end == -1) || (start == -1)) {
			return lcsClusterNodePortletMetricsList;
		}

		if (end > lcsClusterNodePortletMetricsList.size()) {
			end = lcsClusterNodePortletMetricsList.size();
		}

		Collections.sort(
			lcsClusterNodePortletMetricsList,
			new LCSClusterNodePortletMetricsComparator(
				orderByCol, orderByType));

		try {
			return lcsClusterNodePortletMetricsList.subList(start, end);
		}
		catch (IndexOutOfBoundsException ioobe) {
			return Collections.emptyList();
		}
	}

	@Override
	public List<LCSClusterNodePortletMetrics>
		getLCSClusterNodePortletMetricsList(
			long companyId, int end, String key, String layoutName,
			String orderByCol, String orderByType, String requestType,
			int start) {

		return getLCSClusterNodePortletMetricsList(
			companyId, end, 0, key, layoutName, orderByCol, orderByType,
			requestType, start);
	}

	public void setLCSClusterNodeDetailsPersistence(
		LCSClusterNodeDetailsPersistence lcsClusterNodeDetailsPersistence) {

		_lcsClusterNodeDetailsPersistence = lcsClusterNodeDetailsPersistence;
	}

	public void setLCSClusterNodePortletMetricsCounterPersistence(
		LCSClusterNodePortletMetricsCounterPersistence
			lcsClusterNodePortletMetricsCounterPersistence) {

		_lcsClusterNodePortletMetricsCounterPersistence =
			lcsClusterNodePortletMetricsCounterPersistence;
	}

	public void setLCSClusterNodePortletMetricsPersistence(
		LCSClusterNodePortletMetricsPersistence
			lcsClusterNodePortletMetricsPersistence) {

		_lcsClusterNodePortletMetricsPersistence =
			lcsClusterNodePortletMetricsPersistence;
	}

	@Override
	public LCSClusterNodePortletMetrics updateLCSClusterNodePortletMetrics(
		LCSClusterNodePortletMetrics lcsClusterNodePortletMetrics, int duration,
		int frequency, Date modifiedDate) {

		int averageLoadTime = lcsClusterNodePortletMetrics.getAverageLoadTime();
		long count = lcsClusterNodePortletMetrics.getCount() + 1;

		lcsClusterNodePortletMetrics.setAverageLoadTime(
			(int)(((averageLoadTime * count) + duration) / (count + 1)));
		lcsClusterNodePortletMetrics.setCount(count);

		lcsClusterNodePortletMetrics.setFrequency(
			lcsClusterNodePortletMetrics.getFrequency() + frequency);

		if (duration > lcsClusterNodePortletMetrics.getMaxLoadTime()) {
			lcsClusterNodePortletMetrics.setMaxLoadTime(duration);
		}

		if (duration < lcsClusterNodePortletMetrics.getMinLoadTime()) {
			lcsClusterNodePortletMetrics.setMinLoadTime(duration);
		}

		lcsClusterNodePortletMetrics.setModifiedDate(modifiedDate);

		return _lcsClusterNodePortletMetricsPersistence.update(
			lcsClusterNodePortletMetrics);
	}

	private LCSClusterNodeDetailsPersistence _lcsClusterNodeDetailsPersistence;
	private LCSClusterNodePortletMetricsCounterPersistence
		_lcsClusterNodePortletMetricsCounterPersistence;
	private LCSClusterNodePortletMetricsPersistence
		_lcsClusterNodePortletMetricsPersistence;

}