define("frontend-js-metal-web@1.0.4/metal-dom/src/features-min", ["exports","./dom","metal/src/metal"], function(n,e,t){"use strict";function a(n){return n&&n.__esModule?n:{"default":n}}function i(n,e){if(!(n instanceof e))throw new TypeError("Cannot call a class as a function")}Object.defineProperty(n,"__esModule",{value:!0});var r=a(e),o=function(){function n(){i(this,n)}return (n.checkAnimationEventName=function(){return (void 0===n.animationEventName_&&(n.animationEventName_={animation:n.checkAnimationEventName_("animation"),transition:n.checkAnimationEventName_("transition")}), n.animationEventName_)}, n.checkAnimationEventName_=function(e){for(var a=["Webkit","MS","O",""],i=t.string.replaceInterval(e,0,1,e.substring(0,1).toUpperCase()),r=[i+"End",i+"End",i+"End",e+"end"],o=0;o<a.length;o++)if(void 0!==n.animationElement_.style[a[o]+i])return a[o].toLowerCase()+r[o];return e+"end"}, n.checkAttrOrderChange=function(){if(void 0===n.attrOrderChange_){var e='<div data-component="" data-ref=""></div>',t=document.createElement("div");r["default"].append(t,e),n.attrOrderChange_=e!==t.innerHTML}return n.attrOrderChange_}, n)}();o.animationElement_=document.createElement("div"),o.animationEventName_=void 0,o.attrOrderChange_=void 0,n["default"]=o});