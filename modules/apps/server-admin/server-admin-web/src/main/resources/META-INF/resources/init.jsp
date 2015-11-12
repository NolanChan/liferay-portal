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
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.portal.captcha.recaptcha.ReCaptchaImpl" %><%@
page import="com.liferay.portal.convert.ConvertProcess" %><%@
page import="com.liferay.portal.convert.ConvertProcessUtil" %><%@
page import="com.liferay.portal.convert.FileSystemStoreRootDirException" %><%@
page import="com.liferay.portal.kernel.configuration.Filter" %><%@
page import="com.liferay.portal.kernel.dao.search.SearchContainer" %><%@
page import="com.liferay.portal.kernel.image.ImageMagickUtil" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.scripting.ScriptingUtil" %><%@
page import="com.liferay.portal.kernel.search.Indexer" %><%@
page import="com.liferay.portal.kernel.search.IndexerClassNameComparator" %><%@
page import="com.liferay.portal.kernel.search.IndexerRegistryUtil" %><%@
page import="com.liferay.portal.kernel.servlet.SessionMessages" %><%@
page import="com.liferay.portal.kernel.util.ArrayUtil" %><%@
page import="com.liferay.portal.kernel.util.CharPool" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ListUtil" %><%@
page import="com.liferay.portal.kernel.util.OSDetector" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.ProgressTracker" %><%@
page import="com.liferay.portal.kernel.util.PropsKeys" %><%@
page import="com.liferay.portal.kernel.util.ReleaseInfo" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.TextFormatter" %><%@
page import="com.liferay.portal.kernel.util.Time" %><%@
page import="com.liferay.portal.kernel.util.UnicodeProperties" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowConstants" %><%@
page import="com.liferay.portal.kernel.xuggler.XugglerUtil" %><%@
page import="com.liferay.portal.model.*" %><%@
page import="com.liferay.portal.model.impl.*" %><%@
page import="com.liferay.portal.service.*" %><%@
page import="com.liferay.portal.util.Portal" %><%@
page import="com.liferay.portal.util.PortalUtil" %><%@
page import="com.liferay.portal.util.PortletKeys" %><%@
page import="com.liferay.portal.util.PrefsPropsUtil" %><%@
page import="com.liferay.portal.util.PropsUtil" %><%@
page import="com.liferay.portal.util.PropsValues" %><%@
page import="com.liferay.portal.util.ShutdownUtil" %><%@
page import="com.liferay.portlet.PortletURLUtil" %><%@
page import="com.liferay.portlet.documentlibrary.model.DLFileEntry" %><%@
page import="com.liferay.portlet.documentlibrary.model.DLFileVersion" %><%@
page import="com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil" %><%@
page import="com.liferay.portlet.expando.model.ExpandoBridge" %><%@
page import="com.liferay.portlet.expando.model.ExpandoColumnConstants" %><%@
page import="com.liferay.server.admin.web.util.CacheStatistics" %><%@
page import="com.liferay.server.admin.web.util.CacheStatisticsUtil" %><%@
page import="com.liferay.util.log4j.Levels" %>

<%@ page import="java.text.NumberFormat" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.Collection" %><%@
page import="java.util.Collections" %><%@
page import="java.util.Enumeration" %><%@
page import="java.util.Iterator" %><%@
page import="java.util.List" %><%@
page import="java.util.Map" %><%@
page import="java.util.Properties" %><%@
page import="java.util.TreeMap" %>

<%@ page import="javax.portlet.PortletMode" %><%@
page import="javax.portlet.PortletPreferences" %><%@
page import="javax.portlet.PortletURL" %><%@
page import="javax.portlet.WindowState" %>

<%@ page import="org.apache.commons.beanutils.BeanComparator" %><%@
page import="org.apache.log4j.Level" %><%@
page import="org.apache.log4j.LogManager" %><%@
page import="org.apache.log4j.Logger" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
PortletMode portletMode = liferayPortletRequest.getPortletMode();
WindowState windowState = liferayPortletRequest.getWindowState();

PortletURL currentURLObj = PortletURLUtil.getCurrent(liferayPortletRequest, liferayPortletResponse);

String currentURL = currentURLObj.toString();
%>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "server");

boolean showTabs1 = false;

if (portletName.equals(PortletKeys.ADMIN_PLUGINS)) {
	tabs1 = "plugins";
}
else if (portletName.equals(PortletKeys.SERVER_ADMIN)) {
	tabs1 = "server";
}

String tabs2 = ParamUtil.getString(request, "tabs2");
String tabs3 = ParamUtil.getString(request, "tabs3");

if (tabs1.equals("plugins")) {
	if (!tabs2.equals("portlet-plugins") && !tabs2.equals("theme-plugins") && !tabs2.equals("layout-template-plugins") && !tabs2.equals("hook-plugins") && !tabs2.equals("web-plugins")) {
		tabs2 = "portlet-plugins";
	}
}

NumberFormat numberFormat = NumberFormat.getInstance();

numberFormat.setMaximumIntegerDigits(2);
numberFormat.setMinimumIntegerDigits(2);
%>

<%@ include file="/init-ext.jsp" %>