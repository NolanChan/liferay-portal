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

<%@ include file="/html/portlet/layouts_admin/init.jsp" %>

<%
long exportImportConfigurationId = ParamUtil.getLong(request, "exportImportConfigurationId");

ExportImportConfiguration exportImportConfiguration = ExportImportConfigurationLocalServiceUtil.getExportImportConfiguration(exportImportConfigurationId);

String cmd = Constants.EXPORT;
String value = cmd;

if (exportImportConfiguration.getType() == ExportImportConfigurationConstants.TYPE_PUBLISH_LAYOUT_LOCAL) {
	cmd = Constants.PUBLISH_TO_LIVE;
	value = "publish-to-live";
}
else if (exportImportConfiguration.getType() == ExportImportConfigurationConstants.TYPE_PUBLISH_LAYOUT_REMOTE) {
	cmd = Constants.PUBLISH_TO_REMOTE;
	value = "publish-to-remote";
}

Map<String, Serializable> settingsMap = exportImportConfiguration.getSettingsMap();
long [] selectedLayoutIds = GetterUtil.getLongValues(settingsMap.get("layoutIds"));
Map<String, String[]> parameterMap = (Map<String, String[]>)settingsMap.get("parameterMap");

String backUrl = ParamUtil.getString(request, "backUrl");
String redirectUrl = ParamUtil.getString(request, "redirect");

String hoursAgo = Time.getRelativeTimeDescription(exportImportConfiguration.getCreateDate(), locale, timeZone);
%>

<liferay-ui:header
	backURL="<%= backUrl %>"
	title="<%= exportImportConfiguration.getName() %>"
/>

<div class="export-dialog-tree">
	<span class="selected-labels" id="<portlet:namespace />exportImportConfigurationDescription">
		<liferay-ui:message key="<%= exportImportConfiguration.getDescription() %>" />
	</span>

	<ul class="lfr-tree list-unstyled">
		<li class="tree-item">
			<aui:fieldset cssClass="options-group" label="user">
				<liferay-ui:user-display
					displayStyle="1"
					showUserDetails="<%= false %>"
					showUserName="<%= false %>"
					userId="<%= exportImportConfiguration.getUserId() %>"
				/>

				<liferay-ui:message key="<%= hoursAgo %>" />
			</aui:fieldset>

			<portlet:actionURL var="exportByExportImportConfigurationURL">
				<portlet:param name="struts_action" value="/layouts_admin/edit_export_configuration" />
				<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.EXPORT %>" />
				<portlet:param name="redirect" value="<%= redirectUrl %>" />
				<portlet:param name="exportImportConfigurationId" value="<%= String.valueOf(exportImportConfiguration.getExportImportConfigurationId()) %>" />
			</portlet:actionURL>

			<portlet:actionURL var="publishByExportImportConfigurationURL">
				<portlet:param name="struts_action" value="/layouts_admin/edit_publish_configuration" />
				<portlet:param name="<%= Constants.CMD %>" value="<%= cmd %>" />
				<portlet:param name="redirect" value="<%= redirectUrl %>" />
				<portlet:param name="exportImportConfigurationId" value="<%= String.valueOf(exportImportConfiguration.getExportImportConfigurationId()) %>" />
			</portlet:actionURL>

			<aui:form action='<%= (cmd.equals(Constants.EXPORT) ? exportByExportImportConfigurationURL : publishByExportImportConfigurationURL) + "&etag=0&strip=0" %>' cssClass="lfr-export-dialog" method="post" name="fm2">
				<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= cmd %>" />
				<aui:input name="redirect" type="hidden" value="<%= redirectUrl %>" />
				<aui:input name="exportImportConfigurationId" type="hidden" value="<%= exportImportConfigurationId %>" />

				<aui:fieldset cssClass="options-group" label="pages">
					<span class="selected-labels" id="<portlet:namespace />pagesSection">

						<%
						StringBundler sb = new StringBundler();

						if (ArrayUtil.isEmpty(selectedLayoutIds)) {
							sb.append(LanguageUtil.get(locale, "selected-pages"));
						}
						else {
							sb.append(LanguageUtil.get(locale, "all-pages"));
						}

						if (MapUtil.getBoolean(parameterMap, PortletDataHandlerKeys.LAYOUT_SET_SETTINGS)) {
							sb.append(", ");
							sb.append(LanguageUtil.get(locale, "site-pages-settings"));
						}

						if (MapUtil.getBoolean(parameterMap, PortletDataHandlerKeys.THEME_REFERENCE)) {
							sb.append(", ");
							sb.append(LanguageUtil.get(locale, "theme-settings"));
						}

						if (MapUtil.getBoolean(parameterMap, PortletDataHandlerKeys.LOGO)) {
							sb.append(", ");
							sb.append(LanguageUtil.get(locale, "logo"));
						}
						%>

						<liferay-ui:message key="<%= sb.toString() %>" />
					</span>
				</aui:fieldset>

				<liferay-staging:content disableInputs="<%= true %>" parameterMap="<%= parameterMap %>" type="<%= cmd %>" />

				<aui:button-row>
					<aui:button type="submit" value="<%= LanguageUtil.get(request, value) %>" />

					<aui:button href="<%= backUrl %>" type="cancel" />
				</aui:button-row>
			</aui:form>
		</li>
	</ul>
</div>

<aui:script use="liferay-export-import">
	new Liferay.ExportImport(
		{
			archivedSetupsNode: '#<%= PortletDataHandlerKeys.PORTLET_ARCHIVED_SETUPS_ALL %>',
			commentsNode: '#<%= PortletDataHandlerKeys.COMMENTS %>',
			deletionsNode: '#<%= PortletDataHandlerKeys.DELETIONS %>',
			exportLAR: true,
			form: document.<portlet:namespace />fm1,
			incompleteProcessMessageNode: '#<portlet:namespace />incompleteProcessMessage',
			layoutSetSettingsNode: '#<%= PortletDataHandlerKeys.LAYOUT_SET_SETTINGS %>',
			logoNode: '#<%= PortletDataHandlerKeys.LOGO %>',
			namespace: '<portlet:namespace />',
			rangeAllNode: '#rangeAll',
			rangeDateRangeNode: '#rangeDateRange',
			rangeLastNode: '#rangeLast',
			rangeLastPublishNode: '#rangeLastPublish',
			ratingsNode: '#<%= PortletDataHandlerKeys.RATINGS %>',
			setupNode: '#<%= PortletDataHandlerKeys.PORTLET_SETUP_ALL %>',
			themeReferenceNode: '#<%= PortletDataHandlerKeys.THEME_REFERENCE %>',
			userPreferencesNode: '#<%= PortletDataHandlerKeys.PORTLET_USER_PREFERENCES_ALL %>'
		}
	);
</aui:script>