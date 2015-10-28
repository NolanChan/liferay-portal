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
String viewUserGroupsRedirect = ParamUtil.getString(request, "viewUserGroupsRedirect");
String backURL = ParamUtil.getString(request, "backURL", viewUserGroupsRedirect);

PortletURL portletURL = renderResponse.createRenderURL();

if (Validator.isNotNull(viewUserGroupsRedirect)) {
	portletURL.setParameter("viewUserGroupsRedirect", viewUserGroupsRedirect);
}

pageContext.setAttribute("portletURL", portletURL);

String portletURLString = portletURL.toString();
%>

<liferay-ui:error exception="<%= RequiredUserGroupException.class %>" message="you-cannot-delete-user-groups-that-have-users" />

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item cssClass="active" label="user-groups" />
	</aui:nav>

	<aui:nav-bar-search>
		<aui:form action="<%= portletURLString %>" name="searchFm">
			<liferay-ui:input-search markupView="lexicon" />
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>

<aui:form action="<%= portletURLString %>" method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="portletURL" />
	<aui:input name="redirect" type="hidden" value="<%= portletURLString %>" />
	<aui:input name="deleteUserGroupIds" type="hidden" />

	<%@ include file="/view_flat_user_groups.jspf" %>

</aui:form>

<aui:script>
	Liferay.Util.toggleSearchContainerButton('#<portlet:namespace />delete', '#<portlet:namespace /><%= searchContainerReference.getId() %>SearchContainer', document.<portlet:namespace />fm, '<portlet:namespace />allRowIds');

	function <portlet:namespace />deleteUserGroup(userGroupId) {
		<portlet:namespace />doDeleteUserGroup('<%= UserGroup.class.getName() %>', userGroupId);
	}

	function <portlet:namespace />deleteUserGroups() {
		<portlet:namespace />doDeleteUserGroup(
			'<%= UserGroup.class.getName() %>',
			Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, '<portlet:namespace />allRowIds')
		);
	}

	function <portlet:namespace />doDeleteUserGroup(className, ids) {
		var status = <%= WorkflowConstants.STATUS_INACTIVE %>;

		<portlet:namespace />getUsersCount(
			className,
			ids,
			status,
			function(responseData) {
				var count = parseInt(responseData, 10);

				if (count > 0) {
					status = <%= WorkflowConstants.STATUS_APPROVED %>;

					<portlet:namespace />getUsersCount(
						className,
						ids,
						status,
						function(responseData) {
							count = parseInt(responseData, 10);

							if (count > 0) {
								if (confirm('<%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-delete-this") %>')) {
									<portlet:namespace />doDeleteUserGroups(ids);
								}
							}
							else {
								var message;

								if (ids && (ids.toString().split(',').length > 1)) {
									message = '<%= UnicodeLanguageUtil.get(request, "one-or-more-user-groups-are-associated-with-deactivated-users.-do-you-want-to-proceed-with-deleting-the-selected-user-groups-by-automatically-unassociating-the-deactivated-users") %>';
								}
								else {
									message = '<%= UnicodeLanguageUtil.get(request, "the-selected-user-group-is-associated-with-deactivated-users.-do-you-want-to-proceed-with-deleting-the-selected-user-group-by-automatically-unassociating-the-deactivated-users") %>';
								}

								if (confirm(message)) {
									<portlet:namespace />doDeleteUserGroups(ids);
								}
							}
						}
					);
				}
				else if (confirm('<%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-delete-this") %>')) {
					<portlet:namespace />doDeleteUserGroups(ids);
				}
			}
		);
	}

	function <portlet:namespace />doDeleteUserGroups(userGroupIds) {
		var form = AUI.$(document.<portlet:namespace />fm);

		form.attr('method', 'post');
		form.fm('deleteUserGroupIds').val(userGroupIds);

		submitForm(form, '<portlet:actionURL name="deleteUserGroups" />');
	}

	function <portlet:namespace />getUsersCount(className, ids, status, callback) {
		AUI.$.ajax(
			'<%= themeDisplay.getPathMain() %>/user_groups_admin/get_users_count',
			{
				data: {
					className: className,
					ids: ids,
					status: status
				},
				success: callback
			}
		);
	}
</aui:script>