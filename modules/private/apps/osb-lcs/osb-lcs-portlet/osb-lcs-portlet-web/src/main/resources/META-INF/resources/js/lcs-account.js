AUI.add(
	'lcs-account',
	function(A) {
		var CSS_NOTIFICATION_CONTAINER = '.control-group.field-wrapper';

		var EVENT_DATE_CLICK = 'dateClick';

		var ROWS_PER_PAGE = 10;

		var STR_BLANK = '';

		var STR_CHANGE = 'change';

		var STR_DATE_PICKER = 'DatePicker';

		var STR_END_DATE = 'endDate';

		var STR_ERROR = 'error';

		var STR_OPACITY = 'opacity';

		var STR_OVERRIDDEN = 'overridden';

		var STR_START_DATE = 'startDate';

		var STR_UPDATED = 'updated';

		var TPL_OPTION = '<option value="{id}">{name}</option>';

		var LCSAccount = A.Component.create(
			{
				AUGMENTS: [Liferay.LCSBase, Liferay.PortletBase],

				EXTENDS: A.Base,

				NAME: 'lcs-account',

				prototype: {
					initializeEditEmailNotificationsPage: function(config) {
						var instance = this;

						var selected = config.selected;

						var form = instance.byId('fm');
						var notificationsContainer = instance.byId('notifications-container');
						var projectNode = instance.byId('lcsProjectId');
						var saveNotificationsButton = instance.byId('saveNotifications');

						var lcsConstants = instance._lcsConstants;

						instance._projectNode = projectNode;
						instance._saveNotificationsButton = saveNotificationsButton;

						instance._labels = config.labels;
						instance._projectsLCSNotifications = config.projectsLCSNotifications;

						instance._environmentNode = instance.byId('lcsClusterEntryId');
						instance._serverNode = instance.byId('lcsClusterNodeId');

						instance._updateNotifications(
							{
								environmentId: selected.environmentId || lcsConstants.ALL_ID,
								projectId: selected.projectId || projectNode.val(),
								serverId: selected.serverId || lcsConstants.ALL_ID
							}
						);

						if (selected.projectId == 0) {
							instance._initializeNotificationScopeSelects();
						}

						if (form) {
							form.on(
								'submit',
								function(event) {
									var notificationsInput = instance.byId('notifications');
									var notificationTypesInput = instance.byId('notificationTypes');

									var bufferEnabled = [];
									var bufferTypes = [];

									var updatedNotifications = instance.all(CSS_NOTIFICATION_CONTAINER + '.' + STR_UPDATED);

									updatedNotifications.each(
										function(item, index) {
											var checkbox = item.one('input[type="checkbox"]');
											var input = item.one('input[type="hidden"]');

											bufferEnabled.push(input.val());
											bufferTypes.push(checkbox.attr('data-type'));
										}
									);

									notificationsInput.val(bufferEnabled.join());
									notificationTypesInput.val(bufferTypes.join());

									var eventAction = 'RuleAdded';

									if (selected.projectId) {
										eventAction = 'RuleChanged';
									}

									instance._sendGAEvent(
										{
											action: eventAction,
											category: 'Notifications'
										}
									);

								}
							);
						}

						if (notificationsContainer) {
							notificationsContainer.delegate(
								'click',
								function(event) {
									var notificationContainer = event.currentTarget.ancestor(CSS_NOTIFICATION_CONTAINER);

									if (notificationContainer) {
										notificationContainer.addClass(STR_UPDATED);
									}

									Liferay.Util.toggleDisabled(instance._saveNotificationsButton, false);
								},
								'.notification'
							);

							new A.TooltipDelegate(
								{
									container: notificationsContainer,
									trigger: '.requirement'
								}
							);
						}
					},

					initializeMessagesPage: function(config) {
						var instance = this;

						instance._labels = config.labels;
						instance._urls = config.urls;

						instance._lcsMessagesTableContainer = instance.byId('lcsMessagesTableContainer');
						instance._messageContainer = instance.byId('messageContainer');
						instance._updatingNode = instance.byId('updating');

						var endDateInput = instance.byId(STR_END_DATE);

						var endDatePicker = Liferay.component(instance.ns(STR_END_DATE) + STR_DATE_PICKER);

						endDatePicker.useInputNode(endDateInput);

						var endDateCalendar = endDatePicker.getCalendar();

						var getLCSMessages = function(event) {
							instance._getLCSMessages();
						};

						endDateCalendar.after(EVENT_DATE_CLICK, getLCSMessages);

						var startDateInput = instance.byId(STR_START_DATE);

						var startDatePicker = Liferay.component(instance.ns(STR_START_DATE) + STR_DATE_PICKER);

						startDatePicker.useInputNode(startDateInput);

						var startDateCalendar = startDatePicker.getCalendar();

						startDateCalendar.after(EVENT_DATE_CLICK, getLCSMessages);

						getLCSMessages();
					},

					initializeViewEmailNotificationsPage: function(config) {
						var instance = this;

						var notificationRulesContainer = instance.byId('notificationRules');

						if (notificationRulesContainer) {
							instance._initializeSelectableRows(
								{
									container: notificationRulesContainer
								}
							);
						}
					},

					_getLCSMessages: function() {
						var instance = this;

						var form = instance.byId('fm');

						var labels = instance._labels;
						var messageContainer = instance._messageContainer;
						var updatingNode = instance._updatingNode;

						var msgError = labels.msgError;

						updatingNode.show();

						A.io.request(
							instance._urls.getUserLCSMessages,
							{
								dataType: 'json',
								form: {
									id: form.getDOM()
								},
								on: {
									failure: function(event, id, obj) {
										instance._showMessage(
											{
												container: messageContainer,
												message: msgError,
												type: STR_ERROR
											}
										);

										updatingNode.hide();
									},
									success: function(event, id, obj) {
										var responseData = this.get('responseData');

										var lcsConstants = instance._lcsConstants;

										if (responseData[lcsConstants.JSON_KEY_RESULT] == lcsConstants.JSON_VALUE_SUCCESS) {
											var data = responseData[lcsConstants.JSON_KEY_DATA];

											var hasData = 1;

											if (!data.length) {
												instance._showMessage(
													{
														container: messageContainer,
														message: labels.msgNoData,
														type: 'info'
													}
												);

												hasData = 0;
											}
											else {
												var table = instance._lcsMessagesTable;

												if (table) {
													instance._updateSearchableTable(
														{
															data: data,
															table: table
														}
													);
												}
												else {
													instance._initializeLCSMessagesTable(data);
												}

												messageContainer.hide();
											}

											instance._lcsMessagesTableContainer.setStyle(STR_OPACITY, hasData);
										}
										else {
											instance._showMessage(
												{
													container: messageContainer,
													message: msgError,
													type: STR_ERROR
												}
											);
										}

										updatingNode.hide();
									}
								}
							}
						);
					},

					_initializeLCSMessagesTable: function(data) {
						var instance = this;

						var labels = instance._labels;

						var columns = [
							{
								key: 'from',
								label: labels.from
							},
							{
								allowHTML: true,
								key: 'content',
								label: labels.content
							},
							{
								formatter: function(obj) {
									return obj.data.timeLabel;
								},
								key: 'timestamp',
								label: labels.time
							}
						];

						var tableContainer = instance._lcsMessagesTableContainer;

						var tableContent = tableContainer.one('.content');

						if (tableContent) {
							var table = new A.DataTable(
								{
									columnset: columns,
									pageSizes: [ROWS_PER_PAGE, labels.all],
									recordset: data,
									rowsPerPage: ROWS_PER_PAGE,
									sortable: true
								}
							).render(tableContent);

							var paginator = tableContainer.one('.yui3-datatable-paginator-wrapper');

							if (paginator) {
								paginator.toggle(data.length > ROWS_PER_PAGE);

								paginator.all('button').addClass('.btn');
							}

							instance._initializeTableSearch(
								{
									container: tableContainer,
									data: data,
									paginator: paginator,
									rowsPerPage: ROWS_PER_PAGE,
									table: table
								}
							);

							instance._lcsMessagesTable = table;

							tableContainer.setStyle(STR_OPACITY, 1);
						}
					},

					_initializeNotificationScopeSelects: function() {
						var instance = this;

						var environmentSelect = instance._environmentNode;
						var labels = instance._labels;
						var lcsConstants = instance._lcsConstants;
						var projects = instance._projectsLCSNotifications;
						var projectSelect = instance._projectNode;
						var serverSelect = instance._serverNode;

						var optionAll = {
							id: lcsConstants.ALL_ID,
							name: labels.all
						};

						var environments = {};
						var servers = {};

						for (var i = 0; i < projects.length; i++) {
							var project = projects[i];

							var projectEnvironments = project.environments;
							var projectId = project.id;

							environments[projectId] = [optionAll];

							for (var j = 0; j < projectEnvironments.length; j++) {
								var environment = projectEnvironments[j];

								environments[projectId].push(environment);

								var environmentId = environment.id;

								servers[environmentId] = [optionAll];

								if (!environment.isCluster) {
									var environmentServers = environment.servers;

									for (var k = 0; k < environmentServers.length; k++) {
										var server = environmentServers[k];

										servers[environmentId].push(server);
									}
								}
							}
						}

						environmentSelect.val(lcsConstants.ALL_ID);

						environmentSelect.on(
							STR_CHANGE,
							function(event) {
								var environmentId = event.currentTarget.val();

								var html = STR_BLANK;

								if (environmentId == lcsConstants.ALL_ID) {
									html = instance._createTemplatedHTML(
										{
											propertiesArray: [optionAll],
											template: TPL_OPTION
										}
									);
								}
								else {
									html = instance._createTemplatedHTML(
										{
											propertiesArray: servers[environmentId],
											template: TPL_OPTION
										}
									);
								}

								serverSelect.html(html);

								instance._updateNotifications(
									{
										environmentId: environmentId,
										projectId: projectSelect.val(),
										serverId: lcsConstants.ALL_ID
									}
								);
							}
						);

						projectSelect.on(
							STR_CHANGE,
							function(event) {
								var projectId = event.currentTarget.val();

								var projectEnvironments = environments[projectId];

								var html = instance._createTemplatedHTML(
									{
										propertiesArray: projectEnvironments,
										template: TPL_OPTION
									}
								);

								environmentSelect.html(html);

								html = instance._createTemplatedHTML(
									{
										propertiesArray: [optionAll],
										template: TPL_OPTION
									}
								);

								serverSelect.html(html);

								instance._updateNotifications(
									{
										environmentId: lcsConstants.ALL_ID,
										projectId: projectId,
										serverId: lcsConstants.ALL_ID
									}
								);
							}
						);

						serverSelect.on(
							STR_CHANGE,
							function(event) {
								instance._updateNotifications(
									{
										environmentId: environmentSelect.val(),
										projectId: projectSelect.val(),
										serverId: event.currentTarget.val()
									}
								);
							}
						);
					},

					_updateNotifications: function(config) {
						var instance = this;

						var environmentId = config.environmentId;
						var projectId = config.projectId;
						var serverId = config.serverId;

						var lcsConstants = instance._lcsConstants;

						var notificationContainers = instance.all(CSS_NOTIFICATION_CONTAINER);

						notificationContainers.removeClass(STR_OVERRIDDEN);
						notificationContainers.removeClass(STR_UPDATED);

						var projectsLCSNotifications = instance._projectsLCSNotifications;

						var notifications = [];

						for (var i = 0; i < projectsLCSNotifications.length; i++) {
							var project = projectsLCSNotifications[i];

							if (project.id != projectId) {
								continue;
							}

							if (environmentId == lcsConstants.ALL_ID) {
								notifications = project.notifications;

								break;
							}

							var projectEnvironments = project.environments;

							for (var j = 0; j < projectEnvironments.length; j++) {
								var environment = projectEnvironments[j];

								if (environment.id != environmentId) {
									continue;
								}

								if (serverId == lcsConstants.ALL_ID || environment.isCluster) {
									notifications = environment.notifications;

									break;
								}

								var environmentServers = environment.servers;

								for (var k = 0; k < environmentServers.length; k++) {
									var server = environmentServers[k];

									if (server.id != serverId) {
										continue;
									}

									notifications = server.notifications;

									break;
								}
							}
						}

						for (var l = 0; l < notifications.length; l++) {
							var notification = notifications[l];

							var enabled = notification.enabled;
							var inherited = notification.inherited;
							var type = notification.type;

							var notificationType = 'notification' + type;

							var checkbox = instance.byId(notificationType + 'Checkbox');
							var input = instance.byId(notificationType);

							checkbox.attr('checked', enabled);
							input.val(enabled);

							if (!inherited) {
								var container = checkbox.ancestor(CSS_NOTIFICATION_CONTAINER);

								container.addClass(STR_OVERRIDDEN);
							}
						}

						Liferay.Util.toggleDisabled(instance._saveNotificationsButton, true);
					}
				}
			}
		);

		Liferay.Portlet.LCSAccount = LCSAccount;
	},
	'',
	{
		requires: ['aui-datatable', 'datatable-paginator', 'lcs-base', 'liferay-portlet-base']
	}
);