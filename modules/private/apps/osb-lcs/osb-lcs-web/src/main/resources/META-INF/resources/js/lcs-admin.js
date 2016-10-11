AUI.add(
	'lcs-admin',
	function(A) {
		var Lang = A.Lang;

		var CSS_BTN_OFF = 'btn-danger';

		var CSS_BTN_ON = 'btn-success';

		var CSS_LCS_CLUSTER_NODE_PROPERTIES_DIALOG = 'lcs-cluster-node-properties-dialog';

		var CSS_PROPERTIES_DEFAULT = 'osb-lcs-properties-default';

		var CSS_PROPERTIES_VALUE = 'osb-lcs-properties-value';

		var EVENT_CLICK = 'click';

		var STR_BODY_CONTENT = 'bodyContent';

		var STR_RESPONSE_DATA = 'responseData';

		var STR_SUCCESS = 'success';

		var TPL_PROPERTY_VALUE = '<div class="{cssClass}"><h3>{title}</h3>{value}</div>';

		var LCSAdmin = A.Component.create(
			{
				AUGMENTS: [Liferay.LCSBase, Liferay.PortletBase],

				EXTENDS: A.Base,

				NAME: 'lcs-admin',

				prototype: {
					initializeLCSPortalPage: function(config) {
						var instance = this;

						instance._labels = config.labels;

						var billingButton = instance.byId('billingButton');
						var billingEnabledInput = instance.byId('billingEnabled');

						if (billingButton && billingEnabledInput) {
							instance._initializeToggleButton(
								{
									buttonNode: billingButton,
									inputNode: billingEnabledInput,
									pressed: config.billingEnabled
								}
							);
						}

						var messageForwardDevButton = instance.byId('messageForwardDevButton');
						var messageForwardDevEnabledInput = instance.byId('messageForwardDevEnabled');

						if (messageForwardDevButton && messageForwardDevEnabledInput) {
							instance._initializeToggleButton(
								{
									buttonNode: messageForwardDevButton,
									inputNode: messageForwardDevEnabledInput,
									pressed: config.messageForwardDevEnabled
								}
							);
						}

						var messageForwardQAButton = instance.byId('messageForwardQAButton');
						var messageForwardQAEnabledInput = instance.byId('messageForwardQAEnabled');

						if (messageForwardQAButton && messageForwardQAEnabledInput) {
							instance._initializeToggleButton(
								{
									buttonNode: messageForwardQAButton,
									inputNode: messageForwardQAEnabledInput,
									pressed: config.messageForwardQAEnabled
								}
							);
						}

						var sendingEmailsButton = instance.byId('sendingEmailsButton');
						var sendingEmailsEnabledInput = instance.byId('sendingEmailsEnabled');

						if (sendingEmailsButton && sendingEmailsEnabledInput) {
							instance._initializeToggleButton(
								{
									buttonNode: sendingEmailsButton,
									inputNode: sendingEmailsEnabledInput,
									pressed: config.sendingEmailsEnabled
								}
							);
						}

						var subscriptionsButton = instance.byId('subscriptionsButton');
						var subscriptionsEnabledInput = instance.byId('subscriptionsEnabled');

						if (subscriptionsButton && subscriptionsEnabledInput) {
							instance._initializeToggleButton(
								{
									buttonNode: subscriptionsButton,
									inputNode: subscriptionsEnabledInput,
									pressed: config.subscriptionsEnabled
								}
							);
						}
					},

					initializeNodesPanel: function(config) {
						var instance = this;

						var labels = config.labels;

						instance._labels = config.labels;
						instance._urls = config.urls;

						var searchContainer = instance.byId('lcsClusterNodesTable');

						searchContainer.delegate(
							EVENT_CLICK,
							function(event) {
								var icon = event.currentTarget;

								var dataKey = icon.attr('data-key');

								var lcsClusterNodePropertiesPanel = Liferay.Util.Window.getWindow(
									{
										dialog: {
											centered: true,
											constrain2view: true,
											cssClass: CSS_LCS_CLUSTER_NODE_PROPERTIES_DIALOG,
											resizable: {
												handles: 'l, b, r'
											}
										},
										title: labels.difference
									}
								);

								var columns = [
									{
										key: 'key',
										label: labels.key,
										sortable: true
									},
									{
										allowHTML: true,
										formatter: function(obj) {
											var value = '';

											var originalValue = obj.data.originalValue;

											if (originalValue) {
												var originalValueSubs = {
													cssClass: CSS_PROPERTIES_DEFAULT,
													title: labels.default,
													value: originalValue
												};

												value = Lang.sub(TPL_PROPERTY_VALUE, originalValueSubs);
											}

											var lcsClusterNodeValue = obj.data.lcsClusterNodeValue;

											if (lcsClusterNodeValue) {
												var lcsClusterNodeValueSubs = {
													cssClass: CSS_PROPERTIES_VALUE,
													title: labels.value,
													value: lcsClusterNodeValue
												};

												value += Lang.sub(TPL_PROPERTY_VALUE, lcsClusterNodeValueSubs);
											}

											return value;
										},
										key: 'lcsClusterNodeValue',
										label: instance._labels.propertiesValue
									}
								];

								instance._lcsClusterNodePropertiesPanel = lcsClusterNodePropertiesPanel;

								lcsClusterNodePropertiesPanel.hide();

								var requestData = {
									key: dataKey
								};

								A.io.request(
									instance._urls.getPortalPropertiesDifference,
									{
										data: instance.ns(requestData),
										dataType: 'json',
										on: {
											failure: function(event, id, obj) {
												lcsClusterNodePropertiesPanel.set(STR_BODY_CONTENT, instance._errorMessage);

												lcsClusterNodePropertiesPanel.show();
											},
											success: function(event, id, obj) {
												if (!obj || !obj.responseText) {
													lcsClusterNodePropertiesPanel.set(STR_BODY_CONTENT, labels.msgPropertiesNotUploaded);

													lcsClusterNodePropertiesPanel.show();
												}
												else {
													var responseData = this.get(STR_RESPONSE_DATA);

													if (responseData.result == STR_SUCCESS) {
														var data = responseData.data;

														lcsClusterNodePropertiesPanel.show();

														var table = new A.DataTable(
															{
																columns: columns,
																recordset: data
															}
														);

														var propertiesModalBody = A.one('.modal-body');

														table.render(propertiesModalBody);

														var tableContent = propertiesModalBody.one('.table-content');

														instance._propertiesModalBody = propertiesModalBody;

														instance._initializeTableSearch(
															{
																container: tableContent,
																data: data,
																table: table
															}
														);
													}
												}
											}
										}
									}
								);
							},
							'tbody tr td .properties-difference-button'
						);

						searchContainer.delegate(
							EVENT_CLICK,
							function(event) {
								var icon = event.currentTarget;

								var container = icon.ancestor();

								var secondaryStatuses = container.all('.secondary');

								if (secondaryStatuses.size()) {
									secondaryStatuses.toggle();
								}

								icon.toggleClass('icon-minus');
								icon.toggleClass('icon-plus');
							},
							'.toggle-secondary-statuses'
						);
					},

					_initializeToggleButton: function(config) {
						var instance = this;

						var inputNode = config.inputNode;
						var pressed = config.pressed;

						var labels = instance._labels;

						var toggleLabels = {
							false: labels.off,
							true: labels.on
						};

						new A.ToggleButton(
							{
								cssClass: pressed ? CSS_BTN_ON : CSS_BTN_OFF,
								domType: 'button',
								label: toggleLabels[pressed],
								on: {
									'click': function(event) {
										var node = event.currentTarget.getNode();

										pressed = !pressed;

										node.html(toggleLabels[pressed]);

										node.toggleClass(CSS_BTN_OFF, !pressed);
										node.toggleClass(CSS_BTN_ON, pressed);

										inputNode.val(pressed);
									}
								},
								pressed: pressed,
								srcNode: config.buttonNode
							}
						).render();
					}
				}
			}
		);

		Liferay.Portlet.LCSAdmin = LCSAdmin;
	},
	'',
	{
		requires: ['aui-button', 'aui-datatable', 'aui-delegate', 'aui-io-deprecated', 'aui-modal', 'lcs-base', 'liferay-portlet-base', 'liferay-util-window']
	}
);