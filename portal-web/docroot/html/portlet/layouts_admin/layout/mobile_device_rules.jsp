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

<%@ include file="/html/portlet/layouts_admin/init.jsp" %>

<%
Layout selLayout = layoutsAdminDisplayContext.getSelLayout();

long groupId = selLayout.getGroupId();
String className = Layout.class.getName();
long classPK = selLayout.getPlid();
%>

<%@ include file="/html/portlet/layouts_admin/layout/mobile_device_rules_header.jspf" %>

<%
PortletURL redirectURL = layoutsAdminDisplayContext.getRedirectURL();

int mdrRuleGroupInstancesCount = MDRRuleGroupInstanceServiceUtil.getRuleGroupInstancesCount(className, classPK);
%>

<liferay-util:buffer var="rootNodeNameLink">
	<c:choose>
		<c:when test="<%= themeDisplay.isStateExclusive() %>">
			<%= HtmlUtil.escape(layoutsAdminDisplayContext.getRootNodeName()) %>
		</c:when>
		<c:otherwise>
			<aui:a href='<%= redirectURL.toString() + "#tab=mobileDeviceRules" %>'><%= HtmlUtil.escape(layoutsAdminDisplayContext.getRootNodeName()) %></aui:a>
		</c:otherwise>
	</c:choose>
</liferay-util:buffer>

<aui:input checked="<%= mdrRuleGroupInstancesCount == 0 %>" disabled="<%= mdrRuleGroupInstancesCount > 0 %>" id="inheritRuleGroupInstances" label='<%= LanguageUtil.format(request, "use-the-same-mobile-device-rules-of-the-x", rootNodeNameLink, false) %>' name="inheritRuleGroupInstances" type="radio" value="<%= true %>" />

<aui:input checked="<%= mdrRuleGroupInstancesCount > 0 %>" id="uniqueRuleGroupInstances" label="define-specific-mobile-device-rules-for-this-page" name="inheritRuleGroupInstances" type="radio" value="<%= false %>" />

<div class="<%= (mdrRuleGroupInstancesCount == 0) ? StringPool.BLANK : "hide" %>" id="<portlet:namespace />inheritRuleGroupInstancesContainer">
	<div class="alert alert-info">
		<liferay-ui:message arguments="<%= rootNodeNameLink %>" key="mobile-device-rules-will-be-inhertited-from-x" translateArguments="<%= false %>" />
	</div>
</div>

<div class="<%= (mdrRuleGroupInstancesCount > 0) ? StringPool.BLANK : "hide" %>" id="<portlet:namespace />uniqueRuleGroupInstancesContainer">
	<liferay-util:include page="/html/portlet/layouts_admin/layout/mobile_device_rules_rule_group_instances.jsp">
		<liferay-util:param name="groupId" value="<%= String.valueOf(groupId) %>" />
		<liferay-util:param name="className" value="<%= className %>" />
		<liferay-util:param name="classPK" value="<%= String.valueOf(classPK) %>" />
	</liferay-util:include>
</div>

<aui:script>
	Liferay.Util.toggleRadio('<portlet:namespace />inheritRuleGroupInstances', '<portlet:namespace />inheritRuleGroupInstancesContainer', '<portlet:namespace />uniqueRuleGroupInstancesContainer');
	Liferay.Util.toggleRadio('<portlet:namespace />uniqueRuleGroupInstances', '<portlet:namespace />uniqueRuleGroupInstancesContainer', '<portlet:namespace />inheritRuleGroupInstancesContainer');
</aui:script>