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

<c:if test="<%= PropsValues.SEARCH_CONTAINER_SHOW_PAGINATION_BOTTOM && paginate %>">
	<div class="taglib-search-iterator-page-iterator-bottom">
		<liferay-ui:search-paginator id='<%= id + "PageIteratorBottom" %>' markupView="<%= markupView %>" searchContainer="<%= searchContainer %>" type="<%= type %>" />
	</div>
</c:if>

<c:if test="<%= Validator.isNotNull(id) %>">
	<input id="<%= namespace + id %>PrimaryKeys" name="<%= namespace + id %>PrimaryKeys" type="hidden" value="" />

	<%
	String modules = "liferay-search-container";
	String rowCheckerRowSelector = StringPool.BLANK;

	if (rowMover != null) {
		modules += ",liferay-search-container-move";
	}

	if (rowChecker != null) {
		modules += ",liferay-search-container-select";

		rowCheckerRowSelector = rowChecker.getRowSelector();

		if (Validator.isNull(rowCheckerRowSelector)) {
			rowCheckerRowSelector = "[data-selectable=\"true\"]";
		}
	}
	%>

	<aui:script use="<%= modules %>">
		var plugins = [];

		var rowSelector = '<%= rowHtmlTag %><%= rowCheckerRowSelector %>';

		<c:if test="<%= rowChecker != null %>">
			plugins.push(
				{
					cfg: {
						rowSelector: rowSelector
					},
					fn: A.Plugin.SearchContainerSelect
				}
			);
		</c:if>

		<c:if test="<%= rowMover != null %>">
			var rowMoverConfig = <%= rowMover.toJSON().toString() %>;

			rowMoverConfig.rowSelector = rowSelector + rowMoverConfig.rowSelector;

			plugins.push(
				{
					cfg: rowMoverConfig,
					fn: A.Plugin.SearchContainerMove
				}
			);
		</c:if>

		var searchContainer = new Liferay.SearchContainer(
			{
				id: '<%= namespace + id %>',
				plugins: plugins
			}
		).render();

		searchContainer.updateDataStore(<%= primaryKeysJSONArray.toString() %>);

		var destroySearchContainer = function(event) {
			if (event.portletId === '<%= portletDisplay.getRootPortletId() %>') {
				searchContainer.destroy();

				Liferay.detach('destroyPortlet', destroySearchContainer);
			}
		};

		Liferay.on('destroyPortlet', destroySearchContainer);
	</aui:script>
</c:if>