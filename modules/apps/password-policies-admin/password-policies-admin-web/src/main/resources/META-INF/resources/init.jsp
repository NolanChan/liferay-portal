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

<%@ page import="com.liferay.password.policies.admin.constants.PasswordPoliciesAdminPortletKeys" %><%@
page import="com.liferay.password.policies.admin.web.search.AddOrganizationPasswordPolicyChecker" %><%@
page import="com.liferay.password.policies.admin.web.search.AddUserPasswordPolicyChecker" %><%@
page import="com.liferay.password.policies.admin.web.search.DeleteOrganizationPasswordPolicyChecker" %><%@
page import="com.liferay.password.policies.admin.web.search.DeleteUserPasswordPolicyChecker" %><%@
page import="com.liferay.password.policies.admin.web.search.PasswordPolicyChecker" %><%@
page import="com.liferay.password.policies.admin.web.search.PasswordPolicyDisplayTerms" %><%@
page import="com.liferay.password.policies.admin.web.search.PasswordPolicySearch" %><%@
page import="com.liferay.portal.exception.DuplicatePasswordPolicyException" %><%@
page import="com.liferay.portal.exception.NoSuchPasswordPolicyException" %><%@
page import="com.liferay.portal.exception.PasswordPolicyNameException" %><%@
page import="com.liferay.portal.kernel.bean.BeanParamUtil" %><%@
page import="com.liferay.portal.kernel.dao.search.RowChecker" %><%@
page import="com.liferay.portal.kernel.dao.search.SearchContainer" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.security.ldap.LDAPSettingsUtil" %><%@
page import="com.liferay.portal.kernel.security.permission.ActionKeys" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.model.Organization" %><%@
page import="com.liferay.portal.model.OrganizationConstants" %><%@
page import="com.liferay.portal.model.PasswordPolicy" %><%@
page import="com.liferay.portal.model.PasswordPolicyRel" %><%@
page import="com.liferay.portal.model.User" %><%@
page import="com.liferay.portal.service.PasswordPolicyLocalServiceUtil" %><%@
page import="com.liferay.portal.service.PasswordPolicyRelLocalServiceUtil" %><%@
page import="com.liferay.portal.service.permission.PasswordPolicyPermissionUtil" %><%@
page import="com.liferay.portal.service.permission.PortalPermissionUtil" %><%@
page import="com.liferay.portal.util.PortalUtil" %><%@
page import="com.liferay.portlet.PortalPreferences" %><%@
page import="com.liferay.portlet.PortletPreferencesFactoryUtil" %><%@
page import="com.liferay.portlet.PortletURLUtil" %><%@
page import="com.liferay.portlet.usersadmin.search.OrganizationSearch" %><%@
page import="com.liferay.portlet.usersadmin.search.OrganizationSearchTerms" %><%@
page import="com.liferay.portlet.usersadmin.search.UserSearch" %><%@
page import="com.liferay.portlet.usersadmin.search.UserSearchTerms" %><%@
page import="com.liferay.taglib.search.ResultRow" %><%@
page import="com.liferay.users.admin.kernel.util.UsersAdmin" %>

<%@ page import="java.util.LinkedHashMap" %><%@
page import="java.util.List" %>

<%@ page import="javax.portlet.PortletURL" %><%@
page import="javax.portlet.WindowState" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
WindowState windowState = liferayPortletRequest.getWindowState();

PortletURL currentURLObj = PortletURLUtil.getCurrent(liferayPortletRequest, liferayPortletResponse);

String currentURL = currentURLObj.toString();

PortalPreferences portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(liferayPortletRequest);
%>

<%@ include file="/init-ext.jsp" %>