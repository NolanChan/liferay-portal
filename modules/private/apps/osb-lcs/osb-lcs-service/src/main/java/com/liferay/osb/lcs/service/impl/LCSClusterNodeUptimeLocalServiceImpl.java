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

package com.liferay.osb.lcs.service.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.lcs.subscription.SubscriptionType;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.LCSClusterNodeUptime;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.model.impl.LCSClusterNodeUptimeImpl;
import com.liferay.osb.lcs.service.base.LCSClusterNodeUptimeLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.CalendarUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author Igor Beslic
 * @see    LCSClusterNodeUptimeLocalServiceBaseImpl
 * @see    com.liferay.osb.lcs.service.LCSClusterNodeUptimeLocalServiceUtil
 */
@ProviderType
public class LCSClusterNodeUptimeLocalServiceImpl
	extends LCSClusterNodeUptimeLocalServiceBaseImpl {

	@Override
	public List<LCSClusterNodeUptime> getLCSClusterNodeUptimes(
			int start, int end, boolean details)
		throws PortalException {

		List<LCSClusterNodeUptime> lcsClusterNodeUptimes =
			lcsClusterNodeUptimePersistence.findAll(start, end);

		if (details) {
			lcsClusterNodeUptimes = getLCSClusterNodeUptimes(
				lcsClusterNodeUptimes);
		}

		return lcsClusterNodeUptimes;
	}

	@Override
	public double getMonthlyElasticLCSClusterNodeUptimeTotal(
			long lcsProjectId, int month, int year)
		throws PortalException {

		double total = 0;

		List<LCSClusterNodeUptime> lcsClusterNodeUptimes =
			getMonthlyLCSClusterNodeUptimes(
				LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID,
				LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID, lcsProjectId, month,
				year, month, year, true, SubscriptionType.UNDEFINED);

		for (LCSClusterNodeUptime lcsClusterNodeUptime :
				lcsClusterNodeUptimes) {

			total += lcsClusterNodeUptime.getAmount();
		}

		return total;
	}

	@Override
	public Map<Date, Double> getMonthlyElasticLCSClusterNodeUptimeTotalMap(
			long lcsProjectId, int range)
		throws PortalException {

		Map<Date, Double> monthlyLCSClusterNodeUptimeTotalMap =
			new LinkedHashMap<>();

		Calendar calendar = Calendar.getInstance();

		calendar.setTimeZone(TimeZone.getTimeZone("GMT"));

		Date endDate = calendar.getTime();

		calendar.set(
			calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) - 5, 1, 0,
			0, 0);

		if (range == _DATE_RANGE_CURRENT_YEAR) {
			calendar.set(Calendar.MONTH, Calendar.JANUARY);
		}

		do {
			double total = getMonthlyElasticLCSClusterNodeUptimeTotal(
				lcsProjectId, calendar.get(Calendar.MONTH),
				calendar.get(Calendar.YEAR));

			monthlyLCSClusterNodeUptimeTotalMap.put(calendar.getTime(), total);

			calendar.add(Calendar.MONTH, 1);
		}
		while (calendar.getTimeInMillis() < endDate.getTime());

		return monthlyLCSClusterNodeUptimeTotalMap;
	}

	@Override
	public Map<Date, Double> getMonthlyElasticLCSClusterNodeUptimeTotalMap(
			long lcsProjectId, int startMonth, int startYear, int endMonth,
			int endYear)
		throws PortalException {

		Map<Date, Double> monthlyLCSClusterNodeUptimeTotalMap =
			new LinkedHashMap<>();

		Calendar calendar = Calendar.getInstance();

		calendar.setTimeZone(TimeZone.getTimeZone("GMT"));

		calendar.set(endYear, endMonth, 1, 0, 0, 0);

		Date endDate = calendar.getTime();

		calendar.set(startYear, startMonth, 1, 0, 0, 0);

		do {
			double total = getMonthlyElasticLCSClusterNodeUptimeTotal(
				lcsProjectId, calendar.get(Calendar.MONTH),
				calendar.get(Calendar.YEAR));

			monthlyLCSClusterNodeUptimeTotalMap.put(calendar.getTime(), total);

			calendar.add(Calendar.MONTH, 1);
		}
		while (calendar.getTimeInMillis() <= endDate.getTime());

		return monthlyLCSClusterNodeUptimeTotalMap;
	}

	@Override
	public List<LCSClusterNodeUptime>
		getMonthlyElasticTotalLCSClusterNodeUptimes(
			long lcsProjectId, int month, int year)
		throws PortalException {

		List<LCSClusterNodeUptime> totalLCSClusterNodeUptimes =
			new ArrayList<>();

		if (lcsProjectId == LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID) {
			return totalLCSClusterNodeUptimes;
		}

		Map<Long, Double[]> lcsClusterNodeIdTotalArrayMap = new HashMap<>();

		List<LCSClusterNodeUptime> lcsClusterNodeUptimes =
			getMonthlyLCSClusterNodeUptimes(
				LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID,
				LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID, lcsProjectId, month,
				year, month, year, true, SubscriptionType.UNDEFINED);

		for (LCSClusterNodeUptime lcsClusterNodeUptime :
				lcsClusterNodeUptimes) {

			long lcsClusterNodeId = lcsClusterNodeUptime.getLcsClusterNodeId();

			Double[] totalArray = lcsClusterNodeIdTotalArrayMap.get(
				lcsClusterNodeId);

			if (totalArray == null) {
				totalArray = new Double[] {0.0, 0.0};

				lcsClusterNodeIdTotalArrayMap.put(lcsClusterNodeId, totalArray);
			}

			totalArray[0] += lcsClusterNodeUptime.getAmount();
			totalArray[1] += lcsClusterNodeUptime.getUptime();
		}

		for (Map.Entry<Long, Double[]> entry :
				lcsClusterNodeIdTotalArrayMap.entrySet()) {

			long lcsClusterNodeId = entry.getKey();

			LCSClusterNode lcsClusterNode =
				lcsClusterNodePersistence.findByPrimaryKey(lcsClusterNodeId);

			long lcsClusterEntryId = lcsClusterNode.getLcsClusterEntryId();

			LCSClusterEntry lcsClusterEntry =
				lcsClusterEntryPersistence.findByPrimaryKey(lcsClusterEntryId);

			LCSClusterNodeUptime lcsClusterNodeUptime =
				new LCSClusterNodeUptimeImpl();

			lcsClusterNodeUptime.setAmount(entry.getValue()[0]);
			lcsClusterNodeUptime.setLcsClusterEntryId(
				lcsClusterEntry.getLcsClusterEntryId());
			lcsClusterNodeUptime.setLcsClusterEntryName(
				lcsClusterEntry.getName());
			lcsClusterNodeUptime.setLcsClusterNodeId(lcsClusterNodeId);
			lcsClusterNodeUptime.setLcsClusterNodeName(
				lcsClusterNode.getName());
			lcsClusterNodeUptime.setUptime(entry.getValue()[1]);

			totalLCSClusterNodeUptimes.add(lcsClusterNodeUptime);
		}

		return totalLCSClusterNodeUptimes;
	}

	@Override
	public List<LCSClusterNodeUptime> getMonthlyLCSClusterNodeUptimes(
			long lcsClusterEntryId, long lcsClusterNodeId, long lcsProjectId,
			int month, int year, boolean details, boolean elastic,
			SubscriptionType subscriptionType)
		throws PortalException {

		List<LCSClusterNodeUptime> lcsClusterNodeUptimes =
			getMonthlyLCSClusterNodeUptimes(
				lcsClusterEntryId, lcsClusterNodeId, lcsProjectId, month, year,
				month, year, elastic, subscriptionType);

		if (details) {
			lcsClusterNodeUptimes = getLCSClusterNodeUptimes(
				lcsClusterNodeUptimes);
		}

		return lcsClusterNodeUptimes;
	}

	@Override
	public List<LCSClusterNodeUptime> getMonthlyLCSClusterNodeUptimes(
			long lcsClusterEntryId, long lcsClusterNodeId, long lcsProjectId,
			int startMonth, int startYear, int endMonth, int endYear,
			boolean elastic, SubscriptionType subscriptionType)
		throws PortalException {

		if (CalendarUtil.isFuture(startMonth, startYear) ||
			CalendarUtil.isFuture(endMonth, endYear)) {

			return Collections.emptyList();
		}

		List<LCSClusterNodeUptime> lcsClusterNodeUptimes = new ArrayList<>();

		List<LCSClusterNodeUptime> curLCSClusterNodeUptimes = new ArrayList<>();

		if (lcsClusterNodeId != LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID) {
			curLCSClusterNodeUptimes =
				lcsClusterNodeUptimePersistence.findByLCSClusterNodeId(
					lcsClusterNodeId);
		}
		else if (lcsClusterEntryId != LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID) {
			if (elastic) {
				LCSClusterEntry lcsClusterEntry =
					lcsClusterEntryPersistence.findByPrimaryKey(
						lcsClusterEntryId);

				if (!lcsClusterEntry.isElastic()) {
					return Collections.emptyList();
				}
			}

			List<LCSClusterNode> lcsClusterNodes =
				lcsClusterNodePersistence.findByLCSClusterEntryId(
					lcsClusterEntryId);

			if (lcsClusterNodes.isEmpty()) {
				return Collections.emptyList();
			}

			long[] lcsClusterNodeIds = new long[lcsClusterNodes.size()];

			for (int i = 0; i < lcsClusterNodes.size(); i++) {
				LCSClusterNode lcsClusterNode = lcsClusterNodes.get(i);

				lcsClusterNodeIds[i] = lcsClusterNode.getLcsClusterNodeId();
			}

			curLCSClusterNodeUptimes =
				lcsClusterNodeUptimePersistence.findByLCSClusterNodeId(
					lcsClusterNodeIds);
		}
		else if (lcsProjectId != LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID) {
			List<LCSClusterEntry> lcsClusterEntries = new ArrayList<>();

			if (subscriptionType == SubscriptionType.UNDEFINED) {
				lcsClusterEntries.addAll(
					lcsClusterEntryPersistence.findByLCSProjectId(
						lcsProjectId));
			}
			else {
				lcsClusterEntries.addAll(
					lcsClusterEntryPersistence.findByLPI_ST(
						lcsProjectId, subscriptionType.name()));
			}

			if (elastic) {
				Iterator<LCSClusterEntry> lcsClusterEntryIterator =
					lcsClusterEntries.iterator();

				while (lcsClusterEntryIterator.hasNext()) {
					LCSClusterEntry lcsClusterEntry =
						lcsClusterEntryIterator.next();

					if (!lcsClusterEntry.isElastic()) {
						lcsClusterEntryIterator.remove();
					}
				}
			}

			if (lcsClusterEntries.isEmpty()) {
				return Collections.emptyList();
			}

			long[] lcsClusterEntryIds = new long[lcsClusterEntries.size()];

			for (int i = 0; i < lcsClusterEntries.size(); i++) {
				LCSClusterEntry lcsClusterEntry = lcsClusterEntries.get(i);

				lcsClusterEntryIds[i] = lcsClusterEntry.getLcsClusterEntryId();
			}

			List<LCSClusterNode> lcsClusterNodes =
				lcsClusterNodePersistence.findByLCSClusterEntryId(
					lcsClusterEntryIds);

			if (lcsClusterNodes.isEmpty()) {
				return Collections.emptyList();
			}

			long[] lcsClusterNodeIds = new long[lcsClusterNodes.size()];

			for (int i = 0; i < lcsClusterNodes.size(); i++) {
				LCSClusterNode lcsClusterNode = lcsClusterNodes.get(i);

				lcsClusterNodeIds[i] = lcsClusterNode.getLcsClusterNodeId();
			}

			curLCSClusterNodeUptimes =
				lcsClusterNodeUptimePersistence.findByLCSClusterNodeId(
					lcsClusterNodeIds);
		}

		Date endDate = getDayLastDate(endMonth, endYear);

		Date curStartDate = getDayFirstDate(startMonth, startYear);

		long curStartTime = curStartDate.getTime();

		Date curEndDate = getDayLastDate(startMonth, startYear);

		long curEndTime = curEndDate.getTime();

		Calendar curCalendar = Calendar.getInstance();

		curCalendar.setTime(curStartDate);

		while (curStartDate.before(endDate)) {
			List<LCSClusterNodeUptime> filteredLCSClusterNodeUptimes =
				_cloneLCSClusterNodeUptimes(
					filterLCSClusterNodeUptimes(
						curLCSClusterNodeUptimes, curStartTime, curEndTime));

			lcsClusterNodeUptimes.addAll(filteredLCSClusterNodeUptimes);

			if (!filteredLCSClusterNodeUptimes.isEmpty()) {
				Map<Long, List<LCSClusterNodeUptime>> lcsClusterNodeUptimesMap =
					new HashMap<>();

				for (LCSClusterNodeUptime lcsClusterNodeUptime :
						filteredLCSClusterNodeUptimes) {

					List<LCSClusterNodeUptime>
						lcsClusterNodeLCSClusterNodeUptimes =
							lcsClusterNodeUptimesMap.get(
								lcsClusterNodeUptime.getLcsClusterNodeId());

					if (lcsClusterNodeLCSClusterNodeUptimes == null) {
						lcsClusterNodeLCSClusterNodeUptimes = new ArrayList<>();

						lcsClusterNodeUptimesMap.put(
							lcsClusterNodeUptime.getLcsClusterNodeId(),
							lcsClusterNodeLCSClusterNodeUptimes);
					}

					lcsClusterNodeLCSClusterNodeUptimes.add(
						lcsClusterNodeUptime);
				}

				for (List<LCSClusterNodeUptime>
						lcsClusterNodeLCSlusterNodeUptimes :
							lcsClusterNodeUptimesMap.values()) {

					LCSClusterNodeUptime lcsClusterNodeUptime =
						lcsClusterNodeLCSlusterNodeUptimes.get(0);

					if (lcsClusterNodeUptime.getStartTime() < curStartTime) {
						lcsClusterNodeUptime.setStartTime(curStartTime);
					}

					lcsClusterNodeUptime =
						lcsClusterNodeLCSlusterNodeUptimes.get(
							lcsClusterNodeLCSlusterNodeUptimes.size() - 1);

					if (lcsClusterNodeUptime.getEndTime() > curEndTime) {
						lcsClusterNodeUptime.setEndTime(curEndTime);
					}
					else if (lcsClusterNodeUptime.getEndTime() == 0) {
						lcsClusterNodeUptime.setEndTime(
							System.currentTimeMillis());
					}
				}
			}

			for (LCSClusterNodeUptime lcsClusterNodeUptime :
					filteredLCSClusterNodeUptimes) {

				double time =
					lcsClusterNodeUptime.getEndTime() -
						lcsClusterNodeUptime.getStartTime();

				time = time / 1000f / 60 / 60;

				lcsClusterNodeUptime.setUptime(time);
			}

			curCalendar.add(Calendar.MONTH, 1);

			curStartDate = curCalendar.getTime();

			curStartTime = curStartDate.getTime();

			curEndDate = getDayLastDate(
				curCalendar.get(Calendar.MONTH),
				curCalendar.get(Calendar.YEAR));

			curEndTime = curEndDate.getTime();
		}

		return lcsClusterNodeUptimes;
	}

	@Override
	public LCSClusterNodeUptime updateLCSClusterNodeUptime(
			long lcsClusterNodeId)
		throws PortalException {

		LCSClusterNodeUptime lcsClusterNodeUptime =
			lcsClusterNodeUptimePersistence.fetchByLCNI_ET(lcsClusterNodeId, 0);

		if (lcsClusterNodeUptime == null) {
			return null;
		}

		lcsClusterNodeUptime.setEndTime(System.currentTimeMillis());

		lcsClusterNodeUptime = lcsClusterNodeUptimePersistence.update(
			lcsClusterNodeUptime);

		LCSClusterNode lcsClusterNode =
			lcsClusterNodeLocalService.getLCSClusterNode(lcsClusterNodeId);

		lcsClusterNodeLocalService.updateStatus(lcsClusterNode);

		return lcsClusterNodeUptime;
	}

	@Override
	public LCSClusterNodeUptime updateLCSClusterNodeUptime(String key)
		throws PortalException {

		LCSClusterNode lcsClusterNode = lcsClusterNodePersistence.findByKey(
			key);

		return updateLCSClusterNodeUptime(lcsClusterNode.getLcsClusterNodeId());
	}

	@Override
	public void updateLCSClusterNodeUptimes(String key, String uptimesJSON)
		throws PortalException {

		LCSClusterNode lcsClusterNode = lcsClusterNodePersistence.findByKey(
			key);

		JSONArray uptimesJsonArray = JSONFactoryUtil.createJSONArray(
			uptimesJSON);

		for (int i = 0; i < uptimesJsonArray.length(); i++) {
			JSONObject uptimeJsonObject = uptimesJsonArray.getJSONObject(i);

			long endTime = uptimeJsonObject.getLong("endTime");
			long startTime = uptimeJsonObject.getLong("startTime");

			LCSClusterNodeUptime lcsClusterNodeUptime =
				lcsClusterNodeUptimePersistence.fetchByLCNI_ST(
					lcsClusterNode.getLcsClusterNodeId(), startTime);

			if (lcsClusterNodeUptime != null) {
				lcsClusterNodeUptime.setEndTime(endTime);

				lcsClusterNodeUptimePersistence.update(lcsClusterNodeUptime);
			}
			else {
				lcsClusterNodeUptime = new LCSClusterNodeUptimeImpl();

				lcsClusterNodeUptime.setLcsClusterNodeUptimeId(
					counterLocalService.increment(
						LCSClusterNodeUptime.class.getName()));
				lcsClusterNodeUptime.setLcsClusterNodeId(
					lcsClusterNode.getLcsClusterNodeId());
				lcsClusterNodeUptime.setStartTime(startTime);
				lcsClusterNodeUptime.setEndTime(endTime);

				lcsClusterNodeUptimePersistence.update(lcsClusterNodeUptime);
			}
		}

		lcsClusterNodeLocalService.updateStatus(lcsClusterNode);
	}

	protected List<LCSClusterNodeUptime> filterLCSClusterNodeUptimes(
		List<LCSClusterNodeUptime> lcsClusterNodeUptimes, long startTime,
		long endTime) {

		List<LCSClusterNodeUptime> filteredLCSClusterNodeUptimes =
			new ArrayList<>();

		for (LCSClusterNodeUptime lcsClusterNodeUptime :
				lcsClusterNodeUptimes) {

			if ((lcsClusterNodeUptime.getStartTime() >= startTime) &&
				(lcsClusterNodeUptime.getStartTime() < endTime)) {

				filteredLCSClusterNodeUptimes.add(lcsClusterNodeUptime);

				continue;
			}

			if ((lcsClusterNodeUptime.getEndTime() >= startTime) &&
				(lcsClusterNodeUptime.getEndTime() < endTime)) {

				filteredLCSClusterNodeUptimes.add(lcsClusterNodeUptime);

				continue;
			}

			if ((lcsClusterNodeUptime.getEndTime() == 0) &&
				(lcsClusterNodeUptime.getStartTime() < startTime)) {

				filteredLCSClusterNodeUptimes.add(lcsClusterNodeUptime);
			}
		}

		return filteredLCSClusterNodeUptimes;
	}

	protected Date getDayFirstDate(int month, int year) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTimeZone(TimeZone.getTimeZone("GMT"));

		/// Set Calendar.MOTH and Calendar.YEAR to give context to

		// calendar#getActualMaximum

		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);

		calendar.set(Calendar.AM_PM, calendar.getActualMinimum(Calendar.AM_PM));
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		calendar.set(Calendar.HOUR, calendar.getActualMinimum(Calendar.HOUR));
		calendar.set(
			Calendar.MILLISECOND,
			calendar.getActualMinimum(Calendar.MILLISECOND));
		calendar.set(
			Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
		calendar.set(
			Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));

		return calendar.getTime();
	}

	protected Date getDayLastDate(int month, int year) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTimeZone(TimeZone.getTimeZone("GMT"));

		/// Set Calendar.MOTH and Calendar.YEAR to give context to

		// calendar#getActualMaximum

		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);

		calendar.set(Calendar.AM_PM, calendar.getActualMaximum(Calendar.AM_PM));
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		calendar.set(Calendar.HOUR, calendar.getActualMaximum(Calendar.HOUR));
		calendar.set(
			Calendar.MILLISECOND,
			calendar.getActualMaximum(Calendar.MILLISECOND));
		calendar.set(
			Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
		calendar.set(
			Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));

		return calendar.getTime();
	}

	protected List<LCSClusterNodeUptime> getLCSClusterNodeUptimes(
			List<LCSClusterNodeUptime> lcsClusterNodeUptimes)
		throws PortalException {

		for (LCSClusterNodeUptime lcsClusterNodeUptime :
				lcsClusterNodeUptimes) {

			LCSClusterNode lcsClusterNode =
				lcsClusterNodePersistence.findByPrimaryKey(
					lcsClusterNodeUptime.getLcsClusterNodeId());

			LCSClusterEntry lcsClusterEntry =
				lcsClusterEntryPersistence.findByPrimaryKey(
					lcsClusterNode.getLcsClusterEntryId());

			lcsClusterNodeUptime.setLcsClusterEntryId(
				lcsClusterEntry.getLcsClusterEntryId());
			lcsClusterNodeUptime.setLcsClusterEntryName(
				lcsClusterEntry.getName());

			lcsClusterNodeUptime.setLcsClusterNodeKey(lcsClusterNode.getKey());
			lcsClusterNodeUptime.setLcsClusterNodeName(
				lcsClusterNode.getName());

			LCSProject lcsProject = lcsProjectPersistence.findByPrimaryKey(
				lcsClusterEntry.getLcsProjectId());

			lcsClusterNodeUptime.setLcsProjectName(lcsProject.getName());
		}

		return lcsClusterNodeUptimes;
	}

	private List<LCSClusterNodeUptime> _cloneLCSClusterNodeUptimes(
		List<LCSClusterNodeUptime> lcsClusterNodeUptimes) {

		List<LCSClusterNodeUptime> clonedLCSClusterNodeUptimes =
			new ArrayList<>(lcsClusterNodeUptimes.size());

		for (LCSClusterNodeUptime lcsClusterNodeUptime :
				lcsClusterNodeUptimes) {

			clonedLCSClusterNodeUptimes.add(
				(LCSClusterNodeUptime)lcsClusterNodeUptime.clone());
		}

		return clonedLCSClusterNodeUptimes;
	}

	private static final int _DATE_RANGE_CURRENT_YEAR = 1;

}