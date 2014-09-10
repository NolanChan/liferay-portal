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

<%@ include file="/html/portlet/trash/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

TrashRenderer trashRenderer = null;

if (row != null) {
	trashRenderer = (TrashRenderer)row.getObject();
}
else {
	trashRenderer = (TrashRenderer)request.getAttribute(WebKeys.TRASH_RENDERER);
}

TrashHandler trashHandler = TrashHandlerRegistryUtil.getTrashHandler(trashRenderer.getClassName());
%>

<liferay-ui:icon-menu icon="<%= StringPool.BLANK %>" message="<%= StringPool.BLANK %>">
	<c:if test="<%= trashHandler.isMovable() %>">
		<portlet:renderURL var="moveURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="struts_action" value="/trash/view_container_model" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="className" value="<%= trashRenderer.getClassName() %>" />
			<portlet:param name="classPK" value="<%= String.valueOf(trashRenderer.getClassPK()) %>" />
			<portlet:param name="containerModelClassName" value="<%= trashHandler.getRootContainerModelClassName(trashRenderer.getClassPK()) %>" />
			<portlet:param name="containerModelId" value="<%= String.valueOf(trashHandler.getRootContainerModelId(trashRenderer.getClassPK())) %>" />
			<portlet:param name="rootContainerModelMovable" value="<%= String.valueOf(trashHandler.isRootContainerModelMovable()) %>" />
		</portlet:renderURL>

		<%
		Map<String, Object> data = new HashMap<String, Object>();

		data.put("uri", moveURL);
		%>

		<liferay-ui:icon
			cssClass="trash-restore-link"
			data="<%= data %>"
			iconCssClass="icon-undo"
			message="restore"
			url="javascript:;"
		/>
	</c:if>

	<c:if test="<%= trashHandler.isDeletable() %>">
		<portlet:actionURL var="deleteEntryURL">
			<portlet:param name="struts_action" value="/trash/edit_entry" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="className" value="<%= trashRenderer.getClassName() %>" />
			<portlet:param name="classPK" value="<%= String.valueOf(trashRenderer.getClassPK()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteEntryURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>