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

LCSClusterEntry layoutLCSClusterEntry = LCSClusterEntryServiceUtil.getLCSClusterEntry(layoutLCSClusterEntryId);
List<LCSClusterNode> lcsClusterNodes = LCSClusterNodeUtil.getLCSClusterNodes(request, layoutLCSClusterEntryId, layoutLCSClusterNodeId, layoutLCSProjectId);

String defaultPage = lcsClusterNodes.isEmpty() ? "registration" : "fixPacks";

if (!LCSClusterEntryPermission.contains(permissionChecker, layoutLCSClusterEntry, OSBLCSActionKeys.MANAGE_ENTRY)) {
	defaultPage = "settings";
}

String environmentPage = ParamUtil.getString(request, "environmentPage", defaultPage);
%>

<liferay-ui:error key="duplicateLCSClusterEntryName" message="please-enter-a-unique-environment-name" />

<div class="lcs-toolbar">
	<c:if test="<%= !lcsClusterNodes.isEmpty() && LCSClusterEntryPermission.contains(permissionChecker, layoutLCSClusterEntry, OSBLCSActionKeys.MANAGE_ENTRY) %>">
		<div class="item <%= Validator.equals(environmentPage, "fixPacks") ? "selected" : StringPool.BLANK %>">
			<liferay-portlet:renderURL var="fixPacksURL">
				<portlet:param name="environmentPage" value="fixPacks" />
				<portlet:param name="layoutLCSClusterEntryId" value="<%= String.valueOf(layoutLCSClusterEntryId) %>" />
				<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
			</liferay-portlet:renderURL>

			<aui:a href="<%= fixPacksURL %>" label="fix-packs" />
		</div>
	</c:if>

	<c:if test="<%= LCSClusterEntryPermission.contains(permissionChecker, layoutLCSClusterEntry, OSBLCSActionKeys.MANAGE_ENTRY) %>">
		<div class="item <%= Validator.equals(environmentPage, "registration") ? "selected" : StringPool.BLANK %>">
			<liferay-portlet:renderURL var="lcsClusterEntryTokensURL">
				<portlet:param name="environmentPage" value="registration" />
				<portlet:param name="layoutLCSClusterEntryId" value="<%= String.valueOf(layoutLCSClusterEntryId) %>" />
				<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
			</liferay-portlet:renderURL>

			<aui:a href="<%= lcsClusterEntryTokensURL %>" label="registration" />
		</div>
	</c:if>

	<div class="item <%= Validator.equals(environmentPage, "settings") ? "selected" : StringPool.BLANK %>">
		<liferay-portlet:renderURL var="settingsURL">
			<portlet:param name="layoutLCSClusterEntryId" value="<%= String.valueOf(layoutLCSClusterEntryId) %>" />
			<portlet:param name="environmentPage" value="settings" />
			<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
		</liferay-portlet:renderURL>

		<aui:a href="<%= settingsURL %>" label="environment-settings" />
	</div>
</div>

<div id="<portlet:namespace />messageContainer"></div>

<c:choose>
	<c:when test='<%= Validator.equals(environmentPage, "fixPacks") %>'>
		<c:if test="<%= LCSClusterEntryPermission.contains(permissionChecker, layoutLCSClusterEntry, OSBLCSActionKeys.MANAGE_ENTRY) %>">
			<liferay-util:include page="/notifications/view.jsp" servletContext="<%= application %>" />
		</c:if>
	</c:when>
	<c:when test='<%= Validator.equals(environmentPage, "registration") %>'>
		<c:if test="<%= LCSClusterEntryPermission.contains(permissionChecker, layoutLCSClusterEntry, OSBLCSActionKeys.MANAGE_ENTRY) %>">
			<%@ include file="/environment/registration.jspf" %>
		</c:if>
	</c:when>
	<c:when test='<%= Validator.equals(environmentPage, "settings") %>'>
		<%@ include file="/environment/settings.jspf" %>
	</c:when>
</c:choose>