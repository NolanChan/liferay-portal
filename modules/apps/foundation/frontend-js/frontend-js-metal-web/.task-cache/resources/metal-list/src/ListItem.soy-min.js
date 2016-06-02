define("frontend-js-metal-web@1.0.8/metal-list/src/ListItem.soy-min", ["exports","metal-component/src/Component","metal-soy/src/Soy"], function(e,t,n){"use strict";function l(e){return e&&e.__esModule?e:{"default":e}}function o(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function i(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function s(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(e,"__esModule",{value:!0}),e.templates=e.ListItem=void 0;var a,r=l(t),u=l(n);goog.loadModule(function(e){function t(e,t,l){if(s("li",null,null,"class","listitem list-group-item "+(e.elementClasses?" "+e.elementClasses:"")+" clearfix","data-index",e.index),e.item.avatar&&(s("span",null,null,"class","list-image pull-left "+e.item.avatar["class"]),n({content:e.item.avatar.content},null,l),r("span")),s("div",null,null,"class","list-main-content pull-left"),s("div",null,null,"class","list-text-primary"),n({content:e.item.textPrimary},null,l),r("div"),e.item.textSecondary&&(s("div",null,null,"class","list-text-secondary"),n({content:e.item.textSecondary},null,l),r("div")),r("div"),e.item.icons)for(var o=e.item.icons,i=o.length,a=0;i>a;a++){var m=o[a];u("span",null,null,"class","btn-icon "+m+" pull-right")}if(e.item.iconsHtml){s("div",null,null,"class","pull-right");for(var p=e.item.iconsHtml,d=p.length,f=0;d>f;f++){var g=p[f];n({content:g},null,l)}r("div")}e.item.label&&(s("span",null,null,"class","label list-label pull-right "+e.item.label["class"]),c((goog.asserts.assert(null!=e.item.label.content),e.item.label.content)),r("span")),r("li")}function n(e,t,n){e=e||{},l.asserts.assertType(null==e.content||e.content instanceof Function||e.content instanceof o.UnsanitizedText||goog.isString(e.content),"content",e.content,"?soydata.SanitizedHtml|string|undefined");var i=e.content;i&&i()}goog.module("ListItem.incrementaldom");var l=goog.require("soy"),o=goog.require("soydata");goog.require("goog.asserts"),goog.require("soy.asserts"),goog.require("goog.i18n.bidi");var i=goog.require("incrementaldom"),s=i.elementOpen,r=i.elementClose,u=i.elementVoid,c=(i.elementOpenStart,i.elementOpenEnd,i.text);i.attr;return (e.render=t, goog.DEBUG&&(t.soyTemplateName="ListItem.render"), e.htmlContent=n, goog.DEBUG&&(n.soyTemplateName="ListItem.htmlContent"), e.render.params=["index","item","elementClasses"], e.htmlContent.params=["content"], e.templates=a=e, e)});var c=function(e){function t(){return (o(this,t), i(this,e.apply(this,arguments)))}return (s(t,e), t)}(r["default"]);u["default"].register(c,a),e["default"]=a,e.ListItem=c,e.templates=a});