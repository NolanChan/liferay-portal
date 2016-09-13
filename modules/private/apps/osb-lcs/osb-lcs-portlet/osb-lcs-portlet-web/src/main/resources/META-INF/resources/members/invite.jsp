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

<div class="lcs-message-container"></div>

<aui:form method="post" name="inviteFm" onSubmit="event.preventDefault();">
	<aui:input name="lcsProjectId" type="hidden" value="<%= String.valueOf(layoutLCSProjectId) %>" />

	<aui:input name="emailAddress" type="email" />

	<aui:input helpMessage="the-message-will-be-included-in-the-invitation-email" label="invitation-message" name="invitationMessage" type="textarea" />

	<%
	List<LCSClusterEntry> lcsClusterEntries = LCSClusterEntryServiceUtil.getLCSProjectLCSClusterEntries(layoutLCSProjectId);
	%>

	<aui:select name="role">

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

	<c:if test="<%= !lcsClusterEntries.isEmpty() %>">
		<aui:select label="environment" name="lcsClusterEntryId">

			<%
			for (LCSClusterEntry lcsClusterEntry : lcsClusterEntries) {
			%>

				<aui:option label="<%= HtmlUtil.escape(lcsClusterEntry.getName()) %>" value="<%= String.valueOf(lcsClusterEntry.getLcsClusterEntryId()) %>" />

			<%
			}
			%>

		</aui:select>
	</c:if>

	<aui:button-row>
		<aui:button cssClass="btn-primary" disabled="<%= true %>" name="confirmInviteButton" value="send-email" />

		<aui:button cssClass="btn-link" name="cancelButton" value="cancel" />
	</aui:button-row>
</aui:form>