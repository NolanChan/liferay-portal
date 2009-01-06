<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ include file="/html/taglib/init.jsp" %>

<%
Portlet portlet = (Portlet)request.getAttribute("liferay-portlet:icon_portlet:portlet");

boolean showPortletIcon = true;
String message = null;
String src = null;

if (portlet != null) {
	message = PortalUtil.getPortletTitle(portlet, application, locale);
	src = portlet.getContextPath() + portlet.getIcon();
}
else {
	showPortletIcon = portletDisplay.isShowPortletIcon();
	message = portletDisplay.getTitle();
	src = portletDisplay.getURLPortlet();
}
%>

<c:if test="<%= showPortletIcon %>">
	<liferay-ui:icon message="<%= message %>" src="<%= src %>" />
</c:if>