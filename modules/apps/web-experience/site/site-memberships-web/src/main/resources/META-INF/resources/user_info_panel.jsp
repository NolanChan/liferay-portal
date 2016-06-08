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
List<User> users = (List<User>)request.getAttribute(SiteMembershipWebKeys.USERS);

Group group = siteMembershipsDisplayContext.getGroup();
%>

<c:choose>
	<c:when test="<%= ListUtil.isEmpty(users) %>">
		<div class="sidebar-header">
			<h4>
				<liferay-ui:message key="membership-type" />: <liferay-ui:message key="<%= GroupConstants.getTypeLabel(group.getType()) %>" />

				<%
				int pendingRequests = 0;

				if (group.getType() == GroupConstants.TYPE_SITE_RESTRICTED) {
					pendingRequests = MembershipRequestLocalServiceUtil.searchCount(group.getGroupId(), MembershipRequestConstants.STATUS_PENDING);
				}
				%>

				<c:if test="<%= pendingRequests > 0 %>">
					<portlet:renderURL var="viewMembershipRequestsURL">
						<portlet:param name="mvcPath" value="/view_membership_requests.jsp" />
						<portlet:param name="groupId" value="<%= String.valueOf(group.getGroupId()) %>" />
					</portlet:renderURL>

					<aui:a cssClass="badge badge-primary badge-sm" href="<%= viewMembershipRequestsURL %>" label="<%= String.valueOf(pendingRequests) %>" title='<%= LanguageUtil.format(request, "there-are-x-membership-requests-pending", String.valueOf(pendingRequests), false) %>' />
				</c:if>
			</h4>

			<h6 class="text-muted">
				<liferay-ui:message key='<%= "membership-type-" + GroupConstants.getTypeLabel(group.getType()) + "-help" %>' />
			</h6>
		</div>

		<aui:nav-bar markupView="lexicon">
			<aui:nav cssClass="navbar-nav">
				<aui:nav-item label="details" selected="<%= true %>" />
			</aui:nav>
		</aui:nav-bar>

		<div class="sidebar-body">
			<h5><liferay-ui:message key="num-of-users" /></h5>

			<%
			LinkedHashMap<String, Object> userParams = new LinkedHashMap<String, Object>();

			userParams.put("inherit", Boolean.TRUE);
			userParams.put("usersGroups", Long.valueOf(siteMembershipsDisplayContext.getGroupId()));
			%>

			<p>
				<%= UserLocalServiceUtil.searchCount(company.getCompanyId(), StringPool.BLANK, WorkflowConstants.STATUS_APPROVED, userParams) %>
			</p>
		</div>
	</c:when>
	<c:when test="<%= ListUtil.isNotEmpty(users) && (users.size() == 1) %>">

		<%
		User curUser = users.get(0);
		%>

		<div class="sidebar-header">
			<h4>
				<%= curUser.getFullName() %>
			</h4>

			<h6>
				<%= curUser.getScreenName() %>
			</h6>
		</div>

		<aui:nav-bar markupView="lexicon">
			<aui:nav cssClass="navbar-nav">
				<aui:nav-item label="details" selected="<%= true %>" />
			</aui:nav>
		</aui:nav-bar>

		<div class="sidebar-body">

			<%
			String portraitURL = curUser.getPortraitURL(themeDisplay);
			%>

			<c:if test="<%= Validator.isNotNull(portraitURL) %>">
				<img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="thumbnail" />" class="crop-img img-rounded" src="<%= portraitURL %>" />
			</c:if>

			<h5><liferay-ui:message key="email" /></h5>

			<p>
				<%= curUser.getEmailAddress() %>
			</p>
		</div>
	</c:when>
	<c:when test="<%= ListUtil.isNotEmpty(users) && (users.size() > 1) %>">
		<div class="sidebar-header">
			<h4><liferay-ui:message arguments="<%= users.size() %>" key="x-items-are-selected" /></h4>
		</div>

		<aui:nav-bar>
			<aui:nav cssClass="navbar-nav">
				<aui:nav-item label="details" selected="<%= true %>" />
			</aui:nav>
		</aui:nav-bar>

		<div class="sidebar-body">
			<h5><liferay-ui:message arguments="<%= users.size() %>" key="x-items-are-selected" /></h5>
		</div>
	</c:when>
</c:choose>