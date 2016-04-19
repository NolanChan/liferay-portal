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
String backURL = ParamUtil.getString(request, "backURL");

long ddmStructureId = ParamUtil.getLong(request, "ddmStructureId");
String workflowDefinition = ParamUtil.getString(request, "workflowDefinition");
String workflowTaskName = ParamUtil.getString(request, "workflowTaskName");
String mode = ParamUtil.getString(request, "mode");

renderResponse.setTitle(LanguageUtil.format(request, "select-form-for-task-x", workflowTaskName, false));

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(backURL);
%>

<liferay-portlet:renderURL varImpl="iteratorURL">
	<portlet:param name="mvcPath" value="/admin/process/select_template.jsp" />
	<portlet:param name="backURL" value="<%= backURL %>" />
	<portlet:param name="ddmStructureId" value="<%= String.valueOf(ddmStructureId) %>" />
	<portlet:param name="workflowDefinition" value="<%= workflowDefinition %>" />
	<portlet:param name="workflowTaskName" value="<%= workflowTaskName %>" />
	<portlet:param name="mode" value="<%= mode %>" />
</liferay-portlet:renderURL>

<div class="container-fluid-1280">
	<c:if test="<%= DDMTemplatePermission.containsAddTemplatePermission(permissionChecker, scopeGroupId, PortalUtil.getClassNameId(DDMStructure.class), scopeClassNameId) %>">
		<aui:button-row>
			<aui:button onClick='<%= "javascript:" + renderResponse.getNamespace() + "openDDMPortlet();" %>' primary="<%= true %>" value="add-form" />
		</aui:button-row>
	</c:if>

	<liferay-ui:search-container
		searchContainer='<%= new SearchContainer(renderRequest, new DisplayTerms(request), null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, iteratorURL, null, "there-are-no-results") %>'
	>

		<%
		DisplayTerms displayTerms = searchContainer.getDisplayTerms();
		%>

		<liferay-ui:search-container-results>

			<%
			total = DDMTemplateLocalServiceUtil.searchCount(company.getCompanyId(), scopeGroupId, PortalUtil.getClassNameId(DDMStructure.class), ddmStructureId, scopeClassNameId, displayTerms.getKeywords(), DDMTemplateConstants.TEMPLATE_TYPE_FORM, mode, WorkflowConstants.STATUS_ANY);

			searchContainer.setTotal(total);

			results = DDMTemplateLocalServiceUtil.search(company.getCompanyId(), scopeGroupId, PortalUtil.getClassNameId(DDMStructure.class), ddmStructureId, scopeClassNameId, displayTerms.getKeywords(), DDMTemplateConstants.TEMPLATE_TYPE_FORM, mode, WorkflowConstants.STATUS_ANY, searchContainer.getStart(), searchContainer.getEnd(), null);

			searchContainer.setResults(results);
			%>

		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.dynamic.data.mapping.model.DDMTemplate"
			keyProperty="templateId"
			modelVar="ddmTemplate"
		>
			<liferay-ui:search-container-column-text
				name="name"
				value="<%= HtmlUtil.escape(ddmTemplate.getName(locale)) %>"
			/>

			<liferay-ui:search-container-column-date
				name="modified-date"
				value="<%= ddmTemplate.getModifiedDate() %>"
			/>

			<liferay-ui:search-container-column-jsp
				align="right"
				cssClass="entry-action"
				path="/admin/process/template_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator markupView="lexicon" />
	</liferay-ui:search-container>
</div>

<aui:script>
	Liferay.on(
		'<portlet:namespace />chooseTemplate',
		function(event) {
			var A = AUI();

			A.io.request(
				'<portlet:resourceURL id="saveInPortletSession" />',
				{
					after: {
						success: function() {
							window.location = decodeURIComponent('<%= HtmlUtil.escapeURL(backURL) %>');
						}
					},
					data: {
						'<%= HtmlUtil.escapeJS(renderResponse.getNamespace() + ddmStructureId + workflowDefinition + workflowTaskName) %>' : event.ddmtemplateid
					}
				}
			);
		},
		['aui-base', 'aui-io-request']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />openDDMPortlet',
		function(ddmTemplateId) {
			Liferay.Util.openDDMPortlet(
				{
					basePortletURL: '<%= PortletURLFactoryUtil.create(request, DDMPortletKeys.DYNAMIC_DATA_MAPPING, themeDisplay.getPlid(), PortletRequest.RENDER_PHASE) %>',
					classNameId: <%= PortalUtil.getClassNameId(DDMStructure.class) %>,
					classPK: <%= ddmStructureId %>,
					dialog: {
						destroyOnHide: true,
						on: {
							destroy: function() {
								Liferay.Portlet.refresh('#p_p_id_<%= portletDisplay.getId() %>_');
							}
						}
					},
					id: 'ddmDialog',
					mode: '<%= HtmlUtil.escapeJS(mode) %>',
					mvcPath: '/select_template.jsp',
					navigationStartsOn: '<%= DDMNavigationHelper.SELECT_TEMPLATE %>',
					portletResourceNamespace: '<%= renderResponse.getNamespace() %>',
					refererPortletName: '<%= portletDisplay.getId() %>',
					showBackURL: false,
					showHeader: false,
					structureAvailableFields: '<%= renderResponse.getNamespace() + "getAvailableFields" %>',
					resourceClassNameId: <%= scopeClassNameId %>,
					templateId: ddmTemplateId,
					title: '<liferay-ui:message key="form" />'
				}
			);
		},
		['liferay-util']
	);
</aui:script>