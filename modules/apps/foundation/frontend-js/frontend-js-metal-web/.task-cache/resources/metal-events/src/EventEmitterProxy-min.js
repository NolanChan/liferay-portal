define("frontend-js-metal-web@1.0.7/metal-events/src/EventEmitterProxy-min", ["exports","metal/src/metal"], function(t,e){"use strict";function i(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}function n(t,e){if(!t)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!e||"object"!=typeof e&&"function"!=typeof e?t:e}function r(t,e){if("function"!=typeof e&&null!==e)throw new TypeError("Super expression must either be null or a function, not "+typeof e);t.prototype=Object.create(e&&e.prototype,{constructor:{value:t,enumerable:!1,writable:!0,configurable:!0}}),e&&(Object.setPrototypeOf?Object.setPrototypeOf(t,e):t.__proto__=e)}Object.defineProperty(t,"__esModule",{value:!0});var o=function(t){function o(e,r,s,p){i(this,o);var h=n(this,t.call(this));return (h.blacklist_=s||{}, h.originEmitter_=e, h.pendingEvents_=[], h.proxiedEvents_={}, h.targetEmitter_=r, h.whitelist_=p, h.startProxy_(), h)}return (r(o,t), o.prototype.addListener_=function(t,e){return this.originEmitter_.on(t,e)}, o.prototype.addListenerForEvent_=function(t){return this.addListener_(t,this.emitOnTarget_.bind(this,t))}, o.prototype.disposeInternal=function(){this.removeListeners_(),this.proxiedEvents_=null,this.originEmitter_=null,this.targetEmitter_=null}, o.prototype.emitOnTarget_=function(t){var i=[t].concat(e.array.slice(arguments,1));this.targetEmitter_.emit.apply(this.targetEmitter_,i)}, o.prototype.proxyEvent=function(t){this.shouldProxyEvent_(t)&&this.tryToAddListener_(t)}, o.prototype.removeListeners_=function(){for(var t=Object.keys(this.proxiedEvents_),e=0;e<t.length;e++)this.proxiedEvents_[t[e]].removeListener();this.proxiedEvents_={},this.pendingEvents_=[]}, o.prototype.setOriginEmitter=function(t){var e=this,i=this.originEmitter_?Object.keys(this.proxiedEvents_):this.pendingEvents_;this.removeListeners_(),this.originEmitter_=t,i.forEach(function(t){return e.proxyEvent(t)})}, o.prototype.shouldProxyEvent_=function(t){return this.whitelist_&&!this.whitelist_[t]?!1:this.blacklist_[t]?!1:!this.proxiedEvents_[t]}, o.prototype.startProxy_=function(){this.targetEmitter_.on("newListener",this.proxyEvent.bind(this))}, o.prototype.tryToAddListener_=function(t){this.originEmitter_?this.proxiedEvents_[t]=this.addListenerForEvent_(t):this.pendingEvents_.push(t)}, o)}(e.Disposable);t["default"]=o});