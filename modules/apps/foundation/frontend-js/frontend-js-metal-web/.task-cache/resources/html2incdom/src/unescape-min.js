define("frontend-js-metal-web@1.0.6/html2incdom/src/unescape-min", ["exports"], function(e){"use strict";function r(e){var r={"&amp;":"&","&lt;":"<","&gt;":">","&quot;":'"'},n=document.createElement("div");return e.replace(t,function(e,t){var i=r[e];if(i)return i;if("#"===t.charAt(0)){var u=Number("0"+t.substr(1));isNaN(u)||(i=String.fromCharCode(u))}return (i||(n.innerHTML=e+" ",i=n.firstChild.nodeValue.slice(0,-1)), r[e]=i, i)})}Object.defineProperty(e,"__esModule",{value:!0}),e["default"]=r;var t=/&([^;\s<&]+);?/g});