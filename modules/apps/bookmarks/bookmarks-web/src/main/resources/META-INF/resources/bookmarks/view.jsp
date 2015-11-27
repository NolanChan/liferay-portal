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

<%@ include file="/bookmarks/init.jsp" %>

<%
String navigation = ParamUtil.getString(request, "navigation", "home");

BookmarksFolder folder = (BookmarksFolder)request.getAttribute(BookmarksWebKeys.BOOKMARKS_FOLDER);

long folderId = BeanParamUtil.getLong(folder, request, "folderId", rootFolderId);

boolean defaultFolderView = false;

if ((folder == null) && (folderId != BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID)) {
	defaultFolderView = true;
}

if (defaultFolderView) {
	try {
		folder = BookmarksFolderServiceUtil.getFolder(folderId);
	}
	catch (NoSuchFolderException nsfe) {
		folderId = BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID;
	}
}

int foldersCount = BookmarksFolderServiceUtil.getFoldersCount(scopeGroupId, folderId);
int entriesCount = BookmarksEntryServiceUtil.getEntriesCount(scopeGroupId, folderId);

long assetCategoryId = ParamUtil.getLong(request, "categoryId");
String assetTagName = ParamUtil.getString(request, "tag");

boolean useAssetEntryQuery = (assetCategoryId > 0) || Validator.isNotNull(assetTagName);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "/bookmarks/view");
portletURL.setParameter("navigation", navigation);
portletURL.setParameter("folderId", String.valueOf(folderId));

request.setAttribute("view.jsp-folder", folder);

request.setAttribute("view.jsp-folderId", String.valueOf(folderId));

request.setAttribute("view.jsp-viewFolder", Boolean.TRUE.toString());

request.setAttribute("view.jsp-useAssetEntryQuery", String.valueOf(useAssetEntryQuery));

if (folder != null) {
	BookmarksUtil.addPortletBreadcrumbEntries(folder, request, renderResponse);
}
%>

<portlet:actionURL name="/bookmarks/edit_entry" var="restoreTrashEntriesURL">
	<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.RESTORE %>" />
</portlet:actionURL>

<liferay-ui:trash-undo
	portletURL="<%= restoreTrashEntriesURL %>"
/>

<liferay-util:include page="/bookmarks/navigation.jsp" servletContext="<%= application %>" />

<div <%= portletName.equals(BookmarksPortletKeys.BOOKMARKS_ADMIN) ? "class=\"container-fluid-1280\"" : StringPool.BLANK %>>
	<c:choose>
		<c:when test="<%= useAssetEntryQuery %>">
			<liferay-ui:categorization-filter
				assetType="bookmarks"
				portletURL="<%= portletURL %>"
			/>

			<%@ include file="/bookmarks/view_entries.jspf" %>

		</c:when>
		<c:when test='<%= navigation.equals("home") %>'>
			<c:if test="<%= folder != null %>">
				<liferay-ui:header
					localizeTitle="<%= false %>"
					title="<%= folder.getName() %>"
				/>
			</c:if>

			<aui:row>
				<aui:col cssClass="lfr-asset-column lfr-asset-column-details" width="<%= 75 %>">
					<liferay-ui:panel-container extended="<%= false %>" id="bookmarksInfoPanelContainer" persistState="<%= true %>">
						<c:if test="<%= folder != null %>">
							<div class="lfr-asset-description">
								<%= HtmlUtil.escape(folder.getDescription()) %>
							</div>

							<div class="lfr-asset-metadata">
								<div class="icon-calendar lfr-asset-icon">
									<liferay-ui:message arguments="<%= dateFormatDate.format(folder.getModifiedDate()) %>" key="last-updated-x" translateArguments="<%= false %>" />
								</div>

								<%
								AssetRendererFactory<BookmarksEntry> bookmarksEntryAssetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClass(BookmarksEntry.class);

								AssetRendererFactory<BookmarksFolder> bookmarksFolderAssetRendererFactory = AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClass(BookmarksFolder.class);
								%>

								<div class="<%= bookmarksFolderAssetRendererFactory.getIconCssClass() %> lfr-asset-icon">
									<%= foldersCount %> <liferay-ui:message key='<%= (foldersCount == 1) ? "subfolder" : "subfolders" %>' />
								</div>

								<div class="<%= bookmarksEntryAssetRendererFactory.getIconCssClass() %> last lfr-asset-icon">
									<%= entriesCount %> <liferay-ui:message key='<%= (entriesCount == 1) ? "entry" : "entries" %>' />
								</div>
							</div>

							<liferay-ui:custom-attributes-available className="<%= BookmarksFolder.class.getName() %>">
								<liferay-ui:custom-attribute-list
									className="<%= BookmarksFolder.class.getName() %>"
									classPK="<%= (folder != null) ? folder.getFolderId() : 0 %>"
									editable="<%= false %>"
									label="<%= true %>"
								/>
							</liferay-ui:custom-attributes-available>
						</c:if>

						<c:if test="<%= foldersCount > 0 %>">
							<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="bookmarksEntriesFoldersListingPanel" persistState="<%= true %>" title='<%= (folder != null) ? "subfolders" : "folders" %>'>
								<liferay-ui:search-container
									curParam="cur1"
									delta="<%= GetterUtil.getInteger(bookmarksGroupServiceOverriddenConfiguration.foldersPerPage()) %>"
									deltaConfigurable="<%= false %>"
									headerNames="<%= StringUtil.merge(folderColumns) %>"
									iteratorURL="<%= portletURL %>"
									total="<%= BookmarksFolderServiceUtil.getFoldersCount(scopeGroupId, folderId) %>"
								>
									<liferay-ui:search-container-results
										results="<%= BookmarksFolderServiceUtil.getFolders(scopeGroupId, folderId, searchContainer.getStart(), searchContainer.getEnd()) %>"
									/>

									<liferay-ui:search-container-row
										className="com.liferay.bookmarks.model.BookmarksFolder"
										escapedModel="<%= true %>"
										keyProperty="folderId"
										modelVar="curFolder"
									>
										<liferay-portlet:renderURL varImpl="rowURL">
											<portlet:param name="mvcRenderCommandName" value="/bookmarks/view" />
											<portlet:param name="folderId" value="<%= String.valueOf(curFolder.getFolderId()) %>" />
											<portlet:param name="redirect" value="<%= currentURL %>" />
										</liferay-portlet:renderURL>

										<%@ include file="/bookmarks/folder_columns.jspf" %>
									</liferay-ui:search-container-row>

									<liferay-ui:search-iterator />
								</liferay-ui:search-container>
							</liferay-ui:panel>
						</c:if>

						<liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>" id="bookmarksEntriesListingPanel" persistState="<%= true %>" title="bookmarks">
							<%@ include file="/bookmarks/view_entries.jspf" %>
						</liferay-ui:panel>
					</liferay-ui:panel-container>
				</aui:col>

				<aui:col cssClass="lfr-asset-column lfr-asset-column-actions" last="<%= true %>" width="<%= 25 %>">
					<div class="lfr-asset-summary">
						<liferay-ui:icon
							cssClass="lfr-asset-avatar"
							image='<%= "../file_system/large/" + (((foldersCount + entriesCount) > 0) ? "folder_full_bookmark" : "folder_empty_bookmark") %>'
							message='<%= (folder != null) ? HtmlUtil.escapeAttribute(folder.getName()) : "home" %>'
						/>

						<div class="lfr-asset-name">
							<h4><%= (folder != null) ? HtmlUtil.escape(folder.getName()) : LanguageUtil.get(request, "home") %></h4>
						</div>
					</div>

					<%
					request.removeAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
					%>

					<liferay-util:include page="/bookmarks/folder_action.jsp" servletContext="<%= application %>" />
				</aui:col>
			</aui:row>

			<%
			if (!defaultFolderView && (folder != null) && (portletName.equals(BookmarksPortletKeys.BOOKMARKS) || portletName.equals(BookmarksPortletKeys.BOOKMARKS_ADMIN))) {
				PortalUtil.setPageSubtitle(folder.getName(), request);
				PortalUtil.setPageDescription(folder.getDescription(), request);
			}
			%>

		</c:when>
		<c:when test='<%= navigation.equals("mine") || navigation.equals("recent") %>'>
			<liferay-ui:header
				title="<%= navigation %>"
			/>

			<%
			long groupEntriesUserId = 0;

			if (navigation.equals("mine") && themeDisplay.isSignedIn()) {
				groupEntriesUserId = user.getUserId();
			}
			%>

			<liferay-ui:search-container
				delta="<%= GetterUtil.getInteger(bookmarksGroupServiceOverriddenConfiguration.entriesPerPage()) %>"
				deltaConfigurable="<%= false %>"
				emptyResultsMessage="there-are-no-entries"
				iteratorURL="<%= portletURL %>"
				total="<%= BookmarksEntryServiceUtil.getGroupEntriesCount(scopeGroupId, groupEntriesUserId) %>"
			>
				<liferay-ui:search-container-results
					results="<%= BookmarksEntryServiceUtil.getGroupEntries(scopeGroupId, groupEntriesUserId, searchContainer.getStart(), searchContainer.getEnd()) %>"
				/>

				<liferay-ui:search-container-row
					className="com.liferay.bookmarks.model.BookmarksEntry"
					escapedModel="<%= true %>"
					keyProperty="entryId"
					modelVar="entry"
				>

					<%
					String rowHREF = null;

					if (BookmarksEntryPermissionChecker.contains(permissionChecker, entry, ActionKeys.VIEW)) {
						PortletURL tempRowURL = renderResponse.createRenderURL();

						tempRowURL.setParameter("mvcRenderCommandName", "/bookmarks/view_entry");
						tempRowURL.setParameter("redirect", currentURL);
						tempRowURL.setParameter("entryId", String.valueOf(entry.getEntryId()));

						rowHREF = tempRowURL.toString();
					}
					%>

					<%@ include file="/bookmarks/entry_columns.jspf" %>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator />
			</liferay-ui:search-container>

			<%
			if (!layout.isTypeControlPanel()) {
				PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, navigation), currentURL);
			}

			PortalUtil.setPageSubtitle(LanguageUtil.get(request, StringUtil.replace(navigation, StringPool.UNDERLINE, StringPool.DASH)), request);
			%>

		</c:when>
	</c:choose>
</div>

<c:if test="<%= portletName.equals(BookmarksPortletKeys.BOOKMARKS_ADMIN) %>">
	<liferay-util:include page="/bookmarks/add_button.jsp" servletContext="<%= application %>" />
</c:if>