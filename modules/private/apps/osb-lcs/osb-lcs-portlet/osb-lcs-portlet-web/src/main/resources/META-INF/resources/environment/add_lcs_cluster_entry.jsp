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
long layoutLCSProjectId = ParamUtil.getLong(request, "layoutLCSProjectId", LCSProjectServiceUtil.getUserDefaultLCSProjectId());
%>

<c:if test="<%= LCSProjectPermission.contains(permissionChecker, layoutLCSProjectId, ActionKeys.ADD_LCS_CLUSTER_ENTRY) %>">
	<h2>
		<liferay-ui:message key="new-environment" />
	</h2>

	<portlet:actionURL name="addLCSClusterEntry" var="addLCSClusterEntryURL" />

	<aui:form action="<%= addLCSClusterEntryURL %>" method="post" name="fm">
		<liferay-ui:error exception="<%= DuplicateLCSClusterEntryNameException.class %>" message="please-enter-a-unique-environment-name" />
		<liferay-ui:error exception="<%= RequiredLCSClusterEntryNameException.class %>" message="environment-name-is-required" />

		<aui:input name="mvcPath" type="hidden" value="/environment/add_lcs_cluster_entry.jsp" />

		<aui:fieldset>
			<aui:input name="name" />

			<aui:input name="location" />

			<aui:input cssClass="description" name="description" type="textarea" />

			<aui:input helpMessage="check-this-option-to-make-this-environment-a-clustered-environment" name="cluster" type="checkbox" />

			<aui:button-row>
				<aui:button type="submit" value="create" />

				<aui:button cssClass="btn-link" onClick="javascript:history.go(-1);" value="back" />
			</aui:button-row>
		</aui:fieldset>
	</aui:form>
</c:if>