define("frontend-js-metal-web@1.0.8/metal-anim/src/Anim-min", ["exports","metal/src/metal","metal-dom/src/all/dom"], function(t,e,n){"use strict";function i(t){return t&&t.__esModule?t:{"default":t}}function o(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}Object.defineProperty(t,"__esModule",{value:!0});var a=i(e),u=function(){function t(){o(this,t)}return (t.emulateEnd=function(t,e){return this.getComputedDurationMs(t,"animation")>this.getComputedDurationMs(t,"transition")?this.emulateEnd_(t,"animation",e):this.emulateEnd_(t,"transition",e)}, t.emulateAnimationEnd=function(t,e){return this.emulateEnd_(t,"animation",e)}, t.emulateTransitionEnd=function(t,e){this.emulateEnd_(t,"transition",e)}, t.emulateEnd_=function(t,e,i){var o=i;a["default"].isDef(i)||(o=this.getComputedDurationMs(t,e));var u=setTimeout(function(){n.dom.triggerEvent(t,n.features.checkAnimationEventName()[e])},o),r=function(){clearTimeout(u),s.removeListener()},s=n.dom.once(t,e+"end",r);return{abort:r}}, t.getComputedDurationMs=function(t,e){return 1e3*(parseFloat(window.getComputedStyle(t,null).getPropertyValue(e+"-duration"))||0)}, t)}();t["default"]=u});