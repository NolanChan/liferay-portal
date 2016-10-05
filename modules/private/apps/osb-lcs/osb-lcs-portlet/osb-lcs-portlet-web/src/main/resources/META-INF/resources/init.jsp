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
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="lcs" %>

<%@ page import="com.liferay.expando.kernel.model.ExpandoBridge" %><%@
page import="com.liferay.lcs.internal.activation.LCSClusterEntryTokenContentAdvisor" %><%@
page import="com.liferay.lcs.notification.LCSEventType" %><%@
page import="com.liferay.lcs.subscription.SubscriptionType" %><%@
page import="com.liferay.lcs.util.LCSClusterNodeStatus" %><%@
page import="com.liferay.lcs.util.LCSConstants" %><%@
page import="com.liferay.lcs.util.PatchUtil" %><%@
page import="com.liferay.osb.lcs.configuration.OSBLCSConfiguration" %><%@
page import="com.liferay.osb.lcs.constants.LCSRoleConstants" %><%@
page import="com.liferay.osb.lcs.constants.NavigationConstants" %><%@
page import="com.liferay.osb.lcs.constants.OSBLCSActionKeys" %><%@
page import="com.liferay.osb.lcs.constants.OSBLCSPortletKeys" %><%@
page import="com.liferay.osb.lcs.dashboard.util.DashboardUtil" %><%@
page import="com.liferay.osb.lcs.exception.DuplicateLCSClusterEntryNameException" %><%@
page import="com.liferay.osb.lcs.exception.RequiredLCSClusterEntryNameException" %><%@
page import="com.liferay.osb.lcs.members.util.LCSMembersUtil" %><%@
page import="com.liferay.osb.lcs.model.LCSClusterEntry" %><%@
page import="com.liferay.osb.lcs.model.LCSClusterEntryToken" %><%@
page import="com.liferay.osb.lcs.model.LCSClusterNode" %><%@
page import="com.liferay.osb.lcs.model.LCSInvitation" %><%@
page import="com.liferay.osb.lcs.model.LCSMessage" %><%@
page import="com.liferay.osb.lcs.model.LCSMetadata" %><%@
page import="com.liferay.osb.lcs.model.LCSNotification" %><%@
page import="com.liferay.osb.lcs.model.LCSProject" %><%@
page import="com.liferay.osb.lcs.model.LCSRole" %><%@
page import="com.liferay.osb.lcs.model.LCSSubscriptionEntry" %><%@
page import="com.liferay.osb.lcs.model.UserLCSMessage" %><%@
page import="com.liferay.osb.lcs.nosql.model.LCSClusterNodeInstallationEnvironment" %><%@
page import="com.liferay.osb.lcs.nosql.model.LCSClusterNodeLiferayVMMetrics" %><%@
page import="com.liferay.osb.lcs.nosql.service.LCSClusterNodeInstallationEnvironmentServiceUtil" %><%@
page import="com.liferay.osb.lcs.nosql.service.LCSClusterNodeLayoutMetricsServiceUtil" %><%@
page import="com.liferay.osb.lcs.nosql.service.LCSClusterNodePortletMetricsServiceUtil" %><%@
page import="com.liferay.osb.lcs.notifications.util.NotificationsUtil" %><%@
page import="com.liferay.osb.lcs.server.util.ServerAdvisor" %><%@
page import="com.liferay.osb.lcs.service.LCSClusterEntryServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.LCSClusterEntryTokenServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.LCSClusterNodeServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.LCSInvitationServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.LCSMessageServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.LCSMetadataServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.LCSNotificationServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.LCSProjectLocalServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.LCSProjectServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.LCSRoleLocalServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.LCSSubscriptionEntryLocalServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.UserLCSMessageServiceUtil" %><%@
page import="com.liferay.osb.lcs.service.permission.LCSClusterEntryPermission" %><%@
page import="com.liferay.osb.lcs.service.permission.LCSProjectPermission" %><%@
page import="com.liferay.osb.lcs.subscriptions.util.SubscriptionsUtil" %><%@
page import="com.liferay.osb.lcs.util.ApplicationProfile" %><%@
page import="com.liferay.osb.lcs.util.LCSClusterNodeUtil" %><%@
page import="com.liferay.osb.lcs.web.internal.advisor.AccountAdvisor" %><%@
page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %><%@
page import="com.liferay.portal.kernel.json.JSONArray" %><%@
page import="com.liferay.portal.kernel.json.JSONObject" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %><%@
page import="com.liferay.portal.kernel.model.CompanyConstants" %><%@
page import="com.liferay.portal.kernel.model.Layout" %><%@
page import="com.liferay.portal.kernel.model.User" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.portlet.PortletURLUtil" %><%@
page import="com.liferay.portal.kernel.security.auth.AuthTokenUtil" %><%@
page import="com.liferay.portal.kernel.service.ClassNameLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.LayoutLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.UserLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.util.DateUtil" %><%@
page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.HttpUtil" %><%@
page import="com.liferay.portal.kernel.util.ListUtil" %><%@
page import="com.liferay.portal.kernel.util.LocaleUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %>

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

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
PortletURL currentURLObj = PortletURLUtil.getCurrent(renderRequest, renderResponse);

String currentURL = currentURLObj.toString();

DecimalFormat decimalFormat = new DecimalFormat("#.0");
Format mediumDateFormatDate = FastDateFormatFactoryUtil.getDate(DateFormat.MEDIUM, locale, timeZone);
Format mediumDateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(DateFormat.MEDIUM, DateFormat.MEDIUM, locale, timeZone);

NumberFormat numberFormat = NumberFormat.getInstance();

numberFormat.setMaximumFractionDigits(2);
numberFormat.setMinimumFractionDigits(2);

OSBLCSConfiguration osbLCSConfiguration = portletDisplay.getPortletInstanceConfiguration(OSBLCSConfiguration.class);
%>