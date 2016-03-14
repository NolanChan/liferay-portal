define("frontend-js-metal-web@1.0.0/metal-select/src/Select.soy-min", ["exports","metal-component/src/all/component","metal-soy/src/soy"], function(e,t,n){"use strict";function o(e){return e&&e.__esModule?e:{"default":e}}function a(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function s(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function r(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(e,"__esModule",{value:!0});var l=o(t),i=n.SoyTemplates.get();"undefined"==typeof i.Select&&(i.Select={}),i.Select.render=function(e,t,n){var o='<div id="'+soy.$$escapeHtmlAttribute(e.id)+'" class="select component'+soy.$$escapeHtmlAttribute(e.elementClasses?" "+e.elementClasses:"")+'" data-onkeydown="handleKeyDown_">',a=null!=e.selectedIndex?e.selectedIndex:e.label||0==e.items.length?-1:0;o+='<input type="hidden" name="'+soy.$$escapeHtmlAttribute(e.hiddenInputName?e.hiddenInputName:"")+'" value="'+soy.$$escapeHtmlAttribute(-1==a?"":e.items[a])+'" />';for(var s="",r=e.items,l=r.length,c=0;l>c;c++){var d=r[c];s+='<li data-onclick="'+soy.$$escapeHtmlAttribute(e.id)+':handleItemClick_" class="select-option'+soy.$$escapeHtmlAttribute(a==c?" selected":"")+'"><a href="javascript:;">'+soy.$$escapeHtml(d)+"</a></li>"}return (o+=soy.$$escapeHtml(i.Dropdown.render({body:soydata.VERY_UNSAFE.$$ordainSanitizedHtmlForInternalBlocks(s),events:{attrsSynced:e.id+":handleDropdownAttrsSynced_"},header:soydata.VERY_UNSAFE.$$ordainSanitizedHtmlForInternalBlocks('<button class="'+soy.$$escapeHtmlAttribute(e.buttonClass)+' dropdown-select" type="button" data-onclick="toggle">'+soy.$$escapeHtml(-1==a?e.label:e.items[a])+' <span class="'+soy.$$escapeHtmlAttribute(e.arrowClass?e.arrowClass:"caret")+'"></span></button>'),id:e.id+"-dropdown"},null,n)), o+="</div>", soydata.VERY_UNSAFE.ordainSanitizedHtml(o))},goog.DEBUG&&(i.Select.render.soyTemplateName="Templates.Select.render"),i.Select.render.params=["arrowClass","buttonClass","hiddenInputName","id","items","label","selectedIndex"];var c=function(e){function t(){return (a(this,t), s(this,e.apply(this,arguments)))}return (r(t,e), t)}(l["default"]);c.prototype.registerMetalComponent&&c.prototype.registerMetalComponent(c,"Select"),c.RENDERER=n.SoyRenderer,n.SoyAop.registerTemplates("Select"),e["default"]=c});