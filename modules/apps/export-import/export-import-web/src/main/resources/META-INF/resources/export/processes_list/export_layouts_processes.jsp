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

<%@ include file="/export/init.jsp" %>

<%
long groupId = ParamUtil.getLong(request, "groupId");
boolean privateLayout = ParamUtil.getBoolean(request, "privateLayout");
String displayStyle = ParamUtil.getString(request, "displayStyle");
String navigation = ParamUtil.getString(request, "navigation");
String orderByCol = ParamUtil.getString(request, "orderByCol");
String orderByType = ParamUtil.getString(request, "orderByType");
String searchContainerId = ParamUtil.getString(request, "searchContainerId");

PortletURL portletURL = liferayPortletResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "exportLayoutsView");
portletURL.setParameter("groupId", String.valueOf(groupId));
portletURL.setParameter("privateLayout", String.valueOf(privateLayout));
portletURL.setParameter("displayStyle", displayStyle);
portletURL.setParameter("navigation", navigation);
portletURL.setParameter("orderByCol", orderByCol);
portletURL.setParameter("orderByType", orderByType);
portletURL.setParameter("searchContainerId", searchContainerId);

OrderByComparator<BackgroundTask> orderByComparator = BackgroundTaskComparatorFactoryUtil.getBackgroundTaskOrderByComparator(orderByCol, orderByType);
%>

<div id="<portlet:namespace />exportProcessesSearchContainer">
	<liferay-ui:search-container
		emptyResultsMessage="no-export-processes-were-found"
		id="<%= searchContainerId %>"
		iteratorURL="<%= portletURL %>"
		orderByCol="<%= orderByCol %>"
		orderByComparator="<%= orderByComparator %>"
		orderByType="<%= orderByType %>"
		rowChecker="<%= new EmptyOnClickRowChecker(liferayPortletResponse) %>"
	>
		<liferay-ui:search-container-results>

			<%
			int backgroundTasksCount = 0;
			List<BackgroundTask> backgroundTasks = null;

			if (navigation.equals("all")) {
				backgroundTasksCount = BackgroundTaskManagerUtil.getBackgroundTasksCount(groupId, BackgroundTaskExecutorNames.LAYOUT_EXPORT_BACKGROUND_TASK_EXECUTOR);
				backgroundTasks = BackgroundTaskManagerUtil.getBackgroundTasks(groupId, BackgroundTaskExecutorNames.LAYOUT_EXPORT_BACKGROUND_TASK_EXECUTOR, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());
			}
			else {
				boolean completed = false;

				if (navigation.equals("completed")) {
					completed = true;
				}

				backgroundTasksCount = BackgroundTaskManagerUtil.getBackgroundTasksCount(groupId, BackgroundTaskExecutorNames.LAYOUT_EXPORT_BACKGROUND_TASK_EXECUTOR, completed);
				backgroundTasks = BackgroundTaskManagerUtil.getBackgroundTasks(groupId, BackgroundTaskExecutorNames.LAYOUT_EXPORT_BACKGROUND_TASK_EXECUTOR, completed, searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());
			}

			searchContainer.setResults(backgroundTasks);
			searchContainer.setTotal(backgroundTasksCount);
			%>

		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.backgroundtask.BackgroundTask"
			keyProperty="backgroundTaskId"
			modelVar="backgroundTask"
		>

			<c:choose>
				<c:when test='<%= displayStyle.equals("descriptive") %>'>
					<liferay-ui:search-container-column-text>
						<liferay-ui:user-portrait
							cssClass="user-icon-lg"
							userId="<%= backgroundTask.getUserId() %>"
						/>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						colspan="<%= 2 %>"
					>

						<%
						User backgroundTaskUser = UserLocalServiceUtil.getUser(backgroundTask.getUserId());

						Date createDate = backgroundTask.getCreateDate();

						String modifiedDateDescription = LanguageUtil.getTimeDescription(request, System.currentTimeMillis() - createDate.getTime(), true);
						%>

						<h6 class="text-default">
							<liferay-ui:message arguments="<%= new String[] {HtmlUtil.escape(backgroundTaskUser.getFullName()), modifiedDateDescription} %>" key="x-modified-x-ago" />
						</h6>

						<%
						String backgroundTaskName = backgroundTask.getName();

						if (backgroundTaskName.equals(StringPool.BLANK)) {
							backgroundTaskName = LanguageUtil.get(request, "untitled");
						}
						%>

						<h5>
							<%= HtmlUtil.escape(backgroundTaskName) %>

							<%
							List<FileEntry> attachmentsFileEntries = backgroundTask.getAttachmentsFileEntries();

							for (FileEntry fileEntry : attachmentsFileEntries) {
							%>

								<liferay-ui:icon
									icon="download"
									markupView="lexicon"
									method="get"
									url="<%= PortletFileRepositoryUtil.getDownloadPortletFileEntryURL(themeDisplay, fileEntry, StringPool.BLANK) %>"
								/>

							<%
							}
							%>

						</h5>

						<c:if test="<%= backgroundTask.isInProgress() %>">

							<%
							BackgroundTaskStatus backgroundTaskStatus = BackgroundTaskStatusRegistryUtil.getBackgroundTaskStatus(backgroundTask.getBackgroundTaskId());
							%>

							<c:if test="<%= backgroundTaskStatus != null %>">

								<%
								Map<String, Serializable> taskContextMap = backgroundTask.getTaskContextMap();

								String cmd = (String)taskContextMap.get(Constants.CMD);

								int percentage = 100;

								long allModelAdditionCountersTotal = GetterUtil.getLong(backgroundTaskStatus.getAttribute("allModelAdditionCountersTotal"));
								long allPortletAdditionCounter = GetterUtil.getLong(backgroundTaskStatus.getAttribute("allPortletAdditionCounter"));
								long currentModelAdditionCountersTotal = GetterUtil.getLong(backgroundTaskStatus.getAttribute("currentModelAdditionCountersTotal"));
								long currentPortletAdditionCounter = GetterUtil.getLong(backgroundTaskStatus.getAttribute("currentPortletAdditionCounter"));

								long allProgressBarCountersTotal = allModelAdditionCountersTotal + allPortletAdditionCounter;
								long currentProgressBarCountersTotal = currentModelAdditionCountersTotal + currentPortletAdditionCounter;

								if (allProgressBarCountersTotal > 0) {
									int base = 100;

									String phase = GetterUtil.getString(backgroundTaskStatus.getAttribute("phase"));

									if (phase.equals(Constants.EXPORT) && !Validator.equals(cmd, Constants.PUBLISH_TO_REMOTE)) {
										base = 50;
									}

									percentage = Math.round((float)currentProgressBarCountersTotal / allProgressBarCountersTotal * base);
								}
								%>

								<div class="active progress progress-striped progress-xs">
									<div class="progress-bar" style="width: <%= percentage %>%;">
										<c:if test="<%= (allProgressBarCountersTotal > 0) && (!Validator.equals(cmd, Constants.PUBLISH_TO_REMOTE) || (percentage < 100)) %>">
											<%= percentage + StringPool.PERCENT %>
										</c:if>
									</div>
								</div>

								<%
								String stagedModelName = (String)backgroundTaskStatus.getAttribute("stagedModelName");
								String stagedModelType = (String)backgroundTaskStatus.getAttribute("stagedModelType");
								%>

								<c:choose>
									<c:when test="<%= Validator.equals(cmd, Constants.PUBLISH_TO_REMOTE) && (percentage == 100) %>">
										<div class="progress-current-item">
											<strong><liferay-ui:message key="please-wait-as-the-publication-processes-on-the-remote-site" /></strong>
										</div>
									</c:when>
									<c:when test="<%= Validator.isNotNull(stagedModelName) && Validator.isNotNull(stagedModelType) %>">

										<%
										String messageKey = "exporting";

										if (Validator.equals(cmd, Constants.IMPORT)) {
											messageKey = "importing";
										}
										else if (Validator.equals(cmd, Constants.PUBLISH_TO_LIVE) || Validator.equals(cmd, Constants.PUBLISH_TO_REMOTE)) {
											messageKey = "publishing";
										}
										%>

										<div class="progress-current-item">
											<strong><liferay-ui:message key="<%= messageKey %>" /><%= StringPool.TRIPLE_PERIOD %></strong> <%= ResourceActionsUtil.getModelResource(locale, stagedModelType) %> <em><%= HtmlUtil.escape(stagedModelName) %></em>
										</div>
									</c:when>
								</c:choose>
							</c:if>
						</c:if>

						<c:if test="<%= Validator.isNotNull(backgroundTask.getStatusMessage()) %>">

							<%
							long[] expandedBackgroundTaskIds = StringUtil.split(GetterUtil.getString(SessionClicks.get(request, "com.liferay.exportimport.web_backgroundTaskIds", null)), 0L);
							%>

							<a class="details-link toggler-header-<%= ArrayUtil.contains(expandedBackgroundTaskIds, backgroundTask.getBackgroundTaskId()) ? "expanded" : "collapsed" %>" data-persist-id="<%= backgroundTask.getBackgroundTaskId() %>" href="#"><liferay-ui:message key="details" /></a>

							<div class="background-task-status-message toggler-content-<%= ArrayUtil.contains(expandedBackgroundTaskIds, backgroundTask.getBackgroundTaskId()) ? "expanded" : "collapsed" %>">
								<liferay-util:include page="/publish_process_message_task_details.jsp" servletContext="<%= application %>">
									<liferay-util:param name="backgroundTaskId" value="<%= String.valueOf(backgroundTask.getBackgroundTaskId()) %>" />
								</liferay-util:include>
							</div>
						</c:if>

						<h6 class="background-task-status-<%= BackgroundTaskConstants.getStatusLabel(backgroundTask.getStatus()) %> <%= BackgroundTaskConstants.getStatusCssClass(backgroundTask.getStatus()) %>">
							<liferay-ui:message key="<%= backgroundTask.getStatusLabel() %>" />
						</h6>
					</liferay-ui:search-container-column-text>
				</c:when>
				<c:when test='<%= displayStyle.equals("list") %>'>
					<liferay-ui:search-container-column-text
						name="user"
					>
						<liferay-ui:user-display
							displayStyle="3"
							showUserDetails="<%= false %>"
							showUserName="<%= false %>"
							userId="<%= backgroundTask.getUserId() %>"
						/>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						name="title"
					>

						<%
						String backgroundTaskName = backgroundTask.getName();

						if (backgroundTaskName.equals(StringPool.BLANK)) {
							backgroundTaskName = LanguageUtil.get(request, "untitled");
						}
						%>

						<liferay-ui:message key="<%= HtmlUtil.escape(backgroundTaskName) %>" />
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-jsp
						name="status"
						path="/publish_process_message.jsp"
					/>

					<liferay-ui:search-container-column-date
						name="create-date"
						orderable="<%= true %>"
						value="<%= backgroundTask.getCreateDate() %>"
					/>

					<liferay-ui:search-container-column-date
						name="completion-date"
						orderable="<%= true %>"
						value="<%= backgroundTask.getCompletionDate() %>"
					/>

					<liferay-ui:search-container-column-text
						name="download"
					>

						<%
						List<FileEntry> attachmentsFileEntries = backgroundTask.getAttachmentsFileEntries();

						for (FileEntry fileEntry : attachmentsFileEntries) {
						%>

							<%
							StringBundler sb = new StringBundler(4);

							sb.append(fileEntry.getTitle());
							sb.append(StringPool.OPEN_PARENTHESIS);
							sb.append(TextFormatter.formatStorageSize(fileEntry.getSize(), locale));
							sb.append(StringPool.CLOSE_PARENTHESIS);
							%>

							<liferay-ui:icon
								iconCssClass="download"
								label="<%= true %>"
								markupView="lexicon"
								message="<%= sb.toString() %>"
								method="get"
								url="<%= PortletFileRepositoryUtil.getDownloadPortletFileEntryURL(themeDisplay, fileEntry, StringPool.BLANK) %>"
							/>

						<%
						}
						%>

					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text>
						<c:if test="<%= !backgroundTask.isInProgress() %>">
							<liferay-ui:icon-menu icon="<%= StringPool.BLANK %>" markupView="lexicon" message="<%= StringPool.BLANK %>" showWhenSingleIcon="<%= true %>">
								<portlet:actionURL name="editExportConfiguration" var="relaunchURL">
									<portlet:param name="mvcRenderCommandName" value="editExportConfiguration" />
									<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.RELAUNCH %>" />
									<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
									<portlet:param name="backgroundTaskId" value="<%= String.valueOf(backgroundTask.getBackgroundTaskId()) %>" />
								</portlet:actionURL>

								<liferay-ui:icon icon="reload" markupView="lexicon" message="relaunch" url="<%= relaunchURL %>" />

								<portlet:actionURL name="deleteBackgroundTask" var="deleteBackgroundTaskURL">
									<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
									<portlet:param name="backgroundTaskId" value="<%= String.valueOf(backgroundTask.getBackgroundTaskId()) %>" />
								</portlet:actionURL>

								<%
								Date completionDate = backgroundTask.getCompletionDate();
								%>

								<liferay-ui:icon-delete
									label="<%= true %>"
									message='<%= ((completionDate != null) && completionDate.before(new Date())) ? "clear" : "cancel" %>'
									url="<%= deleteBackgroundTaskURL %>"
								/>
							</liferay-ui:icon-menu>
						</c:if>
					</liferay-ui:search-container-column-text>
				</c:when>
			</c:choose>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator displayStyle="<%= displayStyle %>" markupView="lexicon" resultRowSplitter="<%= new ExportImportResultRowSplitter() %>" />
	</liferay-ui:search-container>
</div>

<%
int incompleteBackgroundTaskCount = BackgroundTaskManagerUtil.getBackgroundTasksCount(groupId, BackgroundTaskExecutorNames.LAYOUT_EXPORT_BACKGROUND_TASK_EXECUTOR, false);
%>

<div class="hide incomplete-process-message">
	<liferay-util:include page="/incomplete_processes_message.jsp" servletContext="<%= application %>">
		<liferay-util:param name="incompleteBackgroundTaskCount" value="<%= String.valueOf(incompleteBackgroundTaskCount) %>" />
	</liferay-util:include>
</div>