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

<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.PropertiesParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PropsUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.UnicodeProperties" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.saml.exception.CertificateKeyPasswordException" %><%@
page import="com.liferay.saml.exception.DuplicateSamlIdpSpConnectionSamlSpEntityIdException" %><%@
page import="com.liferay.saml.exception.DuplicateSamlSpIdpConnectionSamlIdpEntityIdException" %><%@
page import="com.liferay.saml.exception.SamlIdpSpConnectionMetadataUrlException" %><%@
page import="com.liferay.saml.exception.SamlIdpSpConnectionMetadataXmlException" %><%@
page import="com.liferay.saml.exception.SamlIdpSpConnectionNameException" %><%@
page import="com.liferay.saml.exception.SamlIdpSpConnectionSamlSpEntityIdException" %><%@
page import="com.liferay.saml.exception.SamlSpIdpConnectionMetadataUrlException" %><%@
page import="com.liferay.saml.exception.SamlSpIdpConnectionMetadataXmlException" %><%@
page import="com.liferay.saml.exception.SamlSpIdpConnectionSamlIdpEntityIdException" %><%@
page import="com.liferay.saml.model.SamlIdpSpConnection" %><%@
page import="com.liferay.saml.model.SamlSpIdpConnection" %><%@
page import="com.liferay.saml.service.SamlIdpSpConnectionLocalServiceUtil" %><%@
page import="com.liferay.saml.service.SamlSpIdpConnectionLocalServiceUtil" %><%@
page import="com.liferay.saml.util.CertificateUtil" %><%@
page import="com.liferay.saml.util.PortletPrefsPropsUtil" %><%@
page import="com.liferay.saml.util.PortletPropsKeys" %><%@
page import="com.liferay.saml.util.SamlUtil" %><%@
page import="com.liferay.saml.web.metadata.MetadataManagerUtil" %><%@
page import="com.liferay.taglib.search.ResultRow" %>

<%@ page import="java.security.InvalidParameterException" %><%@
page import="java.security.cert.X509Certificate" %>

<%@ page import="java.util.Date" %>

<%@ page import="javax.portlet.PortletURL" %>

<%@ page import="org.opensaml.saml2.core.NameIDType" %><%@
page import="org.opensaml.xml.security.x509.X509Credential" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
String currentURL = PortalUtil.getCurrentURL(request);
%>