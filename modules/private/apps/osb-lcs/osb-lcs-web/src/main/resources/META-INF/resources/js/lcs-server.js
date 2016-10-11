/* global Highcharts */

AUI.add(
	'lcs-server',
	function(A) {
		var AArray = A.Array;
		var DataTypeDate = A.DataType.Date;
		var Lang = A.Lang;
		var Util = Liferay.Util;

		var BAR_CHART_COLUMN_HEIGHT = 53;

		var BAR_CHART_X_OFFSET = 5;

		var BAR_CHART_Y_OFFSET = 50;

		var CHART_AXIS_LABEL_MARGIN = 10;

		var CHART_COLUMN_COLOR_BLUE = '#4A96E8';

		var CHART_COLUMN_COLOR_GREY = '#EEE';

		var CHART_ID_PREFIX = 'lcsChart_';

		var COLUMN_CHART_COLUMN_WIDTH = 53;

		var COLUMN_CHART_HEIGHT = 272;

		var COLUMN_CHART_WIDTH = 614;

		var COLUMN_CHART_X_OFFSET = 18;

		var COLUMN_CHART_Y_OFFSET = 27;

		var CSS_BTN = 'btn';

		var CSS_INNER_ROW = 'inner-row';

		var CSS_INNER_ROW_KEY = 'inner-row-key';

		var CSS_OPEN = 'open';

		var CSS_PROPERTIES_DEFAULT = 'properties-default';

		var CSS_PROPERTIES_KEY = 'properties-key';

		var CSS_PROPERTIES_VALUE = 'properties-value';

		var EVENT_CHANGE = 'change';

		var EVENT_CLICK = 'click';

		var EVENT_VALUECHANGE = 'valuechange';

		var FORMAT_TIME = '%H:%M';

		var FORMAT_TIME_AMPM = '%I:%M %p';

		var ROWS_PER_PAGE = 10;

		var SELECTOR_BUTTON = 'button';

		var SELECTOR_DATATABLE_PAGINATOR_WRAPPER = '.yui3-datatable-paginator-wrapper';

		var STR_BLANK = '';

		var STR_CATEGORIES = 'categories';

		var STR_CATEGORY = 'category';

		var STR_CHECKED = 'checked';

		var STR_DATA_LAYOUTNAME = 'data-layoutname';

		var STR_END_DATE = 'endDate';

		var STR_END_TIME = 'endTime';

		var STR_ERROR = 'error';

		var STR_GET = 'GET';

		var STR_INFO = 'info';

		var STR_JSON = 'json';

		var STR_OFFSET_WIDTH = 'offsetWidth';

		var STR_OPACITY = 'opacity';

		var STR_PANEL_NODE = 'panelNode';

		var STR_READONLY = 'readonly';

		var STR_RESPONSE_DATA = 'responseData';

		var STR_SPACE = ' ';

		var STR_TITLE = 'title';

		var STR_UNDEFINED = 'undefined';

		var STR_UPDATING = 'updating';

		var STR_VALUE_1 = 'value1';

		var STR_VALUE_2 = 'value2';

		var STR_VALUES = 'values';

		var STR_X = 'x';

		var STYLES_GRAPH = {
			graph: {
				background: {
					border: {
						weight: 0
					},
					fill: {
						color: '#FFF'
					}
				}
			}
		};

		var STYLES_TOOLTIP = {
			styles: {
				backgroundColor: '#8D8D8D',
				borderWidth: 0,
				color: '#FFF',
				zIndex: 20
			}
		};

		var TPL_BAR_CHART = '<div class="lcs-bar-chart-container">' +
				'<h5>{chartTitle}</h5>' +
				'<div class="lcs-bar-chart" id="{chartId}"></div>' +
				'<div class="lcs-chart-overlay-container"></div>' +
			'</div>';

		var TPL_CHART_CONTAINER = '<div class="chart-container fade-out fluid {cssClass}">' +
				'<div class="header">{header}</div>' +
				'<div class="content" id={id}></div>' +
			'</div>';

		var TPL_CHART_OVERLAY = '<div class="lcs-chart-overlay-value" style="right: {xOffset}px; bottom: {yOffset}px;">' +
				'{metricValue}' +
			'</div>';

		var TPL_CHART_TOOLTIP_DOT = '<span style="color:{color}">\u25CF</span>';

		var TPL_CHART_TOOLTIP_HEADER = '<span style="font-size: 10px">{endDate} {startTime} - {endTime}</span><br />';

		var TPL_CHART_TOOLTIP_LOAD_TIMES = '{headerHTML}' +
			'<span>{dotHTML} {labelAverageLoadTime}: </span><b>{averageLoadTime}</b><br />' +
			'<span>{dotHTML} {labelMinMaxLoadTimes}: </span><b>{minLoadTime} - {maxLoadTime}</b><br />' +
			'<span>{dotHTML} {labelStandardDeviation}: </span><b>{standardDeviation}</b>';

		var TPL_CHART_TOOLTIP_PAGE_VIEWS = '{headerHTML}' +
			'<span>{dotHTML} {labelPageViews}: </span><b>{pageViews}</b>';

		var TPL_LAYOUT_PORTLETS_DIALOG_HEADER = '<div class="header-title">{title}</div>' +
			'<div class="header-layout-name">{layoutName}</div>';

		var TPL_LOAD_TIME_HIGH = '<span class="load-time-high" title="{title}">{value}</span>';

		var TPL_OPTION = '<option value="{id}">{name}</option>';

		var TPL_PROPERTY_VALUE = '<div class="{cssContainer}">' +
				'<span class="inner-cell key">{title}</span>' +
				'<span class="inner-cell value {cssClass}">{value}</span>' +
			'</div>';

		var TPL_SUMMARY_TABLE_CONTAINER = '<div class="fade-out table-container {cssClass}">' +
				'<div class="header">' +
					'<h2>{title}</h2>' +
				'</div>' +
				'<div class="content"></div>' +
			'</div>';

		var TPL_SUMMARY_TABLE_TOTALS = '<tr>' +
				'<td class="separator"></td>' +
			'</tr>' +
			'<tr class="totals">' +
				'<td class="name">{name}</td>' +
				'<td class="avg-load-time">{avgLoadTime}</td>' +
				'<td class="min-load-time">{minLoadTime}</td>' +
				'<td class="max-load-time">{maxLoadTime}</td>' +
				'<td class="page-views">{pageViews}</td>' +
			'</tr>';

		var LCSServer = A.Component.create(
			{
				AUGMENTS: [Liferay.LCSBase, Liferay.PortletBase],

				EXTENDS: A.Base,

				NAME: 'lcs-server',

				prototype: {
					initializeAnalyticsPage: function(config) {
						var instance = this;

						var labels = config.labels;

						instance._labels = labels;

						instance._ampm = config.ampm;
						instance._companies = config.companies;
						instance._thresholds = config.thresholds;
						instance._urls = config.urls;

						instance._endAmPm = instance.byId('endAmPm');
						instance._endDay = instance.byId('endDay');
						instance._endHour = instance.byId('endHour');
						instance._endMonth = instance.byId('endMonth');
						instance._endYear = instance.byId('endYear');
						instance._layoutNameInput = instance.byId('layoutName');
						instance._messageContainer = instance.byId('metricsMessageContainer');

						instance._noDataPeriodUpdateEnabled = true;
						instance._summaryUpdateEnabled = true;

						var tabs = [
							{
								id: instance.ns('pages'),
								label: labels.pages,
								updateFn: A.bind('_updatePagesMetrics', instance)
							}
						];

						var tabViewChildren = AArray.map(
							tabs,
							function(item, index) {
								return {
									content: STR_BLANK,
									id: item.id,
									label: item.label
								};
							}
						);

						var container = instance.byId('metricsTabsContainer');

						var tabView = new A.TabView(
							{
								children: tabViewChildren,
								srcNode: container
							}
						).render();

						if (tabs.length < 2) {
							var nav = container.one('.nav');

							if (nav) {
								nav.hide();
							}
						}

						var updatingNode = instance.byId(STR_UPDATING);

						instance._updatingNode = updatingNode;

						tabView.on(
							'selectionChange',
							function(event) {
								var tabViewChild = event.newVal;

								var tabId = tabViewChild.get('id');

								var selectedTab = AArray.find(
									tabs,
									function(item) {
										return (item.id == tabId);
									}
								);

								selectedTab[STR_PANEL_NODE] = tabViewChild.get(STR_PANEL_NODE);

								instance._selectedTab = selectedTab;

								updatingNode.show();

								selectedTab.updateFn();
							}
						);

						var updateTab = function(event) {
							instance._selectedTab.updateFn();
						};

						var companySelect = instance.byId('companyId');

						companySelect.on(
							EVENT_CHANGE,
							function(event) {
								instance._updateSiteSelect();

								updateTab();
							}
						);

						instance._companySelect = companySelect;

						var siteSelect = instance.byId('groupId');

						siteSelect.on(EVENT_CHANGE, updateTab);

						instance._siteSelect = siteSelect;

						instance._updateSiteSelect();

						var periodSelect = instance.byId('period');

						periodSelect.on(EVENT_CHANGE, updateTab);

						instance._periodSelect = periodSelect;

						var endDateInput = instance.byId(STR_END_DATE);

						endDateInput.attr(STR_READONLY, true);

						instance._endDateInput = endDateInput;

						var endDatePicker = Liferay.component(instance.ns(STR_END_DATE) + 'DatePicker');

						endDatePicker.useInputNode(endDateInput);

						instance._endDatePicker = endDatePicker;

						var endDateCalendar = endDatePicker.getCalendar();

						endDateCalendar.after('dateClick', updateTab);

						var endTimeInput = instance.byId(STR_END_TIME);

						endTimeInput.attr(STR_READONLY, true);

						endTimeInput.on(EVENT_VALUECHANGE, updateTab);

						var endTimePicker = Liferay.component(instance.ns(STR_END_TIME) + 'TimePicker');

						endTimePicker.useInputNode(endTimeInput);

						instance._endTimePicker = endTimePicker;

						var previousPeriodLink = instance.byId('previousPeriod');

						previousPeriodLink.on(EVENT_CLICK, A.fn('_setAdjacentPeriod', instance, -1));

						var nextPeriodLink = instance.byId('nextPeriod');

						nextPeriodLink.on(EVENT_CLICK, A.fn('_setAdjacentPeriod', instance, 1));

						var selectedTab = tabs[0];

						selectedTab[STR_PANEL_NODE] = container.one('.active.tab-pane');

						instance._selectedTab = selectedTab;

						instance._chartTooltipDotHTML = Lang.sub(
							TPL_CHART_TOOLTIP_DOT,
							{
								color: '#4A96E8'
							}
						);

						selectedTab.updateFn();
					},

					initializeApplicationMetrics: function(config) {
						var instance = this;

						instance._layoutPortletsDialogTitle = config.layoutPortletsDialogTitle;
						instance._layoutPortletsDialogUrl = config.layoutPortletsDialogUrl;

						var metricsContainer = instance.byId('lcsClusterNodeLayoutMetricsesSearchContainer');

						metricsContainer.delegate(
							EVENT_CLICK,
							A.bind('_showLayoutPortletsDialog', instance),
							'.lcs-layout-link'
						);
					},

					initializeDetailsPage: function(config) {
						var instance = this;

						var data = config.data;
						var labels = config.labels;

						instance._labels = labels;

						var columns = [
							{
								key: 'name'
							},
							{
								key: 'value'
							}
						];

						var javaMetadataTableContent = instance.byId('javaMetadataTableContent');

						new A.DataTable(
							{
								columnset: columns,
								recordset: data.javaMetadata
							}
						).render(javaMetadataTableContent);

						var javaOptionsData = data.javaOptions;

						if (javaOptionsData.length) {
							var javaOptionsTableContent = instance.byId('javaOptionsTableContent');

							var javaOptionsTable = new A.DataTable(
								{
									columnset: columns,
									pageSizes: [ROWS_PER_PAGE, labels.all],
									recordset: javaOptionsData,
									rowsPerPage: ROWS_PER_PAGE,
									sortable: true
								}
							).render(javaOptionsTableContent);

							var paginator = javaOptionsTableContent.one(SELECTOR_DATATABLE_PAGINATOR_WRAPPER);

							if (paginator) {
								paginator.toggle(javaOptionsData.length > ROWS_PER_PAGE);

								paginator.all(SELECTOR_BUTTON).addClass(CSS_BTN);
							}

							var javaOptionsTableContainer = instance.byId('javaOptionsTableContainer');

							instance._initializeTableSearch(
								{
									container: javaOptionsTableContainer,
									data: javaOptionsData,
									paginator: paginator,
									rowsPerPage: ROWS_PER_PAGE,
									table: javaOptionsTable
								}
							);

							javaOptionsTableContainer.setStyle(STR_OPACITY, 1);
						}

						var liferayMetadataTableContent = instance.byId('liferayMetadataTableContent');

						new A.DataTable(
							{
								columnset: columns,
								recordset: data.liferayMetadata
							}
						).render(liferayMetadataTableContent);

						var liferayMetadataTableContainer = instance.byId('liferayMetadataTableContainer');

						liferayMetadataTableContainer.setStyle(STR_OPACITY, 1);

						var hardwareMetadataTableContent = instance.byId('hardwareMetadataTableContent');

						new A.DataTable(
							{
								columnset: columns,
								recordset: data.hardwareMetadata
							}
						).render(hardwareMetadataTableContent);

						var softwareMetadataTableContent = instance.byId('softwareMetadataTableContent');

						new A.DataTable(
							{
								columnset: columns,
								recordset: data.softwareMetadata
							}
						).render(softwareMetadataTableContent);

						var softwareMetadataTableContainer = instance.byId('softwareMetadataTableContainer');

						softwareMetadataTableContainer.setStyle(STR_OPACITY, 1);

						var updatingNode = instance.byId(STR_UPDATING);

						updatingNode.hide();
					},

					initializeJVMMetrics: function(config) {
						var instance = this;

						var chartNode = config.chartNode;
						var dataUrl = config.dataUrl;
						var labels = config.labels;

						var errorMessage = instance._errorMessage;
						var lcsConstants = instance._lcsConstants;

						var memoryPoolChartContainer = instance.byId('memoryPoolChartContainer');

						A.io.request(
							dataUrl,
							{
								dataType: STR_JSON,
								method: STR_GET,
								on: {
									failure: function(event, id, obj) {
										instance._showMessage(
											{
												container: chartNode,
												message: errorMessage,
												type: STR_ERROR
											}
										);
									},
									success: function(event, id, obj) {
										var responseData = this.get(STR_RESPONSE_DATA);

										if (responseData[lcsConstants.JSON_KEY_RESULT] === lcsConstants.JSON_VALUE_SUCCESS) {
											var memoryPoolUsage = responseData[lcsConstants.JSON_KEY_DATA].memoryPoolUsage;

											if (!memoryPoolUsage) {
												instance._showMessage(
													{
														container: chartNode,
														message: config.msgNoData,
														type: STR_INFO
													}
												);
											}
											else {
												instance._renderMemoryChart(
													{
														chartContainer: memoryPoolChartContainer,
														labelMetric: labels.metric,
														labelValue: labels.value,
														metricsData: memoryPoolUsage
													}

												);
											}
										}
										else {
											instance._showMessage(
												{
													container: chartNode,
													message: errorMessage,
													type: STR_ERROR
												}
											);
										}
									}
								}
							}
						);
					},

					initializePropertiesPage: function(config) {
						var instance = this;

						var tableContent = instance.byId('propertiesTableContent');

						var labels = config.labels;

						instance._labels = labels;

						var showDefaultsCheckbox = instance.byId('showDefaultsCheckbox');
						var showDynamicPropertiesCheckbox = instance.byId('showDynamicPropertiesCheckbox');
						var showValuesCheckbox = instance.byId('showValuesCheckbox');

						var columns = [
							{
								allowHTML: true,
								formatter: function(obj) {
									var key = obj.data.key;

									var value = Lang.sub(
										TPL_PROPERTY_VALUE,
										{
											cssClass: CSS_PROPERTIES_KEY,
											cssContainer: CSS_INNER_ROW + STR_SPACE + CSS_INNER_ROW_KEY,
											title: labels.propertyName,
											value: key
										}
									);

									var originalValue = obj.data.originalValue || STR_BLANK;

									if (showDefaultsCheckbox.get(STR_CHECKED)) {
										var originalValueSubs = {
											cssClass: CSS_PROPERTIES_DEFAULT,
											cssContainer: CSS_INNER_ROW + ' inner-row-default',
											title: labels.defaultValue,
											value: originalValue
										};

										value += Lang.sub(TPL_PROPERTY_VALUE, originalValueSubs);
									}

									if (showValuesCheckbox.get(STR_CHECKED)) {
										var lcsClusterNodeValue = obj.data.lcsClusterNodeValue || STR_BLANK;

										var lcsClusterNodeValueSubs = {
											cssClass: CSS_PROPERTIES_VALUE,
											cssContainer: CSS_INNER_ROW + ' inner-row-value',
											title: labels.customValue,
											value: lcsClusterNodeValue
										};

										value += Lang.sub(TPL_PROPERTY_VALUE, lcsClusterNodeValueSubs);
									}

									return value;
								}
							}
						];

						var data = config.data;

						var table = new A.DataTable(
							{
								columns: columns,
								pageSizes: [ROWS_PER_PAGE, 20, labels.all],
								recordset: data,
								rowsPerPage: ROWS_PER_PAGE
							}
						).render(tableContent);

						var tableContainer = instance.byId('propertiesTableContainer');

						var paginator = tableContent.one(SELECTOR_DATATABLE_PAGINATOR_WRAPPER);

						if (paginator) {
							paginator.toggle(data.length > ROWS_PER_PAGE);

							paginator.all(SELECTOR_BUTTON).addClass(CSS_BTN);
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

						var updateSearchableTable = function(event) {
							var config = {
								data: data,
								table: table
							};

							if (!showDynamicPropertiesCheckbox.attr(STR_CHECKED)) {
								config.additionalFilter = function(key, value) {
									return (key === 'originalValue' && value.match(/\${.*}/));
								};
							}

							instance._updateSearchableTable(config);
						};

						var optionsMenu = instance.byId('propertiesTableOptionsMenu');
						var optionsToggler = instance.byId('propertiesTableOptionsToggler');

						if (optionsToggler) {
							optionsToggler.on(
								EVENT_CLICK,
								function(event) {
									if (optionsMenu) {
										optionsMenu.toggleClass(CSS_OPEN);
									}

									optionsToggler.toggleClass(CSS_OPEN);
								}
							);
						}

						showDefaultsCheckbox.attr(STR_CHECKED, true);

						showDefaultsCheckbox.on(EVENT_CHANGE, updateSearchableTable);

						showValuesCheckbox.attr(STR_CHECKED, true);

						showValuesCheckbox.on(EVENT_CHANGE, updateSearchableTable);

						showDynamicPropertiesCheckbox.on(EVENT_CHANGE, updateSearchableTable);

						var searchInput = tableContainer.one('.search-input');

						if (searchInput) {
							searchInput.on(
								EVENT_VALUECHANGE,
								function(event) {
									instance.byId('showDefaults').val(true);
									instance.byId('showDynamicProperties').val(true);
									instance.byId('showValues').val(true);

									showDefaultsCheckbox.attr(STR_CHECKED, true);
									showDynamicPropertiesCheckbox.attr(STR_CHECKED, true);
									showValuesCheckbox.attr(STR_CHECKED, true);
								}
							);
						}

						updateSearchableTable();

						tableContainer.setStyle(STR_OPACITY, 1);

						var updatingNode = instance.byId('updating');

						updatingNode.hide();
					},

					initializeServerMetrics: function(config) {
						var instance = this;

						var labels = config.labels;
						var msgNoData = config.msgNoData;

						var currentThreadsContainer = instance.byId('currentThreadsContainer');

						var errorMessage = instance._errorMessage;
						var lcsConstants = instance._lcsConstants;

						A.io.request(
							config.getCurrentThreadsMetricsURL,
							{
								dataType: STR_JSON,
								method: STR_GET,
								on: {
									failure: function(event, id, obj) {
										instance._showMessage(
											{
												container: currentThreadsContainer,
												message: errorMessage,
												type: STR_ERROR
											}
										);
									},
									success: function(event, id, obj) {
										var responseData = this.get(STR_RESPONSE_DATA);

										if (responseData[lcsConstants.JSON_KEY_RESULT] == lcsConstants.JSON_VALUE_SUCCESS) {
											var metricsData = responseData[lcsConstants.JSON_KEY_DATA];

											if (!metricsData) {
												instance._showMessage(
													{
														container: currentThreadsContainer,
														message: msgNoData,
														type: STR_INFO
													}
												);
											}
											else {
												var countChannels = metricsData.length;

												for (var i = 0; i < countChannels; i++) {
													var currentMetric = metricsData[i];

													var channelName = Util.escapeHTML(currentMetric.name);
													var countBusy = currentMetric.currentThreadsBusy;
													var countTotal = currentMetric.currentThreadCount;

													if (!countBusy && !countTotal) {
														continue;
													}

													var countUnused = (countTotal - countBusy);

													var chartDataEntry = {};

													chartDataEntry[STR_CATEGORY] = channelName;
													chartDataEntry[STR_VALUE_1] = countBusy;
													chartDataEntry[STR_VALUE_2] = countUnused;

													var chartId = instance.ns(CHART_ID_PREFIX + instance._formatId(channelName));

													instance._renderTwoSeriesBarChart(
														{
															chartData: [chartDataEntry],
															chartId: chartId,
															chartsContainerNode: currentThreadsContainer,
															chartTitle: channelName,
															labelCategory: labels.channel,
															labelSeries1: labels.busy,
															labelSeries2: labels.unused
														}
													);
												}
											}
										}
										else {
											instance._showMessage(
												{
													container: currentThreadsContainer,
													message: errorMessage,
													type: STR_ERROR
												}
											);
										}
									}
								}
							}
						);

						var jdbcConnectionPoolsContainer = instance.byId('jdbcConnectionPoolsContainer');

						A.io.request(
							config.getJDBCConnectionPoolMetricsURL,
							{
								dataType: STR_JSON,
								method: STR_GET,
								on: {
									failure: function(event, id, obj) {
										instance._showMessage(
											{
												container: jdbcConnectionPoolsContainer,
												message: instance._errorMessage,
												type: STR_ERROR
											}
										);
									},
									success: function(event, id, obj) {
										var responseData = this.get(STR_RESPONSE_DATA);

										var metricsData;

										if (responseData) {
											metricsData = responseData[lcsConstants.JSON_KEY_DATA];
										}

										if (!responseData || !metricsData) {
											instance._showMessage(
												{
													container: jdbcConnectionPoolsContainer,
													message: msgNoData,
													type: STR_INFO
												}
											);
										}
										else {
											var countPools = metricsData.length;

											for (var i = 0; i < countPools; i++) {
												var currentMetric = metricsData[i];

												var countActive = currentMetric.numActive;
												var countIdle = currentMetric.numIdle;
												var poolName = Util.escapeHTML(currentMetric.name);

												var chartDataEntry = {};

												chartDataEntry[STR_CATEGORY] = poolName;
												chartDataEntry[STR_VALUE_1] = countActive;
												chartDataEntry[STR_VALUE_2] = countIdle;

												var chartId = instance.ns(CHART_ID_PREFIX + instance._formatId(poolName));

												instance._renderTwoSeriesBarChart(
													{
														chartData: [chartDataEntry],
														chartId: chartId,
														chartsContainerNode: jdbcConnectionPoolsContainer,
														chartTitle: poolName,
														labelCategory: labels.pool,
														labelSeries1: labels.active,
														labelSeries2: labels.idle
													}
												);
											}
										}
									}
								}
							}
						);
					},

					initializeSettingsPage: function(config) {
						var instance = this;

						var unregisterButton = instance.byId('unregister');

						if (unregisterButton) {
							unregisterButton.on(
								EVENT_CLICK,
								function(event) {
									if (confirm(config.msgConfirmUnregisterServer)) {
										window.location.href = config.deleteLCSClusterNodeURL;
									}
								}
							);
						}
					},

					_createChartTooltipHeaderHTML: function(config) {
						var instance = this;

						var startEpoch = config.startEpoch;
						var timeFormat = config.timeFormat;

						var startTime = new Date(startEpoch);

						startTime = DataTypeDate.format(
							startTime,
							{
								format: timeFormat
							}
						);

						var period = instance._periodSelect.val();

						var interval = 3600000;

						if (period && period == 60) {
							interval = 120000;
						}

						var endTime = new Date(startEpoch + interval);

						endTime = DataTypeDate.format(
							endTime,
							{
								format: timeFormat
							}
						);

						return Lang.sub(
							TPL_CHART_TOOLTIP_HEADER,
							{
								endDate: instance._endDateInput.val(),
								endTime: endTime,
								startTime: startTime
							}
						);
					},

					_formatId: function(text) {
						return text.replace(/[^\w-]/g, '_');
					},

					_initializeLoadTimesChart: function(config) {
						var instance = this;

						var ampm = instance._ampm;
						var labels = instance._labels;

						var timeFormat = ampm ? FORMAT_TIME_AMPM : FORMAT_TIME;

						var data = config.data;

						var ranges = data.ranges;

						var thresholds = instance._thresholds;

						var highPageLoadTime = thresholds.highPageLoadTime;
						var warningPageLoadTime = thresholds.warningPageLoadTime;

						var options = {
							chart: {
								renderTo: config.containerId,
								zoomType: STR_X
							},
							legend: {
								enabled: false
							},
							series: [
								{
									data: data.averages,
									name: labels.averageLoadTime,
									zIndex: 1
								},
								{
									data: ranges,
									linkedTo: ':previous',
									name: labels.minMaxLoadTimes,
									type: 'arearange',
									zIndex: 0
								}
							],
							title: {
								text: labels.loadTimes
							},
							tooltip: {
								crosshairs: true,
								formatter: function() {
									var html = false;

									var rangesPoint = this.points[1];

									if (rangesPoint) {
										var rangesData = rangesPoint.series.data;

										var index = rangesData.indexOf(rangesPoint.point);

										var curRange = rangesData[index];

										html = Lang.sub(
											TPL_CHART_TOOLTIP_LOAD_TIMES,
											{
												averageLoadTime: this.y,
												dotHTML: instance._chartTooltipDotHTML,
												headerHTML: instance._createChartTooltipHeaderHTML(
													{
														startEpoch: this.x + instance._timezoneOffset,
														timeFormat: timeFormat
													}
												),
												labelAverageLoadTime: labels.averageLoadTime,
												labelMinMaxLoadTimes: labels.minMaxLoadTimes,
												labelStandardDeviation: labels.standardDeviation,
												maxLoadTime: curRange.high,
												minLoadTime: curRange.low,
												standardDeviation: instance._loadTimesStandardDeviations[index]
											}
										);
									}

									return html;
								},
								shared: true
							},
							xAxis: {
								dateTimeLabelFormats: {
									millisecond: timeFormat,
									minute: timeFormat,
									second: timeFormat
								},
								type: 'datetime'
							},
							yAxis: {
								plotBands: [
									{
										color: 'rgba(150, 206, 104, 0.1)',
										from: 0,
										to: warningPageLoadTime
									},
									{
										color: 'rgba(181, 84, 3, 0.1)',
										from: warningPageLoadTime,
										to: highPageLoadTime
									},
									{
										color: 'rgba(181, 3, 3, 0.1)',
										from: highPageLoadTime,
										to: Number.MAX_VALUE
									}
								]
							}
						};

						A.aggregate(options, instance._commonChartOptions, true);

						if (typeof Highcharts !== STR_UNDEFINED) {
							instance._loadTimesChart = new Highcharts.Chart(options);
						}
					},

					_initializePageViewsChart: function(config) {
						var instance = this;

						var data = config.data;

						var ampm = instance._ampm;
						var labels = instance._labels;

						var timeFormat = ampm ? FORMAT_TIME_AMPM : FORMAT_TIME;

						var options = {
							chart: {
								renderTo: config.containerId,
								zoomType: STR_X
							},
							legend: {
								enabled: false
							},
							series: [
								{
									data: data,
									name: labels.pageViews,
									type: 'area'
								}
							],
							title: {
								text: labels.pageViews
							},
							tooltip: {
								crosshairs: true,
								formatter: function() {
									return Lang.sub(
										TPL_CHART_TOOLTIP_PAGE_VIEWS,
										{
											dotHTML: instance._chartTooltipDotHTML,
											headerHTML: instance._createChartTooltipHeaderHTML(
												{
													startEpoch: this.x + instance._timezoneOffset,
													timeFormat: timeFormat
												}
											),
											labelPageViews: labels.pageViews,
											pageViews: this.y
										}
									);
								}
							},
							xAxis: {
								dateTimeLabelFormats: {
									millisecond: timeFormat,
									minute: timeFormat,
									second: timeFormat
								},
								type: 'datetime'
							}
						};

						A.aggregate(options, instance._commonChartOptions, true);

						if (typeof Highcharts !== STR_UNDEFINED) {
							instance._pageViewsChart = new Highcharts.Chart(options);
						}
					},

					_initializeSummaryTable: function(config) {
						var instance = this;

						var labels = instance._labels;

						var loadTimeFormatter = function(obj) {
							var value = obj.value;

							if (value >= instance._thresholds.highPageLoadTime) {
								value = Lang.sub(
									TPL_LOAD_TIME_HIGH,
									{
										title: Util.escapeHTML(labels.msgHighLoadTime),
										value: value
									}
								);
							}

							return value;
						};

						var columns = [
							{
								nodeFormatter: function(obj) {
									var td = obj.td;
									var value = obj.value;

									var label = value;

									if (value == instance._lcsConstants.ALL_PORTAL_OBJECTS_NAME) {
										label = labels.allPages;
									}

									td.attr(STR_DATA_LAYOUTNAME, value);
									td.html(label);

									return false;
								},
								key: 'name',
								label: labels.pageName
							},
							{
								allowHTML: true,
								formatter: loadTimeFormatter,
								key: 'avgLoadTime',
								label: labels.averageLoadTime
							},
							{
								allowHTML: true,
								formatter: loadTimeFormatter,
								key: 'minLoadTime',
								label: labels.minLoadTime
							},
							{
								allowHTML: true,
								formatter: loadTimeFormatter,
								key: 'maxLoadTime',
								label: labels.maxLoadTime
							},
							{
								key: 'pageViews',
								label: labels.pageViews
							}
						];

						var container = config.container;

						var contentContainer = container.one('.content');

						if (contentContainer) {
							var data = config.data;

							var summaryTable = new A.DataTable(
								{
									columnset: columns,
									pageSizes: [ROWS_PER_PAGE, labels.all],
									recordset: data,
									rowsPerPage: ROWS_PER_PAGE,
									sortable: true
								}
							).render(contentContainer);

							instance._summaryTable = summaryTable;

							var paginator = contentContainer.one(SELECTOR_DATATABLE_PAGINATOR_WRAPPER);

							if (paginator) {
								paginator.toggle(data.length > ROWS_PER_PAGE);

								paginator.all(SELECTOR_BUTTON).addClass(CSS_BTN);
							}

							instance._initializeTableSearch(
								{
									container: container,
									data: data,
									paginator: paginator,
									rowsPerPage: ROWS_PER_PAGE,
									table: summaryTable
								}
							);

							var chartHeaders = instance._chartHeaders;
							var layoutNameInput = instance._layoutNameInput;

							var updateFn = instance._selectedTab.updateFn;

							instance._initializeSelectableRows(
								{
									container: container,
									dataAttr: STR_DATA_LAYOUTNAME,
									dataSelector: '.table-col-name',
									onClick: function(config) {
										layoutNameInput.val(config.data);

										var label = config.label;

										chartHeaders.attr(STR_TITLE, label);
										chartHeaders.html(label);

										instance._summaryUpdateEnabled = false;

										updateFn();
									}
								}
							);

							new A.TooltipDelegate(
								{
									html: true,
									opacity: 1,
									trigger: '.load-time-high',
									triggerHideEvent: EVENT_CLICK
								}
							);

							var totalsData = config.totalsData;

							var totalsHTML = Lang.sub(
								TPL_SUMMARY_TABLE_TOTALS,
								{
									avgLoadTime: totalsData.avgLoadTime,
									maxLoadTime: totalsData.maxLoadTime,
									minLoadTime: totalsData.minLoadTime,
									name: labels.allPages,
									pageViews: totalsData.pageViews
								}
							);

							var footer = contentContainer.one('.table-foot');

							if (footer) {
								footer.prepend(totalsHTML);

								var totalsRow = footer.one('.totals');

								if (totalsRow) {
									totalsRow.on(
										EVENT_CLICK,
										function(event) {
											layoutNameInput.val(instance._lcsConstants.ALL_PORTAL_OBJECTS_NAME);

											var label = labels.allPages;

											chartHeaders.attr(STR_TITLE, label);
											chartHeaders.html(label);

											totalsRow.addClass('selected');

											updateFn();

											event.stopPropagation();
										}
									);
								}
							}
						}
					},

					_prepareEmptyChartData: function(data) {
						return AArray.map(
							data,
							function(item, index) {
								for (var i = 1; i < item.length; i++) {
									if (item[i] == -1) {
										item[i] = null;
									}
								}

								return item;
							}
						);
					},

					_renderMemoryChart: function(config) {
						var instance = this;

						var chartContainer = config.chartContainer;
						var labelMetric = config.labelMetric;
						var labelValue = config.labelValue;
						var metricsData = config.metricsData;

						var chartData = [];
						var chartOverlayHtml = STR_BLANK;

						var xOffset = 0;

						var memoryTypes = A.Object.keys(metricsData);

						var memoryTypesCount = memoryTypes.length;

						var xTotalEmptySpace = COLUMN_CHART_WIDTH - COLUMN_CHART_COLUMN_WIDTH * memoryTypesCount;

						var xSpaceBetweenColumns = xTotalEmptySpace / memoryTypesCount;

						var xSpread = xSpaceBetweenColumns + COLUMN_CHART_COLUMN_WIDTH;

						var xBaseOverlayOffset = COLUMN_CHART_WIDTH - (xSpread / 2) - COLUMN_CHART_X_OFFSET;

						for (var i = 0; i < memoryTypesCount; i++) {
							var memoryType = memoryTypes[i];

							var chartDataEntryValue = Lang.String.round(metricsData[memoryType] * 100, 0);

							var chartDataEntry = {};

							chartDataEntry[STR_CATEGORIES] = Util.escapeHTML(memoryType);
							chartDataEntry[STR_VALUES] = chartDataEntryValue;

							chartData.push(chartDataEntry);

							chartOverlayHtml += Lang.sub(
								TPL_CHART_OVERLAY,
								{
									metricValue: (metricsData[memoryType] >= 0.08) ? chartDataEntryValue + '%' : STR_BLANK,
									xOffset: xBaseOverlayOffset - xOffset,
									yOffset: COLUMN_CHART_Y_OFFSET + Lang.String.round(chartDataEntryValue / 100 * COLUMN_CHART_HEIGHT, 0)
								}
							);

							xOffset += xSpread;
						}

						var chartOverlayNode = chartContainer.next();

						chartOverlayNode.html(chartOverlayHtml);

						var chartAxes = {
							'x': {
								keys: [STR_CATEGORIES],
								position: 'bottom',
								styles: {
									label: {
										height: '40px',
										margin: {
											top: CHART_AXIS_LABEL_MARGIN
										},
										width: xSpread + 'px'
									},
									line: {
										weight: 0
									},
									majorTicks: {
										display: 'none'
									}
								},
								type: 'category'
							},
							'y': {
								keys: [STR_VALUES],
								labelFormat: {
									suffix: '%'
								},
								maximum: 100,
								position: 'left',
								styles: {
									label: {
										margin: {
											top: CHART_AXIS_LABEL_MARGIN
										}
									},
									line: {
										weight: 0
									},
									majorTicks: {
										display: 'none'
									},
									majorUnit: {
										count: 11
									}
								},
								type: 'numeric'
							}
						};

						var chartSeriesCollection = [
							{
								styles: {
									fill: {
										color: CHART_COLUMN_COLOR_BLUE
									},
									width: COLUMN_CHART_COLUMN_WIDTH
								},
								type: 'column',
								xDisplayName: labelMetric,
								xKey: STR_CATEGORIES,
								yDisplayName: labelValue,
								yKey: STR_VALUES
							}
						];

						new A.Chart(
							{
								axes: chartAxes,
								dataProvider: chartData,
								horizontalGridlines: true,
								render: chartContainer,
								seriesCollection: chartSeriesCollection,
								styles: STYLES_GRAPH,
								tooltip: STYLES_TOOLTIP,
								type: 'column',
								verticalGridlines: true
							}
						);
					},

					_renderTPL: function(tpl, parameters) {
						return A.Node.create(Lang.sub(tpl, parameters));
					},

					_renderTwoSeriesBarChart: function(config) {
						var instance = this;

						var chartData = config.chartData;
						var chartId = config.chartId;
						var chartsContainerNode = config.chartsContainerNode;
						var chartTitle = config.chartTitle;
						var labelCategory = config.labelCategory;
						var labelSeries1 = config.labelSeries1;
						var labelSeries2 = config.labelSeries2;

						var chartNode = instance._renderTPL(
							TPL_BAR_CHART,
							{
								chartId: chartId,
								chartTitle: chartTitle
							}
						);

						chartsContainerNode.append(chartNode);

						var value1 = chartData[0][STR_VALUE_1];
						var value2 = chartData[0][STR_VALUE_2];

						var valueTotal = value1 + value2;

						var chartAxes = {
							'x': {
								keys: [STR_VALUE_1, STR_VALUE_2],
								labelFunction: Math.round,
								maximum: valueTotal,
								position: 'bottom',
								styles: {
									label: {
										margin: {
											top: CHART_AXIS_LABEL_MARGIN
										}
									},
									line: {
										weight: 0
									},
									majorTicks: {
										display: 'none'
									}
								},
								type: 'numeric'
							},
							'y': {
								keys: [STR_CATEGORY],
								position: 'left',
								styles: {
									label: {
										display: 'none'
									},
									line: {
										weight: 0
									},
									majorTicks: {
										display: 'none'
									}
								},
								type: 'category'
							}
						};

						var chartSeriesCollection = [
							{
								styles: {
									fill: {
										color: CHART_COLUMN_COLOR_BLUE
									},
									height: BAR_CHART_COLUMN_HEIGHT
								},
								xDisplayName: labelSeries1,
								xField: STR_VALUE_1,
								yDisplayName: labelCategory

							},
							{
								styles: {
									fill: {
										color: CHART_COLUMN_COLOR_GREY
									},
									height: BAR_CHART_COLUMN_HEIGHT
								},
								xDisplayName: labelSeries2,
								xField: STR_VALUE_2,
								yDisplayName: labelCategory
							}
						];

						new A.Chart(
							{
								axes: chartAxes,
								categoryKey: STR_CATEGORY,
								dataProvider: chartData,
								render: '#' + chartId,
								seriesCollection: chartSeriesCollection,
								stacked: true,
								styles: STYLES_GRAPH,
								tooltip: STYLES_TOOLTIP,
								type: 'bar'
							}
						);

						var chartWidth = chartNode.one('.yui3-graph-content').get(STR_OFFSET_WIDTH);

						var chartOverlayNode = instance._renderTPL(
							TPL_CHART_OVERLAY,
							{
								metricValue: ((value2 / valueTotal) < 0.95) ? value1 : STR_BLANK,
								xOffset: BAR_CHART_X_OFFSET + Math.round(value2 / valueTotal * chartWidth),
								yOffset: BAR_CHART_Y_OFFSET
							}
						);

						var chartOverlayContainerNode = chartNode.one('.lcs-chart-overlay-container');

						chartOverlayContainerNode.append(chartOverlayNode);
					},

					_setAdjacentPeriod: function(sign) {
						var instance = this;

						var ampm = instance._endAmPm.val();
						var hour = instance._endHour.val();

						if (ampm == 1) {
							hour = Lang.toInt(hour) + 12;
						}

						var selectedEndDate = new Date(instance._endYear.val(), instance._endMonth.val(), instance._endDay.val(), hour, 0, 0);

						var period = instance._periodSelect.val();

						var millis = Lang.toInt(period) * sign * 60000;

						var newEndDate = new Date(selectedEndDate.getTime() + millis);

						instance._endTimePicker.selectDates([newEndDate]);

						var endDatePicker = instance._endDatePicker;

						endDatePicker.clearSelection();

						endDatePicker.selectDates(newEndDate);

						instance._selectedTab.updateFn();
					},

					_showLayoutPortletsDialog: function(event) {
						var instance = this;

						var showLayoutPortletsLink = event.currentTarget;

						var layoutName = showLayoutPortletsLink.attr(STR_DATA_LAYOUTNAME);

						var dialogTitleHTML = Lang.sub(
							TPL_LAYOUT_PORTLETS_DIALOG_HEADER,
							{
								layoutName: Util.escapeHTML(layoutName),
								title: instance._layoutPortletsDialogTitle
							}
						);

						var dialog = Util.Window.getWindow(
							{
								dialog: {
									cssClass: 'lcs-dialog',
									destroyOnHide: true,
									resizable: false,
									visible: false
								},
								title: dialogTitleHTML
							}
						);

						dialog.plug(
							A.Plugin.IO,
							{
								after: {
									success: function() {
										dialog.show();
									}
								},
								data: instance.ns(
									{
										key: showLayoutPortletsLink.attr('data-key'),
										layoutName: layoutName
									}
								),
								uri: instance._layoutPortletsDialogUrl
							}
						);
					},

					_updatePagesMetrics: function(config) {
						var instance = this;

						var errorMessage = instance._errorMessage;
						var messageContainer = instance._messageContainer;
						var selectedTab = instance._selectedTab;

						var container = selectedTab.panelNode;

						var updatingNode = instance._updatingNode;

						updatingNode.show();

						var form = instance.byId('fm');

						A.io.request(
							instance._urls.getPagesMetrics,
							{
								dataType: STR_JSON,
								form: {
									id: form.getDOM()
								},
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

										messageContainer.hide();

										var labels = instance._labels;
										var lcsConstants = instance._lcsConstants;

										if (responseData[lcsConstants.JSON_KEY_RESULT] == lcsConstants.JSON_VALUE_SUCCESS) {
											responseData = responseData[lcsConstants.JSON_KEY_DATA];

											var loadTimesChartContainer = instance._loadTimesChartContainer;
											var pageViewsChartContainer = instance._pageViewsChartContainer;
											var summaryTableContainer = instance._summaryTableContainer;

											var summary = responseData.summary;

											if (summary) {
												var loadTimes = responseData.loadTimes;

												var averages = loadTimes.averages;
												var ranges = loadTimes.ranges;

												instance._loadTimesStandardDeviations = loadTimes.standardDeviations;

												averages = instance._prepareEmptyChartData(averages);
												ranges = instance._prepareEmptyChartData(ranges);

												var loadTimesChart = instance._loadTimesChart;

												if (loadTimesChart) {
													loadTimesChart.series[0].setData(averages);
													loadTimesChart.series[1].setData(ranges);

													instance._loadTimesChartContainer.show();
												}
												else {
													var loadTimesChartContentId = instance.ns('loadTimesChartContent');

													loadTimesChartContainer = instance._renderTPL(
														TPL_CHART_CONTAINER,
														{
															cssClass: STR_BLANK,
															header: labels.allPages,
															id: loadTimesChartContentId
														}
													);

													container.append(loadTimesChartContainer);

													instance._loadTimesChartContainer = loadTimesChartContainer;

													instance._initializeLoadTimesChart(
														{
															containerId: loadTimesChartContentId,
															data: loadTimes
														}
													);

													loadTimesChartContainer.setStyle(STR_OPACITY, 1);
												}

												var pageViews = instance._prepareEmptyChartData(responseData.pageViews);

												var pageViewsChart = instance._pageViewsChart;

												if (pageViewsChart) {
													pageViewsChart.series[0].setData(pageViews);

													instance._pageViewsChartContainer.show();
												}
												else {
													var pageViewsChartContentId = instance.ns('pageViewsChartContent');

													pageViewsChartContainer = instance._renderTPL(
														TPL_CHART_CONTAINER,
														{
															cssClass: 'last',
															header: labels.allPages,
															id: pageViewsChartContentId
														}
													);

													container.append(pageViewsChartContainer);

													instance._pageViewsChartContainer = pageViewsChartContainer;

													instance._initializePageViewsChart(
														{
															containerId: pageViewsChartContentId,
															data: pageViews
														}
													);

													pageViewsChartContainer.setStyle(STR_OPACITY, 1);
												}

												instance._chartHeaders = instance.all('.chart-container .header');

												var summaryTable = instance._summaryTable;

												if (summaryTable) {
													if (instance._summaryUpdateEnabled) {
														instance._updateSearchableTable(
															{
																data: summary,
																table: summaryTable
															}
														);

														instance._updateSummaryTableTotals(responseData.allPagesSummary);
													}

													instance._summaryTableContainer.show();
												}
												else {
													summaryTableContainer = instance._renderTPL(
														TPL_SUMMARY_TABLE_CONTAINER,
														{
															cssClass: 'block summary',
															title: labels.summary
														}
													);

													container.append(summaryTableContainer);

													instance._summaryTableContainer = summaryTableContainer;

													instance._initializeSummaryTable(
														{
															container: summaryTableContainer,
															data: summary,
															totalsData: responseData.allPagesSummary
														}
													);

													summaryTableContainer.setStyle(STR_OPACITY, 1);
												}
											}
											else if (instance._noDataPeriodUpdateEnabled) {
												instance._noDataPeriodUpdateEnabled = false;

												instance._setAdjacentPeriod(-1);
											}
											else {
												instance._showMessage(
													{
														container: messageContainer,
														message: labels.msgNoMetrics,
														type: STR_INFO
													}
												);

												if (pageViewsChartContainer) {
													pageViewsChartContainer.hide();
												}

												if (loadTimesChartContainer) {
													loadTimesChartContainer.hide();
												}

												if (summaryTableContainer) {
													summaryTableContainer.hide();
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

										instance._noDataPeriodUpdateEnabled = false;
										instance._summaryUpdateEnabled = true;

										updatingNode.hide();
									}
								}
							}
						);
					},

					_updateSiteSelect: function() {
						var instance = this;

						var companies = instance._companies;
						var labels = instance._labels;

						var propertiesArray = [
							{
								id: 0,
								name: labels.all
							}
						];

						for (var i = 0; i < companies.length; i++) {
							var company = companies[i];

							if (company.companyId == instance._companySelect.val()) {
								var sites = company.sites;

								for (var j = 0; j < sites.length; j++) {
									var site = sites[j];

									var name = site.friendlyURL;

									if (site.organizationSite) {
										name = name + ' (' + labels.organizationSite + ')';
									}

									propertiesArray.push(
										{
											id: site.groupId,
											name: name
										}
									);
								}
							}
						}

						var html = instance._createTemplatedHTML(
							{
								propertiesArray: propertiesArray,
								template: TPL_OPTION
							}
						);

						instance._siteSelect.html(html);
					},

					_updateSummaryTableTotals: function(data) {
						var instance = this;

						var summaryTableContainer = instance._summaryTableContainer;

						var totalsRow = summaryTableContainer.one('.totals');

						if (totalsRow) {
							var avgLoadTimeCell = totalsRow.one('.avg-load-time');

							if (avgLoadTimeCell) {
								avgLoadTimeCell.html(data.avgLoadTime);
							}

							var minLoadTimeCell = totalsRow.one('.min-load-time');

							if (minLoadTimeCell) {
								minLoadTimeCell.html(data.minLoadTime);
							}

							var maxLoadTimeCell = totalsRow.one('.max-load-time');

							if (maxLoadTimeCell) {
								maxLoadTimeCell.html(data.maxLoadTime);
							}

							var pageViewsCell = totalsRow.one('.page-views');

							if (pageViewsCell) {
								pageViewsCell.html(data.pageViews);
							}
						}
					}
				}
			}
		);

		Liferay.Portlet.LCSServer = LCSServer;
	},
	'',
	{
		requires: ['aui-datatable', 'aui-datatype', 'aui-io-deprecated', 'aui-tabview', 'aui-tooltip-delegate', 'charts', 'datatable-paginator', 'event-valuechange', 'lcs-base', 'liferay-portlet-base', 'liferay-portlet-url', 'liferay-util-window']
	}
);