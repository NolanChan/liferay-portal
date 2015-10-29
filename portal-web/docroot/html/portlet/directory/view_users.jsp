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

<%@ include file="/html/portlet/directory/init.jsp" %>

<%
String viewUsersRedirect = ParamUtil.getString(request, "viewUsersRedirect");

PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");

if (Validator.isNotNull(viewUsersRedirect)) {
	portletURL.setParameter("viewUsersRedirect", viewUsersRedirect);
}
%>

<c:if test="<%= Validator.isNotNull(viewUsersRedirect) %>">
	<aui:input name="viewUsersRedirect" type="hidden" value="<%= viewUsersRedirect %>" />
</c:if>

<liferay-ui:search-container
	searchContainer="<%= new UserSearch(renderRequest, portletURL) %>"
	var="userSearchContainer"
>
	<aui:input disabled="<%= true %>" name="usersRedirect" type="hidden" value="<%= portletURL.toString() %>" />

	<%
	UserSearchTerms searchTerms = (UserSearchTerms)userSearchContainer.getSearchTerms();

	long organizationId = searchTerms.getOrganizationId();
	long userGroupId = searchTerms.getUserGroupId();

	Organization organization = null;

	if (organizationId > 0) {
		try {
			organization = OrganizationLocalServiceUtil.getOrganization(organizationId);
		}
		catch (NoSuchOrganizationException nsoe) {
		}
	}

	UserGroup userGroup = null;

	if (userGroupId > 0) {
		try {
			userGroup = UserGroupLocalServiceUtil.getUserGroup(userGroupId);
		}
		catch (NoSuchUserGroupException nsuge) {
		}
	}
	%>

	<c:if test="<%= organization != null %>">
		<aui:input name="<%= UserDisplayTerms.ORGANIZATION_ID %>" type="hidden" value="<%= organization.getOrganizationId() %>" />

		<h3>
			<liferay-ui:message arguments="<%= HtmlUtil.escape(organization.getName()) %>" key="users-of-x" translateArguments="<%= false %>" />
		</h3>
	</c:if>

	<c:if test="<%= userGroup != null %>">
		<aui:input name="<%= UserDisplayTerms.USER_GROUP_ID %>" type="hidden" value="<%= userGroup.getUserGroupId() %>" />

		<h3>
			<liferay-ui:message arguments="<%= HtmlUtil.escape(userGroup.getName()) %>" key="users-of-x" translateArguments="<%= false %>" />
		</h3>
	</c:if>

	<aui:nav-bar>
		<aui:nav-bar-search>
			<liferay-ui:user-search-form />
		</aui:nav-bar-search>
	</aui:nav-bar>

	<%
	LinkedHashMap<String, Object> userParams = new LinkedHashMap<String, Object>();

	if (organizationId > 0) {
		userParams.put("usersOrgs", Long.valueOf(organizationId));
	}

	if (userGroupId > 0) {
		userParams.put("usersUserGroups", Long.valueOf(userGroupId));
	}

	if (portletName.equals(PortletKeys.FRIENDS_DIRECTORY)) {
		userParams.put("socialRelationType", new Long[] {themeDisplay.getUserId(), Long.valueOf(SocialRelationConstants.TYPE_BI_FRIEND)});
	}
	else if (portletName.equals(PortletKeys.MY_SITES_DIRECTORY) && (organizationId == 0) && (userGroupId == 0)) {
		LinkedHashMap<String, Object> groupParams = new LinkedHashMap<String, Object>();

		groupParams.put("inherit", Boolean.FALSE);
		groupParams.put("site", Boolean.TRUE);
		groupParams.put("usersGroups", user.getUserId());

		userParams.put("inherit", Boolean.TRUE);

		List<Group> groups = GroupLocalServiceUtil.search(user.getCompanyId(), groupParams, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		userParams.put("usersGroups", SitesUtil.filterGroups(groups, PropsValues.MY_SITES_DIRECTORY_SITE_EXCLUDES));
	}
	else if (portletName.equals(PortletKeys.SITE_MEMBERS_DIRECTORY) && (organizationId == 0) && (userGroupId == 0)) {
		userParams.put("inherit", Boolean.TRUE);
		userParams.put("usersGroups", Long.valueOf(themeDisplay.getScopeGroupId()));
	}
	%>

	<liferay-ui:user-search-container-results
		indexerDisabled="<%= !portletName.equals(PortletKeys.DIRECTORY) %>"
		userParams="<%= userParams %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.model.User"
		escapedModel="<%= true %>"
		keyProperty="userId"
		modelVar="user2"
		rowIdProperty="screenName"
	>
		<liferay-portlet:renderURL varImpl="rowURL">
			<portlet:param name="mvcRenderCommandName" value="/directory/view_user" />
			<portlet:param name="tabs1" value="<%= HtmlUtil.escape(tabs1) %>" />
			<portlet:param name="redirect" value="<%= userSearchContainer.getIteratorURL().toString() %>" />
			<portlet:param name="p_u_i_d" value="<%= String.valueOf(user2.getUserId()) %>" />
		</liferay-portlet:renderURL>

		<%@ include file="/html/portlet/directory/user/search_columns.jspf" %>
	</liferay-ui:search-container-row>

	<c:if test="<%= (organization != null) || (userGroup != null) %>">
		<br />
	</c:if>

	<c:if test="<%= organization != null %>">
		<aui:input name="<%= UserDisplayTerms.ORGANIZATION_ID %>" type="hidden" value="<%= organization.getOrganizationId() %>" />

		<liferay-ui:message key="filter-by-organization" />: <%= HtmlUtil.escape(organization.getName()) %><br />
	</c:if>

	<c:if test="<%= userGroup != null %>">
		<aui:input name="<%= UserDisplayTerms.USER_GROUP_ID %>" type="hidden" value="<%= userGroup.getUserGroupId() %>" />

		<liferay-ui:message key="filter-by-user-group" />: <%= HtmlUtil.escape(userGroup.getName()) %><br />
	</c:if>

	<div class="separator"><!-- --></div>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>