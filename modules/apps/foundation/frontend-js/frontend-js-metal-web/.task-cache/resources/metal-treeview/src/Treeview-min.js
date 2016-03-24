define("frontend-js-metal-web@1.0.4/metal-treeview/src/Treeview-min", ["exports","metal-dom/src/all/dom","./Treeview.soy","metal-jquery-adapter/src/JQueryAdapter"], function(e,t,n,r){"use strict";function o(e){return e&&e.__esModule?e:{"default":e}}function a(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function d(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function i(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(e,"__esModule",{value:!0});var s=o(t),p=o(n),u=o(r),f=function(e){function t(){return (a(this,t), d(this,e.apply(this,arguments)))}return (i(t,e), t.prototype.attached=function(){this.on("nodesChanged",this.onNodesChanged_),this.getRenderer().on("renderSurface",this.handleRenderSurface_.bind(this))}, t.prototype.getNodeObj=function(e){for(var t=this.nodes[e[0]],n=1;n<e.length;n++)t=t.children[e[n]];return t}, t.prototype.getNodeObjFromId_=function(e){var t=e.substr(this.id.length+1).split("-");return this.getNodeObj(t)}, t.prototype.handleNodeClicked_=function(e){this.toggleExpandedState_(e.delegateTarget)}, t.prototype.handleNodeKeyUp_=function(e){13!==e.keyCode&&32!==e.keyCode||this.toggleExpandedState_(e.delegateTarget)}, t.prototype.handleRenderSurface_=function(e,t){this.ignoreSurfaceUpdate_&&(t.preventDefault(),this.ignoreSurfaceUpdate_=!1)}, t.prototype.onNodesChanged_=function(){this.ignoreSurfaceUpdate_=!1}, t.prototype.toggleExpandedState_=function(e){var t=this.getNodeObjFromId_(e.parentNode.parentNode.id);t.expanded=!t.expanded,t.expanded?(s["default"].addClasses(e.parentNode,"expanded"),e.setAttribute("aria-expanded","true")):(s["default"].removeClasses(e.parentNode,"expanded"),e.setAttribute("aria-expanded","false")),this.nodes=this.nodes,this.ignoreSurfaceUpdate_=!0}, t)}(p["default"]);f.prototype.registerMetalComponent&&f.prototype.registerMetalComponent(f,"Treeview"),f.ELEMENT_CLASSES="treeview",f.ATTRS={nodes:{validator:Array.isArray,valueFn:function(){return[]}}},e["default"]=f,u["default"].register("treeview",f)});