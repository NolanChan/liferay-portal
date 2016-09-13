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
String patchesOrderByCol = ParamUtil.getString(request, "patchesOrderByCol");
String patchesOrderByType = ParamUtil.getString(request, "patchesOrderByType", "asc");

List<LCSClusterNode> lcsClusterNodes = LCSClusterNodeUtil.getLCSClusterNodes(request, layoutLCSClusterEntryId, layoutLCSClusterNodeId, layoutLCSProjectId);

List<Object[]> downloadablePatchObjectArrays = NotificationsUtil.getDownloadablePatchObjectArrays(layoutLCSClusterEntryId, layoutLCSClusterNodeId, layoutLCSProjectId, lcsClusterNodes, patchesOrderByCol, patchesOrderByType);

Layout dashboardLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), true, NavigationUtil.FRIENDLY_URL_DASHBOARD);
Layout environmentLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), true, NavigationUtil.FRIENDLY_URL_LCS_CLUSTER_ENTRY);
Layout serverLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), true, NavigationUtil.FRIENDLY_URL_LCS_CLUSTER_NODE);
%>

<c:choose>
	<c:when test="<%= (plid != dashboardLayout.getPlid()) && Validator.equals(lcsClusterNodes.get(0).getPortalEdition(), LCSConstants.PORTAL_EDITION_CE) %>">
		<div class="alert alert-info">
			<liferay-ui:message key="the-fix-packs-update-service-is-unavailable-for-your-subscription-level" />

			<aui:a href="<%= HtmlUtil.escape(PortletPropsValues.LRDCOM_EE_PORTAL_OVERVIEW_URL) %>" label="learn-more" target="_blank" />
		</div>
	</c:when>
	<c:when test="<%= (plid != dashboardLayout.getPlid()) && !lcsClusterNodes.get(0).isPatchesLCSServiceEnabled() %>">
		<div class="alert alert-info">
			<liferay-portlet:renderURL plid="<%= environmentLayout.getPlid() %>" portletName="<%= PortletKeys.ENVIRONMENT %>" var="registrationURL">
				<portlet:param name="environmentPage" value="registration" />
				<portlet:param name="layoutLCSClusterEntryId" value="<%= String.valueOf(layoutLCSClusterEntryId) %>" />
				<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:message arguments="<%= registrationURL %>" key="this-service-is-disabled" />
		</div>
	</c:when>
	<c:otherwise>
		<div class="available-fix-packs-container fluid table-container">
			<h2 class="title">
				<liferay-ui:message key='<%= (plid == serverLayout.getPlid()) ? "available" : "fix-packs" %>' />

				<div class="hide updating"></div>
			</h2>

			<div class="content">
				<%@ include file="/notifications/available_patches.jspf" %>
			</div>
		</div>

		<c:if test="<%= plid == serverLayout.getPlid() %>">
			<div class="fluid installed-fix-packs-container table-container">
				<h2 class="title">
					<liferay-ui:message key="installed" />
				</h2>

				<div class="content">
					<liferay-ui:search-container
						emptyResultsMessage="there-are-no-installed-fix-packs"
					>
						<liferay-ui:search-container-results>

							<%
							List<String> patchesHumanNames = NotificationsUtil.getInstalledPatchesHumanNames(layoutLCSClusterNodeId);

							results = patchesHumanNames;
							total = patchesHumanNames.size();

							pageContext.setAttribute("results", results);
							pageContext.setAttribute("total", total);
							%>

						</liferay-ui:search-container-results>

						<liferay-ui:search-container-row
							className="java.lang.String"
							modelVar="patchHumanName"
						>
							<liferay-ui:search-container-column-text name="name" value="<%= HtmlUtil.escape(patchHumanName) %>" />
						</liferay-ui:search-container-row>

						<liferay-ui:search-iterator paginate="<%= false %>" />
					</liferay-ui:search-container>
				</div>
			</div>
		</c:if>

		<c:if test="<%= !downloadablePatchObjectArrays.isEmpty() %>">
			<div class="footer-note">
				<liferay-ui:message key="use-the-patching-tool-to-complete-installation-of-downloaded-fix-packs" />

				<liferay-ui:message arguments="<%= PortletPropsValues.LRDCOM_PATCHING_TOOL_OVERVIEW_URL %>" key="please-see-user-documentation-for-more-details" />
			</div>
		</c:if>

		<c:if test="<%= !downloadablePatchObjectArrays.isEmpty() %>">
			<liferay-portlet:resourceURL id="downloadPatchStatus" portletName="<%= PortletKeys.NOTIFICATIONS %>" var="downloadPatchStatusURL">
				<portlet:param name="p_auth" value="<%= AuthTokenUtil.getToken(request) %>" />
			</liferay-portlet:resourceURL>

			<liferay-portlet:resourceURL id="downloadPatch" portletName="<%= PortletKeys.NOTIFICATIONS %>" var="downloadPatchURL">
				<portlet:param name="p_auth" value="<%= AuthTokenUtil.getToken(request) %>" />
			</liferay-portlet:resourceURL>

			<liferay-portlet:renderURL portletName="<%= PortletKeys.NOTIFICATIONS %>" var="lcsClusterNodesURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
				<portlet:param name="mvcPath" value="/notifications/view_lcs_cluster_nodes.jsp" />
			</liferay-portlet:renderURL>

			<%
			LCSClusterEntry lcsClusterEntry = null;

			if (layoutLCSClusterEntryId > 0) {
				lcsClusterEntry = LCSClusterEntryServiceUtil.getLCSClusterEntry(layoutLCSClusterEntryId);
			}
			%>

			<aui:script use="lcs-notifications">
				var lcsNotifications = new Liferay.Portlet.LCSNotifications(
					{
						errorMessage: '<%= UnicodeLanguageUtil.get(pageContext, "your-request-failed-to-complete") %>',
						lcsConstants: {
							JSON_KEY_DATA: '<%= LCSConstants.JSON_KEY_DATA %>',
							JSON_KEY_MESSAGE: '<%= LCSConstants.JSON_KEY_MESSAGE %>',
							JSON_KEY_RESULT: '<%= LCSConstants.JSON_KEY_RESULT %>',
							JSON_VALUE_SUCCESS: '<%= LCSConstants.JSON_VALUE_SUCCESS %>'
						},
						namespace: '<%= PortalUtil.getPortletNamespace(PortletKeys.NOTIFICATIONS) %>'
					}
				);

				lcsNotifications.initializeViewNotificationsPage(
					{
						downloadPatchStatusAttributes: {
							'<%= LCSConstants.PATCHES_ERROR %>': {
								cssClass: 'lcs-download-status-<%= LCSConstants.toPatchStatusLabel(LCSConstants.PATCHES_ERROR) %>',
								label: '<%= UnicodeLanguageUtil.get(pageContext, LCSConstants.toPatchStatusLabel(LCSConstants.PATCHES_ERROR)) %>'
							},
							'<%= LCSConstants.PATCHES_DOWNLOAD_INITIATED %>': {
								cssClass: 'lcs-download-status-<%= LCSConstants.toPatchStatusLabel(LCSConstants.PATCHES_DOWNLOAD_INITIATED) %>',
								label: '<%= UnicodeLanguageUtil.get(pageContext, LCSConstants.toPatchStatusLabel(LCSConstants.PATCHES_DOWNLOAD_INITIATED)) %>'
							},
							'<%= LCSConstants.PATCHES_DOWNLOADED %>': {
								cssClass: 'lcs-download-status-<%= LCSConstants.toPatchStatusLabel(LCSConstants.PATCHES_DOWNLOADED) %>',
								label: '<%= UnicodeLanguageUtil.get(pageContext, LCSConstants.toPatchStatusLabel(LCSConstants.PATCHES_DOWNLOADED)) %>'
							},
							'<%= LCSConstants.PATCHES_DOWNLOADING %>': {
								cssClass: 'lcs-download-status-<%= LCSConstants.toPatchStatusLabel(LCSConstants.PATCHES_DOWNLOADING) %>',
								label: '<%= UnicodeLanguageUtil.get(pageContext, LCSConstants.toPatchStatusLabel(LCSConstants.PATCHES_DOWNLOADING)) %>'
							},
							'<%= LCSConstants.PATCHES_UNKNOWN %>': {
								cssClass: 'lcs-download-status-<%= LCSConstants.toPatchStatusLabel(LCSConstants.PATCHES_UNKNOWN) %>',
								label: '<%= UnicodeLanguageUtil.get(pageContext, LCSConstants.toPatchStatusLabel(LCSConstants.PATCHES_UNKNOWN)) %>'
							}
						},
						downloadPatchStatusCodes: {
							'DOWNLOAD_INITIATED': '<%= LCSConstants.PATCHES_DOWNLOAD_INITIATED %>',
							'DOWNLOADED': '<%= LCSConstants.PATCHES_DOWNLOADED %>',
							'DOWNLOADING': '<%= LCSConstants.PATCHES_DOWNLOADING %>',
							'ERROR': '<%= LCSConstants.PATCHES_ERROR %>',
							'UNKNOWN': '<%= LCSConstants.PATCHES_UNKNOWN %>'
						},
						downloadPatchStatusURL: '<%= downloadPatchStatusURL %>',
						downloadPatchURL: '<%= downloadPatchURL %>',
						msgCompletedDownload: '<%= UnicodeLanguageUtil.get(pageContext, "completed-download-for-x.-please-restart-the-server-help") %>',
						msgConfirmDownloadFixPack: '<%= ((lcsClusterEntry != null) && lcsClusterEntry.isCluster()) ? UnicodeLanguageUtil.get(pageContext, "you-are-about-to-download-this-fix-pack-to-all-nodes-in-the-cluster").concat("\\n\\n").concat(UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-continue")) : UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-download-this-fix-pack") %>',
						msgConfirmDownloadSelectedFixPacks: '<%= ((lcsClusterEntry != null) && lcsClusterEntry.isCluster()) ? UnicodeLanguageUtil.get(pageContext, "you-are-about-to-download-the-selected-fix-packs-to-all-nodes-in-the-cluster").concat("\\n\\n").concat(UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-continue")) : UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-download-the-selected-fix-packs") %>',
						msgNoAvailableFixPacks: '<%= UnicodeLanguageUtil.get(pageContext, "there-are-no-fix-packs-available-for-download") %>',
						showServersDialogTitle: '<%= UnicodeLanguageUtil.get(pageContext, "environment") %>: ',
						showServersDialogURL: '<%= lcsClusterNodesURL %>'
					}
				);
			</aui:script>
		</c:if>
	</c:otherwise>
</c:choose>