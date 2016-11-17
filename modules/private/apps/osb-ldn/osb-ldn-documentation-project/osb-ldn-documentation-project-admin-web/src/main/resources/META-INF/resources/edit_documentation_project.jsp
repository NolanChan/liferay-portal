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

String headerTitle = (documentationProjectId > 0) ? LanguageUtil.format(request, "edit-x", documentationProject.getName(), false) : LanguageUtil.get(request, "add-open-source-project");

String documentationProjectType = DocumentationProjectConstants.TYPE_SITE;
DocumentationProjectTypeSettings documentationProjectTypeSettings = null;
String url = StringPool.BLANK;
String gradientStartColor = "#ffffff";
String gradientEndColor = "#ffffff";

if (documentationProject != null) {
	documentationProjectType = documentationProject.getType();
	documentationProjectTypeSettings = DocumentationProjectTypeSettingsFactoryUtil.create(documentationProject);

	if (documentationProjectType.equals(DocumentationProjectConstants.TYPE_URL)) {
		url = ((DocumentationProjectURLTypeSettings)documentationProjectTypeSettings).getURL();
	}
	else {
		gradientStartColor = ((DocumentationProjectSiteTypeSettings)documentationProjectTypeSettings).getHeaderGradientStartColor();
		gradientEndColor = ((DocumentationProjectSiteTypeSettings)documentationProjectTypeSettings).getHeaderGradientEndColor();
	}
}

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(headerTitle);
%>

<div class="container-fluid-1280 edit-documentation-project">
	<portlet:actionURL name="/edit_documentation_project" var="editDocumentationProjectURL">
		<portlet:param name="mvcRenderCommandName" value="/edit_documentation_project" />
	</portlet:actionURL>

	<aui:form action="<%= editDocumentationProjectURL %>" method="post" name="fm">
		<liferay-ui:error exception="<%= DocumentationProjectHeaderGradientColorException.class %>" message="please-enter-a-correct-color-format" />
		<liferay-ui:error exception="<%= DocumentationProjectIconException.class %>" message="please-provide-a-valid-icon" />

		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="documentationProjectId" type="hidden" value="<%= documentationProjectId %>" />

		<div class="lfr-form-content">
			<aui:model-context bean="<%= documentationProject %>" model="<%= DocumentationProject.class %>" />

			<aui:fieldset-group markupView="lexicon">
				<aui:fieldset label="details">
					<aui:input autoFocus="<%= true %>" name="name">
						<aui:validator name="required" />
					</aui:input>

					<aui:input name="description">
						<aui:validator name="required" />
					</aui:input>

					<div class="control-label">
						<liferay-ui:message key="icon" />
					</div>

					<c:if test="<%= documentationProject != null %>">
						<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/serve_documentation_project_icon" var="iconURL">
							<portlet:param name="documentationProjectId" value="<%= String.valueOf(documentationProject.getDocumentationProjectId()) %>" />
						</liferay-portlet:resourceURL>

						<div class="icon">
							<img class="xsmall" src="<%= HtmlUtil.escapeAttribute(iconURL) %>" />
						</div>
					</c:if>

					<aui:input accept="image/*" label="" name="icon" type="file" />
				</aui:fieldset>

				<aui:fieldset label="categorization">
					<aui:input name="tags" type="assetTags" />
				</aui:fieldset>

				<aui:fieldset label="status">
					<aui:select label="site-status" name="status">
						<aui:option label="offline" value="<%= DocumentationProjectConstants.STATUS_OFFLINE %>" />
						<aui:option label="live" value="<%= DocumentationProjectConstants.STATUS_LIVE %>" />
					</aui:select>
				</aui:fieldset>

				<div class="documentation-project-type">
					<aui:fieldset label="type">
						<aui:select label="type" name="type">
							<aui:option label="site" value="<%= DocumentationProjectConstants.TYPE_SITE %>" />
							<aui:option label="url" value="<%= DocumentationProjectConstants.TYPE_URL %>" />
						</aui:select>
					</aui:fieldset>
				</div>

				<aui:fieldset label="options">
					<div class="documentation-project-type-site">
						<aui:input label="gradient-start-color" name="headerGradientStartColor" type="text" value="<%= gradientStartColor %>">
							<aui:validator name="required" />
							<aui:validator errorMessage="please-enter-correct-color-format" name="custom">
								function(val, fieldNode, ruleValue) {
									var result = false;
									var pattern = /#[a-zA-Z0-9] {6}/;

									if (pattern.test(val)) {
										result = true;
									}

									return result;
								}
							</aui:validator>
						</aui:input>

						<aui:input label="gradient-end-color" name="headerGradientEndColor" type="text" value="<%= gradientEndColor %>">
							<aui:validator name="required" />
							<aui:validator errorMessage="please-enter-correct-color-format" name="custom">
								function(val, fieldNode, ruleValue) {
									var result = false;
									var pattern = /#[a-zA-Z0-9] {6}/;

									if (pattern.test(val)) {
										result = true;
									}

									return result;
								}
							</aui:validator>
						</aui:input>
					</div>

					<div class="documentation-project-type-url">
						<aui:input label="url" name="url" type="text" value="<%= url %>" />
					</div>
				</aui:fieldset>
			</aui:fieldset-group>
		</div>

		<aui:button-row>
			<aui:button cssClass="btn-lg" type="submit" />

			<aui:button cssClass="btn-lg" href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:form>
</div>

<aui:script use="aui-base">
	var A = AUI();

	<c:choose>
		<c:when test="<%= documentationProjectType.equals(DocumentationProjectConstants.TYPE_SITE) %>">
			A.one('.documentation-project-type-url').hide();
		</c:when>
		<c:otherwise>
			A.one('.documentation-project-type-site').hide();
		</c:otherwise>
	</c:choose>

	A.one('.documentation-project-type select').on(
		'change',
		function(event) {
			if (event.currentTarget.val() === "site") {
				A.one('.documentation-project-type-site').show();
				A.one('.documentation-project-type-url').hide();
			}
			else if (event.currentTarget.val() === "url") {
				A.one('#<portlet:namespace/>headerGradientStartColor').val('#ffffff');
				A.one('#<portlet:namespace/>headerGradientEndColor').val('#ffffff');

				A.one('.documentation-project-type-site').hide();
				A.one('.documentation-project-type-url').show();
			}
		}
	);
</aui:script>