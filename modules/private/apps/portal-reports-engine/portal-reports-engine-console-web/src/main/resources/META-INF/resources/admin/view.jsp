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
PortletURL portletURL = renderResponse.createRenderURL();

boolean hasAddDefinitionPermission = AdminResourcePermissionChecker.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_DEFINITION);
boolean hasAddSourcePermission = AdminResourcePermissionChecker.contains(permissionChecker, scopeGroupId, ReportsActionKeys.ADD_SOURCE);

String portletId = portletDisplay.getId();

if (portletId.equals(ReportsEngineConsolePortletKeys.REPORTS_ADMIN)) {
	if (hasAddDefinitionPermission) {
		tabs1Names += ",definitions";
	}

	if (hasAddSourcePermission) {
		tabs1Names += ",sources";
	}
}
%>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">

		<%
		String tabs1 = siteMembershipsDisplayContext.getTabs1();
		%>

		<aui:nav-item label="<%= portletDisplay.getPortletDisplayName() %>" selected="<%= true %>" />
	</aui:nav>

	<aui:nav-bar-search>
		<aui:form action="<%= searchURL.toString() %>" name="searchFm">
			<liferay-portlet:renderURLParams varImpl="portletURL" />

			<liferay-ui:input-search markupView="lexicon" />
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>

<div class="container-fluid-1280 main-content-body">
	<liferay-ui:tabs
		names="<%= tabs1Names %>"
		param="tabs1"
		url="<%= portletURL.toString() %>"
	/>

	<c:choose>
		<c:when test='<%= tabs1.equals("reports") %>'>
			<liferay-util:include page="/admin/report/entries.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test='<%= hasAddDefinitionPermission && tabs1.equals("definitions") %>'>
			<liferay-util:include page="/admin/definition/definitions.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test='<%= hasAddSourcePermission && tabs1.equals("sources") %>'>
			<liferay-util:include page="/admin/data_source/sources.jsp" servletContext="<%= application %>" />
		</c:when>
	</c:choose>
</div>