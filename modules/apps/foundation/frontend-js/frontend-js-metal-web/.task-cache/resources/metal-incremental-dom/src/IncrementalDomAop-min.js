define("frontend-js-metal-web@1.0.7/metal-incremental-dom/src/IncrementalDomAop-min", ["exports","metal/src/metal","./incremental-dom"], function(e,n){"use strict";function t(e,n){if(!(e instanceof n))throw new TypeError("Cannot call a class as a function")}function l(e,n){O.push(e,n)}function r(e,n,t){O=[e,n,t]}function a(){return i("elementOpen").apply(null,O)}function m(e){return (i("elementOpen").apply(null,arguments), i("elementClose")(e))}function i(e){return o[o.length-1][e]}function u(e){return i(e).apply(null,n.array.slice(arguments,1))}Object.defineProperty(e,"__esModule",{value:!0});var c=function(){function e(){t(this,e)}return (e.getOriginalFns=function(){return o[0]}, e.startInterception=function(t){var i=e.getOriginalFns();t=n.object.map(t,function(e,n){return n.bind(null,i[e])}),o.push(n.object.mixin({},i,t,{attr:l,elementOpenEnd:a,elementOpenStart:r,elementVoid:m}))}, e.stopInterception=function(){o.length>1&&o.pop()}, e)}(),o=[{attr:IncrementalDOM.attr,attributes:IncrementalDOM.attributes[IncrementalDOM.symbols["default"]],elementClose:IncrementalDOM.elementClose,elementOpen:IncrementalDOM.elementOpen,elementOpenEnd:IncrementalDOM.elementOpenEnd,elementOpenStart:IncrementalDOM.elementOpenStart,elementVoid:IncrementalDOM.elementVoid,text:IncrementalDOM.text}],O=[];IncrementalDOM.attr=u.bind(null,"attr"),IncrementalDOM.elementClose=u.bind(null,"elementClose"),IncrementalDOM.elementOpen=u.bind(null,"elementOpen"),IncrementalDOM.elementOpenEnd=u.bind(null,"elementOpenEnd"),IncrementalDOM.elementOpenStart=u.bind(null,"elementOpenStart"),IncrementalDOM.elementVoid=u.bind(null,"elementVoid"),IncrementalDOM.text=u.bind(null,"text"),IncrementalDOM.attributes[IncrementalDOM.symbols["default"]]=u.bind(null,"attributes"),e["default"]=c});