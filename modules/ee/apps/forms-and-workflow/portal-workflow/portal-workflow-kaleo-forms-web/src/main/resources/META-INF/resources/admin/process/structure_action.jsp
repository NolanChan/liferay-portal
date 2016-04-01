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

<%@ include file="/admin/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

DDMStructure ddmStructure = (DDMStructure)row.getObject();

String redirect = (String)row.getParameter("redirect");
%>

<liferay-ui:icon-menu icon="<%= StringPool.BLANK %>" message="<%= StringPool.BLANK %>">
	<liferay-portlet:renderURL portletName="<%= PortletProviderUtil.getPortletId(DDMStructure.class.getName(), PortletProvider.Action.EDIT) %>" var="editURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="mvcPath" value="/edit_structure.jsp" />
		<portlet:param name="navigationStartsOn" value="<%= DDMNavigationHelper.EDIT_STRUCTURE %>" />
		<portlet:param name="closeRedirect" value="<%= currentURL %>" />
		<portlet:param name="showBackURL" value="<%= Boolean.FALSE.toString() %>" />
		<portlet:param name="refererPortletName" value="<%= KaleoFormsPortletKeys.KALEO_FORMS_ADMIN %>" />
		<portlet:param name="groupId" value="<%= String.valueOf(scopeGroupId) %>" />
		<portlet:param name="classNameId" value="<%= String.valueOf(PortalUtil.getClassNameId(DDMStructure.class)) %>" />
		<portlet:param name="classPK" value="<%= String.valueOf(ddmStructure.getStructureId()) %>" />
	</liferay-portlet:renderURL>

	<%
	String taglibOnClick = "javascript:" + renderResponse.getNamespace() + "editStructure('" + editURL + "');";
	%>

	<liferay-ui:icon
		iconCssClass="icon-edit"
		message="edit"
		onClick="<%= taglibOnClick %>"
		url="javascript:;"
	/>

	<%
	taglibOnClick = "Liferay.fire('" + renderResponse.getNamespace() + "chooseDefinition', {ddmStructureId: " + ddmStructure.getStructureId() + ", name: '" + HtmlUtil.escapeJS(ddmStructure.getName(locale)) + "', node: this});";
	%>

	<liferay-ui:icon
		iconCssClass="icon-check"
		message="choose"
		onClick="<%= taglibOnClick %>"
		url="javascript:;"
	/>

	<portlet:actionURL name="deleteDDMStructure" var="deleteDDMStructureURL">
		<portlet:param name="redirect" value="<%= redirect %>" />
		<portlet:param name="ddmStructureId" value="<%= String.valueOf(ddmStructure.getStructureId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete url="<%= deleteDDMStructureURL %>" />
</liferay-ui:icon-menu>