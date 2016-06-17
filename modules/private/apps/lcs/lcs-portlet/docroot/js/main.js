AUI.add(
	'lcs',
	function(A) {
		var Lang = A.Lang;

		var padNumber = A.rbind('padNumber', Lang.String, 2);

		var CSS_CONNECTED = 'connected';

		var CSS_DISCONNECTED = 'disconnected';

		var CSS_SYNCHRONIZING = 'synchronizing';

		var EVENT_CHANGE = 'change';

		var EVENT_CLICK = 'click';

		var STR_BLANK = '';

		var STR_CHECKED = 'checked';

		var STR_DOUBLE_ZERO = '00';

		var STR_REPONSE_DATA = 'responseData';

		var STR_SUCCESS = 'success';

		var LCS = A.Component.create(
			{
				AUGMENTS: [Liferay.PortletBase],

				EXTENDS: A.Base,

				NAME: 'lcs',

				prototype: {
					initializeConfigureLCSServicesPage: function(config) {
						var instance = this;

						var portalPropertiesSecuritySensitiveNodes = instance.all('.portal-properties-security-sensitive');

						portalPropertiesSecuritySensitiveNodes.each(
							function(item, index, collection) {
								instance._initializeTooltip(item, config.portalPropertiesSecuritySensitive);
							}
						);

						var enableAllServicesCheckbox = instance.byId('enableAllLCSServices');
						var lcsServicesPanel = instance.byId('lcsServicesPanel');
						var propertiesPanel = instance.byId('propertiesPanel');

						var lcsServicesCheckboxes = lcsServicesPanel.all('input:checkbox:not(:disabled)');

						enableAllServicesCheckbox.on(
							EVENT_CHANGE,
							function(event) {
								var enableAllLCSServices = enableAllServicesCheckbox.attr(STR_CHECKED);

								lcsServicesCheckboxes.attr(STR_CHECKED, enableAllLCSServices);

								lcsServicesPanel.toggle(!enableAllLCSServices);

								propertiesPanel.toggle(enableAllLCSServices);
							}
						);

						var portalPropertiesLCSServiceEnabledCheckbox = instance.byId(config.portalPropertiesLCSServiceEnabled);

						portalPropertiesLCSServiceEnabledCheckbox.on(
							EVENT_CHANGE,
							function(event) {
								var val = portalPropertiesLCSServiceEnabledCheckbox.attr(STR_CHECKED);

								propertiesPanel.toggle(val);
							}
						);
					},

					initializeLCSClusterNodePage: function(config) {
						var instance = this;

						var connectURL = config.connectURL;
						var disconnectURL = config.disconnectURL;
						var pending = config.pending;
						var ready = config.ready;

						instance._connectionStatusURL = config.connectionStatusURL;
						instance._handshakeTime = config.handshakeTime;
						instance._labelConnected = config.labelConnected;
						instance._labelConnectedHelp = config.labelConnectedHelp;
						instance._labelDisconnected = config.labelDisconnected;
						instance._labelDisconnectedHelp = config.labelDisconnectedHelp;
						instance._labelPending = config.labelPending;
						instance._labelPendingHelp = config.labelPendingHelp;
						instance._ready = ready;

						var applyToSiblingClusterNodesCheckbox = instance.byId('applyToSiblingClusterNodes');
						var connectButton = instance.byId('connect');
						var disconnectButton = instance.byId('disconnect');

						instance._connectionAlertContainer = instance.byId('connectionAlertContainer');
						instance._durationContainer = instance.byId('duration');
						instance._gatewayUnavailableAlert = instance.byId('lcsGatewayUnavailable');
						instance._heartbeatExpiredAlert = instance.byId('heartbeatExpired');

						var getConnectionStatus = A.bind('_getConnectionStatus', instance);

						connectButton.on(
							EVENT_CLICK,
							function(event) {
								A.io.request(
									connectURL,
									{
										data: Liferay.Util.ns(
											instance.NS,
											{
												applyToSiblingClusterNodes: applyToSiblingClusterNodesCheckbox.attr(STR_CHECKED)
											}
										),
										dataType: 'JSON',
										method: 'GET',
										on: {
											success: function(event, id, obj) {
												var responseData = this.get(STR_REPONSE_DATA);

												if (responseData.result == STR_SUCCESS) {
													instance._refreshConnectionControls(true, false);

													setTimeout(getConnectionStatus, 1000);
												}
											}
										}
									}
								);
							}
						);

						disconnectButton.on(
							EVENT_CLICK,
							function(event) {
								A.io.request(
									disconnectURL,
									{
										data: Liferay.Util.ns(
											instance.NS,
											{
												applyToSiblingClusterNodes: applyToSiblingClusterNodesCheckbox.attr(STR_CHECKED)
											}
										),
										dataType: 'JSON',
										method: 'GET',
										on: {
											success: function(event, id, obj) {
												var responseData = this.get(STR_REPONSE_DATA);

												if (responseData.result == STR_SUCCESS) {
													instance._refreshConnectionControls(true, true);

													setTimeout(getConnectionStatus, 1000);
												}
											}
										}
									}
								);
							}
						);

						instance._connectButton = connectButton;
						instance._disconnectButton = disconnectButton;

						var connectNode = instance.byId('connect');
						var discconnectNode = instance.byId('disconnect');

						instance._initializeTooltip(connectNode, config.tooltipConnect);
						instance._initializeTooltip(discconnectNode, config.tooltipDisconnect);

						var connectionStatusNode = instance.byId('connectionStatus');

						if (connectionStatusNode) {
							instance._connectionStatusNode = connectionStatusNode;

							var connectionStatusHelp = connectionStatusNode.one('.connection-help');

							if (connectionStatusHelp) {
								instance._connectionStatusHelp = connectionStatusHelp;
							}

							var connectionStatusLabel = connectionStatusNode.one('.connection-label');

							if (connectionStatusLabel) {
								instance._connectionStatusLabel = connectionStatusLabel;
							}
						}

						var erroneousClusterNodes = instance.all('.cluster-node-error');

						erroneousClusterNodes.each(
							function(item, index, collection) {
								instance._initializeTooltip(item, config.tooltipClusterNodeError);
							}
						);

						if (pending) {
							instance._getConnectionStatus();
						}

						if (ready) {
							instance._updateDuration();
						}
					},

					_getConnectionStatus: function() {
						var instance = this;

						A.io.request(
							instance._connectionStatusURL,
							{
								dataType: 'JSON',
								method: 'GET',
								on: {
									success: function(event, id, obj) {
										var responseData = this.get(STR_REPONSE_DATA);

										if (responseData.pending) {
											setTimeout(A.bind('_getConnectionStatus', instance), 1000);
										}
										else {
											var responseDataReady = responseData.ready;

											instance._ready = responseDataReady;

											instance._refreshConnectionControls(false, responseDataReady);

											var gatewayUnavailableAlert = instance._gatewayUnavailableAlert;

											if (gatewayUnavailableAlert) {
												gatewayUnavailableAlert.toggle(!responseDataReady);
											}

											var heartbeatExpiredAlert = instance._heartbeatExpiredAlert;

											if (heartbeatExpiredAlert) {
												heartbeatExpiredAlert.toggle(responseData.heartbeatExpiredError);
											}

											if (responseDataReady) {
												instance._handshakeTime = Lang.now();

												instance._updateDuration();
											}
										}
									}
								}
							}
						);
					},

					_initializeTooltip: function(trigger, bodyContent) {
						var instance = this;

						if (trigger) {
							trigger.on(
								'mouseover',
								function(event) {
									Liferay.Portal.ToolTip.show(this, bodyContent);
								}
							);
						}
					},

					_refreshConnectionControls: function(pending, ready) {
						var instance = this;

						var connectionStatusNode = instance._connectionStatusNode;

						if (connectionStatusNode) {
							if (pending) {
								connectionStatusNode.removeClass(CSS_CONNECTED);
								connectionStatusNode.removeClass(CSS_DISCONNECTED);
								connectionStatusNode.addClass(CSS_SYNCHRONIZING);
							}
							else {
								connectionStatusNode.removeClass(CSS_SYNCHRONIZING);

								if (ready) {
									connectionStatusNode.addClass(CSS_CONNECTED);
								}
								else {
									connectionStatusNode.addClass(CSS_DISCONNECTED);
								}
							}
						}

						var connectionStatusLabel = instance._connectionStatusLabel;

						if (connectionStatusLabel) {
							var statusLabel = instance._labelPending;

							if (!pending) {
								if (ready) {
									statusLabel = instance._labelConnected;
								}
								else {
									statusLabel = instance._labelDisconnected;
								}
							}

							connectionStatusLabel.html(statusLabel);
						}

						var connectionStatusHelp = instance._connectionStatusHelp;

						if (connectionStatusHelp) {
							connectionStatusHelp.hide();
						}

						var connectionAlertContainer = instance._connectionAlertContainer;

						if (connectionAlertContainer) {
							connectionAlertContainer.toggle(pending);
						}

						var connectButton = instance._connectButton;

						if (connectButton) {
							Liferay.Util.toggleDisabled(connectButton, pending);

							if (!pending) {
								connectButton.toggle(!ready);
							}
						}

						var disconnectButton = instance._disconnectButton;

						if (disconnectButton) {
							Liferay.Util.toggleDisabled(disconnectButton, pending);

							if (!pending) {
								disconnectButton.toggle(ready);
							}
						}

						if (pending) {
							var tooltip = A.getBody().one('> .tooltip');

							if (tooltip) {
								tooltip.addClass('tooltip-hidden');
							}
						}
					},

					_updateDuration: function() {
						var instance = this;

						var duration = {
							days: STR_BLANK,
							daysSeparator: STR_BLANK,
							hours: STR_DOUBLE_ZERO,
							minutes: STR_DOUBLE_ZERO,
							seconds: STR_DOUBLE_ZERO
						};

						var handshakeTime = instance._handshakeTime;

						if (instance._ready && handshakeTime) {
							var totalSeconds = Math.floor((Lang.now() - handshakeTime) / 1000);

							var days = Math.floor(totalSeconds / 86400);
							var hours = Math.floor(totalSeconds / 3600) % 24;
							var minutes = Math.floor(totalSeconds / 60) % 60;

							var seconds = totalSeconds % 60;

							if (days) {
								duration.days = padNumber(days);

								duration.daysSeparator = ':';
							}

							duration.hours = padNumber(hours);
							duration.minutes = padNumber(minutes);
							duration.seconds = padNumber(seconds);

							setTimeout(
								A.bind('_updateDuration', instance),
								1000
							);
						}

						var durationHTML = Lang.sub('{days}{daysSeparator}{hours}:{minutes}:{seconds}', duration);

						instance._durationContainer.html(durationHTML);
					}
				}
			}
		);

		Liferay.Portlet.LCS = LCS;
	},
	'',
	{
		requires: ['aui-io-deprecated', 'aui-loading-mask-deprecated', 'liferay-portlet-base', 'liferay-portlet-url', 'liferay-util-window', 'resize']
	}
);