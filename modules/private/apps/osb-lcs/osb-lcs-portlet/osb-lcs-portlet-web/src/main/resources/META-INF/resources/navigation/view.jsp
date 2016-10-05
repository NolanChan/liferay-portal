<%@ page import="com.liferay.osb.lcs.advisor.NavigationAdvisor" %>

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
String layoutSettingsModule = ParamUtil.getString(request, "layoutSettingsModule");

Layout dashboardLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), true, NavigationConstants.FRIENDLY_URL_DASHBOARD);
Layout downloadsLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), true, NavigationConstants.FRIENDLY_URL_DOWNLOADS);
Layout lcsClusterEntryLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), true, NavigationConstants.FRIENDLY_URL_LCS_CLUSTER_ENTRY);
Layout lcsClusterNodeLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), true, NavigationConstants.FRIENDLY_URL_LCS_CLUSTER_NODE);
LCSRole lcsRole = LCSRoleLocalServiceUtil.fetchLCSRole(themeDisplay.getUserId(), layoutLCSProjectId);
Layout subscriptionsLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), true, NavigationConstants.FRIENDLY_URL_SUBSCRIPTIONS);
Layout usersLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), true, NavigationConstants.FRIENDLY_URL_USERS);
%>

<c:choose>
	<c:when test="<%= lcsRole == null %>">
		<div class="selected titlebar">
			<div class="title">

				<%
				LCSProject lcsProject = LCSProjectServiceUtil.getLCSProject(layoutLCSProjectId);
				%>

				<%= HtmlUtil.escape(lcsProject.getName()) %>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<div class="lcs-navigation-header">
			<liferay-portlet:renderURL plid="<%= dashboardLayout.getPlid() %>" var="dashboardURL">
				<portlet:param name="layoutLCSClusterEntryId" value="0" />
				<portlet:param name="layoutLCSClusterNodeId" value="0" />
				<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
			</liferay-portlet:renderURL>

			<c:if test="<%= layoutLCSClusterEntryId > 0 %>">
				<div class="menu-item">
					<aui:a href="<%= dashboardURL %>">
						<span class="icon-circle-arrow-left menu-item-icon"></span>

						<span class="menu-item-label">
							<liferay-ui:message key="back" />
						</span>
					</aui:a>
				</div>
			</c:if>

			<div class="menu-item <%= (dashboardLayout.getPlid() == plid) ? "selected" : StringPool.BLANK %>">
				<aui:a cssClass="lcs-dashboard-link" href="<%= dashboardURL %>">
					<span class="icon-home menu-item-icon"></span>

					<span class="menu-item-label">
						<liferay-ui:message key="dashboard" />
					</span>
				</aui:a>
			</div>

			<c:if test="<%= LCSProjectPermission.contains(permissionChecker, layoutLCSProjectId, OSBLCSActionKeys.MANAGE_USERS) %>">
				<div class="menu-item <%= (usersLayout.getPlid() == plid) ? "selected" : StringPool.BLANK %>">
					<liferay-portlet:renderURL plid="<%= usersLayout.getPlid() %>" portletName="<%= OSBLCSPortletKeys.MEMBERS %>" var="usersURL">
						<portlet:param name="mvcPath" value="/members/view.jsp" />
						<portlet:param name="layoutLCSClusterEntryId" value="0" />
						<portlet:param name="layoutLCSClusterNodeId" value="0" />
						<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
					</liferay-portlet:renderURL>

					<aui:a href="<%= usersURL %>">
						<span class="icon-group menu-item-icon"></span>

						<span class="menu-item-label">
							<liferay-ui:message key="users" />
						</span>
					</aui:a>
				</div>
			</c:if>

			<c:if test="<%= AdminUtil.isSubscriptionsEnabled() && LCSProjectPermission.contains(permissionChecker, layoutLCSProjectId, OSBLCSActionKeys.MANAGE_SUBSCRIPTIONS) %>">
				<div class="menu-item <%= (subscriptionsLayout.getPlid() == plid) ? "selected" : StringPool.BLANK %>">
					<liferay-portlet:renderURL plid="<%= subscriptionsLayout.getPlid() %>" portletName="<%= OSBLCSPortletKeys.SUBSCRIPTIONS %>" var="subscriptionsURL">
						<portlet:param name="layoutLCSClusterEntryId" value="0" />
						<portlet:param name="layoutLCSClusterNodeId" value="0" />
						<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
					</liferay-portlet:renderURL>

					<aui:a href="<%= subscriptionsURL %>">
						<span class="icon-subscriptions menu-item-icon"></span>

						<span class="menu-item-label">
							<liferay-ui:message key="subscriptions" />
						</span>
					</aui:a>
				</div>
			</c:if>

			<div class="menu-item <%= (downloadsLayout.getPlid() == plid) ? "selected" : StringPool.BLANK %>">
				<liferay-portlet:renderURL plid="<%= downloadsLayout.getPlid() %>" portletName="<%= OSBLCSPortletKeys.DOWNLOADS %>" var="downloadsURL">
					<portlet:param name="layoutLCSClusterEntryId" value="0" />
					<portlet:param name="layoutLCSClusterNodeId" value="0" />
					<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
				</liferay-portlet:renderURL>

				<aui:a href="<%= downloadsURL %>">
					<span class="icon-download menu-item-icon"></span>

					<span class="menu-item-label">
						<liferay-ui:message key="downloads" />
					</span>
				</aui:a>
			</div>
		</div>

		<div class="lcs-navigation-submenu">
			<c:choose>
				<c:when test="<%= layoutLCSClusterEntryId == 0 %>">
					<div class="environments-titlebar titlebar">
						<c:if test="<%= LCSProjectPermission.contains(permissionChecker, layoutLCSProjectId, OSBLCSActionKeys.ADD_LCS_CLUSTER_ENTRY) %>">
							<div class="titlebar-inner titlebar-top <%= Validator.equals(layoutSettingsModule, LCSConstants.SETTINGS_MODULE_ADD_ENVIRONMENT) ? "selected" : StringPool.BLANK %>">
								<liferay-portlet:renderURL plid="<%= lcsClusterEntryLayout.getPlid() %>" portletName="<%= OSBLCSPortletKeys.ENVIRONMENT %>" var="addLCSClusterEntryURL">
									<portlet:param name="mvcPath" value="/environment/add_lcs_cluster_entry.jsp" />
									<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
								</liferay-portlet:renderURL>

								<aui:a href="<%= addLCSClusterEntryURL %>">
									<span class="icon-plus titlebar-icon"></span>

									<span class="title">
										<liferay-ui:message key="add-environment" />
									</span>
								</aui:a>
							</div>
						</c:if>
					</div>

					<div class="menu-items">

						<%
						for (LCSClusterEntry lcsClusterEntry : LCSClusterEntryServiceUtil.getLCSProjectLCSClusterEntries(layoutLCSProjectId)) {
							long lcsClusterEntryId = lcsClusterEntry.getLcsClusterEntryId();

							List<LCSClusterNode> lcsClusterNodes = LCSClusterNodeServiceUtil.getLCSClusterEntryLCSClusterNodes(lcsClusterEntryId);
						%>

							<div class="menu-item">
								<liferay-portlet:renderURL plid="<%= lcsClusterEntryLayout.getPlid() %>" portletName="<%= OSBLCSPortletKeys.ENVIRONMENT %>" var="lcsClusterEntryURL">
									<portlet:param name="layoutLCSClusterEntryId" value="<%= String.valueOf(lcsClusterEntryId) %>" />
									<portlet:param name="layoutLCSClusterNodeId" value="0" />
									<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
								</liferay-portlet:renderURL>

								<aui:a cssClass="environment" href="<%= lcsClusterEntryURL %>" label="<%= HtmlUtil.escape(lcsClusterEntry.getName()) %>" title="<%= HtmlUtil.escape(lcsClusterEntry.getName()) %>">
									<span class="environment-icon lcs-content-type-icon <%= lcsClusterEntry.isEnvironment() ? "group" : "cluster" %> <%= NavigationAdvisor.getLCSClusterEntryHealthStatusCSSClass(lcsClusterEntry.hasOfflineLCSClusterNode(), lcsClusterNodes.size()) %>" title="<%= LanguageUtil.get(request, NavigationAdvisor.getLCSClusterEntryHealthStatusTitle(lcsClusterEntry.isCluster(), lcsClusterEntry.hasOfflineLCSClusterNode())) %>"></span>
								</aui:a>

								<liferay-portlet:renderURL plid="<%= lcsClusterEntryLayout.getPlid() %>" portletName="<%= OSBLCSPortletKeys.ENVIRONMENT %>" var="lcsClusterEntrySettingsURL">
									<portlet:param name="environmentPage" value="settings" />
									<portlet:param name="layoutLCSClusterEntryId" value="<%= String.valueOf(lcsClusterEntryId) %>" />
									<portlet:param name="layoutLCSClusterNodeId" value="0" />
									<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
								</liferay-portlet:renderURL>

								<aui:a cssClass="icon-cog settings" href="<%= lcsClusterEntrySettingsURL %>" title="environment-settings" />
							</div>

						<%
						}
						%>

					</div>
				</c:when>
				<c:otherwise>
					<liferay-portlet:renderURL plid="<%= lcsClusterEntryLayout.getPlid() %>" portletName="<%= OSBLCSPortletKeys.ENVIRONMENT %>" var="lcsClusterEntryURL">
						<portlet:param name="layoutLCSClusterEntryId" value="<%= String.valueOf(layoutLCSClusterEntryId) %>" />
						<portlet:param name="layoutLCSClusterNodeId" value="0" />
						<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
					</liferay-portlet:renderURL>

					<%
					LCSClusterEntry lcsClusterEntry = LCSClusterEntryServiceUtil.getLCSClusterEntry(layoutLCSClusterEntryId);
					List<LCSClusterNode> lcsClusterNodes = LCSClusterNodeServiceUtil.getLCSClusterEntryLCSClusterNodes(layoutLCSClusterEntryId, true);
					%>

					<div class="environment-titlebar titlebar">
						<div class="titlebar-inner <%= (layoutLCSClusterNodeId == 0) ? "selected" : StringPool.BLANK %>">
							<aui:a cssClass="title" href="<%= lcsClusterEntryURL %>" title="<%= HtmlUtil.escape(lcsClusterEntry.getName()) %>">
								<span class="title-label"><%= HtmlUtil.escape(lcsClusterEntry.getName()) %></span>

								<span class="environment-icon lcs-content-type-icon <%= lcsClusterEntry.isEnvironment() ? "group" : "cluster" %> <%= NavigationAdvisor.getLCSClusterEntryHealthStatusCSSClass(lcsClusterEntry.hasOfflineLCSClusterNode(), lcsClusterNodes.size()) %>" title="<%= LanguageUtil.get(request, NavigationAdvisor.getLCSClusterEntryHealthStatusTitle(lcsClusterEntry.isCluster(), lcsClusterEntry.hasOfflineLCSClusterNode())) %>"></span>
							</aui:a>
						</div>
					</div>

					<div class="menu-items">

						<%
						Map<Long, Boolean> lcsClusterNodeOnlineStatuses = NavigationAdvisor.getLCSClusterNodeOfflineStatuses(layoutLCSClusterEntryId);

						for (LCSClusterNode lcsClusterNode : lcsClusterNodes) {
							long lcsClusterNodeId = lcsClusterNode.getLcsClusterNodeId();
						%>

							<div class="menu-item <%= (lcsClusterNode.getLcsClusterNodeId() == layoutLCSClusterNodeId) ? "selected" : StringPool.BLANK %>">
								<liferay-portlet:renderURL plid="<%= lcsClusterNodeLayout.getPlid() %>" portletName="<%= OSBLCSPortletKeys.SERVER %>" var="lcsClusterNodeURL">
									<portlet:param name="layoutLCSClusterEntryId" value="<%= String.valueOf(lcsClusterNode.getLcsClusterEntryId()) %>" />
									<portlet:param name="layoutLCSClusterNodeId" value="<%= String.valueOf(lcsClusterNodeId) %>" />
									<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
								</liferay-portlet:renderURL>

								<aui:a cssClass="server" href="<%= lcsClusterNodeURL %>" label="<%= HtmlUtil.escape(lcsClusterNode.getName()) %>" title="<%= HtmlUtil.escape(lcsClusterNode.getName()) %>">
									<span class="lcs-content-type-icon server-icon <%= NavigationAdvisor.getLCSClusterNodeHealthStatusCSSClass(lcsClusterNodeOnlineStatuses.get(lcsClusterNodeId)) %>" title="<%= lcsClusterNode.isOffline() ? LanguageUtil.get(request, "the-server-is-offline") : LanguageUtil.get(request, "the-server-is-online") %>"></span>
								</aui:a>

								<liferay-portlet:renderURL plid="<%= lcsClusterNodeLayout.getPlid() %>" portletName="<%= OSBLCSPortletKeys.SERVER %>" var="lcsClusterNodeSettingsURL">
									<portlet:param name="serverPage" value="settings" />
									<portlet:param name="layoutLCSClusterEntryId" value="<%= String.valueOf(lcsClusterNode.getLcsClusterEntryId()) %>" />
									<portlet:param name="layoutLCSClusterNodeId" value="<%= String.valueOf(lcsClusterNodeId) %>" />
									<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
								</liferay-portlet:renderURL>

								<aui:a cssClass="icon-cog settings" href="<%= lcsClusterNodeSettingsURL %>" title="server-settings"></aui:a>
							</div>

						<%
						}
						%>

					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</c:otherwise>
</c:choose>

<aui:script use="aui-tooltip">
	new A.TooltipDelegate(
		{
			position: 'right',
			trigger: '.lcs-content-type-icon.offline, .lcs-content-type-icon.online, .settings'
		}
	);

	var lcsObjectNameLinks = A.all('.lcs-navigation-submenu .environment, .lcs-navigation-submenu .server');

	lcsObjectNameLinks.each(
		function(node) {
			var offsetWidth = node.get('offsetWidth');
			var scrollWidth = node.get('scrollWidth');

			if (offsetWidth < scrollWidth) {
				new A.Tooltip(
					{
						title: true,
						trigger: node,
						visible: false
					}
				).render();
			}
			else {
				node.attr('title', '');
			}
		}
	);
</aui:script>