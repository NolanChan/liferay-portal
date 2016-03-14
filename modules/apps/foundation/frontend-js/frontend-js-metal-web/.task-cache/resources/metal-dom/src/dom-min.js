define("frontend-js-metal-web@1.0.0/metal-dom/src/dom-min", ["exports","metal/src/metal","./DomEventHandle"], function(e,t,n){"use strict";function i(e){return e&&e.__esModule?e:{"default":e}}function o(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}Object.defineProperty(e,"__esModule",{value:!0});var a=i(n),r=function(){function e(){o(this,e)}return (e.addClasses=function(n,i){t.core.isObject(n)&&t.core.isString(i)&&("classList"in n?e.addClassesWithNative_(n,i):e.addClassesWithoutNative_(n,i))}, e.addClassesWithNative_=function(e,t){t.split(" ").forEach(function(t){e.classList.add(t)})}, e.addClassesWithoutNative_=function(e,t){var n=" "+e.className+" ",i="";t=t.split(" ");for(var o=0;o<t.length;o++){var a=t[o];-1===n.indexOf(" "+a+" ")&&(i+=" "+a)}i&&(e.className=e.className+i)}, e.closest=function(t,n){for(;t&&!e.match(t,n);)t=t.parentNode;return t}, e.append=function(n,i){if(t.core.isString(i)&&(i=e.buildFragment(i)),i instanceof NodeList)for(var o=Array.prototype.slice.call(i),a=0;a<o.length;a++)n.appendChild(o[a]);else n.appendChild(i);return i}, e.buildFragment=function(e){var t=document.createElement("div");t.innerHTML="<br>"+e,t.removeChild(t.firstChild);for(var n=document.createDocumentFragment();t.firstChild;)n.appendChild(t.firstChild);return n}, e.contains=function(e,n){return t.core.isDocument(e)?e.documentElement.contains(n):e.contains(n)}, e.delegate=function(t,n,i,o){var a=e.customEvents[n];return (a&&a.delegate&&(n=a.originalEvent,o=a.handler.bind(a,o)), e.on(t,n,e.handleDelegateEvent_.bind(null,i,o)))}, e.enterDocument=function(t){e.append(document.body,t)}, e.exitDocument=function(e){e.parentNode&&e.parentNode.removeChild(e)}, e.handleDelegateEvent_=function(n,i,o){e.normalizeDelegateEvent_(o);for(var a=o.target,r=!0;a&&!o.stopped&&(t.core.isString(n)&&e.match(a,n)&&(o.delegateTarget=a,r&=i(o)),a!==o.currentTarget);)a=a.parentNode;return (o.delegateTarget=null, r)}, e.hasClass=function(t,n){return"classList"in t?e.hasClassWithNative_(t,n):e.hasClassWithoutNative_(t,n)}, e.hasClassWithNative_=function(e,t){return e.classList.contains(t)}, e.hasClassWithoutNative_=function(e,t){return(" "+e.className+" ").indexOf(" "+t+" ")>=0}, e.isEmpty=function(e){return 0===e.childNodes.length}, e.match=function(t,n){if(!t||1!==t.nodeType)return!1;var i=Element.prototype,o=i.matches||i.webkitMatchesSelector||i.mozMatchesSelector||i.msMatchesSelector||i.oMatchesSelector;return o?o.call(t,n):e.matchFallback_(t,n)}, e.matchFallback_=function(e,t){for(var n=document.querySelectorAll(t,e.parentNode),i=0;i<n.length;++i)if(n[i]===e)return!0;return!1}, e.next=function(t,n){do if(t=t.nextSibling,t&&e.match(t,n))return t;while(t);return null}, e.normalizeDelegateEvent_=function(t){t.stopPropagation=e.stopPropagation_,t.stopImmediatePropagation=e.stopImmediatePropagation_}, e.on=function(n,i,o,r){if(t.core.isString(n))return e.delegate(document,i,n,o);var s=e.customEvents[i];return (s&&s.event&&(i=s.originalEvent,o=s.handler.bind(s,o)), n.addEventListener(i,o,r), new a["default"](n,i,o,r))}, e.once=function(e,t,n){var i=this.on(e,t,function(){return (i.removeListener(), n.apply(this,arguments))});return i}, e.parent=function(t,n){return e.closest(t.parentNode,n)}, e.registerCustomEvent=function(t,n){e.customEvents[t]=n}, e.removeChildren=function(e){for(var t;t=e.firstChild;)e.removeChild(t)}, e.removeClasses=function(n,i){t.core.isObject(n)&&t.core.isString(i)&&("classList"in n?e.removeClassesWithNative_(n,i):e.removeClassesWithoutNative_(n,i))}, e.removeClassesWithNative_=function(e,t){t.split(" ").forEach(function(t){e.classList.remove(t)})}, e.removeClassesWithoutNative_=function(e,t){var n=" "+e.className+" ";t=t.split(" ");for(var i=0;i<t.length;i++)n=n.replace(" "+t[i]+" "," ");e.className=n.trim()}, e.replace=function(e,t){e&&t&&e!==t&&e.parentNode&&(e.parentNode.insertBefore(t,e),e.parentNode.removeChild(e))}, e.stopImmediatePropagation_=function(){this.stopped=!0,Event.prototype.stopImmediatePropagation.call(this)}, e.stopPropagation_=function(){this.stopped=!0,Event.prototype.stopPropagation.call(this)}, e.supportsEvent=function(n,i){return e.customEvents[i]?!0:(t.core.isString(n)&&(s[n]||(s[n]=document.createElement(n)),n=s[n]),"on"+i in n)}, e.toElement=function(e){return t.core.isElement(e)||t.core.isDocument(e)?e:t.core.isString(e)?"#"===e[0]&&-1===e.indexOf(" ")?document.getElementById(e.substr(1)):document.querySelector(e):null}, e.toggleClasses=function(n,i){t.core.isObject(n)&&t.core.isString(i)&&("classList"in n?e.toggleClassesWithNative_(n,i):e.toggleClassesWithoutNative_(n,i))}, e.toggleClassesWithNative_=function(e,t){t.split(" ").forEach(function(t){e.classList.toggle(t)})}, e.toggleClassesWithoutNative_=function(e,t){var n=" "+e.className+" ";t=t.split(" ");for(var i=0;i<t.length;i++){var o=" "+t[i]+" ",a=n.indexOf(o);n=-1===a?n+t[i]+" ":n.substring(0,a)+" "+n.substring(a+o.length)}e.className=n.trim()}, e.triggerEvent=function(e,n,i){var o=document.createEvent("HTMLEvents");o.initEvent(n,!0,!0),t.object.mixin(o,i),e.dispatchEvent(o)}, e)}(),s={};r.customEvents={},e["default"]=r});