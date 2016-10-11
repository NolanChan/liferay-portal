YUI.add(
	'lcs-subscription-type-cell-editor',
	function(A) {
		var Lang = A.Lang;

		var TPL_ELASTIC = '<label class="elastic-container">' +
				'<input class="checkbox" type="checkbox" />' +
				'<span class="elastic-label">{label}</span>' +
				'<span class="icon-question-sign" title="{elasticHelp}"></span>' +
			'</label>';

		var TPL_OPTION = '<option value="{subscriptionTypeName}">{subscriptionTypeLabel}</option>';

		var TPL_SUBSCRIPTION_TYPE = '<select class="subscription-type">{options}</select><br />';

		var LCSSubscriptionTypeCellEditor = A.Component.create(
			{
				EXTENDS: A.BaseCellEditor,

				NAME: 'LCSSubscriptionTypeCellEditor',

				prototype: {
					initializer: function(config) {
						var instance = this;

						var optionsHTML = A.Array.map(
							config.subscriptionTypes,
							function(item, index, collection) {
								return Lang.sub(TPL_OPTION, item);
							}
						).join('');

						var html = Lang.sub(
							TPL_SUBSCRIPTION_TYPE,
							{
								options: optionsHTML
							}
						);

						var labels = config.strings;

						if (config.hasElasticSubscription) {
							html += Lang.sub(
								TPL_ELASTIC,
								{
									elasticHelp: labels.elasticHelp,
									label: labels.elastic
								}
							);
						}

						instance.ELEMENT_TEMPLATE = html;

						new A.TooltipDelegate(
							{
								trigger: '.elastic-container .icon-question-sign',
								zIndex: 10000
							}
						);
					}
				}
			}
		);

		A.LCSSubscriptionTypeCellEditor = LCSSubscriptionTypeCellEditor;
	},
	'',
	{
		'requires': [
			'aui-datatable-edit', 'aui-tooltip-delegate'
		]
	}
);