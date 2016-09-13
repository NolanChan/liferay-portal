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

import com.liferay.osb.lcs.model.LCSClusterEntry;
import com.liferay.osb.lcs.model.LCSClusterNode;
import com.liferay.osb.lcs.service.LCSClusterEntryServiceUtil;
import com.liferay.osb.lcs.service.LCSClusterNodeServiceUtil;
import com.liferay.osb.lcs.util.PortletKeys;
import com.liferay.osb.lcs.util.PortletPropsValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.xml.QName;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PublicRenderParameter;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletQNameUtil;
import com.liferay.portlet.PortletURLFactoryUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ivica Cardic
 * @author Marko Cikos
 * @author Peter Shin
 * @author Igor Beslic
 */
public class NavigationUtil {

	public static final String CHECK_LCS_INVITATION_URL =
		"/c/portal/lcs/check_lcs_invitation";

	public static final String FRIENDLY_URL_ACCOUNT = "/account";

	public static final String FRIENDLY_URL_DASHBOARD = "/dashboard";

	public static final String FRIENDLY_URL_DOWNLOADS = "/downloads";

	public static final String FRIENDLY_URL_FEEDBACK = "/feedback";

	public static final String FRIENDLY_URL_INFO = "/info";

	public static final String FRIENDLY_URL_LCS_CLUSTER_ENTRY = "/environment";

	public static final String FRIENDLY_URL_LCS_CLUSTER_NODE = "/server";

	public static final String FRIENDLY_URL_LCS_PRIVATE_SITE =
		PropsUtil.get(PropsKeys.LAYOUT_FRIENDLY_URL_PRIVATE_GROUP_SERVLET_MAPPING) +
			"/guest";

	public static final String FRIENDLY_URL_LCS_PUBLIC_SITE =
		PropsUtil.get(PropsKeys.LAYOUT_FRIENDLY_URL_PUBLIC_SERVLET_MAPPING) +
			"/guest";

	public static final String FRIENDLY_URL_PROJECTS = "/projects";

	public static final String FRIENDLY_URL_SUBSCRIPTIONS = "/subscriptions";

	public static final String FRIENDLY_URL_USERS = "/users";

	public static final String LOGOUT_URL = "/c/portal/logout";

	public static String getLCSClusterEntryHealthStatusCSSClass(
		Boolean lcsClusterEntryOffline,
		int lcsClusterEntryLCSClusterNodesCount) {

		if (lcsClusterEntryLCSClusterNodesCount == 0) {
			return _HEALTH_STATUS_CSS_CLASS_EMPTY;
		}

		if (lcsClusterEntryOffline) {
			return _HEALTH_STATUS_CSS_CLASS_OFFLINE;
		}

		return _HEALTH_STATUS_CSS_CLASS_ONLINE;
	}

	public static String getLCSClusterEntryHealthStatusTitle(
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

	public static String getLCSClusterEntryURL(long lcsClusterEntryId)
		throws PortalException, SystemException {

		String friendlyURL = FRIENDLY_URL_LCS_PRIVATE_SITE.concat(
			FRIENDLY_URL_LCS_CLUSTER_ENTRY);

		LCSClusterEntry lcsClusterEntry =
			LCSClusterEntryServiceUtil.getLCSClusterEntry(lcsClusterEntryId);

		Map<String, String> publicRenderParameters =
			getPublicRenderParametersMap(
				"layoutLCSProjectId",
				String.valueOf(lcsClusterEntry.getLcsProjectId()),
				"layoutLCSClusterEntryId", String.valueOf(lcsClusterEntryId));

		return getLCSLayoutURL(friendlyURL, publicRenderParameters);
	}

	public static String getLCSClusterNodeHealthStatusCSSClass(
		Boolean lcsClusterNodeOffline) {

		if (lcsClusterNodeOffline) {
			return _HEALTH_STATUS_CSS_CLASS_OFFLINE;
		}

		return _HEALTH_STATUS_CSS_CLASS_ONLINE;
	}

	public static Map<Long, Boolean> getLCSClusterNodeOfflineStatuses(
			long lcsClusterEntryId)
		throws PortalException, SystemException {

		Map<Long, Boolean> lcsClusterNodeOfflineStatuses =
			new HashMap<Long, Boolean>();

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

	public static String getLCSClusterNodeURL(long lcsClusterNodeId)
		throws PortalException, SystemException {

		String friendlyURL = FRIENDLY_URL_LCS_PRIVATE_SITE.concat(
			FRIENDLY_URL_LCS_CLUSTER_NODE);

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

	public static String getLCSLayoutURL(
		String friendlyURL, Map<String, String> publicRenderParameters) {

		String layoutFullURL = getLCSPortalURL() + friendlyURL;

		if (publicRenderParameters.isEmpty()) {
			return layoutFullURL;
		}

		StringBundler sb = new StringBundler(
			4 * publicRenderParameters.size() + 3);

		sb.append(layoutFullURL);
		sb.append("?p_p_id=");
		sb.append(PortletKeys.NAVIGATION);

		for (Map.Entry<String, String> entry :
				publicRenderParameters.entrySet()) {

			sb.append(StringPool.AMPERSAND);
			sb.append(getPublicRenderParameterName(entry.getKey()));
			sb.append(StringPool.EQUAL);
			sb.append(entry.getValue());
		}

		return sb.toString();
	}

	public static String getLCSNotificationsURL() {
		String url = getLCSPortalURL().concat(
			FRIENDLY_URL_LCS_PRIVATE_SITE).concat(FRIENDLY_URL_ACCOUNT);

		String namespace = PortalUtil.getPortletNamespace(PortletKeys.ACCOUNT);

		url = HttpUtil.addParameter(url, "p_p_id", PortletKeys.ACCOUNT);
		url = HttpUtil.addParameter(
			url, namespace.concat("accountPage"), "notifications");

		return url;
	}

	public static String getLCSPortalURL() {
		StringBundler sb = new StringBundler(5);

		sb.append(PortletPropsValues.OSB_LCS_PORTLET_PROTOCOL);
		sb.append(Http.PROTOCOL_DELIMITER);
		sb.append(PortletPropsValues.OSB_LCS_PORTLET_HOST_NAME);

		if ((PortletPropsValues.OSB_LCS_PORTLET_HOST_PORT == Http.HTTP_PORT) ||
			(PortletPropsValues.OSB_LCS_PORTLET_HOST_PORT == Http.HTTPS_PORT)) {

			return sb.toString();
		}

		sb.append(StringPool.COLON);
		sb.append(PortletPropsValues.OSB_LCS_PORTLET_HOST_PORT);

		return sb.toString();
	}

	public static String getLCSProjectURL(long lcsProjectId) {
		String friendlyURL = FRIENDLY_URL_LCS_PRIVATE_SITE.concat(
			FRIENDLY_URL_DASHBOARD);

		Map<String, String> publicRenderParameters =
			getPublicRenderParametersMap(
				"layoutLCSProjectId", String.valueOf(lcsProjectId));

		return getLCSLayoutURL(friendlyURL, publicRenderParameters);
	}

	public static String getLCSProjectUsersURL(long lcsProjectId) {
		String friendlyURL = FRIENDLY_URL_LCS_PRIVATE_SITE.concat(
			FRIENDLY_URL_USERS);

		Map<String, String> publicRenderParameters =
			getPublicRenderParametersMap(
				"layoutLCSProjectId", String.valueOf(lcsProjectId));

		return getLCSLayoutURL(friendlyURL, publicRenderParameters);
	}

	public static LiferayPortletURL getPortletRenderURL(
			long companyId, String layoutFriendlyURL, String portletId,
			boolean privateLayout, HttpServletRequest request)
		throws PortalException, SystemException {

		return getPortletRenderURL(
			companyId, layoutFriendlyURL, portletId, privateLayout, request,
			null);
	}

	public static LiferayPortletURL getPortletRenderURL(
			long companyId, String layoutFriendlyURL, String portletId,
			boolean privateLayout, HttpServletRequest request,
			String... parameters)
		throws PortalException, SystemException {

		Group group = GroupLocalServiceUtil.getGroup(
			companyId, GroupConstants.GUEST);

		Layout layout = LayoutLocalServiceUtil.getFriendlyURLLayout(
			group.getGroupId(), privateLayout, layoutFriendlyURL);

		LiferayPortletURL liferayPortletURL = PortletURLFactoryUtil.create(
			request, portletId, layout.getPlid(), PortletRequest.RENDER_PHASE);

		if (ArrayUtil.isEmpty(parameters)) {
			return liferayPortletURL;
		}

		for (int i = 0; (i + 1) < parameters.length; i = i + 2) {
			liferayPortletURL.setParameter(parameters[i], parameters[i + 1]);
		}

		return liferayPortletURL;
	}

	public static String getPublicRenderParameterName(String parameterName) {
		Portlet portlet = PortletLocalServiceUtil.getPortletById(
			PortletKeys.NAVIGATION);

		PublicRenderParameter publicRenderParameter =
			portlet.getPublicRenderParameter(parameterName);

		if (publicRenderParameter == null) {
			return parameterName;
		}

		QName qName = publicRenderParameter.getQName();

		return PortletQNameUtil.getPublicRenderParameterName(qName);
	}

	public static Map<String, String> getPublicRenderParametersMap(
		String... parameters) {

		Map<String, String> publicRenderParameters =
			new HashMap<String, String>();

		for (int i = 0; (i + 1) < parameters.length;) {
			publicRenderParameters.put(parameters[i], parameters[i+1]);

			i = i + 2;
		}

		return publicRenderParameters;
	}

	public static String getSubscriptionsURL(long lcsProjectId) {
		String friendlyURL = FRIENDLY_URL_LCS_PRIVATE_SITE.concat(
			FRIENDLY_URL_SUBSCRIPTIONS);

		Map<String, String> publicRenderParameters =
			getPublicRenderParametersMap(
				"layoutLCSProjectId", String.valueOf(lcsProjectId));

		return getLCSLayoutURL(friendlyURL, publicRenderParameters);
	}

	private static final String _HEALTH_STATUS_CSS_CLASS_EMPTY = "empty";

	private static final String _HEALTH_STATUS_CSS_CLASS_OFFLINE = "offline";

	private static final String _HEALTH_STATUS_CSS_CLASS_ONLINE = "online";

}