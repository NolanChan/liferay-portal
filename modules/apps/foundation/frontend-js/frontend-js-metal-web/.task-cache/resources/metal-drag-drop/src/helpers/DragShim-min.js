define("frontend-js-metal-web@1.0.3/metal-drag-drop/src/helpers/DragShim-min", ["exports","metal-dom/src/all/dom"], function(e,t){"use strict";function o(e){return e&&e.__esModule?e:{"default":e}}function i(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}Object.defineProperty(e,"__esModule",{value:!0});var c=o(t),n=function(){function e(){i(this,e)}return (e.attachDocListeners=function(t,o){var i=document;t&&(i=e.getDocShim(),i.style.display="block");var n=Object.keys(o);return n.map(function(e){var t="touch"===e.substr(0,5);return c["default"].on(t?document:i,e,o[e])})}, e.getDocShim=function(){return (e.docShim_||(e.docShim_=document.createElement("div"),e.docShim_.className="shim",e.docShim_.style.position="fixed",e.docShim_.style.top=0,e.docShim_.style.left=0,e.docShim_.style.width="100%",e.docShim_.style.height="100%",e.docShim_.style.display="none",e.docShim_.style.opacity=0,e.docShim_.style.zIndex=9999,c["default"].enterDocument(e.docShim_)), e.docShim_)}, e.hideDocShim=function(){e.getDocShim().style.display="none"}, e.reset=function(){e.docShim_&&(c["default"].exitDocument(e.docShim_),e.docShim_=null)}, e)}();n.docShim_=null,e["default"]=n});