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

<%@ include file="/html/portlet/portal_instances/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Company companyObject = (Company)row.getObject();
%>

<liferay-ui:icon-menu icon="<%= StringPool.BLANK %>" message="<%= StringPool.BLANK %>">
	<portlet:renderURL var="editURL">
		<portlet:param name="mvcRenderCommandName" value="/portal_instances/edit_instance" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="companyId" value="<%= String.valueOf(companyObject.getCompanyId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon iconCssClass="icon-edit" message="edit" url="<%= editURL %>" />

	<c:if test="<%= companyObject.getCompanyId() != PortalInstances.getDefaultCompanyId() %>">
		<portlet:actionURL name="/portal_instances/edit_instance" var="deleteURL">
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="companyId" value="<%= String.valueOf(companyObject.getCompanyId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete url="<%= deleteURL %>" />
	</c:if>
</liferay-ui:icon-menu>