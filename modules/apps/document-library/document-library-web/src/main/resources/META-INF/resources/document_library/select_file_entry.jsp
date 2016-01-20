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

<%@ include file="/document_library/init.jsp" %>

<%
Folder folder = (Folder)request.getAttribute(WebKeys.DOCUMENT_LIBRARY_FOLDER);

long folderId = BeanParamUtil.getLong(folder, request, "folderId", DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);

long groupId = BeanParamUtil.getLong(folder, request, "groupId");
String eventName = ParamUtil.getString(request, "eventName", liferayPortletResponse.getNamespace() + "selectFileEntry_" + groupId);

if (folder != null) {
	DLBreadcrumbUtil.addPortletBreadcrumbEntries(folder, request, renderResponse);
}
%>

<div class="container-fluid-1280">
	<aui:form method="post" name="selectFileEntryFm">
		<liferay-ui:breadcrumb showGuestGroup="<%= false %>" showLayout="<%= false %>" showParentGroups="<%= false %>" />

		<%
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("mvcRenderCommandName", "/document_library/select_file_entry");
		portletURL.setParameter("groupId", String.valueOf(groupId));
		portletURL.setParameter("folderId", String.valueOf(folderId));
		%>

		<liferay-ui:search-container
			iteratorURL="<%= portletURL %>"
			total="<%= DLAppServiceUtil.getFoldersCount(groupId, folderId) %>"
		>
			<liferay-ui:search-container-results
				results="<%= DLAppServiceUtil.getFolders(groupId, folderId, searchContainer.getStart(), searchContainer.getEnd()) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.portal.kernel.repository.model.Folder"
				keyProperty="folderId"
				modelVar="curFolder"
				rowVar="row"
			>
				<liferay-portlet:renderURL varImpl="rowURL">
					<portlet:param name="mvcRenderCommandName" value="/document_library/select_file_entry" />
					<portlet:param name="groupId" value="<%= String.valueOf(groupId) %>" />
					<portlet:param name="folderId" value="<%= String.valueOf(curFolder.getFolderId()) %>" />
				</liferay-portlet:renderURL>

				<%
				try{
					DLAppServiceUtil.getFoldersFileEntriesCount(curFolder.getRepositoryId(), Arrays.asList(curFolder.getFolderId()), WorkflowConstants.STATUS_APPROVED);
					DLAppServiceUtil.getFoldersCount(curFolder.getRepositoryId(), curFolder.getFolderId());
				}
				catch (com.liferay.portal.kernel.repository.RepositoryException re) {
					rowURL = null;
				}
				catch (com.liferay.portal.security.auth.PrincipalException pe) {
					rowURL = null;
				}
				%>

				<liferay-ui:search-container-column-text
					name="folder"
				>
					<aui:a href="<%= rowURL.toString() %>">
						<%= HtmlUtil.escape(curFolder.getName()) %>
					</aui:a>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator markupView="lexicon" />
		</liferay-ui:search-container>

		<br />

		<liferay-ui:search-container
			iteratorURL="<%= portletURL %>"
			total="<%= DLAppServiceUtil.getFileEntriesCount(groupId, folderId) %>"
		>
			<liferay-ui:search-container-results
				results="<%= DLAppServiceUtil.getFileEntries(groupId, folderId, searchContainer.getStart(), searchContainer.getEnd()) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.portal.kernel.repository.model.FileEntry"
				keyProperty="fileEntryId"
				modelVar="curFile"
				rowVar="row"
			>
				<liferay-ui:search-container-column-text
					name="document"
				>

					<%
					Map<String, Object> data = new HashMap<String, Object>();

					data.put("entryid", curFile.getFileEntryId());
					data.put("entryname", curFile.getTitle());
					%>

					<aui:a cssClass="selector-button" data="<%= data %>" href="javascript:;">
						<%= HtmlUtil.escape(curFile.getTitle()) %>
					</aui:a>

					<c:if test="<%= Validator.isNotNull(curFile.getDescription()) %>">
						<br />
						<%= HtmlUtil.escape(curFile.getDescription()) %>
					</c:if>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator markupView="lexicon" />
		</liferay-ui:search-container>
	</aui:form>
</div>

<aui:script>
	Liferay.Util.selectEntityHandler('#<portlet:namespace />selectFileEntryFm', '<%= HtmlUtil.escapeJS(eventName) %>');
</aui:script>