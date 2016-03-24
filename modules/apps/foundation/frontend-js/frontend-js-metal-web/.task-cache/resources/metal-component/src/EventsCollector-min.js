define("frontend-js-metal-web@1.0.4/metal-component/src/EventsCollector-min", ["exports","metal/src/metal","metal-events/src/events"], function(e,t,n){"use strict";function o(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function r(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function s(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(e,"__esModule",{value:!0});var i=function(e){function t(n){o(this,t);var s=r(this,e.call(this));if(!n)throw new Error("The component instance is mandatory");return (s.component_=n, s.eventHandles_={}, s.groupHasListener_={}, s)}return (s(t,e), t.prototype.attachListener=function(e,t){var o=arguments.length<=2||void 0===arguments[2]?"element":arguments[2],r="[data-on"+e+'="'+t+'"]';if(this.groupHasListener_[o][r]=!0,!this.eventHandles_[r]){this.eventHandles_[r]=new n.EventHandler;for(var s=t.split(","),i=0;i<s.length;i++){var a=this.component_.getListenerFn(s[i]);a&&this.eventHandles_[r].add(this.component_.delegate(e,r,this.onEvent_.bind(this,a)))}}}, t.prototype.attachListenersFromHtml=function(e){var t=arguments.length<=1||void 0===arguments[1]?"element":arguments[1];if(this.startCollecting(t),-1!==e.indexOf("data-on"))for(var n=/data-on([a-z]+)=['"]([^'"]+)['"]/g,o=n.exec(e);o;)this.attachListener(o[1],o[2],t),o=n.exec(e)}, t.prototype.detachAllListeners=function(){for(var e in this.eventHandles_)this.eventHandles_[e]&&this.eventHandles_[e].removeAllListeners();this.eventHandles_={},this.listenerCounts_={}}, t.prototype.detachUnusedListeners=function(){for(var e in this.eventHandles_)if(this.eventHandles_[e]){var t=!0;for(var n in this.groupHasListener_)if(this.groupHasListener_[n][e]){t=!1;break}t&&(this.eventHandles_[e].removeAllListeners(),this.eventHandles_[e]=null)}}, t.prototype.disposeInternal=function(){this.detachAllListeners(),this.component_=null}, t.prototype.hasAttachedForGroup=function(e){return!!this.groupHasListener_.hasOwnProperty(e)}, t.prototype.onEvent_=function(e,t){var n=t.handledByComponent;return!n||n===this.component_||t.delegateTarget.contains(n.element)?(t.handledByComponent=this.component_,e(t)):void 0}, t.prototype.startCollecting=function(){var e=arguments.length<=0||void 0===arguments[0]?"element":arguments[0];this.groupHasListener_[e]={}}, t)}(t.Disposable);i.prototype.registerMetalComponent&&i.prototype.registerMetalComponent(i,"EventsCollector"),e["default"]=i});