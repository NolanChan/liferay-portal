define("frontend-js-metal-web@1.0.6/metal-select/src/Select.soy-min", ["exports","metal-component/src/Component","metal-soy/src/Soy"], function(e,t,n){"use strict";function l(e){return e&&e.__esModule?e:{"default":e}}function a(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function o(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function r(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(e,"__esModule",{value:!0}),e.templates=e.Select=void 0;var s,u=l(t),i=l(n);goog.loadModule(function(e){function t(e,t,o){var s;l.asserts.assertType(null==e.label||e.label instanceof Function||e.label instanceof a.UnsanitizedText||goog.isString(e.label),"label",e.label,"?soydata.SanitizedHtml|string|undefined");var i=e.label;r("div",null,null,"class","select"+(e.elementClasses?" "+e.elementClasses:""),"data-onkeydown","handleKeyDown_");var m=null!=e.selectedIndex?e.selectedIndex:i||0==e.items.length?-1:0;r("input",null,null,"type","hidden","name",e.hiddenInputName?e.hiddenInputName:"","value",-1==m?"":e.values?e.values[m]:""),u("input");var f=function(){for(var t=e.items,l=t.length,a=0;l>a;a++){var i=t[a];r("li",null,null,"data-onclick",null==(s=e.handleItemClick_)?"":s,"class","select-option"+(m==a?" selected":"")),r("a",null,null,"href","javascript:;"),n({value:i},null,o),u("a"),u("li")}},g=function(){r("button",null,null,"class",(e.buttonClass?e.buttonClass:"")+" dropdown-select","type","button","data-onclick","toggle"),-1==m?i&&i():n({value:e.items[m]},null,o),c(" "),d("span",null,null,"class",e.arrowClass?e.arrowClass:"caret"),u("button")};p({body:f,events:{stateSynced:e.handleDropdownStateSynced_},header:g,key:"dropdown"},null,o),u("div")}function n(e,t,n){l.asserts.assertType(e.value instanceof Function||e.value instanceof a.UnsanitizedText||goog.isString(e.value),"value",e.value,"Function");var o=e.value;o()}goog.module("Select.incrementaldom");var l=goog.require("soy"),a=goog.require("soydata");goog.require("goog.asserts"),goog.require("soy.asserts"),goog.require("goog.i18n.bidi");var o=goog.require("incrementaldom"),r=o.elementOpen,u=o.elementClose,d=o.elementVoid,c=(o.elementOpenStart,o.elementOpenEnd,o.text),p=(o.attr,i["default"].getTemplate("Dropdown.incrementaldom","render"));return (e.render=t, goog.DEBUG&&(t.soyTemplateName="Select.render"), e.renderAsHtml_=n, goog.DEBUG&&(n.soyTemplateName="Select.renderAsHtml_"), e.render.params=["label","arrowClass","buttonClass","elementClasses","handleDropdownStateSynced_","handleItemClick_","hiddenInputName","items","values","selectedIndex"], e.renderAsHtml_.params=["value"], e.templates=s=e, e)});var d=function(e){function t(){return (a(this,t), o(this,e.apply(this,arguments)))}return (r(t,e), t)}(u["default"]);i["default"].register(d,s),e["default"]=s,e.Select=d,e.templates=s});