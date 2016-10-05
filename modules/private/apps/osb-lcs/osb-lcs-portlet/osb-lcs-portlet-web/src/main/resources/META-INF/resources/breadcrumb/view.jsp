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
long layoutLCSClusterNodeId = ParamUtil.getLong(request, "layoutLCSClusterNodeId");
long layoutLCSProjectId = ParamUtil.getLong(request, "layoutLCSProjectId", LCSProjectServiceUtil.getUserDefaultLCSProjectId());

Layout dashboardLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), true, NavigationConstants.FRIENDLY_URL_DASHBOARD);
Layout lcsClusterEntryLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), true, NavigationConstants.FRIENDLY_URL_LCS_CLUSTER_ENTRY);
Layout lcsClusterNodeLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), true, NavigationConstants.FRIENDLY_URL_LCS_CLUSTER_NODE);
LCSRole lcsRole = LCSRoleLocalServiceUtil.fetchLCSRole(themeDisplay.getUserId(), layoutLCSProjectId);
%>

<c:if test="<%= lcsRole != null %>">
	<ul>
		<li>
			<liferay-portlet:renderURL plid="<%= (layoutLCSClusterEntryId > 0) ? dashboardLayout.getPlid() : plid %>" portletName="<%= OSBLCSPortletKeys.BREADCRUMB %>" var="layoutLCSProjectURL">
				<portlet:param name="layoutLCSClusterEntryId" value="0" />
				<portlet:param name="layoutLCSClusterNodeId" value="0" />
				<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
			</liferay-portlet:renderURL>

			<aui:a href="<%= layoutLCSProjectURL %>" label="<%= HtmlUtil.escape(LCSProjectServiceUtil.getLCSProject(layoutLCSProjectId).getName()) %>" />

			<liferay-ui:icon-menu
				direction="bottom"
				extended="<%= false %>"
				icon="<%= StringPool.BLANK %>"
				message="<%= StringPool.BLANK %>"
				showWhenSingleIcon="<%= true %>"
				triggerCssClass="icon-chevron-sign-down trigger"
			>

				<%
				for (LCSProject lcsProject : LCSProjectServiceUtil.getUserLCSProjects(false, LCSRoleConstants.ROLE_LCS_ENVIRONMENT_MEMBERSHIP_PENDING_USER)) {
				%>

					<liferay-portlet:renderURL plid="<%= (layoutLCSClusterEntryId > 0) ? dashboardLayout.getPlid() : plid %>" portletName="<%= OSBLCSPortletKeys.BREADCRUMB %>" var="lcsProjectURL">
						<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(lcsProject.getLcsProjectId()) %>" />
						<portlet:param name="layoutLCSClusterEntryId" value="0" />
						<portlet:param name="layoutLCSClusterNodeId" value="0" />
					</liferay-portlet:renderURL>

					<liferay-ui:icon
						message="<%= HtmlUtil.escape(lcsProject.getName()) %>"
						method="get"
						url="<%= lcsProjectURL %>"
					/>

				<%
				}
				%>

			</liferay-ui:icon-menu>
		</li>

		<c:if test="<%= layoutLCSClusterEntryId != 0 %>">
			<li class="space">
				<%= StringPool.FORWARD_SLASH %>
			</li>
			<li>

				<%
				LCSClusterEntry layoutLCSClusterEntry = LCSClusterEntryServiceUtil.getLCSClusterEntry(layoutLCSClusterEntryId);
				%>

				<liferay-portlet:renderURL plid="<%= lcsClusterEntryLayout.getPlid() %>" portletName="<%= OSBLCSPortletKeys.BREADCRUMB %>" var="lcsClusterEntryURL">
					<portlet:param name="layoutLCSClusterEntryId" value="<%= String.valueOf(layoutLCSClusterEntryId) %>" />
					<portlet:param name="layoutLCSClusterNodeId" value="0" />
					<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
				</liferay-portlet:renderURL>

				<aui:a cssClass="environment-link" href="<%= lcsClusterEntryURL %>" label="<%= HtmlUtil.escape(layoutLCSClusterEntry.getName()) %>" />

				<liferay-ui:icon-menu
					direction="bottom"
					extended="<%= false %>"
					icon="<%= StringPool.BLANK %>"
					message="<%= StringPool.BLANK %>"
					showWhenSingleIcon="<%= true %>"
					triggerCssClass="icon-chevron-sign-down trigger"
				>

					<%
					for (LCSClusterEntry lcsClusterEntry : LCSClusterEntryServiceUtil.getLCSProjectLCSClusterEntries(layoutLCSProjectId)) {
					%>

						<liferay-portlet:renderURL plid="<%= lcsClusterEntryLayout.getPlid() %>" portletName="<%= OSBLCSPortletKeys.BREADCRUMB %>" var="lcsClusterEntryURL">
							<portlet:param name="layoutLCSClusterEntryId" value="<%= String.valueOf(lcsClusterEntry.getLcsClusterEntryId()) %>" />
							<portlet:param name="layoutLCSClusterNodeId" value="0" />
							<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
						</liferay-portlet:renderURL>

						<liferay-ui:icon
							message="<%= HtmlUtil.escape(lcsClusterEntry.getName()) %>"
							method="get"
							url="<%= lcsClusterEntryURL %>"
						/>

					<%
					}
					%>

				</liferay-ui:icon-menu>
			</li>
		</c:if>

		<c:if test="<%= layoutLCSClusterNodeId != 0 %>">
			<li class="space">
				<%= StringPool.FORWARD_SLASH %>
			</li>
			<li>

				<%
				LCSClusterNode layoutLCSClusterNode = LCSClusterNodeServiceUtil.getLCSClusterNode(layoutLCSClusterNodeId);
				%>

				<liferay-portlet:renderURL plid="<%= lcsClusterNodeLayout.getPlid() %>" portletName="<%= OSBLCSPortletKeys.BREADCRUMB %>" var="lcsClusterNodeURL">
					<portlet:param name="layoutLCSClusterEntryId" value="<%= String.valueOf(layoutLCSClusterEntryId) %>" />
					<portlet:param name="layoutLCSClusterNodeId" value="<%= String.valueOf(layoutLCSClusterNodeId) %>" />
					<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
				</liferay-portlet:renderURL>

				<aui:a cssClass="server-link" href="<%= lcsClusterNodeURL %>" label="<%= HtmlUtil.escape(layoutLCSClusterNode.getName()) %>" />

				<liferay-ui:icon-menu
					direction="bottom"
					extended="<%= false %>"
					icon="<%= StringPool.BLANK %>"
					message="<%= StringPool.BLANK %>"
					showWhenSingleIcon="<%= true %>"
					triggerCssClass="icon-chevron-sign-down trigger"
				>

					<%
					for (LCSClusterNode lcsClusterNode : LCSClusterNodeServiceUtil.getLCSClusterEntryLCSClusterNodes(layoutLCSClusterEntryId)) {
					%>

						<liferay-portlet:renderURL plid="<%= lcsClusterNodeLayout.getPlid() %>" portletName="<%= OSBLCSPortletKeys.BREADCRUMB %>" var="lcsClusterNodeURL">
							<portlet:param name="layoutLCSClusterEntryId" value="<%= String.valueOf(layoutLCSClusterEntryId) %>" />
							<portlet:param name="layoutLCSClusterNodeId" value="<%= String.valueOf(lcsClusterNode.getLcsClusterNodeId()) %>" />
							<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
						</liferay-portlet:renderURL>

						<liferay-ui:icon
							message="<%= HtmlUtil.escape(lcsClusterNode.getName()) %>"
							method="get"
							url="<%= lcsClusterNodeURL %>"
						/>

					<%
					}
					%>

				</liferay-ui:icon-menu>
			</li>
		</c:if>
	</ul>
</c:if>