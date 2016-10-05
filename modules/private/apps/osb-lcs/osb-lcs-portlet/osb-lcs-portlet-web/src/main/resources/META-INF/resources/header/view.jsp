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
long layoutLCSProjectId = ParamUtil.getLong(request, "layoutLCSProjectId", LCSProjectServiceUtil.getUserDefaultLCSProjectId());
%>

<div class="site">
	<aui:a cssClass="lcs-logo" href="javascript:;" id="logo" />

	<span class="title">
		<liferay-ui:message key="connected-services" />
	</span>
</div>

<div class="dropdown-menu-toggler profile" id="<portlet:namespace />profile">
	<liferay-ui:icon cssClass="user-avatar-image" message="" src="<%= user.getPortraitURL(themeDisplay) %>" />

	<span class="user-full-name">
		<%= HtmlUtil.escape(user.getFullName()) %>
	</span>
	<span class="icon-caret-down"></span>
</div>

<div class="profile-menu" id="<portlet:namespace />profileMenu">
	<ul class="dropdown-menu">
		<li>
			<aui:a href="<%= osbLCSConfiguration.lrdcomUserDocumentationUrl() %>" target="_blank">
				<span class="icon-file-text profile-icon"></span>

				<liferay-ui:message key="user-documentation" />
			</aui:a>
		</li>
		<li>

			<%
			Layout accountLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), true, NavigationConstants.FRIENDLY_URL_ACCOUNT);
			%>

			<liferay-portlet:renderURL plid="<%= accountLayout.getPlid() %>" portletName="<%= OSBLCSPortletKeys.ACCOUNT %>" var="accountURL">
				<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
			</liferay-portlet:renderURL>

			<aui:a href="<%= accountURL %>">
				<span class="icon-user profile-icon"></span>

				<liferay-ui:message key="my-account" />
			</aui:a>
		</li>
		<li class="manage-projects">

			<%
			Layout projectsLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), true, NavigationConstants.FRIENDLY_URL_PROJECTS);
			%>

			<liferay-portlet:renderURL plid="<%= projectsLayout.getPlid() %>" portletName="<%= OSBLCSPortletKeys.ENROLLMENT %>" var="projectsURL">
				<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
			</liferay-portlet:renderURL>

			<aui:a href="<%= projectsURL %>">
				<span class="icon-cog profile-icon"></span>

				<liferay-ui:message key="manage-projects" />
			</aui:a>
		</li>

		<%
		Layout dashboardLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), true, NavigationConstants.FRIENDLY_URL_DASHBOARD);

		for (LCSProject lcsProject : LCSProjectServiceUtil.getUserLCSProjects(false, LCSRoleConstants.ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER)) {
		%>

			<li class="project-item">
				<liferay-portlet:renderURL plid="<%= dashboardLayout.getPlid() %>" portletName="<%= OSBLCSPortletKeys.NAVIGATION %>" var="dashboardURL">
					<portlet:param name="layoutLCSClusterEntryId" value="0" />
					<portlet:param name="layoutLCSClusterNodeId" value="0" />
					<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(lcsProject.getLcsProjectId()) %>" />
				</liferay-portlet:renderURL>

				<aui:a href="<%= dashboardURL %>">
					<span class="icon-chevron-right profile-icon"></span>

					<%= HtmlUtil.escape(lcsProject.getName()) %>
				</aui:a>
			</li>

		<%
		}
		%>

		<li class="sign-out">
			<aui:a href="<%= NavigationConstants.LOGOUT_URL %>">
				<span class="icon-power-off profile-icon"></span>

				<liferay-ui:message key="sign-out" />
			</aui:a>
		</li>
	</ul>
</div>

<div class="dropdown-menu-toggler lcs-messages-toggler" id="<portlet:namespace />lcsMessagesToggler">
	<span class="lcs-messages-icon">
		<i class="icon-bell"></i>

		<%
		int userLCSMessagesCount = UserLCSMessageServiceUtil.getUserLCSMessagesCount();

		if (userLCSMessagesCount > 0) {
		%>

			<span class="unread" id="<portlet:namespace />userLCSMessagesCount">
				<%= userLCSMessagesCount %>
			</span>

		<%
		}
		%>

	</span>
</div>

<div class="dropdown-menu lcs-messages-menu" id="<portlet:namespace />lcsMessagesMenu"></div>

<liferay-portlet:renderURL var="getUserLCSMessagesURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
	<portlet:param name="mvcPath" value="/header/messages.jsp" />
</liferay-portlet:renderURL>

<portlet:resourceURL id="hideUserLCSMessages" var="hideUserLCSMessagesURL">
	<portlet:param name="p_auth" value="<%= AuthTokenUtil.getToken(request) %>" />
</portlet:resourceURL>

<aui:script use="lcs-header">
	new Liferay.Portlet.LCSHeader(
		{
			errorMessage: '<%= UnicodeLanguageUtil.get(request, "your-request-failed-to-complete") %>',
			namespace: '<portlet:namespace />',
			urls: {
				getUserLCSMessages: '<%= getUserLCSMessagesURL %>',
				hideUserLCSMessages: '<%= hideUserLCSMessagesURL %>'
			},
			userLCSMessagesCount: <%= userLCSMessagesCount %>
		}
	);
</aui:script>