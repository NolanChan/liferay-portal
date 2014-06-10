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

<%@ include file="/html/taglib/ui/diff_version_comparator/init.jsp" %>

<%
String diffHtmlResults = (String)request.getAttribute(WebKeys.DIFF_HTML_RESULTS);
%>

<div id="<portlet:namespace />diffContainerHtmlResults">
	<c:choose>
		<c:when test="<%= Validator.isNotNull(diffHtmlResults) %>">
			<liferay-ui:diff-html diffHtmlResults="<%= diffHtmlResults %>" />
		</c:when>
		<c:otherwise>
			<div class="alert alert-info">
				<liferay-ui:message key="those-versions-are-not-comparables" />
			</div>
		</c:otherwise>
	</c:choose>
</div>