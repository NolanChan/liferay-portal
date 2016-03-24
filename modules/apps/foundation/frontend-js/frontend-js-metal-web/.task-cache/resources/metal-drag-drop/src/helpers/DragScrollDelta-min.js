define("frontend-js-metal-web@1.0.4/metal-drag-drop/src/helpers/DragScrollDelta-min", ["exports","metal-dom/src/all/dom","metal-events/src/events","metal-position/src/all/position"], function(t,e,o,l){"use strict";function r(t){return t&&t.__esModule?t:{"default":t}}function n(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}function s(t,e){if(!t)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!e||"object"!=typeof e&&"function"!=typeof e?t:e}function i(t,e){if("function"!=typeof e&&null!==e)throw new TypeError("Super expression must either be null or a function, not "+typeof e);t.prototype=Object.create(e&&e.prototype,{constructor:{value:t,enumerable:!1,writable:!0,configurable:!0}}),e&&(Object.setPrototypeOf?Object.setPrototypeOf(t,e):t.__proto__=e)}Object.defineProperty(t,"__esModule",{value:!0});var c=r(e),a=r(l),p=function(t){function e(){n(this,e);var l=s(this,t.call(this));return (l.handler_=new o.EventHandler, l.scrollPositions_=[], l)}return (i(e,t), e.prototype.disposeInternal=function(){t.prototype.disposeInternal.call(this),this.stop(),this.handler_=null}, e.prototype.handleScroll_=function(t,e){var o={scrollLeft:a["default"].getScrollLeft(e.currentTarget),scrollTop:a["default"].getScrollTop(e.currentTarget)},l=this.scrollPositions_[t];this.scrollPositions_[t]=o,this.emit("scrollDelta",{deltaX:o.scrollLeft-l.scrollLeft,deltaY:o.scrollTop-l.scrollTop})}, e.prototype.start=function(t,e){if("fixed"!==getComputedStyle(t).position)for(var o=0;o<e.length;o++)if(c["default"].contains(e[o],t)){this.scrollPositions_.push({scrollLeft:a["default"].getScrollLeft(e[o]),scrollTop:a["default"].getScrollTop(e[o])});var l=this.scrollPositions_.length-1;this.handler_.add(c["default"].on(e[o],"scroll",this.handleScroll_.bind(this,l)))}}, e.prototype.stop=function(){this.handler_.removeAllListeners(),this.scrollPositions_=[]}, e)}(o.EventEmitter);p.prototype.registerMetalComponent&&p.prototype.registerMetalComponent(p,"DragScrollDelta"),t["default"]=p});