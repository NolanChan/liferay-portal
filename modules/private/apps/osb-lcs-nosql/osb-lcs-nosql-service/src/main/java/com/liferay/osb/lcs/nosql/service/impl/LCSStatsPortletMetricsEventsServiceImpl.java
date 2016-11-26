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

import com.liferay.osb.lcs.nosql.model.LCSStatsPortletMetricsEvents;
import com.liferay.osb.lcs.nosql.service.LCSStatsPortletMetricsEventsService;
import com.liferay.osb.lcs.nosql.service.persistence.LCSStatsPortletMetricsEventsPersistence;
import com.liferay.osb.lcs.nosql.service.persistence.LCSStatsPortletMetricsSummaryEventsPersistence;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Ferrari
 */
@Component(
	immediate = true, service = LCSStatsPortletMetricsEventsService.class
)
public class LCSStatsPortletMetricsEventsServiceImpl
	implements LCSStatsPortletMetricsEventsService {

	@Override
	public List<? extends LCSStatsPortletMetricsEvents>
		getLCSStatsPortletMetricsEventsList(
			long companyId, Date endDate, long groupId, String key,
			String portletId) {

		return _lcsStatsPortletMetricsEventsPersistence.fetchByC_E_G_K_P(
			companyId, endDate, groupId, key, portletId);
	}

	@Override
	public List<? extends LCSStatsPortletMetricsEvents>
		getSummaryLCSStatsPortletMetricsEventsList(
			long companyId, Date endDate, long groupId, String key) {

		return _lcsStatsPortletMetricsSummaryEventsPersistence.fetchByC_E_G_K(
			companyId, endDate, groupId, key);
	}

	public void setLCSStatsPortletMetricsEventsPersistence(
		LCSStatsPortletMetricsEventsPersistence
			lcsStatsPortletMetricsEventsPersistence) {

		_lcsStatsPortletMetricsEventsPersistence =
			lcsStatsPortletMetricsEventsPersistence;
	}

	public void setLCSStatsPortletMetricsSummaryEventsPersistence(
		LCSStatsPortletMetricsSummaryEventsPersistence
			lcsStatsPortletMetricsSummaryEventsPersistence) {

		_lcsStatsPortletMetricsSummaryEventsPersistence =
			lcsStatsPortletMetricsSummaryEventsPersistence;
	}

	private LCSStatsPortletMetricsEventsPersistence
		_lcsStatsPortletMetricsEventsPersistence;
	private LCSStatsPortletMetricsSummaryEventsPersistence
		_lcsStatsPortletMetricsSummaryEventsPersistence;

}