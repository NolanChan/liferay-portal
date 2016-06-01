define("frontend-js-metal-web@1.0.7/metal-list/src/List.soy-min", ["exports","metal-component/src/Component","metal-soy/src/Soy"], function(e,t,n){"use strict";function o(e){return e&&e.__esModule?e:{"default":e}}function r(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function s(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function i(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(e,"__esModule",{value:!0}),e.templates=e.List=void 0;var l,a=o(t),u=o(n);goog.loadModule(function(e){function t(e,t,r){e=e||{},n.asserts.assertType(null==e.itemsHtml||e.itemsHtml instanceof Function||e.itemsHtml instanceof o.UnsanitizedText||goog.isString(e.itemsHtml),"itemsHtml",e.itemsHtml,"?soydata.SanitizedHtml|string|undefined");var l=e.itemsHtml;if(s("div",null,null,"class","list"+(e.elementClasses?" "+e.elementClasses:"")),s("ul",null,null,"class","list-group","data-onclick","handleClick"),null!=l)l();else for(var u=e.items,m=u.length,c=0;m>c;c++){var f=u[c];a({index:c,item:f,key:"-items-"+c},null,r)}i("ul"),i("div")}goog.module("List.incrementaldom");var n=goog.require("soy"),o=goog.require("soydata");goog.require("goog.asserts"),goog.require("soy.asserts"),goog.require("goog.i18n.bidi");var r=goog.require("incrementaldom"),s=r.elementOpen,i=r.elementClose,a=(r.elementVoid,r.elementOpenStart,r.elementOpenEnd,r.text,r.attr,u["default"].getTemplate("ListItem.incrementaldom","render"));return (e.render=t, goog.DEBUG&&(t.soyTemplateName="List.render"), e.render.params=["itemsHtml","elementClasses","items"], e.templates=l=e, e)});var m=function(e){function t(){return (r(this,t), s(this,e.apply(this,arguments)))}return (i(t,e), t)}(a["default"]);u["default"].register(m,l),e["default"]=l,e.List=m,e.templates=l});