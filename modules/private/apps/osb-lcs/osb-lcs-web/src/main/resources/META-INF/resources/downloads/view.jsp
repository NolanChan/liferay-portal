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

<div class="download-options lcs-section">
	<h2>
		<liferay-ui:message key="choose-the-version-of-liferay-connected-services-client" />
	</h2>

	<aui:row>
		<aui:col cssClass="item" width="<%= 50 %>">
			<div class="title">
				<liferay-ui:message key="liferay-portal-6.2-enterprise-edition" />
			</div>

			<%
			List<LCSMetadata> lcsMetadatas = LCSMetadataServiceUtil.getLCSMetadatas(6210, LCSConstants.PORTAL_EDITION_EE);

			LCSMetadata lcsMetadata = lcsMetadatas.get(0);
			%>

			<div class="link">
				<aui:a href="<%= osbLCSConfiguration.lcsPortlet6210DownloadUrl() %>">
					<liferay-ui:message arguments="<%= lcsMetadata.getSupportedLCSPortlet() %>" key="download-version-x" />
				</aui:a>
			</div>
		</aui:col>

		<aui:col cssClass="item" width="<%= 50 %>">
			<div class="title">
				<liferay-ui:message key="liferay-digital-experience-platform" />
			</div>

			<div class="link">
				<liferay-ui:message arguments="<%= osbLCSConfiguration.lcsPortlet7010DownloadUrl() %>" key="download-on-liferay-marketplace" />
			</div>
		</aui:col>
	</aui:row>
</div>

<div class="lcs-section">
	<h2>
		<liferay-ui:message key="liferay-connected-services-client-upgrade" />
	</h2>

	<div class="lcs-note">
		<liferay-ui:message arguments="<%= osbLCSConfiguration.lcsPortletTokenCompatibleVersion() %>" key="if-you-are-using-an-environment-token-with-version-x-or-older-of-the-liferay-connected-services-client-you-must-regenerate-the-token-upon-upgrading-the-client" />
	</div>

	<div class="lcs-note">
		<liferay-ui:message key="versions-160-and-higher-of-the-liferay-connected-services-client-do-not-have-a-user-interface-for-registering-with-liferay-connected-services" />
	</div>
</div>