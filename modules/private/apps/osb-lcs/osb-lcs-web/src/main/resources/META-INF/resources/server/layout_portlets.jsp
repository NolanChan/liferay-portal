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
String key = ParamUtil.getString(request, "key");
String layoutName = ParamUtil.getString(request, "layoutName");
%>

<liferay-portlet:renderURL varImpl="iteratorURL" />

<liferay-ui:search-container
	emptyResultsMessage="there-are-no-metrics"
	id="layoutPortletMetrics"
	iteratorURL="<%= iteratorURL %>"
>
	<liferay-ui:search-container-results>

		<%
		results = LCSClusterNodePortletMetricsServiceUtil.getLCSClusterNodePortletMetricsList(CompanyConstants.SYSTEM, QueryUtil.ALL_POS, key, layoutName, "frequency", "desc", "RENDER", QueryUtil.ALL_POS);

		pageContext.setAttribute("results", results);
		%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.liferay.osb.lcs.nosql.model.LCSClusterNodePortletMetrics"
		escapedModel="<%= true %>"
		keyProperty="key"
		modelVar="lcsClusterNodePortletMetrics"
	>
		<liferay-ui:search-container-column-text name="name" value="<%= HtmlUtil.escape(lcsClusterNodePortletMetrics.getDisplayName()) %>" />

		<liferay-ui:search-container-column-text property="frequency" />

		<liferay-ui:search-container-column-text name="average-load-time-ms" property="averageLoadTime" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator paginate="<%= false %>" />
</liferay-ui:search-container>