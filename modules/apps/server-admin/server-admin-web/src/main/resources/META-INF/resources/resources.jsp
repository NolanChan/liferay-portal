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
long uptimeDiff = System.currentTimeMillis() - PortalUtil.getUptime().getTime();
long days = uptimeDiff / Time.DAY;
long hours = (uptimeDiff / Time.HOUR) % 24;
long minutes = (uptimeDiff / Time.MINUTE) % 60;
long seconds = (uptimeDiff / Time.SECOND) % 60;

Runtime runtime = Runtime.getRuntime();

numberFormat = NumberFormat.getInstance(locale);

long totalMemory = runtime.totalMemory();
long usedMemory = totalMemory - runtime.freeMemory();
%>

<liferay-ui:panel-container extended="<%= true %>" id="adminServerAdministrationActionsPanelContainer" persistState="<%= true %>">
	<liferay-ui:panel collapsible="<%= false %>" extended="<%= false %>" id="adminServerInformationPanel" markupView="lexicon" title="">
		<div class="alert alert-info">
			<strong><liferay-ui:message key="info" /></strong>: <%= ReleaseInfo.getReleaseInfo() %>
			<strong><liferay-ui:message key="uptime" /></strong>:

			<c:if test="<%= days > 0 %>">
				<%= days %> <%= LanguageUtil.get(request, ((days > 1) ? "days" : "day")) %>,
			</c:if>

			<%= numberFormat.format(hours) %>:<%= numberFormat.format(minutes) %>:<%= numberFormat.format(seconds) %>
		</div>
		<div>
			<portlet:resourceURL id="/server_admin/view_chart" var="totalMemoryChartURL">
				<portlet:param name="type" value="total" />
				<portlet:param name="totalMemory" value="<%= String.valueOf(totalMemory) %>" />
				<portlet:param name="usedMemory" value="<%= String.valueOf(usedMemory) %>" />
			</portlet:resourceURL>

			<img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="memory-used-vs-total-memory" />" src="<%= totalMemoryChartURL %>" />

			<portlet:resourceURL id="/server_admin/view_chart" var="maxMemoryChartURL">
				<portlet:param name="type" value="max" />
				<portlet:param name="maxMemory" value="<%= String.valueOf(runtime.maxMemory()) %>" />
				<portlet:param name="usedMemory" value="<%= String.valueOf(usedMemory) %>" />
			</portlet:resourceURL>

			<img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="memory-used-vs-max-memory" />" src="<%= maxMemoryChartURL %>" />
		</div>

		<br />

		<table class="lfr-table">
		<tr>
			<td>
				<liferay-ui:message key="used-memory" />:
			</td>
			<td>
				<%= numberFormat.format(usedMemory) %> <liferay-ui:message key="bytes" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="total-memory" />:
			</td>
			<td>
				<%= numberFormat.format(runtime.totalMemory()) %> <liferay-ui:message key="bytes" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="maximum-memory" />:
			</td>
			<td>
				<%= numberFormat.format(runtime.maxMemory()) %> <liferay-ui:message key="bytes" />
			</td>
		</tr>
		</table>

		<br />
	</liferay-ui:panel>

	<liferay-ui:panel collapsible="<%= true %>" cssClass="server-admin-actions-panel" extended="<%= false %>" id="adminServerAdministrationSystemActionsPanel" markupView="lexicon" persistState="<%= true %>" title="system-actions">
		<div class="table-responsive">
			<table class="table">
				<tr>
					<td>
						<h5><liferay-ui:message key="run-the-garbage-collector-to-free-up-memory" /></h5>
					</td>
					<td>
						<aui:button cssClass="save-server-button" data-cmd="gc" value="execute" />
					</td>
				</tr>
				<tr>
					<td>
						<h5><liferay-ui:message key="generate-thread-dump" /></h5>
					</td>
					<td>
						<aui:button cssClass="save-server-button" data-cmd="threadDump" value="execute" />
					</td>
				</tr>
			</table>
		</div>
	</liferay-ui:panel>

	<liferay-ui:panel collapsible="<%= true %>" cssClass="server-admin-actions-panel" extended="<%= false %>" id="adminServerAdministrationCacheActionsPanel" markupView="lexicon" persistState="<%= true %>" title="cache-actions">
		<div class="table-responsive">
			<table class="table">
				<tr>
					<td>
						<h5><liferay-ui:message key="clear-content-cached-by-this-vm" /></h5>
					</td>
					<td>
						<aui:button cssClass="save-server-button" data-cmd="cacheSingle" value="execute" />
					</td>
				</tr>
				<tr>
					<td>
						<h5><liferay-ui:message key="clear-content-cached-across-the-cluster" /></h5>
					</td>
					<td>
						<aui:button cssClass="save-server-button" data-cmd="cacheMulti" value="execute" />
					</td>
				</tr>
				<tr>
					<td>
						<h5><liferay-ui:message key="clear-the-database-cache" /></h5>
					</td>
					<td>
						<aui:button cssClass="save-server-button" data-cmd="cacheDb" value="execute" />
					</td>
				</tr>
				<tr>
					<td>
						<h5><liferay-ui:message key="clear-the-direct-servlet-cache" /></h5>
					</td>
					<td>
						<aui:button cssClass="save-server-button" data-cmd="cacheServlet" value="execute" />
					</td>
				</tr>
			</table>
		</div>
	</liferay-ui:panel>

	<liferay-ui:panel collapsible="<%= true %>" cssClass="server-admin-actions-panel" extended="<%= false %>" id="adminServerAdministrationIndexActionsPanel" markupView="lexicon" persistState="<%= true %>" title="index-actions">
		<div class="table-responsive">
			<table class="table">
				<tr>
					<td>
						<h5><liferay-ui:message key="reindex-all-search-indexes" /></h5>
					</td>
					<td>

						<%
						long timeout = ParamUtil.getLong(request, "timeout");
						%>

						<aui:button cssClass="save-server-button" data-blocking='<%= ParamUtil.getBoolean(request, "blocking") %>' data-cmd="reindex" data-timeout="<%= (timeout == 0) ? StringPool.BLANK : timeout %>" value="execute" />
					</td>
				</tr>
				<tr>
					<td>
						<h5><liferay-ui:message key="reindex-all-spell-check-indexes" /></h5>
					</td>
					<td>
						<aui:button cssClass="save-server-button" data-cmd="reindexDictionaries" value="execute" />
					</td>
				</tr>

				<%
				List<Indexer<?>> indexers = new ArrayList<>(IndexerRegistryUtil.getIndexers());

				Collections.sort(indexers, new IndexerClassNameComparator(true));

				for (Indexer<?> indexer : indexers) {
				%>

					<tr>
						<td>
							<h5><liferay-ui:message arguments="<%= indexer.getClassName() %>" key="reindex-x" /></h5>
						</td>
						<td>
							<aui:button cssClass="save-server-button" data-classname="<%= indexer.getClassName() %>" data-cmd="reindex" disabled="<%= !indexer.isIndexerEnabled() %>" value="execute" />
						</td>
					</tr>

				<%
				}
				%>

			</table>
		</div>
	</liferay-ui:panel>

	<liferay-ui:panel collapsible="<%= true %>" cssClass="server-admin-actions-panel" extended="<%= false %>" id="adminServerAdministrationVerificationActionsPanel" markupView="lexicon" persistState="<%= true %>" title="verification-actions">
		<div class="table-responsive">
			<table class="table">
				<tr>
					<td>
						<h5><liferay-ui:message key="verify-database-tables-of-all-plugins" /></h5>
					</td>
					<td>
						<aui:button cssClass="save-server-button" data-cmd="verifyPluginTables" value="execute" />
					</td>
				</tr>
				<tr>
					<td>
						<h5><liferay-ui:message key="verify-membership-policies" /></h5>
					</td>
					<td>
						<aui:button cssClass="save-server-button" data-cmd="verifyMembershipPolicies" value="execute" />
					</td>
				</tr>
			</table>
		</div>
	</liferay-ui:panel>

	<liferay-ui:panel collapsible="<%= true %>" cssClass="server-admin-actions-panel" extended="<%= false %>" id="adminServerAdministrationCleanUpActionsPanel" markupView="lexicon" persistState="<%= true %>" title="clean-up-actions">
		<div class="table-responsive">
			<table class="table">
				<tr>
					<td>
						<h5><liferay-ui:message key="reset-preview-and-thumbnail-files-for-documents-and-media-portlet" /></h5>
					</td>
					<td>
						<aui:button cssClass="save-server-button" data-cmd="dlPreviews" value="execute" />
					</td>
				</tr>
				<tr>
					<td>
						<h5><liferay-ui:message key="clean-up-permissions" /> <liferay-ui:icon-help message="clean-up-permissions-help" /></h5>
					</td>
					<td>
						<aui:button cssClass="save-server-button" data-cmd="cleanUpPermissions" value="execute" />
					</td>
				</tr>
			</table>
		</div>
	</liferay-ui:panel>
</liferay-ui:panel-container>