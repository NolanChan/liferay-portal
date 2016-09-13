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

<c:choose>
	<c:when test="<%= LCSProjectPermission.contains(permissionChecker, layoutLCSProjectId, ActionKeys.MANAGE_ENTRY) %>">

		<%
		List<User> unprovisionedLCSProjectUsers = LCSMembersUtil.getUnprovisionedLCSProjectUsers(layoutLCSProjectId);
		%>

		<c:if test="<%= !unprovisionedLCSProjectUsers.isEmpty() %>">
			<div class="request-access-container">
				<div class="alert alert-warning">
					<liferay-ui:message key="there-are-users-requesting-access-to-this-project" />
				</div>

				<aui:button-row>
					<aui:button cssClass="btn-primary" name="assignRolesButton" value="manage-requests" />
				</aui:button-row>
			</div>
		</c:if>

		<liferay-portlet:renderURL varImpl="iteratorURL">
			<portlet:param name="mvcPath" value="/members/view.jsp" />
		</liferay-portlet:renderURL>

		<%
		List<LCSInvitation> lcsInvitations = LCSInvitationServiceUtil.getLCSProjectLCSInvitations(layoutLCSProjectId);
		%>

		<c:choose>
			<c:when test="<%= lcsInvitations.isEmpty() %>">
				<h2>
					<liferay-ui:message key="project-members" />
				</h2>

				<%@ include file="/members/view_members.jspf" %>
			</c:when>
			<c:otherwise>
				<div class="lcs-tabs-default">
					<liferay-ui:tabs
						names="project-members,invitations"
						refresh="<%= false %>"
					>
						<liferay-ui:section>
							<%@ include file="/members/view_members.jspf" %>
						</liferay-ui:section>

						<liferay-ui:section>
							<div class="invitations-message-container"></div>

							<liferay-ui:search-container
								iteratorURL="<%= iteratorURL %>"
							>
								<liferay-ui:search-container-results>

									<%
									results = ListUtil.subList(lcsInvitations, searchContainer.getStart(), searchContainer.getEnd());
									total = lcsInvitations.size();

									pageContext.setAttribute("results", results);
									pageContext.setAttribute("total", total);
									%>

								</liferay-ui:search-container-results>

								<liferay-ui:search-container-row
									className="com.liferay.osb.lcs.model.LCSInvitation"
									escapedModel="<%= true %>"
									modelVar="lcsInvitation"
								>
									<liferay-ui:search-container-column-text name="email-address" property="emailAddress" />

									<%
									User invitedByUser = UserLocalServiceUtil.getUserById(lcsInvitation.getUserId());
									%>

									<liferay-ui:search-container-column-text name="invited-by" value="<%= HtmlUtil.escape(invitedByUser.getFullName()) %>" />

									<liferay-ui:search-container-column-text name="date" value="<%= mediumDateFormatDate.format(lcsInvitation.getCreateDate()) %>" />

									<%
									int role = lcsInvitation.getRole();
									%>

									<liferay-ui:search-container-column-text name="role" value="<%= LanguageUtil.get(pageContext, LCSRoleConstants.getRoleLabel(role)) %>" />

									<%
									LCSClusterEntry lcsClusterEntry = null;

									if (role != LCSRoleConstants.ROLE_LCS_ADMINISTRATOR) {
										lcsClusterEntry = LCSClusterEntryServiceUtil.getLCSClusterEntry(lcsInvitation.getLcsClusterEntryId());
									}
									%>

									<liferay-ui:search-container-column-text name="environment" value='<%= (lcsClusterEntry != null) ? HtmlUtil.escape(lcsClusterEntry.getName()) : LanguageUtil.get(pageContext, "all-environments") %>' />

									<liferay-ui:search-container-column-text name="action">
										<liferay-ui:icon-menu icon="">

											<%
											Map<String, Object> data = new HashMap<String, Object>();

											data.put("lcsinvitationid", lcsInvitation.getLcsInvitationId());
											%>

											<liferay-ui:icon cssClass="cancel-invitation" data="<%= data %>" iconCssClass="icon-remove" message="cancel" url="javascript:;" />
										</liferay-ui:icon-menu>
									</liferay-ui:search-container-column-text>
								</liferay-ui:search-container-row>

								<liferay-ui:search-iterator paginate="<%= false %>" />
							</liferay-ui:search-container>
						</liferay-ui:section>
					</liferay-ui:tabs>
				</div>
			</c:otherwise>
		</c:choose>

		<aui:button-row>
			<aui:button cssClass="btn-primary" name="inviteButton" value="invite" />
		</aui:button-row>

		<aui:script use="lcs-members">
			var lcsMembers = new Liferay.Portlet.LCSMembers(
				{
					errorMessage: '<%= UnicodeLanguageUtil.get(pageContext, "your-request-failed-to-complete") %>',
					lcsAdministratorRole: '<%= LCSRoleConstants.ROLE_LCS_ADMINISTRATOR %>',
					lcsConstants: {
						JSON_KEY_MESSAGE: '<%= LCSConstants.JSON_KEY_MESSAGE %>',
						JSON_KEY_RESULT: '<%= LCSConstants.JSON_KEY_RESULT %>',
						JSON_VALUE_SUCCESS: '<%= LCSConstants.JSON_VALUE_SUCCESS %>'
					},
					namespace: '<portlet:namespace />'
				}
			);

			<portlet:resourceURL id="addLCSRoles" var="addLCSRolesURL">
				<portlet:param name="p_auth" value="<%= AuthTokenUtil.getToken(request) %>" />
			</portlet:resourceURL>

			<portlet:renderURL var="assignRolesPanelURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
				<portlet:param name="mvcPath" value="/members/manage_membership_requests.jsp" />
				<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
				<portlet:param name="lcsProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
			</portlet:renderURL>

			<portlet:resourceURL id="cancelInvitation" var="cancelInvitationURL">
				<portlet:param name="p_auth" value="<%= AuthTokenUtil.getToken(request) %>" />
			</portlet:resourceURL>

			<portlet:resourceURL id="deleteLCSRoles" var="deleteLCSRolesURL">
				<portlet:param name="p_auth" value="<%= AuthTokenUtil.getToken(request) %>" />
			</portlet:resourceURL>

			<portlet:resourceURL id="invite" var="inviteURL">
				<portlet:param name="p_auth" value="<%= AuthTokenUtil.getToken(request) %>" />
			</portlet:resourceURL>

			<portlet:renderURL var="invitePanelURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
				<portlet:param name="mvcPath" value="/members/invite.jsp" />
				<portlet:param name="lcsProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
				<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
			</portlet:renderURL>

			lcsMembers.initializeViewPage(
				{
					assignRolesPanelTitle: '<%= UnicodeLanguageUtil.get(pageContext, "manage-requests") %>',
					assignRolesURL: '<%= addLCSRolesURL %>',
					cancelInvitationURL: '<%= cancelInvitationURL %>',
					invitePanelTitle: '<%= UnicodeLanguageUtil.get(pageContext, "invite-user") %>',
					inviteURL: '<%= inviteURL %>',
					msgConfirmCancelInvitation: '<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-cancel-the-invitation") %>',
					msgConfirmRejectRequests: '<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-reject-the-selected-membership-requests") %>',
					rejectRequestsURL: '<%= deleteLCSRolesURL %>',
					renderAssignRolesPanelURL: '<%= assignRolesPanelURL %>',
					renderInvitePanelURL: '<%= invitePanelURL %>'
				}
			);
		</aui:script>
	</c:when>
	<c:otherwise>
		<div class="portlet-msg-error">
			<liferay-ui:message key="you-do-not-have-permission-to-access-the-requested-resource" />
		</div>
	</c:otherwise>
</c:choose>