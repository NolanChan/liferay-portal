// This file was automatically generated from checkbox.soy.
// Please don't edit this file by hand.

if (typeof ddm == 'undefined') { var ddm = {}; }


ddm.checkbox = function(opt_data, opt_ignored) {
return '\t<div class="form-group field-wrapper form-inline" data-fieldname="' + soy.$$escapeHtml(opt_data.name) + '"><label for="' + soy.$$escapeHtml(opt_data.name) + '"><input class="field" dir="' + soy.$$escapeHtml(opt_data.dir) + '" id="' + soy.$$escapeHtml(opt_data.name) + '" name="' + soy.$$escapeHtml(opt_data.name) + '" type="checkbox" ' + soy.$$escapeHtml(opt_data.status) + ' value="true" />&nbsp;' + soy.$$escapeHtml(opt_data.label) + '</label>' + soy.$$filterNoAutoescape(opt_data.childElementsHTML) + '</div>';
};