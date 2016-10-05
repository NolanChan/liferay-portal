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
boolean hideUserLCSMessages = ParamUtil.getBoolean(request, "hideUserLCSMessages");
long hideUserLCSMessageId = ParamUtil.getLong(request, "hideUserLCSMessageId");
boolean showUserLCSMessages = ParamUtil.getBoolean(request, "showUserLCSMessages");

List<UserLCSMessage> userLCSMessages = new ArrayList<UserLCSMessage>();

if (hideUserLCSMessages) {
	UserLCSMessageServiceUtil.deleteUserLCSMessages();
}
else {
	if (hideUserLCSMessageId > 0) {
		UserLCSMessageServiceUtil.deleteUserLCSMessage(hideUserLCSMessageId);
	}

	userLCSMessages = UserLCSMessageServiceUtil.getUserLCSMessages(showUserLCSMessages ? Integer.MAX_VALUE : 5);
}
%>

<c:choose>
	<c:when test="<%= userLCSMessages.isEmpty() %>">
		<div class="note">
			<liferay-ui:message key="you-have-no-new-messages" />
		</div>
	</c:when>
	<c:otherwise>
		<div class="menu-header">
			<div class="options">
				<aui:a href="javascript:;" id="hideUserLCSMessages" label="mark-all-as-read" />
			</div>
		</div>

		<ul class="lcs-messages">

			<%
			for (UserLCSMessage userLCSMessage : userLCSMessages) {
				LCSMessage lcsMessage = userLCSMessage.getLcsMessage();
			%>

				<li class="lcs-message" data-userlcsmessageid="<%= userLCSMessage.getUserLcsMessageId() %>">
					<div class="header">
						<div class="from">
							<liferay-ui:message key="from" />:

							<c:choose>
								<c:when test="<%= Validator.equals(lcsMessage.getSourceSystemName(), LCSConstants.SOURCE_SYSTEM_NAME_LCS) %>">
									<liferay-ui:message key="liferay-connected-services" />
								</c:when>
								<c:otherwise>
									<liferay-ui:message key="liferay-support" />
								</c:otherwise>
							</c:choose>
						</div>

						<div class="options">
							<aui:a cssClass="icon-remove-circle lcs-message-hide" href="javascript:;" label="" title="mark-as-read" />
						</div>
					</div>

					<div class="body">

						<%
						LCSEventType lcsEventType = LCSEventType.valueOf(lcsMessage.getType());
						%>

						<c:choose>
							<c:when test="<%= lcsEventType == LCSEventType.MEMBERSHIP_INVITATION_ACCEPTED %>">

								<%
								Layout usersLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), true, NavigationConstants.FRIENDLY_URL_USERS);

								LCSProject lcsProject = LCSProjectServiceUtil.getLCSProject(lcsMessage.getClassPK());
								%>

								<liferay-portlet:renderURL plid="<%= usersLayout.getPlid() %>" portletName="<%= OSBLCSPortletKeys.MEMBERS %>" var="usersURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
									<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(lcsMessage.getClassPK()) %>" />
								</liferay-portlet:renderURL>

								<liferay-ui:message arguments="<%= new Object[] {HtmlUtil.escape(lcsMessage.getContent()), usersURL, HtmlUtil.escape(lcsProject.getName())} %>" key="<%= lcsEventType.getMessage() %>" translateArguments="<%= false %>" />
							</c:when>
							<c:when test="<%= lcsEventType == LCSEventType.OSB_SUBSCRIPTION_STATUS_RECEIVED %>">
								<div class="lcs-message-content">
									<%= HtmlUtil.escape(lcsMessage.getContent()) %>
								</div>

								<%
								Layout subscriptionsLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), true, NavigationConstants.FRIENDLY_URL_SUBSCRIPTIONS);
								%>

								<liferay-portlet:renderURL plid="<%= subscriptionsLayout.getPlid() %>" portletName="<%= OSBLCSPortletKeys.SUBSCRIPTIONS %>" var="subscriptionsURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
									<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(lcsMessage.getClassPK()) %>" />
								</liferay-portlet:renderURL>

								<div class="link">
									<aui:a href="<%= subscriptionsURL %>" label="see-subscriptions" />
								</div>
							</c:when>
							<c:when test="<%= lcsMessage.getClassNameId() == ClassNameLocalServiceUtil.getClassNameId(LCSProject.class) %>">

								<%
								Layout dashboardLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), true, NavigationConstants.FRIENDLY_URL_DASHBOARD);

								LCSProject lcsProject = LCSProjectServiceUtil.getLCSProject(lcsMessage.getClassPK());
								%>

								<liferay-portlet:renderURL plid="<%= dashboardLayout.getPlid() %>" portletName="<%= OSBLCSPortletKeys.NAVIGATION %>" var="dashboardURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
									<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(lcsMessage.getClassPK()) %>" />
								</liferay-portlet:renderURL>

								<liferay-ui:message arguments="<%= new Object[] {dashboardURL, HtmlUtil.escape(lcsProject.getName())} %>" key="<%= lcsEventType.getMessage() %>" translateArguments="<%= false %>" />
							</c:when>
							<c:when test="<%= lcsMessage.getClassNameId() == ClassNameLocalServiceUtil.getClassNameId(LCSClusterEntry.class) %>">

								<%
								Layout lcsClusterEntryLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), true, NavigationConstants.FRIENDLY_URL_LCS_CLUSTER_ENTRY);

								LCSClusterEntry lcsClusterEntry = LCSClusterEntryServiceUtil.getLCSClusterEntry(lcsMessage.getClassPK());
								%>

								<liferay-portlet:renderURL plid="<%= lcsClusterEntryLayout.getPlid() %>" portletName="<%= OSBLCSPortletKeys.ENVIRONMENT %>" var="lcsClusterEntryURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
									<portlet:param name="layoutLCSClusterEntryId" value="<%= String.valueOf(lcsClusterEntry.getLcsClusterEntryId()) %>" />
									<portlet:param name="layoutLCSClusterNodeId" value="0" />
									<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(lcsClusterEntry.getLcsProjectId()) %>" />
								</liferay-portlet:renderURL>

								<liferay-ui:message arguments="<%= new Object[] {lcsClusterEntryURL, HtmlUtil.escape(lcsClusterEntry.getName())} %>" key="<%= lcsEventType.getMessage() %>" translateArguments="<%= false %>" />
							</c:when>
							<c:otherwise>

								<%
								Layout lcsClusterNodeLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), true, NavigationConstants.FRIENDLY_URL_LCS_CLUSTER_NODE);

								LCSClusterNode lcsClusterNode = LCSClusterNodeServiceUtil.getLCSClusterNode(lcsMessage.getClassPK());

								LCSClusterEntry lcsClusterEntry = LCSClusterEntryServiceUtil.getLCSClusterEntry(lcsClusterNode.getLcsClusterEntryId());
								%>

								<liferay-portlet:renderURL plid="<%= lcsClusterNodeLayout.getPlid() %>" portletName="<%= OSBLCSPortletKeys.SERVER %>" var="lcsClusterNodeURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
									<portlet:param name="layoutLCSClusterEntryId" value="<%= String.valueOf(lcsClusterNode.getLcsClusterEntryId()) %>" />
									<portlet:param name="layoutLCSClusterNodeId" value="<%= String.valueOf(lcsMessage.getClassPK()) %>" />
									<portlet:param name="layoutLCSProjectId" value="<%= String.valueOf(lcsClusterEntry.getLcsProjectId()) %>" />
								</liferay-portlet:renderURL>

								<liferay-ui:message arguments="<%= new Object[] {lcsClusterNodeURL, HtmlUtil.escape(lcsClusterNode.getName())} %>" key="<%= lcsEventType.getMessage() %>" translateArguments="<%= false %>" />
							</c:otherwise>
						</c:choose>
					</div>

					<div class="footer">
						<div class="datetime">
							<%= mediumDateFormatDateTime.format(lcsMessage.getModifiedDate()) %>
						</div>
					</div>
				</li>

			<%
			}
			%>

		</ul>
	</c:otherwise>
</c:choose>

<div class="menu-footer">
	<div class="options">
		<c:if test="<%= UserLCSMessageServiceUtil.getUserLCSMessagesCount() > 5 %>">
			<aui:a href="javascript:;" id="showUserLCSMessages" label="show-all-unread" /> &middot;
		</c:if>

		<%
		Layout accountLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(themeDisplay.getScopeGroupId(), true, NavigationConstants.FRIENDLY_URL_ACCOUNT);
		%>

		<liferay-portlet:renderURL plid="<%= accountLayout.getPlid() %>" portletName="<%= OSBLCSPortletKeys.ACCOUNT %>" var="accountMessagesURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
			<portlet:param name="mvcPath" value="/account/view.jsp" />
			<portlet:param name="accountPage" value="messages" />
		</liferay-portlet:renderURL>

		<aui:a href="<%= accountMessagesURL %>" label="notifications-history" />
	</div>
</div>