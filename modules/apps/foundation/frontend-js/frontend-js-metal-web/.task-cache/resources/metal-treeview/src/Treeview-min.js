define("frontend-js-metal-web@1.0.8/metal-treeview/src/Treeview-min", ["exports","metal-component/src/all/component","metal-soy/src/Soy","./Treeview.soy","metal-jquery-adapter/src/JQueryAdapter"], function(e,t,o,n,r){"use strict";function a(e){return e&&e.__esModule?e:{"default":e}}function i(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function d(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function u(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(e,"__esModule",{value:!0});var p=a(t),l=a(o),s=a(n),c=a(r),f=function(e){function t(){return (i(this,t), d(this,e.apply(this,arguments)))}return (u(t,e), t.prototype.getNodeObj=function(e){for(var t=this.nodes[e[0]],o=1;o<e.length;o++)t=t.children[e[o]];return t}, t.prototype.handleNodeClicked_=function(e){this.toggleExpandedState_(e.delegateTarget.parentNode.parentNode)}, t.prototype.handleNodeKeyUp_=function(e){13!==e.keyCode&&32!==e.keyCode||this.toggleExpandedState_(e.delegateTarget.parentNode.parentNode)}, t.prototype.toggleExpandedState_=function(e){var t=e.getAttribute("data-treeview-path").split("-"),o=this.getNodeObj(t);o.expanded=!o.expanded,this.nodes=this.nodes}, t)}(p["default"]);l["default"].register(f,s["default"]),f.STATE={nodes:{validator:Array.isArray,valueFn:function(){return[]}}},e["default"]=f,c["default"].register("treeview",f)});