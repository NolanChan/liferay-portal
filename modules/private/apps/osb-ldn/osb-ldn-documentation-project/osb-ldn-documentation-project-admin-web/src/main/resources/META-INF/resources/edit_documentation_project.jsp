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

long documentationProjectId = ParamUtil.getLong(request, "documentationProjectId");

DocumentationProject documentationProject = DocumentationProjectLocalServiceUtil.fetchDocumentationProject(documentationProjectId);

String headerTitle = (documentationProjectId > 0) ? LanguageUtil.format(request, "edit-x", documentationProject.getName(), false) : LanguageUtil.get(request, "add-documentation-project");

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(headerTitle);
%>

<div class="container-fluid-1280 edit-documentation-project">
	<portlet:actionURL name="/edit_documentation_project" var="editDocumentationProjectURL">
		<portlet:param name="mvcRenderCommandName" value="/edit_documentation_project" />
	</portlet:actionURL>

	<aui:form action="<%= editDocumentationProjectURL %>" method="post" name="fm">
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="documentationProjectId" type="hidden" value="<%= documentationProjectId %>" />

		<div class="lfr-form-content">
			<aui:model-context bean="<%= documentationProject %>" model="<%= DocumentationProject.class %>" />

			<aui:fieldset-group markupView="lexicon">
				<aui:fieldset>
					<aui:input autoFocus="<%= true %>" name="name" />

					<aui:input name="description" />

					<div class="control-label">
						<liferay-ui:message key="icon" />
					</div>

					<c:if test="<%= documentationProject != null %>">
						<div>
							<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/serve_documentation_project_icon" var="iconURL">
								<portlet:param name="documentationProjectId" value="<%= String.valueOf(documentationProject.getDocumentationProjectId()) %>" />
							</liferay-portlet:resourceURL>

							<div class="icon">
								<img class="xsmall" src="<%= HtmlUtil.escapeAttribute(iconURL) %>" />
							</div>
						</div>
					</c:if>

					<aui:input accept="image/*" label="" name="icon" type="file" />

					<aui:select name="status">
						<aui:option label="offline" value="<%= DocumentationProjectStatusConstants.STATUS_OFFLINE %>" />
						<aui:option label="live" value="<%= DocumentationProjectStatusConstants.STATUS_LIVE %>" />
					</aui:select>
				</aui:fieldset>
			</aui:fieldset-group>
		</div>

		<aui:button-row>
			<aui:button cssClass="btn-lg" type="submit" />

			<aui:button cssClass="btn-lg" href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:form>
</div>