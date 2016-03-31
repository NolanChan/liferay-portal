define("frontend-js-metal-web@1.0.6/metal-button-group/src/ButtonGroup.soy-min", ["exports","metal-component/src/all/component","metal-soy/src/soy"], function(e,t,o){"use strict";function n(e){return e&&e.__esModule?e:{"default":e}}function s(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function r(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function a(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(e,"__esModule",{value:!0});var l=n(t),u=o.SoyTemplates.get();"undefined"==typeof u.ButtonGroup&&(u.ButtonGroup={}),u.ButtonGroup.render=function(e,t,o){for(var n='<div id="'+soy.$$escapeHtmlAttribute(e.id)+'" class="btn-group component'+soy.$$escapeHtmlAttribute(e.elementClasses?" "+e.elementClasses:"")+'">',s=e.buttons,r=s.length,a=0;r>a;a++){var l=s[a],p=l.type?l.type:"button",c=l.cssClass?l.cssClass:"btn btn-default";n+='<button type="'+soy.$$escapeHtmlAttribute(p)+'" class="'+soy.$$escapeHtmlAttribute(c)+soy.$$escapeHtmlAttribute(u.ButtonGroup.selectedClass({label:l.label,selected:e.selected},null,o))+'" data-index="'+soy.$$escapeHtmlAttribute(a)+'" data-onclick="handleClick_"><span class="btn-group-label">'+soy.$$escapeHtml(l.label?l.label:"")+"</span>"+(l.icon?'<span class="'+soy.$$escapeHtmlAttribute(l.icon)+'"></span>':"")+"</button>"}return (n+="</div>", soydata.VERY_UNSAFE.ordainSanitizedHtml(n))},goog.DEBUG&&(u.ButtonGroup.render.soyTemplateName="Templates.ButtonGroup.render"),u.ButtonGroup.selectedClass=function(e,t,o){var n="";if(e.selected)for(var s=e.selected,r=s.length,a=0;r>a;a++){var l=s[a];n+=l==e.label?" btn-group-selected":""}return soydata.VERY_UNSAFE.ordainSanitizedHtml(n)},goog.DEBUG&&(u.ButtonGroup.selectedClass.soyTemplateName="Templates.ButtonGroup.selectedClass"),u.ButtonGroup.render.params=["buttons","id"],u.ButtonGroup.selectedClass["private"]=!0;var p=function(e){function t(){return (s(this,t), r(this,e.apply(this,arguments)))}return (a(t,e), t)}(l["default"]);p.prototype.registerMetalComponent&&p.prototype.registerMetalComponent(p,"ButtonGroup"),p.RENDERER=o.SoyRenderer,o.SoyAop.registerTemplates("ButtonGroup"),e["default"]=p});