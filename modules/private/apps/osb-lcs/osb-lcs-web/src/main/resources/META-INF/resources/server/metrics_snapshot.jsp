<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1");
String tabs2 = ParamUtil.getString(request, "tabs2");

long layoutLCSClusterEntryId = ParamUtil.getLong(request, "layoutLCSClusterEntryId");
long layoutLCSClusterNodeId = ParamUtil.getLong(request, "layoutLCSClusterNodeId");
long layoutLCSProjectId = ParamUtil.getLong(request, "layoutLCSProjectId", LCSProjectServiceUtil.getUserDefaultLCSProjectId());

String orderByCol = ParamUtil.getString(request, "orderByCol", "frequency");
String orderByType = ParamUtil.getString(request, "orderByType", "desc");

LCSClusterNode lcsClusterNode = LCSClusterNodeServiceUtil.getLCSClusterNode(layoutLCSClusterNodeId, true);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/server/view.jsp");
portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("tabs2", tabs2);
portletURL.setParameter("serverPage", "metrics");

request.setAttribute("view.jsp-portletURL", portletURL);
%>

<c:choose>
	<c:when test="<%= !lcsClusterNode.isMetricsLCSServiceEnabled() %>">
		<div class="alert alert-info">

			<%
			Layout environmentLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), true, NavigationConstants.FRIENDLY_URL_LCS_CLUSTER_ENTRY);
			%>

			<liferay-portlet:renderURL plid="<%= environmentLayout.getPlid() %>" portletName="<%= OSBLCSPortletKeys.ENVIRONMENT %>" var="registrationURL">
				<portlet:param name="environmentPage" value="registration" />
				<portlet:param name="layoutLCSClusterEntryId" value="<%= String.valueOf(layoutLCSClusterEntryId) %>" />
				<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:message arguments="<%= registrationURL %>" key="this-service-is-disabled" />
		</div>
	</c:when>
	<c:otherwise>
		<c:if test="<%= LCSClusterNodeStatus.isInactive(lcsClusterNode.getStatus()) %>">
			<div class="portlet-msg-alert">
				<liferay-ui:message key="the-server-is-offline" />

				<liferay-ui:message key="liferay-connected-services-are-not-receiving-metrics-updates" />
			</div>
		</c:if>

		<c:if test="<%= LCSClusterNodeStatus.isActive(lcsClusterNode.getStatus()) && !LCSClusterNodeStatus.isDataInitalized(lcsClusterNode.getStatus()) %>">
			<div class="portlet-msg-alert">
				<liferay-ui:message key="liferay-connected-services-are-about-to-begin-receiving-metrics-updates" />
			</div>
		</c:if>

		<div class="lcs-metrics-tabs-container">
			<div class="lcs-tabs-default">
				<liferay-ui:tabs
					names="application,jvm,server"
					param="tabs1"
					url="<%= portletURL.toString() %>"
				/>
			</div>

			<c:choose>
				<c:when test='<%= tabs1.equals("jvm") %>'>
					<%@ include file="/server/metrics_jvm.jspf" %>
				</c:when>
				<c:when test='<%= tabs1.equals("server") %>'>
					<%@ include file="/server/metrics_server.jspf" %>
				</c:when>
				<c:otherwise>
					<liferay-util:include page="/server/metrics_application.jsp" servletContext="<%= application %>" />
				</c:otherwise>
			</c:choose>
		</div>
	</c:otherwise>
</c:choose>

<div class="footer-note">

	<%
	Layout feedbackLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), true, NavigationConstants.FRIENDLY_URL_FEEDBACK);
	%>

	<liferay-portlet:renderURL plid="<%= feedbackLayout.getPlid() %>" portletName="<%= OSBLCSPortletKeys.NAVIGATION %>" var="feedbackURL">
		<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
	</liferay-portlet:renderURL>

	<liferay-ui:message arguments="<%= feedbackURL %>" key="do-you-find-these-metrics-useful" />
</div>