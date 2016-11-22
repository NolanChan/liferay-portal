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
SearchContainer searchContainer = (SearchContainer)request.getAttribute("liferay-ui:search:searchContainer");

LCSClusterNodeDisplayTerms displayTerms = (LCSClusterNodeDisplayTerms)searchContainer.getDisplayTerms();
%>

<liferay-ui:search-toggle
	buttonLabel="search"
	displayTerms="<%= displayTerms %>"
	id="toggle_id_lcs_cluster_node_search"
>
	<aui:fieldset>
		<aui:input name="<%= displayTerms.LCS_PROJECT_NAME %>" size="20" value="<%= displayTerms.getLCSProjectName() %>" />

		<aui:input name="<%= displayTerms.LCS_CLUSTER_ENTRY_NAME %>" size="20" value="<%= displayTerms.getLCSClusterEntryName() %>" />

		<aui:select name="<%= displayTerms.LCS_CLUSTER_NODE_STATUS %>">
			<aui:option label="select-status" value="" />

			<%
			for (LCSClusterNodeStatus lcsClusterNodeStatus : LCSClusterNodeStatus.values()) {
			%>

				<aui:option label="<%= lcsClusterNodeStatus.getLabel() %>" value="<%= lcsClusterNodeStatus.name() %>" />

			<%
			}
			%>

		</aui:select>
	</aui:fieldset>
</liferay-ui:search-toggle>