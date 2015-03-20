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

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@ page import="com.liferay.portal.theme.ThemeDisplay" %>

<%@ page import="javax.portlet.WindowState" %>

<%
ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
	WebKeys.THEME_DISPLAY);
%>

<portlet:renderURL var="openIdURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="struts_action" value="/login/open_id" />
</portlet:renderURL>

<liferay-ui:icon
	message="open-id"
	src='<%= themeDisplay.getPathThemeImages() + "/common/openid.gif" %>'
	url="<%= openIdURL %>"
/>