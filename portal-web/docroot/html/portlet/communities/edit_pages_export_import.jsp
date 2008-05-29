<%
/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ include file="/html/portlet/communities/init.jsp" %>

<%
String tabs4 = (String)request.getAttribute("edit_pages.jsp-tab4");

long groupId = ((Long)request.getAttribute("edit_pages.jsp-groupId")).longValue();
long liveGroupId = ((Long)request.getAttribute("edit_pages.jsp-liveGroupId")).longValue();
boolean privateLayout = ((Boolean)request.getAttribute("edit_pages.jsp-privateLayout")).booleanValue();

String rootNodeName = (String)request.getAttribute("edit_pages.jsp-rootNodeName");

PortletURL portletURL = (PortletURL)request.getAttribute("edit_pages.jsp-portletURL");
%>

<liferay-ui:error exception="<%= LayoutImportException.class %>" message="an-unexpected-error-occurred-while-importing-your-file" />

<%
List portletsList = new ArrayList();
Set portletIdsSet = new HashSet();

Iterator itr1 = LayoutLocalServiceUtil.getLayouts(liveGroupId, privateLayout).iterator();

while (itr1.hasNext()) {
	Layout curLayout = (Layout)itr1.next();

	if (curLayout.getType().equals(LayoutConstants.TYPE_PORTLET)) {
		LayoutTypePortlet curLayoutTypePortlet = (LayoutTypePortlet)curLayout.getLayoutType();

		Iterator itr2 = curLayoutTypePortlet.getPortletIds().iterator();

		while (itr2.hasNext()) {
			Portlet curPortlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), (String)itr2.next());

			if (curPortlet != null) {
				PortletDataHandler portletDataHandler = curPortlet.getPortletDataHandlerInstance();

				if ((portletDataHandler != null) && !portletIdsSet.contains(curPortlet.getRootPortletId())) {
					portletIdsSet.add(curPortlet.getRootPortletId());

					portletsList.add(curPortlet);
				}
			}
		}
	}
}

Collections.sort(portletsList, new PortletTitleComparator(application, locale));

String tabs4Names = "export,import";

if (!StringUtil.contains(tabs4Names, tabs4)) {
	tabs4 = "export";
}
%>

<liferay-ui:tabs
	names="<%= tabs4Names %>"
	param="tabs4"
	url="<%= portletURL.toString() %>"
/>

<liferay-ui:error exception="<%= LARFileException.class %>" message="please-specify-a-lar-file-to-import" />
<liferay-ui:error exception="<%= LARTypeException.class %>" message="please-import-a-lar-file-of-the-correct-type" />
<liferay-ui:error exception="<%= LayoutImportException.class %>" message="an-unexpected-error-occurred-while-importing-your-file" />

<c:choose>
	<c:when test='<%= tabs4.equals("export") %>'>
		<script type="text/javascript">
			function <portlet:namespace />toggleDateRange() {
				jQuery("#<portlet:namespace />startEndDate").toggle();
			}
		</script>

		<liferay-ui:message key="export-the-selected-data-to-the-given-lar-file-name" />

		<br /><br />

		<div>
			<input name="<portlet:namespace />exportFileName" size="50" type="text" value="<%= StringUtil.replace(rootNodeName, " ", "_") %>-<%= Time.getShortTimestamp() %>.lar">
		</div>

		<br />

		<div>
			<liferay-ui:input-checkbox param="dateRange" onClick='<%= renderResponse.getNamespace() + "toggleDateRange()" %>' />

			<liferay-ui:message key="date-range" />

			<liferay-ui:icon-help message="export-date-range-help" />
		</div>

		<%
		Calendar today = CalendarFactoryUtil.getCalendar(timeZone, locale);

		Calendar yesterday = CalendarFactoryUtil.getCalendar(timeZone, locale);

		yesterday.add(Calendar.DATE, -1);
		%>

		<br />

		<table class="lfr-table" id="<portlet:namespace />startEndDate" style="display: none;">
		<tr>
			<td>
				<liferay-ui:message key="start-date" />
			</td>
			<td>
				<liferay-ui:input-date
					monthParam="startDateMonth"
					monthValue="<%= yesterday.get(Calendar.MONTH) %>"
					dayParam="startDateDay"
					dayValue="<%= yesterday.get(Calendar.DATE) %>"
					yearParam="startDateYear"
					yearValue="<%= yesterday.get(Calendar.YEAR) %>"
					yearRangeStart="<%= yesterday.get(Calendar.YEAR) - 100 %>"
					yearRangeEnd="<%= yesterday.get(Calendar.YEAR) %>"
					firstDayOfWeek="<%= yesterday.getFirstDayOfWeek() - 1 %>"
					disabled="<%= false %>"
				/>

				&nbsp;

				<liferay-ui:input-time
					hourParam='<%= "startDateHour" %>'
					hourValue="<%= yesterday.get(Calendar.HOUR) %>"
					minuteParam='<%= "startDateMinute" %>'
					minuteValue="<%= yesterday.get(Calendar.MINUTE) %>"
					minuteInterval="1"
					amPmParam='<%= "startDateAmPm" %>'
					amPmValue="<%= yesterday.get(Calendar.AM_PM) %>"
					disabled="<%= false %>"
				/>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="end-date" />
			</td>
			<td>
				<liferay-ui:input-date
					monthParam="endDateMonth"
					monthValue="<%= today.get(Calendar.MONTH) %>"
					dayParam="endDateDay"
					dayValue="<%= today.get(Calendar.DATE) %>"
					yearParam="endDateYear"
					yearValue="<%= today.get(Calendar.YEAR) %>"
					yearRangeStart="<%= today.get(Calendar.YEAR) - 100 %>"
					yearRangeEnd="<%= today.get(Calendar.YEAR) %>"
					firstDayOfWeek="<%= today.getFirstDayOfWeek() - 1 %>"
					disabled="<%= false %>"
				/>

				&nbsp;

				<liferay-ui:input-time
					hourParam='<%= "endDateHour" %>'
					hourValue="<%= today.get(Calendar.HOUR) %>"
					minuteParam='<%= "endDateMinute" %>'
					minuteValue="<%= today.get(Calendar.MINUTE) %>"
					minuteInterval="1"
					amPmParam='<%= "endDateAmPm" %>'
					amPmValue="<%= today.get(Calendar.AM_PM) %>"
					disabled="<%= false %>"
				/>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<br />
			</td>
		</tr>
		</table>

		<liferay-ui:message key="what-would-you-like-to-export" />

		<br /><br />

		<%@ include file="/html/portlet/communities/edit_pages_export_import_options.jspf" %>

		<br />

		<input type="button" value='<liferay-ui:message key="export" />' onClick="<portlet:namespace />exportPages();" />
	</c:when>
	<c:when test='<%= tabs4.equals("import") %>'>
		<c:choose>
			<c:when test="<%= (layout.getGroupId() != groupId) || (layout.isPrivateLayout() != privateLayout) %>">
				<liferay-ui:message key="import-a-lar-file-to-overwrite-the-selected-data" />

				<br /><br />

				<div>
					<input name="<portlet:namespace />importFileName" size="50" type="file" />
				</div>

				<br />

				<liferay-ui:message key="what-would-you-like-to-import" />

				<br /><br />

				<%@ include file="/html/portlet/communities/edit_pages_export_import_options.jspf" %>

				<br />

				<input type="button" value="<liferay-ui:message key="import" />" onClick="<portlet:namespace />importPages();">
			</c:when>
			<c:otherwise>
				<liferay-ui:message key="import-from-within-the-target-community-can-cause-conflicts" />
			</c:otherwise>
		</c:choose>
	</c:when>
</c:choose>

<script type="text/javascript">
	jQuery(function(){
		jQuery(".<portlet:namespace />handler-control input[@type=checkbox]:not([@checked])").parent().parent().parent(".<portlet:namespace />handler-control").children(".<portlet:namespace />handler-control").hide();

		jQuery(".<portlet:namespace />handler-control input[@type=checkbox]").unbind('click').click(function() {
			var input = jQuery(this).parents(".<portlet:namespace />handler-control:first");

			if (this.checked) {
				input.children(".<portlet:namespace />handler-control").show();
			}
			else {
				input.children(".<portlet:namespace />handler-control").hide();
			}
		});
	});
</script>

<%@ include file="/html/portlet/communities/render_controls.jspf" %>