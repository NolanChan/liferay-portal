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

package com.liferay.osb.lcs.admin.portlet;

import com.liferay.lcs.util.LCSConstants;
import com.liferay.osb.lcs.admin.util.AdminUtil;
import com.liferay.osb.lcs.advisor.PortalPropertiesAdvisor;
import com.liferay.osb.lcs.constants.OSBLCSPortletKeys;
import com.liferay.osb.lcs.messaging.CommandMessageSenderUtil;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.model.LCSMetadata;
import com.liferay.osb.lcs.nosql.service.LCSClusterNodePatchesServiceUtil;
import com.liferay.osb.lcs.nosql.service.LCSMetadataDetailsServiceUtil;
import com.liferay.osb.lcs.osbportlet.util.OSBPortletUtil;
import com.liferay.osb.lcs.report.Report;
import com.liferay.osb.lcs.report.ReportContext;
import com.liferay.osb.lcs.report.ReportFactory;
import com.liferay.osb.lcs.report.ReportFactoryUtil;
import com.liferay.osb.lcs.service.CorpProjectServiceUtil;
import com.liferay.osb.lcs.service.LCSClusterEntryLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSClusterNodeServiceUtil;
import com.liferay.osb.lcs.service.LCSMetadataLocalServiceUtil;
import com.liferay.osb.lcs.service.LCSSubscriptionEntryServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bean.PortletBeanLocatorUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletPreferences;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.osgi.service.component.annotations.Component;

/**
 * @author Igor Beslic
 * @author Marko Cikos
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + OSBLCSPortletKeys.ADMIN,
		"javax.portlet.display-name=LCS Admin",
		"javax.portlet.init-param.template-path=/admin/",
		"javax.portlet.init-param.view-template=/admin/view.jsp",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.mime-type=text/html",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.info.title=LCS Admin",
		"javax.portlet.info.short-title=LCS Admin",
		"javax.portlet.info.keywords=LCS Admin",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user",
		"com.liferay.portlet.control-panel-entry-category=portal",
		"com.liferay.portlet.preferences-company-wide=true",
		"com.liferay.portlet.header-portlet-css=/css/lcs-admin.css",
		"com.liferay.portlet.footer-portlet-javascript=/js/lcs-admin.js",
		"com.liferay.portlet.footer-portlet-javascript=/js/lcs-base.js",
		"com.liferay.portlet.css-class-wrapper=osb-lcs-portlet osb-lcs-portlet-admin",
		"com.liferay.portlet.display-category=category.hidden"
	},
	service = Portlet.class
)
public class AdminPortlet extends MVCPortlet {

	public void addLCSMetadata(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		int buildNumber = ParamUtil.getInteger(actionRequest, "buildNumber");
		String gitTag = ParamUtil.getString(actionRequest, "gitTag");
		String portalEdition = ParamUtil.getString(
			actionRequest, "portalEdition");
		int supportedLCSPortlet = ParamUtil.getInteger(
			actionRequest, "supportedLCSPortlet");
		int supportedPatchingTool = ParamUtil.getInteger(
			actionRequest, "supportedPatchingTool");

		LCSMetadata lcsMetadata = LCSMetadataLocalServiceUtil.addLCSMetadata(
			buildNumber, gitTag, portalEdition, supportedLCSPortlet,
			supportedPatchingTool);

		uploadPortalPropertiesFile(
			actionRequest, lcsMetadata.getLcsMetadataId());
	}

	public void checkLCSProjects(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		if (LCSClusterEntryLocalServiceUtil.getLCSClusterEntriesCount() == 0) {
			SessionErrors.add(actionRequest, "noLCSClusterEntries");

			return;
		}

		long[] localCorpProjectIds =
			CorpProjectServiceUtil.getLocalCorpProjectIds();

		int invalidRemoteCorpProjectsCount =
			OSBPortletUtil.getInvalidRemoteCorpProjectsCount(
				localCorpProjectIds);

		if (invalidRemoteCorpProjectsCount == 0) {
			SessionMessages.add(actionRequest, "synchronized");
		}
		else {
			SessionMessages.add(actionRequest, "notSynchronized");
		}
	}

	public void deleteCache(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		AdminUtil.deleteCache(ParamUtil.getString(actionRequest, "key"));
	}

	public void enableLCSClusterNodeLogging(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		AdminUtil.enableLCSClusterNodeLogging(
			ParamUtil.getString(actionRequest, "key"),
			ParamUtil.getBoolean(actionRequest, "enableLogging"));
	}

	public void resetInstallablePatches(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		LCSClusterNodePatchesServiceUtil.resetInstallablePatches();
	}

	public void resetLCSOrderEntries(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		LCSSubscriptionEntryServiceUtil.
			refreshLCSProjectLCSSubscriptionEntries();
	}

	public void saveLCSPortalPreferences(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		PortletPreferences portletPreferences =
			AdminUtil.getPortletPreferences();

		String billingEnabled = ParamUtil.getString(
			actionRequest, "billingEnabled");

		portletPreferences.setValue("billing-enabled", billingEnabled);

		AdminUtil.enableMessageForward(
			"dev",
			ParamUtil.getBoolean(actionRequest, "messageForwardDevEnabled"));

		AdminUtil.enableMessageForward(
			"qa",
			ParamUtil.getBoolean(actionRequest, "messageForwardQAEnabled"));

		String sendingEmailsEnabled = ParamUtil.getString(
			actionRequest, "sendingEmailsEnabled");

		portletPreferences.setValue(
			"sending-emails-enabled", sendingEmailsEnabled);

		String subscriptionsEnabled = ParamUtil.getString(
			actionRequest, "subscriptionsEnabled");

		portletPreferences.setValue(
			"subscriptions-enabled", subscriptionsEnabled);

		portletPreferences.store();
	}

	public void sendScript(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String key = actionRequest.getParameter("key");

		LCSClusterNode lcsClusterNode =
			LCSClusterNodeServiceUtil.getLCSClusterNode(key);

		String script = actionRequest.getParameter("script");

		CommandMessageSenderUtil.executeScript(lcsClusterNode, script);

		sendRedirect(actionRequest, actionResponse);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException {

		try {
			String resourceId = resourceRequest.getResourceID();

			if (resourceId.equals("downloadLCSClusterNodesDelimitedReport")) {
				downloadLCSClusterNodesDelimitedReport(
					resourceRequest, resourceResponse);
			}
			else if (resourceId.equals(
						"downloadLCSClusterNodeUptimesDelimitedReport")) {

				downloadLCSClusterNodeUptimesDelimitedReport(
					resourceRequest, resourceResponse);
			}
			else if (resourceId.equals(
						"downloadLCSClusterNodeUptimesInvoicePDFReport")) {

				downloadLCSClusterNodeUptimesInvoicePDFReport(
					resourceRequest, resourceResponse);
			}
			else if (resourceId.equals(
						"downloadLCSClusterNodeUptimesPDFReport")) {

				downloadLCSClusterNodeUptimesPDFReport(
					resourceRequest, resourceResponse);
			}
			else if (resourceId.equals("getPortalPropertiesDifference")) {
				getPortalPropertiesDifference(
					resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			if (_log.isErrorEnabled()) {
				_log.error("Unable to generate delimited file", e);
			}
		}
	}

	public void updateLCSMetadata(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long lcsMetadataId = ParamUtil.getLong(actionRequest, "lcsMetadataId");
		int supportedLCSPortlet = ParamUtil.getInteger(
			actionRequest, "supportedLCSPortlet");
		int supportedPatchingTool = ParamUtil.getInteger(
			actionRequest, "supportedPatchingTool");

		LCSMetadataLocalServiceUtil.updateSupportedLCSPortlet(
			lcsMetadataId, supportedLCSPortlet);
		LCSMetadataLocalServiceUtil.updateSupportedPatchingTool(
			lcsMetadataId, supportedPatchingTool);

		uploadPortalPropertiesFile(actionRequest, lcsMetadataId);
	}

	protected void downloadLCSClusterNodesDelimitedReport(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		Report report = ReportFactoryUtil.getReport(
			ReportFactory.Type.LCS_CLUSTER_NODE_DELIMITED);

		ByteArrayOutputStream byteArrayOutputStream = report.process(
			new ReportContext(
				getPortletConfig(), getPortletContext(), resourceRequest));

		writeFile(
			resourceResponse, byteArrayOutputStream,
			"lcs-cluster-node-report-" + System.currentTimeMillis() + ".csv",
			"text/csv");
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
			resourceResponse, byteArrayOutputStream,
			"lcs-cluster-node-uptime-report-" + System.currentTimeMillis() +
				".csv",
			"text/csv");
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
			resourceResponse, byteArrayOutputStream,
			"lcs-cluster-node-uptime-invoice-" + System.currentTimeMillis() +
				".pdf",
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
			resourceResponse, byteArrayOutputStream,
			"lcs-cluster-node-uptime-report-" + System.currentTimeMillis() +
				".pdf",
			"application/pdf");
	}

	protected void getPortalPropertiesDifference(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		String key = ParamUtil.getString(resourceRequest, "key");

		PortalPropertiesAdvisor portalPropertiesAdvisor =
			(PortalPropertiesAdvisor)PortletBeanLocatorUtil.locate(
				"com.liferay.osb.lcs.advisor.PortalPropertiesAdvisor");

		JSONArray portalPropertiesDifferenceJSONArray =
			portalPropertiesAdvisor.getPortalPropertiesDifference(key);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			LCSConstants.JSON_KEY_DATA, portalPropertiesDifferenceJSONArray);
		jsonObject.put(
			LCSConstants.JSON_KEY_RESULT, LCSConstants.JSON_VALUE_SUCCESS);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void uploadPortalPropertiesFile(
		ActionRequest actionRequest, long lcsMetadataId) {

		InputStream inputStream = null;

		try {
			UploadPortletRequest uploadPortletRequest =
				PortalUtil.getUploadPortletRequest(actionRequest);

			inputStream = uploadPortletRequest.getFileAsStream(
				"portalPropertiesFile");

			if (inputStream == null) {
				return;
			}

			PropertiesConfiguration propertiesConfiguration =
				new PropertiesConfiguration();

			propertiesConfiguration.load(inputStream);

			Map<String, String> portalProperties =
				new HashMap<String, String>();

			Iterator<String> iterator = propertiesConfiguration.getKeys();

			while (iterator.hasNext()) {
				String key = iterator.next();

				Object property = propertiesConfiguration.getProperty(key);

				if (property instanceof List) {
					char delimiter = propertiesConfiguration.getListDelimiter();

					String value = StringUtil.merge(
						(List<?>)property, Character.toString(delimiter));

					portalProperties.put(key, value);
				}
				else {
					portalProperties.put(key, (String)property);
				}
			}

			if (!portalProperties.isEmpty()) {
				LCSMetadataDetailsServiceUtil.
					updateLCSMetadataDetailsPortletProperties(
						lcsMetadataId, portalProperties);
			}
		}
		catch (Exception e) {
			_log.error("Unable to upload properties file", e);
		}
		finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
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

	private static Log _log = LogFactoryUtil.getLog(AdminPortlet.class);

}