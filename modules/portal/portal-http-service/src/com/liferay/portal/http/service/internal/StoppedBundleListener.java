/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.http.service.internal;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.SynchronousBundleListener;

/**
 * @author Raymond Augé
 * @author Miguel Pastor
 */
public class StoppedBundleListener implements SynchronousBundleListener {

	public StoppedBundleListener(WebBundleDeployer webBundleDeployer) {
		_webBundleDeployer = webBundleDeployer;
	}

	@Override
	public void bundleChanged(BundleEvent bundleEvent) {
		int type = bundleEvent.getType();

		Bundle bundle = bundleEvent.getBundle();

		String servletContextName = WabUtil.getWebContextName(bundle);

		if (Validator.isNull(servletContextName)) {
			return;
		}

		try {
			if ((type & BundleEvent.STOPPED) == BundleEvent.STOPPED) {
				_webBundleDeployer.doStop(bundle);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		StoppedBundleListener.class);

	private WebBundleDeployer _webBundleDeployer;

}