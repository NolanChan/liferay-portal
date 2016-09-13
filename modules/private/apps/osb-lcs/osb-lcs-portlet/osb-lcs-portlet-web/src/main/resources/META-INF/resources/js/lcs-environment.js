AUI.add(
	'lcs-environment',
	function(A) {
		var EVENT_CLICK = 'click';

		var LCSEnvironment = A.Component.create(
			{
				AUGMENTS: [Liferay.LCSBase, Liferay.PortletBase],

				EXTENDS: A.Base,

				NAME: 'lcs-environment',

				prototype: {
					initializeRegistrationPage: function(config) {
						var instance = this;

						var portalPropertiesLCSServiceEnabledCheckbox = instance.byId(config.portalPropertiesLCSServiceEnabled + 'Checkbox');
						var sensitivePropertiesContainer = instance.byId('sensitiveProperties');
						var sensitivePropertiesNote = instance.byId('sensitivePropertiesNote');

						if (portalPropertiesLCSServiceEnabledCheckbox && sensitivePropertiesContainer && sensitivePropertiesNote) {
							portalPropertiesLCSServiceEnabledCheckbox.on(
								'change',
								function(event) {
									var checked = portalPropertiesLCSServiceEnabledCheckbox.attr('checked');

									sensitivePropertiesContainer.toggle(checked);
									sensitivePropertiesNote.toggle(checked);
								}
							);
						}

						var sensitivePropertiesToggler = instance.byId('sensitivePropertiesToggler');

						if (sensitivePropertiesContainer && sensitivePropertiesToggler) {
							sensitivePropertiesToggler.on(
								EVENT_CLICK,
								function(event) {
									sensitivePropertiesContainer.toggle();
								}
							);
						}

						if (config.tokenCreated) {
							var form = instance.byId('fm');
							var generateButton = instance.byId('generate');

							generateButton.on(
								EVENT_CLICK,
								function(event) {
									if (confirm(config.labels.msgConfirmRegenerate)) {
										form.submit();
									}
								}
							);
						}

						var lcsServices = instance.byId('lcsServices');
						var updateAlert = instance.byId('updateAlert');

						if (lcsServices && updateAlert) {
							lcsServices.delegate(
								EVENT_CLICK,
								function(event) {
									updateAlert.show();
								},
								'label'
							);
						}
					},

					initializeSettingsPage: function(config) {
						var instance = this;

						var deleteLCSClusterEntryURL = config.deleteLCSClusterEntryURL;
						var msgConfirmDeleteEnvironment = config.msgConfirmDeleteEnvironment;

						var deleteEnvironmentButton = instance.byId('deleteEnvironment');

						if (deleteEnvironmentButton) {
							deleteEnvironmentButton.on(
								EVENT_CLICK,
								function(event) {
									if (confirm(msgConfirmDeleteEnvironment)) {
										window.location.href = deleteLCSClusterEntryURL;
									}
								}
							);
						}
					}
				}
			}
		);

		Liferay.Portlet.LCSEnvironment = LCSEnvironment;
	},
	'',
	{
		requires: ['aui-io-deprecated', 'lcs-base', 'liferay-portlet-base']
	}
);