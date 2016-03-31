define("frontend-js-metal-web@1.0.6/metal-drag-drop/src/helpers/DragAutoScroll-min", ["exports","metal/src/metal","metal-attribute/src/Attribute","metal-position/src/all/position"], function(t,e,o,i){"use strict";function r(t){return t&&t.__esModule?t:{"default":t}}function n(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}function l(t,e){if(!t)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!e||"object"!=typeof e&&"function"!=typeof e?t:e}function s(t,e){if("function"!=typeof e&&null!==e)throw new TypeError("Super expression must either be null or a function, not "+typeof e);t.prototype=Object.create(e&&e.prototype,{constructor:{value:t,enumerable:!1,writable:!0,configurable:!0}}),e&&(Object.setPrototypeOf?Object.setPrototypeOf(t,e):t.__proto__=e)}Object.defineProperty(t,"__esModule",{value:!0});var a=r(e),u=r(o),c=r(i),p=function(t){function e(o){n(this,e);var i=l(this,t.call(this,o));return (i.scrollTimeout_=null, i)}return (s(e,t), e.prototype.disposeInternal=function(){t.prototype.disposeInternal.call(this),this.stop()}, e.prototype.getRegionWithoutScroll_=function(t){if(a["default"].isDocument(t)){var e=window.innerHeight,o=window.innerWidth;return c["default"].makeRegion(e,e,0,o,0,o)}return c["default"].getRegion(t)}, e.prototype.scroll=function(t,e,o){this.stop(),this.scrollTimeout_=setTimeout(this.scrollInternal_.bind(this,t,e,o),this.delay)}, e.prototype.scrollElement_=function(t,e,o){a["default"].isDocument(t)?window.scrollBy(e,o):(t.scrollTop+=o,t.scrollLeft+=e)}, e.prototype.scrollInternal_=function(t,e,o){for(var i=0;i<t.length;i++){var r=this.getRegionWithoutScroll_(t[i]);if(c["default"].pointInsideRegion(e,o,r)){var n=0,l=0,s=c["default"].getScrollTop(t[i]),a=c["default"].getScrollLeft(t[i]);if(a>0&&Math.abs(e-r.left)<=this.maxDistance?n-=this.speed:Math.abs(e-r.right)<=this.maxDistance&&(n+=this.speed),s>0&&Math.abs(o-r.top)<=this.maxDistance?l-=this.speed:Math.abs(o-r.bottom)<=this.maxDistance&&(l+=this.speed),n||l){this.scrollElement_(t[i],n,l),this.scroll(t,e,o);break}}}}, e.prototype.stop=function(){clearTimeout(this.scrollTimeout_)}, e)}(u["default"]);p.prototype.registerMetalComponent&&p.prototype.registerMetalComponent(p,"DragAutoScroll"),p.ATTRS={delay:{validator:a["default"].isNumber,value:50},maxDistance:{validator:a["default"].isNumber,value:20},speed:{validator:a["default"].isNumber,value:20}},t["default"]=p});