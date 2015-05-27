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
long groupId = themeDisplay.getSiteGroupIdOrLiveGroupId();

List<WorkflowHandler<?>> workflowHandlers = null;

if (portletName.equals(WorkflowDefinitionLinkPortletKeys.WORKFLOW_DEFINITION_LINK_CONTROL_PANEL)) {
	groupId = WorkflowConstants.DEFAULT_GROUP_ID;

	workflowHandlers = WorkflowHandlerRegistryUtil.getWorkflowHandlers();
}
else {
	workflowHandlers = WorkflowHandlerRegistryUtil.getScopeableWorkflowHandlers();
}

List<WorkflowHandler<?>> hiddenWorkflowHandlers = new ArrayList<WorkflowHandler<?>>();

Iterator<WorkflowHandler<?>> itr = workflowHandlers.iterator();

while (itr.hasNext()) {
	WorkflowHandler<?> workflowHandler = itr.next();

	if (!workflowHandler.isVisible()) {
		hiddenWorkflowHandlers.add(workflowHandler);

		itr.remove();
	}
}

List<WorkflowDefinition> workflowDefinitions = WorkflowDefinitionManagerUtil.getActiveWorkflowDefinitions(company.getCompanyId(), 0, 100, WorkflowComparatorFactoryUtil.getDefinitionNameComparator(true));

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/view.jsp");
portletURL.setParameter("tabs1", "default-configuration");

%>

<portlet:actionURL name="updateWorkflowDefinitionLink" var="updateWorkflowDefinitionLinkURL"  />

<aui:form action="<%= updateWorkflowDefinitionLinkURL %>" method="post">
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="groupId" type="hidden" value="<%= groupId %>" />

	<liferay-ui:search-container
		iteratorURL="<%= portletURL %>"
		total="<%= workflowHandlers.size() %>"
	>
		<liferay-ui:search-container-results
			results="<%= ListUtil.subList(workflowHandlers, searchContainer.getStart(), searchContainer.getEnd()) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.workflow.WorkflowHandler"
			modelVar="workflowHandler"
		>

			<liferay-ui:search-container-row-parameter
				name="workflowHandler"
				value="<%= workflowHandler %>"
			/>

			<liferay-ui:search-container-column-text
				name="resource"
			>
				<liferay-ui:icon
					iconCssClass="<%= workflowHandler.getIconCssClass() %>"
					label="<%= true %>"
					message="<%= ResourceActionsUtil.getModelResource(locale, workflowHandler.getClassName()) %>"
				/>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="workflow"
			>
				<aui:select label="" name='<%= "workflowDefinitionName@" + workflowHandler.getClassName() %>' title="workflow-definition">

					<%
					WorkflowDefinitionLink workflowDefinitionLink = null;

					try {
						if (portletName.equals(WorkflowDefinitionLinkPortletKeys.WORKFLOW_DEFINITION_LINK_CONTROL_PANEL)) {
							workflowDefinitionLink = WorkflowDefinitionLinkLocalServiceUtil.getDefaultWorkflowDefinitionLink(company.getCompanyId(), workflowHandler.getClassName(), 0, 0);
						}
						else {
							workflowDefinitionLink = WorkflowDefinitionLinkLocalServiceUtil.getWorkflowDefinitionLink(company.getCompanyId(), groupId, workflowHandler.getClassName(), 0, 0, true);
						}
					}
					catch (NoSuchWorkflowDefinitionLinkException nswdle) {
					}

					String defaultOptionMessage = null;

					if (!portletName.equals(WorkflowDefinitionLinkPortletKeys.WORKFLOW_DEFINITION_LINK_CONTROL_PANEL)) {
						try {
							WorkflowDefinitionLink defaultWorkflowDefinitionLink = WorkflowDefinitionLinkLocalServiceUtil.getDefaultWorkflowDefinitionLink(company.getCompanyId(), workflowHandler.getClassName(), 0, 0);

							defaultOptionMessage = LanguageUtil.get(request, "default") + StringPool.COLON + StringPool.SPACE + defaultWorkflowDefinitionLink.getWorkflowDefinitionName();
						}
						catch (NoSuchWorkflowDefinitionLinkException nswdle) {
							defaultOptionMessage = LanguageUtil.get(request, "default") + StringPool.COLON + StringPool.SPACE + LanguageUtil.get(request, "no-workflow");
						}
					}
					else {
						defaultOptionMessage = LanguageUtil.get(request, "no-workflow");
					}
					%>

					<aui:option><%= defaultOptionMessage %></aui:option>

					<%
					for (WorkflowDefinition workflowDefinition : workflowDefinitions) {
						boolean selected = false;

						if ((workflowDefinitionLink != null) && workflowDefinitionLink.getWorkflowDefinitionName().equals(workflowDefinition.getName()) && (workflowDefinitionLink.getWorkflowDefinitionVersion() == workflowDefinition.getVersion())) {
							selected = true;
						}
					%>

						<aui:option label='<%= HtmlUtil.escape(workflowDefinition.getName()) + " (" + LanguageUtil.format(locale, "version-x", workflowDefinition.getVersion(), false) + ")" %>' selected="<%= selected %>" value="<%= HtmlUtil.escapeAttribute(workflowDefinition.getName()) + StringPool.AT + workflowDefinition.getVersion() %>" />

					<%
					}
					%>

				</aui:select>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>

	<br />

	<div class="alert alert-info">
		<liferay-ui:message key="the-workflows-for-the-following-resources-can-be-configured-within-their-respective-portlets" />
	</div>

	<liferay-ui:search-container
		iteratorURL="<%= portletURL %>"
		total="<%= hiddenWorkflowHandlers.size() %>"
	>
		<liferay-ui:search-container-results
			results="<%= ListUtil.subList(hiddenWorkflowHandlers, searchContainer.getStart(), searchContainer.getEnd()) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.workflow.WorkflowHandler"
			modelVar="workflowHandler"
		>
			<liferay-ui:search-container-row-parameter
				name="workflowHandler"
				value="<%= workflowHandler %>"
			/>

			<liferay-ui:search-container-column-text
				name="resource"
			>
				<liferay-ui:icon
					iconCssClass="<%= workflowHandler.getIconCssClass() %>"
					label="<%= true %>"
					message="<%= ResourceActionsUtil.getModelResource(locale, workflowHandler.getClassName()) %>"
				/>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>