/* global _gaq ga */

AUI.add(
	'lcs-base',
	function(A) {
		var Lang = A.Lang;

		var COLOR_BLUE = '#4A96E8';

		var COLOR_WHITE = '#FFF';

		var COMMON_CHART_OPTIONS = {
			chart: {
				resetZoomButton: {
					theme: {
						fill: COLOR_WHITE,
						states: {
							hover: {
								fill: '#E6F5FC',
								stroke: 'silver',
								style: {
									cursor: 'pointer'
								}
							}
						}
					}
				},
				spacing: [0, 0, 0, 0]
			},
			colors: [
				COLOR_BLUE,
				'#555555',
				'#90ED7D',
				'#F7A35C',
				'#8085E9',
				'#F15C80',
				'#E4D354',
				'#2B908F',
				'#F45B5B',
				'#91E8E1'
			],
			credits: false,
			plotOptions: {
				area: {
					fillOpacity: 0.3,
					lineWidth: 1,
					marker: {
						fillColor: COLOR_WHITE,
						lineColor: COLOR_BLUE,
						lineWidth: 1
					},
					states: {
						hover: {
							lineWidth: 1
						}
					}
				},
				arearange: {
					color: COLOR_BLUE,
					fillOpacity: 0.3,
					lineWidth: 0,
					states: {
						hover: {
							lineWidthPlus: 0
						}
					}
				},
				line: {
					lineWidth: 1,
					marker: {
						fillColor: COLOR_WHITE,
						lineColor: COLOR_BLUE,
						lineWidth: 1
					},
					states: {
						hover: {
							lineWidth: 1
						}
					}
				},
				series: {
					connectNulls: true
				}
			},
			title: {
				align: 'left',
				style: {
					color: '#555',
					fontFamily: 'Helvetica Neue, Helvetica, Arial, sans-serif',
					fontSize: '13.5px',
					fontWeight: 'bold'
				}
			},
			yAxis: {
				allowDecimals: false,
				min: 0,
				title: false
			}
		};

		var CSS_SELECTED = 'selected';

		var STR_OPACITY = 'opacity';

		var TPL_MESSAGE = '<div class="alert alert-{type}">{message}</div>';

		var TPL_TABLE_SEARCH = '<input class="search-input" placeholder="{search}" type="text" value="" />';

		var LCSBase = function() {};

		LCSBase.prototype = {
			initializer: function(config) {
				var instance = this;

				instance._commonChartOptions = COMMON_CHART_OPTIONS;
				instance._errorMessage = config.errorMessage;
				instance._lcsConstants = config.lcsConstants;
				instance._successMessage = config.successMessage;

				var now = new Date();

				instance._timezoneOffset = now.getTimezoneOffset() * 60000;
			},

			_createTemplatedHTML: function(config) {
				var instance = this;

				return A.Array.map(
					config.propertiesArray,
					function(item, index, collection) {
						return A.Lang.sub(config.template, item);
					}
				).join('');
			},

			_filterTableData: function(config) {
				var instance = this;

				var additionalFilter = config.additionalFilter;
				var data = config.data;
				var text = config.text;

				data = A.Array.filter(
					data,
					function(item, index) {
						var showItem = false;

						for (var key in item) {
							if (item.hasOwnProperty(key)) {
								var value = item[key];

								if (!value && value !== 0) {
									continue;
								}

								if (Lang.isNumber(value)) {
									value = value.toString();
								}

								if (!Lang.isString(value)) {
									continue;
								}

								value = value.toLowerCase();

								if (additionalFilter && additionalFilter(key, value)) {
									showItem = false;

									break;
								}

								if (value.indexOf(text.toLowerCase()) !== -1) {
									showItem = true;
								}

								if (showItem && !additionalFilter) {
									break;
								}
							}
						}

						return showItem;
					}
				);

				return data;
			},

			_initializeSelectableRows: function(config) {
				var instance = this;

				var container = config.container;

				if (container) {
					var table = container.one('.table');

					if (table) {
						var dataAttr = config.dataAttr;
						var dataSelector = config.dataSelector;
						var onClick = config.onClick;

						table.addClass('table-selectable-rows');

						table.delegate(
							'click',
							function(event) {
								var selected = container.all('.' + CSS_SELECTED);

								selected.removeClass(CSS_SELECTED);

								var currentTarget = event.currentTarget;

								currentTarget.addClass(CSS_SELECTED);

								var actionLink = currentTarget.one('.row-select-action a');

								if (actionLink) {
									window.location.href = actionLink.attr('href');
								}

								if (dataAttr && dataSelector && onClick) {
									var dataNode = currentTarget.one(dataSelector);

									if (dataNode) {
										onClick(
											{
												data: dataNode.attr(dataAttr),
												label: dataNode.html()
											}
										);
									}
								}
							},
							'tbody tr'
						);
					}
				}
			},

			_initializeTableSearch: function(config) {
				var instance = this;

				var messageHTML = A.Lang.sub(
					TPL_MESSAGE,
					{
						message: instance._labels.msgNoSearchResults,
						type: 'no-search-results'
					}
				);

				var messageNode = A.Node.create(messageHTML);

				messageNode.hide();

				var table = config.table;

				table.messageNode = messageNode;
				table.paginator = config.paginator;
				table.rowsPerPage = config.rowsPerPage;

				var container = config.container;

				container.append(messageNode);

				var searchHTML = Lang.sub(
					TPL_TABLE_SEARCH,
					{
						search: instance._labels.search
					}
				);

				var searchNode = A.Node.create(searchHTML);

				var updateSearchableTableTask = A.debounce('_updateSearchableTable', 100, instance);

				searchNode.on(
					'valuechange',
					function(event) {
						updateSearchableTableTask(
							{
								data: config.data,
								table: table
							}
						);
					}
				);

				table.searchNode = searchNode;

				var header = container.one('.header');

				if (header) {
					var optionsToggler = header.one('.options-toggler');

					if (optionsToggler) {
						optionsToggler.insert(searchNode, 'before');
					}
					else {
						header.append(searchNode);
					}
				}
				else {
					container.prepend(searchNode);
				}

				container.addClass('searchable');
			},

			_sendGAEvent: function(config) {
				var action = config.action;
				var category = config.category;

				if (typeof _gaq !== 'undefined') {
					_gaq.push(['_trackEvent', category, action]);
				}
				else if (typeof ga !== 'undefined') {
					ga(
						'send',
						{
							eventAction: action,
							eventCategory: category,
							hitType: 'event'
						}
					);
				}
			},

			_showMessage: function(config) {
				var instance = this;

				var container = config.container;

				if (container) {
					var messageHTML = A.Lang.sub(
						TPL_MESSAGE,
						{
							message: config.message,
							type: config.type
						}
					);

					container.html(messageHTML);

					container.show();

					if (container.getStyle(STR_OPACITY) == '0') {
						container.setStyle(STR_OPACITY, 1);
					}
				}
			},

			_updateSearchableTable: function(config) {
				var instance = this;

				var table = config.table;

				var searchNode = table.searchNode;

				var text = searchNode.val();

				var additionalFilter = config.additionalFilter;
				var data = config.data;

				if (text || additionalFilter) {
					var filterConfig = {
						additionalFilter: additionalFilter,
						data: data,
						text: text
					};

					data = instance._filterTableData(filterConfig);
				}

				table.set('data', data);

				var dataLength = data.length;

				var messageNode = table.messageNode;

				messageNode.toggle(dataLength == 0);

				var paginator = table.paginator;
				var rowsPerPage = table.rowsPerPage;

				if (paginator && rowsPerPage) {
					paginator.toggle(dataLength > rowsPerPage);

					var paginatorModel = table.get('paginatorModel');

					paginatorModel.set('totalItems', dataLength);
				}
			}
		};

		Liferay.LCSBase = LCSBase;
	},
	'',
	{
		requires: ['aui-debounce', 'aui-io', 'event-valuechange']
	}
);