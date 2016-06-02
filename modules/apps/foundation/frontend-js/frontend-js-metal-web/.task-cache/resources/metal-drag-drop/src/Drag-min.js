define("frontend-js-metal-web@1.0.8/metal-drag-drop/src/Drag-min", ["exports","metal/src/metal","metal-dom/src/all/dom","./helpers/DragAutoScroll","./helpers/DragScrollDelta","./helpers/DragShim","metal-events/src/events","metal-position/src/all/position","metal-state/src/State"], function(t,e,o,i,r,s,n,a,l){"use strict";function c(t){return t&&t.__esModule?t:{"default":t}}function h(t,e){if(!(t instanceof e))throw new TypeError("Cannot call a class as a function")}function u(t,e){if(!t)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return!e||"object"!=typeof e&&"function"!=typeof e?t:e}function d(t,e){if("function"!=typeof e&&null!==e)throw new TypeError("Super expression must either be null or a function, not "+typeof e);t.prototype=Object.create(e&&e.prototype,{constructor:{value:t,enumerable:!1,writable:!0,configurable:!0}}),e&&(Object.setPrototypeOf?Object.setPrototypeOf(t,e):t.__proto__=e)}Object.defineProperty(t,"__esModule",{value:!0});var g=c(o),_=c(i),p=c(r),v=c(s),f=c(a),D=c(l),y=function(t){function o(e){h(this,o);var i=u(this,t.call(this,e));return (i.activeDragPlaceholder_=null, i.activeDragSource_=null, i.distanceDragged_=0, i.dragging_=!1, i.dragHandler_=new n.EventHandler, i.dragScrollDelta_=new p["default"], i.mousePos_=null, i.mouseSourceDelta_=null, i.sourceHandler_=new n.EventHandler, i.sourceRegion_=null, i.sourceRelativePos_=null, i.attachSourceEvents_(), i.on(o.Events.DRAG,i.defaultDragFn_,!0), i.on(o.Events.END,i.defaultEndFn_,!0), i.on("sourcesChanged",i.handleSourcesChanged_.bind(i)), i.on("containerChanged",i.handleContainerChanged_.bind(i)), i.dragScrollDelta_.on("scrollDelta",i.handleScrollDelta_.bind(i)), g["default"].on(document,"keydown",i.handleKeyDown_.bind(i)), i)}return (d(o,t), o.prototype.attachSourceEvents_=function(){for(var t={keydown:this.handleSourceKeyDown_.bind(this),mousedown:this.handleDragStartEvent_.bind(this),touchstart:this.handleDragStartEvent_.bind(this)},o=Object.keys(t),i=0;i<o.length;i++){var r=t[o[i]];e.core.isString(this.sources)?this.sourceHandler_.add(g["default"].delegate(this.container,o[i],this.sources,r)):this.sourceHandler_.add(g["default"].on(this.sources,o[i],r))}}, o.prototype.buildEventObject_=function(){return{placeholder:this.activeDragPlaceholder_,source:this.activeDragSource_,relativeX:this.sourceRelativePos_.x,relativeY:this.sourceRelativePos_.y,x:this.sourceRegion_.left,y:this.sourceRegion_.top}}, o.prototype.calculateInitialPosition_=function(t){this.sourceRegion_=e.object.mixin({},f["default"].getRegion(this.activeDragSource_,!0)),this.sourceRelativePos_={x:this.activeDragSource_.offsetLeft,y:this.activeDragSource_.offsetTop},e.core.isDef(t.clientX)&&(this.mousePos_={x:t.clientX,y:t.clientY},this.mouseSourceDelta_={x:this.sourceRegion_.left-this.mousePos_.x,y:this.sourceRegion_.top-this.mousePos_.y})}, o.prototype.canStartDrag_=function(t){return!this.disabled&&(!e.core.isDef(t.button)||0===t.button)&&!this.isDragging()&&this.isWithinHandle_(t.target)}, o.prototype.cleanUpAfterDragging_=function(){this.activeDragPlaceholder_&&(this.activeDragPlaceholder_.setAttribute("aria-grabbed","false"),g["default"].removeClasses(this.activeDragPlaceholder_,this.draggingClass),this.dragPlaceholder===o.Placeholder.CLONE&&g["default"].exitDocument(this.activeDragPlaceholder_)),this.activeDragPlaceholder_=null,this.activeDragSource_=null,this.sourceRegion_=null,this.sourceRelativePos_=null,this.mousePos_=null,this.mouseSourceDelta_=null,this.dragging_=!1,this.dragHandler_.removeAllListeners()}, o.prototype.cloneActiveDrag_=function(){var t=this.activeDragSource_.cloneNode(!0);return (t.style.position="absolute", t.style.left=this.sourceRelativePos_.x+"px", t.style.top=this.sourceRelativePos_.y+"px", g["default"].append(this.activeDragSource_.parentNode,t), t)}, o.prototype.constrain_=function(t){this.constrainToAxis_(t),this.constrainToSteps_(t),this.constrainToRegion_(t)}, o.prototype.constrainToAxis_=function(t){"x"===this.axis?(t.top=this.sourceRegion_.top,t.bottom=this.sourceRegion_.bottom):"y"===this.axis&&(t.left=this.sourceRegion_.left,t.right=this.sourceRegion_.right)}, o.prototype.constrainToRegion_=function(t){var o=this.constrain;o&&(e.core.isFunction(o)?e.object.mixin(t,o(t)):(e.core.isElement(o)&&(o=f["default"].getRegion(o,!0)),t.left<o.left?t.left=o.left:t.right>o.right&&(t.left-=t.right-o.right),t.top<o.top?t.top=o.top:t.bottom>o.bottom&&(t.top-=t.bottom-o.bottom),t.right=t.left+t.width,t.bottom=t.top+t.height))}, o.prototype.constrainToSteps_=function(t){var e=t.left-this.sourceRegion_.left,o=t.top-this.sourceRegion_.top;t.left-=e%this.steps.x,t.right=t.left+t.width,t.top-=o%this.steps.y,t.bottom=t.top+t.height}, o.prototype.createActiveDragPlaceholder_=function(){var t=this.dragPlaceholder;t===o.Placeholder.CLONE?this.activeDragPlaceholder_=this.cloneActiveDrag_():e.core.isElement(t)?this.activeDragPlaceholder_=t:this.activeDragPlaceholder_=this.activeDragSource_}, o.prototype.defaultDragFn_=function(){this.moveToPosition_(this.activeDragPlaceholder_)}, o.prototype.defaultEndFn_=function(){this.moveToPosition_(this.activeDragSource_)}, o.prototype.disposeInternal=function(){this.cleanUpAfterDragging_(),this.dragHandler_=null,this.dragScrollDelta_.dispose(),this.dragScrollDelta_=null,this.sourceHandler_.removeAllListeners(),this.sourceHandler_=null,t.prototype.disposeInternal.call(this)}, o.prototype.getActiveDrag=function(){return this.activeDragSource_}, o.prototype.handleDragEndEvent_=function(){this.autoScroll&&this.autoScroll.stop(),this.dragScrollDelta_.stop(),v["default"].hideDocShim(),this.emit(o.Events.END,this.buildEventObject_()),this.cleanUpAfterDragging_()}, o.prototype.handleDragMoveEvent_=function(t){var e=t.targetTouches?t.targetTouches[0]:t,o=e.clientX-this.mousePos_.x,i=e.clientY-this.mousePos_.y;this.mousePos_.x=e.clientX,this.mousePos_.y=e.clientY,(this.isDragging()||this.hasReachedMinimumDistance_(o,i))&&(this.isDragging()||(this.startDragging_(),this.dragScrollDelta_.start(this.activeDragPlaceholder_,this.scrollContainers)),this.autoScroll&&this.autoScroll.scroll(this.scrollContainers,this.mousePos_.x,this.mousePos_.y),this.updatePositionFromMouse())}, o.prototype.handleDragStartEvent_=function(t){this.activeDragSource_=t.delegateTarget||t.currentTarget,this.canStartDrag_(t)&&(this.calculateInitialPosition_(t.targetTouches?t.targetTouches[0]:t),t.preventDefault(),"keydown"===t.type?this.startDragging_():(this.dragHandler_.add.apply(this.dragHandler_,v["default"].attachDocListeners(this.useShim,{mousemove:this.handleDragMoveEvent_.bind(this),touchmove:this.handleDragMoveEvent_.bind(this),mouseup:this.handleDragEndEvent_.bind(this),touchend:this.handleDragEndEvent_.bind(this)})),this.distanceDragged_=0))}, o.prototype.handleKeyDown_=function(t){27===t.keyCode&&this.isDragging()&&this.handleDragEndEvent_()}, o.prototype.handleScrollDelta_=function(t){this.mouseSourceDelta_.x+=t.deltaX,this.mouseSourceDelta_.y+=t.deltaY,this.updatePositionFromMouse()}, o.prototype.handleSourceKeyDown_=function(t){if(this.isDragging()){var e=t.delegateTarget||t.currentTarget;if(e!==this.activeDragSource_)return;if(t.keyCode>=37&&t.keyCode<=40){var o=0,i=0,r=this.keyboardSpeed>=this.steps.x?this.keyboardSpeed:this.steps.x,s=this.keyboardSpeed>=this.steps.y?this.keyboardSpeed:this.steps.y;37===t.keyCode?o-=r:38===t.keyCode?i-=s:39===t.keyCode?o+=r:i+=s,this.updatePositionFromDelta(o,i),t.preventDefault()}else 13!==t.keyCode&&32!==t.keyCode&&27!==t.keyCode||this.handleDragEndEvent_()}else 13!==t.keyCode&&32!==t.keyCode||this.handleDragStartEvent_(t)}, o.prototype.handleContainerChanged_=function(){e.core.isString(this.sources)&&(this.sourceHandler_.removeAllListeners(),this.attachSourceEvents_()),this.prevScrollContainersSelector_&&(this.scrollContainers=this.prevScrollContainersSelector_)}, o.prototype.handleSourcesChanged_=function(){this.sourceHandler_.removeAllListeners(),this.attachSourceEvents_()}, o.prototype.hasReachedMinimumDistance_=function(t,e){return (this.distanceDragged_+=Math.abs(t)+Math.abs(e), this.distanceDragged_>=this.minimumDragDistance)}, o.prototype.isDragging=function(){return this.dragging_}, o.prototype.isWithinHandle_=function(t){var o=this.handles;return o?e.core.isString(o)?g["default"].match(t,o+", "+o+" *"):g["default"].contains(o,t):!0}, o.prototype.moveToPosition_=function(t){t.style.left=this.sourceRelativePos_.x+"px",t.style.top=this.sourceRelativePos_.y+"px"}, o.prototype.setterAutoScrollFn_=function(t){return t!==!1?new _["default"](t):void 0}, o.prototype.setterConstrainFn=function(t){return (e.core.isString(t)&&(t=g["default"].toElement(t)), t)}, o.prototype.setterScrollContainersFn_=function(t){this.prevScrollContainersSelector_=e.core.isString(t)?t:null;var o=this.toElements_(t);return (o.push(document), o)}, o.prototype.startDragging_=function(){this.dragging_=!0,this.createActiveDragPlaceholder_(),g["default"].addClasses(this.activeDragPlaceholder_,this.draggingClass),this.activeDragPlaceholder_.setAttribute("aria-grabbed","true"),this.emit(o.Events.START)}, o.prototype.toElements_=function(t){if(e.core.isString(t)){var o=this.container.querySelectorAll(t);return Array.prototype.slice.call(o,0)}return t?[t]:[]}, o.prototype.updatePosition=function(t){this.constrain_(t);var e=t.left-this.sourceRegion_.left,i=t.top-this.sourceRegion_.top;0===e&&0===i||(this.sourceRegion_=t,this.sourceRelativePos_.x+=e,this.sourceRelativePos_.y+=i,this.emit(o.Events.DRAG,this.buildEventObject_()))}, o.prototype.updatePositionFromDelta=function(t,o){var i=e.object.mixin({},this.sourceRegion_);i.left+=t,i.right+=t,i.top+=o,i.bottom+=o,this.updatePosition(i)}, o.prototype.updatePositionFromMouse=function(){var t={height:this.sourceRegion_.height,left:this.mousePos_.x+this.mouseSourceDelta_.x,top:this.mousePos_.y+this.mouseSourceDelta_.y,width:this.sourceRegion_.width};t.right=t.left+t.width,t.bottom=t.top+t.height,this.updatePosition(t)}, o.prototype.validateElementOrString_=function(t){return e.core.isString(t)||e.core.isElement(t)}, o.prototype.validatorConstrainFn=function(t){return e.core.isString(t)||e.core.isObject(t)}, o)}(D["default"]);y.STATE={autoScroll:{setter:"setterAutoScrollFn_",value:!1,writeOnce:!0},axis:{validator:e.core.isString},constrain:{setter:"setterConstrainFn",validator:"validatorConstrainFn"},container:{setter:g["default"].toElement,validator:"validateElementOrString_",value:document},disabled:{validator:e.core.isBoolean,value:!1},draggingClass:{validator:e.core.isString,value:"dragging"},dragPlaceholder:{validator:"validateElementOrString_"},handles:{validator:"validateElementOrString_"},keyboardSpeed:{validator:e.core.isNumber,value:10},minimumDragDistance:{validator:e.core.isNumber,value:5,writeOnce:!0},scrollContainers:{setter:"setterScrollContainersFn_",validator:"validateElementOrString_"},sources:{validator:"validateElementOrString_"},steps:{validator:e.core.isObject,valueFn:function(){return{x:1,y:1}}},useShim:{value:!0}},y.Events={DRAG:"drag",END:"end",START:"start"},y.Placeholder={CLONE:"clone"},t["default"]=y});