;(function() {
	AUI().applyConfig(
		{
			groups: {
				'field-checkbox': {
					base: '/o/ddm-checkbox/',
					modules: {
						'liferay-checkbox-field': {
							path: 'checkbox.soy.js',
							requires: [
								'liferay-soy-utils'
							]
						}
					},
					root: '/o/ddm-checkbox/'
				},
				'field-text': {
					base: '/o/ddm-text/',
					modules: {
						'liferay-text-field': {
							path: 'text.soy.js',
							requires: [
								'liferay-soy-utils'
							]
						}
					},
					root: '/o/ddm-text/'
				},
				'form': {
					base: '/o/ddm-form-renderer/js/',
					modules: {
						'liferay-ddm-form-renderer': {
							path: 'form.js',
							requires: [
								'array-extras',
								'liferay-ddm-form-renderer-field',
								'liferay-ddm-form-renderer-field-types',
								'liferay-ddm-form-renderer-util'
							]
						},
						'liferay-ddm-form-renderer-field': {
							path: 'field.js',
							requires: [
								'aui-boolean-data-editor',
								'aui-form-builder-field-base',
								'aui-form-field',
								'aui-options-data-editor',
								'aui-radio-group-data-editor',
								'aui-tabs-data-editor',
								'aui-text-data-editor',
								'liferay-checkbox-field',
								'liferay-ddm-form-renderer-field-types',
								'liferay-text-field'
							]
						},
						'liferay-ddm-form-renderer-util': {
							path: 'util.js',
							requires: [
								'array-extras',
							]
						},
						'liferay-ddm-form-renderer-field-types': {
							path: 'field_types.js',
							requires: [
								'array-extras',
								'aui-form-builder-field-type',
								'liferay-ddm-form-renderer-field',
								'liferay-ddm-form-renderer-util'
							]
						}
					},
					root: '/o/ddm-form-renderer/js/'
				},
				'third-party': {
					base: '/o/ddm-form-renderer/third-party/',
					modules: {
						'liferay-soy-utils': {
							path: 'soyutils.js'
						}
					},
					root: '/o/ddm-form-renderer/third-party/'
				}
			}
		}
	);
})();