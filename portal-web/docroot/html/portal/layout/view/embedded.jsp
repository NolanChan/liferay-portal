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

<%@ include file="/html/portal/init.jsp" %>

<%@ include file="/html/portal/layout/view/embedded_js.jspf" %>

<div id="iframe">

	<%
	UnicodeProperties typeSettingsProperties = layout.getTypeSettingsProperties();
	%>

	<iframe frameborder="0" id="embeddedIframe" src="<%= HtmlUtil.escapeHREF(typeSettingsProperties.getProperty("embeddedLayoutURL")) %>" width="100%"></iframe>
</div>

<liferay-ui:layout-common />