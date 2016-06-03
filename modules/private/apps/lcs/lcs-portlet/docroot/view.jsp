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
String lcsPage = ParamUtil.getString(request, "lcsPage", "connection");

boolean lcsClusterNodeRegistered = false;
boolean lcsPortletAuthorized = false;

if (LCSUtil.isLCSPortletAuthorized(liferayPortletRequest)) {
	lcsClusterNodeRegistered = LCSUtil.isLCSClusterNodeRegistered(liferayPortletRequest);

	if (!SessionErrors.contains(liferayPortletRequest, "oAuthTokenRejected")) {
		lcsPortletAuthorized = true;
	}
}
%>

<liferay-ui:error key="generalPluginAccess" message="an-error-occurred-while-accessing-liferay-connected-services" />
<liferay-ui:error key="keyStoreAccess" message="unable-to-access-keystore" />
<liferay-ui:error key="lcsInsufficientPrivileges" message="please-provide-user-credentials-with-the-appropriate-lcs-role" />
<liferay-ui:error key="oAuthAuthorizationFailed" message="oauth-authorization-failed" />
<liferay-ui:error key="oAuthTokenExpired" message="provided-oauth-token-expired" />
<liferay-ui:error key="oAuthTokenRejected" message="provided-oauth-token-rejected" />
<liferay-ui:error key="serverIdFileSystemAccess" message="unable-generate-server-id" />

<section class="content">
	<c:choose>
		<c:when test="<%= !lcsPortletAuthorized || !lcsClusterNodeRegistered %>">
			<%@ include file="/info.jspf" %>
		</c:when>
		<c:when test='<%= Validator.equals(lcsPage, "connection") %>'>
			<%@ include file="/connection.jspf" %>
		</c:when>
		<c:when test='<%= Validator.equals(lcsPage, "lcsServices") %>'>
			<%@ include file="/lcs_services.jspf" %>
		</c:when>
	</c:choose>
</section>

<footer class="footer">
	<div class="lcs-version">
		<liferay-ui:message arguments="<%= LCSUtil.getLCSPortletBuildNumber() %>" key="liferay-connected-services-client-x" />
	</div>

	<liferay-ui:message arguments="<%= new Object[] {LCSUtil.getFeedbackURL(request), PortletPropsValues.FEEDBACK_EMAIL_ADDRESS, PortletPropsValues.JIRA_SUPPORT_PROJECT_URL} %>" key="are-you-having-problems" />
</footer>