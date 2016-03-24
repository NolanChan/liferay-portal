define("frontend-js-metal-web@1.0.4/metal-alert/src/Alert.soy-min", ["exports","metal-component/src/all/component","metal-soy/src/soy"], function(e,t,n){"use strict";function r(e){return e&&e.__esModule?e:{"default":e}}function s(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function o(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function a(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(e,"__esModule",{value:!0});var l=r(t),i=n.SoyTemplates.get();"undefined"==typeof i.Alert&&(i.Alert={}),i.Alert.render=function(e,t,n){return soydata.VERY_UNSAFE.ordainSanitizedHtml('<div id="'+soy.$$escapeHtmlAttribute(e.id)+'" class="alert alert-dismissible component'+soy.$$escapeHtmlAttribute(e.elementClasses?" "+e.elementClasses:"")+'" role="alert">'+(e.spinner?'<span class="alert-spinner'+soy.$$escapeHtmlAttribute(e.spinnerClasses?" "+e.spinnerClasses:"")+'"></span>':"")+'<span class="alert-body">'+(e.body?soy.$$escapeHtml(e.body):"")+"</span>"+(e.dismissible?'<button type="button" class="close" aria-label="Close" data-onclick="toggle"><span aria-hidden="true">×</span></button>':"")+"</div>")},goog.DEBUG&&(i.Alert.render.soyTemplateName="Templates.Alert.render"),i.Alert.render.params=["body","dismissible","id","spinner","spinnerClasses"];var p=function(e){function t(){return (s(this,t), o(this,e.apply(this,arguments)))}return (a(t,e), t)}(l["default"]);p.prototype.registerMetalComponent&&p.prototype.registerMetalComponent(p,"Alert"),p.RENDERER=n.SoyRenderer,n.SoyAop.registerTemplates("Alert"),e["default"]=p});