define("frontend-js-metal-web@1.0.6/metal-soy/src/Soy-min", ["exports","metal/src/metal","metal-component/src/all/component","html2incdom/src/withParser","metal-incremental-dom/src/IncrementalDomRenderer","./SoyAop","metal-soy-bundle/build/bundle"], function(t,e,n,o,r,i){"use strict";function a(t){return t&&t.__esModule?t:{"default":t}}function c(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}function l(t,e){if(!t)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!e||"object"!=typeof e&&"function"!=typeof e?t:e}function u(t,e){if("function"!=typeof e&&null!==e)throw new TypeError("Super expression must either be null or a function, not "+typeof e);t.prototype=Object.create(e&&e.prototype,{constructor:{value:t,enumerable:!1,writable:!0,configurable:!0}}),e&&(Object.setPrototypeOf?Object.setPrototypeOf(t,e):t.__proto__=e)}Object.defineProperty(t,"__esModule",{value:!0}),t.SoyAop=t.Soy=void 0;var s=a(o),p=a(r),f=a(i),d={},m=function(t){function o(e){c(this,o);var n=l(this,t.call(this,e));return (n.addMissingStateKeys_(), n)}return (u(o,t), o.prototype.addMissingStateKeys_=function(){var t=this.component_.constructor.TEMPLATE;if(e.core.isFunction(t)){t=f["default"].getOriginalFn(t),this.soyParamTypes_=t.types||{};for(var n=t.params||[],o=this.component_,r=0;r<n.length;r++)o.getStateKeyConfig(n[r])||o[n[r]]||o.addToState(n[r],{},o.getInitialConfig()[n[r]])}}, o.prototype.buildTemplateData_=function(t,n){var r=this,i=this.component_;t=e.object.mixin({},t),i.getStateKeys().forEach(function(e){if("element"!==e){var n=i[e];(i.getStateKeyConfig(e).isHtml||"html"===r.soyParamTypes_[e])&&(n=o.toIncDom(n)),t[e]=n}});for(var a=0;a<n.length;a++)!t[n[a]]&&e.core.isFunction(i[n[a]])&&(t[n[a]]=i[n[a]].bind(i));return t}, o.getTemplate=function(t,e){return function(n,o,r){if(!goog.loadedModules_[t])throw new Error('No template with namespace "'+t+'" has been loaded yet.');return goog.loadedModules_[t][e](n,o,r)}}, o.handleInterceptedCall_=function(t,e){var n=t.componentCtor,o=e;IncrementalDOM.elementVoid("Component",null,[],"ctor",n,"data",o)}, o.register=function(t,e){var r=arguments.length<=2||void 0===arguments[2]?"render":arguments[2];t.RENDERER=o,t.TEMPLATE=f["default"].getOriginalFn(e[r]),t.TEMPLATE.componentCtor=t,f["default"].registerForInterception(e,r),n.ComponentRegistry.register(t)}, o.prototype.renderIncDom=function(n){var r=this.component_.constructor.TEMPLATE;e.core.isFunction(r)?(r=f["default"].getOriginalFn(r),f["default"].startInterception(o.handleInterceptedCall_),r(this.buildTemplateData_(n,r.params||[]),null,d),f["default"].stopInterception()):t.prototype.renderIncDom.call(this)}, o.setInjectedData=function(t){d=t||{}}, o.prototype.shouldUpdate=function(t){for(var e=this.component_.constructor.TEMPLATE,n=e?f["default"].getOriginalFn(e).params:[],o=0;o<n.length;o++)if(t[n[o]])return!0;return!1}, o.toHtmlString=function(t){var e=document.createElement("div");return (IncrementalDOM.patch(e,t), e.innerHTML)}, o.toIncDom=function(t){return (e.core.isObject(t)&&e.core.isString(t.content)&&"HTML"===t.contentKind&&(t=t.content), e.core.isString(t)&&(t=s["default"].buildFn(t)), t)}, o)}(p["default"]);t["default"]=m,t.Soy=m,t.SoyAop=f["default"]});