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

<c:if test="<%= LayoutPermissionUtil.contains(permissionChecker, layout, ActionKeys.VIEW) %>">
	<portlet:actionURL var="discussionURL">
		<portlet:param name="struts_action" value="/page_comments/edit_page_discussion" />
	</portlet:actionURL>

	<portlet:resourceURL var="discussionPaginationURL">
		<portlet:param name="struts_action" value="/page_comments/edit_page_discussion" />
	</portlet:resourceURL>

	<liferay-ui:discussion
		className="<%= Layout.class.getName() %>"
		classPK="<%= layout.getPlid() %>"
		formAction="<%= discussionURL %>"
		formName="fm"
		paginationURL="<%= discussionPaginationURL %>"
		redirect="<%= currentURL %>"
		userId="<%= user.getUserId() %>"
	/>
</c:if>