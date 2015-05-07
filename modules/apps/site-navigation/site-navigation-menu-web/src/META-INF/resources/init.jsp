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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.configuration.Filter" %><%@
page import="com.liferay.portal.kernel.util.ArrayUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.site.navigation.menu.web.configuration.NavigationMenuWebConfiguration" %><%@
page import="com.liferay.site.navigation.menu.web.configuration.NavigationMenuWebConfigurationUtil" %><%@
page import="com.liferay.site.navigation.menu.web.display.context.NavigationMenuDisplayContext" %>

<liferay-theme:defineObjects />
<portlet:defineObjects />

<%
NavigationMenuWebConfiguration navigationMenuWebConfiguration = (NavigationMenuWebConfiguration)renderRequest.getAttribute(NavigationMenuWebConfiguration.class.getName());

NavigationMenuDisplayContext navigationMenuDisplayContext = new NavigationMenuDisplayContext(request, navigationMenuWebConfiguration);

String portletResource = ParamUtil.getString(request, "portletResource");
%>

<%@ include file="/init-ext.jsp" %>