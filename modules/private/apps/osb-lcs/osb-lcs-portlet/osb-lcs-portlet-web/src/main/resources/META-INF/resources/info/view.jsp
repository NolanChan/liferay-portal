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
String errorMessage = ParamUtil.getString(request, "errorMessage");
%>

<div class="info-panel">
	<img class="logo" src="<%= PortalUtil.getPathContext(request) %>/images/logo.svg" />

	<span class="title">
		<liferay-ui:message key="connected-services" />
	</span>

	<p>
		<liferay-ui:message arguments="<%= new Object[] {PortletPropsValues.LRDCOM_PRODUCT_PAGE_URL, PortletPropsValues.LRDCOM_USER_DOCUMENTATION_URL} %>" key="liferay-connected-services-is-a-set-of-online-tools-and-services-that-lets-you-manage-and-monitor-your-liferay-installations" />
	</p>

	<c:if test="<%= Validator.isNotNull(errorMessage) %>">
		<div class="alert alert-error">
			<liferay-ui:message key="<%= errorMessage %>" />
		</div>
	</c:if>

	<c:if test="<%= permissionChecker.isSignedIn() %>">

		<%
		List<LCSProject> lcsProjects = LCSProjectServiceUtil.getUserLCSProjects();
		%>

		<c:choose>
			<c:when test="<%= lcsProjects.isEmpty() %>">
				<div class="alert alert-warning">
					<liferay-ui:message key="you-are-not-a-member-of-any-project" />
				</div>
			</c:when>
			<c:otherwise>
				<h2>
					<liferay-ui:icon-menu icon="">

						<%
						Layout dashboardLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), true, NavigationUtil.FRIENDLY_URL_DASHBOARD);
						%>

						<liferay-portlet:renderURL plid="<%= dashboardLayout.getPlid() %>" var="dashboardURL" />

						<liferay-ui:icon iconCssClass="icon-home" message="go-to-my-dashboard" url="<%= dashboardURL %>" />
					</liferay-ui:icon-menu>
				</h2>
			</c:otherwise>
		</c:choose>
	</c:if>
</div>