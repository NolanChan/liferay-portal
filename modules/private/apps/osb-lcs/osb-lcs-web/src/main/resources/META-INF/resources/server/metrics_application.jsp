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
String tabs2 = ParamUtil.getString(request, "tabs2");

long layoutLCSClusterNodeId = ParamUtil.getLong(request, "layoutLCSClusterNodeId");

String orderByCol = ParamUtil.getString(request, "orderByCol", "frequency");
String orderByType = ParamUtil.getString(request, "orderByType", "desc");

LCSClusterNode lcsClusterNode = LCSClusterNodeServiceUtil.getLCSClusterNode(layoutLCSClusterNodeId);

PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");
%>

<div class="lcs-tabs-sub">
	<liferay-ui:tabs
		names="pages,portlets,cache"
		param="tabs2"
		url="<%= portletURL.toString() %>"
	/>
</div>

<c:choose>
	<c:when test='<%= tabs2.equals("cache") %>'>
		<%@ include file="/server/metrics_application_cache.jspf" %>
	</c:when>
	<c:when test='<%= tabs2.equals("portlets") %>'>
		<%@ include file="/server/metrics_application_portlets.jspf" %>
	</c:when>
	<c:otherwise>
		<%@ include file="/server/metrics_application_pages.jspf" %>
	</c:otherwise>
</c:choose>