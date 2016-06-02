define("frontend-js-metal-web@1.0.8/metal-slider/src/Slider-min", ["exports","metal/src/metal","metal-dom/src/all/dom","metal-component/src/all/component","metal-drag-drop/src/all/drag","metal-position/src/all/position","metal-soy/src/Soy","./Slider.soy","metal-jquery-adapter/src/JQueryAdapter"], function(t,e,a,n,i,o,r,s,l){"use strict";function u(t){return t&&t.__esModule?t:{"default":t}}function d(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}function p(t,e){if(!t)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!e||"object"!=typeof e&&"function"!=typeof e?t:e}function h(t,e){if("function"!=typeof e&&null!==e)throw new TypeError("Super expression must either be null or a function, not "+typeof e);t.prototype=Object.create(e&&e.prototype,{constructor:{value:t,enumerable:!1,writable:!0,configurable:!0}}),e&&(Object.setPrototypeOf?Object.setPrototypeOf(t,e):t.__proto__=e)}Object.defineProperty(t,"__esModule",{value:!0});var c=u(e),f=u(a),y=u(n),m=u(o),g=u(r),_=u(s),v=u(l),D=function(t){function e(){return (d(this,e), p(this,t.apply(this,arguments)))}return (h(e,t), e.prototype.attached=function(){this.drag_=new i.Drag({constrain:this.element.querySelector(".rail"),container:this.element,handles:".handle",sources:".rail-handle"}),this.on("elementChanged",this.handleElementChanged_),this.attachDragEvents_()}, e.prototype.attachDragEvents_=function(){this.drag_.on(i.Drag.Events.DRAG,this.updateValueFromDragData_.bind(this)),this.drag_.on(i.Drag.Events.END,this.updateValueFromDragData_.bind(this))}, e.prototype.disposeInternal=function(){t.prototype.disposeInternal.call(this),this.drag_.dispose()}, e.prototype.getDrag=function(){return this.drag_}, e.prototype.handleElementChanged_=function(t){this.drag_.container=t.newVal,this.drag_.constrain=t.newVal.querySelector(".rail")}, e.prototype.onRailMouseDown_=function(t){(f["default"].hasClass(t.target,"rail")||f["default"].hasClass(t.target,"rail-active"))&&this.updateValue_(t.offsetX,0)}, e.prototype.syncMax=function(t){t<this.value?this.value=t:this.updateHandlePosition_()}, e.prototype.syncMin=function(t){t>this.value?this.value=t:this.updateHandlePosition_()}, e.prototype.syncValue=function(){this.updateHandlePosition_()}, e.prototype.updateHandlePosition_=function(){if(!this.drag_||!this.drag_.isDragging()){var t=100*(this.value-this.min)/(this.max-this.min)+"%";this.element.querySelector(".rail-handle").style.left=t}}, e.prototype.updateValue_=function(t,e){var a=m["default"].getRegion(this.element);this.value=Math.round(e+t/a.width*(this.max-this.min))}, e.prototype.updateValueFromDragData_=function(t){this.updateValue_(t.relativeX,this.min)}, e)}(y["default"]);g["default"].register(D,_["default"]),D.STATE={inputName:{validator:c["default"].isString},max:{value:100},min:{value:0},value:{validator:function(t){return c["default"].isNumber(t)&&this.min<=t&&t<=this.max},value:0}},t["default"]=D,v["default"].register("slider",D)});