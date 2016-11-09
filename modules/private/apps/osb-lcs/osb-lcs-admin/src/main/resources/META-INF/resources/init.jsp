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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>



<%@ page import="com.liferay.lcs.activation.LCSClusterEntryTokenContentAdvisor" %><%@
page import="com.liferay.lcs.notification.LCSEventType" %><%@
page import="com.liferay.lcs.security.KeyStoreFactory" %><%@
page import="com.liferay.lcs.subscription.SubscriptionType" %><%@
page import="com.liferay.lcs.util.LCSClusterNodeStatus" %><%@
page import="com.liferay.lcs.util.LCSConstants" %><%@
page import="com.liferay.lcs.util.PatchUtil" %><%@
page import="com.liferay.osb.lcs.DuplicateLCSClusterEntryNameException" %><%@
page import="com.liferay.osb.lcs.RequiredLCSClusterEntryNameException" %><%@
page import="com.liferay.osb.lcs.account.util.AccountUtil" %><%@
page import="com.liferay.osb.lcs.admin.search.LCSClusterNodeDisplayTerms" %><%@
page import="com.liferay.osb.lcs.admin.search.LCSClusterNodeSearch" %><%@
page import="com.liferay.osb.lcs.admin.search.LCSClusterNodeSearchTerms" %><%@
page import="com.liferay.osb.lcs.admin.util.AdminUtil" %><%@
page import="com.liferay.osb.lcs.dashboard.util.DashboardUtil" %><%@
page import="com.liferay.osb.lcs.environment.util.EnvironmentUtil" %><%@
page import="com.liferay.osb.lcs.members.util.LCSMembersUtil" %><%@
page import="com.liferay.osb.lcs.model.LCSClusterEntry" %><%@
page import="com.liferay.osb.lcs.model.LCSClusterEntryToken" %><%@
page import="com.liferay.osb.lcs.model.LCSClusterNode" %><%@
page import="com.liferay.osb.lcs.model.LCSClusterNodeUptime" %><%@
page import="com.liferay.osb.lcs.model.LCSInvitation" %><%@
page import="com.liferay.osb.lcs.model.LCSMessage" %><%@
page import="com.liferay.osb.lcs.model.LCSMetadata" %><%@
page import="com.liferay.osb.lcs.model.LCSNotification" %><%@
page import="com.liferay.osb.lcs.model.LCSNotificationAuditEntry" %><%@
page import="com.liferay.osb.lcs.model.LCSProject" %><%@
page import="com.liferay.osb.lcs.model.LCSRole" %><%@
page import="com.liferay.osb.lcs.model.LCSRoleConstants" %><%@
page import="com.liferay.osb.lcs.model.LCSSubscriptionEntry" %><%@
page import="com.liferay.osb.lcs.model.UserLCSMessage" %><%@
page import="com.liferay.osb.lcs.navigation.util.NavigationUtil" %><%@
page import="com.liferay.osb.lcs.nosql.model.LCSClusterNodeInstallationEnvironment" %><%@
page import="com.liferay.osb.lcs.nosql.model.LCSClusterNodeLiferayVMMetrics" %><%@
page import="com.liferay.osb.lcs.nosql.model.LCSClusterNodeScript" %><%@
page import="com.liferay.osb.lcs.nosql.service.LCSClusterNodeInstallationEnvironmentServiceUtil" %><%@
page import="com.liferay.osb.lcs.nosql.service.LCSClusterNodeLayoutMetricsServiceUtil" %><%@
page import="com.liferay.osb.lcs.nosql.service.LCSClusterNodePortletMetricsServiceUtil" %><%@
page import="com.liferay.osb.lcs.nosql.service.LCSClusterNodeScriptServiceUtil" %><%@
page import="com.liferay.osb.lcs.notifications.util.NotificationsUtil" %><%@
page import="com.liferay.osb.lcs.server.util.ServerUtil" %><%@
page import="com.liferay.osb.lcs.service.LCSClusterEntryLocalServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.LCSClusterEntryServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.LCSClusterEntryTokenServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.LCSClusterNodeLocalServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.LCSClusterNodeServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.LCSClusterNodeUptimeLocalServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.LCSInvitationServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.LCSMessageServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.LCSMetadataLocalServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.LCSNotificationAuditEntryLocalServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.LCSNotificationServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.LCSPatchEntryLocalServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.LCSProjectLocalServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.LCSProjectServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.LCSRoleLocalServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.LCSSubscriptionEntryLocalServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.UserLCSMessageServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.permission.LCSClusterEntryPermission" %><%@
page import="com.liferay.osb.lcs.service.permission.LCSProjectPermission" %><%@
page import="com.liferay.osb.lcs.subscriptions.util.SubscriptionsUtil" %><%@
page import="com.liferay.osb.lcs.util.ActionKeys" %><%@
page import="com.liferay.osb.lcs.util.ApplicationProfile" %><%@
page import="com.liferay.osb.lcs.util.LCSClusterNodeUtil" %><%@
page import="com.liferay.osb.lcs.util.LCSMetadataAvailabilityIndex" %><%@
page import="com.liferay.osb.lcs.util.PortletKeys" %><%@
page import="com.liferay.osb.lcs.util.PortletPropsValues" %><%@
page import="com.liferay.osb.lcs.util.WebKeys" %><%@
page import="com.liferay.portal.RequiredFieldException" %><%@
page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.dao.search.SearchContainer" %><%@
page import="com.liferay.portal.kernel.json.JSONArray" %><%@
page import="com.liferay.portal.kernel.json.JSONObject" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.util.ArrayUtil" %><%@
page import="com.liferay.portal.kernel.util.CalendarUtil" %><%@
page import="com.liferay.portal.kernel.util.DateUtil" %><%@
page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.HttpUtil" %><%@
page import="com.liferay.portal.kernel.util.ListUtil" %><%@
page import="com.liferay.portal.kernel.util.LocaleUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PropsUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.UnicodeFormatter" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.model.CompanyConstants" %><%@
page import="com.liferay.portal.model.Layout" %><%@
page import="com.liferay.portal.model.ServiceComponent" %><%@
page import="com.liferay.portal.model.User" %><%@
page import="com.liferay.portal.security.auth.AuthTokenUtil" %><%@
page import="com.liferay.portal.service.ClassNameLocalServiceUtil" %><%@
page import="com.liferay.portal.service.LayoutLocalServiceUtil" %><%@
page import="com.liferay.portal.service.ServiceComponentLocalServiceUtil" %><%@
page import="com.liferay.portal.service.UserLocalServiceUtil" %><%@
page import="com.liferay.portal.util.PortalUtil" %><%@
page import="com.liferay.portlet.PortletURLUtil" %><%@
page import="com.liferay.portlet.expando.model.ExpandoBridge" %>

<%@ page import="java.text.DateFormat" %><%@
page import="java.text.DecimalFormat" %><%@
page import="java.text.Format" %><%@
page import="java.text.NumberFormat" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.Calendar" %><%@
page import="java.util.Date" %><%@
page import="java.util.HashMap" %><%@
page import="java.util.List" %><%@
page import="java.util.Locale" %><%@
page import="java.util.Map" %>

<%@ page import="javax.portlet.PortletURL" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
PortletURL currentURLObj = PortletURLUtil.getCurrent(renderRequest, renderResponse);

String currentURL = currentURLObj.toString();

DecimalFormat decimalFormat = new DecimalFormat("#.0");
Format longDateFormatTime = FastDateFormatFactoryUtil.getTime(DateFormat.LONG, locale, timeZone);
Format mediumDateFormatDate = FastDateFormatFactoryUtil.getDate(DateFormat.MEDIUM, locale, timeZone);
Format mediumDateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(DateFormat.MEDIUM, DateFormat.MEDIUM, locale, timeZone);

NumberFormat numberFormat = NumberFormat.getInstance();

numberFormat.setMaximumFractionDigits(2);
numberFormat.setMinimumFractionDigits(2);
%>