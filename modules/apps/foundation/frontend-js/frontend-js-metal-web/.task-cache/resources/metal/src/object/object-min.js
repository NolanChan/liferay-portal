define("frontend-js-metal-web@1.0.6/metal/src/object/object-min", ["exports","../core"], function(n,t){"use strict";function e(n){return n&&n.__esModule?n:{"default":n}}function r(n,t){if(!(n instanceof t))throw new TypeError("Cannot call a class as a function")}Object.defineProperty(n,"__esModule",{value:!0});var o=e(t),u=function(){function n(){r(this,n)}return (n.mixin=function(n){for(var t,e,r=1;r<arguments.length;r++){e=arguments[r];for(t in e)n[t]=e[t]}return n}, n.getObjectByName=function(n,t){for(var e,r=n.split("."),u=t||window;e=r.shift();){if(!o["default"].isDefAndNotNull(u[e]))return null;u=u[e]}return u}, n.map=function(n,t){for(var e={},r=Object.keys(n),o=0;o<r.length;o++)e[r[o]]=t(r[o],n[r[o]]);return e}, n)}();n["default"]=u});