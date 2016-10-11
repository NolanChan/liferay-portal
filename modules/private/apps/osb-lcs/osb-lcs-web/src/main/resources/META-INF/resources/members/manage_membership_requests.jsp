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
%>

<div class="lcs-message-container"></div>

<div class="lcs-manage-membership-request">
	<aui:form name="assignRolesFm" onSubmit="event.preventDefault();">
		<aui:input name="lcsProjectId" type="hidden" value="<%= String.valueOf(layoutLCSProjectId) %>" />

		<aui:row>
			<aui:col cssClass="lcs-search-users" width="<%= 50 %>">
				<span class="icon-search search-users-icon"></span>

				<aui:input cssClass="search-users-input" label="" name="searchUsers" placeholder='<%= LanguageUtil.get(request, "search") %>' />
			</aui:col>
		</aui:row>

		<aui:row>
			<aui:col width="<%= 50 %>">
				<aui:input cssClass="lcs-select-all-users" label="users" name="selectAllUsers" type="checkbox" />
			</aui:col>

			<aui:col width="<%= 50 %>">
				<span class="selected-users-label"><liferay-ui:message key="selected-users" /></span>
			</aui:col>
		</aui:row>

		<aui:row cssClass="lcs-users">
			<aui:col cssClass="lcs-remote-users-column lcs-users-column" width="<%= 50 %>">
				<div class="lcs-scroll-panel lcs-scroll-panel-remote">

					<%
					for (User unprovisionedLCSProjectUser : LCSMembersUtil.getUnprovisionedLCSProjectUsers(layoutLCSProjectId)) {
					%>

						<aui:field-wrapper cssClass="lcs-user-wrapper">
							<aui:input label="" name="remoteUserId" type="checkbox" value="<%= String.valueOf(unprovisionedLCSProjectUser.getUserId()) %>" />

							<span class="lcs-user">
								<div class="user-avatar-image" style="background-image: url(<%= HtmlUtil.escape(unprovisionedLCSProjectUser.getPortraitURL(themeDisplay)) %>);"></div>

								<span class="lcs-user-name"><%= HtmlUtil.escape(unprovisionedLCSProjectUser.getFullName()) %></span>
							</span>
						</aui:field-wrapper>

					<%
					}
					%>

				</div>

				<aui:button cssClass="btn-add-users" icon="icon-chevron-right" name="addButton" />
			</aui:col>

			<aui:col cssClass="lcs-selected-users-column lcs-users-column" last="<%= true %>" width="<%= 50 %>">
				<div class="lcs-scroll-panel lcs-scroll-panel-selected" id="<portlet:namespace />selectedRemoteUsers"></div>
			</aui:col>
		</aui:row>

		<aui:row cssClass="lcs-role-select-container">

			<%
			List<LCSClusterEntry> lcsClusterEntries = LCSClusterEntryServiceUtil.getLCSProjectLCSClusterEntries(layoutLCSProjectId);
			%>

			<aui:col width="<%= 50 %>">
				<aui:select label="choose-role" name="role">

					<%
					for (int role : new int[] {LCSRoleConstants.ROLE_LCS_ENVIRONMENT_VIEWER, LCSRoleConstants.ROLE_LCS_ENVIRONMENT_MANAGER, LCSRoleConstants.ROLE_LCS_ADMINISTRATOR}) {
						if ((role == LCSRoleConstants.ROLE_LCS_ADMINISTRATOR) || !lcsClusterEntries.isEmpty()) {
					%>

							<aui:option label="<%= LCSRoleConstants.getRoleLabel(role) %>" value="<%= String.valueOf(role) %>" />

					<%
						}
					}
					%>

				</aui:select>
			</aui:col>

			<aui:col width="<%= 50 %>">
				<c:if test="<%= !lcsClusterEntries.isEmpty() %>">
					<aui:select label="environment" name="lcsClusterEntryId">

						<%
						for (LCSClusterEntry lcsClusterEntry : lcsClusterEntries) {
						%>

							<aui:option label="<%= HtmlUtil.escape(lcsClusterEntry.getName()) %>" selected="<%= lcsClusterEntry.getLcsClusterEntryId() == layoutLCSClusterEntryId %>" value="<%= String.valueOf(lcsClusterEntry.getLcsClusterEntryId()) %>" />

						<%
						}
						%>

					</aui:select>
				</c:if>
			</aui:col>
		</aui:row>

		<aui:button-row align="right">
			<aui:button cssClass="btn-primary" name="assignRolesButton" value="assign-roles" />

			<aui:button cssClass="btn-link" name="rejectRequestsButton" value="reject" />

			<aui:button cssClass="btn-link" name="cancelButton" value="cancel" />
		</aui:button-row>
	</aui:form>
</div>