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
long lcsClusterEntryId = ParamUtil.getLong(request, "lcsClusterEntryId");
long lcsClusterNodeId = ParamUtil.getLong(request, "lcsClusterNodeId");
long lcsProjectId = ParamUtil.getLong(request, "lcsProjectId");
%>

<h2>
	<liferay-portlet:renderURL var="viewNotificationsURL">
		<portlet:param name="mvcPath" value="/account/view.jsp" />
	</liferay-portlet:renderURL>

	<aui:a cssClass="icon-circle-arrow-left" href="<%= viewNotificationsURL %>" />

	<liferay-ui:message key='<%= (lcsProjectId == 0) ? "add-notification-rule" : "edit-notification-rule" %>' />
</h2>

<liferay-portlet:actionURL name="saveLCSNotifications" var="saveLCSNotificationsURL">
	<portlet:param name="redirect" value="<%= viewNotificationsURL %>" />
</liferay-portlet:actionURL>

<aui:form action="<%= saveLCSNotificationsURL %>" name="fm">
	<aui:input name="notifications" type="hidden" />
	<aui:input name="notificationTypes" type="hidden" />

	<c:choose>
		<c:when test="<%= lcsProjectId > 0 %>">
			<aui:input name="lcsClusterEntryId" type="hidden" value="<%= lcsClusterEntryId %>" />
			<aui:input name="lcsClusterNodeId" type="hidden" value="<%= lcsClusterNodeId %>" />
			<aui:input name="lcsProjectId" type="hidden" value="<%= lcsProjectId %>" />

			<%
			LCSProject lcsProject = LCSProjectServiceUtil.getLCSProject(lcsProjectId);
			%>

			<div class="lcs-section">
				<div class="lcs-section-label">
					<liferay-ui:message key="project" />
				</div>

				<div class="lcs-section-content">
					<%= lcsProject.getName() %>
				</div>
			</div>

			<div class="lcs-section">
				<div class="lcs-section-label">
					<liferay-ui:message key="environment" />
				</div>

				<div class="lcs-section-content">

					<%
					String lcsClusterEntryName = LanguageUtil.get(request, "all");

					if (lcsClusterEntryId != LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID) {
						LCSClusterEntry lcsClusterEntry = LCSClusterEntryServiceUtil.getLCSClusterEntry(lcsClusterEntryId);

						lcsClusterEntryName = lcsClusterEntry.getName();
					}
					%>

					<%= lcsClusterEntryName %>
				</div>
			</div>

			<div class="lcs-section">
				<div class="lcs-section-label">
					<liferay-ui:message key="server" />
				</div>

				<div class="lcs-section-content">

					<%
					String lcsClusterNodeName = LanguageUtil.get(request, "all");

					if (lcsClusterNodeId != LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID) {
						LCSClusterNode lcsClusterNode = LCSClusterNodeServiceUtil.getLCSClusterNode(lcsClusterNodeId);

						lcsClusterNodeName = lcsClusterNode.getName();
					}
					%>

					<%= lcsClusterNodeName %>
				</div>
			</div>
		</c:when>
		<c:otherwise>

			<%
			List<LCSProject> userLCSProjects = LCSProjectServiceUtil.getUserLCSProjects(false, LCSRoleConstants.ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER);
			%>

			<aui:select label="project" name="lcsProjectId">

				<%
				for (LCSProject lcsProject : userLCSProjects) {
				%>

					<aui:option label="<%= HtmlUtil.escape(lcsProject.getName()) %>" selected="<%= lcsProjectId == lcsProject.getLcsProjectId() %>" value="<%= lcsProject.getLcsProjectId() %>" />

				<%
				}
				%>

			</aui:select>

			<aui:select label="environment" name="lcsClusterEntryId">
				<aui:option label="all" value="<%= LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID %>" />

				<%
				LCSProject lcsProject = userLCSProjects.get(0);

				List<LCSClusterEntry> lcsClusterEntries = LCSClusterEntryServiceUtil.getLCSProjectLCSClusterEntries(lcsProject.getLcsProjectId());

				for (LCSClusterEntry lcsClusterEntry : lcsClusterEntries) {
				%>

					<aui:option label="<%= HtmlUtil.escape(lcsClusterEntry.getName()) %>" value="<%= lcsClusterEntry.getLcsClusterEntryId() %>" />

				<%
				}
				%>

			</aui:select>

			<aui:select label="server" name="lcsClusterNodeId">
				<aui:option label="all" value="<%= LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID %>" />
			</aui:select>
		</c:otherwise>
	</c:choose>

	<div class="lcs-section">
		<div class="lcs-section-label">
			<liferay-ui:message key="notifications" />
		</div>

		<div class="lcs-section-note">
			<liferay-ui:message key="send-me-an-email-when" />
		</div>

		<div class="lcs-checkboxes-container lcs-section-content" id="<portlet:namespace />notifications-container">

			<%
			Layout downloadsLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), true, NavigationConstants.FRIENDLY_URL_DOWNLOADS);
			%>

			<liferay-portlet:renderURL plid="<%= downloadsLayout.getPlid() %>" portletName="<%= OSBLCSPortletKeys.DOWNLOADS %>" var="downloadsURL">
				<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
			</liferay-portlet:renderURL>

			<%
			for (LCSEventType lcsNotificationType : LCSEventType.getSupported()) {
			%>

				<aui:field-wrapper>
					<aui:input cssClass="notification" data-type="<%= lcsNotificationType.getType() %>" label="<%= HtmlUtil.escape(LanguageUtil.get(request, lcsNotificationType.getLabel())) %>" name='<%= "notification" + lcsNotificationType.getType() %>' type="checkbox" />

					<c:if test="<%= lcsNotificationType.isEnterpriseSubscriptionRequired() %>">
						<span class="icon-briefcase requirement" title="<liferay-ui:message key="this-notification-is-only-available-for-enterprise-subscribers" />"></span>
					</c:if>

					<c:if test="<%= lcsNotificationType.getMinimumSupportedLCSPortletVersion() > 10 %>">
						<aui:a cssClass="requirement" href="<%= downloadsURL %>" title='<%= LanguageUtil.format(request, "this-notification-requires-that-your-server-has-liferay-connected-services-client-x-or-later-version-installed", lcsNotificationType.getMinimumSupportedLCSPortletVersion()) %>'>
							(<liferay-ui:message arguments="<%= lcsNotificationType.getMinimumSupportedLCSPortletVersion() %>" key="client-x" />)
						</aui:a>
					</c:if>
				</aui:field-wrapper>

			<%
			}
			%>

		</div>
	</div>

	<aui:button-row>
		<aui:button name="saveNotifications" type="submit" value="save" />

		<aui:button cssClass="btn-link" href="<%= viewNotificationsURL %>" value="cancel" />
	</aui:button-row>
</aui:form>

<aui:script use="lcs-account">
	var lcsAccount = new Liferay.Portlet.LCSAccount(
		{
			errorMessage: '<%= UnicodeLanguageUtil.get(request, "your-request-failed-to-complete") %>',
			lcsConstants: {
				ALL_ID: '<%= LCSConstants.ALL_LCS_CLUSTER_OBJECTS_ID %>',
				JSON_KEY_DATA: '<%= LCSConstants.JSON_KEY_DATA %>',
				JSON_KEY_MESSAGE: '<%= LCSConstants.JSON_KEY_MESSAGE %>',
				JSON_KEY_RESULT: '<%= LCSConstants.JSON_KEY_RESULT %>',
				JSON_VALUE_SUCCESS: '<%= LCSConstants.JSON_VALUE_SUCCESS %>'
			},
			namespace: '<portlet:namespace />'
		}
	);

	lcsAccount.initializeEditEmailNotificationsPage(
		{
			labels: {
				all: '<%= UnicodeLanguageUtil.get(request, "all") %>'
			},
			projectsLCSNotifications: <%= com.liferay.osb.lcs.account.util.AccountAdvisor.getLCSProjectsLCSNotificationsJSONArray() %>,
			selected: {
				environmentId: <%= lcsClusterEntryId %>,
				projectId: <%= lcsProjectId %>,
				serverId: <%= lcsClusterNodeId %>
			}
		}
	);
</aui:script>