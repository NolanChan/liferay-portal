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

KaleoTaskFormPair kaleoTaskFormPair = (KaleoTaskFormPair)row.getObject();

String backURL = (String)row.getParameter("backURL");
%>

<liferay-ui:icon-menu direction="left-side" icon="<%= StringPool.BLANK %>" markupView="lexicon" message="<%= StringPool.BLANK %>" showWhenSingleIcon="<%= true %>">

	<%
	long kaleoProcessId = ParamUtil.getLong(request, "kaleoProcessId");

	long ddmStructureId = KaleoFormsUtil.getKaleoProcessDDMStructureId(kaleoProcessId, portletSession);

	String workflowDefinition = ParamUtil.getString(request, "workflowDefinition");

	String initialStateName = KaleoFormsUtil.getInitialStateName(company.getCompanyId(), workflowDefinition);

	String mode = initialStateName.equals(kaleoTaskFormPair.getWorkflowTaskName()) ? DDMTemplateConstants.TEMPLATE_MODE_CREATE : DDMTemplateConstants.TEMPLATE_MODE_EDIT;
	String paramName = HtmlUtil.escapeJS(renderResponse.getNamespace() + ddmStructureId + workflowDefinition + kaleoTaskFormPair.getWorkflowTaskName());
	%>

	<liferay-ui:icon
		message="assign-form"
		onClick='<%= "javascript:" + renderResponse.getNamespace() + "selectFormTemplate(" + ddmStructureId + ",'" + mode + "', '" + paramName + "');" %>'
		url="javascript:;"
	/>

	<%
	DDMTemplate ddmTemplate = null;

	long ddmTemplateId = kaleoTaskFormPair.getDDMTemplateId();

	if (ddmTemplateId > 0) {
		ddmTemplate = DDMTemplateLocalServiceUtil.fetchTemplate(ddmTemplateId);
	}
	%>

	<c:if test="<%= ddmTemplate != null %>">

		<%
		long classNameId = ParamUtil.getLong(request, "classNameId");
		%>

		<liferay-portlet:renderURL portletName="<%= PortletProviderUtil.getPortletId(DDMTemplate.class.getName(), PortletProvider.Action.EDIT) %>" var="editFormTemplateURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="mvcPath" value="/edit_template.jsp" />
			<portlet:param name="navigationStartsOn" value="<%= DDMNavigationHelper.EDIT_TEMPLATE %>" />
			<portlet:param name="closeRedirect" value="<%= backURL %>" />
			<portlet:param name="showBackURL" value="<%= Boolean.FALSE.toString() %>" />
			<portlet:param name="portletResourceNamespace" value="<%= renderResponse.getNamespace() %>" />
			<portlet:param name="refererPortletName" value="<%= KaleoFormsPortletKeys.KALEO_FORMS_ADMIN %>" />
			<portlet:param name="groupId" value="<%= String.valueOf(scopeGroupId) %>" />
			<portlet:param name="classNameId" value="<%= String.valueOf(classNameId) %>" />
			<portlet:param name="templateId" value="<%= String.valueOf(ddmTemplate.getTemplateId()) %>" />
			<portlet:param name="showCacheableInput" value="<%= Boolean.TRUE.toString() %>" />
			<portlet:param name="structureAvailableFields" value='<%= renderResponse.getNamespace() + "getAvailableFields" %>' />
		</liferay-portlet:renderURL>

		<liferay-ui:icon
			message="edit-form"
			onClick='<%= "javascript:" + renderResponse.getNamespace() + "editFormTemplate('" + editFormTemplateURL + "');" %>'
			url="javascript:;"
		/>
	</c:if>
</liferay-ui:icon-menu>