define("frontend-js-metal-web@1.0.5/metal-attribute/src/Attribute-min", ["exports","metal/src/metal","metal-events/src/events"], function(t,e,r){"use strict";function n(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}function i(t,e){if(!t)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!e||"object"!=typeof e&&"function"!=typeof e?t:e}function o(t,e){if("function"!=typeof e&&null!==e)throw new TypeError("Super expression must either be null or a function, not "+typeof e);t.prototype=Object.create(e&&e.prototype,{constructor:{value:t,enumerable:!1,writable:!0,configurable:!0}}),e&&(Object.setPrototypeOf?Object.setPrototypeOf(t,e):t.__proto__=e)}Object.defineProperty(t,"__esModule",{value:!0});var a=function(t){function r(e){n(this,r);var o=i(this,t.call(this));return (o.scheduledBatchData_=null, o.attrsInfo_={}, o.setShouldUseFacade(!0), o.mergeInvalidAttrs_(), o.addAttrsFromStaticHint_(e), o)}return (o(r,t), r.prototype.addAttr=function(t,e,r){this.buildAttrInfo_(t,e,r),Object.defineProperty(this,t,this.buildAttrPropertyDef_(t))}, r.prototype.addAttrs=function(t,e,r){e=e||{};for(var n=Object.keys(t),i={},o=0;o<n.length;o++){var a=n[o];this.buildAttrInfo_(a,t[a],e[a]),i[a]=this.buildAttrPropertyDef_(a)}r!==!1&&Object.defineProperties(r||this,i)}, r.prototype.addAttrsFromStaticHint_=function(t){var e=this.constructor,n=!1;r.mergeAttrsStatic(e)&&(n=e.prototype),this.addAttrs(e.ATTRS_MERGED,t,n)}, r.prototype.assertValidAttrName_=function(t){if(this.constructor.INVALID_ATTRS_MERGED[t])throw new Error("It's not allowed to create an attribute with the name \""+t+'".')}, r.prototype.buildAttrInfo_=function(t,e,n){this.assertValidAttrName_(t),this.attrsInfo_[t]={config:e||{},initialValue:n,state:r.States.UNINITIALIZED}}, r.prototype.buildAttrPropertyDef_=function(t){return{configurable:!0,enumerable:!0,get:function(){return this.getAttrValue_(t)},set:function(e){this.setAttrValue_(t,e)}}}, r.prototype.callFunction_=function(t,r){return e.core.isString(t)?this[t].apply(this,r):e.core.isFunction(t)?t.apply(this,r):void 0}, r.prototype.callSetter_=function(t,e,r){var n=this.attrsInfo_[t],i=n.config;return (i.setter&&(e=this.callFunction_(i.setter,[e,r])), e)}, r.prototype.callValidator_=function(t,e){var r=this.attrsInfo_[t],n=r.config;return n.validator?this.callFunction_(n.validator,[e]):!0}, r.prototype.canSetAttribute=function(t){var e=this.attrsInfo_[t];return!e.config.writeOnce||!e.written}, r.prototype.disposeInternal=function(){t.prototype.disposeInternal.call(this),this.attrsInfo_=null,this.scheduledBatchData_=null}, r.prototype.emitBatchEvent_=function(){if(!this.isDisposed()){var t=this.scheduledBatchData_;this.scheduledBatchData_=null,this.emit("attrsChanged",t)}}, r.prototype.get=function(t){return this[t]}, r.prototype.getAttrConfig=function(t){return(this.attrsInfo_[t]||{}).config}, r.prototype.getAttrs=function(t){for(var e={},r=t||this.getAttrNames(),n=0;n<r.length;n++)e[r[n]]=this[r[n]];return e}, r.prototype.getAttrNames=function(){return Object.keys(this.attrsInfo_)}, r.prototype.getAttrValue_=function(t){return (this.initAttr_(t), this.attrsInfo_[t].value)}, r.prototype.hasBeenSet=function(t){var e=this.attrsInfo_[t];return e.state===r.States.INITIALIZED||e.initialValue}, r.prototype.informChange_=function(t,e){if(this.shouldInformChange_(t,e)){var r={attrName:t,newVal:this[t],prevVal:e};this.emit(t+"Changed",r),this.emit("attrChanged",r),this.scheduleBatchEvent_(r)}}, r.prototype.initAttr_=function(t){var e=this.attrsInfo_[t];e.state===r.States.UNINITIALIZED&&(e.state=r.States.INITIALIZING,this.setInitialValue_(t),e.written||(e.state=r.States.INITIALIZING_DEFAULT,this.setDefaultValue_(t)),e.state=r.States.INITIALIZED)}, r.mergeAttrs_=function(t){return e.object.mixin.apply(null,[{}].concat(t.reverse()))}, r.mergeAttrsStatic=function(t){return e.core.mergeSuperClassesProperty(t,"ATTRS",r.mergeAttrs_)}, r.prototype.mergeInvalidAttrs_=function(){e.core.mergeSuperClassesProperty(this.constructor,"INVALID_ATTRS",function(t){return e.array.flatten(t).reduce(function(t,e){return (e&&(t[e]=!0), t)},{})})}, r.prototype.removeAttr=function(t){this.attrsInfo_[t]=null,delete this[t]}, r.prototype.scheduleBatchEvent_=function(t){this.scheduledBatchData_||(e.async.nextTick(this.emitBatchEvent_,this),this.scheduledBatchData_={changes:{}});var r=t.attrName,n=this.scheduledBatchData_.changes;n[r]?n[r].newVal=t.newVal:n[r]=t}, r.prototype.set=function(t,e){this[t]=e}, r.prototype.setAttrs=function(t){for(var e=Object.keys(t),r=0;r<e.length;r++)this[e[r]]=t[e[r]]}, r.prototype.setAttrValue_=function(t,e){if(this.canSetAttribute(t)&&this.validateAttrValue_(t,e)){var n=this.attrsInfo_[t];void 0===n.initialValue&&n.state===r.States.UNINITIALIZED&&(n.state=r.States.INITIALIZED);var i=this[t];n.value=this.callSetter_(t,e,i),n.written=!0,this.informChange_(t,i)}}, r.prototype.setDefaultValue_=function(t){var e=this.attrsInfo_[t].config;void 0!==e.value?this[t]=e.value:this[t]=this.callFunction_(e.valueFn)}, r.prototype.setInitialValue_=function(t){var e=this.attrsInfo_[t];void 0!==e.initialValue&&(this[t]=e.initialValue,e.initialValue=void 0)}, r.prototype.shouldInformChange_=function(t,n){var i=this.attrsInfo_[t];return i.state===r.States.INITIALIZED&&(e.core.isObject(n)||n!==this[t])}, r.prototype.validateAttrValue_=function(t,e){var n=this.attrsInfo_[t];return n.state===r.States.INITIALIZING_DEFAULT||this.callValidator_(t,e)}, r)}(r.EventEmitter);a.prototype.registerMetalComponent&&a.prototype.registerMetalComponent(a,"Attribute"),a.INVALID_ATTRS=["attr","attrs"],a.States={UNINITIALIZED:0,INITIALIZING:1,INITIALIZING_DEFAULT:2,INITIALIZED:3},t["default"]=a});