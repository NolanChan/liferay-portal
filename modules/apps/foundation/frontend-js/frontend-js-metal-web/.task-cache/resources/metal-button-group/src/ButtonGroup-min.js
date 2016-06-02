define("frontend-js-metal-web@1.0.8/metal-button-group/src/ButtonGroup-min", ["exports","metal/src/metal","metal-component/src/all/component","metal-soy/src/Soy","./ButtonGroup.soy","metal-jquery-adapter/src/JQueryAdapter"], function(e,t,n,r,o,s){"use strict";function i(e){return e&&e.__esModule?e:{"default":e}}function l(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function u(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function a(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(e,"__esModule",{value:!0});var c=i(t),f=i(n),d=i(r),p=i(o),h=i(s),b=function(e){function t(){return (l(this,t), u(this,e.apply(this,arguments)))}return (a(t,e), t.prototype.handleClick_=function(e){var t=e.delegateTarget,n=t.getAttribute("data-index"),r=this.selected.indexOf(this.buttons[n].label);-1===r?(this.selected.push(this.buttons[n].label),this.selected=this.selected):this.selected.length>this.minSelected&&(this.selected.splice(r,1),this.selected=this.selected)}, t.prototype.setterSelectedFn_=function(e){for(var t=Math.min(this.minSelected,this.buttons.length),n=0;e.length<t;)-1===e.indexOf(this.buttons[n].label)&&e.push(this.buttons[n].label),n++;return e}, t)}(f["default"]);d["default"].register(b,p["default"]),b.STATE={buttons:{validator:function(e){return e instanceof Array},valueFn:function(){return[]}},minSelected:{validator:c["default"].isNumber,value:0,writeOnce:!0},selected:{setter:"setterSelectedFn_",validator:Array.isArray,valueFn:function(){return[]}}},b.SELECTED_CLASS="btn-group-selected",e["default"]=b,h["default"].register("buttonGroup",b)});