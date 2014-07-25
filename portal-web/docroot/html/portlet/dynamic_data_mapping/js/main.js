AUI.add(
	'liferay-portlet-dynamic-data-mapping',
	function(A) {
		var AArray = A.Array;
		var Lang = A.Lang;

		var BODY = A.getBody();

		var instanceOf = A.instanceOf;
		var isArray = Lang.isArray;
		var isObject = Lang.isObject;
		var isString = Lang.isString;
		var isUndefined = Lang.isUndefined;

		var DEFAULTS_FORM_VALIDATOR = A.config.FormValidator;

		var MAP_HIDDEN_FIELD_ATTRS = {
			checkbox: ['readOnly', 'required'],

			DEFAULT: ['readOnly'],

			separator: ['readOnly', 'required', 'predefinedValue', 'indexType']
		};

		var SETTINGS_TAB_INDEX = 1;

		var STR_BLANK = '';

		var STR_SPACE = ' ';

		DEFAULTS_FORM_VALIDATOR.STRINGS.structureFieldName = Liferay.Language.get('please-enter-only-alphanumeric-characters');

		DEFAULTS_FORM_VALIDATOR.RULES.structureFieldName = function(value) {
			return (/^[\w\-]+$/).test(value);
		};

		var LiferayAvailableField = A.Component.create(
			{
				ATTRS: {
					localizationMap: {
						validator: isObject,
						value: {}
					}
				},

				EXTENDS: A.FormBuilderAvailableField,

				NAME: 'availableField'
			}
		);

		A.LiferayAvailableField = LiferayAvailableField;

		var LiferayFormBuilder = A.Component.create(
			{
				ATTRS: {
					availableFields: {
						validator: isObject,
						valueFn: function() {
							return LiferayFormBuilder.AVAILABLE_FIELDS.DEFAULT;
						}
					},

					portletNamespace: {
						value: STR_BLANK
					},

					portletResourceNamespace: {
						value: STR_BLANK
					},

					strings: {
						value: {
							addNode: Liferay.Language.get('add-field'),
							button: Liferay.Language.get('button'),
							buttonType: Liferay.Language.get('button-type'),
							deleteFieldsMessage: Liferay.Language.get('are-you-sure-you-want-to-delete-the-selected-entries'),
							duplicateMessage: Liferay.Language.get('duplicate'),
							editMessage: Liferay.Language.get('edit'),
							label: Liferay.Language.get('field-label'),
							large: Liferay.Language.get('large'),
							localizable: Liferay.Language.get('localizable'),
							medium: Liferay.Language.get('medium'),
							multiple: Liferay.Language.get('multiple'),
							name: Liferay.Language.get('name'),
							no: Liferay.Language.get('no'),
							options: Liferay.Language.get('options'),
							predefinedValue: Liferay.Language.get('predefined-value'),
							propertyName: Liferay.Language.get('property-name'),
							required: Liferay.Language.get('required'),
							reset: Liferay.Language.get('reset'),
							save: Liferay.Language.get('save'),
							settings: Liferay.Language.get('settings'),
							showLabel: Liferay.Language.get('show-label'),
							small: Liferay.Language.get('small'),
							submit: Liferay.Language.get('submit'),
							tip: Liferay.Language.get('tip'),
							type: Liferay.Language.get('type'),
							value: Liferay.Language.get('value'),
							width: Liferay.Language.get('width'),
							yes: Liferay.Language.get('yes')
						}
					},

					translationManager: {
						validator: isObject,
						value: {}
					},

					validator: {
						setter: function(val) {
							var instance = this;

							var config = A.merge(
								{
									fieldStrings: {
										name: {
											required: Liferay.Language.get('this-field-is-required')
										}
									},
									rules: {
										name: {
											required: true,
											structureFieldName: true
										}
									}
								},
								val
							);

							return config;
						},
						value: {}
					}
				},

				EXTENDS: A.FormBuilder,

				LOCALIZABLE_FIELD_ATTRS: ['label', 'options', 'predefinedValue', 'style', 'tip'],

				NAME: 'liferayformbuilder',

				UNIQUE_FIELD_NAMES_MAP: new A.Map(),

				UNLOCALIZABLE_FIELD_ATTRS: ['dataType', 'fieldNamespace', 'indexType', 'localizable', 'multiple', 'name', 'readOnly', 'repeatable', 'required', 'type'],

				prototype: {
					initializer: function() {
						var instance = this;

						instance.MAP_HIDDEN_FIELD_ATTRS = A.clone(MAP_HIDDEN_FIELD_ATTRS);

						var translationManager = instance.translationManager = new Liferay.TranslationManager(instance.get('translationManager'));

						instance.after(
							'render',
							function(event) {
								translationManager.render();
							}
						);

						instance.after('fieldsChange', instance._afterFieldsChange);

						instance.addTarget(Liferay.Util.getOpener().Liferay);

						instance._toggleInputDirection(translationManager.get('defaultLocale'));
					},

					bindUI: function() {
						var instance = this;

						LiferayFormBuilder.superclass.bindUI.apply(instance, arguments);

						instance.translationManager.after('editingLocaleChange', instance._afterEditingLocaleChange, instance);

						instance.on('model:change', instance._onPropertyModelChange);
					},

					createField: function() {
						var instance = this;

						var field = LiferayFormBuilder.superclass.createField.apply(instance, arguments);

						field.set('strings', instance.get('strings'));

						return field;
					},

					deserializeDefinitionFields: function(content) {
						var instance = this;

						var availableLanguageIds = content.availableLanguageIds;

						var fields = content.fields;

						A.each(
							fields,
							function(fieldJSON) {
								instance._deserializeField(fieldJSON, availableLanguageIds);
							}
						);

						return fields;
					},

					getContent: function() {
						var instance = this;

						var definition = {};

						var translationManager = instance.translationManager;

						definition.availableLanguageIds = translationManager.get('availableLocales');
						definition.defaultLanguageId = translationManager.get('defaultLocale');

						definition.fields = instance._getSerializedFields();

						return A.JSON.stringify(definition, null, 4);
					},

					getContentValue: function() {
						var instance = this;

						return window[instance.get('portletNamespace') + 'getContentValue']();
					},

					_afterEditingLocaleChange: function(event) {
						var instance = this;

						instance._toggleInputDirection(event.newVal);
					},

					_afterFieldsChange: function(event) {
						var instance = this;

						var tabs = instance.tabView.getTabs();

						var activeTabIndex = tabs.indexOf(instance.tabView.getActiveTab());

						if (activeTabIndex === SETTINGS_TAB_INDEX) {
							instance.editField(event.newVal.item(0));
						}
					},

					_deserializeField: function(fieldJSON, availableLanguageIds) {
						var instance = this;

						var fields = fieldJSON.fields;

						if (isArray(fields)) {
							AArray.each(
								fields,
								function(item, index) {
									instance._deserializeField(item, availableLanguageIds);
								}
							);
						}

						instance._deserializeFieldLocalizationMap(fieldJSON, availableLanguageIds);
						instance._deserializeFieldLocalizableAttributes(fieldJSON);
					},

					_deserializeFieldLocalizableAttributes: function(fieldJSON) {
						var instance = this;

						var defaultLocale = instance.translationManager.get('defaultLocale');
						var editingLocale = instance.translationManager.get('editingLocale');

						A.each(
							LiferayFormBuilder.LOCALIZABLE_FIELD_ATTRS,
							function(item, index) {
								var localizedValue = fieldJSON[item];

								if (localizedValue) {
									fieldJSON[item] = localizedValue[editingLocale] || localizedValue[defaultLocale];
								}
							}
						);
					},

					_deserializeFieldLocalizationMap: function(fieldJSON, availableLanguageIds) {
						var instance = this;

						A.each(
							availableLanguageIds,
							function(languageId) {
								fieldJSON.localizationMap = fieldJSON.localizationMap || {};
								fieldJSON.localizationMap[languageId] = {};

								A.each(
									LiferayFormBuilder.LOCALIZABLE_FIELD_ATTRS,
									function(attribute) {
										var attributeMap = fieldJSON[attribute];

										if (attributeMap && attributeMap[languageId]) {
											fieldJSON.localizationMap[languageId][attribute] = attributeMap[languageId];
										}
									}
								);
							}
						);

						if (fieldJSON.options) {
							instance._deserializeFieldOptionsLocalizationMap(fieldJSON, availableLanguageIds);
						}
					},

					_deserializeFieldOptionsLocalizationMap: function(fieldJSON, availableLanguageIds) {
						var instance = this;

						var labels;

						var defaultLocale = instance.translationManager.get('defaultLocale');
						var editingLocale = instance.translationManager.get('editingLocale');

						A.each(
							fieldJSON.options,
							function(item, index) {
								labels = item.label;

								item.label = labels[editingLocale] || labels[defaultLocale];

								item.localizationMap = {};

								A.each(
									availableLanguageIds,
									function(languageId) {
										item.localizationMap[languageId] = {
											label: labels[languageId]
										};
									}
								);
							}
						);
					},

					_getSerializedFields: function() {
						var instance = this;

						var fields = [];

						instance.get('fields').each(
							function(field) {
								fields.push(
									field.serialize()
								);
							}
						);

						return fields;
					},

					_onPropertyModelChange: function(event) {
						var instance = this;

						var changed = event.changed;

						var attributeName = event.target.get('attributeName');

						var editingField = instance.editingField;

						var readOnlyAttributes = editingField.get('readOnlyAttributes');

						if (changed.hasOwnProperty('value') && (AArray.indexOf(readOnlyAttributes, 'name') === -1)) {
							if (attributeName === 'name') {
								editingField.set('autoGeneratedName', event.autoGeneratedName === true);
							}
							else if ((attributeName === 'label') && editingField.get('autoGeneratedName')) {
								var translationManager = instance.translationManager;

								if (translationManager.get('editingLocale') === translationManager.get('defaultLocale')) {
									var normalizedLabel = LiferayFormBuilder.Util.normalizeKey(changed.value.newVal);

									var generatedName = normalizedLabel;

									var counter = 1;

									while (LiferayFormBuilder.UNIQUE_FIELD_NAMES_MAP.has(generatedName)) {
										generatedName = normalizedLabel + counter++;
									}

									var modelList = instance.propertyList.get('data');

									var nameModel = modelList.filter(
										function(item, index) {
											return (item.get('attributeName') === 'name');
										}
									);

									if (nameModel.length) {
										nameModel[0].set(
											'value',
											generatedName,
											{
												autoGeneratedName: true
											}
										);
									}
								}
							}
						}
					},

					_renderSettings: function() {
						var instance = this;

						instance._renderPropertyList();
					},

					_setAvailableFields: function(val) {
						var instance = this;

						var fields = AArray.map(
							val,
							function(item, index) {
								return A.instanceOf(item, A.PropertyBuilderAvailableField) ? item : new A.LiferayAvailableField(item);
							}
						);

						fields.sort(
							function(a, b) {
								return A.ArraySort.compare(a.get('label'), b.get('label'));
							}
						);

						return fields;
					},

					_setFields: function() {
						var instance = this;

						LiferayFormBuilder.UNIQUE_FIELD_NAMES_MAP.clear();

						return LiferayFormBuilder.superclass._setFields.apply(instance, arguments);
					},

					_toggleInputDirection: function(locale) {
						var rtl = (Liferay.Language.direction[locale] === 'rtl');

						BODY.toggleClass('form-builder-ltr-inputs', !rtl);
						BODY.toggleClass('form-builder-rtl-inputs', rtl);
					}
				}
			}
		);

		LiferayFormBuilder.Util = {
			getFileEntry: function(fileJSON, callback) {
				var instance = this;

				fileJSON = instance.parseJSON(fileJSON);

				Liferay.Service(
					'/dlapp/get-file-entry-by-uuid-and-group-id',
					{
						groupId: fileJSON.groupId,
						uuid: fileJSON.uuid
					},
					callback
				);
			},

			getFileEntryURL: function(fileEntry) {
				var instance = this;

				var buffer = [
					themeDisplay.getPathContext(),
					'documents',
					fileEntry.groupId,
					fileEntry.folderId,
					encodeURIComponent(fileEntry.title)
				];

				return buffer.join('/');
			},

			normalizeKey: function(str) {
				var instance = this;

				if (isString(str)) {
					A.each(
						str,
						function(item, index) {
							if (!A.Text.Unicode.test(item, 'L') && !A.Text.Unicode.test(item, 'N') && !A.Text.Unicode.test(item, 'Pd')) {
								str = str.replace(item, STR_SPACE);
							}
						}
					);

					str = str.replace(/\s/g, '_');
				}

				return str;
			},

			normalizeValue: function(value) {
				var instance = this;

				if (Lang.isUndefined(value)) {
					value = STR_BLANK;
				}

				return value;
			},

			parseJSON: function(value) {
				var instance = this;

				var data = {};

				try {
					data = JSON.parse(value);
				}
				catch (e) {
				}

				return data;
			}
		};

		LiferayFormBuilder.DEFAULT_ICON_CLASS = 'icon-fb-custom-field';

		var AVAILABLE_FIELDS = {
			DDM_STRUCTURE: [
				{
					hiddenAttributes: MAP_HIDDEN_FIELD_ATTRS.checkbox,
					iconClass: 'icon-fb-boolean',
					label: Liferay.Language.get('boolean'),
					type: 'checkbox'
				},
				{
					hiddenAttributes: MAP_HIDDEN_FIELD_ATTRS.DEFAULT,
					iconClass: 'icon-calendar',
					label: Liferay.Language.get('date'),
					type: 'ddm-date'
				},
				{
					hiddenAttributes: MAP_HIDDEN_FIELD_ATTRS.DEFAULT,
					iconClass: 'icon-fb-decimal',
					label: Liferay.Language.get('decimal'),
					type: 'ddm-decimal'
				},
				{
					hiddenAttributes: MAP_HIDDEN_FIELD_ATTRS.DEFAULT,
					iconClass: 'icon-file-text',
					label: Liferay.Language.get('documents-and-media'),
					type: 'ddm-documentlibrary'
				},
				{
					hiddenAttributes: MAP_HIDDEN_FIELD_ATTRS.DEFAULT,
					iconClass: 'icon-edit-sign',
					label: Liferay.Language.get('html'),
					type: 'ddm-text-html'
				},
				{
					hiddenAttributes: MAP_HIDDEN_FIELD_ATTRS.DEFAULT,
					iconClass: 'icon-globe',
					label: Liferay.Language.get('geolocation'),
					type: 'ddm-geolocation'
				},
				{
					hiddenAttributes: MAP_HIDDEN_FIELD_ATTRS.DEFAULT,
					iconClass: 'icon-fb-integer',
					label: Liferay.Language.get('integer'),
					type: 'ddm-integer'
				},
				{
					hiddenAttributes: MAP_HIDDEN_FIELD_ATTRS.DEFAULT,
					iconClass: 'icon-link',
					label: Liferay.Language.get('link-to-page'),
					type: 'ddm-link-to-page'
				},
				{
					hiddenAttributes: MAP_HIDDEN_FIELD_ATTRS.DEFAULT,
					iconClass: 'icon-fb-number',
					label: Liferay.Language.get('number'),
					type: 'ddm-number'
				},
				{
					hiddenAttributes: MAP_HIDDEN_FIELD_ATTRS.DEFAULT,
					iconClass: 'icon-fb-radio',
					label: Liferay.Language.get('radio'),
					type: 'radio'
				},
				{
					hiddenAttributes: MAP_HIDDEN_FIELD_ATTRS.DEFAULT,
					iconClass: 'icon-fb-select',
					label: Liferay.Language.get('select'),
					type: 'select'
				},
				{
					hiddenAttributes: MAP_HIDDEN_FIELD_ATTRS.DEFAULT,
					iconClass: 'icon-fb-text',
					label: Liferay.Language.get('text'),
					type: 'text'
				},
				{
					hiddenAttributes: MAP_HIDDEN_FIELD_ATTRS.DEFAULT,
					iconClass: 'icon-fb-text-box',
					label: Liferay.Language.get('text-box'),
					type: 'textarea'
				}
			],

			DDM_TEMPLATE: [
				{
					hiddenAttributes: MAP_HIDDEN_FIELD_ATTRS.DEFAULT,
					iconClass: 'icon-fb-paragraph',
					label: Liferay.Language.get('paragraph'),
					type: 'ddm-paragraph'
				},
				{
					hiddenAttributes: MAP_HIDDEN_FIELD_ATTRS.DEFAULT,
					iconClass: 'icon-fb-separator',
					label: Liferay.Language.get('separator'),
					type: 'ddm-separator'
				},
				{
					hiddenAttributes: MAP_HIDDEN_FIELD_ATTRS.DEFAULT,
					iconClass: 'icon-fb-fieldset',
					label: Liferay.Language.get('fieldset'),
					type: 'fieldset'
				}
			],

			DEFAULT: [
				{
					fieldLabel: Liferay.Language.get('button'),
					iconClass: 'form-builder-field-icon form-builder-field-icon-button',
					label: Liferay.Language.get('button'),
					type: 'button'
				},
				{
					fieldLabel: Liferay.Language.get('checkbox'),
					iconClass: 'icon-fb-boolean',
					label: Liferay.Language.get('checkbox'),
					type: 'checkbox'
				},
				{
					fieldLabel: Liferay.Language.get('fieldset'),
					iconClass: 'form-builder-field-icon form-builder-field-icon-fieldset',
					label: Liferay.Language.get('fieldset'),
					type: 'fieldset'
				},
				{
					fieldLabel: Liferay.Language.get('text-box'),
					iconClass: 'icon-fb-text',
					label: Liferay.Language.get('text-box'),
					type: 'text'
				},
				{
					fieldLabel: Liferay.Language.get('text-area'),
					iconClass: 'icon-fb-text-box',
					label: Liferay.Language.get('text-area'),
					type: 'textarea'
				},
				{
					fieldLabel: Liferay.Language.get('radio-buttons'),
					iconClass: 'icon-fb-radio',
					label: Liferay.Language.get('radio-buttons'),
					type: 'radio'
				},
				{
					fieldLabel: Liferay.Language.get('select-option'),
					iconClass: 'icon-fb-select',
					label: Liferay.Language.get('select-option'),
					type: 'select'
				}
			],

			WCM_STRUCTURE: [
				{
					hiddenAttributes: MAP_HIDDEN_FIELD_ATTRS.DEFAULT,
					iconClass: 'icon-picture',
					label: Liferay.Language.get('image'),
					type: 'ddm-image'
				},
				{
					hiddenAttributes: MAP_HIDDEN_FIELD_ATTRS.separator,
					iconClass: 'icon-fb-separator',
					label: Liferay.Language.get('separator'),
					type: 'ddm-separator'
				}
			]
		};

		AVAILABLE_FIELDS.WCM_STRUCTURE = AVAILABLE_FIELDS.WCM_STRUCTURE.concat(AVAILABLE_FIELDS.DDM_STRUCTURE);

		LiferayFormBuilder.AVAILABLE_FIELDS = AVAILABLE_FIELDS;

		Liferay.FormBuilder = LiferayFormBuilder;
	},
	'',
	{
		requires: ['arraysort', 'aui-form-builder', 'aui-form-validator', 'aui-map', 'aui-text-unicode', 'json', 'liferay-menu', 'liferay-translation-manager', 'liferay-util-window', 'text']
	}
);