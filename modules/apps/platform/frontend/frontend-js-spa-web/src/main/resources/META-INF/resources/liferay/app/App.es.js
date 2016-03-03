'use strict';

import App from 'senna/src/app/App';
import dom from 'metal-dom/src/dom';
import Utils from '../util/Utils.es';
import LiferaySurface from '../surface/Surface.es';

class LiferayApp extends App {
	constructor() {
		super();

		this.blacklist = {};
		this.validStatusCodes = [];

		var exceptionsSelector = ':not([target="_blank"]):not([data-senna-off]):not([data-resource-href])';

		this.setFormSelector('form' + exceptionsSelector);
		this.setLinkSelector('a' + exceptionsSelector);
		this.setLoadingCssClass('lfr-surface-loading');

		this.on('beforeNavigate', this.onBeforeNavigate);
		this.on('endNavigate', this.onEndNavigate);
		this.on('startNavigate', this.onStartNavigate);

		Liferay.on('io:complete', this.onLiferayIOComplete, this);

		this.addSurfaces(new LiferaySurface(document.body.id));

		dom.append(document.body, '<div class="lfr-surface-loading-bar"></div>');
	}

	getValidStatusCodes() {
		return this.validStatusCodes;
	}

	onBeforeNavigate(event) {
		if (Liferay.SPA.clearScreensCache || event.form) {
			this.clearScreensCache();
		}

		Liferay.fire(
			'beforeNavigate',
			{
				app: this,
				path: event.path
			}
		);
	}

	onDocClickDelegate_(event) {
		var inBlacklist = false;

		Object.keys(this.blacklist).map(
			(portletId) => {
				var boundaryId = Utils.getPortletBoundaryId(portletId);
				var portlets = document.querySelectorAll('[id^="' + boundaryId +  '"]');

				Array.prototype.slice.call(portlets).forEach(
					(portlet) => {
						if (dom.contains(portlet, event.delegateTarget)) {
							inBlacklist = true;
							return;
						}
					}
				);
			}
		);

		if (inBlacklist) {
			return;
		}

		super.onDocClickDelegate_(event);
	}

	onEndNavigate(event) {
		Liferay.fire(
			'endNavigate',
			{
				app: this,
				error: event.error,
				path: event.path
			}
		);

		if (event.error) {
			if (event.error.invalidStatus || event.error.requestError) {
				if (event.form) {
					event.form.submit();
				}
				else {
					window.location.href = event.path;
				}
			}
		}
		else if (Liferay.Layout && Liferay.Data.layoutConfig) {
			Liferay.Layout.init();
		}

		AUI().Get._insertCache = {};

		Liferay.DOMTaskRunner.reset();
	}

	onLiferayIOComplete() {
		this.clearScreensCache();
	}

	onStartNavigate(event) {
		Liferay.fire(
			'startNavigate',
			{
				app: this,
				path: event.path
			}
		);
	}

	setBlacklist(blacklist) {
		this.blacklist = blacklist;
	}

	setValidStatusCodes(validStatusCodes) {
		this.validStatusCodes = validStatusCodes;
	}
}

export default LiferayApp;