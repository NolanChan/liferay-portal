define("frontend-js-metal-web@1.0.6/metal-soy/src/SoyAop-min", ["exports"], function(n){"use strict";Object.defineProperty(n,"__esModule",{value:!0});var e={interceptFns_:[],getOriginalFn:function(n){return n.originalFn?n.originalFn:n},handleTemplateCall_:function(n,t,i,r){var l=e.interceptFns_[e.interceptFns_.length-1];return l?l.call(null,n,t,i,r):n.call(null,t,i,r)},registerForInterception:function(n,t){var i=n[t];i.originalFn||(n[t]=e.handleTemplateCall_.bind(null,i),n[t].originalFn=i)},startInterception:function(n){e.interceptFns_.push(n)},stopAllInterceptions:function(){e.interceptFns_=[]},stopInterception:function(){e.interceptFns_.pop()}};n["default"]=e});