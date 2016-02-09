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
String redirect = ParamUtil.getString(request, "redirect");
String returnToFullPageURL = ParamUtil.getString(request, "returnToFullPageURL");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/edit_app_templates.jsp");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("returnToFullPageURL", returnToFullPageURL);
portletURL.setParameter("portletResource", portletResource);

SearchContainer<ArchivedSettings> archivedSettingsSearch = new SearchContainer<ArchivedSettings>(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, portletURL, null, "there-are-no-archived-setups");

List<ArchivedSettings> archivedSettingsList = SettingsFactoryUtil.getPortletInstanceArchivedSettingsList(scopeGroupId, selPortlet.getRootPortletId());

int archivedSettingsCount = archivedSettingsList.size();

archivedSettingsSearch.setTotal(archivedSettingsCount);

archivedSettingsList = ListUtil.subList(archivedSettingsList, archivedSettingsSearch.getStart(), archivedSettingsSearch.getEnd());

archivedSettingsSearch.setResults(archivedSettingsList);
%>

<liferay-ui:error exception="<%= NoSuchPortletItemException.class %>" message="the-setup-could-not-be-found" />

<div class="container-fluid-1280">
	<div class="button-holder text-center">
		<portlet:renderURL var="addAppTemplateURL">
			<portlet:param name="mvcPath" value="/add_app_template.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
		</portlet:renderURL>

		<aui:button href="<%= addAppTemplateURL %>" value="create-app-template" />
	</div>

	<liferay-ui:search-container
		searchContainer="<%= archivedSettingsSearch %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.settings.ArchivedSettings"
			keyProperty="name"
			modelVar="archivedSettings"
		>
			<liferay-ui:search-container-column-text
				name="name"
			/>

			<liferay-ui:search-container-column-text
				name="user-name"
				property="userName"
			/>

			<liferay-ui:search-container-column-date
				name="modified-date"
				property="modifiedDate"
			/>

			<liferay-ui:search-container-column-jsp
				cssClass="list-group-item-field"
				path="/app_template_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator markupView="lexicon" />
	</liferay-ui:search-container>
</div>
