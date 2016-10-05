<%@ page import="com.liferay.osb.lcs.model.LCSSubscriptionEntry" %>
<%@ page import="com.liferay.osb.lcs.service.LCSSubscriptionEntryLocalServiceUtil" %>

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
long corpProjectId = ParamUtil.getLong(request, "corpProjectId");
%>

<liferay-portlet:renderURL varImpl="portletURL">
	<portlet:param name="mvcPath" value="/supportadmin/view.jsp" />
</liferay-portlet:renderURL>

<aui:form action="<%= portletURL %>" method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="portletURL" />

	<aui:fieldset>
		<aui:row>
			<aui:col>
				<aui:input inlineLabel="true" name="corpProjectId" />
			</aui:col>

			<aui:col>
				<aui:button type="submit" value="search" />
			</aui:col>
		</aui:row>
	</aui:fieldset>
</aui:form>

<liferay-ui:search-container
	emptyResultsMessage="there-are-no-lcs-subscription-entries"
>
	<liferay-ui:search-container-results>

		<%
		LCSProject lcsProject = LCSProjectLocalServiceUtil.fetchByCorpProject(corpProjectId);

		if (lcsProject != null) {
			List<LCSSubscriptionEntry> lcsSubscriptionEntries = LCSSubscriptionEntryLocalServiceUtil.getLCSProjectLCSSubscriptionEntries(lcsProject.getLcsProjectId());

			pageContext.setAttribute("results", lcsSubscriptionEntries);
			pageContext.setAttribute("total", lcsSubscriptionEntries.size());
		}
		%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.liferay.osb.lcs.model.LCSSubscriptionEntry"
		modelVar="lcsSubscriptionEntry"
	>
		<liferay-ui:search-container-column-text name="type" value="<%= HtmlUtil.escape(lcsSubscriptionEntry.getType()) %>" />

		<liferay-ui:search-container-column-text name="servers-used" value="<%= String.valueOf(lcsSubscriptionEntry.getServersUsed()) %>" />

		<liferay-ui:search-container-column-text name="servers-allowed" value="<%= String.valueOf(lcsSubscriptionEntry.getServersAllowed()) %>" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		paginate="false"
	/>
</liferay-ui:search-container>