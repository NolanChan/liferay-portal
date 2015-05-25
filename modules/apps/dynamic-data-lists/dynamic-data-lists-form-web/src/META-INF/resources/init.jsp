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

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.dynamic.data.lists.form.web.context.DDLFormDisplayContext" %><%@
page import="com.liferay.dynamic.data.mapping.form.renderer.DDMFormRenderingException" %><%@
page import="com.liferay.portal.kernel.dao.search.SearchContainer" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.StringBundler" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowConstants" %><%@
page import="com.liferay.portal.security.auth.PrincipalException" %><%@
page import="com.liferay.portal.util.WebKeys" %><%@
page import="com.liferay.portlet.PortletURLUtil" %><%@
page import="com.liferay.portlet.dynamicdatalists.NoSuchRecordSetException" %><%@
page import="com.liferay.portlet.dynamicdatalists.model.DDLRecordSet" %><%@
page import="com.liferay.portlet.dynamicdatalists.model.DDLRecordSetConstants" %><%@
page import="com.liferay.portlet.dynamicdatalists.service.DDLRecordSetServiceUtil" %><%@
page import="com.liferay.portlet.dynamicdatamapping.NoSuchStructureException" %><%@
page import="com.liferay.portlet.dynamicdatamapping.NoSuchStructureLayoutException" %>

<%@ page import="javax.portlet.PortletURL" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
PortletURL currentURLObj = PortletURLUtil.getCurrent(liferayPortletRequest, liferayPortletResponse);

String currentURL = currentURLObj.toString();

DDLFormDisplayContext ddlFormDisplayContext = new DDLFormDisplayContext(request, portletPreferences);
%>

<%@ include file="/init-ext.jsp" %>