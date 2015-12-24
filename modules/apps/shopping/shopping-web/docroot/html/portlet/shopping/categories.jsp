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

<%@ include file="/html/portlet/shopping/init.jsp" %>

<%
ShoppingCategory category = (ShoppingCategory)request.getAttribute(WebKeys.SHOPPING_CATEGORY);

long categoryId = BeanParamUtil.getLong(category, request, "categoryId", ShoppingCategoryConstants.DEFAULT_PARENT_CATEGORY_ID);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/shopping/view");
portletURL.setParameter("tabs1", "cateogires");
portletURL.setParameter("categoryId", String.valueOf(categoryId));
%>

<liferay-util:include page="/html/portlet/shopping/tabs1.jsp" servletContext="<%= application %>" />

<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="struts_action" value="/shopping/search" />
</liferay-portlet:renderURL>

<liferay-ui:panel-container extended="<%= true %>" persistState="<%= true %>">
	<aui:form action="<%= searchURL %>" method="get" name="fm1">
		<liferay-portlet:renderURLParams varImpl="searchURL" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="breadcrumbsCategoryId" type="hidden" value="<%= categoryId %>" />
		<aui:input name="searchCategoryIds" type="hidden" value="<%= categoryId %>" />

		<div class="breadcrumbs">
			<%= ShoppingUtil.getBreadcrumbs(category, renderRequest, renderResponse) %>
		</div>

		<%
		List<String> headerNames = new ArrayList<String>();

		headerNames.add("category");
		headerNames.add("num-of-categories");
		headerNames.add("num-of-items");
		headerNames.add(StringPool.BLANK);

		SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, "cur1", SearchContainer.DEFAULT_DELTA, portletURL, headerNames, null);

		int total = ShoppingCategoryServiceUtil.getCategoriesCount(scopeGroupId, categoryId);

		searchContainer.setTotal(total);

		List results = ShoppingCategoryServiceUtil.getCategories(scopeGroupId, categoryId, searchContainer.getStart(), searchContainer.getEnd());

		searchContainer.setResults(results);

		List resultRows = searchContainer.getResultRows();

		for (int i = 0; i < results.size(); i++) {
			ShoppingCategory curCategory = (ShoppingCategory)results.get(i);

			curCategory = curCategory.toEscapedModel();

			ResultRow row = new ResultRow(curCategory, curCategory.getCategoryId(), i);

			PortletURL rowURL = renderResponse.createRenderURL();

			rowURL.setParameter("struts_action", "/shopping/view");
			rowURL.setParameter("categoryId", String.valueOf(curCategory.getCategoryId()));

			// Name and description

			if (Validator.isNotNull(curCategory.getDescription())) {
				row.addText(curCategory.getName().concat("<br />").concat(curCategory.getDescription()), rowURL);
			}
			else {
				row.addText(curCategory.getName(), rowURL);
			}

			// Statistics

			List subcategoryIds = new ArrayList();

			subcategoryIds.add(Long.valueOf(curCategory.getCategoryId()));

			ShoppingCategoryServiceUtil.getSubcategoryIds(subcategoryIds, scopeGroupId, curCategory.getCategoryId());

			int categoriesCount = subcategoryIds.size() - 1;
			int itemsCount = ShoppingItemServiceUtil.getCategoriesItemsCount(scopeGroupId, subcategoryIds);

			row.addText(String.valueOf(categoriesCount), rowURL);
			row.addText(String.valueOf(itemsCount), rowURL);

			// Action

			row.addJSP("/html/portlet/shopping/category_action.jsp", "entry-action", application, request, response);

			// Add result row

			resultRows.add(row);
		}

		boolean showPermissionsButton = ShoppingCategoryPermission.contains(permissionChecker, scopeGroupId, categoryId, ActionKeys.PERMISSIONS);
		boolean showSearch = !results.isEmpty();
		%>

		<liferay-ui:panel-container extended="<%= true %>" id="shoppingCategoriesPanelContainer" persistState="<%= true %>">
			<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="shoppingCategoriesPanel" persistState="<%= true %>" title="categories">
				<c:if test="<%= showPermissionsButton || showSearch %>">
					<aui:fieldset>
						<c:if test="<%= showSearch %>">
							<aui:input autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>" id="keywords1" label="search" name="keywords" size="30" type="text" />
						</c:if>
					</aui:fieldset>

					<aui:button-row>
						<c:if test="<%= showSearch %>">
							<aui:button cssClass="btn-lg" type="submit" value="search-categories" />
						</c:if>

						<c:if test="<%= showPermissionsButton %>">

							<%
							String modelResource = "com.liferay.shopping";
							String modelResourceDescription = themeDisplay.getScopeGroupName();
							String resourcePrimKey = String.valueOf(scopeGroupId);

							if (category != null) {
								modelResource = ShoppingCategory.class.getName();
								modelResourceDescription = category.getName();
								resourcePrimKey = String.valueOf(category.getCategoryId());
							}
							%>

							<liferay-security:permissionsURL
								modelResource="<%= modelResource %>"
								modelResourceDescription="<%= HtmlUtil.escape(modelResourceDescription) %>"
								resourcePrimKey="<%= resourcePrimKey %>"
								var="permissionsURL"
								windowState="<%= LiferayWindowState.POP_UP.toString() %>"
							/>

							<aui:button cssClass="btn-lg" href="<%= permissionsURL %>" useDialog="<%= true %>" value="permissions" />
						</c:if>
					</aui:button-row>
				</c:if>

				<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
			</liferay-ui:panel>
		</liferay-ui:panel-container>
	</aui:form>

	<aui:form action="<%= searchURL %>" method="get" name="fm2">
		<liferay-portlet:renderURLParams varImpl="searchURL" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
		<aui:input name="breadcrumbsCategoryId" type="hidden" value="<%= categoryId %>" />
		<aui:input name="searchCategoryId" type="hidden" value="<%= categoryId %>" />

		<%
		String orderByCol = ParamUtil.getString(request, "orderByCol");
		String orderByType = ParamUtil.getString(request, "orderByType");

		if (Validator.isNotNull(orderByCol) && Validator.isNotNull(orderByType)) {
			portalPreferences.setValue(PortletKeys.SHOPPING, "items-order-by-col", orderByCol);
			portalPreferences.setValue(PortletKeys.SHOPPING, "items-order-by-type", orderByType);
		}
		else {
			orderByCol = portalPreferences.getValue(PortletKeys.SHOPPING, "items-order-by-col", "sku");
			orderByType = portalPreferences.getValue(PortletKeys.SHOPPING, "items-order-by-type", "asc");
		}

		OrderByComparator<ShoppingItem> orderByComparator = ShoppingUtil.getItemOrderByComparator(orderByCol, orderByType);

		List<String> headerNames = new ArrayList<String>();

		headerNames.add("sku");
		headerNames.add("description");
		headerNames.add("min-qty");
		headerNames.add("price");
		headerNames.add(StringPool.BLANK);

		Map orderableHeaders = new HashMap();

		orderableHeaders.put("sku", "sku");
		orderableHeaders.put("description", "name");
		orderableHeaders.put("min-qty", "min-qty");
		orderableHeaders.put("price", "price");

		SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, "cur2", SearchContainer.DEFAULT_DELTA, portletURL, headerNames, null);

		searchContainer.setOrderableHeaders(orderableHeaders);
		searchContainer.setOrderByCol(orderByCol);
		searchContainer.setOrderByType(orderByType);

		int total = ShoppingItemServiceUtil.getItemsCount(scopeGroupId, categoryId);

		searchContainer.setTotal(total);

		List results = ShoppingItemServiceUtil.getItems(scopeGroupId, categoryId, searchContainer.getStart(), searchContainer.getEnd(), orderByComparator);

		searchContainer.setResults(results);

		List resultRows = searchContainer.getResultRows();

		for (int i = 0; i < results.size(); i++) {
			ShoppingItem item = (ShoppingItem)results.get(i);

			item = item.toEscapedModel();

			ResultRow row = new ResultRow(item, item.getItemId(), i);

			PortletURL rowURL = renderResponse.createRenderURL();

			rowURL.setParameter("struts_action", "/shopping/view_item");
			rowURL.setParameter("redirect", currentURL);
			rowURL.setParameter("itemId", String.valueOf(item.getItemId()));

			// SKU and small image

			StringBundler sb = new StringBundler(6);

			if (item.isSmallImage()) {
				sb.append("<br />");
				sb.append("<img alt=\"");
				sb.append(HtmlUtil.escapeAttribute(item.getSku()));
				sb.append("\" src=\"");
				sb.append(item.getShoppingItemImageURL(themeDisplay));
				sb.append("\">");
			}
			else {
				sb.append(item.getSku());
			}

			row.addText(sb.toString(), rowURL);

			// Description

			sb = new StringBundler();

			sb.append(item.getName());

			if (Validator.isNotNull(item.getDescription())) {
				sb.append("<br />");
				sb.append(item.getDescription());
			}

			Properties props = new OrderedProperties();

			PropertiesUtil.load(props, item.getProperties());

			Enumeration enu = props.propertyNames();

			while (enu.hasMoreElements()) {
				String propsKey = (String)enu.nextElement();
				String propsValue = props.getProperty(propsKey, StringPool.BLANK);

				sb.append("<br />");
				sb.append(propsKey);
				sb.append(": ");
				sb.append(propsValue);
			}

			row.addText(sb.toString(), rowURL);

			// Minimum quantity

			row.addText(String.valueOf(item.getMinQuantity()), rowURL);

			// Price

			if (item.getDiscount() <= 0) {
				row.addText(currencyFormat.format(item.getPrice()), rowURL);
			}
			else {
				row.addText(
					"<div class=\"alert alert-success\">" +
						currencyFormat.format(ShoppingUtil.calculateActualPrice(item)) +
							"</div>",
					rowURL);
			}

			// Action

			row.addJSP("/html/portlet/shopping/item_action.jsp", "entry-action", application, request, response);

			// Add result row

			resultRows.add(row);
		}
		%>

		<liferay-ui:panel-container extended="<%= true %>" id="shoppingItemsPanelContainer" persistState="<%= true %>">
			<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="shoppingItemsPanel" persistState="<%= true %>" title="items">
				<c:if test="<%= !results.isEmpty() %>">
					<aui:fieldset>
						<aui:input autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>" id="keywords2" label="search" name="keywords" size="30" type="text" />
					</aui:fieldset>

					<aui:button-row>
						<aui:button cssClass="btn-lg" type="submit" value="search-this-category" />
					</aui:button-row>
				</c:if>

				<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
			</liferay-ui:panel>
		</liferay-ui:panel-container>
	</aui:form>
</liferay-ui:panel-container>

<%
boolean showAddCategoryButton = ShoppingCategoryPermission.contains(permissionChecker, scopeGroupId, categoryId, ActionKeys.ADD_CATEGORY);
boolean showAddItemButton = ShoppingCategoryPermission.contains(permissionChecker, scopeGroupId, categoryId, ActionKeys.ADD_ITEM);
%>

<c:if test="<%= showAddCategoryButton || showAddItemButton %>">
	<liferay-frontend:add-menu>
		<c:if test="<%= showAddCategoryButton %>">
			<portlet:renderURL var="addCategoriesURL">
				<portlet:param name="struts_action" value="/shopping/edit_category" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="parentCategoryId" value="<%= String.valueOf(categoryId) %>" />
			</portlet:renderURL>

			<liferay-frontend:add-menu-item title='<%= LanguageUtil.get(request, "add-category") %>' url="<%= addCategoriesURL.toString() %>" />
		</c:if>

		<c:if test="<%= showAddItemButton %>">
			<portlet:renderURL var="addItemURL">
				<portlet:param name="struts_action" value="/shopping/edit_item" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="categoryId" value="<%= String.valueOf(categoryId) %>" />
			</portlet:renderURL>

			<liferay-frontend:add-menu-item title='<%= LanguageUtil.get(request, "add-item") %>' url="<%= addItemURL.toString() %>" />
		</c:if>
	</liferay-frontend:add-menu>
</c:if>