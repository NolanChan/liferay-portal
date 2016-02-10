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
Layout selLayout = layoutsAdminDisplayContext.getSelLayout();

String cssText = null;

if ((selLayout != null) && !selLayout.isInheritLookAndFeel()) {
	cssText = selLayout.getCssText();
}
else {
	LayoutSet selLayoutSet = layoutsAdminDisplayContext.getSelLayoutSet();

	cssText = selLayoutSet.getCss();
}
%>

<liferay-ui:error-marker key="<%= WebKeys.ERROR_SECTION %>" value="css" />

<aui:input label="insert-custom-css-that-is-loaded-after-the-theme" name="regularCss" placeholder="css" type="textarea" value="<%= cssText %>" />