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
long lcsClusterEntryId = ParamUtil.getLong(request, "lcsClusterEntryId");
long lcsProjectId = ParamUtil.getLong(request, "lcsProjectId");
String patchName = ParamUtil.getString(request, "patchName");

String patchId = PatchUtil.getPatchId(patchName);
%>

<liferay-portlet:renderURL varImpl="iteratorURL" />

<liferay-ui:search-container
	emptyResultsMessage="all-servers-are-up-and-configured-properly"
	iteratorURL="<%= iteratorURL %>"
>
	<liferay-ui:search-container-results>

		<%
		List<LCSClusterNode> lcsClusterNodes = new ArrayList<LCSClusterNode>();

		for (LCSClusterNode lcsClusterNode : LCSClusterNodeServiceUtil.getLCSClusterEntryLCSClusterNodes(lcsClusterEntryId, true)) {
			Map<String, Integer> patchIdsStatuses = lcsClusterNode.getPatchIdsStatuses();

			if (patchIdsStatuses.containsKey(patchId) && (patchIdsStatuses.get(patchId) == LCSConstants.PATCHES_INSTALLED)) {
				continue;
			}

			lcsClusterNodes.add(lcsClusterNode);
		}

		results = ListUtil.subList(lcsClusterNodes, searchContainer.getStart(), searchContainer.getEnd());
		total = lcsClusterNodes.size();

		pageContext.setAttribute("results", results);
		pageContext.setAttribute("total", total);
		%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.liferay.osb.lcs.model.LCSClusterNode"
		escapedModel="<%= true %>"
		keyProperty="lcsClusterNodeId"
		modelVar="lcsClusterNode"
	>
		<liferay-ui:search-container-column-text name="name">
			<aui:a cssClass="fix-packs-name" href="<%= NotificationsUtil.getPatchReleaseNotesURL(patchId) %>" label="<%= HtmlUtil.escape(PatchUtil.getPatchHumanName(patchName)) %>" target="_blank" />
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text name="status">

			<%
			Map<String, Integer> patchIdsStatuses = lcsClusterNode.getPatchIdsStatuses();

			int patchStatus = LCSConstants.PATCHES_AVAILABLE;

			if (patchIdsStatuses.containsKey(patchId)) {
				patchStatus = GetterUtil.getInteger(patchIdsStatuses.get(patchId));
			}
			%>

			<%= LanguageUtil.get(request, LCSConstants.toPatchStatusLabel(patchStatus)) %>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text name="server">

			<%
			Layout lcsClusterNodeLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), true, NavigationConstants.FRIENDLY_URL_LCS_CLUSTER_NODE);
			%>

			<liferay-portlet:renderURL plid="<%= lcsClusterNodeLayout.getPlid() %>" var="lcsClusterNodeURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
				<portlet:param name="layoutLCSClusterEntryId" value="<%= String.valueOf(lcsClusterEntryId) %>" />
				<portlet:param name="layoutLCSClusterNodeId" value="<%= String.valueOf(lcsClusterNode.getLcsClusterNodeId()) %>" />
				<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(lcsProjectId) %>" />
			</liferay-portlet:renderURL>

			<aui:a href="<%= lcsClusterNodeURL %>" label="<%= HtmlUtil.escape(lcsClusterNode.getName()) %>" />
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text property="location" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator paginate="<%= false %>" />
</liferay-ui:search-container>