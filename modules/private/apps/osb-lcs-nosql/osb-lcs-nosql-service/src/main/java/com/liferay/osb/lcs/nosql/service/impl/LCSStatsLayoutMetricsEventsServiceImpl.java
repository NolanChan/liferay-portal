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

import com.liferay.osb.lcs.nosql.model.LCSStatsLayoutMetricsEvents;
import com.liferay.osb.lcs.nosql.service.LCSStatsLayoutMetricsEventsService;
import com.liferay.osb.lcs.nosql.service.persistence.LCSStatsLayoutMetricsEventsPersistence;
import com.liferay.osb.lcs.nosql.service.persistence.LCSStatsLayoutMetricsSummaryEventsPersistence;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Ferrari
 */
@Component(immediate = true, service = LCSStatsLayoutMetricsEventsService.class)
public class LCSStatsLayoutMetricsEventsServiceImpl
	implements LCSStatsLayoutMetricsEventsService {

	@Override
	public List<? extends LCSStatsLayoutMetricsEvents>
		getLCSStatsLayoutMetricsEventsList(
			long companyId, Date endDate, long groupId, String key,
			String portletId) {

		return _lcsStatsLayoutMetricsEventsPersistence.fetchByC_E_G_K_L(
			companyId, endDate, groupId, key, portletId);
	}

	@Override
	public List<? extends LCSStatsLayoutMetricsEvents>
		getSummaryLCSStatsLayoutMetricsEventsList(
			long companyId, Date endDate, long groupId, String key) {

		return _lcsStatsLayoutMetricsSummaryEventsPersistence.fetchByC_E_G_K(
			companyId, endDate, groupId, key);
	}

	public void setLCSStatsLayoutMetricsEventsPersistence(
		LCSStatsLayoutMetricsEventsPersistence
			lcsStatsLayoutMetricsEventsPersistence) {

		_lcsStatsLayoutMetricsEventsPersistence =
			lcsStatsLayoutMetricsEventsPersistence;
	}

	public void setLCSStatsLayoutMetricsSummaryEventsPersistence(
		LCSStatsLayoutMetricsSummaryEventsPersistence
			lcsStatsLayoutMetricsSummaryEventsPersistence) {

		_lcsStatsLayoutMetricsSummaryEventsPersistence =
			lcsStatsLayoutMetricsSummaryEventsPersistence;
	}

	private LCSStatsLayoutMetricsEventsPersistence
		_lcsStatsLayoutMetricsEventsPersistence;
	private LCSStatsLayoutMetricsSummaryEventsPersistence
		_lcsStatsLayoutMetricsSummaryEventsPersistence;

}