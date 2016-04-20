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
long definitionId = ParamUtil.getLong(request, "definitionId");

Definition definition = DefinitionLocalServiceUtil.getDefinition(definitionId);

String reportName = BeanParamUtil.getString(definition, request, "reportName");
%>

<portlet:renderURL var="searchDefinitionURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="mvcPath" value="/admin/view.jsp" />
	<portlet:param name="tabs1" value="definitions" />
</portlet:renderURL>

<liferay-ui:header
	backURL="<%= searchDefinitionURL %>"
	title='<%= "new-report-entry" %>'
/>

<portlet:renderURL var="searchRequestsURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="mvcPath" value="/admin/view.jsp" />
	<portlet:param name="tabs1" value="reports" />
</portlet:renderURL>

<portlet:actionURL name="addScheduler" var="addSchedulerURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="mvcPath" value="/admin/report/edit_schedule.jsp" />
	<portlet:param name="redirect" value="<%= searchRequestsURL %>" />
</portlet:actionURL>

<aui:form action="<%= addSchedulerURL %>" method="post" name="fm">
	<aui:input name="definitionId" type="hidden" value="<%= definitionId %>" />

	<portlet:renderURL var="generatedReportsURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="mvcPath" value="/admin/report/requested_report_detail.jsp" />
	</portlet:renderURL>

	<aui:input name="generatedReportsURL" type="hidden" value="<%= generatedReportsURL %>" />

	<liferay-ui:error exception="<%= DefinitionNameException.class %>" message="please-enter-a-valid-name" />
	<liferay-ui:error exception="<%= EntryEmailDeliveryException.class %>" message="please-enter-a-valid-email-address" />
	<liferay-ui:error exception="<%= EntryEmailNotificationsException.class %>" message="please-enter-a-valid-email-address" />

	<liferay-ui:input-scheduler />

	<aui:select label="report-format" name="format">

		<%
		for (ReportFormat reportFormat : ReportFormat.values()) {
		%>

			<aui:option label="<%= reportFormat.getValue() %>" value="<%= reportFormat.getValue() %>" />

		<%
		}
		%>

	</aui:select>

	<aui:input name="reportName" value="<%= reportName %>" />

	<aui:field-wrapper helpMessage="entry-report-parameters-help" label="report-parameters">

		<%
		JSONArray reportParametersJSONArray = JSONFactoryUtil.createJSONArray(definition.getReportParameters());

		for (int i = 0; i < reportParametersJSONArray.length(); i++) {
			JSONObject reportParameterJSONObject = reportParametersJSONArray.getJSONObject(i);

			String key = reportParameterJSONObject.getString("key");
			String type = reportParameterJSONObject.getString("type");
			String value = reportParameterJSONObject.getString("value");
		%>

			<aui:row>
				<c:choose>
					<c:when test='<%= type.equals("date") %>'>
						<aui:col width="<%= 20 %>">
							<aui:field-wrapper helpMessage="entry-report-date-parameters-help" label="<%= key %>" />
						</aui:col>

						<aui:col width="<%= 30 %>">

							<%
							Calendar calendar = CalendarFactoryUtil.getCalendar(timeZone, locale);

							String[] date = value.split("-");

							calendar.set(Calendar.YEAR, GetterUtil.getInteger(date[0]));
							calendar.set(Calendar.MONTH, GetterUtil.getInteger(date[1]) - 1);
							calendar.set(Calendar.DATE, GetterUtil.getInteger(date[2]));
							%>

							<liferay-ui:input-date
								dayParam='<%= key + "Day" %>'
								dayValue="<%= calendar.get(Calendar.DATE) %>"
								disabled="<%= false %>"
								firstDayOfWeek="<%= calendar.getFirstDayOfWeek() - 1 %>"
								monthParam='<%= key + "Month" %>'
								monthValue="<%= calendar.get(Calendar.MONTH) %>"
								yearParam='<%= key +"Year" %>'
								yearValue="<%= calendar.get(Calendar.YEAR) %>"
							/>
						</aui:col>

						<aui:col columnWidth="<%= 50 %>">
							<aui:select label="" name='<%= "useVariable" + key %>' onChange='<%= "useVariable" + key + "();" %>' showEmptyOption="<%= true %>">
								<aui:option label="start-date" value="startDate" />
								<aui:option label="end-date" value="endDate" />
							</aui:select>

							<script type="text/javascript">
								function useVariable<%= key %>() {
									var A = AUI();

									var type = A.one('#<%= renderResponse.getNamespace() + "useVariable" + key %>').get('value');
									var day = A.one('#<%= renderResponse.getNamespace()+ key + "Day" %>');
									var month = A.one('#<%= renderResponse.getNamespace()+ key + "Month" %>');
									var year = A.one('#<%= renderResponse.getNamespace()+ key + "Year" %>');

									if ((type == 'startDate') || (type =='endDate')) {
										day.attr('disabled', 'disabled');
										month.attr('disabled', 'disabled');
										year.attr('disabled', 'disabled');

										if (type =='endDate') {
											document.<portlet:namespace />fm.<portlet:namespace />endDateType[1].checked = 'true';
										}
									}
									else {
										day.attr('disabled', '');
										month.attr('disabled', '');
										year.attr('disabled', '');
									}
								}
							</script>
						</aui:col>
					</c:when>
					<c:otherwise>
						<aui:col width="<%= 20 %>">
							<%= key %>
						</aui:col>

						<aui:col width="<%= 80 %>">
							<span class="field field-text" id="aui_3_2_0_1428">
								<input class="form-control" name="<portlet:namespace /><%= "parameterValue" + key %>" type="text" value="<%= value %>" /><br />
							</span>
						</aui:col>
					</c:otherwise>
				</c:choose>
			</aui:row>

		<%
		}
		%>

	</aui:field-wrapper>

	<aui:input label="email-notifications" name="emailNotifications" type="text" />

	<aui:input label="email-recipient" name="emailDelivery" type="text" />

	<aui:field-wrapper label="permissions">
		<liferay-ui:input-permissions modelName="<%= Entry.class.getName() %>" />
	</aui:field-wrapper>

	<aui:button-row>
		<aui:button type="submit" value="schedule" />

		<aui:button href="<%= searchDefinitionURL %>" type="cancel" />
	</aui:button-row>
</aui:form>