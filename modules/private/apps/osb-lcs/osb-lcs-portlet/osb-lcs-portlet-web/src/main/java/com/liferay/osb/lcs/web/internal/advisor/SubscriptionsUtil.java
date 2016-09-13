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

package com.liferay.osb.lcs.subscriptions.util;

import com.liferay.lcs.subscription.SubscriptionType;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.LCSClusterNodeUptime;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.model.LCSSubscriptionEntry;
import com.liferay.osb.lcs.service.LCSClusterEntryService;
import com.liferay.osb.lcs.service.LCSClusterNodeService;
import com.liferay.osb.lcs.service.LCSClusterNodeUptimeService;
import com.liferay.osb.lcs.service.LCSProjectServiceUtil;
import com.liferay.osb.lcs.service.LCSSubscriptionEntryService;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.text.DateFormat;
import java.text.Format;
import java.text.NumberFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.portlet.PortletConfig;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;

/**
 * @author Marko Cikos
 * @author Matija Petanjek
 */
public class SubscriptionsUtil {

	public static final int UPTIMES_START_MONTH = Calendar.JANUARY;

	public static final int UPTIMES_START_YEAR = 2016;

	public static String formatUptime(
		long startTime, long endTime, Locale locale,
		PortletConfig portletConfig) {

		String duration = SubscriptionsUtil.getDuration(
			startTime, endTime, locale, portletConfig);

		StringBundler sb = new StringBundler(7);

		sb.append("^(00(");
		sb.append(LanguageUtil.get(portletConfig, locale, "days-abbreviation"));
		sb.append(StringPool.PIPE);
		sb.append(
			LanguageUtil.get(portletConfig, locale, "hours-abbreviation"));
		sb.append(StringPool.PIPE);
		sb.append(
			LanguageUtil.get(portletConfig, locale, "minutes-abbreviation"));
		sb.append("):)+");

		return duration.replaceAll(sb.toString(), StringPool.BLANK);
	}

	public static JSONObject getBillsJSONObject(
			long lcsProjectId, int month, int year, Locale locale,
			PortletConfig portletConfig)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONArray summaryJSONArray = JSONFactoryUtil.createJSONArray();

		JSONObject amountJSONObject = JSONFactoryUtil.createJSONObject();

		amountJSONObject.put(
			"name",
			LanguageUtil.get(
				portletConfig, locale, "liferay-connected-services-charges"));

		double amount =
			_lcsClusterNodeUptimeService.
				getMonthlyElasticLCSClusterNodeUptimeTotal(
					lcsProjectId, month, year);

		amountJSONObject.put("value", amount);

		summaryJSONArray.put(amountJSONObject);

		JSONObject taxJSONObject = JSONFactoryUtil.createJSONObject();

		taxJSONObject.put(
			"name", LanguageUtil.get(portletConfig, locale, "tax"));
		taxJSONObject.put("value", 0);

		summaryJSONArray.put(taxJSONObject);

		JSONObject totalJSONObject = JSONFactoryUtil.createJSONObject();

		totalJSONObject.put(
			"name", LanguageUtil.get(portletConfig, locale, "total"));
		totalJSONObject.put("value", amount);

		summaryJSONArray.put(totalJSONObject);

		jsonObject.put("summary", summaryJSONArray);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<LCSClusterNodeUptime> lcsClusterNodeUptimes =
			_lcsClusterNodeUptimeService.
				getMonthlyElasticTotalLCSClusterNodeUptimes(
					lcsProjectId, month, year);

		for (LCSClusterNodeUptime lcsClusterNodeUptime :
				lcsClusterNodeUptimes) {

			JSONObject detailsJSONObject = JSONFactoryUtil.createJSONObject();

			detailsJSONObject.put("amount", lcsClusterNodeUptime.getAmount());
			detailsJSONObject.put(
				"environmentName",
				lcsClusterNodeUptime.getLcsClusterEntryName());
			detailsJSONObject.put("rate", lcsClusterNodeUptime.getRate());
			detailsJSONObject.put(
				"serverName", lcsClusterNodeUptime.getLcsClusterNodeName());
			detailsJSONObject.put("uptime", lcsClusterNodeUptime.getUptime());

			jsonArray.put(detailsJSONObject);
		}

		jsonObject.put("details", jsonArray);

		return jsonObject;
	}

	public static String getDuration(
		long start, long end, Locale locale, PortletConfig portletConfig) {

		StringBundler sb = new StringBundler(11);

		DateTime endDateTime = new DateTime(end);
		DateTime startDateTime = new DateTime(start);

		NumberFormat numberFormat = NumberFormat.getInstance();

		numberFormat.setMinimumIntegerDigits(2);

		Days days = Days.daysBetween(startDateTime, endDateTime);

		sb.append(numberFormat.format(days.getDays()));
		sb.append(LanguageUtil.get(portletConfig, locale, "days-abbreviation"));
		sb.append(StringPool.COLON);

		Hours hours = Hours.hoursBetween(startDateTime, endDateTime);

		sb.append(numberFormat.format(hours.getHours() % 24));
		sb.append(
			LanguageUtil.get(portletConfig, locale, "hours-abbreviation"));
		sb.append(StringPool.COLON);

		Minutes minutes = Minutes.minutesBetween(startDateTime, endDateTime);

		sb.append(numberFormat.format(minutes.getMinutes() % 60));
		sb.append(
			LanguageUtil.get(portletConfig, locale, "minutes-abbreviation"));
		sb.append(StringPool.COLON);

		Seconds seconds = Seconds.secondsBetween(startDateTime, endDateTime);

		sb.append(numberFormat.format(seconds.getSeconds() % 60));
		sb.append(
			LanguageUtil.get(portletConfig, locale, "seconds-abbreviation"));

		return sb.toString();
	}

	public static JSONArray getLCSClusterEntriesJSONArray(
			long lcsProjectId, Locale locale, PortletConfig portletConfig)
		throws PortalException, SystemException {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<LCSClusterEntry> lcsClusterEntries =
			_lcsClusterEntryService.getLCSProjectLCSClusterEntries(
				lcsProjectId);

		for (LCSClusterEntry lcsClusterEntry : lcsClusterEntries) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put(
				"editable", isLCSClusterEntryEditable(lcsClusterEntry));
			jsonObject.put("elastic", lcsClusterEntry.isElastic());
			jsonObject.put(
				"lcsClusterEntryId", lcsClusterEntry.getLcsClusterEntryId());
			jsonObject.put("name", lcsClusterEntry.getName());

			SubscriptionType subscriptionType = SubscriptionType.valueOf(
				lcsClusterEntry.getSubscriptionType());

			String subscriptionTypeLabel = LanguageUtil.get(
				portletConfig, locale, subscriptionType.getLabel());

			jsonObject.put("subscriptionTypeLabel", subscriptionTypeLabel);

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	public static JSONArray getLCSClusterNodesJSONArray(
			long lcsProjectId, Locale locale, PortletConfig portletConfig)
		throws PortalException, SystemException {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<LCSClusterEntry> lcsClusterEntries =
			_lcsClusterEntryService.getLCSProjectLCSClusterEntries(
				lcsProjectId);

		for (LCSClusterEntry lcsClusterEntry : lcsClusterEntries) {
			List<LCSClusterNode> lcsClusterNodes =
				_lcsClusterNodeService.getLCSClusterEntryLCSClusterNodes(
					lcsClusterEntry.getLcsClusterEntryId());

			for (LCSClusterNode lcsClusterNode : lcsClusterNodes) {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

				jsonObject.put("environmentName", lcsClusterEntry.getName());
				jsonObject.put("serverName", lcsClusterNode.getName());

				SubscriptionType subscriptionType = SubscriptionType.valueOf(
					lcsClusterEntry.getSubscriptionType());

				String subscriptionTypeLabel = LanguageUtil.get(
					portletConfig, locale, subscriptionType.getLabel());

				jsonObject.put("subscriptionTypeLabel", subscriptionTypeLabel);

				jsonArray.put(jsonObject);
			}
		}

		return jsonArray;
	}

	public static JSONObject getLCSClusterNodeUptimesJSONObject(
			long lcsClusterEntryId, long lcsProjectId, int month, int year,
			Locale locale, PortletConfig portletConfig)
		throws Exception {

		JSONObject JSONObject = JSONFactoryUtil.createJSONObject();

		Format format = FastDateFormatFactoryUtil.getDateTime(
			DateFormat.MEDIUM, DateFormat.MEDIUM, locale,
			TimeZone.getTimeZone("GMT"));
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		long totalDuration = 0;

		List<LCSClusterNodeUptime> lcsClusterNodeUptimes =
			_lcsClusterNodeUptimeService.getMonthlyLCSClusterNodeUptimes(
				lcsClusterEntryId, LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID,
				lcsProjectId, month, year, true, true);

		for (LCSClusterNodeUptime lcsClusterNodeUptime :
				lcsClusterNodeUptimes) {

			JSONObject lcsClusterNodeUptimesJSONObject =
				JSONFactoryUtil.createJSONObject();

			long endTime = lcsClusterNodeUptime.getEndTime();

			String endTimeLabel = LanguageUtil.get(
				portletConfig, locale, "online");

			if (endTime == 0) {
				Date now = new Date();

				endTime = now.getTime();
			}
			else {
				endTimeLabel = format.format(endTime);
			}

			lcsClusterNodeUptimesJSONObject.put("endTimeLabel", endTimeLabel);
			lcsClusterNodeUptimesJSONObject.put(
				"endTime", lcsClusterNodeUptime.getEndTime());
			lcsClusterNodeUptimesJSONObject.put(
				"environment", lcsClusterNodeUptime.getLcsClusterEntryName());
			lcsClusterNodeUptimesJSONObject.put(
				"server", lcsClusterNodeUptime.getLcsClusterNodeName());
			lcsClusterNodeUptimesJSONObject.put(
				"startTimeLabel",
				format.format(lcsClusterNodeUptime.getStartTime()));
			lcsClusterNodeUptimesJSONObject.put(
				"startTime", lcsClusterNodeUptime.getStartTime());
			lcsClusterNodeUptimesJSONObject.put(
				"duration",
				getDuration(
					lcsClusterNodeUptime.getStartTime(), endTime, locale,
					portletConfig));

			totalDuration =
				totalDuration + endTime - lcsClusterNodeUptime.getStartTime();

			jsonArray.put(lcsClusterNodeUptimesJSONObject);
		}

		JSONObject.put("entries", jsonArray);
		JSONObject.put(
			"totalDuration",
			getDuration(0, totalDuration, locale, portletConfig));

		return JSONObject;
	}

	public static JSONArray getLCSSubscriptionEntriesJSONArray(
		List<LCSSubscriptionEntry> lcsOrderEntries, Locale locale,
		TimeZone timeZone, PortletConfig portletConfig) {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		Format format = FastDateFormatFactoryUtil.getSimpleDateFormat(
			_DATE_FORMAT_PATTERN, locale, timeZone);

		for (LCSSubscriptionEntry lcsSubscriptionEntry : lcsOrderEntries) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			Date endDate = lcsSubscriptionEntry.getEndDate();

			String expirationDate = LanguageUtil.get(
				portletConfig, locale, "indefinite");

			if (endDate.getTime() != _EXPIRATION_DATE_INDEFINITE) {
				expirationDate = format.format(endDate);
			}

			jsonObject.put("expirationDate", expirationDate);

			String platform = lcsSubscriptionEntry.getPlatform();

			int platformVersion = lcsSubscriptionEntry.getPlatformVersion();

			if (platformVersion != _PLATFORM_VERSION_ALL) {
				platform += StringPool.SPACE + platformVersion;
			}

			jsonObject.put("platform", platform);

			int processorCoresAllowed =
				lcsSubscriptionEntry.getProcessorCoresAllowed();

			String processorCoresAllowedLabel = String.valueOf(
				processorCoresAllowed);

			if (processorCoresAllowed == Integer.MAX_VALUE) {
				processorCoresAllowedLabel = LanguageUtil.get(
					portletConfig, locale, "unlimited");
			}

			jsonObject.put(
				"processorCoresAllowedLabel", processorCoresAllowedLabel);

			jsonObject.put("product", lcsSubscriptionEntry.getProduct());
			jsonObject.put(
				"serversAllowed", lcsSubscriptionEntry.getServersAllowed());
			jsonObject.put(
				"serversUsed", lcsSubscriptionEntry.getServersUsed());
			jsonObject.put(
				"startDate",
				format.format(lcsSubscriptionEntry.getStartDate()));

			SubscriptionType subscriptionType = SubscriptionType.valueOf(
				lcsSubscriptionEntry.getType());

			String subscriptionTypeLabel = LanguageUtil.get(
				portletConfig, locale, subscriptionType.getLabel());

			jsonObject.put("subscriptionTypeLabel", subscriptionTypeLabel);

			if (lcsSubscriptionEntry.getSupportEndDate() != null) {
				jsonObject.put(
					"supportEndDate",
					format.format(lcsSubscriptionEntry.getSupportEndDate()));
			}
			else {
				jsonObject.put("supportEndDate", StringPool.DASH);
			}

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	public static JSONArray getPaymentsJSONArray(
			long lcsProjectId, int startMonth, int startYear, int endMonth,
			int endYear)
		throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		Map<Date, Double> monthlyLCSClusterNodeUptimeTotalMap =
			_lcsClusterNodeUptimeService.
				getMonthlyElasticLCSClusterNodeUptimeTotalMap(
					lcsProjectId, startMonth, startYear, endMonth, endYear);

		for (Map.Entry<Date, Double> entry :
				monthlyLCSClusterNodeUptimeTotalMap.entrySet()) {

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			Format format = FastDateFormatFactoryUtil.getSimpleDateFormat(
				_MONTH_YEAR_FORMAT_PATTERN);

			jsonObject.put("time", format.format(entry.getKey()));

			Double total = entry.getValue();

			jsonObject.put("total", total);

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	public static JSONArray getSubscriptionsUsageJSONArray(
			long lcsProjectId, Locale locale, PortletConfig portletConfig)
		throws PortalException, SystemException {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		Map<SubscriptionType, int[]> subscriptionTypesServersCounts =
			new HashMap<SubscriptionType, int[]>();

		LCSProject lcsProject = LCSProjectServiceUtil.getLCSProject(
			lcsProjectId);

		List<LCSSubscriptionEntry> lcsSubscriptionEntries =
			_lcsSubscriptionEntryService.getLCSProjectLCSSubscriptionEntries(
				lcsProject.getLcsProjectId());

		for (LCSSubscriptionEntry lcsSubscriptionEntry :
				lcsSubscriptionEntries) {

			String licenseEntryType = lcsSubscriptionEntry.getType();

			SubscriptionType subscriptionType = SubscriptionType.valueOf(
				licenseEntryType);

			int serversAllowed = lcsSubscriptionEntry.getServersAllowed();
			int serversUsed = lcsSubscriptionEntry.getServersUsed();

			int[] serversCounts = subscriptionTypesServersCounts.get(
				subscriptionType);

			if (serversCounts == null) {
				serversCounts = new int[2];
			}
			else {
				serversAllowed = serversAllowed + serversCounts[0];
				serversUsed = serversUsed + serversCounts[1];
			}

			serversCounts[0] = serversAllowed;
			serversCounts[1] = serversUsed;

			subscriptionTypesServersCounts.put(subscriptionType, serversCounts);
		}

		for (Map.Entry<SubscriptionType, int[]> entry :
				subscriptionTypesServersCounts.entrySet()) {

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			int[] serversCounts = entry.getValue();

			int serversAllowed = serversCounts[0];
			int serversUsed = serversCounts[1];

			jsonObject.put("serversAllowed", serversAllowed);
			jsonObject.put("serversAvailable", serversAllowed - serversUsed);
			jsonObject.put("serversUsed", serversUsed);

			SubscriptionType subscriptionType = entry.getKey();

			String subscriptionTypeLabel = LanguageUtil.get(
				portletConfig, locale, subscriptionType.getLabel());

			jsonObject.put("subscriptionTypeLabel", subscriptionTypeLabel);
			jsonObject.put("subscriptionTypeName", subscriptionType.name());

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	public static boolean hasElasticSubscription(long lcsProjectId)
		throws PortalException, SystemException {

		return _lcsSubscriptionEntryService.
			hasLCSProjectElasticLCSSubscriptionEntry(lcsProjectId);
	}

	public static int[] parsePeriod(String period) {
		String[] periodArray = period.split(StringPool.DASH);

		return new int[] {
			GetterUtil.getInteger(periodArray[0]),
			GetterUtil.getInteger(periodArray[1])
		};
	}

	protected static boolean isLCSClusterEntryEditable(
			LCSClusterEntry lcsClusterEntry)
		throws PortalException, SystemException {

		SubscriptionType subscriptionType = SubscriptionType.valueOf(
			lcsClusterEntry.getSubscriptionType());

		if (subscriptionType != SubscriptionType.UNDEFINED) {
			return false;
		}

		List<LCSClusterNode> lcsClusterNodes =
			_lcsClusterNodeService.getLCSClusterEntryLCSClusterNodes(
				lcsClusterEntry.getLcsClusterEntryId(), true);

		for (LCSClusterNode lcsClusterNode : lcsClusterNodes) {
			if (!lcsClusterNode.isOffline()) {
				return false;
			}
		}

		return true;
	}

	private static final String _DATE_FORMAT_PATTERN = "yyyy-MM-dd";

	private static final long _EXPIRATION_DATE_INDEFINITE = 4102444800000L;

	private static final String _MONTH_YEAR_FORMAT_PATTERN = "MMM yyyy";

	private static final int _PLATFORM_VERSION_ALL = 0;

	@BeanReference(type = LCSClusterEntryService.class)
	private static LCSClusterEntryService _lcsClusterEntryService;

	@BeanReference(type = LCSClusterNodeService.class)
	private static LCSClusterNodeService _lcsClusterNodeService;

	@BeanReference(type = LCSClusterNodeUptimeService.class)
	private static LCSClusterNodeUptimeService _lcsClusterNodeUptimeService;

	@BeanReference(type = LCSSubscriptionEntryService.class)
	private static LCSSubscriptionEntryService _lcsSubscriptionEntryService;

}