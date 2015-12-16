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
PanelCategory panelCategory = (PanelCategory)request.getAttribute(ApplicationListWebKeys.PANEL_CATEGORY);

SiteAdministrationPanelCategoryDisplayContext siteAdministrationPanelCategoryDisplayContext = new SiteAdministrationPanelCategoryDisplayContext(liferayPortletRequest, liferayPortletResponse, null);

ResourceBundle resourceBundle = ResourceBundleUtil.getBundle("content.Language", locale, getClass());
%>

<c:if test="<%= siteAdministrationPanelCategoryDisplayContext.getGroup() != null %>">
	<c:if test="<%= siteAdministrationPanelCategoryDisplayContext.isShowStagingInfo() %>">
		<div class="pull-right staging-links">
			<span class="<%= Validator.isNull(siteAdministrationPanelCategoryDisplayContext.getStagingGroupURL()) ? "active" : StringPool.BLANK %>">
				<aui:a href="<%= siteAdministrationPanelCategoryDisplayContext.getStagingGroupURL() %>" label="staging" /> /
			</span>

			<span class="<%= Validator.isNull(siteAdministrationPanelCategoryDisplayContext.getLiveGroupURL()) ? "active" : StringPool.BLANK %>">
				<aui:a href="<%= siteAdministrationPanelCategoryDisplayContext.getLiveGroupURL() %>" label="live" />
			</span>
		</div>
	</c:if>

	<c:if test="<%= siteAdministrationPanelCategoryDisplayContext.isDisplaySiteLink() %>">
		<aui:a
			cssClass="list-group-heading"
			href="<%= siteAdministrationPanelCategoryDisplayContext.getGroupURL() %>"
			label='<%= LanguageUtil.get(resourceBundle, "go-to-site") %>'
		/>
	</c:if>

	<c:if test="<%= siteAdministrationPanelCategoryDisplayContext.isShowSiteAdministration() %>">
		<liferay-application-list:panel-category-body panelCategory="<%= panelCategory %>" />
	</c:if>
</c:if>