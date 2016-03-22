define("frontend-js-metal-web@1.0.2/metal-soy/src/SoyRenderer-min", ["exports","metal/src/metal","metal-dom/src/all/dom","metal-component/src/all/component","./SoyAop","./SoyTemplates"], function(e,t,n,r,a,o){"use strict";function i(e){return e&&e.__esModule?e:{"default":e}}function l(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function u(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function p(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(e,"__esModule",{value:!0});var s=i(n),c=i(a),m=i(o),f={},d=function(e){function n(){return (l(this,n), u(this,e.apply(this,arguments)))}return (p(n,e), n.prototype.addSurfacesFromTemplates_=function(){for(var e=this.component_.getName(),t=m["default"].get(e),r=Object.keys(t),a=0;a<r.length;a++){var o=r[a],i=c["default"].getOriginalFn(t[o]);if(n.isSurfaceTemplate_(o,i)){var l="render"===o?this.component_.id:o;this.addSurface(l,{renderAttrs:i.params,templateComponentName:e,templateName:o})}}}, n.buildComponentConfigData_=function(e,t){var n={id:e};for(var r in t)n[r]=t[r];return n}, n.prototype.buildTemplateData_=function(){var e=this.component_,r=e.getAttrNames().filter(function(e){return"element"!==e}),a=this.getSurface(e.id),o=a&&a.componentData?a.componentData:{},i=t.object.map(e.getAttrs(r),function(r,a){return e.getAttrConfig(r).isHtml&&t.core.isString(a)?n.sanitizeHtml(a):a});return t.object.mixin(o,i)}, n.createComponentFromTemplate=function(e,a,o){var i=a?s["default"].toElement(a):null,f=t.object.mixin({id:i?i.id:null},o,{element:i}),d="TemplateComponent"+t.core.getUid(),h=function(e){function t(){return (l(this,t), u(this,e.apply(this,arguments)))}return (p(t,e), t)}(r.Component);return (h.prototype.registerMetalComponent&&h.prototype.registerMetalComponent(h,"TemplateComponent"), h.RENDERER=n, r.ComponentRegistry.register(h,d), m["default"].set(d,{render:function(t,n,r){return c["default"].getOriginalFn(e)(f,n,r)}}), c["default"].registerTemplates(d), new h(f))}, n.decorateFromTemplate=function(e,t,r){return n.createComponentFromTemplate(e,t,r).decorate()}, n.prototype.generateSurfaceElementId=function(t,n){return n.templateName&&t===this.component_.id&&!this.firstSurfaceFound_[n.templateName]?(this.firstSurfaceFound_[n.templateName]=!0,this.prefixSurfaceId(n.templateName)):e.prototype.generateSurfaceElementId.call(this,t)}, n.prototype.getSurfaceContent=function(e,t){e.surfaceElementId===this.component_.id&&(e.renderAttrs||this.addSurfacesFromTemplates_(),this.firstSurfaceFound_={}),this.surfaceBeingRendered_=e.surfaceElementId,this.skipInnerCalls_=this.skipInnerCalls_||t;var n=e.templateData;e.templateData=null;var r=this.renderTemplateByName_(e.templateComponentName,e.templateName,n);return (this.surfaceBeingRendered_=null, this.skipInnerCalls_=!1, r)}, n.prototype.handleComponentCall_=function(e,t){var r={componentName:e},a=(t||{}).id;return (a||(a=this.generateSurfaceElementId(this.surfaceBeingRendered_,r)), r.componentData=n.buildComponentConfigData_(a,t), this.buildPlaceholder(a,r))}, n.prototype.handleInterceptedCall_=function(e,t,n,r,a,o){return this.skipInnerCalls_?"":"render"===t?this.handleComponentCall_.call(this,e,r):this.handleSurfaceCall_.call(this,e,t,n,r,a,o)}, n.prototype.handleSurfaceCall_=function(e,n,r,a,o,i){var l,u={"static":r["static"],templateComponentName:e,templateData:a,templateName:n};if(t.core.isDefAndNotNull(a.surfaceElementId))l=a.surfaceElementId;else if(t.core.isDefAndNotNull(a.surfaceId))l=this.getSurfaceElementId(a.surfaceId.toString());else{if(r["private"])return r.call(null,a,o,i);l=this.generateSurfaceElementId(this.surfaceBeingRendered_,u)}return this.buildPlaceholder(l,u)}, n.isSurfaceTemplate_=function(e,t){return"__deltemplate"!==e.substr(0,13)&&!t["private"]}, n.renderFromTemplate=function(e,t,r){return n.createComponentFromTemplate(e,t,r).render()}, n.prototype.renderTemplate_=function(e,t){c["default"].startInterception(this.handleInterceptedCall_.bind(this)),e=c["default"].getOriginalFn(e);var n=e(t||this.buildTemplateData_(),null,f).content;return (c["default"].stopInterception(), n)}, n.prototype.renderTemplateByName_=function(e,n,r){var a=m["default"].get(e,n);return t.core.isFunction(a)?this.renderTemplate_(a,r):void 0}, n.sanitizeHtml=function(e){return soydata.VERY_UNSAFE.ordainSanitizedHtml(e)}, n.setInjectedData=function(e){f=e||{}}, n)}(r.SurfaceRenderer);d.prototype.registerMetalComponent&&d.prototype.registerMetalComponent(d,"SoyRenderer");var h=soydata.SanitizedHtml.from;soydata.SanitizedHtml.from=function(e){return (e&&"HTML"===e.contentKind&&(e=d.sanitizeHtml(e.content)), h(e))},e["default"]=d});