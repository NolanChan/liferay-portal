define("frontend-js-metal-web@1.0.4/metal-treeview/src/Treeview.soy-min", ["exports","metal-component/src/all/component","metal-soy/src/soy"], function(e,t,r){"use strict";function n(e){return e&&e.__esModule?e:{"default":e}}function o(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function a(e,t){if(!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!t||"object"!=typeof t&&"function"!=typeof t?e:t}function i(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function, not "+typeof t);e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,enumerable:!1,writable:!0,configurable:!0}}),t&&(Object.setPrototypeOf?Object.setPrototypeOf(e,t):e.__proto__=t)}Object.defineProperty(e,"__esModule",{value:!0});var d=n(t),s=r.SoyTemplates.get();"undefined"==typeof s.Treeview&&(s.Treeview={}),s.Treeview.render=function(e,t,r){return soydata.VERY_UNSAFE.ordainSanitizedHtml('<div id="'+soy.$$escapeHtmlAttribute(e.id)+'" class="treeview component'+soy.$$escapeHtmlAttribute(e.elementClasses?" "+e.elementClasses:"")+'" role="tree">'+s.Treeview.nodes(e,null,r)+"</div>")},goog.DEBUG&&(s.Treeview.render.soyTemplateName="Templates.Treeview.render"),s.Treeview.nodes=function(e,t,r){var n="",o=e.id+"-"+(null!=e.surfaceId?e.surfaceId:"nodes");n+='<ul id="'+soy.$$escapeHtmlAttribute(o)+'" class="treeview-nodes">';for(var a=e.nodes,i=a.length,d=0;i>d;d++){var l=a[d],c=d;n+=s.Treeview.node({id:e.id,node:l,surfaceId:null!=e.parentSurfaceId?e.parentSurfaceId+"-"+c:c},null,r)}return (n+="</ul>", soydata.VERY_UNSAFE.ordainSanitizedHtml(n))},goog.DEBUG&&(s.Treeview.nodes.soyTemplateName="Templates.Treeview.nodes"),s.Treeview.node=function(e,t,r){return soydata.VERY_UNSAFE.ordainSanitizedHtml('<li id="'+soy.$$escapeHtmlAttribute(e.id)+"-"+soy.$$escapeHtmlAttribute(e.surfaceId)+'" class="treeview-node">'+(e.node?'<div class="treeview-node-wrapper'+soy.$$escapeHtmlAttribute(e.node.expanded?" expanded":"")+'"><div class="treeview-node-main clearfix'+soy.$$escapeHtmlAttribute(e.node.children?" hasChildren":"")+'" data-onclick="handleNodeClicked_" data-onkeyup="handleNodeKeyUp_" aria-expanded="'+soy.$$escapeHtmlAttribute(e.node.expanded?"true":"false")+'" role="treeitem" tabindex="0">'+(e.node.children?'<div class="treeview-node-toggler"></div>':"")+'<span class="treeview-node-name">'+soy.$$escapeHtml(e.node.name)+"</span></div>"+(e.node.children?s.Treeview.nodes({id:e.id,nodes:e.node.children,parentSurfaceId:e.surfaceId,surfaceId:e.surfaceId+"-nodes"},null,r):"")+"</div>":"")+"</li>")},goog.DEBUG&&(s.Treeview.node.soyTemplateName="Templates.Treeview.node"),s.Treeview.render.params=["id"],s.Treeview.nodes.params=["id","nodes","parentSurfaceId","surfaceId"],s.Treeview.node["private"]=!0;var l=function(e){function t(){return (o(this,t), a(this,e.apply(this,arguments)))}return (i(t,e), t)}(d["default"]);l.prototype.registerMetalComponent&&l.prototype.registerMetalComponent(l,"Treeview"),l.RENDERER=r.SoyRenderer,r.SoyAop.registerTemplates("Treeview"),e["default"]=l});