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
String statusOrderByCol = ParamUtil.getString(request, "statusOrderByCol");
String statusOrderByType = ParamUtil.getString(request, "statusOrderByType", "asc");

List<LCSClusterEntry> lcsClusterEntries = LCSClusterEntryServiceUtil.getLCSProjectLCSClusterEntries(layoutLCSProjectId);
List<LCSClusterNode> lcsClusterNodes = LCSClusterNodeUtil.getLCSClusterNodes(request, layoutLCSClusterEntryId, layoutLCSClusterNodeId, layoutLCSProjectId);
List<Object[]> statusObjectArrays = DashboardUtil.getStatusObjectArrays(pageContext, lcsClusterNodes, statusOrderByCol, statusOrderByType);
%>

<c:choose>
	<c:when test="<%= lcsClusterEntries.isEmpty() %>">
		<div class="alert alert-info">
			<liferay-ui:message key="there-are-no-environments-in-this-project" />
		</div>

		<div class="lcs-note">
			<liferay-ui:message key="to-register-a-server-with-liferay-connected-services-you-must-first-create-an-environment" />
		</div>

		<div class="lcs-note">
			<liferay-ui:message key="an-environment-is-a-cluster-or-a-virtual-aggregation-of-servers" />
		</div>
	</c:when>
	<c:when test="<%= lcsClusterNodes.isEmpty() %>">
		<div class="alert alert-info">
			<liferay-ui:message key="there-are-no-servers-in-this-project" />
		</div>

		<div class="lcs-note">
			<liferay-ui:message key="select-an-environment-to-register-a-server-in" />
		</div>
	</c:when>
	<c:otherwise>
		<div class="table-container">
			<h2>
				<liferay-ui:message key="status" />
			</h2>

			<div class="content">
				<c:choose>
					<c:when test="<%= statusObjectArrays.isEmpty() %>">
						<div class="alert alert-success">
							<liferay-ui:message key="all-servers-in-the-project-are-online-and-connected" />
						</div>
					</c:when>
					<c:otherwise>
						<liferay-portlet:renderURL varImpl="iteratorURL" />

						<liferay-ui:search-container
							curParam="cur2"
							deltaParam="delta2"
							iteratorURL="<%= iteratorURL %>"
							orderByCol="<%= statusOrderByCol %>"
							orderByColParam="statusOrderByCol"
							orderByType="<%= statusOrderByType %>"
							orderByTypeParam="statusOrderByType"
						>
							<liferay-ui:search-container-results>

								<%
								results = ListUtil.subList(statusObjectArrays, searchContainer.getStart(), searchContainer.getEnd());
								total = statusObjectArrays.size();

								pageContext.setAttribute("results", results);
								pageContext.setAttribute("total", total);
								%>

							</liferay-ui:search-container-results>

							<liferay-ui:search-container-row
								className="java.lang.Object[]"
								modelVar="statusObjectArray"
							>

								<%
								LCSClusterEntry lcsClusterEntry = (LCSClusterEntry)statusObjectArray[0];
								LCSClusterNode lcsClusterNode = (LCSClusterNode)statusObjectArray[1];
								String messageHTML = (String)statusObjectArray[2];
								%>

								<liferay-ui:search-container-column-text name="environment" orderable="<%= true %>" value="<%= HtmlUtil.escape(lcsClusterEntry.getName()) %>" />

								<liferay-ui:search-container-column-text name="server" orderable="<%= true %>">

									<%
									Layout lcsClusterNodeLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), true, NavigationUtil.FRIENDLY_URL_LCS_CLUSTER_NODE);
									%>

									<liferay-portlet:renderURL plid="<%= lcsClusterNodeLayout.getPlid() %>" portletName="<%= PortletKeys.SERVER %>" var="serverPageURL">
										<portlet:param name="layoutLCSClusterEntryId" value="<%= String.valueOf(lcsClusterNode.getLcsClusterEntryId()) %>" />
										<portlet:param name="layoutLCSClusterNodeId" value="<%= String.valueOf(lcsClusterNode.getLcsClusterNodeId()) %>" />
										<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(layoutLCSProjectId) %>" />
									</liferay-portlet:renderURL>

									<aui:a href="<%= serverPageURL %>" label="<%= HtmlUtil.escape(lcsClusterNode.getName()) %>" />
								</liferay-ui:search-container-column-text>

								<liferay-ui:search-container-column-text name="message" orderable="<%= true %>" value="<%= messageHTML %>" />
							</liferay-ui:search-container-row>

							<liferay-ui:search-iterator />
						</liferay-ui:search-container>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</c:otherwise>
</c:choose>