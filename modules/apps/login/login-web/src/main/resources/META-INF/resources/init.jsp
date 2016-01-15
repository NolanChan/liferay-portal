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
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.login.web.portlet.util.LoginUtil" %><%@
page import="com.liferay.portal.CookieNotSupportedException" %><%@
page import="com.liferay.portal.RequiredFieldException" %><%@
page import="com.liferay.portal.TermsOfUseException" %><%@
page import="com.liferay.portal.exception.AddressCityException" %><%@
page import="com.liferay.portal.exception.AddressStreetException" %><%@
page import="com.liferay.portal.exception.AddressZipException" %><%@
page import="com.liferay.portal.exception.CompanyMaxUsersException" %><%@
page import="com.liferay.portal.exception.ContactBirthdayException" %><%@
page import="com.liferay.portal.exception.ContactNameException" %><%@
page import="com.liferay.portal.exception.DuplicateOpenIdException" %><%@
page import="com.liferay.portal.exception.EmailAddressException" %><%@
page import="com.liferay.portal.exception.GroupFriendlyURLException" %><%@
page import="com.liferay.portal.exception.NoSuchCountryException" %><%@
page import="com.liferay.portal.exception.NoSuchListTypeException" %><%@
page import="com.liferay.portal.exception.NoSuchRegionException" %><%@
page import="com.liferay.portal.exception.NoSuchUserException" %><%@
page import="com.liferay.portal.exception.PasswordExpiredException" %><%@
page import="com.liferay.portal.exception.PhoneNumberException" %><%@
page import="com.liferay.portal.exception.PhoneNumberExtensionException" %><%@
page import="com.liferay.portal.exception.RequiredReminderQueryException" %><%@
page import="com.liferay.portal.exception.SendPasswordException" %><%@
page import="com.liferay.portal.exception.UserActiveException" %><%@
page import="com.liferay.portal.exception.UserEmailAddressException" %><%@
page import="com.liferay.portal.exception.UserIdException" %><%@
page import="com.liferay.portal.exception.UserLockoutException" %><%@
page import="com.liferay.portal.exception.UserPasswordException" %><%@
page import="com.liferay.portal.exception.UserReminderQueryException" %><%@
page import="com.liferay.portal.exception.UserScreenNameException" %><%@
page import="com.liferay.portal.exception.WebsiteURLException" %><%@
page import="com.liferay.portal.kernel.bean.BeanParamUtil" %><%@
page import="com.liferay.portal.kernel.captcha.CaptchaConfigurationException" %><%@
page import="com.liferay.portal.kernel.captcha.CaptchaMaxChallengesException" %><%@
page import="com.liferay.portal.kernel.captcha.CaptchaTextException" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.servlet.SessionMessages" %><%@
page import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.LocalizationUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PrefsPropsUtil" %><%@
page import="com.liferay.portal.kernel.util.PropsKeys" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.TextFormatter" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowConstants" %><%@
page import="com.liferay.portal.model.CompanyConstants" %><%@
page import="com.liferay.portal.model.Contact" %><%@
page import="com.liferay.portal.model.User" %><%@
page import="com.liferay.portal.security.auth.AuthException" %><%@
page import="com.liferay.portal.security.auth.FullNameDefinition" %><%@
page import="com.liferay.portal.security.auth.FullNameDefinitionFactory" %><%@
page import="com.liferay.portal.security.auth.PrincipalException" %><%@
page import="com.liferay.portal.security.auth.ScreenNameValidator" %><%@
page import="com.liferay.portal.security.auth.ScreenNameValidatorFactory" %><%@
page import="com.liferay.portal.service.UserLocalServiceUtil" %><%@
page import="com.liferay.portal.util.PortalUtil" %><%@
page import="com.liferay.portal.util.PortletKeys" %><%@
page import="com.liferay.portal.util.PropsValues" %><%@
page import="com.liferay.portlet.PortletURLFactoryUtil" %><%@
page import="com.liferay.util.ContentUtil" %>

<%@ page import="java.util.Calendar" %><%@
page import="java.util.Date" %>

<%@ page import="javax.portlet.PortletMode" %><%@
page import="javax.portlet.PortletRequest" %><%@
page import="javax.portlet.PortletURL" %><%@
page import="javax.portlet.WindowState" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
WindowState windowState = liferayPortletRequest.getWindowState();

String authType = portletPreferences.getValue("authType", StringPool.BLANK);
%>

<%@ include file="/init-ext.jsp" %>