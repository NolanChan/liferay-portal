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
String keywords = ParamUtil.getString(request, "keywords");

PortletURL portletURL = renderResponse.createRenderURL();

boolean approximate = false;

EntrySearch entrySearch = new EntrySearch(renderRequest, portletURL);

EntrySearchTerms searchTerms = (EntrySearchTerms)entrySearch.getSearchTerms();

List trashEntries = null;

if (Validator.isNotNull(searchTerms.getKeywords())) {
	Sort sort = SortFactoryUtil.getSort(TrashEntry.class, entrySearch.getOrderByCol(), entrySearch.getOrderByType());

	BaseModelSearchResult<TrashEntry> baseModelSearchResult = TrashEntryLocalServiceUtil.searchTrashEntries(company.getCompanyId(), themeDisplay.getScopeGroupId(), user.getUserId(), searchTerms.getKeywords(), entrySearch.getStart(), entrySearch.getEnd(), sort);

	entrySearch.setTotal(baseModelSearchResult.getLength());

	trashEntries = baseModelSearchResult.getBaseModels();
}
else {
	TrashEntryList trashEntryList = TrashEntryServiceUtil.getEntries(themeDisplay.getScopeGroupId(), entrySearch.getStart(), entrySearch.getEnd(), entrySearch.getOrderByComparator());

	entrySearch.setTotal(trashEntryList.getCount());

	trashEntries = TrashEntryImpl.toModels(trashEntryList.getArray());

	approximate = trashEntryList.isApproximate();
}

entrySearch.setResults(trashEntries);
entrySearch.setRowChecker(new EmptyOnClickRowChecker(renderResponse));

if ((entrySearch.getTotal() == 0) && Validator.isNotNull(searchTerms.getKeywords())) {
	entrySearch.setEmptyResultsMessage(LanguageUtil.format(request, "no-entries-were-found-that-matched-the-keywords-x", "<strong>" + HtmlUtil.escape(searchTerms.getKeywords()) + "</strong>", false));
}

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "recycle-bin"), portletURL.toString());

if (Validator.isNotNull(keywords)) {
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "search") + ": " + keywords, currentURL);
}

request.setAttribute("view.jsp-recycleBinEntrySearch", entrySearch);
%>

<liferay-util:include page="/navigation.jsp" servletContext="<%= application %>" />

<liferay-util:include page="/toolbar.jsp" servletContext="<%= application %>" />

<liferay-util:include page="/restore_path.jsp" servletContext="<%= application %>" />

<liferay-ui:error exception="<%= RestoreEntryException.class %>">

	<%
	RestoreEntryException ree = (RestoreEntryException)errorException;
	%>

	<c:if test="<%= ree.getType() == RestoreEntryException.DUPLICATE %>">
		<liferay-ui:message key="unable-to-move-this-item-to-the-selected-destination" />
	</c:if>

	<c:if test="<%= ree.getType() == RestoreEntryException.INVALID_CONTAINER %>">
		<liferay-ui:message key="the-destination-you-selected-is-an-invalid-container.-please-select-a-different-destination" />
	</c:if>
</liferay-ui:error>

<liferay-ui:error exception="<%= TrashPermissionException.class %>">

	<%
	TrashPermissionException tpe = (TrashPermissionException)errorException;
	%>

	<c:if test="<%= tpe.getType() == TrashPermissionException.DELETE %>">
		<liferay-ui:message key="you-do-not-have-permission-to-delete-this-item" />
	</c:if>

	<c:if test="<%= tpe.getType() == TrashPermissionException.EMPTY_TRASH %>">
		<liferay-ui:message key="unable-to-completely-empty-trash-you-do-not-have-permission-to-delete-one-or-more-items" />
	</c:if>

	<c:if test="<%= tpe.getType() == TrashPermissionException.MOVE %>">
		<liferay-ui:message key="you-do-not-have-permission-to-move-this-item-to-the-selected-destination" />
	</c:if>

	<c:if test="<%= tpe.getType() == TrashPermissionException.RESTORE %>">
		<liferay-ui:message key="you-do-not-have-permission-to-restore-this-item" />
	</c:if>

	<c:if test="<%= tpe.getType() == TrashPermissionException.RESTORE_OVERWRITE %>">
		<liferay-ui:message key="you-do-not-have-permission-to-replace-an-existing-item-with-the-selected-one" />
	</c:if>

	<c:if test="<%= tpe.getType() == TrashPermissionException.RESTORE_RENAME %>">
		<liferay-ui:message key="you-do-not-have-permission-to-rename-this-item" />
	</c:if>
</liferay-ui:error>

<portlet:actionURL name="deleteEntries" var="deleteEntriesURL">
	<portlet:param name="redirect" value="<%= currentURL %>" />
</portlet:actionURL>

<div class="closed container-fluid-1280 sidenav-container sidenav-right" id="<portlet:namespace />infoPanelId">
	<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/trash/info_panel" var="sidebarPanelURL" />

	<liferay-frontend:sidebar-panel
		resourceURL="<%= sidebarPanelURL %>"
		searchContainerId="trash"
	>
		<liferay-util:include page="/info_panel.jsp" servletContext="<%= application %>" />
	</liferay-frontend:sidebar-panel>

	<div class="sidenav-content">
		<liferay-ui:breadcrumb
			showCurrentGroup="<%= false %>"
			showGuestGroup="<%= false %>"
			showLayout="<%= false %>"
			showParentGroups="<%= false %>"
		/>

		<aui:form action="<%= deleteEntriesURL %>" name="fm">
			<liferay-ui:search-container
				id="trash"
				rowChecker="<%= new EmptyOnClickRowChecker(renderResponse) %>"
				searchContainer="<%= entrySearch %>"
			>
				<liferay-ui:search-container-row
					className="com.liferay.trash.kernel.model.TrashEntry"
					keyProperty="entryId"
					modelVar="trashEntry"
					rowVar="row"
				>

					<%
					TrashHandler trashHandler = TrashHandlerRegistryUtil.getTrashHandler(trashEntry.getClassName());

					TrashRenderer trashRenderer = trashHandler.getTrashRenderer(trashEntry.getClassPK());

					String viewContentURLString = null;

					if (trashRenderer != null) {
						PortletURL viewContentURL = renderResponse.createRenderURL();

						if (!trashHandler.isContainerModel()) {
							viewContentURL.setWindowState(LiferayWindowState.POP_UP);

							viewContentURL.setParameter("mvcPath", "/preview.jsp");
						}
						else {
							viewContentURL.setParameter("mvcPath", "/view_content.jsp");
						}

						if (trashEntry.getRootEntry() != null) {
							viewContentURL.setParameter("classNameId", String.valueOf(trashEntry.getClassNameId()));
							viewContentURL.setParameter("classPK", String.valueOf(trashEntry.getClassPK()));
						}
						else {
							viewContentURL.setParameter("trashEntryId", String.valueOf(trashEntry.getEntryId()));
						}

						viewContentURLString = viewContentURL.toString();
					}
					%>

					<liferay-ui:search-container-column-text
						cssClass="content-column name-column title-column"
						name="name"
						truncate="<%= true %>"
					>
						<c:choose>
							<c:when test="<%= !trashHandler.isContainerModel() %>">

								<%
								Map<String, Object> data = new HashMap<String, Object>();

								data.put("title", HtmlUtil.escape(trashRenderer.getTitle(locale)));
								data.put("url", viewContentURLString);
								%>

								<aui:a cssClass="preview" data="<%= data %>" href="javascript:;">
									<%= HtmlUtil.escape(trashRenderer.getTitle(locale)) %>
								</aui:a>
							</c:when>
							<c:otherwise>
								<aui:a href="<%= viewContentURLString %>">
									<%= HtmlUtil.escape(trashRenderer.getTitle(locale)) %>
								</aui:a>
							</c:otherwise>
						</c:choose>

						<c:if test="<%= trashEntry.getRootEntry() != null %>">

							<%
							TrashEntry rootEntry = trashEntry.getRootEntry();

							TrashHandler rootTrashHandler = TrashHandlerRegistryUtil.getTrashHandler(rootEntry.getClassName());

							TrashRenderer rootTrashRenderer = rootTrashHandler.getTrashRenderer(rootEntry.getClassPK());

							String viewRootContentURLString = null;

							if (rootTrashRenderer != null) {
								PortletURL viewContentURL = renderResponse.createRenderURL();

								viewContentURL.setParameter("mvcPath", "/view_content.jsp");
								viewContentURL.setParameter("trashEntryId", String.valueOf(rootEntry.getEntryId()));

								viewRootContentURLString = viewContentURL.toString();
							}
							%>

							<liferay-util:buffer var="rootEntryIcon">
								<liferay-ui:icon
									label="<%= true %>"
									message="<%= HtmlUtil.escape(rootTrashRenderer.getTitle(locale)) %>"
									method="get"
									url="<%= viewRootContentURLString %>"
								/>
							</liferay-util:buffer>

							<span class="trash-root-entry">(<liferay-ui:message arguments="<%= rootEntryIcon %>" key="<%= rootTrashHandler.getDeleteMessage() %>" translateArguments="<%= false %>" />)</span>
						</c:if>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						cssClass="text-column type-column"
						name="type"
						value="<%= ResourceActionsUtil.getModelResource(locale, trashEntry.getClassName()) %>"
					/>

					<liferay-ui:search-container-column-date
						cssClass="removed-date-column text-column"
						name="removed-date"
						value="<%= trashEntry.getCreateDate() %>"
					/>

					<liferay-ui:search-container-column-text
						cssClass="removed-by-column text-column"
						name="removed-by"
						value="<%= HtmlUtil.escape(trashEntry.getUserName()) %>"
					/>

					<c:choose>
						<c:when test="<%= Validator.isNotNull(trashRenderer.renderActions(renderRequest, renderResponse)) %>">
							<liferay-ui:search-container-column-jsp
								cssClass="entry-action-column"
								path="<%= trashRenderer.renderActions(renderRequest, renderResponse) %>"
							/>
						</c:when>
						<c:when test="<%= trashEntry.getRootEntry() == null %>">
							<liferay-ui:search-container-column-jsp
								cssClass="entry-action-column"
								path="/entry_action.jsp"
							/>
						</c:when>
						<c:otherwise>

							<%
							request.setAttribute(TrashWebKeys.TRASH_RENDERER, trashRenderer);
							%>

							<liferay-ui:search-container-column-jsp
								cssClass="entry-action-column"
								path="/view_content_action.jsp"
							/>
						</c:otherwise>
					</c:choose>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator markupView="lexicon" type='<%= approximate ? "more" : "regular" %>' />
			</liferay-ui:search-container>
		</aui:form>
	</div>
</div>

<aui:script use="liferay-url-preview">
	A.one('#<portlet:namespace />fm').delegate(
		'click',
		function(event) {
			var currentTarget = event.currentTarget;

			var urlPreview = new Liferay.UrlPreview(
				{
					title: currentTarget.attr('data-title'),
					url: currentTarget.attr('data-url')
				}
			);

			urlPreview.open();
		},
		'.preview'
	);
</aui:script>