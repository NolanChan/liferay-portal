define("frontend-js-metal-web@1.0.4/metal/src/core-min", ["exports"], function(n){"use strict";function t(n,t){if(!(n instanceof t))throw new TypeError("Cannot call a class as a function")}Object.defineProperty(n,"__esModule",{value:!0});var e="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(n){return typeof n}:function(n){return n&&"function"==typeof Symbol&&n.constructor===Symbol?"symbol":typeof n},o=function(){function n(){t(this,n)}return (n.abstractMethod=function(){throw Error("Unimplemented abstract method")}, n.collectSuperClassesProperty=function(n,t){for(var e=[n[t]];n.__proto__&&!n.__proto__.isPrototypeOf(Function);)n=n.__proto__,e.push(n[t]);return e}, n.getFunctionName=function(n){if(!n.name){var t=n.toString();n.name=t.substring(9,t.indexOf("("))}return n.name}, n.getUid=function(t){return t?t[n.UID_PROPERTY]||(t[n.UID_PROPERTY]=n.uniqueIdCounter_++):n.uniqueIdCounter_++}, n.identityFunction=function(n){return n}, n.isBoolean=function(n){return"boolean"==typeof n}, n.isDef=function(n){return void 0!==n}, n.isDefAndNotNull=function(t){return n.isDef(t)&&!n.isNull(t)}, n.isDocument=function(n){return n&&"object"===("undefined"==typeof n?"undefined":e(n))&&9===n.nodeType}, n.isElement=function(n){return n&&"object"===("undefined"==typeof n?"undefined":e(n))&&1===n.nodeType}, n.isFunction=function(n){return"function"==typeof n}, n.isNull=function(n){return null===n}, n.isNumber=function(n){return"number"==typeof n}, n.isWindow=function(n){return null!==n&&n===n.window}, n.isObject=function(n){var t="undefined"==typeof n?"undefined":e(n);return"object"===t&&null!==n||"function"===t}, n.isPromise=function(n){return n&&"object"===("undefined"==typeof n?"undefined":e(n))&&"function"==typeof n.then}, n.isString=function(n){return"string"==typeof n}, n.mergeSuperClassesProperty=function(t,e,o){var r=e+"_MERGED";if(t.hasOwnProperty(r))return!1;var u=n.collectSuperClassesProperty(t,e);return (o&&(u=o(u)), t[r]=u, !0)}, n.nullFunction=function(){}, n)}();o.UID_PROPERTY="core_"+(1e9*Math.random()>>>0),o.uniqueIdCounter_=1,n["default"]=o});