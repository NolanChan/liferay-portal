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
String redirect = ParamUtil.getString(request, "redirect");

long structureVersionId = ParamUtil.getLong(request, "structureVersionId");

DDMStructureVersion structureVersion = DDMStructureVersionServiceUtil.getStructureVersion(structureVersionId);

String portletResourceNamespace = ParamUtil.getString(request, "portletResourceNamespace", renderResponse.getNamespace());

String script = BeanParamUtil.getString(structureVersion, request, "definition");

JSONArray fieldsJSONArray = DDMUtil.getDDMFormFieldsJSONArray(structureVersion, script);

String fieldsJSONArrayString = StringPool.BLANK;

if (fieldsJSONArray != null) {
	fieldsJSONArrayString = fieldsJSONArray.toString();
}

PortletURL backURL = renderResponse.createRenderURL();
backURL.setParameter("mvcPath", "/view_structure_history.jsp");
backURL.setParameter("structureId", String.valueOf(structureVersion.getStructureId()));

%>

<aui:model-context bean="<%= structureVersion %>" model="<%= DDMStructureVersion.class %>" />

<liferay-ui:header
	backURL="<%= backURL.toString() %>"
	localizeTitle="<%= false %>"
	showBackURL="<%= true %>"
	title="<%= structureVersion.getName(locale) %>"
/>

<aui:input disabled="<%= true %>" name="name" />

<aui:input disabled="<%= true %>" name="description" />

<%@ include file="/form_builder.jspf" %>

<aui:button-row>
	<aui:button href="<%= redirect %>" type="cancel" />
</aui:button-row>
