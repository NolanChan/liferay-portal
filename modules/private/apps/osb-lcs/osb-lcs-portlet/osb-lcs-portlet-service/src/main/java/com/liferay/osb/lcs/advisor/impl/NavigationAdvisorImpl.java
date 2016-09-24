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

import com.liferay.osb.lcs.advisor.NavigationAdvisor;
import com.liferay.osb.lcs.configuration.OSBLCSConfiguration;
import com.liferay.osb.lcs.constants.NavigationConstants;
import com.liferay.osb.lcs.constants.OSBLCSPortletKeys;
import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.service.LCSClusterEntryServiceUtil;
import com.liferay.osb.lcs.service.LCSClusterNodeServiceUtil;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.PublicRenderParameter;
import com.liferay.portal.kernel.portlet.PortletQNameUtil;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.xml.QName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Marko Cikos
 * @author Peter Shin
 * @author Igor Beslic
 */
public class NavigationAdvisorImpl implements NavigationAdvisor {

	@Override
	public String getLCSClusterEntryHealthStatusCSSClass(
		Boolean lcsClusterEntryOffline,
		int lcsClusterEntryLCSClusterNodesCount) {

		if (lcsClusterEntryLCSClusterNodesCount == 0) {
			return NavigationConstants.HEALTH_STATUS_CSS_CLASS_EMPTY;
		}

		if (lcsClusterEntryOffline) {
			return NavigationConstants.HEALTH_STATUS_CSS_CLASS_OFFLINE;
		}

		return NavigationConstants.HEALTH_STATUS_CSS_CLASS_ONLINE;
	}

	@Override
	public String getLCSClusterEntryHealthStatusTitle(
		boolean cluster, Boolean lcsClusterNodeOffline) {

		String title = "all-servers-in-this-server-group-are-online";

		if (cluster && lcsClusterNodeOffline) {
			title = "one-or-more-cluster-nodes-in-this-cluster-are-offline";
		}
		else if (cluster && !lcsClusterNodeOffline) {
			title = "all-cluster-nodes-in-this-cluster-are-online";
		}
		else if (!cluster && lcsClusterNodeOffline) {
			title = "one-or-more-servers-in-this-server-group-are-offline";
		}

		return title;
	}

	@Override
	public String getLCSClusterEntryURL(long lcsClusterEntryId)
		throws PortalException {

		String friendlyURL =
			NavigationConstants.FRIENDLY_URL_LCS_PRIVATE_SITE.concat(
				NavigationConstants.FRIENDLY_URL_LCS_CLUSTER_ENTRY);

		LCSClusterEntry lcsClusterEntry =
			LCSClusterEntryServiceUtil.getLCSClusterEntry(lcsClusterEntryId);

		Map<String, String> publicRenderParameters =
			getPublicRenderParametersMap(
				"layoutLCSProjectId",
				String.valueOf(lcsClusterEntry.getLcsProjectId()),
				"layoutLCSClusterEntryId", String.valueOf(lcsClusterEntryId));

		return getLCSLayoutURL(friendlyURL, publicRenderParameters);
	}

	@Override
	public String getLCSClusterNodeHealthStatusCSSClass(
		Boolean lcsClusterNodeOffline) {

		if (lcsClusterNodeOffline) {
			return NavigationConstants.HEALTH_STATUS_CSS_CLASS_OFFLINE;
		}

		return NavigationConstants.HEALTH_STATUS_CSS_CLASS_ONLINE;
	}

	@Override
	public Map<Long, Boolean> getLCSClusterNodeOfflineStatuses(
			long lcsClusterEntryId)
		throws PortalException {

		Map<Long, Boolean> lcsClusterNodeOfflineStatuses = new HashMap<>();

		List<LCSClusterNode> lcsClusterNodes =
			LCSClusterNodeServiceUtil.getLCSClusterEntryLCSClusterNodes(
				lcsClusterEntryId, true);

		for (LCSClusterNode lcsClusterNode : lcsClusterNodes) {
			lcsClusterNodeOfflineStatuses.put(
				lcsClusterNode.getLcsClusterNodeId(),
				lcsClusterNode.isOffline());
		}

		return lcsClusterNodeOfflineStatuses;
	}

	@Override
	public String getLCSClusterNodeURL(long lcsClusterNodeId)
		throws PortalException {

		String friendlyURL =
			NavigationConstants.FRIENDLY_URL_LCS_PRIVATE_SITE.concat(
				NavigationConstants.FRIENDLY_URL_LCS_CLUSTER_NODE);

		LCSClusterNode lcsClusterNode =
			LCSClusterNodeServiceUtil.getLCSClusterNode(lcsClusterNodeId);

		LCSClusterEntry lcsClusterEntry =
			LCSClusterEntryServiceUtil.getLCSClusterEntry(
				lcsClusterNode.getLcsClusterEntryId());

		Map<String, String> publicRenderParameters =
			getPublicRenderParametersMap(
				"layoutLCSProjectId",
				String.valueOf(lcsClusterEntry.getLcsProjectId()),
				"layoutLCSClusterEntryId",
				String.valueOf(lcsClusterEntry.getLcsClusterEntryId()),
				"layoutLCSClusterNodeId", String.valueOf(lcsClusterNodeId));

		return getLCSLayoutURL(friendlyURL, publicRenderParameters);
	}

	@Override
	public String getLCSLayoutURL(
		String friendlyURL, Map<String, String> publicRenderParameters) {

		String layoutFullURL = getLCSPortalURL() + friendlyURL;

		if (publicRenderParameters.isEmpty()) {
			return layoutFullURL;
		}

		StringBundler sb = new StringBundler(
			4 * publicRenderParameters.size() + 3);

		sb.append(layoutFullURL);
		sb.append("?p_p_id=");
		sb.append(OSBLCSPortletKeys.NAVIGATION);

		for (Map.Entry<String, String> entry :
				publicRenderParameters.entrySet()) {

			sb.append(StringPool.AMPERSAND);
			sb.append(getPublicRenderParameterName(entry.getKey()));
			sb.append(StringPool.EQUAL);
			sb.append(entry.getValue());
		}

		return sb.toString();
	}

	@Override
	public String getLCSNotificationsURL() {
		String url = getLCSPortalURL().concat(
			NavigationConstants.FRIENDLY_URL_LCS_PRIVATE_SITE).concat(
				NavigationConstants.FRIENDLY_URL_ACCOUNT);

		String namespace = PortalUtil.getPortletNamespace(
			OSBLCSPortletKeys.ACCOUNT);

		url = HttpUtil.addParameter(url, "p_p_id", OSBLCSPortletKeys.ACCOUNT);
		url = HttpUtil.addParameter(
			url, namespace.concat("accountPage"), "notifications");

		return url;
	}

	@Override
	public String getLCSPortalURL() {
		StringBundler sb = new StringBundler(5);

		sb.append(_osbLCSConfiguration.osbLcsPortletProtocol());
		sb.append(Http.PROTOCOL_DELIMITER);
		sb.append(_osbLCSConfiguration.osbLcsPortletHostName());

		if ((_osbLCSConfiguration.osbLcsPortletHostPort() == Http.HTTP_PORT) ||
			(_osbLCSConfiguration.osbLcsPortletHostPort() == Http.HTTPS_PORT)) {

			return sb.toString();
		}

		sb.append(StringPool.COLON);
		sb.append(_osbLCSConfiguration.osbLcsPortletHostPort());

		return sb.toString();
	}

	@Override
	public String getLCSProjectURL(long lcsProjectId) {
		String friendlyURL =
			NavigationConstants.FRIENDLY_URL_LCS_PRIVATE_SITE.concat(
				NavigationConstants.FRIENDLY_URL_DASHBOARD);

		Map<String, String> publicRenderParameters =
			getPublicRenderParametersMap(
				"layoutLCSProjectId", String.valueOf(lcsProjectId));

		return getLCSLayoutURL(friendlyURL, publicRenderParameters);
	}

	@Override
	public String getLCSProjectUsersURL(long lcsProjectId) {
		String friendlyURL =
			NavigationConstants.FRIENDLY_URL_LCS_PRIVATE_SITE.concat(
				NavigationConstants.FRIENDLY_URL_USERS);

		Map<String, String> publicRenderParameters =
			getPublicRenderParametersMap(
				"layoutLCSProjectId", String.valueOf(lcsProjectId));

		return getLCSLayoutURL(friendlyURL, publicRenderParameters);
	}

	@Override
	public String getPublicRenderParameterName(String parameterName) {
		Portlet portlet = _portletLocalService.getPortletById(
			OSBLCSPortletKeys.NAVIGATION);

		PublicRenderParameter publicRenderParameter =
			portlet.getPublicRenderParameter(parameterName);

		if (publicRenderParameter == null) {
			return parameterName;
		}

		QName qName = publicRenderParameter.getQName();

		return PortletQNameUtil.getPublicRenderParameterName(qName);
	}

	@Override
	public Map<String, String> getPublicRenderParametersMap(
		String... parameters) {

		Map<String, String> publicRenderParameters = new HashMap<>();

		for (int i = 0; (i + 1) < parameters.length;) {
			publicRenderParameters.put(parameters[i], parameters[i+1]);

			i = i + 2;
		}

		return publicRenderParameters;
	}

	@Override
	public String getSubscriptionsURL(long lcsProjectId) {
		String friendlyURL =
			NavigationConstants.FRIENDLY_URL_LCS_PRIVATE_SITE.concat(
				NavigationConstants.FRIENDLY_URL_SUBSCRIPTIONS);

		Map<String, String> publicRenderParameters =
			getPublicRenderParametersMap(
				"layoutLCSProjectId", String.valueOf(lcsProjectId));

		return getLCSLayoutURL(friendlyURL, publicRenderParameters);
	}

	@Reference(bind = "-", unbind = "-")
	public void setPortletLocalService(
		PortletLocalService portletLocalService) {

		_portletLocalService = portletLocalService;
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

	private static volatile OSBLCSConfiguration _osbLCSConfiguration;

	private PortletLocalService _portletLocalService;

}