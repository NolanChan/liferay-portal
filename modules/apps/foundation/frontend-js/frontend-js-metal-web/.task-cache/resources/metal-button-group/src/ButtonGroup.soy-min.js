define("frontend-js-metal-web@1.0.7/metal-button-group/src/ButtonGroup.soy-min", ["exports","metal-component/src/Component","metal-soy/src/Soy"], function(e,t,n){"use strict";function o(e){return e&&e.__esModule?e:{"default":e}}function l(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function r(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function s(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(e,"__esModule",{value:!0}),e.templates=e.ButtonGroup=void 0;var a,u=o(t),c=o(n);goog.loadModule(function(e){function t(e,t,o){l("div",null,null,"class","btn-group component"+(e.elementClasses?" "+e.elementClasses:""));for(var a=e.buttons,c=a.length,i=0;c>i;i++){var p=a[i],d=p.type?p.type:"button",f=p.cssClass?p.cssClass:"btn btn-default";l("button",null,null,"type",d,"class",f+n({label:p.label,selected:e.selected},null,o),"data-index",i,"data-onclick","handleClick_"),l("span",null,null,"class","btn-group-label"),u((goog.asserts.assert(null!=(p.label?p.label:"")),p.label?p.label:"")),r("span"),p.icon&&s("span",null,null,"class",p.icon),r("button")}r("div")}function n(e,t,n){var o="";if(e.selected)for(var l=e.selected,r=l.length,s=0;r>s;s++){var a=l[s];o+=a==e.label?" btn-group-selected":""}return o}goog.module("ButtonGroup.incrementaldom");goog.require("soy"),goog.require("soydata");goog.require("goog.i18n.bidi"),goog.require("goog.asserts");var o=goog.require("incrementaldom"),l=o.elementOpen,r=o.elementClose,s=o.elementVoid,u=(o.elementOpenStart,o.elementOpenEnd,o.text);o.attr;return (e.render=t, goog.DEBUG&&(t.soyTemplateName="ButtonGroup.render"), e.selectedClass=n, goog.DEBUG&&(n.soyTemplateName="ButtonGroup.selectedClass"), e.render.params=["buttons","elementClasses","selected"], e.selectedClass.params=["label","selected"], e.templates=a=e, e)});var i=function(e){function t(){return (l(this,t), r(this,e.apply(this,arguments)))}return (s(t,e), t)}(u["default"]);c["default"].register(i,a),e["default"]=a,e.ButtonGroup=i,e.templates=a});