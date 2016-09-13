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

<div class="message-container" id="<portlet:namespace />messageContainer"></div>

<div class="lcs-tabs-default" id="<portlet:namespace />projectTabs">
	<liferay-ui:tabs
		names="my-projects,unlinked,pending,company"
		refresh="<%= false %>"
	>
		<liferay-ui:section>
			<div class="block updating" id="<portlet:namespace />updating"></div>

			<div class="fade-out table-container" id="<portlet:namespace />activeProjects">
				<div class="header">
					<h2 class="title">
						<liferay-ui:message key="projects" />
					</h2>
				</div>

				<div class="content"></div>
			</div>
		</liferay-ui:section>

		<liferay-ui:section>
			<div class="lcs-note">
				<liferay-ui:message key="unlinked-projects-are-your-liferay-projects-that-are-not-connected-with-liferay-connected-services" />
			</div>

			<div id="<portlet:namespace />unlinkedMessageContainer"></div>

			<div class="fade-out hide table-container" id="<portlet:namespace />unadministeredProjects">
				<div class="header">
					<h2 class="title">
						<liferay-ui:message key="projects-without-an-administrator" />
					</h2>

					<div class="hide updating"></div>
				</div>

				<div class="lcs-note">
					<liferay-ui:message key="these-projects-do-not-have-a-project-administrator-on-liferay-connected-services" />
				</div>

				<div class="alert alert-warning lcs-note">
					<liferay-ui:message key="as-a-project-administrator-you-will-be-responsible-for-granting-access-to-your-project" />
				</div>

				<div class="content"></div>
			</div>

			<div class="fade-out hide table-container" id="<portlet:namespace />administeredProjects">
				<div class="header">
					<h2 class="title">
						<liferay-ui:message key="projects-with-an-administrator" />
					</h2>

					<div class="hide updating"></div>
				</div>

				<div class="lcs-note">
					<liferay-ui:message key="these-projects-have-a-project-administrator-on-liferay-connected-services" />
				</div>

				<div class="content"></div>
			</div>
		</liferay-ui:section>

		<liferay-ui:section>
			<div class="lcs-note">
				<liferay-ui:message key="you-requested-access-to-these-projects" />
			</div>

			<div id="<portlet:namespace />pendingMessageContainer"></div>

			<div class="fade-out hide table-container" id="<portlet:namespace />pendingProjects">
				<div class="header">
					<h2 class="title">
						<liferay-ui:message key="projects" />
					</h2>
				</div>

				<div class="content"></div>
			</div>
		</liferay-ui:section>

		<liferay-ui:section>
			<div class="lcs-note">
				<liferay-ui:message key="based-on-your-email-address-here-is-a-list-of-available-projects-created-by-your-company's-employees" />
			</div>

			<div id="<portlet:namespace />companyMessageContainer"></div>

			<div class="fade-out hide table-container" id="<portlet:namespace />companyProjects">
				<div class="header">
					<h2 class="title">
						<liferay-ui:message key="projects" />
					</h2>

					<div class="hide updating"></div>
				</div>

				<div class="content"></div>
			</div>
		</liferay-ui:section>
	</liferay-ui:tabs>
</div>

<div class="fade-out footer-note" id="<portlet:namespace />projectsFooter">
	<liferay-ui:message arguments='<%= StringUtil.replace(PortletPropsValues.LRDCOM_USER_PROJECTS_URL, "{0}", user.getScreenName()) %>' key="for-additional-options-please-visit-your-personal-liferay-projects-page" />
</div>

<portlet:resourceURL id="addLCSAdministratorRole" var="addLCSAdministratorRoleURL">
	<portlet:param name="p_auth" value="<%= AuthTokenUtil.getToken(request) %>" />
</portlet:resourceURL>

<portlet:resourceURL id="addLCSEnvironmentMembershipPendingUserRole" var="addLCSEnvironmentMembershipPendingUserRoleURL">
	<portlet:param name="p_auth" value="<%= AuthTokenUtil.getToken(request) %>" />
</portlet:resourceURL>

<portlet:resourceURL id="getUserLCSProjects" var="getUserLCSProjectsURL">
	<portlet:param name="p_auth" value="<%= AuthTokenUtil.getToken(request) %>" />
</portlet:resourceURL>

<portlet:resourceURL id="updateLCSProjectName" var="updateLCSProjectNameURL">
	<portlet:param name="p_auth" value="<%= AuthTokenUtil.getToken(request) %>" />
</portlet:resourceURL>

<aui:script use="lcs-enrollment">
	new Liferay.Portlet.LCSEnrollment(
		{
			errorMessage: '<%= UnicodeLanguageUtil.get(pageContext, "your-request-failed-to-complete") %>',
			labels: {
				administratorEmailAddresses: '<%= UnicodeLanguageUtil.get(pageContext, "administrator-email-addresses") %>',
				all: '<%= UnicodeLanguageUtil.get(pageContext, "all") %>',
				becomeAnAdministrator: '<%= UnicodeLanguageUtil.get(pageContext, "become-an-administrator") %>',
				msgConfirmBecomingAdministrator: '<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-become-an-administrator-on-the-project") %>',
				msgConfirmProjectAccessRequest: '<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-request-access-to-the-project") %>',
				msgNoCompanyProjects: '<%= UnicodeLanguageUtil.get(pageContext, "there-are-no-company-projects") %>',
				msgNoPendingProjects: '<%= UnicodeLanguageUtil.get(pageContext, "there-are-no-pending-projects") %>',
				msgNoSearchResults: '<%= UnicodeLanguageUtil.get(pageContext, "there-are-no-search-results") %>',
				msgNoUnlinkedProjects: '<%= UnicodeLanguageUtil.get(pageContext, "there-are-no-unlinked-projects") %>',
				msgProjectAccessRequestSuccess: '<%= UnicodeLanguageUtil.get(pageContext, "you-successfully-requested-access-to-the-project") %>',
				name: '<%= UnicodeLanguageUtil.get(pageContext, "name") %>',
				requestAccess: '<%= UnicodeLanguageUtil.get(pageContext, "request-access") %>',
				search: '<%= UnicodeLanguageUtil.get(pageContext, "search") %>'
			},
			lcsConstants: {
				JSON_KEY_DATA: '<%= LCSConstants.JSON_KEY_DATA %>',
				JSON_KEY_MESSAGE: '<%= LCSConstants.JSON_KEY_MESSAGE %>',
				JSON_KEY_RESULT: '<%= LCSConstants.JSON_KEY_RESULT %>',
				JSON_VALUE_SUCCESS: '<%= LCSConstants.JSON_VALUE_SUCCESS %>'
			},
			namespace: '<portlet:namespace />',
			successMessage: '<%= UnicodeLanguageUtil.get(pageContext, "your-request-completed-successfully") %>',
			urls: {
				addLCSAdministratorRole: '<%= addLCSAdministratorRoleURL %>',
				addLCSEnvironmentMembershipPendingUserRole: '<%= addLCSEnvironmentMembershipPendingUserRoleURL %>',
				getUserLCSProjects: '<%= getUserLCSProjectsURL %>',
				updateLCSProjectName: '<%= updateLCSProjectNameURL %>'
			}
		}
	);
</aui:script>