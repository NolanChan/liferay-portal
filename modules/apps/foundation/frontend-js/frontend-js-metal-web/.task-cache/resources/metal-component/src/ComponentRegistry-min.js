define("frontend-js-metal-web@1.0.2/metal-component/src/ComponentRegistry-min", ["exports","metal/src/metal"], function(n,t){"use strict";function e(n,t){if(!(n instanceof t))throw new TypeError("Cannot call a class as a function")}Object.defineProperty(n,"__esModule",{value:!0});var o=function(){function n(){e(this,n)}return (n.getConstructor=function(t){var e=n.components_[t];return e}, n.register=function(e,o){var r=o;r||(r=e.hasOwnProperty("NAME")?e.NAME:t.core.getFunctionName(e)),e.NAME=r,n.components_[r]=e}, n)}();o.components_={},n["default"]=o});