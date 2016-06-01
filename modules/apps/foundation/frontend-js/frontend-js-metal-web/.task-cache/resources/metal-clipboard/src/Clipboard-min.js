define("frontend-js-metal-web@1.0.7/metal-clipboard/src/Clipboard-min", ["exports","metal/src/metal","metal-dom/src/all/dom","metal-state/src/State","metal-jquery-adapter/src/JQueryAdapter"], function(t,e,i,a,n){"use strict";function r(t){return t&&t.__esModule?t:{"default":t}}function o(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}function l(t,e){if(!t)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!e||"object"!=typeof e&&"function"!=typeof e?t:e}function s(t,e){if("function"!=typeof e&&null!==e)throw new TypeError("Super expression must either be null or a function, not "+typeof e);t.prototype=Object.create(e&&e.prototype,{constructor:{value:t,enumerable:!1,writable:!0,configurable:!0}}),e&&(Object.setPrototypeOf?Object.setPrototypeOf(t,e):t.__proto__=e)}Object.defineProperty(t,"__esModule",{value:!0});var c=r(e),u=r(i),d=r(a),h=r(n),f=function(t){function e(i){o(this,e);var a=l(this,t.call(this,i));return (a.listener_=u["default"].on(a.selector,"click",function(t){return a.initialize(t)}), a)}return (s(e,t), e.prototype.disposeInternal=function(){this.listener_.dispose(),this.listener_=null,this.clipboardAction_&&(this.clipboardAction_.dispose(),this.clipboardAction_=null)}, e.prototype.initialize=function(t){this.clipboardAction_&&(this.clipboardAction_=null),this.clipboardAction_=new p({host:this,action:this.action(t.delegateTarget),target:this.target(t.delegateTarget),text:this.text(t.delegateTarget),trigger:t.delegateTarget})}, e)}(d["default"]);f.STATE={action:{validator:c["default"].isFunction,value:function(t){return t.getAttribute("data-action")}},selector:{value:"[data-clipboard]",validator:c["default"].isString},target:{validator:c["default"].isFunction,value:function(t){return document.querySelector(t.getAttribute("data-target"))}},text:{validator:c["default"].isFunction,value:function(t){return t.getAttribute("data-text")}}};var p=function(t){function e(i){o(this,e);var a=l(this,t.call(this,i));return (a.text?a.selectValue():a.target&&a.selectTarget(), a)}return (s(e,t), e.prototype.clearSelection=function(){this.target&&this.target.blur(),window.getSelection().removeAllRanges()}, e.prototype.copyText=function(){var t=void 0;try{t=document.execCommand(this.action)}catch(e){t=!1}this.handleResult(t)}, e.prototype.disposeInternal=function(){this.removeFakeElement(),t.prototype.disposeInternal.call(this)}, e.prototype.handleResult=function(t){t?this.host.emit("success",{action:this.action,text:this.selectedText,trigger:this.trigger,clearSelection:this.clearSelection.bind(this)}):this.host.emit("error",{action:this.action,trigger:this.trigger,clearSelection:this.clearSelection.bind(this)})}, e.prototype.removeFakeElement=function(){this.fake&&u["default"].exitDocument(this.fake),this.removeFakeHandler&&this.removeFakeHandler.removeListener()}, e.prototype.selectTarget=function(){if("INPUT"===this.target.nodeName||"TEXTAREA"===this.target.nodeName)this.target.select(),this.selectedText=this.target.value;else{var t=document.createRange(),e=window.getSelection();t.selectNodeContents(this.target),e.addRange(t),this.selectedText=e.toString()}this.copyText()}, e.prototype.selectValue=function(){this.removeFakeElement(),this.removeFakeHandler=u["default"].once(document,"click",this.removeFakeElement.bind(this)),this.fake=document.createElement("textarea"),this.fake.style.position="fixed",this.fake.style.left="-9999px",this.fake.setAttribute("readonly",""),this.fake.value=this.text,this.selectedText=this.text,u["default"].enterDocument(this.fake),this.fake.select(),this.copyText()}, e)}(d["default"]);p.STATE={action:{value:"copy",validator:function(t){return"copy"===t||"cut"===t}},host:{validator:function(t){return t instanceof f}},selectedText:{validator:c["default"].isString},target:{validator:c["default"].isElement},text:{validator:c["default"].isString},trigger:{validator:c["default"].isElement}},t["default"]=f,h["default"].register("clipboard",f)});