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

package com.liferay.osb.lcs.subscriptions.portlet;

import com.liferay.lcs.util.LCSConstants;
import com.liferay.osb.lcs.configuration.OSBLCSConfiguration;
import com.liferay.osb.lcs.constants.OSBLCSPortletKeys;
import com.liferay.osb.lcs.model.LCSSubscriptionEntry;
import com.liferay.osb.lcs.report.Report;
import com.liferay.osb.lcs.report.ReportContext;
import com.liferay.osb.lcs.service.LCSClusterEntryService;
import com.liferay.osb.lcs.service.LCSSubscriptionEntryService;
import com.liferay.osb.lcs.util.ApplicationProfile;
import com.liferay.osb.lcs.advisor.SubscriptionsAdvisor;
import com.liferay.osb.lcs.report.ReportFactory;
import com.liferay.osb.lcs.report.ReportFactoryUtil;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletContext;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Marko Cikos
 * @author Matija Petanjek
 */
@Component(
	configurationPid = "com.liferay.osb.lcs.configuration.OSBLCSConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=osb-lcs-portlet osb-lcs-portlet-subscriptions" + OSBLCSPortletKeys.SUBSCRIPTIONS,
		"com.liferay.portlet.display-category=category.lcs",
		"com.liferay.portlet.footer-portlet-javascript=/js/jquery.min.js",
		"com.liferay.portlet.footer-portlet-javascript=/js/highcharts-base.min.js",
		"com.liferay.portlet.footer-portlet-javascript=/js/lcs-base.js",
		"com.liferay.portlet.footer-portlet-javascript=/js/lcs-subscription-type-cell-editor.js",
		"com.liferay.portlet.footer-portlet-javascript=/js/lcs-subscriptions.js",
		"javax.portlet.display-name=Subscriptions",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.info.keywords=Subscriptions",
		"javax.portlet.info.short-title=Subscriptions",
		"javax.portlet.info.title=Subscriptions",
		"javax.portlet.init-param.copy-request-parameters=true",
		"javax.portlet.init-param.template-path=/subscriptions/",
		"javax.portlet.init-param.view-template=/subscriptions/view.jsp",
		"javax.portlet.mime-type=text/html", "javax.portlet.name=",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supported-public-render-parameter=layoutLCSProjectId"
	},
	service = Portlet.class
)
public class SubscriptionsPortlet extends MVCPortlet {

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		try {
			AuthTokenUtil.checkCSRFToken(
				PortalUtil.getHttpServletRequest(resourceRequest),
				SubscriptionsPortlet.class.getName());

			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals(
					"downloadLCSClusterNodeUptimesDelimitedReport")) {

				downloadLCSClusterNodeUptimesDelimitedReport(
					resourceRequest, resourceResponse);
			}
			else if (resourceID.equals(
						"downloadLCSClusterNodeUptimesInvoicePDFReport")) {

				downloadLCSClusterNodeUptimesInvoicePDFReport(
					resourceRequest, resourceResponse);
			}
			else if (resourceID.equals(
						"downloadLCSClusterNodeUptimesPDFReport")) {

				downloadLCSClusterNodeUptimesPDFReport(
					resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("getBills")) {
				getBills(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("getElasticSubscriptionsDefaults")) {
				getElasticSubscriptionsDefaults(
					resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("getLCSClusterNodeUptimes")) {
				getLCSClusterNodeUptimes(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("getPayments")) {
				getPayments(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("getSubscriptionsDetails")) {
				getSubscriptionsDetails(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("updateLCSClusterEntry")) {
				updateLCSClusterEntry(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			String message = LanguageUtil.get(
				resourceRequest.getLocale(), "your-request-failed-to-complete");

			jsonObject.put(LCSConstants.JSON_KEY_MESSAGE, message);

			jsonObject.put(
				LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_FAILURE);

			writeJSON(resourceRequest, resourceResponse, jsonObject);
		}
	}

	public void setLCSClusterEntryService(
		LCSClusterEntryService lcsClusterEntryService) {

		_lcsClusterEntryService = lcsClusterEntryService;
	}

	public void setLCSSubscriptionEntryService(
		LCSSubscriptionEntryService lcsSubscriptionEntryService) {

		_lcsSubscriptionEntryService = lcsSubscriptionEntryService;
	}

	public void setSubscriptionsAdvisor(
		SubscriptionsAdvisor subscriptionsAdvisor) {

		_subscriptionsAdvisor = subscriptionsAdvisor;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_osbLCSConfiguration = ConfigurableUtil.createConfigurable(
			OSBLCSConfiguration.class, properties);
	}

	@Deactivate
	protected void deactivate() {
		_osbLCSConfiguration = null;
	}

	protected void downloadLCSClusterNodeUptimesDelimitedReport(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		Report report = ReportFactoryUtil.getReport(
			ReportFactory.Type.LCS_CLUSTER_NODE_UPTIMES_DELIMITED);

		ReportContext.ReportContextBuilder reportContextBuilder =
			new ReportContext.ReportContextBuilder();

		reportContextBuilder.lcsClusterNodeId(
			ParamUtil.getLong(resourceRequest, "lcsClusterNodeId"));
		reportContextBuilder.locale(resourceRequest.getLocale());

		ByteArrayOutputStream byteArrayOutputStream = report.process(
			reportContextBuilder.build());

		writeFile(
			resourceResponse, byteArrayOutputStream, "uptimes.csv", "text/csv");
	}

	protected void downloadLCSClusterNodeUptimesInvoicePDFReport(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		Report report = ReportFactoryUtil.getReport(
			ReportFactory.Type.LCS_CLUSTER_NODE_UPTIMES_INVOICE_PDF);

		ReportContext.ReportContextBuilder reportContextBuilder =
			new ReportContext.ReportContextBuilder();

		reportContextBuilder.lcsClusterNodeId(
			ParamUtil.getLong(resourceRequest, "lcsClusterNodeId"));
		reportContextBuilder.locale(resourceRequest.getLocale());
		reportContextBuilder.reportDependenciesPath(
			getReportDependenciesPath());

		ByteArrayOutputStream byteArrayOutputStream = report.process(
			reportContextBuilder.build());

		writeFile(
			resourceResponse, byteArrayOutputStream, "bill.pdf",
			"application/pdf");
	}

	protected void downloadLCSClusterNodeUptimesPDFReport(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		Report report = ReportFactoryUtil.getReport(
			ReportFactory.Type.LCS_CLUSTER_NODE_UPTIMES_PDF);

		ReportContext.ReportContextBuilder reportContextBuilder =
			new ReportContext.ReportContextBuilder();

		reportContextBuilder.lcsClusterNodeId(
			ParamUtil.getLong(resourceRequest, "lcsClusterNodeId"));
		reportContextBuilder.locale(resourceRequest.getLocale());
		reportContextBuilder.reportDependenciesPath(
			getReportDependenciesPath());

		ByteArrayOutputStream byteArrayOutputStream = report.process(
			reportContextBuilder.build());

		writeFile(
			resourceResponse, byteArrayOutputStream, "uptimes.pdf",
			"application/pdf");
	}

	protected void getBills(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long lcsProjectId = ParamUtil.getLong(resourceRequest, "lcsProjectId");
		String period = ParamUtil.getString(resourceRequest, "period");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		int[] periodArray = _subscriptionsAdvisor.parsePeriod(period);

		JSONObject billsJSONObject = _subscriptionsAdvisor.getBillsJSONObject(
			lcsProjectId, periodArray[1], periodArray[0],
			themeDisplay.getLocale());

		jsonObject.put(LCSConstants.JSON_KEY_DATA, billsJSONObject);

		jsonObject.put(
			LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_SUCCESS);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void getElasticSubscriptionsDefaults(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long lcsProjectId = ParamUtil.getLong(resourceRequest, "lcsProjectId");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject dataJSONObject = JSONFactoryUtil.createJSONObject();

		Calendar billsCalendar = Calendar.getInstance();

		JSONObject billsJSONObject = _subscriptionsAdvisor.getBillsJSONObject(
			lcsProjectId, billsCalendar.get(Calendar.MONTH),
			billsCalendar.get(Calendar.YEAR), themeDisplay.getLocale());

		dataJSONObject.put("bills", billsJSONObject);

		JSONObject lcsClusterNodeUptimesJSONObject =
			_subscriptionsAdvisor.getLCSClusterNodeUptimesJSONObject(
				LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID, lcsProjectId,
				billsCalendar.get(Calendar.MONTH),
				billsCalendar.get(Calendar.YEAR), themeDisplay.getLocale());

		dataJSONObject.put(
			"lcsClusterNodeUptimes", lcsClusterNodeUptimesJSONObject);

		Calendar paymentsCalendar = (Calendar)billsCalendar.clone();

		paymentsCalendar.add(Calendar.MONTH, -5);

		JSONArray paymentsJSONArray =
			_subscriptionsAdvisor.getPaymentsJSONArray(
				lcsProjectId, paymentsCalendar.get(Calendar.MONTH),
				paymentsCalendar.get(Calendar.YEAR),
				billsCalendar.get(Calendar.MONTH),
				billsCalendar.get(Calendar.YEAR));

		dataJSONObject.put("payments", paymentsJSONArray);

		jsonObject.put(LCSConstants.JSON_KEY_DATA, dataJSONObject);
		jsonObject.put(
			LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_SUCCESS);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void getLCSClusterNodeUptimes(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long lcsClusterEntryId = ParamUtil.getLong(
			resourceRequest, "lcsClusterEntryId");
		long lcsProjectId = ParamUtil.getLong(resourceRequest, "lcsProjectId");
		String period = ParamUtil.getString(resourceRequest, "period");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		int[] periodArray = _subscriptionsAdvisor.parsePeriod(period);

		JSONObject lcsClusterNodeUptimesJSONObject =
			_subscriptionsAdvisor.getLCSClusterNodeUptimesJSONObject(
				lcsClusterEntryId, lcsProjectId, periodArray[1], periodArray[0],
				themeDisplay.getLocale());

		jsonObject.put(
			LCSConstants.JSON_KEY_DATA, lcsClusterNodeUptimesJSONObject);

		jsonObject.put(
			LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_SUCCESS);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void getPayments(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long lcsProjectId = ParamUtil.getLong(resourceRequest, "lcsProjectId");
		String startPeriod = ParamUtil.getString(
			resourceRequest, "startPeriod");
		String endPeriod = ParamUtil.getString(resourceRequest, "endPeriod");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		int[] startPeriodArray = _subscriptionsAdvisor.parsePeriod(startPeriod);
		int[] endPeriodArray = _subscriptionsAdvisor.parsePeriod(endPeriod);

		JSONArray jsonArray = _subscriptionsAdvisor.getPaymentsJSONArray(
			lcsProjectId, startPeriodArray[1], startPeriodArray[0],
			endPeriodArray[1], endPeriodArray[0]);

		jsonObject.put(LCSConstants.JSON_KEY_DATA, jsonArray);

		jsonObject.put(
			LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_SUCCESS);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected String getReportDependenciesPath() {
		PortletContext portletContext = getPortletContext();

		return portletContext.getRealPath(
			"/WEB-INF/classes/META-INF/resources/com/liferay/osb/lcs/report");
	}

	protected void getSubscriptionsDetails(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long lcsProjectId = ParamUtil.getLong(resourceRequest, "lcsProjectId");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject dataJSONObject = JSONFactoryUtil.createJSONObject();

		dataJSONObject.put(
			"hasElasticSubscription",
			_subscriptionsAdvisor.hasElasticSubscription(lcsProjectId));

		JSONArray lcsClusterEntriesJSONArray =
			_subscriptionsAdvisor.getLCSClusterEntriesJSONArray(
				lcsProjectId, themeDisplay.getLocale());

		dataJSONObject.put("lcsClusterEntries", lcsClusterEntriesJSONArray);

		JSONArray lcsClusterNodesJSONArray =
			_subscriptionsAdvisor.getLCSClusterNodesJSONArray(
				lcsProjectId, themeDisplay.getLocale());

		dataJSONObject.put("lcsClusterNodes", lcsClusterNodesJSONArray);

		if (_osbLCSConfiguration.applicationProfile() !=
				ApplicationProfile.PRODUCTION) {

			boolean refresh = ParamUtil.getBoolean(resourceRequest, "refresh");

			if (refresh) {
				_lcsSubscriptionEntryService.
					refreshLCSProjectLCSSubscriptionEntries(lcsProjectId);
			}
		}

		List<LCSSubscriptionEntry> lcsOrderEntries =
			_lcsSubscriptionEntryService.getLCSProjectLCSSubscriptionEntries(
				lcsProjectId);

		JSONArray lcsSubscriptionEntriesJSONArray =
			_subscriptionsAdvisor.getLCSSubscriptionEntriesJSONArray(
				lcsOrderEntries, themeDisplay.getLocale(),
				themeDisplay.getTimeZone());

		dataJSONObject.put("subscriptions", lcsSubscriptionEntriesJSONArray);

		JSONArray subscriptionsUsageJSONArray =
			_subscriptionsAdvisor.getSubscriptionsUsageJSONArray(
				lcsProjectId, themeDisplay.getLocale());

		dataJSONObject.put("subscriptionsUsage", subscriptionsUsageJSONArray);

		jsonObject.put(LCSConstants.JSON_KEY_DATA, dataJSONObject);
		jsonObject.put(
			LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_SUCCESS);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void updateLCSClusterEntry(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long lcsClusterEntryId = ParamUtil.getLong(
			resourceRequest, "lcsClusterEntryId");
		long lcsProjectId = ParamUtil.getLong(resourceRequest, "lcsProjectId");
		String subscriptionType = ParamUtil.getString(
			resourceRequest, "subscriptionType");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject dataJSONObject = JSONFactoryUtil.createJSONObject();

		dataJSONObject.put(
			"hasElasticSubscription",
			_subscriptionsAdvisor.hasElasticSubscription(lcsProjectId));

		_lcsClusterEntryService.updateSubscriptionType(
			lcsClusterEntryId, subscriptionType);

		if (_subscriptionsAdvisor.hasElasticSubscription(lcsProjectId)) {
			boolean elastic = ParamUtil.getBoolean(resourceRequest, "elastic");

			_lcsClusterEntryService.updateElastic(lcsClusterEntryId, elastic);
		}

		JSONArray lcsClusterEntriesJSONArray =
			_subscriptionsAdvisor.getLCSClusterEntriesJSONArray(
				lcsProjectId, themeDisplay.getLocale());

		dataJSONObject.put("lcsClusterEntries", lcsClusterEntriesJSONArray);

		JSONArray subscriptionsUsageJSONArray =
			_subscriptionsAdvisor.getSubscriptionsUsageJSONArray(
				lcsProjectId, themeDisplay.getLocale());

		dataJSONObject.put("subscriptionsUsage", subscriptionsUsageJSONArray);

		jsonObject.put(LCSConstants.JSON_KEY_DATA, dataJSONObject);
		jsonObject.put(
			LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_SUCCESS);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void writeFile(
			ResourceResponse resourceResponse,
			ByteArrayOutputStream byteArrayOutputStream, String fileName,
			String contentType)
		throws Exception {

		resourceResponse.addProperty(
			HttpHeaders.CONTENT_DISPOSITION,
			HttpHeaders.CONTENT_DISPOSITION_ATTACHMENT + "; filename=" +
				fileName);
		resourceResponse.setContentLength(byteArrayOutputStream.size());
		resourceResponse.setContentType(contentType);

		OutputStream outputStream = resourceResponse.getPortletOutputStream();

		byteArrayOutputStream.writeTo(outputStream);

		outputStream.flush();

		outputStream.close();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SubscriptionsPortlet.class);

	private static volatile OSBLCSConfiguration _osbLCSConfiguration;

	private LCSClusterEntryService _lcsClusterEntryService;
	private LCSSubscriptionEntryService _lcsSubscriptionEntryService;
	private SubscriptionsAdvisor _subscriptionsAdvisor;

}