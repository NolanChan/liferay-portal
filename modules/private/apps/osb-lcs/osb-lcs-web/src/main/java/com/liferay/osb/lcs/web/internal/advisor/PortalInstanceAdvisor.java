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

package com.liferay.osb.lcs.web.internal.advisor;

import com.liferay.lcs.portal.SiteType;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.osb.lcs.advisor.PortalPropertiesAdvisor;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.nosql.model.LCSClusterNodeDetails;
import com.liferay.osb.lcs.nosql.model.LCSClusterNodeHibernateMetrics;
import com.liferay.osb.lcs.nosql.model.LCSClusterNodeInstallationEnvironment;
import com.liferay.osb.lcs.nosql.model.LCSClusterNodeJVMMetrics;
import com.liferay.osb.lcs.nosql.model.LCSClusterNodeLiferayVMMetrics;
import com.liferay.osb.lcs.nosql.model.LCSClusterNodeSite;
import com.liferay.osb.lcs.nosql.model.LCSStatsLayoutMetricsEvents;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeDetailsService;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeHibernateMetricsService;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeInstallationEnvironmentService;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeJVMMetricsService;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeLiferayVMMetricsService;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodeSiteService;
import com.liferay.osb.lcs.nosql.service.LCSStatsLayoutMetricsEventsService;
import com.liferay.osb.lcs.service.LCSClusterNodeService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Marko Cikos
 */
@Component(immediate = true)
public class PortalInstanceAdvisor {

	public static final int RELEASE_6_2_0_BUILD_NUMBER = 6200;

	public Map<Integer, String> getCompanyIdsWebIds(String key) {
		LCSClusterNodeDetails lcsClusterNodeDetails =
			_lcsClusterNodeDetailsService.fetchLCSClusterNodeDetails(key);

		return lcsClusterNodeDetails.getCompanyIdsWebIds();
	}

	public JSONArray getCompanySitesJSONArray(
		Map<Integer, String> companyIdsWebIds, LCSClusterNode lcsClusterNode) {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<? extends LCSClusterNodeSite> lcsClusterNodeSites =
			_lcsClusterNodeSiteService.getLCSClusterNodeSites(
				lcsClusterNode.getInstallationId());

		lcsClusterNodeSites = ListUtil.sort(
			lcsClusterNodeSites, new LCSClusterNodeSitesComaparator());

		for (Map.Entry<Integer, String> entry : companyIdsWebIds.entrySet()) {
			int companyId = entry.getKey();

			JSONObject company = JSONFactoryUtil.createJSONObject();

			company.put("companyId", companyId);

			JSONArray sites = JSONFactoryUtil.createJSONArray();

			for (LCSClusterNodeSite lcsClusterNodeSite : lcsClusterNodeSites) {
				if (lcsClusterNodeSite.getCompanyId() != companyId) {
					continue;
				}

				JSONObject site = JSONFactoryUtil.createJSONObject();

				site.put("friendlyURL", lcsClusterNodeSite.getFriendlyURL());
				site.put("groupId", lcsClusterNodeSite.getGroupId());
				site.put(
					"organizationSite",
					SiteType.isOrganization(lcsClusterNodeSite.getType()));

				sites.put(site);
			}

			company.put("sites", sites);

			jsonArray.put(company);
		}

		return jsonArray;
	}

	public List<Object[]>
			getGarbageCollectorMetricsList(long layoutLCSClusterNodeId)
		throws PortalException {

		LCSClusterNode lcsClusterNode =
			_lcsClusterNodeService.getLCSClusterNode(layoutLCSClusterNodeId);

		LCSClusterNodeJVMMetrics lcsClusterNodeJVMMetrics =
			_lcsClusterNodeJVMMetricsService.fetchLCSClusterNodeJVMMetrics(
				lcsClusterNode.getKey());

		if (lcsClusterNodeJVMMetrics == null) {
			return Collections.emptyList();
		}

		List<Object[]> lcsGarbageCollectorMetricsList = new ArrayList<>();

		Map<String, Map<String, Long>> garbageCollectorMetricsMap =
			lcsClusterNodeJVMMetrics.getGarbageCollectorMetrics();

		Set<String> garbageCollectorNames = garbageCollectorMetricsMap.keySet();

		for (String garbageCollectorName : garbageCollectorNames) {
			Map<String, Long> garbageCollectorMetrics =
				garbageCollectorMetricsMap.get(garbageCollectorName);

			long runs = garbageCollectorMetrics.get("runs");
			long time = garbageCollectorMetrics.get("time");

			long averageTime = (runs == 0) ? 0 : (time / runs);

			Object[] lcsGarbageCollectorMetric =
				{garbageCollectorName, runs, time, averageTime};

			lcsGarbageCollectorMetricsList.add(lcsGarbageCollectorMetric);
		}

		return lcsGarbageCollectorMetricsList;
	}

	public List<Object[]> getHibernateMetricsObjectArrays(
			long layoutLCSClusterNodeId)
		throws PortalException {

		LCSClusterNode lcsClusterNode =
			_lcsClusterNodeService.getLCSClusterNode(layoutLCSClusterNodeId);

		LCSClusterNodeHibernateMetrics lcsClusterNodeHibernateMetrics =
			_lcsClusterNodeHibernateMetricsService.
				fetchLCSClusterNodeHibernateMetrics(lcsClusterNode.getKey());

		List<Object[]> hibernateMetricsObjectArrays = new ArrayList<>();

		if (lcsClusterNodeHibernateMetrics == null) {
			return Collections.emptyList();
		}

		hibernateMetricsObjectArrays.add(
			new Object[] {
				"queryExecutionMaxTime",
				lcsClusterNodeHibernateMetrics.getQueryCacheHitCount()
			});
		hibernateMetricsObjectArrays.add(
			new Object[] {
				"queryCacheMissCount",
				lcsClusterNodeHibernateMetrics.getQueryCacheMissCount()
			});
		hibernateMetricsObjectArrays.add(
			new Object[] {
				"queryCacheHitCount",
				lcsClusterNodeHibernateMetrics.getQueryExecutionCount()
			});
		hibernateMetricsObjectArrays.add(
			new Object[] {
				"queryExecutionCount",
				lcsClusterNodeHibernateMetrics.getQueryExecutionMaxTime()
			});

		return hibernateMetricsObjectArrays;
	}

	public JSONObject getLCSClusterNodeMetadataJSONObject(
			long lcsClusterNodeId, Locale locale)
		throws PortalException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		DecimalFormat decimalFormat = new DecimalFormat("#0.00");

		LCSClusterNode lcsClusterNode =
			_lcsClusterNodeService.getLCSClusterNode(lcsClusterNodeId, true);

		LCSClusterNodeInstallationEnvironment
			lcsClusterNodeInstallationEnvironment =
				_lcsClusterNodeInstallationEnvironmentService.
					fetchLCSClusterNodeInstallationEnvironment(
						lcsClusterNode.getKey());

		Map<String, String> hardwareMetadata = new TreeMap<>(
			lcsClusterNodeInstallationEnvironment.getHardwareMetadata());

		for (String key : hardwareMetadata.keySet()) {
			if (Objects.equals(key, "cpu.total.cores") ||
				Objects.equals(key, "fs.root")) {

				continue;
			}

			String value = hardwareMetadata.get(key);

			if (Validator.isNull(value)) {
				continue;
			}

			double gigabytes = GetterUtil.getDouble(value) / 1073741824;

			hardwareMetadata.put(key, decimalFormat.format(gigabytes) + " GB");
		}

		jsonObject.put("hardwareMetadata", toJSONArray(hardwareMetadata));

		Map<String, String> liferayMetadata = new TreeMap<>();

		ResourceBundle resourceBundle =
			_resourceBundleLoader.loadResourceBundle(locale.getLanguage());

		liferayMetadata.put(
			LanguageUtil.get(resourceBundle, "lcs-server-key"),
			lcsClusterNode.getKey());
		liferayMetadata.put(
			LanguageUtil.get(resourceBundle, "portal-build-number"),
			String.valueOf(lcsClusterNode.getBuildNumber()));
		liferayMetadata.put(
			LanguageUtil.get(resourceBundle, "portal-edition"),
			lcsClusterNode.getPortalEdition());

		if (Objects.equals(lcsClusterNode.getPortalEdition(), "EE")) {
			liferayMetadata.put(
				LanguageUtil.get(resourceBundle, "patching-tool-version"),
				String.valueOf(lcsClusterNode.getPatchingToolVersion()));
		}

		Map<String, String> baseSoftwareMetadata = new TreeMap<>();
		Map<String, String> javaMetadata = new TreeMap<>();
		List<String[]> javaOptions = new ArrayList<>();

		Map<String, String> softwareMetadata = new TreeMap<>(
			lcsClusterNodeInstallationEnvironment.getSoftwareMetadata());

		for (String key : softwareMetadata.keySet()) {
			if (Objects.equals(key, "lcs.portlet.build.number")) {
				liferayMetadata.put(
					LanguageUtil.get(
						resourceBundle, "lcs-portlet-build-number"),
					String.valueOf(softwareMetadata.get(key)));

				continue;
			}

			if (!key.startsWith("java")) {
				baseSoftwareMetadata.put(key, softwareMetadata.get(key));

				continue;
			}

			if (!Objects.equals(key, "java.input.arguments")) {
				javaMetadata.put(key, softwareMetadata.get(key));

				continue;
			}

			String javaInputArguments = softwareMetadata.get(key);

			javaInputArguments = StringUtil.replaceFirst(
				javaInputArguments, CharPool.DASH, StringPool.BLANK);

			String[] javaInputArgumentsArray = javaInputArguments.split(
				StringPool.COMMA + StringPool.DASH);

			for (String javaInputArgument : javaInputArgumentsArray) {
				String[] javaOption = javaInputArgument.split(
					StringPool.EQUAL, -1);

				if (javaInputArgument.contains("agentlib") ||
					javaInputArgument.contains("javaagent")) {

					javaOption = javaInputArgument.split(StringPool.COLON, 2);
				}

				if (javaOption.length == 1) {
					javaOption =
						new String[] {javaInputArgument, StringPool.BLANK};
				}

				javaOptions.add(javaOption);
			}
		}

		jsonObject.put("javaMetadata", toJSONArray(javaMetadata));
		jsonObject.put("javaOptions", toJSONArray(javaOptions));
		jsonObject.put("liferayMetadata", toJSONArray(liferayMetadata));
		jsonObject.put("softwareMetadata", toJSONArray(baseSoftwareMetadata));

		return jsonObject;
	}

	public List<LCSStatsLayoutMetricsEvents> getLCSStatsLayoutMetricsEventsList(
		long companyId, Date endDate, long groupId, String key,
		String layoutName, int timeSlot) {

		return (List<LCSStatsLayoutMetricsEvents>)
			getLCSStatsLayoutMetricsEventsService(timeSlot).
				getLCSStatsLayoutMetricsEventsList(
					companyId, endDate, groupId, key, layoutName);
	}

	public Object[] getLiferayMultiVMMetricsObjectArray(
			long layoutLCSClusterNodeId)
		throws PortalException {

		List<LCSClusterNodeLiferayVMMetrics> liferayEntityCacheMetricsList =
			new ArrayList<>();
		List<LCSClusterNodeLiferayVMMetrics> liferayFinderCacheMetricsList =
			new ArrayList<>();
		List<LCSClusterNodeLiferayVMMetrics> liferayOtherCacheMetricsList =
			new ArrayList<>();

		LCSClusterNode lcsClusterNode =
			_lcsClusterNodeService.getLCSClusterNode(layoutLCSClusterNodeId);

		List<? extends LCSClusterNodeLiferayVMMetrics>
			lcsClusterNodeLiferayVMMetricsList =
				_lcsClusterNodeLiferayVMMetricsService.
					getLCSClusterNodeLiferayVMMetricsList(
						lcsClusterNode.getKey(), "liferayMultiVMMetrics");

		for (LCSClusterNodeLiferayVMMetrics lcsClusterNodeLiferayVMMetrics :
				lcsClusterNodeLiferayVMMetricsList) {

			String name = lcsClusterNodeLiferayVMMetrics.getName();

			if (name.contains("Entity")) {
				name = name.replace(
					"com.liferay.portal.kernel.dao.orm.EntityCache.", "");

				lcsClusterNodeLiferayVMMetrics.setName(name);

				liferayEntityCacheMetricsList.add(
					lcsClusterNodeLiferayVMMetrics);
			}
			else if (name.contains("Finder")) {
				name = name.replace(
					"com.liferay.portal.kernel.dao.orm.FinderCache.", "");

				lcsClusterNodeLiferayVMMetrics.setName(name);

				liferayFinderCacheMetricsList.add(
					lcsClusterNodeLiferayVMMetrics);
			}
			else {
				liferayOtherCacheMetricsList.add(
					lcsClusterNodeLiferayVMMetrics);
			}
		}

		return new Object[] {
			liferayEntityCacheMetricsList, liferayFinderCacheMetricsList,
			liferayOtherCacheMetricsList
		};
	}

	public List<LCSClusterNodeLiferayVMMetrics>
			getLiferaySingleVMMetricsList(long layoutLCSClusterNodeId)
		throws PortalException {

		LCSClusterNode lcsClusterNode =
			_lcsClusterNodeService.getLCSClusterNode(layoutLCSClusterNodeId);

		return (List<LCSClusterNodeLiferayVMMetrics>)
			_lcsClusterNodeLiferayVMMetricsService.
				getLCSClusterNodeLiferayVMMetricsList(
					lcsClusterNode.getKey(), "liferaySingleVMMetrics");
	}

	public JSONObject getPagesMetricsJSONObject(
		long companyId, Date endDate, long groupId, String key,
		String layoutName, int period, TimeZone timeZone) {

		List<LCSStatsLayoutMetricsEvents>
			summaryLCSStatsLayoutMetricsEventsList =
				getSummaryLCSStatsLayoutMetricsEventsList(
					companyId, endDate, groupId, key, period);

		if (summaryLCSStatsLayoutMetricsEventsList.isEmpty()) {
			return JSONFactoryUtil.createJSONObject();
		}

		List<LCSStatsLayoutMetricsEvents> lcsStatsLayoutMetricsEventsList =
			getLCSStatsLayoutMetricsEventsList(
				companyId, endDate, groupId, key, layoutName, period);

		if (lcsStatsLayoutMetricsEventsList.isEmpty()) {
			return JSONFactoryUtil.createJSONObject();
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject loadTimesJSONObject = JSONFactoryUtil.createJSONObject();

		JSONArray averagesJSONArray = JSONFactoryUtil.createJSONArray();
		JSONArray pageViewsJSONArray = JSONFactoryUtil.createJSONArray();
		JSONArray rangesJSONArray = JSONFactoryUtil.createJSONArray();
		JSONArray standardDeviationsJSONArray =
			JSONFactoryUtil.createJSONArray();

		Map<Long, LCSStatsLayoutMetricsEvents> lcsStatsLayoutMetricsEventsMap =
			new HashMap<>();

		for (LCSStatsLayoutMetricsEvents lcsStatsLayoutMetricsEvents :
				lcsStatsLayoutMetricsEventsList) {

			Date modifiedDate = lcsStatsLayoutMetricsEvents.getModifiedDate();

			lcsStatsLayoutMetricsEventsMap.put(
				modifiedDate.getTime(), lcsStatsLayoutMetricsEvents);
		}

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(endDate);

		calendar.add(Calendar.MINUTE, -period);

		int resolution = 30;

		if (period == 1440) {
			resolution = 24;
		}
		else if (period == 10080) {
			resolution = 168;
		}

		do {
			LCSStatsLayoutMetricsEvents lcsStatsLayoutMetricsEvents =
				lcsStatsLayoutMetricsEventsMap.get(calendar.getTimeInMillis());

			int average = -1;
			int max = -1;
			int min = -1;
			int sampleCount = -1;
			int standardDeviation = -1;

			if (lcsStatsLayoutMetricsEvents != null) {
				average = lcsStatsLayoutMetricsEvents.getAverage();
				max = lcsStatsLayoutMetricsEvents.getMax();
				min = lcsStatsLayoutMetricsEvents.getMin();
				sampleCount = lcsStatsLayoutMetricsEvents.getSampleCount();
				standardDeviation =
					lcsStatsLayoutMetricsEvents.getStandardDeviation();
			}

			long timestamp =
				calendar.getTimeInMillis() + timeZone.getRawOffset();

			JSONArray averageJSONArray = JSONFactoryUtil.createJSONArray();

			averageJSONArray.put(timestamp);
			averageJSONArray.put(average);

			averagesJSONArray.put(averageJSONArray);

			JSONArray rangeJSONArray = JSONFactoryUtil.createJSONArray();

			rangeJSONArray.put(timestamp);
			rangeJSONArray.put(min);
			rangeJSONArray.put(max);

			rangesJSONArray.put(rangeJSONArray);

			JSONArray sampleCountsJSONArray = JSONFactoryUtil.createJSONArray();

			sampleCountsJSONArray.put(timestamp);
			sampleCountsJSONArray.put(sampleCount);

			pageViewsJSONArray.put(sampleCountsJSONArray);

			standardDeviationsJSONArray.put(standardDeviation);

			calendar.add(Calendar.MINUTE, period / resolution);
		}
		while (calendar.getTimeInMillis() < endDate.getTime());

		loadTimesJSONObject.put("averages", averagesJSONArray);
		loadTimesJSONObject.put("ranges", rangesJSONArray);
		loadTimesJSONObject.put(
			"standardDeviations", standardDeviationsJSONArray);

		jsonObject.put("loadTimes", loadTimesJSONObject);
		jsonObject.put("pageViews", pageViewsJSONArray);

		JSONArray summaryJSONArray = JSONFactoryUtil.createJSONArray();

		for (LCSStatsLayoutMetricsEvents lcsStatsLayoutMetricsEvents :
				summaryLCSStatsLayoutMetricsEventsList) {

			JSONObject summaryJSONObject = JSONFactoryUtil.createJSONObject();

			summaryJSONObject.put(
				"avgLoadTime", lcsStatsLayoutMetricsEvents.getAverage());
			summaryJSONObject.put(
				"maxLoadTime", lcsStatsLayoutMetricsEvents.getMax());
			summaryJSONObject.put(
				"minLoadTime", lcsStatsLayoutMetricsEvents.getMin());

			String name = lcsStatsLayoutMetricsEvents.getName();

			summaryJSONObject.put("name", name);

			summaryJSONObject.put(
				"pageViews", lcsStatsLayoutMetricsEvents.getSampleCount());

			if (Objects.equals(name, LCSConstants.ALL_PORTAL_OBJECTS_NAME)) {
				jsonObject.put("allPagesSummary", summaryJSONObject);

				continue;
			}

			summaryJSONArray.put(summaryJSONObject);
		}

		jsonObject.put("summary", summaryJSONArray);

		return jsonObject;
	}

	public JSONObject getPortalPropertiesDifferenceJSONObject(String key)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONArray portalPropertiesDifferenceJSONArray =
			_portalPropertiesAdvisor.getPortalPropertiesDifference(key);

		jsonObject.put(
			LCSConstants.JSON_KEY_DATA, portalPropertiesDifferenceJSONArray);

		jsonObject.put(
			LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_SUCCESS);

		return jsonObject;
	}

	public String getTimeZoneLabel(Locale locale, TimeZone timeZone) {
		int offset = timeZone.getRawOffset();

		if (timeZone.inDaylightTime(new Date())) {
			offset = offset + timeZone.getDSTSavings();
		}

		StringBundler sb = new StringBundler((offset > 0) ? 10 : 9);

		sb.append(timeZone.getDisplayName());
		sb.append(StringPool.SPACE);
		sb.append(StringPool.OPEN_PARENTHESIS);
		sb.append(StringPool.UTC);
		sb.append(StringPool.SPACE);

		if (offset > 0) {
			sb.append(StringPool.PLUS);
		}

		NumberFormat numberFormat = NumberFormat.getInstance(locale);

		numberFormat.setMinimumIntegerDigits(2);

		String offsetHour = numberFormat.format(offset / Time.HOUR);

		sb.append(offsetHour);

		sb.append(StringPool.COLON);

		String offsetMinute = numberFormat.format(
			Math.abs(offset % Time.HOUR) / Time.MINUTE);

		sb.append(offsetMinute);

		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	@Reference(unbind = "-")
	public void setLCSClusterNodeDetailsService(
		LCSClusterNodeDetailsService lcsClusterNodeDetailsService) {

		_lcsClusterNodeDetailsService = lcsClusterNodeDetailsService;
	}

	public void setLCSClusterNodeHibernateMetricsService(
		LCSClusterNodeHibernateMetricsService
			lcsClusterNodeHibernateMetricsService) {

		_lcsClusterNodeHibernateMetricsService =
			lcsClusterNodeHibernateMetricsService;
	}

	public void setLCSClusterNodeInstallationEnvironmentService(
		LCSClusterNodeInstallationEnvironmentService
			lcsClusterNodeInstallationEnvironmentService) {

		_lcsClusterNodeInstallationEnvironmentService =
			lcsClusterNodeInstallationEnvironmentService;
	}

	public void setLCSClusterNodeJVMMetricsService(
		LCSClusterNodeJVMMetricsService lcsClusterNodeJVMMetricsService) {

		_lcsClusterNodeJVMMetricsService = lcsClusterNodeJVMMetricsService;
	}

	public void setLCSClusterNodeLiferayVMMetricsService(
		LCSClusterNodeLiferayVMMetricsService
			lcsClusterNodeLiferayVMMetricsService) {

		_lcsClusterNodeLiferayVMMetricsService =
			lcsClusterNodeLiferayVMMetricsService;
	}

	@Reference(unbind = "-")
	public void setLCSClusterNodeService(
		LCSClusterNodeService lcsClusterNodeService) {

		_lcsClusterNodeService = lcsClusterNodeService;
	}

	public void setLCSClusterNodeSiteService(
		LCSClusterNodeSiteService lcsClusterNodeSiteService) {

		_lcsClusterNodeSiteService = lcsClusterNodeSiteService;
	}

	public void setLCSStatsLayoutMetricsEventsService(
		LCSStatsLayoutMetricsEventsService lcsStatsLayoutMetricsEventsService) {

		_lcsStatsLayoutMetricsEventsService =
			lcsStatsLayoutMetricsEventsService;
	}

	public void setLCSStatsLayoutMetricsEventsService60(
		LCSStatsLayoutMetricsEventsService
			lcsStatsLayoutMetricsEventsService60) {

		_lcsStatsLayoutMetricsEventsService60 =
			lcsStatsLayoutMetricsEventsService60;
	}

	public void setLCSStatsLayoutMetricsEventsService1440(
		LCSStatsLayoutMetricsEventsService
			lcsStatsLayoutMetricsEventsService1440) {

		_lcsStatsLayoutMetricsEventsService1440 =
			lcsStatsLayoutMetricsEventsService1440;
	}

	public void setLCSStatsLayoutMetricsEventsService10080(
		LCSStatsLayoutMetricsEventsService
			lcsStatsLayoutMetricsEventsService10080) {

		_lcsStatsLayoutMetricsEventsService10080 =
			lcsStatsLayoutMetricsEventsService10080;
	}

	@Reference(unbind = "-")
	public void setPortalPropertiesAdvisor(
		PortalPropertiesAdvisor portalPropertiesAdvisor) {

		_portalPropertiesAdvisor = portalPropertiesAdvisor;
	}

	@Reference(unbind = "-")
	public void setResourceBundleLoader(
		ResourceBundleLoader resourceBundleLoader) {

		_resourceBundleLoader = resourceBundleLoader;
	}

	protected LCSStatsLayoutMetricsEventsService
		getLCSStatsLayoutMetricsEventsService(int timeSlot) {

		if (timeSlot == 10080) {
			return _lcsStatsLayoutMetricsEventsService10080;
		}

		if (timeSlot == 1440) {
			return _lcsStatsLayoutMetricsEventsService1440;
		}

		if (timeSlot == 60) {
			return _lcsStatsLayoutMetricsEventsService60;
		}

		return null;
	}

	protected List<LCSStatsLayoutMetricsEvents>
		getSummaryLCSStatsLayoutMetricsEventsList(
			long companyId, Date endDate, long groupId, String key,
			int timeSlot) {

		return (List<LCSStatsLayoutMetricsEvents>)
			getLCSStatsLayoutMetricsEventsService(timeSlot).
				getSummaryLCSStatsLayoutMetricsEventsList(
					companyId, endDate, groupId, key);
	}

	protected JSONArray toJSONArray(List<String[]> arrays) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (String[] array : arrays) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("name", array[0]);
			jsonObject.put("value", array[1]);

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	protected JSONArray toJSONArray(Map<String, String> map) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (Map.Entry<String, String> entry : map.entrySet()) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("name", entry.getKey());
			jsonObject.put("value", entry.getValue());

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	private static LCSStatsLayoutMetricsEventsService
		_lcsStatsLayoutMetricsEventsService60;
	private static LCSStatsLayoutMetricsEventsService
		_lcsStatsLayoutMetricsEventsService1440;
	private static LCSStatsLayoutMetricsEventsService
		_lcsStatsLayoutMetricsEventsService10080;

	private LCSClusterNodeDetailsService _lcsClusterNodeDetailsService;
	private LCSClusterNodeHibernateMetricsService
		_lcsClusterNodeHibernateMetricsService;
	private LCSClusterNodeInstallationEnvironmentService
		_lcsClusterNodeInstallationEnvironmentService;
	private LCSClusterNodeJVMMetricsService _lcsClusterNodeJVMMetricsService;
	private LCSClusterNodeLiferayVMMetricsService
		_lcsClusterNodeLiferayVMMetricsService;
	private LCSClusterNodeService _lcsClusterNodeService;
	private LCSClusterNodeSiteService _lcsClusterNodeSiteService;
	private LCSStatsLayoutMetricsEventsService
		_lcsStatsLayoutMetricsEventsService;
	private PortalPropertiesAdvisor _portalPropertiesAdvisor;
	private ResourceBundleLoader _resourceBundleLoader;

	private static class LCSClusterNodeSitesComaparator
		implements Comparator<LCSClusterNodeSite> {

		@Override
		public int compare(
			LCSClusterNodeSite lcsClusterNodeSite1,
			LCSClusterNodeSite lcsClusterNodeSite2) {

			String friendlyURL1 = lcsClusterNodeSite1.getFriendlyURL();
			String friendlyURL2 = lcsClusterNodeSite2.getFriendlyURL();

			Boolean organizationSite1 = SiteType.isOrganization(
				lcsClusterNodeSite1.getType());
			Boolean organizationSite2 = SiteType.isOrganization(
				lcsClusterNodeSite2.getType());

			if (organizationSite1 != organizationSite2) {
				return organizationSite1.compareTo(organizationSite2);
			}

			return friendlyURL1.compareTo(friendlyURL2);
		}

	}

}