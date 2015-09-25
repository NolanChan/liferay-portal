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

long sapEntryId = ParamUtil.getLong(request, "sapEntryId");

SAPEntry sapEntry = null;

String allowedServiceSignatures = StringPool.BLANK;

if (sapEntryId > 0) {
	sapEntry = SAPEntryServiceUtil.getSAPEntry(sapEntryId);

	allowedServiceSignatures = sapEntry.getAllowedServiceSignatures();
}

String[] allowedServiceSignaturesArray = StringUtil.splitLines(allowedServiceSignatures);

if (allowedServiceSignaturesArray.length == 0) {
	allowedServiceSignaturesArray = new String[] { StringPool.BLANK };
}

boolean systemSAPEntry = false;

if (sapEntry != null) {
	systemSAPEntry = sapEntry.isSystem();
}
%>

<liferay-ui:header
	backURL="<%= redirect %>"
	title='<%= (sapEntry != null) ? sapEntry.getTitle(locale) : "new-service-access-policy" %>'
/>

<portlet:actionURL name="updateSAPEntry" var="updateSAPEntryURL">
	<portlet:param name="mvcPath" value="/edit_entry.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateSAPEntryURL %>">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="sapEntryId" type="hidden" value="<%= sapEntryId %>" />

	<liferay-ui:error exception="<%= DuplicateSAPEntryNameException.class %>" message="please-enter-a-unique-service-access-policy-name" />
	<liferay-ui:error exception="<%= SAPEntryNameException.class %>" message="service-access-policy-name-is-required" />
	<liferay-ui:error exception="<%= SAPEntryTitleException.class %>" message="service-access-policy-title-is-required" />

	<aui:model-context bean="<%= sapEntry %>" model="<%= SAPEntry.class %>" />

	<aui:input disabled="<%= systemSAPEntry %>" name="name" required="<%= true %>">
		<aui:validator errorMessage="this-field-is-required-and-must-contain-only-following-characters" name="custom">
			function(val, fieldNode, ruleValue) {
				var allowedCharacters = '<%= HtmlUtil.escapeJS(SAPEntryConstants.NAME_ALLOWED_CHARACTERS) %>';

				val = val.trim();

				var regex = new RegExp('[^' + allowedCharacters + ']');

				return !regex.test(val);
			}
		</aui:validator>
	</aui:input>

	<aui:input name="enabled" />

	<aui:input disabled="<%= systemSAPEntry %>" helpMessage="default-sap-entry-help" label="default" name="defaultSAPEntry" />

	<aui:input name="title" required="<%= true %>" />

	<aui:input cssClass="hide" helpMessage="allowed-service-signatures-help" name="allowedServiceSignatures" />

	<portlet:resourceURL var="getMethodsURL">
		<portlet:param name="<%= ActionRequest.ACTION_NAME %>" value="getMethods" />
		<portlet:param name="mvcPath" value="<%= StringPool.SPACE %>" />
	</portlet:resourceURL>

	<div id="<portlet:namespace />allowedServiceSignaturesFriendlyContentBox">

		<%
		for (int i = 0; i < allowedServiceSignaturesArray.length; i++) {
			String entry = allowedServiceSignaturesArray[i];

			String[] split = StringUtil.split(entry, CharPool.POUND);

			String serviceClass = StringPool.BLANK;
			String methodName = StringPool.BLANK;

			if (split.length > 0) {
				serviceClass = GetterUtil.getString(split[0], StringPool.BLANK);

				if (split.length > 1) {
					methodName = GetterUtil.getString(split[1], StringPool.BLANK);
				}
			}
		%>

			<div class="lfr-form-row">
				<div class="row-fields">
					<aui:col md="6">
						<aui:input cssClass="service-class" data-service-class="<%= serviceClass %>" id='<%= "serviceClass" + i %>' name="serviceClass" type="text" value="<%= serviceClass %>" />
					</aui:col>
					<aui:col md="6">
						<aui:input cssClass="method-name" id='<%= "methodName" + i %>' name="methodName" type="text" value="<%= methodName %>" />
					</aui:col>
				</div>
			</div>

		<%
		}
		%>

	</div>

	<aui:script use="autocomplete,autocomplete-filters,io-base,liferay-auto-fields">
		var services = <%= JSONFactoryUtil.looseSerialize(JSONWebServiceActionsManagerUtil.getServiceNames()) %>;

		var autoFields = new Liferay.AutoFields(
			{
				contentBox: '#<portlet:namespace />allowedServiceSignaturesFriendlyContentBox',
				namespace: '<portlet:namespace />',
				on: {
					clone: function(event) {
						initAutoCompleteRow(event.row);
					},
					delete: updateAdvancedModeTextarea
				}
			}
		).render();

		var rows = A.all('#<portlet:namespace />allowedServiceSignaturesFriendlyContentBox .lfr-form-row');

		var rowTemplate = rows.first().clone();

		rowTemplate.all('input').val('');

		A.each(
			rows,
			function(item, index) {
				initAutoCompleteRow(item);
			}
		);

		A.one('#<portlet:namespace />allowedServiceSignaturesFriendlyContentBox').delegate('blur', updateAdvancedModeTextarea, '.service-class, .method-name');
		A.one('#<portlet:namespace />allowedServiceSignatures').on('blur', updateFriendlyModeInputs);

		function updateAdvancedModeTextarea() {
			var updatedInput = '';

			A.all('#<portlet:namespace />allowedServiceSignaturesFriendlyContentBox .lfr-form-row:not(.hide)').each(
				function(item, index) {
					var serviceClass = item.one('.service-class').val();
					var methodName = item.one('.method-name').val();

					updatedInput += serviceClass;

					if (methodName.length > 0) {
						updatedInput += '#' + methodName;
					}

					updatedInput += '\n';
				}
			);

			A.one('#<portlet:namespace />allowedServiceSignatures').val(updatedInput);
		}

		function updateFriendlyModeInputs() {
			var advancedInput = A.one('#<portlet:namespace />allowedServiceSignatures').val();
			var contentBox = A.one('#<portlet:namespace />allowedServiceSignaturesFriendlyContentBox');
			var entries = advancedInput.split('\n');
			var rows = contentBox.all('.lfr-form-row:not(.hide)');

			rows.remove();

			for (var i = 0; i < entries.length; i++) {
				var entry = entries[i];
				var row = rowTemplate.clone();

				if (entry.length) {
					var serviceInput = row.one('.service-class');
					var methodInput = row.one('.method-name');

					entry = entry.split('#');

					contentBox.append(row);

					initAutoCompleteRow(row);

					serviceInput.val(entry[0]);
					serviceInput.attr('data-service-class', entry[0]);

					if (entry[1]) {
						methodInput.val(entry[1]);
					}
				}
				else {
					contentBox.append(row);

					initAutoCompleteRow(row);
				}
			}
		}

		function initAutoCompleteRow(rowNode) {
			var serviceInput = rowNode.one('.service-class');
			var methodInput = rowNode.one('.method-name');

			new A.AutoComplete(
				{
					inputNode: serviceInput,
					source: services,
					resultFilters: 'phraseMatch',
					resultTextLocator: 'serviceClass',
					on: {
						select: function(event) {
							var result = event.result.raw;

							serviceInput.attr('data-service-class', result.serviceClass);
							serviceInput.attr('data-context', result.context);
						}
					}
				}
			).render();

			new A.AutoComplete(
				{
					inputNode: methodInput,
					source: function(query, callback) {
						var context = serviceInput.attr('data-context');
						var serviceClass = serviceInput.attr('data-service-class');

						if (context.length == 0) {
							A.Array.each(services, function(item, index) {
								if (item.serviceClass == serviceClass) {
									context = item.context;

									serviceInput.attr('data-context', context);
								}
							});
						}

						if (context.length && serviceClass.length) {
							var methodsURL = '<%= getMethodsURL %>&<portlet:namespace />serviceClass=' + serviceClass + '&<portlet:namespace />context=' + context;

							A.io.request(
								methodsURL,
								{
									dataType: 'JSON',
									method: 'GET',
									on: {
										complete: function(event, id, xhr) {
											var responseData = {};

											try {
												responseData = A.JSON.parse(xhr.responseText);
											}
											catch (e) {
											}

											callback(responseData);
										}
									}
								}
							);
						}
					},
					resultFilters: 'phraseMatch',
					resultTextLocator: 'methodName'
				}
			).render();
		}

		Liferay.provide(
			window,
			'<portlet:namespace />toggleAdvancedMode',
			function() {
				var A = AUI();

				var advancedButton = A.one('#<portlet:namespace />advancedMode');
				var friendlyButton = A.one('#<portlet:namespace />friendlyMode');
				var allowedServiceSignaturesTextarea = A.one('#<portlet:namespace />allowedServiceSignatures');
				var allowedServiceSignaturesFriendlyContentBox = A.one('#<portlet:namespace />allowedServiceSignaturesFriendlyContentBox');

				advancedButton.toggleClass('hide');
				friendlyButton.toggleClass('hide');
				allowedServiceSignaturesTextarea.toggleClass('hide');
				allowedServiceSignaturesFriendlyContentBox.toggleClass('hide');
			}
		);
	</aui:script>

	<aui:button-row>
		<aui:button type="submit" value="save" />
		<aui:button id="advancedMode" onClick='<%= renderResponse.getNamespace() + "toggleAdvancedMode();" %>' value="Switch to Advanced mode" />
		<aui:button cssClass="hide" id="friendlyMode" onClick='<%= renderResponse.getNamespace() + "toggleAdvancedMode();" %>' value="Switch to Friendly mode" />
	</aui:button-row>
</aui:form>