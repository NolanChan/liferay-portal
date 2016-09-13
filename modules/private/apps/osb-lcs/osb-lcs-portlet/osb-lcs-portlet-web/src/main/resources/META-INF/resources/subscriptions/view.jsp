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
long layoutLCSProjectId = ParamUtil.getLong(request, "layoutLCSProjectId", LCSProjectServiceUtil.getUserDefaultLCSProjectId());
String subscriptionsPage = ParamUtil.getString(request, "subscriptionsPage", "details");
%>

<div class="lcs-toolbar">
	<div class="item <%= Validator.equals(subscriptionsPage, "details") || !SubscriptionsUtil.hasElasticSubscription(layoutLCSProjectId) ? "selected" : StringPool.BLANK %>">
		<liferay-portlet:renderURL var="subscriptionsDetailsURL">
			<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
			<portlet:param name="subscriptionsPage" value="details" />
		</liferay-portlet:renderURL>

		<aui:a href="<%= subscriptionsDetailsURL %>" label="details" />
	</div>

	<c:if test="<%= SubscriptionsUtil.hasElasticSubscription(layoutLCSProjectId) %>">
		<div class="item <%= Validator.equals(subscriptionsPage, "elastic") ? "selected" : StringPool.BLANK %>">
			<liferay-portlet:renderURL var="elasticSubscriptionsURL">
				<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
				<portlet:param name="subscriptionsPage" value="elastic" />
			</liferay-portlet:renderURL>

			<aui:a href="<%= elasticSubscriptionsURL %>" label="elastic-subscriptions" />
		</div>
	</c:if>
</div>

<c:choose>
	<c:when test='<%= Validator.equals(subscriptionsPage, "details") || !SubscriptionsUtil.hasElasticSubscription(layoutLCSProjectId) %>'>
		<%@ include file="/subscriptions/details.jspf" %>
	</c:when>
	<c:when test='<%= Validator.equals(subscriptionsPage, "elastic") %>'>
		<%@ include file="/subscriptions/elastic.jspf" %>
	</c:when>
</c:choose>