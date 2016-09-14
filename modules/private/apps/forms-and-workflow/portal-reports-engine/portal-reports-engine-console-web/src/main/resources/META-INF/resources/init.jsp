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
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.portal.kernel.bean.BeanParamUtil" %><%@
page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %><%@
page import="com.liferay.portal.kernel.dao.search.DisplayTerms" %><%@
page import="com.liferay.portal.kernel.dao.search.SearchContainer" %><%@
page import="com.liferay.portal.kernel.json.JSONArray" %><%@
page import="com.liferay.portal.kernel.json.JSONFactoryUtil" %><%@
page import="com.liferay.portal.kernel.json.JSONObject" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %><%@
page import="com.liferay.portal.kernel.model.User" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.security.permission.ActionKeys" %><%@
page import="com.liferay.portal.kernel.service.UserLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.ListUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.StringBundler" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.reports.admin.util.AdminUtil" %><%@
page import="com.liferay.portal.reports.configuration.ReportsGroupServiceEmailConfiguration" %><%@
page import="com.liferay.portal.reports.constants.ReportsPortletKeys" %><%@
page import="com.liferay.portal.reports.engine.ReportDataSourceType" %><%@
page import="com.liferay.portal.reports.engine.ReportFormat" %><%@
page import="com.liferay.portal.reports.exception.DefinitionFileException" %><%@
page import="com.liferay.portal.reports.exception.DefinitionNameException" %><%@
page import="com.liferay.portal.reports.exception.EntryEmailDeliveryException" %><%@
page import="com.liferay.portal.reports.exception.EntryEmailNotificationsException" %><%@
page import="com.liferay.portal.reports.exception.SourceDriverClassNameException" %><%@
page import="com.liferay.portal.reports.exception.SourceJDBCConnectionException" %><%@
page import="com.liferay.portal.reports.exception.SourceTypeException" %><%@
page import="com.liferay.portal.reports.model.Definition" %><%@
page import="com.liferay.portal.reports.model.Entry" %><%@
page import="com.liferay.portal.reports.model.Source" %><%@
page import="com.liferay.portal.reports.service.DefinitionLocalServiceUtil" %><%@
page import="com.liferay.portal.reports.service.DefinitionServiceUtil" %><%@
page import="com.liferay.portal.reports.service.EntryLocalServiceUtil" %><%@
page import="com.liferay.portal.reports.service.EntryServiceUtil" %><%@
page import="com.liferay.portal.reports.service.SourceLocalServiceUtil" %><%@
page import="com.liferay.portal.reports.service.SourceServiceUtil" %><%@
page import="com.liferay.portal.reports.service.permission.AdminResourcePermissionChecker" %><%@
page import="com.liferay.portal.reports.service.permission.DefinitionPermissionChecker" %><%@
page import="com.liferay.portal.reports.service.permission.EntryPermissionChecker" %><%@
page import="com.liferay.portal.reports.service.permission.ReportsActionKeys" %><%@
page import="com.liferay.portal.reports.service.permission.SourcePermissionChecker" %><%@
page import="com.liferay.portal.reports.status.ReportStatus" %><%@
page import="com.liferay.portal.reports.util.ReportsUtil" %><%@
page import="com.liferay.portal.reports.web.admin.util.ReportWebRequestHelper" %><%@
page import="com.liferay.taglib.search.ResultRow" %>

<%@ page import="java.text.Format" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.Arrays" %><%@
page import="java.util.Calendar" %><%@
page import="java.util.Date" %><%@
page import="java.util.List" %><%@
page import="java.util.Map" %>

<%@ page import="javax.portlet.PortletURL" %><%@
page import="javax.portlet.WindowState" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
Format dateFormatDate = FastDateFormatFactoryUtil.getDate(locale, timeZone);
Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(locale, timeZone);

ReportsGroupServiceEmailConfiguration reportsGroupServiceEmailConfiguration =
	ReportWebRequestHelper.getReportsGroupServiceEmailConfiguration(request);
%>