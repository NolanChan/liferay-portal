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
			<div class="container-fluid-1280">
				<%@ include file="/info.jspf" %>
			</div>
		</c:when>
		<c:otherwise>
			<aui:nav-bar markupView="lexicon">
				<aui:nav cssClass="navbar-nav">
					<liferay-portlet:renderURL var="connectionURL">
						<liferay-portlet:param name="lcsPage" value="connection" />
					</liferay-portlet:renderURL>

					<aui:nav-item href="<%= connectionURL %>" label="connection" selected='<%= lcsPage.equals("connection") %>' />

					<liferay-portlet:renderURL var="lcsServicesURL">
						<liferay-portlet:param name="lcsPage" value="services" />
					</liferay-portlet:renderURL>

					<aui:nav-item href="<%= lcsServicesURL %>" label="services" selected='<%= lcsPage.equals("services") %>' />

					<liferay-portlet:renderURL var="infoURL">
						<liferay-portlet:param name="lcsPage" value="info" />
					</liferay-portlet:renderURL>

					<aui:nav-item href="<%= infoURL %>" label="info" selected='<%= lcsPage.equals("info") %>' />
				</aui:nav>
			</aui:nav-bar>

			<div class="container-fluid-1280">
				<c:choose>
					<c:when test='<%= lcsPage.equals("connection") %>'>
						<%@ include file="/connection.jspf" %>
					</c:when>
					<c:when test='<%= lcsPage.equals("info") %>'>
						<%@ include file="/info.jspf" %>
					</c:when>
					<c:when test='<%= lcsPage.equals("services") %>'>
						<%@ include file="/lcs_services.jspf" %>
					</c:when>
				</c:choose>
			</div>
		</c:otherwise>
	</c:choose>
</section>

<footer class="footer">
	<div class="container-fluid-1280">
		<div class="lcs-version">
			<liferay-ui:message arguments="<%= LCSUtil.getLCSPortletBuildNumber() %>" key="liferay-connected-services-client-x" />
		</div>

		<liferay-ui:message arguments="<%= new Object[] {LCSUtil.getFeedbackURL(request), PortletPropsValues.FEEDBACK_EMAIL_ADDRESS, PortletPropsValues.JIRA_SUPPORT_PROJECT_URL} %>" key="are-you-having-problems" />
	</div>
</footer>