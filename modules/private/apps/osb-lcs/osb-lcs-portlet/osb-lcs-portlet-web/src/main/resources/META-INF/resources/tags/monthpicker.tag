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

<%@ include file="/tags/init.jsp" %>

<%@ tag import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %><%@
tag import="com.liferay.portal.kernel.util.CalendarUtil" %><%@
tag import="com.liferay.portal.kernel.util.StringPool" %>

<%@ tag import="java.util.Calendar" %>

<%@ attribute name="inlineField" required="false" type="Boolean" %>
<%@ attribute name="label" required="false" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="startMonth" required="false" type="Integer" %>
<%@ attribute name="startYear" required="false" type="Integer" %>
<%@ attribute name="valueMonth" required="false" type="Integer" %>
<%@ attribute name="valueYear" required="false" type="Integer" %>

<aui:select cssClass="lcs-monthpicker" inlineField='<%= (inlineField == null) ? false : inlineField %>' label='<%= (label == null) ? "month" : label %>' name="<%= name %>">

	<%
	String[] months = CalendarUtil.getMonths(locale);

	Calendar calendar = CalendarFactoryUtil.getCalendar();

	int selectedMonth = calendar.get(Calendar.MONTH);

	if (valueMonth != null) {
		selectedMonth = valueMonth;
	}

	int selectedYear = calendar.get(Calendar.YEAR);

	if (valueYear != null) {
		selectedYear = valueYear;
	}

	for (int i = 0; i < 10; i++) {
		int year = calendar.get(Calendar.YEAR) - i;

		if ((startYear != null) && (year < startYear)) {
			break;
		}

		int month = calendar.get(Calendar.MONTH);

		if (i != 0) {
			month = months.length - 1;
		}

		for (int j = month; j >= 0; j--) {
			if ((startYear != null) && (startMonth != null) && (year == startYear) && (j < startMonth)) {
				break;
			}
	%>

			<aui:option label="<%= months[j] + StringPool.SPACE + year %>" selected="<%= (j == selectedMonth) && (year == selectedYear) %>" value="<%= year + StringPool.DASH + j %>" />

	<%
		}
	}
	%>

</aui:select>