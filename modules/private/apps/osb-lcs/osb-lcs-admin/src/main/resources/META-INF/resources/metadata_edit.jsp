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

String backURL = ParamUtil.getString(request, "backURL", redirect);

long lcsMetadataId = ParamUtil.getLong(request, "lcsMetadataId");

LCSMetadata lcsMetadata = LCSMetadataLocalServiceUtil.fetchLCSMetadata(lcsMetadataId);
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	localizeTitle="<%= (lcsMetadata == null) %>"
	title='<%= (lcsMetadata == null) ? "new-lcs-metadata" : lcsMetadata.getBuildNumber() + StringPool.SPACE + lcsMetadata.getPortalEdition() %>'
/>

<liferay-portlet:actionURL name='<%= (lcsMetadata == null) ? "addLCSMetadata" : "updateLCSMetadata" %>' var="editLCSMetadataURL" />

<aui:form action="<%= editLCSMetadataURL %>" enctype="multipart/form-data" method="post" name="fm">
	<aui:input name="mvcPath" type="hidden" value="/metadata_edit.jsp" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="lcsMetadataId" type="hidden" value="<%= String.valueOf(lcsMetadataId) %>" />

	<liferay-ui:error exception="<%= RequiredFieldException.class %>" message="this-field-is-required" />

	<aui:model-context bean="<%= lcsMetadata %>" model="<%= LCSMetadata.class %>" />

	<aui:fieldset>
		<c:choose>
			<c:when test="<%= lcsMetadata == null %>">
				<aui:input name="buildNumber" />
			</c:when>
			<c:otherwise>
				<aui:field-wrapper label="build-number">
					<%= lcsMetadata.getBuildNumber() %>
				</aui:field-wrapper>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="<%= lcsMetadata == null %>">
				<aui:input name="gitTag" />
			</c:when>
			<c:otherwise>
				<aui:field-wrapper label="git-tag">
					<%= HtmlUtil.escape(lcsMetadata.getGitTag()) %>
				</aui:field-wrapper>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="<%= lcsMetadata == null %>">
				<aui:input name="portalEdition" />
			</c:when>
			<c:otherwise>
				<aui:field-wrapper label="portal-edition">
					<%= HtmlUtil.escape(lcsMetadata.getPortalEdition()) %>
				</aui:field-wrapper>
			</c:otherwise>
		</c:choose>

		<aui:input name="supportedLCSPortlet" />

		<aui:input name="supportedPatchingTool" />

		<aui:input name="portalPropertiesFile" size="50" type="file" />

		<aui:button-row>
			<aui:button type="submit" />

			<aui:button href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>