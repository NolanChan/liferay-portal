AUI.add(
	'lcs',
	function(A) {
		var Lang = A.Lang;

		var padNumber = A.rbind('padNumber', Lang.String, 2);

		var CSS_ALERT_DANGER = 'alert-danger';

		var CSS_ALERT_SUCCESS = 'alert-success';

		var CSS_ALERT_WARNING = 'alert-warning';

		var EVENT_CLICK = 'click';

		var STR_BLANK = '';

		var STR_CHECKED = 'checked';

		var STR_DOUBLE_ZERO = '00';

		var STR_REPONSE_DATA = 'responseData';

		var LCS = A.Component.create(
			{
				AUGMENTS: [Liferay.PortletBase],

				EXTENDS: A.Base,

				NAME: 'lcs',

				prototype: {
					initializeConnectionPage: function(config) {
						var instance = this;

						var cluster = config.cluster;
						var connectURL = config.connectURL;
						var disconnectURL = config.disconnectURL;
						var lcsConstants = config.lcsConstants;
						var pending = config.pending;
						var ready = config.ready;

						instance._connectionStatusURL = config.connectionStatusURL;
						instance._handshakeTime = config.handshakeTime;
						instance._labels = config.labels;
						instance._ready = ready;

						instance._connectionAlertContainer = instance.byId('connectionAlertContainer');
						instance._durationContainer = instance.byId('duration');
						instance._gatewayUnavailableAlert = instance.byId('lcsGatewayUnavailable');
						instance._heartbeatExpiredAlert = instance.byId('heartbeatExpired');
						instance._spinner = instance.byId('spinner');

						var connectionStatusContainer = instance.byId('connectionStatus');

						if (connectionStatusContainer) {
							instance._connectionStatusContainer = connectionStatusContainer;

							instance._connectionStatusLabel = connectionStatusContainer.one('.lead');
							instance._connectionStatusSpinner = connectionStatusContainer.one('.icon-spin');
						}

						var getConnectionStatus = A.bind('_getConnectionStatus', instance);

						var applyToSiblingClusterNodesCheckbox = instance.byId('applyToSiblingClusterNodes');
						var connectButton = instance.byId('connect');

						if (connectButton) {
							connectButton.on(
								EVENT_CLICK,
								function(event) {
									Liferay.Util.toggleDisabled(connectButton, true);

									var data = {};

									if (applyToSiblingClusterNodesCheckbox && cluster) {
										data = instance.ns(
											{
												applyToSiblingClusterNodes: applyToSiblingClusterNodesCheckbox.attr(STR_CHECKED)
											}
										);
									}

									A.io.request(
										connectURL,
										{
											data: data,
											dataType: 'JSON',
											method: 'GET',
											on: {
												failure: function(event, id, obj) {
													Liferay.Util.toggleDisabled(connectButton, false);
												},
												success: function(event, id, obj) {
													var responseData = this.get(STR_REPONSE_DATA);

													if (responseData[lcsConstants.JSON_KEY_RESULT] == lcsConstants.JSON_VALUE_SUCCESS) {
														instance._refreshConnectionControls(true, false);

														setTimeout(getConnectionStatus, 1000);
													}
												}
											}
										}
									);
								}
							);

							instance._connectButton = connectButton;
						}

						var disconnectButton = instance.byId('disconnect');

						if (disconnectButton) {
							disconnectButton.on(
								EVENT_CLICK,
								function(event) {
									Liferay.Util.toggleDisabled(disconnectButton, true);

									var data = {};

									if (applyToSiblingClusterNodesCheckbox && cluster) {
										data = instance.ns(
											{
												applyToSiblingClusterNodes: applyToSiblingClusterNodesCheckbox.attr(STR_CHECKED)
											}
										);
									}

									A.io.request(
										disconnectURL,
										{
											data: data,
											dataType: 'JSON',
											method: 'GET',
											on: {
												failure: function(event, id, obj) {
													Liferay.Util.toggleDisabled(disconnectButton, false);
												},
												success: function(event, id, obj) {
													var responseData = this.get(STR_REPONSE_DATA);

													if (responseData[lcsConstants.JSON_KEY_RESULT] == lcsConstants.JSON_VALUE_SUCCESS) {
														instance._refreshConnectionControls(true, true);

														setTimeout(getConnectionStatus, 1000);
													}
												}
											}
										}
									);
								}
							);

							instance._disconnectButton = disconnectButton;
						}

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
												gatewayUnavailableAlert.toggle(!responseData.lcsGatewayAvailable);
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

					_refreshConnectionControls: function(pending, ready) {
						var instance = this;

						var connectionStatusContainer = instance._connectionStatusContainer;

						if (connectionStatusContainer) {
							if (pending) {
								connectionStatusContainer.addClass(CSS_ALERT_WARNING);

								connectionStatusContainer.removeClass(CSS_ALERT_SUCCESS);
								connectionStatusContainer.removeClass(CSS_ALERT_DANGER);
							}
							else {
								connectionStatusContainer.removeClass(CSS_ALERT_WARNING);

								if (ready) {
									connectionStatusContainer.addClass(CSS_ALERT_SUCCESS);
								}
								else {
									connectionStatusContainer.addClass(CSS_ALERT_DANGER);
								}
							}
						}

						var connectionStatusLabel = instance._connectionStatusLabel;
						var labels = instance._labels;

						if (connectionStatusLabel) {
							var label = labels.pending;

							if (!pending) {
								if (ready) {
									label = labels.connected;
								}
								else {
									label = labels.disconnected;
								}
							}

							connectionStatusLabel.html(label);
						}

						var connectionStatusSpinner = instance._connectionStatusSpinner;

						if (connectionStatusSpinner) {
							connectionStatusSpinner.toggle(pending);
						}

						var connectButton = instance._connectButton;

						if (connectButton) {
							if (!pending) {
								connectButton.toggle(!ready);

								Liferay.Util.toggleDisabled(connectButton, ready);
							}
						}

						var disconnectButton = instance._disconnectButton;

						if (disconnectButton) {
							if (!pending) {
								disconnectButton.toggle(ready);

								Liferay.Util.toggleDisabled(disconnectButton, !ready);
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
		requires: ['aui-io-deprecated', 'liferay-portlet-base', 'liferay-portlet-url', 'liferay-util-window']
	}
);