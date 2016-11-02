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
			label="sites"
			selected="<%= true %>"
		/>
	</aui:nav>
</aui:nav-bar>

<div class="container-fluid-1280 main-content-body">
	<div class="alert alert-warning">
		<liferay-ui:message key="regenerating-a-site-will-reset-any-configurations-made-to-any-pages-and-portlets" />
	</div>

	<portlet:actionURL name="/regenerate_site" var="regenerateSiteURL">
		<portlet:param name="mvcRenderCommandName" value="/view" />
	</portlet:actionURL>

	<aui:form action="<%= regenerateSiteURL %>" method="post" name="fm">
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

		<div class="lfr-form-content">
			<aui:fieldset-group markupView="lexicon">
				<aui:fieldset>
					<aui:select label="site" name="groupId">

						<%
						Group guestGroup = GroupLocalServiceUtil.getGroup(PortalUtil.getDefaultCompanyId(), GroupConstants.GUEST);
						%>

						<aui:option label="<%= guestGroup.getName() %>" value="<%= guestGroup.getGroupId() %>" />

						<%
						List<Group> documentationProjectGroups = GroupLocalServiceUtil.getGroups(themeDisplay.getCompanyId(), DocumentationProject.class.getName(), GroupConstants.DEFAULT_PARENT_GROUP_ID, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

						for (Group documentationProjectGroup : documentationProjectGroups) {
						%>

							<aui:option label="<%= documentationProjectGroup.getName() %>" value="<%= documentationProjectGroup.getGroupId() %>" />

						<%
						}
						%>

					</aui:select>

					<aui:select label="generator" name="siteGeneratorKey">

						<%
						Collection<SiteGenerator> siteGenerators = SiteGeneratorRegistryUtil.getSiteGenerators();

						for (SiteGenerator siteGenerator : siteGenerators) {
						%>

							<aui:option label="<%= siteGenerator.getName() %>" value="<%= siteGenerator.getKey() %>" />

						<%
						}
						%>

					</aui:select>
				</aui:fieldset>
			</aui:fieldset-group>
		</div>

		<aui:button-row>
			<aui:button cssClass="btn-lg" type="submit" value="regenerate" />
		</aui:button-row>
	</aui:form>
</div>