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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

DDMTemplate template = (DDMTemplate)row.getObject();

String rowHREF = (String)row.getParameter("rowHREF");
%>

<a href="<%= rowHREF %>">

<c:choose>
	<c:when test="<%= template.isSmallImage() %>">
		<img alt="" src="<%= HtmlUtil.escapeAttribute(template.getTemplateImageURL(themeDisplay)) %>" />
	</c:when>
	<c:otherwise>
		<%= HtmlUtil.escape(template.getDescription(locale)) %>
	</c:otherwise>
</c:choose>

</a>