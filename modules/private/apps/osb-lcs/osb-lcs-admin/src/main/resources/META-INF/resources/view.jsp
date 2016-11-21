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
String tabs1 = ParamUtil.getString(request, "tabs1");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/view.jsp");
portletURL.setParameter("tabs1", tabs1);

pageContext.setAttribute("portletURL", portletURL);
%>

<liferay-ui:tabs
	names="infrastructure,lcs-portal,metadata,nodes,notification-audits,uptimes,patches,scripts,properties"
	param="tabs1"
	url="<%= portletURL.toString() %>"
/>

<c:choose>
	<c:when test='<%= tabs1.equals("lcs-portal") %>'>
		<%@ include file="/lcs_portal.jspf" %>
	</c:when>
	<c:when test='<%= tabs1.equals("metadata") %>'>
		<%@ include file="/metadata.jspf" %>
	</c:when>
	<c:when test='<%= tabs1.equals("nodes") %>'>
		<%@ include file="/lcs_cluster_nodes.jspf" %>
	</c:when>
	<c:when test='<%= tabs1.equals("notification-audits") %>'>
		<%@ include file="/notification_audits.jspf" %>
	</c:when>
	<c:when test='<%= tabs1.equals("patches") %>'>
		<%@ include file="/patches.jspf" %>
	</c:when>
	<c:when test='<%= tabs1.equals("properties") %>'>
		<%@ include file="/properties.jspf" %>
	</c:when>
	<c:when test='<%= tabs1.equals("scripts") %>'>
		<%@ include file="/scripts.jspf" %>
	</c:when>
	<c:when test='<%= tabs1.equals("uptimes") %>'>
		<%@ include file="/uptimes.jspf" %>
	</c:when>
	<c:otherwise>
		<%@ include file="/infrastructure.jspf" %>
	</c:otherwise>
</c:choose>