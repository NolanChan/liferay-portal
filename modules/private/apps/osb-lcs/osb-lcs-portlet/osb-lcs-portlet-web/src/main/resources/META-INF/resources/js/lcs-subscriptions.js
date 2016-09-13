/* global Highcharts */

AUI.add(
	'lcs-subscriptions',
	function(A) {
		var AObject = A.Object;
		var Lang = A.Lang;

		var CSS_BTN = 'btn';

		var CSS_CONTENT = 'content';

		var CSS_SELECTED = 'selected';

		var CSS_TEXT_RIGHT = 'text-right';

		var EVENT_CHANGE = 'change';

		var EVENT_CLICK = 'click';

		var ROWS_PER_PAGE = 10;

		var SELECTOR_BUTTON = 'button';

		var SELECTOR_DATATABLE_PAGINATOR_WRAPPER = '.yui3-datatable-paginator-wrapper';

		var SELECTOR_SELECT = 'select';

		var SELECTOR_TABLE_FOOT = '.table-foot';

		var SELECTOR_UPDATING = '.updating';

		var STR_BLANK = '';

		var STR_DASH = '-';

		var STR_DATA = 'data';

		var STR_DOLLAR = '$';

		var STR_DOT = '.';

		var STR_ERROR = 'error';

		var STR_INFO = 'info';

		var STR_OPACITY = 'opacity';

		var STR_RESPONSE_DATA = 'responseData';

		var STR_SERVERS_ALLOWED = 'serversAllowed';

		var STR_SERVERS_USED = 'serversUsed';

		var STR_SUBSCRIPTION_TYPE_LABEL = 'subscriptionTypeLabel';

		var STR_SUCCESS = 'success';

		var STR_UNDEFINED = 'undefined';

		var STR_UPTIMES_CHART_CONTENT = 'uptimesChartContent';

		var SELECTOR_CONTENT = STR_DOT + CSS_CONTENT;

		var SELECTOR_SELECTED = STR_DOT + CSS_SELECTED;

		var TPL_ACTION_LINK = '<a class="taglib-icon" data-{dataKey}="{dataValue}" href="javascript:;">' +
				'<i class="{iconCss}"></i>' +
				'<span class="taglib-text">{label}</span>' +
			'</a>';

		var TPL_ROW = '<tr>' +
				'<td class="table-cell">{name}</td>' +
				'<td class="table-cell">{value}</td>' +
			'</tr>';

		var TPL_TABLE = '<table class="table">' +
				'<tbody class="table-data">{rows}</tbody>' +
			'</table>';

		var TPL_TABLE_TOTAL = '<tr class="table-total">' +
				'<td colspan="{labelColspan}">{label}: </td>' +
				'<td class="table-total-value">{value}</td>' +
			'</tr>';

		var LCSSubscriptions = A.Component.create(
			{
				AUGMENTS: [Liferay.LCSBase, Liferay.PortletBase],

				EXTENDS: A.Base,

				NAME: 'lcs-subscriptions',

				prototype: {
					initializer: function(config) {
						var instance = this;

						instance._messageContainer = instance.byId('messageContainer');
					},

					initializeElasticSubscriptionsPage: function(config) {
						var instance = this;

						instance._downloadLCSClusterNodeUptimesDelimitedReportURL = config.downloadLCSClusterNodeUptimesDelimitedReportURL;
						instance._downloadLCSClusterNodeUptimesInvoicePDFReportURL = config.downloadLCSClusterNodeUptimesInvoicePDFReportURL;
						instance._getLCSClusterNodeUptimesURL = config.getLCSClusterNodeUptimesURL;

						var labels = config.labels;
						var uptimesLCSClusterEntrySelect = instance.byId('uptimesLCSClusterEntryId');
						var uptimesPeriodSelect = instance.byId('uptimesPeriod');

						instance._uptimesContent = instance.byId('uptimesContent');
						instance._uptimesMessageContainer = instance.byId('uptimesMessageContainer');
						instance._uptimesTableContainer = instance.byId('uptimesTableContainer');

						instance._labels = labels;
						instance._uptimesLCSClusterEntrySelect = uptimesLCSClusterEntrySelect;
						instance._uptimesPeriodSelect = uptimesPeriodSelect;

						var uptimesControls = instance.byId('uptimesControls');

						if (uptimesControls) {
							uptimesControls.delegate(
								EVENT_CHANGE,
								function(event) {
									instance._getLCSClusterNodeUptimes(
										{
											lcsClusterEntryId: uptimesLCSClusterEntrySelect.val(),
											period: uptimesPeriodSelect.val()
										}
									);
								},
								SELECTOR_SELECT
							);

							instance._uptimesUpdatingNode = uptimesControls.one(SELECTOR_UPDATING);
						}

						var uptimesChartContent = instance.byId(STR_UPTIMES_CHART_CONTENT);

						if (uptimesChartContent) {
							instance._uptimesChartContainer = uptimesChartContent.ancestor();
						}

						var regexStr = Lang.sub('^(00({daysAbbreviation}|{hoursAbbreviation}|{minutesAbbreviation}):)+', labels);

						var durationRegex = new RegExp(regexStr);

						instance._formatter = {
							currency: function(obj) {
								var rounded = Math.round(obj.value * 100) / 100;

								return STR_DOLLAR + rounded.toFixed(2);
							},
							duration: function(obj) {
								return obj.value.replace(durationRegex, STR_BLANK);
							},
							hours: function(obj) {
								return Math.round(obj.value) + labels.hoursAbbreviation;
							},
							percentage: function(obj) {
								return obj.value + '%';
							},
							rate: function(obj) {
								var rounded = Math.round(obj.value * 100) / 100;

								return STR_DOLLAR + rounded.toFixed(2) + '/' + labels.hoursAbbreviation;
							}
						};

						if (config.billingEnabled) {
							instance._getPaymentsURL = config.getPaymentsURL;

							instance._paymentsChartContainer = instance.byId('paymentsChartContainer');
							instance._paymentsMessageContainer = instance.byId('paymentsMessageContainer');

							var paymentsControls = instance.byId('paymentsControls');

							instance._paymentsUpdatingNode = paymentsControls.one(SELECTOR_UPDATING);

							paymentsControls.delegate(
								EVENT_CHANGE,
								function(event) {
									instance._getPayments(
										{
											endPeriod: instance.byId('paymentsEndPeriod').val(),
											startPeriod: instance.byId('paymentsStartPeriod').val()
										}
									);
								},
								SELECTOR_SELECT
							);

							instance._getBillsURL = config.getBillsURL;

							var billsControls = instance.byId('billsControls');
							var billsDetailsContainer = instance.byId('billsDetails');
							var billsSummaryContainer = instance.byId('billsSummary');

							instance._billsMessageContainer = instance.byId('billsMessageContainer');

							instance._billsDetailsContainer = billsDetailsContainer;
							instance._billsSummaryContainer = billsSummaryContainer;

							instance._billsDetailsContent = billsDetailsContainer.one(SELECTOR_CONTENT);
							instance._billsSummaryContent = billsSummaryContainer.one(SELECTOR_CONTENT);
							instance._billsUpdatingNode = billsControls.one(SELECTOR_UPDATING);

							billsControls.delegate(
								EVENT_CHANGE,
								function(event) {
									instance._getBills(
										{
											period: instance.byId('billsPeriod').val()
										}
									);
								},
								SELECTOR_SELECT
							);

							instance._getElasticSubscriptionsDefaultsURL = config.getElasticSubscriptionsDefaultsURL;

							instance._getElasticSubscriptionsDefaults();
						}
						else {
							instance._getLCSClusterNodeUptimes(
								{
									lcsClusterEntryId: uptimesLCSClusterEntrySelect.val(),
									period: uptimesPeriodSelect.val()
								}
							);
						}
					},

					initializeSubscriptionsDetailsPage: function(config) {
						var instance = this;

						instance._getSubscriptionsDetailsURL = config.getSubscriptionsDetailsURL;
						instance._subscriptionTypes = config.subscriptionTypes;
						instance._subscriptionTypeElastic = config.subscriptionTypeElastic;
						instance._updateLCSClusterEntryURL = config.updateLCSClusterEntryURL;

						var labels = config.labels;

						var lcsClusterEntriesContainer = instance.byId('lcsClusterEntries');
						var lcsClusterNodesContainer = instance.byId('lcsClusterNodes');
						var subscriptionsContainer = instance.byId('subscriptions');
						var subscriptionsUsageContainer = instance.byId('subscriptionsUsage');

						instance._labels = labels;
						instance._lcsClusterEntriesContainer = lcsClusterEntriesContainer;
						instance._lcsClusterNodesContainer = lcsClusterNodesContainer;
						instance._updatingNode = instance.byId('updating');
						instance._subscriptionsContainer = subscriptionsContainer;
						instance._subscriptionsUsageContainer = subscriptionsUsageContainer;

						instance._lcsClusterEntriesContent = lcsClusterEntriesContainer.one(SELECTOR_CONTENT);
						instance._lcsClusterNodesContent = lcsClusterNodesContainer.one(SELECTOR_CONTENT);
						instance._subscriptionsContent = subscriptionsContainer.one(SELECTOR_CONTENT);
						instance._subscriptionsUsageContent = subscriptionsUsageContainer.one(SELECTOR_CONTENT);

						var refreshLCSSubscriptionEntriesLink = instance.byId('refreshLCSSubscriptionEntries');

						if (refreshLCSSubscriptionEntriesLink) {
							new A.Tooltip(
								{
									bodyContent: labels.msgRefreshOrders,
									trigger: refreshLCSSubscriptionEntriesLink,
									visible: false
								}
							).render();

							refreshLCSSubscriptionEntriesLink.on(
								EVENT_CLICK,
								function(event) {
									instance._getSubscriptionsDetails(true);
								}
							);
						}

						instance._getSubscriptionsDetails(false);
					},

					_createBillsSummaryHTML: function(propertiesArray) {
						var instance = this;

						var charges = propertiesArray[0];
						var tax = propertiesArray[1];
						var total = propertiesArray[2];

						var formatter = instance._formatter;

						charges.value = formatter.currency(charges);
						tax.value = formatter.percentage(tax);
						total.value = formatter.currency(total);

						var rows = A.Array.map(
							propertiesArray,
							function(item) {
								return Lang.sub(TPL_ROW, item);
							}
						).join(STR_BLANK);

						return Lang.sub(
							TPL_TABLE,
							{
								rows: rows
							}
						);
					},

					_downloadUptimeReport: function(baseURL) {
						var instance = this;

						var lcsClusterEntryId = instance._uptimesLCSClusterEntrySelect.val();
						var period = instance._uptimesPeriodSelect.val();

						var periodArray = period.split(STR_DASH);

						var url = new A.Url(baseURL);

						url.addParameter(instance.ns('lcsClusterEntryId'), lcsClusterEntryId);
						url.addParameter(instance.ns('month'), periodArray[1]);
						url.addParameter(instance.ns('year'), periodArray[0]);

						window.location.href = url.toString();
					},

					_generateUptimesChartData: function(uptimes) {
						var instance = this;

						var period = instance._uptimesPeriodSelect.val();

						var periodArray = period.split(STR_DASH);

						var month = periodArray[1];
						var year = periodArray[0];

						var monthStart = new Date(year, month, 1, 0, 0, 0, 0);

						var pointStart = Lang.toInt(monthStart.getTime()) - Lang.toInt(instance._timezoneOffset);

						var environments = A.Array.reduce(
							uptimes,
							{},
							function(curr, item, index) {
								var environmentName = item.environment;

								var environment = curr[environmentName] || {};

								environment.name = environmentName;
								environment.pointInterval = 86400000;
								environment.pointStart = pointStart;

								var servers = environment.servers || {};

								var curDate = new Date(item.startTime);

								do {
									var curDayInMonth = curDate.getDate();

									var dailyServers = servers[curDayInMonth] || {};

									dailyServers[item.server] = true;

									servers[curDayInMonth] = dailyServers;

									curDate.setDate(curDate.getDate() + 1);
								}
								while (curDate.getTime() < item.endTime);

								environment.servers = servers;

								curr[environmentName] = environment;

								return curr;
							}
						);

						var now = new Date();

						var totalMonthDays = now.getDate();

						if (now.getMonth() != month) {
							totalMonthDays = A.DataType.DateMath.getDaysInMonth(year, month);
						}

						var series = A.Object.map(
							environments,
							function(item, index) {
								var environmentData = [];

								var servers = item.servers;

								for (var i = 1; i <= totalMonthDays; i++) {
									var dailyServers = servers[i];

									var size = null;

									if (dailyServers) {
										size = AObject.size(dailyServers);
									}

									environmentData.push(size);
								}

								item.data = environmentData;

								return item;
							}
						);

						return series;
					},

					_getBills: function(data) {
						var instance = this;

						var billsUpdatingNode = instance._billsUpdatingNode;
						var errorMessage = instance._errorMessage;
						var messageContainer = instance._billsMessageContainer;

						billsUpdatingNode.show();

						A.io.request(
							instance._getBillsURL,
							{
								data: instance.ns(data),
								dataType: 'json',
								on: {
									failure: function(event, id, obj) {
										instance._showMessage(
											{
												container: messageContainer,
												message: errorMessage,
												type: STR_ERROR
											}
										);

										billsUpdatingNode.hide();
									},
									success: function(event, id, obj) {
										var responseData = this.get(STR_RESPONSE_DATA);

										var lcsConstants = instance._lcsConstants;

										if (responseData[lcsConstants.JSON_KEY_RESULT] == lcsConstants.JSON_VALUE_SUCCESS) {
											messageContainer.hide();

											var bills = responseData[lcsConstants.JSON_KEY_DATA];

											instance._processBills(bills);
										}
										else {
											instance._showMessage(
												{
													container: messageContainer,
													message: errorMessage,
													type: STR_ERROR
												}
											);
										}

										billsUpdatingNode.hide();
									}
								}
							}
						);
					},

					_getElasticSubscriptionsDefaults: function() {
						var instance = this;

						var errorMessage = instance._errorMessage;
						var messageContainer = instance._uptimesMessageContainer;

						var updatingNodes = instance.all(SELECTOR_UPDATING);

						updatingNodes.show();

						A.io.request(
							instance._getElasticSubscriptionsDefaultsURL,
							{
								dataType: 'json',
								on: {
									failure: function(event, id, obj) {
										instance._showMessage(
											{
												container: messageContainer,
												message: errorMessage,
												type: STR_ERROR
											}
										);

										updatingNodes.hide();
									},
									success: function(event, id, obj) {
										var responseData = this.get(STR_RESPONSE_DATA);

										var lcsConstants = instance._lcsConstants;

										if (responseData[lcsConstants.JSON_KEY_RESULT] == lcsConstants.JSON_VALUE_SUCCESS) {
											var data = responseData[lcsConstants.JSON_KEY_DATA];

											instance._processUptimes(data.lcsClusterNodeUptimes);
											instance._processPayments(data.payments);
											instance._processBills(data.bills);
										}
										else {
											instance._showMessage(
												{
													container: messageContainer,
													message: errorMessage,
													type: STR_ERROR
												}
											);
										}

										updatingNodes.hide();
									}
								}
							}
						);
					},

					_getLCSClusterNodeUptimes: function(data) {
						var instance = this;

						var errorMessage = instance._errorMessage;
						var messageContainer = instance._uptimesMessageContainer;
						var uptimesUpdatingNode = instance._uptimesUpdatingNode;

						uptimesUpdatingNode.show();

						A.io.request(
							instance._getLCSClusterNodeUptimesURL,
							{
								data: instance.ns(data),
								dataType: 'json',
								on: {
									failure: function(event, id, obj) {
										instance._showMessage(
											{
												container: messageContainer,
												message: errorMessage,
												type: STR_ERROR
											}
										);

										uptimesUpdatingNode.hide();
									},
									success: function(event, id, obj) {
										var responseData = this.get(STR_RESPONSE_DATA);

										var lcsConstants = instance._lcsConstants;

										if (responseData[lcsConstants.JSON_KEY_RESULT] == lcsConstants.JSON_VALUE_SUCCESS) {
											messageContainer.hide();

											var uptimes = responseData[lcsConstants.JSON_KEY_DATA];

											instance._processUptimes(uptimes);
										}
										else {
											instance._showMessage(
												{
													container: messageContainer,
													message: errorMessage,
													type: STR_ERROR
												}
											);
										}

										uptimesUpdatingNode.hide();
									}
								}
							}
						);
					},

					_getPayments: function(data) {
						var instance = this;

						var errorMessage = instance._errorMessage;
						var messageContainer = instance._paymentsMessageContainer;
						var paymentsUpdatingNode = instance._paymentsUpdatingNode;

						paymentsUpdatingNode.show();

						A.io.request(
							instance._getPaymentsURL,
							{
								data: instance.ns(data),
								dataType: 'json',
								on: {
									failure: function(event, id, obj) {
										instance._showMessage(
											{
												container: messageContainer,
												message: errorMessage,
												type: STR_ERROR
											}
										);

										paymentsUpdatingNode.hide();
									},
									success: function(event, id, obj) {
										var responseData = this.get(STR_RESPONSE_DATA);

										var lcsConstants = instance._lcsConstants;

										if (responseData[lcsConstants.JSON_KEY_RESULT] == lcsConstants.JSON_VALUE_SUCCESS) {
											var paymentsData = responseData[lcsConstants.JSON_KEY_DATA];

											instance._processPayments(paymentsData);
										}
										else {
											instance._showMessage(
												{
													container: messageContainer,
													message: errorMessage,
													type: STR_ERROR
												}
											);
										}

										paymentsUpdatingNode.hide();
									}
								}
							}
						);
					},

					_getSubscriptionsDetails: function(refresh) {
						var instance = this;

						var data = {
							refresh: refresh
						};

						var errorMessage = instance._errorMessage;
						var messageContainer = instance._messageContainer;
						var updatingNode = instance._updatingNode;

						updatingNode.show();

						A.io.request(
							instance._getSubscriptionsDetailsURL,
							{
								data: instance.ns(data),
								dataType: 'json',
								on: {
									failure: function(event, id, obj) {
										instance._showMessage(
											{
												container: messageContainer,
												message: errorMessage,
												type: STR_ERROR
											}
										);

										updatingNode.hide();
									},
									success: function(event, id, obj) {
										var responseData = this.get(STR_RESPONSE_DATA);

										var lcsConstants = instance._lcsConstants;

										if (responseData[lcsConstants.JSON_KEY_RESULT] == lcsConstants.JSON_VALUE_SUCCESS) {
											var data = responseData[lcsConstants.JSON_KEY_DATA];

											var subscriptions = data.subscriptions;

											if (!subscriptions.length) {
												instance._showMessage(
													{
														container: messageContainer,
														message: instance._labels.msgNoOrders,
														type: STR_INFO
													}
												);
											}
											else {
												var subscriptionsTable = instance._subscriptionsTable;

												if (subscriptionsTable) {
													subscriptionsTable.set(STR_DATA, subscriptions);
												}
												else {
													instance._initializeSubscriptionsTable(subscriptions);
												}

												var subscriptionsUsage = data.subscriptionsUsage;
												var subscriptionsUsageTable = instance._subscriptionsUsageTable;

												if (subscriptionsUsageTable) {
													subscriptionsUsageTable.set(STR_DATA, subscriptionsUsage);
												}
												else {
													instance._initializeSubscriptionsUsageTable(subscriptionsUsage);
												}

												var lcsClusterEntries = data.lcsClusterEntries;
												var lcsClusterEntriesTable = instance._lcsClusterEntriesTable;

												if (lcsClusterEntriesTable) {
													lcsClusterEntriesTable.set(STR_DATA, lcsClusterEntries);
												}
												else {
													instance._initializeLCSClusterEntriesTable(
														{
															data: lcsClusterEntries,
															hasElasticSubscription: data.hasElasticSubscription,
															subscriptionTypes: subscriptionsUsage
														}
													);
												}

												var lcsClusterNodes = data.lcsClusterNodes;
												var lcsClusterNodesContainer = instance._lcsClusterNodesContainer;
												var lcsClusterNodesTable = instance._lcsClusterNodesTable;

												if (lcsClusterNodesTable) {
													instance._updateTable(
														{
															container: lcsClusterNodesContainer,
															data: lcsClusterNodes,
															table: lcsClusterNodesTable
														}
													);
												}
												else {
													instance._initializeLCSClusterNodesTable(lcsClusterNodes);
												}

												instance._lcsClusterEntriesContainer.setStyle(STR_OPACITY, 1);
												instance._subscriptionsContainer.setStyle(STR_OPACITY, 1);
												instance._subscriptionsUsageContainer.setStyle(STR_OPACITY, 1);

												if (lcsClusterNodes.length) {
													lcsClusterNodesContainer.setStyle(STR_OPACITY, 1);
												}
											}
										}
										else {
											instance._showMessage(
												{
													container: messageContainer,
													message: errorMessage,
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

					_initializeBillsDetailsTable: function(data) {
						var instance = this;

						var formatter = instance._formatter;
						var labels = instance._labels;

						var columns = [
							{
								key: 'serverName',
								label: labels.server
							},
							{
								key: 'environmentName',
								label: labels.environment
							},
							{
								className: CSS_TEXT_RIGHT,
								formatter: formatter.hours,
								key: 'uptime',
								label: labels.uptime
							},
							{
								className: CSS_TEXT_RIGHT,
								formatter: formatter.rate,
								key: 'rate',
								label: labels.rate
							},
							{
								className: CSS_TEXT_RIGHT,
								formatter: formatter.currency,
								key: 'amount',
								label: labels.cost
							}
						];

						var tableContent = instance._billsDetailsContent;

						instance._billsDetailsTable = new A.DataTable(
							{
								columnset: columns,
								pageSizes: [ROWS_PER_PAGE, labels.all],
								recordset: data,
								rowsPerPage: ROWS_PER_PAGE,
								sortable: true
							}
						).render(tableContent);

						var paginator = tableContent.one(SELECTOR_DATATABLE_PAGINATOR_WRAPPER);

						if (paginator) {
							paginator.toggle(data.length > ROWS_PER_PAGE);

							paginator.all(SELECTOR_BUTTON).addClass(CSS_BTN);
						}
					},

					_initializeLCSClusterEntriesTable: function(config) {
						var instance = this;

						var labels = instance._labels;
						var lcsClusterEntriesContent = instance._lcsClusterEntriesContent;

						var lcsSubscriptionTypeCellEditor = new A.LCSSubscriptionTypeCellEditor(
							{
								hasElasticSubscription: config.hasElasticSubscription,
								on: {
									save: function(event) {
										if (confirm(labels.msgConfirmSubscriptionType)) {
											var bodyNode = this.bodyNode;

											var subscriptionTypeSelect = bodyNode.one(SELECTOR_SELECT);

											var selectedLCSClusterEntry = lcsClusterEntriesContent.one('.selected a');

											if (subscriptionTypeSelect && selectedLCSClusterEntry) {
												var subscriptionType = subscriptionTypeSelect.val();

												var lcsClusterEntryId = selectedLCSClusterEntry.attr('data-lcsclusterentryid');

												var elastic = false;

												var elasticCheckbox = bodyNode.one('.checkbox');

												if (elasticCheckbox) {
													elastic = elasticCheckbox.attr('checked');
												}

												instance._updateLCSClusterEntry(
													{
														elastic: elastic,
														lcsClusterEntryId: lcsClusterEntryId,
														subscriptionType: subscriptionType
													}
												);
											}
										}

										return false;
									}
								},
								strings: {
									cancel: labels.cancel,
									elastic: labels.elastic,
									elasticHelp: labels.elasticHelp,
									save: labels.save
								},
								subscriptionTypes: config.subscriptionTypes
							}
						);

						instance._lcsSubscriptionTypeCellEditor = lcsSubscriptionTypeCellEditor;

						var columns = [
							{
								key: 'name',
								label: labels.environment
							},
							{
								allowHTML: true,
								editor: lcsSubscriptionTypeCellEditor,
								key: STR_SUBSCRIPTION_TYPE_LABEL,
								label: labels.subscriptionType,
								nodeFormatter: function(obj) {
									var data = obj.data;
									var td = obj.td;

									var html = Liferay.Util.escapeHTML(obj.value);

									if (data.editable) {
										html = Lang.sub(
											TPL_ACTION_LINK,
											{
												dataKey: 'lcsclusterentryid',
												dataValue: data.lcsClusterEntryId,
												iconCss: 'icon-pencil',
												label: html
											}
										);
									}
									else {
										if (data.elastic) {
											html = html + ', ' + labels.elastic;
										}

										td.removeClass('table-cell');
									}

									td.html(html);

									return false;
								}
							}
						];

						instance._lcsClusterEntriesTable = new A.DataTable(
							{
								columnset: columns,
								recordset: config.data,
								sortable: true
							}
						).render(lcsClusterEntriesContent);

						lcsClusterEntriesContent.delegate(
							EVENT_CLICK,
							function(event) {
								var currentTarget = event.currentTarget;

								var selectedCells = lcsClusterEntriesContent.all(SELECTOR_SELECTED);

								selectedCells.removeClass(CSS_SELECTED);

								currentTarget.addClass(CSS_SELECTED);
							},
							'.table-col-subscriptionTypeLabel'
						);
					},

					_initializeLCSClusterNodesTable: function(data) {
						var instance = this;

						var labels = instance._labels;

						var columns = [
							{
								key: 'serverName',
								label: labels.server
							},
							{
								key: 'environmentName',
								label: labels.environment
							},
							{
								key: STR_SUBSCRIPTION_TYPE_LABEL,
								label: labels.environmentSubscriptionType
							}
						];

						var tableContent = instance._lcsClusterNodesContent;

						var lcsClusterNodesTable = new A.DataTable(
							{
								columnset: columns,
								pageSizes: [ROWS_PER_PAGE, labels.all],
								recordset: data,
								rowsPerPage: ROWS_PER_PAGE,
								sortable: true
							}
						).render(tableContent);

						instance._lcsClusterNodesTable = lcsClusterNodesTable;

						var paginator = tableContent.one(SELECTOR_DATATABLE_PAGINATOR_WRAPPER);

						if (paginator) {
							paginator.toggle(data.length > ROWS_PER_PAGE);

							paginator.all(SELECTOR_BUTTON).addClass(CSS_BTN);
						}

						instance._initializeTableSearch(
							{
								container: instance._lcsClusterNodesContainer,
								data: data,
								paginator: paginator,
								rowsPerPage: ROWS_PER_PAGE,
								table: lcsClusterNodesTable
							}
						);
					},

					_initializePaymentsChart: function(data) {
						var instance = this;

						var chartData = A.Array.map(
							data,
							function(item) {
								return {
									name: item.time,
									y: item.total
								};
							}
						);

						var options = {
							chart: {
								renderTo: instance.ns('paymentsChartContainer'),
								type: 'column'
							},
							legend: {
								enabled: false
							},
							plotOptions: {
								series: {
									dataLabels: {
										enabled: true,
										format: '${point.y:.2f}'
									}
								}
							},
							series: [
								{
									data: chartData
								}
							],
							title: {
								text: instance._labels.payments
							},
							tooltip: {
								enabled: false
							},
							xAxis: {
								type: 'category'
							}
						};

						A.aggregate(options, instance._commonChartOptions, true);

						if (typeof Highcharts !== STR_UNDEFINED) {
							new Highcharts.Chart(options);
						}
					},

					_initializeSubscriptionsTable: function(data) {
						var instance = this;

						var labels = instance._labels;

						var columns = [
							{
								key: STR_SUBSCRIPTION_TYPE_LABEL,
								label: labels.subscriptionType
							},
							{
								key: 'startDate',
								label: labels.startDate
							},
							{
								key: 'expirationDate',
								label: labels.expirationDate
							},
							{
								key: 'supportEndDate',
								label: labels.supportEndDate
							},
							{
								key: 'platform',
								label: labels.platform
							},
							{
								key: 'product',
								label: labels.product
							},
							{
								className: CSS_TEXT_RIGHT,
								key: 'processorCoresAllowedLabel',
								label: labels.processorCoresAllowed
							},
							{
								className: CSS_TEXT_RIGHT,
								key: STR_SERVERS_ALLOWED,
								label: labels.serversAllowed
							},
							{
								className: CSS_TEXT_RIGHT,
								key: STR_SERVERS_USED,
								label: labels.serversUsed
							}
						];

						var subscriptionsTable = new A.DataTable(
							{
								columnset: columns,
								recordset: data,
								sortable: true
							}
						).render(instance._subscriptionsContent);

						instance._subscriptionsTable = subscriptionsTable;

						instance._initializeTableSearch(
							{
								container: instance._subscriptionsContainer,
								data: data,
								table: subscriptionsTable
							}
						);
					},

					_initializeSubscriptionsUsageTable: function(data) {
						var instance = this;

						var labels = instance._labels;

						var columns = [
							{
								key: STR_SUBSCRIPTION_TYPE_LABEL,
								label: labels.subscriptionType
							},
							{
								className: CSS_TEXT_RIGHT,
								key: STR_SERVERS_ALLOWED,
								label: labels.serversAllowed
							},
							{
								className: CSS_TEXT_RIGHT,
								key: STR_SERVERS_USED,
								label: labels.serversUsed
							},
							{
								className: CSS_TEXT_RIGHT,
								key: 'serversAvailable',
								label: labels.serversAvailable
							}
						];

						instance._subscriptionsUsageTable = new A.DataTable(
							{
								columnset: columns,
								recordset: data,
								sortable: true
							}
						).render(instance._subscriptionsUsageContent);
					},

					_initializeUptimesChart: function(chartData) {
						var instance = this;

						var labels = instance._labels;

						var uptimesChartOptions = instance._uptimesChartOptions;

						if (uptimesChartOptions) {
							uptimesChartOptions.series = chartData;
						}
						else {
							uptimesChartOptions = {
								chart: {
									renderTo: instance.ns(STR_UPTIMES_CHART_CONTENT),
									type: 'area',
									zoomType: 'x'
								},
								series: chartData,
								title: {
									text: labels.onlineServers
								},
								tooltip: {
									headerFormat: labels.environment + ': {series.name}<br />',
									pointFormat: '{point.x:%e. %b}: {point.y} ' + labels.onlineServers.toLowerCase()
								},
								xAxis: {
									dateTimeLabelFormats: {
										month: '%e. %b'
									},
									type: 'datetime'
								}
							};
						}

						A.aggregate(uptimesChartOptions, instance._commonChartOptions, true);

						instance._uptimesChartOptions = uptimesChartOptions;

						if (typeof Highcharts !== STR_UNDEFINED) {
							new Highcharts.Chart(uptimesChartOptions);
						}
					},

					_initializeUptimesTable: function(data) {
						var instance = this;

						var labels = instance._labels;

						var columns = [
							{
								key: 'environment',
								label: labels.environment
							},
							{
								key: 'server',
								label: labels.server
							},
							{
								key: 'startTimeLabel',
								label: labels.startTime
							},
							{
								key: 'endTimeLabel',
								label: labels.endTime
							},
							{
								className: CSS_TEXT_RIGHT,
								formatter: instance._formatter.duration,
								key: 'duration',
								label: labels.duration
							}
						];

						var tableContainer = instance._uptimesTableContainer;

						var tableContent = tableContainer.one(SELECTOR_CONTENT);

						var entries = data.entries;

						instance._uptimesTable = new A.DataTable(
							{
								columnset: columns,
								pageSizes: [ROWS_PER_PAGE, labels.all],
								recordset: entries,
								rowsPerPage: ROWS_PER_PAGE,
								sortable: true
							}
						).render(tableContent);

						var paginator = tableContent.one(SELECTOR_DATATABLE_PAGINATOR_WRAPPER);

						if (paginator) {
							paginator.toggle(entries.length > ROWS_PER_PAGE);

							paginator.all(SELECTOR_BUTTON).addClass(CSS_BTN);
						}

						var downloadUptimesCSVLink = instance.byId('downloadUptimesCSV');

						if (downloadUptimesCSVLink) {
							downloadUptimesCSVLink.on(
								EVENT_CLICK,
								function(event) {
									instance._downloadUptimeReport(instance._downloadLCSClusterNodeUptimesDelimitedReportURL);
								}
							);
						}

						var downloadInvoicePDFLink = instance.byId('downloadInvoicePDF');

						if (downloadInvoicePDFLink) {
							downloadInvoicePDFLink.on(
								EVENT_CLICK,
								function(event) {
									instance._downloadUptimeReport(instance._downloadLCSClusterNodeUptimesInvoicePDFReportURL);
								}
							);
						}

						var footer = tableContent.one(SELECTOR_TABLE_FOOT);

						if (footer) {
							var totalDurationHTML = Lang.sub(
								TPL_TABLE_TOTAL,
								{
									label: labels.totalDuration,
									labelColspan: 4,
									value: instance._formatter.duration(
										{
											value: data.totalDuration
										}
									)
								}
							);

							footer.prepend(totalDurationHTML);
						}
					},

					_processBills: function(bills) {
						var instance = this;

						var billsDetails = bills.details;

						var billsDetailsContainer = instance._billsDetailsContainer;
						var billsSummaryContainer = instance._billsSummaryContainer;

						if (!billsDetails.length) {
							instance._showMessage(
								{
									container: instance._billsMessageContainer,
									message: instance._labels.msgNoBills,
									type: STR_INFO
								}
							);

							billsDetailsContainer.setStyle(STR_OPACITY, 0);
							billsSummaryContainer.setStyle(STR_OPACITY, 0);
						}
						else {
							instance._billsSummaryContent.html(instance._createBillsSummaryHTML(bills.summary));

							var billsDetailsTable = instance._billsDetailsTable;

							if (billsDetailsTable) {
								instance._updateTable(
									{
										container: billsDetailsContainer,
										data: billsDetails,
										table: billsDetailsTable
									}
								);
							}
							else {
								instance._initializeBillsDetailsTable(billsDetails);
							}

							billsSummaryContainer.setStyle(STR_OPACITY, 1);
							billsDetailsContainer.setStyle(STR_OPACITY, 1);
						}
					},

					_processPayments: function(payments) {
						var instance = this;

						if (!payments.length) {
							instance._showMessage(
								{
									container: instance._paymentsMessageContainer,
									message: instance._labels.msgNoPayments,
									type: STR_INFO
								}
							);
						}
						else {
							var paymentsChart = instance._paymentsChart;

							if (paymentsChart) {
								paymentsChart.series[0].setData(payments);
							}
							else {
								instance._initializePaymentsChart(payments);
							}
						}
					},

					_processUptimes: function(uptimes) {
						var instance = this;

						var uptimesChartContainer = instance._uptimesChartContainer;
						var uptimesTableContainer = instance._uptimesTableContainer;

						var entries = uptimes.entries;

						var entriesLength = entries.length;

						var uptimesContent = instance._uptimesContent;

						if (uptimesContent) {
							uptimesContent.toggle(entriesLength > 0);
						}

						var hasEntries = 1;

						if (!entriesLength) {
							instance._showMessage(
								{
									container: instance._uptimesMessageContainer,
									message: instance._labels.msgNoUptimes,
									type: STR_INFO
								}
							);

							hasEntries = 0;
						}
						else {
							var uptimesTable = instance._uptimesTable;

							if (uptimesTable) {
								instance._updateTable(
									{
										container: uptimesTableContainer,
										data: entries,
										table: uptimesTable,
										total: instance._formatter.duration(
											{
												value: uptimes.totalDuration
											}
										)
									}
								);
							}
							else {
								instance._initializeUptimesTable(uptimes);
							}

							var chartData = instance._generateUptimesChartData(entries);

							instance._initializeUptimesChart(chartData);
						}

						if (uptimesChartContainer) {
							uptimesChartContainer.setStyle(STR_OPACITY, hasEntries);
						}

						if (uptimesTableContainer) {
							uptimesTableContainer.setStyle(STR_OPACITY, hasEntries);
						}
					},

					_updateLCSClusterEntry: function(data) {
						var instance = this;

						var errorMessage = instance._errorMessage;
						var lcsClusterEntriesContainer = instance._lcsClusterEntriesContainer;

						var messageContainer = lcsClusterEntriesContainer.one('.message-container');

						var updatingNode = lcsClusterEntriesContainer.one(SELECTOR_UPDATING);

						if (updatingNode) {
							updatingNode.show();
						}

						A.io.request(
							instance._updateLCSClusterEntryURL,
							{
								data: instance.ns(data),
								dataType: 'json',
								on: {
									failure: function(event, id, obj) {
										instance._showMessage(
											{
												container: messageContainer,
												message: errorMessage,
												type: STR_ERROR
											}
										);

										if (updatingNode) {
											updatingNode.hide();
										}
									},
									success: function(event, id, obj) {
										var responseData = this.get(STR_RESPONSE_DATA);

										var lcsConstants = instance._lcsConstants;

										if (responseData[lcsConstants.JSON_KEY_RESULT] == lcsConstants.JSON_VALUE_SUCCESS) {
											instance._showMessage(
												{
													container: messageContainer,
													message: instance._successMessage,
													type: STR_SUCCESS
												}
											);

											var data = responseData[lcsConstants.JSON_KEY_DATA];

											instance._lcsClusterEntriesTable.set(STR_DATA, data.lcsClusterEntries);

											instance._subscriptionsUsageTable.set(STR_DATA, data.subscriptionsUsage);
										}
										else {
											instance._showMessage(
												{
													container: messageContainer,
													message: errorMessage,
													type: STR_ERROR
												}
											);
										}

										if (updatingNode) {
											updatingNode.hide();
										}

										instance._lcsSubscriptionTypeCellEditor.hide();
									}
								}
							}
						);
					},

					_updateTable: function(config) {
						var instance = this;

						var container = config.container;
						var data = config.data;
						var table = config.table;
						var total = config.total;

						table.set(STR_DATA, data);

						var paginatorModel = table.get('paginatorModel');

						var dataLength = data.length;

						paginatorModel.set('totalItems', dataLength);

						var paginator = container.one(SELECTOR_DATATABLE_PAGINATOR_WRAPPER);

						if (paginator) {
							var itemsPerPage = paginatorModel.get('itemsPerPage');

							paginator.toggle(dataLength > itemsPerPage);
						}

						var totalValueContainer = container.one(SELECTOR_TABLE_FOOT + ' .table-total-value');

						if (totalValueContainer) {
							totalValueContainer.html(total);
						}
					}
				}
			}
		);

		Liferay.Portlet.LCSSubscriptions = LCSSubscriptions;
	},
	'',
	{
		requires: ['aui-datatable', 'aui-io', 'aui-tooltip', 'datatable-paginator', 'datatable-sort', 'lcs-base', 'lcs-subscription-type-cell-editor', 'liferay-portlet-base', 'liferay-portlet-url']
	}
);