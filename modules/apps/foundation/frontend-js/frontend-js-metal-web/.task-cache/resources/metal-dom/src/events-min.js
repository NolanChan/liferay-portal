define("frontend-js-metal-web@1.0.8/metal-dom/src/events-min", ["./dom","./features"], function(e,t){"use strict";function n(e){return e&&e.__esModule?e:{"default":e}}var o=n(e),r=n(t),a={mouseenter:"mouseover",mouseleave:"mouseout",pointerenter:"pointerover",pointerleave:"pointerout"};Object.keys(a).forEach(function(e){o["default"].registerCustomEvent(e,{delegate:!0,handler:function(t,n){var o=n.relatedTarget,r=n.delegateTarget;return!o||o!==r&&!r.contains(o)?(n.customType=e,t(n)):void 0},originalEvent:a[e]})});var i={animation:"animationend",transition:"transitionend"};Object.keys(i).forEach(function(e){var t=i[e];o["default"].registerCustomEvent(t,{event:!0,delegate:!0,handler:function(e,n){return (n.customType=t, e(n))},originalEvent:r["default"].checkAnimationEventName()[e]})})});