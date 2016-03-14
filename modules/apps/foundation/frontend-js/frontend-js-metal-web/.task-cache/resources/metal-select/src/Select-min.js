define("frontend-js-metal-web@1.0.0/metal-select/src/Select-min", ["exports","metal/src/metal","metal-dom/src/all/dom","./Select.soy","metal-jquery-adapter/src/JQueryAdapter","metal-dropdown/src/Dropdown"], function(e,t,n,o,r){"use strict";function s(e){return e&&e.__esModule?e:{"default":e}}function i(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function d(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function l(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(e,"__esModule",{value:!0});var a=s(t),u=s(n),c=s(o),f=s(r),p=function(e){function t(){return (i(this,t), d(this,e.apply(this,arguments)))}return (l(t,e), t.prototype.findItemIndex_=function(e){for(var t=this.element.querySelectorAll("li"),n=0;n<t.length;n++)if(t.item(n)===e)return n}, t.prototype.focusIndex_=function(e){var t=this.element.querySelector(".select-option:nth-child("+(e+1)+") a");t&&(this.focusedIndex_=e,t.focus())}, t.prototype.getDropdown=function(){return this.components[this.id+"-dropdown"]}, t.prototype.handleDropdownAttrsSynced_=function(e){this.openedWithKeyboard_?(this.focusIndex_(0),this.openedWithKeyboard_=!1):e.changes.expanded&&(this.focusedIndex_=null)}, t.prototype.handleItemClick_=function(e){this.selectedIndex=this.findItemIndex_(e.delegateTarget),this.getDropdown().close(),e.preventDefault()}, t.prototype.handleKeyDown_=function(e){if(this.getDropdown().expanded)switch(e.keyCode){case 27:this.getDropdown().close();break;case 38:this.focusedIndex_=a["default"].isDefAndNotNull(this.focusedIndex_)?this.focusedIndex_:1,this.focusIndex_(0===this.focusedIndex_?this.items.length-1:this.focusedIndex_-1),e.preventDefault();break;case 40:this.focusedIndex_=a["default"].isDefAndNotNull(this.focusedIndex_)?this.focusedIndex_:-1,this.focusIndex_(this.focusedIndex_===this.items.length-1?0:this.focusedIndex_+1),e.preventDefault()}else if((13===e.keyCode||32===e.keyCode)&&u["default"].hasClass(e.target,"dropdown-select"))return (this.openedWithKeyboard_=!0, this.getDropdown().open(), void e.preventDefault())}, t)}(c["default"]);p.prototype.registerMetalComponent&&p.prototype.registerMetalComponent(p,"Select"),p.ATTRS={arrowClass:{value:"caret"},buttonClass:{validator:a["default"].isString,value:"btn btn-default"},hiddenInputName:{validator:a["default"].isString},items:{validator:function(e){return e instanceof Array},valueFn:function(){return[]}},label:{validator:a["default"].isString},selectedIndex:{validator:a["default"].isNumber,valueFn:function(){return this.label||!this.items.length?-1:0}}},p.ELEMENT_CLASSES="select",e["default"]=p,f["default"].register("select",p)});