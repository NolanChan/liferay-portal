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

<%@ include file="/html/portlet/portal_settings/init.jsp" %>

<%
String authenticationURL = currentURL + "#_LFR_FN_authentication";

boolean ldapAuthEnabled = PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.LDAP_AUTH_ENABLED, PropsValues.LDAP_AUTH_ENABLED);
boolean ldapAuthRequired = PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.LDAP_AUTH_REQUIRED);
boolean ldapImportEnabled = PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.LDAP_IMPORT_ENABLED, PropsValues.LDAP_IMPORT_ENABLED);
boolean ldapImportOnStartup = PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.LDAP_IMPORT_ON_STARTUP);
boolean ldapExportEnabled = !(PropsValues.LDAP_IMPORT_USER_PASSWORD_AUTOGENERATED && ldapImportEnabled) && PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.LDAP_EXPORT_ENABLED, PropsValues.LDAP_EXPORT_ENABLED);
boolean ldapPasswordPolicyEnabled = PrefsPropsUtil.getBoolean(company.getCompanyId(), PropsKeys.LDAP_PASSWORD_POLICY_ENABLED);
long[] ldapServerIds = StringUtil.split(PrefsPropsUtil.getString(company.getCompanyId(), "ldap.server.ids"), 0L);

if (ldapAuthEnabled && (ldapServerIds.length <= 0) && Validator.isNull(PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_BASE_PROVIDER_URL + ".0", StringPool.BLANK))) {
	String ldapServerName = themeDisplay.translate("default");
	String ldapBaseProviderUrl = ParamUtil.getString(request, "settings--" + PropsKeys.LDAP_BASE_PROVIDER_URL + "--", PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_BASE_PROVIDER_URL, StringPool.BLANK));
	String ldapBaseDN = ParamUtil.getString(request, "settings--" + PropsKeys.LDAP_BASE_DN + "--", PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_BASE_DN));
	String ldapSecurityPrincipal = ParamUtil.getString(request, "settings--" + PropsKeys.LDAP_SECURITY_PRINCIPAL + "--", PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_SECURITY_PRINCIPAL));
	String ldapSecurityCredentials = ParamUtil.getString(request, "settings--" + PropsKeys.LDAP_SECURITY_CREDENTIALS + "--", PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_SECURITY_CREDENTIALS));
	String ldapAuthSearchFilter = ParamUtil.getString(request, "settings--" + PropsKeys.LDAP_AUTH_SEARCH_FILTER + "--", PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_AUTH_SEARCH_FILTER));
	String ldapImportUserSearchFilter = ParamUtil.getString(request, "settings--" + PropsKeys.LDAP_IMPORT_USER_SEARCH_FILTER + "--", PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_IMPORT_USER_SEARCH_FILTER));
	String ldapImportGroupSearchFilter = ParamUtil.getString(request, "settings--" + PropsKeys.LDAP_IMPORT_GROUP_SEARCH_FILTER + "--", PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_IMPORT_GROUP_SEARCH_FILTER));
	String ldapUsersDN = ParamUtil.getString(request, "settings--" + PropsKeys.LDAP_USERS_DN + "--", PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_USERS_DN));
	String ldapUserDefaultObjectClasses = ParamUtil.getString(request, "settings--" + PropsKeys.LDAP_USER_DEFAULT_OBJECT_CLASSES + "--", PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_USER_DEFAULT_OBJECT_CLASSES));
	String ldapGroupsDN = ParamUtil.getString(request, "settings--" + PropsKeys.LDAP_GROUPS_DN + "--", PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_GROUPS_DN));
	String ldapGroupDefaultObjectClasses = ParamUtil.getString(request, "settings--" + PropsKeys.LDAP_GROUP_DEFAULT_OBJECT_CLASSES + "--", PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_GROUP_DEFAULT_OBJECT_CLASSES));
	String ldapUserMappings = ParamUtil.getString(request, "settings--" + PropsKeys.LDAP_USER_MAPPINGS + "--", PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_USER_MAPPINGS));
	String ldapGroupMappings = ParamUtil.getString(request, "settings--" + PropsKeys.LDAP_GROUP_MAPPINGS + "--", PrefsPropsUtil.getString(company.getCompanyId(), PropsKeys.LDAP_GROUP_MAPPINGS));

	if (Validator.isNotNull(ldapBaseProviderUrl)) {
		long ldapServerId = CounterLocalServiceUtil.increment();

		String postfix = StringPool.PERIOD + ldapServerId;

		ldapServerIds = new long[] {ldapServerId};

		UnicodeProperties properties = new UnicodeProperties();

		properties.put("ldap.server.name" + postfix, ldapServerName);
		properties.put(PropsKeys.LDAP_BASE_PROVIDER_URL + postfix, ldapBaseProviderUrl);
		properties.put(PropsKeys.LDAP_BASE_DN + postfix, ldapBaseDN);
		properties.put(PropsKeys.LDAP_SECURITY_PRINCIPAL + postfix, ldapSecurityPrincipal);
		properties.put(PropsKeys.LDAP_SECURITY_CREDENTIALS + postfix, ldapSecurityCredentials);
		properties.put(PropsKeys.LDAP_AUTH_SEARCH_FILTER + postfix, ldapAuthSearchFilter);
		properties.put(PropsKeys.LDAP_IMPORT_USER_SEARCH_FILTER + postfix, ldapImportUserSearchFilter);
		properties.put(PropsKeys.LDAP_IMPORT_GROUP_SEARCH_FILTER + postfix, ldapImportGroupSearchFilter);
		properties.put(PropsKeys.LDAP_USERS_DN + postfix, ldapUsersDN);
		properties.put(PropsKeys.LDAP_USER_DEFAULT_OBJECT_CLASSES + postfix, ldapUserDefaultObjectClasses);
		properties.put(PropsKeys.LDAP_GROUPS_DN + postfix, ldapGroupsDN);
		properties.put(PropsKeys.LDAP_GROUP_DEFAULT_OBJECT_CLASSES + postfix, ldapGroupDefaultObjectClasses);
		properties.put(PropsKeys.LDAP_USER_MAPPINGS + postfix, ldapUserMappings);
		properties.put(PropsKeys.LDAP_GROUP_MAPPINGS + postfix, ldapGroupMappings);
		properties.put("ldap.server.ids", StringUtil.merge(ldapServerIds));

		List<String> keys = new ArrayList<String>();

		keys.add("ldap.server.name");
		keys.add(PropsKeys.LDAP_BASE_PROVIDER_URL);
		keys.add(PropsKeys.LDAP_BASE_DN);
		keys.add(PropsKeys.LDAP_SECURITY_PRINCIPAL);
		keys.add(PropsKeys.LDAP_SECURITY_CREDENTIALS);
		keys.add(PropsKeys.LDAP_AUTH_SEARCH_FILTER);
		keys.add(PropsKeys.LDAP_IMPORT_USER_SEARCH_FILTER);
		keys.add(PropsKeys.LDAP_IMPORT_GROUP_SEARCH_FILTER);
		keys.add(PropsKeys.LDAP_USERS_DN);
		keys.add(PropsKeys.LDAP_USER_DEFAULT_OBJECT_CLASSES);
		keys.add(PropsKeys.LDAP_GROUPS_DN);
		keys.add(PropsKeys.LDAP_GROUP_DEFAULT_OBJECT_CLASSES);
		keys.add(PropsKeys.LDAP_USER_MAPPINGS);
		keys.add(PropsKeys.LDAP_GROUP_MAPPINGS);

		try {
			CompanyServiceUtil.updatePreferences(company.getCompanyId(), properties);

			CompanyServiceUtil.removePreferences(company.getCompanyId(), keys.toArray(new String[keys.size()]));
		}
		catch (Exception e) {
		}
	}
}
%>

<aui:fieldset>
	<liferay-ui:error key="ldapExportAndImportOnPasswordAutogeneration" message="ldap-export-must-not-be-enabled-when-autogeneration-of-user-passwords-is-enabled-for-ldap-import" />

	<aui:input label="enabled" name='<%= "settings--" + PropsKeys.LDAP_AUTH_ENABLED + "--" %>' type="checkbox" value="<%= ldapAuthEnabled %>" />

	<aui:input label="required" name='<%= "settings--" + PropsKeys.LDAP_AUTH_REQUIRED + "--" %>' type="checkbox" value="<%= ldapAuthRequired %>" />
</aui:fieldset>

<h3><liferay-ui:message key="ldap-servers" /></h3>

<c:if test="<%= ldapAuthEnabled && (ldapServerIds.length <= 0) %>">
	<div class="alert alert-info">
		<liferay-ui:message key="default-ldap-server-settings-are-in-use-please-add-an-ldap-server-to-override-the-default-settings" />
	</div>
</c:if>

<aui:button-row>

	<%
	PortletURL addServerURL = renderResponse.createRenderURL();

	addServerURL.setParameter("struts_action", "portal_settings/edit_ldap_server");
	addServerURL.setParameter("redirect", authenticationURL);
	%>

	<aui:button href="<%= addServerURL.toString() %>" name="addButton" value="add" />
</aui:button-row>

<aui:fieldset>
	<aui:input name="settings--ldap.server.ids--" type="hidden" value="<%= StringUtil.merge(ldapServerIds) %>" />

	<c:if test="<%= ldapServerIds.length > 0 %>">
		<br /><br />

		<div class="ldap-servers searchcontainer-content">
			<table class="table table-bordered table-hover table-striped">
			<thead class="table-columns">
			<tr>
				<td class="table-header">
					<liferay-ui:message key="ldap-server-id" />
				</th>
				<td class="table-header">
					<liferay-ui:message key="ldap-server-name" />
				</th>
				<td class="table-header"></th>
			</tr>
			</thead>

			<tbody class="table-data">

			<%
			for (int i = 0; i < ldapServerIds.length; i++) {
				long ldapServerId = ldapServerIds[i];

				String ldapServerName = PrefsPropsUtil.getString(company.getCompanyId(), "ldap.server.name." + ldapServerId);
			%>

				<tr data-ldapServerId="<%= ldapServerId %>">
					<td class="table-cell">
						<%= ldapServerId %>
					</td>
					<td class="table-cell">
						<%= ldapServerName %>
					</td>
					<td align="right" class="table-cell">
						<div class="control">
							<c:if test="<%= ldapServerIds.length > 1 %>">

								<%
								String taglibUpURL = "javascript:" + renderResponse.getNamespace() + "raiseLDAPServerPriority(" + ldapServerId + ");";
								%>

								<liferay-ui:icon
									iconCssClass="icon-arrow-up"
									message="up"
									url="<%= taglibUpURL %>"
								/>

								<%
								String taglibDownURL = "javascript:" + renderResponse.getNamespace() + "lowerLDAPServerPriority(" + ldapServerId + ");";
								%>

								<liferay-ui:icon
									iconCssClass="icon-arrow-down"
									message="down"
									url="<%= taglibDownURL %>"
								/>
							</c:if>

							<portlet:renderURL var="editURL">
								<portlet:param name="struts_action" value="/portal_settings/edit_ldap_server" />
								<portlet:param name="redirect" value="<%= authenticationURL %>" />
								<portlet:param name="ldapServerId" value="<%= String.valueOf(ldapServerId) %>" />
							</portlet:renderURL>

							<liferay-ui:icon
								iconCssClass="icon-edit"
								message="edit"
								url="<%= editURL %>"
							/>

							<portlet:actionURL var="deleteURL">
								<portlet:param name="struts_action" value="/portal_settings/edit_ldap_server" />
								<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
								<portlet:param name="redirect" value="<%= authenticationURL %>" />
								<portlet:param name="ldapServerId" value="<%= String.valueOf(ldapServerId) %>" />
							</portlet:actionURL>

							<liferay-ui:icon-delete url="<%= deleteURL %>" />
						</div>
					</td>
				</tr>

			<%
			}
			%>

			</tbody>
			</table>
		</div>
	</c:if>
</aui:fieldset>

<h3><liferay-ui:message key="import-export" /></h3>

<aui:fieldset>
	<c:choose>
		<c:when test="<%= PropsValues.LDAP_IMPORT_USER_PASSWORD_AUTOGENERATED %>">
			<aui:input helpMessage="import-enabled-user-password-autogenerated-help" id="ldapImportEnabled" label="enable-import" name='<%= "settings--" + PropsKeys.LDAP_IMPORT_ENABLED + "--" %>' onClick='<%= renderResponse.getNamespace() + "enableExport()" %>' type="checkbox" value="<%= ldapImportEnabled %>" />
		</c:when>
		<c:otherwise>
			<aui:input id="ldapImportEnabled" label="enable-import" name='<%= "settings--" + PropsKeys.LDAP_IMPORT_ENABLED + "--" %>' type="checkbox" value="<%= ldapImportEnabled %>" />
		</c:otherwise>
	</c:choose>

	<div id="<portlet:namespace />importEnabledSettings">
		<aui:input label="enable-import-on-startup" name='<%= "settings--" + PropsKeys.LDAP_IMPORT_ON_STARTUP + "--" %>' type="checkbox" value="<%= ldapImportOnStartup %>" />
	</div>

	<aui:input disabled="<%= PropsValues.LDAP_IMPORT_USER_PASSWORD_AUTOGENERATED && ldapImportEnabled %>" id="ldapExportEnabled" label="enable-export" name='<%= "settings--" + PropsKeys.LDAP_EXPORT_ENABLED + "--" %>' type="checkbox" value="<%= ldapExportEnabled %>" />
</aui:fieldset>

<h3><liferay-ui:message key="password-policy" /></h3>

<aui:fieldset>
	<aui:input label="use-ldap-password-policy" name='<%= "settings--" + PropsKeys.LDAP_PASSWORD_POLICY_ENABLED + "--" %>' type="checkbox" value="<%= ldapPasswordPolicyEnabled %>" />
</aui:fieldset>

<c:if test="<%= PropsValues.LDAP_IMPORT_USER_PASSWORD_AUTOGENERATED %>">
	<aui:script>
		function <portlet:namespace />enableExport() {
			var $ = AUI.$;

			var exportCheckbox = $('#<portlet:namespace />ldapExportEnabled');
			var importCheckbox = $('#<portlet:namespace />ldapImportEnabled');

			exportCheckbox.prop('disabled', importCheckbox.prop('checked'));
		}
	</aui:script>
</c:if>

<aui:script>
	function <portlet:namespace />changeLDAPServerPriority(ldapServerId, action) {
		var ldapServer = AUI.$('.ldap-servers tr[data-ldapServerId="' + ldapServerId + '"]');

		var swapLdapServer = ldapServer[action]();

		if (swapLdapServer.length) {
			ldapServer[(action === 'next') ? 'before' : 'after'](swapLdapServer);
		}
	}

	function <portlet:namespace />lowerLDAPServerPriority(ldapServerId) {
		<portlet:namespace />changeLDAPServerPriority(ldapServerId, 'next');
	}

	function <portlet:namespace />raiseLDAPServerPriority(ldapServerId) {
		<portlet:namespace />changeLDAPServerPriority(ldapServerId, 'prev');
	}

	Liferay.Util.toggleBoxes('<portlet:namespace />ldapImportEnabled', '<portlet:namespace />importEnabledSettings');
</aui:script>