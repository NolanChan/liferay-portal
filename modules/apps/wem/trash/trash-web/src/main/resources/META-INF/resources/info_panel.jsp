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
List<TrashEntry> entries = (List<TrashEntry>)request.getAttribute(TrashWebKeys.TRASH_ENTRIES);
%>

<c:choose>
	<c:when test="<%= ListUtil.isNotEmpty(entries) %>">
		<c:choose>
			<c:when test="<%= entries.size() == 1 %>">

				<%
				TrashEntry entry = entries.get(0);

				TrashHandler trashHandler = TrashHandlerRegistryUtil.getTrashHandler(entry.getClassName());

				TrashRenderer trashRenderer = trashHandler.getTrashRenderer(entry.getClassPK());
				%>

				<div class="sidebar-header">
					<ul class="list-inline list-unstyled sidebar-header-actions">
						<li>
							<c:choose>
								<c:when test="<%= Validator.isNotNull(trashRenderer.renderActions(renderRequest, renderResponse)) %>">
									<liferay-util:include page="<%= trashRenderer.renderActions(renderRequest, renderResponse) %>" servletContext="<%= application %>" />
								</c:when>
								<c:when test="<%= entry.getRootEntry() == null %>">

									<%
									request.setAttribute(WebKeys.TRASH_ENTRY, entry);
									%>

									<liferay-util:include page="/entry_action.jsp" servletContext="<%= application %>" />
								</c:when>
								<c:otherwise>

									<%
									request.setAttribute(WebKeys.TRASH_RENDERER, trashRenderer);
									%>

									<liferay-util:include page="/view_content_action.jsp" servletContext="<%= application %>" />
								</c:otherwise>
							</c:choose>
						</li>
					</ul>

					<h4><%= trashRenderer.getTitle(locale) %></h4>
				</div>

				<aui:nav-bar>
					<aui:nav cssClass="navbar-nav">
						<aui:nav-item label="details" selected="<%= true %>" />
					</aui:nav>
				</aui:nav-bar>

				<div class="sidebar-body">
					<h5><liferay-ui:message key="type" /></h5>

					<p>
						<%= ResourceActionsUtil.getModelResource(locale, entry.getClassName()) %>
					</p>

					<h5><liferay-ui:message key="removed-date" /></h5>

					<p>
						<%= dateFormatDateTime.format(entry.getCreateDate()) %>
					</p>

					<h5><liferay-ui:message key="removed-by" /></h5>

					<p>
						<%= HtmlUtil.escape(entry.getUserName()) %>
					</p>
				</div>
			</c:when>
			<c:otherwise>
				<div class="sidebar-header">
					<h4><liferay-ui:message arguments="<%= entries.size() %>" key="x-items-are-selected" /></h4>
				</div>

				<aui:nav-bar>
					<aui:nav cssClass="navbar-nav">
						<aui:nav-item label="details" selected="<%= true %>" />
					</aui:nav>
				</aui:nav-bar>

				<div class="sidebar-body">
					<h5><liferay-ui:message key="num-of-items" /></h5>

					<p>
						<%= entries.size() %>
					</p>
				</div>
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>

		<%
		long classPK = trashDisplayContext.getClassPK();

		TrashRenderer trashRenderer = trashDisplayContext.getTrashRenderer();

		TrashHandler trashHandler = trashDisplayContext.getTrashHandler();
		%>

		<c:choose>
			<c:when test="<%= trashRenderer != null %>">
				<div class="sidebar-header">
					<ul class="list-inline list-unstyled sidebar-header-actions">
						<li>
							<liferay-util:include page="/container_action.jsp" servletContext="<%= application %>" />
						</li>
					</ul>

					<h4><%= trashRenderer.getTitle(locale) %></h4>
				</div>

				<aui:nav-bar>
					<aui:nav cssClass="navbar-nav">
						<aui:nav-item label="details" selected="<%= true %>" />
					</aui:nav>
				</aui:nav-bar>

				<div class="sidebar-body">
					<h5><liferay-ui:message key="num-of-items" /></h5>

					<p>
						<%= trashHandler.getTrashModelsCount(classPK) %>
					</p>
				</div>
			</c:when>
			<c:otherwise>
				<div class="sidebar-header">
					<h4><%= LanguageUtil.get(request, "home") %></h4>
				</div>

				<aui:nav-bar>
					<aui:nav cssClass="navbar-nav">
						<aui:nav-item label="details" selected="<%= true %>" />
					</aui:nav>
				</aui:nav-bar>

				<div class="sidebar-body">
					<h5><liferay-ui:message key="num-of-items" /></h5>

					<p>
						<%= TrashEntryLocalServiceUtil.getEntriesCount(themeDisplay.getScopeGroupId()) %>
					</p>
				</div>
			</c:otherwise>
		</c:choose>
	</c:otherwise>
</c:choose>