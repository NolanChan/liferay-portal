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

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ddm" prefix="liferay-ddm" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@
page import="com.liferay.portal.kernel.bean.BeanParamUtil" %><%@
page import="com.liferay.portal.kernel.dao.search.DisplayTerms" %><%@
page import="com.liferay.portal.kernel.dao.search.RowChecker" %><%@
page import="com.liferay.portal.kernel.dao.search.SearchContainer" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %><%@
page import="com.liferay.portal.kernel.log.Log" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.portlet.PortletProvider" %><%@
page import="com.liferay.portal.kernel.portlet.PortletProviderUtil" %><%@
page import="com.liferay.portal.kernel.portlet.PortletRequestModel" %><%@
page import="com.liferay.portal.kernel.servlet.SessionMessages" %><%@
page import="com.liferay.portal.kernel.upload.LiferayFileItemException" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ListUtil" %><%@
page import="com.liferay.portal.kernel.util.LocaleUtil" %><%@
page import="com.liferay.portal.kernel.util.LocalizationUtil" %><%@
page import="com.liferay.portal.kernel.util.MathUtil" %><%@
page import="com.liferay.portal.kernel.util.OrderByComparator" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PropsKeys" %><%@
page import="com.liferay.portal.kernel.util.StringBundler" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.TextFormatter" %><%@
page import="com.liferay.portal.kernel.util.UnicodeFormatter" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.security.auth.PrincipalException" %><%@
page import="com.liferay.portal.security.permission.ActionKeys" %><%@
page import="com.liferay.portal.service.*" %><%@
page import="com.liferay.portal.upload.LiferayFileItem" %><%@
page import="com.liferay.portal.util.PortalUtil" %><%@
page import="com.liferay.portal.util.PortletKeys" %><%@
page import="com.liferay.portal.util.PrefsPropsUtil" %><%@
page import="com.liferay.portal.util.PropsValues" %><%@
page import="com.liferay.portal.util.WebKeys" %><%@
page import="com.liferay.portlet.PortalPreferences" %><%@
page import="com.liferay.portlet.PortletPreferencesFactoryUtil" %><%@
page import="com.liferay.portlet.PortletURLFactoryUtil" %><%@
page import="com.liferay.portlet.PortletURLImpl" %><%@
page import="com.liferay.portlet.PortletURLUtil" %><%@
page import="com.liferay.portlet.trash.model.TrashEntry" %><%@
page import="com.liferay.portlet.trash.util.TrashUtil" %><%@
page import="com.liferay.taglib.search.ResultRow" %>

<%@ page import="java.text.Format" %><%@
page import="java.text.NumberFormat" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.Collections" %><%@
page import="java.util.Currency" %><%@
page import="java.util.Date" %><%@
page import="java.util.HashMap" %><%@
page import="java.util.LinkedHashMap" %><%@
page import="java.util.List" %><%@
page import="java.util.Locale" %><%@
page import="java.util.Map" %><%@
page import="java.util.Set" %>

<%@ page import="javax.portlet.ActionRequest" %><%@
page import="javax.portlet.PortletRequest" %><%@
page import="javax.portlet.PortletURL" %><%@
page import="javax.portlet.ResourceURL" %><%@
page import="javax.portlet.WindowState" %>

<%@ page import="com.liferay.shopping.exception.BillingCityException" %><%@
page import="com.liferay.shopping.exception.BillingCountryException" %><%@
page import="com.liferay.shopping.exception.BillingEmailAddressException" %><%@
page import="com.liferay.shopping.exception.BillingFirstNameException" %><%@
page import="com.liferay.shopping.exception.BillingLastNameException" %><%@
page import="com.liferay.shopping.exception.BillingPhoneException" %><%@
page import="com.liferay.shopping.exception.BillingStateException" %><%@
page import="com.liferay.shopping.exception.BillingStreetException" %><%@
page import="com.liferay.shopping.exception.BillingZipException" %><%@
page import="com.liferay.shopping.exception.CCExpirationException" %><%@
page import="com.liferay.shopping.exception.CCNameException" %><%@
page import="com.liferay.shopping.exception.CCNumberException" %><%@
page import="com.liferay.shopping.exception.CCTypeException" %><%@
page import="com.liferay.shopping.exception.CartMinQuantityException" %><%@
page import="com.liferay.shopping.exception.CategoryNameException" %><%@
page import="com.liferay.shopping.exception.CouponActiveException" %><%@
page import="com.liferay.shopping.exception.CouponCodeException" %><%@
page import="com.liferay.shopping.exception.CouponDateException" %><%@
page import="com.liferay.shopping.exception.CouponDescriptionException" %><%@
page import="com.liferay.shopping.exception.CouponDiscountException" %><%@
page import="com.liferay.shopping.exception.CouponEndDateException" %><%@
page import="com.liferay.shopping.exception.CouponLimitCategoriesException" %><%@
page import="com.liferay.shopping.exception.CouponLimitSKUsException" %><%@
page import="com.liferay.shopping.exception.CouponMinimumOrderException" %><%@
page import="com.liferay.shopping.exception.CouponNameException" %><%@
page import="com.liferay.shopping.exception.CouponStartDateException" %><%@
page import="com.liferay.shopping.exception.DuplicateCouponCodeException" %><%@
page import="com.liferay.shopping.exception.DuplicateItemFieldNameException" %><%@
page import="com.liferay.shopping.exception.DuplicateItemSKUException" %><%@
page import="com.liferay.shopping.exception.ItemLargeImageNameException" %><%@
page import="com.liferay.shopping.exception.ItemLargeImageSizeException" %><%@
page import="com.liferay.shopping.exception.ItemMediumImageNameException" %><%@
page import="com.liferay.shopping.exception.ItemMediumImageSizeException" %><%@
page import="com.liferay.shopping.exception.ItemNameException" %><%@
page import="com.liferay.shopping.exception.ItemSKUException" %><%@
page import="com.liferay.shopping.exception.ItemSmallImageNameException" %><%@
page import="com.liferay.shopping.exception.ItemSmallImageSizeException" %><%@
page import="com.liferay.shopping.exception.NoSuchCategoryException" %><%@
page import="com.liferay.shopping.exception.NoSuchCouponException" %><%@
page import="com.liferay.shopping.exception.NoSuchItemException" %><%@
page import="com.liferay.shopping.exception.NoSuchOrderException" %><%@
page import="com.liferay.shopping.exception.ShippingCityException" %><%@
page import="com.liferay.shopping.exception.ShippingCountryException" %><%@
page import="com.liferay.shopping.exception.ShippingEmailAddressException" %><%@
page import="com.liferay.shopping.exception.ShippingFirstNameException" %><%@
page import="com.liferay.shopping.exception.ShippingLastNameException" %><%@
page import="com.liferay.shopping.exception.ShippingPhoneException" %><%@
page import="com.liferay.shopping.exception.ShippingStateException" %><%@
page import="com.liferay.shopping.exception.ShippingStreetException" %><%@
page import="com.liferay.shopping.exception.ShippingZipException" %><%@
page import="com.liferay.shopping.settings.ShoppingGroupServiceSettings" %><%@
page import="com.liferay.shopping.model.ShoppingCart" %><%@
page import="com.liferay.shopping.model.ShoppingCartItem" %><%@
page import="com.liferay.shopping.model.ShoppingCategory" %><%@
page import="com.liferay.shopping.model.ShoppingCategoryConstants" %><%@
page import="com.liferay.shopping.model.ShoppingCoupon" %><%@
page import="com.liferay.shopping.model.ShoppingCouponConstants" %><%@
page import="com.liferay.shopping.model.ShoppingItem" %><%@
page import="com.liferay.shopping.model.ShoppingItemField" %><%@
page import="com.liferay.shopping.model.ShoppingItemPrice" %><%@
page import="com.liferay.shopping.model.ShoppingItemPriceConstants" %><%@
page import="com.liferay.shopping.model.ShoppingOrder" %><%@
page import="com.liferay.shopping.model.ShoppingOrderConstants" %><%@
page import="com.liferay.shopping.model.ShoppingOrderItem" %><%@
page import="com.liferay.shopping.web.search.CouponDisplayTerms" %><%@
page import="com.liferay.shopping.web.search.CouponSearch" %><%@
page import="com.liferay.shopping.web.search.OrderDisplayTerms" %><%@
page import="com.liferay.shopping.web.search.OrderSearch" %><%@
page import="com.liferay.shopping.web.search.OrderSearchTerms" %><%@
page import="com.liferay.shopping.service.ShoppingCartLocalServiceUtil" %><%@
page import="com.liferay.shopping.service.ShoppingCategoryServiceUtil" %><%@
page import="com.liferay.shopping.service.ShoppingCouponLocalServiceUtil" %><%@
page import="com.liferay.shopping.service.ShoppingCouponServiceUtil" %><%@
page import="com.liferay.shopping.service.ShoppingItemFieldLocalServiceUtil" %><%@
page import="com.liferay.shopping.service.ShoppingItemLocalServiceUtil" %><%@
page import="com.liferay.shopping.service.ShoppingItemPriceLocalServiceUtil" %><%@
page import="com.liferay.shopping.service.ShoppingItemServiceUtil" %><%@
page import="com.liferay.shopping.service.ShoppingOrderItemLocalServiceUtil" %><%@
page import="com.liferay.shopping.service.ShoppingOrderLocalServiceUtil" %><%@
page import="com.liferay.shopping.service.permission.ShoppingCategoryPermission" %><%@
page import="com.liferay.shopping.service.permission.ShoppingItemPermission" %><%@
page import="com.liferay.shopping.service.permission.ShoppingOrderPermission" %><%@
page import="com.liferay.shopping.service.permission.ShoppingPermission" %><%@
page import="com.liferay.shopping.util.ShoppingConstants" %><%@
page import="com.liferay.shopping.util.ShoppingUtil" %>

<liferay-theme:defineObjects />
<portlet:defineObjects />

<%
WindowState windowState = liferayPortletRequest.getWindowState();
PortalPreferences portalPreferences = PortletPreferencesFactoryUtil.getPortalPreferences(request);

ShoppingGroupServiceSettings shoppingGroupServiceSettings = ShoppingGroupServiceSettings.getInstance(themeDisplay.getSiteGroupId());

Currency currency = Currency.getInstance(shoppingGroupServiceSettings.getCurrencyId());

NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);

currencyFormat.setCurrency(currency);

Format dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(locale, timeZone);

NumberFormat doubleFormat = NumberFormat.getNumberInstance(locale);

doubleFormat.setMaximumFractionDigits(2);
doubleFormat.setMinimumFractionDigits(2);

NumberFormat percentFormat = NumberFormat.getPercentInstance(locale);

NumberFormat taxFormat = NumberFormat.getPercentInstance(locale);

taxFormat.setMinimumFractionDigits(3);
%>

<%@ include file="/html/portlet/shopping/init-ext.jsp" %>