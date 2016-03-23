define("frontend-js-metal-web@1.0.3/metal-modal/src/Modal.soy-min", ["exports","metal-component/src/all/component","metal-soy/src/soy"], function(e,o,t){"use strict";function a(e){return e&&e.__esModule?e:{"default":e}}function r(e,o){if(!(e instanceof o))throw new TypeError("Cannot call a class as a function")}function l(e,o){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!o||"object"!=typeof o&&"function"!=typeof o?e:o}function d(e,o){if("function"!=typeof o&&null!==o)throw new TypeError("Super expression must either be null or a function, not "+typeof o);e.prototype=Object.create(o&&o.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),o&&(Object.setPrototypeOf?Object.setPrototypeOf(e,o):e.__proto__=o)}Object.defineProperty(e,"__esModule",{value:!0});var n=a(o),s=t.SoyTemplates.get();"undefined"==typeof s.Modal&&(s.Modal={}),s.Modal.render=function(e,o,t){return soydata.VERY_UNSAFE.ordainSanitizedHtml('<div id="'+soy.$$escapeHtmlAttribute(e.id)+'" class="modal component'+soy.$$escapeHtmlAttribute(e.elementClasses?" "+e.elementClasses:"")+'" role="'+soy.$$escapeHtmlAttribute(e.role?e.role:"dialog")+'" aria-labelledby="'+soy.$$escapeHtmlAttribute(e.id)+'-header"><div class="modal-dialog" tabindex="0"><div class="modal-content">'+s.Modal.header(e,null,t)+s.Modal.body(e,null,t)+s.Modal.footer(e,null,t)+"</div></div></div>")},goog.DEBUG&&(s.Modal.render.soyTemplateName="Templates.Modal.render"),s.Modal.body=function(e,o,t){return soydata.VERY_UNSAFE.ordainSanitizedHtml('<section id="'+soy.$$escapeHtmlAttribute(e.id)+'-body" class="modal-body">'+(e.body?soy.$$escapeHtml(e.body):"")+"</section>")},goog.DEBUG&&(s.Modal.body.soyTemplateName="Templates.Modal.body"),s.Modal.footer=function(e,o,t){return soydata.VERY_UNSAFE.ordainSanitizedHtml('<footer id="'+soy.$$escapeHtmlAttribute(e.id)+'-footer" class="modal-footer">'+(e.footer?soy.$$escapeHtml(e.footer):"")+"</footer>")},goog.DEBUG&&(s.Modal.footer.soyTemplateName="Templates.Modal.footer"),s.Modal.header=function(e,o,t){return soydata.VERY_UNSAFE.ordainSanitizedHtml('<header id="'+soy.$$escapeHtmlAttribute(e.id)+'-header" class="modal-header">'+(e.header?'<button type="button" class="close" data-onclick="hide" aria-label="Close"><span aria-hidden="true">×</span></button>'+soy.$$escapeHtml(e.header):"")+"</header>")},goog.DEBUG&&(s.Modal.header.soyTemplateName="Templates.Modal.header"),s.Modal.render.params=["id","role"],s.Modal.body.params=["id","body"],s.Modal.footer.params=["footer","id"],s.Modal.header.params=["header","id"];var i=function(e){function o(){return (r(this,o), l(this,e.apply(this,arguments)))}return (d(o,e), o)}(n["default"]);i.prototype.registerMetalComponent&&i.prototype.registerMetalComponent(i,"Modal"),i.RENDERER=t.SoyRenderer,t.SoyAop.registerTemplates("Modal"),e["default"]=i});