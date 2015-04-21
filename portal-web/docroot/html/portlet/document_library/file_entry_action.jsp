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

<%@ include file="/html/portlet/document_library/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

FileEntry fileEntry = null;
FileShortcut fileShortcut = null;

if (row != null) {
	Object result = row.getObject();

	if (result instanceof AssetEntry) {
		AssetEntry assetEntry = (AssetEntry)result;

		if (assetEntry.getClassName().equals(DLFileEntryConstants.getClassName())) {
			fileEntry = DLAppLocalServiceUtil.getFileEntry(assetEntry.getClassPK());
		}
		else {
			fileShortcut = DLAppLocalServiceUtil.getFileShortcut(assetEntry.getClassPK());
		}
	}
	else if (result instanceof FileEntry) {
		fileEntry = (FileEntry)result;
	}
	else if (result instanceof TrashEntry) {
		TrashEntry trashEntry = (TrashEntry)result;

		String className = trashEntry.getClassName();

		if (className.equals(DLFileEntryConstants.getClassName())) {
			fileEntry = DLAppLocalServiceUtil.getFileEntry(trashEntry.getClassPK());
		}
		else {
			fileShortcut = DLAppLocalServiceUtil.getFileShortcut(trashEntry.getClassPK());
		}
	}
	else if (result instanceof FileShortcut ||
				result instanceof FileShortcut) {

		if (result instanceof FileShortcut) {
			result = ((FileShortcut) result).getModel();
		}

		fileShortcut = (FileShortcut)result;
	}
}
else {
	if (portletName.equals(PortletKeys.DOCUMENT_LIBRARY_DISPLAY)) {
		if (request.getAttribute("search.jsp-fileEntry") != null) {
			fileEntry = (FileEntry)request.getAttribute("search.jsp-fileEntry");
		}
	}
	else {
		if (request.getAttribute("view_entries.jsp-fileEntry") != null) {
			fileEntry = (FileEntry)request.getAttribute("view_entries.jsp-fileEntry");

			if (request.getAttribute("view_entries.jsp-fileShortcut") != null) {
				fileShortcut = (FileShortcut)request.getAttribute("view_entries.jsp-fileShortcut");
			}
		}
	}
}

DLViewFileVersionDisplayContext dlViewFileVersionDisplayContext = null;

if (fileShortcut == null) {
	dlViewFileVersionDisplayContext = DLDisplayContextProviderUtil.getDLViewFileVersionDisplayContext(request, response, fileEntry.getFileVersion());
}
else {
	dlViewFileVersionDisplayContext = DLDisplayContextProviderUtil.getDLViewFileVersionDisplayContext(request, response, fileShortcut);
}

DLVisualizationHelper dlVisualizationHelper = new DLVisualizationHelper(dlRequestHelper);
%>

<c:choose>
	<c:when test="<%= portletName.equals(PortletKeys.DOCUMENT_LIBRARY_DISPLAY) && !dlVisualizationHelper.isShowMinimalActionsButton() %>">
		<liferay-ui:menu menu="<%= dlViewFileVersionDisplayContext.getMenu() %>" />
	</c:when>
	<c:otherwise>
		<span class="entry-action overlay">
			<liferay-ui:menu menu="<%= dlViewFileVersionDisplayContext.getMenu() %>" />
		</span>
	</c:otherwise>
</c:choose>