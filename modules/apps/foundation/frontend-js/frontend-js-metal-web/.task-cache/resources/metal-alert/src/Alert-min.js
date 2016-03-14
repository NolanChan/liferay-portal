define("frontend-js-metal-web@1.0.0/metal-alert/src/Alert-min", ["exports","metal/src/metal","metal-dom/src/all/dom","./Alert.soy","metal-anim/src/Anim","metal-events/src/events","metal-jquery-adapter/src/JQueryAdapter"], function(e,t,s,i,n,l,o){"use strict";function r(e){return e&&e.__esModule?e:{"default":e}}function a(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function d(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function c(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(e,"__esModule",{value:!0});var u=r(s),h=r(i),p=r(n),f=r(o),m=function(e){function s(t){a(this,s);var i=d(this,e.call(this,t));return (i.eventHandler_=new l.EventHandler, i)}return (c(s,e), s.prototype.detached=function(){e.prototype.detached.call(this),this.eventHandler_.removeAllListeners(),clearTimeout(this.delay_)}, s.prototype.close=function(){u["default"].once(this.element,"animationend",this.dispose.bind(this)),u["default"].once(this.element,"transitionend",this.dispose.bind(this)),this.eventHandler_.removeAllListeners(),this.syncVisible(!1)}, s.prototype.handleDocClick_=function(e){this.element.contains(e.target)||this.hide()}, s.prototype.hide=function(){this.visible=!1}, s.prototype.toggle=function(){this.visible=!this.visible}, s.prototype.syncDismissible=function(e){e?this.eventHandler_.add(u["default"].on(document,"click",this.handleDocClick_.bind(this))):this.eventHandler_.removeAllListeners(),u["default"][e?"addClasses":"removeClasses"](this.element,"alert-dismissible")}, s.prototype.syncVisible=function(e){u["default"].removeClasses(this.element,this.animClasses[e?"hide":"show"]),u["default"].addClasses(this.element,this.animClasses[e?"show":"hide"]),p["default"].emulateEnd(this.element),e&&t.core.isNumber(this.hideDelay)&&this.syncHideDelay(this.hideDelay)}, s.prototype.syncHideDelay=function(e){t.core.isNumber(e)&&this.visible&&(clearTimeout(this.delay_),this.delay_=setTimeout(this.hide.bind(this),e))}, s.prototype.syncSpinnerDone=function(e){if(this.spinner){var t=this.element.querySelector(".alert-spinner");u["default"].removeClasses(t,"alert-spinner-done"),e&&u["default"].addClasses(t,"alert-spinner-done")}}, s)}(h["default"]);m.prototype.registerMetalComponent&&m.prototype.registerMetalComponent(m,"Alert"),m.ELEMENT_CLASSES="alert",m.ATTRS={animClasses:{validator:t.core.isObject,value:{show:"fade in",hide:"fade"}},body:{value:""},dismissible:{validator:t.core.isBoolean,value:!0},elementClasses:{value:"alert-success"},hideDelay:{},spinner:{value:!1},spinnerClasses:{},spinnerDone:{value:!1},visible:{value:!1}},e["default"]=m,f["default"].register("alert",m)});