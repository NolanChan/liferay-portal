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
import com.liferay.osb.lcs.model.LCSSubscriptionEntry;
import com.liferay.osb.lcs.report.Report;
import com.liferay.osb.lcs.report.ReportContext;
import com.liferay.osb.lcs.report.ReportFactory;
import com.liferay.osb.lcs.report.ReportFactoryUtil;
import com.liferay.osb.lcs.service.LCSClusterEntryServiceUtil;
import com.liferay.osb.lcs.service.LCSSubscriptionEntryServiceUtil;
import com.liferay.osb.lcs.subscriptions.util.SubscriptionsUtil;
import com.liferay.osb.lcs.util.ApplicationProfile;
import com.liferay.osb.lcs.util.AuthUtil;
import com.liferay.osb.lcs.util.PortletKeys;
import com.liferay.osb.lcs.util.PortletPropsValues;
import com.liferay.osb.lcs.util.WebKeys;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import org.osgi.service.component.annotations.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.util.Calendar;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletConfig;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Marko Cikos
 * @author Matija Petanjek
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + PortletKeys.SUBSCRIPTIONS,
		"javax.portlet.display-name=Subscriptions",
		"javax.portlet.init-param.copy-request-parameters=true",
		"javax.portlet.init-param.template-path=/subscriptions/",
		"javax.portlet.init-param.view-template=/subscriptions/view.jsp",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.mime-type=text/html",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.info.title=Subscriptions",
		"javax.portlet.info.short-title=Subscriptions",
		"javax.portlet.info.keywords=Subscriptions",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"javax.portlet.supported-public-render-parameter=layoutLCSProjectId",
		"com.liferay.portlet.footer-portlet-javascript=/js/jquery.min.js",
		"com.liferay.portlet.footer-portlet-javascript=/js/highcharts-base.min.js",
		"com.liferay.portlet.footer-portlet-javascript=/js/lcs-base.js",
		"com.liferay.portlet.footer-portlet-javascript=/js/lcs-subscription-type-cell-editor.js",
		"com.liferay.portlet.footer-portlet-javascript=/js/lcs-subscriptions.js",
		"com.liferay.portlet.css-class-wrapper=osb-lcs-portlet osb-lcs-portlet-subscriptions",
		"com.liferay.portlet.display-category=category.lcs"
	},
	service = Portlet.class
)
public class SubscriptionsPortlet extends MVCPortlet {

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		try {
			AuthUtil.checkAuthToken(resourceRequest);

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

	protected void downloadLCSClusterNodeUptimesDelimitedReport(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		Report report = ReportFactoryUtil.getReport(
			ReportFactory.Type.LCS_CLUSTER_NODE_UPTIMES_DELIMITED);

		ByteArrayOutputStream byteArrayOutputStream = report.process(
			new ReportContext(
				getPortletConfig(), getPortletContext(), resourceRequest));

		writeFile(
			resourceResponse, byteArrayOutputStream, "uptimes.csv", "text/csv");
	}

	protected void downloadLCSClusterNodeUptimesInvoicePDFReport(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		Report report = ReportFactoryUtil.getReport(
			ReportFactory.Type.LCS_CLUSTER_NODE_UPTIMES_INVOICE_PDF);

		ByteArrayOutputStream byteArrayOutputStream = report.process(
			new ReportContext(
				getPortletConfig(), getPortletContext(), resourceRequest));

		writeFile(
			resourceResponse, byteArrayOutputStream, "bill.pdf",
			"application/pdf");
	}

	protected void downloadLCSClusterNodeUptimesPDFReport(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		Report report = ReportFactoryUtil.getReport(
			ReportFactory.Type.LCS_CLUSTER_NODE_UPTIMES_PDF);

		ByteArrayOutputStream byteArrayOutputStream = report.process(
			new ReportContext(
				getPortletConfig(), getPortletContext(), resourceRequest));

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

		int[] periodArray = SubscriptionsUtil.parsePeriod(period);

		JSONObject billsJSONObject = SubscriptionsUtil.getBillsJSONObject(
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

		JSONObject billsJSONObject = SubscriptionsUtil.getBillsJSONObject(
			lcsProjectId, billsCalendar.get(Calendar.MONTH),
			billsCalendar.get(Calendar.YEAR), themeDisplay.getLocale());

		dataJSONObject.put("bills", billsJSONObject);

		JSONObject lcsClusterNodeUptimesJSONObject =
			SubscriptionsUtil.getLCSClusterNodeUptimesJSONObject(
				LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID, lcsProjectId,
				billsCalendar.get(Calendar.MONTH),
				billsCalendar.get(Calendar.YEAR), themeDisplay.getLocale());

		dataJSONObject.put(
			"lcsClusterNodeUptimes", lcsClusterNodeUptimesJSONObject);

		Calendar paymentsCalendar = (Calendar)billsCalendar.clone();

		paymentsCalendar.add(Calendar.MONTH, -5);

		JSONArray paymentsJSONArray = SubscriptionsUtil.getPaymentsJSONArray(
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

		int[] periodArray = SubscriptionsUtil.parsePeriod(period);

		JSONObject lcsClusterNodeUptimesJSONObject =
			SubscriptionsUtil.getLCSClusterNodeUptimesJSONObject(
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

		int[] startPeriodArray = SubscriptionsUtil.parsePeriod(startPeriod);
		int[] endPeriodArray = SubscriptionsUtil.parsePeriod(endPeriod);

		JSONArray jsonArray = SubscriptionsUtil.getPaymentsJSONArray(
			lcsProjectId, startPeriodArray[1], startPeriodArray[0],
			endPeriodArray[1], endPeriodArray[0]);

		jsonObject.put(LCSConstants.JSON_KEY_DATA, jsonArray);
		jsonObject.put(
			LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_SUCCESS);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
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
			SubscriptionsUtil.hasElasticSubscription(lcsProjectId));

		PortletConfig portletConfig = getPortletConfig();

		JSONArray lcsClusterEntriesJSONArray =
			SubscriptionsUtil.getLCSClusterEntriesJSONArray(
				lcsProjectId, themeDisplay.getLocale());

		dataJSONObject.put("lcsClusterEntries", lcsClusterEntriesJSONArray);

		JSONArray lcsClusterNodesJSONArray =
			SubscriptionsUtil.getLCSClusterNodesJSONArray(
				lcsProjectId, themeDisplay.getLocale());

		dataJSONObject.put("lcsClusterNodes", lcsClusterNodesJSONArray);

		if (PortletPropsValues.APPLICATION_PROFILE !=
				ApplicationProfile.PRODUCTION) {

			boolean refresh = ParamUtil.getBoolean(resourceRequest, "refresh");

			if (refresh) {
				LCSSubscriptionEntryServiceUtil.
					refreshLCSProjectLCSSubscriptionEntries(lcsProjectId);
			}
		}

		List<LCSSubscriptionEntry> lcsOrderEntries =
			LCSSubscriptionEntryServiceUtil.getLCSProjectLCSSubscriptionEntries(
				lcsProjectId);

		JSONArray lcsSubscriptionEntriesJSONArray =
			SubscriptionsUtil.getLCSSubscriptionEntriesJSONArray(
				lcsOrderEntries, themeDisplay.getLocale(),
				themeDisplay.getTimeZone());

		dataJSONObject.put("subscriptions", lcsSubscriptionEntriesJSONArray);

		JSONArray subscriptionsUsageJSONArray =
			SubscriptionsUtil.getSubscriptionsUsageJSONArray(
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
			SubscriptionsUtil.hasElasticSubscription(lcsProjectId));

		LCSClusterEntryServiceUtil.updateSubscriptionType(
			lcsClusterEntryId, subscriptionType);

		if (SubscriptionsUtil.hasElasticSubscription(lcsProjectId)) {
			boolean elastic = ParamUtil.getBoolean(resourceRequest, "elastic");

			LCSClusterEntryServiceUtil.updateElastic(
				lcsClusterEntryId, elastic);
		}

		PortletConfig portletConfig = getPortletConfig();

		JSONArray lcsClusterEntriesJSONArray =
			SubscriptionsUtil.getLCSClusterEntriesJSONArray(
				lcsProjectId, themeDisplay.getLocale());

		dataJSONObject.put("lcsClusterEntries", lcsClusterEntriesJSONArray);

		JSONArray subscriptionsUsageJSONArray =
			SubscriptionsUtil.getSubscriptionsUsageJSONArray(
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

	private static Log _log = LogFactoryUtil.getLog(SubscriptionsPortlet.class);

}