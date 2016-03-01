define("frontend-js-spa-web@1.0.0/liferay/app/App.es", ['exports', 'senna/src/app/App', 'metal-dom/src/dom', '../util/Utils.es'], function (exports, _App2, _dom, _Utils) {
	'use strict';

	Object.defineProperty(exports, "__esModule", {
		value: true
	});

	var _App3 = _interopRequireDefault(_App2);

	var _dom2 = _interopRequireDefault(_dom);

	var _Utils2 = _interopRequireDefault(_Utils);

	function _interopRequireDefault(obj) {
		return obj && obj.__esModule ? obj : {
			default: obj
		};
	}

	function _classCallCheck(instance, Constructor) {
		if (!(instance instanceof Constructor)) {
			throw new TypeError("Cannot call a class as a function");
		}
	}

	function _possibleConstructorReturn(self, call) {
		if (!self) {
			throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
		}

		return call && (typeof call === "object" || typeof call === "function") ? call : self;
	}

	function _inherits(subClass, superClass) {
		if (typeof superClass !== "function" && superClass !== null) {
			throw new TypeError("Super expression must either be null or a function, not " + typeof superClass);
		}

		subClass.prototype = Object.create(superClass && superClass.prototype, {
			constructor: {
				value: subClass,
				enumerable: false,
				writable: true,
				configurable: true
			}
		});
		if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass;
	}

	var LiferayApp = function (_App) {
		_inherits(LiferayApp, _App);

		function LiferayApp() {
			_classCallCheck(this, LiferayApp);

			var _this = _possibleConstructorReturn(this, _App.call(this));

			_this.blacklist = {};
			_this.validStatusCodes = [];

			var exceptionsSelector = ':not([target="_blank"]):not([data-senna-off]):not([data-resource-href])';

			_this.setFormSelector('form' + exceptionsSelector);
			_this.setLinkSelector('a' + exceptionsSelector);
			_this.setLoadingCssClass('lfr-surface-loading');

			_this.on('beforeNavigate', _this.onBeforeNavigate);
			_this.on('endNavigate', _this.onEndNavigate);
			_this.on('startNavigate', _this.onStartNavigate);

			Liferay.on('io:complete', _this.onLiferayIOComplete, _this);

			_this.addSurfaces(document.body.id);

			_dom2.default.append(document.body, '<div class="lfr-surface-loading-bar"></div>');
			return _this;
		}

		LiferayApp.prototype.getValidStatusCodes = function getValidStatusCodes() {
			return this.validStatusCodes;
		};

		LiferayApp.prototype.onBeforeNavigate = function onBeforeNavigate(event) {
			if (event.form) {
				this.clearScreensCache();
			}

			Liferay.fire('beforeNavigate', {
				app: this,
				path: event.path
			});
		};

		LiferayApp.prototype.onDocClickDelegate_ = function onDocClickDelegate_(event) {
			var inBlacklist = false;

			Object.keys(this.blacklist).map(function (portletId) {
				var boundaryId = _Utils2.default.getPortletBoundaryId(portletId);
				var portlets = document.querySelectorAll('[id^="' + boundaryId + '"]');

				Array.prototype.slice.call(portlets).forEach(function (portlet) {
					if (_dom2.default.contains(portlet, event.delegateTarget)) {
						inBlacklist = true;
						return;
					}
				});
			});

			if (inBlacklist) {
				return;
			}

			_App.prototype.onDocClickDelegate_.call(this, event);
		};

		LiferayApp.prototype.onEndNavigate = function onEndNavigate(event) {
			Liferay.fire('endNavigate', {
				app: this,
				error: event.error,
				path: event.path
			});

			if (event.error) {
				if (event.error.invalidStatus || event.error.requestError) {
					if (event.form) {
						event.form.submit();
					} else {
						window.location.href = event.path;
					}
				}
			} else if (Liferay.Layout && Liferay.Data.layoutConfig) {
				Liferay.Layout.init();
			}

			AUI().Get._insertCache = {};
		};

		LiferayApp.prototype.onLiferayIOComplete = function onLiferayIOComplete() {
			this.clearScreensCache();
		};

		LiferayApp.prototype.onStartNavigate = function onStartNavigate(event) {
			Liferay.fire('startNavigate', {
				app: this,
				path: event.path
			});
		};

		LiferayApp.prototype.setBlacklist = function setBlacklist(blacklist) {
			this.blacklist = blacklist;
		};

		LiferayApp.prototype.setValidStatusCodes = function setValidStatusCodes(validStatusCodes) {
			this.validStatusCodes = validStatusCodes;
		};

		return LiferayApp;
	}(_App3.default);

	LiferayApp.prototype.registerMetalComponent && LiferayApp.prototype.registerMetalComponent(LiferayApp, 'LiferayApp')
	exports.default = LiferayApp;
});
//# sourceMappingURL=App.es.js.map