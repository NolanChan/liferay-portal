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
String emailMessageBody = portletPreferences.getValue("email.message.body", "");

String emailMessageSubject = portletPreferences.getValue("email.message.subject", "");
%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationActionURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<liferay-ui:error key="emailMessageBody" message="please-enter-a-valid-body" />
	<liferay-ui:error key="emailMessageSubject" message="please-enter-a-valid-subject" />

	<liferay-ui:email-notification-settings
		emailBody='<%= LocalizationUtil.getLocalizationXmlFromPreferences(portletPreferences, renderRequest, "emailMessageBody", "preferences", ContentUtil.get(emailMessageBody)) %>'
		emailDefinitionTerms="<%= InvitationUtil.getEmailDefinitionTerms(renderRequest) %>"
		emailParam="emailMessage"
		emailSubject='<%= LocalizationUtil.getLocalizationXmlFromPreferences(portletPreferences, renderRequest, "emailMessageSubject", "preferences", ContentUtil.get(emailMessageSubject)) %>'
		showEmailEnabled="<%= false %>"
	/>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>