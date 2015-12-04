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

<%@ include file="/init.jsp" %>

<%
int type = ParamUtil.getInteger(request, "type", 1);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/view.jsp");
portletURL.setParameter("type", String.valueOf(type));

pageContext.setAttribute("portletURL", portletURL);

String portletURLString = portletURL.toString();
%>

<liferay-ui:error exception="<%= RequiredRoleException.class %>" message="you-cannot-delete-a-system-role" />

<aui:form action="<%= portletURLString %>" cssClass="container-fluid-1280" method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="portletURL" />

	<liferay-ui:search-container
		searchContainer="<%= new RoleSearch(renderRequest, portletURL) %>"
	>
		<aui:nav-bar>
			<aui:nav cssClass="navbar-nav">
				<c:if test="<%= PortalPermissionUtil.contains(permissionChecker, ActionKeys.ADD_ROLE) %>">
					<portlet:renderURL var="viewRolesURL">
						<portlet:param name="mvcPath" value="/view.jsp" />
					</portlet:renderURL>

					<liferay-portlet:renderURL varImpl="addRoleURL">
						<portlet:param name="mvcPath" value="/edit_role.jsp" />
						<portlet:param name="redirect" value="<%= viewRolesURL %>" />
					</liferay-portlet:renderURL>

					<%
					String toolbarItem = ParamUtil.getString(request, "toolbarItem");
					%>

					<aui:nav-item dropdown="<%= true %>" label="add" selected='<%= toolbarItem.equals("add") %>'>

						<%
						addRoleURL.setParameter("type", String.valueOf(RoleConstants.TYPE_REGULAR));
						%>

						<aui:nav-item href="<%= addRoleURL.toString() %>" label="regular-role" />

						<%
						addRoleURL.setParameter("type", String.valueOf(RoleConstants.TYPE_SITE));
						%>

						<aui:nav-item href="<%= addRoleURL.toString() %>" label="site-role" />

						<%
						addRoleURL.setParameter("type", String.valueOf(RoleConstants.TYPE_ORGANIZATION));
						%>

						<aui:nav-item href="<%= addRoleURL.toString() %>" label="organization-role" />
					</aui:nav-item>
				</c:if>
			</aui:nav>

			<aui:nav-bar-search searchContainer="<%= searchContainer %>">

				<%
				String cssClass = "form-search";

				if (windowState.equals(WindowState.MAXIMIZED)) {
					cssClass += " col-xs-12";
				}
				%>

				<div class="<%= cssClass %>">
					<liferay-ui:input-search autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>" placeholder='<%= LanguageUtil.get(request, "keywords") %>' />
				</div>
			</aui:nav-bar-search>
		</aui:nav-bar>

		<liferay-ui:search-container-results>

			<%
			RoleSearchTerms searchTerms = (RoleSearchTerms)searchContainer.getSearchTerms();

			total = RoleServiceUtil.searchCount(company.getCompanyId(), searchTerms.getKeywords(), searchTerms.getTypesObj(), new LinkedHashMap<String, Object>());

			searchContainer.setTotal(total);

			results = RoleServiceUtil.search(company.getCompanyId(), searchTerms.getKeywords(), searchTerms.getTypesObj(), new LinkedHashMap<String, Object>(), searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());

			searchContainer.setResults(results);

			portletURL.setParameter(searchContainer.getCurParam(), String.valueOf(searchContainer.getCur()));
			%>

		</liferay-ui:search-container-results>

		<aui:input name="rolesRedirect" type="hidden" value="<%= portletURL.toString() %>" />

		<liferay-ui:search-container-row
			className="com.liferay.portal.model.Role"
			keyProperty="roleId"
			modelVar="role"
		>

			<%
			PortletURL rowURL = null;

			if (RolePermissionUtil.contains(permissionChecker, role.getRoleId(), ActionKeys.UPDATE)) {
				rowURL = renderResponse.createRenderURL();

				rowURL.setParameter("mvcPath", "/edit_role.jsp");
				rowURL.setParameter("redirect", searchContainer.getIteratorURL().toString());
				rowURL.setParameter("roleId", String.valueOf(role.getRoleId()));
			}
			%>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="title"
			>
				<liferay-ui:icon
					iconCssClass="<%= RolesAdminUtil.getIconCssClass(role) %>"
					label="<%= true %>"
					message="<%= HtmlUtil.escape(role.getTitle(locale)) %>"
				/>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="type"
				value="<%= LanguageUtil.get(request, role.getTypeLabel()) %>"
			/>

			<c:if test="<%= (PropsValues.ROLES_ORGANIZATION_SUBTYPES.length > 0) || (PropsValues.ROLES_REGULAR_SUBTYPES.length > 0) || (PropsValues.ROLES_SITE_SUBTYPES.length > 0) %>">
				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="subType"
					value="<%= LanguageUtil.get(request, role.getSubtype()) %>"
				/>
			</c:if>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="description"
				value="<%= HtmlUtil.escape(role.getDescription(locale)) %>"
			/>

			<liferay-ui:search-container-column-jsp
				cssClass="entry-action"
				path="/role_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator markupView="lexicon" />
	</liferay-ui:search-container>
</aui:form>