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

<%@ include file="/wiki/init.jsp" %>

<%
WikiPageInfoPanelDisplayContext wikiPageInfoPanelDisplayContext = wikiDisplayContextProvider.getWikiPageInfoPanelDisplayContext(request, response);

WikiPage wikiPage = wikiPageInfoPanelDisplayContext.getFirstPage();

List<WikiPage> incomingLinkPages = WikiPageLocalServiceUtil.getIncomingLinks(wikiPage.getNodeId(), wikiPage.getTitle());
List<WikiPage> outgoingLinkPages = WikiPageLocalServiceUtil.getOutgoingLinks(wikiPage.getNodeId(), wikiPage.getTitle());

boolean hasIncomingLinkPages = (incomingLinkPages != null) && !incomingLinkPages.isEmpty();
boolean hasOutgoingLinkPages = (outgoingLinkPages != null) && !outgoingLinkPages.isEmpty();
%>

<div>
	<c:choose>
		<c:when test="<%= hasIncomingLinkPages || hasOutgoingLinkPages %>">
			<liferay-ui:panel collapsible="<%= true %>" extended="<%= hasIncomingLinkPages %>" markupView="lexicon" title="incoming-links">
				<c:choose>
					<c:when test="<%= hasIncomingLinkPages %>">

						<%
						for (WikiPage incomingLinkPage : incomingLinkPages) {
							PortletURL portletURL = liferayPortletResponse.createRenderURL();

							portletURL.setParameter("mvcRenderCommandName", "/wiki/view");
							portletURL.setParameter("redirect", currentURL);
							portletURL.setParameter("nodeName", incomingLinkPage.getNode().getName());
							portletURL.setParameter("title", incomingLinkPage.getTitle());
						%>

							<h4>
								<a class="text-default" href="<%= portletURL.toString() %>"><%= incomingLinkPage.getTitle() %></a>
							</h4>

							<small>
								<aui:workflow-status markupView="lexicon" showLabel="<%= false %>" status="<%= incomingLinkPage.getStatus() %>" />
							</small>

						<%
						}
						%>

					</c:when>
					<c:otherwise>
						<div class="alert alert-info">
							<liferay-ui:message key="there-are-no-pages-that-link-to-this-page" />
						</div>
					</c:otherwise>
				</c:choose>
			</liferay-ui:panel>

			<liferay-ui:panel collapsible="<%= true %>" extended="<%= hasOutgoingLinkPages %>" markupView="lexicon" title="outgoing-links">
				<c:choose>
					<c:when test="<%= hasOutgoingLinkPages %>">

						<%
						for (WikiPage outgoingLinkPage : outgoingLinkPages) {
							PortletURL portletURL = liferayPortletResponse.createRenderURL();

							portletURL.setParameter("mvcRenderCommandName", "/wiki/view");
							portletURL.setParameter("redirect", currentURL);
							portletURL.setParameter("nodeName", outgoingLinkPage.getNode().getName());
							portletURL.setParameter("title", outgoingLinkPage.getTitle());
						%>

							<h4>
								<a class="text-default" href="<%= portletURL.toString() %>"><%= outgoingLinkPage.getTitle() %></a>
							</h4>

							<small>
								<aui:workflow-status markupView="lexicon" showLabel="<%= false %>" status="<%= outgoingLinkPage.getStatus() %>" />
							</small>

						<%
						}
						%>

					</c:when>
					<c:otherwise>
						<div class="alert alert-info">
							<liferay-ui:message key="this-page-has-no-links" />
						</div>
					</c:otherwise>
				</c:choose>
			</liferay-ui:panel>
		</c:when>
		<c:otherwise>
			<div class="alert alert-info">
				<liferay-ui:message key="this-page-has-no-links" />
			</div>
		</c:otherwise>
	</c:choose>
</div>