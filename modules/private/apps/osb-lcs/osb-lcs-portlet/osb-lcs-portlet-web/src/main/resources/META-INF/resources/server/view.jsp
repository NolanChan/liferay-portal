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
long layoutLCSClusterEntryId = ParamUtil.getLong(request, "layoutLCSClusterEntryId");
long layoutLCSClusterNodeId = ParamUtil.getLong(request, "layoutLCSClusterNodeId");
long layoutLCSProjectId = ParamUtil.getLong(request, "layoutLCSProjectId", LCSProjectServiceUtil.getUserDefaultLCSProjectId());
String serverPage = ParamUtil.getString(request, "serverPage", "analytics");

LCSClusterEntry lcsClusterEntry = LCSClusterEntryServiceUtil.getLCSClusterEntry(layoutLCSClusterEntryId);
LCSClusterNode lcsClusterNode = LCSClusterNodeServiceUtil.getLCSClusterNode(layoutLCSClusterNodeId, true);
%>

<liferay-ui:error key="duplicateLCSClusterNodeName" message="please-enter-a-unique-server-name" />

<div class="lcs-toolbar">
	<div class="item <%= Validator.equals(serverPage, "analytics") ? "selected" : StringPool.BLANK %>">
		<liferay-portlet:renderURL var="analyticsURL">
			<portlet:param name="layoutLCSClusterEntryId" value="<%= String.valueOf(layoutLCSClusterEntryId) %>" />
			<portlet:param name="layoutLCSClusterNodeId" value="<%= String.valueOf(layoutLCSClusterNodeId) %>" />
			<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
			<portlet:param name="serverPage" value="analytics" />
		</liferay-portlet:renderURL>

		<aui:a href="<%= analyticsURL %>" label="page-analytics" />
	</div>

	<div class="item <%= Validator.equals(serverPage, "metrics") ? "selected" : StringPool.BLANK %>">
		<liferay-portlet:renderURL var="metricsURL">
			<portlet:param name="layoutLCSClusterEntryId" value="<%= String.valueOf(layoutLCSClusterEntryId) %>" />
			<portlet:param name="layoutLCSClusterNodeId" value="<%= String.valueOf(layoutLCSClusterNodeId) %>" />
			<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
			<portlet:param name="serverPage" value="metrics" />
		</liferay-portlet:renderURL>

		<aui:a href="<%= metricsURL %>" label="snapshot-metrics" />
	</div>

	<div class="item <%= Validator.equals(serverPage, "fixPacks") ? "selected" : StringPool.BLANK %>">
		<liferay-portlet:renderURL var="fixPacksURL">
			<portlet:param name="layoutLCSClusterEntryId" value="<%= String.valueOf(layoutLCSClusterEntryId) %>" />
			<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
			<portlet:param name="serverPage" value="fixPacks" />
		</liferay-portlet:renderURL>

		<aui:a href="<%= fixPacksURL %>" label="fix-packs" />
	</div>

	<div class="item <%= Validator.equals(serverPage, "properties") ? "selected" : StringPool.BLANK %>">
		<liferay-portlet:renderURL var="propertiesURL">
			<portlet:param name="layoutLCSClusterEntryId" value="<%= String.valueOf(layoutLCSClusterEntryId) %>" />
			<portlet:param name="layoutLCSClusterNodeId" value="<%= String.valueOf(layoutLCSClusterNodeId) %>" />
			<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
			<portlet:param name="serverPage" value="properties" />
		</liferay-portlet:renderURL>

		<aui:a href="<%= propertiesURL %>" label="portal-properties" />
	</div>

	<div class="item <%= Validator.equals(serverPage, "details") ? "selected" : StringPool.BLANK %>">
		<liferay-portlet:renderURL var="detailsURL">
			<portlet:param name="layoutLCSClusterEntryId" value="<%= String.valueOf(layoutLCSClusterEntryId) %>" />
			<portlet:param name="layoutLCSClusterNodeId" value="<%= String.valueOf(layoutLCSClusterNodeId) %>" />
			<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
			<portlet:param name="serverPage" value="details" />
		</liferay-portlet:renderURL>

		<aui:a href="<%= detailsURL %>" label="details" />
	</div>

	<div class="item <%= Validator.equals(serverPage, "settings") ? "selected" : StringPool.BLANK %>">
		<liferay-portlet:renderURL var="settingsURL">
			<portlet:param name="layoutLCSClusterEntryId" value="<%= String.valueOf(layoutLCSClusterEntryId) %>" />
			<portlet:param name="layoutLCSClusterNodeId" value="<%= String.valueOf(layoutLCSClusterNodeId) %>" />
			<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
			<portlet:param name="serverPage" value="settings" />
		</liferay-portlet:renderURL>

		<aui:a href="<%= settingsURL %>" label="server-settings" />
	</div>
</div>

<div id="<portlet:namespace />messageContainer"></div>

<c:choose>
	<c:when test='<%= Validator.equals(serverPage, "analytics") %>'>
		<%@ include file="/server/analytics.jspf" %>
	</c:when>
	<c:when test='<%= Validator.equals(serverPage, "details") %>'>
		<%@ include file="/server/details.jspf" %>
	</c:when>
	<c:when test='<%= Validator.equals(serverPage, "fixPacks") %>'>
		<liferay-util:include page="/notifications/view.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= Validator.equals(serverPage, "metrics") %>'>
		<liferay-util:include page="/server/metrics_snapshot.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= Validator.equals(serverPage, "properties") %>'>
		<%@ include file="/server/properties.jspf" %>
	</c:when>
	<c:when test='<%= Validator.equals(serverPage, "settings") %>'>
		<%@ include file="/server/settings.jspf" %>
	</c:when>
</c:choose>