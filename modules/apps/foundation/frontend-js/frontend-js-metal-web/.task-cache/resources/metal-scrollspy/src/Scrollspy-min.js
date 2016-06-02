define("frontend-js-metal-web@1.0.8/metal-scrollspy/src/Scrollspy-min", ["exports","metal/src/metal","metal-dom/src/all/dom","metal-position/src/all/position","metal-state/src/State","metal-jquery-adapter/src/JQueryAdapter"], function(t,e,i,o,n,s){"use strict";function l(t){return t&&t.__esModule?t:{"default":t}}function r(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}function a(t,e){if(!t)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!e||"object"!=typeof e&&"function"!=typeof e?t:e}function c(t,e){if("function"!=typeof e&&null!==e)throw new TypeError("Super expression must either be null or a function, not "+typeof e);t.prototype=Object.create(e&&e.prototype,{constructor:{value:t,enumerable:!1,writable:!0,configurable:!0}}),e&&(Object.setPrototypeOf?Object.setPrototypeOf(t,e):t.__proto__=e)}Object.defineProperty(t,"__esModule",{value:!0});var h=l(e),u=l(i),f=l(o),d=l(n),p=l(s),g=function(t){function e(i){r(this,e);var o=a(this,t.call(this,i));return (o.regions=[], o.scrollHandle_=u["default"].on(o.scrollElement,"scroll",o.checkPosition.bind(o)), o.init(), o)}return (c(e,t), e.prototype.disposeInternal=function(){this.deactivateAll(),this.scrollHandle_.dispose(),t.prototype.disposeInternal.call(this)}, e.prototype.activate=function(t){this.activeIndex>=0&&this.deactivate(this.activeIndex),this.activeIndex=t,u["default"].addClasses(this.getElementForIndex(t),this.activeClass)}, e.prototype.checkPosition=function(){var t=this.getScrollHeight_(),e=f["default"].getScrollTop(this.scrollElement);if(t<e+this.offset)return void this.activate(this.regions.length-1);var i=this.findBestRegionAt_();i!==this.activeIndex&&(-1===i?this.deactivateAll():this.activate(i))}, e.prototype.deactivate=function(t){u["default"].removeClasses(this.getElementForIndex(t),this.activeClass)}, e.prototype.deactivateAll=function(){for(var t=0;t<this.regions.length;t++)this.deactivate(t);this.activeIndex=-1}, e.prototype.findBestRegionAt_=function(){var t=-1,e=this.getCurrentPosition();if(this.regions.length>0&&e>=this.regions[0].top)for(var i=0;i<this.regions.length;i++){var o=this.regions[i],n=i===this.regions.length-1;if(e>=o.top&&(n||e<this.regions[i+1].top)){t=i;break}}return t}, e.prototype.getCurrentPosition=function(){var t=f["default"].getScrollTop(this.scrollElement);return t+this.offset+this.scrollElementRegion_.top}, e.prototype.getElementForIndex=function(t){return this.resolveElement(this.regions[t].link)}, e.prototype.getScrollHeight_=function(){var t=f["default"].getHeight(this.scrollElement);return (t+=this.scrollElementRegion_.top, t-=f["default"].getClientHeight(this.scrollElement))}, e.prototype.init=function(){this.refresh(),this.on("elementChanged",this.refresh),this.on("offsetChanged",this.checkPosition),this.on("scrollElementChanged",this.onScrollElementChanged_),this.on("selectorChanged",this.refresh)}, e.prototype.onScrollElementChanged_=function(t){this.refresh(),this.scrollHandle_.dispose(),this.scrollHandle_=u["default"].on(t.newVal,"scroll",this.checkPosition.bind(this))}, e.prototype.refresh=function(){this.deactivateAll(),this.scrollElementRegion_=f["default"].getRegion(this.scrollElement),this.scrollHeight_=this.getScrollHeight_(),this.regions=[];for(var t=this.element.querySelectorAll(this.selector),e=f["default"].getScrollTop(this.scrollElement),i=0;i<t.length;++i){var o=t[i];if(o.hash&&o.hash.length>1){var n=document.getElementById(o.hash.substring(1));if(n){var s=f["default"].getRegion(n);this.regions.push({link:o,top:s.top+e,bottom:s.bottom+e})}}}this.sortRegions_(),this.deactivateAll(),this.checkPosition()}, e.prototype.sortRegions_=function(){this.regions.sort(function(t,e){return t.top-e.top})}, e)}(d["default"]);g.STATE={activeClass:{validator:h["default"].isString,value:"active"},activeIndex:{validator:h["default"].isNumber,value:-1},resolveElement:{validator:h["default"].isFunction,value:h["default"].identityFunction},scrollElement:{setter:u["default"].toElement,value:document},offset:{validator:h["default"].isNumber,value:0},element:{setter:u["default"].toElement},selector:{validator:h["default"].isString,value:"a"}},t["default"]=g,p["default"].register("scrollspy",g)});