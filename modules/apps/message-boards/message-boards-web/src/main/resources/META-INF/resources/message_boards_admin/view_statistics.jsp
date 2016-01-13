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

<%@ include file="/message_boards/init.jsp" %>

<%
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "/message_boards/view_banned_users");
%>

<aui:nav-bar markupView="lexicon">
	<aui:nav cssClass="navbar-nav">

		<%
		PortletURL navigationURL = renderResponse.createRenderURL();

		navigationURL.setParameter("mvcRenderCommandName", "/message_boards/view");
		navigationURL.setParameter("tag", StringPool.BLANK);
		%>

		<aui:nav-item
			href="<%= navigationURL.toString() %>"
			label="message-boards-home"
			selected="<%= false %>"
		/>

		<%
		PortletURL viewStatisticsURL = renderResponse.createRenderURL();

		viewStatisticsURL.setParameter("mvcRenderCommandName", "/message_boards/view_statistics");
		%>

		<aui:nav-item
			href="<%= viewStatisticsURL.toString() %>"
			label="statistics"
			selected="<%= true %>"
		/>

		<%
		PortletURL bannedUsersURL = renderResponse.createRenderURL();

		bannedUsersURL.setParameter("mvcRenderCommandName", "/message_boards/view_banned_users");
		%>

		<aui:nav-item
			href="<%= bannedUsersURL.toString() %>"
			label="banned-users"
			selected="<%= false %>"
		/>
	</aui:nav>
</aui:nav-bar>

<%
long categoryId = GetterUtil.getLong(request.getAttribute("view.jsp-categoryId"));

MBCategoryDisplay categoryDisplay = new MBCategoryDisplayImpl(scopeGroupId, categoryId);
%>

<div class="container-fluid-1280">
	<liferay-ui:panel-container cssClass="statistics-panel" extended="<%= false %>" id="messageBoardsStatisticsPanelContainer" persistState="<%= true %>">
		<liferay-ui:panel collapsible="<%= true %>" cssClass="statistics-panel-content" extended="<%= true %>" id="messageBoardsGeneralStatisticsPanel" persistState="<%= true %>" title="general">
			<dl>
				<dt>
					<liferay-ui:message key="num-of-categories" />:
				</dt>
				<dd>
					<%= numberFormat.format(categoryDisplay.getAllCategoriesCount()) %>
				</dd>
				<dt>
					<liferay-ui:message key="num-of-posts" />:
				</dt>
				<dd>
					<%= numberFormat.format(MBStatsUserLocalServiceUtil.getMessageCountByGroupId(scopeGroupId)) %>
				</dd>
				<dt>
					<liferay-ui:message key="num-of-participants" />:
				</dt>
				<dd>
					<%= numberFormat.format(MBStatsUserLocalServiceUtil.getStatsUsersByGroupIdCount(scopeGroupId)) %>
				</dd>
			</dl>
		</liferay-ui:panel>

		<liferay-ui:panel collapsible="<%= true %>" cssClass="statistics-panel-content" extended="<%= true %>" id="messageBoardsTopPostersPanel" persistState="<%= true %>" title="top-posters">
			<liferay-ui:search-container
				emptyResultsMessage="there-are-no-top-posters"
				iteratorURL="<%= portletURL %>"
				total="<%= MBStatsUserLocalServiceUtil.getStatsUsersByGroupIdCount(scopeGroupId) %>"
			>
				<liferay-ui:search-container-results
					results="<%= MBStatsUserLocalServiceUtil.getStatsUsersByGroupId(scopeGroupId, searchContainer.getStart(), searchContainer.getEnd()) %>"
				/>

				<liferay-ui:search-container-row
					className="com.liferay.portlet.messageboards.model.MBStatsUser"
					keyProperty="statsUserId"
					modelVar="statsUser"
				>
					<liferay-ui:search-container-column-jsp
						path="/message_boards/top_posters_user_display.jsp"
					/>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator displayStyle="descriptive" markupView="lexicon" />
			</liferay-ui:search-container>
		</liferay-ui:panel>
	</liferay-ui:panel-container>
</div>

<%
PortalUtil.setPageSubtitle(LanguageUtil.get(request, "statistics"), request);
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, TextFormatter.format("statistics", TextFormatter.O)), portletURL.toString());
%>