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
long layoutLCSClusterEntryId = ParamUtil.getLong(request, "layoutLCSClusterEntryId");
long layoutLCSProjectId = ParamUtil.getLong(request, "layoutLCSProjectId", LCSProjectServiceUtil.getUserDefaultLCSProjectId());
long memberUserId = ParamUtil.getLong(request, "memberUserId");

List<LCSClusterEntry> lcsClusterEntries = LCSClusterEntryServiceUtil.getLCSProjectLCSClusterEntries(layoutLCSProjectId);
List<LCSRole> memberUserLCSRoles = LCSRoleLocalServiceUtil.getUserLCSRoles(memberUserId, layoutLCSProjectId);

List<LCSClusterEntry> assignableLCSClusterEntries = new ArrayList<LCSClusterEntry>(lcsClusterEntries);
Map<Long, String> lcsClusterEntryNameToLCSClusterEntryId = new HashMap<Long, String>();

for (LCSClusterEntry lcsClusterEntry : lcsClusterEntries) {
	lcsClusterEntryNameToLCSClusterEntryId.put(lcsClusterEntry.getLcsClusterEntryId(), lcsClusterEntry.getName());

	for (LCSRole memberUserLCSRole : memberUserLCSRoles) {
		if (lcsClusterEntry.getLcsClusterEntryId() == memberUserLCSRole.getLcsClusterEntryId()) {
			assignableLCSClusterEntries.remove(lcsClusterEntry);
		}
	}
}
%>

<liferay-portlet:renderURL var="backURL">
	<portlet:param name="mvcPath" value="/members/view.jsp" />
</liferay-portlet:renderURL>

<liferay-portlet:actionURL name="addLCSRole" var="addLCSRoleURL" />

<aui:form action="<%= addLCSRoleURL %>" name="fm" onSubmit="event.preventDefault();">
	<aui:input name="mvcPath" type="hidden" value="/members/manage_lcs_role.jsp" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="lcsProjectId" type="hidden" value="<%= String.valueOf(layoutLCSProjectId) %>" />
	<aui:input name="userId" type="hidden" value="<%= String.valueOf(memberUserId) %>" />

	<%
	User memberUser = UserLocalServiceUtil.fetchUser(memberUserId);
	%>

	<h2>
		<liferay-ui:message arguments="<%= memberUser.getFullName() %>" key="xs-roles" />
	</h2>

	<liferay-portlet:renderURL varImpl="iteratorURL">
		<portlet:param name="mvcPath" value="/members/manage_lcs_role.jsp" />
		<portlet:param name="layoutLCSClusterEntryId" value="<%= String.valueOf(layoutLCSClusterEntryId) %>" />
		<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
		<portlet:param name="memberUserId" value="<%= String.valueOf(memberUserId) %>" />
	</liferay-portlet:renderURL>

	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-assigned-roles"
		iteratorURL="<%= iteratorURL %>"
	>
		<liferay-ui:search-container-results
			results="<%= memberUserLCSRoles %>"
			total="<%= memberUserLCSRoles.size() %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.lcs.model.LCSRole"
			escapedModel="<%= true %>"
			keyProperty="lcsRoleId"
			modelVar="memberUserLCSRole"
		>

			<%
			String environmentLabel = null;

			String environmentName = lcsClusterEntryNameToLCSClusterEntryId.get(memberUserLCSRole.getLcsClusterEntryId());

			if (environmentName == null) {
				environmentLabel = LanguageUtil.get(request, "all-environments");
			}
			else {
				environmentLabel = HtmlUtil.escape(environmentName);
			}
			%>

			<liferay-ui:search-container-column-text cssClass='<%= (memberUserLCSRole.getRole() == LCSRoleConstants.ROLE_LCS_ADMINISTRATOR) ? StringPool.BLANK : "lcs-environment-role" %>' name="role" value="<%= LanguageUtil.get(request, LCSRoleConstants.getRoleLabel(memberUserLCSRole.getRole())) %>" />

			<liferay-ui:search-container-column-text name="environment" value="<%= environmentLabel %>" />

			<c:if test="<%= memberUserLCSRole.getUserId() != themeDisplay.getUserId() %>">
				<liferay-ui:search-container-column-text name="action">
					<liferay-ui:icon-menu icon="">
						<portlet:actionURL name="deleteLCSRole" var="deleteLCSRoleURL">
							<portlet:param name="mvcPath" value="/members/manage_lcs_role.jsp" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="lcsRoleId" value="<%= String.valueOf(memberUserLCSRole.getLcsRoleId()) %>" />
							<portlet:param name="userId" value="<%= String.valueOf(memberUserLCSRole.getUserId()) %>" />
						</portlet:actionURL>

						<liferay-ui:icon iconCssClass="icon-remove" message="revoke-role" url="<%= deleteLCSRoleURL %>" />
					</liferay-ui:icon-menu>
				</liferay-ui:search-container-column-text>
			</c:if>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator paginate="<%= false %>" />

		<%
		int selRole = -1;

		if (!memberUserLCSRoles.isEmpty()) {
			LCSRole selLCSRole = memberUserLCSRoles.get(0);

			selRole = selLCSRole.getRole();
		}
		%>

		<c:if test="<%= memberUserLCSRoles.isEmpty() || (selRole != LCSRoleConstants.ROLE_LCS_ADMINISTRATOR) %>">
			<aui:select name="role">

				<%
				for (int role : new int[] {LCSRoleConstants.ROLE_LCS_ENVIRONMENT_VIEWER, LCSRoleConstants.ROLE_LCS_ENVIRONMENT_MANAGER, LCSRoleConstants.ROLE_LCS_ADMINISTRATOR}) {
					if ((role == LCSRoleConstants.ROLE_LCS_ADMINISTRATOR) || !assignableLCSClusterEntries.isEmpty()) {
				%>

						<aui:option label="<%= LCSRoleConstants.getRoleLabel(role) %>" value="<%= String.valueOf(role) %>" />

				<%
					}
				}
				%>

			</aui:select>

			<c:if test="<%= !assignableLCSClusterEntries.isEmpty() %>">
				<aui:select label="environment" name="lcsClusterEntryId">

					<%
					for (LCSClusterEntry lcsClusterEntry : assignableLCSClusterEntries) {
					%>

						<aui:option label="<%= HtmlUtil.escape(lcsClusterEntry.getName()) %>" selected="<%= (layoutLCSClusterEntryId == lcsClusterEntry.getLcsClusterEntryId()) %>" value="<%= String.valueOf(lcsClusterEntry.getLcsClusterEntryId()) %>" />

					<%
					}
					%>

				</aui:select>
			</c:if>
		</c:if>

		<aui:button-row>
			<c:if test="<%= memberUserLCSRoles.isEmpty() || (selRole != LCSRoleConstants.ROLE_LCS_ADMINISTRATOR) %>">
				<aui:button type="submit" value="assign-role" />
			</c:if>

			<aui:button cssClass="btn-link" href="<%= backURL %>" value="back" />
		</aui:button-row>
	</liferay-ui:search-container>
</aui:form>

<aui:script use="lcs-members">
	var lcsMembers = new Liferay.Portlet.LCSMembers(
		{
			lcsAdministratorRole: '<%= LCSRoleConstants.ROLE_LCS_ADMINISTRATOR %>',
			namespace: '<portlet:namespace />'
		}
	);

	lcsMembers.initializeManageRolesPage(
		{
			msgConfirmAssignAdministratorRole: '<%= UnicodeLanguageUtil.get(request, "you-are-about-to-assign-the-lcs-administrator-role-to-a-user-that-has-one-or-more-lcs-environment-roles") + UnicodeLanguageUtil.get(request, StringPool.NEW_LINE + StringPool.NEW_LINE) + UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-continue") %>'
		}
	);
</aui:script>