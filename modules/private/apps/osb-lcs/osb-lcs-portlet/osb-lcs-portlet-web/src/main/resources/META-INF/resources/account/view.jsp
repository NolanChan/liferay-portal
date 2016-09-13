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
String accountPage = ParamUtil.getString(request, "accountPage", "notifications");
long layoutLCSProjectId = ParamUtil.getLong(request, "layoutLCSProjectId", LCSProjectServiceUtil.getUserDefaultLCSProjectId());
%>

<div class="lcs-toolbar">
	<div class="item <%= Validator.equals(accountPage, "notifications") ? "selected" : StringPool.BLANK %>">
		<liferay-portlet:renderURL var="notificationsURL">
			<portlet:param name="accountPage" value="notifications" />
			<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
		</liferay-portlet:renderURL>

		<aui:a href="<%= notificationsURL %>" label="email-notifications" />
	</div>

	<div class="item <%= Validator.equals(accountPage, "messages") ? "selected" : StringPool.BLANK %>">
		<liferay-portlet:renderURL var="messagesURL">
			<portlet:param name="accountPage" value="messages" />
			<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
		</liferay-portlet:renderURL>

		<aui:a href="<%= messagesURL %>" label="notifications-history" />
	</div>

	<div class="item <%= Validator.equals(accountPage, "preferences") ? "selected" : StringPool.BLANK %>">
		<liferay-portlet:renderURL var="preferencesURL">
			<portlet:param name="accountPage" value="preferences" />
			<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
		</liferay-portlet:renderURL>

		<aui:a href="<%= preferencesURL %>" label="preferences" />
	</div>
</div>

<c:choose>
	<c:when test='<%= Validator.equals(accountPage, "messages") %>'>
		<%@ include file="/account/messages.jspf" %>
	</c:when>
	<c:when test='<%= Validator.equals(accountPage, "notifications") %>'>
		<%@ include file="/account/view_notifications_settings.jspf" %>
	</c:when>
	<c:when test='<%= Validator.equals(accountPage, "preferences") %>'>
		<%@ include file="/account/preferences.jspf" %>
	</c:when>
</c:choose>