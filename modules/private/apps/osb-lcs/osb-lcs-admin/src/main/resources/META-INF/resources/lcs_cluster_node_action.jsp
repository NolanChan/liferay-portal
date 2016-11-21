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
AdminAdvisor adminAdvisor = (AdminAdvisor) renderRequest.getAttribute(AdminAdvisor.class.getName());

ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Object[] lcsClusterNodeObjectArray = (Object[])row.getObject();

boolean loggingEnabled = adminAdvisor.isLCSClusterNodeLoggingEnabled(String.valueOf(lcsClusterNodeObjectArray[2]));
%>

<aui:nav-bar cssClass="nav-tabs">
	<aui:nav collapsible="<%= true %>">
		<aui:nav-item cssClass="properties-difference" dropdown="<%= true %>" label="actions">
			<liferay-portlet:actionURL name="enableLCSClusterNodeLogging" var="enableLCSClusterNodeLoggingURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="enableLogging" value="<%= String.valueOf(!loggingEnabled) %>" />
				<portlet:param name="key" value="<%= String.valueOf(lcsClusterNodeObjectArray[2]) %>" />
			</liferay-portlet:actionURL>

			<aui:nav-item cssClass="properties-difference-menu" href="<%= enableLCSClusterNodeLoggingURL %>" iconCssClass="icon-file" label='<%= (loggingEnabled ? "disable-logging" : "enable-logging") %>' title="enable-logging" />

			<c:if test="<%= lcsClusterNodeObjectArray[13] != null %>">
				<portlet:actionURL name="deleteCache" var="deleteCacheURL">
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="key" value="<%= String.valueOf(lcsClusterNodeObjectArray[2]) %>" />
				</portlet:actionURL>

				<aui:nav-item href="<%= deleteCacheURL %>" iconCssClass="icon-pages" label="delete-node-from-cache" title="delete-node-from-cache" />
			</c:if>

			<%
			Map<String, Object> data = new HashMap<String, Object> ();

			data.put("key", String.valueOf(lcsClusterNodeObjectArray[2]));
			%>

			<aui:nav-item cssClass="properties-difference-button" data="<%= data %>" href="javascript:;" iconCssClass="icon-list" label="properties-difference" />
		</aui:nav-item>
	</aui:nav>
</aui:nav-bar>