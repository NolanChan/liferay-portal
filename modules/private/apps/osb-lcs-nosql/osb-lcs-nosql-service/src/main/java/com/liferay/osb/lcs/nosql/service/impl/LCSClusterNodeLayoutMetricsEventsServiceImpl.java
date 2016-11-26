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

import com.liferay.osb.lcs.nosql.model.LCSClusterNodeLayoutMetricsEvents;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeLayoutMetricsEventsService;
import com.liferay.osb.lcs.nosql.service.persistence.LCSClusterNodeLayoutMetricsEventsPersistence;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Ferrari
 */
@Component(
	immediate = true, service = LCSClusterNodeLayoutMetricsEventsService.class
)
public class LCSClusterNodeLayoutMetricsEventsServiceImpl
	implements LCSClusterNodeLayoutMetricsEventsService {

	@Override
	public LCSClusterNodeLayoutMetricsEvents
		addLCSClusterNodeLayoutMetricsEvents(
			int averageLoadTime, long companyId, int frequency, long groupId,
			String key, Date modifiedDate, String name, String referer,
			String remoteAddr, String requestStatus, String requestURL,
			int statusCode, String userAgent, long userId) {

		LCSClusterNodeLayoutMetricsEvents lcsClusterNodeLayoutMetricsEvents =
			_lcsClusterNodeLayoutMetricsEventsPersistence.create();

		lcsClusterNodeLayoutMetricsEvents.setAverageLoadTime(averageLoadTime);
		lcsClusterNodeLayoutMetricsEvents.setCompanyId(companyId);
		lcsClusterNodeLayoutMetricsEvents.setFrequency(frequency);
		lcsClusterNodeLayoutMetricsEvents.setGroupId(groupId);
		lcsClusterNodeLayoutMetricsEvents.setKey(key);
		lcsClusterNodeLayoutMetricsEvents.setModifiedDate(modifiedDate);
		lcsClusterNodeLayoutMetricsEvents.setName(name);
		lcsClusterNodeLayoutMetricsEvents.setPartitionKey(
			getPartitionKey(modifiedDate));
		lcsClusterNodeLayoutMetricsEvents.setReferer(referer);
		lcsClusterNodeLayoutMetricsEvents.setRemoteAddr(remoteAddr);
		lcsClusterNodeLayoutMetricsEvents.setRequestStatus(requestStatus);
		lcsClusterNodeLayoutMetricsEvents.setRequestURL(requestURL);
		lcsClusterNodeLayoutMetricsEvents.setStatusCode(statusCode);
		lcsClusterNodeLayoutMetricsEvents.setUserAgent(userAgent);
		lcsClusterNodeLayoutMetricsEvents.setUserId(userId);

		lcsClusterNodeLayoutMetricsEvents =
			_lcsClusterNodeLayoutMetricsEventsPersistence.update(
				lcsClusterNodeLayoutMetricsEvents);

		return lcsClusterNodeLayoutMetricsEvents;
	}

	public void setLCSClusterNodeLayoutMetricsEventsPersistence(
		LCSClusterNodeLayoutMetricsEventsPersistence
			lcsClusterNodeLayoutMetricsEventsPersistence) {

		_lcsClusterNodeLayoutMetricsEventsPersistence =
			lcsClusterNodeLayoutMetricsEventsPersistence;
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

	private LCSClusterNodeLayoutMetricsEventsPersistence
		_lcsClusterNodeLayoutMetricsEventsPersistence;

}