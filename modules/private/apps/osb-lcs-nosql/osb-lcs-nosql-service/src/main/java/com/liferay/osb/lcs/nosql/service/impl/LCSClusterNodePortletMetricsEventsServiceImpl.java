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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodePortletMetricsEvents;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodePortletMetricsEventsService;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodePortletMetricsEventsPersistence;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Ferrari
 */
@Component(
	immediate = true, service = LCSClusterNodePortletMetricsEventsService.class
)
public class LCSClusterNodePortletMetricsEventsServiceImpl
	implements LCSClusterNodePortletMetricsEventsService {

	@Override
	public LCSClusterNodePortletMetricsEvents
		addLCSClusterNodePortletMetricsEvents(
			int averageLoadTime, long companyId, String displayName,
			int frequency, long groupId, String key, String layoutName,
			Date modifiedDate, String name, String portletId,
			String requestType) {

		LCSClusterNodePortletMetricsEvents lcsClusterNodePortletMetricsEvents =
			_lcsClusterNodePortletMetricsEventsPersistence.create();

		lcsClusterNodePortletMetricsEvents.setAverageLoadTime(averageLoadTime);
		lcsClusterNodePortletMetricsEvents.setCompanyId(companyId);
		lcsClusterNodePortletMetricsEvents.setDisplayName(displayName);
		lcsClusterNodePortletMetricsEvents.setFrequency(frequency);
		lcsClusterNodePortletMetricsEvents.setGroupId(groupId);
		lcsClusterNodePortletMetricsEvents.setKey(key);
		lcsClusterNodePortletMetricsEvents.setLayoutName(layoutName);
		lcsClusterNodePortletMetricsEvents.setModifiedDate(modifiedDate);
		lcsClusterNodePortletMetricsEvents.setName(name);
		lcsClusterNodePortletMetricsEvents.setPartitionKey(
			getPartitionKey(modifiedDate));
		lcsClusterNodePortletMetricsEvents.setPortletId(portletId);
		lcsClusterNodePortletMetricsEvents.setRequestType(requestType);

		lcsClusterNodePortletMetricsEvents =
			_lcsClusterNodePortletMetricsEventsPersistence.update(
				lcsClusterNodePortletMetricsEvents);

		return lcsClusterNodePortletMetricsEvents;
	}

	public void setLCSClusterNodePortletMetricsEventsPersistence(
		LCSClusterNodePortletMetricsEventsPersistence
			lcsClusterNodePortletMetricsEventsPersistence) {

		_lcsClusterNodePortletMetricsEventsPersistence =
			lcsClusterNodePortletMetricsEventsPersistence;
	}

	protected String getPartitionKey(Date modifiedDate) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-");

		String partitionKey = dateFormat.format(modifiedDate);

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(modifiedDate);

		int minutes = calendar.get(Calendar.MINUTE);

		int quarter = minutes / 15;

		partitionKey = partitionKey.concat(String.valueOf(quarter));

		return partitionKey;
	}

	private LCSClusterNodePortletMetricsEventsPersistence
		_lcsClusterNodePortletMetricsEventsPersistence;

}