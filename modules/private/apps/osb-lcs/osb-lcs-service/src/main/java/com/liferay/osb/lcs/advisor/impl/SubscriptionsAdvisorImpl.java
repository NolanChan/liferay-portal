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

package com.liferay.osb.lcs.advisor.impl;

import com.liferay.lcs.subscription.SubscriptionType;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.osb.lcs.advisor.SubscriptionsAdvisor;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.LCSClusterNodeUptime;
import com.liferay.osb.lcs.model.LCSProject;
import com.liferay.osb.lcs.model.LCSSubscriptionEntry;
import com.liferay.osb.lcs.service.LCSClusterEntryService;
import com.liferay.osb.lcs.service.LCSClusterNodeService;
import com.liferay.osb.lcs.service.LCSClusterNodeUptimeService;
import com.liferay.osb.lcs.service.LCSProjectService;
import com.liferay.osb.lcs.service.LCSSubscriptionEntryService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
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
import java.util.ResourceBundle;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marko Cikos
 * @author Matija Petanjek
 */
@Component(immediate = true)
public class SubscriptionsAdvisorImpl implements SubscriptionsAdvisor {

	public static final int UPTIMES_START_MONTH = Calendar.JANUARY;

	public static final int UPTIMES_START_YEAR = 2016;

	@Override
	public String formatUptime(long startTime, long endTime, Locale locale) {
		String duration = getDuration(startTime, endTime, locale);

		ResourceBundle resourceBundle =
			_resourceBundleLoader.loadResourceBundle(locale.getLanguage());

		StringBundler sb = new StringBundler(7);

		sb.append("^(00(");
		sb.append(LanguageUtil.get(resourceBundle, "days-abbreviation"));
		sb.append(StringPool.PIPE);
		sb.append(LanguageUtil.get(resourceBundle, "hours-abbreviation"));
		sb.append(StringPool.PIPE);
		sb.append(LanguageUtil.get(resourceBundle, "minutes-abbreviation"));
		sb.append("):)+");

		return duration.replaceAll(sb.toString(), StringPool.BLANK);
	}

	@Override
	public JSONObject getBillsJSONObject(
		long lcsProjectId, int month, int year, Locale locale)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONArray summaryJSONArray = JSONFactoryUtil.createJSONArray();

		JSONObject amountJSONObject = JSONFactoryUtil.createJSONObject();

		ResourceBundle resourceBundle =
			_resourceBundleLoader.loadResourceBundle(locale.getLanguage());

		amountJSONObject.put(
			"name",
			LanguageUtil.get(
				resourceBundle, "liferay-connected-services-charges"));

		double amount =
			_lcsClusterNodeUptimeService.
				getMonthlyElasticLCSClusterNodeUptimeTotal(
					lcsProjectId, month, year);

		amountJSONObject.put("value", amount);

		summaryJSONArray.put(amountJSONObject);

		JSONObject taxJSONObject = JSONFactoryUtil.createJSONObject();

		taxJSONObject.put("name", LanguageUtil.get(resourceBundle, "tax"));
		taxJSONObject.put("value", 0);

		summaryJSONArray.put(taxJSONObject);

		JSONObject totalJSONObject = JSONFactoryUtil.createJSONObject();

		totalJSONObject.put("name", LanguageUtil.get(resourceBundle, "total"));
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

	@Override
	public String getDuration(long start, long end, Locale locale) {
		StringBundler sb = new StringBundler(11);

		DateTime endDateTime = new DateTime(end);
		DateTime startDateTime = new DateTime(start);

		NumberFormat numberFormat = NumberFormat.getInstance();

		numberFormat.setMinimumIntegerDigits(2);

		Days days = Days.daysBetween(startDateTime, endDateTime);

		ResourceBundle resourceBundle =
			_resourceBundleLoader.loadResourceBundle(locale.getLanguage());

		sb.append(numberFormat.format(days.getDays()));
		sb.append(LanguageUtil.get(resourceBundle, "days-abbreviation"));
		sb.append(StringPool.COLON);

		Hours hours = Hours.hoursBetween(startDateTime, endDateTime);

		sb.append(numberFormat.format(hours.getHours() % 24));

		sb.append(LanguageUtil.get(resourceBundle, "hours-abbreviation"));
		sb.append(StringPool.COLON);

		Minutes minutes = Minutes.minutesBetween(startDateTime, endDateTime);

		sb.append(numberFormat.format(minutes.getMinutes() % 60));

		sb.append(LanguageUtil.get(resourceBundle, "minutes-abbreviation"));
		sb.append(StringPool.COLON);

		Seconds seconds = Seconds.secondsBetween(startDateTime, endDateTime);

		sb.append(numberFormat.format(seconds.getSeconds() % 60));

		sb.append(LanguageUtil.get(resourceBundle, "seconds-abbreviation"));

		return sb.toString();
	}

	@Override
	public JSONArray getLCSClusterEntriesJSONArray(
		long lcsProjectId, Locale locale)
		throws PortalException {

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

			ResourceBundle resourceBundle =
				_resourceBundleLoader.loadResourceBundle(locale.getLanguage());

			String subscriptionTypeLabel = LanguageUtil.get(
				resourceBundle, subscriptionType.getLabel());

			jsonObject.put("subscriptionTypeLabel", subscriptionTypeLabel);

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	@Override
	public JSONArray getLCSClusterNodesJSONArray(
		long lcsProjectId, Locale locale)
		throws PortalException {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<LCSClusterEntry> lcsClusterEntries =
			_lcsClusterEntryService.getLCSProjectLCSClusterEntries(
				lcsProjectId);

		ResourceBundle resourceBundle =
			_resourceBundleLoader.loadResourceBundle(locale.getLanguage());

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
					resourceBundle, subscriptionType.getLabel());

				jsonObject.put("subscriptionTypeLabel", subscriptionTypeLabel);

				jsonArray.put(jsonObject);
			}
		}

		return jsonArray;
	}

	@Override
	public JSONObject getLCSClusterNodeUptimesJSONObject(
		long lcsClusterEntryId, long lcsProjectId, int month, int year,
		Locale locale)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		Format format = FastDateFormatFactoryUtil.getDateTime(
			DateFormat.MEDIUM, DateFormat.MEDIUM, locale,
			TimeZone.getTimeZone("GMT"));
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		long totalDuration = 0;

		List<LCSClusterNodeUptime> lcsClusterNodeUptimes =
			_lcsClusterNodeUptimeService.getMonthlyLCSClusterNodeUptimes(
				lcsClusterEntryId, LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID,
				lcsProjectId, month, year, true, true);

		ResourceBundle resourceBundle =
			_resourceBundleLoader.loadResourceBundle(locale.getLanguage());

		for (LCSClusterNodeUptime lcsClusterNodeUptime :
				lcsClusterNodeUptimes) {

			JSONObject lcsClusterNodeUptimesJSONObject =
				JSONFactoryUtil.createJSONObject();

			long endTime = lcsClusterNodeUptime.getEndTime();

			String endTimeLabel = LanguageUtil.get(resourceBundle, "online");

			if (endTime == 0) {
				Date now = new Date();

				endTime = now.getTime();
			}
			else {
				endTimeLabel = format.format(endTime);
			}

			lcsClusterNodeUptimesJSONObject.put(
				"duration",
				getDuration(
					lcsClusterNodeUptime.getStartTime(), endTime, locale));
			lcsClusterNodeUptimesJSONObject.put(
				"endTime", lcsClusterNodeUptime.getEndTime());
			lcsClusterNodeUptimesJSONObject.put("endTimeLabel", endTimeLabel);
			lcsClusterNodeUptimesJSONObject.put(
				"environment", lcsClusterNodeUptime.getLcsClusterEntryName());
			lcsClusterNodeUptimesJSONObject.put(
				"server", lcsClusterNodeUptime.getLcsClusterNodeName());
			lcsClusterNodeUptimesJSONObject.put(
				"startTime", lcsClusterNodeUptime.getStartTime());
			lcsClusterNodeUptimesJSONObject.put(
				"startTimeLabel",
				format.format(lcsClusterNodeUptime.getStartTime()));

			totalDuration =
				totalDuration + endTime - lcsClusterNodeUptime.getStartTime();

			jsonArray.put(lcsClusterNodeUptimesJSONObject);
		}

		jsonObject.put("entries", jsonArray);
		jsonObject.put("totalDuration", getDuration(0, totalDuration, locale));

		return jsonObject;
	}

	@Override
	public JSONArray getLCSSubscriptionEntriesJSONArray(
		List<LCSSubscriptionEntry> lcsOrderEntries, Locale locale,
		TimeZone timeZone) {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		Format format = FastDateFormatFactoryUtil.getSimpleDateFormat(
			_DATE_FORMAT_PATTERN, locale, timeZone);

		ResourceBundle resourceBundle =
			_resourceBundleLoader.loadResourceBundle(locale.getLanguage());

		for (LCSSubscriptionEntry lcsSubscriptionEntry : lcsOrderEntries) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			Date endDate = lcsSubscriptionEntry.getEndDate();

			String expirationDate = LanguageUtil.get(
				resourceBundle, "indefinite");

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
					resourceBundle, "unlimited");
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
				resourceBundle, subscriptionType.getLabel());

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

	@Override
	public JSONArray getPaymentsJSONArray(
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

	@Override
	public JSONArray getSubscriptionsUsageJSONArray(
		long lcsProjectId, Locale locale)
		throws PortalException {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		Map<SubscriptionType, int[]> subscriptionTypesServersCounts =
			new HashMap<>();

		LCSProject lcsProject = _lcsProjectService.getLCSProject(lcsProjectId);

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

		ResourceBundle resourceBundle =
			_resourceBundleLoader.loadResourceBundle(locale.getLanguage());

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
				resourceBundle, subscriptionType.getLabel());

			jsonObject.put("subscriptionTypeLabel", subscriptionTypeLabel);

			jsonObject.put("subscriptionTypeName", subscriptionType.name());

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	@Override
	public boolean hasElasticSubscription(long lcsProjectId)
		throws PortalException {

		return _lcsSubscriptionEntryService.
			hasLCSProjectElasticLCSSubscriptionEntry(lcsProjectId);
	}

	@Override
	public int[] parsePeriod(String period) {
		String[] periodArray = period.split(StringPool.DASH);

		return new int[] {
			GetterUtil.getInteger(periodArray[0]),
			GetterUtil.getInteger(periodArray[1])
		};
	}

	@Reference(unbind = "-")
	public void setLCSClusterEntryService(
		LCSClusterEntryService lcsClusterEntryService) {

		_lcsClusterEntryService = lcsClusterEntryService;
	}

	@Reference(unbind = "-")
	public void setLCSClusterNodeService(
		LCSClusterNodeService lcsClusterNodeService) {

		_lcsClusterNodeService = lcsClusterNodeService;
	}

	@Reference(unbind = "-")
	public void setLCSClusterNodeUptimeService(
		LCSClusterNodeUptimeService lcsClusterNodeUptimeService) {

		_lcsClusterNodeUptimeService = lcsClusterNodeUptimeService;
	}

	@Reference(unbind = "-")
	public void setLCSProjectService(LCSProjectService lcsProjectService) {
		_lcsProjectService = lcsProjectService;
	}

	@Reference(unbind = "-")
	public void setLCSSubscriptionEntryService(
		LCSSubscriptionEntryService lcsSubscriptionEntryService) {

		_lcsSubscriptionEntryService = lcsSubscriptionEntryService;
	}

	public void setResourceBundleLoader(
		ResourceBundleLoader resourceBundleLoader) {

		_resourceBundleLoader = resourceBundleLoader;
	}

	protected boolean isLCSClusterEntryEditable(LCSClusterEntry lcsClusterEntry)
		throws PortalException {

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

	private LCSClusterEntryService _lcsClusterEntryService;
	private LCSClusterNodeService _lcsClusterNodeService;
	private LCSClusterNodeUptimeService _lcsClusterNodeUptimeService;
	private LCSProjectService _lcsProjectService;
	private LCSSubscriptionEntryService _lcsSubscriptionEntryService;
	private ResourceBundleLoader _resourceBundleLoader;

}