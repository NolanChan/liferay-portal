// This file was automatically generated from key_value.soy.
// Please don't edit this file by hand.

if (typeof ddm == 'undefined') { var ddm = {}; }


ddm.key_value = function(opt_data, opt_ignored) {
return '\t<div class="form-group' + soy.$$escapeHtml(opt_data.visible ? '' : ' hide') + ' liferay-ddm-form-field-key-value liferay-ddm-form-field-text" data-fieldname="' + soy.$$escapeHtml(opt_data.name) + '">' + ((opt_data.showLabel) ? '<label class="control-label" for="' + soy.$$escapeHtml(opt_data.name) + '">' + soy.$$escapeHtml(opt_data.label) + ((opt_data.required) ? '<span class="icon-asterisk text-warning"></span>' : '') + '</label>' : '') + '<div class="input-group-container ' + ((opt_data.tip) ? 'input-group-default' : '') + '"><input class="field form-control" dir="' + soy.$$escapeHtml(opt_data.dir) + '" id="' + soy.$$escapeHtml(opt_data.name) + '" name="' + soy.$$escapeHtml(opt_data.name) + '" placeholder="' + soy.$$escapeHtml(opt_data.placeholder) + '" type="text" value="' + soy.$$escapeHtml(opt_data.value) + '" />' + ((opt_data.tip) ? '<span class="input-group-addon"><span class="input-group-addon-content"><a class="help-icon help-icon-default icon-monospaced icon-question" data-original-title="' + soy.$$escapeHtml(opt_data.tip) + '" data-toggle="popover" href="javascript:;" title="' + soy.$$escapeHtml(opt_data.tip) + '"></a></span></span>' : '') + '</div><div class="' + soy.$$escapeHtml(opt_data.editing ? 'active ' : '') + 'key-value-editor"><label class="control-label key-value-label">' + soy.$$escapeHtml(opt_data.strings.keyLabel) + ':</label><span class="key-value-output">' + soy.$$escapeHtml(opt_data.key) + '</span><input class="key-value-input" placeholder="' + soy.$$escapeHtml(opt_data.key) + '" /><a class="key-value-done" href="javascript:;">' + soy.$$escapeHtml(opt_data.strings.done) + '</a><a class="key-value-cancel" href="javascript:;">' + soy.$$escapeHtml(opt_data.strings.cancel) + '</a></div></div>';
};