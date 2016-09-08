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
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "/view");
%>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item
			href="<%= portletURL.toString() %>"
			label="documentation-projects"
			selected="<%= true %>"
		/>
	</aui:nav>
</aui:nav-bar>

<div class="container-fluid-1280 main-content-body">
	<liferay-ui:categorization-filter
		assetType="documentation-projects"
		portletURL="<%= portletURL %>"
	/>

	<liferay-ui:search-container
		emptyResultsMessage="no-documentation-projects-were-found"
		id="documentationProjects"
		iteratorURL="<%= portletURL %>"
	>

		<liferay-ui:search-container-results>

			<%
			total = DocumentationProjectLocalServiceUtil.getDocumentationProjectsCount();

			searchContainer.setTotal(total);

			results = DocumentationProjectLocalServiceUtil.getDocumentationProjects(searchContainer.getStart(), searchContainer.getEnd());

			searchContainer.setResults(results);
			%>

		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.osb.ldn.documentation.project.model.DocumentationProject"
			keyProperty="documentationProjectId"
			modelVar="documentationProject"
		>
			<liferay-ui:search-container-column-text
				name="name"
				orderable="<%= false %>"
				property="name"
			/>

			<liferay-ui:search-container-column-text
				name="creator"
				orderable="<%= false %>"
				property="userName"
			/>

			<liferay-ui:search-container-column-date
				name="create-date"
				orderable="<%= false %>"
				property="createDate"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator displayStyle="list" markupView="lexicon" />
	</liferay-ui:search-container>
</div>